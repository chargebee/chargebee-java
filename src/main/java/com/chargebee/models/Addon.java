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

public class Addon extends Resource<Addon> {

    @Deprecated
    public enum Type {
        ON_OFF,
        QUANTITY,
        TIERED,
        VOLUME,
        STAIRSTEP,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum ChargeType {
        RECURRING,
        NON_RECURRING,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum PeriodUnit {
        DAY,
        WEEK,
        MONTH,
        YEAR,
        NOT_APPLICABLE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Status {
        ACTIVE,
        ARCHIVED,
        DELETED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum ShippingFrequencyPeriodUnit {
        YEAR,
        MONTH,
        WEEK,
        DAY,
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

    //Constructors
    //============

    public Addon(String jsonStr) {
        super(jsonStr);
    }

    public Addon(JSONObject jsonObj) {
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

    public String invoiceName() {
        return optString("invoice_name");
    }

    public String description() {
        return optString("description");
    }

    public PricingModel pricingModel() {
        return reqEnum("pricing_model", PricingModel.class);
    }

    @Deprecated
    public Type type() {
        return reqEnum("type", Type.class);
    }

    public ChargeType chargeType() {
        return reqEnum("charge_type", ChargeType.class);
    }

    public Long price() {
        return optLong("price");
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public Integer period() {
        return optInteger("period");
    }

    public PeriodUnit periodUnit() {
        return reqEnum("period_unit", PeriodUnit.class);
    }

    public String unit() {
        return optString("unit");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp archivedAt() {
        return optTimestamp("archived_at");
    }

    public Boolean enabledInPortal() {
        return reqBoolean("enabled_in_portal");
    }

    public String taxCode() {
        return optString("tax_code");
    }

    public String hsnCode() {
        return optString("hsn_code");
    }

    public String taxjarProductCode() {
        return optString("taxjar_product_code");
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

    public Boolean isShippable() {
        return optBoolean("is_shippable");
    }

    public Integer shippingFrequencyPeriod() {
        return optInteger("shipping_frequency_period");
    }

    public ShippingFrequencyPeriodUnit shippingFrequencyPeriodUnit() {
        return optEnum("shipping_frequency_period_unit", ShippingFrequencyPeriodUnit.class);
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public String priceInDecimal() {
        return optString("price_in_decimal");
    }

    public Boolean includedInMrr() {
        return optBoolean("included_in_mrr");
    }

    public Channel channel() {
        return optEnum("channel", Channel.class);
    }

    public String invoiceNotes() {
        return optString("invoice_notes");
    }

    public Boolean taxable() {
        return optBoolean("taxable");
    }

    public String taxProfileId() {
        return optString("tax_profile_id");
    }

    public JSONObject metaData() {
        return optJSONObject("meta_data");
    }

    public List<Addon.Tier> tiers() {
        return optList("tiers", Addon.Tier.class);
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
        String uri = uri("addons");
        return new CreateRequest(Method.POST, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("addons", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static AddonListRequest list() {
        String uri = uri("addons");
        return new AddonListRequest(uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("addons", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static Request delete(String id) {
        String uri = uri("addons", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static CopyRequest copy() {
        String uri = uri("addons", "copy");
        return new CopyRequest(Method.POST, uri);
    }

    public static Request unarchive(String id) {
        String uri = uri("addons", nullCheck(id), "unarchive");
        return new Request(Method.POST, uri);
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


        public CreateRequest invoiceName(String invoiceName) {
            params.addOpt("invoice_name", invoiceName);
            return this;
        }


        public CreateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        public CreateRequest chargeType(Addon.ChargeType chargeType) {
            params.add("charge_type", chargeType);
            return this;
        }


        public CreateRequest price(Long price) {
            params.addOpt("price", price);
            return this;
        }


        public CreateRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public CreateRequest period(Integer period) {
            params.addOpt("period", period);
            return this;
        }


        public CreateRequest periodUnit(Addon.PeriodUnit periodUnit) {
            params.addOpt("period_unit", periodUnit);
            return this;
        }


        public CreateRequest pricingModel(com.chargebee.models.enums.PricingModel pricingModel) {
            params.addOpt("pricing_model", pricingModel);
            return this;
        }


        @Deprecated
        public CreateRequest type(Type type) {
            params.addOpt("type", type);
            return this;
        }


        public CreateRequest unit(String unit) {
            params.addOpt("unit", unit);
            return this;
        }


        public CreateRequest enabledInPortal(Boolean enabledInPortal) {
            params.addOpt("enabled_in_portal", enabledInPortal);
            return this;
        }


        public CreateRequest taxable(Boolean taxable) {
            params.addOpt("taxable", taxable);
            return this;
        }


        public CreateRequest taxProfileId(String taxProfileId) {
            params.addOpt("tax_profile_id", taxProfileId);
            return this;
        }


        public CreateRequest avalaraSaleType(com.chargebee.models.enums.AvalaraSaleType avalaraSaleType) {
            params.addOpt("avalara_sale_type", avalaraSaleType);
            return this;
        }


        public CreateRequest avalaraTransactionType(Integer avalaraTransactionType) {
            params.addOpt("avalara_transaction_type", avalaraTransactionType);
            return this;
        }


        public CreateRequest avalaraServiceType(Integer avalaraServiceType) {
            params.addOpt("avalara_service_type", avalaraServiceType);
            return this;
        }


        public CreateRequest taxCode(String taxCode) {
            params.addOpt("tax_code", taxCode);
            return this;
        }


        public CreateRequest hsnCode(String hsnCode) {
            params.addOpt("hsn_code", hsnCode);
            return this;
        }


        public CreateRequest taxjarProductCode(String taxjarProductCode) {
            params.addOpt("taxjar_product_code", taxjarProductCode);
            return this;
        }


        public CreateRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        public CreateRequest metaData(JSONObject metaData) {
            params.addOpt("meta_data", metaData);
            return this;
        }


        public CreateRequest sku(String sku) {
            params.addOpt("sku", sku);
            return this;
        }


        public CreateRequest accountingCode(String accountingCode) {
            params.addOpt("accounting_code", accountingCode);
            return this;
        }


        public CreateRequest accountingCategory1(String accountingCategory1) {
            params.addOpt("accounting_category1", accountingCategory1);
            return this;
        }


        public CreateRequest accountingCategory2(String accountingCategory2) {
            params.addOpt("accounting_category2", accountingCategory2);
            return this;
        }


        public CreateRequest accountingCategory3(String accountingCategory3) {
            params.addOpt("accounting_category3", accountingCategory3);
            return this;
        }


        public CreateRequest accountingCategory4(String accountingCategory4) {
            params.addOpt("accounting_category4", accountingCategory4);
            return this;
        }


        public CreateRequest isShippable(Boolean isShippable) {
            params.addOpt("is_shippable", isShippable);
            return this;
        }


        public CreateRequest shippingFrequencyPeriod(Integer shippingFrequencyPeriod) {
            params.addOpt("shipping_frequency_period", shippingFrequencyPeriod);
            return this;
        }


        public CreateRequest shippingFrequencyPeriodUnit(Addon.ShippingFrequencyPeriodUnit shippingFrequencyPeriodUnit) {
            params.addOpt("shipping_frequency_period_unit", shippingFrequencyPeriodUnit);
            return this;
        }


        public CreateRequest includedInMrr(Boolean includedInMrr) {
            params.addOpt("included_in_mrr", includedInMrr);
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


        public CreateRequest priceInDecimal(String priceInDecimal) {
            params.addOpt("price_in_decimal", priceInDecimal);
            return this;
        }




        public CreateRequest status(Addon.Status status) {
            params.addOpt("status", status);
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


        public UpdateRequest invoiceName(String invoiceName) {
            params.addOpt("invoice_name", invoiceName);
            return this;
        }


        public UpdateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        public UpdateRequest chargeType(Addon.ChargeType chargeType) {
            params.addOpt("charge_type", chargeType);
            return this;
        }


        public UpdateRequest price(Long price) {
            params.addOpt("price", price);
            return this;
        }


        public UpdateRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public UpdateRequest period(Integer period) {
            params.addOpt("period", period);
            return this;
        }


        public UpdateRequest periodUnit(Addon.PeriodUnit periodUnit) {
            params.addOpt("period_unit", periodUnit);
            return this;
        }


        public UpdateRequest pricingModel(com.chargebee.models.enums.PricingModel pricingModel) {
            params.addOpt("pricing_model", pricingModel);
            return this;
        }


        @Deprecated
        public UpdateRequest type(Type type) {
            params.addOpt("type", type);
            return this;
        }


        public UpdateRequest unit(String unit) {
            params.addOpt("unit", unit);
            return this;
        }


        public UpdateRequest enabledInPortal(Boolean enabledInPortal) {
            params.addOpt("enabled_in_portal", enabledInPortal);
            return this;
        }


        public UpdateRequest taxable(Boolean taxable) {
            params.addOpt("taxable", taxable);
            return this;
        }


        public UpdateRequest taxProfileId(String taxProfileId) {
            params.addOpt("tax_profile_id", taxProfileId);
            return this;
        }


        public UpdateRequest avalaraSaleType(com.chargebee.models.enums.AvalaraSaleType avalaraSaleType) {
            params.addOpt("avalara_sale_type", avalaraSaleType);
            return this;
        }


        public UpdateRequest avalaraTransactionType(Integer avalaraTransactionType) {
            params.addOpt("avalara_transaction_type", avalaraTransactionType);
            return this;
        }


        public UpdateRequest avalaraServiceType(Integer avalaraServiceType) {
            params.addOpt("avalara_service_type", avalaraServiceType);
            return this;
        }


        public UpdateRequest taxCode(String taxCode) {
            params.addOpt("tax_code", taxCode);
            return this;
        }


        public UpdateRequest hsnCode(String hsnCode) {
            params.addOpt("hsn_code", hsnCode);
            return this;
        }


        public UpdateRequest taxjarProductCode(String taxjarProductCode) {
            params.addOpt("taxjar_product_code", taxjarProductCode);
            return this;
        }


        public UpdateRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        public UpdateRequest metaData(JSONObject metaData) {
            params.addOpt("meta_data", metaData);
            return this;
        }


        public UpdateRequest sku(String sku) {
            params.addOpt("sku", sku);
            return this;
        }


        public UpdateRequest accountingCode(String accountingCode) {
            params.addOpt("accounting_code", accountingCode);
            return this;
        }


        public UpdateRequest accountingCategory1(String accountingCategory1) {
            params.addOpt("accounting_category1", accountingCategory1);
            return this;
        }


        public UpdateRequest accountingCategory2(String accountingCategory2) {
            params.addOpt("accounting_category2", accountingCategory2);
            return this;
        }


        public UpdateRequest accountingCategory3(String accountingCategory3) {
            params.addOpt("accounting_category3", accountingCategory3);
            return this;
        }


        public UpdateRequest accountingCategory4(String accountingCategory4) {
            params.addOpt("accounting_category4", accountingCategory4);
            return this;
        }


        public UpdateRequest isShippable(Boolean isShippable) {
            params.addOpt("is_shippable", isShippable);
            return this;
        }


        public UpdateRequest shippingFrequencyPeriod(Integer shippingFrequencyPeriod) {
            params.addOpt("shipping_frequency_period", shippingFrequencyPeriod);
            return this;
        }


        public UpdateRequest shippingFrequencyPeriodUnit(Addon.ShippingFrequencyPeriodUnit shippingFrequencyPeriodUnit) {
            params.addOpt("shipping_frequency_period_unit", shippingFrequencyPeriodUnit);
            return this;
        }


        public UpdateRequest includedInMrr(Boolean includedInMrr) {
            params.addOpt("included_in_mrr", includedInMrr);
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


        public UpdateRequest priceInDecimal(String priceInDecimal) {
            params.addOpt("price_in_decimal", priceInDecimal);
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

    public static class AddonListRequest extends ListRequest<AddonListRequest> {

        private AddonListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<AddonListRequest> id() {
            return new StringFilter<AddonListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<AddonListRequest> name() {
            return new StringFilter<AddonListRequest>("name",this).supportsMultiOperators(true);        
        }


        public EnumFilter<com.chargebee.models.enums.PricingModel, AddonListRequest> pricingModel() {
            return new EnumFilter<com.chargebee.models.enums.PricingModel, AddonListRequest>("pricing_model",this);        
        }


        @Deprecated
        public EnumFilter<Type, AddonListRequest> type() {
            return new EnumFilter<Type, AddonListRequest>("type",this);        
        }


        public EnumFilter<Addon.ChargeType, AddonListRequest> chargeType() {
            return new EnumFilter<Addon.ChargeType, AddonListRequest>("charge_type",this);        
        }


        public NumberFilter<Long, AddonListRequest> price() {
            return new NumberFilter<Long, AddonListRequest>("price",this);        
        }


        public NumberFilter<Integer, AddonListRequest> period() {
            return new NumberFilter<Integer, AddonListRequest>("period",this);        
        }


        public EnumFilter<Addon.PeriodUnit, AddonListRequest> periodUnit() {
            return new EnumFilter<Addon.PeriodUnit, AddonListRequest>("period_unit",this);        
        }


        public EnumFilter<Addon.Status, AddonListRequest> status() {
            return new EnumFilter<Addon.Status, AddonListRequest>("status",this);        
        }


        public TimestampFilter<AddonListRequest> updatedAt() {
            return new TimestampFilter<AddonListRequest>("updated_at",this);        
        }


        public StringFilter<AddonListRequest> currencyCode() {
            return new StringFilter<AddonListRequest>("currency_code",this).supportsMultiOperators(true);        
        }


        public EnumFilter<com.chargebee.models.enums.Channel, AddonListRequest> channel() {
            return new EnumFilter<com.chargebee.models.enums.Channel, AddonListRequest>("channel",this);        
        }


        public AddonListRequest includeDeleted(Boolean includeDeleted) {
            params.addOpt("include_deleted", includeDeleted);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class CopyRequest extends Request<CopyRequest> {

        private CopyRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CopyRequest fromSite(String fromSite) {
            params.add("from_site", fromSite);
            return this;
        }


        public CopyRequest idAtFromSite(String idAtFromSite) {
            params.add("id_at_from_site", idAtFromSite);
            return this;
        }


        public CopyRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }


        public CopyRequest forSiteMerging(Boolean forSiteMerging) {
            params.addOpt("for_site_merging", forSiteMerging);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
