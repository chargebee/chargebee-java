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

public class Item extends Resource<Item> {

    public enum Status {
        ACTIVE,
        ARCHIVED,
        DELETED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Type {
        PLAN,
        ADDON,
        CHARGE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum ItemApplicability {
        ALL,
        RESTRICTED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum UsageCalculation {
        SUM_OF_USAGES,
        LAST_USAGE,
        MAX_USAGE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class ApplicableItem extends Resource<ApplicableItem> {
        public ApplicableItem(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return optString("id");
        }

    }

    public static class BundleItem extends Resource<BundleItem> {
        public enum ItemType {
             PLAN,ADDON,CHARGE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public BundleItem(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String itemId() {
            return reqString("item_id");
        }

        public ItemType itemType() {
            return optEnum("item_type", ItemType.class);
        }

        public Integer quantity() {
            return optInteger("quantity");
        }

        public BigDecimal priceAllocation() {
            return optBigDecimal("price_allocation");
        }

    }

    public static class BundleConfiguration extends Resource<BundleConfiguration> {
        public enum Type {
             FIXED,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public BundleConfiguration(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Type type() {
            return optEnum("type", Type.class);
        }

    }

    //Constructors
    //============

    public Item(String jsonStr) {
        super(jsonStr);
    }

    public Item(JSONObject jsonObj) {
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

    public String externalName() {
        return optString("external_name");
    }

    public String description() {
        return optString("description");
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public String itemFamilyId() {
        return optString("item_family_id");
    }

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public Boolean isShippable() {
        return optBoolean("is_shippable");
    }

    public Boolean isGiftable() {
        return reqBoolean("is_giftable");
    }

    public String redirectUrl() {
        return optString("redirect_url");
    }

    public Boolean enabledForCheckout() {
        return reqBoolean("enabled_for_checkout");
    }

    public Boolean enabledInPortal() {
        return reqBoolean("enabled_in_portal");
    }

    public Boolean includedInMrr() {
        return optBoolean("included_in_mrr");
    }

    public ItemApplicability itemApplicability() {
        return optEnum("item_applicability", ItemApplicability.class);
    }

    public String giftClaimRedirectUrl() {
        return optString("gift_claim_redirect_url");
    }

    public String unit() {
        return optString("unit");
    }

    public Boolean metered() {
        return reqBoolean("metered");
    }

    public UsageCalculation usageCalculation() {
        return optEnum("usage_calculation", UsageCalculation.class);
    }

    public Boolean isPercentagePricing() {
        return optBoolean("is_percentage_pricing");
    }

    public Timestamp archivedAt() {
        return optTimestamp("archived_at");
    }

    public Channel channel() {
        return optEnum("channel", Channel.class);
    }

    public List<Item.ApplicableItem> applicableItems() {
        return optList("applicable_items", Item.ApplicableItem.class);
    }

    public List<Item.BundleItem> bundleItems() {
        return optList("bundle_items", Item.BundleItem.class);
    }

    public Item.BundleConfiguration bundleConfiguration() {
        return optSubResource("bundle_configuration", Item.BundleConfiguration.class);
    }

    public JSONObject metadata() {
        return optJSONObject("metadata");
    }

    public Boolean deleted() {
        return reqBoolean("deleted");
    }

    public String businessEntityId() {
        return optString("business_entity_id");
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("items");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("items", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("items", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static ItemListRequest list() {
        String uri = uri("items");
        return new ItemListRequest(uri);
    }

    public static Request delete(String id) {
        String uri = uri("items", nullCheck(id), "delete");
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


        public CreateRequest type(Item.Type type) {
            params.add("type", type);
            return this;
        }


        public CreateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        public CreateRequest itemFamilyId(String itemFamilyId) {
            params.add("item_family_id", itemFamilyId);
            return this;
        }


        public CreateRequest isGiftable(Boolean isGiftable) {
            params.addOpt("is_giftable", isGiftable);
            return this;
        }


        public CreateRequest isShippable(Boolean isShippable) {
            params.addOpt("is_shippable", isShippable);
            return this;
        }


        public CreateRequest externalName(String externalName) {
            params.addOpt("external_name", externalName);
            return this;
        }


        public CreateRequest enabledInPortal(Boolean enabledInPortal) {
            params.addOpt("enabled_in_portal", enabledInPortal);
            return this;
        }


        public CreateRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public CreateRequest enabledForCheckout(Boolean enabledForCheckout) {
            params.addOpt("enabled_for_checkout", enabledForCheckout);
            return this;
        }


        public CreateRequest itemApplicability(Item.ItemApplicability itemApplicability) {
            params.addOpt("item_applicability", itemApplicability);
            return this;
        }


        public CreateRequest applicableItems(List<String> applicableItems) {
            params.addOpt("applicable_items", applicableItems);
            return this;
        }

        public CreateRequest applicableItems(String... applicableItems) {
            params.addOpt("applicable_items", applicableItems);
            return this;
        }

        public CreateRequest unit(String unit) {
            params.addOpt("unit", unit);
            return this;
        }


        public CreateRequest giftClaimRedirectUrl(String giftClaimRedirectUrl) {
            params.addOpt("gift_claim_redirect_url", giftClaimRedirectUrl);
            return this;
        }


        public CreateRequest includedInMrr(Boolean includedInMrr) {
            params.addOpt("included_in_mrr", includedInMrr);
            return this;
        }


        public CreateRequest metered(Boolean metered) {
            params.addOpt("metered", metered);
            return this;
        }


        public CreateRequest usageCalculation(Item.UsageCalculation usageCalculation) {
            params.addOpt("usage_calculation", usageCalculation);
            return this;
        }


        public CreateRequest isPercentagePricing(Boolean isPercentagePricing) {
            params.addOpt("is_percentage_pricing", isPercentagePricing);
            return this;
        }


        public CreateRequest metadata(JSONObject metadata) {
            params.addOpt("metadata", metadata);
            return this;
        }


        public CreateRequest businessEntityId(String businessEntityId) {
            params.addOpt("business_entity_id", businessEntityId);
            return this;
        }


        public CreateRequest bundleConfigurationType(BundleConfiguration.Type bundleConfigurationType) {
            params.addOpt("bundle_configuration[type]", bundleConfigurationType);
            return this;
        }

        public CreateRequest bundleItemsToAddItemId(int index, String bundleItemsToAddItemId) {
            params.addOpt("bundle_items_to_add[item_id][" + index + "]", bundleItemsToAddItemId);
            return this;
        }
        public CreateRequest bundleItemsToAddItemType(int index, com.chargebee.models.enums.ItemType bundleItemsToAddItemType) {
            params.addOpt("bundle_items_to_add[item_type][" + index + "]", bundleItemsToAddItemType);
            return this;
        }
        public CreateRequest bundleItemsToAddQuantity(int index, Integer bundleItemsToAddQuantity) {
            params.addOpt("bundle_items_to_add[quantity][" + index + "]", bundleItemsToAddQuantity);
            return this;
        }
        public CreateRequest bundleItemsToAddPriceAllocation(int index, BigDecimal bundleItemsToAddPriceAllocation) {
            params.addOpt("bundle_items_to_add[price_allocation][" + index + "]", bundleItemsToAddPriceAllocation);
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


        public UpdateRequest isShippable(Boolean isShippable) {
            params.addOpt("is_shippable", isShippable);
            return this;
        }


        public UpdateRequest externalName(String externalName) {
            params.addOpt("external_name", externalName);
            return this;
        }


        public UpdateRequest itemFamilyId(String itemFamilyId) {
            params.addOpt("item_family_id", itemFamilyId);
            return this;
        }


        public UpdateRequest enabledInPortal(Boolean enabledInPortal) {
            params.addOpt("enabled_in_portal", enabledInPortal);
            return this;
        }


        public UpdateRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public UpdateRequest enabledForCheckout(Boolean enabledForCheckout) {
            params.addOpt("enabled_for_checkout", enabledForCheckout);
            return this;
        }


        public UpdateRequest itemApplicability(Item.ItemApplicability itemApplicability) {
            params.addOpt("item_applicability", itemApplicability);
            return this;
        }


        @Deprecated
        public UpdateRequest clearApplicableItems(Boolean clearApplicableItems) {
            params.addOpt("clear_applicable_items", clearApplicableItems);
            return this;
        }


        public UpdateRequest applicableItems(List<String> applicableItems) {
            params.addOpt("applicable_items", applicableItems);
            return this;
        }

        public UpdateRequest applicableItems(String... applicableItems) {
            params.addOpt("applicable_items", applicableItems);
            return this;
        }

        public UpdateRequest unit(String unit) {
            params.addOpt("unit", unit);
            return this;
        }


        public UpdateRequest giftClaimRedirectUrl(String giftClaimRedirectUrl) {
            params.addOpt("gift_claim_redirect_url", giftClaimRedirectUrl);
            return this;
        }


        public UpdateRequest metadata(JSONObject metadata) {
            params.addOpt("metadata", metadata);
            return this;
        }


        public UpdateRequest includedInMrr(Boolean includedInMrr) {
            params.addOpt("included_in_mrr", includedInMrr);
            return this;
        }


        public UpdateRequest status(Item.Status status) {
            params.addOpt("status", status);
            return this;
        }


        public UpdateRequest isPercentagePricing(Boolean isPercentagePricing) {
            params.addOpt("is_percentage_pricing", isPercentagePricing);
            return this;
        }


        public UpdateRequest bundleConfigurationType(BundleConfiguration.Type bundleConfigurationType) {
            params.addOpt("bundle_configuration[type]", bundleConfigurationType);
            return this;
        }

        public UpdateRequest bundleItemsToAddItemId(int index, String bundleItemsToAddItemId) {
            params.addOpt("bundle_items_to_add[item_id][" + index + "]", bundleItemsToAddItemId);
            return this;
        }
        public UpdateRequest bundleItemsToAddItemType(int index, com.chargebee.models.enums.ItemType bundleItemsToAddItemType) {
            params.addOpt("bundle_items_to_add[item_type][" + index + "]", bundleItemsToAddItemType);
            return this;
        }
        public UpdateRequest bundleItemsToAddQuantity(int index, Integer bundleItemsToAddQuantity) {
            params.addOpt("bundle_items_to_add[quantity][" + index + "]", bundleItemsToAddQuantity);
            return this;
        }
        public UpdateRequest bundleItemsToAddPriceAllocation(int index, BigDecimal bundleItemsToAddPriceAllocation) {
            params.addOpt("bundle_items_to_add[price_allocation][" + index + "]", bundleItemsToAddPriceAllocation);
            return this;
        }
        public UpdateRequest bundleItemsToUpdateItemId(int index, String bundleItemsToUpdateItemId) {
            params.addOpt("bundle_items_to_update[item_id][" + index + "]", bundleItemsToUpdateItemId);
            return this;
        }
        public UpdateRequest bundleItemsToUpdateItemType(int index, com.chargebee.models.enums.ItemType bundleItemsToUpdateItemType) {
            params.addOpt("bundle_items_to_update[item_type][" + index + "]", bundleItemsToUpdateItemType);
            return this;
        }
        public UpdateRequest bundleItemsToUpdateQuantity(int index, Integer bundleItemsToUpdateQuantity) {
            params.addOpt("bundle_items_to_update[quantity][" + index + "]", bundleItemsToUpdateQuantity);
            return this;
        }
        public UpdateRequest bundleItemsToUpdatePriceAllocation(int index, BigDecimal bundleItemsToUpdatePriceAllocation) {
            params.addOpt("bundle_items_to_update[price_allocation][" + index + "]", bundleItemsToUpdatePriceAllocation);
            return this;
        }
        public UpdateRequest bundleItemsToRemoveItemId(int index, String bundleItemsToRemoveItemId) {
            params.addOpt("bundle_items_to_remove[item_id][" + index + "]", bundleItemsToRemoveItemId);
            return this;
        }
        public UpdateRequest bundleItemsToRemoveItemType(int index, com.chargebee.models.enums.ItemType bundleItemsToRemoveItemType) {
            params.addOpt("bundle_items_to_remove[item_type][" + index + "]", bundleItemsToRemoveItemType);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class ItemListRequest extends ListRequest<ItemListRequest> {

        private ItemListRequest(String uri) {
            super(uri);
        }
    
        public EnumFilter<BundleConfiguration.Type, ItemListRequest> bundleConfigurationType() {
            return new EnumFilter<BundleConfiguration.Type, ItemListRequest>("bundle_configuration[type]",this);        
        }


        public StringFilter<ItemListRequest> id() {
            return new StringFilter<ItemListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<ItemListRequest> itemFamilyId() {
            return new StringFilter<ItemListRequest>("item_family_id",this).supportsMultiOperators(true);        
        }


        public EnumFilter<Item.Type, ItemListRequest> type() {
            return new EnumFilter<Item.Type, ItemListRequest>("type",this);        
        }


        public StringFilter<ItemListRequest> name() {
            return new StringFilter<ItemListRequest>("name",this);        
        }


        public EnumFilter<Item.ItemApplicability, ItemListRequest> itemApplicability() {
            return new EnumFilter<Item.ItemApplicability, ItemListRequest>("item_applicability",this);        
        }


        public EnumFilter<Item.Status, ItemListRequest> status() {
            return new EnumFilter<Item.Status, ItemListRequest>("status",this);        
        }


        public BooleanFilter<ItemListRequest> isGiftable() {
            return new BooleanFilter<ItemListRequest>("is_giftable",this);        
        }


        public TimestampFilter<ItemListRequest> updatedAt() {
            return new TimestampFilter<ItemListRequest>("updated_at",this);        
        }


        public BooleanFilter<ItemListRequest> enabledForCheckout() {
            return new BooleanFilter<ItemListRequest>("enabled_for_checkout",this);        
        }


        public BooleanFilter<ItemListRequest> enabledInPortal() {
            return new BooleanFilter<ItemListRequest>("enabled_in_portal",this);        
        }


        public BooleanFilter<ItemListRequest> metered() {
            return new BooleanFilter<ItemListRequest>("metered",this);        
        }


        public EnumFilter<Item.UsageCalculation, ItemListRequest> usageCalculation() {
            return new EnumFilter<Item.UsageCalculation, ItemListRequest>("usage_calculation",this);        
        }


        public EnumFilter<com.chargebee.models.enums.Channel, ItemListRequest> channel() {
            return new EnumFilter<com.chargebee.models.enums.Channel, ItemListRequest>("channel",this);        
        }


        public StringFilter<ItemListRequest> businessEntityId() {
            return new StringFilter<ItemListRequest>("business_entity_id",this).supportsPresenceOperator(true);        
        }


        public BooleanFilter<ItemListRequest> includeSiteLevelResources() {
            return new BooleanFilter<ItemListRequest>("include_site_level_resources",this);        
        }


        public ItemListRequest sortByName(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","name");
            return this;
        }
        public ItemListRequest sortById(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","id");
            return this;
        }
        public ItemListRequest sortByUpdatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","updated_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
