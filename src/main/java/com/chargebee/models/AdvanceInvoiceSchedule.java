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

public class AdvanceInvoiceSchedule extends Resource<AdvanceInvoiceSchedule> {

    public enum ScheduleType {
        FIXED_INTERVALS,
        SPECIFIC_DATES,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class FixedIntervalSchedule extends Resource<FixedIntervalSchedule> {
        public enum EndScheduleOn {
             AFTER_NUMBER_OF_INTERVALS,SPECIFIC_DATE,SUBSCRIPTION_END,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public FixedIntervalSchedule(JSONObject jsonObj) {
            super(jsonObj);
        }

        public EndScheduleOn endScheduleOn() {
            return optEnum("end_schedule_on", EndScheduleOn.class);
        }

        public Integer numberOfOccurrences() {
            return optInteger("number_of_occurrences");
        }

        public Integer daysBeforeRenewal() {
            return optInteger("days_before_renewal");
        }

        public Timestamp endDate() {
            return optTimestamp("end_date");
        }

        public Timestamp createdAt() {
            return reqTimestamp("created_at");
        }

        public Integer termsToCharge() {
            return optInteger("terms_to_charge");
        }

    }

    public static class SpecificDatesSchedule extends Resource<SpecificDatesSchedule> {
        public SpecificDatesSchedule(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Integer termsToCharge() {
            return optInteger("terms_to_charge");
        }

        public Timestamp date() {
            return optTimestamp("date");
        }

        public Timestamp createdAt() {
            return reqTimestamp("created_at");
        }

    }

    //Constructors
    //============

    public AdvanceInvoiceSchedule(String jsonStr) {
        super(jsonStr);
    }

    public AdvanceInvoiceSchedule(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public ScheduleType scheduleType() {
        return optEnum("schedule_type", ScheduleType.class);
    }

    public AdvanceInvoiceSchedule.FixedIntervalSchedule fixedIntervalSchedule() {
        return optSubResource("fixed_interval_schedule", AdvanceInvoiceSchedule.FixedIntervalSchedule.class);
    }

    public AdvanceInvoiceSchedule.SpecificDatesSchedule specificDatesSchedule() {
        return optSubResource("specific_dates_schedule", AdvanceInvoiceSchedule.SpecificDatesSchedule.class);
    }

    // Operations
    //===========


}
