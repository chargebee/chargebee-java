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

public class VirtualBankAccount extends Resource<VirtualBankAccount> {

    public enum Scheme {
        ACH_CREDIT,
        SEPA_CREDIT,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public VirtualBankAccount(String jsonStr) {
        super(jsonStr);
    }

    public VirtualBankAccount(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String customerId() {
        return reqString("customer_id");
    }

    public String email() {
        return reqString("email");
    }

    public Scheme scheme() {
        return optEnum("scheme", Scheme.class);
    }

    public String bankName() {
        return optString("bank_name");
    }

    public String accountNumber() {
        return reqString("account_number");
    }

    public String routingNumber() {
        return optString("routing_number");
    }

    public String swiftCode() {
        return reqString("swift_code");
    }

    public Gateway gateway() {
        return reqEnum("gateway", Gateway.class);
    }

    public String gatewayAccountId() {
        return reqString("gateway_account_id");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public String referenceId() {
        return reqString("reference_id");
    }

    public Boolean deleted() {
        return reqBoolean("deleted");
    }

    // Operations
    //===========

    public static CreateUsingPermanentTokenRequest createUsingPermanentToken() {
        String uri = uri("virtual_bank_accounts", "create_using_permanent_token");
        return new CreateUsingPermanentTokenRequest(Method.POST, uri);
    }

    public static CreateRequest create() {
        String uri = uri("virtual_bank_accounts");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("virtual_bank_accounts", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static VirtualBankAccountListRequest list() {
        String uri = uri("virtual_bank_accounts");
        return new VirtualBankAccountListRequest(uri);
    }

    public static Request delete(String id) {
        String uri = uri("virtual_bank_accounts", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static Request deleteLocal(String id) {
        String uri = uri("virtual_bank_accounts", nullCheck(id), "delete_local");
        return new Request(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateUsingPermanentTokenRequest extends Request<CreateUsingPermanentTokenRequest> {

        private CreateUsingPermanentTokenRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateUsingPermanentTokenRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public CreateUsingPermanentTokenRequest referenceId(String referenceId) {
            params.add("reference_id", referenceId);
            return this;
        }


        public CreateUsingPermanentTokenRequest scheme(VirtualBankAccount.Scheme scheme) {
            params.addOpt("scheme", scheme);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public CreateRequest email(String email) {
            params.addOpt("email", email);
            return this;
        }


        public CreateRequest scheme(VirtualBankAccount.Scheme scheme) {
            params.addOpt("scheme", scheme);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class VirtualBankAccountListRequest extends ListRequest<VirtualBankAccountListRequest> {

        private VirtualBankAccountListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<VirtualBankAccountListRequest> customerId() {
            return new StringFilter<VirtualBankAccountListRequest>("customer_id",this).supportsMultiOperators(true);        
        }


        public TimestampFilter<VirtualBankAccountListRequest> updatedAt() {
            return new TimestampFilter<VirtualBankAccountListRequest>("updated_at",this);        
        }


        public TimestampFilter<VirtualBankAccountListRequest> createdAt() {
            return new TimestampFilter<VirtualBankAccountListRequest>("created_at",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
