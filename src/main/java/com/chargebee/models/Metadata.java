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

public class Metadata extends Resource<Metadata> {

    //Constructors
    //============

    public Metadata(String jsonStr) {
        super(jsonStr);
    }

    public Metadata(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String changeType() {
        return optString("change_type");
    }

    // Operations
    //===========


}
