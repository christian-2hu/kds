import { FoodItem } from './food-item.model';
import { OrderStatus } from './food-order-status.model';

export interface FoodOrder {
  id: number;
  foodItem: FoodItem[];
  quantity: number[];
  foodOrderStatus: OrderStatus;
  observation: string;
}
