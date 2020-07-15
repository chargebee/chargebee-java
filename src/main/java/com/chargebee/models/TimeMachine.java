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

public class TimeMachine extends Resource<TimeMachine> {

    public enum TimeTravelStatus {
        NOT_ENABLED,
        IN_PROGRESS,
        SUCCEEDED,
        FAILED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public TimeMachine(String jsonStr) {
        super(jsonStr);
    }

    public TimeMachine(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String name() {
        return reqString("name");
    }

    public TimeTravelStatus timeTravelStatus() {
        return reqEnum("time_travel_status", TimeTravelStatus.class);
    }

    public Timestamp genesisTime() {
        return reqTimestamp("genesis_time");
    }

    public Timestamp destinationTime() {
        return reqTimestamp("destination_time");
    }

    public String failureCode() {
        return optString("failure_code");
    }

    public String failureReason() {
        return optString("failure_reason");
    }

    public String errorJson() {
        return optString("error_json");
    }

    // Operations
    //===========

    public static Request retrieve(String id) {
        String uri = uri("time_machines", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static StartAfreshRequest startAfresh(String id) {
        String uri = uri("time_machines", nullCheck(id), "start_afresh");
        return new StartAfreshRequest(Method.POST, uri);
    }

    public static TravelForwardRequest travelForward(String id) {
        String uri = uri("time_machines", nullCheck(id), "travel_forward");
        return new TravelForwardRequest(Method.POST, uri);
    }

    public TimeMachine waitForTimeTravelCompletion() 
            throws Exception{ 
        return waitForTimeTravelCompletion(null);
    }

    public TimeMachine waitForTimeTravelCompletion(Environment env) 
            throws Exception {
        int count = 0;
        int sleepTime = Integer.getInteger("cb.java.time_travel.sleep.millis", 3000);
        while(timeTravelStatus() == TimeTravelStatus.IN_PROGRESS){
            if(count++ > 30){
                throw new RuntimeException("The time travel is taking too much time");
            }
            Thread.sleep(sleepTime);
            Request req = retrieve(name());
            jsonObj = ((env == null) ? req.request() : req.request(env)).timeMachine().jsonObj;
        }
        if(timeTravelStatus() == TimeTravelStatus.FAILED){
            JSONObject errorJson = new JSONObject(errorJson());
            int httpStatusCode = errorJson.getInt("http_code");
            String exceptionMessage = errorJson.getString("message");
            throw new com.chargebee.exceptions.OperationFailedException(httpStatusCode, exceptionMessage, errorJson);
        }
        if(timeTravelStatus() == TimeTravelStatus.NOT_ENABLED
                || timeTravelStatus() == TimeTravelStatus._UNKNOWN){
            throw new RuntimeException("Time travel status is in wrong state" + timeTravelStatus());
        }
        return this;
    }


    // Operation Request Classes
    //==========================

    public static class StartAfreshRequest extends Request<StartAfreshRequest> {

        private StartAfreshRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public StartAfreshRequest genesisTime(Timestamp genesisTime) {
            params.addOpt("genesis_time", genesisTime);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class TravelForwardRequest extends Request<TravelForwardRequest> {

        private TravelForwardRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public TravelForwardRequest destinationTime(Timestamp destinationTime) {
            params.addOpt("destination_time", destinationTime);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
