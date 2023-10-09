import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Environment } from 'src/app/environment/environment';
import { FoodOrder } from 'src/app/models/food-order.model';
import { Response } from 'src/app/models/server-response.model';
@Injectable({
  providedIn: 'root',
})
export class OrderService {
  constructor(private http: HttpClient) {}

  public getOrders() {
    return this.http.get<FoodOrder[]>(`${Environment.api}/order`);
  }

  public addOrder(order: FoodOrder) {
    return this.http.post<Response<FoodOrder>>(
      `${Environment.api}/order`,
      order
    );
  }
}
