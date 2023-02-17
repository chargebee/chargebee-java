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

    public static class Einvoice extends Resource<Einvoice> {
        public enum Status {
             SCHEDULED,SKIPPED,IN_PROGRESS,SUCCESS,FAILED,REGISTERED,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public Einvoice(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public Status status() {
            return reqEnum("status", Status.class);
        }

        public String message() {
            return optString("message");
        }

    }

    public static class LineItem extends Resource<LineItem> {
        public enum EntityType {
             PLAN_SETUP,PLAN,ADDON,ADHOC,PLAN_ITEM_PRICE,ADDON_ITEM_PRICE,CHARGE_ITEM_PRICE,
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

        public Long unitAmount() {
            return reqLong("unit_amount");
        }

        public Integer quantity() {
            return optInteger("quantity");
        }

        public Long amount() {
            return optLong("amount");
        }

        public PricingModel pricingModel() {
            return optEnum("pricing_model", PricingModel.class);
        }

        public Boolean isTaxed() {
            return reqBoolean("is_taxed");
        }

        public Long taxAmount() {
            return optLong("tax_amount");
        }

        public Double taxRate() {
            return optDouble("tax_rate");
        }

        public String unitAmountInDecimal() {
            return optString("unit_amount_in_decimal");
        }

        public String quantityInDecimal() {
            return optString("quantity_in_decimal");
        }

        public String amountInDecimal() {
            return optString("amount_in_decimal");
        }

        public Long discountAmount() {
            return optLong("discount_amount");
        }

        public Long itemLevelDiscountAmount() {
            return optLong("item_level_discount_amount");
        }

        public String referenceLineItemId() {
            return optString("reference_line_item_id");
        }

        public String description() {
            return reqString("description");
        }

        public String entityDescription() {
            return optString("entity_description");
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
             ITEM_LEVEL_COUPON,DOCUMENT_LEVEL_COUPON,PROMOTIONAL_CREDITS,PRORATED_CREDITS,ITEM_LEVEL_DISCOUNT,DOCUMENT_LEVEL_DISCOUNT,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public Discount(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Long amount() {
            return reqLong("amount");
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

        public String couponSetCode() {
            return optString("coupon_set_code");
        }

    }

    public static class LineItemDiscount extends Resource<LineItemDiscount> {
        public enum DiscountType {
             ITEM_LEVEL_COUPON,DOCUMENT_LEVEL_COUPON,PROMOTIONAL_CREDITS,PRORATED_CREDITS,ITEM_LEVEL_DISCOUNT,DOCUMENT_LEVEL_DISCOUNT,
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

        public String entityId() {
            return optString("entity_id");
        }

        public Long discountAmount() {
            return reqLong("discount_amount");
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

        public Long unitAmount() {
            return reqLong("unit_amount");
        }

        public String startingUnitInDecimal() {
            return optString("starting_unit_in_decimal");
        }

        public String endingUnitInDecimal() {
            return optString("ending_unit_in_decimal");
        }

        public String quantityUsedInDecimal() {
            return optString("quantity_used_in_decimal");
        }

        public String unitAmountInDecimal() {
            return optString("unit_amount_in_decimal");
        }

    }

    public static class Tax extends Resource<Tax> {
        public Tax(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String name() {
            return reqString("name");
        }

        public Long amount() {
            return reqLong("amount");
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

        public Long taxableAmount() {
            return reqLong("taxable_amount");
        }

        public Long taxAmount() {
            return reqLong("tax_amount");
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

        public Long taxAmountInLocalCurrency() {
            return optLong("tax_amount_in_local_currency");
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

        public Long appliedAmount() {
            return reqLong("applied_amount");
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

        public Long txnAmount() {
            return optLong("txn_amount");
        }

        public String refundReasonCode() {
            return optString("refund_reason_code");
        }

    }

    public static class Allocation extends Resource<Allocation> {
        public Allocation(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String invoiceId() {
            return reqString("invoice_id");
        }

        public Long allocatedAmount() {
            return reqLong("allocated_amount");
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

    public static class ShippingAddress extends Resource<ShippingAddress> {
        public ShippingAddress(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String firstName() {
            return optString("first_name");
        }

        public String lastName() {
            return optString("last_name");
        }

        public String email() {
            return optString("email");
        }

        public String company() {
            return optString("company");
        }

        public String phone() {
            return optString("phone");
        }

        public String line1() {
            return optString("line1");
        }

        public String line2() {
            return optString("line2");
        }

        public String line3() {
            return optString("line3");
        }

        public String city() {
            return optString("city");
        }

        public String stateCode() {
            return optString("state_code");
        }

        public String state() {
            return optString("state");
        }

        public String country() {
            return optString("country");
        }

        public String zip() {
            return optString("zip");
        }

        public ValidationStatus validationStatus() {
            return optEnum("validation_status", ValidationStatus.class);
        }

        public Integer index() {
            return reqInteger("index");
        }

    }

    public static class BillingAddress extends Resource<BillingAddress> {
        public BillingAddress(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String firstName() {
            return optString("first_name");
        }

        public String lastName() {
            return optString("last_name");
        }

        public String email() {
            return optString("email");
        }

        public String company() {
            return optString("company");
        }

        public String phone() {
            return optString("phone");
        }

        public String line1() {
            return optString("line1");
        }

        public String line2() {
            return optString("line2");
        }

        public String line3() {
            return optString("line3");
        }

        public String city() {
            return optString("city");
        }

        public String stateCode() {
            return optString("state_code");
        }

        public String state() {
            return optString("state");
        }

        public String country() {
            return optString("country");
        }

        public String zip() {
            return optString("zip");
        }

        public ValidationStatus validationStatus() {
            return optEnum("validation_status", ValidationStatus.class);
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
        return optEnum("reason_code", ReasonCode.class);
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

    public Long total() {
        return optLong("total");
    }

    public Long amountAllocated() {
        return optLong("amount_allocated");
    }

    public Long amountRefunded() {
        return optLong("amount_refunded");
    }

    public Long amountAvailable() {
        return optLong("amount_available");
    }

    public Timestamp refundedAt() {
        return optTimestamp("refunded_at");
    }

    public Timestamp voidedAt() {
        return optTimestamp("voided_at");
    }

    public Timestamp generatedAt() {
        return optTimestamp("generated_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public Channel channel() {
        return optEnum("channel", Channel.class);
    }

    public CreditNote.Einvoice einvoice() {
        return optSubResource("einvoice", CreditNote.Einvoice.class);
    }

    public Long subTotal() {
        return reqLong("sub_total");
    }

    public Long subTotalInLocalCurrency() {
        return optLong("sub_total_in_local_currency");
    }

    public Long totalInLocalCurrency() {
        return optLong("total_in_local_currency");
    }

    public String localCurrencyCode() {
        return optString("local_currency_code");
    }

    public Long roundOffAmount() {
        return optLong("round_off_amount");
    }

    public Long fractionalCorrection() {
        return optLong("fractional_correction");
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

    public String createReasonCode() {
        return optString("create_reason_code");
    }

    public String vatNumberPrefix() {
        return optString("vat_number_prefix");
    }

    public String businessEntityId() {
        return reqString("business_entity_id");
    }

    public CreditNote.ShippingAddress shippingAddress() {
        return optSubResource("shipping_address", CreditNote.ShippingAddress.class);
    }

    public CreditNote.BillingAddress billingAddress() {
        return optSubResource("billing_address", CreditNote.BillingAddress.class);
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

    public static Request downloadEinvoice(String id) {
        String uri = uri("credit_notes", nullCheck(id), "download_einvoice");
        return new Request(Method.GET, uri);
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

    public static RemoveTaxWithheldRefundRequest removeTaxWithheldRefund(String id) {
        String uri = uri("credit_notes", nullCheck(id), "remove_tax_withheld_refund");
        return new RemoveTaxWithheldRefundRequest(Method.POST, uri);
    }

    public static Request resendEinvoice(String id) {
        String uri = uri("credit_notes", nullCheck(id), "resend_einvoice");
        return new Request(Method.POST, uri);
    }

    public static ImportCreditNoteRequest importCreditNote() {
        String uri = uri("credit_notes", "import_credit_note");
        return new ImportCreditNoteRequest(Method.POST, uri);
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


        public CreateRequest total(Long total) {
            params.addOpt("total", total);
            return this;
        }


        public CreateRequest type(CreditNote.Type type) {
            params.add("type", type);
            return this;
        }


        public CreateRequest reasonCode(CreditNote.ReasonCode reasonCode) {
            params.addOpt("reason_code", reasonCode);
            return this;
        }


        public CreateRequest createReasonCode(String createReasonCode) {
            params.addOpt("create_reason_code", createReasonCode);
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
        public CreateRequest lineItemUnitAmount(int index, Long lineItemUnitAmount) {
            params.addOpt("line_items[unit_amount][" + index + "]", lineItemUnitAmount);
            return this;
        }
        public CreateRequest lineItemUnitAmountInDecimal(int index, String lineItemUnitAmountInDecimal) {
            params.addOpt("line_items[unit_amount_in_decimal][" + index + "]", lineItemUnitAmountInDecimal);
            return this;
        }
        public CreateRequest lineItemQuantity(int index, Integer lineItemQuantity) {
            params.addOpt("line_items[quantity][" + index + "]", lineItemQuantity);
            return this;
        }
        public CreateRequest lineItemQuantityInDecimal(int index, String lineItemQuantityInDecimal) {
            params.addOpt("line_items[quantity_in_decimal][" + index + "]", lineItemQuantityInDecimal);
            return this;
        }
        public CreateRequest lineItemAmount(int index, Long lineItemAmount) {
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
    
        public RefundRequest refundAmount(Long refundAmount) {
            params.addOpt("refund_amount", refundAmount);
            return this;
        }


        public RefundRequest customerNotes(String customerNotes) {
            params.addOpt("customer_notes", customerNotes);
            return this;
        }


        public RefundRequest refundReasonCode(String refundReasonCode) {
            params.addOpt("refund_reason_code", refundReasonCode);
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
    
        public RecordRefundRequest refundReasonCode(String refundReasonCode) {
            params.addOpt("refund_reason_code", refundReasonCode);
            return this;
        }


        public RecordRefundRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        public RecordRefundRequest transactionAmount(Long transactionAmount) {
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


        public StringFilter<CreditNoteListRequest> createReasonCode() {
            return new StringFilter<CreditNoteListRequest>("create_reason_code",this).supportsMultiOperators(true);        
        }


        public EnumFilter<CreditNote.Status, CreditNoteListRequest> status() {
            return new EnumFilter<CreditNote.Status, CreditNoteListRequest>("status",this);        
        }


        public TimestampFilter<CreditNoteListRequest> date() {
            return new TimestampFilter<CreditNoteListRequest>("date",this);        
        }


        public NumberFilter<Long, CreditNoteListRequest> total() {
            return new NumberFilter<Long, CreditNoteListRequest>("total",this);        
        }


        public EnumFilter<com.chargebee.models.enums.PriceType, CreditNoteListRequest> priceType() {
            return new EnumFilter<com.chargebee.models.enums.PriceType, CreditNoteListRequest>("price_type",this);        
        }


        public NumberFilter<Long, CreditNoteListRequest> amountAllocated() {
            return new NumberFilter<Long, CreditNoteListRequest>("amount_allocated",this);        
        }


        public NumberFilter<Long, CreditNoteListRequest> amountRefunded() {
            return new NumberFilter<Long, CreditNoteListRequest>("amount_refunded",this);        
        }


        public NumberFilter<Long, CreditNoteListRequest> amountAvailable() {
            return new NumberFilter<Long, CreditNoteListRequest>("amount_available",this);        
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


        public EnumFilter<com.chargebee.models.enums.Channel, CreditNoteListRequest> channel() {
            return new EnumFilter<com.chargebee.models.enums.Channel, CreditNoteListRequest>("channel",this);        
        }


        public EnumFilter<Einvoice.Status, CreditNoteListRequest> einvoiceStatus() {
            return new EnumFilter<Einvoice.Status, CreditNoteListRequest>("einvoice[status]",this);        
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

    public static class RemoveTaxWithheldRefundRequest extends Request<RemoveTaxWithheldRefundRequest> {

        private RemoveTaxWithheldRefundRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RemoveTaxWithheldRefundRequest taxWithheldId(String taxWithheldId) {
            params.add("tax_withheld[id]", taxWithheldId);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class ImportCreditNoteRequest extends Request<ImportCreditNoteRequest> {

        private ImportCreditNoteRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ImportCreditNoteRequest id(String id) {
            params.add("id", id);
            return this;
        }


        public ImportCreditNoteRequest customerId(String customerId) {
            params.addOpt("customer_id", customerId);
            return this;
        }


        public ImportCreditNoteRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public ImportCreditNoteRequest referenceInvoiceId(String referenceInvoiceId) {
            params.add("reference_invoice_id", referenceInvoiceId);
            return this;
        }


        public ImportCreditNoteRequest type(CreditNote.Type type) {
            params.add("type", type);
            return this;
        }


        public ImportCreditNoteRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public ImportCreditNoteRequest createReasonCode(String createReasonCode) {
            params.add("create_reason_code", createReasonCode);
            return this;
        }


        public ImportCreditNoteRequest date(Timestamp date) {
            params.addOpt("date", date);
            return this;
        }


        public ImportCreditNoteRequest status(CreditNote.Status status) {
            params.addOpt("status", status);
            return this;
        }


        public ImportCreditNoteRequest total(Long total) {
            params.addOpt("total", total);
            return this;
        }


        public ImportCreditNoteRequest refundedAt(Timestamp refundedAt) {
            params.addOpt("refunded_at", refundedAt);
            return this;
        }


        public ImportCreditNoteRequest voidedAt(Timestamp voidedAt) {
            params.addOpt("voided_at", voidedAt);
            return this;
        }


        public ImportCreditNoteRequest subTotal(Long subTotal) {
            params.addOpt("sub_total", subTotal);
            return this;
        }


        public ImportCreditNoteRequest roundOffAmount(Long roundOffAmount) {
            params.addOpt("round_off_amount", roundOffAmount);
            return this;
        }


        public ImportCreditNoteRequest fractionalCorrection(Long fractionalCorrection) {
            params.addOpt("fractional_correction", fractionalCorrection);
            return this;
        }


        public ImportCreditNoteRequest vatNumberPrefix(String vatNumberPrefix) {
            params.addOpt("vat_number_prefix", vatNumberPrefix);
            return this;
        }


        public ImportCreditNoteRequest lineItemReferenceLineItemId(int index, String lineItemReferenceLineItemId) {
            params.addOpt("line_items[reference_line_item_id][" + index + "]", lineItemReferenceLineItemId);
            return this;
        }
        public ImportCreditNoteRequest lineItemId(int index, String lineItemId) {
            params.addOpt("line_items[id][" + index + "]", lineItemId);
            return this;
        }
        public ImportCreditNoteRequest lineItemDateFrom(int index, Timestamp lineItemDateFrom) {
            params.addOpt("line_items[date_from][" + index + "]", lineItemDateFrom);
            return this;
        }
        public ImportCreditNoteRequest lineItemDateTo(int index, Timestamp lineItemDateTo) {
            params.addOpt("line_items[date_to][" + index + "]", lineItemDateTo);
            return this;
        }
        public ImportCreditNoteRequest lineItemSubscriptionId(int index, String lineItemSubscriptionId) {
            params.addOpt("line_items[subscription_id][" + index + "]", lineItemSubscriptionId);
            return this;
        }
        public ImportCreditNoteRequest lineItemDescription(int index, String lineItemDescription) {
            params.add("line_items[description][" + index + "]", lineItemDescription);
            return this;
        }
        public ImportCreditNoteRequest lineItemUnitAmount(int index, Long lineItemUnitAmount) {
            params.addOpt("line_items[unit_amount][" + index + "]", lineItemUnitAmount);
            return this;
        }
        public ImportCreditNoteRequest lineItemQuantity(int index, Integer lineItemQuantity) {
            params.addOpt("line_items[quantity][" + index + "]", lineItemQuantity);
            return this;
        }
        public ImportCreditNoteRequest lineItemAmount(int index, Long lineItemAmount) {
            params.addOpt("line_items[amount][" + index + "]", lineItemAmount);
            return this;
        }
        public ImportCreditNoteRequest lineItemUnitAmountInDecimal(int index, String lineItemUnitAmountInDecimal) {
            params.addOpt("line_items[unit_amount_in_decimal][" + index + "]", lineItemUnitAmountInDecimal);
            return this;
        }
        public ImportCreditNoteRequest lineItemQuantityInDecimal(int index, String lineItemQuantityInDecimal) {
            params.addOpt("line_items[quantity_in_decimal][" + index + "]", lineItemQuantityInDecimal);
            return this;
        }
        public ImportCreditNoteRequest lineItemAmountInDecimal(int index, String lineItemAmountInDecimal) {
            params.addOpt("line_items[amount_in_decimal][" + index + "]", lineItemAmountInDecimal);
            return this;
        }
        public ImportCreditNoteRequest lineItemEntityType(int index, LineItem.EntityType lineItemEntityType) {
            params.addOpt("line_items[entity_type][" + index + "]", lineItemEntityType);
            return this;
        }
        public ImportCreditNoteRequest lineItemEntityId(int index, String lineItemEntityId) {
            params.addOpt("line_items[entity_id][" + index + "]", lineItemEntityId);
            return this;
        }
        public ImportCreditNoteRequest lineItemItemLevelDiscount1EntityId(int index, String lineItemItemLevelDiscount1EntityId) {
            params.addOpt("line_items[item_level_discount1_entity_id][" + index + "]", lineItemItemLevelDiscount1EntityId);
            return this;
        }
        public ImportCreditNoteRequest lineItemItemLevelDiscount1Amount(int index, Long lineItemItemLevelDiscount1Amount) {
            params.addOpt("line_items[item_level_discount1_amount][" + index + "]", lineItemItemLevelDiscount1Amount);
            return this;
        }
        public ImportCreditNoteRequest lineItemItemLevelDiscount2EntityId(int index, String lineItemItemLevelDiscount2EntityId) {
            params.addOpt("line_items[item_level_discount2_entity_id][" + index + "]", lineItemItemLevelDiscount2EntityId);
            return this;
        }
        public ImportCreditNoteRequest lineItemItemLevelDiscount2Amount(int index, Long lineItemItemLevelDiscount2Amount) {
            params.addOpt("line_items[item_level_discount2_amount][" + index + "]", lineItemItemLevelDiscount2Amount);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax1Name(int index, String lineItemTax1Name) {
            params.addOpt("line_items[tax1_name][" + index + "]", lineItemTax1Name);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax1Amount(int index, Long lineItemTax1Amount) {
            params.addOpt("line_items[tax1_amount][" + index + "]", lineItemTax1Amount);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax2Name(int index, String lineItemTax2Name) {
            params.addOpt("line_items[tax2_name][" + index + "]", lineItemTax2Name);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax2Amount(int index, Long lineItemTax2Amount) {
            params.addOpt("line_items[tax2_amount][" + index + "]", lineItemTax2Amount);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax3Name(int index, String lineItemTax3Name) {
            params.addOpt("line_items[tax3_name][" + index + "]", lineItemTax3Name);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax3Amount(int index, Long lineItemTax3Amount) {
            params.addOpt("line_items[tax3_amount][" + index + "]", lineItemTax3Amount);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax4Name(int index, String lineItemTax4Name) {
            params.addOpt("line_items[tax4_name][" + index + "]", lineItemTax4Name);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax4Amount(int index, Long lineItemTax4Amount) {
            params.addOpt("line_items[tax4_amount][" + index + "]", lineItemTax4Amount);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax5Name(int index, String lineItemTax5Name) {
            params.addOpt("line_items[tax5_name][" + index + "]", lineItemTax5Name);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax5Amount(int index, Long lineItemTax5Amount) {
            params.addOpt("line_items[tax5_amount][" + index + "]", lineItemTax5Amount);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax6Name(int index, String lineItemTax6Name) {
            params.addOpt("line_items[tax6_name][" + index + "]", lineItemTax6Name);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax6Amount(int index, Long lineItemTax6Amount) {
            params.addOpt("line_items[tax6_amount][" + index + "]", lineItemTax6Amount);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax7Name(int index, String lineItemTax7Name) {
            params.addOpt("line_items[tax7_name][" + index + "]", lineItemTax7Name);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax7Amount(int index, Long lineItemTax7Amount) {
            params.addOpt("line_items[tax7_amount][" + index + "]", lineItemTax7Amount);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax8Name(int index, String lineItemTax8Name) {
            params.addOpt("line_items[tax8_name][" + index + "]", lineItemTax8Name);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax8Amount(int index, Long lineItemTax8Amount) {
            params.addOpt("line_items[tax8_amount][" + index + "]", lineItemTax8Amount);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax9Name(int index, String lineItemTax9Name) {
            params.addOpt("line_items[tax9_name][" + index + "]", lineItemTax9Name);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax9Amount(int index, Long lineItemTax9Amount) {
            params.addOpt("line_items[tax9_amount][" + index + "]", lineItemTax9Amount);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax10Name(int index, String lineItemTax10Name) {
            params.addOpt("line_items[tax10_name][" + index + "]", lineItemTax10Name);
            return this;
        }
        public ImportCreditNoteRequest lineItemTax10Amount(int index, Long lineItemTax10Amount) {
            params.addOpt("line_items[tax10_amount][" + index + "]", lineItemTax10Amount);
            return this;
        }
        public ImportCreditNoteRequest lineItemTierLineItemId(int index, String lineItemTierLineItemId) {
            params.add("line_item_tiers[line_item_id][" + index + "]", lineItemTierLineItemId);
            return this;
        }
        public ImportCreditNoteRequest lineItemTierStartingUnit(int index, Integer lineItemTierStartingUnit) {
            params.addOpt("line_item_tiers[starting_unit][" + index + "]", lineItemTierStartingUnit);
            return this;
        }
        public ImportCreditNoteRequest lineItemTierEndingUnit(int index, Integer lineItemTierEndingUnit) {
            params.addOpt("line_item_tiers[ending_unit][" + index + "]", lineItemTierEndingUnit);
            return this;
        }
        public ImportCreditNoteRequest lineItemTierQuantityUsed(int index, Integer lineItemTierQuantityUsed) {
            params.addOpt("line_item_tiers[quantity_used][" + index + "]", lineItemTierQuantityUsed);
            return this;
        }
        public ImportCreditNoteRequest lineItemTierUnitAmount(int index, Long lineItemTierUnitAmount) {
            params.addOpt("line_item_tiers[unit_amount][" + index + "]", lineItemTierUnitAmount);
            return this;
        }
        public ImportCreditNoteRequest lineItemTierStartingUnitInDecimal(int index, String lineItemTierStartingUnitInDecimal) {
            params.addOpt("line_item_tiers[starting_unit_in_decimal][" + index + "]", lineItemTierStartingUnitInDecimal);
            return this;
        }
        public ImportCreditNoteRequest lineItemTierEndingUnitInDecimal(int index, String lineItemTierEndingUnitInDecimal) {
            params.addOpt("line_item_tiers[ending_unit_in_decimal][" + index + "]", lineItemTierEndingUnitInDecimal);
            return this;
        }
        public ImportCreditNoteRequest lineItemTierQuantityUsedInDecimal(int index, String lineItemTierQuantityUsedInDecimal) {
            params.addOpt("line_item_tiers[quantity_used_in_decimal][" + index + "]", lineItemTierQuantityUsedInDecimal);
            return this;
        }
        public ImportCreditNoteRequest lineItemTierUnitAmountInDecimal(int index, String lineItemTierUnitAmountInDecimal) {
            params.addOpt("line_item_tiers[unit_amount_in_decimal][" + index + "]", lineItemTierUnitAmountInDecimal);
            return this;
        }
        public ImportCreditNoteRequest discountLineItemId(int index, String discountLineItemId) {
            params.addOpt("discounts[line_item_id][" + index + "]", discountLineItemId);
            return this;
        }
        public ImportCreditNoteRequest discountEntityType(int index, Discount.EntityType discountEntityType) {
            params.add("discounts[entity_type][" + index + "]", discountEntityType);
            return this;
        }
        public ImportCreditNoteRequest discountEntityId(int index, String discountEntityId) {
            params.addOpt("discounts[entity_id][" + index + "]", discountEntityId);
            return this;
        }
        public ImportCreditNoteRequest discountDescription(int index, String discountDescription) {
            params.addOpt("discounts[description][" + index + "]", discountDescription);
            return this;
        }
        public ImportCreditNoteRequest discountAmount(int index, Long discountAmount) {
            params.add("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public ImportCreditNoteRequest taxName(int index, String taxName) {
            params.add("taxes[name][" + index + "]", taxName);
            return this;
        }
        public ImportCreditNoteRequest taxRate(int index, Double taxRate) {
            params.add("taxes[rate][" + index + "]", taxRate);
            return this;
        }
        public ImportCreditNoteRequest taxAmount(int index, Long taxAmount) {
            params.addOpt("taxes[amount][" + index + "]", taxAmount);
            return this;
        }
        public ImportCreditNoteRequest taxDescription(int index, String taxDescription) {
            params.addOpt("taxes[description][" + index + "]", taxDescription);
            return this;
        }
        public ImportCreditNoteRequest taxJurisType(int index, com.chargebee.models.enums.TaxJurisType taxJurisType) {
            params.addOpt("taxes[juris_type][" + index + "]", taxJurisType);
            return this;
        }
        public ImportCreditNoteRequest taxJurisName(int index, String taxJurisName) {
            params.addOpt("taxes[juris_name][" + index + "]", taxJurisName);
            return this;
        }
        public ImportCreditNoteRequest taxJurisCode(int index, String taxJurisCode) {
            params.addOpt("taxes[juris_code][" + index + "]", taxJurisCode);
            return this;
        }
        public ImportCreditNoteRequest allocationInvoiceId(int index, String allocationInvoiceId) {
            params.add("allocations[invoice_id][" + index + "]", allocationInvoiceId);
            return this;
        }
        public ImportCreditNoteRequest allocationAllocatedAmount(int index, Long allocationAllocatedAmount) {
            params.add("allocations[allocated_amount][" + index + "]", allocationAllocatedAmount);
            return this;
        }
        public ImportCreditNoteRequest allocationAllocatedAt(int index, Timestamp allocationAllocatedAt) {
            params.add("allocations[allocated_at][" + index + "]", allocationAllocatedAt);
            return this;
        }
        public ImportCreditNoteRequest linkedRefundAmount(int index, Long linkedRefundAmount) {
            params.add("linked_refunds[amount][" + index + "]", linkedRefundAmount);
            return this;
        }
        public ImportCreditNoteRequest linkedRefundPaymentMethod(int index, com.chargebee.models.enums.PaymentMethod linkedRefundPaymentMethod) {
            params.add("linked_refunds[payment_method][" + index + "]", linkedRefundPaymentMethod);
            return this;
        }
        public ImportCreditNoteRequest linkedRefundDate(int index, Timestamp linkedRefundDate) {
            params.add("linked_refunds[date][" + index + "]", linkedRefundDate);
            return this;
        }
        public ImportCreditNoteRequest linkedRefundReferenceNumber(int index, String linkedRefundReferenceNumber) {
            params.addOpt("linked_refunds[reference_number][" + index + "]", linkedRefundReferenceNumber);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

}
