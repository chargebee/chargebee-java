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

public class PersonalizedOffer extends Resource<PersonalizedOffer> {

    public static class Content extends Resource<Content> {
        public Content(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String title() {
            return reqString("title");
        }

        public String description() {
            return reqString("description");
        }

    }

    public static class Option extends Resource<Option> {
        public enum ProcessingType {
             BILLING_UPDATE,CHECKOUT,URL_REDIRECT,WEBHOOK,EMAIL,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum ProcessingLayout {
             IN_APP,FULL_PAGE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public Option(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public String label() {
            return reqString("label");
        }

        public ProcessingType processingType() {
            return reqEnum("processing_type", ProcessingType.class);
        }

        public ProcessingLayout processingLayout() {
            return reqEnum("processing_layout", ProcessingLayout.class);
        }

        public String redirectUrl() {
            return reqString("redirect_url");
        }

    }

    //Constructors
    //============

    public PersonalizedOffer(String jsonStr) {
        super(jsonStr);
    }

    public PersonalizedOffer(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String offerId() {
        return reqString("offer_id");
    }

    public PersonalizedOffer.Content content() {
        return reqSubResource("content", PersonalizedOffer.Content.class);
    }

    public List<PersonalizedOffer.Option> options() {
        return reqList("options", PersonalizedOffer.Option.class);
    }

    // Operations
    //===========

    public static PersonalizedOffersRequest personalizedOffers() {
        String uri = uri("personalized_offers");
        return new PersonalizedOffersRequest(Method.POST, uri).setIdempotency(false);
    }


    // Operation Request Classes
    //==========================

    public static class PersonalizedOffersRequest extends Request<PersonalizedOffersRequest> {

        private PersonalizedOffersRequest(Method httpMeth, String uri) {
            super(httpMeth, uri, null, "grow",true);
        }
    
        public PersonalizedOffersRequest firstName(String firstName) {
            params.addOpt("first_name", firstName);
            return this;
        }


        public PersonalizedOffersRequest lastName(String lastName) {
            params.addOpt("last_name", lastName);
            return this;
        }


        public PersonalizedOffersRequest email(String email) {
            params.addOpt("email", email);
            return this;
        }


        public PersonalizedOffersRequest roles(List<String> roles) {
            params.addOpt("roles", roles);
            return this;
        }

        public PersonalizedOffersRequest roles(String... roles) {
            params.addOpt("roles", roles);
            return this;
        }

        public PersonalizedOffersRequest externalUserId(String externalUserId) {
            params.addOpt("external_user_id", externalUserId);
            return this;
        }


        public PersonalizedOffersRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public PersonalizedOffersRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public PersonalizedOffersRequest custom(Map<String, Object> custom) {
            params.addOpt("custom", custom);
            return this;
        }


        public PersonalizedOffersRequest requestContext(RequestContextPersonalizedOffersInputParams item) {
            params.add("request_context", item.toJObject());
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class RequestContextPersonalizedOffersInputParams { 
        private final String UserAgent;
        private final String Locale;
        private final String Timezone;
        private final String Url;
        private final String ReferrerUrl;
        private RequestContextPersonalizedOffersInputParams(RequestContextPersonalizedOffersInputParamsBuilder builder){
            this.UserAgent = builder.UserAgent;
            this.Locale = builder.Locale;
            this.Timezone = builder.Timezone;
            this.Url = builder.Url;
            this.ReferrerUrl = builder.ReferrerUrl;
            
        }
        public JSONObject toJObject(){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_agent", this.UserAgent);
            jsonObject.put("locale", this.Locale);
            jsonObject.put("timezone", this.Timezone);
            jsonObject.put("url", this.Url);
            jsonObject.put("referrer_url", this.ReferrerUrl);
            
            return jsonObject;
            }
        }
        public static class RequestContextPersonalizedOffersInputParamsBuilder { 
            private String UserAgent;
            private String Locale;
            private String Timezone;
            private String Url;
            private String ReferrerUrl;
        
            public RequestContextPersonalizedOffersInputParamsBuilder setUserAgent( String UserAgent ) {
            this.UserAgent = UserAgent;
            return this;
        } 
            public RequestContextPersonalizedOffersInputParamsBuilder setLocale( String Locale ) {
            this.Locale = Locale;
            return this;
        } 
            public RequestContextPersonalizedOffersInputParamsBuilder setTimezone( String Timezone ) {
            this.Timezone = Timezone;
            return this;
        } 
            public RequestContextPersonalizedOffersInputParamsBuilder setUrl( String Url ) {
            this.Url = Url;
            return this;
        } 
            public RequestContextPersonalizedOffersInputParamsBuilder setReferrerUrl( String ReferrerUrl ) {
            this.ReferrerUrl = ReferrerUrl;
            return this;
        } 

        public RequestContextPersonalizedOffersInputParams build() {
            return new RequestContextPersonalizedOffersInputParams(this);
        }

        }
    
}
