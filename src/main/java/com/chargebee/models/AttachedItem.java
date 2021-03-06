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

public class AttachedItem extends Resource<AttachedItem> {

    public enum Type {
        RECOMMENDED,
        MANDATORY,
        OPTIONAL,
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

    public AttachedItem(String jsonStr) {
        super(jsonStr);
    }

    public AttachedItem(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String parentItemId() {
        return reqString("parent_item_id");
    }

    public String itemId() {
        return reqString("item_id");
    }

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public Integer quantity() {
        return optInteger("quantity");
    }

    public Integer billingCycles() {
        return optInteger("billing_cycles");
    }

    public ChargeOnEvent chargeOnEvent() {
        return reqEnum("charge_on_event", ChargeOnEvent.class);
    }

    public Boolean chargeOnce() {
        return reqBoolean("charge_once");
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

    public static CreateRequest create(String id) {
        String uri = uri("items", nullCheck(id), "attached_items");
        return new CreateRequest(Method.POST, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("attached_items", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static RetrieveRequest retrieve(String id) {
        String uri = uri("attached_items", nullCheck(id));
        return new RetrieveRequest(Method.GET, uri);
    }

    public static DeleteRequest delete(String id) {
        String uri = uri("attached_items", nullCheck(id), "delete");
        return new DeleteRequest(Method.POST, uri);
    }

    public static AttachedItemListRequest list(String id) {
        String uri = uri("items", nullCheck(id), "attached_items");
        return new AttachedItemListRequest(uri);
    }

    @Deprecated
    public static AttachedItemListInternalRequest listInternal() {
        String uri = uri("attached_items", "list_internal");
        return new AttachedItemListInternalRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest itemId(String itemId) {
            params.add("item_id", itemId);
            return this;
        }


        public CreateRequest type(AttachedItem.Type type) {
            params.addOpt("type", type);
            return this;
        }


        public CreateRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public CreateRequest quantity(Integer quantity) {
            params.addOpt("quantity", quantity);
            return this;
        }


        public CreateRequest chargeOnEvent(com.chargebee.models.enums.ChargeOnEvent chargeOnEvent) {
            params.addOpt("charge_on_event", chargeOnEvent);
            return this;
        }


        public CreateRequest chargeOnce(Boolean chargeOnce) {
            params.addOpt("charge_once", chargeOnce);
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
    
        public UpdateRequest parentItemId(String parentItemId) {
            params.add("parent_item_id", parentItemId);
            return this;
        }


        public UpdateRequest type(AttachedItem.Type type) {
            params.addOpt("type", type);
            return this;
        }


        public UpdateRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public UpdateRequest quantity(Integer quantity) {
            params.addOpt("quantity", quantity);
            return this;
        }


        public UpdateRequest chargeOnEvent(com.chargebee.models.enums.ChargeOnEvent chargeOnEvent) {
            params.addOpt("charge_on_event", chargeOnEvent);
            return this;
        }


        public UpdateRequest chargeOnce(Boolean chargeOnce) {
            params.addOpt("charge_once", chargeOnce);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class RetrieveRequest extends Request<RetrieveRequest> {

        private RetrieveRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RetrieveRequest parentItemId(String parentItemId) {
            params.add("parent_item_id", parentItemId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class DeleteRequest extends Request<DeleteRequest> {

        private DeleteRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public DeleteRequest parentItemId(String parentItemId) {
            params.add("parent_item_id", parentItemId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class AttachedItemListRequest extends ListRequest<AttachedItemListRequest> {

        private AttachedItemListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<AttachedItemListRequest> id() {
            return new StringFilter<AttachedItemListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<AttachedItemListRequest> itemId() {
            return new StringFilter<AttachedItemListRequest>("item_id",this).supportsMultiOperators(true);        
        }


        public EnumFilter<AttachedItem.Type, AttachedItemListRequest> type() {
            return new EnumFilter<AttachedItem.Type, AttachedItemListRequest>("type",this);        
        }


        public EnumFilter<com.chargebee.models.enums.ItemType, AttachedItemListRequest> itemType() {
            return new EnumFilter<com.chargebee.models.enums.ItemType, AttachedItemListRequest>("item_type",this);        
        }


        public EnumFilter<com.chargebee.models.enums.ChargeOnEvent, AttachedItemListRequest> chargeOnEvent() {
            return new EnumFilter<com.chargebee.models.enums.ChargeOnEvent, AttachedItemListRequest>("charge_on_event",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class AttachedItemListInternalRequest extends ListRequest<AttachedItemListInternalRequest> {

        private AttachedItemListInternalRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<AttachedItemListInternalRequest> id() {
            return new StringFilter<AttachedItemListInternalRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<AttachedItemListInternalRequest> itemId() {
            return new StringFilter<AttachedItemListInternalRequest>("item_id",this).supportsMultiOperators(true);        
        }


        public EnumFilter<AttachedItem.Type, AttachedItemListInternalRequest> type() {
            return new EnumFilter<AttachedItem.Type, AttachedItemListInternalRequest>("type",this);        
        }


        public EnumFilter<com.chargebee.models.enums.ItemType, AttachedItemListInternalRequest> itemType() {
            return new EnumFilter<com.chargebee.models.enums.ItemType, AttachedItemListInternalRequest>("item_type",this);        
        }


        public EnumFilter<com.chargebee.models.enums.ChargeOnEvent, AttachedItemListInternalRequest> chargeOnEvent() {
            return new EnumFilter<com.chargebee.models.enums.ChargeOnEvent, AttachedItemListInternalRequest>("charge_on_event",this);        
        }


        public StringFilter<AttachedItemListInternalRequest> parentItemId() {
            return new StringFilter<AttachedItemListInternalRequest>("parent_item_id",this).supportsMultiOperators(true);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
