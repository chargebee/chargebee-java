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

        public Long scheduledAmount() {
            return reqLong("scheduled_amount");
        }

        public Status status() {
            return reqEnum("status", Status.class);
        }

    }

    public static class ReferenceTransaction extends Resource<ReferenceTransaction> {
        public ReferenceTransaction(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String scheduleEntryId() {
            return reqString("schedule_entry_id");
        }

        public Long appliedAmount() {
            return optLong("applied_amount");
        }

        public String txnId() {
            return reqString("txn_id");
        }

        public Transaction.Status txnStatus() {
            return optEnum("txn_status", Transaction.Status.class);
        }

        public Timestamp txnDate() {
            return optTimestamp("txn_date");
        }

        public Long txnAmount() {
            return optLong("txn_amount");
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

    public List<PaymentSchedule.ReferenceTransaction> referenceTransactions() {
        return optList("reference_transactions", PaymentSchedule.ReferenceTransaction.class);
    }

    // Operations
    //===========

    public static PaymentScheduleListRequest list() {
        String uri = uri("payment_schedules");
        return new PaymentScheduleListRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class PaymentScheduleListRequest extends ListRequest<PaymentScheduleListRequest> {

        private PaymentScheduleListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<PaymentScheduleListRequest> invoiceId() {
            return new StringFilter<PaymentScheduleListRequest>("invoice_id",this).supportsMultiOperators(true);        
        }


        public StringFilter<PaymentScheduleListRequest> id() {
            return new StringFilter<PaymentScheduleListRequest>("id",this).supportsMultiOperators(true);        
        }


        public TimestampFilter<PaymentScheduleListRequest> updatedAt() {
            return new TimestampFilter<PaymentScheduleListRequest>("updated_at",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
