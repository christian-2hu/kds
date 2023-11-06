package com.scheduler.scheduler.ifood;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.scheduler.messaging.consumers.order.OrderEvent;
import com.scheduler.messaging.producers.order.OrderMessageProducer;
import com.scheduler.models.ifood.IfoodEventPolling;
import com.scheduler.models.ifood.IfoodOrderStatus;
import com.scheduler.models.ifood.Order;
import com.scheduler.scheduler.DeliveryScheduler;
import com.scheduler.services.DeliveryService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class IfoodDeliveryScheduler extends DeliveryScheduler {
  
  private final DeliveryService<IfoodEventPolling> ifooDeliveryService;
  private final OrderMessageProducer orderMessageProducer;

  @Override
  @Scheduled(fixedDelayString = "${ifood.production.eventPollingDelayMilliseconds}")
  public void fetchOrders() throws InterruptedException {
    List<IfoodEventPolling> orders = ifooDeliveryService.getOrders();
    if(orders == null) {
      return;
    }
    LOGGER.info(String.format("%s orders were found on the polling", orders.size()));
    emmitOrder(orders);
  }

  @Override
  public <T> void emmitOrderEvent(T order, String routingKey) {
    orderMessageProducer.sendMessage(order, routingKey);
  }

  private void emmitOrder(List<IfoodEventPolling> orders) {
    for (IfoodEventPolling order : orders) {
      IfoodOrderStatus ifoodOrder = getIfoodOrderFromPolling(order);
      Order orderToEmmit = ifooDeliveryService.convertToOrder(ifoodOrder);
      switch (order.getFullCode()) {
        case CANCELLED:
          Order canceledOrderToEmit = ifooDeliveryService.convertToOrder(ifoodOrder);
          OrderEvent event = new OrderEvent(canceledOrderToEmit.getIfoodOrderId(), canceledOrderToEmit.getFoodOrderStatus(), null, null); 
          emmitOrderEvent(event, "order.server.canceled");
          break;
        case PLACED:
          emmitOrderEvent(orderToEmmit, "order.created");
          break;
        default:
          LOGGER.info("Not supported yet: " + orderToEmmit.toString());
          break;
      }
      acknowledgeOrder(order);
    }
  } 

  private IfoodOrderStatus getIfoodOrderFromPolling(IfoodEventPolling order) {
    IfoodOrderStatus ifoodOrder = ifooDeliveryService.getOrder(order.getOrderId(), IfoodOrderStatus.class);
    ifoodOrder.setFullCode(order.getFullCode());
    return ifoodOrder;
  }

  private void acknowledgeOrder(IfoodEventPolling order) {
    ifooDeliveryService.acknowledgeOrders(order.getId());
  }

}

