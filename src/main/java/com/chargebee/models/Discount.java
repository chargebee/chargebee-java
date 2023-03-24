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

public class Discount extends Resource<Discount> {

    public enum Type {
        FIXED_AMOUNT,
        PERCENTAGE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Discount(String jsonStr) {
        super(jsonStr);
    }

    public Discount(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

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

    public Integer amount() {
        return optInteger("amount");
    }

    public String currencyCode() {
        return optString("currency_code");
    }

    public DurationType durationType() {
        return reqEnum("duration_type", DurationType.class);
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

    public Timestamp applyTill() {
        return optTimestamp("apply_till");
    }

    public Integer appliedCount() {
        return optInteger("applied_count");
    }

    public String couponId() {
        return reqString("coupon_id");
    }

    public Integer index() {
        return reqInteger("index");
    }

    // Operations
    //===========


}
