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

public class WebhookEndpoint extends Resource<WebhookEndpoint> {

    public enum ApiVersion {
        V1,
        V2,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public WebhookEndpoint(String jsonStr) {
        super(jsonStr);
    }

    public WebhookEndpoint(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String name() {
        return reqString("name");
    }

    public String url() {
        return reqString("url");
    }

    public Boolean sendCardResource() {
        return optBoolean("send_card_resource");
    }

    public Boolean disabled() {
        return reqBoolean("disabled");
    }

    public Boolean primaryUrl() {
        return reqBoolean("primary_url");
    }

    public ApiVersion apiVersion() {
        return reqEnum("api_version", ApiVersion.class);
    }

    public ChargebeeResponseSchemaType chargebeeResponseSchemaType() {
        return optEnum("chargebee_response_schema_type", ChargebeeResponseSchemaType.class);
    }

    public List<com.chargebee.models.enums.EventType> enabledEvents() {
        return optList("enabled_events", com.chargebee.models.enums.EventType.class);
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("webhook_endpoints");
        return new CreateRequest(Method.POST, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("webhook_endpoints", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("webhook_endpoints", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static Request delete(String id) {
        String uri = uri("webhook_endpoints", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static ListRequest list() {
        String uri = uri("webhook_endpoints");
        return new ListRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest name(String name) {
            params.add("name", name);
            return this;
        }


        public CreateRequest apiVersion(WebhookEndpoint.ApiVersion apiVersion) {
            params.addOpt("api_version", apiVersion);
            return this;
        }


        public CreateRequest url(String url) {
            params.add("url", url);
            return this;
        }


        public CreateRequest primaryUrl(Boolean primaryUrl) {
            params.addOpt("primary_url", primaryUrl);
            return this;
        }


        public CreateRequest disabled(Boolean disabled) {
            params.addOpt("disabled", disabled);
            return this;
        }


        public CreateRequest basicAuthPassword(String basicAuthPassword) {
            params.addOpt("basic_auth_password", basicAuthPassword);
            return this;
        }


        public CreateRequest basicAuthUsername(String basicAuthUsername) {
            params.addOpt("basic_auth_username", basicAuthUsername);
            return this;
        }


        public CreateRequest sendCardResource(Boolean sendCardResource) {
            params.addOpt("send_card_resource", sendCardResource);
            return this;
        }


        public CreateRequest chargebeeResponseSchemaType(com.chargebee.models.enums.ChargebeeResponseSchemaType chargebeeResponseSchemaType) {
            params.addOpt("chargebee_response_schema_type", chargebeeResponseSchemaType);
            return this;
        }


        public CreateRequest enabledEvents(List<com.chargebee.models.enums.EventType> enabledEvents) {
            params.addOpt("enabled_events", enabledEvents);
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
    
        public UpdateRequest name(String name) {
            params.addOpt("name", name);
            return this;
        }


        public UpdateRequest apiVersion(WebhookEndpoint.ApiVersion apiVersion) {
            params.addOpt("api_version", apiVersion);
            return this;
        }


        public UpdateRequest url(String url) {
            params.addOpt("url", url);
            return this;
        }


        public UpdateRequest primaryUrl(Boolean primaryUrl) {
            params.addOpt("primary_url", primaryUrl);
            return this;
        }


        public UpdateRequest sendCardResource(Boolean sendCardResource) {
            params.addOpt("send_card_resource", sendCardResource);
            return this;
        }


        public UpdateRequest basicAuthPassword(String basicAuthPassword) {
            params.addOpt("basic_auth_password", basicAuthPassword);
            return this;
        }


        public UpdateRequest basicAuthUsername(String basicAuthUsername) {
            params.addOpt("basic_auth_username", basicAuthUsername);
            return this;
        }


        public UpdateRequest disabled(Boolean disabled) {
            params.addOpt("disabled", disabled);
            return this;
        }


        public UpdateRequest enabledEvents(List<com.chargebee.models.enums.EventType> enabledEvents) {
            params.addOpt("enabled_events", enabledEvents);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
