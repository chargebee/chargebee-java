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
        IN_DUNNING,
        IN_GRACE_PERIOD,
        PAUSED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum AutoRenewStatus {
        OFF,
        ON,
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
        REFUNDED_DUE_TO_APP_ISSUE,
        REFUNDED_FOR_OTHER_REASON,
        MERCHANT_REVOKED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class UpcomingRenewal extends Resource<UpcomingRenewal> {
        public UpcomingRenewal(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String priceCurrency() {
            return optString("price_currency");
        }

        public Long priceUnits() {
            return optLong("price_units");
        }

        public Long priceNanos() {
            return optLong("price_nanos");
        }

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

    public String itemParentIdAtSource() {
        return optString("item_parent_id_at_source");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public AutoRenewStatus autoRenewStatus() {
        return optEnum("auto_renew_status", AutoRenewStatus.class);
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

    public Timestamp gracePeriodExpiresAt() {
        return optTimestamp("grace_period_expires_at");
    }

    public Boolean hasScheduledChanges() {
        return reqBoolean("has_scheduled_changes");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public OmnichannelSubscriptionItem.UpcomingRenewal upcomingRenewal() {
        return optSubResource("upcoming_renewal", OmnichannelSubscriptionItem.UpcomingRenewal.class);
    }

    // Operations
    //===========

    public static ListRequest listOmniSubItemScheduleChanges(String id) {
        String uri = uri("omnichannel_subscription_items", nullCheck(id), "scheduled_changes");
        return new ListRequest(uri);
    }


}
