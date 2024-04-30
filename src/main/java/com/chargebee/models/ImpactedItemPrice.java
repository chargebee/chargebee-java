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

public class ImpactedItemPrice extends Resource<ImpactedItemPrice> {

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

    public ImpactedItemPrice(String jsonStr) {
        super(jsonStr);
    }

    public ImpactedItemPrice(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public Integer count() {
        return optInteger("count");
    }

    public ImpactedItemPrice.Download download() {
        return optSubResource("download", ImpactedItemPrice.Download.class);
    }

    public JSONArray itemPrices() {
        return optJSONArray("item_prices");
    }

    // Operations
    //===========


}
