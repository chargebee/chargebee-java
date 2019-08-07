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

public class Address extends Resource<Address> {

    //Constructors
    //============

    public Address(String jsonStr) {
        super(jsonStr);
    }

    public Address(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String label() {
        return reqString("label");
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

    public String addr() {
        return optString("addr");
    }

    public String extendedAddr() {
        return optString("extended_addr");
    }

    public String extendedAddr2() {
        return optString("extended_addr2");
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

    public String subscriptionId() {
        return reqString("subscription_id");
    }

    // Operations
    //===========

    public static RetrieveRequest retrieve() {
        String uri = uri("addresses");
        return new RetrieveRequest(Method.GET, uri);
    }

    public static UpdateRequest update() {
        String uri = uri("addresses");
        return new UpdateRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class RetrieveRequest extends Request<RetrieveRequest> {

        private RetrieveRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RetrieveRequest subscriptionId(String subscriptionId) {
            params.add("subscription_id", subscriptionId);
            return this;
        }


        public RetrieveRequest label(String label) {
            params.add("label", label);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateRequest extends Request<UpdateRequest> {

        private UpdateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateRequest subscriptionId(String subscriptionId) {
            params.add("subscription_id", subscriptionId);
            return this;
        }


        public UpdateRequest label(String label) {
            params.add("label", label);
            return this;
        }


        public UpdateRequest firstName(String firstName) {
            params.addOpt("first_name", firstName);
            return this;
        }


        public UpdateRequest lastName(String lastName) {
            params.addOpt("last_name", lastName);
            return this;
        }


        public UpdateRequest email(String email) {
            params.addOpt("email", email);
            return this;
        }


        public UpdateRequest company(String company) {
            params.addOpt("company", company);
            return this;
        }


        public UpdateRequest phone(String phone) {
            params.addOpt("phone", phone);
            return this;
        }


        public UpdateRequest addr(String addr) {
            params.addOpt("addr", addr);
            return this;
        }


        public UpdateRequest extendedAddr(String extendedAddr) {
            params.addOpt("extended_addr", extendedAddr);
            return this;
        }


        public UpdateRequest extendedAddr2(String extendedAddr2) {
            params.addOpt("extended_addr2", extendedAddr2);
            return this;
        }


        public UpdateRequest city(String city) {
            params.addOpt("city", city);
            return this;
        }


        public UpdateRequest stateCode(String stateCode) {
            params.addOpt("state_code", stateCode);
            return this;
        }


        public UpdateRequest state(String state) {
            params.addOpt("state", state);
            return this;
        }


        public UpdateRequest zip(String zip) {
            params.addOpt("zip", zip);
            return this;
        }


        public UpdateRequest country(String country) {
            params.addOpt("country", country);
            return this;
        }


        public UpdateRequest validationStatus(com.chargebee.models.enums.ValidationStatus validationStatus) {
            params.addOpt("validation_status", validationStatus);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
