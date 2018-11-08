package com.flodeb.payplug.resource;

import com.flodeb.payplug.core.PayplugConfiguration;
import com.flodeb.payplug.exception.PayplugException;
import com.flodeb.payplug.model.Resource;

public interface IAPIResource {
    Resource getConsistentResource(PayplugConfiguration payplugConfiguration) throws PayplugException;
}
