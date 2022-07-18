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

public class ImpactedItem extends Resource<ImpactedItem> {

    public static class Download extends Resource<Download> {
        public Download(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String downloadUrl() {
            return reqString("download_url");
        }

        public Timestamp validTill() {
            return reqTimestamp("valid_till");
        }

        public String mimeType() {
            return optString("mime_type");
        }

    }

    //Constructors
    //============

    public ImpactedItem(String jsonStr) {
        super(jsonStr);
    }

    public ImpactedItem(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public Integer count() {
        return optInteger("count");
    }

    public ImpactedItem.Download download() {
        return optSubResource("download", ImpactedItem.Download.class);
    }

    public JSONArray items() {
        return optJSONArray("items");
    }

    // Operations
    //===========


}
