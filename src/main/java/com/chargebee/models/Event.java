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

public class Event extends Resource<Event> {

    @Deprecated
    public enum WebhookStatus {
        NOT_CONFIGURED,
        SCHEDULED,
        SUCCEEDED,
        RE_SCHEDULED,
        FAILED,
        SKIPPED,
        NOT_APPLICABLE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class Webhook extends Resource<Webhook> {
        public enum WebhookStatus {
             NOT_CONFIGURED,SCHEDULED,SUCCEEDED,RE_SCHEDULED,FAILED,SKIPPED,NOT_APPLICABLE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public Webhook(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public WebhookStatus webhookStatus() {
            return reqEnum("webhook_status", WebhookStatus.class);
        }

    }

    //Constructors
    //============

    public Event(InputStream is) throws IOException {
        super(is);
        apiVersionCheck(jsonObj);
    }

    public Event(BufferedReader rd) throws IOException {
        super(rd);
        apiVersionCheck(jsonObj);
    }

    public Event(String jsonStr) {
        super(jsonStr);
        apiVersionCheck(jsonObj);
    }

    public Event(JSONObject jsonObj) {
        super(jsonObj);
        apiVersionCheck(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public Timestamp occurredAt() {
        return reqTimestamp("occurred_at");
    }

    public Source source() {
        return reqEnum("source", Source.class);
    }

    public String user() {
        return optString("user");
    }

    @Deprecated
    public WebhookStatus webhookStatus() {
        return reqEnum("webhook_status", WebhookStatus.class);
    }

    @Deprecated
    public String webhookFailureReason() {
        return optString("webhook_failure_reason");
    }

    public List<Event.Webhook> webhooks() {
        return optList("webhooks", Event.Webhook.class);
    }

    public EventType eventType() {
        return optEnum("event_type", EventType.class);
    }

    public ApiVersion apiVersion() {
        return optEnum("api_version", ApiVersion.class);
    }

    // Operations
    //===========

    public static EventListRequest list() {
        String uri = uri("events");
        return new EventListRequest(uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("events", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static class Content extends ResultBase{

        public Content(JSONObject jsonObj) {
            super(jsonObj);
        }
    }

    public Content content(){
        return new Content(optJSONObject("content"));
    }

    // Operation Request Classes
    //==========================

    public static class EventListRequest extends ListRequest<EventListRequest> {

        private EventListRequest(String uri) {
            super(uri);
        }
    
        @Deprecated
        public EventListRequest startTime(Timestamp startTime) {
            params.addOpt("start_time", startTime);
            return this;
        }


        @Deprecated
        public EventListRequest endTime(Timestamp endTime) {
            params.addOpt("end_time", endTime);
            return this;
        }






        public StringFilter<EventListRequest> id() {
            return new StringFilter<EventListRequest>("id",this).supportsMultiOperators(true);        
        }


        public EnumFilter<WebhookStatus, EventListRequest> webhookStatus() {
            return new EnumFilter<WebhookStatus, EventListRequest>("webhook_status",this);        
        }
        @Deprecated
        public EventListRequest webhookStatus(WebhookStatus webhookStatus) {
            params.addOpt("webhook_status", webhookStatus);
            return this;
        }


        public EnumFilter<com.chargebee.models.enums.EventType, EventListRequest> eventType() {
            return new EnumFilter<com.chargebee.models.enums.EventType, EventListRequest>("event_type",this);        
        }
        @Deprecated
        public EventListRequest eventType(com.chargebee.models.enums.EventType eventType) {
            params.addOpt("event_type", eventType);
            return this;
        }


        public EnumFilter<com.chargebee.models.enums.Source, EventListRequest> source() {
            return new EnumFilter<com.chargebee.models.enums.Source, EventListRequest>("source",this);        
        }


        public TimestampFilter<EventListRequest> occurredAt() {
            return new TimestampFilter<EventListRequest>("occurred_at",this);        
        }


        public EventListRequest sortByOccurredAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","occurred_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
