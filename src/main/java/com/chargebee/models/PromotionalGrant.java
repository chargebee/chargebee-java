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

public class PromotionalGrant extends Resource<PromotionalGrant> {

    //Constructors
    //============

    public PromotionalGrant(String jsonStr) {
        super(jsonStr);
    }

    public PromotionalGrant(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String subscriptionId() {
        return reqString("subscription_id");
    }

    public String unitId() {
        return reqString("unit_id");
    }

    public String amount() {
        return reqString("amount");
    }

    public Timestamp expiresAt() {
        return reqTimestamp("expires_at");
    }

    public String metadata() {
        return optString("metadata");
    }

    // Operations
    //===========

    public static PromotionalGrantsRequest promotionalGrants() {
        String uri = uri("promotional_grants");
        return new PromotionalGrantsRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class PromotionalGrantsRequest extends Request<PromotionalGrantsRequest> {

        private PromotionalGrantsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri, null, null,true);
        }
    
        public PromotionalGrantsRequest subscriptionId(String subscriptionId) {
            params.add("subscription_id", subscriptionId);
            return this;
        }


        public PromotionalGrantsRequest unitId(String unitId) {
            params.add("unit_id", unitId);
            return this;
        }


        public PromotionalGrantsRequest amount(String amount) {
            params.add("amount", amount);
            return this;
        }


        public PromotionalGrantsRequest expiresAt(Timestamp expiresAt) {
            params.add("expires_at", expiresAt);
            return this;
        }


        public PromotionalGrantsRequest metadata(Map<String, Object> metadata) {
            params.addOpt("metadata", metadata);
            return this;
        }



        @Override
        public Params params() {
            return params;
        }
    }

}
