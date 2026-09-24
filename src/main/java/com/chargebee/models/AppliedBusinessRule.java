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

public class AppliedBusinessRule extends Resource<AppliedBusinessRule> {

    public enum EntityType {
        CPQ_QUOTE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public AppliedBusinessRule(String jsonStr) {
        super(jsonStr);
    }

    public AppliedBusinessRule(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String handle() {
        return reqString("handle");
    }

    public EntityType entityType() {
        return reqEnum("entity_type", EntityType.class);
    }

    public Long entityId() {
        return reqLong("entity_id");
    }

    public Integer entityVersion() {
        return optInteger("entity_version");
    }

    public String ruleId() {
        return reqString("rule_id");
    }

    public Integer version() {
        return reqInteger("version");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp modifiedAt() {
        return reqTimestamp("modified_at");
    }

    // Operations
    //===========


}
