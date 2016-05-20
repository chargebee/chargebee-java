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

public class CreditNote extends Resource<CreditNote> {

    public enum Type {
        ADJUSTMENT,
        REFUNDABLE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum ReasonCode {
        WRITE_OFF,
        SUBSCRIPTION_CHANGE,
        CHARGEBACK,
        PRODUCT_UNSATISFACTORY,
        SERVICE_UNSATISFACTORY,
        ORDER_CHANGE,
        ORDER_CANCELLATION,
        WAIVER,
        OTHER,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Status {
        ADJUSTED,
        REFUNDED,
        REFUND_DUE,
        VOIDED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class LineItem extends Resource<LineItem> {
        public enum EntityType {
             PLAN_SETUP,PLAN,ADDON,ADHOC,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public LineItem(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return optString("id");
        }

        public Timestamp dateFrom() {
            return reqTimestamp("date_from");
        }

        public Timestamp dateTo() {
            return reqTimestamp("date_to");
        }

        public Integer unitAmount() {
            return reqInteger("unit_amount");
        }

        public Integer quantity() {
            return optInteger("quantity");
        }

        public Boolean isTaxed() {
            return reqBoolean("is_taxed");
        }

        public Integer taxAmount() {
            return optInteger("tax_amount");
        }

        public Double taxRate() {
            return optDouble("tax_rate");
        }

        public Integer amount() {
            return reqInteger("amount");
        }

        public Integer discountAmount() {
            return optInteger("discount_amount");
        }

        public Integer itemLevelDiscountAmount() {
            return optInteger("item_level_discount_amount");
        }

        public String description() {
            return reqString("description");
        }

        public EntityType entityType() {
            return reqEnum("entity_type", EntityType.class);
        }

        public String entityId() {
            return optString("entity_id");
        }

    }

    public static class Discount extends Resource<Discount> {
        public enum EntityType {
             ITEM_LEVEL_COUPON,DOCUMENT_LEVEL_COUPON,PROMOTIONAL_CREDITS,PRORATED_CREDITS,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public Discount(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Integer amount() {
            return reqInteger("amount");
        }

        public String description() {
            return optString("description");
        }

        public EntityType entityType() {
            return reqEnum("entity_type", EntityType.class);
        }

        public String entityId() {
            return optString("entity_id");
        }

    }

    public static class Tax extends Resource<Tax> {
        public Tax(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String name() {
            return reqString("name");
        }

        public Integer amount() {
            return reqInteger("amount");
        }

        public String description() {
            return optString("description");
        }

    }

    public static class LineItemTax extends Resource<LineItemTax> {
        public LineItemTax(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String lineItemId() {
            return optString("line_item_id");
        }

        public String taxName() {
            return reqString("tax_name");
        }

        public Double taxRate() {
            return reqDouble("tax_rate");
        }

        public Integer taxAmount() {
            return reqInteger("tax_amount");
        }

        public TaxJurisType taxJurisType() {
            return optEnum("tax_juris_type", TaxJurisType.class);
        }

        public String taxJurisName() {
            return optString("tax_juris_name");
        }

        public String taxJurisCode() {
            return optString("tax_juris_code");
        }

    }

    public static class LinkedRefund extends Resource<LinkedRefund> {
        public LinkedRefund(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String txnId() {
            return reqString("txn_id");
        }

        public Integer appliedAmount() {
            return reqInteger("applied_amount");
        }

        public Timestamp appliedAt() {
            return reqTimestamp("applied_at");
        }

        public Transaction.Status txnStatus() {
            return optEnum("txn_status", Transaction.Status.class);
        }

        public Timestamp txnDate() {
            return optTimestamp("txn_date");
        }

        public Integer txnAmount() {
            return optInteger("txn_amount");
        }

    }

    public static class Allocation extends Resource<Allocation> {
        public Allocation(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String invoiceId() {
            return reqString("invoice_id");
        }

        public Integer allocatedAmount() {
            return reqInteger("allocated_amount");
        }

        public Timestamp allocatedAt() {
            return reqTimestamp("allocated_at");
        }

        public Timestamp invoiceDate() {
            return optTimestamp("invoice_date");
        }

        public Invoice.Status invoiceStatus() {
            return reqEnum("invoice_status", Invoice.Status.class);
        }

    }

    //Constructors
    //============

    public CreditNote(String jsonStr) {
        super(jsonStr);
    }

    public CreditNote(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String customerId() {
        return reqString("customer_id");
    }

    public String subscriptionId() {
        return optString("subscription_id");
    }

    public String referenceInvoiceId() {
        return reqString("reference_invoice_id");
    }

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public ReasonCode reasonCode() {
        return reqEnum("reason_code", ReasonCode.class);
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public String vatNumber() {
        return optString("vat_number");
    }

    public Timestamp date() {
        return optTimestamp("date");
    }

    public PriceType priceType() {
        return reqEnum("price_type", PriceType.class);
    }

    public Integer total() {
        return optInteger("total");
    }

    public Integer amountAllocated() {
        return optInteger("amount_allocated");
    }

    public Integer amountRefunded() {
        return optInteger("amount_refunded");
    }

    public Integer amountAvailable() {
        return optInteger("amount_available");
    }

    public Timestamp refundedAt() {
        return optTimestamp("refunded_at");
    }

    public Integer subTotal() {
        return reqInteger("sub_total");
    }

    public List<CreditNote.LineItem> lineItems() {
        return optList("line_items", CreditNote.LineItem.class);
    }

    public List<CreditNote.Discount> discounts() {
        return optList("discounts", CreditNote.Discount.class);
    }

    public List<CreditNote.Tax> taxes() {
        return optList("taxes", CreditNote.Tax.class);
    }

    public List<CreditNote.LineItemTax> lineItemTaxes() {
        return optList("line_item_taxes", CreditNote.LineItemTax.class);
    }

    public List<CreditNote.LinkedRefund> linkedRefunds() {
        return optList("linked_refunds", CreditNote.LinkedRefund.class);
    }

    public List<CreditNote.Allocation> allocations() {
        return optList("allocations", CreditNote.Allocation.class);
    }

    // Operations
    //===========

    public static Request retrieve(String id) throws IOException {
        String uri = uri("credit_notes", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static CreditNoteListRequest list() throws IOException {
        String uri = uri("credit_notes");
        return new CreditNoteListRequest(uri);
    }

    @Deprecated
    public static ListRequest creditNotesForCustomer(String id) throws IOException {
        String uri = uri("customers", nullCheck(id), "credit_notes");
        return new ListRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreditNoteListRequest extends ListRequest<CreditNoteListRequest> {

        private CreditNoteListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<CreditNoteListRequest> id() {
            return new StringFilter<CreditNoteListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<CreditNoteListRequest> customerId() {
            return new StringFilter<CreditNoteListRequest>("customer_id",this).supportsMultiOperators(true);        
        }


        public StringFilter<CreditNoteListRequest> subscriptionId() {
            return new StringFilter<CreditNoteListRequest>("subscription_id",this).supportsMultiOperators(true).supportsPresenceOperator(true);        
        }


        public StringFilter<CreditNoteListRequest> referenceInvoiceId() {
            return new StringFilter<CreditNoteListRequest>("reference_invoice_id",this).supportsMultiOperators(true);        
        }


        public EnumFilter<Type, CreditNoteListRequest> type() {
            return new EnumFilter<Type, CreditNoteListRequest>("type",this);        
        }


        public EnumFilter<ReasonCode, CreditNoteListRequest> reasonCode() {
            return new EnumFilter<ReasonCode, CreditNoteListRequest>("reason_code",this);        
        }


        public EnumFilter<Status, CreditNoteListRequest> status() {
            return new EnumFilter<Status, CreditNoteListRequest>("status",this);        
        }


        public TimestampFilter<CreditNoteListRequest> date() {
            return new TimestampFilter<CreditNoteListRequest>("date",this);        
        }


        public NumberFilter<Integer, CreditNoteListRequest> total() {
            return new NumberFilter<Integer, CreditNoteListRequest>("total",this);        
        }


        public EnumFilter<PriceType, CreditNoteListRequest> priceType() {
            return new EnumFilter<PriceType, CreditNoteListRequest>("price_type",this);        
        }


        public NumberFilter<Integer, CreditNoteListRequest> amountAllocated() {
            return new NumberFilter<Integer, CreditNoteListRequest>("amount_allocated",this);        
        }


        public NumberFilter<Integer, CreditNoteListRequest> amountRefunded() {
            return new NumberFilter<Integer, CreditNoteListRequest>("amount_refunded",this);        
        }


        public NumberFilter<Integer, CreditNoteListRequest> amountAvailable() {
            return new NumberFilter<Integer, CreditNoteListRequest>("amount_available",this);        
        }


        public CreditNoteListRequest sortByDate(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","date");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
