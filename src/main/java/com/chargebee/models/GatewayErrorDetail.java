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

public class GatewayErrorDetail extends Resource<GatewayErrorDetail> {

    //Constructors
    //============

    public GatewayErrorDetail(String jsonStr) {
        super(jsonStr);
    }

    public GatewayErrorDetail(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String requestId() {
        return optString("request_id");
    }

    public String errorCategory() {
        return optString("error_category");
    }

    public String errorCode() {
        return optString("error_code");
    }

    public String errorMessage() {
        return optString("error_message");
    }

    public String declineCode() {
        return optString("decline_code");
    }

    public String declineMessage() {
        return optString("decline_message");
    }

    public String networkErrorCode() {
        return optString("network_error_code");
    }

    public String networkErrorMessage() {
        return optString("network_error_message");
    }

    public String errorField() {
        return optString("error_field");
    }

    public String recommendationCode() {
        return optString("recommendation_code");
    }

    public String recommendationMessage() {
        return optString("recommendation_message");
    }

    public String processorErrorCode() {
        return optString("processor_error_code");
    }

    public String processorErrorMessage() {
        return optString("processor_error_message");
    }

    // Operations
    //===========


}