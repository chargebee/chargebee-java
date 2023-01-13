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

public class UnbilledCharge extends Resource<UnbilledCharge> {

    public enum EntityType {
        PLAN_SETUP,
        PLAN,
        ADDON,
        ADHOC,
        PLAN_ITEM_PRICE,
        ADDON_ITEM_PRICE,
        CHARGE_ITEM_PRICE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class Tier extends Resource<Tier> {
        public Tier(JSONObject jsonObj) {
            super(jsonObj);
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

    //Constructors
    //============

    public UnbilledCharge(String jsonStr) {
        super(jsonStr);
    }

    public UnbilledCharge(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return optString("id");
    }

    public String customerId() {
        return optString("customer_id");
    }

    public String subscriptionId() {
        return optString("subscription_id");
    }

    public Timestamp dateFrom() {
        return optTimestamp("date_from");
    }

    public Timestamp dateTo() {
        return optTimestamp("date_to");
    }

    public Long unitAmount() {
        return optLong("unit_amount");
    }

    public PricingModel pricingModel() {
        return optEnum("pricing_model", PricingModel.class);
    }

    public Integer quantity() {
        return optInteger("quantity");
    }

    public Long amount() {
        return optLong("amount");
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public Long discountAmount() {
        return optLong("discount_amount");
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

    public Boolean isVoided() {
        return reqBoolean("is_voided");
    }

    public Timestamp voidedAt() {
        return optTimestamp("voided_at");
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

    public Timestamp updatedAt() {
        return reqTimestamp("updated_at");
    }

    public List<UnbilledCharge.Tier> tiers() {
        return optList("tiers", UnbilledCharge.Tier.class);
    }

    public Boolean isAdvanceCharge() {
        return optBoolean("is_advance_charge");
    }

    public Boolean deleted() {
        return reqBoolean("deleted");
    }

    // Operations
    //===========

    public static CreateUnbilledChargeRequest createUnbilledCharge() {
        String uri = uri("unbilled_charges", "create");
        return new CreateUnbilledChargeRequest(Method.POST, uri);
    }

    public static CreateRequest create() {
        String uri = uri("unbilled_charges");
        return new CreateRequest(Method.POST, uri);
    }

    public static InvoiceUnbilledChargesRequest invoiceUnbilledCharges() {
        String uri = uri("unbilled_charges", "invoice_unbilled_charges");
        return new InvoiceUnbilledChargesRequest(Method.POST, uri);
    }

    public static Request delete(String id) {
        String uri = uri("unbilled_charges", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static UnbilledChargeListRequest list() {
        String uri = uri("unbilled_charges");
        return new UnbilledChargeListRequest(uri);
    }

    public static InvoiceNowEstimateRequest invoiceNowEstimate() {
        String uri = uri("unbilled_charges", "invoice_now_estimate");
        return new InvoiceNowEstimateRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateUnbilledChargeRequest extends Request<CreateUnbilledChargeRequest> {

        private CreateUnbilledChargeRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateUnbilledChargeRequest subscriptionId(String subscriptionId) {
            params.add("subscription_id", subscriptionId);
            return this;
        }


        public CreateUnbilledChargeRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public CreateUnbilledChargeRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public CreateUnbilledChargeRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public CreateUnbilledChargeRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public CreateUnbilledChargeRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public CreateUnbilledChargeRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public CreateUnbilledChargeRequest addonDateFrom(int index, Timestamp addonDateFrom) {
            params.addOpt("addons[date_from][" + index + "]", addonDateFrom);
            return this;
        }
        public CreateUnbilledChargeRequest addonDateTo(int index, Timestamp addonDateTo) {
            params.addOpt("addons[date_to][" + index + "]", addonDateTo);
            return this;
        }
        public CreateUnbilledChargeRequest chargeAmount(int index, Long chargeAmount) {
            params.addOpt("charges[amount][" + index + "]", chargeAmount);
            return this;
        }
        public CreateUnbilledChargeRequest chargeAmountInDecimal(int index, String chargeAmountInDecimal) {
            params.addOpt("charges[amount_in_decimal][" + index + "]", chargeAmountInDecimal);
            return this;
        }
        public CreateUnbilledChargeRequest chargeDescription(int index, String chargeDescription) {
            params.addOpt("charges[description][" + index + "]", chargeDescription);
            return this;
        }
        public CreateUnbilledChargeRequest chargeTaxable(int index, Boolean chargeTaxable) {
            params.addOpt("charges[taxable][" + index + "]", chargeTaxable);
            return this;
        }
        public CreateUnbilledChargeRequest chargeTaxProfileId(int index, String chargeTaxProfileId) {
            params.addOpt("charges[tax_profile_id][" + index + "]", chargeTaxProfileId);
            return this;
        }
        public CreateUnbilledChargeRequest chargeAvalaraTaxCode(int index, String chargeAvalaraTaxCode) {
            params.addOpt("charges[avalara_tax_code][" + index + "]", chargeAvalaraTaxCode);
            return this;
        }
        public CreateUnbilledChargeRequest chargeHsnCode(int index, String chargeHsnCode) {
            params.addOpt("charges[hsn_code][" + index + "]", chargeHsnCode);
            return this;
        }
        public CreateUnbilledChargeRequest chargeTaxjarProductCode(int index, String chargeTaxjarProductCode) {
            params.addOpt("charges[taxjar_product_code][" + index + "]", chargeTaxjarProductCode);
            return this;
        }
        public CreateUnbilledChargeRequest chargeAvalaraSaleType(int index, com.chargebee.models.enums.AvalaraSaleType chargeAvalaraSaleType) {
            params.addOpt("charges[avalara_sale_type][" + index + "]", chargeAvalaraSaleType);
            return this;
        }
        public CreateUnbilledChargeRequest chargeAvalaraTransactionType(int index, Integer chargeAvalaraTransactionType) {
            params.addOpt("charges[avalara_transaction_type][" + index + "]", chargeAvalaraTransactionType);
            return this;
        }
        public CreateUnbilledChargeRequest chargeAvalaraServiceType(int index, Integer chargeAvalaraServiceType) {
            params.addOpt("charges[avalara_service_type][" + index + "]", chargeAvalaraServiceType);
            return this;
        }
        public CreateUnbilledChargeRequest chargeDateFrom(int index, Timestamp chargeDateFrom) {
            params.addOpt("charges[date_from][" + index + "]", chargeDateFrom);
            return this;
        }
        public CreateUnbilledChargeRequest chargeDateTo(int index, Timestamp chargeDateTo) {
            params.addOpt("charges[date_to][" + index + "]", chargeDateTo);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest subscriptionId(String subscriptionId) {
            params.add("subscription_id", subscriptionId);
            return this;
        }


        public CreateRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public CreateRequest itemPriceItemPriceId(int index, String itemPriceItemPriceId) {
            params.addOpt("item_prices[item_price_id][" + index + "]", itemPriceItemPriceId);
            return this;
        }
        public CreateRequest itemPriceQuantity(int index, Integer itemPriceQuantity) {
            params.addOpt("item_prices[quantity][" + index + "]", itemPriceQuantity);
            return this;
        }
        public CreateRequest itemPriceQuantityInDecimal(int index, String itemPriceQuantityInDecimal) {
            params.addOpt("item_prices[quantity_in_decimal][" + index + "]", itemPriceQuantityInDecimal);
            return this;
        }
        public CreateRequest itemPriceUnitPrice(int index, Long itemPriceUnitPrice) {
            params.addOpt("item_prices[unit_price][" + index + "]", itemPriceUnitPrice);
            return this;
        }
        public CreateRequest itemPriceUnitPriceInDecimal(int index, String itemPriceUnitPriceInDecimal) {
            params.addOpt("item_prices[unit_price_in_decimal][" + index + "]", itemPriceUnitPriceInDecimal);
            return this;
        }
        public CreateRequest itemPriceDateFrom(int index, Timestamp itemPriceDateFrom) {
            params.addOpt("item_prices[date_from][" + index + "]", itemPriceDateFrom);
            return this;
        }
        public CreateRequest itemPriceDateTo(int index, Timestamp itemPriceDateTo) {
            params.addOpt("item_prices[date_to][" + index + "]", itemPriceDateTo);
            return this;
        }
        public CreateRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public CreateRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public CreateRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public CreateRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public CreateRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public CreateRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public CreateRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
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
        @Override
        public Params params() {
            return params;
        }
    }

    public static class InvoiceUnbilledChargesRequest extends Request<InvoiceUnbilledChargesRequest> {

        private InvoiceUnbilledChargesRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public InvoiceUnbilledChargesRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public InvoiceUnbilledChargesRequest customerId(String customerId) {
            params.addOpt("customer_id", customerId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class UnbilledChargeListRequest extends ListRequest<UnbilledChargeListRequest> {

        private UnbilledChargeListRequest(String uri) {
            super(uri);
        }
    
        public UnbilledChargeListRequest includeDeleted(Boolean includeDeleted) {
            params.addOpt("include_deleted", includeDeleted);
            return this;
        }


        public UnbilledChargeListRequest isVoided(Boolean isVoided) {
            params.addOpt("is_voided", isVoided);
            return this;
        }


        public StringFilter<UnbilledChargeListRequest> subscriptionId() {
            return new StringFilter<UnbilledChargeListRequest>("subscription_id",this).supportsMultiOperators(true).supportsPresenceOperator(true);        
        }


        public StringFilter<UnbilledChargeListRequest> customerId() {
            return new StringFilter<UnbilledChargeListRequest>("customer_id",this).supportsMultiOperators(true).supportsPresenceOperator(true);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class InvoiceNowEstimateRequest extends Request<InvoiceNowEstimateRequest> {

        private InvoiceNowEstimateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public InvoiceNowEstimateRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public InvoiceNowEstimateRequest customerId(String customerId) {
            params.addOpt("customer_id", customerId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
