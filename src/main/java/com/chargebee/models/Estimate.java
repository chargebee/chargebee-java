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

public class Estimate extends Resource<Estimate> {

    //Constructors
    //============

    public Estimate(String jsonStr) {
        super(jsonStr);
    }

    public Estimate(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public SubscriptionEstimate subscriptionEstimate() {
        return optSubResource("subscription_estimate", SubscriptionEstimate.class);
    }

    public InvoiceEstimate invoiceEstimate() {
        return optSubResource("invoice_estimate", InvoiceEstimate.class);
    }

    public List<InvoiceEstimate> invoiceEstimates() {
        return optList("invoice_estimates", InvoiceEstimate.class);
    }

    public InvoiceEstimate nextInvoiceEstimate() {
        return optSubResource("next_invoice_estimate", InvoiceEstimate.class);
    }

    public List<CreditNoteEstimate> creditNoteEstimates() {
        return optList("credit_note_estimates", CreditNoteEstimate.class);
    }

    public List<UnbilledCharge> unbilledChargeEstimates() {
        return optList("unbilled_charge_estimates", UnbilledCharge.class);
    }

    // Operations
    //===========

    public static CreateSubscriptionRequest createSubscription() throws IOException {
        String uri = uri("estimates", "create_subscription");
        return new CreateSubscriptionRequest(Method.POST, uri);
    }

    public static CreateSubForCustomerEstimateRequest createSubForCustomerEstimate(String id) throws IOException {
        String uri = uri("customers", nullCheck(id), "create_subscription_estimate");
        return new CreateSubForCustomerEstimateRequest(Method.GET, uri);
    }

    public static UpdateSubscriptionRequest updateSubscription() throws IOException {
        String uri = uri("estimates", "update_subscription");
        return new UpdateSubscriptionRequest(Method.POST, uri);
    }

    public static RenewalEstimateRequest renewalEstimate(String id) throws IOException {
        String uri = uri("subscriptions", nullCheck(id), "renewal_estimate");
        return new RenewalEstimateRequest(Method.GET, uri);
    }

    public static Request upcomingInvoicesEstimate(String id) throws IOException {
        String uri = uri("customers", nullCheck(id), "upcoming_invoices_estimate");
        return new Request(Method.GET, uri);
    }

    public static ChangeTermEndRequest changeTermEnd(String id) throws IOException {
        String uri = uri("subscriptions", nullCheck(id), "change_term_end_estimate");
        return new ChangeTermEndRequest(Method.POST, uri);
    }

    public static CancelSubscriptionRequest cancelSubscription(String id) throws IOException {
        String uri = uri("subscriptions", nullCheck(id), "cancel_subscription_estimate");
        return new CancelSubscriptionRequest(Method.POST, uri);
    }

    public static PauseSubscriptionRequest pauseSubscription(String id) throws IOException {
        String uri = uri("subscriptions", nullCheck(id), "pause_subscription_estimate");
        return new PauseSubscriptionRequest(Method.POST, uri);
    }

    public static ResumeSubscriptionRequest resumeSubscription(String id) throws IOException {
        String uri = uri("subscriptions", nullCheck(id), "resume_subscription_estimate");
        return new ResumeSubscriptionRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateSubscriptionRequest extends Request<CreateSubscriptionRequest> {

        private CreateSubscriptionRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateSubscriptionRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public CreateSubscriptionRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }






        public CreateSubscriptionRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }




        public CreateSubscriptionRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateSubscriptionRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateSubscriptionRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }


        public CreateSubscriptionRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription[id]", subscriptionId);
            return this;
        }

        public CreateSubscriptionRequest subscriptionPlanId(String subscriptionPlanId) {
            params.add("subscription[plan_id]", subscriptionPlanId);
            return this;
        }

        public CreateSubscriptionRequest subscriptionPlanQuantity(Integer subscriptionPlanQuantity) {
            params.addOpt("subscription[plan_quantity]", subscriptionPlanQuantity);
            return this;
        }

        public CreateSubscriptionRequest subscriptionPlanUnitPrice(Integer subscriptionPlanUnitPrice) {
            params.addOpt("subscription[plan_unit_price]", subscriptionPlanUnitPrice);
            return this;
        }

        public CreateSubscriptionRequest subscriptionSetupFee(Integer subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public CreateSubscriptionRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public CreateSubscriptionRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        @Deprecated
        public CreateSubscriptionRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public CreateSubscriptionRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public CreateSubscriptionRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public CreateSubscriptionRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public CreateSubscriptionRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public CreateSubscriptionRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public CreateSubscriptionRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public CreateSubscriptionRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public CreateSubscriptionRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public CreateSubscriptionRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CreateSubscriptionRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CreateSubscriptionRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CreateSubscriptionRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CreateSubscriptionRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CreateSubscriptionRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CreateSubscriptionRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CreateSubscriptionRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CreateSubscriptionRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public CreateSubscriptionRequest customerRegisteredForGst(Boolean customerRegisteredForGst) {
            params.addOpt("customer[registered_for_gst]", customerRegisteredForGst);
            return this;
        }

        public CreateSubscriptionRequest customerTaxability(com.chargebee.models.enums.Taxability customerTaxability) {
            params.addOpt("customer[taxability]", customerTaxability);
            return this;
        }

        public CreateSubscriptionRequest customerEntityCode(com.chargebee.models.enums.EntityCode customerEntityCode) {
            params.addOpt("customer[entity_code]", customerEntityCode);
            return this;
        }

        public CreateSubscriptionRequest customerExemptNumber(String customerExemptNumber) {
            params.addOpt("customer[exempt_number]", customerExemptNumber);
            return this;
        }

        public CreateSubscriptionRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }

        public CreateSubscriptionRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }

        public CreateSubscriptionRequest addonUnitPrice(int index, Integer addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }

        public CreateSubscriptionRequest addonTrialEnd(int index, Timestamp addonTrialEnd) {
            params.addOpt("addons[trial_end][" + index + "]", addonTrialEnd);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateSubForCustomerEstimateRequest extends Request<CreateSubForCustomerEstimateRequest> {

        private CreateSubForCustomerEstimateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateSubForCustomerEstimateRequest useExistingBalances(Boolean useExistingBalances) {
            params.addOpt("use_existing_balances", useExistingBalances);
            return this;
        }


        public CreateSubForCustomerEstimateRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }


        public CreateSubForCustomerEstimateRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public CreateSubForCustomerEstimateRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }






        public CreateSubForCustomerEstimateRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }




        public CreateSubForCustomerEstimateRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateSubForCustomerEstimateRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateSubForCustomerEstimateRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription[id]", subscriptionId);
            return this;
        }

        public CreateSubForCustomerEstimateRequest subscriptionPlanId(String subscriptionPlanId) {
            params.add("subscription[plan_id]", subscriptionPlanId);
            return this;
        }

        public CreateSubForCustomerEstimateRequest subscriptionPlanQuantity(Integer subscriptionPlanQuantity) {
            params.addOpt("subscription[plan_quantity]", subscriptionPlanQuantity);
            return this;
        }

        public CreateSubForCustomerEstimateRequest subscriptionPlanUnitPrice(Integer subscriptionPlanUnitPrice) {
            params.addOpt("subscription[plan_unit_price]", subscriptionPlanUnitPrice);
            return this;
        }

        public CreateSubForCustomerEstimateRequest subscriptionSetupFee(Integer subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public CreateSubForCustomerEstimateRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public CreateSubForCustomerEstimateRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        public CreateSubForCustomerEstimateRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CreateSubForCustomerEstimateRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CreateSubForCustomerEstimateRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CreateSubForCustomerEstimateRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CreateSubForCustomerEstimateRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CreateSubForCustomerEstimateRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CreateSubForCustomerEstimateRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CreateSubForCustomerEstimateRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CreateSubForCustomerEstimateRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }

        public CreateSubForCustomerEstimateRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }

        public CreateSubForCustomerEstimateRequest addonUnitPrice(int index, Integer addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }

        public CreateSubForCustomerEstimateRequest addonTrialEnd(int index, Timestamp addonTrialEnd) {
            params.addOpt("addons[trial_end][" + index + "]", addonTrialEnd);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateSubscriptionRequest extends Request<UpdateSubscriptionRequest> {

        private UpdateSubscriptionRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateSubscriptionRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public UpdateSubscriptionRequest replaceAddonList(Boolean replaceAddonList) {
            params.addOpt("replace_addon_list", replaceAddonList);
            return this;
        }


        public UpdateSubscriptionRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }


        public UpdateSubscriptionRequest reactivateFrom(Timestamp reactivateFrom) {
            params.addOpt("reactivate_from", reactivateFrom);
            return this;
        }




        public UpdateSubscriptionRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }


        public UpdateSubscriptionRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public UpdateSubscriptionRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public UpdateSubscriptionRequest replaceCouponList(Boolean replaceCouponList) {
            params.addOpt("replace_coupon_list", replaceCouponList);
            return this;
        }


        public UpdateSubscriptionRequest prorate(Boolean prorate) {
            params.addOpt("prorate", prorate);
            return this;
        }


        public UpdateSubscriptionRequest endOfTerm(Boolean endOfTerm) {
            params.addOpt("end_of_term", endOfTerm);
            return this;
        }


        public UpdateSubscriptionRequest forceTermReset(Boolean forceTermReset) {
            params.addOpt("force_term_reset", forceTermReset);
            return this;
        }


        public UpdateSubscriptionRequest reactivate(Boolean reactivate) {
            params.addOpt("reactivate", reactivate);
            return this;
        }


        public UpdateSubscriptionRequest includeDelayedCharges(Boolean includeDelayedCharges) {
            params.addOpt("include_delayed_charges", includeDelayedCharges);
            return this;
        }


        public UpdateSubscriptionRequest useExistingBalances(Boolean useExistingBalances) {
            params.addOpt("use_existing_balances", useExistingBalances);
            return this;
        }


        public UpdateSubscriptionRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }


        public UpdateSubscriptionRequest subscriptionId(String subscriptionId) {
            params.add("subscription[id]", subscriptionId);
            return this;
        }

        public UpdateSubscriptionRequest subscriptionPlanId(String subscriptionPlanId) {
            params.addOpt("subscription[plan_id]", subscriptionPlanId);
            return this;
        }

        public UpdateSubscriptionRequest subscriptionPlanQuantity(Integer subscriptionPlanQuantity) {
            params.addOpt("subscription[plan_quantity]", subscriptionPlanQuantity);
            return this;
        }

        public UpdateSubscriptionRequest subscriptionPlanUnitPrice(Integer subscriptionPlanUnitPrice) {
            params.addOpt("subscription[plan_unit_price]", subscriptionPlanUnitPrice);
            return this;
        }

        public UpdateSubscriptionRequest subscriptionSetupFee(Integer subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public UpdateSubscriptionRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public UpdateSubscriptionRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        @Deprecated
        public UpdateSubscriptionRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public UpdateSubscriptionRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public UpdateSubscriptionRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public UpdateSubscriptionRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public UpdateSubscriptionRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public UpdateSubscriptionRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public UpdateSubscriptionRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public UpdateSubscriptionRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public UpdateSubscriptionRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public UpdateSubscriptionRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public UpdateSubscriptionRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public UpdateSubscriptionRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public UpdateSubscriptionRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public UpdateSubscriptionRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public UpdateSubscriptionRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public UpdateSubscriptionRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public UpdateSubscriptionRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public UpdateSubscriptionRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public UpdateSubscriptionRequest customerRegisteredForGst(Boolean customerRegisteredForGst) {
            params.addOpt("customer[registered_for_gst]", customerRegisteredForGst);
            return this;
        }

        @Deprecated
        public UpdateSubscriptionRequest customerTaxability(com.chargebee.models.enums.Taxability customerTaxability) {
            params.addOpt("customer[taxability]", customerTaxability);
            return this;
        }

        public UpdateSubscriptionRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }

        public UpdateSubscriptionRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }

        public UpdateSubscriptionRequest addonUnitPrice(int index, Integer addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }

        public UpdateSubscriptionRequest addonTrialEnd(int index, Timestamp addonTrialEnd) {
            params.addOpt("addons[trial_end][" + index + "]", addonTrialEnd);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class RenewalEstimateRequest extends Request<RenewalEstimateRequest> {

        private RenewalEstimateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RenewalEstimateRequest includeDelayedCharges(Boolean includeDelayedCharges) {
            params.addOpt("include_delayed_charges", includeDelayedCharges);
            return this;
        }


        public RenewalEstimateRequest useExistingBalances(Boolean useExistingBalances) {
            params.addOpt("use_existing_balances", useExistingBalances);
            return this;
        }


        public RenewalEstimateRequest ignoreScheduledCancellation(Boolean ignoreScheduledCancellation) {
            params.addOpt("ignore_scheduled_cancellation", ignoreScheduledCancellation);
            return this;
        }


        public RenewalEstimateRequest ignoreScheduledChanges(Boolean ignoreScheduledChanges) {
            params.addOpt("ignore_scheduled_changes", ignoreScheduledChanges);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class ChangeTermEndRequest extends Request<ChangeTermEndRequest> {

        private ChangeTermEndRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ChangeTermEndRequest termEndsAt(Timestamp termEndsAt) {
            params.addOpt("term_ends_at", termEndsAt);
            return this;
        }


        public ChangeTermEndRequest prorate(Boolean prorate) {
            params.addOpt("prorate", prorate);
            return this;
        }


        public ChangeTermEndRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class CancelSubscriptionRequest extends Request<CancelSubscriptionRequest> {

        private CancelSubscriptionRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CancelSubscriptionRequest endOfTerm(Boolean endOfTerm) {
            params.addOpt("end_of_term", endOfTerm);
            return this;
        }


        public CancelSubscriptionRequest creditOptionForCurrentTermCharges(com.chargebee.models.enums.CreditOptionForCurrentTermCharges creditOptionForCurrentTermCharges) {
            params.addOpt("credit_option_for_current_term_charges", creditOptionForCurrentTermCharges);
            return this;
        }


        public CancelSubscriptionRequest unbilledChargesOption(com.chargebee.models.enums.UnbilledChargesOption unbilledChargesOption) {
            params.addOpt("unbilled_charges_option", unbilledChargesOption);
            return this;
        }


        public CancelSubscriptionRequest accountReceivablesHandling(com.chargebee.models.enums.AccountReceivablesHandling accountReceivablesHandling) {
            params.addOpt("account_receivables_handling", accountReceivablesHandling);
            return this;
        }


        public CancelSubscriptionRequest refundableCreditsHandling(com.chargebee.models.enums.RefundableCreditsHandling refundableCreditsHandling) {
            params.addOpt("refundable_credits_handling", refundableCreditsHandling);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class PauseSubscriptionRequest extends Request<PauseSubscriptionRequest> {

        private PauseSubscriptionRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public PauseSubscriptionRequest pauseOption(com.chargebee.models.enums.PauseOption pauseOption) {
            params.addOpt("pause_option", pauseOption);
            return this;
        }


        public PauseSubscriptionRequest unbilledChargesHandling(com.chargebee.models.enums.UnbilledChargesHandling unbilledChargesHandling) {
            params.addOpt("unbilled_charges_handling", unbilledChargesHandling);
            return this;
        }


        public PauseSubscriptionRequest subscriptionPauseDate(Timestamp subscriptionPauseDate) {
            params.addOpt("subscription[pause_date]", subscriptionPauseDate);
            return this;
        }

        public PauseSubscriptionRequest subscriptionResumeDate(Timestamp subscriptionResumeDate) {
            params.addOpt("subscription[resume_date]", subscriptionResumeDate);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class ResumeSubscriptionRequest extends Request<ResumeSubscriptionRequest> {

        private ResumeSubscriptionRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ResumeSubscriptionRequest resumeOption(com.chargebee.models.enums.ResumeOption resumeOption) {
            params.addOpt("resume_option", resumeOption);
            return this;
        }


        public ResumeSubscriptionRequest chargesHandling(com.chargebee.models.enums.ChargesHandling chargesHandling) {
            params.addOpt("charges_handling", chargesHandling);
            return this;
        }


        public ResumeSubscriptionRequest subscriptionResumeDate(Timestamp subscriptionResumeDate) {
            params.addOpt("subscription[resume_date]", subscriptionResumeDate);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

}
