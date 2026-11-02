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

public class PreferenceVariant extends Resource<PreferenceVariant> {

    //Constructors
    //============

    public PreferenceVariant(String jsonStr) {
        super(jsonStr);
    }

    public PreferenceVariant(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    @Deprecated
    public String value() {
        return reqString("value");
    }

    @Deprecated
    public Boolean allowOverrideInInterface() {
        return optBoolean("allow_override_in_interface");
    }

    @Deprecated
    public Boolean allowOverrideInApi() {
        return optBoolean("allow_override_in_api");
    }

    // Operations
    //===========


}
