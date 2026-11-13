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

public class BillingMetadata extends Resource<BillingMetadata> {

    //Constructors
    //============

    public BillingMetadata(String jsonStr) {
        super(jsonStr);
    }

    public BillingMetadata(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String itemPriceId() {
        return optString("item_price_id");
    }

    public List<BillingLineItem> lineItems() {
        return optList("line_items", BillingLineItem.class);
    }

    // Operations
    //===========


}
