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

public class OmnichannelTransaction extends Resource<OmnichannelTransaction> {

    public enum Type {
        PURCHASE,
        RENEWAL,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public OmnichannelTransaction(String jsonStr) {
        super(jsonStr);
    }

    public OmnichannelTransaction(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String idAtSource() {
        return reqString("id_at_source");
    }

    public String appId() {
        return reqString("app_id");
    }

    public String priceCurrency() {
        return reqString("price_currency");
    }

    public Long priceUnits() {
        return reqLong("price_units");
    }

    public Long priceNanos() {
        return reqLong("price_nanos");
    }

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public Timestamp transactedAt() {
        return reqTimestamp("transacted_at");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    // Operations
    //===========


}
