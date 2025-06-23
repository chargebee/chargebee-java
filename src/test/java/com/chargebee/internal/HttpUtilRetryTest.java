package com.chargebee.internal;

import com.chargebee.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedStatic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class HttpUtilRetryTest {

    private Environment env;
    private RetryConfig retryConfig;
    private Map<String, String> headers;

    @BeforeEach
    void setUp() {
        retryConfig = new RetryConfig(true, 3, 1000, new HashSet<>(Arrays.asList(503, 504)));
        env = new Environment("test-site", "test-key");
        env.updateRetryConfig(retryConfig);
        env.enableDebugLogging = false;
        headers = new HashMap<>();
    }

    @Test
    void testRetryOn503Error() throws IOException {
        String url = "https://test-site.chargebee.com/api/v2/customers";

        try (MockedStatic<HttpUtil> mockedHttpUtil = mockStatic(HttpUtil.class, CALLS_REAL_METHODS)) {
            HttpURLConnection mockConn1 = mock(HttpURLConnection.class);
            HttpURLConnection mockConn2 = mock(HttpURLConnection.class);

            when(mockConn1.getResponseCode()).thenReturn(503);
            when(mockConn1.getErrorStream()).thenReturn(new ByteArrayInputStream("Service Unavailable 503".getBytes()));
            when(mockConn1.getURL()).thenReturn(new URL(url));
            when(mockConn1.getRequestMethod()).thenReturn("GET");

            when(mockConn2.getResponseCode()).thenReturn(200);
            when(mockConn2.getInputStream()).thenReturn(new ByteArrayInputStream("{\"customer\":{\"id\":\"test\"}}".getBytes()));
            when(mockConn2.getHeaderFields()).thenReturn(new HashMap<>());

            AtomicInteger callCount = new AtomicInteger(0);
            mockedHttpUtil.when(() -> HttpUtil.createConnection(eq(url), eq(HttpUtil.Method.GET), eq(headers), eq(env)))
                .thenAnswer(invocation -> callCount.getAndIncrement() == 0 ? mockConn1 : mockConn2);

            assertDoesNotThrow(() -> HttpUtil.get(url, new Params(), headers, env));
        }
    }

    @Test
    void testRetryExhaustedThrowsException() throws IOException {
        String url = "https://test-site.chargebee.com/api/v2/customers";
        RetryConfig limitedRetry = new RetryConfig(true, 2, 100, new HashSet<>(Arrays.asList(503, 504)));
        Environment limitedEnv = new Environment("test-site", "test-key");
        limitedEnv.updateRetryConfig(limitedRetry);

        try (MockedStatic<HttpUtil> mockedHttpUtil = mockStatic(HttpUtil.class, CALLS_REAL_METHODS)) {
            HttpURLConnection mockConn = mock(HttpURLConnection.class);

            when(mockConn.getResponseCode()).thenReturn(503);
            when(mockConn.getErrorStream()).thenReturn(new ByteArrayInputStream("Service Unavailable 503".getBytes()));
            when(mockConn.getURL()).thenReturn(new URL(url));
            when(mockConn.getRequestMethod()).thenReturn("GET");

            mockedHttpUtil.when(() -> HttpUtil.createConnection(eq(url), eq(HttpUtil.Method.GET), eq(headers), eq(limitedEnv)))
                .thenReturn(mockConn);

            assertThrows(RuntimeException.class, () -> HttpUtil.get(url, new Params(), headers, limitedEnv));
        }
    }

    @Test
    void testRetryAfterHeaderRespected() throws IOException {
        String url = "https://test-site.chargebee.com/api/v2/customers";
        RetryConfig customRetry = new RetryConfig(true, 3, 500, new HashSet<>(Arrays.asList(429)));
        Environment customEnv = new Environment("test-site", "test-key");
        customEnv.updateRetryConfig(customRetry);

        try (MockedStatic<HttpUtil> mockedHttpUtil = mockStatic(HttpUtil.class, CALLS_REAL_METHODS)) {
            HttpURLConnection mockConn1 = mock(HttpURLConnection.class);
            HttpURLConnection mockConn2 = mock(HttpURLConnection.class);

            when(mockConn1.getResponseCode()).thenReturn(429);
            when(mockConn1.getHeaderField("Retry-After")).thenReturn("2");
            when(mockConn1.getErrorStream()).thenReturn(new ByteArrayInputStream("Rate Limited 429".getBytes()));
            when(mockConn1.getURL()).thenReturn(new URL(url));
            when(mockConn1.getRequestMethod()).thenReturn("GET");

            when(mockConn2.getResponseCode()).thenReturn(200);
            when(mockConn2.getInputStream()).thenReturn(new ByteArrayInputStream("{\"customer\":{\"id\":\"test\"}}".getBytes()));
            when(mockConn2.getHeaderFields()).thenReturn(new HashMap<>());

            AtomicInteger callCount = new AtomicInteger(0);
            mockedHttpUtil.when(() -> HttpUtil.createConnection(eq(url), eq(HttpUtil.Method.GET), eq(headers), eq(customEnv)))
                .thenAnswer(invocation -> callCount.getAndIncrement() == 0 ? mockConn1 : mockConn2);

            long startTime = System.currentTimeMillis();
            assertDoesNotThrow(() -> HttpUtil.get(url, new Params(), headers, customEnv));
            long endTime = System.currentTimeMillis();

            assertTrue(endTime - startTime >= 2000);
        }
    }

    @Test
    void testRetryDisabledDoesNotRetry() throws IOException {
        String url = "https://test-site.chargebee.com/api/v2/customers";
        RetryConfig disabledRetry = new RetryConfig(false, 3, 1000, new HashSet<>(Arrays.asList(503, 504)));
        Environment noRetryEnv = new Environment("test-site", "test-key");
        noRetryEnv.updateRetryConfig(disabledRetry);

        try (MockedStatic<HttpUtil> mockedHttpUtil = mockStatic(HttpUtil.class, CALLS_REAL_METHODS)) {
            HttpURLConnection mockConn = mock(HttpURLConnection.class);

            when(mockConn.getResponseCode()).thenThrow(new SocketTimeoutException("Read timeout"));
            when(mockConn.getURL()).thenReturn(new URL(url));
            when(mockConn.getRequestMethod()).thenReturn("GET");

            mockedHttpUtil.when(() -> HttpUtil.createConnection(eq(url), eq(HttpUtil.Method.GET), eq(headers), eq(noRetryEnv)))
                .thenReturn(mockConn);

            assertThrows(SocketTimeoutException.class, () -> HttpUtil.get(url, new Params(), headers, noRetryEnv));
        }
    }

    @Test
    void testRetryOnPostRequest() throws IOException {
        String url = "https://test-site.chargebee.com/api/v2/customers";
        Params params = new Params();
        params.add("id", "test-customer");

        try (MockedStatic<HttpUtil> mockedHttpUtil = mockStatic(HttpUtil.class, CALLS_REAL_METHODS)) {
            HttpURLConnection mockConn1 = mock(HttpURLConnection.class);
            HttpURLConnection mockConn2 = mock(HttpURLConnection.class);

            when(mockConn1.getOutputStream()).thenReturn(new java.io.ByteArrayOutputStream());
            when(mockConn2.getOutputStream()).thenReturn(new java.io.ByteArrayOutputStream());

            when(mockConn1.getResponseCode()).thenReturn(504);
            when(mockConn1.getErrorStream()).thenReturn(new ByteArrayInputStream("Gateway Timeout 504".getBytes()));
            when(mockConn1.getURL()).thenReturn(new URL(url));
            when(mockConn1.getRequestMethod()).thenReturn("POST");

            when(mockConn2.getResponseCode()).thenReturn(200);
            when(mockConn2.getInputStream()).thenReturn(new ByteArrayInputStream("{\"customer\":{\"id\":\"test-customer\"}}".getBytes()));
            when(mockConn2.getHeaderFields()).thenReturn(new HashMap<>());

            AtomicInteger callCount = new AtomicInteger(0);
            mockedHttpUtil.when(() -> HttpUtil.createConnection(eq(url), eq(HttpUtil.Method.POST), eq(headers), eq(env)))
                .thenAnswer(invocation -> callCount.getAndIncrement() == 0 ? mockConn1 : mockConn2);

            assertDoesNotThrow(() -> HttpUtil.post(url, params, headers, env));
        }
    }

    @Test
    void testExponentialBackoffWithJitter() throws IOException {
        String url = "https://test-site.chargebee.com/api/v2/customers";
        RetryConfig customRetry = new RetryConfig(true, 3, 500, new HashSet<>(Arrays.asList(503)));
        Environment customEnv = new Environment("test-site", "test-key");
        customEnv.updateRetryConfig(customRetry);

        try (MockedStatic<HttpUtil> mockedHttpUtil = mockStatic(HttpUtil.class, CALLS_REAL_METHODS)) {
            HttpURLConnection mockConn1 = mock(HttpURLConnection.class);
            HttpURLConnection mockConn2 = mock(HttpURLConnection.class);
            HttpURLConnection mockConn3 = mock(HttpURLConnection.class);

            when(mockConn1.getResponseCode()).thenReturn(503);
            when(mockConn1.getErrorStream()).thenReturn(new ByteArrayInputStream("Service Unavailable 503".getBytes()));
            when(mockConn1.getURL()).thenReturn(new URL(url));
            when(mockConn1.getRequestMethod()).thenReturn("GET");

            when(mockConn2.getResponseCode()).thenReturn(503);
            when(mockConn2.getErrorStream()).thenReturn(new ByteArrayInputStream("Service Unavailable 503".getBytes()));
            when(mockConn2.getURL()).thenReturn(new URL(url));
            when(mockConn2.getRequestMethod()).thenReturn("GET");

            when(mockConn3.getResponseCode()).thenReturn(200);
            when(mockConn3.getInputStream()).thenReturn(new ByteArrayInputStream("{\"customer\":{\"id\":\"test\"}}".getBytes()));
            when(mockConn3.getHeaderFields()).thenReturn(new HashMap<>());

            AtomicInteger callCount = new AtomicInteger(0);
            mockedHttpUtil.when(() -> HttpUtil.createConnection(eq(url), eq(HttpUtil.Method.GET), eq(headers), eq(customEnv)))
                .thenAnswer(invocation -> {
                    int c = callCount.getAndIncrement();
                    if (c == 0) return mockConn1;
                    if (c == 1) return mockConn2;
                    return mockConn3;
                });

            long startTime = System.currentTimeMillis();
            assertDoesNotThrow(() -> HttpUtil.get(url, new Params(), headers, customEnv));
            long endTime = System.currentTimeMillis();

            assertTrue(endTime - startTime >= 1500); // 500ms + 1000ms + processing time
        }
    }
}