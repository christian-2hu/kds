package com.scheduler.services;

public interface DeliveryService<T> {
    public T[] getOrders();
    public void confirmOrder(String orderId);
    public void prepareOrder(String orderId);
    public void finishOrder(String orderId);
    public void acknowledgeOrder(String orderId);
}
