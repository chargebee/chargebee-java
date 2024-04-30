package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.filters.*;
import com.chargebee.filters.enums.SortOrder;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

public class InstallmentConfig extends Resource<InstallmentConfig> {

    public enum PeriodUnit {
        DAY,
        WEEK,
        MONTH,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class Installment extends Resource<Installment> {
        public Installment(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Integer period() {
            return optInteger("period");
        }

        public BigDecimal amountPercentage() {
            return optBigDecimal("amount_percentage");
        }

    }

    //Constructors
    //============

    public InstallmentConfig(String jsonStr) {
        super(jsonStr);
    }

    public InstallmentConfig(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String description() {
        return optString("description");
    }

    public Integer numberOfInstallments() {
        return reqInteger("number_of_installments");
    }

    public PeriodUnit periodUnit() {
        return reqEnum("period_unit", PeriodUnit.class);
    }

    public Integer period() {
        return optInteger("period");
    }

    public Integer preferredDay() {
        return optInteger("preferred_day");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public List<InstallmentConfig.Installment> installments() {
        return optList("installments", InstallmentConfig.Installment.class);
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("installment_configs");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("installment_configs", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static Request delete(String id) {
        String uri = uri("installment_configs", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest numberOfInstallments(Integer numberOfInstallments) {
            params.add("number_of_installments", numberOfInstallments);
            return this;
        }


        public CreateRequest periodUnit(InstallmentConfig.PeriodUnit periodUnit) {
            params.add("period_unit", periodUnit);
            return this;
        }


        public CreateRequest period(Integer period) {
            params.addOpt("period", period);
            return this;
        }


        public CreateRequest preferredDay(Integer preferredDay) {
            params.addOpt("preferred_day", preferredDay);
            return this;
        }


        public CreateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        public CreateRequest installmentPeriod(int index, Integer installmentPeriod) {
            params.addOpt("installments[period][" + index + "]", installmentPeriod);
            return this;
        }
        public CreateRequest installmentAmountPercentage(int index, BigDecimal installmentAmountPercentage) {
            params.addOpt("installments[amount_percentage][" + index + "]", installmentAmountPercentage);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

}
