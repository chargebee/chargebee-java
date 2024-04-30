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

public class Installment extends Resource<Installment> {

    public enum Status {
        POSTED,
        PAYMENT_DUE,
        PAID,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Installment(String jsonStr) {
        super(jsonStr);
    }

    public Installment(JSONObject jsonObj) {
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

    // Operations
    //===========

    public static Request retrieve(String id) {
        String uri = uri("installments", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static InstallmentListRequest list() {
        String uri = uri("installments");
        return new InstallmentListRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class InstallmentListRequest extends ListRequest<InstallmentListRequest> {

        private InstallmentListRequest(String uri) {
            super(uri);
        }
    
        public InstallmentListRequest sortBy(String sortBy) {
            params.addOpt("sort_by", sortBy);
            return this;
        }


        public StringFilter<InstallmentListRequest> invoiceId() {
            return new StringFilter<InstallmentListRequest>("invoice_id",this).supportsMultiOperators(true);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
