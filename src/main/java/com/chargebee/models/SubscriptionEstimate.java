package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class SubscriptionEstimate extends Resource<SubscriptionEstimate> {

    public enum Status {
        FUTURE,
        IN_TRIAL,
        ACTIVE,
        NON_RENEWING,
        CANCELLED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public SubscriptionEstimate(String jsonStr) {
        super(jsonStr);
    }

    public SubscriptionEstimate(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public Timestamp nextBillingAt() {
        return optTimestamp("next_billing_at");
    }

    // Operations
    //===========


}
