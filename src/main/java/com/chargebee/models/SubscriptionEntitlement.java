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

public class SubscriptionEntitlement extends Resource<SubscriptionEntitlement> {

    public static class Component extends Resource<Component> {
        public Component(JSONObject jsonObj) {
            super(jsonObj);
        }

        public EntitlementOverride entitlementOverrides() {
            return optSubResource("entitlement_overrides", EntitlementOverride.class);
        }

    }

    //Constructors
    //============

    public SubscriptionEntitlement(String jsonStr) {
        super(jsonStr);
    }

    public SubscriptionEntitlement(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String subscriptionId() {
        return reqString("subscription_id");
    }

    public String featureId() {
        return optString("feature_id");
    }

    public String featureName() {
        return optString("feature_name");
    }

    public String featureUnit() {
        return optString("feature_unit");
    }

    public String value() {
        return optString("value");
    }

    public String name() {
        return optString("name");
    }

    public Boolean isOverridden() {
        return reqBoolean("is_overridden");
    }

    public Boolean isEnabled() {
        return reqBoolean("is_enabled");
    }

    public Timestamp expiresAt() {
        return optTimestamp("expires_at");
    }

    public SubscriptionEntitlement.Component components() {
        return optSubResource("components", SubscriptionEntitlement.Component.class);
    }

    // Operations
    //===========

    public static SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionRequest subscriptionEntitlementsForSubscription(String id) {
        String uri = uri("subscriptions", nullCheck(id), "subscription_entitlements");
        return new SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionRequest(uri);
    }

    public static SetSubscriptionEntitlementAvailabilityRequest setSubscriptionEntitlementAvailability(String id) {
        String uri = uri("subscriptions", nullCheck(id), "subscription_entitlements/set_availability");
        return new SetSubscriptionEntitlementAvailabilityRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionRequest extends ListRequest<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionRequest> {

        private SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionRequest(String uri) {
            super(uri);
        }
    
        @Deprecated
        public SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionRequest includeDrafts(Boolean includeDrafts) {
            params.addOpt("include_drafts", includeDrafts);
            return this;
        }


        @Deprecated
        public SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionRequest embed(String embed) {
            params.addOpt("embed", embed);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class SetSubscriptionEntitlementAvailabilityRequest extends Request<SetSubscriptionEntitlementAvailabilityRequest> {

        private SetSubscriptionEntitlementAvailabilityRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public SetSubscriptionEntitlementAvailabilityRequest isEnabled(Boolean isEnabled) {
            params.add("is_enabled", isEnabled);
            return this;
        }


        public SetSubscriptionEntitlementAvailabilityRequest subscriptionEntitlementFeatureId(int index, String subscriptionEntitlementFeatureId) {
            params.add("subscription_entitlements[feature_id][" + index + "]", subscriptionEntitlementFeatureId);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

}
