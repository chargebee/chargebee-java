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

public class Session extends Resource<Session> {

    //Constructors
    //============

    public Session(String jsonStr) {
        super(jsonStr);
    }

    public Session(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp expiresAt() {
        return reqTimestamp("expires_at");
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("sessions");
        return new CreateRequest(Method.POST, uri);
    }

    public static RetrieveRequest retrieve(String id) {
        String uri = uri("sessions", nullCheck(id));
        return new RetrieveRequest(Method.GET, uri);
    }

    public static class Content extends ResultBase{

        public Content(JSONObject jsonObj) {
            super(jsonObj);
        }
    }

    public Content content(){
        if (optJSONObject("content") == null) {
            return null;
        }
        return new Content(optJSONObject("content"));
    }
    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest customerId(String customerId) {
            params.addOpt("customer[id]", customerId);
            return this;
        }

        public CreateRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription[id]", subscriptionId);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class RetrieveRequest extends Request<RetrieveRequest> {

        private RetrieveRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RetrieveRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
