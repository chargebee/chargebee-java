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

public class QuoteEntitlement extends Resource<QuoteEntitlement> {

    public enum EntityType {
        PLAN_PRICE,
        ADDON_PRICE,
        CHARGE_PRICE,
        CHARGE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public QuoteEntitlement(String jsonStr) {
        super(jsonStr);
    }

    public QuoteEntitlement(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String entityId() {
        return reqString("entity_id");
    }

    public EntityType entityType() {
        return reqEnum("entity_type", EntityType.class);
    }

    public String featureId() {
        return reqString("feature_id");
    }

    public String value() {
        return reqString("value");
    }

    public Boolean isEnabled() {
        return reqBoolean("is_enabled");
    }

    public Timestamp startDate() {
        return optTimestamp("start_date");
    }

    public Timestamp endDate() {
        return optTimestamp("end_date");
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
