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

public class SubscriptionEstimate extends Resource<SubscriptionEstimate> {

    public enum Status {
        FUTURE,
        IN_TRIAL,
        ACTIVE,
        NON_RENEWING,
        PAUSED,
        CANCELLED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class ShippingAddress extends Resource<ShippingAddress> {
        public ShippingAddress(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String firstName() {
            return optString("first_name");
        }

        public String lastName() {
            return optString("last_name");
        }

        public String email() {
            return optString("email");
        }

        public String company() {
            return optString("company");
        }

        public String phone() {
            return optString("phone");
        }

        public String line1() {
            return optString("line1");
        }

        public String line2() {
            return optString("line2");
        }

        public String line3() {
            return optString("line3");
        }

        public String city() {
            return optString("city");
        }

        public String stateCode() {
            return optString("state_code");
        }

        public String state() {
            return optString("state");
        }

        public String country() {
            return optString("country");
        }

        public String zip() {
            return optString("zip");
        }

        public ValidationStatus validationStatus() {
            return optEnum("validation_status", ValidationStatus.class);
        }

        public Integer index() {
            return reqInteger("index");
        }

    }

    public static class ContractTerm extends Resource<ContractTerm> {
        public enum Status {
             ACTIVE,COMPLETED,CANCELLED,TERMINATED,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum ActionAtTermEnd {
             RENEW,EVERGREEN,CANCEL,RENEW_ONCE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public ContractTerm(JSONObject jsonObj) {
            super(jsonObj);
        }

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

    }

    //Constructors
    //============

    public SubscriptionEstimate(String jsonStr) {
        super(jsonStr);
    }

    public SubscriptionEstimate(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return optString("id");
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public TrialEndAction trialEndAction() {
        return optEnum("trial_end_action", TrialEndAction.class);
    }

    public Timestamp nextBillingAt() {
        return optTimestamp("next_billing_at");
    }

    public Timestamp pauseDate() {
        return optTimestamp("pause_date");
    }

    public Timestamp resumeDate() {
        return optTimestamp("resume_date");
    }

    public SubscriptionEstimate.ShippingAddress shippingAddress() {
        return optSubResource("shipping_address", SubscriptionEstimate.ShippingAddress.class);
    }

    public SubscriptionEstimate.ContractTerm contractTerm() {
        return optSubResource("contract_term", SubscriptionEstimate.ContractTerm.class);
    }

    // Operations
    //===========


}
