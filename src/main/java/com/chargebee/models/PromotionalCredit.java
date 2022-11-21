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

public class PromotionalCredit extends Resource<PromotionalCredit> {

    public enum Type {
        INCREMENT,
        DECREMENT,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public PromotionalCredit(String jsonStr) {
        super(jsonStr);
    }

    public PromotionalCredit(JSONObject jsonObj) {
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

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public String amountInDecimal() {
        return optString("amount_in_decimal");
    }

    public Long amount() {
        return reqLong("amount");
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public String description() {
        return reqString("description");
    }

    public CreditType creditType() {
        return reqEnum("credit_type", CreditType.class);
    }

    public String reference() {
        return optString("reference");
    }

    public Long closingBalance() {
        return reqLong("closing_balance");
    }

    public String doneBy() {
        return optString("done_by");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    // Operations
    //===========

    public static AddRequest add() {
        String uri = uri("promotional_credits", "add");
        return new AddRequest(Method.POST, uri);
    }

    public static DeductRequest deduct() {
        String uri = uri("promotional_credits", "deduct");
        return new DeductRequest(Method.POST, uri);
    }

    public static SetRequest set() {
        String uri = uri("promotional_credits", "set");
        return new SetRequest(Method.POST, uri);
    }

    public static PromotionalCreditListRequest list() {
        String uri = uri("promotional_credits");
        return new PromotionalCreditListRequest(uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("promotional_credits", nullCheck(id));
        return new Request(Method.GET, uri);
    }


    // Operation Request Classes
    //==========================

    public static class AddRequest extends Request<AddRequest> {

        private AddRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public AddRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public AddRequest amount(Long amount) {
            params.addOpt("amount", amount);
            return this;
        }


        public AddRequest amountInDecimal(String amountInDecimal) {
            params.addOpt("amount_in_decimal", amountInDecimal);
            return this;
        }


        public AddRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public AddRequest description(String description) {
            params.add("description", description);
            return this;
        }


        public AddRequest creditType(com.chargebee.models.enums.CreditType creditType) {
            params.addOpt("credit_type", creditType);
            return this;
        }


        public AddRequest reference(String reference) {
            params.addOpt("reference", reference);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class DeductRequest extends Request<DeductRequest> {

        private DeductRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public DeductRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public DeductRequest amount(Long amount) {
            params.addOpt("amount", amount);
            return this;
        }


        public DeductRequest amountInDecimal(String amountInDecimal) {
            params.addOpt("amount_in_decimal", amountInDecimal);
            return this;
        }


        public DeductRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public DeductRequest description(String description) {
            params.add("description", description);
            return this;
        }


        public DeductRequest creditType(com.chargebee.models.enums.CreditType creditType) {
            params.addOpt("credit_type", creditType);
            return this;
        }


        public DeductRequest reference(String reference) {
            params.addOpt("reference", reference);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class SetRequest extends Request<SetRequest> {

        private SetRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public SetRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public SetRequest amount(Long amount) {
            params.addOpt("amount", amount);
            return this;
        }


        public SetRequest amountInDecimal(String amountInDecimal) {
            params.addOpt("amount_in_decimal", amountInDecimal);
            return this;
        }


        public SetRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public SetRequest description(String description) {
            params.add("description", description);
            return this;
        }


        public SetRequest creditType(com.chargebee.models.enums.CreditType creditType) {
            params.addOpt("credit_type", creditType);
            return this;
        }


        public SetRequest reference(String reference) {
            params.addOpt("reference", reference);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class PromotionalCreditListRequest extends ListRequest<PromotionalCreditListRequest> {

        private PromotionalCreditListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<PromotionalCreditListRequest> id() {
            return new StringFilter<PromotionalCreditListRequest>("id",this);        
        }


        public TimestampFilter<PromotionalCreditListRequest> createdAt() {
            return new TimestampFilter<PromotionalCreditListRequest>("created_at",this);        
        }


        public EnumFilter<PromotionalCredit.Type, PromotionalCreditListRequest> type() {
            return new EnumFilter<PromotionalCredit.Type, PromotionalCreditListRequest>("type",this);        
        }


        public StringFilter<PromotionalCreditListRequest> customerId() {
            return new StringFilter<PromotionalCreditListRequest>("customer_id",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
