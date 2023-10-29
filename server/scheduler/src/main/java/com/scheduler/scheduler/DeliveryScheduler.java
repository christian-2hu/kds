package com.scheduler.scheduler;

import java.util.logging.Logger;

import com.scheduler.models.ifood.Order;

public abstract class DeliveryScheduler {
    protected final Logger LOGGER = Logger.getLogger(DeliveryScheduler.class.getName());
  
    public abstract void fetchOrders() throws InterruptedException;
    public abstract void emmitOrderEvent(Order order, String routingKey);

}