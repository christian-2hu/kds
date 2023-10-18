import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FoodOrder } from 'src/app/models/food-order.model';
import { PaginatedContentResponse } from 'src/app/models/paginated-content-response.model';
import { PaginationResponse } from 'src/app/models/pagination-response.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
})
export class OrderComponent implements OnInit {
  public orders: FoodOrder[] = [];
  public ordersPagination!: PaginationResponse;

  constructor(private activatedRoute: ActivatedRoute) {}

  public ngOnInit(): void {
    this.activatedRoute.data.subscribe((data) => {
      let response: PaginatedContentResponse<FoodOrder[]> = data['data'];
      if (response.error != undefined) {
        this.onServerError(response.error);
      }
      this.orders = response.content;
      this.ordersPagination = response.pagination;
    });
  }

  private onServerError(error: string): void {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: `${error}`,
    });
  }
}
