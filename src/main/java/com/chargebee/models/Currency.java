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

public class Currency extends Resource<Currency> {

    public enum ForexType {
        MANUAL,
        AUTO,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Currency(String jsonStr) {
        super(jsonStr);
    }

    public Currency(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public Boolean enabled() {
        return reqBoolean("enabled");
    }

    public ForexType forexType() {
        return optEnum("forex_type", ForexType.class);
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public Boolean isBaseCurrency() {
        return reqBoolean("is_base_currency");
    }

    public String manualExchangeRate() {
        return optString("manual_exchange_rate");
    }

    // Operations
    //===========

    public static ListRequest list() {
        String uri = uri("currencies", "list");
        return new ListRequest(uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("currencies", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static CreateRequest create() {
        String uri = uri("currencies");
        return new CreateRequest(Method.POST, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("currencies", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static AddScheduleRequest addSchedule(String id) {
        String uri = uri("currencies", nullCheck(id), "add_schedule");
        return new AddScheduleRequest(Method.POST, uri);
    }

    public static Request removeSchedule(String id) {
        String uri = uri("currencies", nullCheck(id), "remove_schedule");
        return new Request(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest currencyCode(String currencyCode) {
            params.add("currency_code", currencyCode);
            return this;
        }


        public CreateRequest forexType(Currency.ForexType forexType) {
            params.add("forex_type", forexType);
            return this;
        }


        public CreateRequest manualExchangeRate(String manualExchangeRate) {
            params.addOpt("manual_exchange_rate", manualExchangeRate);
            return this;
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
    
        public UpdateRequest forexType(Currency.ForexType forexType) {
            params.add("forex_type", forexType);
            return this;
        }


        public UpdateRequest manualExchangeRate(String manualExchangeRate) {
            params.addOpt("manual_exchange_rate", manualExchangeRate);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class AddScheduleRequest extends Request<AddScheduleRequest> {

        private AddScheduleRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public AddScheduleRequest manualExchangeRate(String manualExchangeRate) {
            params.add("manual_exchange_rate", manualExchangeRate);
            return this;
        }


        public AddScheduleRequest scheduleAt(Timestamp scheduleAt) {
            params.add("schedule_at", scheduleAt);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
