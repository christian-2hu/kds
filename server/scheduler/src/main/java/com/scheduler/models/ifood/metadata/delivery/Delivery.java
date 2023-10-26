package com.scheduler.models.ifood.metadata.delivery;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Delivery {
    // TODO: Create an enum for this
    private String mode;
    // TODO: Create an enum for this
    private String deliveredBy;
    private Date expectedDeliveryDateTime;
    private String observations;
    private DeliveryAddress deliveryAddress;
    
}
