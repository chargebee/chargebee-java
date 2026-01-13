package com.chargebee;

import com.chargebee.v4.client.ChargebeeClient;
import com.chargebee.v4.client.request.RequestInterceptor;
import com.chargebee.v4.transport.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.Arrays;

/**
 * Tests for RequestInterceptor functionality.
 */
public class RequestInterceptorTest {
    
    private FakeTransport fakeTransport;
    
    @BeforeEach
    void setUp() {
        fakeTransport = new FakeTransport();
        fakeTransport.setDefaultResponse(200, "{\"success\": true}");
    }
    
    @Test
    void testBasicInterceptorInvocation() throws Exception {
        final boolean[] interceptorCalled = {false};
        
        RequestInterceptor interceptor = requestWrap -> {
            interceptorCalled[0] = true;
            assertTrue(requestWrap.getRequest().getUrl().contains("/customers"));
            return requestWrap.proceed();
        };
        
        ChargebeeClient client = ChargebeeClient.builder()
            .apiKey("cb_test_123")
            .siteName("test")
            .transport(fakeTransport)
            .requestInterceptor(interceptor)
            .build();
        
        Response response = client.get("/customers");
        
        assertTrue(interceptorCalled[0], "Interceptor should have been called");
        assertEquals(200, response.getStatusCode());
        assertTrue(response.isSuccessful());
    }
    
    @Test
    void testInterceptorCanModifyHeaders() throws Exception {
        RequestInterceptor interceptor = requestWrap -> {
            // Modify request by rebuilding with custom header
            Request originalRequest = requestWrap.getRequest();
            Request modifiedRequest = Request.builder()
                .method(originalRequest.getMethod())
                .url(originalRequest.getUrl())
                .headers(originalRequest.getHeaders())
                .header("X-Custom-Header", "test-value")
                .build();
            requestWrap.setRequest(modifiedRequest);
            return requestWrap.proceed();
        };
        
        ChargebeeClient client = ChargebeeClient.builder()
            .apiKey("cb_test_123")
            .siteName("test")
            .transport(fakeTransport)
            .requestInterceptor(interceptor)
            .build();
        
        client.get("/customers");
        
        // Verify the transport received the custom header
        Request lastRequest = fakeTransport.getLastRequest();
        assertNotNull(lastRequest);
        assertEquals("test-value", lastRequest.getHeaders().get("X-Custom-Header"));
    }
    
    @Test
    void testInterceptorCanOverrideResponse() throws Exception {
        RequestInterceptor interceptor = requestWrap -> {
            // Return custom response without calling proceed()
            Map<String, List<String>> headers = new HashMap<>();
            headers.put("Content-Type", Arrays.asList("application/json; charset=utf-8"));
            return new Response(201, headers, "{\"intercepted\": true}".getBytes());
        };
        
        ChargebeeClient client = ChargebeeClient.builder()
            .apiKey("cb_test_123")
            .siteName("test")
            .transport(fakeTransport)
            .requestInterceptor(interceptor)
            .build();
        
        Response response = client.get("/customers");
        
        assertEquals(201, response.getStatusCode());
        assertEquals("{\"intercepted\": true}", response.getBodyAsString());
        
        // Verify transport was never called
        assertNull(fakeTransport.getLastRequest());
    }
    
    @Test
    void testNoInterceptorWhenNotConfigured() throws Exception {
        ChargebeeClient client = ChargebeeClient.builder()
            .apiKey("cb_test_123")
            .siteName("test")
            .transport(fakeTransport)
            .build();
        
        Response response = client.get("/customers");
        
        assertEquals(200, response.getStatusCode());
        assertEquals("{\"success\": true}", response.getBodyAsString());
        
        // Verify transport was called directly
        assertNotNull(fakeTransport.getLastRequest());
    }
    
    @Test
    void testInterceptorWithPostRequest() throws Exception {
        final List<String> methodsCalled = new ArrayList<>();
        
        RequestInterceptor interceptor = requestWrap -> {
            methodsCalled.add(requestWrap.getRequest().getMethod());
            return requestWrap.proceed();
        };
        
        ChargebeeClient client = ChargebeeClient.builder()
            .apiKey("cb_test_123")
            .siteName("test")
            .transport(fakeTransport)
            .requestInterceptor(interceptor)
            .build();
        
        Map<String, Object> formData = new HashMap<>();
        formData.put("email", "test@example.com");
        client.post("/customers", formData);
        
        assertEquals(1, methodsCalled.size());
        assertEquals("POST", methodsCalled.get(0));
        
        Request lastRequest = fakeTransport.getLastRequest();
        assertEquals("POST", lastRequest.getMethod());
    }
    
    @Test
    void testInterceptorWithJsonRequest() throws Exception {
        RequestInterceptor interceptor = requestWrap -> {
            // Modify request by rebuilding with Content-Type header
            Request originalRequest = requestWrap.getRequest();
            Request modifiedRequest = Request.builder()
                .method(originalRequest.getMethod())
                .url(originalRequest.getUrl())
                .headers(originalRequest.getHeaders())
                .header("Content-Type", "application/json")
                .build();
            requestWrap.setRequest(modifiedRequest);
            return requestWrap.proceed();
        };
        
        ChargebeeClient client = ChargebeeClient.builder()
            .apiKey("cb_test_123")
            .siteName("test")
            .transport(fakeTransport)
            .requestInterceptor(interceptor)
            .build();
        
        client.postJson("/customers", "{\"email\": \"test@example.com\"}");
        
        Request lastRequest = fakeTransport.getLastRequest();
        assertEquals("application/json", lastRequest.getHeaders().get("Content-Type"));
    }
    
    @Test
    void testInterceptorExceptionHandling() throws Exception {
        RequestInterceptor interceptor = requestWrap -> {
            throw new RuntimeException("Interceptor error");
        };
        
        ChargebeeClient client = ChargebeeClient.builder()
            .apiKey("cb_test_123")
            .siteName("test")
            .transport(fakeTransport)
            .requestInterceptor(interceptor)
            .build();
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            client.get("/customers");
        });
        
        assertEquals("Interceptor error", exception.getMessage());
    }
    
    @Test
    void testMultipleRequestsWithSameInterceptor() throws Exception {
        final List<String> uris = new ArrayList<>();
        
        RequestInterceptor interceptor = requestWrap -> {
            uris.add(requestWrap.getRequest().getUrl());
            return requestWrap.proceed();
        };
        
        ChargebeeClient client = ChargebeeClient.builder()
            .apiKey("cb_test_123")
            .siteName("test")
            .transport(fakeTransport)
            .requestInterceptor(interceptor)
            .build();
        
        client.get("/customers");
        client.get("/plans");
        Map<String, Object> subscriptionData = new HashMap<>();
        subscriptionData.put("customer_id", "cust_123");
        client.post("/subscriptions", subscriptionData);
        
        assertEquals(3, uris.size());
        assertTrue(uris.get(0).contains("/customers"));
        assertTrue(uris.get(1).contains("/plans"));
        assertTrue(uris.get(2).contains("/subscriptions"));
    }
    
    @Test
    void testInterceptorBuilder() {
        RequestInterceptor interceptor = requestWrap -> requestWrap.proceed();
        
        ChargebeeClient client = ChargebeeClient.builder()
            .apiKey("cb_test_123")
            .siteName("test")
            .requestInterceptor(interceptor)
            .build();
        
        assertEquals(interceptor, client.getRequestInterceptor());
    }
}