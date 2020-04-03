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

public class ContractTerm extends Resource<ContractTerm> {

    public enum Status {
        ACTIVE,
        COMPLETED,
        CANCELLED,
        TERMINATED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum ActionAtTermEnd {
        RENEW,
        EVERGREEN,
        CANCEL,
        RENEW_ONCE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public ContractTerm(String jsonStr) {
        super(jsonStr);
    }

    public ContractTerm(JSONObject jsonObj) {
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

    public Timestamp contractStart() {
        return reqTimestamp("contract_start");
    }

    public Timestamp contractEnd() {
        return reqTimestamp("contract_end");
    }

    public Integer billingCycle() {
        return reqInteger("billing_cycle");
    }

    public ActionAtTermEnd actionAtTermEnd() {
        return reqEnum("action_at_term_end", ActionAtTermEnd.class);
    }

    public Long totalContractValue() {
        return reqLong("total_contract_value");
    }

    public Integer cancellationCutoffPeriod() {
        return optInteger("cancellation_cutoff_period");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public String subscriptionId() {
        return reqString("subscription_id");
    }

    public Integer remainingBillingCycles() {
        return optInteger("remaining_billing_cycles");
    }

    // Operations
    //===========


}
