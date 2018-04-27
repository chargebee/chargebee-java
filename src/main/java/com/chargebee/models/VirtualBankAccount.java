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

    public String bankName() {
        return optString("bank_name");
    }

    public String accountNumber() {
        return reqString("account_number");
    }

    public String routingNumber() {
        return reqString("routing_number");
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

    public String referenceId() {
        return reqString("reference_id");
    }

    public Boolean deleted() {
        return reqBoolean("deleted");
    }

    // Operations
    //===========

    public static CreateUsingPermanentTokenRequest createUsingPermanentToken() throws IOException {
        String uri = uri("virtual_bank_accounts", "create_using_permanent_token");
        return new CreateUsingPermanentTokenRequest(Method.POST, uri);
    }

    public static CreateRequest create() throws IOException {
        String uri = uri("virtual_bank_accounts");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) throws IOException {
        String uri = uri("virtual_bank_accounts", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static VirtualBankAccountListRequest list() throws IOException {
        String uri = uri("virtual_bank_accounts");
        return new VirtualBankAccountListRequest(uri);
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


        @Override
        public Params params() {
            return params;
        }
    }

}
