import { ReturnStatement } from '@angular/compiler';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PaginatorState } from 'primeng/paginator';
import { Observable } from 'rxjs';
import { OrderStatus } from 'src/app/models/food-order-status.model';
import { FoodOrder } from 'src/app/models/food-order.model';
import { PaginatedContentResponse } from 'src/app/models/paginated-content-response.model';
import { PaginationResponse } from 'src/app/models/pagination-response.model';
import { OrderService } from 'src/app/services/order/order.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css'],
})
export class OrdersComponent {
  public orderStatus = OrderStatus;
  @Input({ required: true }) public foodOrders: FoodOrder[] = [];
  @Input({ required: true }) public ordersPagination!: PaginationResponse;

  constructor(private orderService: OrderService, private router: Router) {}

  public async onChangeOrderStatus(
    order: FoodOrder,
    optionalOrderStatus?: OrderStatus
  ) {
    if (order.foodOrderStatus == 'COMPLETE') {
      const userInput = this.warnUserAboutCompletingOrder(order);
      if ((await userInput).isDenied || (await userInput).isDismissed) {
        return;
      }
    }
    let orderStatus = this.getOrderStatusFromStringLiteral(
      order.foodOrderStatus
    );
    let orderStatusToLiteral =
      optionalOrderStatus != undefined
        ? optionalOrderStatus
        : this.changeOrderStatus(orderStatus);

    order.foodOrderStatus = this.getStringLiteralFromUnkown(
      OrderStatus[orderStatusToLiteral]
    );
    this.updateOrder(order);
  }

  public removeOrder(order: FoodOrder) {
    this.deleteOrderFromArray(order);
    this.orderService.deleteOrder(order).subscribe({
      next: () => {
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: `Pedido removido!`,
          text: 'O pedido foi removido! Você pode consultá-lo no arquivo.',
          showConfirmButton: true,
          timer: 3500,
        });
      },
      error: (error) => console.log(error),
    });
  }

  public onPageChange(pageEvent: PaginatorState) {
    let ordersObservable: Observable<PaginatedContentResponse<FoodOrder[]>>;
    ordersObservable = this.orderService.getOrders(pageEvent.page);

    if (this.router.url == '/archive') {
      ordersObservable = this.orderService.getArchivedOrders(pageEvent.page);
    }

    ordersObservable.subscribe({
      next: (response) => {
        this.foodOrders = response.content;
      },
      error: (error) => console.log(error),
    });
  }

  public changeOrderStatus(orderStatus: OrderStatus) {
    switch (orderStatus) {
      case OrderStatus.WAITING:
        return OrderStatus.PREPARING;
      case OrderStatus.PREPARING:
        return OrderStatus.COMPLETE;
      default:
        return OrderStatus.WAITING;
    }
  }

  public getOrderStatusFromStringLiteral(
    literal: 'WAITING' | 'PREPARING' | 'COMPLETE' | 'CANCELED'
  ) {
    switch (literal) {
      case 'WAITING':
        return OrderStatus.WAITING;
      case 'PREPARING':
        return OrderStatus.PREPARING;
      case 'COMPLETE':
        return OrderStatus.COMPLETE;
      case 'CANCELED':
        return OrderStatus.CANCELED;
      default:
        throw new Error('Could not get literal');
    }
  }

  // TODO: The array is ordered, a binary search can be done
  private deleteOrderFromArray(order: FoodOrder) {
    this.foodOrders.forEach((item, i) => {
      if (item == order) {
        this.foodOrders.splice(i, 1);
      }
    });
  }

  private updateOrder(order: FoodOrder) {
    this.orderService.updateOrder(order).subscribe({
      next: (response) => {
        let updatedOrder: FoodOrder = response.data as FoodOrder;
        console.log(updatedOrder);
        switch (updatedOrder.foodOrderStatus) {
          case 'COMPLETE':
            this.deleteOrderFromArray(order);
            Swal.fire({
              position: 'top-end',
              icon: 'success',
              title: `Pedido #${updatedOrder.id} foi finalizado!`,
              text: 'O pedido foi removido desta página, você pode vê-lo novamente no arquivo.',
              showConfirmButton: true,
              timer: 3500,
            });
            break;
        }
      },
      error: (error) => console.log(error),
    });
  }

  private async warnUserAboutCompletingOrder(order: FoodOrder) {
    const userInput = await Swal.fire({
      title: `Finalizar pedido ${order.id}?`,
      text: `Você está finzalidando o pedido ${order.id}, está ação não é reversível e você não poderá mais interagir com esse pedido.`,
      showDenyButton: true,
      confirmButtonText: 'Finalizar',
      denyButtonText: `Não finalizar`,
    });
    return userInput;
  }

  private getStringLiteralFromUnkown(
    literal: unknown
  ): 'WAITING' | 'PREPARING' | 'COMPLETE' | 'CANCELED' {
    switch (literal) {
      case 'WAITING':
        return 'WAITING';
      case 'PREPARING':
        return 'PREPARING';
      case 'COMPLETE':
        return 'COMPLETE';
      case 'CANCELED':
        return 'CANCELED';
    }
    throw new Error('Could not get literal');
  }
}
