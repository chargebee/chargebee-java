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

public class OfferFulfillment extends Resource<OfferFulfillment> {

    public enum ProcessingType {
        BILLING_UPDATE,
        CHECKOUT,
        URL_REDIRECT,
        WEBHOOK,
        EMAIL,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Status {
        IN_PROGRESS,
        COMPLETED,
        FAILED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class Error extends Resource<Error> {
        public enum Code {
             BILLING_UPDATE_FAILED,CHECKOUT_ABANDONED,EXTERNAL_FULFILLMENT_FAILED,INTERNAL_ERROR,FULFILLMENT_EXPIRED,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public Error(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Code code() {
            return reqEnum("code", Code.class);
        }

        public String message() {
            return reqString("message");
        }

    }

    //Constructors
    //============

    public OfferFulfillment(String jsonStr) {
        super(jsonStr);
    }

    public OfferFulfillment(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String personalizedOfferId() {
        return reqString("personalized_offer_id");
    }

    public String optionId() {
        return reqString("option_id");
    }

    public ProcessingType processingType() {
        return reqEnum("processing_type", ProcessingType.class);
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public String redirectUrl() {
        return optString("redirect_url");
    }

    public Timestamp failedAt() {
        return optTimestamp("failed_at");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp completedAt() {
        return optTimestamp("completed_at");
    }

    public OfferFulfillment.Error error() {
        return optSubResource("error", OfferFulfillment.Error.class);
    }

    // Operations
    //===========

    public static OfferFulfillmentsRequest offerFulfillments() {
        String uri = uri("offer_fulfillments");
        return new OfferFulfillmentsRequest(Method.POST, uri).setIdempotency(false);
    }

    public static Request offerFulfillmentsGet(String id) {
        String uri = uri("offer_fulfillments", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static OfferFulfillmentsUpdateRequest offerFulfillmentsUpdate(String id) {
        String uri = uri("offer_fulfillments", nullCheck(id));
        return new OfferFulfillmentsUpdateRequest(Method.POST, uri).setIdempotency(false);
    }


    // Operation Request Classes
    //==========================

    public static class OfferFulfillmentsRequest extends Request<OfferFulfillmentsRequest> {

        private OfferFulfillmentsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri, null, "grow",true);
        }
    
        public OfferFulfillmentsRequest personalizedOfferId(String personalizedOfferId) {
            params.add("personalized_offer_id", personalizedOfferId);
            return this;
        }


        public OfferFulfillmentsRequest optionId(String optionId) {
            params.add("option_id", optionId);
            return this;
        }



        @Override
        public Params params() {
            return params;
        }
    }

    public static class OfferFulfillmentsUpdateRequest extends Request<OfferFulfillmentsUpdateRequest> {

        private OfferFulfillmentsUpdateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri, null, "grow",true);
        }
    
        public OfferFulfillmentsUpdateRequest id(String id) {
            params.add("id", id);
            return this;
        }


        public OfferFulfillmentsUpdateRequest status(OfferFulfillment.Status status) {
            params.add("status", status);
            return this;
        }


        public OfferFulfillmentsUpdateRequest failureReason(String failureReason) {
            params.addOpt("failure_reason", failureReason);
            return this;
        }



        @Override
        public Params params() {
            return params;
        }
    }

}
