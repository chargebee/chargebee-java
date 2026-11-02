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

public class RampInterval extends Resource<RampInterval> {

    //Constructors
    //============

    public RampInterval(String jsonStr) {
        super(jsonStr);
    }

    public RampInterval(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public Integer startingBillingCycle() {
        return reqInteger("starting_billing_cycle");
    }

    public Integer endingBillingCycle() {
        return optInteger("ending_billing_cycle");
    }

    public Boolean applyCatalogPrice() {
        return optBoolean("apply_catalog_price");
    }

    public Long price() {
        return optLong("price");
    }

    public String priceInDecimal() {
        return optString("price_in_decimal");
    }

    // Operations
    //===========


}
