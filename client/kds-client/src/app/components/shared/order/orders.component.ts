import { ReturnStatement } from '@angular/compiler';
import { Component, Input, OnInit } from '@angular/core';
import { OrderStatus } from 'src/app/models/food-order-status.model';
import { FoodOrder } from 'src/app/models/food-order.model';
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

  constructor(private orderService: OrderService) {}

  public async changeOrderStatusFromGivenOrder(
    order: FoodOrder,
    orderStatus: unknown
  ) {
    const orderStatusLiteral = this.getStringLiteralFromUnkown(orderStatus);
    if (orderStatusLiteral == 'COMPLETE') {
      const userInput = this.warnUserAboutCompletingOrder(order);

      if ((await userInput).isDenied || (await userInput).isDismissed) {
        return;
      }
    }

    order.foodOrderStatus = orderStatusLiteral;
    this.orderService.updateOrder(order).subscribe({
      next: (response) => {
        switch (response.body?.foodOrderStatus) {
          case 'COMPLETE':
            Swal.fire({
              position: 'top-end',
              icon: 'success',
              title: `Pedido #${response.body.id} foi finalizado!`,
              text: 'O pedido foi finalizado! Você pode removê-lo desta página clicando no botão "Remover".',
              showConfirmButton: true,
              timer: 3500,
            });
            break;
        }
      },
      error: (error) => console.log(error),
    });
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

  // TODO: The array is ordered, a binary search can be done
  private deleteOrderFromArray(order: FoodOrder) {
    this.foodOrders.forEach((item, i) => {
      if (item == order) {
        this.foodOrders.splice(i, 1);
      }
    });
  }

  private async warnUserAboutCompletingOrder(order: FoodOrder) {
    const userInput = await Swal.fire({
      title: `Finalizar pedido ${order.id}?`,
      text: `Você está finzalidando o pedido ${order.id}, está ação não é reversível e você não poderá mais interagir com esse pedido.`,
      showDenyButton: true,
      showCancelButton: true,
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
