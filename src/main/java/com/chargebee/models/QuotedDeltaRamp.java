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

public class QuotedDeltaRamp extends Resource<QuotedDeltaRamp> {

    public static class LineItem extends Resource<LineItem> {
        public LineItem(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String itemLevelDiscountPerBillingCycleInDecimal() {
            return optString("item_level_discount_per_billing_cycle_in_decimal");
        }

    }

    //Constructors
    //============

    public QuotedDeltaRamp(String jsonStr) {
        super(jsonStr);
    }

    public QuotedDeltaRamp(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public List<QuotedDeltaRamp.LineItem> lineItems() {
        return optList("line_items", QuotedDeltaRamp.LineItem.class);
    }

    // Operations
    //===========


}
