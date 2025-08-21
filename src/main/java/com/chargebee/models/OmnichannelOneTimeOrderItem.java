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

public class OmnichannelOneTimeOrderItem extends Resource<OmnichannelOneTimeOrderItem> {

    public enum CancellationReason {
        CUSTOMER_CANCELLED,
        CUSTOMER_DID_NOT_CONSENT_TO_PRICE_INCREASE,
        REFUNDED_DUE_TO_APP_ISSUE,
        REFUNDED_FOR_OTHER_REASON,
        MERCHANT_REVOKED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public OmnichannelOneTimeOrderItem(String jsonStr) {
        super(jsonStr);
    }

    public OmnichannelOneTimeOrderItem(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String itemIdAtSource() {
        return reqString("item_id_at_source");
    }

    public String itemTypeAtSource() {
        return optString("item_type_at_source");
    }

    public Integer quantity() {
        return optInteger("quantity");
    }

    public Timestamp cancelledAt() {
        return optTimestamp("cancelled_at");
    }

    public CancellationReason cancellationReason() {
        return optEnum("cancellation_reason", CancellationReason.class);
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    // Operations
    //===========


}
