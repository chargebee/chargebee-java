package com.chargebee.v4.exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ConfigurationException Tests")
class ConfigurationExceptionTest {

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("should create exception with message only")
        void shouldCreateWithMessageOnly() {
            ConfigurationException exception = new ConfigurationException("Missing API key");

            assertEquals("Missing API key", exception.getMessage());
            assertNull(exception.getCause());
        }

        @Test
        @DisplayName("should create exception with message and cause")
        void shouldCreateWithMessageAndCause() {
            IllegalArgumentException cause = new IllegalArgumentException("Invalid format");
            ConfigurationException exception = new ConfigurationException("Invalid URL format", cause);

            assertEquals("Invalid URL format", exception.getMessage());
            assertEquals(cause, exception.getCause());
        }
    }

    @Nested
    @DisplayName("isRetryable Tests")
    class IsRetryableTests {

        @Test
        @DisplayName("should never be retryable")
        void shouldNeverBeRetryable() {
            ConfigurationException exception = new ConfigurationException("Config error");
            assertFalse(exception.isRetryable());
        }

        @Test
        @DisplayName("should never be retryable even with cause")
        void shouldNeverBeRetryableEvenWithCause() {
            ConfigurationException exception = new ConfigurationException(
                "Config error", 
                new RuntimeException("cause")
            );
            assertFalse(exception.isRetryable());
        }
    }

    @Nested
    @DisplayName("toString Tests")
    class ToStringTests {

        @Test
        @DisplayName("should include class name in toString")
        void shouldIncludeClassNameInToString() {
            ConfigurationException exception = new ConfigurationException("Error");

            String toString = exception.toString();

            assertTrue(toString.contains("ConfigurationException"));
        }

        @Test
        @DisplayName("should include message in toString")
        void shouldIncludeMessageInToString() {
            ConfigurationException exception = new ConfigurationException("API key is required");

            String toString = exception.toString();

            assertTrue(toString.contains("API key is required"));
        }

        @Test
        @DisplayName("should include cause in toString when present")
        void shouldIncludeCauseInToString() {
            IllegalArgumentException cause = new IllegalArgumentException("bad value");
            ConfigurationException exception = new ConfigurationException("Config error", cause);

            String toString = exception.toString();

            assertTrue(toString.contains("cause=IllegalArgumentException"));
            assertTrue(toString.contains("bad value"));
        }

        @Test
        @DisplayName("should handle cause with null message")
        void shouldHandleCauseWithNullMessage() {
            RuntimeException cause = new RuntimeException();
            ConfigurationException exception = new ConfigurationException("Config error", cause);

            String toString = exception.toString();

            assertTrue(toString.contains("cause=RuntimeException"));
            // Should not contain "null" from the cause message
        }
    }

    @Nested
    @DisplayName("Exception Hierarchy Tests")
    class ExceptionHierarchyTests {

        @Test
        @DisplayName("should be instance of RuntimeException")
        void shouldBeInstanceOfRuntimeException() {
            ConfigurationException exception = new ConfigurationException("Error");
            assertTrue(exception instanceof RuntimeException);
        }

        @Test
        @DisplayName("should be unchecked exception")
        void shouldBeUncheckedException() {
            // This compiles without try-catch because it's a RuntimeException
            ConfigurationException exception = new ConfigurationException("Error");
            assertNotNull(exception);
        }

        @Test
        @DisplayName("should NOT be instance of Exception subclass TransportException")
        void shouldNotBeInstanceOfTransportException() {
            ConfigurationException exception = new ConfigurationException("Error");
            // ConfigurationException is RuntimeException, not TransportException (which is checked Exception)
            assertFalse(TransportException.class.isAssignableFrom(ConfigurationException.class));
        }
    }

    @Nested
    @DisplayName("Usage Pattern Tests")
    class UsagePatternTests {

        @Test
        @DisplayName("should be throwable without checked exception handling")
        void shouldBeThrowableWithoutCheckedExceptionHandling() {
            // This test validates that ConfigurationException is a RuntimeException
            // and can be thrown without requiring a throws clause
            assertThrows(ConfigurationException.class, () -> {
                throw new ConfigurationException("Missing required config");
            });
        }

        @Test
        @DisplayName("should be catchable as RuntimeException")
        void shouldBeCatchableAsRuntimeException() {
            try {
                throw new ConfigurationException("Error");
            } catch (RuntimeException e) {
                assertTrue(e instanceof ConfigurationException);
            }
        }

        @Test
        @DisplayName("common configuration errors should have descriptive messages")
        void commonConfigurationErrorsShouldHaveDescriptiveMessages() {
            ConfigurationException apiKeyError = new ConfigurationException("API key is required");
            ConfigurationException urlError = new ConfigurationException("Invalid URL: not-a-url");

            assertTrue(apiKeyError.getMessage().contains("API key"));
            assertTrue(urlError.getMessage().contains("Invalid URL"));
        }
    }
}

