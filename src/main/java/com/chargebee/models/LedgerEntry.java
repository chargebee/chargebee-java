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

public class LedgerEntry extends Resource<LedgerEntry> {

    public enum AccountType {
        PROVISIONED,
        OVERDRAFT,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum UnitType {
        CREDIT_UNIT,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public LedgerEntry(String jsonStr) {
        super(jsonStr);
    }

    public LedgerEntry(JSONObject jsonObj) {
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

    public AccountType accountType() {
        return optEnum("account_type", AccountType.class);
    }

    public String unitId() {
        return optString("unit_id");
    }

    public UnitType unitType() {
        return optEnum("unit_type", UnitType.class);
    }

    public String amount() {
        return reqString("amount");
    }

    public String grantBlockStartBalance() {
        return reqString("grant_block_start_balance");
    }

    public String grantBlockEndBalance() {
        return reqString("grant_block_end_balance");
    }

    public String accountStartBalance() {
        return reqString("account_start_balance");
    }

    public String accountEndBalance() {
        return reqString("account_end_balance");
    }

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public String ledgerOperationId() {
        return reqString("ledger_operation_id");
    }

    public String grantBlockId() {
        return reqString("grant_block_id");
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
