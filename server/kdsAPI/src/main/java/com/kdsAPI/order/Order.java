package com.kdsAPI.order;

public interface Order {
    public Long getId();
    public void setId(Long id);
    public OrderStatus getFoodOrderStatus();
    public void setFoodOrderStatus(OrderStatus orderStatus);
    public String getFoodOrder();
    public void setFoodOrder(String order);
}
