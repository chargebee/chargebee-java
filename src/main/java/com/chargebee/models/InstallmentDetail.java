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

public class InstallmentDetail extends Resource<InstallmentDetail> {

    public static class Installment extends Resource<Installment> {
        public enum Status {
             POSTED,PAYMENT_DUE,PAID,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public Installment(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public String invoiceId() {
            return reqString("invoice_id");
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

        public Timestamp createdAt() {
            return reqTimestamp("created_at");
        }

        public Long resourceVersion() {
            return optLong("resource_version");
        }

        public Timestamp updatedAt() {
            return optTimestamp("updated_at");
        }

    }

    //Constructors
    //============

    public InstallmentDetail(String jsonStr) {
        super(jsonStr);
    }

    public InstallmentDetail(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return optString("id");
    }

    public String invoiceId() {
        return optString("invoice_id");
    }

    public Long amount() {
        return optLong("amount");
    }

    public List<InstallmentDetail.Installment> installments() {
        return optList("installments", InstallmentDetail.Installment.class);
    }

    // Operations
    //===========


}
