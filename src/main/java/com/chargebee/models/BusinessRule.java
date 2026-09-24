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

public class BusinessRule extends Resource<BusinessRule> {

    //Constructors
    //============

    public BusinessRule(String jsonStr) {
        super(jsonStr);
    }

    public BusinessRule(JSONObject jsonObj) {
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

    public Integer latestVersion() {
        return optInteger("latest_version");
    }

    public Boolean active() {
        return reqBoolean("active");
    }

    public Timestamp releasedAt() {
        return optTimestamp("released_at");
    }

    public String releasedBy() {
        return optString("released_by");
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

    public JSONArray tags() {
        return optJSONArray("tags");
    }

    public JSONObject structuredExpression() {
        return optJSONObject("structured_expression");
    }

    public JSONArray actionsOnSuccess() {
        return optJSONArray("actions_on_success");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("business_rules");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request delete(String id) {
        String uri = uri("business_rules", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static UpdateDraftRequest updateDraft(String id) {
        String uri = uri("business_rules", nullCheck(id), "draft");
        return new UpdateDraftRequest(Method.POST, uri);
    }

    public static BusinessRuleListRequest list() {
        String uri = uri("business_rules");
        return new BusinessRuleListRequest(uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("business_rules", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static Request retrieveDraft(String id) {
        String uri = uri("business_rules", nullCheck(id), "draft");
        return new Request(Method.GET, uri);
    }

    public static Request deleteDraft(String id) {
        String uri = uri("business_rules", nullCheck(id), "delete_draft");
        return new Request(Method.POST, uri);
    }

    public static Request activateRule(String id) {
        String uri = uri("business_rules", nullCheck(id), "activate");
        return new Request(Method.POST, uri);
    }

    public static Request deactivateRule(String id) {
        String uri = uri("business_rules", nullCheck(id), "deactivate");
        return new Request(Method.POST, uri);
    }

    public static Request releaseRule(String id) {
        String uri = uri("business_rules", nullCheck(id), "release");
        return new Request(Method.POST, uri);
    }

    public static ApplyRulesRequest applyRules() {
        String uri = uri("business_rules", "apply_rules");
        return new ApplyRulesRequest(Method.POST, uri);
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


        public CreateRequest tags(JSONArray tags) {
            params.addOpt("tags", tags);
            return this;
        }


        public CreateRequest structuredExpression(JSONObject structuredExpression) {
            params.add("structured_expression", structuredExpression);
            return this;
        }


        public CreateRequest actionsOnSuccess(JSONArray actionsOnSuccess) {
            params.addOpt("actions_on_success", actionsOnSuccess);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateDraftRequest extends Request<UpdateDraftRequest> {

        private UpdateDraftRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateDraftRequest name(String name) {
            params.add("name", name);
            return this;
        }


        public UpdateDraftRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        public UpdateDraftRequest tags(JSONArray tags) {
            params.addOpt("tags", tags);
            return this;
        }


        public UpdateDraftRequest structuredExpression(JSONObject structuredExpression) {
            params.add("structured_expression", structuredExpression);
            return this;
        }


        public UpdateDraftRequest actionsOnSuccess(JSONArray actionsOnSuccess) {
            params.addOpt("actions_on_success", actionsOnSuccess);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class BusinessRuleListRequest extends ListRequest<BusinessRuleListRequest> {

        private BusinessRuleListRequest(String uri) {
            super(uri);
        }
    
        public BooleanFilter<BusinessRuleListRequest> draft() {
            return new BooleanFilter<BusinessRuleListRequest>("draft",this);        
        }


        public BooleanFilter<BusinessRuleListRequest> active() {
            return new BooleanFilter<BusinessRuleListRequest>("active",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class ApplyRulesRequest extends Request<ApplyRulesRequest> {

        private ApplyRulesRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ApplyRulesRequest evaluate(Boolean evaluate) {
            params.addOpt("evaluate", evaluate);
            return this;
        }


        public ApplyRulesRequest ruleId(String ruleId) {
            params.addOpt("rule_id", ruleId);
            return this;
        }


        public ApplyRulesRequest rulesetId(String rulesetId) {
            params.addOpt("ruleset_id", rulesetId);
            return this;
        }


        public ApplyRulesRequest skipFailedRules(Boolean skipFailedRules) {
            params.addOpt("skip_failed_rules", skipFailedRules);
            return this;
        }


        public ApplyRulesRequest structuredExpression(JSONObject structuredExpression) {
            params.addOpt("structured_expression", structuredExpression);
            return this;
        }


        public ApplyRulesRequest context(JSONObject context) {
            params.addOpt("context", context);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
