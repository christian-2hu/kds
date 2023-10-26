package com.scheduler.models.ifood.metadata.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DeliveryAddress {
    private String streetName;
    private String streetNumber;
    private String formattedAddress;
    private String neighborhood;
    private String complement;
    private String postalCode;
    private String city;
    private String state;
}
