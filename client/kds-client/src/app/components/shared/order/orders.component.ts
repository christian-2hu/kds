import { Component, Input, OnInit } from '@angular/core';
import { OrderStatus } from 'src/app/models/food-order-status.model';
import { FoodOrder } from 'src/app/models/food-order.model';
import { OrderService } from 'src/app/services/order/order.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css'],
})
export class OrdersComponent {
  public orderStatus = OrderStatus;
  @Input({ required: true }) public foodOrders: FoodOrder[] = [];

  constructor() {}
}
