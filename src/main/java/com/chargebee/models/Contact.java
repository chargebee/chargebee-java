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

public class Contact extends Resource<Contact> {

    //Constructors
    //============

    public Contact(String jsonStr) {
        super(jsonStr);
    }

    public Contact(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String firstName() {
        return optString("first_name");
    }

    public String lastName() {
        return optString("last_name");
    }

    public String email() {
        return reqString("email");
    }

    public String phone() {
        return optString("phone");
    }

    public String label() {
        return optString("label");
    }

    public Boolean enabled() {
        return reqBoolean("enabled");
    }

    public Boolean sendAccountEmail() {
        return reqBoolean("send_account_email");
    }

    public Boolean sendBillingEmail() {
        return reqBoolean("send_billing_email");
    }

    // Operations
    //===========


}
