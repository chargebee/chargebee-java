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

public class PaymentVoucher extends Resource<PaymentVoucher> {

    public enum Status {
        ACTIVE,
        CONSUMED,
        EXPIRED,
        FAILURE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class LinkedInvoice extends Resource<LinkedInvoice> {
        public LinkedInvoice(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String invoiceId() {
            return reqString("invoice_id");
        }

        public String txnId() {
            return reqString("txn_id");
        }

        public Timestamp appliedAt() {
            return reqTimestamp("applied_at");
        }

    }

    //Constructors
    //============

    public PaymentVoucher(String jsonStr) {
        super(jsonStr);
    }

    public PaymentVoucher(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String idAtGateway() {
        return optString("id_at_gateway");
    }

    public PaymentVoucherType paymentVoucherType() {
        return reqEnum("payment_voucher_type", PaymentVoucherType.class);
    }

    public Timestamp expiresAt() {
        return optTimestamp("expires_at");
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public String subscriptionId() {
        return optString("subscription_id");
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public Long amount() {
        return optLong("amount");
    }

    public String gatewayAccountId() {
        return optString("gateway_account_id");
    }

    public String paymentSourceId() {
        return optString("payment_source_id");
    }

    public Gateway gateway() {
        return reqEnum("gateway", Gateway.class);
    }

    public String payload() {
        return optString("payload");
    }

    public String errorCode() {
        return optString("error_code");
    }

    public String errorText() {
        return optString("error_text");
    }

    public String url() {
        return optString("url");
    }

    public Timestamp date() {
        return optTimestamp("date");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public String customerId() {
        return reqString("customer_id");
    }

    public List<PaymentVoucher.LinkedInvoice> linkedInvoices() {
        return optList("linked_invoices", PaymentVoucher.LinkedInvoice.class);
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("payment_vouchers");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("payment_vouchers", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static PaymentVoucherPaymentVouchersForInvoiceRequest paymentVouchersForInvoice(String id) {
        String uri = uri("invoices", nullCheck(id), "payment_vouchers");
        return new PaymentVoucherPaymentVouchersForInvoiceRequest(uri);
    }

    public static PaymentVoucherPaymentVouchersForCustomerRequest paymentVouchersForCustomer(String id) {
        String uri = uri("customers", nullCheck(id), "payment_vouchers");
        return new PaymentVoucherPaymentVouchersForCustomerRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public CreateRequest paymentSourceId(String paymentSourceId) {
            params.addOpt("payment_source_id", paymentSourceId);
            return this;
        }


        public CreateRequest voucherPaymentSourceVoucherType(com.chargebee.models.enums.VoucherType voucherPaymentSourceVoucherType) {
            params.add("voucher_payment_source[voucher_type]", voucherPaymentSourceVoucherType);
            return this;
        }

        public CreateRequest invoiceAllocationInvoiceId(int index, String invoiceAllocationInvoiceId) {
            params.add("invoice_allocations[invoice_id][" + index + "]", invoiceAllocationInvoiceId);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class PaymentVoucherPaymentVouchersForInvoiceRequest extends ListRequest<PaymentVoucherPaymentVouchersForInvoiceRequest> {

        private PaymentVoucherPaymentVouchersForInvoiceRequest(String uri) {
            super(uri);
        }
    
        public EnumFilter<PaymentVoucher.Status, PaymentVoucherPaymentVouchersForInvoiceRequest> status() {
            return new EnumFilter<PaymentVoucher.Status, PaymentVoucherPaymentVouchersForInvoiceRequest>("status",this);        
        }


        public PaymentVoucherPaymentVouchersForInvoiceRequest sortByDate(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","date");
            return this;
        }
        public PaymentVoucherPaymentVouchersForInvoiceRequest sortByUpdatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","updated_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class PaymentVoucherPaymentVouchersForCustomerRequest extends ListRequest<PaymentVoucherPaymentVouchersForCustomerRequest> {

        private PaymentVoucherPaymentVouchersForCustomerRequest(String uri) {
            super(uri);
        }
    
        public EnumFilter<PaymentVoucher.Status, PaymentVoucherPaymentVouchersForCustomerRequest> status() {
            return new EnumFilter<PaymentVoucher.Status, PaymentVoucherPaymentVouchersForCustomerRequest>("status",this);        
        }


        public PaymentVoucherPaymentVouchersForCustomerRequest sortByDate(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","date");
            return this;
        }
        public PaymentVoucherPaymentVouchersForCustomerRequest sortByUpdatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","updated_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
