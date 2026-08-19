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

public class OmnichannelSubscriptionItemMetric extends Resource<OmnichannelSubscriptionItemMetric> {

    //Constructors
    //============

    public OmnichannelSubscriptionItemMetric(String jsonStr) {
        super(jsonStr);
    }

    public OmnichannelSubscriptionItemMetric(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String customerId() {
        return optString("customer_id");
    }

    public String omnichannelSubscriptionId() {
        return optString("omnichannel_subscription_id");
    }

    public String omnichannelSubscriptionItemId() {
        return optString("omnichannel_subscription_item_id");
    }

    public String itemIdAtSource() {
        return reqString("item_id_at_source");
    }

    public String mrrCurrency() {
        return optString("mrr_currency");
    }

    public Long mrrUnits() {
        return optLong("mrr_units");
    }

    public Long mrrNanos() {
        return optLong("mrr_nanos");
    }

    public Timestamp effectiveFrom() {
        return reqTimestamp("effective_from");
    }

    public Timestamp calculatedAt() {
        return optTimestamp("calculated_at");
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
