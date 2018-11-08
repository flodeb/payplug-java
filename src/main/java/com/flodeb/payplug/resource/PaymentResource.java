package com.flodeb.payplug.resource;

import com.flodeb.payplug.client.APIRoutes;
import com.flodeb.payplug.client.HttpClient;
import com.flodeb.payplug.core.PayplugConfiguration;
import com.flodeb.payplug.exception.PayplugException;
import com.flodeb.payplug.exception.UndefinedAttributeException;
import com.flodeb.payplug.model.Payment;
import org.springframework.util.StringUtils;

public class PaymentResource implements IAPIResource {

    private final HttpClient httpClient;

    private Payment payment;

    public PaymentResource(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Payment getConsistentResource(PayplugConfiguration configuration) throws PayplugException {
        if (StringUtils.isEmpty(payment.getId())) {
            throw new UndefinedAttributeException("The id of the PAYMENT is not set");
        }

        return httpClient.get(configuration, APIRoutes.getRoute(APIRoutes.PAYMENT_RESOURCE, payment.getId()), Payment.class);
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
