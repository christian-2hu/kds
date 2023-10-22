package com.scheduler.scheduler.ifood;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.scheduler.models.ifood.IfoodEventPolling;
import com.scheduler.scheduler.DeliveryScheduler;
import com.scheduler.services.DeliveryService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class IfoodDeliveryScheduler extends DeliveryScheduler {
  
  private final DeliveryService<IfoodEventPolling> ifooDeliveryService;

  @Override
  @Scheduled(fixedDelayString = "${ifood.production.eventPollingDelayMilliseconds}")
  public void fetchOrders() throws InterruptedException {
    List<IfoodEventPolling> orders = ifooDeliveryService.getOrders();

    if(orders == null) {
      return;
    }

    LOGGER.info(String.format("%s orders were found on the polling", orders.size()));

    // TODO: Emmit event with found orders

    // After emmiting, acknowledge it
    for (IfoodEventPolling eventPolling : orders) {
      ifooDeliveryService.acknowledgeOrder(eventPolling.getId());
    }
  }

  @Override
  public void emmitOrderEvent() {

  }


}

