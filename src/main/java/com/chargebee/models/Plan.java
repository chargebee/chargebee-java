package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class Plan extends Resource<Plan> {

    public enum PeriodUnit {
        WEEK,
        MONTH,
        YEAR,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum TrialPeriodUnit {
        DAY,
        MONTH,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Status {
        ACTIVE,
        ARCHIVED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Plan(String jsonStr) {
        super(jsonStr);
    }

    public Plan(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String name() {
        return reqString("name");
    }

    public String invoiceName() {
        return optString("invoice_name");
    }

    public Integer price() {
        return reqInteger("price");
    }

    public Integer period() {
        return reqInteger("period");
    }

    public PeriodUnit periodUnit() {
        return reqEnum("period_unit", PeriodUnit.class);
    }

    public Integer trialPeriod() {
        return optInteger("trial_period");
    }

    public TrialPeriodUnit trialPeriodUnit() {
        return optEnum("trial_period_unit", TrialPeriodUnit.class);
    }

    public Integer freeQuantity() {
        return reqInteger("free_quantity");
    }

    public Integer setupCost() {
        return optInteger("setup_cost");
    }

    public Double downgradePenalty() {
        return optDouble("downgrade_penalty");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp archivedAt() {
        return optTimestamp("archived_at");
    }

    public Integer billingCycles() {
        return optInteger("billing_cycles");
    }

    public String redirectUrl() {
        return optString("redirect_url");
    }

    // Operations
    //===========

    public static ListRequest list() throws IOException {
        String url = url("plans");
        return new ListRequest(url);
    }

    public static Request retrieve(String id) throws IOException {
        String url = url("plans", nullCheck(id));
        return new Request(Method.GET, url);
    }


}
