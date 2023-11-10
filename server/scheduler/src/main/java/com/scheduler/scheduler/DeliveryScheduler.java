package com.scheduler.scheduler;

import java.util.logging.Logger;

public abstract class DeliveryScheduler {
    protected final Logger LOGGER = Logger.getLogger(DeliveryScheduler.class.getName());
  
    public abstract void fetchOrders() throws InterruptedException;
    public abstract <T> void emmitOrderEvent(T order, String routingKey);

}