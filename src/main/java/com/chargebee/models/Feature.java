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

public class Feature extends Resource<Feature> {

    public enum Status {
        ACTIVE,
        ARCHIVED,
        DRAFT,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Type {
        SWITCH,
        CUSTOM,
        QUANTITY,
        RANGE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class Level extends Resource<Level> {
        public Level(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String name() {
            return optString("name");
        }

        public String value() {
            return reqString("value");
        }

        public Integer level() {
            return reqInteger("level");
        }

        public Boolean isUnlimited() {
            return reqBoolean("is_unlimited");
        }

    }

    //Constructors
    //============

    public Feature(String jsonStr) {
        super(jsonStr);
    }

    public Feature(JSONObject jsonObj) {
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

    public Type type() {
        return optEnum("type", Type.class);
    }

    public String unit() {
        return optString("unit");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public List<Feature.Level> levels() {
        return optList("levels", Feature.Level.class);
    }

    // Operations
    //===========

    public static FeatureListRequest list() {
        String uri = uri("features");
        return new FeatureListRequest(uri);
    }

    public static CreateRequest create() {
        String uri = uri("features");
        return new CreateRequest(Method.POST, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("features", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("features", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static Request delete(String id) {
        String uri = uri("features", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static Request activate(String id) {
        String uri = uri("features", nullCheck(id), "activate_command");
        return new Request(Method.POST, uri);
    }

    public static Request archive(String id) {
        String uri = uri("features", nullCheck(id), "archive_command");
        return new Request(Method.POST, uri);
    }

    public static Request reactivate(String id) {
        String uri = uri("features", nullCheck(id), "reactivate_command");
        return new Request(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class FeatureListRequest extends ListRequest<FeatureListRequest> {

        private FeatureListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<FeatureListRequest> name() {
            return new StringFilter<FeatureListRequest>("name",this).supportsMultiOperators(true);        
        }


        public StringFilter<FeatureListRequest> id() {
            return new StringFilter<FeatureListRequest>("id",this).supportsMultiOperators(true);        
        }


        public EnumFilter<Feature.Status, FeatureListRequest> status() {
            return new EnumFilter<Feature.Status, FeatureListRequest>("status",this);        
        }


        public EnumFilter<Feature.Type, FeatureListRequest> type() {
            return new EnumFilter<Feature.Type, FeatureListRequest>("type",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest id(String id) {
            params.addOpt("id", id);
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


        public CreateRequest type(Feature.Type type) {
            params.addOpt("type", type);
            return this;
        }


        public CreateRequest status(Status status) {
            params.addOpt("status", status);
            return this;
        }


        public CreateRequest unit(String unit) {
            params.addOpt("unit", unit);
            return this;
        }


        public CreateRequest levelName(int index, String levelName) {
            params.addOpt("levels[name][" + index + "]", levelName);
            return this;
        }
        public CreateRequest levelValue(int index, String levelValue) {
            params.addOpt("levels[value][" + index + "]", levelValue);
            return this;
        }
        public CreateRequest levelIsUnlimited(int index, Boolean levelIsUnlimited) {
            params.addOpt("levels[is_unlimited][" + index + "]", levelIsUnlimited);
            return this;
        }
        public CreateRequest levelLevel(int index, Integer levelLevel) {
            params.addOpt("levels[level][" + index + "]", levelLevel);
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


        public UpdateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        public UpdateRequest status(Status status) {
            params.addOpt("status", status);
            return this;
        }


        public UpdateRequest unit(String unit) {
            params.addOpt("unit", unit);
            return this;
        }


        public UpdateRequest levelName(int index, String levelName) {
            params.addOpt("levels[name][" + index + "]", levelName);
            return this;
        }
        public UpdateRequest levelValue(int index, String levelValue) {
            params.addOpt("levels[value][" + index + "]", levelValue);
            return this;
        }
        public UpdateRequest levelIsUnlimited(int index, Boolean levelIsUnlimited) {
            params.addOpt("levels[is_unlimited][" + index + "]", levelIsUnlimited);
            return this;
        }
        public UpdateRequest levelLevel(int index, Integer levelLevel) {
            params.addOpt("levels[level][" + index + "]", levelLevel);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

}
