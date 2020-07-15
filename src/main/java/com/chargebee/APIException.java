package com.chargebee;

import org.json.*;

public class APIException extends RuntimeException {

    public final JSONObject jsonObj;

    public final int httpStatusCode;
    public final String type;
    public final String param;
    public final String apiErrorCode;

    
    /**
     * Use {@link #httpStatusCode} instead.
     * @deprecated
     */
    @Deprecated
    public final int httpCode;
    
    /**
     * Use {@link #apiErrorCode} instead.
     * @deprecated
     */
    @Deprecated
    public final String code;
    /**
     * Use {@link #getMessage()} instead.
     * @deprecated
     */
    @Deprecated
    public final String message;
    

    public APIException(int httpStatusCode, String message, JSONObject jsonObj) {
        super(message);
        this.jsonObj = jsonObj;
        this.httpStatusCode = httpStatusCode;
        try {
            this.apiErrorCode = jsonObj.getString("api_error_code");
            this.code = jsonObj.getString("error_code");
            this.message = jsonObj.getString("error_msg");
        } catch (JSONException ex) {
            throw new RuntimeException("Error when parsing the error response. Probably not ChargeBee' error response.", ex);
        }
        this.type = jsonObj.optString("type");
        this.param = jsonObj.optString("param");

        this.httpCode = httpStatusCode;
    }
    
    
    public boolean isParamErr() {
        return param != null;
    }

    @Override
    public String toString() {
        try {
            return jsonObj.toString(2);
        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        }
    }
}
