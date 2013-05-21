package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class OfflineCheckout extends Resource<OfflineCheckout> {

    public enum Type {
        CHECKOUT_NEW,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Status {
        CREATED,
        COMPLETED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public OfflineCheckout(String jsonStr) {
        super(jsonStr);
    }

    public OfflineCheckout(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public Integer amount() {
        return reqInteger("amount");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp updatedAt() {
        return reqTimestamp("updated_at");
    }

    // Operations
    //===========

    public static Request retrieve(String id) throws IOException {
        String uri = uri("offline_checkouts", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static PreRegisterRequest preRegister() throws IOException {
        String uri = uri("offline_checkouts", "pre_register");
        return new PreRegisterRequest(Method.POST, uri);
    }

    public static PostRegisterRequest postRegister(String id) throws IOException {
        String uri = uri("offline_checkouts", nullCheck(id), "post_register");
        return new PostRegisterRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class PreRegisterRequest extends Request {

        private PreRegisterRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public PreRegisterRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }

        public PreRegisterRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription[id]", subscriptionId);
            return this;
        }

        public PreRegisterRequest customerEmail(String customerEmail) {
            params.add("customer[email]", customerEmail);
            return this;
        }

        public PreRegisterRequest customerFirstName(String customerFirstName) {
            params.addOpt("customer[first_name]", customerFirstName);
            return this;
        }

        public PreRegisterRequest customerLastName(String customerLastName) {
            params.addOpt("customer[last_name]", customerLastName);
            return this;
        }

        public PreRegisterRequest subscriptionPlanId(String subscriptionPlanId) {
            params.add("subscription[plan_id]", subscriptionPlanId);
            return this;
        }

        public PreRegisterRequest subscriptionPlanQuantity(Integer subscriptionPlanQuantity) {
            params.addOpt("subscription[plan_quantity]", subscriptionPlanQuantity);
            return this;
        }

        public PreRegisterRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public PreRegisterRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }

        public PreRegisterRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class PostRegisterRequest extends Request {

        private PostRegisterRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public PostRegisterRequest succeeded(Boolean succeeded) {
            params.add("succeeded", succeeded);
            return this;
        }

        public PostRegisterRequest referenceNumber(String referenceNumber) {
            params.addOpt("reference_number", referenceNumber);
            return this;
        }

        public PostRegisterRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }

        public PostRegisterRequest errorMessage(String errorMessage) {
            params.addOpt("error_message", errorMessage);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

}
