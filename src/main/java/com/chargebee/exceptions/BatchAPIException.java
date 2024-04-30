package com.chargebee.exceptions;

import com.chargebee.APIException;
import org.json.JSONObject;

import static com.chargebee.BatchConstants.CORRELATION_ID;

public class BatchAPIException extends APIException {
    String correlationId;

    public BatchAPIException(int httpStatusCode, String message, JSONObject jsonObj) {
        super(httpStatusCode, message, jsonObj);
        this.correlationId = jsonObj.optString(CORRELATION_ID);
    }

    public String getCorrelationId() {
        return correlationId;
    }

}
