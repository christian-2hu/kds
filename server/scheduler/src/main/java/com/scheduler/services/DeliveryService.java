package com.scheduler.services;

import java.util.List;

public interface DeliveryService<T> {
    public List<T> getOrders();
    public void confirmOrder(String orderId);
    public void prepareOrder(String orderId);
    public void finishOrder(String orderId);
    public void acknowledgeOrder(String orderId);
}
