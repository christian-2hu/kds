package com.scheduler.models.ifood.metadata.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Payment {
    private Double prepaid;
    private Double pending;
    
}
