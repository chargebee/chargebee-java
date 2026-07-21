package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.filters.*;
import com.chargebee.filters.enums.SortOrder;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class AsyncResponse extends Resource<AsyncResponse> {

    public enum Status {
        SUCCESS,
        FAILED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class RequestAsyncApi extends Resource<RequestAsyncApi> {
        public RequestAsyncApi(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public String resource() {
            return optString("resource");
        }

        public String operationType() {
            return optString("operation_type");
        }

        public String method() {
            return optString("method");
        }

        public String uri() {
            return optString("uri");
        }

        public String idempotencyKey() {
            return optString("idempotency_key");
        }

    }

    public static class Error extends Resource<Error> {
        public Error(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String message() {
            return optString("message");
        }

        public String type() {
            return optString("type");
        }

        public String apiErrorCode() {
            return optString("api_error_code");
        }

        public String errorCode() {
            return optString("error_code");
        }

        public String errorMsg() {
            return optString("error_msg");
        }

        public String httpStatusCode() {
            return optString("http_status_code");
        }

    }

    //Constructors
    //============

    public AsyncResponse(String jsonStr) {
        super(jsonStr);
    }

    public AsyncResponse(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String apiVersion() {
        return optString("api_version");
    }

    public Timestamp createdAt() {
        return optTimestamp("created_at");
    }

    public Timestamp completedAt() {
        return optTimestamp("completed_at");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public AsyncResponse.RequestAsyncApi request() {
        return optSubResource("request", AsyncResponse.RequestAsyncApi.class);
    }

    public AsyncResponse.Error errorDetail() {
        return optSubResource("error_detail", AsyncResponse.Error.class);
    }

    public JSONObject result() {
        return optJSONObject("result");
    }

    // Operations
    //===========


}
