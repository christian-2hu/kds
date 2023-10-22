package com.scheduler.models.ifood;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Order {
    private Long id;
    private Map<String, Integer> order;
    private List<Integer> quantity;
    private OrderStatus foodOrderStatus;
}
