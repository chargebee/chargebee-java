package com.chargebee.v4.transport;

import com.chargebee.v4.exceptions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HttpStatusHandler Tests")
class HttpStatusHandlerTest {

    @Test
    @DisplayName("Should not throw exception for successful response")
    void shouldNotThrowExceptionForSuccessfulResponse() {
        Request request = createMockRequest("https://test-site.chargebee.com/api/v2/customers");
        Response response = createMockResponse(200, "{\"success\":true}");

        assertDoesNotThrow(() -> HttpStatusHandler.validateResponse(request, response));
    }

    @Test
    @DisplayName("Should throw PaymentException for payment error type")
    void shouldThrowPaymentExceptionForPaymentErrorType() {
        Request request = createMockRequest("https://test-site.chargebee.com/api/v2/invoices");
        String errorJson = "{\"type\":\"payment\",\"api_error_code\":\"card_declined\",\"message\":\"Your card was declined\"}";
        Response response = createMockResponse(402, errorJson);

        PaymentException exception = assertThrows(PaymentException.class,
            () -> HttpStatusHandler.validateResponse(request, response));

        assertEquals(402, exception.getStatusCode());
        assertEquals("payment", exception.getType());
        assertEquals("card_declined", exception.getApiErrorCode());
        assertEquals("Your card was declined", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw OperationFailedException for operation_failed error type")
    void shouldThrowOperationFailedExceptionForOperationFailedType() {
        Request request = createMockRequest("https://test-site.chargebee.com/api/v2/subscriptions");
        String errorJson = "{\"type\":\"operation_failed\",\"api_error_code\":\"state_conflict\",\"message\":\"Cannot perform this operation in current state\"}";
        Response response = createMockResponse(400, errorJson);

        OperationFailedException exception = assertThrows(OperationFailedException.class,
            () -> HttpStatusHandler.validateResponse(request, response));

        assertEquals(400, exception.getStatusCode());
        assertEquals("operation_failed", exception.getType());
        assertEquals("state_conflict", exception.getApiErrorCode());
    }

    @Test
    @DisplayName("Should throw InvalidRequestException for invalid_request error type")
    void shouldThrowInvalidRequestExceptionForInvalidRequestType() {
        Request request = createMockRequest("https://test-site.chargebee.com/api/v2/customers");
        String errorJson = "{\"type\":\"invalid_request\",\"api_error_code\":\"missing_field\",\"message\":\"Required field is missing\",\"param\":\"email\"}";
        Response response = createMockResponse(400, errorJson);

        InvalidRequestException exception = assertThrows(InvalidRequestException.class,
            () -> HttpStatusHandler.validateResponse(request, response));

        assertEquals(400, exception.getStatusCode());
        assertEquals("invalid_request", exception.getType());
        assertEquals("missing_field", exception.getApiErrorCode());
        assertEquals(1, exception.getParams().size());
        assertEquals("email", exception.getParams().get(0));
    }

    @Test
    @DisplayName("Should throw UbbBatchIngestionInvalidRequestException for ubb_batch_ingestion_invalid_request type")
    void shouldThrowUbbBatchIngestionExceptionForUbbBatchType() {
        Request request = createMockRequest("https://test-site.chargebee.com/api/v2/usages");
        String errorJson = "{\"type\":\"ubb_batch_ingestion_invalid_request\",\"api_error_code\":\"invalid_format\",\"message\":\"Batch data format is invalid\"}";
        Response response = createMockResponse(400, errorJson);

        UbbBatchIngestionInvalidRequestException exception = assertThrows(
            UbbBatchIngestionInvalidRequestException.class,
            () -> HttpStatusHandler.validateResponse(request, response));

        assertEquals(400, exception.getStatusCode());
        assertEquals("ubb_batch_ingestion_invalid_request", exception.getType());
        assertEquals("invalid_format", exception.getApiErrorCode());
    }

    @Test
    @DisplayName("Should throw generic APIException for unknown error type")
    void shouldThrowGenericAPIExceptionForUnknownType() {
        Request request = createMockRequest("https://test-site.chargebee.com/api/v2/customers");
        String errorJson = "{\"type\":\"unknown_error\",\"message\":\"Something went wrong\"}";
        Response response = createMockResponse(400, errorJson);

        APIException exception = assertThrows(APIException.class,
            () -> HttpStatusHandler.validateResponse(request, response));

        assertEquals(400, exception.getStatusCode());
        assertEquals("unknown_error", exception.getType());
        assertEquals("Something went wrong", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw generic APIException when type is missing but message and api_error_code exist")
    void shouldThrowGenericAPIExceptionWhenTypeIsMissing() {
        Request request = createMockRequest("https://test-site.chargebee.com/api/v2/customers");
        String errorJson = "{\"message\":\"An error occurred\",\"api_error_code\":\"general_error\"}";
        Response response = createMockResponse(400, errorJson);

        APIException exception = assertThrows(APIException.class,
            () -> HttpStatusHandler.validateResponse(request, response));

        assertEquals(400, exception.getStatusCode());
        assertNull(exception.getType());
        assertEquals("general_error", exception.getApiErrorCode());
        assertEquals("An error occurred", exception.getMessage());
    }

    @Test
    @DisplayName("Should fall back to ClientErrorException when only message field is present")
    void shouldFallBackToClientErrorExceptionWhenOnlyMessagePresent() {
        Request request = createMockRequest("https://test-site.chargebee.com/api/v2/customers");
        String errorJson = "{\"message\":\"Bad Request\"}";
        Response response = createMockResponse(400, errorJson);

        ClientErrorException exception = assertThrows(ClientErrorException.class,
            () -> HttpStatusHandler.validateResponse(request, response));

        assertEquals(400, exception.getStatusCode());
    }

    @Test
    @DisplayName("Should fall back to ClientErrorException for non-JSON error response")
    void shouldFallBackToClientErrorExceptionForNonJsonResponse() {
        Request request = createMockRequest("https://test-site.chargebee.com/api/v2/customers");
        String errorHtml = "<html><body>Bad Request</body></html>";
        Response response = createMockResponse(400, errorHtml);

        ClientErrorException exception = assertThrows(ClientErrorException.class,
            () -> HttpStatusHandler.validateResponse(request, response));

        assertEquals(400, exception.getStatusCode());
    }

    @Test
    @DisplayName("Should throw ServerErrorException for 5xx errors without JSON")
    void shouldThrowServerErrorExceptionFor5xxErrors() {
        Request request = createMockRequest("https://test-site.chargebee.com/api/v2/customers");
        String errorText = "Internal Server Error";
        Response response = createMockResponse(500, errorText);

        ServerErrorException exception = assertThrows(ServerErrorException.class,
            () -> HttpStatusHandler.validateResponse(request, response));

        assertEquals(500, exception.getStatusCode());
    }

    @Test
    @DisplayName("Should handle empty error response gracefully")
    void shouldHandleEmptyErrorResponseGracefully() {
        Request request = createMockRequest("https://test-site.chargebee.com/api/v2/customers");
        Response response = createMockResponse(400, "");

        ClientErrorException exception = assertThrows(ClientErrorException.class,
            () -> HttpStatusHandler.validateResponse(request, response));

        assertEquals(400, exception.getStatusCode());
    }

    @Test
    @DisplayName("Should use default message when message field is missing")
    void shouldUseDefaultMessageWhenMessageFieldIsMissing() {
        Request request = createMockRequest("https://test-site.chargebee.com/api/v2/invoices");
        String errorJson = "{\"type\":\"payment\",\"api_error_code\":\"card_declined\"}";
        Response response = createMockResponse(402, errorJson);

        PaymentException exception = assertThrows(PaymentException.class,
            () -> HttpStatusHandler.validateResponse(request, response));

        assertEquals("API Error", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw BatchAPIException when URL contains /batch/")
    void shouldThrowBatchAPIExceptionWhenUrlContainsBatch() {
        Request request = createMockRequest("https://test-site.chargebee.com/api/v2/batch/customers");
        String errorJson = "{\"type\":\"invalid_request\",\"api_error_code\":\"batch_error\",\"message\":\"Batch operation failed\"}";
        Response response = createMockResponse(400, errorJson);

        BatchAPIException exception = assertThrows(BatchAPIException.class,
            () -> HttpStatusHandler.validateResponse(request, response));

        assertEquals(400, exception.getStatusCode());
        assertEquals("invalid_request", exception.getType());
        assertEquals("batch_error", exception.getApiErrorCode());
    }

    private Request createMockRequest(String url) {
        return Request.builder()
            .method("GET")
            .url(url)
            .build();
    }

    private Response createMockResponse(int statusCode, String body) {
        Map<String, List<String>> headers = new HashMap<>();
        return new Response(statusCode, headers, body.getBytes());
    }
}
