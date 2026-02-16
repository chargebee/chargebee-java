package com.chargebee.v4.services;

import com.chargebee.v4.client.ChargebeeClient;
import com.chargebee.v4.client.request.RequestOptions;
import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.transport.Response;

import java.util.Map;

/**
 * Test-only BaseService subclass that exposes protected subdomain methods for testing.
 */
public class TestSubDomainService extends BaseService<TestSubDomainService> {

    public TestSubDomainService(ChargebeeClient client) {
        super(client);
    }

    @Override
    TestSubDomainService with(RequestOptions newOptions) {
        return new TestSubDomainService(client);
    }

    public Response callGetWithSubDomain(String path, String subDomain) throws ChargebeeException {
        return getWithSubDomain(path, subDomain, null);
    }

    public Response callPostWithSubDomain(String path, String subDomain) throws ChargebeeException {
        return postWithSubDomain(path, subDomain, null);
    }

    public Response callPostJsonWithSubDomain(String path, String subDomain) throws ChargebeeException {
        return postJsonWithSubDomain(path, subDomain, "{}");
    }
}
