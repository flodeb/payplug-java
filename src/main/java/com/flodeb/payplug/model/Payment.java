package com.flodeb.payplug.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Payment extends Resource {
    private Integer amountRefunded;

    private Boolean isPaid;

    private Boolean isRefunded;

    private Boolean is3ds;

    private Boolean saveCard;

    private Boolean allowSaveCard;

    private Customer customer;

    private HostedPayment hostedPayment;

    private String notificationUrl;

    private Notification notification;

    private Failure failure;
}
