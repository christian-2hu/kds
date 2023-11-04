package com.scheduler.services;

import java.util.List;

import com.scheduler.models.ifood.IfoodOrderStatus;
import com.scheduler.models.ifood.Order;

public interface DeliveryService<T> {
    public List<T> getOrders();
    public <S> S getOrder(String orderId, Class<S> elementClass);
    public void confirmOrder(String orderId);
    public void prepareOrder(String orderId);
    public void finishOrder(String orderId);
    public <U> void cancelOrder(String orderId, U cancelDetails);
    public void acknowledgeOrders(String... ids);
    //TODO: Change the D generic inheritance for a more generic object 
    public <D extends IfoodOrderStatus> Order convertToOrder(D deliveryOrder);
}
