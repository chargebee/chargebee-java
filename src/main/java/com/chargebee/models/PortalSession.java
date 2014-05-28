package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
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
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
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

    public String accessUrl() {
        return reqString("access_url");
    }

    public String redirectUrl() {
        return reqString("redirect_url");
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

    // Operations
    //===========

    public static CreateRequest create() throws IOException {
        String uri = uri("portal_sessions");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) throws IOException {
        String uri = uri("portal_sessions", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static Request logout(String id) throws IOException {
        String uri = uri("portal_sessions", nullCheck(id), "logout");
        return new Request(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest redirectUrl(String redirectUrl) {
            params.add("redirect_url", redirectUrl);
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

}
