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

public class Gift extends Resource<Gift> {

    public enum Status {
        SCHEDULED,
        UNCLAIMED,
        CLAIMED,
        CANCELLED,
        EXPIRED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class Gifter extends Resource<Gifter> {
        public Gifter(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String customerId() {
            return reqString("customer_id");
        }

        public String invoiceId() {
            return optString("invoice_id");
        }

        public String signature() {
            return optString("signature");
        }

        public String note() {
            return optString("note");
        }

    }

    public static class GiftReceiver extends Resource<GiftReceiver> {
        public GiftReceiver(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String customerId() {
            return reqString("customer_id");
        }

        public String subscriptionId() {
            return reqString("subscription_id");
        }

        public String firstName() {
            return optString("first_name");
        }

        public String lastName() {
            return optString("last_name");
        }

        public String email() {
            return optString("email");
        }

    }

    public static class GiftTimeline extends Resource<GiftTimeline> {
        public GiftTimeline(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Status status() {
            return reqEnum("status", Status.class);
        }

        public Timestamp occurredAt() {
            return optTimestamp("occurred_at");
        }

    }

    //Constructors
    //============

    public Gift(String jsonStr) {
        super(jsonStr);
    }

    public Gift(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp scheduledAt() {
        return optTimestamp("scheduled_at");
    }

    public Boolean autoClaim() {
        return reqBoolean("auto_claim");
    }

    public Boolean noExpiry() {
        return reqBoolean("no_expiry");
    }

    public Timestamp claimExpiryDate() {
        return optTimestamp("claim_expiry_date");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public Gift.Gifter gifter() {
        return reqSubResource("gifter", Gift.Gifter.class);
    }

    public Gift.GiftReceiver giftReceiver() {
        return reqSubResource("gift_receiver", Gift.GiftReceiver.class);
    }

    public List<Gift.GiftTimeline> giftTimelines() {
        return optList("gift_timelines", Gift.GiftTimeline.class);
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("gifts");
        return new CreateRequest(Method.POST, uri);
    }

    public static CreateForItemsRequest createForItems() {
        String uri = uri("gifts", "create_for_items");
        return new CreateForItemsRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("gifts", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static GiftListRequest list() {
        String uri = uri("gifts");
        return new GiftListRequest(uri);
    }

    public static Request claim(String id) {
        String uri = uri("gifts", nullCheck(id), "claim");
        return new Request(Method.POST, uri);
    }

    public static Request cancel(String id) {
        String uri = uri("gifts", nullCheck(id), "cancel");
        return new Request(Method.POST, uri);
    }

    public static UpdateGiftRequest updateGift(String id) {
        String uri = uri("gifts", nullCheck(id), "update_gift");
        return new UpdateGiftRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest scheduledAt(Timestamp scheduledAt) {
            params.addOpt("scheduled_at", scheduledAt);
            return this;
        }


        public CreateRequest autoClaim(Boolean autoClaim) {
            params.addOpt("auto_claim", autoClaim);
            return this;
        }


        public CreateRequest noExpiry(Boolean noExpiry) {
            params.addOpt("no_expiry", noExpiry);
            return this;
        }


        public CreateRequest claimExpiryDate(Timestamp claimExpiryDate) {
            params.addOpt("claim_expiry_date", claimExpiryDate);
            return this;
        }


        public CreateRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateRequest gifterCustomerId(String gifterCustomerId) {
            params.add("gifter[customer_id]", gifterCustomerId);
            return this;
        }

        public CreateRequest gifterSignature(String gifterSignature) {
            params.add("gifter[signature]", gifterSignature);
            return this;
        }

        public CreateRequest gifterNote(String gifterNote) {
            params.addOpt("gifter[note]", gifterNote);
            return this;
        }

        public CreateRequest gifterPaymentSrcId(String gifterPaymentSrcId) {
            params.addOpt("gifter[payment_src_id]", gifterPaymentSrcId);
            return this;
        }

        public CreateRequest giftReceiverCustomerId(String giftReceiverCustomerId) {
            params.add("gift_receiver[customer_id]", giftReceiverCustomerId);
            return this;
        }

        public CreateRequest giftReceiverFirstName(String giftReceiverFirstName) {
            params.add("gift_receiver[first_name]", giftReceiverFirstName);
            return this;
        }

        public CreateRequest giftReceiverLastName(String giftReceiverLastName) {
            params.add("gift_receiver[last_name]", giftReceiverLastName);
            return this;
        }

        public CreateRequest giftReceiverEmail(String giftReceiverEmail) {
            params.add("gift_receiver[email]", giftReceiverEmail);
            return this;
        }

        public CreateRequest paymentIntentId(String paymentIntentId) {
            params.addOpt("payment_intent[id]", paymentIntentId);
            return this;
        }

        public CreateRequest paymentIntentGatewayAccountId(String paymentIntentGatewayAccountId) {
            params.addOpt("payment_intent[gateway_account_id]", paymentIntentGatewayAccountId);
            return this;
        }

        public CreateRequest paymentIntentGwToken(String paymentIntentGwToken) {
            params.addOpt("payment_intent[gw_token]", paymentIntentGwToken);
            return this;
        }

        public CreateRequest paymentIntentPaymentMethodType(PaymentIntent.PaymentMethodType paymentIntentPaymentMethodType) {
            params.addOpt("payment_intent[payment_method_type]", paymentIntentPaymentMethodType);
            return this;
        }

        public CreateRequest paymentIntentReferenceId(String paymentIntentReferenceId) {
            params.addOpt("payment_intent[reference_id]", paymentIntentReferenceId);
            return this;
        }

        @Deprecated
        public CreateRequest paymentIntentGwPaymentMethodId(String paymentIntentGwPaymentMethodId) {
            params.addOpt("payment_intent[gw_payment_method_id]", paymentIntentGwPaymentMethodId);
            return this;
        }

        public CreateRequest paymentIntentAdditionalInformation(JSONObject paymentIntentAdditionalInformation) {
            params.addOpt("payment_intent[additional_information]", paymentIntentAdditionalInformation);
            return this;
        }

        public CreateRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public CreateRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public CreateRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public CreateRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public CreateRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public CreateRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CreateRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CreateRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CreateRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CreateRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CreateRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public CreateRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CreateRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CreateRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CreateRequest subscriptionPlanId(String subscriptionPlanId) {
            params.add("subscription[plan_id]", subscriptionPlanId);
            return this;
        }

        public CreateRequest subscriptionPlanQuantity(Integer subscriptionPlanQuantity) {
            params.addOpt("subscription[plan_quantity]", subscriptionPlanQuantity);
            return this;
        }

        public CreateRequest subscriptionPlanQuantityInDecimal(String subscriptionPlanQuantityInDecimal) {
            params.addOpt("subscription[plan_quantity_in_decimal]", subscriptionPlanQuantityInDecimal);
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
        public CreateRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateForItemsRequest extends Request<CreateForItemsRequest> {

        private CreateForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateForItemsRequest scheduledAt(Timestamp scheduledAt) {
            params.addOpt("scheduled_at", scheduledAt);
            return this;
        }


        public CreateForItemsRequest autoClaim(Boolean autoClaim) {
            params.addOpt("auto_claim", autoClaim);
            return this;
        }


        public CreateForItemsRequest noExpiry(Boolean noExpiry) {
            params.addOpt("no_expiry", noExpiry);
            return this;
        }


        public CreateForItemsRequest claimExpiryDate(Timestamp claimExpiryDate) {
            params.addOpt("claim_expiry_date", claimExpiryDate);
            return this;
        }


        public CreateForItemsRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateForItemsRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateForItemsRequest gifterCustomerId(String gifterCustomerId) {
            params.add("gifter[customer_id]", gifterCustomerId);
            return this;
        }

        public CreateForItemsRequest gifterSignature(String gifterSignature) {
            params.add("gifter[signature]", gifterSignature);
            return this;
        }

        public CreateForItemsRequest gifterNote(String gifterNote) {
            params.addOpt("gifter[note]", gifterNote);
            return this;
        }

        public CreateForItemsRequest gifterPaymentSrcId(String gifterPaymentSrcId) {
            params.addOpt("gifter[payment_src_id]", gifterPaymentSrcId);
            return this;
        }

        public CreateForItemsRequest giftReceiverCustomerId(String giftReceiverCustomerId) {
            params.add("gift_receiver[customer_id]", giftReceiverCustomerId);
            return this;
        }

        public CreateForItemsRequest giftReceiverFirstName(String giftReceiverFirstName) {
            params.add("gift_receiver[first_name]", giftReceiverFirstName);
            return this;
        }

        public CreateForItemsRequest giftReceiverLastName(String giftReceiverLastName) {
            params.add("gift_receiver[last_name]", giftReceiverLastName);
            return this;
        }

        public CreateForItemsRequest giftReceiverEmail(String giftReceiverEmail) {
            params.add("gift_receiver[email]", giftReceiverEmail);
            return this;
        }

        public CreateForItemsRequest paymentIntentId(String paymentIntentId) {
            params.addOpt("payment_intent[id]", paymentIntentId);
            return this;
        }

        public CreateForItemsRequest paymentIntentGatewayAccountId(String paymentIntentGatewayAccountId) {
            params.addOpt("payment_intent[gateway_account_id]", paymentIntentGatewayAccountId);
            return this;
        }

        public CreateForItemsRequest paymentIntentGwToken(String paymentIntentGwToken) {
            params.addOpt("payment_intent[gw_token]", paymentIntentGwToken);
            return this;
        }

        public CreateForItemsRequest paymentIntentPaymentMethodType(PaymentIntent.PaymentMethodType paymentIntentPaymentMethodType) {
            params.addOpt("payment_intent[payment_method_type]", paymentIntentPaymentMethodType);
            return this;
        }

        public CreateForItemsRequest paymentIntentReferenceId(String paymentIntentReferenceId) {
            params.addOpt("payment_intent[reference_id]", paymentIntentReferenceId);
            return this;
        }

        @Deprecated
        public CreateForItemsRequest paymentIntentGwPaymentMethodId(String paymentIntentGwPaymentMethodId) {
            params.addOpt("payment_intent[gw_payment_method_id]", paymentIntentGwPaymentMethodId);
            return this;
        }

        public CreateForItemsRequest paymentIntentAdditionalInformation(JSONObject paymentIntentAdditionalInformation) {
            params.addOpt("payment_intent[additional_information]", paymentIntentAdditionalInformation);
            return this;
        }

        public CreateForItemsRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public CreateForItemsRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public CreateForItemsRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public CreateForItemsRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public CreateForItemsRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public CreateForItemsRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CreateForItemsRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CreateForItemsRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CreateForItemsRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CreateForItemsRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CreateForItemsRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public CreateForItemsRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CreateForItemsRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CreateForItemsRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CreateForItemsRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.addOpt("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public CreateForItemsRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public CreateForItemsRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class GiftListRequest extends ListRequest<GiftListRequest> {

        private GiftListRequest(String uri) {
            super(uri);
        }
    
        public EnumFilter<Gift.Status, GiftListRequest> status() {
            return new EnumFilter<Gift.Status, GiftListRequest>("status",this);        
        }


        public StringFilter<GiftListRequest> giftReceiverEmail() {
            return new StringFilter<GiftListRequest>("gift_receiver[email]",this);        
        }

        public StringFilter<GiftListRequest> gifterCustomerId() {
            return new StringFilter<GiftListRequest>("gifter[customer_id]",this);        
        }

        public StringFilter<GiftListRequest> giftReceiverCustomerId() {
            return new StringFilter<GiftListRequest>("gift_receiver[customer_id]",this);        
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateGiftRequest extends Request<UpdateGiftRequest> {

        private UpdateGiftRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateGiftRequest scheduledAt(Timestamp scheduledAt) {
            params.add("scheduled_at", scheduledAt);
            return this;
        }


        public UpdateGiftRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
