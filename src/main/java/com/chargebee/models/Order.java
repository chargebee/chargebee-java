package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class Order extends Resource<Order> {

    public enum Status {
        NEW,
        PROCESSING,
        COMPLETE,
        CANCELLED,
        VOIDED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Order(String jsonStr) {
        super(jsonStr);
    }

    public Order(JSONObject jsonObj) {
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

    public Status status() {
        return optEnum("status", Status.class);
    }

    public String referenceId() {
        return optString("reference_id");
    }

    public String fulfillmentStatus() {
        return optString("fulfillment_status");
    }

    public String note() {
        return optString("note");
    }

    public String trackingId() {
        return optString("tracking_id");
    }

    public String batchId() {
        return optString("batch_id");
    }

    public String createdBy() {
        return optString("created_by");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp statusUpdateAt() {
        return reqTimestamp("status_update_at");
    }

    // Operations
    //===========

    public static CreateRequest create() throws IOException {
        String uri = uri("orders");
        return new CreateRequest(Method.POST, uri);
    }

    public static UpdateRequest update(String id) throws IOException {
        String uri = uri("orders", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) throws IOException {
        String uri = uri("orders", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static ListRequest list() throws IOException {
        String uri = uri("orders");
        return new ListRequest(uri);
    }

    public static ListRequest ordersForInvoice(String id) throws IOException {
        String uri = uri("invoices", nullCheck(id), "orders");
        return new ListRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }


        public CreateRequest invoiceId(String invoiceId) {
            params.add("invoice_id", invoiceId);
            return this;
        }


        public CreateRequest status(Status status) {
            params.addOpt("status", status);
            return this;
        }


        public CreateRequest referenceId(String referenceId) {
            params.addOpt("reference_id", referenceId);
            return this;
        }


        public CreateRequest fulfillmentStatus(String fulfillmentStatus) {
            params.addOpt("fulfillment_status", fulfillmentStatus);
            return this;
        }


        public CreateRequest note(String note) {
            params.addOpt("note", note);
            return this;
        }


        public CreateRequest trackingId(String trackingId) {
            params.addOpt("tracking_id", trackingId);
            return this;
        }


        public CreateRequest batchId(String batchId) {
            params.addOpt("batch_id", batchId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateRequest extends Request<UpdateRequest> {

        private UpdateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateRequest status(Status status) {
            params.addOpt("status", status);
            return this;
        }


        public UpdateRequest referenceId(String referenceId) {
            params.addOpt("reference_id", referenceId);
            return this;
        }


        public UpdateRequest fulfillmentStatus(String fulfillmentStatus) {
            params.addOpt("fulfillment_status", fulfillmentStatus);
            return this;
        }


        public UpdateRequest note(String note) {
            params.addOpt("note", note);
            return this;
        }


        public UpdateRequest trackingId(String trackingId) {
            params.addOpt("tracking_id", trackingId);
            return this;
        }


        public UpdateRequest batchId(String batchId) {
            params.addOpt("batch_id", batchId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
