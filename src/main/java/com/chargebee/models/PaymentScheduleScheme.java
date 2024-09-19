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

public class PaymentScheduleScheme extends Resource<PaymentScheduleScheme> {

    public enum PeriodUnit {
        DAY,
        WEEK,
        MONTH,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class PreferredSchedule extends Resource<PreferredSchedule> {
        public PreferredSchedule(JSONObject jsonObj) {
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

    public PaymentScheduleScheme(String jsonStr) {
        super(jsonStr);
    }

    public PaymentScheduleScheme(JSONObject jsonObj) {
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

    public Integer numberOfSchedules() {
        return reqInteger("number_of_schedules");
    }

    public PeriodUnit periodUnit() {
        return reqEnum("period_unit", PeriodUnit.class);
    }

    public Integer period() {
        return optInteger("period");
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

    @Deprecated
    public List<PaymentScheduleScheme.PreferredSchedule> preferredSchedules() {
        return optList("preferred_schedules", PaymentScheduleScheme.PreferredSchedule.class);
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("payment_schedule_schemes");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("payment_schedule_schemes", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static Request delete(String id) {
        String uri = uri("payment_schedule_schemes", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest numberOfSchedules(Integer numberOfSchedules) {
            params.add("number_of_schedules", numberOfSchedules);
            return this;
        }


        public CreateRequest periodUnit(PaymentScheduleScheme.PeriodUnit periodUnit) {
            params.add("period_unit", periodUnit);
            return this;
        }


        public CreateRequest period(Integer period) {
            params.addOpt("period", period);
            return this;
        }


        public CreateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
