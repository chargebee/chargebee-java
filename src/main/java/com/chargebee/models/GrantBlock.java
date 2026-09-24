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

public class GrantBlock extends Resource<GrantBlock> {

    public enum UnitType {
        CREDIT_UNIT,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum AccountType {
        PROVISIONED,
        OVERDRAFT,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum GrantSource {
        SUBSCRIPTION_CREATED,
        SUBSCRIPTION_CHANGED,
        TOP_UP,
        PROMOTIONAL_GRANTS,
        ROLLOVER,
        GRANT_RENEWAL,
        SUBSCRIPTION_RENEWED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class ProvisionedBlockBalance extends Resource<ProvisionedBlockBalance> {
        public ProvisionedBlockBalance(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String grantedAmount() {
            return optString("granted_amount");
        }

        public String totalBalance() {
            return optString("total_balance");
        }

        public String usableBalance() {
            return optString("usable_balance");
        }

        public String holdAmount() {
            return optString("hold_amount");
        }

        public String usedAmount() {
            return optString("used_amount");
        }

        public String expiredAmount() {
            return optString("expired_amount");
        }

        public String rolledOverAmount() {
            return optString("rolled_over_amount");
        }

        public String voidedAmount() {
            return optString("voided_amount");
        }

    }

    public static class OverdraftBlockBalance extends Resource<OverdraftBlockBalance> {
        public OverdraftBlockBalance(JSONObject jsonObj) {
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
            return optString("used_amount");
        }

    }

    //Constructors
    //============

    public GrantBlock(String jsonStr) {
        super(jsonStr);
    }

    public GrantBlock(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String subscriptionId() {
        return reqString("subscription_id");
    }

    public String unitId() {
        return reqString("unit_id");
    }

    public UnitType unitType() {
        return reqEnum("unit_type", UnitType.class);
    }

    public AccountType accountType() {
        return reqEnum("account_type", AccountType.class);
    }

    @Deprecated
    public String grantedAmount() {
        return reqString("granted_amount");
    }

    public Timestamp effectiveFrom() {
        return reqTimestamp("effective_from");
    }

    public Timestamp expiresAt() {
        return reqTimestamp("expires_at");
    }

    @Deprecated
    public String balance() {
        return reqString("balance");
    }

    @Deprecated
    public String holdAmount() {
        return reqString("hold_amount");
    }

    @Deprecated
    public String usedAmount() {
        return reqString("used_amount");
    }

    @Deprecated
    public String expiredAmount() {
        return optString("expired_amount");
    }

    @Deprecated
    public String rolledOverAmount() {
        return optString("rolled_over_amount");
    }

    @Deprecated
    public String voidedAmount() {
        return optString("voided_amount");
    }

    public String originGrantBlockId() {
        return optString("origin_grant_block_id");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public GrantSource grantSource() {
        return reqEnum("grant_source", GrantSource.class);
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp modifiedAt() {
        return reqTimestamp("modified_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public GrantBlock.ProvisionedBlockBalance provisionedBlockBalance() {
        return optSubResource("provisioned_block_balance", GrantBlock.ProvisionedBlockBalance.class);
    }

    public GrantBlock.OverdraftBlockBalance overdraftBlockBalance() {
        return optSubResource("overdraft_block_balance", GrantBlock.OverdraftBlockBalance.class);
    }

    public JSONObject metadata() {
        return optJSONObject("metadata");
    }

    // Operations
    //===========

    public static GrantBlockListGrantBlocksRequest listGrantBlocks() {
        String uri = uri("grant_blocks");
        return new GrantBlockListGrantBlocksRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class GrantBlockListGrantBlocksRequest extends ListRequest<GrantBlockListGrantBlocksRequest> {

        private GrantBlockListGrantBlocksRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<GrantBlockListGrantBlocksRequest> subscriptionId() {
            return new StringFilter<GrantBlockListGrantBlocksRequest>("subscription_id",this);        
        }


        public StringFilter<GrantBlockListGrantBlocksRequest> unitId() {
            return new StringFilter<GrantBlockListGrantBlocksRequest>("unit_id",this);        
        }


        public EnumFilter<AccountType, GrantBlockListGrantBlocksRequest> accountType() {
            return new EnumFilter<AccountType, GrantBlockListGrantBlocksRequest>("account_type",this);        
        }


        public TimestampFilter<GrantBlockListGrantBlocksRequest> effectiveFrom() {
            return new TimestampFilter<GrantBlockListGrantBlocksRequest>("effective_from",this);        
        }


        public TimestampFilter<GrantBlockListGrantBlocksRequest> expiresAt() {
            return new TimestampFilter<GrantBlockListGrantBlocksRequest>("expires_at",this);        
        }


        public TimestampFilter<GrantBlockListGrantBlocksRequest> createdAt() {
            return new TimestampFilter<GrantBlockListGrantBlocksRequest>("created_at",this);        
        }


        public GrantBlockListGrantBlocksRequest sortByEffectiveFrom(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","effective_from");
            return this;
        }
        public GrantBlockListGrantBlocksRequest sortByExpiresAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","expires_at");
            return this;
        }
        public GrantBlockListGrantBlocksRequest sortByCreatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","created_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
