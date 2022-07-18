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

public class ImpactedSubscription extends Resource<ImpactedSubscription> {

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

    public ImpactedSubscription(String jsonStr) {
        super(jsonStr);
    }

    public ImpactedSubscription(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public Integer count() {
        return optInteger("count");
    }

    public ImpactedSubscription.Download download() {
        return optSubResource("download", ImpactedSubscription.Download.class);
    }

    public JSONArray subscriptionIds() {
        return optJSONArray("subscription_ids");
    }

    // Operations
    //===========


}
