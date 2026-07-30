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

public class Alert extends Resource<Alert> {

    public enum Status {
        ENABLED,
        DISABLED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class Threshold extends Resource<Threshold> {
        public Threshold(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Mode mode() {
            return reqEnum("mode", Mode.class);
        }

        public Double value() {
            return reqDouble("value");
        }

    }

    public static class FilterCondition extends Resource<FilterCondition> {
        public enum Field {
             PLAN_PRICE_ID,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum Operator {
             EQUALS,NOT_EQUALS,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public FilterCondition(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Field field() {
            return reqEnum("field", Field.class);
        }

        public Operator operator() {
            return reqEnum("operator", Operator.class);
        }

        public String value() {
            return reqString("value");
        }

    }

    //Constructors
    //============

    public Alert(String jsonStr) {
        super(jsonStr);
    }

    public Alert(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public String name() {
        return reqString("name");
    }

    public String description() {
        return optString("description");
    }

    public String meteredFeatureId() {
        return optString("metered_feature_id");
    }

    public String currencyCode() {
        return optString("currency_code");
    }

    public String unitId() {
        return optString("unit_id");
    }

    public String subscriptionId() {
        return optString("subscription_id");
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public String meta() {
        return optString("meta");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp updatedAt() {
        return reqTimestamp("updated_at");
    }

    public Alert.Threshold threshold() {
        return optSubResource("threshold", Alert.Threshold.class);
    }

    public List<Alert.FilterCondition> filterConditions() {
        return optList("filter_conditions", Alert.FilterCondition.class);
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("alerts");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("alerts", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static AlertListRequest list() {
        String uri = uri("alerts");
        return new AlertListRequest(uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("alerts", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static Request delete(String id) {
        String uri = uri("alerts", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static AlertApplicationAlertsForSubscriptionRequest applicationAlertsForSubscription(String id) {
        String uri = uri("subscriptions", nullCheck(id), "applicable_alerts");
        return new AlertApplicationAlertsForSubscriptionRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest type(com.chargebee.models.enums.Type type) {
            params.add("type", type);
            return this;
        }


        public CreateRequest name(String name) {
            params.add("name", name);
            return this;
        }


        public CreateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        public CreateRequest meteredFeatureId(String meteredFeatureId) {
            params.addOpt("metered_feature_id", meteredFeatureId);
            return this;
        }


        public CreateRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public CreateRequest unitId(String unitId) {
            params.addOpt("unit_id", unitId);
            return this;
        }


        public CreateRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public CreateRequest meta(String meta) {
            params.addOpt("meta", meta);
            return this;
        }


        public CreateRequest thresholdMode(com.chargebee.models.enums.Mode thresholdMode) {
            params.addOpt("threshold[mode]", thresholdMode);
            return this;
        }
        
        public CreateRequest thresholdValue(Double thresholdValue) {
            params.add("threshold[value]", thresholdValue);
            return this;
        }
        
        public CreateRequest filterConditionField(int index, FilterCondition.Field filterConditionField) {
            params.addOpt("filter_conditions[field][" + index + "]", filterConditionField);
            return this;
        }
        public CreateRequest filterConditionOperator(int index, FilterCondition.Operator filterConditionOperator) {
            params.addOpt("filter_conditions[operator][" + index + "]", filterConditionOperator);
            return this;
        }
        public CreateRequest filterConditionValue(int index, String filterConditionValue) {
            params.addOpt("filter_conditions[value][" + index + "]", filterConditionValue);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class AlertListRequest extends ListRequest<AlertListRequest> {

        private AlertListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<AlertListRequest> id() {
            return new StringFilter<AlertListRequest>("id",this).supportsMultiOperators(true);        
        }


        public EnumFilter<com.chargebee.models.enums.Type, AlertListRequest> type() {
            return new EnumFilter<com.chargebee.models.enums.Type, AlertListRequest>("type",this).supportsMultiOperators(true);        
        }


        public StringFilter<AlertListRequest> subscriptionId() {
            return new StringFilter<AlertListRequest>("subscription_id",this).supportsMultiOperators(true);        
        }


        public EnumFilter<Alert.Status, AlertListRequest> status() {
            return new EnumFilter<Alert.Status, AlertListRequest>("status",this).supportsMultiOperators(true);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateRequest extends Request<UpdateRequest> {

        private UpdateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateRequest status(Alert.Status status) {
            params.addOpt("status", status);
            return this;
        }


        public UpdateRequest thresholdMode(com.chargebee.models.enums.Mode thresholdMode) {
            params.addOpt("threshold[mode]", thresholdMode);
            return this;
        }
        
        public UpdateRequest thresholdValue(Double thresholdValue) {
            params.addOpt("threshold[value]", thresholdValue);
            return this;
        }
        
        @Override
        public Params params() {
            return params;
        }
    }

    public static class AlertApplicationAlertsForSubscriptionRequest extends ListRequest<AlertApplicationAlertsForSubscriptionRequest> {

        private AlertApplicationAlertsForSubscriptionRequest(String uri) {
            super(uri);
        }
    
        public EnumFilter<Alert.Status, AlertApplicationAlertsForSubscriptionRequest> status() {
            return new EnumFilter<Alert.Status, AlertApplicationAlertsForSubscriptionRequest>("status",this).supportsMultiOperators(true);        
        }


        public EnumFilter<com.chargebee.models.enums.Type, AlertApplicationAlertsForSubscriptionRequest> type() {
            return new EnumFilter<com.chargebee.models.enums.Type, AlertApplicationAlertsForSubscriptionRequest>("type",this).supportsMultiOperators(true);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
