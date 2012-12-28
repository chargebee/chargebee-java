package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class Transaction extends Resource<Transaction> {

    public enum PaymentMethod {
        CARD,
        OFFLINE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Type {
        AUTHORIZATION,
        PAYMENT,
        REFUND,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Status {
        SUCCESS,
        VOIDED,
        FAILURE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Transaction(String jsonStr) {
        super(jsonStr);
    }

    public Transaction(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String subscriptionId() {
        return optString("subscription_id");
    }

    public PaymentMethod paymentMethod() {
        return reqEnum("payment_method", PaymentMethod.class);
    }

    public Gateway gateway() {
        return reqEnum("gateway", Gateway.class);
    }

    public String description() {
        return optString("description");
    }

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public Timestamp date() {
        return optTimestamp("date");
    }

    public Integer amount() {
        return optInteger("amount");
    }

    public String idAtGateway() {
        return optString("id_at_gateway");
    }

    public String errorCode() {
        return optString("error_code");
    }

    public String errorText() {
        return optString("error_text");
    }

    public Long refundedTxId() {
        return optLong("refunded_tx_id");
    }

    public Timestamp voidedAt() {
        return optTimestamp("voided_at");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public String maskedCardNumber() {
        return reqString("masked_card_number");
    }

    // Operations
    //===========

    public static ListRequest list() throws IOException {
        String url = url("transactions");
        return new ListRequest(url);
    }

    public static Request retrieve(String id) throws IOException {
        String url = url("transactions", nullCheck(id));
        return new Request(Method.GET, url);
    }

    public static ListRequest transactionsForSubscription(String id) throws IOException {
        String url = url("subscriptions", nullCheck(id), "transactions");
        return new ListRequest(url);
    }


}
