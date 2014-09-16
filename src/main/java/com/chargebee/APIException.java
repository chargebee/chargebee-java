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
    

    public APIException(int httpStatusCode,JSONObject jsonObj) throws Exception {
        super(jsonObj.getString("message"));
        this.jsonObj = jsonObj;
        this.httpStatusCode = httpStatusCode;
        this.apiErrorCode = jsonObj.getString("api_error_code");
        this.type = jsonObj.optString("type");
        this.param = jsonObj.optString("param");

        this.httpCode = httpStatusCode;
        this.code = jsonObj.getString("error_code");
        this.message = jsonObj.getString("error_msg");
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
