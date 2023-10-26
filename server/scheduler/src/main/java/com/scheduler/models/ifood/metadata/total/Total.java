package com.scheduler.models.ifood.metadata.total;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Total {
    private Double subTotal;
    private Double deliveryFee;
    private Double benefits;
    private Double orderAmount;
    private Double additionalFees;
}
