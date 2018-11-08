package com.flodeb.payplug.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flodeb.payplug.model.enums.Currency;
import com.flodeb.payplug.model.enums.Type;
import lombok.Data;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Resource {
    private String id;

    private Type object;

    private Boolean isLive;

    private Integer amount;

    private Currency currency;

    private Long createdAt;

    private Map<String, Object> metadata;
}
