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

public class UnbilledCharge extends Resource<UnbilledCharge> {

    public enum EntityType {
        PLAN_SETUP,
        PLAN,
        ADDON,
        ADHOC,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class Tier extends Resource<Tier> {
        public Tier(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Integer startingUnit() {
            return reqInteger("starting_unit");
        }

        public Integer endingUnit() {
            return optInteger("ending_unit");
        }

        public Integer quantityUsed() {
            return reqInteger("quantity_used");
        }

        public Integer unitAmount() {
            return reqInteger("unit_amount");
        }

    }

    //Constructors
    //============

    public UnbilledCharge(String jsonStr) {
        super(jsonStr);
    }

    public UnbilledCharge(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return optString("id");
    }

    public String customerId() {
        return optString("customer_id");
    }

    public String subscriptionId() {
        return optString("subscription_id");
    }

    public Timestamp dateFrom() {
        return optTimestamp("date_from");
    }

    public Timestamp dateTo() {
        return optTimestamp("date_to");
    }

    public Integer unitAmount() {
        return optInteger("unit_amount");
    }

    public PricingModel pricingModel() {
        return optEnum("pricing_model", PricingModel.class);
    }

    public Integer quantity() {
        return optInteger("quantity");
    }

    public Integer amount() {
        return optInteger("amount");
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public Integer discountAmount() {
        return optInteger("discount_amount");
    }

    public String description() {
        return optString("description");
    }

    public EntityType entityType() {
        return reqEnum("entity_type", EntityType.class);
    }

    public String entityId() {
        return optString("entity_id");
    }

    public Boolean isVoided() {
        return reqBoolean("is_voided");
    }

    public Timestamp voidedAt() {
        return optTimestamp("voided_at");
    }

    public List<UnbilledCharge.Tier> tiers() {
        return optList("tiers", UnbilledCharge.Tier.class);
    }

    public Boolean deleted() {
        return reqBoolean("deleted");
    }

    // Operations
    //===========

    public static InvoiceUnbilledChargesRequest invoiceUnbilledCharges() {
        String uri = uri("unbilled_charges", "invoice_unbilled_charges");
        return new InvoiceUnbilledChargesRequest(Method.POST, uri);
    }

    public static Request delete(String id) {
        String uri = uri("unbilled_charges", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static UnbilledChargeListRequest list() {
        String uri = uri("unbilled_charges");
        return new UnbilledChargeListRequest(uri);
    }

    public static InvoiceNowEstimateRequest invoiceNowEstimate() {
        String uri = uri("unbilled_charges", "invoice_now_estimate");
        return new InvoiceNowEstimateRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class InvoiceUnbilledChargesRequest extends Request<InvoiceUnbilledChargesRequest> {

        private InvoiceUnbilledChargesRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public InvoiceUnbilledChargesRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public InvoiceUnbilledChargesRequest customerId(String customerId) {
            params.addOpt("customer_id", customerId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class UnbilledChargeListRequest extends ListRequest<UnbilledChargeListRequest> {

        private UnbilledChargeListRequest(String uri) {
            super(uri);
        }
    
        public UnbilledChargeListRequest includeDeleted(Boolean includeDeleted) {
            params.addOpt("include_deleted", includeDeleted);
            return this;
        }


        public StringFilter<UnbilledChargeListRequest> subscriptionId() {
            return new StringFilter<UnbilledChargeListRequest>("subscription_id",this).supportsMultiOperators(true).supportsPresenceOperator(true);        
        }


        public StringFilter<UnbilledChargeListRequest> customerId() {
            return new StringFilter<UnbilledChargeListRequest>("customer_id",this).supportsMultiOperators(true).supportsPresenceOperator(true);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class InvoiceNowEstimateRequest extends Request<InvoiceNowEstimateRequest> {

        private InvoiceNowEstimateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public InvoiceNowEstimateRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public InvoiceNowEstimateRequest customerId(String customerId) {
            params.addOpt("customer_id", customerId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
