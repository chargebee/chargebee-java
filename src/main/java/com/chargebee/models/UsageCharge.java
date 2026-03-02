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

public class UsageCharge extends Resource<UsageCharge> {

    //Constructors
    //============

    public UsageCharge(String jsonStr) {
        super(jsonStr);
    }

    public UsageCharge(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String subscriptionId() {
        return reqString("subscription_id");
    }

    public String featureId() {
        return reqString("feature_id");
    }

    public String includedUsage() {
        return optString("included_usage");
    }

    public String totalUsage() {
        return optString("total_usage");
    }

    public String onDemandUsage() {
        return optString("on_demand_usage");
    }

    public String meteredItemPriceId() {
        return optString("metered_item_price_id");
    }

    public String amount() {
        return optString("amount");
    }

    public String currencyCode() {
        return optString("currency_code");
    }

    public Timestamp usageFrom() {
        return reqTimestamp("usage_from");
    }

    public Timestamp usageTo() {
        return reqTimestamp("usage_to");
    }

    // Operations
    //===========

    @Deprecated
    public static UsageChargeRetrieveUsageChargesForSubscriptionRequest retrieveUsageChargesForSubscription(String id) {
        String uri = uri("subscriptions", nullCheck(id), "usage_charges");
        return new UsageChargeRetrieveUsageChargesForSubscriptionRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class UsageChargeRetrieveUsageChargesForSubscriptionRequest extends ListRequest<UsageChargeRetrieveUsageChargesForSubscriptionRequest> {

        private UsageChargeRetrieveUsageChargesForSubscriptionRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<UsageChargeRetrieveUsageChargesForSubscriptionRequest> featureId() {
            return new StringFilter<UsageChargeRetrieveUsageChargesForSubscriptionRequest>("feature_id",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
