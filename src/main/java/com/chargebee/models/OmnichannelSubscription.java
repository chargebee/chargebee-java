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

    public static class OmnichannelSubscriptionItem extends Resource<OmnichannelSubscriptionItem> {
        public enum Status {
             ACTIVE,EXPIRED,CANCELLED,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum ExpirationReason {
             BILLING_ERROR,PRODUCT_NOT_AVAILABLE,OTHER,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum CancellationReason {
             CUSTOMER_CANCELLED,CUSTOMER_DID_NOT_CONSENT_TO_PRICE_INCREASE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public OmnichannelSubscriptionItem(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public String idAtSource() {
            return reqString("id_at_source");
        }

        public Status status() {
            return reqEnum("status", Status.class);
        }

        public Timestamp currentTermStart() {
            return optTimestamp("current_term_start");
        }

        public Timestamp currentTermEnd() {
            return optTimestamp("current_term_end");
        }

        public Timestamp expiredAt() {
            return optTimestamp("expired_at");
        }

        public ExpirationReason expirationReason() {
            return optEnum("expiration_reason", ExpirationReason.class);
        }

        public Timestamp cancelledAt() {
            return optTimestamp("cancelled_at");
        }

        public CancellationReason cancellationReason() {
            return optEnum("cancellation_reason", CancellationReason.class);
        }

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

    public List<OmnichannelSubscription.OmnichannelSubscriptionItem> omnichannelSubscriptionItems() {
        return reqList("omnichannel_subscription_items", OmnichannelSubscription.OmnichannelSubscriptionItem.class);
    }

    // Operations
    //===========

    public static Request retrieve(String id) {
        String uri = uri("omnichannel_subscriptions", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static ListRequest list() {
        String uri = uri("omnichannel_subscriptions");
        return new ListRequest(uri);
    }

    public static ListRequest omnichannelTransactionsForOmnichannelSubscription(String id) {
        String uri = uri("omnichannel_subscriptions", nullCheck(id), "omnichannel_transactions");
        return new ListRequest(uri);
    }


}
