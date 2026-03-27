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

public class Alert extends Resource<Alert> {

    public enum Status {
        ENABLED,
        DISABLED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Scope {
        GLOBAL,
        SUBSCRIPTION,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Alert(String jsonStr) {
        super(jsonStr);
    }

    public Alert(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public Type type() {
        return optEnum("type", Type.class);
    }

    public String name() {
        return reqString("name");
    }

    public String description() {
        return optString("description");
    }

    public String meteredFeatureId() {
        return reqString("metered_feature_id");
    }

    public String subscriptionId() {
        return optString("subscription_id");
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public Timestamp alarmTriggeredAt() {
        return optTimestamp("alarm_triggered_at");
    }

    public Scope scope() {
        return optEnum("scope", Scope.class);
    }

    public String meta() {
        return optString("meta");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp updatedAt() {
        return reqTimestamp("updated_at");
    }

    // Operations
    //===========


}
