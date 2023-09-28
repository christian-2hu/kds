package com.kdsAPI.order;

public interface Order {
    public Long getId();
    public void setId(Long id);
    public String getFoodOrderStatus();
    public void setFoodOrderStatus(String orderStatus);
    public String getFoodOrder();
    public void setFoodOrder(String order);
}
