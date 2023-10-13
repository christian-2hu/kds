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

  public getArchivedOrders() {
    return this.http.get<FoodOrder[]>(`${Environment.api}/order/archive`);
  }

  public addOrder(order: FoodOrder) {
    return this.http.post<Response<FoodOrder>>(
      `${Environment.api}/order`,
      order
    );
  }

  public updateOrder(order: FoodOrder) {
    const id = order.id;
    return this.http.put<Response<FoodOrder>>(
      `${Environment.api}/order/${id}`,
      order
    );
  }

  public deleteOrder(order: FoodOrder) {
    const id = order.id;
    return this.http.delete<Response<FoodOrder>>(
      `${Environment.api}/order/${id}`
    );
  }
}
