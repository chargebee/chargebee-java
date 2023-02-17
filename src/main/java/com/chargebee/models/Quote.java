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

    public String name() {
        return optString("name");
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

    public String invoiceId() {
        return optString("invoice_id");
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

    public Long totalPayable() {
        return optLong("total_payable");
    }

    public Long chargeOnAcceptance() {
        return optLong("charge_on_acceptance");
    }

    public Long subTotal() {
        return reqLong("sub_total");
    }

    public Long total() {
        return optLong("total");
    }

    public Long creditsApplied() {
        return optLong("credits_applied");
    }

    public Long amountPaid() {
        return optLong("amount_paid");
    }

    public Long amountDue() {
        return optLong("amount_due");
    }

    public Integer version() {
        return optInteger("version");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public String vatNumberPrefix() {
        return optString("vat_number_prefix");
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

    public List<Quote.LineItemTier> lineItemTiers() {
        return optList("line_item_tiers", Quote.LineItemTier.class);
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public JSONArray notes() {
        return optJSONArray("notes");
    }

    public Quote.ShippingAddress shippingAddress() {
        return optSubResource("shipping_address", Quote.ShippingAddress.class);
    }

    public Quote.BillingAddress billingAddress() {
        return optSubResource("billing_address", Quote.BillingAddress.class);
    }

    public Timestamp contractTermStart() {
        return optTimestamp("contract_term_start");
    }

    public Timestamp contractTermEnd() {
        return optTimestamp("contract_term_end");
    }

    public Long contractTermTerminationFee() {
        return optLong("contract_term_termination_fee");
    }

    public String businessEntityId() {
        return reqString("business_entity_id");
    }

    // Operations
    //===========

    public static Request retrieve(String id) {
        String uri = uri("quotes", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static CreateSubForCustomerQuoteRequest createSubForCustomerQuote(String id) {
        String uri = uri("customers", nullCheck(id), "create_subscription_quote");
        return new CreateSubForCustomerQuoteRequest(Method.POST, uri);
    }

    public static EditCreateSubForCustomerQuoteRequest editCreateSubForCustomerQuote(String id) {
        String uri = uri("quotes", nullCheck(id), "edit_create_subscription_quote");
        return new EditCreateSubForCustomerQuoteRequest(Method.POST, uri);
    }

    public static UpdateSubscriptionQuoteRequest updateSubscriptionQuote() {
        String uri = uri("quotes", "update_subscription_quote");
        return new UpdateSubscriptionQuoteRequest(Method.POST, uri);
    }

    public static EditUpdateSubscriptionQuoteRequest editUpdateSubscriptionQuote(String id) {
        String uri = uri("quotes", nullCheck(id), "edit_update_subscription_quote");
        return new EditUpdateSubscriptionQuoteRequest(Method.POST, uri);
    }

    public static CreateForOnetimeChargesRequest createForOnetimeCharges() {
        String uri = uri("quotes", "create_for_onetime_charges");
        return new CreateForOnetimeChargesRequest(Method.POST, uri);
    }

    public static EditOneTimeQuoteRequest editOneTimeQuote(String id) {
        String uri = uri("quotes", nullCheck(id), "edit_one_time_quote");
        return new EditOneTimeQuoteRequest(Method.POST, uri);
    }

    public static CreateSubItemsForCustomerQuoteRequest createSubItemsForCustomerQuote(String id) {
        String uri = uri("customers", nullCheck(id), "create_subscription_quote_for_items");
        return new CreateSubItemsForCustomerQuoteRequest(Method.POST, uri);
    }

    public static EditCreateSubCustomerQuoteForItemsRequest editCreateSubCustomerQuoteForItems(String id) {
        String uri = uri("quotes", nullCheck(id), "edit_create_subscription_quote_for_items");
        return new EditCreateSubCustomerQuoteForItemsRequest(Method.POST, uri);
    }

    public static UpdateSubscriptionQuoteForItemsRequest updateSubscriptionQuoteForItems() {
        String uri = uri("quotes", "update_subscription_quote_for_items");
        return new UpdateSubscriptionQuoteForItemsRequest(Method.POST, uri);
    }

    public static EditUpdateSubscriptionQuoteForItemsRequest editUpdateSubscriptionQuoteForItems(String id) {
        String uri = uri("quotes", nullCheck(id), "edit_update_subscription_quote_for_items");
        return new EditUpdateSubscriptionQuoteForItemsRequest(Method.POST, uri);
    }

    public static CreateForChargeItemsAndChargesRequest createForChargeItemsAndCharges() {
        String uri = uri("quotes", "create_for_charge_items_and_charges");
        return new CreateForChargeItemsAndChargesRequest(Method.POST, uri);
    }

    public static EditForChargeItemsAndChargesRequest editForChargeItemsAndCharges(String id) {
        String uri = uri("quotes", nullCheck(id), "edit_for_charge_items_and_charges");
        return new EditForChargeItemsAndChargesRequest(Method.POST, uri);
    }

    public static QuoteListRequest list() {
        String uri = uri("quotes");
        return new QuoteListRequest(uri);
    }

    public static ListRequest quoteLineGroupsForQuote(String id) {
        String uri = uri("quotes", nullCheck(id), "quote_line_groups");
        return new ListRequest(uri);
    }

    public static ConvertRequest convert(String id) {
        String uri = uri("quotes", nullCheck(id), "convert");
        return new ConvertRequest(Method.POST, uri);
    }

    public static UpdateStatusRequest updateStatus(String id) {
        String uri = uri("quotes", nullCheck(id), "update_status");
        return new UpdateStatusRequest(Method.POST, uri);
    }

    public static ExtendExpiryDateRequest extendExpiryDate(String id) {
        String uri = uri("quotes", nullCheck(id), "extend_expiry_date");
        return new ExtendExpiryDateRequest(Method.POST, uri);
    }

    public static DeleteRequest delete(String id) {
        String uri = uri("quotes", nullCheck(id), "delete");
        return new DeleteRequest(Method.POST, uri);
    }

    public static PdfRequest pdf(String id) {
        String uri = uri("quotes", nullCheck(id), "pdf");
        return new PdfRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateSubForCustomerQuoteRequest extends Request<CreateSubForCustomerQuoteRequest> {

        private CreateSubForCustomerQuoteRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateSubForCustomerQuoteRequest name(String name) {
            params.addOpt("name", name);
            return this;
        }


        public CreateSubForCustomerQuoteRequest notes(String notes) {
            params.addOpt("notes", notes);
            return this;
        }


        public CreateSubForCustomerQuoteRequest expiresAt(Timestamp expiresAt) {
            params.addOpt("expires_at", expiresAt);
            return this;
        }












        public CreateSubForCustomerQuoteRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public CreateSubForCustomerQuoteRequest mandatoryAddonsToRemove(List<String> mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public CreateSubForCustomerQuoteRequest mandatoryAddonsToRemove(String... mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public CreateSubForCustomerQuoteRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }






        public CreateSubForCustomerQuoteRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }




        public CreateSubForCustomerQuoteRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateSubForCustomerQuoteRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateSubForCustomerQuoteRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription[id]", subscriptionId);
            return this;
        }

        public CreateSubForCustomerQuoteRequest subscriptionPoNumber(String subscriptionPoNumber) {
            params.addOpt("subscription[po_number]", subscriptionPoNumber);
            return this;
        }

        public CreateSubForCustomerQuoteRequest subscriptionPlanId(String subscriptionPlanId) {
            params.add("subscription[plan_id]", subscriptionPlanId);
            return this;
        }

        public CreateSubForCustomerQuoteRequest subscriptionPlanQuantity(Integer subscriptionPlanQuantity) {
            params.addOpt("subscription[plan_quantity]", subscriptionPlanQuantity);
            return this;
        }

        public CreateSubForCustomerQuoteRequest subscriptionPlanQuantityInDecimal(String subscriptionPlanQuantityInDecimal) {
            params.addOpt("subscription[plan_quantity_in_decimal]", subscriptionPlanQuantityInDecimal);
            return this;
        }

        public CreateSubForCustomerQuoteRequest subscriptionPlanUnitPrice(Long subscriptionPlanUnitPrice) {
            params.addOpt("subscription[plan_unit_price]", subscriptionPlanUnitPrice);
            return this;
        }

        public CreateSubForCustomerQuoteRequest subscriptionPlanUnitPriceInDecimal(String subscriptionPlanUnitPriceInDecimal) {
            params.addOpt("subscription[plan_unit_price_in_decimal]", subscriptionPlanUnitPriceInDecimal);
            return this;
        }

        public CreateSubForCustomerQuoteRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public CreateSubForCustomerQuoteRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        public CreateSubForCustomerQuoteRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public CreateSubForCustomerQuoteRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
            return this;
        }

        public CreateSubForCustomerQuoteRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public CreateSubForCustomerQuoteRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public CreateSubForCustomerQuoteRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public CreateSubForCustomerQuoteRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public CreateSubForCustomerQuoteRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public CreateSubForCustomerQuoteRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CreateSubForCustomerQuoteRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CreateSubForCustomerQuoteRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CreateSubForCustomerQuoteRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CreateSubForCustomerQuoteRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CreateSubForCustomerQuoteRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public CreateSubForCustomerQuoteRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CreateSubForCustomerQuoteRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CreateSubForCustomerQuoteRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CreateSubForCustomerQuoteRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public CreateSubForCustomerQuoteRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public CreateSubForCustomerQuoteRequest subscriptionContractTermBillingCycleOnRenewal(Integer subscriptionContractTermBillingCycleOnRenewal) {
            params.addOpt("subscription[contract_term_billing_cycle_on_renewal]", subscriptionContractTermBillingCycleOnRenewal);
            return this;
        }

        public CreateSubForCustomerQuoteRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public CreateSubForCustomerQuoteRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public CreateSubForCustomerQuoteRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public CreateSubForCustomerQuoteRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public CreateSubForCustomerQuoteRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public CreateSubForCustomerQuoteRequest addonBillingCycles(int index, Integer addonBillingCycles) {
            params.addOpt("addons[billing_cycles][" + index + "]", addonBillingCycles);
            return this;
        }
        public CreateSubForCustomerQuoteRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public CreateSubForCustomerQuoteRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public CreateSubForCustomerQuoteRequest eventBasedAddonUnitPrice(int index, Long eventBasedAddonUnitPrice) {
            params.addOpt("event_based_addons[unit_price][" + index + "]", eventBasedAddonUnitPrice);
            return this;
        }
        public CreateSubForCustomerQuoteRequest eventBasedAddonQuantityInDecimal(int index, String eventBasedAddonQuantityInDecimal) {
            params.addOpt("event_based_addons[quantity_in_decimal][" + index + "]", eventBasedAddonQuantityInDecimal);
            return this;
        }
        public CreateSubForCustomerQuoteRequest eventBasedAddonUnitPriceInDecimal(int index, String eventBasedAddonUnitPriceInDecimal) {
            params.addOpt("event_based_addons[unit_price_in_decimal][" + index + "]", eventBasedAddonUnitPriceInDecimal);
            return this;
        }
        public CreateSubForCustomerQuoteRequest eventBasedAddonServicePeriodInDays(int index, Integer eventBasedAddonServicePeriodInDays) {
            params.addOpt("event_based_addons[service_period_in_days][" + index + "]", eventBasedAddonServicePeriodInDays);
            return this;
        }
        public CreateSubForCustomerQuoteRequest eventBasedAddonOnEvent(int index, com.chargebee.models.enums.OnEvent eventBasedAddonOnEvent) {
            params.addOpt("event_based_addons[on_event][" + index + "]", eventBasedAddonOnEvent);
            return this;
        }
        public CreateSubForCustomerQuoteRequest eventBasedAddonChargeOnce(int index, Boolean eventBasedAddonChargeOnce) {
            params.addOpt("event_based_addons[charge_once][" + index + "]", eventBasedAddonChargeOnce);
            return this;
        }
        public CreateSubForCustomerQuoteRequest eventBasedAddonChargeOn(int index, com.chargebee.models.enums.ChargeOn eventBasedAddonChargeOn) {
            params.addOpt("event_based_addons[charge_on][" + index + "]", eventBasedAddonChargeOn);
            return this;
        }
        public CreateSubForCustomerQuoteRequest addonTrialEnd(int index, Timestamp addonTrialEnd) {
            params.addOpt("addons[trial_end][" + index + "]", addonTrialEnd);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class EditCreateSubForCustomerQuoteRequest extends Request<EditCreateSubForCustomerQuoteRequest> {

        private EditCreateSubForCustomerQuoteRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    


        public EditCreateSubForCustomerQuoteRequest notes(String notes) {
            params.addOpt("notes", notes);
            return this;
        }


        public EditCreateSubForCustomerQuoteRequest expiresAt(Timestamp expiresAt) {
            params.addOpt("expires_at", expiresAt);
            return this;
        }


        public EditCreateSubForCustomerQuoteRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public EditCreateSubForCustomerQuoteRequest mandatoryAddonsToRemove(List<String> mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest mandatoryAddonsToRemove(String... mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }






        public EditCreateSubForCustomerQuoteRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }




        public EditCreateSubForCustomerQuoteRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription[id]", subscriptionId);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest subscriptionPlanId(String subscriptionPlanId) {
            params.add("subscription[plan_id]", subscriptionPlanId);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest subscriptionPlanQuantity(Integer subscriptionPlanQuantity) {
            params.addOpt("subscription[plan_quantity]", subscriptionPlanQuantity);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest subscriptionPlanQuantityInDecimal(String subscriptionPlanQuantityInDecimal) {
            params.addOpt("subscription[plan_quantity_in_decimal]", subscriptionPlanQuantityInDecimal);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest subscriptionPlanUnitPrice(Long subscriptionPlanUnitPrice) {
            params.addOpt("subscription[plan_unit_price]", subscriptionPlanUnitPrice);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest subscriptionPlanUnitPriceInDecimal(String subscriptionPlanUnitPriceInDecimal) {
            params.addOpt("subscription[plan_unit_price_in_decimal]", subscriptionPlanUnitPriceInDecimal);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest subscriptionContractTermBillingCycleOnRenewal(Integer subscriptionContractTermBillingCycleOnRenewal) {
            params.addOpt("subscription[contract_term_billing_cycle_on_renewal]", subscriptionContractTermBillingCycleOnRenewal);
            return this;
        }

        public EditCreateSubForCustomerQuoteRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public EditCreateSubForCustomerQuoteRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public EditCreateSubForCustomerQuoteRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public EditCreateSubForCustomerQuoteRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public EditCreateSubForCustomerQuoteRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public EditCreateSubForCustomerQuoteRequest addonBillingCycles(int index, Integer addonBillingCycles) {
            params.addOpt("addons[billing_cycles][" + index + "]", addonBillingCycles);
            return this;
        }
        public EditCreateSubForCustomerQuoteRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public EditCreateSubForCustomerQuoteRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public EditCreateSubForCustomerQuoteRequest eventBasedAddonUnitPrice(int index, Long eventBasedAddonUnitPrice) {
            params.addOpt("event_based_addons[unit_price][" + index + "]", eventBasedAddonUnitPrice);
            return this;
        }
        public EditCreateSubForCustomerQuoteRequest eventBasedAddonQuantityInDecimal(int index, String eventBasedAddonQuantityInDecimal) {
            params.addOpt("event_based_addons[quantity_in_decimal][" + index + "]", eventBasedAddonQuantityInDecimal);
            return this;
        }
        public EditCreateSubForCustomerQuoteRequest eventBasedAddonUnitPriceInDecimal(int index, String eventBasedAddonUnitPriceInDecimal) {
            params.addOpt("event_based_addons[unit_price_in_decimal][" + index + "]", eventBasedAddonUnitPriceInDecimal);
            return this;
        }
        public EditCreateSubForCustomerQuoteRequest eventBasedAddonServicePeriodInDays(int index, Integer eventBasedAddonServicePeriodInDays) {
            params.addOpt("event_based_addons[service_period_in_days][" + index + "]", eventBasedAddonServicePeriodInDays);
            return this;
        }
        public EditCreateSubForCustomerQuoteRequest eventBasedAddonOnEvent(int index, com.chargebee.models.enums.OnEvent eventBasedAddonOnEvent) {
            params.addOpt("event_based_addons[on_event][" + index + "]", eventBasedAddonOnEvent);
            return this;
        }
        public EditCreateSubForCustomerQuoteRequest eventBasedAddonChargeOnce(int index, Boolean eventBasedAddonChargeOnce) {
            params.addOpt("event_based_addons[charge_once][" + index + "]", eventBasedAddonChargeOnce);
            return this;
        }
        public EditCreateSubForCustomerQuoteRequest eventBasedAddonChargeOn(int index, com.chargebee.models.enums.ChargeOn eventBasedAddonChargeOn) {
            params.addOpt("event_based_addons[charge_on][" + index + "]", eventBasedAddonChargeOn);
            return this;
        }
        public EditCreateSubForCustomerQuoteRequest addonTrialEnd(int index, Timestamp addonTrialEnd) {
            params.addOpt("addons[trial_end][" + index + "]", addonTrialEnd);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateSubscriptionQuoteRequest extends Request<UpdateSubscriptionQuoteRequest> {

        private UpdateSubscriptionQuoteRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateSubscriptionQuoteRequest name(String name) {
            params.addOpt("name", name);
            return this;
        }


        public UpdateSubscriptionQuoteRequest notes(String notes) {
            params.addOpt("notes", notes);
            return this;
        }


        public UpdateSubscriptionQuoteRequest expiresAt(Timestamp expiresAt) {
            params.addOpt("expires_at", expiresAt);
            return this;
        }




        public UpdateSubscriptionQuoteRequest replaceAddonList(Boolean replaceAddonList) {
            params.addOpt("replace_addon_list", replaceAddonList);
            return this;
        }


        public UpdateSubscriptionQuoteRequest mandatoryAddonsToRemove(List<String> mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public UpdateSubscriptionQuoteRequest mandatoryAddonsToRemove(String... mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }



        public UpdateSubscriptionQuoteRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public UpdateSubscriptionQuoteRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }




        public UpdateSubscriptionQuoteRequest reactivateFrom(Timestamp reactivateFrom) {
            params.addOpt("reactivate_from", reactivateFrom);
            return this;
        }




        public UpdateSubscriptionQuoteRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }


        public UpdateSubscriptionQuoteRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public UpdateSubscriptionQuoteRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public UpdateSubscriptionQuoteRequest replaceCouponList(Boolean replaceCouponList) {
            params.addOpt("replace_coupon_list", replaceCouponList);
            return this;
        }




        public UpdateSubscriptionQuoteRequest changeOption(com.chargebee.models.enums.ChangeOption changeOption) {
            params.addOpt("change_option", changeOption);
            return this;
        }


        public UpdateSubscriptionQuoteRequest changesScheduledAt(Timestamp changesScheduledAt) {
            params.addOpt("changes_scheduled_at", changesScheduledAt);
            return this;
        }




        public UpdateSubscriptionQuoteRequest forceTermReset(Boolean forceTermReset) {
            params.addOpt("force_term_reset", forceTermReset);
            return this;
        }


        public UpdateSubscriptionQuoteRequest reactivate(Boolean reactivate) {
            params.addOpt("reactivate", reactivate);
            return this;
        }








        public UpdateSubscriptionQuoteRequest subscriptionId(String subscriptionId) {
            params.add("subscription[id]", subscriptionId);
            return this;
        }

        public UpdateSubscriptionQuoteRequest subscriptionPlanId(String subscriptionPlanId) {
            params.addOpt("subscription[plan_id]", subscriptionPlanId);
            return this;
        }

        public UpdateSubscriptionQuoteRequest subscriptionPlanQuantity(Integer subscriptionPlanQuantity) {
            params.addOpt("subscription[plan_quantity]", subscriptionPlanQuantity);
            return this;
        }

        public UpdateSubscriptionQuoteRequest subscriptionPlanUnitPrice(Long subscriptionPlanUnitPrice) {
            params.addOpt("subscription[plan_unit_price]", subscriptionPlanUnitPrice);
            return this;
        }

        public UpdateSubscriptionQuoteRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public UpdateSubscriptionQuoteRequest subscriptionPlanQuantityInDecimal(String subscriptionPlanQuantityInDecimal) {
            params.addOpt("subscription[plan_quantity_in_decimal]", subscriptionPlanQuantityInDecimal);
            return this;
        }

        public UpdateSubscriptionQuoteRequest subscriptionPlanUnitPriceInDecimal(String subscriptionPlanUnitPriceInDecimal) {
            params.addOpt("subscription[plan_unit_price_in_decimal]", subscriptionPlanUnitPriceInDecimal);
            return this;
        }

        public UpdateSubscriptionQuoteRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public UpdateSubscriptionQuoteRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        @Deprecated
        public UpdateSubscriptionQuoteRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public UpdateSubscriptionQuoteRequest subscriptionAutoCollection(com.chargebee.models.enums.AutoCollection subscriptionAutoCollection) {
            params.addOpt("subscription[auto_collection]", subscriptionAutoCollection);
            return this;
        }

        public UpdateSubscriptionQuoteRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
            return this;
        }

        public UpdateSubscriptionQuoteRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }

        public UpdateSubscriptionQuoteRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }

        public UpdateSubscriptionQuoteRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }

        public UpdateSubscriptionQuoteRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }

        public UpdateSubscriptionQuoteRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }

        public UpdateSubscriptionQuoteRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public UpdateSubscriptionQuoteRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public UpdateSubscriptionQuoteRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public UpdateSubscriptionQuoteRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public UpdateSubscriptionQuoteRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public UpdateSubscriptionQuoteRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }

        public UpdateSubscriptionQuoteRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public UpdateSubscriptionQuoteRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public UpdateSubscriptionQuoteRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public UpdateSubscriptionQuoteRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public UpdateSubscriptionQuoteRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public UpdateSubscriptionQuoteRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public UpdateSubscriptionQuoteRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public UpdateSubscriptionQuoteRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public UpdateSubscriptionQuoteRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public UpdateSubscriptionQuoteRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public UpdateSubscriptionQuoteRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public UpdateSubscriptionQuoteRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public UpdateSubscriptionQuoteRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public UpdateSubscriptionQuoteRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public UpdateSubscriptionQuoteRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public UpdateSubscriptionQuoteRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public UpdateSubscriptionQuoteRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public UpdateSubscriptionQuoteRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public UpdateSubscriptionQuoteRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
            return this;
        }

        public UpdateSubscriptionQuoteRequest customerRegisteredForGst(Boolean customerRegisteredForGst) {
            params.addOpt("customer[registered_for_gst]", customerRegisteredForGst);
            return this;
        }

        public UpdateSubscriptionQuoteRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public UpdateSubscriptionQuoteRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public UpdateSubscriptionQuoteRequest subscriptionContractTermBillingCycleOnRenewal(Integer subscriptionContractTermBillingCycleOnRenewal) {
            params.addOpt("subscription[contract_term_billing_cycle_on_renewal]", subscriptionContractTermBillingCycleOnRenewal);
            return this;
        }

        public UpdateSubscriptionQuoteRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public UpdateSubscriptionQuoteRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public UpdateSubscriptionQuoteRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public UpdateSubscriptionQuoteRequest addonBillingCycles(int index, Integer addonBillingCycles) {
            params.addOpt("addons[billing_cycles][" + index + "]", addonBillingCycles);
            return this;
        }
        public UpdateSubscriptionQuoteRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public UpdateSubscriptionQuoteRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public UpdateSubscriptionQuoteRequest eventBasedAddonUnitPrice(int index, Long eventBasedAddonUnitPrice) {
            params.addOpt("event_based_addons[unit_price][" + index + "]", eventBasedAddonUnitPrice);
            return this;
        }
        public UpdateSubscriptionQuoteRequest eventBasedAddonServicePeriodInDays(int index, Integer eventBasedAddonServicePeriodInDays) {
            params.addOpt("event_based_addons[service_period_in_days][" + index + "]", eventBasedAddonServicePeriodInDays);
            return this;
        }
        public UpdateSubscriptionQuoteRequest eventBasedAddonChargeOn(int index, com.chargebee.models.enums.ChargeOn eventBasedAddonChargeOn) {
            params.addOpt("event_based_addons[charge_on][" + index + "]", eventBasedAddonChargeOn);
            return this;
        }
        public UpdateSubscriptionQuoteRequest eventBasedAddonOnEvent(int index, com.chargebee.models.enums.OnEvent eventBasedAddonOnEvent) {
            params.addOpt("event_based_addons[on_event][" + index + "]", eventBasedAddonOnEvent);
            return this;
        }
        public UpdateSubscriptionQuoteRequest eventBasedAddonChargeOnce(int index, Boolean eventBasedAddonChargeOnce) {
            params.addOpt("event_based_addons[charge_once][" + index + "]", eventBasedAddonChargeOnce);
            return this;
        }
        public UpdateSubscriptionQuoteRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public UpdateSubscriptionQuoteRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public UpdateSubscriptionQuoteRequest eventBasedAddonQuantityInDecimal(int index, String eventBasedAddonQuantityInDecimal) {
            params.addOpt("event_based_addons[quantity_in_decimal][" + index + "]", eventBasedAddonQuantityInDecimal);
            return this;
        }
        public UpdateSubscriptionQuoteRequest eventBasedAddonUnitPriceInDecimal(int index, String eventBasedAddonUnitPriceInDecimal) {
            params.addOpt("event_based_addons[unit_price_in_decimal][" + index + "]", eventBasedAddonUnitPriceInDecimal);
            return this;
        }
        public UpdateSubscriptionQuoteRequest addonTrialEnd(int index, Timestamp addonTrialEnd) {
            params.addOpt("addons[trial_end][" + index + "]", addonTrialEnd);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class EditUpdateSubscriptionQuoteRequest extends Request<EditUpdateSubscriptionQuoteRequest> {

        private EditUpdateSubscriptionQuoteRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public EditUpdateSubscriptionQuoteRequest notes(String notes) {
            params.addOpt("notes", notes);
            return this;
        }


        public EditUpdateSubscriptionQuoteRequest expiresAt(Timestamp expiresAt) {
            params.addOpt("expires_at", expiresAt);
            return this;
        }


        public EditUpdateSubscriptionQuoteRequest replaceAddonList(Boolean replaceAddonList) {
            params.addOpt("replace_addon_list", replaceAddonList);
            return this;
        }


        public EditUpdateSubscriptionQuoteRequest mandatoryAddonsToRemove(List<String> mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest mandatoryAddonsToRemove(String... mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }



        public EditUpdateSubscriptionQuoteRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public EditUpdateSubscriptionQuoteRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }




        public EditUpdateSubscriptionQuoteRequest reactivateFrom(Timestamp reactivateFrom) {
            params.addOpt("reactivate_from", reactivateFrom);
            return this;
        }




        public EditUpdateSubscriptionQuoteRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }


        public EditUpdateSubscriptionQuoteRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest replaceCouponList(Boolean replaceCouponList) {
            params.addOpt("replace_coupon_list", replaceCouponList);
            return this;
        }




        public EditUpdateSubscriptionQuoteRequest changeOption(com.chargebee.models.enums.ChangeOption changeOption) {
            params.addOpt("change_option", changeOption);
            return this;
        }


        public EditUpdateSubscriptionQuoteRequest changesScheduledAt(Timestamp changesScheduledAt) {
            params.addOpt("changes_scheduled_at", changesScheduledAt);
            return this;
        }




        public EditUpdateSubscriptionQuoteRequest forceTermReset(Boolean forceTermReset) {
            params.addOpt("force_term_reset", forceTermReset);
            return this;
        }


        public EditUpdateSubscriptionQuoteRequest reactivate(Boolean reactivate) {
            params.addOpt("reactivate", reactivate);
            return this;
        }






        public EditUpdateSubscriptionQuoteRequest subscriptionPlanId(String subscriptionPlanId) {
            params.addOpt("subscription[plan_id]", subscriptionPlanId);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest subscriptionPlanQuantity(Integer subscriptionPlanQuantity) {
            params.addOpt("subscription[plan_quantity]", subscriptionPlanQuantity);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest subscriptionPlanUnitPrice(Long subscriptionPlanUnitPrice) {
            params.addOpt("subscription[plan_unit_price]", subscriptionPlanUnitPrice);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest subscriptionPlanQuantityInDecimal(String subscriptionPlanQuantityInDecimal) {
            params.addOpt("subscription[plan_quantity_in_decimal]", subscriptionPlanQuantityInDecimal);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest subscriptionPlanUnitPriceInDecimal(String subscriptionPlanUnitPriceInDecimal) {
            params.addOpt("subscription[plan_unit_price_in_decimal]", subscriptionPlanUnitPriceInDecimal);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        @Deprecated
        public EditUpdateSubscriptionQuoteRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest subscriptionAutoCollection(com.chargebee.models.enums.AutoCollection subscriptionAutoCollection) {
            params.addOpt("subscription[auto_collection]", subscriptionAutoCollection);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest customerRegisteredForGst(Boolean customerRegisteredForGst) {
            params.addOpt("customer[registered_for_gst]", customerRegisteredForGst);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest subscriptionContractTermBillingCycleOnRenewal(Integer subscriptionContractTermBillingCycleOnRenewal) {
            params.addOpt("subscription[contract_term_billing_cycle_on_renewal]", subscriptionContractTermBillingCycleOnRenewal);
            return this;
        }

        public EditUpdateSubscriptionQuoteRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public EditUpdateSubscriptionQuoteRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public EditUpdateSubscriptionQuoteRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public EditUpdateSubscriptionQuoteRequest addonBillingCycles(int index, Integer addonBillingCycles) {
            params.addOpt("addons[billing_cycles][" + index + "]", addonBillingCycles);
            return this;
        }
        public EditUpdateSubscriptionQuoteRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public EditUpdateSubscriptionQuoteRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public EditUpdateSubscriptionQuoteRequest eventBasedAddonUnitPrice(int index, Long eventBasedAddonUnitPrice) {
            params.addOpt("event_based_addons[unit_price][" + index + "]", eventBasedAddonUnitPrice);
            return this;
        }
        public EditUpdateSubscriptionQuoteRequest eventBasedAddonServicePeriodInDays(int index, Integer eventBasedAddonServicePeriodInDays) {
            params.addOpt("event_based_addons[service_period_in_days][" + index + "]", eventBasedAddonServicePeriodInDays);
            return this;
        }
        public EditUpdateSubscriptionQuoteRequest eventBasedAddonChargeOn(int index, com.chargebee.models.enums.ChargeOn eventBasedAddonChargeOn) {
            params.addOpt("event_based_addons[charge_on][" + index + "]", eventBasedAddonChargeOn);
            return this;
        }
        public EditUpdateSubscriptionQuoteRequest eventBasedAddonOnEvent(int index, com.chargebee.models.enums.OnEvent eventBasedAddonOnEvent) {
            params.addOpt("event_based_addons[on_event][" + index + "]", eventBasedAddonOnEvent);
            return this;
        }
        public EditUpdateSubscriptionQuoteRequest eventBasedAddonChargeOnce(int index, Boolean eventBasedAddonChargeOnce) {
            params.addOpt("event_based_addons[charge_once][" + index + "]", eventBasedAddonChargeOnce);
            return this;
        }
        public EditUpdateSubscriptionQuoteRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public EditUpdateSubscriptionQuoteRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public EditUpdateSubscriptionQuoteRequest eventBasedAddonQuantityInDecimal(int index, String eventBasedAddonQuantityInDecimal) {
            params.addOpt("event_based_addons[quantity_in_decimal][" + index + "]", eventBasedAddonQuantityInDecimal);
            return this;
        }
        public EditUpdateSubscriptionQuoteRequest eventBasedAddonUnitPriceInDecimal(int index, String eventBasedAddonUnitPriceInDecimal) {
            params.addOpt("event_based_addons[unit_price_in_decimal][" + index + "]", eventBasedAddonUnitPriceInDecimal);
            return this;
        }
        public EditUpdateSubscriptionQuoteRequest addonTrialEnd(int index, Timestamp addonTrialEnd) {
            params.addOpt("addons[trial_end][" + index + "]", addonTrialEnd);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateForOnetimeChargesRequest extends Request<CreateForOnetimeChargesRequest> {

        private CreateForOnetimeChargesRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateForOnetimeChargesRequest name(String name) {
            params.addOpt("name", name);
            return this;
        }


        public CreateForOnetimeChargesRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public CreateForOnetimeChargesRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public CreateForOnetimeChargesRequest notes(String notes) {
            params.addOpt("notes", notes);
            return this;
        }


        public CreateForOnetimeChargesRequest expiresAt(Timestamp expiresAt) {
            params.addOpt("expires_at", expiresAt);
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


        public CreateForOnetimeChargesRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateForOnetimeChargesRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
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
        public CreateForOnetimeChargesRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public CreateForOnetimeChargesRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public CreateForOnetimeChargesRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public CreateForOnetimeChargesRequest addonServicePeriod(int index, Integer addonServicePeriod) {
            params.addOpt("addons[service_period][" + index + "]", addonServicePeriod);
            return this;
        }
        public CreateForOnetimeChargesRequest chargeAmount(int index, Long chargeAmount) {
            params.addOpt("charges[amount][" + index + "]", chargeAmount);
            return this;
        }
        public CreateForOnetimeChargesRequest chargeAmountInDecimal(int index, String chargeAmountInDecimal) {
            params.addOpt("charges[amount_in_decimal][" + index + "]", chargeAmountInDecimal);
            return this;
        }
        public CreateForOnetimeChargesRequest chargeDescription(int index, String chargeDescription) {
            params.addOpt("charges[description][" + index + "]", chargeDescription);
            return this;
        }
        public CreateForOnetimeChargesRequest chargeAvalaraSaleType(int index, com.chargebee.models.enums.AvalaraSaleType chargeAvalaraSaleType) {
            params.addOpt("charges[avalara_sale_type][" + index + "]", chargeAvalaraSaleType);
            return this;
        }
        public CreateForOnetimeChargesRequest chargeAvalaraTransactionType(int index, Integer chargeAvalaraTransactionType) {
            params.addOpt("charges[avalara_transaction_type][" + index + "]", chargeAvalaraTransactionType);
            return this;
        }
        public CreateForOnetimeChargesRequest chargeAvalaraServiceType(int index, Integer chargeAvalaraServiceType) {
            params.addOpt("charges[avalara_service_type][" + index + "]", chargeAvalaraServiceType);
            return this;
        }
        public CreateForOnetimeChargesRequest chargeServicePeriod(int index, Integer chargeServicePeriod) {
            params.addOpt("charges[service_period][" + index + "]", chargeServicePeriod);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class EditOneTimeQuoteRequest extends Request<EditOneTimeQuoteRequest> {

        private EditOneTimeQuoteRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public EditOneTimeQuoteRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public EditOneTimeQuoteRequest notes(String notes) {
            params.addOpt("notes", notes);
            return this;
        }


        public EditOneTimeQuoteRequest expiresAt(Timestamp expiresAt) {
            params.addOpt("expires_at", expiresAt);
            return this;
        }


        public EditOneTimeQuoteRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public EditOneTimeQuoteRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public EditOneTimeQuoteRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public EditOneTimeQuoteRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public EditOneTimeQuoteRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public EditOneTimeQuoteRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public EditOneTimeQuoteRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public EditOneTimeQuoteRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public EditOneTimeQuoteRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public EditOneTimeQuoteRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public EditOneTimeQuoteRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public EditOneTimeQuoteRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public EditOneTimeQuoteRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public EditOneTimeQuoteRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public EditOneTimeQuoteRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public EditOneTimeQuoteRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public EditOneTimeQuoteRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public EditOneTimeQuoteRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public EditOneTimeQuoteRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public EditOneTimeQuoteRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public EditOneTimeQuoteRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public EditOneTimeQuoteRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public EditOneTimeQuoteRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public EditOneTimeQuoteRequest addonServicePeriod(int index, Integer addonServicePeriod) {
            params.addOpt("addons[service_period][" + index + "]", addonServicePeriod);
            return this;
        }
        public EditOneTimeQuoteRequest chargeAmount(int index, Long chargeAmount) {
            params.addOpt("charges[amount][" + index + "]", chargeAmount);
            return this;
        }
        public EditOneTimeQuoteRequest chargeAmountInDecimal(int index, String chargeAmountInDecimal) {
            params.addOpt("charges[amount_in_decimal][" + index + "]", chargeAmountInDecimal);
            return this;
        }
        public EditOneTimeQuoteRequest chargeDescription(int index, String chargeDescription) {
            params.addOpt("charges[description][" + index + "]", chargeDescription);
            return this;
        }
        public EditOneTimeQuoteRequest chargeAvalaraSaleType(int index, com.chargebee.models.enums.AvalaraSaleType chargeAvalaraSaleType) {
            params.addOpt("charges[avalara_sale_type][" + index + "]", chargeAvalaraSaleType);
            return this;
        }
        public EditOneTimeQuoteRequest chargeAvalaraTransactionType(int index, Integer chargeAvalaraTransactionType) {
            params.addOpt("charges[avalara_transaction_type][" + index + "]", chargeAvalaraTransactionType);
            return this;
        }
        public EditOneTimeQuoteRequest chargeAvalaraServiceType(int index, Integer chargeAvalaraServiceType) {
            params.addOpt("charges[avalara_service_type][" + index + "]", chargeAvalaraServiceType);
            return this;
        }
        public EditOneTimeQuoteRequest chargeServicePeriod(int index, Integer chargeServicePeriod) {
            params.addOpt("charges[service_period][" + index + "]", chargeServicePeriod);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateSubItemsForCustomerQuoteRequest extends Request<CreateSubItemsForCustomerQuoteRequest> {

        private CreateSubItemsForCustomerQuoteRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateSubItemsForCustomerQuoteRequest name(String name) {
            params.addOpt("name", name);
            return this;
        }


        public CreateSubItemsForCustomerQuoteRequest notes(String notes) {
            params.addOpt("notes", notes);
            return this;
        }


        public CreateSubItemsForCustomerQuoteRequest expiresAt(Timestamp expiresAt) {
            params.addOpt("expires_at", expiresAt);
            return this;
        }












        public CreateSubItemsForCustomerQuoteRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public CreateSubItemsForCustomerQuoteRequest mandatoryItemsToRemove(List<String> mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest mandatoryItemsToRemove(String... mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }






        public CreateSubItemsForCustomerQuoteRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }




        public CreateSubItemsForCustomerQuoteRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription[id]", subscriptionId);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest subscriptionPoNumber(String subscriptionPoNumber) {
            params.addOpt("subscription[po_number]", subscriptionPoNumber);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        @Deprecated
        public CreateSubItemsForCustomerQuoteRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest subscriptionContractTermBillingCycleOnRenewal(Integer subscriptionContractTermBillingCycleOnRenewal) {
            params.addOpt("subscription[contract_term_billing_cycle_on_renewal]", subscriptionContractTermBillingCycleOnRenewal);
            return this;
        }

        public CreateSubItemsForCustomerQuoteRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.add("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest subscriptionItemUnitPrice(int index, Long subscriptionItemUnitPrice) {
            params.addOpt("subscription_items[unit_price][" + index + "]", subscriptionItemUnitPrice);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest subscriptionItemUnitPriceInDecimal(int index, String subscriptionItemUnitPriceInDecimal) {
            params.addOpt("subscription_items[unit_price_in_decimal][" + index + "]", subscriptionItemUnitPriceInDecimal);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest subscriptionItemBillingCycles(int index, Integer subscriptionItemBillingCycles) {
            params.addOpt("subscription_items[billing_cycles][" + index + "]", subscriptionItemBillingCycles);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest subscriptionItemTrialEnd(int index, Timestamp subscriptionItemTrialEnd) {
            params.addOpt("subscription_items[trial_end][" + index + "]", subscriptionItemTrialEnd);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest subscriptionItemServicePeriodDays(int index, Integer subscriptionItemServicePeriodDays) {
            params.addOpt("subscription_items[service_period_days][" + index + "]", subscriptionItemServicePeriodDays);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest subscriptionItemChargeOnEvent(int index, com.chargebee.models.enums.ChargeOnEvent subscriptionItemChargeOnEvent) {
            params.addOpt("subscription_items[charge_on_event][" + index + "]", subscriptionItemChargeOnEvent);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest subscriptionItemChargeOnce(int index, Boolean subscriptionItemChargeOnce) {
            params.addOpt("subscription_items[charge_once][" + index + "]", subscriptionItemChargeOnce);
            return this;
        }
        @Deprecated
        public CreateSubItemsForCustomerQuoteRequest subscriptionItemItemType(int index, com.chargebee.models.enums.ItemType subscriptionItemItemType) {
            params.addOpt("subscription_items[item_type][" + index + "]", subscriptionItemItemType);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.add("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest discountDurationType(int index, com.chargebee.models.enums.DurationType discountDurationType) {
            params.add("discounts[duration_type][" + index + "]", discountDurationType);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest discountPeriod(int index, Integer discountPeriod) {
            params.addOpt("discounts[period][" + index + "]", discountPeriod);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest discountPeriodUnit(int index, com.chargebee.models.enums.PeriodUnit discountPeriodUnit) {
            params.addOpt("discounts[period_unit][" + index + "]", discountPeriodUnit);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest discountIncludedInMrr(int index, Boolean discountIncludedInMrr) {
            params.addOpt("discounts[included_in_mrr][" + index + "]", discountIncludedInMrr);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest subscriptionItemChargeOnOption(int index, com.chargebee.models.enums.ChargeOnOption subscriptionItemChargeOnOption) {
            params.addOpt("subscription_items[charge_on_option][" + index + "]", subscriptionItemChargeOnOption);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public CreateSubItemsForCustomerQuoteRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class EditCreateSubCustomerQuoteForItemsRequest extends Request<EditCreateSubCustomerQuoteForItemsRequest> {

        private EditCreateSubCustomerQuoteForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    


        public EditCreateSubCustomerQuoteForItemsRequest notes(String notes) {
            params.addOpt("notes", notes);
            return this;
        }


        public EditCreateSubCustomerQuoteForItemsRequest expiresAt(Timestamp expiresAt) {
            params.addOpt("expires_at", expiresAt);
            return this;
        }


        public EditCreateSubCustomerQuoteForItemsRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public EditCreateSubCustomerQuoteForItemsRequest mandatoryItemsToRemove(List<String> mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest mandatoryItemsToRemove(String... mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }






        public EditCreateSubCustomerQuoteForItemsRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }




        public EditCreateSubCustomerQuoteForItemsRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription[id]", subscriptionId);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        @Deprecated
        public EditCreateSubCustomerQuoteForItemsRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest subscriptionContractTermBillingCycleOnRenewal(Integer subscriptionContractTermBillingCycleOnRenewal) {
            params.addOpt("subscription[contract_term_billing_cycle_on_renewal]", subscriptionContractTermBillingCycleOnRenewal);
            return this;
        }

        public EditCreateSubCustomerQuoteForItemsRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.add("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest subscriptionItemUnitPrice(int index, Long subscriptionItemUnitPrice) {
            params.addOpt("subscription_items[unit_price][" + index + "]", subscriptionItemUnitPrice);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest subscriptionItemUnitPriceInDecimal(int index, String subscriptionItemUnitPriceInDecimal) {
            params.addOpt("subscription_items[unit_price_in_decimal][" + index + "]", subscriptionItemUnitPriceInDecimal);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest subscriptionItemBillingCycles(int index, Integer subscriptionItemBillingCycles) {
            params.addOpt("subscription_items[billing_cycles][" + index + "]", subscriptionItemBillingCycles);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest subscriptionItemTrialEnd(int index, Timestamp subscriptionItemTrialEnd) {
            params.addOpt("subscription_items[trial_end][" + index + "]", subscriptionItemTrialEnd);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest subscriptionItemServicePeriodDays(int index, Integer subscriptionItemServicePeriodDays) {
            params.addOpt("subscription_items[service_period_days][" + index + "]", subscriptionItemServicePeriodDays);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest subscriptionItemChargeOnEvent(int index, com.chargebee.models.enums.ChargeOnEvent subscriptionItemChargeOnEvent) {
            params.addOpt("subscription_items[charge_on_event][" + index + "]", subscriptionItemChargeOnEvent);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest subscriptionItemChargeOnce(int index, Boolean subscriptionItemChargeOnce) {
            params.addOpt("subscription_items[charge_once][" + index + "]", subscriptionItemChargeOnce);
            return this;
        }
        @Deprecated
        public EditCreateSubCustomerQuoteForItemsRequest subscriptionItemItemType(int index, com.chargebee.models.enums.ItemType subscriptionItemItemType) {
            params.addOpt("subscription_items[item_type][" + index + "]", subscriptionItemItemType);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.add("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest discountDurationType(int index, com.chargebee.models.enums.DurationType discountDurationType) {
            params.add("discounts[duration_type][" + index + "]", discountDurationType);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest discountPeriod(int index, Integer discountPeriod) {
            params.addOpt("discounts[period][" + index + "]", discountPeriod);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest discountPeriodUnit(int index, com.chargebee.models.enums.PeriodUnit discountPeriodUnit) {
            params.addOpt("discounts[period_unit][" + index + "]", discountPeriodUnit);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest discountIncludedInMrr(int index, Boolean discountIncludedInMrr) {
            params.addOpt("discounts[included_in_mrr][" + index + "]", discountIncludedInMrr);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest subscriptionItemChargeOnOption(int index, com.chargebee.models.enums.ChargeOnOption subscriptionItemChargeOnOption) {
            params.addOpt("subscription_items[charge_on_option][" + index + "]", subscriptionItemChargeOnOption);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public EditCreateSubCustomerQuoteForItemsRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateSubscriptionQuoteForItemsRequest extends Request<UpdateSubscriptionQuoteForItemsRequest> {

        private UpdateSubscriptionQuoteForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateSubscriptionQuoteForItemsRequest name(String name) {
            params.addOpt("name", name);
            return this;
        }


        public UpdateSubscriptionQuoteForItemsRequest notes(String notes) {
            params.addOpt("notes", notes);
            return this;
        }


        public UpdateSubscriptionQuoteForItemsRequest expiresAt(Timestamp expiresAt) {
            params.addOpt("expires_at", expiresAt);
            return this;
        }




        public UpdateSubscriptionQuoteForItemsRequest mandatoryItemsToRemove(List<String> mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest mandatoryItemsToRemove(String... mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest replaceItemsList(Boolean replaceItemsList) {
            params.addOpt("replace_items_list", replaceItemsList);
            return this;
        }




        public UpdateSubscriptionQuoteForItemsRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public UpdateSubscriptionQuoteForItemsRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }




        public UpdateSubscriptionQuoteForItemsRequest reactivateFrom(Timestamp reactivateFrom) {
            params.addOpt("reactivate_from", reactivateFrom);
            return this;
        }




        public UpdateSubscriptionQuoteForItemsRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }


        public UpdateSubscriptionQuoteForItemsRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest replaceCouponList(Boolean replaceCouponList) {
            params.addOpt("replace_coupon_list", replaceCouponList);
            return this;
        }




        public UpdateSubscriptionQuoteForItemsRequest changeOption(com.chargebee.models.enums.ChangeOption changeOption) {
            params.addOpt("change_option", changeOption);
            return this;
        }


        public UpdateSubscriptionQuoteForItemsRequest changesScheduledAt(Timestamp changesScheduledAt) {
            params.addOpt("changes_scheduled_at", changesScheduledAt);
            return this;
        }




        public UpdateSubscriptionQuoteForItemsRequest forceTermReset(Boolean forceTermReset) {
            params.addOpt("force_term_reset", forceTermReset);
            return this;
        }


        public UpdateSubscriptionQuoteForItemsRequest reactivate(Boolean reactivate) {
            params.addOpt("reactivate", reactivate);
            return this;
        }








        public UpdateSubscriptionQuoteForItemsRequest subscriptionId(String subscriptionId) {
            params.add("subscription[id]", subscriptionId);
            return this;
        }

        @Deprecated
        public UpdateSubscriptionQuoteForItemsRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        @Deprecated
        public UpdateSubscriptionQuoteForItemsRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest subscriptionAutoCollection(com.chargebee.models.enums.AutoCollection subscriptionAutoCollection) {
            params.addOpt("subscription[auto_collection]", subscriptionAutoCollection);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest customerRegisteredForGst(Boolean customerRegisteredForGst) {
            params.addOpt("customer[registered_for_gst]", customerRegisteredForGst);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest subscriptionContractTermBillingCycleOnRenewal(Integer subscriptionContractTermBillingCycleOnRenewal) {
            params.addOpt("subscription[contract_term_billing_cycle_on_renewal]", subscriptionContractTermBillingCycleOnRenewal);
            return this;
        }

        public UpdateSubscriptionQuoteForItemsRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.add("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest subscriptionItemUnitPrice(int index, Long subscriptionItemUnitPrice) {
            params.addOpt("subscription_items[unit_price][" + index + "]", subscriptionItemUnitPrice);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest subscriptionItemUnitPriceInDecimal(int index, String subscriptionItemUnitPriceInDecimal) {
            params.addOpt("subscription_items[unit_price_in_decimal][" + index + "]", subscriptionItemUnitPriceInDecimal);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest subscriptionItemBillingCycles(int index, Integer subscriptionItemBillingCycles) {
            params.addOpt("subscription_items[billing_cycles][" + index + "]", subscriptionItemBillingCycles);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest subscriptionItemTrialEnd(int index, Timestamp subscriptionItemTrialEnd) {
            params.addOpt("subscription_items[trial_end][" + index + "]", subscriptionItemTrialEnd);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest subscriptionItemServicePeriodDays(int index, Integer subscriptionItemServicePeriodDays) {
            params.addOpt("subscription_items[service_period_days][" + index + "]", subscriptionItemServicePeriodDays);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest subscriptionItemChargeOnEvent(int index, com.chargebee.models.enums.ChargeOnEvent subscriptionItemChargeOnEvent) {
            params.addOpt("subscription_items[charge_on_event][" + index + "]", subscriptionItemChargeOnEvent);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest subscriptionItemChargeOnce(int index, Boolean subscriptionItemChargeOnce) {
            params.addOpt("subscription_items[charge_once][" + index + "]", subscriptionItemChargeOnce);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest subscriptionItemChargeOnOption(int index, com.chargebee.models.enums.ChargeOnOption subscriptionItemChargeOnOption) {
            params.addOpt("subscription_items[charge_on_option][" + index + "]", subscriptionItemChargeOnOption);
            return this;
        }
        @Deprecated
        public UpdateSubscriptionQuoteForItemsRequest subscriptionItemItemType(int index, com.chargebee.models.enums.ItemType subscriptionItemItemType) {
            params.addOpt("subscription_items[item_type][" + index + "]", subscriptionItemItemType);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.add("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest discountDurationType(int index, com.chargebee.models.enums.DurationType discountDurationType) {
            params.add("discounts[duration_type][" + index + "]", discountDurationType);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest discountPeriod(int index, Integer discountPeriod) {
            params.addOpt("discounts[period][" + index + "]", discountPeriod);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest discountPeriodUnit(int index, com.chargebee.models.enums.PeriodUnit discountPeriodUnit) {
            params.addOpt("discounts[period_unit][" + index + "]", discountPeriodUnit);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest discountIncludedInMrr(int index, Boolean discountIncludedInMrr) {
            params.addOpt("discounts[included_in_mrr][" + index + "]", discountIncludedInMrr);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest discountOperationType(int index, com.chargebee.models.enums.OperationType discountOperationType) {
            params.add("discounts[operation_type][" + index + "]", discountOperationType);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest discountId(int index, String discountId) {
            params.addOpt("discounts[id][" + index + "]", discountId);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public UpdateSubscriptionQuoteForItemsRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class EditUpdateSubscriptionQuoteForItemsRequest extends Request<EditUpdateSubscriptionQuoteForItemsRequest> {

        private EditUpdateSubscriptionQuoteForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public EditUpdateSubscriptionQuoteForItemsRequest notes(String notes) {
            params.addOpt("notes", notes);
            return this;
        }


        public EditUpdateSubscriptionQuoteForItemsRequest expiresAt(Timestamp expiresAt) {
            params.addOpt("expires_at", expiresAt);
            return this;
        }


        public EditUpdateSubscriptionQuoteForItemsRequest mandatoryItemsToRemove(List<String> mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest mandatoryItemsToRemove(String... mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest replaceItemsList(Boolean replaceItemsList) {
            params.addOpt("replace_items_list", replaceItemsList);
            return this;
        }




        public EditUpdateSubscriptionQuoteForItemsRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public EditUpdateSubscriptionQuoteForItemsRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }




        public EditUpdateSubscriptionQuoteForItemsRequest reactivateFrom(Timestamp reactivateFrom) {
            params.addOpt("reactivate_from", reactivateFrom);
            return this;
        }




        public EditUpdateSubscriptionQuoteForItemsRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }


        public EditUpdateSubscriptionQuoteForItemsRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest replaceCouponList(Boolean replaceCouponList) {
            params.addOpt("replace_coupon_list", replaceCouponList);
            return this;
        }




        public EditUpdateSubscriptionQuoteForItemsRequest changeOption(com.chargebee.models.enums.ChangeOption changeOption) {
            params.addOpt("change_option", changeOption);
            return this;
        }


        public EditUpdateSubscriptionQuoteForItemsRequest changesScheduledAt(Timestamp changesScheduledAt) {
            params.addOpt("changes_scheduled_at", changesScheduledAt);
            return this;
        }




        public EditUpdateSubscriptionQuoteForItemsRequest forceTermReset(Boolean forceTermReset) {
            params.addOpt("force_term_reset", forceTermReset);
            return this;
        }


        public EditUpdateSubscriptionQuoteForItemsRequest reactivate(Boolean reactivate) {
            params.addOpt("reactivate", reactivate);
            return this;
        }






        @Deprecated
        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionSetupFee(Long subscriptionSetupFee) {
            params.addOpt("subscription[setup_fee]", subscriptionSetupFee);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionStartDate(Timestamp subscriptionStartDate) {
            params.addOpt("subscription[start_date]", subscriptionStartDate);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionTrialEnd(Timestamp subscriptionTrialEnd) {
            params.addOpt("subscription[trial_end]", subscriptionTrialEnd);
            return this;
        }

        @Deprecated
        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionCoupon(String subscriptionCoupon) {
            params.addOpt("subscription[coupon]", subscriptionCoupon);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionAutoCollection(com.chargebee.models.enums.AutoCollection subscriptionAutoCollection) {
            params.addOpt("subscription[auto_collection]", subscriptionAutoCollection);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod subscriptionOfflinePaymentMethod) {
            params.addOpt("subscription[offline_payment_method]", subscriptionOfflinePaymentMethod);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest customerRegisteredForGst(Boolean customerRegisteredForGst) {
            params.addOpt("customer[registered_for_gst]", customerRegisteredForGst);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionContractTermBillingCycleOnRenewal(Integer subscriptionContractTermBillingCycleOnRenewal) {
            params.addOpt("subscription[contract_term_billing_cycle_on_renewal]", subscriptionContractTermBillingCycleOnRenewal);
            return this;
        }

        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.add("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionItemUnitPrice(int index, Long subscriptionItemUnitPrice) {
            params.addOpt("subscription_items[unit_price][" + index + "]", subscriptionItemUnitPrice);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionItemUnitPriceInDecimal(int index, String subscriptionItemUnitPriceInDecimal) {
            params.addOpt("subscription_items[unit_price_in_decimal][" + index + "]", subscriptionItemUnitPriceInDecimal);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionItemBillingCycles(int index, Integer subscriptionItemBillingCycles) {
            params.addOpt("subscription_items[billing_cycles][" + index + "]", subscriptionItemBillingCycles);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionItemTrialEnd(int index, Timestamp subscriptionItemTrialEnd) {
            params.addOpt("subscription_items[trial_end][" + index + "]", subscriptionItemTrialEnd);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionItemServicePeriodDays(int index, Integer subscriptionItemServicePeriodDays) {
            params.addOpt("subscription_items[service_period_days][" + index + "]", subscriptionItemServicePeriodDays);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionItemChargeOnEvent(int index, com.chargebee.models.enums.ChargeOnEvent subscriptionItemChargeOnEvent) {
            params.addOpt("subscription_items[charge_on_event][" + index + "]", subscriptionItemChargeOnEvent);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionItemChargeOnce(int index, Boolean subscriptionItemChargeOnce) {
            params.addOpt("subscription_items[charge_once][" + index + "]", subscriptionItemChargeOnce);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionItemChargeOnOption(int index, com.chargebee.models.enums.ChargeOnOption subscriptionItemChargeOnOption) {
            params.addOpt("subscription_items[charge_on_option][" + index + "]", subscriptionItemChargeOnOption);
            return this;
        }
        @Deprecated
        public EditUpdateSubscriptionQuoteForItemsRequest subscriptionItemItemType(int index, com.chargebee.models.enums.ItemType subscriptionItemItemType) {
            params.addOpt("subscription_items[item_type][" + index + "]", subscriptionItemItemType);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.add("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest discountDurationType(int index, com.chargebee.models.enums.DurationType discountDurationType) {
            params.add("discounts[duration_type][" + index + "]", discountDurationType);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest discountPeriod(int index, Integer discountPeriod) {
            params.addOpt("discounts[period][" + index + "]", discountPeriod);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest discountPeriodUnit(int index, com.chargebee.models.enums.PeriodUnit discountPeriodUnit) {
            params.addOpt("discounts[period_unit][" + index + "]", discountPeriodUnit);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest discountIncludedInMrr(int index, Boolean discountIncludedInMrr) {
            params.addOpt("discounts[included_in_mrr][" + index + "]", discountIncludedInMrr);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest discountOperationType(int index, com.chargebee.models.enums.OperationType discountOperationType) {
            params.add("discounts[operation_type][" + index + "]", discountOperationType);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest discountId(int index, String discountId) {
            params.addOpt("discounts[id][" + index + "]", discountId);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public EditUpdateSubscriptionQuoteForItemsRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
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
    
        public CreateForChargeItemsAndChargesRequest name(String name) {
            params.addOpt("name", name);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest notes(String notes) {
            params.addOpt("notes", notes);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest expiresAt(Timestamp expiresAt) {
            params.addOpt("expires_at", expiresAt);
            return this;
        }


        public CreateForChargeItemsAndChargesRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


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
        public CreateForChargeItemsAndChargesRequest itemPriceServicePeriodDays(int index, Integer itemPriceServicePeriodDays) {
            params.addOpt("item_prices[service_period_days][" + index + "]", itemPriceServicePeriodDays);
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
        public CreateForChargeItemsAndChargesRequest chargeServicePeriod(int index, Integer chargeServicePeriod) {
            params.addOpt("charges[service_period][" + index + "]", chargeServicePeriod);
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

    public static class EditForChargeItemsAndChargesRequest extends Request<EditForChargeItemsAndChargesRequest> {

        private EditForChargeItemsAndChargesRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public EditForChargeItemsAndChargesRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public EditForChargeItemsAndChargesRequest notes(String notes) {
            params.addOpt("notes", notes);
            return this;
        }


        public EditForChargeItemsAndChargesRequest expiresAt(Timestamp expiresAt) {
            params.addOpt("expires_at", expiresAt);
            return this;
        }


        public EditForChargeItemsAndChargesRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public EditForChargeItemsAndChargesRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public EditForChargeItemsAndChargesRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public EditForChargeItemsAndChargesRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public EditForChargeItemsAndChargesRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public EditForChargeItemsAndChargesRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public EditForChargeItemsAndChargesRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public EditForChargeItemsAndChargesRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public EditForChargeItemsAndChargesRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public EditForChargeItemsAndChargesRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public EditForChargeItemsAndChargesRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public EditForChargeItemsAndChargesRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public EditForChargeItemsAndChargesRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public EditForChargeItemsAndChargesRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public EditForChargeItemsAndChargesRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public EditForChargeItemsAndChargesRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public EditForChargeItemsAndChargesRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public EditForChargeItemsAndChargesRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public EditForChargeItemsAndChargesRequest itemPriceItemPriceId(int index, String itemPriceItemPriceId) {
            params.addOpt("item_prices[item_price_id][" + index + "]", itemPriceItemPriceId);
            return this;
        }
        public EditForChargeItemsAndChargesRequest itemPriceQuantity(int index, Integer itemPriceQuantity) {
            params.addOpt("item_prices[quantity][" + index + "]", itemPriceQuantity);
            return this;
        }
        public EditForChargeItemsAndChargesRequest itemPriceQuantityInDecimal(int index, String itemPriceQuantityInDecimal) {
            params.addOpt("item_prices[quantity_in_decimal][" + index + "]", itemPriceQuantityInDecimal);
            return this;
        }
        public EditForChargeItemsAndChargesRequest itemPriceUnitPrice(int index, Long itemPriceUnitPrice) {
            params.addOpt("item_prices[unit_price][" + index + "]", itemPriceUnitPrice);
            return this;
        }
        public EditForChargeItemsAndChargesRequest itemPriceUnitPriceInDecimal(int index, String itemPriceUnitPriceInDecimal) {
            params.addOpt("item_prices[unit_price_in_decimal][" + index + "]", itemPriceUnitPriceInDecimal);
            return this;
        }
        public EditForChargeItemsAndChargesRequest itemPriceServicePeriodDays(int index, Integer itemPriceServicePeriodDays) {
            params.addOpt("item_prices[service_period_days][" + index + "]", itemPriceServicePeriodDays);
            return this;
        }
        public EditForChargeItemsAndChargesRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public EditForChargeItemsAndChargesRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public EditForChargeItemsAndChargesRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public EditForChargeItemsAndChargesRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public EditForChargeItemsAndChargesRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public EditForChargeItemsAndChargesRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public EditForChargeItemsAndChargesRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            return this;
        }
        public EditForChargeItemsAndChargesRequest chargeAmount(int index, Long chargeAmount) {
            params.addOpt("charges[amount][" + index + "]", chargeAmount);
            return this;
        }
        public EditForChargeItemsAndChargesRequest chargeAmountInDecimal(int index, String chargeAmountInDecimal) {
            params.addOpt("charges[amount_in_decimal][" + index + "]", chargeAmountInDecimal);
            return this;
        }
        public EditForChargeItemsAndChargesRequest chargeDescription(int index, String chargeDescription) {
            params.addOpt("charges[description][" + index + "]", chargeDescription);
            return this;
        }
        public EditForChargeItemsAndChargesRequest chargeAvalaraSaleType(int index, com.chargebee.models.enums.AvalaraSaleType chargeAvalaraSaleType) {
            params.addOpt("charges[avalara_sale_type][" + index + "]", chargeAvalaraSaleType);
            return this;
        }
        public EditForChargeItemsAndChargesRequest chargeAvalaraTransactionType(int index, Integer chargeAvalaraTransactionType) {
            params.addOpt("charges[avalara_transaction_type][" + index + "]", chargeAvalaraTransactionType);
            return this;
        }
        public EditForChargeItemsAndChargesRequest chargeAvalaraServiceType(int index, Integer chargeAvalaraServiceType) {
            params.addOpt("charges[avalara_service_type][" + index + "]", chargeAvalaraServiceType);
            return this;
        }
        public EditForChargeItemsAndChargesRequest chargeServicePeriod(int index, Integer chargeServicePeriod) {
            params.addOpt("charges[service_period][" + index + "]", chargeServicePeriod);
            return this;
        }
        public EditForChargeItemsAndChargesRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public EditForChargeItemsAndChargesRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public EditForChargeItemsAndChargesRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.add("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public EditForChargeItemsAndChargesRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class QuoteListRequest extends ListRequest<QuoteListRequest> {

        private QuoteListRequest(String uri) {
            super(uri);
        }
    
        public QuoteListRequest includeDeleted(Boolean includeDeleted) {
            params.addOpt("include_deleted", includeDeleted);
            return this;
        }


        public StringFilter<QuoteListRequest> id() {
            return new StringFilter<QuoteListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<QuoteListRequest> customerId() {
            return new StringFilter<QuoteListRequest>("customer_id",this).supportsMultiOperators(true);        
        }


        public StringFilter<QuoteListRequest> subscriptionId() {
            return new StringFilter<QuoteListRequest>("subscription_id",this).supportsMultiOperators(true).supportsPresenceOperator(true);        
        }


        public EnumFilter<Quote.Status, QuoteListRequest> status() {
            return new EnumFilter<Quote.Status, QuoteListRequest>("status",this);        
        }


        public TimestampFilter<QuoteListRequest> date() {
            return new TimestampFilter<QuoteListRequest>("date",this);        
        }


        public TimestampFilter<QuoteListRequest> updatedAt() {
            return new TimestampFilter<QuoteListRequest>("updated_at",this);        
        }


        public QuoteListRequest sortByDate(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","date");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class ConvertRequest extends Request<ConvertRequest> {

        private ConvertRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ConvertRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }




        public ConvertRequest createPendingInvoices(Boolean createPendingInvoices) {
            params.addOpt("create_pending_invoices", createPendingInvoices);
            return this;
        }


        public ConvertRequest firstInvoicePending(Boolean firstInvoicePending) {
            params.addOpt("first_invoice_pending", firstInvoicePending);
            return this;
        }


        public ConvertRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription[id]", subscriptionId);
            return this;
        }

        public ConvertRequest subscriptionAutoCollection(com.chargebee.models.enums.AutoCollection subscriptionAutoCollection) {
            params.addOpt("subscription[auto_collection]", subscriptionAutoCollection);
            return this;
        }

        public ConvertRequest subscriptionPoNumber(String subscriptionPoNumber) {
            params.addOpt("subscription[po_number]", subscriptionPoNumber);
            return this;
        }

        public ConvertRequest subscriptionAutoCloseInvoices(Boolean subscriptionAutoCloseInvoices) {
            params.addOpt("subscription[auto_close_invoices]", subscriptionAutoCloseInvoices);
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

    public static class ExtendExpiryDateRequest extends Request<ExtendExpiryDateRequest> {

        private ExtendExpiryDateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ExtendExpiryDateRequest validTill(Timestamp validTill) {
            params.add("valid_till", validTill);
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

    public static class PdfRequest extends Request<PdfRequest> {

        private PdfRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public PdfRequest consolidatedView(Boolean consolidatedView) {
            params.addOpt("consolidated_view", consolidatedView);
            return this;
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
