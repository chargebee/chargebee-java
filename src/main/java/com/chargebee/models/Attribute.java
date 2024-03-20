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

public class Attribute extends Resource<Attribute> {

    //Constructors
    //============

    public Attribute(String jsonStr) {
        super(jsonStr);
    }

    public Attribute(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String name() {
        return reqString("name");
    }

    public String value() {
        return reqString("value");
    }

    // Operations
    //===========


}