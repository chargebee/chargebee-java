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

public class ColumnDefinition extends Resource<ColumnDefinition> {

    public enum DataType {
        NUMBER,
        STRING,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public ColumnDefinition(String jsonStr) {
        super(jsonStr);
    }

    public ColumnDefinition(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String columnName() {
        return reqString("column_name");
    }

    public DataType dataType() {
        return reqEnum("data_type", DataType.class);
    }

    // Operations
    //===========


}
