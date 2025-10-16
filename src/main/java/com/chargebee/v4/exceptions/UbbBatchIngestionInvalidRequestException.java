package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Response;

/**
 * Exception thrown for usage-based billing (UBB) batch ingestion invalid request errors
 * (type: "ubb_batch_ingestion_invalid_request").
 */
public class UbbBatchIngestionInvalidRequestException extends APIException {

    public UbbBatchIngestionInvalidRequestException(int statusCode, String type, String apiErrorCode,
                                                   String message, String jsonResponse, Response response) {
        super(statusCode, type, apiErrorCode, message, jsonResponse, response);
    }
}
