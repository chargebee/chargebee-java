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

    public enum ChargeModel {
        FLAT_FEE,
        PER_UNIT,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Status {
        ACTIVE,
        ARCHIVED,
        DELETED,
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

    public String description() {
        return optString("description");
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

    public ChargeModel chargeModel() {
        return reqEnum("charge_model", ChargeModel.class);
    }

    public Integer freeQuantity() {
        return reqInteger("free_quantity");
    }

    public Integer setupCost() {
        return optInteger("setup_cost");
    }

    @Deprecated
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

    public Boolean enabledInHostedPages() {
        return reqBoolean("enabled_in_hosted_pages");
    }

    public String invoiceNotes() {
        return optString("invoice_notes");
    }

    // Operations
    //===========

    public static CreateRequest create() throws IOException {
        String uri = uri("plans");
        return new CreateRequest(Method.POST, uri);
    }

    public static UpdateRequest update(String id) throws IOException {
        String uri = uri("plans", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static ListRequest list() throws IOException {
        String uri = uri("plans");
        return new ListRequest(uri);
    }

    public static Request retrieve(String id) throws IOException {
        String uri = uri("plans", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static Request delete(String id) throws IOException {
        String uri = uri("plans", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
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


        public CreateRequest description(String description) {
            params.addOpt("description", description);
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


        public CreateRequest chargeModel(ChargeModel chargeModel) {
            params.addOpt("charge_model", chargeModel);
            return this;
        }


        public CreateRequest freeQuantity(Integer freeQuantity) {
            params.addOpt("free_quantity", freeQuantity);
            return this;
        }


        @Deprecated
        public CreateRequest downgradePenalty(Double downgradePenalty) {
            params.addOpt("downgrade_penalty", downgradePenalty);
            return this;
        }


        public CreateRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public CreateRequest enabledInHostedPages(Boolean enabledInHostedPages) {
            params.addOpt("enabled_in_hosted_pages", enabledInHostedPages);
            return this;
        }


        public CreateRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateRequest extends Request<UpdateRequest> {

        private UpdateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }


        public UpdateRequest name(String name) {
            params.addOpt("name", name);
            return this;
        }


        public UpdateRequest invoiceName(String invoiceName) {
            params.addOpt("invoice_name", invoiceName);
            return this;
        }


        public UpdateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        public UpdateRequest trialPeriod(Integer trialPeriod) {
            params.addOpt("trial_period", trialPeriod);
            return this;
        }


        public UpdateRequest trialPeriodUnit(TrialPeriodUnit trialPeriodUnit) {
            params.addOpt("trial_period_unit", trialPeriodUnit);
            return this;
        }


        public UpdateRequest period(Integer period) {
            params.addOpt("period", period);
            return this;
        }


        public UpdateRequest periodUnit(PeriodUnit periodUnit) {
            params.addOpt("period_unit", periodUnit);
            return this;
        }


        public UpdateRequest setupCost(Integer setupCost) {
            params.addOpt("setup_cost", setupCost);
            return this;
        }


        public UpdateRequest price(Integer price) {
            params.addOpt("price", price);
            return this;
        }


        public UpdateRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public UpdateRequest chargeModel(ChargeModel chargeModel) {
            params.addOpt("charge_model", chargeModel);
            return this;
        }


        public UpdateRequest freeQuantity(Integer freeQuantity) {
            params.addOpt("free_quantity", freeQuantity);
            return this;
        }


        @Deprecated
        public UpdateRequest downgradePenalty(Double downgradePenalty) {
            params.addOpt("downgrade_penalty", downgradePenalty);
            return this;
        }


        public UpdateRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public UpdateRequest enabledInHostedPages(Boolean enabledInHostedPages) {
            params.addOpt("enabled_in_hosted_pages", enabledInHostedPages);
            return this;
        }


        public UpdateRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
