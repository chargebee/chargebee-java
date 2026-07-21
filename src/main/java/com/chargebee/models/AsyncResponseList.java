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

public class AsyncResponseList extends Resource<AsyncResponseList> {

    //Constructors
    //============

    public AsyncResponseList(String jsonStr) {
        super(jsonStr);
    }

    public AsyncResponseList(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public List<AsyncResponse> list() {
        return optList("list", AsyncResponse.class);
    }

    // Operations
    //===========


}
