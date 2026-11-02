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

public class GroupByAttribute extends Resource<GroupByAttribute> {

    public enum WarningReasons {
        LOW_COVERAGE,
        HIGH_CARDINALITY,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public GroupByAttribute(String jsonStr) {
        super(jsonStr);
    }

    public GroupByAttribute(JSONObject jsonObj) {
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

    public Integer coveragePercent() {
        return reqInteger("coverage_percent");
    }

    public Long cardinality() {
        return reqLong("cardinality");
    }

    public Long remainingValueCount() {
        return reqLong("remaining_value_count");
    }

    public Boolean warning() {
        return reqBoolean("warning");
    }

    public List<String> sampleValues() {
        return optList("sample_values", String.class);
    }

    public List<WarningReasons> warningReasons() {
        return optList("warning_reasons", WarningReasons.class);
    }

    // Operations
    //===========


}
