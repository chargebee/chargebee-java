package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Request;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TransportException Tests")
class TransportExceptionTest {

    private static final String TEST_URL = "https://test-site.chargebee.com/api/v2/customers";
    private static final String TEST_MESSAGE = "Transport error occurred";

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("should create exception with message only")
        void shouldCreateWithMessageOnly() {
            TransportException exception = new TransportException(TEST_MESSAGE);

            assertEquals(TEST_MESSAGE, exception.getMessage());
            assertNull(exception.getCause());
            assertNull(exception.getRequest());
            assertNull(exception.getUrl());
            assertNull(exception.getHttpMethod());
        }

        @Test
        @DisplayName("should create exception with message and cause")
        void shouldCreateWithMessageAndCause() {
            Exception cause = new RuntimeException("root cause");
            TransportException exception = new TransportException(TEST_MESSAGE, cause);

            assertEquals(TEST_MESSAGE, exception.getMessage());
            assertEquals(cause, exception.getCause());
            assertNull(exception.getRequest());
        }

        @Test
        @DisplayName("should create exception with message, cause, and request")
        void shouldCreateWithMessageCauseAndRequest() {
            Exception cause = new RuntimeException("root cause");
            Request request = createRequest("GET", TEST_URL);

            TransportException exception = new TransportException(TEST_MESSAGE, cause, request);

            assertEquals(TEST_MESSAGE, exception.getMessage());
            assertEquals(cause, exception.getCause());
            assertNotNull(exception.getRequest());
            assertEquals(TEST_URL, exception.getUrl());
            assertEquals("GET", exception.getHttpMethod());
        }

        @Test
        @DisplayName("should create exception with message and request only")
        void shouldCreateWithMessageAndRequest() {
            Request request = createRequest("POST", TEST_URL);

            TransportException exception = new TransportException(TEST_MESSAGE, request);

            assertEquals(TEST_MESSAGE, exception.getMessage());
            assertNull(exception.getCause());
            assertEquals(TEST_URL, exception.getUrl());
            assertEquals("POST", exception.getHttpMethod());
        }
    }

    @Nested
    @DisplayName("Request Context Tests")
    class RequestContextTests {

        @Test
        @DisplayName("should return null URL when request is null")
        void shouldReturnNullUrlWhenRequestIsNull() {
            TransportException exception = new TransportException(TEST_MESSAGE);
            assertNull(exception.getUrl());
        }

        @Test
        @DisplayName("should return null method when request is null")
        void shouldReturnNullMethodWhenRequestIsNull() {
            TransportException exception = new TransportException(TEST_MESSAGE);
            assertNull(exception.getHttpMethod());
        }

        @Test
        @DisplayName("should return URL from request")
        void shouldReturnUrlFromRequest() {
            Request request = createRequest("GET", TEST_URL);
            TransportException exception = new TransportException(TEST_MESSAGE, request);

            assertEquals(TEST_URL, exception.getUrl());
        }

        @Test
        @DisplayName("should return HTTP method from request")
        void shouldReturnHttpMethodFromRequest() {
            Request request = createRequest("DELETE", TEST_URL);
            TransportException exception = new TransportException(TEST_MESSAGE, request);

            assertEquals("DELETE", exception.getHttpMethod());
        }
    }

    @Nested
    @DisplayName("isRetryable Tests")
    class IsRetryableTests {

        @Test
        @DisplayName("base TransportException should not be retryable by default")
        void shouldNotBeRetryableByDefault() {
            TransportException exception = new TransportException(TEST_MESSAGE);
            assertFalse(exception.isRetryable());
        }
    }

    @Nested
    @DisplayName("toString Tests")
    class ToStringTests {

        @Test
        @DisplayName("should include message in toString")
        void shouldIncludeMessageInToString() {
            TransportException exception = new TransportException(TEST_MESSAGE);

            String toString = exception.toString();

            assertTrue(toString.contains("TransportException"));
            assertTrue(toString.contains(TEST_MESSAGE));
        }

        @Test
        @DisplayName("should include request details in toString when available")
        void shouldIncludeRequestDetailsInToString() {
            Request request = createRequest("PUT", TEST_URL);
            TransportException exception = new TransportException(TEST_MESSAGE, request);

            String toString = exception.toString();

            assertTrue(toString.contains("method='PUT'"));
            assertTrue(toString.contains("url='" + TEST_URL + "'"));
        }

        @Test
        @DisplayName("should include cause details in toString when available")
        void shouldIncludeCauseDetailsInToString() {
            Exception cause = new RuntimeException("network failure");
            TransportException exception = new TransportException(TEST_MESSAGE, cause);

            String toString = exception.toString();

            assertTrue(toString.contains("cause=RuntimeException"));
            assertTrue(toString.contains("network failure"));
        }

        @Test
        @DisplayName("should handle cause with null message")
        void shouldHandleCauseWithNullMessage() {
            Exception cause = new RuntimeException();
            TransportException exception = new TransportException(TEST_MESSAGE, cause);

            String toString = exception.toString();

            assertTrue(toString.contains("cause=RuntimeException"));
            assertFalse(toString.contains("null"));
        }
    }

    private Request createRequest(String method, String url) {
        return Request.builder()
            .method(method)
            .url(url)
            .build();
    }
}

