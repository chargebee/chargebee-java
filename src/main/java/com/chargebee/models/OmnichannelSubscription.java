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

public class OmnichannelSubscription extends Resource<OmnichannelSubscription> {

    public enum Source {
        APPLE_APP_STORE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public OmnichannelSubscription(String jsonStr) {
        super(jsonStr);
    }

    public OmnichannelSubscription(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String idAtSource() {
        return reqString("id_at_source");
    }

    public String appId() {
        return reqString("app_id");
    }

    public Source source() {
        return reqEnum("source", Source.class);
    }

    public String customerId() {
        return optString("customer_id");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public List<OmnichannelSubscriptionItem> omnichannelSubscriptionItems() {
        return reqList("omnichannel_subscription_items", OmnichannelSubscriptionItem.class);
    }

    // Operations
    //===========

    public static Request retrieve(String id) {
        String uri = uri("omnichannel_subscriptions", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static OmnichannelSubscriptionListRequest list() {
        String uri = uri("omnichannel_subscriptions");
        return new OmnichannelSubscriptionListRequest(uri);
    }

    public static ListRequest omnichannelTransactionsForOmnichannelSubscription(String id) {
        String uri = uri("omnichannel_subscriptions", nullCheck(id), "omnichannel_transactions");
        return new ListRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class OmnichannelSubscriptionListRequest extends ListRequest<OmnichannelSubscriptionListRequest> {

        private OmnichannelSubscriptionListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<OmnichannelSubscriptionListRequest> customerId() {
            return new StringFilter<OmnichannelSubscriptionListRequest>("customer_id",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
