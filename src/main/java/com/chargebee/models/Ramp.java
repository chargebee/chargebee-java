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

public class Ramp extends Resource<Ramp> {

    public enum Status {
        SCHEDULED,
        SUCCEEDED,
        FAILED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class ItemsToAdd extends Resource<ItemsToAdd> {
        public enum ItemType {
             PLAN,ADDON,CHARGE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public ItemsToAdd(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String itemPriceId() {
            return reqString("item_price_id");
        }

        public ItemType itemType() {
            return reqEnum("item_type", ItemType.class);
        }

        public Integer quantity() {
            return optInteger("quantity");
        }

        public String quantityInDecimal() {
            return optString("quantity_in_decimal");
        }

        public Long unitPrice() {
            return optLong("unit_price");
        }

        public String unitPriceInDecimal() {
            return optString("unit_price_in_decimal");
        }

        public Long amount() {
            return optLong("amount");
        }

        public String amountInDecimal() {
            return optString("amount_in_decimal");
        }

        public Integer freeQuantity() {
            return optInteger("free_quantity");
        }

        public String freeQuantityInDecimal() {
            return optString("free_quantity_in_decimal");
        }

        public Integer billingCycles() {
            return optInteger("billing_cycles");
        }

        public Integer servicePeriodDays() {
            return optInteger("service_period_days");
        }

        public String meteredQuantity() {
            return optString("metered_quantity");
        }

    }

    public static class ItemsToUpdate extends Resource<ItemsToUpdate> {
        public enum ItemType {
             PLAN,ADDON,CHARGE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public ItemsToUpdate(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String itemPriceId() {
            return reqString("item_price_id");
        }

        public ItemType itemType() {
            return reqEnum("item_type", ItemType.class);
        }

        public Integer quantity() {
            return optInteger("quantity");
        }

        public String quantityInDecimal() {
            return optString("quantity_in_decimal");
        }

        public Long unitPrice() {
            return optLong("unit_price");
        }

        public String unitPriceInDecimal() {
            return optString("unit_price_in_decimal");
        }

        public Long amount() {
            return optLong("amount");
        }

        public String amountInDecimal() {
            return optString("amount_in_decimal");
        }

        public Integer freeQuantity() {
            return optInteger("free_quantity");
        }

        public String freeQuantityInDecimal() {
            return optString("free_quantity_in_decimal");
        }

        public Integer billingCycles() {
            return optInteger("billing_cycles");
        }

        public Integer servicePeriodDays() {
            return optInteger("service_period_days");
        }

        public String meteredQuantity() {
            return optString("metered_quantity");
        }

    }

    public static class CouponsToAdd extends Resource<CouponsToAdd> {
        public CouponsToAdd(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String couponId() {
            return reqString("coupon_id");
        }

        public Timestamp applyTill() {
            return optTimestamp("apply_till");
        }

    }

    public static class DiscountsToAdd extends Resource<DiscountsToAdd> {
        public enum Type {
             FIXED_AMOUNT,PERCENTAGE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum DurationType {
             ONE_TIME,FOREVER,LIMITED_PERIOD,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum ApplyOn {
             INVOICE_AMOUNT,SPECIFIC_ITEM_PRICE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public DiscountsToAdd(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public String invoiceName() {
            return optString("invoice_name");
        }

        public Type type() {
            return reqEnum("type", Type.class);
        }

        public Double percentage() {
            return optDouble("percentage");
        }

        public Long amount() {
            return optLong("amount");
        }

        public DurationType durationType() {
            return reqEnum("duration_type", DurationType.class);
        }

        public Integer period() {
            return optInteger("period");
        }

        public PeriodUnit periodUnit() {
            return optEnum("period_unit", PeriodUnit.class);
        }

        public Boolean includedInMrr() {
            return reqBoolean("included_in_mrr");
        }

        public ApplyOn applyOn() {
            return reqEnum("apply_on", ApplyOn.class);
        }

        public String itemPriceId() {
            return optString("item_price_id");
        }

        public Timestamp createdAt() {
            return reqTimestamp("created_at");
        }

    }

    public static class ItemTier extends Resource<ItemTier> {
        public ItemTier(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String itemPriceId() {
            return reqString("item_price_id");
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

        public Integer index() {
            return reqInteger("index");
        }

    }

    //Constructors
    //============

    public Ramp(String jsonStr) {
        super(jsonStr);
    }

    public Ramp(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String description() {
        return optString("description");
    }

    public String subscriptionId() {
        return reqString("subscription_id");
    }

    public Timestamp effectiveFrom() {
        return reqTimestamp("effective_from");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public List<Ramp.ItemsToAdd> itemsToAdd() {
        return optList("items_to_add", Ramp.ItemsToAdd.class);
    }

    public List<Ramp.ItemsToUpdate> itemsToUpdate() {
        return optList("items_to_update", Ramp.ItemsToUpdate.class);
    }

    public List<Ramp.CouponsToAdd> couponsToAdd() {
        return optList("coupons_to_add", Ramp.CouponsToAdd.class);
    }

    public List<Ramp.DiscountsToAdd> discountsToAdd() {
        return optList("discounts_to_add", Ramp.DiscountsToAdd.class);
    }

    public List<Ramp.ItemTier> itemTiers() {
        return optList("item_tiers", Ramp.ItemTier.class);
    }

    public List<String> itemsToRemove() {
        return optList("items_to_remove", String.class);
    }

    public List<String> couponsToRemove() {
        return optList("coupons_to_remove", String.class);
    }

    public List<String> discountsToRemove() {
        return optList("discounts_to_remove", String.class);
    }

    public Boolean deleted() {
        return reqBoolean("deleted");
    }

    // Operations
    //===========

    public static CreateForSubscriptionRequest createForSubscription(String id) {
        String uri = uri("subscriptions", nullCheck(id), "create_ramp");
        return new CreateForSubscriptionRequest(Method.POST, uri, nullCheckWithoutEncoding(id));
    }

    public static Request retrieve(String id) {
        String uri = uri("ramps", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static Request delete(String id) {
        String uri = uri("ramps", nullCheck(id), "delete");
        return new Request(Method.POST, uri, nullCheckWithoutEncoding(id));
    }

    public static RampListRequest list() {
        String uri = uri("ramps");
        return new RampListRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateForSubscriptionRequest extends Request<CreateForSubscriptionRequest> {

        private CreateForSubscriptionRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
        private CreateForSubscriptionRequest(Method httpMeth, String uri, String pathParam) {
            super(httpMeth, uri, pathParam);
        }
    
        public CreateForSubscriptionRequest effectiveFrom(Timestamp effectiveFrom) {
            params.add("effective_from", effectiveFrom);
            paramsV2.add(new PrimitiveParameter("effective_from"), effectiveFrom);
            return this;
        }


        public CreateForSubscriptionRequest description(String description) {
            params.addOpt("description", description);
            paramsV2.addOpt(new PrimitiveParameter("description"), description);
            return this;
        }


        public CreateForSubscriptionRequest couponsToRemove(List<String> couponsToRemove) {
            params.addOpt("coupons_to_remove", couponsToRemove);
            paramsV2.addOpt(new PrimitiveParameter("coupons_to_remove"), couponsToRemove);
            return this;
        }

        public CreateForSubscriptionRequest couponsToRemove(String... couponsToRemove) {
            params.addOpt("coupons_to_remove", couponsToRemove);
            paramsV2.addOpt(new PrimitiveParameter("coupons_to_remove"), couponsToRemove);
            return this;
        }

        public CreateForSubscriptionRequest discountsToRemove(List<String> discountsToRemove) {
            params.addOpt("discounts_to_remove", discountsToRemove);
            paramsV2.addOpt(new PrimitiveParameter("discounts_to_remove"), discountsToRemove);
            return this;
        }

        public CreateForSubscriptionRequest discountsToRemove(String... discountsToRemove) {
            params.addOpt("discounts_to_remove", discountsToRemove);
            paramsV2.addOpt(new PrimitiveParameter("discounts_to_remove"), discountsToRemove);
            return this;
        }

        public CreateForSubscriptionRequest itemsToRemove(List<String> itemsToRemove) {
            params.addOpt("items_to_remove", itemsToRemove);
            paramsV2.addOpt(new PrimitiveParameter("items_to_remove"), itemsToRemove);
            return this;
        }

        public CreateForSubscriptionRequest itemsToRemove(String... itemsToRemove) {
            params.addOpt("items_to_remove", itemsToRemove);
            paramsV2.addOpt(new PrimitiveParameter("items_to_remove"), itemsToRemove);
            return this;
        }

        public CreateForSubscriptionRequest itemsToAddItemPriceId(int index, String itemsToAddItemPriceId) {
            params.add("items_to_add[item_price_id][" + index + "]", itemsToAddItemPriceId);
            paramsV2.add(new CompositeArrayParameter("items_to_add", "item_price_id", index), itemsToAddItemPriceId);
            return this;
        }
        public CreateForSubscriptionRequest itemsToAddQuantity(int index, Integer itemsToAddQuantity) {
            params.addOpt("items_to_add[quantity][" + index + "]", itemsToAddQuantity);
            paramsV2.addOpt(new CompositeArrayParameter("items_to_add", "quantity", index), itemsToAddQuantity);
            return this;
        }
        public CreateForSubscriptionRequest itemsToAddQuantityInDecimal(int index, String itemsToAddQuantityInDecimal) {
            params.addOpt("items_to_add[quantity_in_decimal][" + index + "]", itemsToAddQuantityInDecimal);
            paramsV2.addOpt(new CompositeArrayParameter("items_to_add", "quantity_in_decimal", index), itemsToAddQuantityInDecimal);
            return this;
        }
        public CreateForSubscriptionRequest itemsToAddUnitPrice(int index, Long itemsToAddUnitPrice) {
            params.addOpt("items_to_add[unit_price][" + index + "]", itemsToAddUnitPrice);
            paramsV2.addOpt(new CompositeArrayParameter("items_to_add", "unit_price", index), itemsToAddUnitPrice);
            return this;
        }
        public CreateForSubscriptionRequest itemsToAddUnitPriceInDecimal(int index, String itemsToAddUnitPriceInDecimal) {
            params.addOpt("items_to_add[unit_price_in_decimal][" + index + "]", itemsToAddUnitPriceInDecimal);
            paramsV2.addOpt(new CompositeArrayParameter("items_to_add", "unit_price_in_decimal", index), itemsToAddUnitPriceInDecimal);
            return this;
        }
        public CreateForSubscriptionRequest itemsToAddBillingCycles(int index, Integer itemsToAddBillingCycles) {
            params.addOpt("items_to_add[billing_cycles][" + index + "]", itemsToAddBillingCycles);
            paramsV2.addOpt(new CompositeArrayParameter("items_to_add", "billing_cycles", index), itemsToAddBillingCycles);
            return this;
        }
        public CreateForSubscriptionRequest itemsToAddServicePeriodDays(int index, Integer itemsToAddServicePeriodDays) {
            params.addOpt("items_to_add[service_period_days][" + index + "]", itemsToAddServicePeriodDays);
            paramsV2.addOpt(new CompositeArrayParameter("items_to_add", "service_period_days", index), itemsToAddServicePeriodDays);
            return this;
        }
        public CreateForSubscriptionRequest itemsToUpdateItemPriceId(int index, String itemsToUpdateItemPriceId) {
            params.add("items_to_update[item_price_id][" + index + "]", itemsToUpdateItemPriceId);
            paramsV2.add(new CompositeArrayParameter("items_to_update", "item_price_id", index), itemsToUpdateItemPriceId);
            return this;
        }
        public CreateForSubscriptionRequest itemsToUpdateQuantity(int index, Integer itemsToUpdateQuantity) {
            params.addOpt("items_to_update[quantity][" + index + "]", itemsToUpdateQuantity);
            paramsV2.addOpt(new CompositeArrayParameter("items_to_update", "quantity", index), itemsToUpdateQuantity);
            return this;
        }
        public CreateForSubscriptionRequest itemsToUpdateQuantityInDecimal(int index, String itemsToUpdateQuantityInDecimal) {
            params.addOpt("items_to_update[quantity_in_decimal][" + index + "]", itemsToUpdateQuantityInDecimal);
            paramsV2.addOpt(new CompositeArrayParameter("items_to_update", "quantity_in_decimal", index), itemsToUpdateQuantityInDecimal);
            return this;
        }
        public CreateForSubscriptionRequest itemsToUpdateUnitPrice(int index, Long itemsToUpdateUnitPrice) {
            params.addOpt("items_to_update[unit_price][" + index + "]", itemsToUpdateUnitPrice);
            paramsV2.addOpt(new CompositeArrayParameter("items_to_update", "unit_price", index), itemsToUpdateUnitPrice);
            return this;
        }
        public CreateForSubscriptionRequest itemsToUpdateUnitPriceInDecimal(int index, String itemsToUpdateUnitPriceInDecimal) {
            params.addOpt("items_to_update[unit_price_in_decimal][" + index + "]", itemsToUpdateUnitPriceInDecimal);
            paramsV2.addOpt(new CompositeArrayParameter("items_to_update", "unit_price_in_decimal", index), itemsToUpdateUnitPriceInDecimal);
            return this;
        }
        public CreateForSubscriptionRequest itemsToUpdateBillingCycles(int index, Integer itemsToUpdateBillingCycles) {
            params.addOpt("items_to_update[billing_cycles][" + index + "]", itemsToUpdateBillingCycles);
            paramsV2.addOpt(new CompositeArrayParameter("items_to_update", "billing_cycles", index), itemsToUpdateBillingCycles);
            return this;
        }
        public CreateForSubscriptionRequest itemsToUpdateServicePeriodDays(int index, Integer itemsToUpdateServicePeriodDays) {
            params.addOpt("items_to_update[service_period_days][" + index + "]", itemsToUpdateServicePeriodDays);
            paramsV2.addOpt(new CompositeArrayParameter("items_to_update", "service_period_days", index), itemsToUpdateServicePeriodDays);
            return this;
        }
        public CreateForSubscriptionRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            paramsV2.addOpt(new CompositeArrayParameter("item_tiers", "item_price_id", index), itemTierItemPriceId);
            return this;
        }
        public CreateForSubscriptionRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            paramsV2.addOpt(new CompositeArrayParameter("item_tiers", "starting_unit", index), itemTierStartingUnit);
            return this;
        }
        public CreateForSubscriptionRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            paramsV2.addOpt(new CompositeArrayParameter("item_tiers", "ending_unit", index), itemTierEndingUnit);
            return this;
        }
        public CreateForSubscriptionRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            paramsV2.addOpt(new CompositeArrayParameter("item_tiers", "price", index), itemTierPrice);
            return this;
        }
        public CreateForSubscriptionRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            paramsV2.addOpt(new CompositeArrayParameter("item_tiers", "starting_unit_in_decimal", index), itemTierStartingUnitInDecimal);
            return this;
        }
        public CreateForSubscriptionRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            paramsV2.addOpt(new CompositeArrayParameter("item_tiers", "ending_unit_in_decimal", index), itemTierEndingUnitInDecimal);
            return this;
        }
        public CreateForSubscriptionRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            paramsV2.addOpt(new CompositeArrayParameter("item_tiers", "price_in_decimal", index), itemTierPriceInDecimal);
            return this;
        }
        public CreateForSubscriptionRequest couponsToAddCouponId(int index, String couponsToAddCouponId) {
            params.addOpt("coupons_to_add[coupon_id][" + index + "]", couponsToAddCouponId);
            paramsV2.addOpt(new CompositeArrayParameter("coupons_to_add", "coupon_id", index), couponsToAddCouponId);
            return this;
        }
        public CreateForSubscriptionRequest couponsToAddApplyTill(int index, Timestamp couponsToAddApplyTill) {
            params.addOpt("coupons_to_add[apply_till][" + index + "]", couponsToAddApplyTill);
            paramsV2.addOpt(new CompositeArrayParameter("coupons_to_add", "apply_till", index), couponsToAddApplyTill);
            return this;
        }
        public CreateForSubscriptionRequest discountsToAddApplyOn(int index, com.chargebee.models.enums.ApplyOn discountsToAddApplyOn) {
            params.add("discounts_to_add[apply_on][" + index + "]", discountsToAddApplyOn);
            paramsV2.add(new CompositeArrayParameter("discounts_to_add", "apply_on", index), discountsToAddApplyOn);
            return this;
        }
        public CreateForSubscriptionRequest discountsToAddDurationType(int index, com.chargebee.models.enums.DurationType discountsToAddDurationType) {
            params.add("discounts_to_add[duration_type][" + index + "]", discountsToAddDurationType);
            paramsV2.add(new CompositeArrayParameter("discounts_to_add", "duration_type", index), discountsToAddDurationType);
            return this;
        }
        public CreateForSubscriptionRequest discountsToAddPercentage(int index, Double discountsToAddPercentage) {
            params.addOpt("discounts_to_add[percentage][" + index + "]", discountsToAddPercentage);
            paramsV2.addOpt(new CompositeArrayParameter("discounts_to_add", "percentage", index), discountsToAddPercentage);
            return this;
        }
        public CreateForSubscriptionRequest discountsToAddAmount(int index, Long discountsToAddAmount) {
            params.addOpt("discounts_to_add[amount][" + index + "]", discountsToAddAmount);
            paramsV2.addOpt(new CompositeArrayParameter("discounts_to_add", "amount", index), discountsToAddAmount);
            return this;
        }
        public CreateForSubscriptionRequest discountsToAddPeriod(int index, Integer discountsToAddPeriod) {
            params.addOpt("discounts_to_add[period][" + index + "]", discountsToAddPeriod);
            paramsV2.addOpt(new CompositeArrayParameter("discounts_to_add", "period", index), discountsToAddPeriod);
            return this;
        }
        public CreateForSubscriptionRequest discountsToAddPeriodUnit(int index, com.chargebee.models.enums.PeriodUnit discountsToAddPeriodUnit) {
            params.addOpt("discounts_to_add[period_unit][" + index + "]", discountsToAddPeriodUnit);
            paramsV2.addOpt(new CompositeArrayParameter("discounts_to_add", "period_unit", index), discountsToAddPeriodUnit);
            return this;
        }
        public CreateForSubscriptionRequest discountsToAddIncludedInMrr(int index, Boolean discountsToAddIncludedInMrr) {
            params.addOpt("discounts_to_add[included_in_mrr][" + index + "]", discountsToAddIncludedInMrr);
            paramsV2.addOpt(new CompositeArrayParameter("discounts_to_add", "included_in_mrr", index), discountsToAddIncludedInMrr);
            return this;
        }
        public CreateForSubscriptionRequest discountsToAddItemPriceId(int index, String discountsToAddItemPriceId) {
            params.addOpt("discounts_to_add[item_price_id][" + index + "]", discountsToAddItemPriceId);
            paramsV2.addOpt(new CompositeArrayParameter("discounts_to_add", "item_price_id", index), discountsToAddItemPriceId);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
            @Override
            public ParamsV2 paramsV2() {
                return paramsV2;
            }
    }

    public static class RampListRequest extends ListRequest<RampListRequest> {

        private RampListRequest(String uri) {
            super(uri);
        }
    
        public RampListRequest includeDeleted(Boolean includeDeleted) {
            params.addOpt("include_deleted", includeDeleted);
            return this;
        }


        public EnumFilter<Ramp.Status, RampListRequest> status() {
            return new EnumFilter<Ramp.Status, RampListRequest>("status",this);        
        }


        public StringFilter<RampListRequest> subscriptionId() {
            return new StringFilter<RampListRequest>("subscription_id",this).supportsMultiOperators(true);        
        }


        public TimestampFilter<RampListRequest> effectiveFrom() {
            return new TimestampFilter<RampListRequest>("effective_from",this);        
        }


        public TimestampFilter<RampListRequest> updatedAt() {
            return new TimestampFilter<RampListRequest>("updated_at",this);        
        }


        public RampListRequest sortByEffectiveFrom(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","effective_from");
            return this;
        }
        public RampListRequest sortByUpdatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","updated_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
