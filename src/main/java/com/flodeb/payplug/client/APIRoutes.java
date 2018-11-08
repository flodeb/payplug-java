package com.flodeb.payplug.client;

import org.springframework.util.StringUtils;

public interface APIRoutes {
    String API_BASE_URL = "https://api.payplug.com";

    Integer API_VERSION = 1;

    // Parameters
    String PAYMENT_ID = "PAYMENT_ID";

    // Resources routes
    String PAYMENT_RESOURCE    = "/payments";
    String REFUND_RESOURCE     = PAYMENT_RESOURCE + "/{" + PAYMENT_ID + "}/refunds";

    static String getRoute(String route, String resourceId) {
        String resourceIdUrl = !StringUtils.isEmpty(resourceId) ? "/" + resourceId : "";

        return API_BASE_URL + "/v" + API_VERSION +  route + resourceIdUrl;
    }
}
