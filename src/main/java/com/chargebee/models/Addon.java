package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class Addon extends Resource<Addon> {

    public enum Type {
        ON_OFF,
        QUANTITY,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum ChargeType {
        RECURRING,
        NON_RECURRING,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum PeriodUnit {
        WEEK,
        MONTH,
        YEAR,
        NOT_APPLICABLE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Status {
        ACTIVE,
        ARCHIVED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Addon(String jsonStr) {
        super(jsonStr);
    }

    public Addon(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String name() {
        return reqString("name");
    }

    public String invoiceName() {
        return optString("invoice_name");
    }

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public ChargeType chargeType() {
        return reqEnum("charge_type", ChargeType.class);
    }

    public Integer price() {
        return reqInteger("price");
    }

    public Integer period() {
        return optInteger("period");
    }

    public PeriodUnit periodUnit() {
        return reqEnum("period_unit", PeriodUnit.class);
    }

    public String unit() {
        return optString("unit");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp archivedAt() {
        return optTimestamp("archived_at");
    }

    // Operations
    //===========

    public static ListRequest list() throws IOException {
        String uri = uri("addons");
        return new ListRequest(uri);
    }

    public static Request retrieve(String id) throws IOException {
        String uri = uri("addons", nullCheck(id));
        return new Request(Method.GET, uri);
    }


}
