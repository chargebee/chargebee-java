package com.chargebee.v4.client;

import com.chargebee.v4.transport.*;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import static org.junit.jupiter.api.Assertions.*;

class CustomTransportHeaderTest {
    
    @Test
    void customTransportShouldReceiveDefaultHeaders() throws Exception {
        AtomicReference<Request> capturedRequest = new AtomicReference<>();
        
        Transport customTransport = new Transport() {
            @Override
            public Response send(Request request) throws TransportException {
                capturedRequest.set(request);
                return new Response(200, java.util.Collections.emptyMap(), "{}".getBytes());
            }
            
            @Override
            public CompletableFuture<Response> sendAsync(Request request) {
                capturedRequest.set(request);
                return CompletableFuture.completedFuture(
                    new Response(200, java.util.Collections.emptyMap(), "{}".getBytes())
                );
            }
        };
        
        ChargebeeClient client = ChargebeeClient.builder()
            .apiKey("test_api_key")
            .siteName("test-site")
            .transport(customTransport)
            .header("X-Client-Header", "client-value")
            .build();
        
        try {
            client.get("/test");
        } catch (Exception e) {
        }
        
        Request request = capturedRequest.get();
        assertNotNull(request);
        assertTrue(request.getHeaders().containsKey("Authorization"), 
            "Custom transport should receive Authorization header");
        assertTrue(request.getHeaders().containsKey("User-Agent"), 
            "Custom transport should receive User-Agent header");
        assertTrue(request.getHeaders().containsKey("Accept"), 
            "Custom transport should receive Accept header");
        assertTrue(request.getHeaders().containsKey("X-Client-Header"), 
            "Custom transport should receive client headers");
    }
}
