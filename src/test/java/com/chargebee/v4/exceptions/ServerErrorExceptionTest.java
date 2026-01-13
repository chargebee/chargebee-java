package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Request;
import com.chargebee.v4.transport.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ServerErrorException Tests")
class ServerErrorExceptionTest {

    private static final String TEST_URL = "https://test-site.chargebee.com/api/v2/customers";

    @Nested
    @DisplayName("Status Code Helper Tests")
    class StatusCodeHelperTests {

        @Test
        @DisplayName("should identify 500 as internal server error")
        void shouldIdentify500AsInternalServerError() {
            ServerErrorException exception = createException(500);
            assertTrue(exception.isInternalServerError());
        }

        @Test
        @DisplayName("should identify 501 as not implemented")
        void shouldIdentify501AsNotImplemented() {
            ServerErrorException exception = createException(501);
            assertTrue(exception.isNotImplemented());
        }

        @Test
        @DisplayName("should identify 502 as bad gateway")
        void shouldIdentify502AsBadGateway() {
            ServerErrorException exception = createException(502);
            assertTrue(exception.isBadGateway());
        }

        @Test
        @DisplayName("should identify 503 as service unavailable")
        void shouldIdentify503AsServiceUnavailable() {
            ServerErrorException exception = createException(503);
            assertTrue(exception.isServiceUnavailable());
        }

        @Test
        @DisplayName("should identify 504 as gateway timeout")
        void shouldIdentify504AsGatewayTimeout() {
            ServerErrorException exception = createException(504);
            assertTrue(exception.isGatewayTimeout());
        }

        @Test
        @DisplayName("should not identify 500 as bad gateway")
        void shouldNotIdentify500AsBadGateway() {
            ServerErrorException exception = createException(500);
            assertFalse(exception.isBadGateway());
        }
    }

    @Nested
    @DisplayName("isRetryable Tests")
    class IsRetryableTests {

        @ParameterizedTest
        @ValueSource(ints = {500, 502, 503, 504})
        @DisplayName("should be retryable for most 5xx errors")
        void shouldBeRetryableForMost5xx(int statusCode) {
            ServerErrorException exception = createException(statusCode);
            assertTrue(exception.isRetryable(), "Status " + statusCode + " should be retryable");
        }

        @Test
        @DisplayName("501 Not Implemented should NOT be retryable")
        void shouldNotBeRetryableFor501() {
            ServerErrorException exception = createException(501);
            assertFalse(exception.isRetryable());
        }

        @ParameterizedTest
        @ValueSource(ints = {505, 506, 507, 508, 510, 511})
        @DisplayName("should be retryable for other 5xx errors")
        void shouldBeRetryableForOther5xx(int statusCode) {
            ServerErrorException exception = createException(statusCode);
            assertTrue(exception.isRetryable());
        }
    }

    @Nested
    @DisplayName("Exception Hierarchy Tests")
    class ExceptionHierarchyTests {

        @Test
        @DisplayName("should be instance of HttpException")
        void shouldBeInstanceOfHttpException() {
            ServerErrorException exception = createException(500);
            assertTrue(exception instanceof HttpException);
        }

        @Test
        @DisplayName("should be instance of TransportException")
        void shouldBeInstanceOfTransportException() {
            ServerErrorException exception = createException(500);
            assertTrue(exception instanceof TransportException);
        }

        @Test
        @DisplayName("should be catchable as HttpException")
        void shouldBeCatchableAsHttpException() {
            try {
                throw createException(503);
            } catch (HttpException e) {
                assertTrue(e instanceof ServerErrorException);
                assertEquals(503, e.getStatusCode());
            }
        }

        @Test
        @DisplayName("should be catchable as TransportException with isRetryable")
        void shouldBeCatchableAsTransportExceptionWithIsRetryable() {
            try {
                throw createException(502);
            } catch (TransportException e) {
                assertTrue(e.isRetryable());
            }
        }
    }

    @Nested
    @DisplayName("Inherited Methods Tests")
    class InheritedMethodsTests {

        @Test
        @DisplayName("should inherit isServerError returning true")
        void shouldInheritIsServerError() {
            ServerErrorException exception = createException(500);
            assertTrue(exception.isServerError());
        }

        @Test
        @DisplayName("should inherit isClientError returning false")
        void shouldInheritIsClientError() {
            ServerErrorException exception = createException(500);
            assertFalse(exception.isClientError());
        }

        @Test
        @DisplayName("should inherit getUrl")
        void shouldInheritGetUrl() {
            ServerErrorException exception = createException(500);
            assertEquals(TEST_URL, exception.getUrl());
        }

        @Test
        @DisplayName("should inherit getHttpMethod")
        void shouldInheritGetHttpMethod() {
            Request request = Request.builder()
                .method("POST")
                .url(TEST_URL)
                .build();
            Response response = createResponse(500, "{}");
            ServerErrorException exception = new ServerErrorException(500, "Error", request, response);

            assertEquals("POST", exception.getHttpMethod());
        }
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("should create exception with all parameters")
        void shouldCreateWithAllParameters() {
            Request request = createRequest();
            Response response = createResponse(500, "{\"error\":\"internal error\"}");

            ServerErrorException exception = new ServerErrorException(500, "Internal Server Error", request, response);

            assertEquals(500, exception.getStatusCode());
            assertEquals("Internal Server Error", exception.getMessage());
            assertNotNull(exception.getRequest());
            assertNotNull(exception.getResponse());
        }

        @Test
        @DisplayName("should create exception with cause")
        void shouldCreateWithCause() {
            Request request = createRequest();
            Response response = createResponse(500, "{}");
            Exception cause = new RuntimeException("database error");

            ServerErrorException exception = new ServerErrorException(500, "Error", request, response, cause);

            assertEquals(cause, exception.getCause());
        }
    }

    private ServerErrorException createException(int statusCode) {
        Request request = createRequest();
        Response response = createResponse(statusCode, "{}");
        return new ServerErrorException(statusCode, "Error " + statusCode, request, response);
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

