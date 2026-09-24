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

public class Dispute extends Resource<Dispute> {

    public enum Status {
        INITIATED,
        FUNDS_WITHDRAWN,
        IN_REVIEW,
        CANCELLED,
        LOST,
        WON,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Type {
        CHARGEBACK,
        INQUIRY,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Dispute(String jsonStr) {
        super(jsonStr);
    }

    public Dispute(JSONObject jsonObj) {
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

    public String transactionId() {
        return reqString("transaction_id");
    }

    public String gatewayAccountId() {
        return reqString("gateway_account_id");
    }

    public String idAtGateway() {
        return optString("id_at_gateway");
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public Long amount() {
        return reqLong("amount");
    }

    public String reason() {
        return optString("reason");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public Boolean isPartialDispute() {
        return reqBoolean("is_partial_dispute");
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
        String uri = uri("disputes", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static DisputeListRequest list() {
        String uri = uri("disputes");
        return new DisputeListRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class DisputeListRequest extends ListRequest<DisputeListRequest> {

        private DisputeListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<DisputeListRequest> id() {
            return new StringFilter<DisputeListRequest>("id",this).supportsMultiOperators(true);        
        }


        public EnumFilter<Dispute.Status, DisputeListRequest> status() {
            return new EnumFilter<Dispute.Status, DisputeListRequest>("status",this);        
        }


        public EnumFilter<Dispute.Type, DisputeListRequest> type() {
            return new EnumFilter<Dispute.Type, DisputeListRequest>("type",this);        
        }


        public StringFilter<DisputeListRequest> customerId() {
            return new StringFilter<DisputeListRequest>("customer_id",this).supportsMultiOperators(true);        
        }


        public StringFilter<DisputeListRequest> transactionId() {
            return new StringFilter<DisputeListRequest>("transaction_id",this).supportsMultiOperators(true);        
        }


        public NumberFilter<Long, DisputeListRequest> amount() {
            return new NumberFilter<Long, DisputeListRequest>("amount",this);        
        }


        public TimestampFilter<DisputeListRequest> createdAt() {
            return new TimestampFilter<DisputeListRequest>("created_at",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
