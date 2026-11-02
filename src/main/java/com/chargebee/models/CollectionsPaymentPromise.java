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

public class CollectionsPaymentPromise extends Resource<CollectionsPaymentPromise> {

    public enum Status {
        ACTIVE,
        FULFILLED,
        BROKEN,
        CANCELLED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public CollectionsPaymentPromise(String jsonStr) {
        super(jsonStr);
    }

    public CollectionsPaymentPromise(JSONObject jsonObj) {
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

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public String reason() {
        return optString("reason");
    }

    public String promisedBy() {
        return optString("promised_by");
    }

    public Timestamp promisedOn() {
        return reqTimestamp("promised_on");
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

    // Operations
    //===========

    public static Request retrieve(String id) {
        String uri = uri("collections_payment_promises", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static CollectionsPaymentPromiseListCollectionsPaymentPromisesRequest listCollectionsPaymentPromises() {
        String uri = uri("collections_payment_promises");
        return new CollectionsPaymentPromiseListCollectionsPaymentPromisesRequest(uri);
    }

    public static CreateCollectionsPaymentPromiseRequest createCollectionsPaymentPromise() {
        String uri = uri("collections_payment_promises");
        return new CreateCollectionsPaymentPromiseRequest(Method.POST, uri).setIdempotency(false);
    }

    public static UpdateCollectionsPaymentPromiseRequest updateCollectionsPaymentPromise(String id) {
        String uri = uri("collections_payment_promises", nullCheck(id));
        return new UpdateCollectionsPaymentPromiseRequest(Method.POST, uri);
    }

    public static Request cancel(String id) {
        String uri = uri("collections_payment_promises", nullCheck(id), "cancel");
        return new Request(Method.POST, uri).setIdempotency(false);
    }


    // Operation Request Classes
    //==========================

    public static class CollectionsPaymentPromiseListCollectionsPaymentPromisesRequest extends ListRequest<CollectionsPaymentPromiseListCollectionsPaymentPromisesRequest> {

        private CollectionsPaymentPromiseListCollectionsPaymentPromisesRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<CollectionsPaymentPromiseListCollectionsPaymentPromisesRequest> customerId() {
            return new StringFilter<CollectionsPaymentPromiseListCollectionsPaymentPromisesRequest>("customer_id",this);        
        }


        public StringFilter<CollectionsPaymentPromiseListCollectionsPaymentPromisesRequest> invoiceId() {
            return new StringFilter<CollectionsPaymentPromiseListCollectionsPaymentPromisesRequest>("invoice_id",this);        
        }


        public EnumFilter<CollectionsPaymentPromise.Status, CollectionsPaymentPromiseListCollectionsPaymentPromisesRequest> status() {
            return new EnumFilter<CollectionsPaymentPromise.Status, CollectionsPaymentPromiseListCollectionsPaymentPromisesRequest>("status",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateCollectionsPaymentPromiseRequest extends Request<CreateCollectionsPaymentPromiseRequest> {

        private CreateCollectionsPaymentPromiseRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateCollectionsPaymentPromiseRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public CreateCollectionsPaymentPromiseRequest invoiceId(String invoiceId) {
            params.addOpt("invoice_id", invoiceId);
            return this;
        }


        public CreateCollectionsPaymentPromiseRequest amount(Long amount) {
            params.add("amount", amount);
            return this;
        }


        public CreateCollectionsPaymentPromiseRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public CreateCollectionsPaymentPromiseRequest promisedOn(Timestamp promisedOn) {
            params.add("promised_on", promisedOn);
            return this;
        }


        public CreateCollectionsPaymentPromiseRequest reason(String reason) {
            params.addOpt("reason", reason);
            return this;
        }


        public CreateCollectionsPaymentPromiseRequest promisedBy(String promisedBy) {
            params.addOpt("promised_by", promisedBy);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateCollectionsPaymentPromiseRequest extends Request<UpdateCollectionsPaymentPromiseRequest> {

        private UpdateCollectionsPaymentPromiseRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateCollectionsPaymentPromiseRequest promisedOn(Timestamp promisedOn) {
            params.addOpt("promised_on", promisedOn);
            return this;
        }


        public UpdateCollectionsPaymentPromiseRequest reason(String reason) {
            params.addOpt("reason", reason);
            return this;
        }


        public UpdateCollectionsPaymentPromiseRequest promisedBy(String promisedBy) {
            params.addOpt("promised_by", promisedBy);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
