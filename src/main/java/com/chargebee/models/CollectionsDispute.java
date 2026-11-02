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

public class CollectionsDispute extends Resource<CollectionsDispute> {

    public enum Status {
        OPEN,
        RESOLVED,
        WITHDRAWN,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public CollectionsDispute(String jsonStr) {
        super(jsonStr);
    }

    public CollectionsDispute(JSONObject jsonObj) {
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

    public String invoiceId() {
        return reqString("invoice_id");
    }

    public String lineItemId() {
        return optString("line_item_id");
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public Long billedAmount() {
        return reqLong("billed_amount");
    }

    public Long claimedDeduction() {
        return reqLong("claimed_deduction");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public String disputeReasonCode() {
        return reqString("dispute_reason_code");
    }

    public String resolvedReasonCode() {
        return optString("resolved_reason_code");
    }

    public String reason() {
        return optString("reason");
    }

    public Timestamp openedAt() {
        return reqTimestamp("opened_at");
    }

    public Timestamp resolvedAt() {
        return optTimestamp("resolved_at");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp updatedAt() {
        return reqTimestamp("updated_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Long undisputedAmount() {
        return optLong("undisputed_amount");
    }

    // Operations
    //===========


}
