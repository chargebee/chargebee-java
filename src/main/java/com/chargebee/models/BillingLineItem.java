package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.filters.*;
import com.chargebee.filters.enums.SortOrder;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

public class BillingLineItem extends Resource<BillingLineItem> {

    //Constructors
    //============

    public BillingLineItem(String jsonStr) {
        super(jsonStr);
    }

    public BillingLineItem(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return optString("id");
    }

    public String invoiceNumber() {
        return optString("invoice_number");
    }

    public BigDecimal quantity() {
        return optBigDecimal("quantity");
    }

    // Operations
    //===========


}
