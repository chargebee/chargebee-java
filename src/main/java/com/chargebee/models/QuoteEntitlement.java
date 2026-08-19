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

public class QuoteEntitlement extends Resource<QuoteEntitlement> {

    public enum EntityType {
        PLAN_PRICE,
        ADDON_PRICE,
        CHARGE_PRICE,
        @Deprecated
        CHARGE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    @Deprecated
    public enum ActionType {
        UPSERT,
        REMOVE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public QuoteEntitlement(String jsonStr) {
        super(jsonStr);
    }

    public QuoteEntitlement(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String entityId() {
        return reqString("entity_id");
    }

    public EntityType entityType() {
        return reqEnum("entity_type", EntityType.class);
    }

    @Deprecated
    public ActionType actionType() {
        return reqEnum("action_type", ActionType.class);
    }

    public String featureId() {
        return reqString("feature_id");
    }

    public String value() {
        return optString("value");
    }

    public Boolean isEnabled() {
        return reqBoolean("is_enabled");
    }

    public Timestamp startDate() {
        return optTimestamp("start_date");
    }

    public Timestamp endDate() {
        return optTimestamp("end_date");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp modifiedAt() {
        return reqTimestamp("modified_at");
    }

    public Boolean isOverridden() {
        return optBoolean("is_overridden");
    }

    public String featureName() {
        return optString("feature_name");
    }

    public String featureUnit() {
        return optString("feature_unit");
    }

    public String featureType() {
        return optString("feature_type");
    }

    public String name() {
        return optString("name");
    }

    public Boolean metered() {
        return optBoolean("metered");
    }

    // Operations
    //===========

    public static QuoteEntitlementListQuoteEntitlementsRequest listQuoteEntitlements(String id) {
        String uri = uri("quotes", nullCheck(id), "quote_entitlements");
        return new QuoteEntitlementListQuoteEntitlementsRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class QuoteEntitlementListQuoteEntitlementsRequest extends ListRequest<QuoteEntitlementListQuoteEntitlementsRequest> {

        private QuoteEntitlementListQuoteEntitlementsRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<QuoteEntitlementListQuoteEntitlementsRequest> entityId() {
            return new StringFilter<QuoteEntitlementListQuoteEntitlementsRequest>("entity_id",this);        
        }


        public TimestampFilter<QuoteEntitlementListQuoteEntitlementsRequest> startDate() {
            return new TimestampFilter<QuoteEntitlementListQuoteEntitlementsRequest>("start_date",this);        
        }


        public TimestampFilter<QuoteEntitlementListQuoteEntitlementsRequest> endDate() {
            return new TimestampFilter<QuoteEntitlementListQuoteEntitlementsRequest>("end_date",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
