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

public class QuotedCharge extends Resource<QuotedCharge> {

    public static class Charge extends Resource<Charge> {
        public Charge(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Long amount() {
            return optLong("amount");
        }

        public String amountInDecimal() {
            return optString("amount_in_decimal");
        }

        public String description() {
            return optString("description");
        }

        public Integer servicePeriodInDays() {
            return optInteger("service_period_in_days");
        }

        public AvalaraSaleType avalaraSaleType() {
            return optEnum("avalara_sale_type", AvalaraSaleType.class);
        }

        public Integer avalaraTransactionType() {
            return optInteger("avalara_transaction_type");
        }

        public Integer avalaraServiceType() {
            return optInteger("avalara_service_type");
        }

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

        public Long unitPrice() {
            return optLong("unit_price");
        }

        public String quantityInDecimal() {
            return optString("quantity_in_decimal");
        }

        public String unitPriceInDecimal() {
            return optString("unit_price_in_decimal");
        }

        public Integer servicePeriod() {
            return optInteger("service_period");
        }

    }

    public static class InvoiceItem extends Resource<InvoiceItem> {
        public InvoiceItem(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String itemPriceId() {
            return reqString("item_price_id");
        }

        public Integer quantity() {
            return optInteger("quantity");
        }

        public String quantityInDecimal() {
            return optString("quantity_in_decimal");
        }

        public Long unitPrice() {
            return optLong("unit_price");
        }

        public String unitPriceInDecimal() {
            return optString("unit_price_in_decimal");
        }

        public Integer servicePeriodDays() {
            return optInteger("service_period_days");
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

        public Integer index() {
            return reqInteger("index");
        }

    }

    public static class Coupon extends Resource<Coupon> {
        public Coupon(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String couponId() {
            return reqString("coupon_id");
        }

    }

    //Constructors
    //============

    public QuotedCharge(String jsonStr) {
        super(jsonStr);
    }

    public QuotedCharge(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public List<QuotedCharge.Charge> charges() {
        return optList("charges", QuotedCharge.Charge.class);
    }

    public List<QuotedCharge.Addon> addons() {
        return optList("addons", QuotedCharge.Addon.class);
    }

    public List<QuotedCharge.InvoiceItem> invoiceItems() {
        return optList("invoice_items", QuotedCharge.InvoiceItem.class);
    }

    public List<QuotedCharge.ItemTier> itemTiers() {
        return optList("item_tiers", QuotedCharge.ItemTier.class);
    }

    public List<QuotedCharge.Coupon> coupons() {
        return optList("coupons", QuotedCharge.Coupon.class);
    }

    // Operations
    //===========


}
