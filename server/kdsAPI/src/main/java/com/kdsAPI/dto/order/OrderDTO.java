package com.kdsAPI.dto.order;


import java.util.Date;
import java.util.List;

import com.kdsAPI.dto.DTO;
import com.kdsAPI.models.FoodItem;
import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.models.order.Order;
import com.kdsAPI.models.order.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO extends Order implements DTO<FoodOrder>{

    public OrderDTO() {
        super();
    }

    public OrderDTO(Long id, OrderStatus fooOrderStatus) {
        super(id, fooOrderStatus);
    }

    public OrderDTO(
        Long id, 
        String costumerName, 
        List<FoodItem> orders, 
        OrderStatus orderStatus, 
        String ifoodOrderId, 
        Date createdAt, 
        String observation
    ) {
        super(id, costumerName, orders, orderStatus, ifoodOrderId, createdAt, observation);
    }


    @Override
    public FoodOrder convertToDAO() {
        return new FoodOrder(id, costumerName, orders, foodOrderStatus, ifoodOrderId, createdAt, observations);
    }

    @Override
    public DTO<FoodOrder> convertToDTO(FoodOrder dao) {
        if(dao == null) {
            throw new NullPointerException("FoodOrder dao cannot be null");
        }
        this.id = dao.getId();
        this.costumerName = dao.getCostumerName();
        this.orders = dao.getOrders();
        this.foodOrderStatus = dao.getFoodOrderStatus();
        this.ifoodOrderId = dao.getIfoodOrderId();
        this.createdAt = dao.getCreatedAt();
        this.observations = dao.getObservations();
        return this;
    }
}
