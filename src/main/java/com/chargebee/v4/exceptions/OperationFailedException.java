package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Response;

/**
 * Exception thrown when an operation fails (type: "operation_failed").
 * Examples include business logic violations, state conflicts, etc.
 */
public class OperationFailedException extends APIException {

    public OperationFailedException(int statusCode, String type, String apiErrorCode, String message,
                                   String jsonResponse, Response response) {
        super(statusCode, type, apiErrorCode, message, jsonResponse, response);
    }
}
