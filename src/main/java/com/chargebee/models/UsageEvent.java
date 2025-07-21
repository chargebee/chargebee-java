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

public class UsageEvent extends Resource<UsageEvent> {

    //Constructors
    //============

    public UsageEvent(String jsonStr) {
        super(jsonStr);
    }

    public UsageEvent(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String subscriptionId() {
        return reqString("subscription_id");
    }

    public String deduplicationId() {
        return reqString("deduplication_id");
    }

    public Long usageTimestamp() {
        return reqLong("usage_timestamp");
    }

    public Map<String, Object> properties() {
        return reqMap( "properties");
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("usage_events");
        return new CreateRequest(Method.POST, uri).setIdempotency(false);
    }

    public static BatchIngestRequest batchIngest() {
        String uri = uri("batch", "usage_events");
        return new BatchIngestRequest(Method.POST, uri).setIdempotency(false);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri, null, "ingest",true);
        }
    
        public CreateRequest deduplicationId(String deduplicationId) {
            params.add("deduplication_id", deduplicationId);
            return this;
        }


        public CreateRequest subscriptionId(String subscriptionId) {
            params.add("subscription_id", subscriptionId);
            return this;
        }


        public CreateRequest usageTimestamp(Long usageTimestamp) {
            params.add("usage_timestamp", usageTimestamp);
            return this;
        }


        public CreateRequest properties(Map<String, Object> properties) {
            params.add("properties", properties);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class BatchIngestRequest extends Request<BatchIngestRequest> {

        private BatchIngestRequest(Method httpMeth, String uri) {
            super(httpMeth, uri, null, "ingest",true);
        }
    
        public BatchIngestRequest events(List<EventBatchIngestInputParams > array) {
            JSONArray jarray = new JSONArray();
            for (EventBatchIngestInputParams item : array) {
                jarray.put(item.toJObject());
            }
            params.add("events", jarray);
            return this;}
        
        @Override
        public Params params() {
            return params;
        }
    }

    public static class EventBatchIngestInputParams { 
        private final String DeduplicationId;
        private final String SubscriptionId;
        private final Timestamp UsageTimestamp;
        private final Map<String, Object> Properties;
        private EventBatchIngestInputParams(EventBatchIngestInputParamsBuilder builder){
            this.DeduplicationId = builder.DeduplicationId;
            this.SubscriptionId = builder.SubscriptionId;
            this.UsageTimestamp = builder.UsageTimestamp;
            this.Properties = builder.Properties;
            
        }
        public JSONObject toJObject(){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("deduplication_id", this.DeduplicationId);
            jsonObject.put("subscription_id", this.SubscriptionId);
            jsonObject.put("usage_timestamp", this.UsageTimestamp.getTime());
            jsonObject.put("properties", this.Properties);
            
            return jsonObject;
        }
    }
    public static class EventBatchIngestInputParamsBuilder { 
        private String DeduplicationId;
        private String SubscriptionId;
        private Timestamp UsageTimestamp;
        private Map<String, Object> Properties;
        
        public EventBatchIngestInputParamsBuilder setDeduplicationId( String DeduplicationId ) {
            this.DeduplicationId = DeduplicationId;
            return this;
        } 
        public EventBatchIngestInputParamsBuilder setSubscriptionId( String SubscriptionId ) {
            this.SubscriptionId = SubscriptionId;
            return this;
        } 
        public EventBatchIngestInputParamsBuilder setUsageTimestamp( Timestamp UsageTimestamp ) {
            this.UsageTimestamp = UsageTimestamp;
            return this;
        } 
        public EventBatchIngestInputParamsBuilder setProperties( Map<String, Object> Properties ) {
            this.Properties = Properties;
            return this;
        } 

        public EventBatchIngestInputParams build() {
            return new EventBatchIngestInputParams(this);
        }

    }
    
}
