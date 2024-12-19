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

public class Configuration extends Resource<Configuration> {

    //Constructors
    //============

    public Configuration(String jsonStr) {
        super(jsonStr);
    }

    public Configuration(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String domain() {
        return optString("domain");
    }

    public ProductCatalogVersion productCatalogVersion() {
        return optEnum("product_catalog_version", ProductCatalogVersion.class);
    }

    // Operations
    //===========

    public static Request list() {
        String uri = uri("configurations");
        return new Request(Method.GET, uri);
    }


}
