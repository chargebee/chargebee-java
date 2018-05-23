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

public class Export extends Resource<Export> {

    public enum MimeType {
        PDF,
        ZIP,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Status {
        IN_PROCESS,
        COMPLETED,
        FAILED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class Download extends Resource<Download> {
        public Download(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String downloadUrl() {
            return reqString("download_url");
        }

        public Timestamp validTill() {
            return reqTimestamp("valid_till");
        }

    }

    //Constructors
    //============

    public Export(String jsonStr) {
        super(jsonStr);
    }

    public Export(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String operationType() {
        return reqString("operation_type");
    }

    public MimeType mimeType() {
        return reqEnum("mime_type", MimeType.class);
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public String id() {
        return reqString("id");
    }

    public Export.Download download() {
        return optSubResource("download", Export.Download.class);
    }

    // Operations
    //===========

    public static Request retrieve(String id) throws IOException {
        String uri = uri("exports", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static RevenueRecognitionRequest revenueRecognition() throws IOException {
        String uri = uri("exports", "revenue_recognition");
        return new RevenueRecognitionRequest(Method.POST, uri);
    }

    public static DeferredRevenueRequest deferredRevenue() throws IOException {
        String uri = uri("exports", "deferred_revenue");
        return new DeferredRevenueRequest(Method.POST, uri);
    }

public Export waitForExportCompletion() 
            throws Exception {
        int count = 0;
        int sleepTime = Integer.getInteger("cb.java.export.sleep.millis", 10000);
        while(status() == Status.IN_PROCESS){
            if(count++ > 30){
                throw new RuntimeException("Export is taking too long");
            }
            Thread.sleep(sleepTime);
            Request req = retrieve(id());
            jsonObj = req.request().export().jsonObj;
        }
        return this;
    }
    // Operation Request Classes
    //==========================

    public static class RevenueRecognitionRequest extends Request<RevenueRecognitionRequest> {

        private RevenueRecognitionRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RevenueRecognitionRequest reportBy(com.chargebee.models.enums.ReportBy reportBy) {
            params.add("report_by", reportBy);
            return this;
        }


        public RevenueRecognitionRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public RevenueRecognitionRequest reportFromYear(Integer reportFromYear) {
            params.add("report_from_year", reportFromYear);
            return this;
        }


        public RevenueRecognitionRequest reportToYear(Integer reportToYear) {
            params.add("report_to_year", reportToYear);
            return this;
        }


        public RevenueRecognitionRequest reportToMonth(Integer reportToMonth) {
            params.add("report_to_month", reportToMonth);
            return this;
        }


        public RevenueRecognitionRequest reportFromMonth(Integer reportFromMonth) {
            params.add("report_from_month", reportFromMonth);
            return this;
        }


        public RevenueRecognitionRequest includeDiscounts(Boolean includeDiscounts) {
            params.addOpt("include_discounts", includeDiscounts);
            return this;
        }


        public StringFilter<RevenueRecognitionRequest> invoiceId() {
            return new StringFilter<RevenueRecognitionRequest>("invoice[id]",this).supportsMultiOperators(true);        
        }

        public BooleanFilter<RevenueRecognitionRequest> invoiceRecurring() {
            return new BooleanFilter<RevenueRecognitionRequest>("invoice[recurring]",this);        
        }

        public EnumFilter<Invoice.Status, RevenueRecognitionRequest> invoiceStatus() {
            return new EnumFilter<Invoice.Status, RevenueRecognitionRequest>("invoice[status]",this);        
        }

        public EnumFilter<com.chargebee.models.enums.PriceType, RevenueRecognitionRequest> invoicePriceType() {
            return new EnumFilter<com.chargebee.models.enums.PriceType, RevenueRecognitionRequest>("invoice[price_type]",this);        
        }

        public TimestampFilter<RevenueRecognitionRequest> invoiceDate() {
            return new TimestampFilter<RevenueRecognitionRequest>("invoice[date]",this);        
        }

        public TimestampFilter<RevenueRecognitionRequest> invoicePaidAt() {
            return new TimestampFilter<RevenueRecognitionRequest>("invoice[paid_at]",this);        
        }

        public NumberFilter<Integer, RevenueRecognitionRequest> invoiceTotal() {
            return new NumberFilter<Integer, RevenueRecognitionRequest>("invoice[total]",this);        
        }

        public NumberFilter<Integer, RevenueRecognitionRequest> invoiceAmountPaid() {
            return new NumberFilter<Integer, RevenueRecognitionRequest>("invoice[amount_paid]",this);        
        }

        public NumberFilter<Integer, RevenueRecognitionRequest> invoiceAmountAdjusted() {
            return new NumberFilter<Integer, RevenueRecognitionRequest>("invoice[amount_adjusted]",this);        
        }

        public NumberFilter<Integer, RevenueRecognitionRequest> invoiceCreditsApplied() {
            return new NumberFilter<Integer, RevenueRecognitionRequest>("invoice[credits_applied]",this);        
        }

        public NumberFilter<Integer, RevenueRecognitionRequest> invoiceAmountDue() {
            return new NumberFilter<Integer, RevenueRecognitionRequest>("invoice[amount_due]",this);        
        }

        public EnumFilter<Invoice.DunningStatus, RevenueRecognitionRequest> invoiceDunningStatus() {
            return new EnumFilter<Invoice.DunningStatus, RevenueRecognitionRequest>("invoice[dunning_status]",this).supportsPresenceOperator(true);        
        }

        public TimestampFilter<RevenueRecognitionRequest> invoiceUpdatedAt() {
            return new TimestampFilter<RevenueRecognitionRequest>("invoice[updated_at]",this);        
        }

        public StringFilter<RevenueRecognitionRequest> subscriptionId() {
            return new StringFilter<RevenueRecognitionRequest>("subscription[id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<RevenueRecognitionRequest> subscriptionCustomerId() {
            return new StringFilter<RevenueRecognitionRequest>("subscription[customer_id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<RevenueRecognitionRequest> subscriptionPlanId() {
            return new StringFilter<RevenueRecognitionRequest>("subscription[plan_id]",this).supportsMultiOperators(true);        
        }

        public EnumFilter<Subscription.Status, RevenueRecognitionRequest> subscriptionStatus() {
            return new EnumFilter<Subscription.Status, RevenueRecognitionRequest>("subscription[status]",this);        
        }

        public EnumFilter<Subscription.CancelReason, RevenueRecognitionRequest> subscriptionCancelReason() {
            return new EnumFilter<Subscription.CancelReason, RevenueRecognitionRequest>("subscription[cancel_reason]",this).supportsPresenceOperator(true);        
        }

        public NumberFilter<Integer, RevenueRecognitionRequest> subscriptionRemainingBillingCycles() {
            return new NumberFilter<Integer, RevenueRecognitionRequest>("subscription[remaining_billing_cycles]",this).supportsPresenceOperator(true);        
        }

        public TimestampFilter<RevenueRecognitionRequest> subscriptionCreatedAt() {
            return new TimestampFilter<RevenueRecognitionRequest>("subscription[created_at]",this);        
        }

        public TimestampFilter<RevenueRecognitionRequest> subscriptionActivatedAt() {
            return new TimestampFilter<RevenueRecognitionRequest>("subscription[activated_at]",this).supportsPresenceOperator(true);        
        }

        public TimestampFilter<RevenueRecognitionRequest> subscriptionNextBillingAt() {
            return new TimestampFilter<RevenueRecognitionRequest>("subscription[next_billing_at]",this);        
        }

        public TimestampFilter<RevenueRecognitionRequest> subscriptionCancelledAt() {
            return new TimestampFilter<RevenueRecognitionRequest>("subscription[cancelled_at]",this);        
        }

        public BooleanFilter<RevenueRecognitionRequest> subscriptionHasScheduledChanges() {
            return new BooleanFilter<RevenueRecognitionRequest>("subscription[has_scheduled_changes]",this);        
        }

        public TimestampFilter<RevenueRecognitionRequest> subscriptionUpdatedAt() {
            return new TimestampFilter<RevenueRecognitionRequest>("subscription[updated_at]",this);        
        }

        public StringFilter<RevenueRecognitionRequest> customerId() {
            return new StringFilter<RevenueRecognitionRequest>("customer[id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<RevenueRecognitionRequest> customerFirstName() {
            return new StringFilter<RevenueRecognitionRequest>("customer[first_name]",this).supportsPresenceOperator(true);        
        }

        public StringFilter<RevenueRecognitionRequest> customerLastName() {
            return new StringFilter<RevenueRecognitionRequest>("customer[last_name]",this).supportsPresenceOperator(true);        
        }

        public StringFilter<RevenueRecognitionRequest> customerEmail() {
            return new StringFilter<RevenueRecognitionRequest>("customer[email]",this).supportsPresenceOperator(true);        
        }

        public StringFilter<RevenueRecognitionRequest> customerCompany() {
            return new StringFilter<RevenueRecognitionRequest>("customer[company]",this).supportsPresenceOperator(true);        
        }

        public StringFilter<RevenueRecognitionRequest> customerPhone() {
            return new StringFilter<RevenueRecognitionRequest>("customer[phone]",this).supportsPresenceOperator(true);        
        }

        public EnumFilter<com.chargebee.models.enums.AutoCollection, RevenueRecognitionRequest> customerAutoCollection() {
            return new EnumFilter<com.chargebee.models.enums.AutoCollection, RevenueRecognitionRequest>("customer[auto_collection]",this);        
        }

        public EnumFilter<com.chargebee.models.enums.Taxability, RevenueRecognitionRequest> customerTaxability() {
            return new EnumFilter<com.chargebee.models.enums.Taxability, RevenueRecognitionRequest>("customer[taxability]",this);        
        }

        public TimestampFilter<RevenueRecognitionRequest> customerCreatedAt() {
            return new TimestampFilter<RevenueRecognitionRequest>("customer[created_at]",this);        
        }

        public TimestampFilter<RevenueRecognitionRequest> customerUpdatedAt() {
            return new TimestampFilter<RevenueRecognitionRequest>("customer[updated_at]",this);        
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class DeferredRevenueRequest extends Request<DeferredRevenueRequest> {

        private DeferredRevenueRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public DeferredRevenueRequest reportBy(com.chargebee.models.enums.ReportBy reportBy) {
            params.add("report_by", reportBy);
            return this;
        }


        public DeferredRevenueRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public DeferredRevenueRequest reportFromYear(Integer reportFromYear) {
            params.add("report_from_year", reportFromYear);
            return this;
        }


        public DeferredRevenueRequest reportToYear(Integer reportToYear) {
            params.add("report_to_year", reportToYear);
            return this;
        }


        public DeferredRevenueRequest reportToMonth(Integer reportToMonth) {
            params.add("report_to_month", reportToMonth);
            return this;
        }


        public DeferredRevenueRequest reportFromMonth(Integer reportFromMonth) {
            params.add("report_from_month", reportFromMonth);
            return this;
        }


        public DeferredRevenueRequest includeDiscounts(Boolean includeDiscounts) {
            params.addOpt("include_discounts", includeDiscounts);
            return this;
        }


        public StringFilter<DeferredRevenueRequest> invoiceId() {
            return new StringFilter<DeferredRevenueRequest>("invoice[id]",this).supportsMultiOperators(true);        
        }

        public BooleanFilter<DeferredRevenueRequest> invoiceRecurring() {
            return new BooleanFilter<DeferredRevenueRequest>("invoice[recurring]",this);        
        }

        public EnumFilter<Invoice.Status, DeferredRevenueRequest> invoiceStatus() {
            return new EnumFilter<Invoice.Status, DeferredRevenueRequest>("invoice[status]",this);        
        }

        public EnumFilter<com.chargebee.models.enums.PriceType, DeferredRevenueRequest> invoicePriceType() {
            return new EnumFilter<com.chargebee.models.enums.PriceType, DeferredRevenueRequest>("invoice[price_type]",this);        
        }

        public TimestampFilter<DeferredRevenueRequest> invoiceDate() {
            return new TimestampFilter<DeferredRevenueRequest>("invoice[date]",this);        
        }

        public TimestampFilter<DeferredRevenueRequest> invoicePaidAt() {
            return new TimestampFilter<DeferredRevenueRequest>("invoice[paid_at]",this);        
        }

        public NumberFilter<Integer, DeferredRevenueRequest> invoiceTotal() {
            return new NumberFilter<Integer, DeferredRevenueRequest>("invoice[total]",this);        
        }

        public NumberFilter<Integer, DeferredRevenueRequest> invoiceAmountPaid() {
            return new NumberFilter<Integer, DeferredRevenueRequest>("invoice[amount_paid]",this);        
        }

        public NumberFilter<Integer, DeferredRevenueRequest> invoiceAmountAdjusted() {
            return new NumberFilter<Integer, DeferredRevenueRequest>("invoice[amount_adjusted]",this);        
        }

        public NumberFilter<Integer, DeferredRevenueRequest> invoiceCreditsApplied() {
            return new NumberFilter<Integer, DeferredRevenueRequest>("invoice[credits_applied]",this);        
        }

        public NumberFilter<Integer, DeferredRevenueRequest> invoiceAmountDue() {
            return new NumberFilter<Integer, DeferredRevenueRequest>("invoice[amount_due]",this);        
        }

        public EnumFilter<Invoice.DunningStatus, DeferredRevenueRequest> invoiceDunningStatus() {
            return new EnumFilter<Invoice.DunningStatus, DeferredRevenueRequest>("invoice[dunning_status]",this).supportsPresenceOperator(true);        
        }

        public TimestampFilter<DeferredRevenueRequest> invoiceUpdatedAt() {
            return new TimestampFilter<DeferredRevenueRequest>("invoice[updated_at]",this);        
        }

        public StringFilter<DeferredRevenueRequest> subscriptionId() {
            return new StringFilter<DeferredRevenueRequest>("subscription[id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<DeferredRevenueRequest> subscriptionCustomerId() {
            return new StringFilter<DeferredRevenueRequest>("subscription[customer_id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<DeferredRevenueRequest> subscriptionPlanId() {
            return new StringFilter<DeferredRevenueRequest>("subscription[plan_id]",this).supportsMultiOperators(true);        
        }

        public EnumFilter<Subscription.Status, DeferredRevenueRequest> subscriptionStatus() {
            return new EnumFilter<Subscription.Status, DeferredRevenueRequest>("subscription[status]",this);        
        }

        public EnumFilter<Subscription.CancelReason, DeferredRevenueRequest> subscriptionCancelReason() {
            return new EnumFilter<Subscription.CancelReason, DeferredRevenueRequest>("subscription[cancel_reason]",this).supportsPresenceOperator(true);        
        }

        public NumberFilter<Integer, DeferredRevenueRequest> subscriptionRemainingBillingCycles() {
            return new NumberFilter<Integer, DeferredRevenueRequest>("subscription[remaining_billing_cycles]",this).supportsPresenceOperator(true);        
        }

        public TimestampFilter<DeferredRevenueRequest> subscriptionCreatedAt() {
            return new TimestampFilter<DeferredRevenueRequest>("subscription[created_at]",this);        
        }

        public TimestampFilter<DeferredRevenueRequest> subscriptionActivatedAt() {
            return new TimestampFilter<DeferredRevenueRequest>("subscription[activated_at]",this).supportsPresenceOperator(true);        
        }

        public TimestampFilter<DeferredRevenueRequest> subscriptionNextBillingAt() {
            return new TimestampFilter<DeferredRevenueRequest>("subscription[next_billing_at]",this);        
        }

        public TimestampFilter<DeferredRevenueRequest> subscriptionCancelledAt() {
            return new TimestampFilter<DeferredRevenueRequest>("subscription[cancelled_at]",this);        
        }

        public BooleanFilter<DeferredRevenueRequest> subscriptionHasScheduledChanges() {
            return new BooleanFilter<DeferredRevenueRequest>("subscription[has_scheduled_changes]",this);        
        }

        public TimestampFilter<DeferredRevenueRequest> subscriptionUpdatedAt() {
            return new TimestampFilter<DeferredRevenueRequest>("subscription[updated_at]",this);        
        }

        public StringFilter<DeferredRevenueRequest> customerId() {
            return new StringFilter<DeferredRevenueRequest>("customer[id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<DeferredRevenueRequest> customerFirstName() {
            return new StringFilter<DeferredRevenueRequest>("customer[first_name]",this).supportsPresenceOperator(true);        
        }

        public StringFilter<DeferredRevenueRequest> customerLastName() {
            return new StringFilter<DeferredRevenueRequest>("customer[last_name]",this).supportsPresenceOperator(true);        
        }

        public StringFilter<DeferredRevenueRequest> customerEmail() {
            return new StringFilter<DeferredRevenueRequest>("customer[email]",this).supportsPresenceOperator(true);        
        }

        public StringFilter<DeferredRevenueRequest> customerCompany() {
            return new StringFilter<DeferredRevenueRequest>("customer[company]",this).supportsPresenceOperator(true);        
        }

        public StringFilter<DeferredRevenueRequest> customerPhone() {
            return new StringFilter<DeferredRevenueRequest>("customer[phone]",this).supportsPresenceOperator(true);        
        }

        public EnumFilter<com.chargebee.models.enums.AutoCollection, DeferredRevenueRequest> customerAutoCollection() {
            return new EnumFilter<com.chargebee.models.enums.AutoCollection, DeferredRevenueRequest>("customer[auto_collection]",this);        
        }

        public EnumFilter<com.chargebee.models.enums.Taxability, DeferredRevenueRequest> customerTaxability() {
            return new EnumFilter<com.chargebee.models.enums.Taxability, DeferredRevenueRequest>("customer[taxability]",this);        
        }

        public TimestampFilter<DeferredRevenueRequest> customerCreatedAt() {
            return new TimestampFilter<DeferredRevenueRequest>("customer[created_at]",this);        
        }

        public TimestampFilter<DeferredRevenueRequest> customerUpdatedAt() {
            return new TimestampFilter<DeferredRevenueRequest>("customer[updated_at]",this);        
        }

        @Override
        public Params params() {
            return params;
        }
    }

}
