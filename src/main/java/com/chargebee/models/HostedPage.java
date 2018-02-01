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
        MANAGE_PAYMENT_SOURCES,
        COLLECT_NOW,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum State {
        CREATED,
        REQUESTED,
        SUCCEEDED,
        CANCELLED,
        @Deprecated
        FAILED,
        ACKNOWLEDGED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    @Deprecated
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

    @Deprecated
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

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public JSONObject checkoutInfo() {
        return optJSONObject("checkout_info");
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

    @Deprecated
    public static ManagePaymentSourcesRequest managePaymentSources() throws IOException {
        String uri = uri("hosted_pages", "manage_payment_sources");
        return new ManagePaymentSourcesRequest(Method.POST, uri);
    }

    public static CollectNowRequest collectNow() throws IOException {
        String uri = uri("hosted_pages", "collect_now");
        return new CollectNowRequest(Method.POST, uri);
    }

    public static Request acknowledge(String id) throws IOException {
        String uri = uri("hosted_pages", nullCheck(id), "acknowledge");
        return new Request(Method.POST, uri);
    }

    public static Request retrieve(String id) throws IOException {
        String uri = uri("hosted_pages", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static HostedPageListRequest list() throws IOException {
        String uri = uri("hosted_pages");
        return new HostedPageListRequest(uri);
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


        public CheckoutNewRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }






        public CheckoutNewRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
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

        public CheckoutNewRequest customerTaxability(com.chargebee.models.enums.Taxability customerTaxability) {
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

        public CheckoutNewRequest subscriptionAutoCollection(com.chargebee.models.enums.AutoCollection subscriptionAutoCollection) {
            params.addOpt("subscription[auto_collection]", subscriptionAutoCollection);
            return this;
        }

        public CheckoutNewRequest subscriptionInvoiceNotes(String subscriptionInvoiceNotes) {
            params.addOpt("subscription[invoice_notes]", subscriptionInvoiceNotes);
            return this;
        }

        @Deprecated
        public CheckoutNewRequest cardGateway(com.chargebee.models.enums.Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public CheckoutNewRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
            return this;
        }

        public CheckoutNewRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public CheckoutNewRequest customerConsolidatedInvoicing(Boolean customerConsolidatedInvoicing) {
            params.addOpt("customer[consolidated_invoicing]", customerConsolidatedInvoicing);
            return this;
        }

        public CheckoutNewRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }

        public CheckoutNewRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }

        public CheckoutNewRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }

        public CheckoutNewRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }

        public CheckoutNewRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }

        public CheckoutNewRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public CheckoutNewRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public CheckoutNewRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public CheckoutNewRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public CheckoutNewRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public CheckoutNewRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }

        public CheckoutNewRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public CheckoutNewRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public CheckoutNewRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public CheckoutNewRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public CheckoutNewRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public CheckoutNewRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public CheckoutNewRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public CheckoutNewRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public CheckoutNewRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CheckoutNewRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CheckoutNewRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CheckoutNewRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CheckoutNewRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CheckoutNewRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public CheckoutNewRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CheckoutNewRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CheckoutNewRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
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


        public CheckoutExistingRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }


        public CheckoutExistingRequest reactivateFrom(Timestamp reactivateFrom) {
            params.addOpt("reactivate_from", reactivateFrom);
            return this;
        }




        public CheckoutExistingRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }


        public CheckoutExistingRequest reactivate(Boolean reactivate) {
            params.addOpt("reactivate", reactivate);
            return this;
        }


        public CheckoutExistingRequest forceTermReset(Boolean forceTermReset) {
            params.addOpt("force_term_reset", forceTermReset);
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

        public CheckoutExistingRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        @Deprecated
        public CheckoutExistingRequest cardGateway(com.chargebee.models.enums.Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public CheckoutExistingRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
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

        @Deprecated
        public UpdateCardRequest cardGateway(com.chargebee.models.enums.Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public UpdateCardRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
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

        @Deprecated
        public UpdatePaymentMethodRequest cardGateway(com.chargebee.models.enums.Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public UpdatePaymentMethodRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class ManagePaymentSourcesRequest extends Request<ManagePaymentSourcesRequest> {

        private ManagePaymentSourcesRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ManagePaymentSourcesRequest customerId(String customerId) {
            params.add("customer[id]", customerId);
            return this;
        }

        @Deprecated
        public ManagePaymentSourcesRequest cardGateway(com.chargebee.models.enums.Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public ManagePaymentSourcesRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class CollectNowRequest extends Request<CollectNowRequest> {

        private CollectNowRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CollectNowRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public CollectNowRequest customerId(String customerId) {
            params.add("customer[id]", customerId);
            return this;
        }

        @Deprecated
        public CollectNowRequest cardGateway(com.chargebee.models.enums.Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public CollectNowRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class HostedPageListRequest extends ListRequest<HostedPageListRequest> {

        private HostedPageListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<HostedPageListRequest> id() {
            return new StringFilter<HostedPageListRequest>("id",this).supportsMultiOperators(true);        
        }


        public EnumFilter<HostedPage.Type, HostedPageListRequest> type() {
            return new EnumFilter<HostedPage.Type, HostedPageListRequest>("type",this);        
        }


        public EnumFilter<HostedPage.State, HostedPageListRequest> state() {
            return new EnumFilter<HostedPage.State, HostedPageListRequest>("state",this);        
        }


        public TimestampFilter<HostedPageListRequest> updatedAt() {
            return new TimestampFilter<HostedPageListRequest>("updated_at",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
