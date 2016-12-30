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

public class HostedPage extends Resource<HostedPage> {

    public enum Type {
        CHECKOUT_NEW,
        CHECKOUT_EXISTING,
        @Deprecated
        UPDATE_CARD,
        UPDATE_PAYMENT_METHOD,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum State {
        CREATED,
        REQUESTED,
        SUCCEEDED,
        CANCELLED,
        FAILED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum FailureReason {
        CARD_ERROR,
        SERVER_ERROR,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public HostedPage(String jsonStr) {
        super(jsonStr);
    }

    public HostedPage(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return optString("id");
    }

    public Type type() {
        return optEnum("type", Type.class);
    }

    public String url() {
        return optString("url");
    }

    public State state() {
        return optEnum("state", State.class);
    }

    public FailureReason failureReason() {
        return optEnum("failure_reason", FailureReason.class);
    }

    public String passThruContent() {
        return optString("pass_thru_content");
    }

    public Boolean embed() {
        return reqBoolean("embed");
    }

    public Timestamp createdAt() {
        return optTimestamp("created_at");
    }

    public Timestamp expiresAt() {
        return optTimestamp("expires_at");
    }

    // Operations
    //===========

    public static CheckoutNewRequest checkoutNew() throws IOException {
        String uri = uri("hosted_pages", "checkout_new");
        return new CheckoutNewRequest(Method.POST, uri);
    }

    public static CheckoutExistingRequest checkoutExisting() throws IOException {
        String uri = uri("hosted_pages", "checkout_existing");
        return new CheckoutExistingRequest(Method.POST, uri);
    }

    @Deprecated
    public static UpdateCardRequest updateCard() throws IOException {
        String uri = uri("hosted_pages", "update_card");
        return new UpdateCardRequest(Method.POST, uri);
    }

    public static UpdatePaymentMethodRequest updatePaymentMethod() throws IOException {
        String uri = uri("hosted_pages", "update_payment_method");
        return new UpdatePaymentMethodRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) throws IOException {
        String uri = uri("hosted_pages", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static class Content extends ResultBase{

        public Content(JSONObject jsonObj) {
            super(jsonObj);
        }
    }

    public Content content(){
        if (optJSONObject("content") == null) {
            return null;
        }
        return new Content(optJSONObject("content"));
    }
    // Operation Request Classes
    //==========================

    public static class CheckoutNewRequest extends Request<CheckoutNewRequest> {

        private CheckoutNewRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CheckoutNewRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public CheckoutNewRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public CheckoutNewRequest cancelUrl(String cancelUrl) {
            params.addOpt("cancel_url", cancelUrl);
            return this;
        }


        public CheckoutNewRequest passThruContent(String passThruContent) {
            params.addOpt("pass_thru_content", passThruContent);
            return this;
        }


        public CheckoutNewRequest embed(Boolean embed) {
            params.addOpt("embed", embed);
            return this;
        }


        public CheckoutNewRequest iframeMessaging(Boolean iframeMessaging) {
            params.addOpt("iframe_messaging", iframeMessaging);
            return this;
        }


        public CheckoutNewRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription[id]", subscriptionId);
            return this;
        }

        public CheckoutNewRequest customerId(String customerId) {
            params.addOpt("customer[id]", customerId);
            return this;
        }

        public CheckoutNewRequest customerEmail(String customerEmail) {
            params.addOpt("customer[email]", customerEmail);
            return this;
        }

        public CheckoutNewRequest customerFirstName(String customerFirstName) {
            params.addOpt("customer[first_name]", customerFirstName);
            return this;
        }

        public CheckoutNewRequest customerLastName(String customerLastName) {
            params.addOpt("customer[last_name]", customerLastName);
            return this;
        }

        public CheckoutNewRequest customerCompany(String customerCompany) {
            params.addOpt("customer[company]", customerCompany);
            return this;
        }

        public CheckoutNewRequest customerTaxability(Taxability customerTaxability) {
            params.addOpt("customer[taxability]", customerTaxability);
            return this;
        }

        public CheckoutNewRequest customerLocale(String customerLocale) {
            params.addOpt("customer[locale]", customerLocale);
            return this;
        }

        public CheckoutNewRequest customerPhone(String customerPhone) {
            params.addOpt("customer[phone]", customerPhone);
            return this;
        }

        public CheckoutNewRequest subscriptionPlanId(String subscriptionPlanId) {
            params.add("subscription[plan_id]", subscriptionPlanId);
            return this;
        }

        public CheckoutNewRequest subscriptionPlanQuantity(Integer subscriptionPlanQuantity) {
            params.addOpt("subscription[plan_quantity]", subscriptionPlanQuantity);
            return this;
        }

        public CheckoutNewRequest subscriptionPlanUnitPrice(Integer subscriptionPlanUnitPrice) {
            params.addOpt("subscription[plan_unit_price]", subscriptionPlanUnitPrice);
            return this;
        }

        public CheckoutNewRequest subscriptionSetupFee(Integer subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public CheckoutNewRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public CheckoutNewRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        public CheckoutNewRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public CheckoutNewRequest subscriptionInvoiceNotes(String subscriptionInvoiceNotes) {
            params.addOpt("subscription[invoice_notes]", subscriptionInvoiceNotes);
            return this;
        }

        public CheckoutNewRequest cardGateway(Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        @Deprecated
        public CheckoutNewRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public CheckoutNewRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }

        public CheckoutNewRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }

        public CheckoutNewRequest addonUnitPrice(int index, Integer addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class CheckoutExistingRequest extends Request<CheckoutExistingRequest> {

        private CheckoutExistingRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CheckoutExistingRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public CheckoutExistingRequest replaceAddonList(Boolean replaceAddonList) {
            params.addOpt("replace_addon_list", replaceAddonList);
            return this;
        }


        public CheckoutExistingRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public CheckoutExistingRequest cancelUrl(String cancelUrl) {
            params.addOpt("cancel_url", cancelUrl);
            return this;
        }


        public CheckoutExistingRequest passThruContent(String passThruContent) {
            params.addOpt("pass_thru_content", passThruContent);
            return this;
        }


        public CheckoutExistingRequest embed(Boolean embed) {
            params.addOpt("embed", embed);
            return this;
        }


        public CheckoutExistingRequest iframeMessaging(Boolean iframeMessaging) {
            params.addOpt("iframe_messaging", iframeMessaging);
            return this;
        }


        public CheckoutExistingRequest subscriptionId(String subscriptionId) {
            params.add("subscription[id]", subscriptionId);
            return this;
        }

        public CheckoutExistingRequest subscriptionPlanId(String subscriptionPlanId) {
            params.addOpt("subscription[plan_id]", subscriptionPlanId);
            return this;
        }

        public CheckoutExistingRequest subscriptionPlanQuantity(Integer subscriptionPlanQuantity) {
            params.addOpt("subscription[plan_quantity]", subscriptionPlanQuantity);
            return this;
        }

        public CheckoutExistingRequest subscriptionPlanUnitPrice(Integer subscriptionPlanUnitPrice) {
            params.addOpt("subscription[plan_unit_price]", subscriptionPlanUnitPrice);
            return this;
        }

        public CheckoutExistingRequest subscriptionSetupFee(Integer subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public CheckoutExistingRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public CheckoutExistingRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        public CheckoutExistingRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public CheckoutExistingRequest subscriptionInvoiceNotes(String subscriptionInvoiceNotes) {
            params.addOpt("subscription[invoice_notes]", subscriptionInvoiceNotes);
            return this;
        }

        @Deprecated
        public CheckoutExistingRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public CheckoutExistingRequest cardGateway(Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public CheckoutExistingRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }

        public CheckoutExistingRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }

        public CheckoutExistingRequest addonUnitPrice(int index, Integer addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateCardRequest extends Request<UpdateCardRequest> {

        private UpdateCardRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateCardRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public UpdateCardRequest cancelUrl(String cancelUrl) {
            params.addOpt("cancel_url", cancelUrl);
            return this;
        }


        public UpdateCardRequest passThruContent(String passThruContent) {
            params.addOpt("pass_thru_content", passThruContent);
            return this;
        }


        public UpdateCardRequest embed(Boolean embed) {
            params.addOpt("embed", embed);
            return this;
        }


        public UpdateCardRequest iframeMessaging(Boolean iframeMessaging) {
            params.addOpt("iframe_messaging", iframeMessaging);
            return this;
        }


        public UpdateCardRequest customerId(String customerId) {
            params.add("customer[id]", customerId);
            return this;
        }

        @Deprecated
        public UpdateCardRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public UpdateCardRequest cardGateway(Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdatePaymentMethodRequest extends Request<UpdatePaymentMethodRequest> {

        private UpdatePaymentMethodRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdatePaymentMethodRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public UpdatePaymentMethodRequest cancelUrl(String cancelUrl) {
            params.addOpt("cancel_url", cancelUrl);
            return this;
        }


        public UpdatePaymentMethodRequest passThruContent(String passThruContent) {
            params.addOpt("pass_thru_content", passThruContent);
            return this;
        }


        public UpdatePaymentMethodRequest embed(Boolean embed) {
            params.addOpt("embed", embed);
            return this;
        }


        public UpdatePaymentMethodRequest iframeMessaging(Boolean iframeMessaging) {
            params.addOpt("iframe_messaging", iframeMessaging);
            return this;
        }


        public UpdatePaymentMethodRequest customerId(String customerId) {
            params.add("customer[id]", customerId);
            return this;
        }

        @Deprecated
        public UpdatePaymentMethodRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public UpdatePaymentMethodRequest cardGateway(Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

}
