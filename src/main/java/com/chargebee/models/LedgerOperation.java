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

public class LedgerOperation extends Resource<LedgerOperation> {

    public enum Type {
        ALLOCATION,
        CAPTURE,
        AUTHORIZE,
        RELEASE_AUTHORIZATION,
        CAPTURE_AUTHORIZATION,
        EXPIRY,
        VOID,
        ROLLOVER,
        ADJUSTMENT,
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

    public LedgerOperation(String jsonStr) {
        super(jsonStr);
    }

    public LedgerOperation(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public String amount() {
        return reqString("amount");
    }

    public String provisionedStartBalance() {
        return reqString("provisioned_start_balance");
    }

    public String provisionedEndBalance() {
        return reqString("provisioned_end_balance");
    }

    public String overdraftStartBalance() {
        return reqString("overdraft_start_balance");
    }

    public String overdraftEndBalance() {
        return reqString("overdraft_end_balance");
    }

    public String parentLedgerOperationId() {
        return optString("parent_ledger_operation_id");
    }

    public Timestamp ledgerOperationTimestamp() {
        return optTimestamp("ledger_operation_timestamp");
    }

    public Timestamp autoReleaseTimestamp() {
        return optTimestamp("auto_release_timestamp");
    }

    public Timestamp createdAt() {
        return optTimestamp("created_at");
    }

    public Timestamp modifiedAt() {
        return optTimestamp("modified_at");
    }

    public String subscriptionId() {
        return optString("subscription_id");
    }

    public String unitId() {
        return optString("unit_id");
    }

    public UnitType unitType() {
        return optEnum("unit_type", UnitType.class);
    }

    public Map<String, Object> metadata() {
        return optMap("metadata");
    }

    // Operations
    //===========

    public static Request retrieveLedgerOperation(String id) {
        String uri = uri("ledger_operations", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static LedgerOperationListLedgerOperationsRequest listLedgerOperations() {
        String uri = uri("ledger_operations");
        return new LedgerOperationListLedgerOperationsRequest(uri);
    }

    public static CaptureRequest capture() {
        String uri = uri("ledger_operations", "capture");
        return new CaptureRequest(Method.POST, uri).setIdempotency(false);
    }

    public static AuthorizeRequest authorize() {
        String uri = uri("ledger_operations", "authorize");
        return new AuthorizeRequest(Method.POST, uri).setIdempotency(false);
    }

    public static CaptureAuthorizationRequest captureAuthorization() {
        String uri = uri("ledger_operations", "capture_authorization");
        return new CaptureAuthorizationRequest(Method.POST, uri).setIdempotency(false);
    }

    public static ReleaseAuthorizationRequest releaseAuthorization() {
        String uri = uri("ledger_operations", "release_authorization");
        return new ReleaseAuthorizationRequest(Method.POST, uri).setIdempotency(false);
    }


    // Operation Request Classes
    //==========================

    public static class LedgerOperationListLedgerOperationsRequest extends ListRequest<LedgerOperationListLedgerOperationsRequest> {

        private LedgerOperationListLedgerOperationsRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<LedgerOperationListLedgerOperationsRequest> subscriptionId() {
            return new StringFilter<LedgerOperationListLedgerOperationsRequest>("subscription_id",this);        
        }


        public StringFilter<LedgerOperationListLedgerOperationsRequest> unitId() {
            return new StringFilter<LedgerOperationListLedgerOperationsRequest>("unit_id",this);        
        }


        public TimestampFilter<LedgerOperationListLedgerOperationsRequest> createdAt() {
            return new TimestampFilter<LedgerOperationListLedgerOperationsRequest>("created_at",this);        
        }


        public EnumFilter<LedgerOperation.Type, LedgerOperationListLedgerOperationsRequest> type() {
            return new EnumFilter<LedgerOperation.Type, LedgerOperationListLedgerOperationsRequest>("type",this).supportsMultiOperators(true);        
        }


        public LedgerOperationListLedgerOperationsRequest sortByCreatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","created_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class CaptureRequest extends Request<CaptureRequest> {

        private CaptureRequest(Method httpMeth, String uri) {
            super(httpMeth, uri, null, null,true);
        }
    
        public CaptureRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }


        public CaptureRequest subscriptionId(String subscriptionId) {
            params.add("subscription_id", subscriptionId);
            return this;
        }


        public CaptureRequest unitId(String unitId) {
            params.add("unit_id", unitId);
            return this;
        }


        public CaptureRequest amount(String amount) {
            params.add("amount", amount);
            return this;
        }


        public CaptureRequest ledgerOperationTimestamp(Timestamp ledgerOperationTimestamp) {
            params.add("ledger_operation_timestamp", ledgerOperationTimestamp);
            return this;
        }


        public CaptureRequest metadata(Map<String, Object> metadata) {
            params.addOpt("metadata", metadata);
            return this;
        }



        @Override
        public Params params() {
            return params;
        }
    }

    public static class AuthorizeRequest extends Request<AuthorizeRequest> {

        private AuthorizeRequest(Method httpMeth, String uri) {
            super(httpMeth, uri, null, null,true);
        }
    
        public AuthorizeRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }


        public AuthorizeRequest subscriptionId(String subscriptionId) {
            params.add("subscription_id", subscriptionId);
            return this;
        }


        public AuthorizeRequest unitId(String unitId) {
            params.add("unit_id", unitId);
            return this;
        }


        public AuthorizeRequest amount(String amount) {
            params.add("amount", amount);
            return this;
        }


        public AuthorizeRequest ledgerOperationTimestamp(Timestamp ledgerOperationTimestamp) {
            params.add("ledger_operation_timestamp", ledgerOperationTimestamp);
            return this;
        }


        public AuthorizeRequest autoReleaseTimestamp(Timestamp autoReleaseTimestamp) {
            params.addOpt("auto_release_timestamp", autoReleaseTimestamp);
            return this;
        }


        public AuthorizeRequest metadata(Map<String, Object> metadata) {
            params.addOpt("metadata", metadata);
            return this;
        }



        @Override
        public Params params() {
            return params;
        }
    }

    public static class CaptureAuthorizationRequest extends Request<CaptureAuthorizationRequest> {

        private CaptureAuthorizationRequest(Method httpMeth, String uri) {
            super(httpMeth, uri, null, null,true);
        }
    
        public CaptureAuthorizationRequest authorizationId(String authorizationId) {
            params.add("authorization_id", authorizationId);
            return this;
        }


        public CaptureAuthorizationRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }


        public CaptureAuthorizationRequest amount(String amount) {
            params.add("amount", amount);
            return this;
        }


        public CaptureAuthorizationRequest ledgerOperationTimestamp(Timestamp ledgerOperationTimestamp) {
            params.add("ledger_operation_timestamp", ledgerOperationTimestamp);
            return this;
        }


        public CaptureAuthorizationRequest metadata(Map<String, Object> metadata) {
            params.addOpt("metadata", metadata);
            return this;
        }



        @Override
        public Params params() {
            return params;
        }
    }

    public static class ReleaseAuthorizationRequest extends Request<ReleaseAuthorizationRequest> {

        private ReleaseAuthorizationRequest(Method httpMeth, String uri) {
            super(httpMeth, uri, null, null,true);
        }
    
        public ReleaseAuthorizationRequest authorizationId(String authorizationId) {
            params.add("authorization_id", authorizationId);
            return this;
        }


        public ReleaseAuthorizationRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }


        public ReleaseAuthorizationRequest ledgerOperationTimestamp(Timestamp ledgerOperationTimestamp) {
            params.add("ledger_operation_timestamp", ledgerOperationTimestamp);
            return this;
        }


        public ReleaseAuthorizationRequest metadata(Map<String, Object> metadata) {
            params.addOpt("metadata", metadata);
            return this;
        }



        @Override
        public Params params() {
            return params;
        }
    }

}
