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

public class PriceVariant extends Resource<PriceVariant> {

    public enum Status {
        ACTIVE,
        ARCHIVED,
        DELETED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class Attribute extends Resource<Attribute> {
        public Attribute(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String name() {
            return reqString("name");
        }

        public String value() {
            return reqString("value");
        }

    }

    //Constructors
    //============

    public PriceVariant(String jsonStr) {
        super(jsonStr);
    }

    public PriceVariant(JSONObject jsonObj) {
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

    public String externalName() {
        return optString("external_name");
    }

    public String variantGroup() {
        return optString("variant_group");
    }

    public String description() {
        return optString("description");
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public Timestamp archivedAt() {
        return optTimestamp("archived_at");
    }

    public List<PriceVariant.Attribute> attributes() {
        return optList("attributes", PriceVariant.Attribute.class);
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("price_variants");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("price_variants", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("price_variants", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static Request delete(String id) {
        String uri = uri("price_variants", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static PriceVariantListRequest list() {
        String uri = uri("price_variants");
        return new PriceVariantListRequest(uri);
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


        public CreateRequest externalName(String externalName) {
            params.addOpt("external_name", externalName);
            return this;
        }


        public CreateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        public CreateRequest variantGroup(String variantGroup) {
            params.addOpt("variant_group", variantGroup);
            return this;
        }


        public CreateRequest attributeName(int index, String attributeName) {
            params.add("attributes[name][" + index + "]", attributeName);
            return this;
        }
        public CreateRequest attributeValue(int index, String attributeValue) {
            params.add("attributes[value][" + index + "]", attributeValue);
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


        public UpdateRequest externalName(String externalName) {
            params.addOpt("external_name", externalName);
            return this;
        }


        public UpdateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        public UpdateRequest variantGroup(String variantGroup) {
            params.addOpt("variant_group", variantGroup);
            return this;
        }


        public UpdateRequest status(PriceVariant.Status status) {
            params.addOpt("status", status);
            return this;
        }


        public UpdateRequest attributeName(int index, String attributeName) {
            params.add("attributes[name][" + index + "]", attributeName);
            return this;
        }
        public UpdateRequest attributeValue(int index, String attributeValue) {
            params.add("attributes[value][" + index + "]", attributeValue);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class PriceVariantListRequest extends ListRequest<PriceVariantListRequest> {

        private PriceVariantListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<PriceVariantListRequest> id() {
            return new StringFilter<PriceVariantListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<PriceVariantListRequest> name() {
            return new StringFilter<PriceVariantListRequest>("name",this).supportsMultiOperators(true);        
        }


        public EnumFilter<PriceVariant.Status, PriceVariantListRequest> status() {
            return new EnumFilter<PriceVariant.Status, PriceVariantListRequest>("status",this);        
        }


        public TimestampFilter<PriceVariantListRequest> updatedAt() {
            return new TimestampFilter<PriceVariantListRequest>("updated_at",this);        
        }


        public TimestampFilter<PriceVariantListRequest> createdAt() {
            return new TimestampFilter<PriceVariantListRequest>("created_at",this);        
        }


        public PriceVariantListRequest sortByName(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","name");
            return this;
        }
        public PriceVariantListRequest sortById(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","id");
            return this;
        }
        public PriceVariantListRequest sortByStatus(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","status");
            return this;
        }
        public PriceVariantListRequest sortByCreatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","created_at");
            return this;
        }
        public PriceVariantListRequest sortByUpdatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","updated_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
