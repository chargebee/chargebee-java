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

    public com.chargebee.models.enums.AlertStatus alertStatus() {
        return reqEnum("alert_status", com.chargebee.models.enums.AlertStatus.class);
    }

    public Timestamp alarmTriggeredAt() {
        return optTimestamp("alarm_triggered_at");
    }

    // Operations
    //===========


}
