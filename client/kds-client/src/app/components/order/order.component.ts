import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FoodOrder } from 'src/app/models/food-order.model';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
})
export class OrderComponent {
  public orders: FoodOrder[] = [];

  constructor(private activatedRoute: ActivatedRoute) {}

  public ngOnInit(): void {
    this.activatedRoute.data.subscribe((data) => {
      let orders: FoodOrder[] = data['data'];
      this.orders = orders;
    });
  }
}
