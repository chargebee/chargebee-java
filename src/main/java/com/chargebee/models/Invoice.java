package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class Invoice extends Resource<Invoice> {

    public enum Status {
        DRAFT,
        PAID,
        PAYMENT_DUE,
        NOT_PAID,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class LineItem extends Resource<LineItem> {
        public enum Type {
            PLAN_CHARGE, ADDON_CHARGE, CHARGE, CREDIT, PLAN_PRORATED_CHARGE, PLAN_PRORATED_CREDIT, ADDON_PRORATED_CHARGE, ADDON_PRORATED_CREDIT;
        }

        public LineItem(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Timestamp dateFrom() {
            return optTimestamp("date_from");
        }

        public Timestamp dateTo() {
            return optTimestamp("date_to");
        }

        public Integer unitAmount() {
            return reqInteger("unit_amount");
        }

        public Integer quantity() {
            return optInteger("quantity");
        }

    }

    public static class Discount extends Resource<Discount> {
        public enum Type {
            COUPON;
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

    }

    //Constructors
    //============

    public Invoice(String jsonStr) {
        super(jsonStr);
    }

    public Invoice(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return optString("id");
    }

    public String subscriptionId() {
        return reqString("subscription_id");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp startDate() {
        return optTimestamp("start_date");
    }

    public Timestamp endDate() {
        return optTimestamp("end_date");
    }

    public Integer amount() {
        return optInteger("amount");
    }

    public Timestamp paidOn() {
        return optTimestamp("paid_on");
    }

    public Timestamp nextRetry() {
        return optTimestamp("next_retry");
    }

    public Integer subTotal() {
        return reqInteger("sub_total");
    }

    public List<Invoice.LineItem> lineItems() {
        return optList("line_items", Invoice.LineItem.class);
    }

    public List<Invoice.Discount> discounts() {
        return optList("discounts", Invoice.Discount.class);
    }

    // Operations
    //===========

    public static ListRequest list() throws IOException {
        String url = url("invoices");
        return new ListRequest(url);
    }

    public static Request retrieve(String id) throws IOException {
        String url = url("invoices", nullCheck(id));
        return new Request(Method.GET, url);
    }

    public static ListRequest invoicesForSubscription(String id) throws IOException {
        String url = url("subscriptions", nullCheck(id), "invoices");
        return new ListRequest(url);
    }


}
