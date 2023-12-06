package com.chargebee.models;

import com.chargebee.internal.*;
import org.json.*;
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
