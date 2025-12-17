package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Request;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("NetworkException Tests")
class NetworkExceptionTest {

    private static final String TEST_URL = "https://test-site.chargebee.com/api/v2/customers";

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("should create exception with message and cause only")
        void shouldCreateWithMessageAndCauseOnly() {
            IOException cause = new IOException("Connection refused");
            NetworkException exception = new NetworkException("Network error", cause);

            assertEquals("Network error", exception.getMessage());
            assertEquals(cause, exception.getCause());
            assertNull(exception.getRequest());
            assertNull(exception.getUrl());
        }

        @Test
        @DisplayName("should create exception with full context")
        void shouldCreateWithFullContext() {
            UnknownHostException cause = new UnknownHostException("unknown-host.com");
            Request request = createRequest("GET", TEST_URL);

            NetworkException exception = new NetworkException("DNS lookup failed", cause, request);

            assertEquals("DNS lookup failed", exception.getMessage());
            assertEquals(cause, exception.getCause());
            assertNotNull(exception.getRequest());
            assertEquals(TEST_URL, exception.getUrl());
            assertEquals("GET", exception.getHttpMethod());
        }
    }

    @Nested
    @DisplayName("isRetryable Tests")
    class IsRetryableTests {

        @Test
        @DisplayName("should always be retryable")
        void shouldAlwaysBeRetryable() {
            NetworkException exception = new NetworkException("Network error", new IOException());
            assertTrue(exception.isRetryable());
        }

        @Test
        @DisplayName("should be retryable even with UnknownHostException")
        void shouldBeRetryableEvenWithUnknownHostException() {
            NetworkException exception = new NetworkException(
                "DNS failed", 
                new UnknownHostException("host.example.com")
            );
            assertTrue(exception.isRetryable());
        }
    }

    @Nested
    @DisplayName("Inherited Methods Tests")
    class InheritedMethodsTests {

        @Test
        @DisplayName("should inherit getUrl from TransportException")
        void shouldInheritGetUrl() {
            Request request = createRequest("POST", TEST_URL);
            NetworkException exception = new NetworkException("Error", new IOException(), request);

            assertEquals(TEST_URL, exception.getUrl());
        }

        @Test
        @DisplayName("should inherit getHttpMethod from TransportException")
        void shouldInheritGetHttpMethod() {
            Request request = createRequest("DELETE", TEST_URL);
            NetworkException exception = new NetworkException("Error", new IOException(), request);

            assertEquals("DELETE", exception.getHttpMethod());
        }

        @Test
        @DisplayName("should inherit getRequest from TransportException")
        void shouldInheritGetRequest() {
            Request request = createRequest("GET", TEST_URL);
            NetworkException exception = new NetworkException("Error", new IOException(), request);

            assertSame(request, exception.getRequest());
        }
    }

    @Nested
    @DisplayName("toString Tests")
    class ToStringTests {

        @Test
        @DisplayName("should include NetworkException in toString")
        void shouldIncludeClassNameInToString() {
            NetworkException exception = new NetworkException("Connection refused", new IOException());

            String toString = exception.toString();

            assertTrue(toString.contains("NetworkException"));
        }

        @Test
        @DisplayName("should include request context in toString")
        void shouldIncludeRequestContextInToString() {
            Request request = createRequest("GET", TEST_URL);
            NetworkException exception = new NetworkException("Error", new IOException("timeout"), request);

            String toString = exception.toString();

            assertTrue(toString.contains("method='GET'"));
            assertTrue(toString.contains(TEST_URL));
            assertTrue(toString.contains("IOException"));
        }
    }

    @Nested
    @DisplayName("Exception Hierarchy Tests")
    class ExceptionHierarchyTests {

        @Test
        @DisplayName("should be instance of TransportException")
        void shouldBeInstanceOfTransportException() {
            NetworkException exception = new NetworkException("Error", new IOException());
            assertTrue(exception instanceof TransportException);
        }

        @Test
        @DisplayName("should be catchable as TransportException")
        void shouldBeCatchableAsTransportException() {
            try {
                throw new NetworkException("Network error", new IOException());
            } catch (TransportException e) {
                assertTrue(e instanceof NetworkException);
                assertTrue(e.isRetryable());
            }
        }
    }

    private Request createRequest(String method, String url) {
        return Request.builder()
            .method(method)
            .url(url)
            .build();
    }
}

