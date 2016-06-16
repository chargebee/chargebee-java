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

    public InvoiceEstimate nextInvoiceEstimate() {
        return optSubResource("next_invoice_estimate", InvoiceEstimate.class);
    }

    public List<CreditNoteEstimate> creditNoteEstimates() {
        return optList("credit_note_estimates", CreditNoteEstimate.class);
    }

    // Operations
    //===========

    public static CreateSubscriptionRequest createSubscription() throws IOException {
        String uri = uri("estimates", "create_subscription");
        return new CreateSubscriptionRequest(Method.POST, uri);
    }

    public static UpdateSubscriptionRequest updateSubscription() throws IOException {
        String uri = uri("estimates", "update_subscription");
        return new UpdateSubscriptionRequest(Method.POST, uri);
    }

    public static RenewalEstimateRequest renewalEstimate(String id) throws IOException {
        String uri = uri("subscriptions", nullCheck(id), "renewal_estimate");
        return new RenewalEstimateRequest(Method.GET, uri);
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

        public CreateSubscriptionRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public CreateSubscriptionRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

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

        public CreateSubscriptionRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public CreateSubscriptionRequest customerTaxability(Taxability customerTaxability) {
            params.addOpt("customer[taxability]", customerTaxability);
            return this;
        }

        public CreateSubscriptionRequest customerEntityCode(EntityCode customerEntityCode) {
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


        public UpdateSubscriptionRequest prorate(Boolean prorate) {
            params.addOpt("prorate", prorate);
            return this;
        }


        public UpdateSubscriptionRequest endOfTerm(Boolean endOfTerm) {
            params.addOpt("end_of_term", endOfTerm);
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

        public UpdateSubscriptionRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public UpdateSubscriptionRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

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

        public UpdateSubscriptionRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        @Deprecated
        public UpdateSubscriptionRequest customerTaxability(Taxability customerTaxability) {
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


        @Override
        public Params params() {
            return params;
        }
    }

}
