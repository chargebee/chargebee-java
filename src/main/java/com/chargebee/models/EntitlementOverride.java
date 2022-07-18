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

public class EntitlementOverride extends Resource<EntitlementOverride> {

    //Constructors
    //============

    public EntitlementOverride(String jsonStr) {
        super(jsonStr);
    }

    public EntitlementOverride(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String entityId() {
        return optString("entity_id");
    }

    public String entityType() {
        return optString("entity_type");
    }

    public String featureId() {
        return optString("feature_id");
    }

    public String featureName() {
        return optString("feature_name");
    }

    public String value() {
        return optString("value");
    }

    public String name() {
        return optString("name");
    }

    public Timestamp expiresAt() {
        return optTimestamp("expires_at");
    }

    // Operations
    //===========

    public static AddEntitlementOverrideForSubscriptionRequest addEntitlementOverrideForSubscription(String id) {
        String uri = uri("subscriptions", nullCheck(id), "entitlement_overrides");
        return new AddEntitlementOverrideForSubscriptionRequest(Method.POST, uri);
    }

    public static EntitlementOverrideListEntitlementOverrideForSubscriptionRequest listEntitlementOverrideForSubscription(String id) {
        String uri = uri("subscriptions", nullCheck(id), "entitlement_overrides");
        return new EntitlementOverrideListEntitlementOverrideForSubscriptionRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class AddEntitlementOverrideForSubscriptionRequest extends Request<AddEntitlementOverrideForSubscriptionRequest> {

        private AddEntitlementOverrideForSubscriptionRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public AddEntitlementOverrideForSubscriptionRequest action(com.chargebee.models.enums.Action action) {
            params.addOpt("action", action);
            return this;
        }


        public AddEntitlementOverrideForSubscriptionRequest entitlementOverrideFeatureId(int index, String entitlementOverrideFeatureId) {
            params.add("entitlement_overrides[feature_id][" + index + "]", entitlementOverrideFeatureId);
            return this;
        }
        public AddEntitlementOverrideForSubscriptionRequest entitlementOverrideValue(int index, String entitlementOverrideValue) {
            params.addOpt("entitlement_overrides[value][" + index + "]", entitlementOverrideValue);
            return this;
        }
        public AddEntitlementOverrideForSubscriptionRequest entitlementOverrideExpiresAt(int index, Timestamp entitlementOverrideExpiresAt) {
            params.addOpt("entitlement_overrides[expires_at][" + index + "]", entitlementOverrideExpiresAt);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class EntitlementOverrideListEntitlementOverrideForSubscriptionRequest extends ListRequest<EntitlementOverrideListEntitlementOverrideForSubscriptionRequest> {

        private EntitlementOverrideListEntitlementOverrideForSubscriptionRequest(String uri) {
            super(uri);
        }
    
        @Deprecated
        public EntitlementOverrideListEntitlementOverrideForSubscriptionRequest embed(String embed) {
            params.addOpt("embed", embed);
            return this;
        }


        @Deprecated
        public EntitlementOverrideListEntitlementOverrideForSubscriptionRequest includeDrafts(Boolean includeDrafts) {
            params.addOpt("include_drafts", includeDrafts);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
