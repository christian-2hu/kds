package com.kdsAPI.dto.order;

import com.kdsAPI.dto.DTO;
import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.order.Order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class OrderDTO implements DTO<FoodOrder>, Order{

    private final String notEmptyMessage = "This field cannot be empty";
    private final String nullMessage = "This field should be empty";
    
    @Null(message = nullMessage)
    private Long id;
    @NotEmpty(message = notEmptyMessage)
    // TODO: create an object for an order instead of using a string
    private String foodOrder;
    @NotEmpty(message = notEmptyMessage)
    // TODO: create an object for order status instead of using a string
    private String foodOrderStatus;

    public OrderDTO(Long id, String order) {
        this.id = id;
        this.foodOrder = order;
    }

    @Override
    public FoodOrder convertToDAO() {
        return new FoodOrder(id, foodOrder, foodOrderStatus);
    }
}
