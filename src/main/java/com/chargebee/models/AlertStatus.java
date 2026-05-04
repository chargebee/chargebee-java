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

public class AlertStatus extends Resource<AlertStatus> {

    //Constructors
    //============

    public AlertStatus(String jsonStr) {
        super(jsonStr);
    }

    public AlertStatus(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String alertId() {
        return reqString("alert_id");
    }

    public String subscriptionId() {
        return reqString("subscription_id");
    }

    public AlarmStatus alarmStatus() {
        return reqEnum("alarm_status", AlarmStatus.class);
    }

    public Timestamp alarmTriggeredAt() {
        return optTimestamp("alarm_triggered_at");
    }

    // Operations
    //===========

    public static AlertStatusAlertStatusesForSubscriptionRequest alertStatusesForSubscription(String id) {
        String uri = uri("subscriptions", nullCheck(id), "alert_statuses");
        return new AlertStatusAlertStatusesForSubscriptionRequest(uri);
    }

    public static AlertStatusAlertStatusesForAlertRequest alertStatusesForAlert(String id) {
        String uri = uri("alerts", nullCheck(id), "alert_statuses");
        return new AlertStatusAlertStatusesForAlertRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class AlertStatusAlertStatusesForSubscriptionRequest extends ListRequest<AlertStatusAlertStatusesForSubscriptionRequest> {

        private AlertStatusAlertStatusesForSubscriptionRequest(String uri) {
            super(uri);
        }
    
        public EnumFilter<com.chargebee.models.enums.AlarmStatus, AlertStatusAlertStatusesForSubscriptionRequest> alarmStatus() {
            return new EnumFilter<com.chargebee.models.enums.AlarmStatus, AlertStatusAlertStatusesForSubscriptionRequest>("alarm_status",this).supportsMultiOperators(true);        
        }


        public StringFilter<AlertStatusAlertStatusesForSubscriptionRequest> alertId() {
            return new StringFilter<AlertStatusAlertStatusesForSubscriptionRequest>("alert_id",this).supportsMultiOperators(true);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class AlertStatusAlertStatusesForAlertRequest extends ListRequest<AlertStatusAlertStatusesForAlertRequest> {

        private AlertStatusAlertStatusesForAlertRequest(String uri) {
            super(uri);
        }
    
        public EnumFilter<com.chargebee.models.enums.AlarmStatus, AlertStatusAlertStatusesForAlertRequest> alarmStatus() {
            return new EnumFilter<com.chargebee.models.enums.AlarmStatus, AlertStatusAlertStatusesForAlertRequest>("alarm_status",this).supportsMultiOperators(true);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
