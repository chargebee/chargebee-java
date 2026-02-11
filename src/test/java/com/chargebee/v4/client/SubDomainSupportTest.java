package com.chargebee.v4.client;

import com.chargebee.v4.transport.FakeTransport;
import com.chargebee.v4.transport.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SubDomainSupportTest {

    private FakeTransport fakeTransport;

    @BeforeEach
    void setUp() {
        fakeTransport = new FakeTransport();
    }

    @Nested
    @DisplayName("getBaseUrlWithSubDomain")
    class GetBaseUrlWithSubDomain {

        @Test
        @DisplayName("should construct URL with subdomain")
        void shouldConstructUrlWithSubDomain() {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .transport(fakeTransport)
                    .build();

            String url = client.getBaseUrlWithSubDomain("integrations");

            assertEquals("https://acme.integrations.chargebee.com/api/v2", url);
        }

        @Test
        @DisplayName("should fall back to base URL when subdomain is null")
        void shouldFallBackWhenSubDomainIsNull() {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .transport(fakeTransport)
                    .build();

            String url = client.getBaseUrlWithSubDomain(null);

            assertEquals(client.getBaseUrl(), url);
        }

        @Test
        @DisplayName("should fall back to base URL when subdomain is empty")
        void shouldFallBackWhenSubDomainIsEmpty() {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .transport(fakeTransport)
                    .build();

            String url = client.getBaseUrlWithSubDomain("");

            assertEquals(client.getBaseUrl(), url);
        }

        @Test
        @DisplayName("should fall back to base URL when subdomain is blank")
        void shouldFallBackWhenSubDomainIsBlank() {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .transport(fakeTransport)
                    .build();

            String url = client.getBaseUrlWithSubDomain("   ");

            assertEquals(client.getBaseUrl(), url);
        }

        @Test
        @DisplayName("should return configured endpoint when endpoint is set")
        void shouldReturnEndpointWhenSet() {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .endpoint("https://custom.example.com/api/v2")
                    .transport(fakeTransport)
                    .build();

            String url = client.getBaseUrlWithSubDomain("integrations");

            assertEquals("https://custom.example.com/api/v2", url);
        }

        @Test
        @DisplayName("should respect custom domain suffix")
        void shouldRespectCustomDomainSuffix() {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .domainSuffix("chargebee-staging.com")
                    .transport(fakeTransport)
                    .build();

            String url = client.getBaseUrlWithSubDomain("integrations");

            assertEquals("https://acme.integrations.chargebee-staging.com/api/v2", url);
        }

        @Test
        @DisplayName("should respect custom protocol")
        void shouldRespectCustomProtocol() {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .protocol("http")
                    .transport(fakeTransport)
                    .build();

            String url = client.getBaseUrlWithSubDomain("integrations");

            assertEquals("http://acme.integrations.chargebee.com/api/v2", url);
        }
    }

    @Nested
    @DisplayName("BaseService subdomain methods")
    class BaseServiceSubDomainMethods {

        @Test
        @DisplayName("getWithSubDomain should route request through subdomain URL")
        void getWithSubDomainShouldRouteCorrectly() {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .transport(fakeTransport)
                    .build();

            client.get("/test-path", new HashMap<>());
            Request normalRequest = fakeTransport.getLastRequest();
            fakeTransport.clearRequests();

            assertTrue(normalRequest.getUrl().startsWith("https://acme.chargebee.com/api/v2"));
        }

        @Test
        @DisplayName("subdomain URL should differ from normal URL")
        void subDomainUrlShouldDiffer() {
            ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey("cb_test_123")
                    .siteName("acme")
                    .transport(fakeTransport)
                    .build();

            String normalUrl = client.getBaseUrl();
            String subDomainUrl = client.getBaseUrlWithSubDomain("integrations");

            assertNotEquals(normalUrl, subDomainUrl);
            assertFalse(normalUrl.contains("integrations"));
            assertTrue(subDomainUrl.contains("integrations"));
        }
    }
}
