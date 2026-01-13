package com.chargebee.v4.client.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

/**
 * Unit tests for RequestOptions class.
 */
public class RequestOptionsTest {

    @Test
    public void testIdempotencyKeyIsSetInHeaders() {
        String testKey = "test-uuid-12345";
        RequestOptions options = RequestOptions.builder()
                .idempotencyKey(testKey)
                .build();

        assertEquals(testKey, options.getIdempotencyKey());
        assertTrue(options.getHeaders().containsKey("chargebee-idempotency-key"));
        assertEquals(testKey, options.getHeaders().get("chargebee-idempotency-key"));
    }

    @Test
    public void testIdempotencyKeyWithNullValue() {
        RequestOptions options = RequestOptions.builder()
                .idempotencyKey(null)
                .build();

        assertNull(options.getIdempotencyKey());
        assertFalse(options.getHeaders().containsKey("chargebee-idempotency-key"));
    }

    @Test
    public void testIdempotencyKeyWithEmptyString() {
        RequestOptions options = RequestOptions.builder()
                .idempotencyKey("")
                .build();

        assertNull(options.getIdempotencyKey());
        assertFalse(options.getHeaders().containsKey("chargebee-idempotency-key"));
    }

    @Test
    public void testIdempotencyKeyWithOtherHeaders() {
        String testKey = "test-uuid-67890";
        RequestOptions options = RequestOptions.builder()
                .header("X-Custom-Header", "custom-value")
                .idempotencyKey(testKey)
                .header("X-Another-Header", "another-value")
                .build();

        assertEquals(testKey, options.getIdempotencyKey());
        Map<String, String> headers = options.getHeaders();
        assertEquals(3, headers.size());
        assertEquals("custom-value", headers.get("X-Custom-Header"));
        assertEquals(testKey, headers.get("chargebee-idempotency-key"));
        assertEquals("another-value", headers.get("X-Another-Header"));
    }

    @Test
    public void testIdempotencyKeyCanBeOverwritten() {
        String firstKey = "first-key";
        String secondKey = "second-key";

        RequestOptions options = RequestOptions.builder()
                .idempotencyKey(firstKey)
                .idempotencyKey(secondKey)
                .build();

        assertEquals(secondKey, options.getIdempotencyKey());
        assertEquals(secondKey, options.getHeaders().get("chargebee-idempotency-key"));
    }

    @Test
    public void testBuilderMethodChaining() {
        RequestOptions options = RequestOptions.builder()
                .idempotencyKey("test-key")
                .maxNetworkRetries(3)
                .retryEnabled(true)
                .build();

        assertEquals("test-key", options.getIdempotencyKey());
        assertEquals(3, options.getMaxNetworkRetries());
        assertTrue(options.getRetryEnabled());
    }
}
