import { Component, Input, OnInit } from '@angular/core';
import { OrderStatus } from 'src/app/models/food-order-status.model';
import { FoodOrder } from 'src/app/models/food-order.model';
import { OrderService } from 'src/app/services/order/order.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css'],
})
export class OrdersComponent implements OnInit {
  public orderStatus = OrderStatus;
  @Input({ required: true }) public foodOrders: FoodOrder[] = [];

  constructor() {}

  public ngOnInit(): void {
    // this.orders = [
    //   {
    //     id: 1,
    //     foodItem: [
    //       {
    //         id: 1,
    //         item: 'Burgão de frango',
    //         recipe:
    //           'Exemplo teste de igredientes que vai no burgão de frango, teste teste teste teste teste teste teste teste teste ',
    //       },
    //       {
    //         id: 2,
    //         item: 'Burgão de Picanha',
    //         recipe:
    //           'Exemplo teste de igredientes que vai no burgão de frango, teste teste teste teste teste teste teste teste teste ',
    //       },
    //       {
    //         id: 3,
    //         item: 'Burgão de bisteca',
    //         recipe:
    //           'Exemplo teste de igredientes que vai no burgão de frango, teste teste teste teste teste teste teste teste teste ',
    //       },
    //       {
    //         id: 4,
    //         item: 'Coca cola',
    //         recipe: '',
    //       },
    //     ],
    //     quantity: [2, 2, 1, 3],
    //     foodOrderStatus: OrderStatus.WAITING,
    //     observation:
    //       'Não acrescente cebola, ou qualquer viadagem que o cliente pedir,isso aqui só vai aparecer se o cliente escrever alguma coisa',
    //   },
    //   {
    //     id: 2,
    //     foodItem: [
    //       {
    //         id: 1,
    //         item: 'Burgão de frango',
    //         recipe:
    //           'Exemplo teste de igredientes que vai no burgão de frango, teste teste teste teste teste teste teste teste teste ',
    //       },
    //       {
    //         id: 2,
    //         item: 'Burgão de Picanha',
    //         recipe:
    //           'Exemplo teste de igredientes que vai no burgão de frango, teste teste teste teste teste teste teste teste teste ',
    //       },
    //       {
    //         id: 3,
    //         item: 'Burgão de bisteca',
    //         recipe:
    //           'Exemplo teste de igredientes que vai no burgão de frango, teste teste teste teste teste teste teste teste teste ',
    //       },
    //       {
    //         id: 4,
    //         item: 'Coca cola',
    //         recipe: '',
    //       },
    //     ],
    //     quantity: [2, 2, 1, 3],
    //     foodOrderStatus: OrderStatus.WAITING,
    //     observation:
    //       'Não acrescente cebola, ou qualquer viadagem que o cliente pedir,isso aqui só vai aparecer se o cliente escrever alguma coisa',
    //   },
    // ];
    // let order: FoodOrder = {
    //   foodItem: [
    //     {
    //       id: 1,
    //       item: 'burgão de frango',
    //     },
    //   ],
    //   quantity: [1],
    //   foodOrderStatus: this.orderStatus.WAITING,
    // };
    // this.orderService.getOrders().subscribe({
    //   next: (response) => {
    //     console.log(response);
    //   },
    //   error: (error) => console.log(error),
    // });
  }
}
