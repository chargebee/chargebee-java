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

public class Einvoice extends Resource<Einvoice> {

    public enum Status {
        SCHEDULED,
        SKIPPED,
        IN_PROGRESS,
        SUCCESS,
        FAILED,
        REGISTERED,
        ACCEPTED,
        REJECTED,
        MESSAGE_ACKNOWLEDGEMENT,
        IN_PROCESS,
        UNDER_QUERY,
        CONDITIONALLY_ACCEPTED,
        PAID,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Einvoice(String jsonStr) {
        super(jsonStr);
    }

    public Einvoice(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String referenceId() {
        return optString("reference_id");
    }

    public String referenceNumber() {
        return optString("reference_number");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public String message() {
        return optString("message");
    }

    public JSONArray providerReferences() {
        return optJSONArray("provider_references");
    }

    // Operations
    //===========


}
