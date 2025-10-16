package com.chargebee.v4.exceptions;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.HttpException;
import com.chargebee.v4.transport.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Base exception for Chargebee API errors.
 * Contains parsed error information from the API response including type, message, and error parameters.
 */
public class APIException extends HttpException {
    private final String type;
    private final String apiErrorCode;
    private final String jsonResponse;
    private final List<String> params;

    public APIException(int statusCode, String type, String apiErrorCode, String message,
                       String jsonResponse, Response response) {
        super(statusCode, message, response);
        this.type = type;
        this.apiErrorCode = apiErrorCode;
        this.jsonResponse = jsonResponse;
        this.params = extractParams(jsonResponse);
    }

    /**
     * Get the error type from API response (e.g., "payment", "invalid_request", "operation_failed").
     */
    public String getType() {
        return type;
    }

    /**
     * Get the API-specific error code (e.g., "card_declined", "invalid_parameter").
     */
    public String getApiErrorCode() {
        return apiErrorCode;
    }

    /**
     * Get the full JSON response as string.
     */
    public String getJsonResponse() {
        return jsonResponse;
    }

    /**
     * Get the parameter names that were invalid (for validation errors).
     */
    public List<String> getParams() {
        return params;
    }

    /**
     * Extract parameter names from error response.
     */
    private List<String> extractParams(String jsonResponse) {
        List<String> paramsList = new ArrayList<>();
        if (jsonResponse != null) {
            // Try to extract single param string
            String param = JsonUtil.getString(jsonResponse, "param");
            if (param != null) {
                paramsList.add(param);
            } else {
                // Try to extract param array
                String paramArray = JsonUtil.getArray(jsonResponse, "param");
                if (paramArray != null) {
                    paramsList.addAll(JsonUtil.parseArrayOfString(paramArray));
                }
            }
        }
        return paramsList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("{statusCode=").append(getStatusCode());
        if (type != null) {
            sb.append(", type='").append(type).append("'");
        }
        if (apiErrorCode != null) {
            sb.append(", apiErrorCode='").append(apiErrorCode).append("'");
        }
        sb.append(", message='").append(getMessage()).append("'");
        if (params != null && !params.isEmpty()) {
            sb.append(", params=").append(params);
        }
        sb.append("}");
        return sb.toString();
    }
}
