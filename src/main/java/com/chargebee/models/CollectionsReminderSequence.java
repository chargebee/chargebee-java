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

public class CollectionsReminderSequence extends Resource<CollectionsReminderSequence> {

    public enum Status {
        ACTIVE,
        COMPLETED,
        PAUSED,
        FAILED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class ActionLog extends Resource<ActionLog> {
        public enum ActionType {
             SEND_EMAIL,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum Status {
             COMPLETED,SCHEDULED,SKIPPED,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum ReasonCode {
             DATE_ALREADY_PASSED,BEFORE_INVOICE_DATE,TRIGGER_EVENT_NOT_RECEIVED,SEQUENCE_PAUSED,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public ActionLog(JSONObject jsonObj) {
            super(jsonObj);
        }

        public ActionType actionType() {
            return reqEnum("action_type", ActionType.class);
        }

        public Status status() {
            return reqEnum("status", Status.class);
        }

        public ReasonCode reasonCode() {
            return optEnum("reason_code", ReasonCode.class);
        }

        public Timestamp executedAt() {
            return optTimestamp("executed_at");
        }

        public Timestamp scheduledAt() {
            return optTimestamp("scheduled_at");
        }

    }

    //Constructors
    //============

    public CollectionsReminderSequence(String jsonStr) {
        super(jsonStr);
    }

    public CollectionsReminderSequence(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String invoiceId() {
        return reqString("invoice_id");
    }

    public String customerId() {
        return reqString("customer_id");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp pausedUntil() {
        return optTimestamp("paused_until");
    }

    public String pauseNote() {
        return optString("pause_note");
    }

    public Timestamp startedAt() {
        return optTimestamp("started_at");
    }

    public Timestamp completedAt() {
        return optTimestamp("completed_at");
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

    public List<CollectionsReminderSequence.ActionLog> actionLogs() {
        return optList("action_logs", CollectionsReminderSequence.ActionLog.class);
    }

    // Operations
    //===========


}
