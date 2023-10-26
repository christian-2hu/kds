package com.scheduler.models.ifood.metadata.costumer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Costumer {
    private String id;
    private String name;
    private String documentNumber;
    private Phone phone;
    private String segmentation;
}
