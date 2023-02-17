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
        EXTEND_SUBSCRIPTION,
        CHECKOUT_GIFT,
        CLAIM_GIFT,
        CHECKOUT_ONE_TIME,
        PRE_CANCEL,
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

    public String businessEntityId() {
        return optString("business_entity_id");
    }

    // Operations
    //===========

    public static CheckoutNewRequest checkoutNew() {
        String uri = uri("hosted_pages", "checkout_new");
        return new CheckoutNewRequest(Method.POST, uri);
    }

    public static CheckoutOneTimeRequest checkoutOneTime() {
        String uri = uri("hosted_pages", "checkout_one_time");
        return new CheckoutOneTimeRequest(Method.POST, uri);
    }

    public static CheckoutOneTimeForItemsRequest checkoutOneTimeForItems() {
        String uri = uri("hosted_pages", "checkout_one_time_for_items");
        return new CheckoutOneTimeForItemsRequest(Method.POST, uri);
    }

    public static CheckoutNewForItemsRequest checkoutNewForItems() {
        String uri = uri("hosted_pages", "checkout_new_for_items");
        return new CheckoutNewForItemsRequest(Method.POST, uri);
    }

    public static CheckoutExistingRequest checkoutExisting() {
        String uri = uri("hosted_pages", "checkout_existing");
        return new CheckoutExistingRequest(Method.POST, uri);
    }

    public static CheckoutExistingForItemsRequest checkoutExistingForItems() {
        String uri = uri("hosted_pages", "checkout_existing_for_items");
        return new CheckoutExistingForItemsRequest(Method.POST, uri);
    }

    @Deprecated
    public static UpdateCardRequest updateCard() {
        String uri = uri("hosted_pages", "update_card");
        return new UpdateCardRequest(Method.POST, uri);
    }

    public static UpdatePaymentMethodRequest updatePaymentMethod() {
        String uri = uri("hosted_pages", "update_payment_method");
        return new UpdatePaymentMethodRequest(Method.POST, uri);
    }

    public static ManagePaymentSourcesRequest managePaymentSources() {
        String uri = uri("hosted_pages", "manage_payment_sources");
        return new ManagePaymentSourcesRequest(Method.POST, uri);
    }

    public static CollectNowRequest collectNow() {
        String uri = uri("hosted_pages", "collect_now");
        return new CollectNowRequest(Method.POST, uri);
    }

    public static AcceptQuoteRequest acceptQuote() {
        String uri = uri("hosted_pages", "accept_quote");
        return new AcceptQuoteRequest(Method.POST, uri);
    }

    public static ExtendSubscriptionRequest extendSubscription() {
        String uri = uri("hosted_pages", "extend_subscription");
        return new ExtendSubscriptionRequest(Method.POST, uri);
    }

    public static CheckoutGiftRequest checkoutGift() {
        String uri = uri("hosted_pages", "checkout_gift");
        return new CheckoutGiftRequest(Method.POST, uri);
    }

    public static CheckoutGiftForItemsRequest checkoutGiftForItems() {
        String uri = uri("hosted_pages", "checkout_gift_for_items");
        return new CheckoutGiftForItemsRequest(Method.POST, uri);
    }

    public static ClaimGiftRequest claimGift() {
        String uri = uri("hosted_pages", "claim_gift");
        return new ClaimGiftRequest(Method.POST, uri);
    }

    public static RetrieveAgreementPdfRequest retrieveAgreementPdf() {
        String uri = uri("hosted_pages", "retrieve_agreement_pdf");
        return new RetrieveAgreementPdfRequest(Method.POST, uri);
    }

    public static Request acknowledge(String id) {
        String uri = uri("hosted_pages", nullCheck(id), "acknowledge");
        return new Request(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("hosted_pages", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static HostedPageListRequest list() {
        String uri = uri("hosted_pages");
        return new HostedPageListRequest(uri);
    }

    public static PreCancelRequest preCancel() {
        String uri = uri("hosted_pages", "pre_cancel");
        return new PreCancelRequest(Method.POST, uri);
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


        public CheckoutNewRequest mandatoryAddonsToRemove(List<String> mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public CheckoutNewRequest mandatoryAddonsToRemove(String... mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
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




        public CheckoutNewRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CheckoutNewRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
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


        public CheckoutNewRequest allowOfflinePaymentMethods(Boolean allowOfflinePaymentMethods) {
            params.addOpt("allow_offline_payment_methods", allowOfflinePaymentMethods);
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

        public CheckoutNewRequest subscriptionPlanQuantityInDecimal(String subscriptionPlanQuantityInDecimal) {
            params.addOpt("subscription[plan_quantity_in_decimal]", subscriptionPlanQuantityInDecimal);
            return this;
        }

        public CheckoutNewRequest subscriptionPlanUnitPrice(Long subscriptionPlanUnitPrice) {
            params.addOpt("subscription[plan_unit_price]", subscriptionPlanUnitPrice);
            return this;
        }

        public CheckoutNewRequest subscriptionPlanUnitPriceInDecimal(String subscriptionPlanUnitPriceInDecimal) {
            params.addOpt("subscription[plan_unit_price_in_decimal]", subscriptionPlanUnitPriceInDecimal);
            return this;
        }

        public CheckoutNewRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public CheckoutNewRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        public CheckoutNewRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        @Deprecated
        public CheckoutNewRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public CheckoutNewRequest subscriptionAutoCollection(com.chargebee.models.enums.AutoCollection subscriptionAutoCollection) {
            params.addOpt("subscription[auto_collection]", subscriptionAutoCollection);
            return this;
        }

        public CheckoutNewRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
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

        public CheckoutNewRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
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

        public CheckoutNewRequest subscriptionAffiliateToken(String subscriptionAffiliateToken) {
            params.addOpt("subscription[affiliate_token]", subscriptionAffiliateToken);
            return this;
        }

        public CheckoutNewRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public CheckoutNewRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public CheckoutNewRequest subscriptionContractTermBillingCycleOnRenewal(Integer subscriptionContractTermBillingCycleOnRenewal) {
            params.addOpt("subscription[contract_term_billing_cycle_on_renewal]", subscriptionContractTermBillingCycleOnRenewal);
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
        public CheckoutNewRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public CheckoutNewRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public CheckoutNewRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public CheckoutNewRequest addonBillingCycles(int index, Integer addonBillingCycles) {
            params.addOpt("addons[billing_cycles][" + index + "]", addonBillingCycles);
            return this;
        }
        public CheckoutNewRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public CheckoutNewRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public CheckoutNewRequest eventBasedAddonUnitPrice(int index, Long eventBasedAddonUnitPrice) {
            params.addOpt("event_based_addons[unit_price][" + index + "]", eventBasedAddonUnitPrice);
            return this;
        }
        public CheckoutNewRequest eventBasedAddonQuantityInDecimal(int index, String eventBasedAddonQuantityInDecimal) {
            params.addOpt("event_based_addons[quantity_in_decimal][" + index + "]", eventBasedAddonQuantityInDecimal);
            return this;
        }
        public CheckoutNewRequest eventBasedAddonUnitPriceInDecimal(int index, String eventBasedAddonUnitPriceInDecimal) {
            params.addOpt("event_based_addons[unit_price_in_decimal][" + index + "]", eventBasedAddonUnitPriceInDecimal);
            return this;
        }
        public CheckoutNewRequest eventBasedAddonServicePeriodInDays(int index, Integer eventBasedAddonServicePeriodInDays) {
            params.addOpt("event_based_addons[service_period_in_days][" + index + "]", eventBasedAddonServicePeriodInDays);
            return this;
        }
        public CheckoutNewRequest eventBasedAddonOnEvent(int index, com.chargebee.models.enums.OnEvent eventBasedAddonOnEvent) {
            params.addOpt("event_based_addons[on_event][" + index + "]", eventBasedAddonOnEvent);
            return this;
        }
        public CheckoutNewRequest eventBasedAddonChargeOnce(int index, Boolean eventBasedAddonChargeOnce) {
            params.addOpt("event_based_addons[charge_once][" + index + "]", eventBasedAddonChargeOnce);
            return this;
        }
        public CheckoutNewRequest eventBasedAddonChargeOn(int index, com.chargebee.models.enums.ChargeOn eventBasedAddonChargeOn) {
            params.addOpt("event_based_addons[charge_on][" + index + "]", eventBasedAddonChargeOn);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CheckoutOneTimeRequest extends Request<CheckoutOneTimeRequest> {

        private CheckoutOneTimeRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CheckoutOneTimeRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public CheckoutOneTimeRequest invoiceNote(String invoiceNote) {
            params.addOpt("invoice_note", invoiceNote);
            return this;
        }


        @Deprecated
        public CheckoutOneTimeRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public CheckoutOneTimeRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CheckoutOneTimeRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CheckoutOneTimeRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public CheckoutOneTimeRequest cancelUrl(String cancelUrl) {
            params.addOpt("cancel_url", cancelUrl);
            return this;
        }


        public CheckoutOneTimeRequest passThruContent(String passThruContent) {
            params.addOpt("pass_thru_content", passThruContent);
            return this;
        }


        public CheckoutOneTimeRequest embed(Boolean embed) {
            params.addOpt("embed", embed);
            return this;
        }


        public CheckoutOneTimeRequest iframeMessaging(Boolean iframeMessaging) {
            params.addOpt("iframe_messaging", iframeMessaging);
            return this;
        }


        public CheckoutOneTimeRequest customerId(String customerId) {
            params.addOpt("customer[id]", customerId);
            return this;
        }

        public CheckoutOneTimeRequest customerEmail(String customerEmail) {
            params.addOpt("customer[email]", customerEmail);
            return this;
        }

        public CheckoutOneTimeRequest customerFirstName(String customerFirstName) {
            params.addOpt("customer[first_name]", customerFirstName);
            return this;
        }

        public CheckoutOneTimeRequest customerLastName(String customerLastName) {
            params.addOpt("customer[last_name]", customerLastName);
            return this;
        }

        public CheckoutOneTimeRequest customerCompany(String customerCompany) {
            params.addOpt("customer[company]", customerCompany);
            return this;
        }

        public CheckoutOneTimeRequest customerTaxability(com.chargebee.models.enums.Taxability customerTaxability) {
            params.addOpt("customer[taxability]", customerTaxability);
            return this;
        }

        public CheckoutOneTimeRequest customerLocale(String customerLocale) {
            params.addOpt("customer[locale]", customerLocale);
            return this;
        }

        public CheckoutOneTimeRequest customerPhone(String customerPhone) {
            params.addOpt("customer[phone]", customerPhone);
            return this;
        }

        public CheckoutOneTimeRequest invoicePoNumber(String invoicePoNumber) {
            params.addOpt("invoice[po_number]", invoicePoNumber);
            return this;
        }

        @Deprecated
        public CheckoutOneTimeRequest cardGateway(com.chargebee.models.enums.Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public CheckoutOneTimeRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
            return this;
        }

        public CheckoutOneTimeRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public CheckoutOneTimeRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
            return this;
        }

        public CheckoutOneTimeRequest customerConsolidatedInvoicing(Boolean customerConsolidatedInvoicing) {
            params.addOpt("customer[consolidated_invoicing]", customerConsolidatedInvoicing);
            return this;
        }

        public CheckoutOneTimeRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }

        public CheckoutOneTimeRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }

        public CheckoutOneTimeRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }

        public CheckoutOneTimeRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }

        public CheckoutOneTimeRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }

        public CheckoutOneTimeRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public CheckoutOneTimeRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public CheckoutOneTimeRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public CheckoutOneTimeRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public CheckoutOneTimeRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public CheckoutOneTimeRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }

        public CheckoutOneTimeRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public CheckoutOneTimeRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public CheckoutOneTimeRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public CheckoutOneTimeRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public CheckoutOneTimeRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public CheckoutOneTimeRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public CheckoutOneTimeRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public CheckoutOneTimeRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public CheckoutOneTimeRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CheckoutOneTimeRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CheckoutOneTimeRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CheckoutOneTimeRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CheckoutOneTimeRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CheckoutOneTimeRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public CheckoutOneTimeRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CheckoutOneTimeRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CheckoutOneTimeRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CheckoutOneTimeRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public CheckoutOneTimeRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public CheckoutOneTimeRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public CheckoutOneTimeRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public CheckoutOneTimeRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public CheckoutOneTimeRequest addonDateFrom(int index, Timestamp addonDateFrom) {
            params.addOpt("addons[date_from][" + index + "]", addonDateFrom);
            return this;
        }
        public CheckoutOneTimeRequest addonDateTo(int index, Timestamp addonDateTo) {
            params.addOpt("addons[date_to][" + index + "]", addonDateTo);
            return this;
        }
        public CheckoutOneTimeRequest chargeAmount(int index, Long chargeAmount) {
            params.addOpt("charges[amount][" + index + "]", chargeAmount);
            return this;
        }
        public CheckoutOneTimeRequest chargeAmountInDecimal(int index, String chargeAmountInDecimal) {
            params.addOpt("charges[amount_in_decimal][" + index + "]", chargeAmountInDecimal);
            return this;
        }
        public CheckoutOneTimeRequest chargeDescription(int index, String chargeDescription) {
            params.addOpt("charges[description][" + index + "]", chargeDescription);
            return this;
        }
        public CheckoutOneTimeRequest chargeTaxable(int index, Boolean chargeTaxable) {
            params.addOpt("charges[taxable][" + index + "]", chargeTaxable);
            return this;
        }
        public CheckoutOneTimeRequest chargeTaxProfileId(int index, String chargeTaxProfileId) {
            params.addOpt("charges[tax_profile_id][" + index + "]", chargeTaxProfileId);
            return this;
        }
        public CheckoutOneTimeRequest chargeAvalaraTaxCode(int index, String chargeAvalaraTaxCode) {
            params.addOpt("charges[avalara_tax_code][" + index + "]", chargeAvalaraTaxCode);
            return this;
        }
        public CheckoutOneTimeRequest chargeHsnCode(int index, String chargeHsnCode) {
            params.addOpt("charges[hsn_code][" + index + "]", chargeHsnCode);
            return this;
        }
        public CheckoutOneTimeRequest chargeTaxjarProductCode(int index, String chargeTaxjarProductCode) {
            params.addOpt("charges[taxjar_product_code][" + index + "]", chargeTaxjarProductCode);
            return this;
        }
        public CheckoutOneTimeRequest chargeAvalaraSaleType(int index, com.chargebee.models.enums.AvalaraSaleType chargeAvalaraSaleType) {
            params.addOpt("charges[avalara_sale_type][" + index + "]", chargeAvalaraSaleType);
            return this;
        }
        public CheckoutOneTimeRequest chargeAvalaraTransactionType(int index, Integer chargeAvalaraTransactionType) {
            params.addOpt("charges[avalara_transaction_type][" + index + "]", chargeAvalaraTransactionType);
            return this;
        }
        public CheckoutOneTimeRequest chargeAvalaraServiceType(int index, Integer chargeAvalaraServiceType) {
            params.addOpt("charges[avalara_service_type][" + index + "]", chargeAvalaraServiceType);
            return this;
        }
        public CheckoutOneTimeRequest chargeDateFrom(int index, Timestamp chargeDateFrom) {
            params.addOpt("charges[date_from][" + index + "]", chargeDateFrom);
            return this;
        }
        public CheckoutOneTimeRequest chargeDateTo(int index, Timestamp chargeDateTo) {
            params.addOpt("charges[date_to][" + index + "]", chargeDateTo);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CheckoutOneTimeForItemsRequest extends Request<CheckoutOneTimeForItemsRequest> {

        private CheckoutOneTimeForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CheckoutOneTimeForItemsRequest businessEntityId(String businessEntityId) {
            params.addOpt("business_entity_id", businessEntityId);
            return this;
        }


        public CheckoutOneTimeForItemsRequest layout(com.chargebee.models.enums.Layout layout) {
            params.addOpt("layout", layout);
            return this;
        }


        public CheckoutOneTimeForItemsRequest invoiceNote(String invoiceNote) {
            params.addOpt("invoice_note", invoiceNote);
            return this;
        }


        @Deprecated
        public CheckoutOneTimeForItemsRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public CheckoutOneTimeForItemsRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CheckoutOneTimeForItemsRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CheckoutOneTimeForItemsRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public CheckoutOneTimeForItemsRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public CheckoutOneTimeForItemsRequest cancelUrl(String cancelUrl) {
            params.addOpt("cancel_url", cancelUrl);
            return this;
        }


        public CheckoutOneTimeForItemsRequest passThruContent(String passThruContent) {
            params.addOpt("pass_thru_content", passThruContent);
            return this;
        }


        public CheckoutOneTimeForItemsRequest customerId(String customerId) {
            params.addOpt("customer[id]", customerId);
            return this;
        }

        public CheckoutOneTimeForItemsRequest customerEmail(String customerEmail) {
            params.addOpt("customer[email]", customerEmail);
            return this;
        }

        public CheckoutOneTimeForItemsRequest customerFirstName(String customerFirstName) {
            params.addOpt("customer[first_name]", customerFirstName);
            return this;
        }

        public CheckoutOneTimeForItemsRequest customerLastName(String customerLastName) {
            params.addOpt("customer[last_name]", customerLastName);
            return this;
        }

        public CheckoutOneTimeForItemsRequest customerCompany(String customerCompany) {
            params.addOpt("customer[company]", customerCompany);
            return this;
        }

        public CheckoutOneTimeForItemsRequest customerTaxability(com.chargebee.models.enums.Taxability customerTaxability) {
            params.addOpt("customer[taxability]", customerTaxability);
            return this;
        }

        public CheckoutOneTimeForItemsRequest customerLocale(String customerLocale) {
            params.addOpt("customer[locale]", customerLocale);
            return this;
        }

        public CheckoutOneTimeForItemsRequest customerPhone(String customerPhone) {
            params.addOpt("customer[phone]", customerPhone);
            return this;
        }

        public CheckoutOneTimeForItemsRequest invoicePoNumber(String invoicePoNumber) {
            params.addOpt("invoice[po_number]", invoicePoNumber);
            return this;
        }

        @Deprecated
        public CheckoutOneTimeForItemsRequest cardGateway(com.chargebee.models.enums.Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public CheckoutOneTimeForItemsRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
            return this;
        }

        public CheckoutOneTimeForItemsRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public CheckoutOneTimeForItemsRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
            return this;
        }

        public CheckoutOneTimeForItemsRequest customerIsEinvoiceEnabled(Boolean customerIsEinvoiceEnabled) {
            params.addOpt("customer[is_einvoice_enabled]", customerIsEinvoiceEnabled);
            return this;
        }

        public CheckoutOneTimeForItemsRequest customerEntityIdentifierScheme(String customerEntityIdentifierScheme) {
            params.addOpt("customer[entity_identifier_scheme]", customerEntityIdentifierScheme);
            return this;
        }

        public CheckoutOneTimeForItemsRequest customerEntityIdentifierStandard(String customerEntityIdentifierStandard) {
            params.addOpt("customer[entity_identifier_standard]", customerEntityIdentifierStandard);
            return this;
        }

        public CheckoutOneTimeForItemsRequest customerConsolidatedInvoicing(Boolean customerConsolidatedInvoicing) {
            params.addOpt("customer[consolidated_invoicing]", customerConsolidatedInvoicing);
            return this;
        }

        public CheckoutOneTimeForItemsRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }

        public CheckoutOneTimeForItemsRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }

        public CheckoutOneTimeForItemsRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }

        public CheckoutOneTimeForItemsRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }

        public CheckoutOneTimeForItemsRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }

        public CheckoutOneTimeForItemsRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public CheckoutOneTimeForItemsRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public CheckoutOneTimeForItemsRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public CheckoutOneTimeForItemsRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public CheckoutOneTimeForItemsRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public CheckoutOneTimeForItemsRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }

        public CheckoutOneTimeForItemsRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public CheckoutOneTimeForItemsRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public CheckoutOneTimeForItemsRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public CheckoutOneTimeForItemsRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public CheckoutOneTimeForItemsRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public CheckoutOneTimeForItemsRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public CheckoutOneTimeForItemsRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public CheckoutOneTimeForItemsRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public CheckoutOneTimeForItemsRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CheckoutOneTimeForItemsRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CheckoutOneTimeForItemsRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CheckoutOneTimeForItemsRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CheckoutOneTimeForItemsRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CheckoutOneTimeForItemsRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public CheckoutOneTimeForItemsRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CheckoutOneTimeForItemsRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CheckoutOneTimeForItemsRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CheckoutOneTimeForItemsRequest itemPriceItemPriceId(int index, String itemPriceItemPriceId) {
            params.addOpt("item_prices[item_price_id][" + index + "]", itemPriceItemPriceId);
            return this;
        }
        public CheckoutOneTimeForItemsRequest itemPriceQuantity(int index, Integer itemPriceQuantity) {
            params.addOpt("item_prices[quantity][" + index + "]", itemPriceQuantity);
            return this;
        }
        public CheckoutOneTimeForItemsRequest itemPriceQuantityInDecimal(int index, String itemPriceQuantityInDecimal) {
            params.addOpt("item_prices[quantity_in_decimal][" + index + "]", itemPriceQuantityInDecimal);
            return this;
        }
        public CheckoutOneTimeForItemsRequest itemPriceUnitPrice(int index, Long itemPriceUnitPrice) {
            params.addOpt("item_prices[unit_price][" + index + "]", itemPriceUnitPrice);
            return this;
        }
        public CheckoutOneTimeForItemsRequest itemPriceUnitPriceInDecimal(int index, String itemPriceUnitPriceInDecimal) {
            params.addOpt("item_prices[unit_price_in_decimal][" + index + "]", itemPriceUnitPriceInDecimal);
            return this;
        }
        public CheckoutOneTimeForItemsRequest itemPriceDateFrom(int index, Timestamp itemPriceDateFrom) {
            params.addOpt("item_prices[date_from][" + index + "]", itemPriceDateFrom);
            return this;
        }
        public CheckoutOneTimeForItemsRequest itemPriceDateTo(int index, Timestamp itemPriceDateTo) {
            params.addOpt("item_prices[date_to][" + index + "]", itemPriceDateTo);
            return this;
        }
        public CheckoutOneTimeForItemsRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public CheckoutOneTimeForItemsRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public CheckoutOneTimeForItemsRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public CheckoutOneTimeForItemsRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public CheckoutOneTimeForItemsRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public CheckoutOneTimeForItemsRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public CheckoutOneTimeForItemsRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            return this;
        }
        public CheckoutOneTimeForItemsRequest chargeAmount(int index, Long chargeAmount) {
            params.addOpt("charges[amount][" + index + "]", chargeAmount);
            return this;
        }
        public CheckoutOneTimeForItemsRequest chargeAmountInDecimal(int index, String chargeAmountInDecimal) {
            params.addOpt("charges[amount_in_decimal][" + index + "]", chargeAmountInDecimal);
            return this;
        }
        public CheckoutOneTimeForItemsRequest chargeDescription(int index, String chargeDescription) {
            params.addOpt("charges[description][" + index + "]", chargeDescription);
            return this;
        }
        public CheckoutOneTimeForItemsRequest chargeTaxable(int index, Boolean chargeTaxable) {
            params.addOpt("charges[taxable][" + index + "]", chargeTaxable);
            return this;
        }
        public CheckoutOneTimeForItemsRequest chargeTaxProfileId(int index, String chargeTaxProfileId) {
            params.addOpt("charges[tax_profile_id][" + index + "]", chargeTaxProfileId);
            return this;
        }
        public CheckoutOneTimeForItemsRequest chargeAvalaraTaxCode(int index, String chargeAvalaraTaxCode) {
            params.addOpt("charges[avalara_tax_code][" + index + "]", chargeAvalaraTaxCode);
            return this;
        }
        public CheckoutOneTimeForItemsRequest chargeHsnCode(int index, String chargeHsnCode) {
            params.addOpt("charges[hsn_code][" + index + "]", chargeHsnCode);
            return this;
        }
        public CheckoutOneTimeForItemsRequest chargeTaxjarProductCode(int index, String chargeTaxjarProductCode) {
            params.addOpt("charges[taxjar_product_code][" + index + "]", chargeTaxjarProductCode);
            return this;
        }
        public CheckoutOneTimeForItemsRequest chargeAvalaraSaleType(int index, com.chargebee.models.enums.AvalaraSaleType chargeAvalaraSaleType) {
            params.addOpt("charges[avalara_sale_type][" + index + "]", chargeAvalaraSaleType);
            return this;
        }
        public CheckoutOneTimeForItemsRequest chargeAvalaraTransactionType(int index, Integer chargeAvalaraTransactionType) {
            params.addOpt("charges[avalara_transaction_type][" + index + "]", chargeAvalaraTransactionType);
            return this;
        }
        public CheckoutOneTimeForItemsRequest chargeAvalaraServiceType(int index, Integer chargeAvalaraServiceType) {
            params.addOpt("charges[avalara_service_type][" + index + "]", chargeAvalaraServiceType);
            return this;
        }
        public CheckoutOneTimeForItemsRequest chargeDateFrom(int index, Timestamp chargeDateFrom) {
            params.addOpt("charges[date_from][" + index + "]", chargeDateFrom);
            return this;
        }
        public CheckoutOneTimeForItemsRequest chargeDateTo(int index, Timestamp chargeDateTo) {
            params.addOpt("charges[date_to][" + index + "]", chargeDateTo);
            return this;
        }
        public CheckoutOneTimeForItemsRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public CheckoutOneTimeForItemsRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public CheckoutOneTimeForItemsRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.add("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public CheckoutOneTimeForItemsRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        public CheckoutOneTimeForItemsRequest entityIdentifierId(int index, String entityIdentifierId) {
            params.addOpt("entity_identifiers[id][" + index + "]", entityIdentifierId);
            return this;
        }
        public CheckoutOneTimeForItemsRequest entityIdentifierScheme(int index, String entityIdentifierScheme) {
            params.addOpt("entity_identifiers[scheme][" + index + "]", entityIdentifierScheme);
            return this;
        }
        public CheckoutOneTimeForItemsRequest entityIdentifierValue(int index, String entityIdentifierValue) {
            params.addOpt("entity_identifiers[value][" + index + "]", entityIdentifierValue);
            return this;
        }
        public CheckoutOneTimeForItemsRequest entityIdentifierOperation(int index, com.chargebee.models.enums.Operation entityIdentifierOperation) {
            params.addOpt("entity_identifiers[operation][" + index + "]", entityIdentifierOperation);
            return this;
        }
        public CheckoutOneTimeForItemsRequest entityIdentifierStandard(int index, String entityIdentifierStandard) {
            params.addOpt("entity_identifiers[standard][" + index + "]", entityIdentifierStandard);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CheckoutNewForItemsRequest extends Request<CheckoutNewForItemsRequest> {

        private CheckoutNewForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CheckoutNewForItemsRequest layout(com.chargebee.models.enums.Layout layout) {
            params.addOpt("layout", layout);
            return this;
        }


        public CheckoutNewForItemsRequest businessEntityId(String businessEntityId) {
            params.addOpt("business_entity_id", businessEntityId);
            return this;
        }


        public CheckoutNewForItemsRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public CheckoutNewForItemsRequest mandatoryItemsToRemove(List<String> mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public CheckoutNewForItemsRequest mandatoryItemsToRemove(String... mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public CheckoutNewForItemsRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }






        public CheckoutNewForItemsRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }




        public CheckoutNewForItemsRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CheckoutNewForItemsRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CheckoutNewForItemsRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public CheckoutNewForItemsRequest cancelUrl(String cancelUrl) {
            params.addOpt("cancel_url", cancelUrl);
            return this;
        }


        public CheckoutNewForItemsRequest passThruContent(String passThruContent) {
            params.addOpt("pass_thru_content", passThruContent);
            return this;
        }


        public CheckoutNewForItemsRequest allowOfflinePaymentMethods(Boolean allowOfflinePaymentMethods) {
            params.addOpt("allow_offline_payment_methods", allowOfflinePaymentMethods);
            return this;
        }




        public CheckoutNewForItemsRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription[id]", subscriptionId);
            return this;
        }

        public CheckoutNewForItemsRequest customerId(String customerId) {
            params.addOpt("customer[id]", customerId);
            return this;
        }

        public CheckoutNewForItemsRequest customerEmail(String customerEmail) {
            params.addOpt("customer[email]", customerEmail);
            return this;
        }

        public CheckoutNewForItemsRequest customerFirstName(String customerFirstName) {
            params.addOpt("customer[first_name]", customerFirstName);
            return this;
        }

        public CheckoutNewForItemsRequest customerLastName(String customerLastName) {
            params.addOpt("customer[last_name]", customerLastName);
            return this;
        }

        public CheckoutNewForItemsRequest customerCompany(String customerCompany) {
            params.addOpt("customer[company]", customerCompany);
            return this;
        }

        public CheckoutNewForItemsRequest customerTaxability(com.chargebee.models.enums.Taxability customerTaxability) {
            params.addOpt("customer[taxability]", customerTaxability);
            return this;
        }

        public CheckoutNewForItemsRequest customerLocale(String customerLocale) {
            params.addOpt("customer[locale]", customerLocale);
            return this;
        }

        public CheckoutNewForItemsRequest customerPhone(String customerPhone) {
            params.addOpt("customer[phone]", customerPhone);
            return this;
        }

        public CheckoutNewForItemsRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        @Deprecated
        public CheckoutNewForItemsRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public CheckoutNewForItemsRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public CheckoutNewForItemsRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public CheckoutNewForItemsRequest subscriptionAutoCollection(com.chargebee.models.enums.AutoCollection subscriptionAutoCollection) {
            params.addOpt("subscription[auto_collection]", subscriptionAutoCollection);
            return this;
        }

        public CheckoutNewForItemsRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
            return this;
        }

        public CheckoutNewForItemsRequest subscriptionInvoiceNotes(String subscriptionInvoiceNotes) {
            params.addOpt("subscription[invoice_notes]", subscriptionInvoiceNotes);
            return this;
        }

        @Deprecated
        public CheckoutNewForItemsRequest cardGateway(com.chargebee.models.enums.Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public CheckoutNewForItemsRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
            return this;
        }

        public CheckoutNewForItemsRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public CheckoutNewForItemsRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
            return this;
        }

        public CheckoutNewForItemsRequest customerIsEinvoiceEnabled(Boolean customerIsEinvoiceEnabled) {
            params.addOpt("customer[is_einvoice_enabled]", customerIsEinvoiceEnabled);
            return this;
        }

        public CheckoutNewForItemsRequest customerEntityIdentifierScheme(String customerEntityIdentifierScheme) {
            params.addOpt("customer[entity_identifier_scheme]", customerEntityIdentifierScheme);
            return this;
        }

        public CheckoutNewForItemsRequest customerEntityIdentifierStandard(String customerEntityIdentifierStandard) {
            params.addOpt("customer[entity_identifier_standard]", customerEntityIdentifierStandard);
            return this;
        }

        public CheckoutNewForItemsRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }

        public CheckoutNewForItemsRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }

        public CheckoutNewForItemsRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }

        public CheckoutNewForItemsRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }

        public CheckoutNewForItemsRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }

        public CheckoutNewForItemsRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public CheckoutNewForItemsRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public CheckoutNewForItemsRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public CheckoutNewForItemsRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public CheckoutNewForItemsRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public CheckoutNewForItemsRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }

        public CheckoutNewForItemsRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public CheckoutNewForItemsRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public CheckoutNewForItemsRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public CheckoutNewForItemsRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public CheckoutNewForItemsRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public CheckoutNewForItemsRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public CheckoutNewForItemsRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public CheckoutNewForItemsRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public CheckoutNewForItemsRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CheckoutNewForItemsRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CheckoutNewForItemsRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CheckoutNewForItemsRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CheckoutNewForItemsRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CheckoutNewForItemsRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public CheckoutNewForItemsRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CheckoutNewForItemsRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CheckoutNewForItemsRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CheckoutNewForItemsRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public CheckoutNewForItemsRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public CheckoutNewForItemsRequest subscriptionContractTermBillingCycleOnRenewal(Integer subscriptionContractTermBillingCycleOnRenewal) {
            params.addOpt("subscription[contract_term_billing_cycle_on_renewal]", subscriptionContractTermBillingCycleOnRenewal);
            return this;
        }

        public CheckoutNewForItemsRequest subscriptionAffiliateToken(String subscriptionAffiliateToken) {
            params.addOpt("subscription[affiliate_token]", subscriptionAffiliateToken);
            return this;
        }

        public CheckoutNewForItemsRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.add("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public CheckoutNewForItemsRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public CheckoutNewForItemsRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        public CheckoutNewForItemsRequest subscriptionItemUnitPrice(int index, Long subscriptionItemUnitPrice) {
            params.addOpt("subscription_items[unit_price][" + index + "]", subscriptionItemUnitPrice);
            return this;
        }
        public CheckoutNewForItemsRequest subscriptionItemUnitPriceInDecimal(int index, String subscriptionItemUnitPriceInDecimal) {
            params.addOpt("subscription_items[unit_price_in_decimal][" + index + "]", subscriptionItemUnitPriceInDecimal);
            return this;
        }
        public CheckoutNewForItemsRequest subscriptionItemBillingCycles(int index, Integer subscriptionItemBillingCycles) {
            params.addOpt("subscription_items[billing_cycles][" + index + "]", subscriptionItemBillingCycles);
            return this;
        }
        public CheckoutNewForItemsRequest subscriptionItemTrialEnd(int index, Timestamp subscriptionItemTrialEnd) {
            params.addOpt("subscription_items[trial_end][" + index + "]", subscriptionItemTrialEnd);
            return this;
        }
        public CheckoutNewForItemsRequest subscriptionItemServicePeriodDays(int index, Integer subscriptionItemServicePeriodDays) {
            params.addOpt("subscription_items[service_period_days][" + index + "]", subscriptionItemServicePeriodDays);
            return this;
        }
        public CheckoutNewForItemsRequest subscriptionItemChargeOnEvent(int index, com.chargebee.models.enums.ChargeOnEvent subscriptionItemChargeOnEvent) {
            params.addOpt("subscription_items[charge_on_event][" + index + "]", subscriptionItemChargeOnEvent);
            return this;
        }
        public CheckoutNewForItemsRequest subscriptionItemChargeOnce(int index, Boolean subscriptionItemChargeOnce) {
            params.addOpt("subscription_items[charge_once][" + index + "]", subscriptionItemChargeOnce);
            return this;
        }
        @Deprecated
        public CheckoutNewForItemsRequest subscriptionItemItemType(int index, com.chargebee.models.enums.ItemType subscriptionItemItemType) {
            params.addOpt("subscription_items[item_type][" + index + "]", subscriptionItemItemType);
            return this;
        }
        public CheckoutNewForItemsRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.add("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public CheckoutNewForItemsRequest discountDurationType(int index, com.chargebee.models.enums.DurationType discountDurationType) {
            params.add("discounts[duration_type][" + index + "]", discountDurationType);
            return this;
        }
        public CheckoutNewForItemsRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public CheckoutNewForItemsRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public CheckoutNewForItemsRequest discountPeriod(int index, Integer discountPeriod) {
            params.addOpt("discounts[period][" + index + "]", discountPeriod);
            return this;
        }
        public CheckoutNewForItemsRequest discountPeriodUnit(int index, com.chargebee.models.enums.PeriodUnit discountPeriodUnit) {
            params.addOpt("discounts[period_unit][" + index + "]", discountPeriodUnit);
            return this;
        }
        public CheckoutNewForItemsRequest discountIncludedInMrr(int index, Boolean discountIncludedInMrr) {
            params.addOpt("discounts[included_in_mrr][" + index + "]", discountIncludedInMrr);
            return this;
        }
        public CheckoutNewForItemsRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        public CheckoutNewForItemsRequest subscriptionItemChargeOnOption(int index, com.chargebee.models.enums.ChargeOnOption subscriptionItemChargeOnOption) {
            params.addOpt("subscription_items[charge_on_option][" + index + "]", subscriptionItemChargeOnOption);
            return this;
        }
        public CheckoutNewForItemsRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public CheckoutNewForItemsRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public CheckoutNewForItemsRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public CheckoutNewForItemsRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public CheckoutNewForItemsRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public CheckoutNewForItemsRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public CheckoutNewForItemsRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            return this;
        }
        public CheckoutNewForItemsRequest entityIdentifierId(int index, String entityIdentifierId) {
            params.addOpt("entity_identifiers[id][" + index + "]", entityIdentifierId);
            return this;
        }
        public CheckoutNewForItemsRequest entityIdentifierScheme(int index, String entityIdentifierScheme) {
            params.addOpt("entity_identifiers[scheme][" + index + "]", entityIdentifierScheme);
            return this;
        }
        public CheckoutNewForItemsRequest entityIdentifierValue(int index, String entityIdentifierValue) {
            params.addOpt("entity_identifiers[value][" + index + "]", entityIdentifierValue);
            return this;
        }
        public CheckoutNewForItemsRequest entityIdentifierOperation(int index, com.chargebee.models.enums.Operation entityIdentifierOperation) {
            params.addOpt("entity_identifiers[operation][" + index + "]", entityIdentifierOperation);
            return this;
        }
        public CheckoutNewForItemsRequest entityIdentifierStandard(int index, String entityIdentifierStandard) {
            params.addOpt("entity_identifiers[standard][" + index + "]", entityIdentifierStandard);
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
    
        public CheckoutExistingRequest replaceAddonList(Boolean replaceAddonList) {
            params.addOpt("replace_addon_list", replaceAddonList);
            return this;
        }


        public CheckoutExistingRequest mandatoryAddonsToRemove(List<String> mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public CheckoutExistingRequest mandatoryAddonsToRemove(String... mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public CheckoutExistingRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public CheckoutExistingRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
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


        public CheckoutExistingRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CheckoutExistingRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
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


        public CheckoutExistingRequest allowOfflinePaymentMethods(Boolean allowOfflinePaymentMethods) {
            params.addOpt("allow_offline_payment_methods", allowOfflinePaymentMethods);
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

        public CheckoutExistingRequest subscriptionPlanUnitPrice(Long subscriptionPlanUnitPrice) {
            params.addOpt("subscription[plan_unit_price]", subscriptionPlanUnitPrice);
            return this;
        }

        public CheckoutExistingRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public CheckoutExistingRequest subscriptionPlanQuantityInDecimal(String subscriptionPlanQuantityInDecimal) {
            params.addOpt("subscription[plan_quantity_in_decimal]", subscriptionPlanQuantityInDecimal);
            return this;
        }

        public CheckoutExistingRequest subscriptionPlanUnitPriceInDecimal(String subscriptionPlanUnitPriceInDecimal) {
            params.addOpt("subscription[plan_unit_price_in_decimal]", subscriptionPlanUnitPriceInDecimal);
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

        @Deprecated
        public CheckoutExistingRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public CheckoutExistingRequest subscriptionAutoCollection(com.chargebee.models.enums.AutoCollection subscriptionAutoCollection) {
            params.addOpt("subscription[auto_collection]", subscriptionAutoCollection);
            return this;
        }

        public CheckoutExistingRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
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

        public CheckoutExistingRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
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

        public CheckoutExistingRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public CheckoutExistingRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public CheckoutExistingRequest subscriptionContractTermBillingCycleOnRenewal(Integer subscriptionContractTermBillingCycleOnRenewal) {
            params.addOpt("subscription[contract_term_billing_cycle_on_renewal]", subscriptionContractTermBillingCycleOnRenewal);
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
        public CheckoutExistingRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public CheckoutExistingRequest addonBillingCycles(int index, Integer addonBillingCycles) {
            params.addOpt("addons[billing_cycles][" + index + "]", addonBillingCycles);
            return this;
        }
        public CheckoutExistingRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public CheckoutExistingRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public CheckoutExistingRequest eventBasedAddonUnitPrice(int index, Long eventBasedAddonUnitPrice) {
            params.addOpt("event_based_addons[unit_price][" + index + "]", eventBasedAddonUnitPrice);
            return this;
        }
        public CheckoutExistingRequest eventBasedAddonServicePeriodInDays(int index, Integer eventBasedAddonServicePeriodInDays) {
            params.addOpt("event_based_addons[service_period_in_days][" + index + "]", eventBasedAddonServicePeriodInDays);
            return this;
        }
        public CheckoutExistingRequest eventBasedAddonChargeOn(int index, com.chargebee.models.enums.ChargeOn eventBasedAddonChargeOn) {
            params.addOpt("event_based_addons[charge_on][" + index + "]", eventBasedAddonChargeOn);
            return this;
        }
        public CheckoutExistingRequest eventBasedAddonOnEvent(int index, com.chargebee.models.enums.OnEvent eventBasedAddonOnEvent) {
            params.addOpt("event_based_addons[on_event][" + index + "]", eventBasedAddonOnEvent);
            return this;
        }
        public CheckoutExistingRequest eventBasedAddonChargeOnce(int index, Boolean eventBasedAddonChargeOnce) {
            params.addOpt("event_based_addons[charge_once][" + index + "]", eventBasedAddonChargeOnce);
            return this;
        }
        public CheckoutExistingRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public CheckoutExistingRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public CheckoutExistingRequest eventBasedAddonQuantityInDecimal(int index, String eventBasedAddonQuantityInDecimal) {
            params.addOpt("event_based_addons[quantity_in_decimal][" + index + "]", eventBasedAddonQuantityInDecimal);
            return this;
        }
        public CheckoutExistingRequest eventBasedAddonUnitPriceInDecimal(int index, String eventBasedAddonUnitPriceInDecimal) {
            params.addOpt("event_based_addons[unit_price_in_decimal][" + index + "]", eventBasedAddonUnitPriceInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CheckoutExistingForItemsRequest extends Request<CheckoutExistingForItemsRequest> {

        private CheckoutExistingForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CheckoutExistingForItemsRequest layout(com.chargebee.models.enums.Layout layout) {
            params.addOpt("layout", layout);
            return this;
        }


        public CheckoutExistingForItemsRequest mandatoryItemsToRemove(List<String> mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public CheckoutExistingForItemsRequest mandatoryItemsToRemove(String... mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public CheckoutExistingForItemsRequest replaceItemsList(Boolean replaceItemsList) {
            params.addOpt("replace_items_list", replaceItemsList);
            return this;
        }


        public CheckoutExistingForItemsRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public CheckoutExistingForItemsRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public CheckoutExistingForItemsRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }




        public CheckoutExistingForItemsRequest reactivateFrom(Timestamp reactivateFrom) {
            params.addOpt("reactivate_from", reactivateFrom);
            return this;
        }




        public CheckoutExistingForItemsRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }


        public CheckoutExistingForItemsRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CheckoutExistingForItemsRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CheckoutExistingForItemsRequest reactivate(Boolean reactivate) {
            params.addOpt("reactivate", reactivate);
            return this;
        }


        public CheckoutExistingForItemsRequest forceTermReset(Boolean forceTermReset) {
            params.addOpt("force_term_reset", forceTermReset);
            return this;
        }


        public CheckoutExistingForItemsRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public CheckoutExistingForItemsRequest cancelUrl(String cancelUrl) {
            params.addOpt("cancel_url", cancelUrl);
            return this;
        }


        public CheckoutExistingForItemsRequest passThruContent(String passThruContent) {
            params.addOpt("pass_thru_content", passThruContent);
            return this;
        }


        public CheckoutExistingForItemsRequest allowOfflinePaymentMethods(Boolean allowOfflinePaymentMethods) {
            params.addOpt("allow_offline_payment_methods", allowOfflinePaymentMethods);
            return this;
        }




        public CheckoutExistingForItemsRequest subscriptionId(String subscriptionId) {
            params.add("subscription[id]", subscriptionId);
            return this;
        }

        @Deprecated
        public CheckoutExistingForItemsRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public CheckoutExistingForItemsRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public CheckoutExistingForItemsRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        @Deprecated
        public CheckoutExistingForItemsRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public CheckoutExistingForItemsRequest subscriptionAutoCollection(com.chargebee.models.enums.AutoCollection subscriptionAutoCollection) {
            params.addOpt("subscription[auto_collection]", subscriptionAutoCollection);
            return this;
        }

        public CheckoutExistingForItemsRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
            return this;
        }

        public CheckoutExistingForItemsRequest subscriptionInvoiceNotes(String subscriptionInvoiceNotes) {
            params.addOpt("subscription[invoice_notes]", subscriptionInvoiceNotes);
            return this;
        }

        public CheckoutExistingForItemsRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public CheckoutExistingForItemsRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
            return this;
        }

        public CheckoutExistingForItemsRequest customerIsEinvoiceEnabled(Boolean customerIsEinvoiceEnabled) {
            params.addOpt("customer[is_einvoice_enabled]", customerIsEinvoiceEnabled);
            return this;
        }

        public CheckoutExistingForItemsRequest customerEntityIdentifierScheme(String customerEntityIdentifierScheme) {
            params.addOpt("customer[entity_identifier_scheme]", customerEntityIdentifierScheme);
            return this;
        }

        public CheckoutExistingForItemsRequest customerEntityIdentifierStandard(String customerEntityIdentifierStandard) {
            params.addOpt("customer[entity_identifier_standard]", customerEntityIdentifierStandard);
            return this;
        }

        @Deprecated
        public CheckoutExistingForItemsRequest cardGateway(com.chargebee.models.enums.Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public CheckoutExistingForItemsRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
            return this;
        }

        public CheckoutExistingForItemsRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public CheckoutExistingForItemsRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public CheckoutExistingForItemsRequest subscriptionContractTermBillingCycleOnRenewal(Integer subscriptionContractTermBillingCycleOnRenewal) {
            params.addOpt("subscription[contract_term_billing_cycle_on_renewal]", subscriptionContractTermBillingCycleOnRenewal);
            return this;
        }

        public CheckoutExistingForItemsRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.add("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public CheckoutExistingForItemsRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public CheckoutExistingForItemsRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        public CheckoutExistingForItemsRequest subscriptionItemUnitPrice(int index, Long subscriptionItemUnitPrice) {
            params.addOpt("subscription_items[unit_price][" + index + "]", subscriptionItemUnitPrice);
            return this;
        }
        public CheckoutExistingForItemsRequest subscriptionItemUnitPriceInDecimal(int index, String subscriptionItemUnitPriceInDecimal) {
            params.addOpt("subscription_items[unit_price_in_decimal][" + index + "]", subscriptionItemUnitPriceInDecimal);
            return this;
        }
        public CheckoutExistingForItemsRequest subscriptionItemBillingCycles(int index, Integer subscriptionItemBillingCycles) {
            params.addOpt("subscription_items[billing_cycles][" + index + "]", subscriptionItemBillingCycles);
            return this;
        }
        public CheckoutExistingForItemsRequest subscriptionItemTrialEnd(int index, Timestamp subscriptionItemTrialEnd) {
            params.addOpt("subscription_items[trial_end][" + index + "]", subscriptionItemTrialEnd);
            return this;
        }
        public CheckoutExistingForItemsRequest subscriptionItemServicePeriodDays(int index, Integer subscriptionItemServicePeriodDays) {
            params.addOpt("subscription_items[service_period_days][" + index + "]", subscriptionItemServicePeriodDays);
            return this;
        }
        public CheckoutExistingForItemsRequest subscriptionItemChargeOnEvent(int index, com.chargebee.models.enums.ChargeOnEvent subscriptionItemChargeOnEvent) {
            params.addOpt("subscription_items[charge_on_event][" + index + "]", subscriptionItemChargeOnEvent);
            return this;
        }
        public CheckoutExistingForItemsRequest subscriptionItemChargeOnce(int index, Boolean subscriptionItemChargeOnce) {
            params.addOpt("subscription_items[charge_once][" + index + "]", subscriptionItemChargeOnce);
            return this;
        }
        public CheckoutExistingForItemsRequest subscriptionItemChargeOnOption(int index, com.chargebee.models.enums.ChargeOnOption subscriptionItemChargeOnOption) {
            params.addOpt("subscription_items[charge_on_option][" + index + "]", subscriptionItemChargeOnOption);
            return this;
        }
        @Deprecated
        public CheckoutExistingForItemsRequest subscriptionItemItemType(int index, com.chargebee.models.enums.ItemType subscriptionItemItemType) {
            params.addOpt("subscription_items[item_type][" + index + "]", subscriptionItemItemType);
            return this;
        }
        public CheckoutExistingForItemsRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.add("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public CheckoutExistingForItemsRequest discountDurationType(int index, com.chargebee.models.enums.DurationType discountDurationType) {
            params.add("discounts[duration_type][" + index + "]", discountDurationType);
            return this;
        }
        public CheckoutExistingForItemsRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public CheckoutExistingForItemsRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public CheckoutExistingForItemsRequest discountPeriod(int index, Integer discountPeriod) {
            params.addOpt("discounts[period][" + index + "]", discountPeriod);
            return this;
        }
        public CheckoutExistingForItemsRequest discountPeriodUnit(int index, com.chargebee.models.enums.PeriodUnit discountPeriodUnit) {
            params.addOpt("discounts[period_unit][" + index + "]", discountPeriodUnit);
            return this;
        }
        public CheckoutExistingForItemsRequest discountIncludedInMrr(int index, Boolean discountIncludedInMrr) {
            params.addOpt("discounts[included_in_mrr][" + index + "]", discountIncludedInMrr);
            return this;
        }
        public CheckoutExistingForItemsRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        public CheckoutExistingForItemsRequest discountOperationType(int index, com.chargebee.models.enums.OperationType discountOperationType) {
            params.add("discounts[operation_type][" + index + "]", discountOperationType);
            return this;
        }
        public CheckoutExistingForItemsRequest discountId(int index, String discountId) {
            params.addOpt("discounts[id][" + index + "]", discountId);
            return this;
        }
        public CheckoutExistingForItemsRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public CheckoutExistingForItemsRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public CheckoutExistingForItemsRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public CheckoutExistingForItemsRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public CheckoutExistingForItemsRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public CheckoutExistingForItemsRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public CheckoutExistingForItemsRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            return this;
        }
        public CheckoutExistingForItemsRequest entityIdentifierId(int index, String entityIdentifierId) {
            params.addOpt("entity_identifiers[id][" + index + "]", entityIdentifierId);
            return this;
        }
        public CheckoutExistingForItemsRequest entityIdentifierScheme(int index, String entityIdentifierScheme) {
            params.addOpt("entity_identifiers[scheme][" + index + "]", entityIdentifierScheme);
            return this;
        }
        public CheckoutExistingForItemsRequest entityIdentifierValue(int index, String entityIdentifierValue) {
            params.addOpt("entity_identifiers[value][" + index + "]", entityIdentifierValue);
            return this;
        }
        public CheckoutExistingForItemsRequest entityIdentifierOperation(int index, com.chargebee.models.enums.Operation entityIdentifierOperation) {
            params.addOpt("entity_identifiers[operation][" + index + "]", entityIdentifierOperation);
            return this;
        }
        public CheckoutExistingForItemsRequest entityIdentifierStandard(int index, String entityIdentifierStandard) {
            params.addOpt("entity_identifiers[standard][" + index + "]", entityIdentifierStandard);
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
        public UpdateCardRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
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
        public UpdatePaymentMethodRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
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
    
        public ManagePaymentSourcesRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
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
    
        public CollectNowRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
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

    public static class AcceptQuoteRequest extends Request<AcceptQuoteRequest> {

        private AcceptQuoteRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public AcceptQuoteRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }




        public AcceptQuoteRequest quoteId(String quoteId) {
            params.add("quote[id]", quoteId);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class ExtendSubscriptionRequest extends Request<ExtendSubscriptionRequest> {

        private ExtendSubscriptionRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ExtendSubscriptionRequest expiry(Integer expiry) {
            params.addOpt("expiry", expiry);
            return this;
        }


        public ExtendSubscriptionRequest billingCycle(Integer billingCycle) {
            params.addOpt("billing_cycle", billingCycle);
            return this;
        }


        public ExtendSubscriptionRequest subscriptionId(String subscriptionId) {
            params.add("subscription[id]", subscriptionId);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class CheckoutGiftRequest extends Request<CheckoutGiftRequest> {

        private CheckoutGiftRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CheckoutGiftRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public CheckoutGiftRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CheckoutGiftRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CheckoutGiftRequest gifterCustomerId(String gifterCustomerId) {
            params.addOpt("gifter[customer_id]", gifterCustomerId);
            return this;
        }

        public CheckoutGiftRequest gifterLocale(String gifterLocale) {
            params.addOpt("gifter[locale]", gifterLocale);
            return this;
        }

        public CheckoutGiftRequest subscriptionPlanId(String subscriptionPlanId) {
            params.add("subscription[plan_id]", subscriptionPlanId);
            return this;
        }

        public CheckoutGiftRequest subscriptionPlanQuantity(Integer subscriptionPlanQuantity) {
            params.addOpt("subscription[plan_quantity]", subscriptionPlanQuantity);
            return this;
        }

        public CheckoutGiftRequest subscriptionPlanQuantityInDecimal(String subscriptionPlanQuantityInDecimal) {
            params.addOpt("subscription[plan_quantity_in_decimal]", subscriptionPlanQuantityInDecimal);
            return this;
        }

        @Deprecated
        public CheckoutGiftRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public CheckoutGiftRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public CheckoutGiftRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public CheckoutGiftRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CheckoutGiftForItemsRequest extends Request<CheckoutGiftForItemsRequest> {

        private CheckoutGiftForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CheckoutGiftForItemsRequest businessEntityId(String businessEntityId) {
            params.addOpt("business_entity_id", businessEntityId);
            return this;
        }


        public CheckoutGiftForItemsRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public CheckoutGiftForItemsRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CheckoutGiftForItemsRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CheckoutGiftForItemsRequest gifterCustomerId(String gifterCustomerId) {
            params.addOpt("gifter[customer_id]", gifterCustomerId);
            return this;
        }

        public CheckoutGiftForItemsRequest gifterLocale(String gifterLocale) {
            params.addOpt("gifter[locale]", gifterLocale);
            return this;
        }

        public CheckoutGiftForItemsRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.addOpt("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public CheckoutGiftForItemsRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public CheckoutGiftForItemsRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class ClaimGiftRequest extends Request<ClaimGiftRequest> {

        private ClaimGiftRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ClaimGiftRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public ClaimGiftRequest giftId(String giftId) {
            params.add("gift[id]", giftId);
            return this;
        }

        public ClaimGiftRequest customerLocale(String customerLocale) {
            params.addOpt("customer[locale]", customerLocale);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class RetrieveAgreementPdfRequest extends Request<RetrieveAgreementPdfRequest> {

        private RetrieveAgreementPdfRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RetrieveAgreementPdfRequest paymentSourceId(String paymentSourceId) {
            params.add("payment_source_id", paymentSourceId);
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

    public static class PreCancelRequest extends Request<PreCancelRequest> {

        private PreCancelRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public PreCancelRequest passThruContent(String passThruContent) {
            params.addOpt("pass_thru_content", passThruContent);
            return this;
        }


        public PreCancelRequest cancelUrl(String cancelUrl) {
            params.addOpt("cancel_url", cancelUrl);
            return this;
        }


        public PreCancelRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public PreCancelRequest subscriptionId(String subscriptionId) {
            params.add("subscription[id]", subscriptionId);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

}
