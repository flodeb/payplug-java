package com.flodeb.payplug;

import com.flodeb.payplug.client.APIRoutes;
import com.flodeb.payplug.client.HttpClient;
import com.flodeb.payplug.core.PayplugConfiguration;
import com.flodeb.payplug.exception.PayplugException;
import com.flodeb.payplug.model.Payment;
import org.springframework.util.Assert;

public class PaymentService {

    private final HttpClient httpClient;

    public PaymentService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Payment create(Payment payment, PayplugConfiguration configuration) throws PayplugException {
        Assert.notNull(payment, "payment must not be null");

        return httpClient.post(configuration, APIRoutes.getRoute(APIRoutes.PAYMENT_RESOURCE, null), payment, Payment.class);
    }

    public Payment get(String paymentId, PayplugConfiguration configuration) throws PayplugException {
        return httpClient.get(configuration, APIRoutes.getRoute(APIRoutes.PAYMENT_RESOURCE, paymentId), Payment.class);
    }
}
