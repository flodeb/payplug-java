package com.flodeb.payplug.client;

import org.junit.Assert;
import org.junit.Test;

public class APIRoutesTest {

    @Test
    public void shouldGetRootUrl() {
        // Given
        String baseUrl = "/payments";

        // When
        String route = APIRoutes.getRoute(baseUrl, null);

        // Then
        Assert.assertEquals("https://api.payplug.com/v1/payments", route);
    }

    @Test
    public void shouldGetResourceUrl() {
        // Given
        String baseUrl = "/payments";

        // When
        String route = APIRoutes.getRoute(baseUrl, "659181");

        // Then
        Assert.assertEquals("https://api.payplug.com/v1/payments/659181", route);
    }
}