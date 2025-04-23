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

public class OmnichannelSubscriptionItemScheduledChange extends Resource<OmnichannelSubscriptionItemScheduledChange> {

    public enum ChangeType {
        DOWNGRADE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class CurrentState extends Resource<CurrentState> {
        public CurrentState(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String itemIdAtSource() {
            return optString("item_id_at_source");
        }

    }

    public static class ScheduledState extends Resource<ScheduledState> {
        public ScheduledState(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String itemIdAtSource() {
            return optString("item_id_at_source");
        }

    }

    //Constructors
    //============

    public OmnichannelSubscriptionItemScheduledChange(String jsonStr) {
        super(jsonStr);
    }

    public OmnichannelSubscriptionItemScheduledChange(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return optString("id");
    }

    public String omnichannelSubscriptionItemId() {
        return optString("omnichannel_subscription_item_id");
    }

    public Timestamp scheduledAt() {
        return reqTimestamp("scheduled_at");
    }

    public ChangeType changeType() {
        return reqEnum("change_type", ChangeType.class);
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp modifiedAt() {
        return reqTimestamp("modified_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public OmnichannelSubscriptionItemScheduledChange.CurrentState currentState() {
        return optSubResource("current_state", OmnichannelSubscriptionItemScheduledChange.CurrentState.class);
    }

    public OmnichannelSubscriptionItemScheduledChange.ScheduledState scheduledState() {
        return optSubResource("scheduled_state", OmnichannelSubscriptionItemScheduledChange.ScheduledState.class);
    }

    // Operations
    //===========


}
