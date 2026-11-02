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

public class RetrySegment extends Resource<RetrySegment> {

    public enum ConditionLogic {
        AND,
        OR,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public RetrySegment(String jsonStr) {
        super(jsonStr);
    }

    public RetrySegment(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String name() {
        return optString("name");
    }

    public String invoiceId() {
        return optString("invoice_id");
    }

    public Boolean enabled() {
        return reqBoolean("enabled");
    }

    public Integer priority() {
        return optInteger("priority");
    }

    public ConditionLogic conditionLogic() {
        return optEnum("condition_logic", ConditionLogic.class);
    }

    public JSONArray criteria() {
        return optJSONArray("criteria");
    }

    public JSONObject retrySettings() {
        return optJSONObject("retry_settings");
    }

    // Operations
    //===========

    public static RetrieveRequest retrieve(String id) {
        String uri = uri("retry_segments", nullCheck(id));
        return new RetrieveRequest(Method.GET, uri);
    }


    // Operation Request Classes
    //==========================

    public static class RetrieveRequest extends Request<RetrieveRequest> {

        private RetrieveRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RetrieveRequest invoiceId(String invoiceId) {
            params.addOpt("invoice_id", invoiceId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
