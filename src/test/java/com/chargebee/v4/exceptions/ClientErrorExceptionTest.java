package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Request;
import com.chargebee.v4.transport.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ClientErrorException Tests")
class ClientErrorExceptionTest {

    private static final String TEST_URL = "https://test-site.chargebee.com/api/v2/customers";

    @Nested
    @DisplayName("Status Code Helper Tests")
    class StatusCodeHelperTests {

        @Test
        @DisplayName("should identify 400 as bad request")
        void shouldIdentify400AsBadRequest() {
            ClientErrorException exception = createException(400);
            assertTrue(exception.isBadRequest());
        }

        @Test
        @DisplayName("should identify 401 as unauthorized")
        void shouldIdentify401AsUnauthorized() {
            ClientErrorException exception = createException(401);
            assertTrue(exception.isUnauthorized());
        }

        @Test
        @DisplayName("should identify 403 as forbidden")
        void shouldIdentify403AsForbidden() {
            ClientErrorException exception = createException(403);
            assertTrue(exception.isForbidden());
        }

        @Test
        @DisplayName("should identify 404 as not found")
        void shouldIdentify404AsNotFound() {
            ClientErrorException exception = createException(404);
            assertTrue(exception.isNotFound());
        }

        @Test
        @DisplayName("should identify 405 as method not allowed")
        void shouldIdentify405AsMethodNotAllowed() {
            ClientErrorException exception = createException(405);
            assertTrue(exception.isMethodNotAllowed());
        }

        @Test
        @DisplayName("should identify 409 as conflict")
        void shouldIdentify409AsConflict() {
            ClientErrorException exception = createException(409);
            assertTrue(exception.isConflict());
        }

        @Test
        @DisplayName("should identify 422 as unprocessable entity")
        void shouldIdentify422AsUnprocessableEntity() {
            ClientErrorException exception = createException(422);
            assertTrue(exception.isUnprocessableEntity());
        }

        @Test
        @DisplayName("should identify 429 as too many requests")
        void shouldIdentify429AsTooManyRequests() {
            ClientErrorException exception = createException(429);
            assertTrue(exception.isTooManyRequests());
        }

        @Test
        @DisplayName("TOO_MANY_REQUESTS constant should be 429")
        void tooManyRequestsConstantShouldBe429() {
            assertEquals(429, ClientErrorException.TOO_MANY_REQUESTS);
        }
    }

    @Nested
    @DisplayName("isRetryable Tests")
    class IsRetryableTests {

        @Test
        @DisplayName("429 Too Many Requests should be retryable")
        void shouldBeRetryableFor429() {
            ClientErrorException exception = createException(429);
            assertTrue(exception.isRetryable());
        }

        @ParameterizedTest
        @ValueSource(ints = {400, 401, 403, 404, 405, 409, 422})
        @DisplayName("other client errors should not be retryable")
        void shouldNotBeRetryableForOtherErrors(int statusCode) {
            ClientErrorException exception = createException(statusCode);
            assertFalse(exception.isRetryable());
        }
    }

    @Nested
    @DisplayName("Retry-After Header Tests")
    class RetryAfterHeaderTests {

        @Test
        @DisplayName("should parse Retry-After header in seconds")
        void shouldParseRetryAfterInSeconds() {
            Map<String, List<String>> headers = new HashMap<>();
            headers.put("Retry-After", Arrays.asList("30"));
            Response response = new Response(429, headers, "".getBytes());
            Request request = createRequest();

            ClientErrorException exception = new ClientErrorException(429, "Too Many Requests", request, response);

            assertEquals(30000L, exception.getRetryAfterMs());
        }

        @Test
        @DisplayName("should return -1 when Retry-After header is missing")
        void shouldReturnNegativeOneWhenHeaderMissing() {
            ClientErrorException exception = createException(429);
            assertEquals(-1L, exception.getRetryAfterMs());
        }

        @Test
        @DisplayName("should return -1 when response is null")
        void shouldReturnNegativeOneWhenResponseNull() {
            Request request = createRequest();
            ClientErrorException exception = new ClientErrorException(429, "Too Many Requests", request, null);

            assertEquals(-1L, exception.getRetryAfterMs());
        }

        @Test
        @DisplayName("should return -1 for non-numeric Retry-After value")
        void shouldReturnNegativeOneForNonNumericValue() {
            Map<String, List<String>> headers = new HashMap<>();
            headers.put("Retry-After", Arrays.asList("Wed, 21 Oct 2024 07:28:00 GMT"));
            Response response = new Response(429, headers, "".getBytes());
            Request request = createRequest();

            ClientErrorException exception = new ClientErrorException(429, "Too Many Requests", request, response);

            assertEquals(-1L, exception.getRetryAfterMs());
        }

        @Test
        @DisplayName("should handle whitespace in Retry-After value")
        void shouldHandleWhitespaceInRetryAfterValue() {
            Map<String, List<String>> headers = new HashMap<>();
            headers.put("Retry-After", Arrays.asList("  60  "));
            Response response = new Response(429, headers, "".getBytes());
            Request request = createRequest();

            ClientErrorException exception = new ClientErrorException(429, "Too Many Requests", request, response);

            assertEquals(60000L, exception.getRetryAfterMs());
        }
    }

    @Nested
    @DisplayName("Exception Hierarchy Tests")
    class ExceptionHierarchyTests {

        @Test
        @DisplayName("should be instance of HttpException")
        void shouldBeInstanceOfHttpException() {
            ClientErrorException exception = createException(400);
            assertTrue(exception instanceof HttpException);
        }

        @Test
        @DisplayName("should be instance of TransportException")
        void shouldBeInstanceOfTransportException() {
            ClientErrorException exception = createException(400);
            assertTrue(exception instanceof TransportException);
        }

        @Test
        @DisplayName("should be catchable as HttpException")
        void shouldBeCatchableAsHttpException() {
            try {
                throw createException(404);
            } catch (HttpException e) {
                assertTrue(e instanceof ClientErrorException);
                assertEquals(404, e.getStatusCode());
            }
        }
    }

    @Nested
    @DisplayName("Inherited Methods Tests")
    class InheritedMethodsTests {

        @Test
        @DisplayName("should inherit isClientError returning true")
        void shouldInheritIsClientError() {
            ClientErrorException exception = createException(400);
            assertTrue(exception.isClientError());
        }

        @Test
        @DisplayName("should inherit isServerError returning false")
        void shouldInheritIsServerError() {
            ClientErrorException exception = createException(400);
            assertFalse(exception.isServerError());
        }

        @Test
        @DisplayName("should inherit getUrl")
        void shouldInheritGetUrl() {
            ClientErrorException exception = createException(400);
            assertEquals(TEST_URL, exception.getUrl());
        }

        @Test
        @DisplayName("should inherit getResponseBody")
        void shouldInheritGetResponseBody() {
            Response response = createResponse(400, "{\"error\":\"bad request\"}");
            Request request = createRequest();
            ClientErrorException exception = new ClientErrorException(400, "Bad Request", request, response);

            assertEquals("{\"error\":\"bad request\"}", exception.getResponseBody());
        }
    }

    private ClientErrorException createException(int statusCode) {
        Request request = createRequest();
        Response response = createResponse(statusCode, "{}");
        return new ClientErrorException(statusCode, "Error " + statusCode, request, response);
    }

    private Request createRequest() {
        return Request.builder()
            .method("GET")
            .url(TEST_URL)
            .build();
    }

    private Response createResponse(int statusCode, String body) {
        Map<String, List<String>> headers = new HashMap<>();
        return new Response(statusCode, headers, body.getBytes());
    }
}

