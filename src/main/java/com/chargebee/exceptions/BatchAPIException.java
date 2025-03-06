package com.chargebee.exceptions;

import com.chargebee.APIException;
import org.json.JSONObject;
import java.util.List;
import java.util.Map;

import static com.chargebee.BatchConstants.CORRELATION_ID;

public class BatchAPIException extends APIException {
    String correlationId;

    public BatchAPIException(int httpStatusCode, String message, JSONObject jsonObj) {
        super(httpStatusCode, message, jsonObj);
        this.correlationId = jsonObj.optString(CORRELATION_ID);
    }
    public BatchAPIException(int httpStatusCode, String message, JSONObject jsonObj, Map<String, List<String>> responseHeaders) {
        super(httpStatusCode, message, jsonObj, responseHeaders);
        this.correlationId = jsonObj.optString(CORRELATION_ID);
    }
    public String getCorrelationId() {
        return correlationId;
    }

}
