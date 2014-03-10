package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class Customer extends Resource<Customer> {

    public enum CardStatus {
        NO_CARD,
        VALID,
        EXPIRING,
        EXPIRED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class BillingAddress extends Resource<BillingAddress> {
        public BillingAddress(JSONObject jsonObj) {
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

        public String state() {
            return optString("state");
        }

        public String country() {
            return optString("country");
        }

        public String zip() {
            return optString("zip");
        }

    }

    //Constructors
    //============

    public Customer(String jsonStr) {
        super(jsonStr);
    }

    public Customer(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
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

    public String phone() {
        return optString("phone");
    }

    public String company() {
        return optString("company");
    }

    public String vatNumber() {
        return optString("vat_number");
    }

    public AutoCollection autoCollection() {
        return reqEnum("auto_collection", AutoCollection.class);
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public CardStatus cardStatus() {
        return optEnum("card_status", CardStatus.class);
    }

    public Customer.BillingAddress billingAddress() {
        return optSubResource("billing_address", Customer.BillingAddress.class);
    }

    // Operations
    //===========

    public static ListRequest list() throws IOException {
        String uri = uri("customers");
        return new ListRequest(uri);
    }

    public static Request retrieve(String id) throws IOException {
        String uri = uri("customers", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static UpdateRequest update(String id) throws IOException {
        String uri = uri("customers", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static UpdateBillingInfoRequest updateBillingInfo(String id) throws IOException {
        String uri = uri("customers", nullCheck(id), "update_billing_info");
        return new UpdateBillingInfoRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class UpdateRequest extends Request<UpdateRequest> {

        private UpdateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
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


        public UpdateRequest phone(String phone) {
            params.addOpt("phone", phone);
            return this;
        }


        public UpdateRequest company(String company) {
            params.addOpt("company", company);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateBillingInfoRequest extends Request<UpdateBillingInfoRequest> {

        private UpdateBillingInfoRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateBillingInfoRequest vatNumber(String vatNumber) {
            params.addOpt("vat_number", vatNumber);
            return this;
        }


        public UpdateBillingInfoRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

}
