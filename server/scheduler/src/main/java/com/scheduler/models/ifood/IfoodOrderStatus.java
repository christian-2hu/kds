package com.scheduler.models.ifood;

import java.util.Date;

import com.scheduler.models.ifood.metadata.costumer.Costumer;
import com.scheduler.models.ifood.metadata.delivery.Delivery;
import com.scheduler.models.ifood.metadata.item.Item;
import com.scheduler.models.ifood.metadata.payment.Payment;
import com.scheduler.models.ifood.metadata.total.Total;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class IfoodOrderStatus {
    private String id;
    private Delivery delivery;
    private IfoodOrderCode fullCode;
    // TODO: Create an enum for this
    private String orderType;
    // TODO: Create an enum for this
    private String orderTiming;
    private Date createdAt;
    private Date preparationStartDateTime;
    private Boolean isTest;
    private Costumer costumer;
    private Item[] items;
    private Total total;
    private Payment payments;
}
