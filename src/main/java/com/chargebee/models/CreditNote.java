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
        SUBSCRIPTION_CANCELLATION,
        SUBSCRIPTION_PAUSE,
        CHARGEBACK,
        PRODUCT_UNSATISFACTORY,
        SERVICE_UNSATISFACTORY,
        ORDER_CHANGE,
        ORDER_CANCELLATION,
        WAIVER,
        OTHER,
        FRAUDULENT,
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

        public String subscriptionId() {
            return optString("subscription_id");
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

        public Integer amount() {
            return optInteger("amount");
        }

        public PricingModel pricingModel() {
            return optEnum("pricing_model", PricingModel.class);
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

        public TaxExemptReason taxExemptReason() {
            return optEnum("tax_exempt_reason", TaxExemptReason.class);
        }

        public String entityId() {
            return optString("entity_id");
        }

        public String customerId() {
            return optString("customer_id");
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

    public static class LineItemDiscount extends Resource<LineItemDiscount> {
        public enum DiscountType {
             ITEM_LEVEL_COUPON,DOCUMENT_LEVEL_COUPON,PROMOTIONAL_CREDITS,PRORATED_CREDITS,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public LineItemDiscount(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String lineItemId() {
            return reqString("line_item_id");
        }

        public DiscountType discountType() {
            return reqEnum("discount_type", DiscountType.class);
        }

        public String couponId() {
            return optString("coupon_id");
        }

        public Integer discountAmount() {
            return reqInteger("discount_amount");
        }

    }

    public static class LineItemTier extends Resource<LineItemTier> {
        public LineItemTier(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String lineItemId() {
            return optString("line_item_id");
        }

        public Integer startingUnit() {
            return reqInteger("starting_unit");
        }

        public Integer endingUnit() {
            return optInteger("ending_unit");
        }

        public Integer quantityUsed() {
            return reqInteger("quantity_used");
        }

        public Integer unitAmount() {
            return reqInteger("unit_amount");
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

        public Boolean isPartialTaxApplied() {
            return optBoolean("is_partial_tax_applied");
        }

        public Boolean isNonComplianceTax() {
            return optBoolean("is_non_compliance_tax");
        }

        public Integer taxableAmount() {
            return reqInteger("taxable_amount");
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

        public Integer taxAmountInLocalCurrency() {
            return optInteger("tax_amount_in_local_currency");
        }

        public String localCurrencyCode() {
            return optString("local_currency_code");
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

    public String currencyCode() {
        return reqString("currency_code");
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

    public Timestamp voidedAt() {
        return optTimestamp("voided_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public Integer subTotal() {
        return reqInteger("sub_total");
    }

    public Integer subTotalInLocalCurrency() {
        return optInteger("sub_total_in_local_currency");
    }

    public Integer totalInLocalCurrency() {
        return optInteger("total_in_local_currency");
    }

    public String localCurrencyCode() {
        return optString("local_currency_code");
    }

    public Integer roundOffAmount() {
        return optInteger("round_off_amount");
    }

    public Integer fractionalCorrection() {
        return optInteger("fractional_correction");
    }

    public List<CreditNote.LineItem> lineItems() {
        return optList("line_items", CreditNote.LineItem.class);
    }

    public List<CreditNote.Discount> discounts() {
        return optList("discounts", CreditNote.Discount.class);
    }

    public List<CreditNote.LineItemDiscount> lineItemDiscounts() {
        return optList("line_item_discounts", CreditNote.LineItemDiscount.class);
    }

    public List<CreditNote.LineItemTier> lineItemTiers() {
        return optList("line_item_tiers", CreditNote.LineItemTier.class);
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

    public Boolean deleted() {
        return reqBoolean("deleted");
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("credit_notes");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("credit_notes", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static PdfRequest pdf(String id) {
        String uri = uri("credit_notes", nullCheck(id), "pdf");
        return new PdfRequest(Method.POST, uri);
    }

    public static RefundRequest refund(String id) {
        String uri = uri("credit_notes", nullCheck(id), "refund");
        return new RefundRequest(Method.POST, uri);
    }

    public static RecordRefundRequest recordRefund(String id) {
        String uri = uri("credit_notes", nullCheck(id), "record_refund");
        return new RecordRefundRequest(Method.POST, uri);
    }

    public static VoidCreditNoteRequest voidCreditNote(String id) {
        String uri = uri("credit_notes", nullCheck(id), "void");
        return new VoidCreditNoteRequest(Method.POST, uri);
    }

    public static CreditNoteListRequest list() {
        String uri = uri("credit_notes");
        return new CreditNoteListRequest(uri);
    }

    @Deprecated
    public static ListRequest creditNotesForCustomer(String id) {
        String uri = uri("customers", nullCheck(id), "credit_notes");
        return new ListRequest(uri);
    }

    public static DeleteRequest delete(String id) {
        String uri = uri("credit_notes", nullCheck(id), "delete");
        return new DeleteRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest referenceInvoiceId(String referenceInvoiceId) {
            params.add("reference_invoice_id", referenceInvoiceId);
            return this;
        }


        public CreateRequest total(Integer total) {
            params.addOpt("total", total);
            return this;
        }


        public CreateRequest type(CreditNote.Type type) {
            params.add("type", type);
            return this;
        }


        public CreateRequest reasonCode(CreditNote.ReasonCode reasonCode) {
            params.add("reason_code", reasonCode);
            return this;
        }


        public CreateRequest date(Timestamp date) {
            params.addOpt("date", date);
            return this;
        }


        public CreateRequest customerNotes(String customerNotes) {
            params.addOpt("customer_notes", customerNotes);
            return this;
        }


        public CreateRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        public CreateRequest lineItemReferenceLineItemId(int index, String lineItemReferenceLineItemId) {
            params.add("line_items[reference_line_item_id][" + index + "]", lineItemReferenceLineItemId);
            return this;
        }
        public CreateRequest lineItemUnitAmount(int index, Integer lineItemUnitAmount) {
            params.addOpt("line_items[unit_amount][" + index + "]", lineItemUnitAmount);
            return this;
        }
        public CreateRequest lineItemQuantity(int index, Integer lineItemQuantity) {
            params.addOpt("line_items[quantity][" + index + "]", lineItemQuantity);
            return this;
        }
        public CreateRequest lineItemAmount(int index, Integer lineItemAmount) {
            params.addOpt("line_items[amount][" + index + "]", lineItemAmount);
            return this;
        }
        public CreateRequest lineItemDateFrom(int index, Timestamp lineItemDateFrom) {
            params.addOpt("line_items[date_from][" + index + "]", lineItemDateFrom);
            return this;
        }
        public CreateRequest lineItemDateTo(int index, Timestamp lineItemDateTo) {
            params.addOpt("line_items[date_to][" + index + "]", lineItemDateTo);
            return this;
        }
        public CreateRequest lineItemDescription(int index, String lineItemDescription) {
            params.addOpt("line_items[description][" + index + "]", lineItemDescription);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class PdfRequest extends Request<PdfRequest> {

        private PdfRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public PdfRequest dispositionType(com.chargebee.models.enums.DispositionType dispositionType) {
            params.addOpt("disposition_type", dispositionType);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class RefundRequest extends Request<RefundRequest> {

        private RefundRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RefundRequest refundAmount(Integer refundAmount) {
            params.addOpt("refund_amount", refundAmount);
            return this;
        }


        public RefundRequest customerNotes(String customerNotes) {
            params.addOpt("customer_notes", customerNotes);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class RecordRefundRequest extends Request<RecordRefundRequest> {

        private RecordRefundRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RecordRefundRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        public RecordRefundRequest transactionAmount(Integer transactionAmount) {
            params.addOpt("transaction[amount]", transactionAmount);
            return this;
        }

        public RecordRefundRequest transactionPaymentMethod(com.chargebee.models.enums.PaymentMethod transactionPaymentMethod) {
            params.add("transaction[payment_method]", transactionPaymentMethod);
            return this;
        }

        public RecordRefundRequest transactionReferenceNumber(String transactionReferenceNumber) {
            params.addOpt("transaction[reference_number]", transactionReferenceNumber);
            return this;
        }

        public RecordRefundRequest transactionDate(Timestamp transactionDate) {
            params.add("transaction[date]", transactionDate);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class VoidCreditNoteRequest extends Request<VoidCreditNoteRequest> {

        private VoidCreditNoteRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public VoidCreditNoteRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreditNoteListRequest extends ListRequest<CreditNoteListRequest> {

        private CreditNoteListRequest(String uri) {
            super(uri);
        }
    


        public CreditNoteListRequest includeDeleted(Boolean includeDeleted) {
            params.addOpt("include_deleted", includeDeleted);
            return this;
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


        public EnumFilter<CreditNote.Type, CreditNoteListRequest> type() {
            return new EnumFilter<CreditNote.Type, CreditNoteListRequest>("type",this);        
        }


        public EnumFilter<CreditNote.ReasonCode, CreditNoteListRequest> reasonCode() {
            return new EnumFilter<CreditNote.ReasonCode, CreditNoteListRequest>("reason_code",this);        
        }


        public EnumFilter<CreditNote.Status, CreditNoteListRequest> status() {
            return new EnumFilter<CreditNote.Status, CreditNoteListRequest>("status",this);        
        }


        public TimestampFilter<CreditNoteListRequest> date() {
            return new TimestampFilter<CreditNoteListRequest>("date",this);        
        }


        public NumberFilter<Integer, CreditNoteListRequest> total() {
            return new NumberFilter<Integer, CreditNoteListRequest>("total",this);        
        }


        public EnumFilter<com.chargebee.models.enums.PriceType, CreditNoteListRequest> priceType() {
            return new EnumFilter<com.chargebee.models.enums.PriceType, CreditNoteListRequest>("price_type",this);        
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


        public TimestampFilter<CreditNoteListRequest> voidedAt() {
            return new TimestampFilter<CreditNoteListRequest>("voided_at",this);        
        }


        public TimestampFilter<CreditNoteListRequest> updatedAt() {
            return new TimestampFilter<CreditNoteListRequest>("updated_at",this);        
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

    public static class DeleteRequest extends Request<DeleteRequest> {

        private DeleteRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public DeleteRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
