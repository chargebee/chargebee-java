package com.chargebee.v4.exceptions;

import com.chargebee.v4.exceptions.codes.ApiErrorCode;
import com.chargebee.v4.transport.Request;
import com.chargebee.v4.transport.Response;

/**
 * Exception thrown for batch API errors.
 * These occur when processing batch operations.
 */
public class BatchAPIException extends APIException {

    public BatchAPIException(int statusCode, String type, ApiErrorCode apiErrorCode, String message,
                            String jsonResponse, Request request, Response response) {
        super(statusCode, type, apiErrorCode, message, jsonResponse, request, response);
    }
}
