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

public class SubscriptionEstimate extends Resource<SubscriptionEstimate> {

    public enum Status {
        FUTURE,
        IN_TRIAL,
        ACTIVE,
        NON_RENEWING,
        CANCELLED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class ShippingAddress extends Resource<ShippingAddress> {
        public ShippingAddress(JSONObject jsonObj) {
            super(jsonObj);
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

        public String company() {
            return optString("company");
        }

        public String phone() {
            return optString("phone");
        }

        public String line1() {
            return optString("line1");
        }

        public String line2() {
            return optString("line2");
        }

        public String line3() {
            return optString("line3");
        }

        public String city() {
            return optString("city");
        }

        public String stateCode() {
            return optString("state_code");
        }

        public String state() {
            return optString("state");
        }

        public String country() {
            return optString("country");
        }

        public String zip() {
            return optString("zip");
        }

        public ValidationStatus validationStatus() {
            return optEnum("validation_status", ValidationStatus.class);
        }

    }

    //Constructors
    //============

    public SubscriptionEstimate(String jsonStr) {
        super(jsonStr);
    }

    public SubscriptionEstimate(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return optString("id");
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public Timestamp nextBillingAt() {
        return optTimestamp("next_billing_at");
    }

    public SubscriptionEstimate.ShippingAddress shippingAddress() {
        return optSubResource("shipping_address", SubscriptionEstimate.ShippingAddress.class);
    }

    // Operations
    //===========


}
