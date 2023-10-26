package com.scheduler.models.ifood.metadata.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Item {
    // private String id;
    private String uniqueId;
    private String name;
    // TODO: Create an enum for this
    private String unit;
    private Double quantity;
    private Double unitPrice;
    private Double optionsPrice;
    private Double totalPrice;
    private Double price;
}
