package com.flodeb.payplug.resource;

import com.flodeb.payplug.client.APIRoutes;
import com.flodeb.payplug.client.HttpClient;
import com.flodeb.payplug.core.PayplugConfiguration;
import com.flodeb.payplug.exception.PayplugException;
import com.flodeb.payplug.exception.UndefinedAttributeException;
import com.flodeb.payplug.model.Refund;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class RefundResource implements IAPIResource {

    private final HttpClient httpClient;

    private Refund refund;

    public RefundResource(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Refund getConsistentResource(PayplugConfiguration configuration) throws PayplugException {
        if (StringUtils.isEmpty(refund.getId())) {
            throw new UndefinedAttributeException("The id of the REFUND is not set");
        }
        if (StringUtils.isEmpty(refund.getPaymentId())) {
            throw new UndefinedAttributeException("The payment_id of the REFUND is not set");
        }

        Map<String, String> params = new HashMap<>();
        params.put(APIRoutes.PAYMENT_ID, refund.getPaymentId());

        return httpClient.get(configuration, APIRoutes.getRoute(APIRoutes.REFUND_RESOURCE, refund.getId()), params, Refund.class);
    }

    public Refund getRefund() {
        return refund;
    }

    public void setRefund(Refund refund) {
        this.refund = refund;
    }
}
