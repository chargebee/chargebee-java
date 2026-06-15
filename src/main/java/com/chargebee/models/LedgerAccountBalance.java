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

public class LedgerAccountBalance extends Resource<LedgerAccountBalance> {

    public enum UnitType {
        CREDIT_UNIT,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class ProvisionedBalance extends Resource<ProvisionedBalance> {
        public ProvisionedBalance(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String totalBalance() {
            return reqString("total_balance");
        }

        public String usableBalance() {
            return reqString("usable_balance");
        }

        public String holdAmount() {
            return reqString("hold_amount");
        }

    }

    public static class OverdraftBalance extends Resource<OverdraftBalance> {
        public OverdraftBalance(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Boolean isUnlimited() {
            return reqBoolean("is_unlimited");
        }

        public String limit() {
            return optString("limit");
        }

        public String totalBalance() {
            return optString("total_balance");
        }

        public String usableBalance() {
            return optString("usable_balance");
        }

        public String usedAmount() {
            return reqString("used_amount");
        }

        public String holdAmount() {
            return reqString("hold_amount");
        }

    }

    //Constructors
    //============

    public LedgerAccountBalance(String jsonStr) {
        super(jsonStr);
    }

    public LedgerAccountBalance(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String subscriptionId() {
        return reqString("subscription_id");
    }

    public String unitId() {
        return reqString("unit_id");
    }

    public UnitType unitType() {
        return reqEnum("unit_type", UnitType.class);
    }

    public Timestamp modifiedAt() {
        return optTimestamp("modified_at");
    }

    public LedgerAccountBalance.ProvisionedBalance provisionedBalance() {
        return optSubResource("provisioned_balance", LedgerAccountBalance.ProvisionedBalance.class);
    }

    public LedgerAccountBalance.OverdraftBalance overdraftBalance() {
        return optSubResource("overdraft_balance", LedgerAccountBalance.OverdraftBalance.class);
    }

    // Operations
    //===========

    public static LedgerAccountBalanceListLedgerAccountBalancesRequest listLedgerAccountBalances() {
        String uri = uri("ledger_account_balances");
        return new LedgerAccountBalanceListLedgerAccountBalancesRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class LedgerAccountBalanceListLedgerAccountBalancesRequest extends ListRequest<LedgerAccountBalanceListLedgerAccountBalancesRequest> {

        private LedgerAccountBalanceListLedgerAccountBalancesRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<LedgerAccountBalanceListLedgerAccountBalancesRequest> subscriptionId() {
            return new StringFilter<LedgerAccountBalanceListLedgerAccountBalancesRequest>("subscription_id",this);        
        }


        public StringFilter<LedgerAccountBalanceListLedgerAccountBalancesRequest> unitId() {
            return new StringFilter<LedgerAccountBalanceListLedgerAccountBalancesRequest>("unit_id",this);        
        }



        @Override
        public Params params() {
            return params;
        }
    }

}
