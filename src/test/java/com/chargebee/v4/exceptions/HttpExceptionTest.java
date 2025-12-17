package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Request;
import com.chargebee.v4.transport.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HttpException Tests")
class HttpExceptionTest {

    private static final String TEST_URL = "https://test-site.chargebee.com/api/v2/customers";

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("should create exception with all parameters")
        void shouldCreateWithAllParameters() {
            Request request = createRequest("GET", TEST_URL);
            Response response = createResponse(404, "{\"error\":\"not found\"}");

            HttpException exception = new HttpException(404, "Not Found", request, response);

            assertEquals(404, exception.getStatusCode());
            assertEquals("Not Found", exception.getMessage());
            assertNotNull(exception.getRequest());
            assertNotNull(exception.getResponse());
        }

        @Test
        @DisplayName("should create exception with cause")
        void shouldCreateWithCause() {
            Request request = createRequest("POST", TEST_URL);
            Response response = createResponse(500, "{\"error\":\"server error\"}");
            Exception cause = new RuntimeException("parsing error");

            HttpException exception = new HttpException(500, "Server Error", request, response, cause);

            assertEquals(500, exception.getStatusCode());
            assertEquals(cause, exception.getCause());
        }
    }

    @Nested
    @DisplayName("Status Code Helpers Tests")
    class StatusCodeHelperTests {

        @Test
        @DisplayName("should identify client error (4xx)")
        void shouldIdentifyClientError() {
            HttpException exception = createException(400);
            assertTrue(exception.isClientError());
            assertFalse(exception.isServerError());
        }

        @Test
        @DisplayName("should identify server error (5xx)")
        void shouldIdentifyServerError() {
            HttpException exception = createException(503);
            assertFalse(exception.isClientError());
            assertTrue(exception.isServerError());
        }

        @Test
        @DisplayName("should identify 401 as client error")
        void shouldIdentify401AsClientError() {
            HttpException exception = createException(401);
            assertTrue(exception.isClientError());
        }

        @Test
        @DisplayName("should identify 499 as client error")
        void shouldIdentify499AsClientError() {
            HttpException exception = createException(499);
            assertTrue(exception.isClientError());
        }

        @Test
        @DisplayName("should identify 500 as server error")
        void shouldIdentify500AsServerError() {
            HttpException exception = createException(500);
            assertTrue(exception.isServerError());
        }

        @Test
        @DisplayName("should identify 599 as server error")
        void shouldIdentify599AsServerError() {
            HttpException exception = createException(599);
            assertTrue(exception.isServerError());
        }

        @Test
        @DisplayName("should not identify 200 as error")
        void shouldNotIdentify200AsError() {
            HttpException exception = createException(200);
            assertFalse(exception.isClientError());
            assertFalse(exception.isServerError());
        }
    }

    @Nested
    @DisplayName("Response Body Tests")
    class ResponseBodyTests {

        @Test
        @DisplayName("should return response body as string")
        void shouldReturnResponseBodyAsString() {
            Response response = createResponse(400, "{\"error\":\"bad request\"}");
            HttpException exception = new HttpException(400, "Bad Request", createRequest("GET", TEST_URL), response);

            assertEquals("{\"error\":\"bad request\"}", exception.getResponseBody());
        }

        @Test
        @DisplayName("should return null when response is null")
        void shouldReturnNullWhenResponseIsNull() {
            HttpException exception = new HttpException(500, "Error", createRequest("GET", TEST_URL), null);

            assertNull(exception.getResponseBody());
        }
    }

    @Nested
    @DisplayName("Request Context Tests")
    class RequestContextTests {

        @Test
        @DisplayName("should return URL from request")
        void shouldReturnUrlFromRequest() {
            Request request = createRequest("GET", TEST_URL);
            HttpException exception = new HttpException(404, "Not Found", request, createResponse(404, ""));

            assertEquals(TEST_URL, exception.getUrl());
        }

        @Test
        @DisplayName("should return HTTP method from request")
        void shouldReturnHttpMethodFromRequest() {
            Request request = createRequest("DELETE", TEST_URL);
            HttpException exception = new HttpException(404, "Not Found", request, createResponse(404, ""));

            assertEquals("DELETE", exception.getHttpMethod());
        }
    }

    @Nested
    @DisplayName("isRetryable Tests")
    class IsRetryableTests {

        @Test
        @DisplayName("base HttpException should not be retryable")
        void shouldNotBeRetryableByDefault() {
            HttpException exception = createException(500);
            assertFalse(exception.isRetryable());
        }
    }

    @Nested
    @DisplayName("toString Tests")
    class ToStringTests {

        @Test
        @DisplayName("should include status code in toString")
        void shouldIncludeStatusCodeInToString() {
            HttpException exception = createException(404);

            String toString = exception.toString();

            assertTrue(toString.contains("statusCode=404"));
        }

        @Test
        @DisplayName("should include message in toString")
        void shouldIncludeMessageInToString() {
            Request request = createRequest("GET", TEST_URL);
            Response response = createResponse(404, "");
            HttpException exception = new HttpException(404, "Resource not found", request, response);

            String toString = exception.toString();

            assertTrue(toString.contains("Resource not found"));
        }

        @Test
        @DisplayName("should include URL in toString")
        void shouldIncludeUrlInToString() {
            HttpException exception = createException(500);

            String toString = exception.toString();

            assertTrue(toString.contains(TEST_URL));
        }
    }

    @Nested
    @DisplayName("Exception Hierarchy Tests")
    class ExceptionHierarchyTests {

        @Test
        @DisplayName("should be instance of TransportException")
        void shouldBeInstanceOfTransportException() {
            HttpException exception = createException(400);
            assertTrue(exception instanceof TransportException);
        }
    }

    private HttpException createException(int statusCode) {
        Request request = createRequest("GET", TEST_URL);
        Response response = createResponse(statusCode, "{\"status\":" + statusCode + "}");
        return new HttpException(statusCode, "Error " + statusCode, request, response);
    }

    private Request createRequest(String method, String url) {
        return Request.builder()
            .method(method)
            .url(url)
            .build();
    }

    private Response createResponse(int statusCode, String body) {
        Map<String, List<String>> headers = new HashMap<>();
        return new Response(statusCode, headers, body.getBytes());
    }
}

