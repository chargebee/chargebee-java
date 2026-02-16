package com.chargebee.v4.client;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.services.TestSubDomainService;
import com.chargebee.v4.transport.FakeTransport;
import com.chargebee.v4.transport.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubDomainSupportTest {

    private FakeTransport fakeTransport;

    @BeforeEach
    void setUp() {
        fakeTransport = new FakeTransport();
    }

    @Nested
    @DisplayName("Subdomain URL construction via BaseService")
    class SubDomainUrlConstruction {

        @Test
        @DisplayName("should construct URL with subdomain")
        void shouldConstructUrlWithSubDomain() throws ChargebeeException {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .transport(fakeTransport)
                    .build();

            TestSubDomainService service = new TestSubDomainService(client);
            service.callGetWithSubDomain("/test-path", "integrations");

            Request request = fakeTransport.getLastRequest();
            assertTrue(request.getUrl().startsWith("https://acme.integrations.chargebee.com/api/v2"));
        }

        @Test
        @DisplayName("should return configured endpoint when endpoint is set")
        void shouldReturnEndpointWhenSet() throws ChargebeeException {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .endpoint("https://custom.example.com/api/v2")
                    .transport(fakeTransport)
                    .build();

            TestSubDomainService service = new TestSubDomainService(client);
            service.callGetWithSubDomain("/test-path", "integrations");

            Request request = fakeTransport.getLastRequest();
            assertTrue(request.getUrl().startsWith("https://custom.example.com/api/v2"));
        }

        @Test
        @DisplayName("should respect custom domain suffix")
        void shouldRespectCustomDomainSuffix() throws ChargebeeException {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .domainSuffix("chargebee-staging.com")
                    .transport(fakeTransport)
                    .build();

            TestSubDomainService service = new TestSubDomainService(client);
            service.callGetWithSubDomain("/test-path", "integrations");

            Request request = fakeTransport.getLastRequest();
            assertTrue(request.getUrl().startsWith("https://acme.integrations.chargebee-staging.com/api/v2"));
        }

        @Test
        @DisplayName("should respect custom protocol")
        void shouldRespectCustomProtocol() throws ChargebeeException {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .protocol("http")
                    .transport(fakeTransport)
                    .build();

            TestSubDomainService service = new TestSubDomainService(client);
            service.callGetWithSubDomain("/test-path", "integrations");

            Request request = fakeTransport.getLastRequest();
            assertTrue(request.getUrl().startsWith("http://acme.integrations.chargebee.com/api/v2"));
        }
    }

    @Nested
    @DisplayName("BaseService subdomain methods")
    class BaseServiceSubDomainMethods {

        @Test
        @DisplayName("getWithSubDomain should route request through subdomain URL")
        void getWithSubDomainShouldRouteCorrectly() throws ChargebeeException {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .transport(fakeTransport)
                    .build();

            TestSubDomainService service = new TestSubDomainService(client);
            service.callGetWithSubDomain("/test-path", "integrations");

            Request request = fakeTransport.getLastRequest();
            assertTrue(request.getUrl().contains("integrations"));
            assertEquals("GET", request.getMethod());
        }

        @Test
        @DisplayName("postWithSubDomain should route request through subdomain URL")
        void postWithSubDomainShouldRouteCorrectly() throws ChargebeeException {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .transport(fakeTransport)
                    .build();

            TestSubDomainService service = new TestSubDomainService(client);
            service.callPostWithSubDomain("/test-path", "integrations");

            Request request = fakeTransport.getLastRequest();
            assertTrue(request.getUrl().contains("integrations"));
            assertEquals("POST", request.getMethod());
        }

        @Test
        @DisplayName("postJsonWithSubDomain should route request through subdomain URL")
        void postJsonWithSubDomainShouldRouteCorrectly() throws ChargebeeException {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .transport(fakeTransport)
                    .build();

            TestSubDomainService service = new TestSubDomainService(client);
            service.callPostJsonWithSubDomain("/test-path", "integrations");

            Request request = fakeTransport.getLastRequest();
            assertTrue(request.getUrl().contains("integrations"));
            assertEquals("POST", request.getMethod());
        }

        @Test
        @DisplayName("subdomain URL should differ from normal URL")
        void subDomainUrlShouldDiffer() throws ChargebeeException {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .transport(fakeTransport)
                    .build();

            String normalUrl = client.getBaseUrl();

            TestSubDomainService service = new TestSubDomainService(client);
            service.callGetWithSubDomain("/test-path", "integrations");
            Request request = fakeTransport.getLastRequest();

            assertFalse(normalUrl.contains("integrations"));
            assertTrue(request.getUrl().contains("integrations"));
        }
    }
}
