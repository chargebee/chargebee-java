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
    public void testIdempotencyKeySetViaMixedCaseHeader() {
        RequestOptions options = RequestOptions.builder()
                .header("Chargebee-Idempotency-Key", "mixed-case-key")
                .build();

        assertEquals("mixed-case-key", options.getIdempotencyKey());
    }

    @Test
    public void testIdempotencyKeySetViaHeadersMapAndWithHeader() {
        Map<String, String> headers = new java.util.HashMap<>();
        headers.put("CHARGEBEE-IDEMPOTENCY-KEY", "from-map");

        RequestOptions fromMap = RequestOptions.builder().headers(headers).build();
        assertEquals("from-map", fromMap.getIdempotencyKey());

        RequestOptions fromWith = RequestOptions.empty().withHeader("Chargebee-Idempotency-Key", "from-with");
        assertEquals("from-with", fromWith.getIdempotencyKey());

        RequestOptions fromWithMap = RequestOptions.empty().withHeaders(headers);
        assertEquals("from-map", fromWithMap.getIdempotencyKey());
    }

    @Test
    public void testIdempotencyKeyDifferentCasingsCollapseToSingleHeader() {
        RequestOptions options = RequestOptions.builder()
                .header("Chargebee-Idempotency-Key", "first")
                .idempotencyKey("second")
                .build();

        Map<String, String> headers = options.getHeaders();
        long idempotencyHeaders = headers.keySet().stream()
                .filter(k -> k.equalsIgnoreCase("chargebee-idempotency-key"))
                .count();
        assertEquals(1, idempotencyHeaders);
        assertEquals("second", options.getIdempotencyKey());
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
