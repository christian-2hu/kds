package com.scheduler.models.ifood;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IfoodEventPolling {
    private String id;
    private String code;
    private String fullCode;
    private String orderId;
    private String merchantId;
    private Date createdAt;
}
