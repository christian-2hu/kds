package com.scheduler.models.ifood;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class IfoodRequestCancellation {
    // TODO: transform this into an enum
    private String cancellationCode;
    private String reason;
}
