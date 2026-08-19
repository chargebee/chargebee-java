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

public class GatewayPaymentMethodToken extends Resource<GatewayPaymentMethodToken> {

    public enum Status {
        ACTIVE,
        INACTIVE,
        PENDING_VERIFICATION,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public GatewayPaymentMethodToken(String jsonStr) {
        super(jsonStr);
    }

    public GatewayPaymentMethodToken(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String gatewayAccountId() {
        return reqString("gateway_account_id");
    }

    public GatewayName gatewayName() {
        return reqEnum("gateway_name", GatewayName.class);
    }

    public String gatewayCustomerId() {
        return optString("gateway_customer_id");
    }

    public String gatewayToken() {
        return reqString("gateway_token");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    // Operations
    //===========


}
