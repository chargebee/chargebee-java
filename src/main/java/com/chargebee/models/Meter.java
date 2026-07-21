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

public class Meter extends Resource<Meter> {

    public enum Type {
        SIMPLE,
        COMPOUND,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Status {
        ACTIVE,
        ARCHIVED,
        DELETED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Meter(String jsonStr) {
        super(jsonStr);
    }

    public Meter(JSONObject jsonObj) {
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

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public String query() {
        return reqString("query");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public List<ColumnDefinition> columnDefinitions() {
        return optList("column_definitions", ColumnDefinition.class);
    }

    public List<Feature> features() {
        return optList("features", Feature.class);
    }

    // Operations
    //===========

    public static MeterListRequest list() {
        String uri = uri("meters");
        return new MeterListRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class MeterListRequest extends ListRequest<MeterListRequest> {

        private MeterListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<MeterListRequest> name() {
            return new StringFilter<MeterListRequest>("name",this);        
        }


        public MeterListRequest sortById(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","id");
            return this;
        }
        public MeterListRequest sortByName(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","name");
            return this;
        }
        public MeterListRequest sortByCreatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","created_at");
            return this;
        }
        public MeterListRequest sortByUpdatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","updated_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
