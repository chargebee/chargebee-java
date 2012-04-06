/*
 * Copyright (c) 2012 chargebee.com
 * All Rights Reserved.
 */
package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class HostedPage extends Resource<HostedPage> {

    public enum Type {
        CHECKOUT_NEW,
        CHECKOUT_EXISTING,
        UPDATE_CARD,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a 
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum State {
        CREATED,
        REQUESTED,
        SUCCEEDED,
        CANCELLED,
        FAILED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a 
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum FailureReason {
        CARD_ERROR,
        SERVER_ERROR,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a 
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public HostedPage(String jsonStr) {
        super(jsonStr);
    }
    
    public HostedPage(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return optString("id");
    }

    public Type type() {
        return optEnum("type", Type.class);
    }

    public String url() {
        return optString("url");
    }

    public State state() {
        return optEnum("state", State.class);
    }

    public FailureReason failureReason() {
        return optEnum("failure_reason", FailureReason.class);
    }

    public String passThruContent() {
        return optString("pass_thru_content");
    }

    public Timestamp createdAt() {
        return optTimestamp("created_at");
    }

    public Timestamp expiresAt() {
        return optTimestamp("expires_at");
    }

    // Operations
    //===========

    public static CheckoutNewRequest checkoutNew() throws IOException {
        String url = url("hosted_pages", "checkout_new");
        return new CheckoutNewRequest(Method.POST, url);
    }

    public static CheckoutExistingRequest checkoutExisting() throws IOException {
        String url = url("hosted_pages", "checkout_existing");
        return new CheckoutExistingRequest(Method.POST, url);
    }

    public static UpdateCardRequest updateCard() throws IOException {
        String url = url("hosted_pages", "update_card");
        return new UpdateCardRequest(Method.POST, url);
    }

    public static Request retrieve(String id) throws IOException {
        String url = url("hosted_pages", nullCheck(id));
        return new Request(Method.GET, url);
    }

    public static class Content extends ResultBase{

        public Content(JSONObject jsonObj) {
            super(jsonObj);
        }
    }

    public Content content(){
        return new Content(optJSONObject("content"));
    }
    // Operation Request Classes
    //==========================

    public static class CheckoutNewRequest extends Request {

        private Params params = new Params();

        private CheckoutNewRequest(Method httpMeth, String url) {
            super(httpMeth, url);
        }

        public CheckoutNewRequest passThruContent(String passThruContent) {
            params.addOpt("pass_thru_content", passThruContent);
            return this;
        }

        public CheckoutNewRequest subscriptionId(String subscriptionId) {
            params.add("subscription[id]", subscriptionId);
            return this;
        }

        public CheckoutNewRequest subscriptionPlanId(String subscriptionPlanId) {
            params.add("subscription[plan_id]", subscriptionPlanId);
            return this;
        }

        public CheckoutNewRequest subscriptionPlanQuantity(Integer subscriptionPlanQuantity) {
            params.addOpt("subscription[plan_quantity]", subscriptionPlanQuantity);
            return this;
        }

        public CheckoutNewRequest customerEmail(String customerEmail) {
            params.addOpt("customer[email]", customerEmail);
            return this;
        }

        public CheckoutNewRequest customerFirstName(String customerFirstName) {
            params.addOpt("customer[first_name]", customerFirstName);
            return this;
        }

        public CheckoutNewRequest customerLastName(String customerLastName) {
            params.addOpt("customer[last_name]", customerLastName);
            return this;
        }

        public CheckoutNewRequest customerCompany(String customerCompany) {
            params.addOpt("customer[company]", customerCompany);
            return this;
        }

        public CheckoutNewRequest cardGateway(Gateway cardGateway) {
            params.add("card[gateway]", cardGateway);
            return this;
        }

        public CheckoutNewRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }

        public CheckoutNewRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class CheckoutExistingRequest extends Request {

        private Params params = new Params();

        private CheckoutExistingRequest(Method httpMeth, String url) {
            super(httpMeth, url);
        }

        public CheckoutExistingRequest replaceAddonList(Boolean replaceAddonList) {
            params.addOpt("replace_addon_list", replaceAddonList);
            return this;
        }

        public CheckoutExistingRequest passThruContent(String passThruContent) {
            params.addOpt("pass_thru_content", passThruContent);
            return this;
        }

        public CheckoutExistingRequest subscriptionId(String subscriptionId) {
            params.add("subscription[id]", subscriptionId);
            return this;
        }

        public CheckoutExistingRequest subscriptionPlanId(String subscriptionPlanId) {
            params.addOpt("subscription[plan_id]", subscriptionPlanId);
            return this;
        }

        public CheckoutExistingRequest subscriptionPlanQuantity(Integer subscriptionPlanQuantity) {
            params.addOpt("subscription[plan_quantity]", subscriptionPlanQuantity);
            return this;
        }

        public CheckoutExistingRequest cardGateway(Gateway cardGateway) {
            params.add("card[gateway]", cardGateway);
            return this;
        }

        public CheckoutExistingRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }

        public CheckoutExistingRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateCardRequest extends Request {

        private Params params = new Params();

        private UpdateCardRequest(Method httpMeth, String url) {
            super(httpMeth, url);
        }

        public UpdateCardRequest customerId(String customerId) {
            params.add("customer[id]", customerId);
            return this;
        }

        public UpdateCardRequest cardGateway(Gateway cardGateway) {
            params.add("card[gateway]", cardGateway);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

}
