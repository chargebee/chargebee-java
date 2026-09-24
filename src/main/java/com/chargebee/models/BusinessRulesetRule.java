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

public class BusinessRulesetRule extends Resource<BusinessRulesetRule> {

    //Constructors
    //============

    public BusinessRulesetRule(String jsonStr) {
        super(jsonStr);
    }

    public BusinessRulesetRule(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String ruleId() {
        return reqString("rule_id");
    }

    public Integer priority() {
        return reqInteger("priority");
    }

    // Operations
    //===========


}
