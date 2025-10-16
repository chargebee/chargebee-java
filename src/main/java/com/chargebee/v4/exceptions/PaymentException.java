package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Response;

/**
 * Exception thrown for payment-related errors (type: "payment").
 * Examples include card declined, insufficient funds, etc.
 */
public class PaymentException extends APIException {

    public PaymentException(int statusCode, String type, String apiErrorCode, String message,
                           String jsonResponse, Response response) {
        super(statusCode, type, apiErrorCode, message, jsonResponse, response);
    }
}
