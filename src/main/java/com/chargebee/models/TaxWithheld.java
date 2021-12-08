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

public class TaxWithheld extends Resource<TaxWithheld> {

    //Constructors
    //============

    public TaxWithheld(String jsonStr) {
        super(jsonStr);
    }

    public TaxWithheld(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String referenceNumber() {
        return optString("reference_number");
    }

    public String description() {
        return optString("description");
    }

    public Timestamp date() {
        return optTimestamp("date");
    }

    @Deprecated
    public String currencyCode() {
        return reqString("currency_code");
    }

    public Integer amount() {
        return optInteger("amount");
    }

    // Operations
    //===========


}
