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

    public String id() {
        return reqString("id");
    }

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

    public static PlansRequest plans() throws IOException {
        String uri = uri("exports", "plans");
        return new PlansRequest(Method.POST, uri);
    }

    public static AddonsRequest addons() throws IOException {
        String uri = uri("exports", "addons");
        return new AddonsRequest(Method.POST, uri);
    }

    public static CouponsRequest coupons() throws IOException {
        String uri = uri("exports", "coupons");
        return new CouponsRequest(Method.POST, uri);
    }

    public static CustomersRequest customers() throws IOException {
        String uri = uri("exports", "customers");
        return new CustomersRequest(Method.POST, uri);
    }

    public static SubscriptionsRequest subscriptions() throws IOException {
        String uri = uri("exports", "subscriptions");
        return new SubscriptionsRequest(Method.POST, uri);
    }

    public static InvoicesRequest invoices() throws IOException {
        String uri = uri("exports", "invoices");
        return new InvoicesRequest(Method.POST, uri);
    }

    public static CreditNotesRequest creditNotes() throws IOException {
        String uri = uri("exports", "credit_notes");
        return new CreditNotesRequest(Method.POST, uri);
    }

    public static TransactionsRequest transactions() throws IOException {
        String uri = uri("exports", "transactions");
        return new TransactionsRequest(Method.POST, uri);
    }

    public static OrdersRequest orders() throws IOException {
        String uri = uri("exports", "orders");
        return new OrdersRequest(Method.POST, uri);
    }

public Export waitForExportCompletion() 
            throws Exception {
        int count = 0;
        int sleepTime = Integer.getInteger("cb.java.export.sleep.millis", 10000);
        while(status() == Status.IN_PROCESS){
            if(count++ > 50){
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


        public RevenueRecognitionRequest reportFromMonth(Integer reportFromMonth) {
            params.add("report_from_month", reportFromMonth);
            return this;
        }


        public RevenueRecognitionRequest reportFromYear(Integer reportFromYear) {
            params.add("report_from_year", reportFromYear);
            return this;
        }


        public RevenueRecognitionRequest reportToMonth(Integer reportToMonth) {
            params.add("report_to_month", reportToMonth);
            return this;
        }


        public RevenueRecognitionRequest reportToYear(Integer reportToYear) {
            params.add("report_to_year", reportToYear);
            return this;
        }


        public RevenueRecognitionRequest includeDiscounts(Boolean includeDiscounts) {
            params.addOpt("include_discounts", includeDiscounts);
            return this;
        }


        public StringFilter<RevenueRecognitionRequest> paymentOwner() {
            return new StringFilter<RevenueRecognitionRequest>("payment_owner",this).supportsMultiOperators(true);        
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

        public StringFilter<RevenueRecognitionRequest> relationshipParentId() {
            return new StringFilter<RevenueRecognitionRequest>("relationship[parent_id]",this);        
        }

        public StringFilter<RevenueRecognitionRequest> relationshipPaymentOwnerId() {
            return new StringFilter<RevenueRecognitionRequest>("relationship[payment_owner_id]",this);        
        }

        public StringFilter<RevenueRecognitionRequest> relationshipInvoiceOwnerId() {
            return new StringFilter<RevenueRecognitionRequest>("relationship[invoice_owner_id]",this);        
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


        public DeferredRevenueRequest reportFromMonth(Integer reportFromMonth) {
            params.add("report_from_month", reportFromMonth);
            return this;
        }


        public DeferredRevenueRequest reportFromYear(Integer reportFromYear) {
            params.add("report_from_year", reportFromYear);
            return this;
        }


        public DeferredRevenueRequest reportToMonth(Integer reportToMonth) {
            params.add("report_to_month", reportToMonth);
            return this;
        }


        public DeferredRevenueRequest reportToYear(Integer reportToYear) {
            params.add("report_to_year", reportToYear);
            return this;
        }


        public DeferredRevenueRequest includeDiscounts(Boolean includeDiscounts) {
            params.addOpt("include_discounts", includeDiscounts);
            return this;
        }


        public StringFilter<DeferredRevenueRequest> paymentOwner() {
            return new StringFilter<DeferredRevenueRequest>("payment_owner",this).supportsMultiOperators(true);        
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

        public StringFilter<DeferredRevenueRequest> relationshipParentId() {
            return new StringFilter<DeferredRevenueRequest>("relationship[parent_id]",this);        
        }

        public StringFilter<DeferredRevenueRequest> relationshipPaymentOwnerId() {
            return new StringFilter<DeferredRevenueRequest>("relationship[payment_owner_id]",this);        
        }

        public StringFilter<DeferredRevenueRequest> relationshipInvoiceOwnerId() {
            return new StringFilter<DeferredRevenueRequest>("relationship[invoice_owner_id]",this);        
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class PlansRequest extends Request<PlansRequest> {

        private PlansRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public StringFilter<PlansRequest> planId() {
            return new StringFilter<PlansRequest>("plan[id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<PlansRequest> planName() {
            return new StringFilter<PlansRequest>("plan[name]",this).supportsMultiOperators(true);        
        }

        public NumberFilter<Integer, PlansRequest> planPrice() {
            return new NumberFilter<Integer, PlansRequest>("plan[price]",this);        
        }

        public NumberFilter<Integer, PlansRequest> planPeriod() {
            return new NumberFilter<Integer, PlansRequest>("plan[period]",this);        
        }

        public EnumFilter<Plan.PeriodUnit, PlansRequest> planPeriodUnit() {
            return new EnumFilter<Plan.PeriodUnit, PlansRequest>("plan[period_unit]",this);        
        }

        public NumberFilter<Integer, PlansRequest> planTrialPeriod() {
            return new NumberFilter<Integer, PlansRequest>("plan[trial_period]",this).supportsPresenceOperator(true);        
        }

        public EnumFilter<Plan.TrialPeriodUnit, PlansRequest> planTrialPeriodUnit() {
            return new EnumFilter<Plan.TrialPeriodUnit, PlansRequest>("plan[trial_period_unit]",this);        
        }

        public EnumFilter<Plan.AddonApplicability, PlansRequest> planAddonApplicability() {
            return new EnumFilter<Plan.AddonApplicability, PlansRequest>("plan[addon_applicability]",this);        
        }

        public BooleanFilter<PlansRequest> planGiftable() {
            return new BooleanFilter<PlansRequest>("plan[giftable]",this);        
        }

        public EnumFilter<Plan.Status, PlansRequest> planStatus() {
            return new EnumFilter<Plan.Status, PlansRequest>("plan[status]",this);        
        }

        public TimestampFilter<PlansRequest> planUpdatedAt() {
            return new TimestampFilter<PlansRequest>("plan[updated_at]",this);        
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class AddonsRequest extends Request<AddonsRequest> {

        private AddonsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public StringFilter<AddonsRequest> addonId() {
            return new StringFilter<AddonsRequest>("addon[id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<AddonsRequest> addonName() {
            return new StringFilter<AddonsRequest>("addon[name]",this).supportsMultiOperators(true);        
        }

        public EnumFilter<Addon.ChargeType, AddonsRequest> addonChargeType() {
            return new EnumFilter<Addon.ChargeType, AddonsRequest>("addon[charge_type]",this);        
        }

        public NumberFilter<Integer, AddonsRequest> addonPrice() {
            return new NumberFilter<Integer, AddonsRequest>("addon[price]",this);        
        }

        public NumberFilter<Integer, AddonsRequest> addonPeriod() {
            return new NumberFilter<Integer, AddonsRequest>("addon[period]",this);        
        }

        public EnumFilter<Addon.PeriodUnit, AddonsRequest> addonPeriodUnit() {
            return new EnumFilter<Addon.PeriodUnit, AddonsRequest>("addon[period_unit]",this);        
        }

        public EnumFilter<Addon.Status, AddonsRequest> addonStatus() {
            return new EnumFilter<Addon.Status, AddonsRequest>("addon[status]",this);        
        }

        public TimestampFilter<AddonsRequest> addonUpdatedAt() {
            return new TimestampFilter<AddonsRequest>("addon[updated_at]",this);        
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class CouponsRequest extends Request<CouponsRequest> {

        private CouponsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public StringFilter<CouponsRequest> couponId() {
            return new StringFilter<CouponsRequest>("coupon[id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<CouponsRequest> couponName() {
            return new StringFilter<CouponsRequest>("coupon[name]",this).supportsMultiOperators(true);        
        }

        public EnumFilter<Coupon.DiscountType, CouponsRequest> couponDiscountType() {
            return new EnumFilter<Coupon.DiscountType, CouponsRequest>("coupon[discount_type]",this);        
        }

        public EnumFilter<Coupon.DurationType, CouponsRequest> couponDurationType() {
            return new EnumFilter<Coupon.DurationType, CouponsRequest>("coupon[duration_type]",this);        
        }

        public EnumFilter<Coupon.Status, CouponsRequest> couponStatus() {
            return new EnumFilter<Coupon.Status, CouponsRequest>("coupon[status]",this);        
        }

        public EnumFilter<Coupon.ApplyOn, CouponsRequest> couponApplyOn() {
            return new EnumFilter<Coupon.ApplyOn, CouponsRequest>("coupon[apply_on]",this);        
        }

        public TimestampFilter<CouponsRequest> couponCreatedAt() {
            return new TimestampFilter<CouponsRequest>("coupon[created_at]",this);        
        }

        public TimestampFilter<CouponsRequest> couponUpdatedAt() {
            return new TimestampFilter<CouponsRequest>("coupon[updated_at]",this);        
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class CustomersRequest extends Request<CustomersRequest> {

        private CustomersRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public StringFilter<CustomersRequest> customerId() {
            return new StringFilter<CustomersRequest>("customer[id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<CustomersRequest> customerFirstName() {
            return new StringFilter<CustomersRequest>("customer[first_name]",this).supportsPresenceOperator(true);        
        }

        public StringFilter<CustomersRequest> customerLastName() {
            return new StringFilter<CustomersRequest>("customer[last_name]",this).supportsPresenceOperator(true);        
        }

        public StringFilter<CustomersRequest> customerEmail() {
            return new StringFilter<CustomersRequest>("customer[email]",this).supportsPresenceOperator(true);        
        }

        public StringFilter<CustomersRequest> customerCompany() {
            return new StringFilter<CustomersRequest>("customer[company]",this).supportsPresenceOperator(true);        
        }

        public StringFilter<CustomersRequest> customerPhone() {
            return new StringFilter<CustomersRequest>("customer[phone]",this).supportsPresenceOperator(true);        
        }

        public EnumFilter<com.chargebee.models.enums.AutoCollection, CustomersRequest> customerAutoCollection() {
            return new EnumFilter<com.chargebee.models.enums.AutoCollection, CustomersRequest>("customer[auto_collection]",this);        
        }

        public EnumFilter<com.chargebee.models.enums.Taxability, CustomersRequest> customerTaxability() {
            return new EnumFilter<com.chargebee.models.enums.Taxability, CustomersRequest>("customer[taxability]",this);        
        }

        public TimestampFilter<CustomersRequest> customerCreatedAt() {
            return new TimestampFilter<CustomersRequest>("customer[created_at]",this);        
        }

        public TimestampFilter<CustomersRequest> customerUpdatedAt() {
            return new TimestampFilter<CustomersRequest>("customer[updated_at]",this);        
        }

        public StringFilter<CustomersRequest> relationshipParentId() {
            return new StringFilter<CustomersRequest>("relationship[parent_id]",this);        
        }

        public StringFilter<CustomersRequest> relationshipPaymentOwnerId() {
            return new StringFilter<CustomersRequest>("relationship[payment_owner_id]",this);        
        }

        public StringFilter<CustomersRequest> relationshipInvoiceOwnerId() {
            return new StringFilter<CustomersRequest>("relationship[invoice_owner_id]",this);        
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class SubscriptionsRequest extends Request<SubscriptionsRequest> {

        private SubscriptionsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public StringFilter<SubscriptionsRequest> subscriptionId() {
            return new StringFilter<SubscriptionsRequest>("subscription[id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<SubscriptionsRequest> subscriptionCustomerId() {
            return new StringFilter<SubscriptionsRequest>("subscription[customer_id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<SubscriptionsRequest> subscriptionPlanId() {
            return new StringFilter<SubscriptionsRequest>("subscription[plan_id]",this).supportsMultiOperators(true);        
        }

        public EnumFilter<Subscription.Status, SubscriptionsRequest> subscriptionStatus() {
            return new EnumFilter<Subscription.Status, SubscriptionsRequest>("subscription[status]",this);        
        }

        public EnumFilter<Subscription.CancelReason, SubscriptionsRequest> subscriptionCancelReason() {
            return new EnumFilter<Subscription.CancelReason, SubscriptionsRequest>("subscription[cancel_reason]",this).supportsPresenceOperator(true);        
        }

        public NumberFilter<Integer, SubscriptionsRequest> subscriptionRemainingBillingCycles() {
            return new NumberFilter<Integer, SubscriptionsRequest>("subscription[remaining_billing_cycles]",this).supportsPresenceOperator(true);        
        }

        public TimestampFilter<SubscriptionsRequest> subscriptionCreatedAt() {
            return new TimestampFilter<SubscriptionsRequest>("subscription[created_at]",this);        
        }

        public TimestampFilter<SubscriptionsRequest> subscriptionActivatedAt() {
            return new TimestampFilter<SubscriptionsRequest>("subscription[activated_at]",this).supportsPresenceOperator(true);        
        }

        public TimestampFilter<SubscriptionsRequest> subscriptionNextBillingAt() {
            return new TimestampFilter<SubscriptionsRequest>("subscription[next_billing_at]",this);        
        }

        public TimestampFilter<SubscriptionsRequest> subscriptionCancelledAt() {
            return new TimestampFilter<SubscriptionsRequest>("subscription[cancelled_at]",this);        
        }

        public BooleanFilter<SubscriptionsRequest> subscriptionHasScheduledChanges() {
            return new BooleanFilter<SubscriptionsRequest>("subscription[has_scheduled_changes]",this);        
        }

        public TimestampFilter<SubscriptionsRequest> subscriptionUpdatedAt() {
            return new TimestampFilter<SubscriptionsRequest>("subscription[updated_at]",this);        
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class InvoicesRequest extends Request<InvoicesRequest> {

        private InvoicesRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public StringFilter<InvoicesRequest> paymentOwner() {
            return new StringFilter<InvoicesRequest>("payment_owner",this).supportsMultiOperators(true);        
        }


        public StringFilter<InvoicesRequest> invoiceId() {
            return new StringFilter<InvoicesRequest>("invoice[id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<InvoicesRequest> invoiceSubscriptionId() {
            return new StringFilter<InvoicesRequest>("invoice[subscription_id]",this).supportsMultiOperators(true).supportsPresenceOperator(true);        
        }

        public StringFilter<InvoicesRequest> invoiceCustomerId() {
            return new StringFilter<InvoicesRequest>("invoice[customer_id]",this).supportsMultiOperators(true);        
        }

        public BooleanFilter<InvoicesRequest> invoiceRecurring() {
            return new BooleanFilter<InvoicesRequest>("invoice[recurring]",this);        
        }

        public EnumFilter<Invoice.Status, InvoicesRequest> invoiceStatus() {
            return new EnumFilter<Invoice.Status, InvoicesRequest>("invoice[status]",this);        
        }

        public EnumFilter<com.chargebee.models.enums.PriceType, InvoicesRequest> invoicePriceType() {
            return new EnumFilter<com.chargebee.models.enums.PriceType, InvoicesRequest>("invoice[price_type]",this);        
        }

        public TimestampFilter<InvoicesRequest> invoiceDate() {
            return new TimestampFilter<InvoicesRequest>("invoice[date]",this);        
        }

        public TimestampFilter<InvoicesRequest> invoicePaidAt() {
            return new TimestampFilter<InvoicesRequest>("invoice[paid_at]",this);        
        }

        public NumberFilter<Integer, InvoicesRequest> invoiceTotal() {
            return new NumberFilter<Integer, InvoicesRequest>("invoice[total]",this);        
        }

        public NumberFilter<Integer, InvoicesRequest> invoiceAmountPaid() {
            return new NumberFilter<Integer, InvoicesRequest>("invoice[amount_paid]",this);        
        }

        public NumberFilter<Integer, InvoicesRequest> invoiceAmountAdjusted() {
            return new NumberFilter<Integer, InvoicesRequest>("invoice[amount_adjusted]",this);        
        }

        public NumberFilter<Integer, InvoicesRequest> invoiceCreditsApplied() {
            return new NumberFilter<Integer, InvoicesRequest>("invoice[credits_applied]",this);        
        }

        public NumberFilter<Integer, InvoicesRequest> invoiceAmountDue() {
            return new NumberFilter<Integer, InvoicesRequest>("invoice[amount_due]",this);        
        }

        public EnumFilter<Invoice.DunningStatus, InvoicesRequest> invoiceDunningStatus() {
            return new EnumFilter<Invoice.DunningStatus, InvoicesRequest>("invoice[dunning_status]",this).supportsPresenceOperator(true);        
        }

        public TimestampFilter<InvoicesRequest> invoiceUpdatedAt() {
            return new TimestampFilter<InvoicesRequest>("invoice[updated_at]",this);        
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreditNotesRequest extends Request<CreditNotesRequest> {

        private CreditNotesRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public StringFilter<CreditNotesRequest> creditNoteId() {
            return new StringFilter<CreditNotesRequest>("credit_note[id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<CreditNotesRequest> creditNoteCustomerId() {
            return new StringFilter<CreditNotesRequest>("credit_note[customer_id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<CreditNotesRequest> creditNoteSubscriptionId() {
            return new StringFilter<CreditNotesRequest>("credit_note[subscription_id]",this).supportsMultiOperators(true).supportsPresenceOperator(true);        
        }

        public StringFilter<CreditNotesRequest> creditNoteReferenceInvoiceId() {
            return new StringFilter<CreditNotesRequest>("credit_note[reference_invoice_id]",this).supportsMultiOperators(true);        
        }

        public EnumFilter<CreditNote.Type, CreditNotesRequest> creditNoteType() {
            return new EnumFilter<CreditNote.Type, CreditNotesRequest>("credit_note[type]",this);        
        }

        public EnumFilter<CreditNote.ReasonCode, CreditNotesRequest> creditNoteReasonCode() {
            return new EnumFilter<CreditNote.ReasonCode, CreditNotesRequest>("credit_note[reason_code]",this);        
        }

        public EnumFilter<CreditNote.Status, CreditNotesRequest> creditNoteStatus() {
            return new EnumFilter<CreditNote.Status, CreditNotesRequest>("credit_note[status]",this);        
        }

        public TimestampFilter<CreditNotesRequest> creditNoteDate() {
            return new TimestampFilter<CreditNotesRequest>("credit_note[date]",this);        
        }

        public NumberFilter<Integer, CreditNotesRequest> creditNoteTotal() {
            return new NumberFilter<Integer, CreditNotesRequest>("credit_note[total]",this);        
        }

        public EnumFilter<com.chargebee.models.enums.PriceType, CreditNotesRequest> creditNotePriceType() {
            return new EnumFilter<com.chargebee.models.enums.PriceType, CreditNotesRequest>("credit_note[price_type]",this);        
        }

        public NumberFilter<Integer, CreditNotesRequest> creditNoteAmountAllocated() {
            return new NumberFilter<Integer, CreditNotesRequest>("credit_note[amount_allocated]",this);        
        }

        public NumberFilter<Integer, CreditNotesRequest> creditNoteAmountRefunded() {
            return new NumberFilter<Integer, CreditNotesRequest>("credit_note[amount_refunded]",this);        
        }

        public NumberFilter<Integer, CreditNotesRequest> creditNoteAmountAvailable() {
            return new NumberFilter<Integer, CreditNotesRequest>("credit_note[amount_available]",this);        
        }

        public TimestampFilter<CreditNotesRequest> creditNoteVoidedAt() {
            return new TimestampFilter<CreditNotesRequest>("credit_note[voided_at]",this);        
        }

        public TimestampFilter<CreditNotesRequest> creditNoteUpdatedAt() {
            return new TimestampFilter<CreditNotesRequest>("credit_note[updated_at]",this);        
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class TransactionsRequest extends Request<TransactionsRequest> {

        private TransactionsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public StringFilter<TransactionsRequest> transactionId() {
            return new StringFilter<TransactionsRequest>("transaction[id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<TransactionsRequest> transactionCustomerId() {
            return new StringFilter<TransactionsRequest>("transaction[customer_id]",this).supportsMultiOperators(true).supportsPresenceOperator(true);        
        }

        public StringFilter<TransactionsRequest> transactionSubscriptionId() {
            return new StringFilter<TransactionsRequest>("transaction[subscription_id]",this).supportsMultiOperators(true).supportsPresenceOperator(true);        
        }

        public StringFilter<TransactionsRequest> transactionPaymentSourceId() {
            return new StringFilter<TransactionsRequest>("transaction[payment_source_id]",this).supportsMultiOperators(true).supportsPresenceOperator(true);        
        }

        public EnumFilter<com.chargebee.models.enums.PaymentMethod, TransactionsRequest> transactionPaymentMethod() {
            return new EnumFilter<com.chargebee.models.enums.PaymentMethod, TransactionsRequest>("transaction[payment_method]",this);        
        }

        public EnumFilter<com.chargebee.models.enums.Gateway, TransactionsRequest> transactionGateway() {
            return new EnumFilter<com.chargebee.models.enums.Gateway, TransactionsRequest>("transaction[gateway]",this);        
        }

        public StringFilter<TransactionsRequest> transactionGatewayAccountId() {
            return new StringFilter<TransactionsRequest>("transaction[gateway_account_id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<TransactionsRequest> transactionIdAtGateway() {
            return new StringFilter<TransactionsRequest>("transaction[id_at_gateway]",this);        
        }

        public StringFilter<TransactionsRequest> transactionReferenceNumber() {
            return new StringFilter<TransactionsRequest>("transaction[reference_number]",this).supportsPresenceOperator(true);        
        }

        public EnumFilter<Transaction.Type, TransactionsRequest> transactionType() {
            return new EnumFilter<Transaction.Type, TransactionsRequest>("transaction[type]",this);        
        }

        public TimestampFilter<TransactionsRequest> transactionDate() {
            return new TimestampFilter<TransactionsRequest>("transaction[date]",this);        
        }

        public NumberFilter<Integer, TransactionsRequest> transactionAmount() {
            return new NumberFilter<Integer, TransactionsRequest>("transaction[amount]",this);        
        }

        public NumberFilter<Integer, TransactionsRequest> transactionAmountCapturable() {
            return new NumberFilter<Integer, TransactionsRequest>("transaction[amount_capturable]",this);        
        }

        public EnumFilter<Transaction.Status, TransactionsRequest> transactionStatus() {
            return new EnumFilter<Transaction.Status, TransactionsRequest>("transaction[status]",this);        
        }

        public TimestampFilter<TransactionsRequest> transactionUpdatedAt() {
            return new TimestampFilter<TransactionsRequest>("transaction[updated_at]",this);        
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class OrdersRequest extends Request<OrdersRequest> {

        private OrdersRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public NumberFilter<Integer, OrdersRequest> total() {
            return new NumberFilter<Integer, OrdersRequest>("total",this);        
        }


        public StringFilter<OrdersRequest> orderId() {
            return new StringFilter<OrdersRequest>("order[id]",this).supportsMultiOperators(true);        
        }

        public StringFilter<OrdersRequest> orderSubscriptionId() {
            return new StringFilter<OrdersRequest>("order[subscription_id]",this).supportsMultiOperators(true).supportsPresenceOperator(true);        
        }

        public StringFilter<OrdersRequest> orderCustomerId() {
            return new StringFilter<OrdersRequest>("order[customer_id]",this).supportsMultiOperators(true);        
        }

        public EnumFilter<Order.Status, OrdersRequest> orderStatus() {
            return new EnumFilter<Order.Status, OrdersRequest>("order[status]",this);        
        }

        public EnumFilter<com.chargebee.models.enums.PriceType, OrdersRequest> orderPriceType() {
            return new EnumFilter<com.chargebee.models.enums.PriceType, OrdersRequest>("order[price_type]",this);        
        }

        public TimestampFilter<OrdersRequest> orderOrderDate() {
            return new TimestampFilter<OrdersRequest>("order[order_date]",this);        
        }

        public TimestampFilter<OrdersRequest> orderShippingDate() {
            return new TimestampFilter<OrdersRequest>("order[shipping_date]",this);        
        }

        public TimestampFilter<OrdersRequest> orderShippedAt() {
            return new TimestampFilter<OrdersRequest>("order[shipped_at]",this);        
        }

        public TimestampFilter<OrdersRequest> orderDeliveredAt() {
            return new TimestampFilter<OrdersRequest>("order[delivered_at]",this);        
        }

        public TimestampFilter<OrdersRequest> orderCancelledAt() {
            return new TimestampFilter<OrdersRequest>("order[cancelled_at]",this);        
        }

        public NumberFilter<Integer, OrdersRequest> orderAmountPaid() {
            return new NumberFilter<Integer, OrdersRequest>("order[amount_paid]",this);        
        }

        public NumberFilter<Integer, OrdersRequest> orderRefundableCredits() {
            return new NumberFilter<Integer, OrdersRequest>("order[refundable_credits]",this);        
        }

        public NumberFilter<Integer, OrdersRequest> orderRefundableCreditsIssued() {
            return new NumberFilter<Integer, OrdersRequest>("order[refundable_credits_issued]",this);        
        }

        public TimestampFilter<OrdersRequest> orderUpdatedAt() {
            return new TimestampFilter<OrdersRequest>("order[updated_at]",this);        
        }

        @Override
        public Params params() {
            return params;
        }
    }

}
