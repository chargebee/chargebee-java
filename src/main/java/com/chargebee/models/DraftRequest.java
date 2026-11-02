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

public class DraftRequest extends Resource<DraftRequest> {

    public enum Status {
        OPEN,
        EXECUTED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class DraftRevision extends Resource<DraftRevision> {
        public DraftRevision(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Timestamp version() {
            return reqTimestamp("version");
        }

        public Timestamp createdAt() {
            return reqTimestamp("created_at");
        }

        public JSONObject payload() {
            return optJSONObject("payload");
        }

    }

    public static class Validation extends Resource<Validation> {
        public Validation(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String category() {
            return optString("category");
        }

        public String level() {
            return optString("level");
        }

        public String message() {
            return optString("message");
        }

        public String paramName() {
            return optString("param_name");
        }

        public String type() {
            return optString("type");
        }

    }

    public static class Execution extends Resource<Execution> {
        public enum Status {
             SUCCESS,FAILED,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public Execution(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Status status() {
            return reqEnum("status", Status.class);
        }

        public Timestamp createdAt() {
            return reqTimestamp("created_at");
        }

        public JSONObject result() {
            return optJSONObject("result");
        }

        public JSONObject errorDetail() {
            return optJSONObject("error_detail");
        }

        public Timestamp revisionVersion() {
            return optTimestamp("revision_version");
        }

    }

    //Constructors
    //============

    public DraftRequest(String jsonStr) {
        super(jsonStr);
    }

    public DraftRequest(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String pathParam() {
        return optString("path_param");
    }

    public String requestUri() {
        return optString("request_uri");
    }

    public String operationId() {
        return reqString("operation_id");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp executedAt() {
        return optTimestamp("executed_at");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return reqTimestamp("updated_at");
    }

    public DraftRequest.DraftRevision currentRevision() {
        return optSubResource("current_revision", DraftRequest.DraftRevision.class);
    }

    public List<DraftRequest.Validation> validations() {
        return optList("validations", DraftRequest.Validation.class);
    }

    public Boolean hasComments() {
        return optBoolean("has_comments");
    }

    public Boolean hasCitations() {
        return optBoolean("has_citations");
    }

    public List<DraftRequest.Execution> executions() {
        return optList("executions", DraftRequest.Execution.class);
    }

    // Operations
    //===========


}
