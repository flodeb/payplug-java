package com.flodeb.payplug;

import com.flodeb.payplug.core.PayplugConfiguration;
import com.flodeb.payplug.exception.PayplugException;
import com.flodeb.payplug.model.Resource;
import com.flodeb.payplug.resource.IAPIResource;
import com.flodeb.payplug.resource.factory.APIResourceFactory;
import org.springframework.util.Assert;

public class NotificationService {
    private final APIResourceFactory apiResourceFactory;

    public NotificationService(APIResourceFactory apiResourceFactory) {
        this.apiResourceFactory = apiResourceFactory;
    }

    public Resource treat(String content, PayplugConfiguration payplugConfiguration) throws PayplugException {
        Assert.hasText(content, "content must have text");

        IAPIResource resource = apiResourceFactory.getResource(content);
        return resource.getConsistentResource(payplugConfiguration);
    }
}
