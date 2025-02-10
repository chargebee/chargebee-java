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

public class Rule extends Resource<Rule> {

    public enum Status {
        ACTIVE,
        DISABLED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Rule(String jsonStr) {
        super(jsonStr);
    }

    public Rule(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String namespace() {
        return reqString("namespace");
    }

    public String ruleName() {
        return reqString("rule_name");
    }

    public Integer ruleOrder() {
        return optInteger("rule_order");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public String conditions() {
        return optString("conditions");
    }

    public String outcome() {
        return optString("outcome");
    }

    public Boolean deleted() {
        return reqBoolean("deleted");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp modifiedAt() {
        return reqTimestamp("modified_at");
    }

    // Operations
    //===========

    public static Request retrieve(String id) {
        String uri = uri("rules", nullCheck(id));
        return new Request(Method.GET, uri);
    }


}
