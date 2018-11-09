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

public class Quote extends Resource<Quote> {

    public enum Status {
        OPEN,
        ACCEPTED,
        DECLINED,
        INVOICED,
        CLOSED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum OperationType {
        CREATE_SUBSCRIPTION_FOR_CUSTOMER,
        CHANGE_SUBSCRIPTION,
        ONETIME_INVOICE,
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

    public Quote(String jsonStr) {
        super(jsonStr);
    }

    public Quote(JSONObject jsonObj) {
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

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public OperationType operationType() {
        return reqEnum("operation_type", OperationType.class);
    }

    public String vatNumber() {
        return optString("vat_number");
    }

    public PriceType priceType() {
        return reqEnum("price_type", PriceType.class);
    }

    public Timestamp validTill() {
        return reqTimestamp("valid_till");
    }

    public Timestamp date() {
        return reqTimestamp("date");
    }

    public Integer subTotal() {
        return reqInteger("sub_total");
    }

    public Integer total() {
        return optInteger("total");
    }

    public Integer creditsApplied() {
        return optInteger("credits_applied");
    }

    public Integer amountPaid() {
        return optInteger("amount_paid");
    }

    public Integer amountDue() {
        return optInteger("amount_due");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public List<Quote.LineItem> lineItems() {
        return optList("line_items", Quote.LineItem.class);
    }

    public List<Quote.Discount> discounts() {
        return optList("discounts", Quote.Discount.class);
    }

    public List<Quote.LineItemDiscount> lineItemDiscounts() {
        return optList("line_item_discounts", Quote.LineItemDiscount.class);
    }

    public List<Quote.Tax> taxes() {
        return optList("taxes", Quote.Tax.class);
    }

    public List<Quote.LineItemTax> lineItemTaxes() {
        return optList("line_item_taxes", Quote.LineItemTax.class);
    }

    public Quote.ShippingAddress shippingAddress() {
        return optSubResource("shipping_address", Quote.ShippingAddress.class);
    }

    public Quote.BillingAddress billingAddress() {
        return optSubResource("billing_address", Quote.BillingAddress.class);
    }

    // Operations
    //===========

    public static Request retrieve(String id) throws IOException {
        String uri = uri("quotes", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static CreateForOnetimeChargesRequest createForOnetimeCharges() throws IOException {
        String uri = uri("quotes", "create_for_onetime_charges");
        return new CreateForOnetimeChargesRequest(Method.POST, uri);
    }

    public static Request convert(String id) throws IOException {
        String uri = uri("quotes", nullCheck(id), "convert");
        return new Request(Method.POST, uri);
    }

    public static UpdateStatusRequest updateStatus(String id) throws IOException {
        String uri = uri("quotes", nullCheck(id), "update_status");
        return new UpdateStatusRequest(Method.POST, uri);
    }

    public static PdfRequest pdf(String id) throws IOException {
        String uri = uri("quotes", nullCheck(id), "pdf");
        return new PdfRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateForOnetimeChargesRequest extends Request<CreateForOnetimeChargesRequest> {

        private CreateForOnetimeChargesRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateForOnetimeChargesRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public CreateForOnetimeChargesRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public CreateForOnetimeChargesRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public CreateForOnetimeChargesRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public CreateForOnetimeChargesRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public CreateForOnetimeChargesRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public CreateForOnetimeChargesRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public CreateForOnetimeChargesRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public CreateForOnetimeChargesRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public CreateForOnetimeChargesRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CreateForOnetimeChargesRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CreateForOnetimeChargesRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CreateForOnetimeChargesRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CreateForOnetimeChargesRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CreateForOnetimeChargesRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public CreateForOnetimeChargesRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CreateForOnetimeChargesRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CreateForOnetimeChargesRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CreateForOnetimeChargesRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public CreateForOnetimeChargesRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public CreateForOnetimeChargesRequest addonUnitPrice(int index, Integer addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public CreateForOnetimeChargesRequest chargeAmount(int index, Integer chargeAmount) {
            params.addOpt("charges[amount][" + index + "]", chargeAmount);
            return this;
        }
        public CreateForOnetimeChargesRequest chargeDescription(int index, String chargeDescription) {
            params.addOpt("charges[description][" + index + "]", chargeDescription);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateStatusRequest extends Request<UpdateStatusRequest> {

        private UpdateStatusRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateStatusRequest status(Status status) {
            params.add("status", status);
            return this;
        }


        public UpdateStatusRequest comment(String comment) {
            params.addOpt("comment", comment);
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

}
