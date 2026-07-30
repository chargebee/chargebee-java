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

public class CreditUnit extends Resource<CreditUnit> {

    public enum Status {
        ACTIVE,
        ARCHIVED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public CreditUnit(String jsonStr) {
        super(jsonStr);
    }

    public CreditUnit(JSONObject jsonObj) {
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
        return reqString("external_name");
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

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public String createdBy() {
        return optString("created_by");
    }

    public String updatedBy() {
        return optString("updated_by");
    }

    public Boolean isUnlimited() {
        return reqBoolean("is_unlimited");
    }

    public String overdraftAmount() {
        return optString("overdraft_amount");
    }

    // Operations
    //===========

    public static CreditUnitListRequest list() {
        String uri = uri("credit_units");
        return new CreditUnitListRequest(uri);
    }

    public static CreateRequest create() {
        String uri = uri("credit_units");
        return new CreateRequest(Method.POST, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("credit_units", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static Request archive(String id) {
        String uri = uri("credit_units", nullCheck(id), "archive_command");
        return new Request(Method.POST, uri);
    }

    public static Request reactivate(String id) {
        String uri = uri("credit_units", nullCheck(id), "reactivate_command");
        return new Request(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreditUnitListRequest extends ListRequest<CreditUnitListRequest> {

        private CreditUnitListRequest(String uri) {
            super(uri);
        }
    
        public EnumFilter<CreditUnit.Status, CreditUnitListRequest> status() {
            return new EnumFilter<CreditUnit.Status, CreditUnitListRequest>("status",this);        
        }


        public StringFilter<CreditUnitListRequest> id() {
            return new StringFilter<CreditUnitListRequest>("id",this).supportsMultiOperators(true);        
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
            params.add("id", id);
            return this;
        }


        public CreateRequest name(String name) {
            params.add("name", name);
            return this;
        }


        public CreateRequest isUnlimited(Boolean isUnlimited) {
            params.add("is_unlimited", isUnlimited);
            return this;
        }


        public CreateRequest overdraftAmount(String overdraftAmount) {
            params.addOpt("overdraft_amount", overdraftAmount);
            return this;
        }


        public CreateRequest externalName(String externalName) {
            params.addOpt("external_name", externalName);
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


        @Override
        public Params params() {
            return params;
        }
    }

}
