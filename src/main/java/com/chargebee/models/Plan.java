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

public class Plan extends Resource<Plan> {

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

    @Deprecated
    public enum ChargeModel {
        FLAT_FEE,
        PER_UNIT,
        TIERED,
        VOLUME,
        STAIRSTEP,
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

    public enum AddonApplicability {
        ALL,
        RESTRICTED,
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

    public static class ApplicableAddon extends Resource<ApplicableAddon> {
        public ApplicableAddon(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

    }

    public static class AttachedAddon extends Resource<AttachedAddon> {
        public enum Type {
             RECOMMENDED,MANDATORY,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public AttachedAddon(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public Integer quantity() {
            return reqInteger("quantity");
        }

        public Integer billingCycles() {
            return optInteger("billing_cycles");
        }

        public Type type() {
            return reqEnum("type", Type.class);
        }

        public String quantityInDecimal() {
            return optString("quantity_in_decimal");
        }

    }

    public static class EventBasedAddon extends Resource<EventBasedAddon> {
        public enum OnEvent {
             SUBSCRIPTION_CREATION,SUBSCRIPTION_TRIAL_START,PLAN_ACTIVATION,SUBSCRIPTION_ACTIVATION,CONTRACT_TERMINATION,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public EventBasedAddon(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public Integer quantity() {
            return reqInteger("quantity");
        }

        public OnEvent onEvent() {
            return reqEnum("on_event", OnEvent.class);
        }

        public Boolean chargeOnce() {
            return reqBoolean("charge_once");
        }

        public String quantityInDecimal() {
            return optString("quantity_in_decimal");
        }

    }

    //Constructors
    //============

    public Plan(String jsonStr) {
        super(jsonStr);
    }

    public Plan(JSONObject jsonObj) {
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

    public Long price() {
        return optLong("price");
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public Integer period() {
        return reqInteger("period");
    }

    public PeriodUnit periodUnit() {
        return reqEnum("period_unit", PeriodUnit.class);
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

    public PricingModel pricingModel() {
        return reqEnum("pricing_model", PricingModel.class);
    }

    @Deprecated
    public ChargeModel chargeModel() {
        return reqEnum("charge_model", ChargeModel.class);
    }

    public Integer freeQuantity() {
        return reqInteger("free_quantity");
    }

    public Long setupCost() {
        return optLong("setup_cost");
    }

    @Deprecated
    public Double downgradePenalty() {
        return optDouble("downgrade_penalty");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp archivedAt() {
        return optTimestamp("archived_at");
    }

    public Integer billingCycles() {
        return optInteger("billing_cycles");
    }

    public String redirectUrl() {
        return optString("redirect_url");
    }

    public Boolean enabledInHostedPages() {
        return reqBoolean("enabled_in_hosted_pages");
    }

    public Boolean enabledInPortal() {
        return reqBoolean("enabled_in_portal");
    }

    public AddonApplicability addonApplicability() {
        return reqEnum("addon_applicability", AddonApplicability.class);
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

    public Boolean giftable() {
        return reqBoolean("giftable");
    }

    public String claimUrl() {
        return optString("claim_url");
    }

    public String freeQuantityInDecimal() {
        return optString("free_quantity_in_decimal");
    }

    public String priceInDecimal() {
        return optString("price_in_decimal");
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

    public List<Plan.Tier> tiers() {
        return optList("tiers", Plan.Tier.class);
    }

    public List<Plan.ApplicableAddon> applicableAddons() {
        return optList("applicable_addons", Plan.ApplicableAddon.class);
    }

    public List<Plan.AttachedAddon> attachedAddons() {
        return optList("attached_addons", Plan.AttachedAddon.class);
    }

    public List<Plan.EventBasedAddon> eventBasedAddons() {
        return optList("event_based_addons", Plan.EventBasedAddon.class);
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
        String uri = uri("plans");
        return new CreateRequest(Method.POST, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("plans", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static PlanListRequest list() {
        String uri = uri("plans");
        return new PlanListRequest(uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("plans", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static Request delete(String id) {
        String uri = uri("plans", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static CopyRequest copy() {
        String uri = uri("plans", "copy");
        return new CopyRequest(Method.POST, uri);
    }

    public static Request unarchive(String id) {
        String uri = uri("plans", nullCheck(id), "unarchive");
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


        public CreateRequest trialPeriod(Integer trialPeriod) {
            params.addOpt("trial_period", trialPeriod);
            return this;
        }


        public CreateRequest trialPeriodUnit(Plan.TrialPeriodUnit trialPeriodUnit) {
            params.addOpt("trial_period_unit", trialPeriodUnit);
            return this;
        }


        public CreateRequest trialEndAction(Plan.TrialEndAction trialEndAction) {
            params.addOpt("trial_end_action", trialEndAction);
            return this;
        }


        public CreateRequest period(Integer period) {
            params.addOpt("period", period);
            return this;
        }


        public CreateRequest periodUnit(Plan.PeriodUnit periodUnit) {
            params.addOpt("period_unit", periodUnit);
            return this;
        }


        public CreateRequest setupCost(Long setupCost) {
            params.addOpt("setup_cost", setupCost);
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


        public CreateRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public CreateRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public CreateRequest pricingModel(com.chargebee.models.enums.PricingModel pricingModel) {
            params.addOpt("pricing_model", pricingModel);
            return this;
        }


        @Deprecated
        public CreateRequest chargeModel(ChargeModel chargeModel) {
            params.addOpt("charge_model", chargeModel);
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


        public CreateRequest addonApplicability(Plan.AddonApplicability addonApplicability) {
            params.addOpt("addon_applicability", addonApplicability);
            return this;
        }


        @Deprecated
        public CreateRequest downgradePenalty(Double downgradePenalty) {
            params.addOpt("downgrade_penalty", downgradePenalty);
            return this;
        }


        public CreateRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public CreateRequest enabledInHostedPages(Boolean enabledInHostedPages) {
            params.addOpt("enabled_in_hosted_pages", enabledInHostedPages);
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


        public CreateRequest shippingFrequencyPeriodUnit(Plan.ShippingFrequencyPeriodUnit shippingFrequencyPeriodUnit) {
            params.addOpt("shipping_frequency_period_unit", shippingFrequencyPeriodUnit);
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


        public CreateRequest showDescriptionInInvoices(Boolean showDescriptionInInvoices) {
            params.addOpt("show_description_in_invoices", showDescriptionInInvoices);
            return this;
        }


        public CreateRequest showDescriptionInQuotes(Boolean showDescriptionInQuotes) {
            params.addOpt("show_description_in_quotes", showDescriptionInQuotes);
            return this;
        }




        public CreateRequest giftable(Boolean giftable) {
            params.addOpt("giftable", giftable);
            return this;
        }


        public CreateRequest status(Plan.Status status) {
            params.addOpt("status", status);
            return this;
        }


        public CreateRequest claimUrl(String claimUrl) {
            params.addOpt("claim_url", claimUrl);
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
        public CreateRequest applicableAddonId(int index, String applicableAddonId) {
            params.addOpt("applicable_addons[id][" + index + "]", applicableAddonId);
            return this;
        }
        public CreateRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public CreateRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public CreateRequest eventBasedAddonQuantityInDecimal(int index, String eventBasedAddonQuantityInDecimal) {
            params.addOpt("event_based_addons[quantity_in_decimal][" + index + "]", eventBasedAddonQuantityInDecimal);
            return this;
        }
        public CreateRequest eventBasedAddonOnEvent(int index, com.chargebee.models.enums.OnEvent eventBasedAddonOnEvent) {
            params.addOpt("event_based_addons[on_event][" + index + "]", eventBasedAddonOnEvent);
            return this;
        }
        public CreateRequest eventBasedAddonChargeOnce(int index, Boolean eventBasedAddonChargeOnce) {
            params.addOpt("event_based_addons[charge_once][" + index + "]", eventBasedAddonChargeOnce);
            return this;
        }
        public CreateRequest attachedAddonId(int index, String attachedAddonId) {
            params.addOpt("attached_addons[id][" + index + "]", attachedAddonId);
            return this;
        }
        public CreateRequest attachedAddonQuantity(int index, Integer attachedAddonQuantity) {
            params.addOpt("attached_addons[quantity][" + index + "]", attachedAddonQuantity);
            return this;
        }
        public CreateRequest attachedAddonQuantityInDecimal(int index, String attachedAddonQuantityInDecimal) {
            params.addOpt("attached_addons[quantity_in_decimal][" + index + "]", attachedAddonQuantityInDecimal);
            return this;
        }
        public CreateRequest attachedAddonBillingCycles(int index, Integer attachedAddonBillingCycles) {
            params.addOpt("attached_addons[billing_cycles][" + index + "]", attachedAddonBillingCycles);
            return this;
        }
        public CreateRequest attachedAddonType(int index, AttachedAddon.Type attachedAddonType) {
            params.addOpt("attached_addons[type][" + index + "]", attachedAddonType);
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


        public UpdateRequest trialPeriod(Integer trialPeriod) {
            params.addOpt("trial_period", trialPeriod);
            return this;
        }


        public UpdateRequest trialPeriodUnit(Plan.TrialPeriodUnit trialPeriodUnit) {
            params.addOpt("trial_period_unit", trialPeriodUnit);
            return this;
        }


        public UpdateRequest trialEndAction(Plan.TrialEndAction trialEndAction) {
            params.addOpt("trial_end_action", trialEndAction);
            return this;
        }


        public UpdateRequest period(Integer period) {
            params.addOpt("period", period);
            return this;
        }


        public UpdateRequest periodUnit(Plan.PeriodUnit periodUnit) {
            params.addOpt("period_unit", periodUnit);
            return this;
        }


        public UpdateRequest setupCost(Long setupCost) {
            params.addOpt("setup_cost", setupCost);
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


        public UpdateRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public UpdateRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public UpdateRequest pricingModel(com.chargebee.models.enums.PricingModel pricingModel) {
            params.addOpt("pricing_model", pricingModel);
            return this;
        }


        @Deprecated
        public UpdateRequest chargeModel(ChargeModel chargeModel) {
            params.addOpt("charge_model", chargeModel);
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


        public UpdateRequest addonApplicability(Plan.AddonApplicability addonApplicability) {
            params.addOpt("addon_applicability", addonApplicability);
            return this;
        }


        @Deprecated
        public UpdateRequest downgradePenalty(Double downgradePenalty) {
            params.addOpt("downgrade_penalty", downgradePenalty);
            return this;
        }


        public UpdateRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public UpdateRequest enabledInHostedPages(Boolean enabledInHostedPages) {
            params.addOpt("enabled_in_hosted_pages", enabledInHostedPages);
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


        public UpdateRequest shippingFrequencyPeriodUnit(Plan.ShippingFrequencyPeriodUnit shippingFrequencyPeriodUnit) {
            params.addOpt("shipping_frequency_period_unit", shippingFrequencyPeriodUnit);
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


        public UpdateRequest showDescriptionInInvoices(Boolean showDescriptionInInvoices) {
            params.addOpt("show_description_in_invoices", showDescriptionInInvoices);
            return this;
        }


        public UpdateRequest showDescriptionInQuotes(Boolean showDescriptionInQuotes) {
            params.addOpt("show_description_in_quotes", showDescriptionInQuotes);
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
        public UpdateRequest applicableAddonId(int index, String applicableAddonId) {
            params.addOpt("applicable_addons[id][" + index + "]", applicableAddonId);
            return this;
        }
        public UpdateRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public UpdateRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public UpdateRequest eventBasedAddonQuantityInDecimal(int index, String eventBasedAddonQuantityInDecimal) {
            params.addOpt("event_based_addons[quantity_in_decimal][" + index + "]", eventBasedAddonQuantityInDecimal);
            return this;
        }
        public UpdateRequest eventBasedAddonOnEvent(int index, com.chargebee.models.enums.OnEvent eventBasedAddonOnEvent) {
            params.addOpt("event_based_addons[on_event][" + index + "]", eventBasedAddonOnEvent);
            return this;
        }
        public UpdateRequest eventBasedAddonChargeOnce(int index, Boolean eventBasedAddonChargeOnce) {
            params.addOpt("event_based_addons[charge_once][" + index + "]", eventBasedAddonChargeOnce);
            return this;
        }
        public UpdateRequest attachedAddonId(int index, String attachedAddonId) {
            params.addOpt("attached_addons[id][" + index + "]", attachedAddonId);
            return this;
        }
        public UpdateRequest attachedAddonQuantity(int index, Integer attachedAddonQuantity) {
            params.addOpt("attached_addons[quantity][" + index + "]", attachedAddonQuantity);
            return this;
        }
        public UpdateRequest attachedAddonQuantityInDecimal(int index, String attachedAddonQuantityInDecimal) {
            params.addOpt("attached_addons[quantity_in_decimal][" + index + "]", attachedAddonQuantityInDecimal);
            return this;
        }
        public UpdateRequest attachedAddonBillingCycles(int index, Integer attachedAddonBillingCycles) {
            params.addOpt("attached_addons[billing_cycles][" + index + "]", attachedAddonBillingCycles);
            return this;
        }
        public UpdateRequest attachedAddonType(int index, AttachedAddon.Type attachedAddonType) {
            params.addOpt("attached_addons[type][" + index + "]", attachedAddonType);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class PlanListRequest extends ListRequest<PlanListRequest> {

        private PlanListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<PlanListRequest> id() {
            return new StringFilter<PlanListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<PlanListRequest> name() {
            return new StringFilter<PlanListRequest>("name",this).supportsMultiOperators(true);        
        }


        public NumberFilter<Long, PlanListRequest> price() {
            return new NumberFilter<Long, PlanListRequest>("price",this);        
        }


        public NumberFilter<Integer, PlanListRequest> period() {
            return new NumberFilter<Integer, PlanListRequest>("period",this);        
        }


        public EnumFilter<Plan.PeriodUnit, PlanListRequest> periodUnit() {
            return new EnumFilter<Plan.PeriodUnit, PlanListRequest>("period_unit",this);        
        }


        public NumberFilter<Integer, PlanListRequest> trialPeriod() {
            return new NumberFilter<Integer, PlanListRequest>("trial_period",this).supportsPresenceOperator(true);        
        }


        public EnumFilter<Plan.TrialPeriodUnit, PlanListRequest> trialPeriodUnit() {
            return new EnumFilter<Plan.TrialPeriodUnit, PlanListRequest>("trial_period_unit",this);        
        }


        public EnumFilter<Plan.AddonApplicability, PlanListRequest> addonApplicability() {
            return new EnumFilter<Plan.AddonApplicability, PlanListRequest>("addon_applicability",this);        
        }


        public BooleanFilter<PlanListRequest> giftable() {
            return new BooleanFilter<PlanListRequest>("giftable",this);        
        }


        @Deprecated
        public EnumFilter<ChargeModel, PlanListRequest> chargeModel() {
            return new EnumFilter<ChargeModel, PlanListRequest>("charge_model",this);        
        }


        public EnumFilter<com.chargebee.models.enums.PricingModel, PlanListRequest> pricingModel() {
            return new EnumFilter<com.chargebee.models.enums.PricingModel, PlanListRequest>("pricing_model",this);        
        }


        public EnumFilter<Plan.Status, PlanListRequest> status() {
            return new EnumFilter<Plan.Status, PlanListRequest>("status",this);        
        }


        public TimestampFilter<PlanListRequest> updatedAt() {
            return new TimestampFilter<PlanListRequest>("updated_at",this);        
        }


        public StringFilter<PlanListRequest> currencyCode() {
            return new StringFilter<PlanListRequest>("currency_code",this).supportsMultiOperators(true);        
        }


        public EnumFilter<com.chargebee.models.enums.Channel, PlanListRequest> channel() {
            return new EnumFilter<com.chargebee.models.enums.Channel, PlanListRequest>("channel",this);        
        }


        public PlanListRequest includeDeleted(Boolean includeDeleted) {
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
