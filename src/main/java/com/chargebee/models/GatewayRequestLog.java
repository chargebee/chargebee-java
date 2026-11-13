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

public class GatewayRequestLog extends Resource<GatewayRequestLog> {

    //Constructors
    //============

    public GatewayRequestLog(String jsonStr) {
        super(jsonStr);
    }

    public GatewayRequestLog(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String logId() {
        return optString("log_id");
    }

    public String url() {
        return optString("url");
    }

    public String statusCode() {
        return optString("status_code");
    }

    public String method() {
        return optString("method");
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

    public String gateway() {
        return optString("gateway");
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

    public String operationName() {
        return optString("operation_name");
    }

    public String operationType() {
        return optString("operation_type");
    }

    public String actionType() {
        return optString("action_type");
    }

    public String source() {
        return optString("source");
    }

    public String traceIdHash() {
        return optString("trace_id_hash");
    }

    public Timestamp invocationTime() {
        return optTimestamp("invocation_time");
    }

    public Timestamp responseReceivedAt() {
        return optTimestamp("response_received_at");
    }

    public Long responseTime() {
        return optLong("response_time");
    }

    // Operations
    //===========

    public static GatewayRequestLogListRequest list() {
        String uri = uri("gateway_request_logs");
        return new GatewayRequestLogListRequest(uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("gateway_request_logs", nullCheck(id));
        return new Request(Method.GET, uri);
    }


    // Operation Request Classes
    //==========================

    public static class GatewayRequestLogListRequest extends ListRequest<GatewayRequestLogListRequest> {

        private GatewayRequestLogListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<GatewayRequestLogListRequest> logId() {
            return new StringFilter<GatewayRequestLogListRequest>("log_id",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayRequestLogListRequest> gateway() {
            return new StringFilter<GatewayRequestLogListRequest>("gateway",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayRequestLogListRequest> statusCode() {
            return new StringFilter<GatewayRequestLogListRequest>("status_code",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayRequestLogListRequest> method() {
            return new StringFilter<GatewayRequestLogListRequest>("method",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayRequestLogListRequest> url() {
            return new StringFilter<GatewayRequestLogListRequest>("url",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayRequestLogListRequest> customerHandle() {
            return new StringFilter<GatewayRequestLogListRequest>("customer_handle",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayRequestLogListRequest> invoiceHandle() {
            return new StringFilter<GatewayRequestLogListRequest>("invoice_handle",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayRequestLogListRequest> transactionHandle() {
            return new StringFilter<GatewayRequestLogListRequest>("transaction_handle",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayRequestLogListRequest> paymentMethodHandle() {
            return new StringFilter<GatewayRequestLogListRequest>("payment_method_handle",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayRequestLogListRequest> gatewayCredentialHandle() {
            return new StringFilter<GatewayRequestLogListRequest>("gateway_credential_handle",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayRequestLogListRequest> paymentAttemptHandle() {
            return new StringFilter<GatewayRequestLogListRequest>("payment_attempt_handle",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayRequestLogListRequest> paymentIntentHandle() {
            return new StringFilter<GatewayRequestLogListRequest>("payment_intent_handle",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayRequestLogListRequest> operationName() {
            return new StringFilter<GatewayRequestLogListRequest>("operation_name",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayRequestLogListRequest> operationType() {
            return new StringFilter<GatewayRequestLogListRequest>("operation_type",this).supportsMultiOperators(true);        
        }


        public StringFilter<GatewayRequestLogListRequest> source() {
            return new StringFilter<GatewayRequestLogListRequest>("source",this).supportsMultiOperators(true);        
        }


        public TimestampFilter<GatewayRequestLogListRequest> invocationTime() {
            return new TimestampFilter<GatewayRequestLogListRequest>("invocation_time",this);        
        }


        public TimestampFilter<GatewayRequestLogListRequest> responseReceivedAt() {
            return new TimestampFilter<GatewayRequestLogListRequest>("response_received_at",this);        
        }


        public GatewayRequestLogListRequest sortByInvocationTime(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","invocation_time");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
