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

public class InvoiceEstimate extends Resource<InvoiceEstimate> {

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

        public Long unitAmount() {
            return reqLong("unit_amount");
        }

        public Integer quantity() {
            return optInteger("quantity");
        }

        public Long amount() {
            return optLong("amount");
        }

        public PricingModel pricingModel() {
            return optEnum("pricing_model", PricingModel.class);
        }

        public Boolean isTaxed() {
            return reqBoolean("is_taxed");
        }

        public Long taxAmount() {
            return optLong("tax_amount");
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

        public Long discountAmount() {
            return optLong("discount_amount");
        }

        public Long itemLevelDiscountAmount() {
            return optLong("item_level_discount_amount");
        }

        public String referenceLineItemId() {
            return optString("reference_line_item_id");
        }

        public String description() {
            return reqString("description");
        }

        public String entityDescription() {
            return optString("entity_description");
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

        public Long amount() {
            return reqLong("amount");
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

    public static class Tax extends Resource<Tax> {
        public Tax(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String name() {
            return reqString("name");
        }

        public Long amount() {
            return reqLong("amount");
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

        public Long taxableAmount() {
            return reqLong("taxable_amount");
        }

        public Long taxAmount() {
            return reqLong("tax_amount");
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

        public Long taxAmountInLocalCurrency() {
            return optLong("tax_amount_in_local_currency");
        }

        public String localCurrencyCode() {
            return optString("local_currency_code");
        }

    }

    public static class LineItemTier extends Resource<LineItemTier> {
        public LineItemTier(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String lineItemId() {
            return optString("line_item_id");
        }

        public Integer startingUnit() {
            return reqInteger("starting_unit");
        }

        public Integer endingUnit() {
            return optInteger("ending_unit");
        }

        public Integer quantityUsed() {
            return reqInteger("quantity_used");
        }

        public Long unitAmount() {
            return reqLong("unit_amount");
        }

        public String startingUnitInDecimal() {
            return optString("starting_unit_in_decimal");
        }

        public String endingUnitInDecimal() {
            return optString("ending_unit_in_decimal");
        }

        public String quantityUsedInDecimal() {
            return optString("quantity_used_in_decimal");
        }

        public String unitAmountInDecimal() {
            return optString("unit_amount_in_decimal");
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

        public Long discountAmount() {
            return reqLong("discount_amount");
        }

    }

    //Constructors
    //============

    public InvoiceEstimate(String jsonStr) {
        super(jsonStr);
    }

    public InvoiceEstimate(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public Boolean recurring() {
        return reqBoolean("recurring");
    }

    public PriceType priceType() {
        return reqEnum("price_type", PriceType.class);
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public Long subTotal() {
        return reqLong("sub_total");
    }

    public Long total() {
        return optLong("total");
    }

    public Long creditsApplied() {
        return optLong("credits_applied");
    }

    public Long amountPaid() {
        return optLong("amount_paid");
    }

    public Long amountDue() {
        return optLong("amount_due");
    }

    public List<InvoiceEstimate.LineItem> lineItems() {
        return optList("line_items", InvoiceEstimate.LineItem.class);
    }

    public List<InvoiceEstimate.Discount> discounts() {
        return optList("discounts", InvoiceEstimate.Discount.class);
    }

    public List<InvoiceEstimate.Tax> taxes() {
        return optList("taxes", InvoiceEstimate.Tax.class);
    }

    public List<InvoiceEstimate.LineItemTax> lineItemTaxes() {
        return optList("line_item_taxes", InvoiceEstimate.LineItemTax.class);
    }

    public List<InvoiceEstimate.LineItemTier> lineItemTiers() {
        return optList("line_item_tiers", InvoiceEstimate.LineItemTier.class);
    }

    public List<InvoiceEstimate.LineItemDiscount> lineItemDiscounts() {
        return optList("line_item_discounts", InvoiceEstimate.LineItemDiscount.class);
    }

    public Long roundOffAmount() {
        return optLong("round_off_amount");
    }

    public String customerId() {
        return optString("customer_id");
    }

    // Operations
    //===========


}
