package com.kdsAPI.models;

import jakarta.validation.constraints.NotEmpty;
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
public class CanceledOrderDetails {
    // TODO: transform this into an enum
    @NotEmpty(message = "\"This field cannot be empty\"")
    protected String cancellationCode;
    @NotEmpty(message = "\"This field cannot be empty\"")
    protected String reason;
}
