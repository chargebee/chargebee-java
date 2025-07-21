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

public class QuotedRamp extends Resource<QuotedRamp> {

    public static class LineItem extends Resource<LineItem> {
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

        public LineItem(JSONObject jsonObj) {
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

        public String quantityInDecimal() {
            return optString("quantity_in_decimal");
        }

        @Deprecated
        public String meteredQuantity() {
            return optString("metered_quantity");
        }

        public Long unitPrice() {
            return optLong("unit_price");
        }

        public String unitPriceInDecimal() {
            return optString("unit_price_in_decimal");
        }

        public Long amount() {
            return optLong("amount");
        }

        public String amountInDecimal() {
            return optString("amount_in_decimal");
        }

        public Integer billingPeriod() {
            return optInteger("billing_period");
        }

        public BillingPeriodUnit billingPeriodUnit() {
            return optEnum("billing_period_unit", BillingPeriodUnit.class);
        }

        public Integer freeQuantity() {
            return optInteger("free_quantity");
        }

        public String freeQuantityInDecimal() {
            return optString("free_quantity_in_decimal");
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

        public Timestamp startDate() {
            return optTimestamp("start_date");
        }

        public Timestamp endDate() {
            return optTimestamp("end_date");
        }

        public String rampTierId() {
            return optString("ramp_tier_id");
        }

        public Long discountPerBillingCycle() {
            return optLong("discount_per_billing_cycle");
        }

        public String discountPerBillingCycleInDecimal() {
            return optString("discount_per_billing_cycle_in_decimal");
        }

        public Long itemLevelDiscountPerBillingCycle() {
            return optLong("item_level_discount_per_billing_cycle");
        }

        public String itemLevelDiscountPerBillingCycleInDecimal() {
            return optString("item_level_discount_per_billing_cycle_in_decimal");
        }

        public Long amountPerBillingCycle() {
            return optLong("amount_per_billing_cycle");
        }

        public String amountPerBillingCycleInDecimal() {
            return optString("amount_per_billing_cycle_in_decimal");
        }

        public Long netAmountPerBillingCycle() {
            return optLong("net_amount_per_billing_cycle");
        }

        public String netAmountPerBillingCycleInDecimal() {
            return optString("net_amount_per_billing_cycle_in_decimal");
        }

    }

    public static class Discount extends Resource<Discount> {
        public enum Type {
             FIXED_AMOUNT,PERCENTAGE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum DurationType {
             ONE_TIME,FOREVER,LIMITED_PERIOD,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum EntityType {
             ITEM_LEVEL_COUPON,DOCUMENT_LEVEL_COUPON,ITEM_LEVEL_DISCOUNT,DOCUMENT_LEVEL_DISCOUNT,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum ApplyOn {
             INVOICE_AMOUNT,SPECIFIC_ITEM_PRICE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public Discount(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public String invoiceName() {
            return optString("invoice_name");
        }

        public Type type() {
            return reqEnum("type", Type.class);
        }

        public Double percentage() {
            return optDouble("percentage");
        }

        public Long amount() {
            return optLong("amount");
        }

        public DurationType durationType() {
            return reqEnum("duration_type", DurationType.class);
        }

        public EntityType entityType() {
            return reqEnum("entity_type", EntityType.class);
        }

        public String entityId() {
            return optString("entity_id");
        }

        public Integer period() {
            return optInteger("period");
        }

        public PeriodUnit periodUnit() {
            return optEnum("period_unit", PeriodUnit.class);
        }

        public Boolean includedInMrr() {
            return reqBoolean("included_in_mrr");
        }

        public ApplyOn applyOn() {
            return reqEnum("apply_on", ApplyOn.class);
        }

        public String itemPriceId() {
            return optString("item_price_id");
        }

        public Timestamp createdAt() {
            return reqTimestamp("created_at");
        }

        public Timestamp updatedAt() {
            return optTimestamp("updated_at");
        }

        public Timestamp startDate() {
            return optTimestamp("start_date");
        }

        public Timestamp endDate() {
            return optTimestamp("end_date");
        }

    }

    public static class ItemTier extends Resource<ItemTier> {
        public enum PricingType {
             PER_UNIT,FLAT_FEE,PACKAGE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

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

        public Long price() {
            return reqLong("price");
        }

        public String startingUnitInDecimal() {
            return optString("starting_unit_in_decimal");
        }

        public String endingUnitInDecimal() {
            return optString("ending_unit_in_decimal");
        }

        public String priceInDecimal() {
            return optString("price_in_decimal");
        }

        public String rampTierId() {
            return optString("ramp_tier_id");
        }

        public PricingType pricingType() {
            return optEnum("pricing_type", PricingType.class);
        }

        public Integer packageSize() {
            return optInteger("package_size");
        }

    }

    public static class CouponApplicabilityMapping extends Resource<CouponApplicabilityMapping> {
        public CouponApplicabilityMapping(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String couponId() {
            return optString("coupon_id");
        }

        public List<String> applicableItemPriceIds() {
            return optList("applicable_item_price_ids", String.class);
        }

    }

    //Constructors
    //============

    public QuotedRamp(String jsonStr) {
        super(jsonStr);
    }

    public QuotedRamp(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public List<QuotedRamp.LineItem> lineItems() {
        return optList("line_items", QuotedRamp.LineItem.class);
    }

    public List<QuotedRamp.Discount> discounts() {
        return optList("discounts", QuotedRamp.Discount.class);
    }

    public List<QuotedRamp.ItemTier> itemTiers() {
        return optList("item_tiers", QuotedRamp.ItemTier.class);
    }

    public List<QuotedRamp.CouponApplicabilityMapping> couponApplicabilityMappings() {
        return optList("coupon_applicability_mappings", QuotedRamp.CouponApplicabilityMapping.class);
    }

    // Operations
    //===========


}
