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

public class Hierarchy extends Resource<Hierarchy> {

    //Constructors
    //============

    public Hierarchy(String jsonStr) {
        super(jsonStr);
    }

    public Hierarchy(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String customerId() {
        return reqString("customer_id");
    }

    public String parentId() {
        return optString("parent_id");
    }

    public String paymentOwnerId() {
        return reqString("payment_owner_id");
    }

    public String invoiceOwnerId() {
        return reqString("invoice_owner_id");
    }

    public List<String> childrenIds() {
        return optList("children_ids", String.class);
    }

    // Operations
    //===========


}
