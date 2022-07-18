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

public class ItemEntitlement extends Resource<ItemEntitlement> {

    public enum ItemType {
        PLAN,
        ADDON,
        CHARGE,
        SUBSCRIPTION,
        ITEM,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public ItemEntitlement(String jsonStr) {
        super(jsonStr);
    }

    public ItemEntitlement(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String itemId() {
        return optString("item_id");
    }

    public ItemType itemType() {
        return optEnum("item_type", ItemType.class);
    }

    public String featureId() {
        return optString("feature_id");
    }

    public String featureName() {
        return optString("feature_name");
    }

    public String value() {
        return optString("value");
    }

    public String name() {
        return optString("name");
    }

    // Operations
    //===========

    public static ItemEntitlementItemEntitlementsForItemRequest itemEntitlementsForItem(String id) {
        String uri = uri("items", nullCheck(id), "item_entitlements");
        return new ItemEntitlementItemEntitlementsForItemRequest(uri);
    }

    public static ItemEntitlementItemEntitlementsForFeatureRequest itemEntitlementsForFeature(String id) {
        String uri = uri("features", nullCheck(id), "item_entitlements");
        return new ItemEntitlementItemEntitlementsForFeatureRequest(uri);
    }

    public static AddItemEntitlementsRequest addItemEntitlements(String id) {
        String uri = uri("features", nullCheck(id), "item_entitlements");
        return new AddItemEntitlementsRequest(Method.POST, uri);
    }

    public static UpsertOrRemoveItemEntitlementsForItemRequest upsertOrRemoveItemEntitlementsForItem(String id) {
        String uri = uri("items", nullCheck(id), "item_entitlements");
        return new UpsertOrRemoveItemEntitlementsForItemRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class ItemEntitlementItemEntitlementsForItemRequest extends ListRequest<ItemEntitlementItemEntitlementsForItemRequest> {

        private ItemEntitlementItemEntitlementsForItemRequest(String uri) {
            super(uri);
        }
    
        @Deprecated
        public ItemEntitlementItemEntitlementsForItemRequest includeDrafts(Boolean includeDrafts) {
            params.addOpt("include_drafts", includeDrafts);
            return this;
        }


        @Deprecated
        public ItemEntitlementItemEntitlementsForItemRequest embed(String embed) {
            params.addOpt("embed", embed);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class ItemEntitlementItemEntitlementsForFeatureRequest extends ListRequest<ItemEntitlementItemEntitlementsForFeatureRequest> {

        private ItemEntitlementItemEntitlementsForFeatureRequest(String uri) {
            super(uri);
        }
    
        @Deprecated
        public ItemEntitlementItemEntitlementsForFeatureRequest includeDrafts(Boolean includeDrafts) {
            params.addOpt("include_drafts", includeDrafts);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class AddItemEntitlementsRequest extends Request<AddItemEntitlementsRequest> {

        private AddItemEntitlementsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public AddItemEntitlementsRequest action(com.chargebee.models.enums.Action action) {
            params.add("action", action);
            return this;
        }


        public AddItemEntitlementsRequest itemEntitlementItemId(int index, String itemEntitlementItemId) {
            params.add("item_entitlements[item_id][" + index + "]", itemEntitlementItemId);
            return this;
        }
        public AddItemEntitlementsRequest itemEntitlementItemType(int index, ItemEntitlement.ItemType itemEntitlementItemType) {
            params.addOpt("item_entitlements[item_type][" + index + "]", itemEntitlementItemType);
            return this;
        }
        public AddItemEntitlementsRequest itemEntitlementValue(int index, String itemEntitlementValue) {
            params.addOpt("item_entitlements[value][" + index + "]", itemEntitlementValue);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpsertOrRemoveItemEntitlementsForItemRequest extends Request<UpsertOrRemoveItemEntitlementsForItemRequest> {

        private UpsertOrRemoveItemEntitlementsForItemRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpsertOrRemoveItemEntitlementsForItemRequest action(com.chargebee.models.enums.Action action) {
            params.add("action", action);
            return this;
        }


        public UpsertOrRemoveItemEntitlementsForItemRequest itemEntitlementFeatureId(int index, String itemEntitlementFeatureId) {
            params.add("item_entitlements[feature_id][" + index + "]", itemEntitlementFeatureId);
            return this;
        }
        public UpsertOrRemoveItemEntitlementsForItemRequest itemEntitlementValue(int index, String itemEntitlementValue) {
            params.addOpt("item_entitlements[value][" + index + "]", itemEntitlementValue);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

}
