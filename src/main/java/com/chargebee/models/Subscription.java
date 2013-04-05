package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class Subscription extends Resource<Subscription> {

    public enum Status {
        IN_TRIAL,
        ACTIVE,
        NON_RENEWING,
        CANCELLED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum CancelReason {
        NOT_PAID,
        NO_CARD,
        FRAUD_REVIEW_FAILED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class Addon extends Resource<Addon> {
        public Addon(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public Integer quantity() {
            return optInteger("quantity");
        }

    }

    public static class Coupon extends Resource<Coupon> {
        public Coupon(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String couponId() {
            return reqString("coupon_id");
        }

        public Timestamp applyTill() {
            return optTimestamp("apply_till");
        }

        public Integer appliedCount() {
            return reqInteger("applied_count");
        }

    }

    //Constructors
    //============

    public Subscription(String jsonStr) {
        super(jsonStr);
    }

    public Subscription(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String planId() {
        return reqString("plan_id");
    }

    public Integer planQuantity() {
        return reqInteger("plan_quantity");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp trialStart() {
        return optTimestamp("trial_start");
    }

    public Timestamp trialEnd() {
        return optTimestamp("trial_end");
    }

    public Timestamp currentTermStart() {
        return optTimestamp("current_term_start");
    }

    public Timestamp currentTermEnd() {
        return optTimestamp("current_term_end");
    }

    public Integer remainingBillingCycles() {
        return optInteger("remaining_billing_cycles");
    }

    public Timestamp createdAt() {
        return optTimestamp("created_at");
    }

    public Timestamp activatedAt() {
        return optTimestamp("activated_at");
    }

    public Timestamp cancelledAt() {
        return optTimestamp("cancelled_at");
    }

    public CancelReason cancelReason() {
        return optEnum("cancel_reason", CancelReason.class);
    }

    public Integer dueInvoicesCount() {
        return optInteger("due_invoices_count");
    }

    public Timestamp dueSince() {
        return optTimestamp("due_since");
    }

    public Integer totalDues() {
        return optInteger("total_dues");
    }

    public List<Subscription.Addon> addons() {
        return optList("addons", Subscription.Addon.class);
    }

    public String coupon() {
        return optString("coupon");
    }

    public List<Subscription.Coupon> coupons() {
        return optList("coupons", Subscription.Coupon.class);
    }

    // Operations
    //===========

    public static CreateRequest create() throws IOException {
        String url = url("subscriptions");
        return new CreateRequest(Method.POST, url);
    }

    public static ListRequest list() throws IOException {
        String url = url("subscriptions");
        return new ListRequest(url);
    }

    public static Request retrieve(String id) throws IOException {
        String url = url("subscriptions", nullCheck(id));
        return new Request(Method.GET, url);
    }

    public static UpdateRequest update(String id) throws IOException {
        String url = url("subscriptions", nullCheck(id));
        return new UpdateRequest(Method.POST, url);
    }

    public static CancelRequest cancel(String id) throws IOException {
        String url = url("subscriptions", nullCheck(id), "cancel");
        return new CancelRequest(Method.POST, url);
    }

    public static ReactivateRequest reactivate(String id) throws IOException {
        String url = url("subscriptions", nullCheck(id), "reactivate");
        return new ReactivateRequest(Method.POST, url);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request {

        private CreateRequest(Method httpMeth, String url) {
            super(httpMeth, url);
        }
    
        public CreateRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }

        public CreateRequest planId(String planId) {
            params.add("plan_id", planId);
            return this;
        }

        public CreateRequest planQuantity(Integer planQuantity) {
            params.addOpt("plan_quantity", planQuantity);
            return this;
        }

        public CreateRequest trialEnd(Timestamp trialEnd) {
            params.addOpt("trial_end", trialEnd);
            return this;
        }

        public CreateRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }

        public CreateRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }

        public CreateRequest customerEmail(String customerEmail) {
            params.addOpt("customer[email]", customerEmail);
            return this;
        }

        public CreateRequest customerFirstName(String customerFirstName) {
            params.addOpt("customer[first_name]", customerFirstName);
            return this;
        }

        public CreateRequest customerLastName(String customerLastName) {
            params.addOpt("customer[last_name]", customerLastName);
            return this;
        }

        public CreateRequest customerCompany(String customerCompany) {
            params.addOpt("customer[company]", customerCompany);
            return this;
        }

        public CreateRequest customerAutoCollection(AutoCollection customerAutoCollection) {
            params.addOpt("customer[auto_collection]", customerAutoCollection);
            return this;
        }

        public CreateRequest cardGateway(Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public CreateRequest cardTmpToken(String cardTmpToken) {
            params.addOpt("card[tmp_token]", cardTmpToken);
            return this;
        }

        public CreateRequest cardFirstName(String cardFirstName) {
            params.addOpt("card[first_name]", cardFirstName);
            return this;
        }

        public CreateRequest cardLastName(String cardLastName) {
            params.addOpt("card[last_name]", cardLastName);
            return this;
        }

        public CreateRequest cardNumber(String cardNumber) {
            params.addOpt("card[number]", cardNumber);
            return this;
        }

        public CreateRequest cardExpiryMonth(Integer cardExpiryMonth) {
            params.addOpt("card[expiry_month]", cardExpiryMonth);
            return this;
        }

        public CreateRequest cardExpiryYear(Integer cardExpiryYear) {
            params.addOpt("card[expiry_year]", cardExpiryYear);
            return this;
        }

        public CreateRequest cardCvv(String cardCvv) {
            params.addOpt("card[cvv]", cardCvv);
            return this;
        }

        public CreateRequest cardBillingAddr1(String cardBillingAddr1) {
            params.addOpt("card[billing_addr1]", cardBillingAddr1);
            return this;
        }

        public CreateRequest cardBillingAddr2(String cardBillingAddr2) {
            params.addOpt("card[billing_addr2]", cardBillingAddr2);
            return this;
        }

        public CreateRequest cardBillingCity(String cardBillingCity) {
            params.addOpt("card[billing_city]", cardBillingCity);
            return this;
        }

        public CreateRequest cardBillingState(String cardBillingState) {
            params.addOpt("card[billing_state]", cardBillingState);
            return this;
        }

        public CreateRequest cardBillingZip(String cardBillingZip) {
            params.addOpt("card[billing_zip]", cardBillingZip);
            return this;
        }

        public CreateRequest cardBillingCountry(String cardBillingCountry) {
            params.addOpt("card[billing_country]", cardBillingCountry);
            return this;
        }

        public CreateRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public CreateRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }

        public CreateRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateRequest extends Request {

        private UpdateRequest(Method httpMeth, String url) {
            super(httpMeth, url);
        }
    
        public UpdateRequest planId(String planId) {
            params.addOpt("plan_id", planId);
            return this;
        }

        public UpdateRequest planQuantity(Integer planQuantity) {
            params.addOpt("plan_quantity", planQuantity);
            return this;
        }

        public UpdateRequest trialEnd(Timestamp trialEnd) {
            params.addOpt("trial_end", trialEnd);
            return this;
        }

        public UpdateRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }

        public UpdateRequest replaceAddonList(Boolean replaceAddonList) {
            params.addOpt("replace_addon_list", replaceAddonList);
            return this;
        }

        public UpdateRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }

        public UpdateRequest prorate(Boolean prorate) {
            params.addOpt("prorate", prorate);
            return this;
        }

        public UpdateRequest endOfTerm(Boolean endOfTerm) {
            params.addOpt("end_of_term", endOfTerm);
            return this;
        }

        public UpdateRequest cardGateway(Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public UpdateRequest cardTmpToken(String cardTmpToken) {
            params.addOpt("card[tmp_token]", cardTmpToken);
            return this;
        }

        public UpdateRequest cardFirstName(String cardFirstName) {
            params.addOpt("card[first_name]", cardFirstName);
            return this;
        }

        public UpdateRequest cardLastName(String cardLastName) {
            params.addOpt("card[last_name]", cardLastName);
            return this;
        }

        public UpdateRequest cardNumber(String cardNumber) {
            params.addOpt("card[number]", cardNumber);
            return this;
        }

        public UpdateRequest cardExpiryMonth(Integer cardExpiryMonth) {
            params.addOpt("card[expiry_month]", cardExpiryMonth);
            return this;
        }

        public UpdateRequest cardExpiryYear(Integer cardExpiryYear) {
            params.addOpt("card[expiry_year]", cardExpiryYear);
            return this;
        }

        public UpdateRequest cardCvv(String cardCvv) {
            params.addOpt("card[cvv]", cardCvv);
            return this;
        }

        public UpdateRequest cardBillingAddr1(String cardBillingAddr1) {
            params.addOpt("card[billing_addr1]", cardBillingAddr1);
            return this;
        }

        public UpdateRequest cardBillingAddr2(String cardBillingAddr2) {
            params.addOpt("card[billing_addr2]", cardBillingAddr2);
            return this;
        }

        public UpdateRequest cardBillingCity(String cardBillingCity) {
            params.addOpt("card[billing_city]", cardBillingCity);
            return this;
        }

        public UpdateRequest cardBillingState(String cardBillingState) {
            params.addOpt("card[billing_state]", cardBillingState);
            return this;
        }

        public UpdateRequest cardBillingZip(String cardBillingZip) {
            params.addOpt("card[billing_zip]", cardBillingZip);
            return this;
        }

        public UpdateRequest cardBillingCountry(String cardBillingCountry) {
            params.addOpt("card[billing_country]", cardBillingCountry);
            return this;
        }

        public UpdateRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public UpdateRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }

        public UpdateRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class CancelRequest extends Request {

        private CancelRequest(Method httpMeth, String url) {
            super(httpMeth, url);
        }
    
        public CancelRequest endOfTerm(Boolean endOfTerm) {
            params.addOpt("end_of_term", endOfTerm);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class ReactivateRequest extends Request {

        private ReactivateRequest(Method httpMeth, String url) {
            super(httpMeth, url);
        }
    
        public ReactivateRequest trialEnd(Timestamp trialEnd) {
            params.addOpt("trial_end", trialEnd);
            return this;
        }

        @Deprecated
        public ReactivateRequest trialPeriodDays(Integer trialPeriodDays) {
            params.addOpt("trial_period_days", trialPeriodDays);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

}
