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

public class OfferEvent extends Resource<OfferEvent> {

    public enum Type {
        VIEWED,
        DISMISSED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public OfferEvent(String jsonStr) {
        super(jsonStr);
    }

    public OfferEvent(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    // Operations
    //===========

    public static OfferEventsRequest offerEvents() {
        String uri = uri("offer_events");
        return new OfferEventsRequest(Method.POST, uri).setIdempotency(false);
    }


    // Operation Request Classes
    //==========================

    public static class OfferEventsRequest extends Request<OfferEventsRequest> {

        private OfferEventsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri, null, "grow",true);
        }
    
        public OfferEventsRequest personalizedOfferId(String personalizedOfferId) {
            params.add("personalized_offer_id", personalizedOfferId);
            return this;
        }


        public OfferEventsRequest type(Type type) {
            params.add("type", type);
            return this;
        }



        @Override
        public Params params() {
            return params;
        }
    }

}
