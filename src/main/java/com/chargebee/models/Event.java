package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class Event extends Resource<Event> {

    public enum WebhookStatus {
        NOT_CONFIGURED,
        NOT_APPLICABLE,
        SCHEDULED,
        SUCCEEDED,
        RE_SCHEDULED,
        FAILED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Event(InputStream is) throws IOException {
        super(is);
    }

    public Event(BufferedReader rd) throws IOException {
        super(rd);
    }

    public Event(String jsonStr) {
        super(jsonStr);
    }

    public Event(JSONObject jsonObj) {
        super(jsonObj);
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

    public WebhookStatus webhookStatus() {
        return reqEnum("webhook_status", WebhookStatus.class);
    }

    public String webhookFailureReason() {
        return optString("webhook_failure_reason");
    }

    public EventType eventType() {
        return optEnum("event_type", EventType.class);
    }

    // Operations
    //===========

    public static EventListRequest list() throws IOException {
        String uri = uri("events");
        return new EventListRequest(uri);
    }

    public static Request retrieve(String id) throws IOException {
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
    
        public EventListRequest limit(Integer limit) {
            params.addOpt("limit", limit);
            return this;
        }


        public EventListRequest offset(String offset) {
            params.addOpt("offset", offset);
            return this;
        }


        public EventListRequest startTime(Timestamp startTime) {
            params.addOpt("start_time", startTime);
            return this;
        }


        public EventListRequest endTime(Timestamp endTime) {
            params.addOpt("end_time", endTime);
            return this;
        }


        public EventListRequest webhookStatus(WebhookStatus webhookStatus) {
            params.addOpt("webhook_status", webhookStatus);
            return this;
        }


        public EventListRequest eventType(EventType eventType) {
            params.addOpt("event_type", eventType);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
