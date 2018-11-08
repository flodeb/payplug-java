package com.flodeb.payplug.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class HostedPayment {
    private String paymentUrl;

    private String returnUrl;

    private String cancelUrl;

    private Long paidAt;
}
