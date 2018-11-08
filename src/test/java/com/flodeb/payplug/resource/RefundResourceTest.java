package com.flodeb.payplug.resource;

import com.flodeb.payplug.client.APIRoutes;
import com.flodeb.payplug.core.PayplugConfiguration;
import com.flodeb.payplug.exception.PayplugException;
import com.flodeb.payplug.exception.UndefinedAttributeException;
import com.flodeb.payplug.model.Refund;
import com.flodeb.payplug.client.HttpClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(APIRoutes.class)
public class RefundResourceTest {

    @InjectMocks
    private RefundResource refundResource;

    @Mock
    private HttpClient httpClient;

    @Test(expected = UndefinedAttributeException.class)
    public void shouldNotGetResourceForNoRefundId() throws PayplugException {
        // Given
        PayplugConfiguration configuration = new PayplugConfiguration("8gf1fg7e1erg");

        Refund refund = new Refund();
        refundResource.setRefund(refund);

        // When
        refundResource.getConsistentResource(configuration);

        // Then
    }

    @Test(expected = UndefinedAttributeException.class)
    public void shouldNotGetResourceForNoPaymentId() throws PayplugException {
        // Given
        PayplugConfiguration configuration = new PayplugConfiguration("8gf1fg7e1erg");

        Refund refund = new Refund();
        refund.setId("Id123");
        refundResource.setRefund(refund);

        // When
        refundResource.getConsistentResource(configuration);

        // Then
    }

    @Test
    public void shouldGetResource() throws PayplugException {
        // Given
        PowerMockito.mockStatic(APIRoutes.class);

        PayplugConfiguration configuration = new PayplugConfiguration("8gf1fg7e1erg");

        Refund originalRefund = new Refund();
        originalRefund.setId("OLD_ID");
        originalRefund.setPaymentId("OLD_PAY_ID");
        refundResource.setRefund(originalRefund);

        Refund realRefund = new Refund();
        realRefund.setId("NEW_ID");
        realRefund.setPaymentId("NEW_PAY_ID");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(APIRoutes.PAYMENT_ID, "OLD_PAY_ID");

        when(APIRoutes.getRoute(APIRoutes.REFUND_RESOURCE, "OLD_ID")).thenReturn("http://refunds.com/OLD_ID");
        when(httpClient.get(configuration, "http://refunds.com/OLD_ID", params, Refund.class)).thenReturn(realRefund);

        // When
        Refund retrievedRefund = refundResource.getConsistentResource(configuration);

        // Then
        Assert.assertEquals(realRefund, retrievedRefund);
        Assert.assertEquals("NEW_ID", retrievedRefund.getId());
        Assert.assertEquals("NEW_PAY_ID", retrievedRefund.getPaymentId());
    }
}