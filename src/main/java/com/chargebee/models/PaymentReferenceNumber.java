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

public class PaymentReferenceNumber extends Resource<PaymentReferenceNumber> {

    public enum Type {
        KID,
        OCR,
        FRN,
        FIK,
        SWISS_REFERENCE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public PaymentReferenceNumber(String jsonStr) {
        super(jsonStr);
    }

    public PaymentReferenceNumber(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public String number() {
        return reqString("number");
    }

    public String invoiceId() {
        return optString("invoice_id");
    }

    // Operations
    //===========


}
