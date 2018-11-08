package com.flodeb.payplug.core;

public class PayplugConfiguration {

    private String token;

    public PayplugConfiguration(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
