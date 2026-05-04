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

public class FilterCondition extends Resource<FilterCondition> {

    public enum Field {
        PLAN_PRICE_ID,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Operator {
        EQUALS,
        NOT_EQUALS,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public FilterCondition(String jsonStr) {
        super(jsonStr);
    }

    public FilterCondition(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public Field field() {
        return reqEnum("field", Field.class);
    }

    public Operator operator() {
        return reqEnum("operator", Operator.class);
    }

    public String value() {
        return reqString("value");
    }

    // Operations
    //===========


}
