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

public class OmnichannelSubscriptionItem extends Resource<OmnichannelSubscriptionItem> {

    public enum Status {
        ACTIVE,
        EXPIRED,
        CANCELLED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum ExpirationReason {
        BILLING_ERROR,
        PRODUCT_NOT_AVAILABLE,
        OTHER,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum CancellationReason {
        CUSTOMER_CANCELLED,
        CUSTOMER_DID_NOT_CONSENT_TO_PRICE_INCREASE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public OmnichannelSubscriptionItem(String jsonStr) {
        super(jsonStr);
    }

    public OmnichannelSubscriptionItem(JSONObject jsonObj) {
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

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    // Operations
    //===========


}
