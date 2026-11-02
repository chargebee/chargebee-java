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

public class BusinessEntity extends Resource<BusinessEntity> {

    public enum Status {
        ACTIVE,
        INACTIVE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public BusinessEntity(String jsonStr) {
        super(jsonStr);
    }

    public BusinessEntity(JSONObject jsonObj) {
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

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Boolean deleted() {
        return reqBoolean("deleted");
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

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("business_entities");
        return new CreateRequest(Method.POST, uri);
    }

    public static BusinessEntityListRequest list() {
        String uri = uri("business_entities");
        return new BusinessEntityListRequest(uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("business_entities", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("business_entities", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static CreateTransfersRequest createTransfers() {
        String uri = uri("business_entities", "transfers");
        return new CreateTransfersRequest(Method.POST, uri);
    }

    public static BusinessEntityGetTransfersRequest getTransfers() {
        String uri = uri("business_entities", "transfers");
        return new BusinessEntityGetTransfersRequest(uri);
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


        public CreateRequest status(BusinessEntity.Status status) {
            params.add("status", status);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class BusinessEntityListRequest extends ListRequest<BusinessEntityListRequest> {

        private BusinessEntityListRequest(String uri) {
            super(uri);
        }
    
        public BusinessEntityListRequest includeDeleted(Boolean includeDeleted) {
            params.addOpt("include_deleted", includeDeleted);
            return this;
        }


        public StringFilter<BusinessEntityListRequest> name() {
            return new StringFilter<BusinessEntityListRequest>("name",this).supportsPresenceOperator(true);        
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


        public UpdateRequest status(BusinessEntity.Status status) {
            params.addOpt("status", status);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateTransfersRequest extends Request<CreateTransfersRequest> {

        private CreateTransfersRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateTransfersRequest activeResourceIds(List<String> activeResourceIds) {
            params.add("active_resource_ids", activeResourceIds);
            return this;
        }

        public CreateTransfersRequest activeResourceIds(String... activeResourceIds) {
            params.add("active_resource_ids", activeResourceIds);
            return this;
        }

        public CreateTransfersRequest destinationBusinessEntityIds(List<String> destinationBusinessEntityIds) {
            params.add("destination_business_entity_ids", destinationBusinessEntityIds);
            return this;
        }

        public CreateTransfersRequest destinationBusinessEntityIds(String... destinationBusinessEntityIds) {
            params.add("destination_business_entity_ids", destinationBusinessEntityIds);
            return this;
        }

        @Deprecated
        public CreateTransfersRequest sourceBusinessEntityIds(List<String> sourceBusinessEntityIds) {
            params.addOpt("source_business_entity_ids", sourceBusinessEntityIds);
            return this;
        }

        @Deprecated
        public CreateTransfersRequest sourceBusinessEntityIds(String... sourceBusinessEntityIds) {
            params.addOpt("source_business_entity_ids", sourceBusinessEntityIds);
            return this;
        }

        @Deprecated
        public CreateTransfersRequest resourceTypes(List<String> resourceTypes) {
            params.add("resource_types", resourceTypes);
            return this;
        }

        @Deprecated
        public CreateTransfersRequest resourceTypes(String... resourceTypes) {
            params.add("resource_types", resourceTypes);
            return this;
        }

        public CreateTransfersRequest reasonCodes(List<String> reasonCodes) {
            params.add("reason_codes", reasonCodes);
            return this;
        }

        public CreateTransfersRequest reasonCodes(String... reasonCodes) {
            params.add("reason_codes", reasonCodes);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class BusinessEntityGetTransfersRequest extends ListRequest<BusinessEntityGetTransfersRequest> {

        private BusinessEntityGetTransfersRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<BusinessEntityGetTransfersRequest> resourceType() {
            return new StringFilter<BusinessEntityGetTransfersRequest>("resource_type",this);        
        }


        public StringFilter<BusinessEntityGetTransfersRequest> resourceId() {
            return new StringFilter<BusinessEntityGetTransfersRequest>("resource_id",this);        
        }


        public StringFilter<BusinessEntityGetTransfersRequest> activeResourceId() {
            return new StringFilter<BusinessEntityGetTransfersRequest>("active_resource_id",this);        
        }


        public TimestampFilter<BusinessEntityGetTransfersRequest> createdAt() {
            return new TimestampFilter<BusinessEntityGetTransfersRequest>("created_at",this);        
        }


        public BusinessEntityGetTransfersRequest sortByCreatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","created_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
