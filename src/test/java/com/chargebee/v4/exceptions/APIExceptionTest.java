package com.chargebee.v4.exceptions;

import com.chargebee.v4.exceptions.codes.ApiErrorCode;
import com.chargebee.v4.exceptions.codes.BadRequestApiErrorCode;
import com.chargebee.v4.transport.Request;
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
        String jsonResponse = "{\"type\":\"invalid_request\",\"api_error_code\":\"param_wrong_value\",\"message\":\"Required field is missing\",\"param\":\"email\"}";
        Request mockRequest = createMockRequest();
        Response mockResponse = createMockResponse(400, jsonResponse);

        APIException exception = new APIException(
            400, "invalid_request", BadRequestApiErrorCode.PARAM_WRONG_VALUE, "Required field is missing", jsonResponse, mockRequest, mockResponse
        );

        assertEquals(400, exception.getStatusCode());
        assertEquals("invalid_request", exception.getType());
        assertEquals(BadRequestApiErrorCode.PARAM_WRONG_VALUE, exception.getApiErrorCode());
        assertEquals("param_wrong_value", exception.getApiErrorCodeRaw());
        assertEquals("Required field is missing", exception.getMessage());
        assertEquals(jsonResponse, exception.getJsonResponse());
        assertNotNull(exception.getParams());
        assertEquals(1, exception.getParams().size());
        assertEquals("email", exception.getParams().get(0));
        assertNotNull(exception.getRequest());
        assertEquals("https://test-site.chargebee.com/api/v2/customers", exception.getRequest().getUrl());
    }

    @Test
    @DisplayName("Should extract single param from error response")
    void shouldExtractSingleParamFromErrorResponse() {
        String jsonResponse = "{\"type\":\"invalid_request\",\"message\":\"Invalid parameter\",\"param\":\"customer_id\"}";
        Request mockRequest = createMockRequest();
        Response mockResponse = createMockResponse(400, jsonResponse);

        APIException exception = new APIException(
            400, "invalid_request", null, "Invalid parameter", jsonResponse, mockRequest, mockResponse
        );

        List<String> params = exception.getParams();
        assertEquals(1, params.size());
        assertEquals("customer_id", params.get(0));
    }

    @Test
    @DisplayName("Should extract multiple params from error response array")
    void shouldExtractMultipleParamsFromArray() {
        String jsonResponse = "{\"type\":\"invalid_request\",\"message\":\"Invalid parameters\",\"param\":[\"email\",\"phone\"]}";
        Request mockRequest = createMockRequest();
        Response mockResponse = createMockResponse(400, jsonResponse);

        APIException exception = new APIException(
            400, "invalid_request", null, "Invalid parameters", jsonResponse, mockRequest, mockResponse
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
        Request mockRequest = createMockRequest();
        Response mockResponse = createMockResponse(400, jsonResponse);

        APIException exception = new APIException(
            400, "operation_failed", null, "Operation failed", jsonResponse, mockRequest, mockResponse
        );

        List<String> params = exception.getParams();
        assertNotNull(params);
        assertTrue(params.isEmpty());
    }

    @Test
    @DisplayName("Should create meaningful toString representation")
    void shouldCreateMeaningfulToString() {
        String jsonResponse = "{\"type\":\"payment\",\"api_error_code\":\"payment_processing_failed\",\"message\":\"Card was declined\"}";
        Request mockRequest = createMockRequest();
        Response mockResponse = createMockResponse(402, jsonResponse);

        APIException exception = new PaymentException(
            402, "payment", BadRequestApiErrorCode.PAYMENT_PROCESSING_FAILED, "Card was declined", jsonResponse, mockRequest, mockResponse
        );

        String toString = exception.toString();
        assertTrue(toString.contains("PaymentException"));
        assertTrue(toString.contains("statusCode=402"));
        assertTrue(toString.contains("type='payment'"));
        assertTrue(toString.contains("apiErrorCode='payment_processing_failed'"));
        assertTrue(toString.contains("Card was declined"));
    }

    @Test
    @DisplayName("Should handle strongly-typed error type enum")
    void shouldHandleStronglyTypedErrorTypeEnum() {
        String jsonResponse = "{\"type\":\"invalid_request\",\"api_error_code\":\"duplicate_entry\",\"message\":\"Duplicate entry\"}";
        Request mockRequest = createMockRequest();
        Response mockResponse = createMockResponse(400, jsonResponse);

        APIException exception = new APIException(
            400, "invalid_request", BadRequestApiErrorCode.DUPLICATE_ENTRY, "Duplicate entry", jsonResponse, mockRequest, mockResponse
        );

        assertEquals(ErrorType.INVALID_REQUEST, exception.getErrorType());
        assertTrue(exception.getErrorType().isKnown());
        assertEquals("invalid_request", exception.getType());
    }

    @Test
    @DisplayName("Should handle unknown error type gracefully")
    void shouldHandleUnknownErrorTypeGracefully() {
        String jsonResponse = "{\"type\":\"future_error_type\",\"message\":\"Some future error\"}";
        Request mockRequest = createMockRequest();
        Response mockResponse = createMockResponse(400, jsonResponse);

        APIException exception = new APIException(
            400, "future_error_type", null, "Some future error", jsonResponse, mockRequest, mockResponse
        );

        assertEquals(ErrorType._UNKNOWN, exception.getErrorType());
        assertFalse(exception.getErrorType().isKnown());
        assertEquals("future_error_type", exception.getType()); // Raw value still accessible
    }

    @Test
    @DisplayName("Should extract error_cause_id from response")
    void shouldExtractErrorCauseIdFromResponse() {
        String jsonResponse = "{\"type\":\"payment\",\"api_error_code\":\"payment_processing_failed\",\"message\":\"Payment failed\",\"error_cause_id\":\"card_declined_do_not_honor\"}";
        Request mockRequest = createMockRequest();
        Response mockResponse = createMockResponse(402, jsonResponse);

        APIException exception = new PaymentException(
            402, "payment", BadRequestApiErrorCode.PAYMENT_PROCESSING_FAILED, "Payment failed", jsonResponse, mockRequest, mockResponse
        );

        assertEquals("card_declined_do_not_honor", exception.getErrorCauseId());
    }

    @Test
    @DisplayName("Should handle unknown api_error_code with _UNKNOWN enum")
    void shouldHandleUnknownApiErrorCodeWithUnknownEnum() {
        String jsonResponse = "{\"type\":\"invalid_request\",\"api_error_code\":\"some_future_error_code\",\"message\":\"Unknown error\"}";
        Request mockRequest = createMockRequest();
        Response mockResponse = createMockResponse(400, jsonResponse);

        // Simulate parsing an unknown error code
        ApiErrorCode errorCode = BadRequestApiErrorCode.fromString("some_future_error_code");

        APIException exception = new APIException(
            400, "invalid_request", errorCode, "Unknown error", jsonResponse, mockRequest, mockResponse
        );

        assertEquals(BadRequestApiErrorCode._UNKNOWN, exception.getApiErrorCode());
        assertFalse(((BadRequestApiErrorCode) exception.getApiErrorCode()).isKnown());
        assertNull(exception.getApiErrorCodeRaw()); // _UNKNOWN has null value
    }

    private Request createMockRequest() {
        return Request.builder()
            .method("POST")
            .url("https://test-site.chargebee.com/api/v2/customers")
            .header("Authorization", "Basic dGVzdF9hcGlfa2V5Og==")
            .build();
    }

    private Response createMockResponse(int statusCode, String body) {
        Map<String, List<String>> headers = new HashMap<>();
        return new Response(statusCode, headers, body.getBytes());
    }
}
