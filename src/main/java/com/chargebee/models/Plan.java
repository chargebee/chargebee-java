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

    public static CreateRequest create() throws IOException {
        String uri = uri("plans");
        return new CreateRequest(Method.POST, uri);
    }

    public static ListRequest list() throws IOException {
        String uri = uri("plans");
        return new ListRequest(uri);
    }

    public static Request retrieve(String id) throws IOException {
        String uri = uri("plans", nullCheck(id));
        return new Request(Method.GET, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest id(String id) {
            params.add("id", id);
            return this;
        }

        public CreateRequest name(String name) {
            params.add("name", name);
            return this;
        }

        public CreateRequest invoiceName(String invoiceName) {
            params.addOpt("invoice_name", invoiceName);
            return this;
        }

        public CreateRequest trialPeriod(Integer trialPeriod) {
            params.addOpt("trial_period", trialPeriod);
            return this;
        }

        public CreateRequest trialPeriodUnit(TrialPeriodUnit trialPeriodUnit) {
            params.addOpt("trial_period_unit", trialPeriodUnit);
            return this;
        }

        public CreateRequest period(Integer period) {
            params.addOpt("period", period);
            return this;
        }

        public CreateRequest periodUnit(PeriodUnit periodUnit) {
            params.addOpt("period_unit", periodUnit);
            return this;
        }

        public CreateRequest setupCost(Integer setupCost) {
            params.addOpt("setup_cost", setupCost);
            return this;
        }

        public CreateRequest price(Integer price) {
            params.addOpt("price", price);
            return this;
        }

        public CreateRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }

        public CreateRequest freeQuantity(Integer freeQuantity) {
            params.addOpt("free_quantity", freeQuantity);
            return this;
        }

        public CreateRequest downgradePenalty(Double downgradePenalty) {
            params.addOpt("downgrade_penalty", downgradePenalty);
            return this;
        }

        public CreateRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

}
