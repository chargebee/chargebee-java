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

public class RecordedPurchase extends Resource<RecordedPurchase> {

    public enum Source {
        APPLE_APP_STORE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Status {
        IN_PROCESS,
        COMPLETED,
        FAILED,
        IGNORED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class LinkedOmnichannelSubscription extends Resource<LinkedOmnichannelSubscription> {
        public LinkedOmnichannelSubscription(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String omnichannelSubscriptionId() {
            return optString("omnichannel_subscription_id");
        }

    }

    public static class ErrorDetail extends Resource<ErrorDetail> {
        public ErrorDetail(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String errorMessage() {
            return optString("error_message");
        }

    }

    //Constructors
    //============

    public RecordedPurchase(String jsonStr) {
        super(jsonStr);
    }

    public RecordedPurchase(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String customerId() {
        return reqString("customer_id");
    }

    public String appId() {
        return reqString("app_id");
    }

    public Source source() {
        return reqEnum("source", Source.class);
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public String omnichannelTransactionId() {
        return optString("omnichannel_transaction_id");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public List<RecordedPurchase.LinkedOmnichannelSubscription> linkedOmnichannelSubscriptions() {
        return optList("linked_omnichannel_subscriptions", RecordedPurchase.LinkedOmnichannelSubscription.class);
    }

    public RecordedPurchase.ErrorDetail errorDetail() {
        return optSubResource("error_detail", RecordedPurchase.ErrorDetail.class);
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("recorded_purchases");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("recorded_purchases", nullCheck(id));
        return new Request(Method.GET, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest appId(String appId) {
            params.add("app_id", appId);
            return this;
        }


        public CreateRequest customerId(String customerId) {
            params.add("customer[id]", customerId);
            return this;
        }

        public CreateRequest appleAppStoreTransactionId(String appleAppStoreTransactionId) {
            params.addOpt("apple_app_store[transaction_id]", appleAppStoreTransactionId);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

}
