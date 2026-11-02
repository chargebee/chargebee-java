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

public class Citation extends Resource<Citation> {

    public enum ConfidenceLevel {
        LOW,
        MEDIUM,
        HIGH,
        NOT_PROVIDED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Citation(String jsonStr) {
        super(jsonStr);
    }

    public Citation(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String paramName() {
        return optString("param_name");
    }

    public String authorName() {
        return reqString("author_name");
    }

    public ConfidenceLevel confidenceLevel() {
        return optEnum("confidence_level", ConfidenceLevel.class);
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    // Operations
    //===========


}
