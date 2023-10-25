package com.scheduler.models.ifood;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private Long id;
    private Map<String, Integer> order;
    private List<Integer> quantity;
    private OrderStatus foodOrderStatus;
}
