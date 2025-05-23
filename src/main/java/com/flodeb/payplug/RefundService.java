package com.flodeb.payplug;

import com.flodeb.payplug.client.APIRoutes;
import com.flodeb.payplug.client.HttpClient;
import com.flodeb.payplug.core.PayplugConfiguration;
import com.flodeb.payplug.exception.PayplugException;
import com.flodeb.payplug.model.Payment;
import org.springframework.util.Assert;

public class RefundService {

    private final HttpClient httpClient;

    public RefundService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Payment create(String paymentId, PayplugConfiguration configuration) throws PayplugException {
        Assert.notNull(paymentId, "paymentId must not be null");

        return httpClient.post(configuration, APIRoutes.getCreateRefundRoute(paymentId), null, Payment.class);
    }
}
