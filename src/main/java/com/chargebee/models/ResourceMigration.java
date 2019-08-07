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

public class ResourceMigration extends Resource<ResourceMigration> {

    public enum Status {
        SCHEDULED,
        FAILED,
        SUCCEEDED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public ResourceMigration(String jsonStr) {
        super(jsonStr);
    }

    public ResourceMigration(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String fromSite() {
        return reqString("from_site");
    }

    public EntityType entityType() {
        return reqEnum("entity_type", EntityType.class);
    }

    public String entityId() {
        return reqString("entity_id");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public String errors() {
        return optString("errors");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp updatedAt() {
        return reqTimestamp("updated_at");
    }

    // Operations
    //===========

    public static RetrieveLatestRequest retrieveLatest() {
        String uri = uri("resource_migrations", "retrieve_latest");
        return new RetrieveLatestRequest(Method.GET, uri);
    }


    // Operation Request Classes
    //==========================

    public static class RetrieveLatestRequest extends Request<RetrieveLatestRequest> {

        private RetrieveLatestRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RetrieveLatestRequest fromSite(String fromSite) {
            params.add("from_site", fromSite);
            return this;
        }


        public RetrieveLatestRequest entityType(com.chargebee.models.enums.EntityType entityType) {
            params.add("entity_type", entityType);
            return this;
        }


        public RetrieveLatestRequest entityId(String entityId) {
            params.add("entity_id", entityId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
