package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Request;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.net.SocketTimeoutException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TimeoutException Tests")
class TimeoutExceptionTest {

    private static final String TEST_URL = "https://test-site.chargebee.com/api/v2/customers";

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("should create connect timeout exception")
        void shouldCreateConnectTimeoutException() {
            SocketTimeoutException cause = new SocketTimeoutException("connect timed out");
            
            TimeoutException exception = new TimeoutException(
                TimeoutException.CONNECT, 
                "Connection timeout", 
                cause
            );

            assertEquals("connect", exception.getTimeoutType());
            assertEquals("Connection timeout", exception.getMessage());
            assertEquals(cause, exception.getCause());
            assertNull(exception.getRequest());
        }

        @Test
        @DisplayName("should create read timeout exception")
        void shouldCreateReadTimeoutException() {
            SocketTimeoutException cause = new SocketTimeoutException("Read timed out");
            
            TimeoutException exception = new TimeoutException(
                TimeoutException.READ, 
                "Read timeout", 
                cause
            );

            assertEquals("read", exception.getTimeoutType());
            assertEquals("Read timeout", exception.getMessage());
        }

        @Test
        @DisplayName("should create timeout exception with full context")
        void shouldCreateWithFullContext() {
            SocketTimeoutException cause = new SocketTimeoutException("connect timed out");
            Request request = createRequest("POST", TEST_URL);

            TimeoutException exception = new TimeoutException(
                TimeoutException.CONNECT, 
                "Connection timeout", 
                cause, 
                request
            );

            assertEquals("connect", exception.getTimeoutType());
            assertNotNull(exception.getRequest());
            assertEquals(TEST_URL, exception.getUrl());
            assertEquals("POST", exception.getHttpMethod());
        }
    }

    @Nested
    @DisplayName("Timeout Type Tests")
    class TimeoutTypeTests {

        @Test
        @DisplayName("should identify connect timeout")
        void shouldIdentifyConnectTimeout() {
            TimeoutException exception = new TimeoutException(
                TimeoutException.CONNECT, 
                "Timeout", 
                new SocketTimeoutException()
            );

            assertTrue(exception.isConnectTimeout());
            assertFalse(exception.isReadTimeout());
        }

        @Test
        @DisplayName("should identify read timeout")
        void shouldIdentifyReadTimeout() {
            TimeoutException exception = new TimeoutException(
                TimeoutException.READ, 
                "Timeout", 
                new SocketTimeoutException()
            );

            assertFalse(exception.isConnectTimeout());
            assertTrue(exception.isReadTimeout());
        }

        @Test
        @DisplayName("should handle custom timeout type")
        void shouldHandleCustomTimeoutType() {
            TimeoutException exception = new TimeoutException(
                "custom", 
                "Custom timeout", 
                new SocketTimeoutException()
            );

            assertFalse(exception.isConnectTimeout());
            assertFalse(exception.isReadTimeout());
            assertEquals("custom", exception.getTimeoutType());
        }

        @Test
        @DisplayName("CONNECT constant should be 'connect'")
        void connectConstantShouldBeConnect() {
            assertEquals("connect", TimeoutException.CONNECT);
        }

        @Test
        @DisplayName("READ constant should be 'read'")
        void readConstantShouldBeRead() {
            assertEquals("read", TimeoutException.READ);
        }
    }

    @Nested
    @DisplayName("isRetryable Tests")
    class IsRetryableTests {

        @Test
        @DisplayName("connect timeout should be retryable")
        void connectTimeoutShouldBeRetryable() {
            TimeoutException exception = new TimeoutException(
                TimeoutException.CONNECT, 
                "Timeout", 
                new SocketTimeoutException()
            );

            assertTrue(exception.isRetryable());
        }

        @Test
        @DisplayName("read timeout should be retryable")
        void readTimeoutShouldBeRetryable() {
            TimeoutException exception = new TimeoutException(
                TimeoutException.READ, 
                "Timeout", 
                new SocketTimeoutException()
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
            Request request = createRequest("GET", TEST_URL);
            TimeoutException exception = new TimeoutException(
                TimeoutException.READ, 
                "Timeout", 
                new SocketTimeoutException(), 
                request
            );

            assertEquals(TEST_URL, exception.getUrl());
        }

        @Test
        @DisplayName("should return null URL when request not provided")
        void shouldReturnNullUrlWhenNoRequest() {
            TimeoutException exception = new TimeoutException(
                TimeoutException.READ, 
                "Timeout", 
                new SocketTimeoutException()
            );

            assertNull(exception.getUrl());
        }
    }

    @Nested
    @DisplayName("toString Tests")
    class ToStringTests {

        @Test
        @DisplayName("should include timeout type in toString")
        void shouldIncludeTimeoutTypeInToString() {
            TimeoutException exception = new TimeoutException(
                TimeoutException.CONNECT, 
                "Connection timeout", 
                new SocketTimeoutException()
            );

            String toString = exception.toString();

            assertTrue(toString.contains("TimeoutException"));
            assertTrue(toString.contains("timeoutType='connect'"));
        }

        @Test
        @DisplayName("should include request context in toString")
        void shouldIncludeRequestContextInToString() {
            Request request = createRequest("GET", TEST_URL);
            TimeoutException exception = new TimeoutException(
                TimeoutException.READ, 
                "Read timeout", 
                new SocketTimeoutException(), 
                request
            );

            String toString = exception.toString();

            assertTrue(toString.contains("method='GET'"));
            assertTrue(toString.contains(TEST_URL));
        }
    }

    @Nested
    @DisplayName("Exception Hierarchy Tests")
    class ExceptionHierarchyTests {

        @Test
        @DisplayName("should be instance of TransportException")
        void shouldBeInstanceOfTransportException() {
            TimeoutException exception = new TimeoutException(
                TimeoutException.CONNECT, 
                "Timeout", 
                new SocketTimeoutException()
            );

            assertTrue(exception instanceof TransportException);
        }

        @Test
        @DisplayName("should be catchable as TransportException")
        void shouldBeCatchableAsTransportException() {
            try {
                throw new TimeoutException(TimeoutException.READ, "Timeout", new SocketTimeoutException());
            } catch (TransportException e) {
                assertTrue(e instanceof TimeoutException);
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

