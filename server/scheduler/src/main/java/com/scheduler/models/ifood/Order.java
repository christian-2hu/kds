package com.scheduler.models.ifood;

import java.util.Date;
import java.util.List;

import com.scheduler.models.ifood.metadata.delivery.DeliveryAddress;
import com.scheduler.models.ifood.metadata.item.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private Long id;
    private String costumerName;
    private List<Item> orders;
    private OrderStatus foodOrderStatus;
    private String ifoodOrderId;
    private Date createdAt;
    private String observations;
    private DeliveryAddress address;
}
