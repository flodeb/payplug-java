package com.flodeb.payplug.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flodeb.payplug.model.enums.FailureCode;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Failure {
    private FailureCode code;

    private String message;
}
