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

public class AppliedRule extends Resource<AppliedRule> {

    //Constructors
    //============

    public AppliedRule(String jsonStr) {
        super(jsonStr);
    }

    public AppliedRule(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public Integer version() {
        return optInteger("version");
    }

    public String name() {
        return optString("name");
    }

    public String description() {
        return optString("description");
    }

    public Boolean evaluationResult() {
        return optBoolean("evaluation_result");
    }

    public String errorMessage() {
        return optString("error_message");
    }

    public JSONArray actions() {
        return optJSONArray("actions");
    }

    // Operations
    //===========


}
