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

public class ItemPrice extends Resource<ItemPrice> {

    public enum Status {
        ACTIVE,
        ARCHIVED,
        DELETED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum PeriodUnit {
        DAY,
        WEEK,
        MONTH,
        YEAR,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum TrialPeriodUnit {
        DAY,
        MONTH,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum TrialEndAction {
        SITE_DEFAULT,
        ACTIVATE_SUBSCRIPTION,
        CANCEL_SUBSCRIPTION,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum ShippingPeriodUnit {
        DAY,
        WEEK,
        MONTH,
        YEAR,
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

        public Long price() {
            return reqLong("price");
        }

        public String startingUnitInDecimal() {
            return optString("starting_unit_in_decimal");
        }

        public String endingUnitInDecimal() {
            return optString("ending_unit_in_decimal");
        }

        public String priceInDecimal() {
            return optString("price_in_decimal");
        }

    }

    public static class TaxDetail extends Resource<TaxDetail> {
        public TaxDetail(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String taxProfileId() {
            return optString("tax_profile_id");
        }

        public AvalaraSaleType avalaraSaleType() {
            return optEnum("avalara_sale_type", AvalaraSaleType.class);
        }

        public Integer avalaraTransactionType() {
            return optInteger("avalara_transaction_type");
        }

        public Integer avalaraServiceType() {
            return optInteger("avalara_service_type");
        }

        public String avalaraTaxCode() {
            return optString("avalara_tax_code");
        }

        public String hsnCode() {
            return optString("hsn_code");
        }

        public String taxjarProductCode() {
            return optString("taxjar_product_code");
        }

    }

    public static class AccountingDetail extends Resource<AccountingDetail> {
        public AccountingDetail(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String sku() {
            return optString("sku");
        }

        public String accountingCode() {
            return optString("accounting_code");
        }

        public String accountingCategory1() {
            return optString("accounting_category1");
        }

        public String accountingCategory2() {
            return optString("accounting_category2");
        }

        public String accountingCategory3() {
            return optString("accounting_category3");
        }

        public String accountingCategory4() {
            return optString("accounting_category4");
        }

    }

    //Constructors
    //============

    public ItemPrice(String jsonStr) {
        super(jsonStr);
    }

    public ItemPrice(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String name() {
        return reqString("name");
    }

    public String itemFamilyId() {
        return optString("item_family_id");
    }

    public String itemId() {
        return optString("item_id");
    }

    public String description() {
        return optString("description");
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public String externalName() {
        return optString("external_name");
    }

    public PricingModel pricingModel() {
        return reqEnum("pricing_model", PricingModel.class);
    }

    public Long price() {
        return optLong("price");
    }

    public String priceInDecimal() {
        return optString("price_in_decimal");
    }

    public Integer period() {
        return optInteger("period");
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public PeriodUnit periodUnit() {
        return optEnum("period_unit", PeriodUnit.class);
    }

    public Integer trialPeriod() {
        return optInteger("trial_period");
    }

    public TrialPeriodUnit trialPeriodUnit() {
        return optEnum("trial_period_unit", TrialPeriodUnit.class);
    }

    public TrialEndAction trialEndAction() {
        return optEnum("trial_end_action", TrialEndAction.class);
    }

    public Integer shippingPeriod() {
        return optInteger("shipping_period");
    }

    public ShippingPeriodUnit shippingPeriodUnit() {
        return optEnum("shipping_period_unit", ShippingPeriodUnit.class);
    }

    public Integer billingCycles() {
        return optInteger("billing_cycles");
    }

    public Integer freeQuantity() {
        return reqInteger("free_quantity");
    }

    public String freeQuantityInDecimal() {
        return optString("free_quantity_in_decimal");
    }

    public Channel channel() {
        return optEnum("channel", Channel.class);
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp archivedAt() {
        return optTimestamp("archived_at");
    }

    public String invoiceNotes() {
        return optString("invoice_notes");
    }

    public List<ItemPrice.Tier> tiers() {
        return optList("tiers", ItemPrice.Tier.class);
    }

    public Boolean isTaxable() {
        return optBoolean("is_taxable");
    }

    public ItemPrice.TaxDetail taxDetail() {
        return optSubResource("tax_detail", ItemPrice.TaxDetail.class);
    }

    public ItemPrice.AccountingDetail accountingDetail() {
        return optSubResource("accounting_detail", ItemPrice.AccountingDetail.class);
    }

    public JSONObject metadata() {
        return optJSONObject("metadata");
    }

    public ItemType itemType() {
        return optEnum("item_type", ItemType.class);
    }

    @Deprecated
    public Boolean archivable() {
        return optBoolean("archivable");
    }

    @Deprecated
    public String parentItemId() {
        return optString("parent_item_id");
    }

    public Boolean showDescriptionInInvoices() {
        return optBoolean("show_description_in_invoices");
    }

    public Boolean showDescriptionInQuotes() {
        return optBoolean("show_description_in_quotes");
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("item_prices");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("item_prices", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("item_prices", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static ItemPriceListRequest list() {
        String uri = uri("item_prices");
        return new ItemPriceListRequest(uri);
    }

    public static Request delete(String id) {
        String uri = uri("item_prices", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static ItemPriceFindApplicableItemsRequest findApplicableItems(String id) {
        String uri = uri("item_prices", nullCheck(id), "applicable_items");
        return new ItemPriceFindApplicableItemsRequest(uri);
    }

    public static ItemPriceFindApplicableItemPricesRequest findApplicableItemPrices(String id) {
        String uri = uri("item_prices", nullCheck(id), "applicable_item_prices");
        return new ItemPriceFindApplicableItemPricesRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest id(String id) {
            params.add("id", id);
            return this;
        }


        public CreateRequest name(String name) {
            params.add("name", name);
            return this;
        }


        public CreateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        public CreateRequest itemId(String itemId) {
            params.add("item_id", itemId);
            return this;
        }


        public CreateRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        public CreateRequest externalName(String externalName) {
            params.addOpt("external_name", externalName);
            return this;
        }


        public CreateRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public CreateRequest isTaxable(Boolean isTaxable) {
            params.addOpt("is_taxable", isTaxable);
            return this;
        }


        public CreateRequest freeQuantity(Integer freeQuantity) {
            params.addOpt("free_quantity", freeQuantity);
            return this;
        }


        public CreateRequest freeQuantityInDecimal(String freeQuantityInDecimal) {
            params.addOpt("free_quantity_in_decimal", freeQuantityInDecimal);
            return this;
        }


        public CreateRequest metadata(JSONObject metadata) {
            params.addOpt("metadata", metadata);
            return this;
        }


        public CreateRequest showDescriptionInInvoices(Boolean showDescriptionInInvoices) {
            params.addOpt("show_description_in_invoices", showDescriptionInInvoices);
            return this;
        }


        public CreateRequest showDescriptionInQuotes(Boolean showDescriptionInQuotes) {
            params.addOpt("show_description_in_quotes", showDescriptionInQuotes);
            return this;
        }




        public CreateRequest pricingModel(com.chargebee.models.enums.PricingModel pricingModel) {
            params.addOpt("pricing_model", pricingModel);
            return this;
        }


        public CreateRequest price(Long price) {
            params.addOpt("price", price);
            return this;
        }


        public CreateRequest priceInDecimal(String priceInDecimal) {
            params.addOpt("price_in_decimal", priceInDecimal);
            return this;
        }


        public CreateRequest periodUnit(ItemPrice.PeriodUnit periodUnit) {
            params.addOpt("period_unit", periodUnit);
            return this;
        }


        public CreateRequest period(Integer period) {
            params.addOpt("period", period);
            return this;
        }


        public CreateRequest trialPeriodUnit(ItemPrice.TrialPeriodUnit trialPeriodUnit) {
            params.addOpt("trial_period_unit", trialPeriodUnit);
            return this;
        }


        public CreateRequest trialPeriod(Integer trialPeriod) {
            params.addOpt("trial_period", trialPeriod);
            return this;
        }


        public CreateRequest shippingPeriod(Integer shippingPeriod) {
            params.addOpt("shipping_period", shippingPeriod);
            return this;
        }


        public CreateRequest shippingPeriodUnit(ItemPrice.ShippingPeriodUnit shippingPeriodUnit) {
            params.addOpt("shipping_period_unit", shippingPeriodUnit);
            return this;
        }


        public CreateRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public CreateRequest trialEndAction(ItemPrice.TrialEndAction trialEndAction) {
            params.addOpt("trial_end_action", trialEndAction);
            return this;
        }


        public CreateRequest taxDetailTaxProfileId(String taxDetailTaxProfileId) {
            params.addOpt("tax_detail[tax_profile_id]", taxDetailTaxProfileId);
            return this;
        }

        public CreateRequest taxDetailAvalaraTaxCode(String taxDetailAvalaraTaxCode) {
            params.addOpt("tax_detail[avalara_tax_code]", taxDetailAvalaraTaxCode);
            return this;
        }

        public CreateRequest taxDetailHsnCode(String taxDetailHsnCode) {
            params.addOpt("tax_detail[hsn_code]", taxDetailHsnCode);
            return this;
        }

        public CreateRequest taxDetailAvalaraSaleType(com.chargebee.models.enums.AvalaraSaleType taxDetailAvalaraSaleType) {
            params.addOpt("tax_detail[avalara_sale_type]", taxDetailAvalaraSaleType);
            return this;
        }

        public CreateRequest taxDetailAvalaraTransactionType(Integer taxDetailAvalaraTransactionType) {
            params.addOpt("tax_detail[avalara_transaction_type]", taxDetailAvalaraTransactionType);
            return this;
        }

        public CreateRequest taxDetailAvalaraServiceType(Integer taxDetailAvalaraServiceType) {
            params.addOpt("tax_detail[avalara_service_type]", taxDetailAvalaraServiceType);
            return this;
        }

        public CreateRequest taxDetailTaxjarProductCode(String taxDetailTaxjarProductCode) {
            params.addOpt("tax_detail[taxjar_product_code]", taxDetailTaxjarProductCode);
            return this;
        }

        public CreateRequest accountingDetailSku(String accountingDetailSku) {
            params.addOpt("accounting_detail[sku]", accountingDetailSku);
            return this;
        }

        public CreateRequest accountingDetailAccountingCode(String accountingDetailAccountingCode) {
            params.addOpt("accounting_detail[accounting_code]", accountingDetailAccountingCode);
            return this;
        }

        public CreateRequest accountingDetailAccountingCategory1(String accountingDetailAccountingCategory1) {
            params.addOpt("accounting_detail[accounting_category1]", accountingDetailAccountingCategory1);
            return this;
        }

        public CreateRequest accountingDetailAccountingCategory2(String accountingDetailAccountingCategory2) {
            params.addOpt("accounting_detail[accounting_category2]", accountingDetailAccountingCategory2);
            return this;
        }

        public CreateRequest accountingDetailAccountingCategory3(String accountingDetailAccountingCategory3) {
            params.addOpt("accounting_detail[accounting_category3]", accountingDetailAccountingCategory3);
            return this;
        }

        public CreateRequest accountingDetailAccountingCategory4(String accountingDetailAccountingCategory4) {
            params.addOpt("accounting_detail[accounting_category4]", accountingDetailAccountingCategory4);
            return this;
        }

        public CreateRequest tierStartingUnit(int index, Integer tierStartingUnit) {
            params.addOpt("tiers[starting_unit][" + index + "]", tierStartingUnit);
            return this;
        }
        public CreateRequest tierEndingUnit(int index, Integer tierEndingUnit) {
            params.addOpt("tiers[ending_unit][" + index + "]", tierEndingUnit);
            return this;
        }
        public CreateRequest tierPrice(int index, Long tierPrice) {
            params.addOpt("tiers[price][" + index + "]", tierPrice);
            return this;
        }
        public CreateRequest tierStartingUnitInDecimal(int index, String tierStartingUnitInDecimal) {
            params.addOpt("tiers[starting_unit_in_decimal][" + index + "]", tierStartingUnitInDecimal);
            return this;
        }
        public CreateRequest tierEndingUnitInDecimal(int index, String tierEndingUnitInDecimal) {
            params.addOpt("tiers[ending_unit_in_decimal][" + index + "]", tierEndingUnitInDecimal);
            return this;
        }
        public CreateRequest tierPriceInDecimal(int index, String tierPriceInDecimal) {
            params.addOpt("tiers[price_in_decimal][" + index + "]", tierPriceInDecimal);
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
    
        public UpdateRequest name(String name) {
            params.addOpt("name", name);
            return this;
        }


        public UpdateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        public UpdateRequest status(ItemPrice.Status status) {
            params.addOpt("status", status);
            return this;
        }


        public UpdateRequest externalName(String externalName) {
            params.addOpt("external_name", externalName);
            return this;
        }


        public UpdateRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public UpdateRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        public UpdateRequest isTaxable(Boolean isTaxable) {
            params.addOpt("is_taxable", isTaxable);
            return this;
        }


        public UpdateRequest freeQuantity(Integer freeQuantity) {
            params.addOpt("free_quantity", freeQuantity);
            return this;
        }


        public UpdateRequest freeQuantityInDecimal(String freeQuantityInDecimal) {
            params.addOpt("free_quantity_in_decimal", freeQuantityInDecimal);
            return this;
        }




        public UpdateRequest metadata(JSONObject metadata) {
            params.addOpt("metadata", metadata);
            return this;
        }


        public UpdateRequest pricingModel(com.chargebee.models.enums.PricingModel pricingModel) {
            params.addOpt("pricing_model", pricingModel);
            return this;
        }


        public UpdateRequest price(Long price) {
            params.addOpt("price", price);
            return this;
        }


        public UpdateRequest priceInDecimal(String priceInDecimal) {
            params.addOpt("price_in_decimal", priceInDecimal);
            return this;
        }


        public UpdateRequest periodUnit(ItemPrice.PeriodUnit periodUnit) {
            params.addOpt("period_unit", periodUnit);
            return this;
        }


        public UpdateRequest period(Integer period) {
            params.addOpt("period", period);
            return this;
        }


        public UpdateRequest trialPeriodUnit(ItemPrice.TrialPeriodUnit trialPeriodUnit) {
            params.addOpt("trial_period_unit", trialPeriodUnit);
            return this;
        }


        public UpdateRequest trialPeriod(Integer trialPeriod) {
            params.addOpt("trial_period", trialPeriod);
            return this;
        }


        public UpdateRequest shippingPeriod(Integer shippingPeriod) {
            params.addOpt("shipping_period", shippingPeriod);
            return this;
        }


        public UpdateRequest shippingPeriodUnit(ItemPrice.ShippingPeriodUnit shippingPeriodUnit) {
            params.addOpt("shipping_period_unit", shippingPeriodUnit);
            return this;
        }


        public UpdateRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public UpdateRequest trialEndAction(ItemPrice.TrialEndAction trialEndAction) {
            params.addOpt("trial_end_action", trialEndAction);
            return this;
        }


        public UpdateRequest showDescriptionInInvoices(Boolean showDescriptionInInvoices) {
            params.addOpt("show_description_in_invoices", showDescriptionInInvoices);
            return this;
        }


        public UpdateRequest showDescriptionInQuotes(Boolean showDescriptionInQuotes) {
            params.addOpt("show_description_in_quotes", showDescriptionInQuotes);
            return this;
        }


        public UpdateRequest taxDetailTaxProfileId(String taxDetailTaxProfileId) {
            params.addOpt("tax_detail[tax_profile_id]", taxDetailTaxProfileId);
            return this;
        }

        public UpdateRequest taxDetailAvalaraTaxCode(String taxDetailAvalaraTaxCode) {
            params.addOpt("tax_detail[avalara_tax_code]", taxDetailAvalaraTaxCode);
            return this;
        }

        public UpdateRequest taxDetailHsnCode(String taxDetailHsnCode) {
            params.addOpt("tax_detail[hsn_code]", taxDetailHsnCode);
            return this;
        }

        public UpdateRequest taxDetailAvalaraSaleType(com.chargebee.models.enums.AvalaraSaleType taxDetailAvalaraSaleType) {
            params.addOpt("tax_detail[avalara_sale_type]", taxDetailAvalaraSaleType);
            return this;
        }

        public UpdateRequest taxDetailAvalaraTransactionType(Integer taxDetailAvalaraTransactionType) {
            params.addOpt("tax_detail[avalara_transaction_type]", taxDetailAvalaraTransactionType);
            return this;
        }

        public UpdateRequest taxDetailAvalaraServiceType(Integer taxDetailAvalaraServiceType) {
            params.addOpt("tax_detail[avalara_service_type]", taxDetailAvalaraServiceType);
            return this;
        }

        public UpdateRequest taxDetailTaxjarProductCode(String taxDetailTaxjarProductCode) {
            params.addOpt("tax_detail[taxjar_product_code]", taxDetailTaxjarProductCode);
            return this;
        }

        public UpdateRequest accountingDetailSku(String accountingDetailSku) {
            params.addOpt("accounting_detail[sku]", accountingDetailSku);
            return this;
        }

        public UpdateRequest accountingDetailAccountingCode(String accountingDetailAccountingCode) {
            params.addOpt("accounting_detail[accounting_code]", accountingDetailAccountingCode);
            return this;
        }

        public UpdateRequest accountingDetailAccountingCategory1(String accountingDetailAccountingCategory1) {
            params.addOpt("accounting_detail[accounting_category1]", accountingDetailAccountingCategory1);
            return this;
        }

        public UpdateRequest accountingDetailAccountingCategory2(String accountingDetailAccountingCategory2) {
            params.addOpt("accounting_detail[accounting_category2]", accountingDetailAccountingCategory2);
            return this;
        }

        public UpdateRequest accountingDetailAccountingCategory3(String accountingDetailAccountingCategory3) {
            params.addOpt("accounting_detail[accounting_category3]", accountingDetailAccountingCategory3);
            return this;
        }

        public UpdateRequest accountingDetailAccountingCategory4(String accountingDetailAccountingCategory4) {
            params.addOpt("accounting_detail[accounting_category4]", accountingDetailAccountingCategory4);
            return this;
        }

        public UpdateRequest tierStartingUnit(int index, Integer tierStartingUnit) {
            params.addOpt("tiers[starting_unit][" + index + "]", tierStartingUnit);
            return this;
        }
        public UpdateRequest tierEndingUnit(int index, Integer tierEndingUnit) {
            params.addOpt("tiers[ending_unit][" + index + "]", tierEndingUnit);
            return this;
        }
        public UpdateRequest tierPrice(int index, Long tierPrice) {
            params.addOpt("tiers[price][" + index + "]", tierPrice);
            return this;
        }
        public UpdateRequest tierStartingUnitInDecimal(int index, String tierStartingUnitInDecimal) {
            params.addOpt("tiers[starting_unit_in_decimal][" + index + "]", tierStartingUnitInDecimal);
            return this;
        }
        public UpdateRequest tierEndingUnitInDecimal(int index, String tierEndingUnitInDecimal) {
            params.addOpt("tiers[ending_unit_in_decimal][" + index + "]", tierEndingUnitInDecimal);
            return this;
        }
        public UpdateRequest tierPriceInDecimal(int index, String tierPriceInDecimal) {
            params.addOpt("tiers[price_in_decimal][" + index + "]", tierPriceInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class ItemPriceListRequest extends ListRequest<ItemPriceListRequest> {

        private ItemPriceListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<ItemPriceListRequest> id() {
            return new StringFilter<ItemPriceListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<ItemPriceListRequest> name() {
            return new StringFilter<ItemPriceListRequest>("name",this).supportsMultiOperators(true);        
        }


        public EnumFilter<com.chargebee.models.enums.PricingModel, ItemPriceListRequest> pricingModel() {
            return new EnumFilter<com.chargebee.models.enums.PricingModel, ItemPriceListRequest>("pricing_model",this);        
        }


        public StringFilter<ItemPriceListRequest> itemId() {
            return new StringFilter<ItemPriceListRequest>("item_id",this).supportsMultiOperators(true);        
        }


        public StringFilter<ItemPriceListRequest> itemFamilyId() {
            return new StringFilter<ItemPriceListRequest>("item_family_id",this).supportsMultiOperators(true);        
        }


        public EnumFilter<com.chargebee.models.enums.ItemType, ItemPriceListRequest> itemType() {
            return new EnumFilter<com.chargebee.models.enums.ItemType, ItemPriceListRequest>("item_type",this);        
        }


        public StringFilter<ItemPriceListRequest> currencyCode() {
            return new StringFilter<ItemPriceListRequest>("currency_code",this).supportsMultiOperators(true);        
        }


        public NumberFilter<Integer, ItemPriceListRequest> trialPeriod() {
            return new NumberFilter<Integer, ItemPriceListRequest>("trial_period",this);        
        }


        public EnumFilter<ItemPrice.TrialPeriodUnit, ItemPriceListRequest> trialPeriodUnit() {
            return new EnumFilter<ItemPrice.TrialPeriodUnit, ItemPriceListRequest>("trial_period_unit",this);        
        }


        public EnumFilter<ItemPrice.Status, ItemPriceListRequest> status() {
            return new EnumFilter<ItemPrice.Status, ItemPriceListRequest>("status",this);        
        }


        public TimestampFilter<ItemPriceListRequest> updatedAt() {
            return new TimestampFilter<ItemPriceListRequest>("updated_at",this);        
        }


        public EnumFilter<ItemPrice.PeriodUnit, ItemPriceListRequest> periodUnit() {
            return new EnumFilter<ItemPrice.PeriodUnit, ItemPriceListRequest>("period_unit",this);        
        }


        public NumberFilter<Integer, ItemPriceListRequest> period() {
            return new NumberFilter<Integer, ItemPriceListRequest>("period",this);        
        }


        public EnumFilter<com.chargebee.models.enums.Channel, ItemPriceListRequest> channel() {
            return new EnumFilter<com.chargebee.models.enums.Channel, ItemPriceListRequest>("channel",this);        
        }


        public ItemPriceListRequest sortByName(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","name");
            return this;
        }
        public ItemPriceListRequest sortById(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","id");
            return this;
        }
        public ItemPriceListRequest sortByUpdatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","updated_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class ItemPriceFindApplicableItemsRequest extends ListRequest<ItemPriceFindApplicableItemsRequest> {

        private ItemPriceFindApplicableItemsRequest(String uri) {
            super(uri);
        }
    
        public ItemPriceFindApplicableItemsRequest sortByName(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","name");
            return this;
        }
        public ItemPriceFindApplicableItemsRequest sortById(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","id");
            return this;
        }
        public ItemPriceFindApplicableItemsRequest sortByUpdatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","updated_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class ItemPriceFindApplicableItemPricesRequest extends ListRequest<ItemPriceFindApplicableItemPricesRequest> {

        private ItemPriceFindApplicableItemPricesRequest(String uri) {
            super(uri);
        }
    
        public ItemPriceFindApplicableItemPricesRequest itemId(String itemId) {
            params.addOpt("item_id", itemId);
            return this;
        }


        public ItemPriceFindApplicableItemPricesRequest sortByName(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","name");
            return this;
        }
        public ItemPriceFindApplicableItemPricesRequest sortById(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","id");
            return this;
        }
        public ItemPriceFindApplicableItemPricesRequest sortByUpdatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","updated_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
