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

public class Invoice extends Resource<Invoice> {

    public enum Status {
        PAID,
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

    public static class LinkedPayment extends Resource<LinkedPayment> {
        public LinkedPayment(JSONObject jsonObj) {
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

    public static class AppliedCredit extends Resource<AppliedCredit> {
        public AppliedCredit(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String cnId() {
            return reqString("cn_id");
        }

        public Integer appliedAmount() {
            return reqInteger("applied_amount");
        }

        public Timestamp appliedAt() {
            return reqTimestamp("applied_at");
        }

        public CreditNote.ReasonCode cnReasonCode() {
            return reqEnum("cn_reason_code", CreditNote.ReasonCode.class);
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
            return reqEnum("cn_reason_code", CreditNote.ReasonCode.class);
        }

        public Timestamp cnDate() {
            return optTimestamp("cn_date");
        }

        public Integer cnTotal() {
            return optInteger("cn_total");
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
            return reqEnum("cn_reason_code", CreditNote.ReasonCode.class);
        }

        public Timestamp cnDate() {
            return optTimestamp("cn_date");
        }

        public Integer cnTotal() {
            return optInteger("cn_total");
        }

        public CreditNote.Status cnStatus() {
            return reqEnum("cn_status", CreditNote.Status.class);
        }

    }

    public static class LinkedOrder extends Resource<LinkedOrder> {
        public enum Status {
             NEW,PROCESSING,COMPLETE,CANCELLED,VOIDED,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public LinkedOrder(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public Status status() {
            return optEnum("status", Status.class);
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
             PLAN,ADDON,COUPON,SUBSCRIPTION,CUSTOMER,
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

    public String currencyCode() {
        return reqString("currency_code");
    }

    public Integer total() {
        return optInteger("total");
    }

    public Integer amountPaid() {
        return optInteger("amount_paid");
    }

    public Integer amountAdjusted() {
        return optInteger("amount_adjusted");
    }

    public Integer writeOffAmount() {
        return optInteger("write_off_amount");
    }

    public Integer creditsApplied() {
        return optInteger("credits_applied");
    }

    public Integer amountDue() {
        return optInteger("amount_due");
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

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public Integer subTotal() {
        return reqInteger("sub_total");
    }

    public Integer tax() {
        return reqInteger("tax");
    }

    public Boolean firstInvoice() {
        return optBoolean("first_invoice");
    }

    public List<Invoice.LineItem> lineItems() {
        return optList("line_items", Invoice.LineItem.class);
    }

    public List<Invoice.Discount> discounts() {
        return optList("discounts", Invoice.Discount.class);
    }

    public List<Invoice.Tax> taxes() {
        return optList("taxes", Invoice.Tax.class);
    }

    public List<Invoice.LineItemTax> lineItemTaxes() {
        return optList("line_item_taxes", Invoice.LineItemTax.class);
    }

    public List<Invoice.LinkedPayment> linkedPayments() {
        return optList("linked_payments", Invoice.LinkedPayment.class);
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

    public Boolean deleted() {
        return reqBoolean("deleted");
    }

    // Operations
    //===========

    public static CreateRequest create() throws IOException {
        String uri = uri("invoices");
        return new CreateRequest(Method.POST, uri);
    }

    public static ChargeRequest charge() throws IOException {
        String uri = uri("invoices", "charge");
        return new ChargeRequest(Method.POST, uri);
    }

    public static ChargeAddonRequest chargeAddon() throws IOException {
        String uri = uri("invoices", "charge_addon");
        return new ChargeAddonRequest(Method.POST, uri);
    }

    public static Request stopDunning(String id) throws IOException {
        String uri = uri("invoices", nullCheck(id), "stop_dunning");
        return new Request(Method.POST, uri);
    }

    public static InvoiceListRequest list() throws IOException {
        String uri = uri("invoices");
        return new InvoiceListRequest(uri);
    }

    @Deprecated
    public static ListRequest invoicesForCustomer(String id) throws IOException {
        String uri = uri("customers", nullCheck(id), "invoices");
        return new ListRequest(uri);
    }

    @Deprecated
    public static ListRequest invoicesForSubscription(String id) throws IOException {
        String uri = uri("subscriptions", nullCheck(id), "invoices");
        return new ListRequest(uri);
    }

    public static Request retrieve(String id) throws IOException {
        String uri = uri("invoices", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static Request pdf(String id) throws IOException {
        String uri = uri("invoices", nullCheck(id), "pdf");
        return new Request(Method.POST, uri);
    }

    public static AddChargeRequest addCharge(String id) throws IOException {
        String uri = uri("invoices", nullCheck(id), "add_charge");
        return new AddChargeRequest(Method.POST, uri);
    }

    public static AddAddonChargeRequest addAddonCharge(String id) throws IOException {
        String uri = uri("invoices", nullCheck(id), "add_addon_charge");
        return new AddAddonChargeRequest(Method.POST, uri);
    }

    public static Request close(String id) throws IOException {
        String uri = uri("invoices", nullCheck(id), "close");
        return new Request(Method.POST, uri);
    }

    public static CollectPaymentRequest collectPayment(String id) throws IOException {
        String uri = uri("invoices", nullCheck(id), "collect_payment");
        return new CollectPaymentRequest(Method.POST, uri);
    }

    public static RecordPaymentRequest recordPayment(String id) throws IOException {
        String uri = uri("invoices", nullCheck(id), "record_payment");
        return new RecordPaymentRequest(Method.POST, uri);
    }

    public static RefundRequest refund(String id) throws IOException {
        String uri = uri("invoices", nullCheck(id), "refund");
        return new RefundRequest(Method.POST, uri);
    }

    public static RecordRefundRequest recordRefund(String id) throws IOException {
        String uri = uri("invoices", nullCheck(id), "record_refund");
        return new RecordRefundRequest(Method.POST, uri);
    }

    public static VoidInvoiceRequest voidInvoice(String id) throws IOException {
        String uri = uri("invoices", nullCheck(id), "void");
        return new VoidInvoiceRequest(Method.POST, uri);
    }

    public static WriteOffRequest writeOff(String id) throws IOException {
        String uri = uri("invoices", nullCheck(id), "write_off");
        return new WriteOffRequest(Method.POST, uri);
    }

    public static DeleteRequest delete(String id) throws IOException {
        String uri = uri("invoices", nullCheck(id), "delete");
        return new DeleteRequest(Method.POST, uri);
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


        public CreateRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public CreateRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public CreateRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
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

        public CreateRequest shippingAddressValidationStatus(ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
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

        public CreateRequest chargeAmount(int index, Integer chargeAmount) {
            params.addOpt("charges[amount][" + index + "]", chargeAmount);
            return this;
        }

        public CreateRequest chargeDescription(int index, String chargeDescription) {
            params.addOpt("charges[description][" + index + "]", chargeDescription);
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


        public ChargeRequest amount(Integer amount) {
            params.add("amount", amount);
            return this;
        }


        public ChargeRequest description(String description) {
            params.add("description", description);
            return this;
        }


        public ChargeRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public ChargeRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
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


        public ChargeAddonRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public ChargeAddonRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
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


        public EnumFilter<Status, InvoiceListRequest> status() {
            return new EnumFilter<Status, InvoiceListRequest>("status",this);        
        }


        public EnumFilter<PriceType, InvoiceListRequest> priceType() {
            return new EnumFilter<PriceType, InvoiceListRequest>("price_type",this);        
        }


        public TimestampFilter<InvoiceListRequest> date() {
            return new TimestampFilter<InvoiceListRequest>("date",this);        
        }


        public TimestampFilter<InvoiceListRequest> paidAt() {
            return new TimestampFilter<InvoiceListRequest>("paid_at",this);        
        }


        public NumberFilter<Integer, InvoiceListRequest> total() {
            return new NumberFilter<Integer, InvoiceListRequest>("total",this);        
        }


        public NumberFilter<Integer, InvoiceListRequest> amountPaid() {
            return new NumberFilter<Integer, InvoiceListRequest>("amount_paid",this);        
        }


        public NumberFilter<Integer, InvoiceListRequest> amountAdjusted() {
            return new NumberFilter<Integer, InvoiceListRequest>("amount_adjusted",this);        
        }


        public NumberFilter<Integer, InvoiceListRequest> creditsApplied() {
            return new NumberFilter<Integer, InvoiceListRequest>("credits_applied",this);        
        }


        public NumberFilter<Integer, InvoiceListRequest> amountDue() {
            return new NumberFilter<Integer, InvoiceListRequest>("amount_due",this);        
        }


        public EnumFilter<DunningStatus, InvoiceListRequest> dunningStatus() {
            return new EnumFilter<DunningStatus, InvoiceListRequest>("dunning_status",this).supportsPresenceOperator(true);        
        }


        public TimestampFilter<InvoiceListRequest> updatedAt() {
            return new TimestampFilter<InvoiceListRequest>("updated_at",this);        
        }


        public InvoiceListRequest sortByDate(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","date");
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
    
        public AddChargeRequest amount(Integer amount) {
            params.add("amount", amount);
            return this;
        }


        public AddChargeRequest description(String description) {
            params.add("description", description);
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


        @Override
        public Params params() {
            return params;
        }
    }

    public static class CollectPaymentRequest extends Request<CollectPaymentRequest> {

        private CollectPaymentRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CollectPaymentRequest amount(Integer amount) {
            params.addOpt("amount", amount);
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


        public RecordPaymentRequest transactionAmount(Integer transactionAmount) {
            params.addOpt("transaction[amount]", transactionAmount);
            return this;
        }

        public RecordPaymentRequest transactionPaymentMethod(PaymentMethod transactionPaymentMethod) {
            params.add("transaction[payment_method]", transactionPaymentMethod);
            return this;
        }

        public RecordPaymentRequest transactionReferenceNumber(String transactionReferenceNumber) {
            params.addOpt("transaction[reference_number]", transactionReferenceNumber);
            return this;
        }

        public RecordPaymentRequest transactionDate(Timestamp transactionDate) {
            params.addOpt("transaction[date]", transactionDate);
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


        public RecordRefundRequest transactionAmount(Integer transactionAmount) {
            params.addOpt("transaction[amount]", transactionAmount);
            return this;
        }

        public RecordRefundRequest transactionPaymentMethod(PaymentMethod transactionPaymentMethod) {
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


        @Override
        public Params params() {
            return params;
        }
    }

}
