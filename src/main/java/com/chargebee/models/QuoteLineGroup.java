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

public class QuoteLineGroup extends Resource<QuoteLineGroup> {

    public enum ChargeEvent {
        IMMEDIATE,
        SUBSCRIPTION_CREATION,
        TRIAL_START,
        SUBSCRIPTION_CHANGE,
        SUBSCRIPTION_RENEWAL,
        SUBSCRIPTION_CANCEL,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class LineItem extends Resource<LineItem> {
        public enum EntityType {
             PLAN_SETUP,PLAN,ADDON,ADHOC,PLAN_ITEM_PRICE,ADDON_ITEM_PRICE,CHARGE_ITEM_PRICE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public LineItem(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return optString("id");
        }

        public String subscriptionId() {
            return optString("subscription_id");
        }

        public Timestamp dateFrom() {
            return reqTimestamp("date_from");
        }

        public Timestamp dateTo() {
            return reqTimestamp("date_to");
        }

        public Integer unitAmount() {
            return reqInteger("unit_amount");
        }

        public Integer quantity() {
            return optInteger("quantity");
        }

        public Integer amount() {
            return optInteger("amount");
        }

        public PricingModel pricingModel() {
            return optEnum("pricing_model", PricingModel.class);
        }

        public Boolean isTaxed() {
            return reqBoolean("is_taxed");
        }

        public Integer taxAmount() {
            return optInteger("tax_amount");
        }

        public Double taxRate() {
            return optDouble("tax_rate");
        }

        public String unitAmountInDecimal() {
            return optString("unit_amount_in_decimal");
        }

        public String quantityInDecimal() {
            return optString("quantity_in_decimal");
        }

        public String amountInDecimal() {
            return optString("amount_in_decimal");
        }

        public Integer discountAmount() {
            return optInteger("discount_amount");
        }

        public Integer itemLevelDiscountAmount() {
            return optInteger("item_level_discount_amount");
        }

        public String description() {
            return reqString("description");
        }

        public String entityDescription() {
            return reqString("entity_description");
        }

        public EntityType entityType() {
            return reqEnum("entity_type", EntityType.class);
        }

        public TaxExemptReason taxExemptReason() {
            return optEnum("tax_exempt_reason", TaxExemptReason.class);
        }

        public String entityId() {
            return optString("entity_id");
        }

        public String customerId() {
            return optString("customer_id");
        }

    }

    public static class Discount extends Resource<Discount> {
        public enum EntityType {
             ITEM_LEVEL_COUPON,DOCUMENT_LEVEL_COUPON,PROMOTIONAL_CREDITS,PRORATED_CREDITS,ITEM_LEVEL_DISCOUNT,DOCUMENT_LEVEL_DISCOUNT,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public Discount(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Integer amount() {
            return reqInteger("amount");
        }

        public String description() {
            return optString("description");
        }

        public EntityType entityType() {
            return reqEnum("entity_type", EntityType.class);
        }

        public String entityId() {
            return optString("entity_id");
        }

        public String couponSetCode() {
            return optString("coupon_set_code");
        }

    }

    public static class LineItemDiscount extends Resource<LineItemDiscount> {
        public enum DiscountType {
             ITEM_LEVEL_COUPON,DOCUMENT_LEVEL_COUPON,PROMOTIONAL_CREDITS,PRORATED_CREDITS,ITEM_LEVEL_DISCOUNT,DOCUMENT_LEVEL_DISCOUNT,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public LineItemDiscount(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String lineItemId() {
            return reqString("line_item_id");
        }

        public DiscountType discountType() {
            return reqEnum("discount_type", DiscountType.class);
        }

        public String couponId() {
            return optString("coupon_id");
        }

        public String entityId() {
            return optString("entity_id");
        }

        public Integer discountAmount() {
            return reqInteger("discount_amount");
        }

    }

    public static class Tax extends Resource<Tax> {
        public Tax(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String name() {
            return reqString("name");
        }

        public Integer amount() {
            return reqInteger("amount");
        }

        public String description() {
            return optString("description");
        }

    }

    public static class LineItemTax extends Resource<LineItemTax> {
        public LineItemTax(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String lineItemId() {
            return optString("line_item_id");
        }

        public String taxName() {
            return reqString("tax_name");
        }

        public Double taxRate() {
            return reqDouble("tax_rate");
        }

        public Boolean isPartialTaxApplied() {
            return optBoolean("is_partial_tax_applied");
        }

        public Boolean isNonComplianceTax() {
            return optBoolean("is_non_compliance_tax");
        }

        public Integer taxableAmount() {
            return reqInteger("taxable_amount");
        }

        public Integer taxAmount() {
            return reqInteger("tax_amount");
        }

        public TaxJurisType taxJurisType() {
            return optEnum("tax_juris_type", TaxJurisType.class);
        }

        public String taxJurisName() {
            return optString("tax_juris_name");
        }

        public String taxJurisCode() {
            return optString("tax_juris_code");
        }

        public Integer taxAmountInLocalCurrency() {
            return optInteger("tax_amount_in_local_currency");
        }

        public String localCurrencyCode() {
            return optString("local_currency_code");
        }

    }

    //Constructors
    //============

    public QuoteLineGroup(String jsonStr) {
        super(jsonStr);
    }

    public QuoteLineGroup(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public Integer version() {
        return optInteger("version");
    }

    public String id() {
        return optString("id");
    }

    public Integer subTotal() {
        return reqInteger("sub_total");
    }

    public Integer total() {
        return optInteger("total");
    }

    public Integer creditsApplied() {
        return optInteger("credits_applied");
    }

    public Integer amountPaid() {
        return optInteger("amount_paid");
    }

    public Integer amountDue() {
        return optInteger("amount_due");
    }

    public ChargeEvent chargeEvent() {
        return optEnum("charge_event", ChargeEvent.class);
    }

    public Integer billingCycleNumber() {
        return optInteger("billing_cycle_number");
    }

    public List<QuoteLineGroup.LineItem> lineItems() {
        return optList("line_items", QuoteLineGroup.LineItem.class);
    }

    public List<QuoteLineGroup.Discount> discounts() {
        return optList("discounts", QuoteLineGroup.Discount.class);
    }

    public List<QuoteLineGroup.LineItemDiscount> lineItemDiscounts() {
        return optList("line_item_discounts", QuoteLineGroup.LineItemDiscount.class);
    }

    public List<QuoteLineGroup.Tax> taxes() {
        return optList("taxes", QuoteLineGroup.Tax.class);
    }

    public List<QuoteLineGroup.LineItemTax> lineItemTaxes() {
        return optList("line_item_taxes", QuoteLineGroup.LineItemTax.class);
    }

    // Operations
    //===========


}
