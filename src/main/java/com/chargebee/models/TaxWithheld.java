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

public class TaxWithheld extends Resource<TaxWithheld> {

    @Deprecated
    public enum Type {
        PAYMENT,
        REFUND,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    @Deprecated
    public enum PaymentMethod {
        CASH,
        CHECK,
        CHARGEBACK,
        BANK_TRANSFER,
        OTHER,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public TaxWithheld(String jsonStr) {
        super(jsonStr);
    }

    public TaxWithheld(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    @Deprecated
    public String user() {
        return optString("user");
    }

    public String referenceNumber() {
        return optString("reference_number");
    }

    public String description() {
        return optString("description");
    }

    @Deprecated
    public Type type() {
        return reqEnum("type", Type.class);
    }

    @Deprecated
    public PaymentMethod paymentMethod() {
        return reqEnum("payment_method", PaymentMethod.class);
    }

    public Timestamp date() {
        return optTimestamp("date");
    }

    @Deprecated
    public String currencyCode() {
        return reqString("currency_code");
    }

    public Long amount() {
        return optLong("amount");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    @Deprecated
    public BigDecimal exchangeRate() {
        return optBigDecimal("exchange_rate");
    }

    // Operations
    //===========


}
