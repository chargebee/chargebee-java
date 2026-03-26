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

public class CpqQuoteSignature extends Resource<CpqQuoteSignature> {

    public enum Status {
        DRAFT,
        ACTIVE,
        SIGNED,
        EXPIRED,
        CANCELLED,
        DECLINED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum CustomerAcceptanceMethod {
        ESIGN_AND_PAY,
        ESIGN,
        PAY,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum QuoteType {
        CONSOLIDATED,
        DETAILED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public CpqQuoteSignature(String jsonStr) {
        super(jsonStr);
    }

    public CpqQuoteSignature(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public String name() {
        return optString("name");
    }

    public String documentName() {
        return optString("document_name");
    }

    public CustomerAcceptanceMethod customerAcceptanceMethod() {
        return reqEnum("customer_acceptance_method", CustomerAcceptanceMethod.class);
    }

    public QuoteType quoteType() {
        return reqEnum("quote_type", QuoteType.class);
    }

    public Timestamp expiresAt() {
        return optTimestamp("expires_at");
    }

    public String timezone() {
        return optString("timezone");
    }

    public String providerRequestId() {
        return optString("provider_request_id");
    }

    public String providerDocumentId() {
        return optString("provider_document_id");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp modifiedAt() {
        return reqTimestamp("modified_at");
    }

    // Operations
    //===========


}
