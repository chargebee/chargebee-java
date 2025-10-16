package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Response;

/**
 * Exception thrown for invalid request errors (type: "invalid_request").
 * Examples include invalid parameters, missing required fields, validation errors, etc.
 */
public class InvalidRequestException extends APIException {

    public InvalidRequestException(int statusCode, String type, String apiErrorCode, String message,
                                  String jsonResponse, Response response) {
        super(statusCode, type, apiErrorCode, message, jsonResponse, response);
    }
}
