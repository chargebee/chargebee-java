package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class Download extends Resource<Download> {

    //Constructors
    //============

    public Download(String jsonStr) {
        super(jsonStr);
    }

    public Download(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String downloadUrl() {
        return reqString("download_url");
    }

    public Timestamp validTill() {
        return reqTimestamp("valid_till");
    }

    // Operations
    //===========


}
