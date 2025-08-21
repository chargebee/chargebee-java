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

public class CustomerEntitlement extends Resource<CustomerEntitlement> {

    //Constructors
    //============

    public CustomerEntitlement(String jsonStr) {
        super(jsonStr);
    }

    public CustomerEntitlement(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String customerId() {
        return reqString("customer_id");
    }

    public String subscriptionId() {
        return optString("subscription_id");
    }

    public String featureId() {
        return optString("feature_id");
    }

    public String value() {
        return optString("value");
    }

    public String name() {
        return optString("name");
    }

    public Boolean isEnabled() {
        return reqBoolean("is_enabled");
    }

    // Operations
    //===========

    public static CustomerEntitlementEntitlementsForCustomerRequest entitlementsForCustomer(String id) {
        String uri = uri("customers", nullCheck(id), "customer_entitlements");
        return new CustomerEntitlementEntitlementsForCustomerRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class CustomerEntitlementEntitlementsForCustomerRequest extends ListRequest<CustomerEntitlementEntitlementsForCustomerRequest> {

        private CustomerEntitlementEntitlementsForCustomerRequest(String uri) {
            super(uri);
        }
    
        public CustomerEntitlementEntitlementsForCustomerRequest consolidateEntitlements(Boolean consolidateEntitlements) {
            params.addOpt("consolidate_entitlements", consolidateEntitlements);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
