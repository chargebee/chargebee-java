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

public class PortalSession extends Resource<PortalSession> {

    public enum Status {
        CREATED,
        LOGGED_IN,
        LOGGED_OUT,
        NOT_YET_ACTIVATED,
        ACTIVATED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class LinkedCustomer extends Resource<LinkedCustomer> {
        public LinkedCustomer(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String customerId() {
            return reqString("customer_id");
        }

        public String email() {
            return optString("email");
        }

        public Boolean hasBillingAddress() {
            return reqBoolean("has_billing_address");
        }

        public Boolean hasPaymentMethod() {
            return reqBoolean("has_payment_method");
        }

        public Boolean hasActiveSubscription() {
            return reqBoolean("has_active_subscription");
        }

    }

    //Constructors
    //============

    public PortalSession(String jsonStr) {
        super(jsonStr);
    }

    public PortalSession(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String token() {
        return reqString("token");
    }

    public String accessUrl() {
        return reqString("access_url");
    }

    public String redirectUrl() {
        return optString("redirect_url");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp expiresAt() {
        return optTimestamp("expires_at");
    }

    public String customerId() {
        return reqString("customer_id");
    }

    public Timestamp loginAt() {
        return optTimestamp("login_at");
    }

    public Timestamp logoutAt() {
        return optTimestamp("logout_at");
    }

    public String loginIpaddress() {
        return optString("login_ipaddress");
    }

    public String logoutIpaddress() {
        return optString("logout_ipaddress");
    }

    public List<PortalSession.LinkedCustomer> linkedCustomers() {
        return optList("linked_customers", PortalSession.LinkedCustomer.class);
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("portal_sessions");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("portal_sessions", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static Request logout(String id) {
        String uri = uri("portal_sessions", nullCheck(id), "logout");
        return new Request(Method.POST, uri);
    }

    public static ActivateRequest activate(String id) {
        String uri = uri("portal_sessions", nullCheck(id), "activate");
        return new ActivateRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public CreateRequest forwardUrl(String forwardUrl) {
            params.addOpt("forward_url", forwardUrl);
            return this;
        }


        public CreateRequest customerId(String customerId) {
            params.add("customer[id]", customerId);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class ActivateRequest extends Request<ActivateRequest> {

        private ActivateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ActivateRequest token(String token) {
            params.add("token", token);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
