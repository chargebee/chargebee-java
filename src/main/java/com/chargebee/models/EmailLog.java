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

public class EmailLog extends Resource<EmailLog> {

    //Constructors
    //============

    public EmailLog(String jsonStr) {
        super(jsonStr);
    }

    public EmailLog(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String templateName() {
        return optString("template_name");
    }

    public String fromAddress() {
        return reqString("from_address");
    }

    public String toAddress() {
        return reqString("to_address");
    }

    public String subject() {
        return reqString("subject");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp sentOn() {
        return optTimestamp("sent_on");
    }

    public String customerId() {
        return optString("customer_id");
    }

    public String siteId() {
        return optString("site_id");
    }

    public String businessEntityId() {
        return optString("business_entity_id");
    }

    public String brandId() {
        return optString("brand_id");
    }

    public String errorMessage() {
        return optString("error_message");
    }

    // Operations
    //===========

    public static EmailLogEmailLogsForCustomerRequest emailLogsForCustomer(String id) {
        String uri = uri("customers", nullCheck(id), "email_logs");
        return new EmailLogEmailLogsForCustomerRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class EmailLogEmailLogsForCustomerRequest extends ListRequest<EmailLogEmailLogsForCustomerRequest> {

        private EmailLogEmailLogsForCustomerRequest(String uri) {
            super(uri);
        }
    
        public TimestampFilter<EmailLogEmailLogsForCustomerRequest> sentOn() {
            return new TimestampFilter<EmailLogEmailLogsForCustomerRequest>("sent_on",this);        
        }


        public StringFilter<EmailLogEmailLogsForCustomerRequest> businessEntityId() {
            return new StringFilter<EmailLogEmailLogsForCustomerRequest>("business_entity_id",this);        
        }


        public StringFilter<EmailLogEmailLogsForCustomerRequest> brandId() {
            return new StringFilter<EmailLogEmailLogsForCustomerRequest>("brand_id",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
