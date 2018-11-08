package com.flodeb.payplug.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Refund extends Resource {
    private String paymentId;

    private Integer amount;
}
