package com.flodeb.payplug.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Customer {
    private String firstName;

    private String lastName;

    private String email;

    private String address1;

    private String address2;

    private String postcode;

    private String city;

    private String country;
}
