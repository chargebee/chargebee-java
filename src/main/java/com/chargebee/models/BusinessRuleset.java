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

public class BusinessRuleset extends Resource<BusinessRuleset> {

    public enum ExecuteMode {
        STOP_ON_FIRST_TRUE,
        STOP_ON_FIRST_FALSE,
        EXECUTE_ALL,
        EXECUTE_ALL_TRUE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public BusinessRuleset(String jsonStr) {
        super(jsonStr);
    }

    public BusinessRuleset(JSONObject jsonObj) {
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

    public Boolean active() {
        return reqBoolean("active");
    }

    public ExecuteMode executeMode() {
        return reqEnum("execute_mode", ExecuteMode.class);
    }

    public Timestamp updatedAt() {
        return reqTimestamp("updated_at");
    }

    public String updatedBy() {
        return optString("updated_by");
    }

    public String createdBy() {
        return reqString("created_by");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public JSONArray rules() {
        return optJSONArray("rules");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("business_rulesets");
        return new CreateRequest(Method.POST, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("business_rulesets", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static Request delete(String id) {
        String uri = uri("business_rulesets", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static Request activate(String id) {
        String uri = uri("business_rulesets", nullCheck(id), "activate");
        return new Request(Method.POST, uri);
    }

    public static Request deactivate(String id) {
        String uri = uri("business_rulesets", nullCheck(id), "deactivate");
        return new Request(Method.POST, uri);
    }

    public static AddRulesRequest addRules(String id) {
        String uri = uri("business_rulesets", nullCheck(id), "add_rules");
        return new AddRulesRequest(Method.POST, uri);
    }

    public static RemoveRulesRequest removeRules(String id) {
        String uri = uri("business_rulesets", nullCheck(id), "remove_rules");
        return new RemoveRulesRequest(Method.POST, uri);
    }

    public static ListRulesRequest listRules(String id) {
        String uri = uri("business_rulesets", nullCheck(id), "rules");
        return new ListRulesRequest(Method.GET, uri);
    }

    public static BusinessRulesetListRequest list() {
        String uri = uri("business_rulesets");
        return new BusinessRulesetListRequest(uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("business_rulesets", nullCheck(id));
        return new Request(Method.GET, uri);
    }


    // Operation Request Classes
    //==========================

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


        public CreateRequest executeMode(BusinessRuleset.ExecuteMode executeMode) {
            params.addOpt("execute_mode", executeMode);
            return this;
        }


        public CreateRequest rules(JSONArray rules) {
            params.addOpt("rules", rules);
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
            params.add("name", name);
            return this;
        }


        public UpdateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        public UpdateRequest executeMode(BusinessRuleset.ExecuteMode executeMode) {
            params.addOpt("execute_mode", executeMode);
            return this;
        }


        public UpdateRequest rules(JSONArray rules) {
            params.addOpt("rules", rules);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class AddRulesRequest extends Request<AddRulesRequest> {

        private AddRulesRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public AddRulesRequest rules(JSONArray rules) {
            params.addOpt("rules", rules);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class RemoveRulesRequest extends Request<RemoveRulesRequest> {

        private RemoveRulesRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RemoveRulesRequest rules(JSONArray rules) {
            params.addOpt("rules", rules);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class ListRulesRequest extends Request<ListRulesRequest> {

        private ListRulesRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public BooleanFilter<ListRulesRequest> active() {
            return new BooleanFilter<ListRulesRequest>("active",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class BusinessRulesetListRequest extends ListRequest<BusinessRulesetListRequest> {

        private BusinessRulesetListRequest(String uri) {
            super(uri);
        }
    
        public BooleanFilter<BusinessRulesetListRequest> active() {
            return new BooleanFilter<BusinessRulesetListRequest>("active",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
