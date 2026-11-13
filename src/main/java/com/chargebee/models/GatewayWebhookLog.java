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

public class GatewayWebhookLog extends Resource<GatewayWebhookLog> {

    //Constructors
    //============

    public GatewayWebhookLog(String jsonStr) {
        super(jsonStr);
    }

    public GatewayWebhookLog(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String webhookId() {
        return optString("webhook_id");
    }

    public String url() {
        return optString("url");
    }

    public String method() {
        return optString("method");
    }

    public String statusCode() {
        return optString("status_code");
    }

    public String gateway() {
        return optString("gateway");
    }

    public String idAtGateway() {
        return optString("id_at_gateway");
    }

    public String customerHandle() {
        return optString("customer_handle");
    }

    public String invoiceHandle() {
        return optString("invoice_handle");
    }

    public String transactionHandle() {
        return optString("transaction_handle");
    }

    public String paymentMethodHandle() {
        return optString("payment_method_handle");
    }

    public String gatewayCredentialHandle() {
        return optString("gateway_credential_handle");
    }

    public String paymentAttemptHandle() {
        return optString("payment_attempt_handle");
    }

    public String paymentIntentHandle() {
        return optString("payment_intent_handle");
    }

    public String paymentMethodName() {
        return optString("payment_method_name");
    }

    public String requestBody() {
        return optString("request_body");
    }

    public String responseBody() {
        return optString("response_body");
    }

    public String requestHeaders() {
        return optString("request_headers");
    }

    public String responseHeaders() {
        return optString("response_headers");
    }

    public Timestamp receivedAt() {
        return optTimestamp("received_at");
    }

    public Long responseTime() {
        return optLong("response_time");
    }

    // Operations
    //===========

    public static GatewayWebhookLogListRequest list() {
        String uri = uri("gateway_webhook_logs");
        return new GatewayWebhookLogListRequest(uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("gateway_webhook_logs", nullCheck(id));
        return new Request(Method.GET, uri);
    }


    // Operation Request Classes
    //==========================

    public static class GatewayWebhookLogListRequest extends ListRequest<GatewayWebhookLogListRequest> {

        private GatewayWebhookLogListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<GatewayWebhookLogListRequest> webhookId() {
            return new StringFilter<GatewayWebhookLogListRequest>("webhook_id",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayWebhookLogListRequest> gateway() {
            return new StringFilter<GatewayWebhookLogListRequest>("gateway",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayWebhookLogListRequest> statusCode() {
            return new StringFilter<GatewayWebhookLogListRequest>("status_code",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayWebhookLogListRequest> method() {
            return new StringFilter<GatewayWebhookLogListRequest>("method",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayWebhookLogListRequest> url() {
            return new StringFilter<GatewayWebhookLogListRequest>("url",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayWebhookLogListRequest> idAtGateway() {
            return new StringFilter<GatewayWebhookLogListRequest>("id_at_gateway",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayWebhookLogListRequest> customerHandle() {
            return new StringFilter<GatewayWebhookLogListRequest>("customer_handle",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayWebhookLogListRequest> invoiceHandle() {
            return new StringFilter<GatewayWebhookLogListRequest>("invoice_handle",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayWebhookLogListRequest> transactionHandle() {
            return new StringFilter<GatewayWebhookLogListRequest>("transaction_handle",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayWebhookLogListRequest> paymentMethodHandle() {
            return new StringFilter<GatewayWebhookLogListRequest>("payment_method_handle",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayWebhookLogListRequest> gatewayCredentialHandle() {
            return new StringFilter<GatewayWebhookLogListRequest>("gateway_credential_handle",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayWebhookLogListRequest> paymentAttemptHandle() {
            return new StringFilter<GatewayWebhookLogListRequest>("payment_attempt_handle",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayWebhookLogListRequest> paymentIntentHandle() {
            return new StringFilter<GatewayWebhookLogListRequest>("payment_intent_handle",this).supportsMultiOperators(true);        
        }


        public TimestampFilter<GatewayWebhookLogListRequest> receivedAt() {
            return new TimestampFilter<GatewayWebhookLogListRequest>("received_at",this);        
        }


        public GatewayWebhookLogListRequest sortByReceivedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","received_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
