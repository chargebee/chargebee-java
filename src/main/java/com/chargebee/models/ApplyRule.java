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

public class ApplyRule extends Resource<ApplyRule> {

    public static class Rule extends Resource<Rule> {
        public Rule(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public Integer version() {
            return optInteger("version");
        }

        public String name() {
            return optString("name");
        }

        public String description() {
            return optString("description");
        }

        public Boolean evaluationResult() {
            return optBoolean("evaluation_result");
        }

        public String errorMessage() {
            return optString("error_message");
        }

        public JSONArray actions() {
            return optJSONArray("actions");
        }

    }

    //Constructors
    //============

    public ApplyRule(String jsonStr) {
        super(jsonStr);
    }

    public ApplyRule(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public Boolean evaluate() {
        return optBoolean("evaluate");
    }

    public String ruleId() {
        return optString("rule_id");
    }

    public String rulesetId() {
        return optString("ruleset_id");
    }

    public Boolean skipFailedRules() {
        return optBoolean("skip_failed_rules");
    }

    public JSONObject structuredExpression() {
        return optJSONObject("structured_expression");
    }

    public JSONObject context() {
        return optJSONObject("context");
    }

    public List<ApplyRule.Rule> rules() {
        return optList("rules", ApplyRule.Rule.class);
    }

    // Operations
    //===========


}
