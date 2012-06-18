package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class Coupon extends Resource<Coupon> {

    public enum DiscountType {
        FIXED_AMOUNT,
        PERCENTAGE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum DurationType {
        ONE_TIME,
        FOREVER,
        LIMITED_PERIOD,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Status {
        ACTIVE,
        EXPIRED,
        ARCHIVED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum ApplyDiscountOn {
        PLANS,
        PLANS_AND_ADDONS,
        PLANS_WITH_QUANTITY,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Coupon(String jsonStr) {
        super(jsonStr);
    }

    public Coupon(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String name() {
        return reqString("name");
    }

    public String invoiceName() {
        return optString("invoice_name");
    }

    public DiscountType discountType() {
        return reqEnum("discount_type", DiscountType.class);
    }

    public Double discountPercentage() {
        return optDouble("discount_percentage");
    }

    public Integer discountAmount() {
        return optInteger("discount_amount");
    }

    public DurationType durationType() {
        return reqEnum("duration_type", DurationType.class);
    }

    public Integer durationMonth() {
        return optInteger("duration_month");
    }

    public Date validTill() {
        return optTimestamp("valid_till");
    }

    public Integer maxRedemptions() {
        return optInteger("max_redemptions");
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public Integer redemptions() {
        return optInteger("redemptions");
    }

    public ApplyDiscountOn applyDiscountOn() {
        return reqEnum("apply_discount_on", ApplyDiscountOn.class);
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp archivedAt() {
        return optTimestamp("archived_at");
    }

    // Operations
    //===========

    public static ListRequest list() throws IOException {
        String url = url("coupons");
        return new ListRequest(url);
    }

    public static Request retrieve(String id) throws IOException {
        String url = url("coupons", nullCheck(id));
        return new Request(Method.GET, url);
    }


}
