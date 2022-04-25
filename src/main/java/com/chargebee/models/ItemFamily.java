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

public class ItemFamily extends Resource<ItemFamily> {

    public enum Status {
        ACTIVE,
        DELETED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public ItemFamily(String jsonStr) {
        super(jsonStr);
    }

    public ItemFamily(JSONObject jsonObj) {
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

    public String description() {
        return optString("description");
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public Channel channel() {
        return optEnum("channel", Channel.class);
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("item_families");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("item_families", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static ItemFamilyListRequest list() {
        String uri = uri("item_families");
        return new ItemFamilyListRequest(uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("item_families", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static Request delete(String id) {
        String uri = uri("item_families", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest id(String id) {
            params.add("id", id);
            return this;
        }


        public CreateRequest name(String name) {
            params.add("name", name);
            return this;
        }


        public CreateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }




        @Override
        public Params params() {
            return params;
        }
    }

    public static class ItemFamilyListRequest extends ListRequest<ItemFamilyListRequest> {

        private ItemFamilyListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<ItemFamilyListRequest> id() {
            return new StringFilter<ItemFamilyListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<ItemFamilyListRequest> name() {
            return new StringFilter<ItemFamilyListRequest>("name",this);        
        }


        public TimestampFilter<ItemFamilyListRequest> updatedAt() {
            return new TimestampFilter<ItemFamilyListRequest>("updated_at",this);        
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


        public UpdateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }




        @Override
        public Params params() {
            return params;
        }
    }

}
