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

public class Comment extends Resource<Comment> {

    public enum Type {
        USER,
        SYSTEM,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Comment(String jsonStr) {
        super(jsonStr);
    }

    public Comment(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public EntityType entityType() {
        return reqEnum("entity_type", EntityType.class);
    }

    public String addedBy() {
        return optString("added_by");
    }

    public String notes() {
        return reqString("notes");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public String entityId() {
        return reqString("entity_id");
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("comments");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("comments", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static CommentListRequest list() {
        String uri = uri("comments");
        return new CommentListRequest(uri);
    }

    public static Request delete(String id) {
        String uri = uri("comments", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest entityType(com.chargebee.models.enums.EntityType entityType) {
            params.add("entity_type", entityType);
            return this;
        }


        public CreateRequest entityId(String entityId) {
            params.add("entity_id", entityId);
            return this;
        }


        public CreateRequest notes(String notes) {
            params.add("notes", notes);
            return this;
        }


        public CreateRequest addedBy(String addedBy) {
            params.addOpt("added_by", addedBy);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class CommentListRequest extends ListRequest<CommentListRequest> {

        private CommentListRequest(String uri) {
            super(uri);
        }
    
        public CommentListRequest entityType(com.chargebee.models.enums.EntityType entityType) {
            params.addOpt("entity_type", entityType);
            return this;
        }


        public CommentListRequest entityId(String entityId) {
            params.addOpt("entity_id", entityId);
            return this;
        }


        public TimestampFilter<CommentListRequest> createdAt() {
            return new TimestampFilter<CommentListRequest>("created_at",this);        
        }


        public CommentListRequest sortByCreatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","created_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
