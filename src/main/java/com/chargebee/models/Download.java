package com.chargebee.models;

import com.chargebee.internal.*;
import org.json.*;
import java.sql.Timestamp;

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
