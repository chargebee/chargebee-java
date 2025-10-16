package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("APIException Tests")
class APIExceptionTest {

    @Test
    @DisplayName("Should create APIException with all fields")
    void shouldCreateAPIExceptionWithAllFields() {
        String jsonResponse = "{\"type\":\"invalid_request\",\"api_error_code\":\"missing_field\",\"message\":\"Required field is missing\",\"param\":\"email\"}";
        Response mockResponse = createMockResponse(400, jsonResponse);

        APIException exception = new APIException(
            400, "invalid_request", "missing_field", "Required field is missing", jsonResponse, mockResponse
        );

        assertEquals(400, exception.getStatusCode());
        assertEquals("invalid_request", exception.getType());
        assertEquals("missing_field", exception.getApiErrorCode());
        assertEquals("Required field is missing", exception.getMessage());
        assertEquals(jsonResponse, exception.getJsonResponse());
        assertNotNull(exception.getParams());
        assertEquals(1, exception.getParams().size());
        assertEquals("email", exception.getParams().get(0));
    }

    @Test
    @DisplayName("Should extract single param from error response")
    void shouldExtractSingleParamFromErrorResponse() {
        String jsonResponse = "{\"type\":\"invalid_request\",\"message\":\"Invalid parameter\",\"param\":\"customer_id\"}";
        Response mockResponse = createMockResponse(400, jsonResponse);

        APIException exception = new APIException(
            400, "invalid_request", null, "Invalid parameter", jsonResponse, mockResponse
        );

        List<String> params = exception.getParams();
        assertEquals(1, params.size());
        assertEquals("customer_id", params.get(0));
    }

    @Test
    @DisplayName("Should extract multiple params from error response array")
    void shouldExtractMultipleParamsFromArray() {
        String jsonResponse = "{\"type\":\"invalid_request\",\"message\":\"Invalid parameters\",\"param\":[\"email\",\"phone\"]}";
        Response mockResponse = createMockResponse(400, jsonResponse);

        APIException exception = new APIException(
            400, "invalid_request", null, "Invalid parameters", jsonResponse, mockResponse
        );

        List<String> params = exception.getParams();
        assertEquals(2, params.size());
        assertTrue(params.contains("email"));
        assertTrue(params.contains("phone"));
    }

    @Test
    @DisplayName("Should handle missing params gracefully")
    void shouldHandleMissingParamsGracefully() {
        String jsonResponse = "{\"type\":\"operation_failed\",\"message\":\"Operation failed\"}";
        Response mockResponse = createMockResponse(400, jsonResponse);

        APIException exception = new APIException(
            400, "operation_failed", null, "Operation failed", jsonResponse, mockResponse
        );

        List<String> params = exception.getParams();
        assertNotNull(params);
        assertTrue(params.isEmpty());
    }

    @Test
    @DisplayName("Should create meaningful toString representation")
    void shouldCreateMeaningfulToString() {
        String jsonResponse = "{\"type\":\"payment\",\"api_error_code\":\"card_declined\",\"message\":\"Card was declined\"}";
        Response mockResponse = createMockResponse(402, jsonResponse);

        APIException exception = new PaymentException(
            402, "payment", "card_declined", "Card was declined", jsonResponse, mockResponse
        );

        String toString = exception.toString();
        assertTrue(toString.contains("PaymentException"));
        assertTrue(toString.contains("statusCode=402"));
        assertTrue(toString.contains("type='payment'"));
        assertTrue(toString.contains("apiErrorCode='card_declined'"));
        assertTrue(toString.contains("Card was declined"));
    }

    private Response createMockResponse(int statusCode, String body) {
        Map<String, List<String>> headers = new HashMap<>();
        return new Response(statusCode, headers, body.getBytes());
    }
}
