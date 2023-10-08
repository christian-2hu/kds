import { Component, OnInit } from '@angular/core';
import { OrderStatus } from 'src/app/models/food-order-status.model';
import { FoodOrder } from 'src/app/models/food-order.model';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
})
export class OrderComponent implements OnInit {
  public orderStatus = OrderStatus;
  public orders: FoodOrder[] = [];

  public ngOnInit(): void {}
}
