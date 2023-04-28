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

public class InAppSubscription extends Resource<InAppSubscription> {

    public enum StoreStatus {
        IN_TRIAL,
        ACTIVE,
        CANCELLED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public InAppSubscription(String jsonStr) {
        super(jsonStr);
    }

    public InAppSubscription(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    @Deprecated
    public String appId() {
        return reqString("app_id");
    }

    public String subscriptionId() {
        return reqString("subscription_id");
    }

    public String customerId() {
        return optString("customer_id");
    }

    public String planId() {
        return optString("plan_id");
    }

    public StoreStatus storeStatus() {
        return optEnum("store_status", StoreStatus.class);
    }

    public String invoiceId() {
        return optString("invoice_id");
    }

    // Operations
    //===========

    public static ProcessReceiptRequest processReceipt(String id) {
        String uri = uri("in_app_subscriptions", nullCheck(id), "process_purchase_command");
        return new ProcessReceiptRequest(Method.POST, uri);
    }

    public static ImportReceiptRequest importReceipt(String id) {
        String uri = uri("in_app_subscriptions", nullCheck(id), "import_receipt");
        return new ImportReceiptRequest(Method.POST, uri);
    }

    public static ImportSubscriptionRequest importSubscription(String id) {
        String uri = uri("in_app_subscriptions", nullCheck(id), "import_subscription");
        return new ImportSubscriptionRequest(Method.POST, uri);
    }

    public static RetrieveStoreSubsRequest retrieveStoreSubs(String id) {
        String uri = uri("in_app_subscriptions", nullCheck(id), "retrieve");
        return new RetrieveStoreSubsRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class ProcessReceiptRequest extends Request<ProcessReceiptRequest> {

        private ProcessReceiptRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ProcessReceiptRequest receipt(String receipt) {
            params.add("receipt", receipt);
            return this;
        }


        public ProcessReceiptRequest productId(String productId) {
            params.add("product[id]", productId);
            return this;
        }

        public ProcessReceiptRequest productCurrencyCode(String productCurrencyCode) {
            params.add("product[currency_code]", productCurrencyCode);
            return this;
        }

        public ProcessReceiptRequest productPrice(Integer productPrice) {
            params.add("product[price]", productPrice);
            return this;
        }

        public ProcessReceiptRequest productName(String productName) {
            params.addOpt("product[name]", productName);
            return this;
        }

        public ProcessReceiptRequest productPriceInDecimal(String productPriceInDecimal) {
            params.addOpt("product[price_in_decimal]", productPriceInDecimal);
            return this;
        }

        public ProcessReceiptRequest productPeriod(String productPeriod) {
            params.addOpt("product[period]", productPeriod);
            return this;
        }

        public ProcessReceiptRequest productPeriodUnit(String productPeriodUnit) {
            params.addOpt("product[period_unit]", productPeriodUnit);
            return this;
        }

        public ProcessReceiptRequest customerId(String customerId) {
            params.addOpt("customer[id]", customerId);
            return this;
        }

        public ProcessReceiptRequest customerEmail(String customerEmail) {
            params.addOpt("customer[email]", customerEmail);
            return this;
        }

        public ProcessReceiptRequest customerFirstName(String customerFirstName) {
            params.addOpt("customer[first_name]", customerFirstName);
            return this;
        }

        public ProcessReceiptRequest customerLastName(String customerLastName) {
            params.addOpt("customer[last_name]", customerLastName);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class ImportReceiptRequest extends Request<ImportReceiptRequest> {

        private ImportReceiptRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ImportReceiptRequest receipt(String receipt) {
            params.add("receipt", receipt);
            return this;
        }


        public ImportReceiptRequest productCurrencyCode(String productCurrencyCode) {
            params.add("product[currency_code]", productCurrencyCode);
            return this;
        }

        public ImportReceiptRequest customerId(String customerId) {
            params.addOpt("customer[id]", customerId);
            return this;
        }

        public ImportReceiptRequest customerEmail(String customerEmail) {
            params.addOpt("customer[email]", customerEmail);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class ImportSubscriptionRequest extends Request<ImportSubscriptionRequest> {

        private ImportSubscriptionRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ImportSubscriptionRequest subscriptionId(String subscriptionId) {
            params.add("subscription[id]", subscriptionId);
            return this;
        }

        public ImportSubscriptionRequest subscriptionStartedAt(Timestamp subscriptionStartedAt) {
            params.add("subscription[started_at]", subscriptionStartedAt);
            return this;
        }

        public ImportSubscriptionRequest subscriptionTermStart(Timestamp subscriptionTermStart) {
            params.add("subscription[term_start]", subscriptionTermStart);
            return this;
        }

        public ImportSubscriptionRequest subscriptionTermEnd(Timestamp subscriptionTermEnd) {
            params.add("subscription[term_end]", subscriptionTermEnd);
            return this;
        }

        public ImportSubscriptionRequest subscriptionProductId(String subscriptionProductId) {
            params.add("subscription[product_id]", subscriptionProductId);
            return this;
        }

        public ImportSubscriptionRequest subscriptionCurrencyCode(String subscriptionCurrencyCode) {
            params.add("subscription[currency_code]", subscriptionCurrencyCode);
            return this;
        }

        public ImportSubscriptionRequest subscriptionTransactionId(String subscriptionTransactionId) {
            params.add("subscription[transaction_id]", subscriptionTransactionId);
            return this;
        }

        public ImportSubscriptionRequest subscriptionIsTrial(Boolean subscriptionIsTrial) {
            params.addOpt("subscription[is_trial]", subscriptionIsTrial);
            return this;
        }

        public ImportSubscriptionRequest customerId(String customerId) {
            params.addOpt("customer[id]", customerId);
            return this;
        }

        public ImportSubscriptionRequest customerEmail(String customerEmail) {
            params.addOpt("customer[email]", customerEmail);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class RetrieveStoreSubsRequest extends Request<RetrieveStoreSubsRequest> {

        private RetrieveStoreSubsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RetrieveStoreSubsRequest receipt(String receipt) {
            params.add("receipt", receipt);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
