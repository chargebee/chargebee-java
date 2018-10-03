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

public class Order extends Resource<Order> {

    public enum Status {
        NEW,
        PROCESSING,
        COMPLETE,
        CANCELLED,
        VOIDED,
        QUEUED,
        AWAITING_SHIPMENT,
        ON_HOLD,
        DELIVERED,
        SHIPPED,
        PARTIALLY_DELIVERED,
        RETURNED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum CancellationReason {
        SHIPPING_CUT_OFF_PASSED,
        PRODUCT_UNSATISFACTORY,
        THIRD_PARTY_CANCELLATION,
        PRODUCT_NOT_REQUIRED,
        DELIVERY_DATE_MISSED,
        ALTERNATIVE_FOUND,
        INVOICE_WRITTEN_OFF,
        INVOICE_VOIDED,
        FRAUDULENT_TRANSACTION,
        PAYMENT_DECLINED,
        SUBSCRIPTION_CANCELLED,
        PRODUCT_NOT_AVAILABLE,
        OTHERS,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum PaymentStatus {
        NOT_PAID,
        PAID,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum OrderType {
        MANUAL,
        SYSTEM_GENERATED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class OrderLineItem extends Resource<OrderLineItem> {
        public enum Status {
             QUEUED,AWAITING_SHIPMENT,ON_HOLD,DELIVERED,SHIPPED,PARTIALLY_DELIVERED,RETURNED,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum EntityType {
             PLAN_SETUP,PLAN,ADDON,ADHOC,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public OrderLineItem(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public String invoiceId() {
            return reqString("invoice_id");
        }

        public String invoiceLineItemId() {
            return reqString("invoice_line_item_id");
        }

        public Integer unitPrice() {
            return optInteger("unit_price");
        }

        public String description() {
            return optString("description");
        }

        public Integer amount() {
            return optInteger("amount");
        }

        public Integer fulfillmentQuantity() {
            return optInteger("fulfillment_quantity");
        }

        public Integer fulfillmentAmount() {
            return optInteger("fulfillment_amount");
        }

        public Integer taxAmount() {
            return optInteger("tax_amount");
        }

        public Integer amountPaid() {
            return optInteger("amount_paid");
        }

        public Integer amountAdjusted() {
            return optInteger("amount_adjusted");
        }

        public Integer refundableCreditsIssued() {
            return optInteger("refundable_credits_issued");
        }

        public Integer refundableCredits() {
            return optInteger("refundable_credits");
        }

        public Boolean isShippable() {
            return reqBoolean("is_shippable");
        }

        public Status status() {
            return optEnum("status", Status.class);
        }

        public EntityType entityType() {
            return reqEnum("entity_type", EntityType.class);
        }

        public Integer itemLevelDiscountAmount() {
            return optInteger("item_level_discount_amount");
        }

        public Integer discountAmount() {
            return optInteger("discount_amount");
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

    public static class LinkedCreditNote extends Resource<LinkedCreditNote> {
        public enum Type {
             ADJUSTMENT,REFUNDABLE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum Status {
             ADJUSTED,REFUNDED,REFUND_DUE,VOIDED,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public LinkedCreditNote(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Integer amount() {
            return optInteger("amount");
        }

        public Type type() {
            return reqEnum("type", Type.class);
        }

        public String id() {
            return reqString("id");
        }

        public Status status() {
            return reqEnum("status", Status.class);
        }

        public Integer amountAdjusted() {
            return optInteger("amount_adjusted");
        }

        public Integer amountRefunded() {
            return optInteger("amount_refunded");
        }

    }

    //Constructors
    //============

    public Order(String jsonStr) {
        super(jsonStr);
    }

    public Order(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String documentNumber() {
        return optString("document_number");
    }

    public String invoiceId() {
        return optString("invoice_id");
    }

    public String subscriptionId() {
        return optString("subscription_id");
    }

    public String customerId() {
        return optString("customer_id");
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public CancellationReason cancellationReason() {
        return optEnum("cancellation_reason", CancellationReason.class);
    }

    public PaymentStatus paymentStatus() {
        return optEnum("payment_status", PaymentStatus.class);
    }

    public OrderType orderType() {
        return optEnum("order_type", OrderType.class);
    }

    public PriceType priceType() {
        return reqEnum("price_type", PriceType.class);
    }

    public String referenceId() {
        return optString("reference_id");
    }

    public String fulfillmentStatus() {
        return optString("fulfillment_status");
    }

    public Timestamp orderDate() {
        return optTimestamp("order_date");
    }

    public Timestamp shippingDate() {
        return optTimestamp("shipping_date");
    }

    public String note() {
        return optString("note");
    }

    public String trackingId() {
        return optString("tracking_id");
    }

    public String batchId() {
        return optString("batch_id");
    }

    public String createdBy() {
        return optString("created_by");
    }

    public String shipmentCarrier() {
        return optString("shipment_carrier");
    }

    public Integer invoiceRoundOffAmount() {
        return optInteger("invoice_round_off_amount");
    }

    public Integer tax() {
        return optInteger("tax");
    }

    public Integer amountPaid() {
        return optInteger("amount_paid");
    }

    public Integer amountAdjusted() {
        return optInteger("amount_adjusted");
    }

    public Integer refundableCreditsIssued() {
        return optInteger("refundable_credits_issued");
    }

    public Integer refundableCredits() {
        return optInteger("refundable_credits");
    }

    public Integer roundingAdjustement() {
        return optInteger("rounding_adjustement");
    }

    public Timestamp paidOn() {
        return optTimestamp("paid_on");
    }

    public Timestamp shippingCutOffDate() {
        return optTimestamp("shipping_cut_off_date");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp statusUpdateAt() {
        return optTimestamp("status_update_at");
    }

    public Timestamp deliveredAt() {
        return optTimestamp("delivered_at");
    }

    public Timestamp shippedAt() {
        return optTimestamp("shipped_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public Timestamp cancelledAt() {
        return optTimestamp("cancelled_at");
    }

    public List<Order.OrderLineItem> orderLineItems() {
        return optList("order_line_items", Order.OrderLineItem.class);
    }

    public Order.ShippingAddress shippingAddress() {
        return optSubResource("shipping_address", Order.ShippingAddress.class);
    }

    public Order.BillingAddress billingAddress() {
        return optSubResource("billing_address", Order.BillingAddress.class);
    }

    public Integer discount() {
        return optInteger("discount");
    }

    public Integer subTotal() {
        return optInteger("sub_total");
    }

    public Integer total() {
        return optInteger("total");
    }

    public List<Order.LineItemTax> lineItemTaxes() {
        return optList("line_item_taxes", Order.LineItemTax.class);
    }

    public List<Order.LineItemDiscount> lineItemDiscounts() {
        return optList("line_item_discounts", Order.LineItemDiscount.class);
    }

    public List<Order.LinkedCreditNote> linkedCreditNotes() {
        return optList("linked_credit_notes", Order.LinkedCreditNote.class);
    }

    public Boolean deleted() {
        return reqBoolean("deleted");
    }

    public String currencyCode() {
        return optString("currency_code");
    }

    // Operations
    //===========

    public static CreateRequest create() throws IOException {
        String uri = uri("orders");
        return new CreateRequest(Method.POST, uri);
    }

    public static UpdateRequest update(String id) throws IOException {
        String uri = uri("orders", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static Request assignOrderNumber(String id) throws IOException {
        String uri = uri("orders", nullCheck(id), "assign_order_number");
        return new Request(Method.POST, uri);
    }

    public static CancelRequest cancel(String id) throws IOException {
        String uri = uri("orders", nullCheck(id), "cancel");
        return new CancelRequest(Method.POST, uri);
    }

    public static CreateRefundableCreditNoteRequest createRefundableCreditNote(String id) throws IOException {
        String uri = uri("orders", nullCheck(id), "create_refundable_credit_note");
        return new CreateRefundableCreditNoteRequest(Method.POST, uri);
    }

    public static ReopenRequest reopen(String id) throws IOException {
        String uri = uri("orders", nullCheck(id), "reopen");
        return new ReopenRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) throws IOException {
        String uri = uri("orders", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static OrderListRequest list() throws IOException {
        String uri = uri("orders");
        return new OrderListRequest(uri);
    }

    @Deprecated
    public static ListRequest ordersForInvoice(String id) throws IOException {
        String uri = uri("invoices", nullCheck(id), "orders");
        return new ListRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }


        public CreateRequest invoiceId(String invoiceId) {
            params.add("invoice_id", invoiceId);
            return this;
        }


        public CreateRequest status(Order.Status status) {
            params.addOpt("status", status);
            return this;
        }


        public CreateRequest referenceId(String referenceId) {
            params.addOpt("reference_id", referenceId);
            return this;
        }


        public CreateRequest fulfillmentStatus(String fulfillmentStatus) {
            params.addOpt("fulfillment_status", fulfillmentStatus);
            return this;
        }


        public CreateRequest note(String note) {
            params.addOpt("note", note);
            return this;
        }


        public CreateRequest trackingId(String trackingId) {
            params.addOpt("tracking_id", trackingId);
            return this;
        }


        public CreateRequest batchId(String batchId) {
            params.addOpt("batch_id", batchId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateRequest extends Request<UpdateRequest> {

        private UpdateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateRequest referenceId(String referenceId) {
            params.addOpt("reference_id", referenceId);
            return this;
        }


        public UpdateRequest batchId(String batchId) {
            params.addOpt("batch_id", batchId);
            return this;
        }


        public UpdateRequest note(String note) {
            params.addOpt("note", note);
            return this;
        }


        public UpdateRequest shippingDate(Timestamp shippingDate) {
            params.addOpt("shipping_date", shippingDate);
            return this;
        }


        public UpdateRequest orderDate(Timestamp orderDate) {
            params.addOpt("order_date", orderDate);
            return this;
        }


        public UpdateRequest cancelledAt(Timestamp cancelledAt) {
            params.addOpt("cancelled_at", cancelledAt);
            return this;
        }


        public UpdateRequest cancellationReason(Order.CancellationReason cancellationReason) {
            params.addOpt("cancellation_reason", cancellationReason);
            return this;
        }


        public UpdateRequest shippedAt(Timestamp shippedAt) {
            params.addOpt("shipped_at", shippedAt);
            return this;
        }


        public UpdateRequest deliveredAt(Timestamp deliveredAt) {
            params.addOpt("delivered_at", deliveredAt);
            return this;
        }


        public UpdateRequest trackingId(String trackingId) {
            params.addOpt("tracking_id", trackingId);
            return this;
        }


        public UpdateRequest shipmentCarrier(String shipmentCarrier) {
            params.addOpt("shipment_carrier", shipmentCarrier);
            return this;
        }


        public UpdateRequest fulfillmentStatus(String fulfillmentStatus) {
            params.addOpt("fulfillment_status", fulfillmentStatus);
            return this;
        }


        public UpdateRequest status(Order.Status status) {
            params.addOpt("status", status);
            return this;
        }


        public UpdateRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public UpdateRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public UpdateRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public UpdateRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public UpdateRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public UpdateRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public UpdateRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public UpdateRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public UpdateRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public UpdateRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public UpdateRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public UpdateRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public UpdateRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public UpdateRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public UpdateRequest orderLineItemId(int index, String orderLineItemId) {
            params.addOpt("order_line_items[id][" + index + "]", orderLineItemId);
            return this;
        }
        public UpdateRequest orderLineItemStatus(int index, OrderLineItem.Status orderLineItemStatus) {
            params.addOpt("order_line_items[status][" + index + "]", orderLineItemStatus);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CancelRequest extends Request<CancelRequest> {

        private CancelRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CancelRequest cancellationReason(Order.CancellationReason cancellationReason) {
            params.add("cancellation_reason", cancellationReason);
            return this;
        }


        public CancelRequest customerNotes(String customerNotes) {
            params.addOpt("customer_notes", customerNotes);
            return this;
        }


        public CancelRequest creditNoteTotal(Integer creditNoteTotal) {
            params.addOpt("credit_note[total]", creditNoteTotal);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateRefundableCreditNoteRequest extends Request<CreateRefundableCreditNoteRequest> {

        private CreateRefundableCreditNoteRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRefundableCreditNoteRequest customerNotes(String customerNotes) {
            params.addOpt("customer_notes", customerNotes);
            return this;
        }


        public CreateRefundableCreditNoteRequest creditNoteReasonCode(CreditNote.ReasonCode creditNoteReasonCode) {
            params.add("credit_note[reason_code]", creditNoteReasonCode);
            return this;
        }

        public CreateRefundableCreditNoteRequest creditNoteTotal(Integer creditNoteTotal) {
            params.add("credit_note[total]", creditNoteTotal);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class ReopenRequest extends Request<ReopenRequest> {

        private ReopenRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ReopenRequest voidCancellationCreditNotes(Boolean voidCancellationCreditNotes) {
            params.addOpt("void_cancellation_credit_notes", voidCancellationCreditNotes);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class OrderListRequest extends ListRequest<OrderListRequest> {

        private OrderListRequest(String uri) {
            super(uri);
        }
    
        public OrderListRequest includeDeleted(Boolean includeDeleted) {
            params.addOpt("include_deleted", includeDeleted);
            return this;
        }


        public StringFilter<OrderListRequest> id() {
            return new StringFilter<OrderListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<OrderListRequest> invoiceId() {
            return new StringFilter<OrderListRequest>("invoice_id",this).supportsMultiOperators(true);        
        }


        public EnumFilter<Order.Status, OrderListRequest> status() {
            return new EnumFilter<Order.Status, OrderListRequest>("status",this);        
        }


        public TimestampFilter<OrderListRequest> shippingDate() {
            return new TimestampFilter<OrderListRequest>("shipping_date",this);        
        }


        public TimestampFilter<OrderListRequest> orderDate() {
            return new TimestampFilter<OrderListRequest>("order_date",this);        
        }


        public TimestampFilter<OrderListRequest> paidOn() {
            return new TimestampFilter<OrderListRequest>("paid_on",this);        
        }


        public TimestampFilter<OrderListRequest> updatedAt() {
            return new TimestampFilter<OrderListRequest>("updated_at",this);        
        }


        public TimestampFilter<OrderListRequest> createdAt() {
            return new TimestampFilter<OrderListRequest>("created_at",this);        
        }


        public OrderListRequest sortByCreatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","created_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
