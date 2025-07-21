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

public class BillingConfiguration extends Resource<BillingConfiguration> {

    public static class BillingDate extends Resource<BillingDate> {
        public BillingDate(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Timestamp startDate() {
            return optTimestamp("start_date");
        }

        public Timestamp endDate() {
            return optTimestamp("end_date");
        }

    }

    //Constructors
    //============

    public BillingConfiguration(String jsonStr) {
        super(jsonStr);
    }

    public BillingConfiguration(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public Boolean isCalendarBillingEnabled() {
        return reqBoolean("is_calendar_billing_enabled");
    }

    public List<BillingConfiguration.BillingDate> billingDates() {
        return optList("billing_dates", BillingConfiguration.BillingDate.class);
    }

    // Operations
    //===========


}
