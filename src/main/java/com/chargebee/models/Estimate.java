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

    public List<SubscriptionEstimate> subscriptionEstimates() {
        return optList("subscription_estimates", SubscriptionEstimate.class);
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

    public static CreateSubscriptionRequest createSubscription() {
        String uri = uri("estimates", "create_subscription");
        return new CreateSubscriptionRequest(Method.POST, uri);
    }

    public static CreateSubItemEstimateRequest createSubItemEstimate() {
        String uri = uri("estimates", "create_subscription_for_items");
        return new CreateSubItemEstimateRequest(Method.POST, uri);
    }

    public static CreateSubForCustomerEstimateRequest createSubForCustomerEstimate(String id) {
        String uri = uri("customers", nullCheck(id), "create_subscription_estimate");
        return new CreateSubForCustomerEstimateRequest(Method.GET, uri);
    }

    public static CreateSubItemForCustomerEstimateRequest createSubItemForCustomerEstimate(String id) {
        String uri = uri("customers", nullCheck(id), "create_subscription_for_items_estimate");
        return new CreateSubItemForCustomerEstimateRequest(Method.POST, uri);
    }

    public static UpdateSubscriptionRequest updateSubscription() {
        String uri = uri("estimates", "update_subscription");
        return new UpdateSubscriptionRequest(Method.POST, uri);
    }

    public static UpdateSubscriptionForItemsRequest updateSubscriptionForItems() {
        String uri = uri("estimates", "update_subscription_for_items");
        return new UpdateSubscriptionForItemsRequest(Method.POST, uri);
    }

    public static RenewalEstimateRequest renewalEstimate(String id) {
        String uri = uri("subscriptions", nullCheck(id), "renewal_estimate");
        return new RenewalEstimateRequest(Method.GET, uri);
    }

    public static AdvanceInvoiceEstimateRequest advanceInvoiceEstimate(String id) {
        String uri = uri("subscriptions", nullCheck(id), "advance_invoice_estimate");
        return new AdvanceInvoiceEstimateRequest(Method.POST, uri);
    }

    public static RegenerateInvoiceEstimateRequest regenerateInvoiceEstimate(String id) {
        String uri = uri("subscriptions", nullCheck(id), "regenerate_invoice_estimate");
        return new RegenerateInvoiceEstimateRequest(Method.POST, uri);
    }

    public static Request upcomingInvoicesEstimate(String id) {
        String uri = uri("customers", nullCheck(id), "upcoming_invoices_estimate");
        return new Request(Method.GET, uri);
    }

    public static ChangeTermEndRequest changeTermEnd(String id) {
        String uri = uri("subscriptions", nullCheck(id), "change_term_end_estimate");
        return new ChangeTermEndRequest(Method.POST, uri);
    }

    public static CancelSubscriptionRequest cancelSubscription(String id) {
        String uri = uri("subscriptions", nullCheck(id), "cancel_subscription_estimate");
        return new CancelSubscriptionRequest(Method.POST, uri);
    }

    public static CancelSubscriptionForItemsRequest cancelSubscriptionForItems(String id) {
        String uri = uri("subscriptions", nullCheck(id), "cancel_subscription_for_items_estimate");
        return new CancelSubscriptionForItemsRequest(Method.POST, uri);
    }

    public static PauseSubscriptionRequest pauseSubscription(String id) {
        String uri = uri("subscriptions", nullCheck(id), "pause_subscription_estimate");
        return new PauseSubscriptionRequest(Method.POST, uri);
    }

    public static ResumeSubscriptionRequest resumeSubscription(String id) {
        String uri = uri("subscriptions", nullCheck(id), "resume_subscription_estimate");
        return new ResumeSubscriptionRequest(Method.POST, uri);
    }

    public static GiftSubscriptionRequest giftSubscription() {
        String uri = uri("estimates", "gift_subscription");
        return new GiftSubscriptionRequest(Method.POST, uri);
    }

    public static GiftSubscriptionForItemsRequest giftSubscriptionForItems() {
        String uri = uri("estimates", "gift_subscription_for_items");
        return new GiftSubscriptionForItemsRequest(Method.POST, uri);
    }

    public static CreateInvoiceRequest createInvoice() {
        String uri = uri("estimates", "create_invoice");
        return new CreateInvoiceRequest(Method.POST, uri);
    }

    public static CreateInvoiceForItemsRequest createInvoiceForItems() {
        String uri = uri("estimates", "create_invoice_for_items");
        return new CreateInvoiceForItemsRequest(Method.POST, uri);
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


        public CreateSubscriptionRequest mandatoryAddonsToRemove(List<String> mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public CreateSubscriptionRequest mandatoryAddonsToRemove(String... mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
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




        public CreateSubscriptionRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public CreateSubscriptionRequest clientProfileId(String clientProfileId) {
            params.addOpt("client_profile_id", clientProfileId);
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

        public CreateSubscriptionRequest subscriptionPlanQuantityInDecimal(String subscriptionPlanQuantityInDecimal) {
            params.addOpt("subscription[plan_quantity_in_decimal]", subscriptionPlanQuantityInDecimal);
            return this;
        }

        public CreateSubscriptionRequest subscriptionPlanUnitPrice(Long subscriptionPlanUnitPrice) {
            params.addOpt("subscription[plan_unit_price]", subscriptionPlanUnitPrice);
            return this;
        }

        public CreateSubscriptionRequest subscriptionPlanUnitPriceInDecimal(String subscriptionPlanUnitPriceInDecimal) {
            params.addOpt("subscription[plan_unit_price_in_decimal]", subscriptionPlanUnitPriceInDecimal);
            return this;
        }

        public CreateSubscriptionRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public CreateSubscriptionRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        public CreateSubscriptionRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        @Deprecated
        public CreateSubscriptionRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public CreateSubscriptionRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
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

        public CreateSubscriptionRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
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

        public CreateSubscriptionRequest subscriptionFreePeriod(Integer subscriptionFreePeriod) {
            params.addOpt("subscription[free_period]", subscriptionFreePeriod);
            return this;
        }

        public CreateSubscriptionRequest subscriptionFreePeriodUnit(com.chargebee.models.enums.FreePeriodUnit subscriptionFreePeriodUnit) {
            params.addOpt("subscription[free_period_unit]", subscriptionFreePeriodUnit);
            return this;
        }

        public CreateSubscriptionRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public CreateSubscriptionRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public CreateSubscriptionRequest subscriptionContractTermBillingCycleOnRenewal(Integer subscriptionContractTermBillingCycleOnRenewal) {
            params.addOpt("subscription[contract_term_billing_cycle_on_renewal]", subscriptionContractTermBillingCycleOnRenewal);
            return this;
        }

        public CreateSubscriptionRequest subscriptionTrialEndAction(com.chargebee.models.enums.TrialEndAction subscriptionTrialEndAction) {
            params.addOpt("subscription[trial_end_action]", subscriptionTrialEndAction);
            return this;
        }

        public CreateSubscriptionRequest customerExemptionDetails(JSONArray customerExemptionDetails) {
            params.addOpt("customer[exemption_details]", customerExemptionDetails);
            return this;
        }

        public CreateSubscriptionRequest customerCustomerType(com.chargebee.models.enums.CustomerType customerCustomerType) {
            params.addOpt("customer[customer_type]", customerCustomerType);
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
        public CreateSubscriptionRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public CreateSubscriptionRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public CreateSubscriptionRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public CreateSubscriptionRequest addonBillingCycles(int index, Integer addonBillingCycles) {
            params.addOpt("addons[billing_cycles][" + index + "]", addonBillingCycles);
            return this;
        }
        public CreateSubscriptionRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public CreateSubscriptionRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public CreateSubscriptionRequest eventBasedAddonUnitPrice(int index, Long eventBasedAddonUnitPrice) {
            params.addOpt("event_based_addons[unit_price][" + index + "]", eventBasedAddonUnitPrice);
            return this;
        }
        public CreateSubscriptionRequest eventBasedAddonQuantityInDecimal(int index, String eventBasedAddonQuantityInDecimal) {
            params.addOpt("event_based_addons[quantity_in_decimal][" + index + "]", eventBasedAddonQuantityInDecimal);
            return this;
        }
        public CreateSubscriptionRequest eventBasedAddonUnitPriceInDecimal(int index, String eventBasedAddonUnitPriceInDecimal) {
            params.addOpt("event_based_addons[unit_price_in_decimal][" + index + "]", eventBasedAddonUnitPriceInDecimal);
            return this;
        }
        public CreateSubscriptionRequest eventBasedAddonServicePeriodInDays(int index, Integer eventBasedAddonServicePeriodInDays) {
            params.addOpt("event_based_addons[service_period_in_days][" + index + "]", eventBasedAddonServicePeriodInDays);
            return this;
        }
        public CreateSubscriptionRequest eventBasedAddonOnEvent(int index, com.chargebee.models.enums.OnEvent eventBasedAddonOnEvent) {
            params.addOpt("event_based_addons[on_event][" + index + "]", eventBasedAddonOnEvent);
            return this;
        }
        public CreateSubscriptionRequest eventBasedAddonChargeOnce(int index, Boolean eventBasedAddonChargeOnce) {
            params.addOpt("event_based_addons[charge_once][" + index + "]", eventBasedAddonChargeOnce);
            return this;
        }
        public CreateSubscriptionRequest eventBasedAddonChargeOn(int index, com.chargebee.models.enums.ChargeOn eventBasedAddonChargeOn) {
            params.addOpt("event_based_addons[charge_on][" + index + "]", eventBasedAddonChargeOn);
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

    public static class CreateSubItemEstimateRequest extends Request<CreateSubItemEstimateRequest> {

        private CreateSubItemEstimateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateSubItemEstimateRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public CreateSubItemEstimateRequest mandatoryItemsToRemove(List<String> mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public CreateSubItemEstimateRequest mandatoryItemsToRemove(String... mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public CreateSubItemEstimateRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }






        public CreateSubItemEstimateRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }




        public CreateSubItemEstimateRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateSubItemEstimateRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateSubItemEstimateRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }




        public CreateSubItemEstimateRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public CreateSubItemEstimateRequest clientProfileId(String clientProfileId) {
            params.addOpt("client_profile_id", clientProfileId);
            return this;
        }


        public CreateSubItemEstimateRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription[id]", subscriptionId);
            return this;
        }

        public CreateSubItemEstimateRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        @Deprecated
        public CreateSubItemEstimateRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public CreateSubItemEstimateRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        @Deprecated
        public CreateSubItemEstimateRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public CreateSubItemEstimateRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
            return this;
        }

        public CreateSubItemEstimateRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public CreateSubItemEstimateRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public CreateSubItemEstimateRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public CreateSubItemEstimateRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public CreateSubItemEstimateRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public CreateSubItemEstimateRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public CreateSubItemEstimateRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public CreateSubItemEstimateRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public CreateSubItemEstimateRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CreateSubItemEstimateRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CreateSubItemEstimateRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CreateSubItemEstimateRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CreateSubItemEstimateRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CreateSubItemEstimateRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CreateSubItemEstimateRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CreateSubItemEstimateRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CreateSubItemEstimateRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public CreateSubItemEstimateRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
            return this;
        }

        public CreateSubItemEstimateRequest customerRegisteredForGst(Boolean customerRegisteredForGst) {
            params.addOpt("customer[registered_for_gst]", customerRegisteredForGst);
            return this;
        }

        public CreateSubItemEstimateRequest customerTaxability(com.chargebee.models.enums.Taxability customerTaxability) {
            params.addOpt("customer[taxability]", customerTaxability);
            return this;
        }

        public CreateSubItemEstimateRequest customerEntityCode(com.chargebee.models.enums.EntityCode customerEntityCode) {
            params.addOpt("customer[entity_code]", customerEntityCode);
            return this;
        }

        public CreateSubItemEstimateRequest customerExemptNumber(String customerExemptNumber) {
            params.addOpt("customer[exempt_number]", customerExemptNumber);
            return this;
        }

        public CreateSubItemEstimateRequest subscriptionFreePeriod(Integer subscriptionFreePeriod) {
            params.addOpt("subscription[free_period]", subscriptionFreePeriod);
            return this;
        }

        public CreateSubItemEstimateRequest subscriptionFreePeriodUnit(com.chargebee.models.enums.FreePeriodUnit subscriptionFreePeriodUnit) {
            params.addOpt("subscription[free_period_unit]", subscriptionFreePeriodUnit);
            return this;
        }

        public CreateSubItemEstimateRequest customerExemptionDetails(JSONArray customerExemptionDetails) {
            params.addOpt("customer[exemption_details]", customerExemptionDetails);
            return this;
        }

        public CreateSubItemEstimateRequest customerCustomerType(com.chargebee.models.enums.CustomerType customerCustomerType) {
            params.addOpt("customer[customer_type]", customerCustomerType);
            return this;
        }

        public CreateSubItemEstimateRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public CreateSubItemEstimateRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public CreateSubItemEstimateRequest subscriptionContractTermBillingCycleOnRenewal(Integer subscriptionContractTermBillingCycleOnRenewal) {
            params.addOpt("subscription[contract_term_billing_cycle_on_renewal]", subscriptionContractTermBillingCycleOnRenewal);
            return this;
        }

        public CreateSubItemEstimateRequest subscriptionTrialEndAction(com.chargebee.models.enums.TrialEndAction subscriptionTrialEndAction) {
            params.addOpt("subscription[trial_end_action]", subscriptionTrialEndAction);
            return this;
        }

        public CreateSubItemEstimateRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.add("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public CreateSubItemEstimateRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public CreateSubItemEstimateRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        public CreateSubItemEstimateRequest subscriptionItemUnitPrice(int index, Long subscriptionItemUnitPrice) {
            params.addOpt("subscription_items[unit_price][" + index + "]", subscriptionItemUnitPrice);
            return this;
        }
        public CreateSubItemEstimateRequest subscriptionItemUnitPriceInDecimal(int index, String subscriptionItemUnitPriceInDecimal) {
            params.addOpt("subscription_items[unit_price_in_decimal][" + index + "]", subscriptionItemUnitPriceInDecimal);
            return this;
        }
        public CreateSubItemEstimateRequest subscriptionItemBillingCycles(int index, Integer subscriptionItemBillingCycles) {
            params.addOpt("subscription_items[billing_cycles][" + index + "]", subscriptionItemBillingCycles);
            return this;
        }
        public CreateSubItemEstimateRequest subscriptionItemTrialEnd(int index, Timestamp subscriptionItemTrialEnd) {
            params.addOpt("subscription_items[trial_end][" + index + "]", subscriptionItemTrialEnd);
            return this;
        }
        public CreateSubItemEstimateRequest subscriptionItemServicePeriodDays(int index, Integer subscriptionItemServicePeriodDays) {
            params.addOpt("subscription_items[service_period_days][" + index + "]", subscriptionItemServicePeriodDays);
            return this;
        }
        public CreateSubItemEstimateRequest subscriptionItemChargeOnEvent(int index, com.chargebee.models.enums.ChargeOnEvent subscriptionItemChargeOnEvent) {
            params.addOpt("subscription_items[charge_on_event][" + index + "]", subscriptionItemChargeOnEvent);
            return this;
        }
        public CreateSubItemEstimateRequest subscriptionItemChargeOnce(int index, Boolean subscriptionItemChargeOnce) {
            params.addOpt("subscription_items[charge_once][" + index + "]", subscriptionItemChargeOnce);
            return this;
        }
        @Deprecated
        public CreateSubItemEstimateRequest subscriptionItemItemType(int index, com.chargebee.models.enums.ItemType subscriptionItemItemType) {
            params.addOpt("subscription_items[item_type][" + index + "]", subscriptionItemItemType);
            return this;
        }
        public CreateSubItemEstimateRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.add("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public CreateSubItemEstimateRequest discountDurationType(int index, com.chargebee.models.enums.DurationType discountDurationType) {
            params.add("discounts[duration_type][" + index + "]", discountDurationType);
            return this;
        }
        public CreateSubItemEstimateRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public CreateSubItemEstimateRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public CreateSubItemEstimateRequest discountPeriod(int index, Integer discountPeriod) {
            params.addOpt("discounts[period][" + index + "]", discountPeriod);
            return this;
        }
        public CreateSubItemEstimateRequest discountPeriodUnit(int index, com.chargebee.models.enums.PeriodUnit discountPeriodUnit) {
            params.addOpt("discounts[period_unit][" + index + "]", discountPeriodUnit);
            return this;
        }
        public CreateSubItemEstimateRequest discountIncludedInMrr(int index, Boolean discountIncludedInMrr) {
            params.addOpt("discounts[included_in_mrr][" + index + "]", discountIncludedInMrr);
            return this;
        }
        public CreateSubItemEstimateRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        public CreateSubItemEstimateRequest subscriptionItemChargeOnOption(int index, com.chargebee.models.enums.ChargeOnOption subscriptionItemChargeOnOption) {
            params.addOpt("subscription_items[charge_on_option][" + index + "]", subscriptionItemChargeOnOption);
            return this;
        }
        public CreateSubItemEstimateRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public CreateSubItemEstimateRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public CreateSubItemEstimateRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public CreateSubItemEstimateRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public CreateSubItemEstimateRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public CreateSubItemEstimateRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public CreateSubItemEstimateRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
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


        public CreateSubForCustomerEstimateRequest mandatoryAddonsToRemove(List<String> mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public CreateSubForCustomerEstimateRequest mandatoryAddonsToRemove(String... mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
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




        public CreateSubForCustomerEstimateRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
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

        public CreateSubForCustomerEstimateRequest subscriptionPlanQuantityInDecimal(String subscriptionPlanQuantityInDecimal) {
            params.addOpt("subscription[plan_quantity_in_decimal]", subscriptionPlanQuantityInDecimal);
            return this;
        }

        public CreateSubForCustomerEstimateRequest subscriptionPlanUnitPrice(Long subscriptionPlanUnitPrice) {
            params.addOpt("subscription[plan_unit_price]", subscriptionPlanUnitPrice);
            return this;
        }

        public CreateSubForCustomerEstimateRequest subscriptionPlanUnitPriceInDecimal(String subscriptionPlanUnitPriceInDecimal) {
            params.addOpt("subscription[plan_unit_price_in_decimal]", subscriptionPlanUnitPriceInDecimal);
            return this;
        }

        public CreateSubForCustomerEstimateRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public CreateSubForCustomerEstimateRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        public CreateSubForCustomerEstimateRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public CreateSubForCustomerEstimateRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
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

        public CreateSubForCustomerEstimateRequest subscriptionFreePeriod(Integer subscriptionFreePeriod) {
            params.addOpt("subscription[free_period]", subscriptionFreePeriod);
            return this;
        }

        public CreateSubForCustomerEstimateRequest subscriptionFreePeriodUnit(com.chargebee.models.enums.FreePeriodUnit subscriptionFreePeriodUnit) {
            params.addOpt("subscription[free_period_unit]", subscriptionFreePeriodUnit);
            return this;
        }

        public CreateSubForCustomerEstimateRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public CreateSubForCustomerEstimateRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public CreateSubForCustomerEstimateRequest subscriptionContractTermBillingCycleOnRenewal(Integer subscriptionContractTermBillingCycleOnRenewal) {
            params.addOpt("subscription[contract_term_billing_cycle_on_renewal]", subscriptionContractTermBillingCycleOnRenewal);
            return this;
        }

        public CreateSubForCustomerEstimateRequest subscriptionTrialEndAction(com.chargebee.models.enums.TrialEndAction subscriptionTrialEndAction) {
            params.addOpt("subscription[trial_end_action]", subscriptionTrialEndAction);
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
        public CreateSubForCustomerEstimateRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public CreateSubForCustomerEstimateRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public CreateSubForCustomerEstimateRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public CreateSubForCustomerEstimateRequest addonBillingCycles(int index, Integer addonBillingCycles) {
            params.addOpt("addons[billing_cycles][" + index + "]", addonBillingCycles);
            return this;
        }
        public CreateSubForCustomerEstimateRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public CreateSubForCustomerEstimateRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public CreateSubForCustomerEstimateRequest eventBasedAddonUnitPrice(int index, Long eventBasedAddonUnitPrice) {
            params.addOpt("event_based_addons[unit_price][" + index + "]", eventBasedAddonUnitPrice);
            return this;
        }
        public CreateSubForCustomerEstimateRequest eventBasedAddonQuantityInDecimal(int index, String eventBasedAddonQuantityInDecimal) {
            params.addOpt("event_based_addons[quantity_in_decimal][" + index + "]", eventBasedAddonQuantityInDecimal);
            return this;
        }
        public CreateSubForCustomerEstimateRequest eventBasedAddonUnitPriceInDecimal(int index, String eventBasedAddonUnitPriceInDecimal) {
            params.addOpt("event_based_addons[unit_price_in_decimal][" + index + "]", eventBasedAddonUnitPriceInDecimal);
            return this;
        }
        public CreateSubForCustomerEstimateRequest eventBasedAddonServicePeriodInDays(int index, Integer eventBasedAddonServicePeriodInDays) {
            params.addOpt("event_based_addons[service_period_in_days][" + index + "]", eventBasedAddonServicePeriodInDays);
            return this;
        }
        public CreateSubForCustomerEstimateRequest eventBasedAddonOnEvent(int index, com.chargebee.models.enums.OnEvent eventBasedAddonOnEvent) {
            params.addOpt("event_based_addons[on_event][" + index + "]", eventBasedAddonOnEvent);
            return this;
        }
        public CreateSubForCustomerEstimateRequest eventBasedAddonChargeOnce(int index, Boolean eventBasedAddonChargeOnce) {
            params.addOpt("event_based_addons[charge_once][" + index + "]", eventBasedAddonChargeOnce);
            return this;
        }
        public CreateSubForCustomerEstimateRequest eventBasedAddonChargeOn(int index, com.chargebee.models.enums.ChargeOn eventBasedAddonChargeOn) {
            params.addOpt("event_based_addons[charge_on][" + index + "]", eventBasedAddonChargeOn);
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

    public static class CreateSubItemForCustomerEstimateRequest extends Request<CreateSubItemForCustomerEstimateRequest> {

        private CreateSubItemForCustomerEstimateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateSubItemForCustomerEstimateRequest useExistingBalances(Boolean useExistingBalances) {
            params.addOpt("use_existing_balances", useExistingBalances);
            return this;
        }


        public CreateSubItemForCustomerEstimateRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }


        public CreateSubItemForCustomerEstimateRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public CreateSubItemForCustomerEstimateRequest mandatoryItemsToRemove(List<String> mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest mandatoryItemsToRemove(String... mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }






        public CreateSubItemForCustomerEstimateRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }




        public CreateSubItemForCustomerEstimateRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public CreateSubItemForCustomerEstimateRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }



        public CreateSubItemForCustomerEstimateRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription[id]", subscriptionId);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        @Deprecated
        public CreateSubItemForCustomerEstimateRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest subscriptionFreePeriod(Integer subscriptionFreePeriod) {
            params.addOpt("subscription[free_period]", subscriptionFreePeriod);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest subscriptionFreePeriodUnit(com.chargebee.models.enums.FreePeriodUnit subscriptionFreePeriodUnit) {
            params.addOpt("subscription[free_period_unit]", subscriptionFreePeriodUnit);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest subscriptionContractTermBillingCycleOnRenewal(Integer subscriptionContractTermBillingCycleOnRenewal) {
            params.addOpt("subscription[contract_term_billing_cycle_on_renewal]", subscriptionContractTermBillingCycleOnRenewal);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest subscriptionTrialEndAction(com.chargebee.models.enums.TrialEndAction subscriptionTrialEndAction) {
            params.addOpt("subscription[trial_end_action]", subscriptionTrialEndAction);
            return this;
        }

        public CreateSubItemForCustomerEstimateRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.add("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest subscriptionItemUnitPrice(int index, Long subscriptionItemUnitPrice) {
            params.addOpt("subscription_items[unit_price][" + index + "]", subscriptionItemUnitPrice);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest subscriptionItemUnitPriceInDecimal(int index, String subscriptionItemUnitPriceInDecimal) {
            params.addOpt("subscription_items[unit_price_in_decimal][" + index + "]", subscriptionItemUnitPriceInDecimal);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest subscriptionItemBillingCycles(int index, Integer subscriptionItemBillingCycles) {
            params.addOpt("subscription_items[billing_cycles][" + index + "]", subscriptionItemBillingCycles);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest subscriptionItemTrialEnd(int index, Timestamp subscriptionItemTrialEnd) {
            params.addOpt("subscription_items[trial_end][" + index + "]", subscriptionItemTrialEnd);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest subscriptionItemServicePeriodDays(int index, Integer subscriptionItemServicePeriodDays) {
            params.addOpt("subscription_items[service_period_days][" + index + "]", subscriptionItemServicePeriodDays);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest subscriptionItemChargeOnEvent(int index, com.chargebee.models.enums.ChargeOnEvent subscriptionItemChargeOnEvent) {
            params.addOpt("subscription_items[charge_on_event][" + index + "]", subscriptionItemChargeOnEvent);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest subscriptionItemChargeOnce(int index, Boolean subscriptionItemChargeOnce) {
            params.addOpt("subscription_items[charge_once][" + index + "]", subscriptionItemChargeOnce);
            return this;
        }
        @Deprecated
        public CreateSubItemForCustomerEstimateRequest subscriptionItemItemType(int index, com.chargebee.models.enums.ItemType subscriptionItemItemType) {
            params.addOpt("subscription_items[item_type][" + index + "]", subscriptionItemItemType);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.add("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest discountDurationType(int index, com.chargebee.models.enums.DurationType discountDurationType) {
            params.add("discounts[duration_type][" + index + "]", discountDurationType);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest discountPeriod(int index, Integer discountPeriod) {
            params.addOpt("discounts[period][" + index + "]", discountPeriod);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest discountPeriodUnit(int index, com.chargebee.models.enums.PeriodUnit discountPeriodUnit) {
            params.addOpt("discounts[period_unit][" + index + "]", discountPeriodUnit);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest discountIncludedInMrr(int index, Boolean discountIncludedInMrr) {
            params.addOpt("discounts[included_in_mrr][" + index + "]", discountIncludedInMrr);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest subscriptionItemChargeOnOption(int index, com.chargebee.models.enums.ChargeOnOption subscriptionItemChargeOnOption) {
            params.addOpt("subscription_items[charge_on_option][" + index + "]", subscriptionItemChargeOnOption);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public CreateSubItemForCustomerEstimateRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
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
    




        public UpdateSubscriptionRequest replaceAddonList(Boolean replaceAddonList) {
            params.addOpt("replace_addon_list", replaceAddonList);
            return this;
        }


        public UpdateSubscriptionRequest mandatoryAddonsToRemove(List<String> mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public UpdateSubscriptionRequest mandatoryAddonsToRemove(String... mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public UpdateSubscriptionRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public UpdateSubscriptionRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
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

        public UpdateSubscriptionRequest subscriptionPlanUnitPrice(Long subscriptionPlanUnitPrice) {
            params.addOpt("subscription[plan_unit_price]", subscriptionPlanUnitPrice);
            return this;
        }

        public UpdateSubscriptionRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public UpdateSubscriptionRequest subscriptionPlanQuantityInDecimal(String subscriptionPlanQuantityInDecimal) {
            params.addOpt("subscription[plan_quantity_in_decimal]", subscriptionPlanQuantityInDecimal);
            return this;
        }

        public UpdateSubscriptionRequest subscriptionPlanUnitPriceInDecimal(String subscriptionPlanUnitPriceInDecimal) {
            params.addOpt("subscription[plan_unit_price_in_decimal]", subscriptionPlanUnitPriceInDecimal);
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

        public UpdateSubscriptionRequest subscriptionAutoCollection(com.chargebee.models.enums.AutoCollection subscriptionAutoCollection) {
            params.addOpt("subscription[auto_collection]", subscriptionAutoCollection);
            return this;
        }

        public UpdateSubscriptionRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
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

        public UpdateSubscriptionRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
            return this;
        }

        public UpdateSubscriptionRequest customerRegisteredForGst(Boolean customerRegisteredForGst) {
            params.addOpt("customer[registered_for_gst]", customerRegisteredForGst);
            return this;
        }

        public UpdateSubscriptionRequest subscriptionFreePeriod(Integer subscriptionFreePeriod) {
            params.addOpt("subscription[free_period]", subscriptionFreePeriod);
            return this;
        }

        public UpdateSubscriptionRequest subscriptionFreePeriodUnit(com.chargebee.models.enums.FreePeriodUnit subscriptionFreePeriodUnit) {
            params.addOpt("subscription[free_period_unit]", subscriptionFreePeriodUnit);
            return this;
        }

        @Deprecated
        public UpdateSubscriptionRequest customerTaxability(com.chargebee.models.enums.Taxability customerTaxability) {
            params.addOpt("customer[taxability]", customerTaxability);
            return this;
        }

        public UpdateSubscriptionRequest subscriptionTrialEndAction(com.chargebee.models.enums.TrialEndAction subscriptionTrialEndAction) {
            params.addOpt("subscription[trial_end_action]", subscriptionTrialEndAction);
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
        public UpdateSubscriptionRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public UpdateSubscriptionRequest addonBillingCycles(int index, Integer addonBillingCycles) {
            params.addOpt("addons[billing_cycles][" + index + "]", addonBillingCycles);
            return this;
        }
        public UpdateSubscriptionRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public UpdateSubscriptionRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public UpdateSubscriptionRequest eventBasedAddonUnitPrice(int index, Long eventBasedAddonUnitPrice) {
            params.addOpt("event_based_addons[unit_price][" + index + "]", eventBasedAddonUnitPrice);
            return this;
        }
        public UpdateSubscriptionRequest eventBasedAddonServicePeriodInDays(int index, Integer eventBasedAddonServicePeriodInDays) {
            params.addOpt("event_based_addons[service_period_in_days][" + index + "]", eventBasedAddonServicePeriodInDays);
            return this;
        }
        public UpdateSubscriptionRequest eventBasedAddonChargeOn(int index, com.chargebee.models.enums.ChargeOn eventBasedAddonChargeOn) {
            params.addOpt("event_based_addons[charge_on][" + index + "]", eventBasedAddonChargeOn);
            return this;
        }
        public UpdateSubscriptionRequest eventBasedAddonOnEvent(int index, com.chargebee.models.enums.OnEvent eventBasedAddonOnEvent) {
            params.addOpt("event_based_addons[on_event][" + index + "]", eventBasedAddonOnEvent);
            return this;
        }
        public UpdateSubscriptionRequest eventBasedAddonChargeOnce(int index, Boolean eventBasedAddonChargeOnce) {
            params.addOpt("event_based_addons[charge_once][" + index + "]", eventBasedAddonChargeOnce);
            return this;
        }
        public UpdateSubscriptionRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public UpdateSubscriptionRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public UpdateSubscriptionRequest eventBasedAddonQuantityInDecimal(int index, String eventBasedAddonQuantityInDecimal) {
            params.addOpt("event_based_addons[quantity_in_decimal][" + index + "]", eventBasedAddonQuantityInDecimal);
            return this;
        }
        public UpdateSubscriptionRequest eventBasedAddonUnitPriceInDecimal(int index, String eventBasedAddonUnitPriceInDecimal) {
            params.addOpt("event_based_addons[unit_price_in_decimal][" + index + "]", eventBasedAddonUnitPriceInDecimal);
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

    public static class UpdateSubscriptionForItemsRequest extends Request<UpdateSubscriptionForItemsRequest> {

        private UpdateSubscriptionForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    




        public UpdateSubscriptionForItemsRequest mandatoryItemsToRemove(List<String> mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public UpdateSubscriptionForItemsRequest mandatoryItemsToRemove(String... mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public UpdateSubscriptionForItemsRequest replaceItemsList(Boolean replaceItemsList) {
            params.addOpt("replace_items_list", replaceItemsList);
            return this;
        }


        public UpdateSubscriptionForItemsRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public UpdateSubscriptionForItemsRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public UpdateSubscriptionForItemsRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }




        public UpdateSubscriptionForItemsRequest reactivateFrom(Timestamp reactivateFrom) {
            params.addOpt("reactivate_from", reactivateFrom);
            return this;
        }




        public UpdateSubscriptionForItemsRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }


        public UpdateSubscriptionForItemsRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public UpdateSubscriptionForItemsRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public UpdateSubscriptionForItemsRequest replaceCouponList(Boolean replaceCouponList) {
            params.addOpt("replace_coupon_list", replaceCouponList);
            return this;
        }


        public UpdateSubscriptionForItemsRequest prorate(Boolean prorate) {
            params.addOpt("prorate", prorate);
            return this;
        }


        public UpdateSubscriptionForItemsRequest endOfTerm(Boolean endOfTerm) {
            params.addOpt("end_of_term", endOfTerm);
            return this;
        }


        public UpdateSubscriptionForItemsRequest forceTermReset(Boolean forceTermReset) {
            params.addOpt("force_term_reset", forceTermReset);
            return this;
        }


        public UpdateSubscriptionForItemsRequest reactivate(Boolean reactivate) {
            params.addOpt("reactivate", reactivate);
            return this;
        }


        public UpdateSubscriptionForItemsRequest includeDelayedCharges(Boolean includeDelayedCharges) {
            params.addOpt("include_delayed_charges", includeDelayedCharges);
            return this;
        }


        public UpdateSubscriptionForItemsRequest useExistingBalances(Boolean useExistingBalances) {
            params.addOpt("use_existing_balances", useExistingBalances);
            return this;
        }


        public UpdateSubscriptionForItemsRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }


        public UpdateSubscriptionForItemsRequest subscriptionId(String subscriptionId) {
            params.add("subscription[id]", subscriptionId);
            return this;
        }

        @Deprecated
        public UpdateSubscriptionForItemsRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public UpdateSubscriptionForItemsRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public UpdateSubscriptionForItemsRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        @Deprecated
        public UpdateSubscriptionForItemsRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public UpdateSubscriptionForItemsRequest subscriptionAutoCollection(com.chargebee.models.enums.AutoCollection subscriptionAutoCollection) {
            params.addOpt("subscription[auto_collection]", subscriptionAutoCollection);
            return this;
        }

        public UpdateSubscriptionForItemsRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
            return this;
        }

        public UpdateSubscriptionForItemsRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public UpdateSubscriptionForItemsRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public UpdateSubscriptionForItemsRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public UpdateSubscriptionForItemsRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public UpdateSubscriptionForItemsRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public UpdateSubscriptionForItemsRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public UpdateSubscriptionForItemsRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public UpdateSubscriptionForItemsRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public UpdateSubscriptionForItemsRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public UpdateSubscriptionForItemsRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public UpdateSubscriptionForItemsRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public UpdateSubscriptionForItemsRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public UpdateSubscriptionForItemsRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public UpdateSubscriptionForItemsRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public UpdateSubscriptionForItemsRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public UpdateSubscriptionForItemsRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public UpdateSubscriptionForItemsRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public UpdateSubscriptionForItemsRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
            return this;
        }

        public UpdateSubscriptionForItemsRequest customerRegisteredForGst(Boolean customerRegisteredForGst) {
            params.addOpt("customer[registered_for_gst]", customerRegisteredForGst);
            return this;
        }

        public UpdateSubscriptionForItemsRequest subscriptionFreePeriod(Integer subscriptionFreePeriod) {
            params.addOpt("subscription[free_period]", subscriptionFreePeriod);
            return this;
        }

        public UpdateSubscriptionForItemsRequest subscriptionFreePeriodUnit(com.chargebee.models.enums.FreePeriodUnit subscriptionFreePeriodUnit) {
            params.addOpt("subscription[free_period_unit]", subscriptionFreePeriodUnit);
            return this;
        }

        @Deprecated
        public UpdateSubscriptionForItemsRequest customerTaxability(com.chargebee.models.enums.Taxability customerTaxability) {
            params.addOpt("customer[taxability]", customerTaxability);
            return this;
        }

        public UpdateSubscriptionForItemsRequest subscriptionTrialEndAction(com.chargebee.models.enums.TrialEndAction subscriptionTrialEndAction) {
            params.addOpt("subscription[trial_end_action]", subscriptionTrialEndAction);
            return this;
        }

        public UpdateSubscriptionForItemsRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.add("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public UpdateSubscriptionForItemsRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public UpdateSubscriptionForItemsRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        public UpdateSubscriptionForItemsRequest subscriptionItemUnitPrice(int index, Long subscriptionItemUnitPrice) {
            params.addOpt("subscription_items[unit_price][" + index + "]", subscriptionItemUnitPrice);
            return this;
        }
        public UpdateSubscriptionForItemsRequest subscriptionItemUnitPriceInDecimal(int index, String subscriptionItemUnitPriceInDecimal) {
            params.addOpt("subscription_items[unit_price_in_decimal][" + index + "]", subscriptionItemUnitPriceInDecimal);
            return this;
        }
        public UpdateSubscriptionForItemsRequest subscriptionItemBillingCycles(int index, Integer subscriptionItemBillingCycles) {
            params.addOpt("subscription_items[billing_cycles][" + index + "]", subscriptionItemBillingCycles);
            return this;
        }
        public UpdateSubscriptionForItemsRequest subscriptionItemTrialEnd(int index, Timestamp subscriptionItemTrialEnd) {
            params.addOpt("subscription_items[trial_end][" + index + "]", subscriptionItemTrialEnd);
            return this;
        }
        public UpdateSubscriptionForItemsRequest subscriptionItemServicePeriodDays(int index, Integer subscriptionItemServicePeriodDays) {
            params.addOpt("subscription_items[service_period_days][" + index + "]", subscriptionItemServicePeriodDays);
            return this;
        }
        public UpdateSubscriptionForItemsRequest subscriptionItemChargeOnEvent(int index, com.chargebee.models.enums.ChargeOnEvent subscriptionItemChargeOnEvent) {
            params.addOpt("subscription_items[charge_on_event][" + index + "]", subscriptionItemChargeOnEvent);
            return this;
        }
        public UpdateSubscriptionForItemsRequest subscriptionItemChargeOnce(int index, Boolean subscriptionItemChargeOnce) {
            params.addOpt("subscription_items[charge_once][" + index + "]", subscriptionItemChargeOnce);
            return this;
        }
        public UpdateSubscriptionForItemsRequest subscriptionItemChargeOnOption(int index, com.chargebee.models.enums.ChargeOnOption subscriptionItemChargeOnOption) {
            params.addOpt("subscription_items[charge_on_option][" + index + "]", subscriptionItemChargeOnOption);
            return this;
        }
        @Deprecated
        public UpdateSubscriptionForItemsRequest subscriptionItemItemType(int index, com.chargebee.models.enums.ItemType subscriptionItemItemType) {
            params.addOpt("subscription_items[item_type][" + index + "]", subscriptionItemItemType);
            return this;
        }
        public UpdateSubscriptionForItemsRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.add("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public UpdateSubscriptionForItemsRequest discountDurationType(int index, com.chargebee.models.enums.DurationType discountDurationType) {
            params.add("discounts[duration_type][" + index + "]", discountDurationType);
            return this;
        }
        public UpdateSubscriptionForItemsRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public UpdateSubscriptionForItemsRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public UpdateSubscriptionForItemsRequest discountPeriod(int index, Integer discountPeriod) {
            params.addOpt("discounts[period][" + index + "]", discountPeriod);
            return this;
        }
        public UpdateSubscriptionForItemsRequest discountPeriodUnit(int index, com.chargebee.models.enums.PeriodUnit discountPeriodUnit) {
            params.addOpt("discounts[period_unit][" + index + "]", discountPeriodUnit);
            return this;
        }
        public UpdateSubscriptionForItemsRequest discountIncludedInMrr(int index, Boolean discountIncludedInMrr) {
            params.addOpt("discounts[included_in_mrr][" + index + "]", discountIncludedInMrr);
            return this;
        }
        public UpdateSubscriptionForItemsRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        public UpdateSubscriptionForItemsRequest discountOperationType(int index, com.chargebee.models.enums.OperationType discountOperationType) {
            params.add("discounts[operation_type][" + index + "]", discountOperationType);
            return this;
        }
        public UpdateSubscriptionForItemsRequest discountId(int index, String discountId) {
            params.addOpt("discounts[id][" + index + "]", discountId);
            return this;
        }
        public UpdateSubscriptionForItemsRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public UpdateSubscriptionForItemsRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public UpdateSubscriptionForItemsRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public UpdateSubscriptionForItemsRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public UpdateSubscriptionForItemsRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public UpdateSubscriptionForItemsRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public UpdateSubscriptionForItemsRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
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

    public static class AdvanceInvoiceEstimateRequest extends Request<AdvanceInvoiceEstimateRequest> {

        private AdvanceInvoiceEstimateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public AdvanceInvoiceEstimateRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }


        public AdvanceInvoiceEstimateRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }


        public AdvanceInvoiceEstimateRequest scheduleType(com.chargebee.models.enums.ScheduleType scheduleType) {
            params.addOpt("schedule_type", scheduleType);
            return this;
        }


        public AdvanceInvoiceEstimateRequest fixedIntervalScheduleNumberOfOccurrences(Integer fixedIntervalScheduleNumberOfOccurrences) {
            params.addOpt("fixed_interval_schedule[number_of_occurrences]", fixedIntervalScheduleNumberOfOccurrences);
            return this;
        }

        public AdvanceInvoiceEstimateRequest fixedIntervalScheduleDaysBeforeRenewal(Integer fixedIntervalScheduleDaysBeforeRenewal) {
            params.addOpt("fixed_interval_schedule[days_before_renewal]", fixedIntervalScheduleDaysBeforeRenewal);
            return this;
        }

        public AdvanceInvoiceEstimateRequest fixedIntervalScheduleEndScheduleOn(com.chargebee.models.enums.EndScheduleOn fixedIntervalScheduleEndScheduleOn) {
            params.addOpt("fixed_interval_schedule[end_schedule_on]", fixedIntervalScheduleEndScheduleOn);
            return this;
        }

        public AdvanceInvoiceEstimateRequest fixedIntervalScheduleEndDate(Timestamp fixedIntervalScheduleEndDate) {
            params.addOpt("fixed_interval_schedule[end_date]", fixedIntervalScheduleEndDate);
            return this;
        }

        public AdvanceInvoiceEstimateRequest specificDatesScheduleTermsToCharge(int index, Integer specificDatesScheduleTermsToCharge) {
            params.addOpt("specific_dates_schedule[terms_to_charge][" + index + "]", specificDatesScheduleTermsToCharge);
            return this;
        }
        public AdvanceInvoiceEstimateRequest specificDatesScheduleDate(int index, Timestamp specificDatesScheduleDate) {
            params.addOpt("specific_dates_schedule[date][" + index + "]", specificDatesScheduleDate);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class RegenerateInvoiceEstimateRequest extends Request<RegenerateInvoiceEstimateRequest> {

        private RegenerateInvoiceEstimateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RegenerateInvoiceEstimateRequest dateFrom(Timestamp dateFrom) {
            params.addOpt("date_from", dateFrom);
            return this;
        }


        public RegenerateInvoiceEstimateRequest dateTo(Timestamp dateTo) {
            params.addOpt("date_to", dateTo);
            return this;
        }


        public RegenerateInvoiceEstimateRequest prorate(Boolean prorate) {
            params.addOpt("prorate", prorate);
            return this;
        }


        public RegenerateInvoiceEstimateRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
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
            params.add("term_ends_at", termEndsAt);
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


        public CancelSubscriptionRequest cancelAt(Timestamp cancelAt) {
            params.addOpt("cancel_at", cancelAt);
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


        public CancelSubscriptionRequest contractTermCancelOption(com.chargebee.models.enums.ContractTermCancelOption contractTermCancelOption) {
            params.addOpt("contract_term_cancel_option", contractTermCancelOption);
            return this;
        }


        public CancelSubscriptionRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public CancelSubscriptionRequest cancelReasonCode(String cancelReasonCode) {
            params.addOpt("cancel_reason_code", cancelReasonCode);
            return this;
        }


        public CancelSubscriptionRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public CancelSubscriptionRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public CancelSubscriptionRequest eventBasedAddonUnitPrice(int index, Long eventBasedAddonUnitPrice) {
            params.addOpt("event_based_addons[unit_price][" + index + "]", eventBasedAddonUnitPrice);
            return this;
        }
        public CancelSubscriptionRequest eventBasedAddonServicePeriodInDays(int index, Integer eventBasedAddonServicePeriodInDays) {
            params.addOpt("event_based_addons[service_period_in_days][" + index + "]", eventBasedAddonServicePeriodInDays);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CancelSubscriptionForItemsRequest extends Request<CancelSubscriptionForItemsRequest> {

        private CancelSubscriptionForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CancelSubscriptionForItemsRequest endOfTerm(Boolean endOfTerm) {
            params.addOpt("end_of_term", endOfTerm);
            return this;
        }


        public CancelSubscriptionForItemsRequest cancelAt(Timestamp cancelAt) {
            params.addOpt("cancel_at", cancelAt);
            return this;
        }


        public CancelSubscriptionForItemsRequest creditOptionForCurrentTermCharges(com.chargebee.models.enums.CreditOptionForCurrentTermCharges creditOptionForCurrentTermCharges) {
            params.addOpt("credit_option_for_current_term_charges", creditOptionForCurrentTermCharges);
            return this;
        }


        public CancelSubscriptionForItemsRequest unbilledChargesOption(com.chargebee.models.enums.UnbilledChargesOption unbilledChargesOption) {
            params.addOpt("unbilled_charges_option", unbilledChargesOption);
            return this;
        }


        public CancelSubscriptionForItemsRequest accountReceivablesHandling(com.chargebee.models.enums.AccountReceivablesHandling accountReceivablesHandling) {
            params.addOpt("account_receivables_handling", accountReceivablesHandling);
            return this;
        }


        public CancelSubscriptionForItemsRequest refundableCreditsHandling(com.chargebee.models.enums.RefundableCreditsHandling refundableCreditsHandling) {
            params.addOpt("refundable_credits_handling", refundableCreditsHandling);
            return this;
        }


        public CancelSubscriptionForItemsRequest contractTermCancelOption(com.chargebee.models.enums.ContractTermCancelOption contractTermCancelOption) {
            params.addOpt("contract_term_cancel_option", contractTermCancelOption);
            return this;
        }


        public CancelSubscriptionForItemsRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public CancelSubscriptionForItemsRequest cancelReasonCode(String cancelReasonCode) {
            params.addOpt("cancel_reason_code", cancelReasonCode);
            return this;
        }


        public CancelSubscriptionForItemsRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.addOpt("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public CancelSubscriptionForItemsRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public CancelSubscriptionForItemsRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        public CancelSubscriptionForItemsRequest subscriptionItemUnitPrice(int index, Long subscriptionItemUnitPrice) {
            params.addOpt("subscription_items[unit_price][" + index + "]", subscriptionItemUnitPrice);
            return this;
        }
        public CancelSubscriptionForItemsRequest subscriptionItemUnitPriceInDecimal(int index, String subscriptionItemUnitPriceInDecimal) {
            params.addOpt("subscription_items[unit_price_in_decimal][" + index + "]", subscriptionItemUnitPriceInDecimal);
            return this;
        }
        public CancelSubscriptionForItemsRequest subscriptionItemServicePeriodDays(int index, Integer subscriptionItemServicePeriodDays) {
            params.addOpt("subscription_items[service_period_days][" + index + "]", subscriptionItemServicePeriodDays);
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

        public PauseSubscriptionRequest subscriptionSkipBillingCycles(Integer subscriptionSkipBillingCycles) {
            params.addOpt("subscription[skip_billing_cycles]", subscriptionSkipBillingCycles);
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

    public static class GiftSubscriptionRequest extends Request<GiftSubscriptionRequest> {

        private GiftSubscriptionRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public GiftSubscriptionRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public GiftSubscriptionRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public GiftSubscriptionRequest giftScheduledAt(Timestamp giftScheduledAt) {
            params.addOpt("gift[scheduled_at]", giftScheduledAt);
            return this;
        }

        public GiftSubscriptionRequest giftAutoClaim(Boolean giftAutoClaim) {
            params.addOpt("gift[auto_claim]", giftAutoClaim);
            return this;
        }

        public GiftSubscriptionRequest giftNoExpiry(Boolean giftNoExpiry) {
            params.addOpt("gift[no_expiry]", giftNoExpiry);
            return this;
        }

        public GiftSubscriptionRequest giftClaimExpiryDate(Timestamp giftClaimExpiryDate) {
            params.addOpt("gift[claim_expiry_date]", giftClaimExpiryDate);
            return this;
        }

        public GiftSubscriptionRequest gifterCustomerId(String gifterCustomerId) {
            params.add("gifter[customer_id]", gifterCustomerId);
            return this;
        }

        public GiftSubscriptionRequest gifterSignature(String gifterSignature) {
            params.add("gifter[signature]", gifterSignature);
            return this;
        }

        public GiftSubscriptionRequest gifterNote(String gifterNote) {
            params.addOpt("gifter[note]", gifterNote);
            return this;
        }

        public GiftSubscriptionRequest gifterPaymentSrcId(String gifterPaymentSrcId) {
            params.addOpt("gifter[payment_src_id]", gifterPaymentSrcId);
            return this;
        }

        public GiftSubscriptionRequest giftReceiverCustomerId(String giftReceiverCustomerId) {
            params.add("gift_receiver[customer_id]", giftReceiverCustomerId);
            return this;
        }

        public GiftSubscriptionRequest giftReceiverFirstName(String giftReceiverFirstName) {
            params.add("gift_receiver[first_name]", giftReceiverFirstName);
            return this;
        }

        public GiftSubscriptionRequest giftReceiverLastName(String giftReceiverLastName) {
            params.add("gift_receiver[last_name]", giftReceiverLastName);
            return this;
        }

        public GiftSubscriptionRequest giftReceiverEmail(String giftReceiverEmail) {
            params.add("gift_receiver[email]", giftReceiverEmail);
            return this;
        }

        public GiftSubscriptionRequest paymentIntentId(String paymentIntentId) {
            params.addOpt("payment_intent[id]", paymentIntentId);
            return this;
        }

        public GiftSubscriptionRequest paymentIntentGatewayAccountId(String paymentIntentGatewayAccountId) {
            params.addOpt("payment_intent[gateway_account_id]", paymentIntentGatewayAccountId);
            return this;
        }

        public GiftSubscriptionRequest paymentIntentGwToken(String paymentIntentGwToken) {
            params.addOpt("payment_intent[gw_token]", paymentIntentGwToken);
            return this;
        }

        public GiftSubscriptionRequest paymentIntentPaymentMethodType(PaymentIntent.PaymentMethodType paymentIntentPaymentMethodType) {
            params.addOpt("payment_intent[payment_method_type]", paymentIntentPaymentMethodType);
            return this;
        }

        public GiftSubscriptionRequest paymentIntentReferenceId(String paymentIntentReferenceId) {
            params.addOpt("payment_intent[reference_id]", paymentIntentReferenceId);
            return this;
        }

        @Deprecated
        public GiftSubscriptionRequest paymentIntentGwPaymentMethodId(String paymentIntentGwPaymentMethodId) {
            params.addOpt("payment_intent[gw_payment_method_id]", paymentIntentGwPaymentMethodId);
            return this;
        }

        public GiftSubscriptionRequest paymentIntentAdditionalInformation(JSONObject paymentIntentAdditionalInformation) {
            params.addOpt("payment_intent[additional_information]", paymentIntentAdditionalInformation);
            return this;
        }

        public GiftSubscriptionRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public GiftSubscriptionRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public GiftSubscriptionRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public GiftSubscriptionRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public GiftSubscriptionRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public GiftSubscriptionRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public GiftSubscriptionRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public GiftSubscriptionRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public GiftSubscriptionRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public GiftSubscriptionRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public GiftSubscriptionRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public GiftSubscriptionRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public GiftSubscriptionRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public GiftSubscriptionRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public GiftSubscriptionRequest subscriptionPlanId(String subscriptionPlanId) {
            params.add("subscription[plan_id]", subscriptionPlanId);
            return this;
        }

        public GiftSubscriptionRequest subscriptionPlanQuantity(Integer subscriptionPlanQuantity) {
            params.addOpt("subscription[plan_quantity]", subscriptionPlanQuantity);
            return this;
        }

        public GiftSubscriptionRequest subscriptionPlanQuantityInDecimal(String subscriptionPlanQuantityInDecimal) {
            params.addOpt("subscription[plan_quantity_in_decimal]", subscriptionPlanQuantityInDecimal);
            return this;
        }

        public GiftSubscriptionRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public GiftSubscriptionRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public GiftSubscriptionRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class GiftSubscriptionForItemsRequest extends Request<GiftSubscriptionForItemsRequest> {

        private GiftSubscriptionForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public GiftSubscriptionForItemsRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public GiftSubscriptionForItemsRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public GiftSubscriptionForItemsRequest giftScheduledAt(Timestamp giftScheduledAt) {
            params.addOpt("gift[scheduled_at]", giftScheduledAt);
            return this;
        }

        public GiftSubscriptionForItemsRequest giftAutoClaim(Boolean giftAutoClaim) {
            params.addOpt("gift[auto_claim]", giftAutoClaim);
            return this;
        }

        public GiftSubscriptionForItemsRequest giftNoExpiry(Boolean giftNoExpiry) {
            params.addOpt("gift[no_expiry]", giftNoExpiry);
            return this;
        }

        public GiftSubscriptionForItemsRequest giftClaimExpiryDate(Timestamp giftClaimExpiryDate) {
            params.addOpt("gift[claim_expiry_date]", giftClaimExpiryDate);
            return this;
        }

        public GiftSubscriptionForItemsRequest gifterCustomerId(String gifterCustomerId) {
            params.add("gifter[customer_id]", gifterCustomerId);
            return this;
        }

        public GiftSubscriptionForItemsRequest gifterSignature(String gifterSignature) {
            params.add("gifter[signature]", gifterSignature);
            return this;
        }

        public GiftSubscriptionForItemsRequest gifterNote(String gifterNote) {
            params.addOpt("gifter[note]", gifterNote);
            return this;
        }

        public GiftSubscriptionForItemsRequest gifterPaymentSrcId(String gifterPaymentSrcId) {
            params.addOpt("gifter[payment_src_id]", gifterPaymentSrcId);
            return this;
        }

        public GiftSubscriptionForItemsRequest giftReceiverCustomerId(String giftReceiverCustomerId) {
            params.add("gift_receiver[customer_id]", giftReceiverCustomerId);
            return this;
        }

        public GiftSubscriptionForItemsRequest giftReceiverFirstName(String giftReceiverFirstName) {
            params.add("gift_receiver[first_name]", giftReceiverFirstName);
            return this;
        }

        public GiftSubscriptionForItemsRequest giftReceiverLastName(String giftReceiverLastName) {
            params.add("gift_receiver[last_name]", giftReceiverLastName);
            return this;
        }

        public GiftSubscriptionForItemsRequest giftReceiverEmail(String giftReceiverEmail) {
            params.add("gift_receiver[email]", giftReceiverEmail);
            return this;
        }

        public GiftSubscriptionForItemsRequest paymentIntentId(String paymentIntentId) {
            params.addOpt("payment_intent[id]", paymentIntentId);
            return this;
        }

        public GiftSubscriptionForItemsRequest paymentIntentGatewayAccountId(String paymentIntentGatewayAccountId) {
            params.addOpt("payment_intent[gateway_account_id]", paymentIntentGatewayAccountId);
            return this;
        }

        public GiftSubscriptionForItemsRequest paymentIntentGwToken(String paymentIntentGwToken) {
            params.addOpt("payment_intent[gw_token]", paymentIntentGwToken);
            return this;
        }

        public GiftSubscriptionForItemsRequest paymentIntentPaymentMethodType(PaymentIntent.PaymentMethodType paymentIntentPaymentMethodType) {
            params.addOpt("payment_intent[payment_method_type]", paymentIntentPaymentMethodType);
            return this;
        }

        public GiftSubscriptionForItemsRequest paymentIntentReferenceId(String paymentIntentReferenceId) {
            params.addOpt("payment_intent[reference_id]", paymentIntentReferenceId);
            return this;
        }

        @Deprecated
        public GiftSubscriptionForItemsRequest paymentIntentGwPaymentMethodId(String paymentIntentGwPaymentMethodId) {
            params.addOpt("payment_intent[gw_payment_method_id]", paymentIntentGwPaymentMethodId);
            return this;
        }

        public GiftSubscriptionForItemsRequest paymentIntentAdditionalInformation(JSONObject paymentIntentAdditionalInformation) {
            params.addOpt("payment_intent[additional_information]", paymentIntentAdditionalInformation);
            return this;
        }

        public GiftSubscriptionForItemsRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public GiftSubscriptionForItemsRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public GiftSubscriptionForItemsRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public GiftSubscriptionForItemsRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public GiftSubscriptionForItemsRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public GiftSubscriptionForItemsRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public GiftSubscriptionForItemsRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public GiftSubscriptionForItemsRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public GiftSubscriptionForItemsRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public GiftSubscriptionForItemsRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public GiftSubscriptionForItemsRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public GiftSubscriptionForItemsRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public GiftSubscriptionForItemsRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public GiftSubscriptionForItemsRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public GiftSubscriptionForItemsRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.addOpt("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public GiftSubscriptionForItemsRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public GiftSubscriptionForItemsRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateInvoiceRequest extends Request<CreateInvoiceRequest> {

        private CreateInvoiceRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateInvoiceRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }




        public CreateInvoiceRequest invoiceNote(String invoiceNote) {
            params.addOpt("invoice_note", invoiceNote);
            return this;
        }


        public CreateInvoiceRequest removeGeneralNote(Boolean removeGeneralNote) {
            params.addOpt("remove_general_note", removeGeneralNote);
            return this;
        }


        @Deprecated
        public CreateInvoiceRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public CreateInvoiceRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateInvoiceRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateInvoiceRequest authorizationTransactionId(String authorizationTransactionId) {
            params.addOpt("authorization_transaction_id", authorizationTransactionId);
            return this;
        }


        public CreateInvoiceRequest paymentSourceId(String paymentSourceId) {
            params.addOpt("payment_source_id", paymentSourceId);
            return this;
        }


        public CreateInvoiceRequest autoCollection(com.chargebee.models.enums.AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        public CreateInvoiceRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public CreateInvoiceRequest invoiceCustomerId(String invoiceCustomerId) {
            params.addOpt("invoice[customer_id]", invoiceCustomerId);
            return this;
        }

        public CreateInvoiceRequest invoiceSubscriptionId(String invoiceSubscriptionId) {
            params.addOpt("invoice[subscription_id]", invoiceSubscriptionId);
            return this;
        }

        public CreateInvoiceRequest invoicePoNumber(String invoicePoNumber) {
            params.addOpt("invoice[po_number]", invoicePoNumber);
            return this;
        }

        public CreateInvoiceRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public CreateInvoiceRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public CreateInvoiceRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public CreateInvoiceRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public CreateInvoiceRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public CreateInvoiceRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CreateInvoiceRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CreateInvoiceRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CreateInvoiceRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CreateInvoiceRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CreateInvoiceRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public CreateInvoiceRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CreateInvoiceRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CreateInvoiceRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CreateInvoiceRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public CreateInvoiceRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public CreateInvoiceRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public CreateInvoiceRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public CreateInvoiceRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public CreateInvoiceRequest addonDateFrom(int index, Timestamp addonDateFrom) {
            params.addOpt("addons[date_from][" + index + "]", addonDateFrom);
            return this;
        }
        public CreateInvoiceRequest addonDateTo(int index, Timestamp addonDateTo) {
            params.addOpt("addons[date_to][" + index + "]", addonDateTo);
            return this;
        }
        public CreateInvoiceRequest chargeAmount(int index, Long chargeAmount) {
            params.addOpt("charges[amount][" + index + "]", chargeAmount);
            return this;
        }
        public CreateInvoiceRequest chargeAmountInDecimal(int index, String chargeAmountInDecimal) {
            params.addOpt("charges[amount_in_decimal][" + index + "]", chargeAmountInDecimal);
            return this;
        }
        public CreateInvoiceRequest chargeDescription(int index, String chargeDescription) {
            params.addOpt("charges[description][" + index + "]", chargeDescription);
            return this;
        }
        public CreateInvoiceRequest chargeTaxable(int index, Boolean chargeTaxable) {
            params.addOpt("charges[taxable][" + index + "]", chargeTaxable);
            return this;
        }
        public CreateInvoiceRequest chargeTaxProfileId(int index, String chargeTaxProfileId) {
            params.addOpt("charges[tax_profile_id][" + index + "]", chargeTaxProfileId);
            return this;
        }
        public CreateInvoiceRequest chargeAvalaraTaxCode(int index, String chargeAvalaraTaxCode) {
            params.addOpt("charges[avalara_tax_code][" + index + "]", chargeAvalaraTaxCode);
            return this;
        }
        public CreateInvoiceRequest chargeHsnCode(int index, String chargeHsnCode) {
            params.addOpt("charges[hsn_code][" + index + "]", chargeHsnCode);
            return this;
        }
        public CreateInvoiceRequest chargeTaxjarProductCode(int index, String chargeTaxjarProductCode) {
            params.addOpt("charges[taxjar_product_code][" + index + "]", chargeTaxjarProductCode);
            return this;
        }
        public CreateInvoiceRequest chargeAvalaraSaleType(int index, com.chargebee.models.enums.AvalaraSaleType chargeAvalaraSaleType) {
            params.addOpt("charges[avalara_sale_type][" + index + "]", chargeAvalaraSaleType);
            return this;
        }
        public CreateInvoiceRequest chargeAvalaraTransactionType(int index, Integer chargeAvalaraTransactionType) {
            params.addOpt("charges[avalara_transaction_type][" + index + "]", chargeAvalaraTransactionType);
            return this;
        }
        public CreateInvoiceRequest chargeAvalaraServiceType(int index, Integer chargeAvalaraServiceType) {
            params.addOpt("charges[avalara_service_type][" + index + "]", chargeAvalaraServiceType);
            return this;
        }
        public CreateInvoiceRequest chargeDateFrom(int index, Timestamp chargeDateFrom) {
            params.addOpt("charges[date_from][" + index + "]", chargeDateFrom);
            return this;
        }
        public CreateInvoiceRequest chargeDateTo(int index, Timestamp chargeDateTo) {
            params.addOpt("charges[date_to][" + index + "]", chargeDateTo);
            return this;
        }
        public CreateInvoiceRequest notesToRemoveEntityType(int index, com.chargebee.models.enums.EntityType notesToRemoveEntityType) {
            params.addOpt("notes_to_remove[entity_type][" + index + "]", notesToRemoveEntityType);
            return this;
        }
        public CreateInvoiceRequest notesToRemoveEntityId(int index, String notesToRemoveEntityId) {
            params.addOpt("notes_to_remove[entity_id][" + index + "]", notesToRemoveEntityId);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateInvoiceForItemsRequest extends Request<CreateInvoiceForItemsRequest> {

        private CreateInvoiceForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateInvoiceForItemsRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public CreateInvoiceForItemsRequest invoiceNote(String invoiceNote) {
            params.addOpt("invoice_note", invoiceNote);
            return this;
        }


        public CreateInvoiceForItemsRequest removeGeneralNote(Boolean removeGeneralNote) {
            params.addOpt("remove_general_note", removeGeneralNote);
            return this;
        }


        @Deprecated
        public CreateInvoiceForItemsRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public CreateInvoiceForItemsRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateInvoiceForItemsRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateInvoiceForItemsRequest authorizationTransactionId(String authorizationTransactionId) {
            params.addOpt("authorization_transaction_id", authorizationTransactionId);
            return this;
        }


        public CreateInvoiceForItemsRequest paymentSourceId(String paymentSourceId) {
            params.addOpt("payment_source_id", paymentSourceId);
            return this;
        }


        public CreateInvoiceForItemsRequest autoCollection(com.chargebee.models.enums.AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        public CreateInvoiceForItemsRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }




        public CreateInvoiceForItemsRequest invoiceCustomerId(String invoiceCustomerId) {
            params.addOpt("invoice[customer_id]", invoiceCustomerId);
            return this;
        }

        public CreateInvoiceForItemsRequest invoiceSubscriptionId(String invoiceSubscriptionId) {
            params.addOpt("invoice[subscription_id]", invoiceSubscriptionId);
            return this;
        }

        public CreateInvoiceForItemsRequest invoicePoNumber(String invoicePoNumber) {
            params.addOpt("invoice[po_number]", invoicePoNumber);
            return this;
        }

        public CreateInvoiceForItemsRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public CreateInvoiceForItemsRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public CreateInvoiceForItemsRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public CreateInvoiceForItemsRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public CreateInvoiceForItemsRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public CreateInvoiceForItemsRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CreateInvoiceForItemsRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CreateInvoiceForItemsRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CreateInvoiceForItemsRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CreateInvoiceForItemsRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CreateInvoiceForItemsRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public CreateInvoiceForItemsRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CreateInvoiceForItemsRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CreateInvoiceForItemsRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CreateInvoiceForItemsRequest itemPriceItemPriceId(int index, String itemPriceItemPriceId) {
            params.addOpt("item_prices[item_price_id][" + index + "]", itemPriceItemPriceId);
            return this;
        }
        public CreateInvoiceForItemsRequest itemPriceQuantity(int index, Integer itemPriceQuantity) {
            params.addOpt("item_prices[quantity][" + index + "]", itemPriceQuantity);
            return this;
        }
        public CreateInvoiceForItemsRequest itemPriceQuantityInDecimal(int index, String itemPriceQuantityInDecimal) {
            params.addOpt("item_prices[quantity_in_decimal][" + index + "]", itemPriceQuantityInDecimal);
            return this;
        }
        public CreateInvoiceForItemsRequest itemPriceUnitPrice(int index, Long itemPriceUnitPrice) {
            params.addOpt("item_prices[unit_price][" + index + "]", itemPriceUnitPrice);
            return this;
        }
        public CreateInvoiceForItemsRequest itemPriceUnitPriceInDecimal(int index, String itemPriceUnitPriceInDecimal) {
            params.addOpt("item_prices[unit_price_in_decimal][" + index + "]", itemPriceUnitPriceInDecimal);
            return this;
        }
        public CreateInvoiceForItemsRequest itemPriceDateFrom(int index, Timestamp itemPriceDateFrom) {
            params.addOpt("item_prices[date_from][" + index + "]", itemPriceDateFrom);
            return this;
        }
        public CreateInvoiceForItemsRequest itemPriceDateTo(int index, Timestamp itemPriceDateTo) {
            params.addOpt("item_prices[date_to][" + index + "]", itemPriceDateTo);
            return this;
        }
        public CreateInvoiceForItemsRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public CreateInvoiceForItemsRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public CreateInvoiceForItemsRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public CreateInvoiceForItemsRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public CreateInvoiceForItemsRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public CreateInvoiceForItemsRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public CreateInvoiceForItemsRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            return this;
        }
        public CreateInvoiceForItemsRequest chargeAmount(int index, Long chargeAmount) {
            params.addOpt("charges[amount][" + index + "]", chargeAmount);
            return this;
        }
        public CreateInvoiceForItemsRequest chargeAmountInDecimal(int index, String chargeAmountInDecimal) {
            params.addOpt("charges[amount_in_decimal][" + index + "]", chargeAmountInDecimal);
            return this;
        }
        public CreateInvoiceForItemsRequest chargeDescription(int index, String chargeDescription) {
            params.addOpt("charges[description][" + index + "]", chargeDescription);
            return this;
        }
        public CreateInvoiceForItemsRequest chargeTaxable(int index, Boolean chargeTaxable) {
            params.addOpt("charges[taxable][" + index + "]", chargeTaxable);
            return this;
        }
        public CreateInvoiceForItemsRequest chargeTaxProfileId(int index, String chargeTaxProfileId) {
            params.addOpt("charges[tax_profile_id][" + index + "]", chargeTaxProfileId);
            return this;
        }
        public CreateInvoiceForItemsRequest chargeAvalaraTaxCode(int index, String chargeAvalaraTaxCode) {
            params.addOpt("charges[avalara_tax_code][" + index + "]", chargeAvalaraTaxCode);
            return this;
        }
        public CreateInvoiceForItemsRequest chargeHsnCode(int index, String chargeHsnCode) {
            params.addOpt("charges[hsn_code][" + index + "]", chargeHsnCode);
            return this;
        }
        public CreateInvoiceForItemsRequest chargeTaxjarProductCode(int index, String chargeTaxjarProductCode) {
            params.addOpt("charges[taxjar_product_code][" + index + "]", chargeTaxjarProductCode);
            return this;
        }
        public CreateInvoiceForItemsRequest chargeAvalaraSaleType(int index, com.chargebee.models.enums.AvalaraSaleType chargeAvalaraSaleType) {
            params.addOpt("charges[avalara_sale_type][" + index + "]", chargeAvalaraSaleType);
            return this;
        }
        public CreateInvoiceForItemsRequest chargeAvalaraTransactionType(int index, Integer chargeAvalaraTransactionType) {
            params.addOpt("charges[avalara_transaction_type][" + index + "]", chargeAvalaraTransactionType);
            return this;
        }
        public CreateInvoiceForItemsRequest chargeAvalaraServiceType(int index, Integer chargeAvalaraServiceType) {
            params.addOpt("charges[avalara_service_type][" + index + "]", chargeAvalaraServiceType);
            return this;
        }
        public CreateInvoiceForItemsRequest chargeDateFrom(int index, Timestamp chargeDateFrom) {
            params.addOpt("charges[date_from][" + index + "]", chargeDateFrom);
            return this;
        }
        public CreateInvoiceForItemsRequest chargeDateTo(int index, Timestamp chargeDateTo) {
            params.addOpt("charges[date_to][" + index + "]", chargeDateTo);
            return this;
        }
        public CreateInvoiceForItemsRequest notesToRemoveEntityType(int index, com.chargebee.models.enums.EntityType notesToRemoveEntityType) {
            params.addOpt("notes_to_remove[entity_type][" + index + "]", notesToRemoveEntityType);
            return this;
        }
        public CreateInvoiceForItemsRequest notesToRemoveEntityId(int index, String notesToRemoveEntityId) {
            params.addOpt("notes_to_remove[entity_id][" + index + "]", notesToRemoveEntityId);
            return this;
        }
        public CreateInvoiceForItemsRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public CreateInvoiceForItemsRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public CreateInvoiceForItemsRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.add("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public CreateInvoiceForItemsRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

}
