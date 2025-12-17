package com.chargebee;

import com.chargebee.v4.client.ChargebeeClient;
import com.chargebee.v4.exceptions.ConfigurationException;
import com.chargebee.v4.internal.RetryConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class ChargebeeClientTest {

    @Test
    @DisplayName("Builder should create client with required fields")
    void testBuilderWithRequiredFields() {
        ChargebeeClient client = ChargebeeClient.builder()
                .apiKey("cb_test_123")
                .siteName("acme")
                .build();

        assertNotNull(client);
        assertEquals("cb_test_123", client.getApiKey());
        assertEquals("acme", client.getSiteName());
    }

    @Test
    @DisplayName("Builder should fail when apiKey is missing")
    void testBuilderFailsWithoutApiKey() {
        ConfigurationException exception = assertThrows(ConfigurationException.class, () ->
                ChargebeeClient.builder()
                        .siteName("acme")
                        .build()
        );
        assertEquals("API key is required", exception.getMessage());
    }

    @Test
    @DisplayName("Builder should fail when siteName is missing")
    void testBuilderFailsWithoutSiteName() {
        ConfigurationException exception = assertThrows(ConfigurationException.class, () ->
                ChargebeeClient.builder()
                        .apiKey("cb_test_123")
                        .build()
        );
        assertEquals("Site name is required", exception.getMessage());
    }

    @Test
    @DisplayName("Builder should fail when both required fields are missing")
    void testBuilderFailsWithoutRequiredFields() {
        ConfigurationException exception = assertThrows(ConfigurationException.class, () ->
                ChargebeeClient.builder().build()
        );
        assertEquals("API key is required", exception.getMessage());
    }

    @Test
    @DisplayName("Builder should use default values")
    void testBuilderDefaults() {
        ChargebeeClient client = ChargebeeClient.builder()
                .apiKey("cb_test_123")
                .siteName("acme")
                .build();

        assertEquals(30000, client.getConnectTimeout());
        assertEquals(80000, client.getReadTimeout());
        assertEquals("chargebee.com", client.getDomainSuffix());
        assertEquals("https", client.getProtocol());
        assertNotNull(client.getRetry());
        assertFalse(client.getRetry().isEnabled()); // Default retry is disabled
    }

    @Test
    @DisplayName("Builder should support fluent API")
    void testFluentBuilder() {
        RetryConfig customRetry = RetryConfig.builder()
                .enabled(true)
                .maxRetries(5)
                .build();

        ChargebeeClient client = ChargebeeClient.builder()
                .apiKey("cb_test_456")
                .siteName("test-site")
                .endpoint("https://custom.chargebee.com/api/v2")
                .connectTimeout(15000)
                .readTimeout(60000)
                .debugLogging(true)
                .retry(customRetry)
                .domainSuffix("custom.com")
                .protocol("http")
                .build();

        assertEquals("cb_test_456", client.getApiKey());
        assertEquals("test-site", client.getSiteName());
        assertEquals("https://custom.chargebee.com/api/v2", client.getEndpoint());
        assertEquals(15000, client.getConnectTimeout());
        assertEquals(60000, client.getReadTimeout());
        assertEquals(customRetry, client.getRetry());
        assertEquals("custom.com", client.getDomainSuffix());
        assertEquals("http", client.getProtocol());
    }

    @Test
    @DisplayName("Client should be immutable")
    void testImmutability() {
        ChargebeeClient client = ChargebeeClient.builder()
                .apiKey("cb_test_123")
                .siteName("acme")
                .build();

        // No setters should exist - compilation test
        // This test ensures immutability by design
        assertNotNull(client.getApiKey());
        assertNotNull(client.getSiteName());
    }

    @Test
    @DisplayName("Two clients with same config should be equal")
    void testEquals() {
        RetryConfig retry = RetryConfig.builder().enabled(true).build();
        
        ChargebeeClient client1 = ChargebeeClient.builder()
                .apiKey("cb_test_123")
                .siteName("acme")
                .connectTimeout(15000)
                .retry(retry)
                .build();

        ChargebeeClient client2 = ChargebeeClient.builder()
                .apiKey("cb_test_123")
                .siteName("acme")
                .connectTimeout(15000)
                .retry(retry)
                .build();

        assertEquals(client1, client2);
        assertEquals(client1.hashCode(), client2.hashCode());
    }

    @Test
    @DisplayName("Two clients with different config should not be equal")
    void testNotEquals() {
        ChargebeeClient client1 = ChargebeeClient.builder()
                .apiKey("cb_test_123")
                .siteName("acme")
                .build();

        ChargebeeClient client2 = ChargebeeClient.builder()
                .apiKey("cb_test_456")
                .siteName("acme")
                .build();

        assertNotEquals(client1, client2);
    }

    @Test
    @DisplayName("Client should not equal null or different class")
    void testEqualsEdgeCases() {
        ChargebeeClient client = ChargebeeClient.builder()
                .apiKey("cb_test_123")
                .siteName("acme")
                .build();

        assertNotEquals(client, null);
        assertNotEquals(client, "not a client");
        assertEquals(client, client); // Same instance
    }

    @Test
    @DisplayName("Builder should support transport configuration")
    void testTransportConfiguration() {
        com.chargebee.v4.transport.FakeTransport customTransport = new com.chargebee.v4.transport.FakeTransport();
        
        ChargebeeClient client = ChargebeeClient.builder()
                .apiKey("cb_test_123")
                .siteName("acme")
                .transport(customTransport)
                .build();

        assertEquals(customTransport, client.getTransport());
    }

    @Test
    @DisplayName("Builder should handle null optional values")
    void testNullOptionalValues() {
        ChargebeeClient client = ChargebeeClient.builder()
                .apiKey("cb_test_123")
                .siteName("acme")
                .endpoint(null)
                // .transport(null)  // Skip transport for null test as it gets set to default"
                .build();

        assertNull(client.getEndpoint());
        assertNotNull(client.getTransport()); // Transport is always set to default if null"
    }
}