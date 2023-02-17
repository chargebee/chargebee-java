package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.filters.*;
import com.chargebee.filters.enums.SortOrder;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

public class Invoice extends Resource<Invoice> {

    public enum Status {
        PAID,
        POSTED,
        PAYMENT_DUE,
        NOT_PAID,
        VOIDED,
        PENDING,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum DunningStatus {
        IN_PROGRESS,
        EXHAUSTED,
        STOPPED,
        SUCCESS,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
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

    public static class LinkedPayment extends Resource<LinkedPayment> {
        public LinkedPayment(JSONObject jsonObj) {
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

    }

    public static class DunningAttempt extends Resource<DunningAttempt> {
        public DunningAttempt(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Integer attempt() {
            return reqInteger("attempt");
        }

        public String transactionId() {
            return optString("transaction_id");
        }

        public DunningType dunningType() {
            return reqEnum("dunning_type", DunningType.class);
        }

        public Timestamp createdAt() {
            return optTimestamp("created_at");
        }

        public Transaction.Status txnStatus() {
            return optEnum("txn_status", Transaction.Status.class);
        }

        public Long txnAmount() {
            return optLong("txn_amount");
        }

    }

    public static class AppliedCredit extends Resource<AppliedCredit> {
        public AppliedCredit(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String cnId() {
            return reqString("cn_id");
        }

        public Long appliedAmount() {
            return reqLong("applied_amount");
        }

        public Timestamp appliedAt() {
            return reqTimestamp("applied_at");
        }

        public CreditNote.ReasonCode cnReasonCode() {
            return optEnum("cn_reason_code", CreditNote.ReasonCode.class);
        }

        public String cnCreateReasonCode() {
            return optString("cn_create_reason_code");
        }

        public Timestamp cnDate() {
            return optTimestamp("cn_date");
        }

        public CreditNote.Status cnStatus() {
            return reqEnum("cn_status", CreditNote.Status.class);
        }

    }

    public static class AdjustmentCreditNote extends Resource<AdjustmentCreditNote> {
        public AdjustmentCreditNote(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String cnId() {
            return reqString("cn_id");
        }

        public CreditNote.ReasonCode cnReasonCode() {
            return optEnum("cn_reason_code", CreditNote.ReasonCode.class);
        }

        public String cnCreateReasonCode() {
            return optString("cn_create_reason_code");
        }

        public Timestamp cnDate() {
            return optTimestamp("cn_date");
        }

        public Long cnTotal() {
            return optLong("cn_total");
        }

        public CreditNote.Status cnStatus() {
            return reqEnum("cn_status", CreditNote.Status.class);
        }

    }

    public static class IssuedCreditNote extends Resource<IssuedCreditNote> {
        public IssuedCreditNote(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String cnId() {
            return reqString("cn_id");
        }

        public CreditNote.ReasonCode cnReasonCode() {
            return optEnum("cn_reason_code", CreditNote.ReasonCode.class);
        }

        public String cnCreateReasonCode() {
            return optString("cn_create_reason_code");
        }

        public Timestamp cnDate() {
            return optTimestamp("cn_date");
        }

        public Long cnTotal() {
            return optLong("cn_total");
        }

        public CreditNote.Status cnStatus() {
            return reqEnum("cn_status", CreditNote.Status.class);
        }

    }

    public static class LinkedOrder extends Resource<LinkedOrder> {
        public enum Status {
             NEW,PROCESSING,COMPLETE,CANCELLED,VOIDED,QUEUED,AWAITING_SHIPMENT,ON_HOLD,DELIVERED,SHIPPED,PARTIALLY_DELIVERED,RETURNED,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum OrderType {
             MANUAL,SYSTEM_GENERATED,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public LinkedOrder(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public String documentNumber() {
            return optString("document_number");
        }

        public Status status() {
            return optEnum("status", Status.class);
        }

        public OrderType orderType() {
            return optEnum("order_type", OrderType.class);
        }

        public String referenceId() {
            return optString("reference_id");
        }

        public String fulfillmentStatus() {
            return optString("fulfillment_status");
        }

        public String batchId() {
            return optString("batch_id");
        }

        public Timestamp createdAt() {
            return reqTimestamp("created_at");
        }

    }

    public static class Note extends Resource<Note> {
        public enum EntityType {
             PLAN,ADDON,COUPON,SUBSCRIPTION,CUSTOMER,PLAN_ITEM_PRICE,ADDON_ITEM_PRICE,CHARGE_ITEM_PRICE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public Note(JSONObject jsonObj) {
            super(jsonObj);
        }

        public EntityType entityType() {
            return reqEnum("entity_type", EntityType.class);
        }

        public String note() {
            return reqString("note");
        }

        public String entityId() {
            return optString("entity_id");
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

    //Constructors
    //============

    public Invoice(String jsonStr) {
        super(jsonStr);
    }

    public Invoice(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String poNumber() {
        return optString("po_number");
    }

    public String customerId() {
        return reqString("customer_id");
    }

    public String subscriptionId() {
        return optString("subscription_id");
    }

    public Boolean recurring() {
        return reqBoolean("recurring");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public String vatNumber() {
        return optString("vat_number");
    }

    public PriceType priceType() {
        return reqEnum("price_type", PriceType.class);
    }

    public Timestamp date() {
        return optTimestamp("date");
    }

    public Timestamp dueDate() {
        return optTimestamp("due_date");
    }

    public Integer netTermDays() {
        return optInteger("net_term_days");
    }

    public BigDecimal exchangeRate() {
        return optBigDecimal("exchange_rate");
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public Long total() {
        return optLong("total");
    }

    public Long amountPaid() {
        return optLong("amount_paid");
    }

    public Long amountAdjusted() {
        return optLong("amount_adjusted");
    }

    public Long writeOffAmount() {
        return optLong("write_off_amount");
    }

    public Long creditsApplied() {
        return optLong("credits_applied");
    }

    public Long amountDue() {
        return optLong("amount_due");
    }

    public Timestamp paidAt() {
        return optTimestamp("paid_at");
    }

    public DunningStatus dunningStatus() {
        return optEnum("dunning_status", DunningStatus.class);
    }

    public Timestamp nextRetryAt() {
        return optTimestamp("next_retry_at");
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

    public Long tax() {
        return reqLong("tax");
    }

    public Boolean firstInvoice() {
        return optBoolean("first_invoice");
    }

    public Long newSalesAmount() {
        return optLong("new_sales_amount");
    }

    public Boolean hasAdvanceCharges() {
        return optBoolean("has_advance_charges");
    }

    public Boolean termFinalized() {
        return reqBoolean("term_finalized");
    }

    public Boolean isGifted() {
        return reqBoolean("is_gifted");
    }

    public Timestamp generatedAt() {
        return optTimestamp("generated_at");
    }

    public Timestamp expectedPaymentDate() {
        return optTimestamp("expected_payment_date");
    }

    public Long amountToCollect() {
        return optLong("amount_to_collect");
    }

    public Long roundOffAmount() {
        return optLong("round_off_amount");
    }

    public List<Invoice.LineItem> lineItems() {
        return optList("line_items", Invoice.LineItem.class);
    }

    public List<Invoice.Discount> discounts() {
        return optList("discounts", Invoice.Discount.class);
    }

    public List<Invoice.LineItemDiscount> lineItemDiscounts() {
        return optList("line_item_discounts", Invoice.LineItemDiscount.class);
    }

    public List<Invoice.Tax> taxes() {
        return optList("taxes", Invoice.Tax.class);
    }

    public List<Invoice.LineItemTax> lineItemTaxes() {
        return optList("line_item_taxes", Invoice.LineItemTax.class);
    }

    public List<Invoice.LineItemTier> lineItemTiers() {
        return optList("line_item_tiers", Invoice.LineItemTier.class);
    }

    public List<Invoice.LinkedPayment> linkedPayments() {
        return optList("linked_payments", Invoice.LinkedPayment.class);
    }

    public List<Invoice.DunningAttempt> dunningAttempts() {
        return optList("dunning_attempts", Invoice.DunningAttempt.class);
    }

    public List<Invoice.AppliedCredit> appliedCredits() {
        return optList("applied_credits", Invoice.AppliedCredit.class);
    }

    public List<Invoice.AdjustmentCreditNote> adjustmentCreditNotes() {
        return optList("adjustment_credit_notes", Invoice.AdjustmentCreditNote.class);
    }

    public List<Invoice.IssuedCreditNote> issuedCreditNotes() {
        return optList("issued_credit_notes", Invoice.IssuedCreditNote.class);
    }

    public List<Invoice.LinkedOrder> linkedOrders() {
        return optList("linked_orders", Invoice.LinkedOrder.class);
    }

    public List<Invoice.Note> notes() {
        return optList("notes", Invoice.Note.class);
    }

    public Invoice.ShippingAddress shippingAddress() {
        return optSubResource("shipping_address", Invoice.ShippingAddress.class);
    }

    public Invoice.BillingAddress billingAddress() {
        return optSubResource("billing_address", Invoice.BillingAddress.class);
    }

    public Invoice.Einvoice einvoice() {
        return optSubResource("einvoice", Invoice.Einvoice.class);
    }

    public String paymentOwner() {
        return optString("payment_owner");
    }

    public String voidReasonCode() {
        return optString("void_reason_code");
    }

    public Boolean deleted() {
        return reqBoolean("deleted");
    }

    public String vatNumberPrefix() {
        return optString("vat_number_prefix");
    }

    public Channel channel() {
        return optEnum("channel", Channel.class);
    }

    public String businessEntityId() {
        return optString("business_entity_id");
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("invoices");
        return new CreateRequest(Method.POST, uri);
    }

    public static CreateForChargeItemsAndChargesRequest createForChargeItemsAndCharges() {
        String uri = uri("invoices", "create_for_charge_items_and_charges");
        return new CreateForChargeItemsAndChargesRequest(Method.POST, uri);
    }

    public static ChargeRequest charge() {
        String uri = uri("invoices", "charge");
        return new ChargeRequest(Method.POST, uri);
    }

    public static ChargeAddonRequest chargeAddon() {
        String uri = uri("invoices", "charge_addon");
        return new ChargeAddonRequest(Method.POST, uri);
    }

    @Deprecated
    public static CreateForChargeItemRequest createForChargeItem() {
        String uri = uri("invoices", "create_for_charge_item");
        return new CreateForChargeItemRequest(Method.POST, uri);
    }

    public static StopDunningRequest stopDunning(String id) {
        String uri = uri("invoices", nullCheck(id), "stop_dunning");
        return new StopDunningRequest(Method.POST, uri);
    }

    public static ImportInvoiceRequest importInvoice() {
        String uri = uri("invoices", "import_invoice");
        return new ImportInvoiceRequest(Method.POST, uri);
    }

    public static ApplyPaymentsRequest applyPayments(String id) {
        String uri = uri("invoices", nullCheck(id), "apply_payments");
        return new ApplyPaymentsRequest(Method.POST, uri);
    }

    public static Request syncUsages(String id) {
        String uri = uri("invoices", nullCheck(id), "sync_usages");
        return new Request(Method.POST, uri);
    }

    public static DeleteLineItemsRequest deleteLineItems(String id) {
        String uri = uri("invoices", nullCheck(id), "delete_line_items");
        return new DeleteLineItemsRequest(Method.POST, uri);
    }

    public static ApplyCreditsRequest applyCredits(String id) {
        String uri = uri("invoices", nullCheck(id), "apply_credits");
        return new ApplyCreditsRequest(Method.POST, uri);
    }

    public static InvoiceListRequest list() {
        String uri = uri("invoices");
        return new InvoiceListRequest(uri);
    }

    @Deprecated
    public static ListRequest invoicesForCustomer(String id) {
        String uri = uri("customers", nullCheck(id), "invoices");
        return new ListRequest(uri);
    }

    @Deprecated
    public static ListRequest invoicesForSubscription(String id) {
        String uri = uri("subscriptions", nullCheck(id), "invoices");
        return new ListRequest(uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("invoices", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static PdfRequest pdf(String id) {
        String uri = uri("invoices", nullCheck(id), "pdf");
        return new PdfRequest(Method.POST, uri);
    }

    public static Request downloadEinvoice(String id) {
        String uri = uri("invoices", nullCheck(id), "download_einvoice");
        return new Request(Method.GET, uri);
    }

    public static AddChargeRequest addCharge(String id) {
        String uri = uri("invoices", nullCheck(id), "add_charge");
        return new AddChargeRequest(Method.POST, uri);
    }

    public static AddAddonChargeRequest addAddonCharge(String id) {
        String uri = uri("invoices", nullCheck(id), "add_addon_charge");
        return new AddAddonChargeRequest(Method.POST, uri);
    }

    public static AddChargeItemRequest addChargeItem(String id) {
        String uri = uri("invoices", nullCheck(id), "add_charge_item");
        return new AddChargeItemRequest(Method.POST, uri);
    }

    public static CloseRequest close(String id) {
        String uri = uri("invoices", nullCheck(id), "close");
        return new CloseRequest(Method.POST, uri);
    }

    public static CollectPaymentRequest collectPayment(String id) {
        String uri = uri("invoices", nullCheck(id), "collect_payment");
        return new CollectPaymentRequest(Method.POST, uri);
    }

    public static RecordPaymentRequest recordPayment(String id) {
        String uri = uri("invoices", nullCheck(id), "record_payment");
        return new RecordPaymentRequest(Method.POST, uri);
    }

    public static RecordTaxWithheldRequest recordTaxWithheld(String id) {
        String uri = uri("invoices", nullCheck(id), "record_tax_withheld");
        return new RecordTaxWithheldRequest(Method.POST, uri);
    }

    public static RemoveTaxWithheldRequest removeTaxWithheld(String id) {
        String uri = uri("invoices", nullCheck(id), "remove_tax_withheld");
        return new RemoveTaxWithheldRequest(Method.POST, uri);
    }

    public static RefundRequest refund(String id) {
        String uri = uri("invoices", nullCheck(id), "refund");
        return new RefundRequest(Method.POST, uri);
    }

    public static RecordRefundRequest recordRefund(String id) {
        String uri = uri("invoices", nullCheck(id), "record_refund");
        return new RecordRefundRequest(Method.POST, uri);
    }

    public static RemovePaymentRequest removePayment(String id) {
        String uri = uri("invoices", nullCheck(id), "remove_payment");
        return new RemovePaymentRequest(Method.POST, uri);
    }

    public static RemoveCreditNoteRequest removeCreditNote(String id) {
        String uri = uri("invoices", nullCheck(id), "remove_credit_note");
        return new RemoveCreditNoteRequest(Method.POST, uri);
    }

    public static VoidInvoiceRequest voidInvoice(String id) {
        String uri = uri("invoices", nullCheck(id), "void");
        return new VoidInvoiceRequest(Method.POST, uri);
    }

    public static WriteOffRequest writeOff(String id) {
        String uri = uri("invoices", nullCheck(id), "write_off");
        return new WriteOffRequest(Method.POST, uri);
    }

    public static DeleteRequest delete(String id) {
        String uri = uri("invoices", nullCheck(id), "delete");
        return new DeleteRequest(Method.POST, uri);
    }

    public static UpdateDetailsRequest updateDetails(String id) {
        String uri = uri("invoices", nullCheck(id), "update_details");
        return new UpdateDetailsRequest(Method.POST, uri);
    }

    public static Request resendEinvoice(String id) {
        String uri = uri("invoices", nullCheck(id), "resend_einvoice");
        return new Request(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest customerId(String customerId) {
            params.addOpt("customer_id", customerId);
            return this;
        }


        public CreateRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public CreateRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }




        public CreateRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public CreateRequest invoiceNote(String invoiceNote) {
            params.addOpt("invoice_note", invoiceNote);
            return this;
        }


        public CreateRequest removeGeneralNote(Boolean removeGeneralNote) {
            params.addOpt("remove_general_note", removeGeneralNote);
            return this;
        }


        public CreateRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        @Deprecated
        public CreateRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public CreateRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateRequest authorizationTransactionId(String authorizationTransactionId) {
            params.addOpt("authorization_transaction_id", authorizationTransactionId);
            return this;
        }


        public CreateRequest paymentSourceId(String paymentSourceId) {
            params.addOpt("payment_source_id", paymentSourceId);
            return this;
        }


        public CreateRequest autoCollection(com.chargebee.models.enums.AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        public CreateRequest tokenId(String tokenId) {
            params.addOpt("token_id", tokenId);
            return this;
        }


        public CreateRequest replacePrimaryPaymentSource(Boolean replacePrimaryPaymentSource) {
            params.addOpt("replace_primary_payment_source", replacePrimaryPaymentSource);
            return this;
        }


        public CreateRequest retainPaymentSource(Boolean retainPaymentSource) {
            params.addOpt("retain_payment_source", retainPaymentSource);
            return this;
        }




        public CreateRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public CreateRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public CreateRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public CreateRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public CreateRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public CreateRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CreateRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CreateRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CreateRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CreateRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CreateRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public CreateRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CreateRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CreateRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        @Deprecated
        public CreateRequest cardGateway(com.chargebee.models.enums.Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public CreateRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
            return this;
        }

        @Deprecated
        public CreateRequest cardTmpToken(String cardTmpToken) {
            params.addOpt("card[tmp_token]", cardTmpToken);
            return this;
        }

        public CreateRequest bankAccountGatewayAccountId(String bankAccountGatewayAccountId) {
            params.addOpt("bank_account[gateway_account_id]", bankAccountGatewayAccountId);
            return this;
        }

        public CreateRequest bankAccountIban(String bankAccountIban) {
            params.addOpt("bank_account[iban]", bankAccountIban);
            return this;
        }

        public CreateRequest bankAccountFirstName(String bankAccountFirstName) {
            params.addOpt("bank_account[first_name]", bankAccountFirstName);
            return this;
        }

        public CreateRequest bankAccountLastName(String bankAccountLastName) {
            params.addOpt("bank_account[last_name]", bankAccountLastName);
            return this;
        }

        public CreateRequest bankAccountCompany(String bankAccountCompany) {
            params.addOpt("bank_account[company]", bankAccountCompany);
            return this;
        }

        public CreateRequest bankAccountEmail(String bankAccountEmail) {
            params.addOpt("bank_account[email]", bankAccountEmail);
            return this;
        }

        public CreateRequest bankAccountPhone(String bankAccountPhone) {
            params.addOpt("bank_account[phone]", bankAccountPhone);
            return this;
        }

        public CreateRequest bankAccountBankName(String bankAccountBankName) {
            params.addOpt("bank_account[bank_name]", bankAccountBankName);
            return this;
        }

        public CreateRequest bankAccountAccountNumber(String bankAccountAccountNumber) {
            params.addOpt("bank_account[account_number]", bankAccountAccountNumber);
            return this;
        }

        public CreateRequest bankAccountRoutingNumber(String bankAccountRoutingNumber) {
            params.addOpt("bank_account[routing_number]", bankAccountRoutingNumber);
            return this;
        }

        public CreateRequest bankAccountBankCode(String bankAccountBankCode) {
            params.addOpt("bank_account[bank_code]", bankAccountBankCode);
            return this;
        }

        public CreateRequest bankAccountAccountType(com.chargebee.models.enums.AccountType bankAccountAccountType) {
            params.addOpt("bank_account[account_type]", bankAccountAccountType);
            return this;
        }

        public CreateRequest bankAccountAccountHolderType(com.chargebee.models.enums.AccountHolderType bankAccountAccountHolderType) {
            params.addOpt("bank_account[account_holder_type]", bankAccountAccountHolderType);
            return this;
        }

        public CreateRequest bankAccountEcheckType(com.chargebee.models.enums.EcheckType bankAccountEcheckType) {
            params.addOpt("bank_account[echeck_type]", bankAccountEcheckType);
            return this;
        }

        public CreateRequest bankAccountIssuingCountry(String bankAccountIssuingCountry) {
            params.addOpt("bank_account[issuing_country]", bankAccountIssuingCountry);
            return this;
        }

        public CreateRequest bankAccountSwedishIdentityNumber(String bankAccountSwedishIdentityNumber) {
            params.addOpt("bank_account[swedish_identity_number]", bankAccountSwedishIdentityNumber);
            return this;
        }

        public CreateRequest bankAccountBillingAddress(JSONObject bankAccountBillingAddress) {
            params.addOpt("bank_account[billing_address]", bankAccountBillingAddress);
            return this;
        }

        public CreateRequest paymentMethodType(com.chargebee.models.enums.Type paymentMethodType) {
            params.addOpt("payment_method[type]", paymentMethodType);
            return this;
        }

        @Deprecated
        public CreateRequest paymentMethodGateway(com.chargebee.models.enums.Gateway paymentMethodGateway) {
            params.addOpt("payment_method[gateway]", paymentMethodGateway);
            return this;
        }

        public CreateRequest paymentMethodGatewayAccountId(String paymentMethodGatewayAccountId) {
            params.addOpt("payment_method[gateway_account_id]", paymentMethodGatewayAccountId);
            return this;
        }

        public CreateRequest paymentMethodReferenceId(String paymentMethodReferenceId) {
            params.addOpt("payment_method[reference_id]", paymentMethodReferenceId);
            return this;
        }

        public CreateRequest paymentMethodTmpToken(String paymentMethodTmpToken) {
            params.addOpt("payment_method[tmp_token]", paymentMethodTmpToken);
            return this;
        }

        public CreateRequest paymentMethodIssuingCountry(String paymentMethodIssuingCountry) {
            params.addOpt("payment_method[issuing_country]", paymentMethodIssuingCountry);
            return this;
        }

        public CreateRequest paymentMethodAdditionalInformation(JSONObject paymentMethodAdditionalInformation) {
            params.addOpt("payment_method[additional_information]", paymentMethodAdditionalInformation);
            return this;
        }

        public CreateRequest cardFirstName(String cardFirstName) {
            params.addOpt("card[first_name]", cardFirstName);
            return this;
        }

        public CreateRequest cardLastName(String cardLastName) {
            params.addOpt("card[last_name]", cardLastName);
            return this;
        }

        public CreateRequest cardNumber(String cardNumber) {
            params.addOpt("card[number]", cardNumber);
            return this;
        }

        public CreateRequest cardExpiryMonth(Integer cardExpiryMonth) {
            params.addOpt("card[expiry_month]", cardExpiryMonth);
            return this;
        }

        public CreateRequest cardExpiryYear(Integer cardExpiryYear) {
            params.addOpt("card[expiry_year]", cardExpiryYear);
            return this;
        }

        public CreateRequest cardCvv(String cardCvv) {
            params.addOpt("card[cvv]", cardCvv);
            return this;
        }

        public CreateRequest cardBillingAddr1(String cardBillingAddr1) {
            params.addOpt("card[billing_addr1]", cardBillingAddr1);
            return this;
        }

        public CreateRequest cardBillingAddr2(String cardBillingAddr2) {
            params.addOpt("card[billing_addr2]", cardBillingAddr2);
            return this;
        }

        public CreateRequest cardBillingCity(String cardBillingCity) {
            params.addOpt("card[billing_city]", cardBillingCity);
            return this;
        }

        public CreateRequest cardBillingStateCode(String cardBillingStateCode) {
            params.addOpt("card[billing_state_code]", cardBillingStateCode);
            return this;
        }

        public CreateRequest cardBillingState(String cardBillingState) {
            params.addOpt("card[billing_state]", cardBillingState);
            return this;
        }

        public CreateRequest cardBillingZip(String cardBillingZip) {
            params.addOpt("card[billing_zip]", cardBillingZip);
            return this;
        }

        public CreateRequest cardBillingCountry(String cardBillingCountry) {
            params.addOpt("card[billing_country]", cardBillingCountry);
            return this;
        }

        @Deprecated
        public CreateRequest cardIpAddress(String cardIpAddress) {
            params.addOpt("card[ip_address]", cardIpAddress);
            return this;
        }

        public CreateRequest cardAdditionalInformation(JSONObject cardAdditionalInformation) {
            params.addOpt("card[additional_information]", cardAdditionalInformation);
            return this;
        }

        public CreateRequest paymentIntentId(String paymentIntentId) {
            params.addOpt("payment_intent[id]", paymentIntentId);
            return this;
        }

        public CreateRequest paymentIntentGatewayAccountId(String paymentIntentGatewayAccountId) {
            params.addOpt("payment_intent[gateway_account_id]", paymentIntentGatewayAccountId);
            return this;
        }

        public CreateRequest paymentIntentGwToken(String paymentIntentGwToken) {
            params.addOpt("payment_intent[gw_token]", paymentIntentGwToken);
            return this;
        }

        public CreateRequest paymentIntentPaymentMethodType(PaymentIntent.PaymentMethodType paymentIntentPaymentMethodType) {
            params.addOpt("payment_intent[payment_method_type]", paymentIntentPaymentMethodType);
            return this;
        }

        public CreateRequest paymentIntentReferenceId(String paymentIntentReferenceId) {
            params.addOpt("payment_intent[reference_id]", paymentIntentReferenceId);
            return this;
        }

        @Deprecated
        public CreateRequest paymentIntentGwPaymentMethodId(String paymentIntentGwPaymentMethodId) {
            params.addOpt("payment_intent[gw_payment_method_id]", paymentIntentGwPaymentMethodId);
            return this;
        }

        public CreateRequest paymentIntentAdditionalInformation(JSONObject paymentIntentAdditionalInformation) {
            params.addOpt("payment_intent[additional_information]", paymentIntentAdditionalInformation);
            return this;
        }

        public CreateRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public CreateRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public CreateRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public CreateRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public CreateRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public CreateRequest addonDateFrom(int index, Timestamp addonDateFrom) {
            params.addOpt("addons[date_from][" + index + "]", addonDateFrom);
            return this;
        }
        public CreateRequest addonDateTo(int index, Timestamp addonDateTo) {
            params.addOpt("addons[date_to][" + index + "]", addonDateTo);
            return this;
        }
        public CreateRequest chargeAmount(int index, Long chargeAmount) {
            params.addOpt("charges[amount][" + index + "]", chargeAmount);
            return this;
        }
        public CreateRequest chargeAmountInDecimal(int index, String chargeAmountInDecimal) {
            params.addOpt("charges[amount_in_decimal][" + index + "]", chargeAmountInDecimal);
            return this;
        }
        public CreateRequest chargeDescription(int index, String chargeDescription) {
            params.addOpt("charges[description][" + index + "]", chargeDescription);
            return this;
        }
        public CreateRequest chargeTaxable(int index, Boolean chargeTaxable) {
            params.addOpt("charges[taxable][" + index + "]", chargeTaxable);
            return this;
        }
        public CreateRequest chargeTaxProfileId(int index, String chargeTaxProfileId) {
            params.addOpt("charges[tax_profile_id][" + index + "]", chargeTaxProfileId);
            return this;
        }
        public CreateRequest chargeAvalaraTaxCode(int index, String chargeAvalaraTaxCode) {
            params.addOpt("charges[avalara_tax_code][" + index + "]", chargeAvalaraTaxCode);
            return this;
        }
        public CreateRequest chargeHsnCode(int index, String chargeHsnCode) {
            params.addOpt("charges[hsn_code][" + index + "]", chargeHsnCode);
            return this;
        }
        public CreateRequest chargeTaxjarProductCode(int index, String chargeTaxjarProductCode) {
            params.addOpt("charges[taxjar_product_code][" + index + "]", chargeTaxjarProductCode);
            return this;
        }
        public CreateRequest chargeAvalaraSaleType(int index, com.chargebee.models.enums.AvalaraSaleType chargeAvalaraSaleType) {
            params.addOpt("charges[avalara_sale_type][" + index + "]", chargeAvalaraSaleType);
            return this;
        }
        public CreateRequest chargeAvalaraTransactionType(int index, Integer chargeAvalaraTransactionType) {
            params.addOpt("charges[avalara_transaction_type][" + index + "]", chargeAvalaraTransactionType);
            return this;
        }
        public CreateRequest chargeAvalaraServiceType(int index, Integer chargeAvalaraServiceType) {
            params.addOpt("charges[avalara_service_type][" + index + "]", chargeAvalaraServiceType);
            return this;
        }
        public CreateRequest chargeDateFrom(int index, Timestamp chargeDateFrom) {
            params.addOpt("charges[date_from][" + index + "]", chargeDateFrom);
            return this;
        }
        public CreateRequest chargeDateTo(int index, Timestamp chargeDateTo) {
            params.addOpt("charges[date_to][" + index + "]", chargeDateTo);
            return this;
        }
        public CreateRequest notesToRemoveEntityType(int index, com.chargebee.models.enums.EntityType notesToRemoveEntityType) {
            params.addOpt("notes_to_remove[entity_type][" + index + "]", notesToRemoveEntityType);
            return this;
        }
        public CreateRequest notesToRemoveEntityId(int index, String notesToRemoveEntityId) {
            params.addOpt("notes_to_remove[entity_id][" + index + "]", notesToRemoveEntityId);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateForChargeItemsAndChargesRequest extends Request<CreateForChargeItemsAndChargesRequest> {

        private CreateForChargeItemsAndChargesRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateForChargeItemsAndChargesRequest customerId(String customerId) {
            params.addOpt("customer_id", customerId);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest invoiceNote(String invoiceNote) {
            params.addOpt("invoice_note", invoiceNote);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest removeGeneralNote(Boolean removeGeneralNote) {
            params.addOpt("remove_general_note", removeGeneralNote);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        @Deprecated
        public CreateForChargeItemsAndChargesRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest authorizationTransactionId(String authorizationTransactionId) {
            params.addOpt("authorization_transaction_id", authorizationTransactionId);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest paymentSourceId(String paymentSourceId) {
            params.addOpt("payment_source_id", paymentSourceId);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest autoCollection(com.chargebee.models.enums.AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest tokenId(String tokenId) {
            params.addOpt("token_id", tokenId);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest replacePrimaryPaymentSource(Boolean replacePrimaryPaymentSource) {
            params.addOpt("replace_primary_payment_source", replacePrimaryPaymentSource);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest retainPaymentSource(Boolean retainPaymentSource) {
            params.addOpt("retain_payment_source", retainPaymentSource);
            return this;
        }






        public CreateForChargeItemsAndChargesRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        @Deprecated
        public CreateForChargeItemsAndChargesRequest cardGateway(com.chargebee.models.enums.Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
            return this;
        }

        @Deprecated
        public CreateForChargeItemsAndChargesRequest cardTmpToken(String cardTmpToken) {
            params.addOpt("card[tmp_token]", cardTmpToken);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountGatewayAccountId(String bankAccountGatewayAccountId) {
            params.addOpt("bank_account[gateway_account_id]", bankAccountGatewayAccountId);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountIban(String bankAccountIban) {
            params.addOpt("bank_account[iban]", bankAccountIban);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountFirstName(String bankAccountFirstName) {
            params.addOpt("bank_account[first_name]", bankAccountFirstName);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountLastName(String bankAccountLastName) {
            params.addOpt("bank_account[last_name]", bankAccountLastName);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountCompany(String bankAccountCompany) {
            params.addOpt("bank_account[company]", bankAccountCompany);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountEmail(String bankAccountEmail) {
            params.addOpt("bank_account[email]", bankAccountEmail);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountPhone(String bankAccountPhone) {
            params.addOpt("bank_account[phone]", bankAccountPhone);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountBankName(String bankAccountBankName) {
            params.addOpt("bank_account[bank_name]", bankAccountBankName);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountAccountNumber(String bankAccountAccountNumber) {
            params.addOpt("bank_account[account_number]", bankAccountAccountNumber);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountRoutingNumber(String bankAccountRoutingNumber) {
            params.addOpt("bank_account[routing_number]", bankAccountRoutingNumber);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountBankCode(String bankAccountBankCode) {
            params.addOpt("bank_account[bank_code]", bankAccountBankCode);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountAccountType(com.chargebee.models.enums.AccountType bankAccountAccountType) {
            params.addOpt("bank_account[account_type]", bankAccountAccountType);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountAccountHolderType(com.chargebee.models.enums.AccountHolderType bankAccountAccountHolderType) {
            params.addOpt("bank_account[account_holder_type]", bankAccountAccountHolderType);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountEcheckType(com.chargebee.models.enums.EcheckType bankAccountEcheckType) {
            params.addOpt("bank_account[echeck_type]", bankAccountEcheckType);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountIssuingCountry(String bankAccountIssuingCountry) {
            params.addOpt("bank_account[issuing_country]", bankAccountIssuingCountry);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountSwedishIdentityNumber(String bankAccountSwedishIdentityNumber) {
            params.addOpt("bank_account[swedish_identity_number]", bankAccountSwedishIdentityNumber);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest bankAccountBillingAddress(JSONObject bankAccountBillingAddress) {
            params.addOpt("bank_account[billing_address]", bankAccountBillingAddress);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest paymentMethodType(com.chargebee.models.enums.Type paymentMethodType) {
            params.addOpt("payment_method[type]", paymentMethodType);
            return this;
        }

        @Deprecated
        public CreateForChargeItemsAndChargesRequest paymentMethodGateway(com.chargebee.models.enums.Gateway paymentMethodGateway) {
            params.addOpt("payment_method[gateway]", paymentMethodGateway);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest paymentMethodGatewayAccountId(String paymentMethodGatewayAccountId) {
            params.addOpt("payment_method[gateway_account_id]", paymentMethodGatewayAccountId);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest paymentMethodReferenceId(String paymentMethodReferenceId) {
            params.addOpt("payment_method[reference_id]", paymentMethodReferenceId);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest paymentMethodTmpToken(String paymentMethodTmpToken) {
            params.addOpt("payment_method[tmp_token]", paymentMethodTmpToken);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest paymentMethodIssuingCountry(String paymentMethodIssuingCountry) {
            params.addOpt("payment_method[issuing_country]", paymentMethodIssuingCountry);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest paymentMethodAdditionalInformation(JSONObject paymentMethodAdditionalInformation) {
            params.addOpt("payment_method[additional_information]", paymentMethodAdditionalInformation);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest cardFirstName(String cardFirstName) {
            params.addOpt("card[first_name]", cardFirstName);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest cardLastName(String cardLastName) {
            params.addOpt("card[last_name]", cardLastName);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest cardNumber(String cardNumber) {
            params.addOpt("card[number]", cardNumber);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest cardExpiryMonth(Integer cardExpiryMonth) {
            params.addOpt("card[expiry_month]", cardExpiryMonth);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest cardExpiryYear(Integer cardExpiryYear) {
            params.addOpt("card[expiry_year]", cardExpiryYear);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest cardCvv(String cardCvv) {
            params.addOpt("card[cvv]", cardCvv);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest cardBillingAddr1(String cardBillingAddr1) {
            params.addOpt("card[billing_addr1]", cardBillingAddr1);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest cardBillingAddr2(String cardBillingAddr2) {
            params.addOpt("card[billing_addr2]", cardBillingAddr2);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest cardBillingCity(String cardBillingCity) {
            params.addOpt("card[billing_city]", cardBillingCity);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest cardBillingStateCode(String cardBillingStateCode) {
            params.addOpt("card[billing_state_code]", cardBillingStateCode);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest cardBillingState(String cardBillingState) {
            params.addOpt("card[billing_state]", cardBillingState);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest cardBillingZip(String cardBillingZip) {
            params.addOpt("card[billing_zip]", cardBillingZip);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest cardBillingCountry(String cardBillingCountry) {
            params.addOpt("card[billing_country]", cardBillingCountry);
            return this;
        }

        @Deprecated
        public CreateForChargeItemsAndChargesRequest cardIpAddress(String cardIpAddress) {
            params.addOpt("card[ip_address]", cardIpAddress);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest cardAdditionalInformation(JSONObject cardAdditionalInformation) {
            params.addOpt("card[additional_information]", cardAdditionalInformation);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest paymentIntentId(String paymentIntentId) {
            params.addOpt("payment_intent[id]", paymentIntentId);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest paymentIntentGatewayAccountId(String paymentIntentGatewayAccountId) {
            params.addOpt("payment_intent[gateway_account_id]", paymentIntentGatewayAccountId);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest paymentIntentGwToken(String paymentIntentGwToken) {
            params.addOpt("payment_intent[gw_token]", paymentIntentGwToken);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest paymentIntentPaymentMethodType(PaymentIntent.PaymentMethodType paymentIntentPaymentMethodType) {
            params.addOpt("payment_intent[payment_method_type]", paymentIntentPaymentMethodType);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest paymentIntentReferenceId(String paymentIntentReferenceId) {
            params.addOpt("payment_intent[reference_id]", paymentIntentReferenceId);
            return this;
        }

        @Deprecated
        public CreateForChargeItemsAndChargesRequest paymentIntentGwPaymentMethodId(String paymentIntentGwPaymentMethodId) {
            params.addOpt("payment_intent[gw_payment_method_id]", paymentIntentGwPaymentMethodId);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest paymentIntentAdditionalInformation(JSONObject paymentIntentAdditionalInformation) {
            params.addOpt("payment_intent[additional_information]", paymentIntentAdditionalInformation);
            return this;
        }

        public CreateForChargeItemsAndChargesRequest itemPriceItemPriceId(int index, String itemPriceItemPriceId) {
            params.addOpt("item_prices[item_price_id][" + index + "]", itemPriceItemPriceId);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest itemPriceQuantity(int index, Integer itemPriceQuantity) {
            params.addOpt("item_prices[quantity][" + index + "]", itemPriceQuantity);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest itemPriceQuantityInDecimal(int index, String itemPriceQuantityInDecimal) {
            params.addOpt("item_prices[quantity_in_decimal][" + index + "]", itemPriceQuantityInDecimal);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest itemPriceUnitPrice(int index, Long itemPriceUnitPrice) {
            params.addOpt("item_prices[unit_price][" + index + "]", itemPriceUnitPrice);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest itemPriceUnitPriceInDecimal(int index, String itemPriceUnitPriceInDecimal) {
            params.addOpt("item_prices[unit_price_in_decimal][" + index + "]", itemPriceUnitPriceInDecimal);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest itemPriceDateFrom(int index, Timestamp itemPriceDateFrom) {
            params.addOpt("item_prices[date_from][" + index + "]", itemPriceDateFrom);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest itemPriceDateTo(int index, Timestamp itemPriceDateTo) {
            params.addOpt("item_prices[date_to][" + index + "]", itemPriceDateTo);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest chargeAmount(int index, Long chargeAmount) {
            params.addOpt("charges[amount][" + index + "]", chargeAmount);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest chargeAmountInDecimal(int index, String chargeAmountInDecimal) {
            params.addOpt("charges[amount_in_decimal][" + index + "]", chargeAmountInDecimal);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest chargeDescription(int index, String chargeDescription) {
            params.addOpt("charges[description][" + index + "]", chargeDescription);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest chargeTaxable(int index, Boolean chargeTaxable) {
            params.addOpt("charges[taxable][" + index + "]", chargeTaxable);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest chargeTaxProfileId(int index, String chargeTaxProfileId) {
            params.addOpt("charges[tax_profile_id][" + index + "]", chargeTaxProfileId);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest chargeAvalaraTaxCode(int index, String chargeAvalaraTaxCode) {
            params.addOpt("charges[avalara_tax_code][" + index + "]", chargeAvalaraTaxCode);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest chargeHsnCode(int index, String chargeHsnCode) {
            params.addOpt("charges[hsn_code][" + index + "]", chargeHsnCode);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest chargeTaxjarProductCode(int index, String chargeTaxjarProductCode) {
            params.addOpt("charges[taxjar_product_code][" + index + "]", chargeTaxjarProductCode);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest chargeAvalaraSaleType(int index, com.chargebee.models.enums.AvalaraSaleType chargeAvalaraSaleType) {
            params.addOpt("charges[avalara_sale_type][" + index + "]", chargeAvalaraSaleType);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest chargeAvalaraTransactionType(int index, Integer chargeAvalaraTransactionType) {
            params.addOpt("charges[avalara_transaction_type][" + index + "]", chargeAvalaraTransactionType);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest chargeAvalaraServiceType(int index, Integer chargeAvalaraServiceType) {
            params.addOpt("charges[avalara_service_type][" + index + "]", chargeAvalaraServiceType);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest chargeDateFrom(int index, Timestamp chargeDateFrom) {
            params.addOpt("charges[date_from][" + index + "]", chargeDateFrom);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest chargeDateTo(int index, Timestamp chargeDateTo) {
            params.addOpt("charges[date_to][" + index + "]", chargeDateTo);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest notesToRemoveEntityType(int index, com.chargebee.models.enums.EntityType notesToRemoveEntityType) {
            params.addOpt("notes_to_remove[entity_type][" + index + "]", notesToRemoveEntityType);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest notesToRemoveEntityId(int index, String notesToRemoveEntityId) {
            params.addOpt("notes_to_remove[entity_id][" + index + "]", notesToRemoveEntityId);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.add("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public CreateForChargeItemsAndChargesRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class ChargeRequest extends Request<ChargeRequest> {

        private ChargeRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ChargeRequest customerId(String customerId) {
            params.addOpt("customer_id", customerId);
            return this;
        }


        public ChargeRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public ChargeRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public ChargeRequest amount(Long amount) {
            params.addOpt("amount", amount);
            return this;
        }


        public ChargeRequest amountInDecimal(String amountInDecimal) {
            params.addOpt("amount_in_decimal", amountInDecimal);
            return this;
        }


        public ChargeRequest description(String description) {
            params.add("description", description);
            return this;
        }


        public ChargeRequest dateFrom(Timestamp dateFrom) {
            params.addOpt("date_from", dateFrom);
            return this;
        }


        public ChargeRequest dateTo(Timestamp dateTo) {
            params.addOpt("date_to", dateTo);
            return this;
        }


        public ChargeRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public ChargeRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        @Deprecated
        public ChargeRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public ChargeRequest avalaraSaleType(com.chargebee.models.enums.AvalaraSaleType avalaraSaleType) {
            params.addOpt("avalara_sale_type", avalaraSaleType);
            return this;
        }


        public ChargeRequest avalaraTransactionType(Integer avalaraTransactionType) {
            params.addOpt("avalara_transaction_type", avalaraTransactionType);
            return this;
        }


        public ChargeRequest avalaraServiceType(Integer avalaraServiceType) {
            params.addOpt("avalara_service_type", avalaraServiceType);
            return this;
        }


        public ChargeRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public ChargeRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public ChargeRequest paymentSourceId(String paymentSourceId) {
            params.addOpt("payment_source_id", paymentSourceId);
            return this;
        }




        @Override
        public Params params() {
            return params;
        }
    }

    public static class ChargeAddonRequest extends Request<ChargeAddonRequest> {

        private ChargeAddonRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ChargeAddonRequest customerId(String customerId) {
            params.addOpt("customer_id", customerId);
            return this;
        }


        public ChargeAddonRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public ChargeAddonRequest addonId(String addonId) {
            params.add("addon_id", addonId);
            return this;
        }


        public ChargeAddonRequest addonQuantity(Integer addonQuantity) {
            params.addOpt("addon_quantity", addonQuantity);
            return this;
        }


        public ChargeAddonRequest addonUnitPrice(Long addonUnitPrice) {
            params.addOpt("addon_unit_price", addonUnitPrice);
            return this;
        }


        public ChargeAddonRequest addonQuantityInDecimal(String addonQuantityInDecimal) {
            params.addOpt("addon_quantity_in_decimal", addonQuantityInDecimal);
            return this;
        }


        public ChargeAddonRequest addonUnitPriceInDecimal(String addonUnitPriceInDecimal) {
            params.addOpt("addon_unit_price_in_decimal", addonUnitPriceInDecimal);
            return this;
        }


        public ChargeAddonRequest dateFrom(Timestamp dateFrom) {
            params.addOpt("date_from", dateFrom);
            return this;
        }


        public ChargeAddonRequest dateTo(Timestamp dateTo) {
            params.addOpt("date_to", dateTo);
            return this;
        }


        public ChargeAddonRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public ChargeAddonRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        @Deprecated
        public ChargeAddonRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public ChargeAddonRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public ChargeAddonRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public ChargeAddonRequest paymentSourceId(String paymentSourceId) {
            params.addOpt("payment_source_id", paymentSourceId);
            return this;
        }




        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateForChargeItemRequest extends Request<CreateForChargeItemRequest> {

        private CreateForChargeItemRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateForChargeItemRequest customerId(String customerId) {
            params.addOpt("customer_id", customerId);
            return this;
        }


        public CreateForChargeItemRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public CreateForChargeItemRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public CreateForChargeItemRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public CreateForChargeItemRequest paymentSourceId(String paymentSourceId) {
            params.addOpt("payment_source_id", paymentSourceId);
            return this;
        }


        public CreateForChargeItemRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }




        public CreateForChargeItemRequest itemPriceItemPriceId(String itemPriceItemPriceId) {
            params.add("item_price[item_price_id]", itemPriceItemPriceId);
            return this;
        }

        public CreateForChargeItemRequest itemPriceQuantity(Integer itemPriceQuantity) {
            params.addOpt("item_price[quantity]", itemPriceQuantity);
            return this;
        }

        public CreateForChargeItemRequest itemPriceQuantityInDecimal(String itemPriceQuantityInDecimal) {
            params.addOpt("item_price[quantity_in_decimal]", itemPriceQuantityInDecimal);
            return this;
        }

        public CreateForChargeItemRequest itemPriceUnitPrice(Long itemPriceUnitPrice) {
            params.addOpt("item_price[unit_price]", itemPriceUnitPrice);
            return this;
        }

        public CreateForChargeItemRequest itemPriceUnitPriceInDecimal(String itemPriceUnitPriceInDecimal) {
            params.addOpt("item_price[unit_price_in_decimal]", itemPriceUnitPriceInDecimal);
            return this;
        }

        public CreateForChargeItemRequest itemPriceDateFrom(Timestamp itemPriceDateFrom) {
            params.addOpt("item_price[date_from]", itemPriceDateFrom);
            return this;
        }

        public CreateForChargeItemRequest itemPriceDateTo(Timestamp itemPriceDateTo) {
            params.addOpt("item_price[date_to]", itemPriceDateTo);
            return this;
        }

        public CreateForChargeItemRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public CreateForChargeItemRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public CreateForChargeItemRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public CreateForChargeItemRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public CreateForChargeItemRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public CreateForChargeItemRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class StopDunningRequest extends Request<StopDunningRequest> {

        private StopDunningRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public StopDunningRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class ImportInvoiceRequest extends Request<ImportInvoiceRequest> {

        private ImportInvoiceRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ImportInvoiceRequest id(String id) {
            params.add("id", id);
            return this;
        }


        public ImportInvoiceRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public ImportInvoiceRequest customerId(String customerId) {
            params.addOpt("customer_id", customerId);
            return this;
        }


        public ImportInvoiceRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public ImportInvoiceRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public ImportInvoiceRequest priceType(com.chargebee.models.enums.PriceType priceType) {
            params.addOpt("price_type", priceType);
            return this;
        }


        public ImportInvoiceRequest taxOverrideReason(com.chargebee.models.enums.TaxOverrideReason taxOverrideReason) {
            params.addOpt("tax_override_reason", taxOverrideReason);
            return this;
        }


        public ImportInvoiceRequest vatNumber(String vatNumber) {
            params.addOpt("vat_number", vatNumber);
            return this;
        }


        public ImportInvoiceRequest vatNumberPrefix(String vatNumberPrefix) {
            params.addOpt("vat_number_prefix", vatNumberPrefix);
            return this;
        }


        public ImportInvoiceRequest date(Timestamp date) {
            params.add("date", date);
            return this;
        }


        public ImportInvoiceRequest total(Long total) {
            params.add("total", total);
            return this;
        }


        public ImportInvoiceRequest roundOff(Long roundOff) {
            params.addOpt("round_off", roundOff);
            return this;
        }


        public ImportInvoiceRequest status(Invoice.Status status) {
            params.addOpt("status", status);
            return this;
        }


        public ImportInvoiceRequest voidedAt(Timestamp voidedAt) {
            params.addOpt("voided_at", voidedAt);
            return this;
        }


        public ImportInvoiceRequest voidReasonCode(String voidReasonCode) {
            params.addOpt("void_reason_code", voidReasonCode);
            return this;
        }


        public ImportInvoiceRequest isWrittenOff(Boolean isWrittenOff) {
            params.addOpt("is_written_off", isWrittenOff);
            return this;
        }


        public ImportInvoiceRequest writeOffAmount(Long writeOffAmount) {
            params.addOpt("write_off_amount", writeOffAmount);
            return this;
        }


        public ImportInvoiceRequest writeOffDate(Timestamp writeOffDate) {
            params.addOpt("write_off_date", writeOffDate);
            return this;
        }


        public ImportInvoiceRequest dueDate(Timestamp dueDate) {
            params.addOpt("due_date", dueDate);
            return this;
        }


        public ImportInvoiceRequest netTermDays(Integer netTermDays) {
            params.addOpt("net_term_days", netTermDays);
            return this;
        }


        public ImportInvoiceRequest hasAdvanceCharges(Boolean hasAdvanceCharges) {
            params.addOpt("has_advance_charges", hasAdvanceCharges);
            return this;
        }


        public ImportInvoiceRequest useForProration(Boolean useForProration) {
            params.addOpt("use_for_proration", useForProration);
            return this;
        }




        public ImportInvoiceRequest creditNoteId(String creditNoteId) {
            params.addOpt("credit_note[id]", creditNoteId);
            return this;
        }

        public ImportInvoiceRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }

        public ImportInvoiceRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }

        public ImportInvoiceRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }

        public ImportInvoiceRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }

        public ImportInvoiceRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }

        public ImportInvoiceRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public ImportInvoiceRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public ImportInvoiceRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public ImportInvoiceRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public ImportInvoiceRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public ImportInvoiceRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }

        public ImportInvoiceRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public ImportInvoiceRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public ImportInvoiceRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public ImportInvoiceRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public ImportInvoiceRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public ImportInvoiceRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public ImportInvoiceRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public ImportInvoiceRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public ImportInvoiceRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public ImportInvoiceRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public ImportInvoiceRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public ImportInvoiceRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public ImportInvoiceRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public ImportInvoiceRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public ImportInvoiceRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public ImportInvoiceRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public ImportInvoiceRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public ImportInvoiceRequest lineItemId(int index, String lineItemId) {
            params.addOpt("line_items[id][" + index + "]", lineItemId);
            return this;
        }
        public ImportInvoiceRequest lineItemDateFrom(int index, Timestamp lineItemDateFrom) {
            params.addOpt("line_items[date_from][" + index + "]", lineItemDateFrom);
            return this;
        }
        public ImportInvoiceRequest lineItemDateTo(int index, Timestamp lineItemDateTo) {
            params.addOpt("line_items[date_to][" + index + "]", lineItemDateTo);
            return this;
        }
        public ImportInvoiceRequest lineItemSubscriptionId(int index, String lineItemSubscriptionId) {
            params.addOpt("line_items[subscription_id][" + index + "]", lineItemSubscriptionId);
            return this;
        }
        public ImportInvoiceRequest lineItemDescription(int index, String lineItemDescription) {
            params.add("line_items[description][" + index + "]", lineItemDescription);
            return this;
        }
        public ImportInvoiceRequest lineItemUnitAmount(int index, Long lineItemUnitAmount) {
            params.addOpt("line_items[unit_amount][" + index + "]", lineItemUnitAmount);
            return this;
        }
        public ImportInvoiceRequest lineItemQuantity(int index, Integer lineItemQuantity) {
            params.addOpt("line_items[quantity][" + index + "]", lineItemQuantity);
            return this;
        }
        public ImportInvoiceRequest lineItemAmount(int index, Long lineItemAmount) {
            params.addOpt("line_items[amount][" + index + "]", lineItemAmount);
            return this;
        }
        public ImportInvoiceRequest lineItemUnitAmountInDecimal(int index, String lineItemUnitAmountInDecimal) {
            params.addOpt("line_items[unit_amount_in_decimal][" + index + "]", lineItemUnitAmountInDecimal);
            return this;
        }
        public ImportInvoiceRequest lineItemQuantityInDecimal(int index, String lineItemQuantityInDecimal) {
            params.addOpt("line_items[quantity_in_decimal][" + index + "]", lineItemQuantityInDecimal);
            return this;
        }
        public ImportInvoiceRequest lineItemAmountInDecimal(int index, String lineItemAmountInDecimal) {
            params.addOpt("line_items[amount_in_decimal][" + index + "]", lineItemAmountInDecimal);
            return this;
        }
        public ImportInvoiceRequest lineItemEntityType(int index, LineItem.EntityType lineItemEntityType) {
            params.addOpt("line_items[entity_type][" + index + "]", lineItemEntityType);
            return this;
        }
        public ImportInvoiceRequest lineItemEntityId(int index, String lineItemEntityId) {
            params.addOpt("line_items[entity_id][" + index + "]", lineItemEntityId);
            return this;
        }
        public ImportInvoiceRequest lineItemItemLevelDiscount1EntityId(int index, String lineItemItemLevelDiscount1EntityId) {
            params.addOpt("line_items[item_level_discount1_entity_id][" + index + "]", lineItemItemLevelDiscount1EntityId);
            return this;
        }
        public ImportInvoiceRequest lineItemItemLevelDiscount1Amount(int index, Long lineItemItemLevelDiscount1Amount) {
            params.addOpt("line_items[item_level_discount1_amount][" + index + "]", lineItemItemLevelDiscount1Amount);
            return this;
        }
        public ImportInvoiceRequest lineItemItemLevelDiscount2EntityId(int index, String lineItemItemLevelDiscount2EntityId) {
            params.addOpt("line_items[item_level_discount2_entity_id][" + index + "]", lineItemItemLevelDiscount2EntityId);
            return this;
        }
        public ImportInvoiceRequest lineItemItemLevelDiscount2Amount(int index, Long lineItemItemLevelDiscount2Amount) {
            params.addOpt("line_items[item_level_discount2_amount][" + index + "]", lineItemItemLevelDiscount2Amount);
            return this;
        }
        public ImportInvoiceRequest lineItemTax1Name(int index, String lineItemTax1Name) {
            params.addOpt("line_items[tax1_name][" + index + "]", lineItemTax1Name);
            return this;
        }
        public ImportInvoiceRequest lineItemTax1Amount(int index, Long lineItemTax1Amount) {
            params.addOpt("line_items[tax1_amount][" + index + "]", lineItemTax1Amount);
            return this;
        }
        public ImportInvoiceRequest lineItemTax2Name(int index, String lineItemTax2Name) {
            params.addOpt("line_items[tax2_name][" + index + "]", lineItemTax2Name);
            return this;
        }
        public ImportInvoiceRequest lineItemTax2Amount(int index, Long lineItemTax2Amount) {
            params.addOpt("line_items[tax2_amount][" + index + "]", lineItemTax2Amount);
            return this;
        }
        public ImportInvoiceRequest lineItemTax3Name(int index, String lineItemTax3Name) {
            params.addOpt("line_items[tax3_name][" + index + "]", lineItemTax3Name);
            return this;
        }
        public ImportInvoiceRequest lineItemTax3Amount(int index, Long lineItemTax3Amount) {
            params.addOpt("line_items[tax3_amount][" + index + "]", lineItemTax3Amount);
            return this;
        }
        public ImportInvoiceRequest lineItemTax4Name(int index, String lineItemTax4Name) {
            params.addOpt("line_items[tax4_name][" + index + "]", lineItemTax4Name);
            return this;
        }
        public ImportInvoiceRequest lineItemTax4Amount(int index, Long lineItemTax4Amount) {
            params.addOpt("line_items[tax4_amount][" + index + "]", lineItemTax4Amount);
            return this;
        }
        public ImportInvoiceRequest lineItemTax5Name(int index, String lineItemTax5Name) {
            params.addOpt("line_items[tax5_name][" + index + "]", lineItemTax5Name);
            return this;
        }
        public ImportInvoiceRequest lineItemTax5Amount(int index, Long lineItemTax5Amount) {
            params.addOpt("line_items[tax5_amount][" + index + "]", lineItemTax5Amount);
            return this;
        }
        public ImportInvoiceRequest lineItemTax6Name(int index, String lineItemTax6Name) {
            params.addOpt("line_items[tax6_name][" + index + "]", lineItemTax6Name);
            return this;
        }
        public ImportInvoiceRequest lineItemTax6Amount(int index, Long lineItemTax6Amount) {
            params.addOpt("line_items[tax6_amount][" + index + "]", lineItemTax6Amount);
            return this;
        }
        public ImportInvoiceRequest lineItemTax7Name(int index, String lineItemTax7Name) {
            params.addOpt("line_items[tax7_name][" + index + "]", lineItemTax7Name);
            return this;
        }
        public ImportInvoiceRequest lineItemTax7Amount(int index, Long lineItemTax7Amount) {
            params.addOpt("line_items[tax7_amount][" + index + "]", lineItemTax7Amount);
            return this;
        }
        public ImportInvoiceRequest lineItemTax8Name(int index, String lineItemTax8Name) {
            params.addOpt("line_items[tax8_name][" + index + "]", lineItemTax8Name);
            return this;
        }
        public ImportInvoiceRequest lineItemTax8Amount(int index, Long lineItemTax8Amount) {
            params.addOpt("line_items[tax8_amount][" + index + "]", lineItemTax8Amount);
            return this;
        }
        public ImportInvoiceRequest lineItemTax9Name(int index, String lineItemTax9Name) {
            params.addOpt("line_items[tax9_name][" + index + "]", lineItemTax9Name);
            return this;
        }
        public ImportInvoiceRequest lineItemTax9Amount(int index, Long lineItemTax9Amount) {
            params.addOpt("line_items[tax9_amount][" + index + "]", lineItemTax9Amount);
            return this;
        }
        public ImportInvoiceRequest lineItemTax10Name(int index, String lineItemTax10Name) {
            params.addOpt("line_items[tax10_name][" + index + "]", lineItemTax10Name);
            return this;
        }
        public ImportInvoiceRequest lineItemTax10Amount(int index, Long lineItemTax10Amount) {
            params.addOpt("line_items[tax10_amount][" + index + "]", lineItemTax10Amount);
            return this;
        }
        public ImportInvoiceRequest lineItemTierLineItemId(int index, String lineItemTierLineItemId) {
            params.add("line_item_tiers[line_item_id][" + index + "]", lineItemTierLineItemId);
            return this;
        }
        public ImportInvoiceRequest lineItemTierStartingUnit(int index, Integer lineItemTierStartingUnit) {
            params.addOpt("line_item_tiers[starting_unit][" + index + "]", lineItemTierStartingUnit);
            return this;
        }
        public ImportInvoiceRequest lineItemTierEndingUnit(int index, Integer lineItemTierEndingUnit) {
            params.addOpt("line_item_tiers[ending_unit][" + index + "]", lineItemTierEndingUnit);
            return this;
        }
        public ImportInvoiceRequest lineItemTierQuantityUsed(int index, Integer lineItemTierQuantityUsed) {
            params.addOpt("line_item_tiers[quantity_used][" + index + "]", lineItemTierQuantityUsed);
            return this;
        }
        public ImportInvoiceRequest lineItemTierUnitAmount(int index, Long lineItemTierUnitAmount) {
            params.addOpt("line_item_tiers[unit_amount][" + index + "]", lineItemTierUnitAmount);
            return this;
        }
        public ImportInvoiceRequest lineItemTierStartingUnitInDecimal(int index, String lineItemTierStartingUnitInDecimal) {
            params.addOpt("line_item_tiers[starting_unit_in_decimal][" + index + "]", lineItemTierStartingUnitInDecimal);
            return this;
        }
        public ImportInvoiceRequest lineItemTierEndingUnitInDecimal(int index, String lineItemTierEndingUnitInDecimal) {
            params.addOpt("line_item_tiers[ending_unit_in_decimal][" + index + "]", lineItemTierEndingUnitInDecimal);
            return this;
        }
        public ImportInvoiceRequest lineItemTierQuantityUsedInDecimal(int index, String lineItemTierQuantityUsedInDecimal) {
            params.addOpt("line_item_tiers[quantity_used_in_decimal][" + index + "]", lineItemTierQuantityUsedInDecimal);
            return this;
        }
        public ImportInvoiceRequest lineItemTierUnitAmountInDecimal(int index, String lineItemTierUnitAmountInDecimal) {
            params.addOpt("line_item_tiers[unit_amount_in_decimal][" + index + "]", lineItemTierUnitAmountInDecimal);
            return this;
        }
        public ImportInvoiceRequest discountLineItemId(int index, String discountLineItemId) {
            params.addOpt("discounts[line_item_id][" + index + "]", discountLineItemId);
            return this;
        }
        public ImportInvoiceRequest discountEntityType(int index, Discount.EntityType discountEntityType) {
            params.add("discounts[entity_type][" + index + "]", discountEntityType);
            return this;
        }
        public ImportInvoiceRequest discountEntityId(int index, String discountEntityId) {
            params.addOpt("discounts[entity_id][" + index + "]", discountEntityId);
            return this;
        }
        public ImportInvoiceRequest discountDescription(int index, String discountDescription) {
            params.addOpt("discounts[description][" + index + "]", discountDescription);
            return this;
        }
        public ImportInvoiceRequest discountAmount(int index, Long discountAmount) {
            params.add("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public ImportInvoiceRequest taxName(int index, String taxName) {
            params.add("taxes[name][" + index + "]", taxName);
            return this;
        }
        public ImportInvoiceRequest taxRate(int index, Double taxRate) {
            params.add("taxes[rate][" + index + "]", taxRate);
            return this;
        }
        public ImportInvoiceRequest taxAmount(int index, Long taxAmount) {
            params.addOpt("taxes[amount][" + index + "]", taxAmount);
            return this;
        }
        public ImportInvoiceRequest taxDescription(int index, String taxDescription) {
            params.addOpt("taxes[description][" + index + "]", taxDescription);
            return this;
        }
        public ImportInvoiceRequest taxJurisType(int index, com.chargebee.models.enums.TaxJurisType taxJurisType) {
            params.addOpt("taxes[juris_type][" + index + "]", taxJurisType);
            return this;
        }
        public ImportInvoiceRequest taxJurisName(int index, String taxJurisName) {
            params.addOpt("taxes[juris_name][" + index + "]", taxJurisName);
            return this;
        }
        public ImportInvoiceRequest taxJurisCode(int index, String taxJurisCode) {
            params.addOpt("taxes[juris_code][" + index + "]", taxJurisCode);
            return this;
        }
        public ImportInvoiceRequest paymentAmount(int index, Long paymentAmount) {
            params.add("payments[amount][" + index + "]", paymentAmount);
            return this;
        }
        public ImportInvoiceRequest paymentPaymentMethod(int index, com.chargebee.models.enums.PaymentMethod paymentPaymentMethod) {
            params.add("payments[payment_method][" + index + "]", paymentPaymentMethod);
            return this;
        }
        public ImportInvoiceRequest paymentDate(int index, Timestamp paymentDate) {
            params.addOpt("payments[date][" + index + "]", paymentDate);
            return this;
        }
        public ImportInvoiceRequest paymentReferenceNumber(int index, String paymentReferenceNumber) {
            params.addOpt("payments[reference_number][" + index + "]", paymentReferenceNumber);
            return this;
        }
        public ImportInvoiceRequest noteEntityType(int index, Note.EntityType noteEntityType) {
            params.addOpt("notes[entity_type][" + index + "]", noteEntityType);
            return this;
        }
        public ImportInvoiceRequest noteEntityId(int index, String noteEntityId) {
            params.addOpt("notes[entity_id][" + index + "]", noteEntityId);
            return this;
        }
        public ImportInvoiceRequest noteNote(int index, String noteNote) {
            params.addOpt("notes[note][" + index + "]", noteNote);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class ApplyPaymentsRequest extends Request<ApplyPaymentsRequest> {

        private ApplyPaymentsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ApplyPaymentsRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        public ApplyPaymentsRequest transactionId(int index, String transactionId) {
            params.addOpt("transactions[id][" + index + "]", transactionId);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class DeleteLineItemsRequest extends Request<DeleteLineItemsRequest> {

        private DeleteLineItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public DeleteLineItemsRequest lineItemId(int index, String lineItemId) {
            params.addOpt("line_items[id][" + index + "]", lineItemId);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class ApplyCreditsRequest extends Request<ApplyCreditsRequest> {

        private ApplyCreditsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ApplyCreditsRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        public ApplyCreditsRequest creditNoteId(int index, String creditNoteId) {
            params.addOpt("credit_notes[id][" + index + "]", creditNoteId);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class InvoiceListRequest extends ListRequest<InvoiceListRequest> {

        private InvoiceListRequest(String uri) {
            super(uri);
        }
    
        @Deprecated
        public InvoiceListRequest paidOnAfter(Timestamp paidOnAfter) {
            params.addOpt("paid_on_after", paidOnAfter);
            return this;
        }


        public InvoiceListRequest includeDeleted(Boolean includeDeleted) {
            params.addOpt("include_deleted", includeDeleted);
            return this;
        }












        public StringFilter<InvoiceListRequest> id() {
            return new StringFilter<InvoiceListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<InvoiceListRequest> subscriptionId() {
            return new StringFilter<InvoiceListRequest>("subscription_id",this).supportsMultiOperators(true).supportsPresenceOperator(true);        
        }


        public StringFilter<InvoiceListRequest> customerId() {
            return new StringFilter<InvoiceListRequest>("customer_id",this).supportsMultiOperators(true);        
        }


        public BooleanFilter<InvoiceListRequest> recurring() {
            return new BooleanFilter<InvoiceListRequest>("recurring",this);        
        }


        public EnumFilter<Invoice.Status, InvoiceListRequest> status() {
            return new EnumFilter<Invoice.Status, InvoiceListRequest>("status",this);        
        }


        public EnumFilter<com.chargebee.models.enums.PriceType, InvoiceListRequest> priceType() {
            return new EnumFilter<com.chargebee.models.enums.PriceType, InvoiceListRequest>("price_type",this);        
        }


        public TimestampFilter<InvoiceListRequest> date() {
            return new TimestampFilter<InvoiceListRequest>("date",this);        
        }


        public TimestampFilter<InvoiceListRequest> paidAt() {
            return new TimestampFilter<InvoiceListRequest>("paid_at",this);        
        }


        public NumberFilter<Long, InvoiceListRequest> total() {
            return new NumberFilter<Long, InvoiceListRequest>("total",this);        
        }


        public NumberFilter<Long, InvoiceListRequest> amountPaid() {
            return new NumberFilter<Long, InvoiceListRequest>("amount_paid",this);        
        }


        public NumberFilter<Long, InvoiceListRequest> amountAdjusted() {
            return new NumberFilter<Long, InvoiceListRequest>("amount_adjusted",this);        
        }


        public NumberFilter<Long, InvoiceListRequest> creditsApplied() {
            return new NumberFilter<Long, InvoiceListRequest>("credits_applied",this);        
        }


        public NumberFilter<Long, InvoiceListRequest> amountDue() {
            return new NumberFilter<Long, InvoiceListRequest>("amount_due",this);        
        }


        public EnumFilter<Invoice.DunningStatus, InvoiceListRequest> dunningStatus() {
            return new EnumFilter<Invoice.DunningStatus, InvoiceListRequest>("dunning_status",this).supportsPresenceOperator(true);        
        }


        public StringFilter<InvoiceListRequest> paymentOwner() {
            return new StringFilter<InvoiceListRequest>("payment_owner",this).supportsMultiOperators(true);        
        }


        public TimestampFilter<InvoiceListRequest> updatedAt() {
            return new TimestampFilter<InvoiceListRequest>("updated_at",this);        
        }


        public EnumFilter<com.chargebee.models.enums.Channel, InvoiceListRequest> channel() {
            return new EnumFilter<com.chargebee.models.enums.Channel, InvoiceListRequest>("channel",this);        
        }


        public TimestampFilter<InvoiceListRequest> voidedAt() {
            return new TimestampFilter<InvoiceListRequest>("voided_at",this);        
        }


        public StringFilter<InvoiceListRequest> voidReasonCode() {
            return new StringFilter<InvoiceListRequest>("void_reason_code",this).supportsMultiOperators(true);        
        }


        public InvoiceListRequest sortByDate(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","date");
            return this;
        }
        public InvoiceListRequest sortByUpdatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","updated_at");
            return this;
        }


        public EnumFilter<Einvoice.Status, InvoiceListRequest> einvoiceStatus() {
            return new EnumFilter<Einvoice.Status, InvoiceListRequest>("einvoice[status]",this);        
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

    public static class AddChargeRequest extends Request<AddChargeRequest> {

        private AddChargeRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public AddChargeRequest amount(Long amount) {
            params.add("amount", amount);
            return this;
        }


        public AddChargeRequest description(String description) {
            params.add("description", description);
            return this;
        }


        public AddChargeRequest avalaraSaleType(com.chargebee.models.enums.AvalaraSaleType avalaraSaleType) {
            params.addOpt("avalara_sale_type", avalaraSaleType);
            return this;
        }


        public AddChargeRequest avalaraTransactionType(Integer avalaraTransactionType) {
            params.addOpt("avalara_transaction_type", avalaraTransactionType);
            return this;
        }


        public AddChargeRequest avalaraServiceType(Integer avalaraServiceType) {
            params.addOpt("avalara_service_type", avalaraServiceType);
            return this;
        }


        public AddChargeRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        public AddChargeRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public AddChargeRequest lineItemDateFrom(Timestamp lineItemDateFrom) {
            params.addOpt("line_item[date_from]", lineItemDateFrom);
            return this;
        }

        public AddChargeRequest lineItemDateTo(Timestamp lineItemDateTo) {
            params.addOpt("line_item[date_to]", lineItemDateTo);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class AddAddonChargeRequest extends Request<AddAddonChargeRequest> {

        private AddAddonChargeRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public AddAddonChargeRequest addonId(String addonId) {
            params.add("addon_id", addonId);
            return this;
        }


        public AddAddonChargeRequest addonQuantity(Integer addonQuantity) {
            params.addOpt("addon_quantity", addonQuantity);
            return this;
        }


        public AddAddonChargeRequest addonUnitPrice(Long addonUnitPrice) {
            params.addOpt("addon_unit_price", addonUnitPrice);
            return this;
        }


        public AddAddonChargeRequest addonQuantityInDecimal(String addonQuantityInDecimal) {
            params.addOpt("addon_quantity_in_decimal", addonQuantityInDecimal);
            return this;
        }


        public AddAddonChargeRequest addonUnitPriceInDecimal(String addonUnitPriceInDecimal) {
            params.addOpt("addon_unit_price_in_decimal", addonUnitPriceInDecimal);
            return this;
        }


        public AddAddonChargeRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        public AddAddonChargeRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public AddAddonChargeRequest lineItemDateFrom(Timestamp lineItemDateFrom) {
            params.addOpt("line_item[date_from]", lineItemDateFrom);
            return this;
        }

        public AddAddonChargeRequest lineItemDateTo(Timestamp lineItemDateTo) {
            params.addOpt("line_item[date_to]", lineItemDateTo);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class AddChargeItemRequest extends Request<AddChargeItemRequest> {

        private AddChargeItemRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public AddChargeItemRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        public AddChargeItemRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public AddChargeItemRequest itemPriceItemPriceId(String itemPriceItemPriceId) {
            params.add("item_price[item_price_id]", itemPriceItemPriceId);
            return this;
        }

        public AddChargeItemRequest itemPriceQuantity(Integer itemPriceQuantity) {
            params.addOpt("item_price[quantity]", itemPriceQuantity);
            return this;
        }

        public AddChargeItemRequest itemPriceQuantityInDecimal(String itemPriceQuantityInDecimal) {
            params.addOpt("item_price[quantity_in_decimal]", itemPriceQuantityInDecimal);
            return this;
        }

        public AddChargeItemRequest itemPriceUnitPrice(Long itemPriceUnitPrice) {
            params.addOpt("item_price[unit_price]", itemPriceUnitPrice);
            return this;
        }

        public AddChargeItemRequest itemPriceUnitPriceInDecimal(String itemPriceUnitPriceInDecimal) {
            params.addOpt("item_price[unit_price_in_decimal]", itemPriceUnitPriceInDecimal);
            return this;
        }

        public AddChargeItemRequest itemPriceDateFrom(Timestamp itemPriceDateFrom) {
            params.addOpt("item_price[date_from]", itemPriceDateFrom);
            return this;
        }

        public AddChargeItemRequest itemPriceDateTo(Timestamp itemPriceDateTo) {
            params.addOpt("item_price[date_to]", itemPriceDateTo);
            return this;
        }

        public AddChargeItemRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public AddChargeItemRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public AddChargeItemRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public AddChargeItemRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public AddChargeItemRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public AddChargeItemRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CloseRequest extends Request<CloseRequest> {

        private CloseRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CloseRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        public CloseRequest invoiceNote(String invoiceNote) {
            params.addOpt("invoice_note", invoiceNote);
            return this;
        }


        public CloseRequest removeGeneralNote(Boolean removeGeneralNote) {
            params.addOpt("remove_general_note", removeGeneralNote);
            return this;
        }


        public CloseRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public CloseRequest notesToRemoveEntityType(int index, com.chargebee.models.enums.EntityType notesToRemoveEntityType) {
            params.addOpt("notes_to_remove[entity_type][" + index + "]", notesToRemoveEntityType);
            return this;
        }
        public CloseRequest notesToRemoveEntityId(int index, String notesToRemoveEntityId) {
            params.addOpt("notes_to_remove[entity_id][" + index + "]", notesToRemoveEntityId);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CollectPaymentRequest extends Request<CollectPaymentRequest> {

        private CollectPaymentRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CollectPaymentRequest amount(Long amount) {
            params.addOpt("amount", amount);
            return this;
        }


        public CollectPaymentRequest authorizationTransactionId(String authorizationTransactionId) {
            params.addOpt("authorization_transaction_id", authorizationTransactionId);
            return this;
        }


        public CollectPaymentRequest paymentSourceId(String paymentSourceId) {
            params.addOpt("payment_source_id", paymentSourceId);
            return this;
        }


        public CollectPaymentRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class RecordPaymentRequest extends Request<RecordPaymentRequest> {

        private RecordPaymentRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RecordPaymentRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        public RecordPaymentRequest transactionAmount(Long transactionAmount) {
            params.addOpt("transaction[amount]", transactionAmount);
            return this;
        }

        public RecordPaymentRequest transactionPaymentMethod(com.chargebee.models.enums.PaymentMethod transactionPaymentMethod) {
            params.add("transaction[payment_method]", transactionPaymentMethod);
            return this;
        }

        public RecordPaymentRequest transactionReferenceNumber(String transactionReferenceNumber) {
            params.addOpt("transaction[reference_number]", transactionReferenceNumber);
            return this;
        }

        public RecordPaymentRequest transactionIdAtGateway(String transactionIdAtGateway) {
            params.addOpt("transaction[id_at_gateway]", transactionIdAtGateway);
            return this;
        }

        public RecordPaymentRequest transactionStatus(Transaction.Status transactionStatus) {
            params.addOpt("transaction[status]", transactionStatus);
            return this;
        }

        public RecordPaymentRequest transactionDate(Timestamp transactionDate) {
            params.addOpt("transaction[date]", transactionDate);
            return this;
        }

        public RecordPaymentRequest transactionErrorCode(String transactionErrorCode) {
            params.addOpt("transaction[error_code]", transactionErrorCode);
            return this;
        }

        public RecordPaymentRequest transactionErrorText(String transactionErrorText) {
            params.addOpt("transaction[error_text]", transactionErrorText);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class RecordTaxWithheldRequest extends Request<RecordTaxWithheldRequest> {

        private RecordTaxWithheldRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RecordTaxWithheldRequest taxWithheldAmount(Long taxWithheldAmount) {
            params.add("tax_withheld[amount]", taxWithheldAmount);
            return this;
        }

        public RecordTaxWithheldRequest taxWithheldReferenceNumber(String taxWithheldReferenceNumber) {
            params.addOpt("tax_withheld[reference_number]", taxWithheldReferenceNumber);
            return this;
        }

        public RecordTaxWithheldRequest taxWithheldDate(Timestamp taxWithheldDate) {
            params.addOpt("tax_withheld[date]", taxWithheldDate);
            return this;
        }

        public RecordTaxWithheldRequest taxWithheldDescription(String taxWithheldDescription) {
            params.addOpt("tax_withheld[description]", taxWithheldDescription);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class RemoveTaxWithheldRequest extends Request<RemoveTaxWithheldRequest> {

        private RemoveTaxWithheldRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RemoveTaxWithheldRequest taxWithheldId(String taxWithheldId) {
            params.add("tax_withheld[id]", taxWithheldId);
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


        public RefundRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        public RefundRequest customerNotes(String customerNotes) {
            params.addOpt("customer_notes", customerNotes);
            return this;
        }


        public RefundRequest creditNoteReasonCode(CreditNote.ReasonCode creditNoteReasonCode) {
            params.addOpt("credit_note[reason_code]", creditNoteReasonCode);
            return this;
        }

        public RefundRequest creditNoteCreateReasonCode(String creditNoteCreateReasonCode) {
            params.addOpt("credit_note[create_reason_code]", creditNoteCreateReasonCode);
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


        public RecordRefundRequest customerNotes(String customerNotes) {
            params.addOpt("customer_notes", customerNotes);
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

        public RecordRefundRequest creditNoteReasonCode(CreditNote.ReasonCode creditNoteReasonCode) {
            params.addOpt("credit_note[reason_code]", creditNoteReasonCode);
            return this;
        }

        public RecordRefundRequest creditNoteCreateReasonCode(String creditNoteCreateReasonCode) {
            params.addOpt("credit_note[create_reason_code]", creditNoteCreateReasonCode);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class RemovePaymentRequest extends Request<RemovePaymentRequest> {

        private RemovePaymentRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RemovePaymentRequest transactionId(String transactionId) {
            params.add("transaction[id]", transactionId);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class RemoveCreditNoteRequest extends Request<RemoveCreditNoteRequest> {

        private RemoveCreditNoteRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RemoveCreditNoteRequest creditNoteId(String creditNoteId) {
            params.add("credit_note[id]", creditNoteId);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class VoidInvoiceRequest extends Request<VoidInvoiceRequest> {

        private VoidInvoiceRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public VoidInvoiceRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        public VoidInvoiceRequest voidReasonCode(String voidReasonCode) {
            params.addOpt("void_reason_code", voidReasonCode);
            return this;
        }


        @Deprecated
        public VoidInvoiceRequest createCreditNote(Boolean createCreditNote) {
            params.addOpt("create_credit_note", createCreditNote);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class WriteOffRequest extends Request<WriteOffRequest> {

        private WriteOffRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public WriteOffRequest comment(String comment) {
            params.addOpt("comment", comment);
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


        public DeleteRequest claimCredits(Boolean claimCredits) {
            params.addOpt("claim_credits", claimCredits);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateDetailsRequest extends Request<UpdateDetailsRequest> {

        private UpdateDetailsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateDetailsRequest vatNumber(String vatNumber) {
            params.addOpt("vat_number", vatNumber);
            return this;
        }


        public UpdateDetailsRequest vatNumberPrefix(String vatNumberPrefix) {
            params.addOpt("vat_number_prefix", vatNumberPrefix);
            return this;
        }


        public UpdateDetailsRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public UpdateDetailsRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        public UpdateDetailsRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }

        public UpdateDetailsRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }

        public UpdateDetailsRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }

        public UpdateDetailsRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }

        public UpdateDetailsRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }

        public UpdateDetailsRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public UpdateDetailsRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public UpdateDetailsRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public UpdateDetailsRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public UpdateDetailsRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public UpdateDetailsRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }

        public UpdateDetailsRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public UpdateDetailsRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public UpdateDetailsRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public UpdateDetailsRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public UpdateDetailsRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public UpdateDetailsRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public UpdateDetailsRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public UpdateDetailsRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public UpdateDetailsRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public UpdateDetailsRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public UpdateDetailsRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public UpdateDetailsRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public UpdateDetailsRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public UpdateDetailsRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public UpdateDetailsRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public UpdateDetailsRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public UpdateDetailsRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

}
