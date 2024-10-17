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

public class PaymentScheduleEstimate extends Resource<PaymentScheduleEstimate> {

    public enum EntityType {
        INVOICE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class ScheduleEntry extends Resource<ScheduleEntry> {
        public enum Status {
             POSTED,PAYMENT_DUE,PAID,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public ScheduleEntry(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public Timestamp date() {
            return reqTimestamp("date");
        }

        public Long amount() {
            return reqLong("amount");
        }

        public Status status() {
            return reqEnum("status", Status.class);
        }

    }

    //Constructors
    //============

    public PaymentScheduleEstimate(String jsonStr) {
        super(jsonStr);
    }

    public PaymentScheduleEstimate(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String schemeId() {
        return reqString("scheme_id");
    }

    public EntityType entityType() {
        return reqEnum("entity_type", EntityType.class);
    }

    public String entityId() {
        return optString("entity_id");
    }

    public Long amount() {
        return reqLong("amount");
    }

    public String currencyCode() {
        return optString("currency_code");
    }

    public List<PaymentScheduleEstimate.ScheduleEntry> scheduleEntries() {
        return optList("schedule_entries", PaymentScheduleEstimate.ScheduleEntry.class);
    }

    // Operations
    //===========


}
