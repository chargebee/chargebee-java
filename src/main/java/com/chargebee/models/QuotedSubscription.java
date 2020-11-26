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

public class QuotedSubscription extends Resource<QuotedSubscription> {

    public enum BillingPeriodUnit {
        DAY,
        WEEK,
        MONTH,
        YEAR,
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

        public Integer unitPrice() {
            return optInteger("unit_price");
        }

        public Integer amount() {
            return optInteger("amount");
        }

        public Timestamp trialEnd() {
            return optTimestamp("trial_end");
        }

        public Integer remainingBillingCycles() {
            return optInteger("remaining_billing_cycles");
        }

        public String quantityInDecimal() {
            return optString("quantity_in_decimal");
        }

        public String unitPriceInDecimal() {
            return optString("unit_price_in_decimal");
        }

        public String amountInDecimal() {
            return optString("amount_in_decimal");
        }

    }

    public static class EventBasedAddon extends Resource<EventBasedAddon> {
        public enum OnEvent {
             SUBSCRIPTION_CREATION,SUBSCRIPTION_TRIAL_START,PLAN_ACTIVATION,SUBSCRIPTION_ACTIVATION,CONTRACT_TERMINATION,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public EventBasedAddon(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public Integer quantity() {
            return reqInteger("quantity");
        }

        public Integer unitPrice() {
            return reqInteger("unit_price");
        }

        @Deprecated
        public Integer servicePeriodInDays() {
            return optInteger("service_period_in_days");
        }

        public OnEvent onEvent() {
            return reqEnum("on_event", OnEvent.class);
        }

        public Boolean chargeOnce() {
            return reqBoolean("charge_once");
        }

        public String quantityInDecimal() {
            return optString("quantity_in_decimal");
        }

        public String unitPriceInDecimal() {
            return optString("unit_price_in_decimal");
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

        public String couponCode() {
            return optString("coupon_code");
        }

    }

    public static class SubscriptionItem extends Resource<SubscriptionItem> {
        public enum ItemType {
             PLAN,ADDON,CHARGE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum ChargeOnOption {
             IMMEDIATELY,ON_EVENT,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public SubscriptionItem(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String itemPriceId() {
            return reqString("item_price_id");
        }

        public ItemType itemType() {
            return reqEnum("item_type", ItemType.class);
        }

        public Integer quantity() {
            return optInteger("quantity");
        }

        public Integer unitPrice() {
            return optInteger("unit_price");
        }

        public Integer amount() {
            return optInteger("amount");
        }

        public Integer freeQuantity() {
            return optInteger("free_quantity");
        }

        public Timestamp trialEnd() {
            return optTimestamp("trial_end");
        }

        public Integer billingCycles() {
            return optInteger("billing_cycles");
        }

        public Integer servicePeriodDays() {
            return optInteger("service_period_days");
        }

        public ChargeOnEvent chargeOnEvent() {
            return optEnum("charge_on_event", ChargeOnEvent.class);
        }

        public Boolean chargeOnce() {
            return optBoolean("charge_once");
        }

        public ChargeOnOption chargeOnOption() {
            return optEnum("charge_on_option", ChargeOnOption.class);
        }

    }

    public static class ItemTier extends Resource<ItemTier> {
        public ItemTier(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String itemPriceId() {
            return reqString("item_price_id");
        }

        public Integer startingUnit() {
            return reqInteger("starting_unit");
        }

        public Integer endingUnit() {
            return optInteger("ending_unit");
        }

        public Integer price() {
            return reqInteger("price");
        }

        @Deprecated
        public String startingUnitInDecimal() {
            return optString("starting_unit_in_decimal");
        }

        @Deprecated
        public String endingUnitInDecimal() {
            return optString("ending_unit_in_decimal");
        }

        @Deprecated
        public String priceInDecimal() {
            return optString("price_in_decimal");
        }

    }

    //Constructors
    //============

    public QuotedSubscription(String jsonStr) {
        super(jsonStr);
    }

    public QuotedSubscription(JSONObject jsonObj) {
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

    public Integer planUnitPrice() {
        return optInteger("plan_unit_price");
    }

    public Integer setupFee() {
        return optInteger("setup_fee");
    }

    public Integer billingPeriod() {
        return optInteger("billing_period");
    }

    public BillingPeriodUnit billingPeriodUnit() {
        return optEnum("billing_period_unit", BillingPeriodUnit.class);
    }

    public Timestamp startDate() {
        return optTimestamp("start_date");
    }

    public Timestamp trialEnd() {
        return optTimestamp("trial_end");
    }

    public Integer remainingBillingCycles() {
        return optInteger("remaining_billing_cycles");
    }

    public String poNumber() {
        return optString("po_number");
    }

    public AutoCollection autoCollection() {
        return optEnum("auto_collection", AutoCollection.class);
    }

    public List<QuotedSubscription.Addon> addons() {
        return optList("addons", QuotedSubscription.Addon.class);
    }

    public List<QuotedSubscription.EventBasedAddon> eventBasedAddons() {
        return optList("event_based_addons", QuotedSubscription.EventBasedAddon.class);
    }

    public List<QuotedSubscription.Coupon> coupons() {
        return optList("coupons", QuotedSubscription.Coupon.class);
    }

    public List<QuotedSubscription.SubscriptionItem> subscriptionItems() {
        return optList("subscription_items", QuotedSubscription.SubscriptionItem.class);
    }

    public List<QuotedSubscription.ItemTier> itemTiers() {
        return optList("item_tiers", QuotedSubscription.ItemTier.class);
    }

    // Operations
    //===========


}
