package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Response;

/**
 * Exception thrown for batch API errors.
 * These occur when processing batch operations.
 */
public class BatchAPIException extends APIException {

    public BatchAPIException(int statusCode, String type, String apiErrorCode, String message,
                            String jsonResponse, Response response) {
        super(statusCode, type, apiErrorCode, message, jsonResponse, response);
    }
}
