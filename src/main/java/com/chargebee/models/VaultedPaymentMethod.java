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

public class VaultedPaymentMethod extends Resource<VaultedPaymentMethod> {

    //Constructors
    //============

    public VaultedPaymentMethod(String jsonStr) {
        super(jsonStr);
    }

    public VaultedPaymentMethod(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String customerId() {
        return reqString("customer_id");
    }

    public String creditCardId() {
        return reqString("credit_card_id");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp modifiedAt() {
        return reqTimestamp("modified_at");
    }

    // Operations
    //===========

    public static Request retrieve(String id) {
        String uri = uri("vaulted_payment_methods", nullCheck(id));
        return new Request(Method.GET, uri);
    }


}
