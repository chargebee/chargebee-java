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

public class UsageSummary extends Resource<UsageSummary> {

    //Constructors
    //============

    public UsageSummary(String jsonStr) {
        super(jsonStr);
    }

    public UsageSummary(JSONObject jsonObj) {
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

    public String aggregatedValue() {
        return reqString("aggregated_value");
    }

    public Timestamp aggregatedFrom() {
        return reqTimestamp("aggregated_from");
    }

    public Timestamp aggregatedTo() {
        return reqTimestamp("aggregated_to");
    }

    // Operations
    //===========

    @Deprecated
    public static UsageSummaryRetrieveUsageSummaryForSubscriptionRequest retrieveUsageSummaryForSubscription(String id) {
        String uri = uri("subscriptions", nullCheck(id), "usage_summary");
        return new UsageSummaryRetrieveUsageSummaryForSubscriptionRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class UsageSummaryRetrieveUsageSummaryForSubscriptionRequest extends ListRequest<UsageSummaryRetrieveUsageSummaryForSubscriptionRequest> {

        private UsageSummaryRetrieveUsageSummaryForSubscriptionRequest(String uri) {
            super(uri);
        }
    
        public UsageSummaryRetrieveUsageSummaryForSubscriptionRequest featureId(String featureId) {
            params.add("feature_id", featureId);
            return this;
        }


        public UsageSummaryRetrieveUsageSummaryForSubscriptionRequest windowSize(com.chargebee.models.enums.WindowSize windowSize) {
            params.addOpt("window_size", windowSize);
            return this;
        }


        public UsageSummaryRetrieveUsageSummaryForSubscriptionRequest timeframeStart(Timestamp timeframeStart) {
            params.addOpt("timeframe_start", timeframeStart);
            return this;
        }


        public UsageSummaryRetrieveUsageSummaryForSubscriptionRequest timeframeEnd(Timestamp timeframeEnd) {
            params.addOpt("timeframe_end", timeframeEnd);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
