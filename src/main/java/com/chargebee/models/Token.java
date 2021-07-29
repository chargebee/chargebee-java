package com.chargebee.models;

import com.chargebee.internal.*;
import com.chargebee.models.enums.*;
import org.json.*;
import java.sql.Timestamp;

public class Token extends Resource<Token> {

    public enum Status {
        NEW,
        EXPIRED,
        CONSUMED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Vault {
        SPREEDLY,
        GATEWAY,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Token(String jsonStr) {
        super(jsonStr);
    }

    public Token(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public Gateway gateway() {
        return reqEnum("gateway", Gateway.class);
    }

    public String gatewayAccountId() {
        return reqString("gateway_account_id");
    }

    public PaymentMethodType paymentMethodType() {
        return reqEnum("payment_method_type", PaymentMethodType.class);
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public String idAtVault() {
        return reqString("id_at_vault");
    }

    public Vault vault() {
        return reqEnum("vault", Vault.class);
    }

    public String ipAddress() {
        return optString("ip_address");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp expiredAt() {
        return optTimestamp("expired_at");
    }

    // Operations
    //===========


}
