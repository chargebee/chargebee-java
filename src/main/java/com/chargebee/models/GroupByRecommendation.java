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

public class GroupByRecommendation extends Resource<GroupByRecommendation> {

    //Constructors
    //============

    public GroupByRecommendation(String jsonStr) {
        super(jsonStr);
    }

    public GroupByRecommendation(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String attributeName() {
        return reqString("attribute_name");
    }

    public Integer rank() {
        return reqInteger("rank");
    }

    public String reason() {
        return reqString("reason");
    }

    // Operations
    //===========


}
