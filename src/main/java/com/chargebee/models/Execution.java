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

public class Execution extends Resource<Execution> {

    public enum Status {
        SUCCESS,
        FAILED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Execution(String jsonStr) {
        super(jsonStr);
    }

    public Execution(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

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

    // Operations
    //===========


}
