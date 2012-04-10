package com.chargebee;

import org.json.*;

public class APIException extends RuntimeException {

    private JSONObject jsonObj;
    public final int httpCode;
    public final String code;
    public final String message;
    public final String param;

    public APIException(JSONObject jsonObj) {
        this.jsonObj = jsonObj;
        try {
            this.httpCode = jsonObj.getInt("http_status_code");
            this.message = jsonObj.getString("error_msg");
            this.code = jsonObj.optString("error_code");
            this.param = jsonObj.optString("error_param");
        } catch(JSONException exp) {
            throw new RuntimeException(exp);
        }
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
