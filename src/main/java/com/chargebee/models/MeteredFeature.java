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

public class MeteredFeature extends Resource<MeteredFeature> {

    //Constructors
    //============

    public MeteredFeature(String jsonStr) {
        super(jsonStr);
    }

    public MeteredFeature(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String name() {
        return optString("name");
    }

    public String description() {
        return optString("description");
    }

    public Type type() {
        return optEnum("type", Type.class);
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public String query() {
        return optString("query");
    }

    public List<ColumnDefinition> columnDefinitions() {
        return optList("column_definitions", ColumnDefinition.class);
    }

    public List<Feature> features() {
        return optList("features", Feature.class);
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("metered_features");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request archive(String id) {
        String uri = uri("metered_features", nullCheck(id), "archive_command");
        return new Request(Method.POST, uri);
    }

    public static Request reactivate(String id) {
        String uri = uri("metered_features", nullCheck(id), "reactivate_command");
        return new Request(Method.POST, uri);
    }

    public static Request delete(String id) {
        String uri = uri("metered_features", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
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


        public CreateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        public CreateRequest featureUnit(String featureUnit) {
            params.add("feature_unit", featureUnit);
            return this;
        }


        public CreateRequest query(String query) {
            params.add("query", query);
            return this;
        }


        public CreateRequest columnDefinitionColumnName(int index, String columnDefinitionColumnName) {
            params.add("column_definitions[column_name][" + index + "]", columnDefinitionColumnName);
            return this;
        }
        public CreateRequest columnDefinitionDataType(int index, ColumnDefinition.DataType columnDefinitionDataType) {
            params.add("column_definitions[data_type][" + index + "]", columnDefinitionDataType);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

}
