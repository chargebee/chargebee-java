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

public class Entitlement extends Resource<Entitlement> {

    public enum EntityType {
        PLAN,
        ADDON,
        CHARGE,
        PLAN_PRICE,
        ADDON_PRICE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Entitlement(String jsonStr) {
        super(jsonStr);
    }

    public Entitlement(JSONObject jsonObj) {
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

    public EntityType entityType() {
        return optEnum("entity_type", EntityType.class);
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

    // Operations
    //===========

    public static EntitlementListRequest list() {
        String uri = uri("entitlements");
        return new EntitlementListRequest(uri);
    }

    public static CreateRequest create() {
        String uri = uri("entitlements");
        return new CreateRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class EntitlementListRequest extends ListRequest<EntitlementListRequest> {

        private EntitlementListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<EntitlementListRequest> featureId() {
            return new StringFilter<EntitlementListRequest>("feature_id",this).supportsMultiOperators(true);        
        }


        public EnumFilter<Entitlement.EntityType, EntitlementListRequest> entityType() {
            return new EnumFilter<Entitlement.EntityType, EntitlementListRequest>("entity_type",this).supportsMultiOperators(true);        
        }


        public StringFilter<EntitlementListRequest> entityId() {
            return new StringFilter<EntitlementListRequest>("entity_id",this).supportsMultiOperators(true);        
        }


        @Deprecated
        public EntitlementListRequest includeDrafts(Boolean includeDrafts) {
            params.addOpt("include_drafts", includeDrafts);
            return this;
        }


        @Deprecated
        public EntitlementListRequest embed(String embed) {
            params.addOpt("embed", embed);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest action(com.chargebee.models.enums.Action action) {
            params.add("action", action);
            return this;
        }


        public CreateRequest changeReason(String changeReason) {
            params.addOpt("change_reason", changeReason);
            return this;
        }


        public CreateRequest entitlementEntityId(int index, String entitlementEntityId) {
            params.add("entitlements[entity_id][" + index + "]", entitlementEntityId);
            return this;
        }
        public CreateRequest entitlementFeatureId(int index, String entitlementFeatureId) {
            params.add("entitlements[feature_id][" + index + "]", entitlementFeatureId);
            return this;
        }
        public CreateRequest entitlementEntityType(int index, Entitlement.EntityType entitlementEntityType) {
            params.addOpt("entitlements[entity_type][" + index + "]", entitlementEntityType);
            return this;
        }
        public CreateRequest entitlementValue(int index, String entitlementValue) {
            params.addOpt("entitlements[value][" + index + "]", entitlementValue);
            return this;
        }
        public CreateRequest entitlementApplyGrandfathering(int index, Boolean entitlementApplyGrandfathering) {
            params.addOpt("entitlements[apply_grandfathering][" + index + "]", entitlementApplyGrandfathering);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

}
