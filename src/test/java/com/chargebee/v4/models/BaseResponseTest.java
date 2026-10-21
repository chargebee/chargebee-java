package com.chargebee.v4.models;

import com.chargebee.v4.models.customer.responses.CustomerListResponse;
import com.chargebee.v4.transport.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BaseResponse header Tests")
class BaseResponseTest {

    /** Mirrors HttpURLConnection.getHeaderFields(), which maps the status line to a null key. */
    private static Map<String, List<String>> httpUrlConnectionHeaders(String name, String value) {
        Map<String, List<String>> headers = new HashMap<>();
        headers.put(null, Collections.singletonList("HTTP/1.1 200 OK"));
        headers.put("Content-Type", Collections.singletonList("application/json"));
        if (name != null) {
            headers.put(name, Collections.singletonList(value));
        }
        return headers;
    }

    private static BaseResponse wrap(Response response) {
        return new BaseResponse(response) {};
    }

    @Test
    @DisplayName("should detect replay when headers contain the status-line null key")
    void shouldDetectReplayWithNullStatusLineKey() {
        Response response = new Response(200,
            httpUrlConnectionHeaders("chargebee-idempotency-replayed", "true"), "{}".getBytes());

        BaseResponse base = wrap(response);

        assertTrue(base.isIdempotencyReplayed());
        assertEquals(Collections.singletonList("true"), base.header("chargebee-idempotency-replayed"));
    }

    @Test
    @DisplayName("should match the replay header case-insensitively")
    void shouldMatchReplayHeaderCaseInsensitively() {
        Response response = new Response(200,
            httpUrlConnectionHeaders("Chargebee-Idempotency-Replayed", "TRUE"), "{}".getBytes());

        assertTrue(wrap(response).isIdempotencyReplayed());
    }

    @Test
    @DisplayName("should return false when the replay header is absent")
    void shouldReturnFalseWhenReplayHeaderAbsent() {
        Response response = new Response(200, httpUrlConnectionHeaders(null, null), "{}".getBytes());

        BaseResponse base = wrap(response);

        assertFalse(base.isIdempotencyReplayed());
        assertNull(base.header("chargebee-idempotency-replayed"));
    }

    @Test
    @DisplayName("should return false when the replay header is not true")
    void shouldReturnFalseWhenReplayHeaderNotTrue() {
        Response response = new Response(200,
            httpUrlConnectionHeaders("chargebee-idempotency-replayed", "false"), "{}".getBytes());

        assertFalse(wrap(response).isIdempotencyReplayed());
    }

    @Test
    @DisplayName("should not expose the status-line null key in headers()")
    void shouldNotExposeNullKey() {
        Response response = new Response(200, httpUrlConnectionHeaders(null, null), "{}".getBytes());

        assertFalse(wrap(response).headers().containsKey(null));
        assertFalse(response.getHeaders().containsKey(null));
    }

    @Test
    @DisplayName("generated list responses should look up headers with the status-line null key")
    void generatedListResponseHeaderLookup() {
        Response response = new Response(200,
            httpUrlConnectionHeaders("X-Request-Id", "req_123"), "{\"list\":[]}".getBytes());

        CustomerListResponse list =
            CustomerListResponse.fromJson(response.getBodyAsString(), null, null, response);

        assertEquals(Collections.singletonList("req_123"), list.header("x-request-id"));
        assertNull(list.header("missing"));
    }
}
