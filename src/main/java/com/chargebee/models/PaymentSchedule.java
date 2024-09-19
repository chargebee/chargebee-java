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

public class PaymentSchedule extends Resource<PaymentSchedule> {

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

    public PaymentSchedule(String jsonStr) {
        super(jsonStr);
    }

    public PaymentSchedule(JSONObject jsonObj) {
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
        return reqString("entity_id");
    }

    public Long amount() {
        return optLong("amount");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public String currencyCode() {
        return optString("currency_code");
    }

    public List<PaymentSchedule.ScheduleEntry> scheduleEntries() {
        return optList("schedule_entries", PaymentSchedule.ScheduleEntry.class);
    }

    // Operations
    //===========


}
