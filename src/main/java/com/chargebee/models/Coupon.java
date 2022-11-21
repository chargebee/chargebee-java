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

public class Coupon extends Resource<Coupon> {

    public enum DiscountType {
        FIXED_AMOUNT,
        PERCENTAGE,
        @Deprecated
        OFFER_QUANTITY,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum DurationType {
        ONE_TIME,
        FOREVER,
        LIMITED_PERIOD,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Status {
        ACTIVE,
        EXPIRED,
        ARCHIVED,
        DELETED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    @Deprecated
    public enum ApplyDiscountOn {
        PLANS,
        PLANS_AND_ADDONS,
        PLANS_WITH_QUANTITY,
        NOT_APPLICABLE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum ApplyOn {
        INVOICE_AMOUNT,
        @Deprecated
        SPECIFIED_ITEMS_TOTAL,
        EACH_SPECIFIED_ITEM,
        @Deprecated
        EACH_UNIT_OF_SPECIFIED_ITEMS,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum PlanConstraint {
        NONE,
        ALL,
        SPECIFIC,
        NOT_APPLICABLE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum AddonConstraint {
        NONE,
        ALL,
        SPECIFIC,
        NOT_APPLICABLE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class ItemConstraint extends Resource<ItemConstraint> {
        public enum ItemType {
             PLAN,ADDON,CHARGE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum Constraint {
             NONE,ALL,SPECIFIC,CRITERIA,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public ItemConstraint(JSONObject jsonObj) {
            super(jsonObj);
        }

        public ItemType itemType() {
            return reqEnum("item_type", ItemType.class);
        }

        public Constraint constraint() {
            return reqEnum("constraint", Constraint.class);
        }

        public JSONArray itemPriceIds() {
            return optJSONArray("item_price_ids");
        }

    }

    public static class ItemConstraintCriteria extends Resource<ItemConstraintCriteria> {
        public enum ItemType {
             PLAN,ADDON,CHARGE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public ItemConstraintCriteria(JSONObject jsonObj) {
            super(jsonObj);
        }

        public ItemType itemType() {
            return reqEnum("item_type", ItemType.class);
        }

        public JSONArray currencies() {
            return optJSONArray("currencies");
        }

        public JSONArray itemFamilyIds() {
            return optJSONArray("item_family_ids");
        }

        public JSONArray itemPricePeriods() {
            return optJSONArray("item_price_periods");
        }

    }

    //Constructors
    //============

    public Coupon(String jsonStr) {
        super(jsonStr);
    }

    public Coupon(JSONObject jsonObj) {
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

    public DiscountType discountType() {
        return reqEnum("discount_type", DiscountType.class);
    }

    public Double discountPercentage() {
        return optDouble("discount_percentage");
    }

    public Long discountAmount() {
        return optLong("discount_amount");
    }

    @Deprecated
    public Integer discountQuantity() {
        return optInteger("discount_quantity");
    }

    public String currencyCode() {
        return optString("currency_code");
    }

    public DurationType durationType() {
        return reqEnum("duration_type", DurationType.class);
    }

    @Deprecated
    public Integer durationMonth() {
        return optInteger("duration_month");
    }

    public Timestamp validTill() {
        return optTimestamp("valid_till");
    }

    public Integer maxRedemptions() {
        return optInteger("max_redemptions");
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    @Deprecated
    public ApplyDiscountOn applyDiscountOn() {
        return reqEnum("apply_discount_on", ApplyDiscountOn.class);
    }

    public ApplyOn applyOn() {
        return reqEnum("apply_on", ApplyOn.class);
    }

    public PlanConstraint planConstraint() {
        return reqEnum("plan_constraint", PlanConstraint.class);
    }

    public AddonConstraint addonConstraint() {
        return reqEnum("addon_constraint", AddonConstraint.class);
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp archivedAt() {
        return optTimestamp("archived_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public Boolean includedInMrr() {
        return optBoolean("included_in_mrr");
    }

    public Integer period() {
        return optInteger("period");
    }

    public PeriodUnit periodUnit() {
        return optEnum("period_unit", PeriodUnit.class);
    }

    public List<String> planIds() {
        return optList("plan_ids", String.class);
    }

    public List<String> addonIds() {
        return optList("addon_ids", String.class);
    }

    public List<Coupon.ItemConstraint> itemConstraints() {
        return optList("item_constraints", Coupon.ItemConstraint.class);
    }

    public List<Coupon.ItemConstraintCriteria> itemConstraintCriteria() {
        return optList("item_constraint_criteria", Coupon.ItemConstraintCriteria.class);
    }

    public Integer redemptions() {
        return optInteger("redemptions");
    }

    public String invoiceNotes() {
        return optString("invoice_notes");
    }

    public JSONObject metaData() {
        return optJSONObject("meta_data");
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("coupons");
        return new CreateRequest(Method.POST, uri);
    }

    public static CreateForItemsRequest createForItems() {
        String uri = uri("coupons", "create_for_items");
        return new CreateForItemsRequest(Method.POST, uri);
    }

    public static UpdateForItemsRequest updateForItems(String id) {
        String uri = uri("coupons", nullCheck(id), "update_for_items");
        return new UpdateForItemsRequest(Method.POST, uri);
    }

    public static CouponListRequest list() {
        String uri = uri("coupons");
        return new CouponListRequest(uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("coupons", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("coupons", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static Request delete(String id) {
        String uri = uri("coupons", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static CopyRequest copy() {
        String uri = uri("coupons", "copy");
        return new CopyRequest(Method.POST, uri);
    }

    public static Request unarchive(String id) {
        String uri = uri("coupons", nullCheck(id), "unarchive");
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


        public CreateRequest discountType(Coupon.DiscountType discountType) {
            params.add("discount_type", discountType);
            return this;
        }


        public CreateRequest discountAmount(Long discountAmount) {
            params.addOpt("discount_amount", discountAmount);
            return this;
        }


        public CreateRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public CreateRequest discountPercentage(Double discountPercentage) {
            params.addOpt("discount_percentage", discountPercentage);
            return this;
        }


        @Deprecated
        public CreateRequest discountQuantity(Integer discountQuantity) {
            params.addOpt("discount_quantity", discountQuantity);
            return this;
        }


        public CreateRequest applyOn(Coupon.ApplyOn applyOn) {
            params.add("apply_on", applyOn);
            return this;
        }


        public CreateRequest durationType(Coupon.DurationType durationType) {
            params.add("duration_type", durationType);
            return this;
        }


        public CreateRequest durationMonth(Integer durationMonth) {
            params.addOpt("duration_month", durationMonth);
            return this;
        }


        public CreateRequest validTill(Timestamp validTill) {
            params.addOpt("valid_till", validTill);
            return this;
        }


        public CreateRequest maxRedemptions(Integer maxRedemptions) {
            params.addOpt("max_redemptions", maxRedemptions);
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


        public CreateRequest includedInMrr(Boolean includedInMrr) {
            params.addOpt("included_in_mrr", includedInMrr);
            return this;
        }


        public CreateRequest period(Integer period) {
            params.addOpt("period", period);
            return this;
        }


        public CreateRequest periodUnit(com.chargebee.models.enums.PeriodUnit periodUnit) {
            params.addOpt("period_unit", periodUnit);
            return this;
        }


        public CreateRequest planConstraint(PlanConstraint planConstraint) {
            params.addOpt("plan_constraint", planConstraint);
            return this;
        }


        public CreateRequest addonConstraint(AddonConstraint addonConstraint) {
            params.addOpt("addon_constraint", addonConstraint);
            return this;
        }


        public CreateRequest planIds(List<String> planIds) {
            params.addOpt("plan_ids", planIds);
            return this;
        }

        public CreateRequest planIds(String... planIds) {
            params.addOpt("plan_ids", planIds);
            return this;
        }

        public CreateRequest addonIds(List<String> addonIds) {
            params.addOpt("addon_ids", addonIds);
            return this;
        }

        public CreateRequest addonIds(String... addonIds) {
            params.addOpt("addon_ids", addonIds);
            return this;
        }

        public CreateRequest status(Coupon.Status status) {
            params.addOpt("status", status);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateForItemsRequest extends Request<CreateForItemsRequest> {

        private CreateForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateForItemsRequest id(String id) {
            params.add("id", id);
            return this;
        }


        public CreateForItemsRequest name(String name) {
            params.add("name", name);
            return this;
        }


        public CreateForItemsRequest invoiceName(String invoiceName) {
            params.addOpt("invoice_name", invoiceName);
            return this;
        }


        public CreateForItemsRequest discountType(Coupon.DiscountType discountType) {
            params.add("discount_type", discountType);
            return this;
        }


        public CreateForItemsRequest discountAmount(Long discountAmount) {
            params.addOpt("discount_amount", discountAmount);
            return this;
        }


        public CreateForItemsRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public CreateForItemsRequest discountPercentage(Double discountPercentage) {
            params.addOpt("discount_percentage", discountPercentage);
            return this;
        }


        @Deprecated
        public CreateForItemsRequest discountQuantity(Integer discountQuantity) {
            params.addOpt("discount_quantity", discountQuantity);
            return this;
        }


        public CreateForItemsRequest applyOn(Coupon.ApplyOn applyOn) {
            params.add("apply_on", applyOn);
            return this;
        }


        public CreateForItemsRequest durationType(Coupon.DurationType durationType) {
            params.add("duration_type", durationType);
            return this;
        }


        public CreateForItemsRequest durationMonth(Integer durationMonth) {
            params.addOpt("duration_month", durationMonth);
            return this;
        }


        public CreateForItemsRequest validTill(Timestamp validTill) {
            params.addOpt("valid_till", validTill);
            return this;
        }


        public CreateForItemsRequest maxRedemptions(Integer maxRedemptions) {
            params.addOpt("max_redemptions", maxRedemptions);
            return this;
        }


        public CreateForItemsRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        public CreateForItemsRequest metaData(JSONObject metaData) {
            params.addOpt("meta_data", metaData);
            return this;
        }


        public CreateForItemsRequest includedInMrr(Boolean includedInMrr) {
            params.addOpt("included_in_mrr", includedInMrr);
            return this;
        }


        public CreateForItemsRequest period(Integer period) {
            params.addOpt("period", period);
            return this;
        }


        public CreateForItemsRequest periodUnit(com.chargebee.models.enums.PeriodUnit periodUnit) {
            params.addOpt("period_unit", periodUnit);
            return this;
        }


        public CreateForItemsRequest status(Coupon.Status status) {
            params.addOpt("status", status);
            return this;
        }


        public CreateForItemsRequest itemConstraintConstraint(int index, ItemConstraint.Constraint itemConstraintConstraint) {
            params.add("item_constraints[constraint][" + index + "]", itemConstraintConstraint);
            return this;
        }
        public CreateForItemsRequest itemConstraintItemType(int index, ItemConstraint.ItemType itemConstraintItemType) {
            params.add("item_constraints[item_type][" + index + "]", itemConstraintItemType);
            return this;
        }
        public CreateForItemsRequest itemConstraintItemPriceIds(int index, JSONArray itemConstraintItemPriceIds) {
            params.addOpt("item_constraints[item_price_ids][" + index + "]", itemConstraintItemPriceIds);
            return this;
        }
        public CreateForItemsRequest itemConstraintCriteriaItemType(int index, ItemConstraintCriteria.ItemType itemConstraintCriteriaItemType) {
            params.addOpt("item_constraint_criteria[item_type][" + index + "]", itemConstraintCriteriaItemType);
            return this;
        }
        public CreateForItemsRequest itemConstraintCriteriaItemFamilyIds(int index, JSONArray itemConstraintCriteriaItemFamilyIds) {
            params.addOpt("item_constraint_criteria[item_family_ids][" + index + "]", itemConstraintCriteriaItemFamilyIds);
            return this;
        }
        public CreateForItemsRequest itemConstraintCriteriaCurrencies(int index, JSONArray itemConstraintCriteriaCurrencies) {
            params.addOpt("item_constraint_criteria[currencies][" + index + "]", itemConstraintCriteriaCurrencies);
            return this;
        }
        public CreateForItemsRequest itemConstraintCriteriaItemPricePeriods(int index, JSONArray itemConstraintCriteriaItemPricePeriods) {
            params.addOpt("item_constraint_criteria[item_price_periods][" + index + "]", itemConstraintCriteriaItemPricePeriods);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateForItemsRequest extends Request<UpdateForItemsRequest> {

        private UpdateForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateForItemsRequest name(String name) {
            params.addOpt("name", name);
            return this;
        }


        public UpdateForItemsRequest invoiceName(String invoiceName) {
            params.addOpt("invoice_name", invoiceName);
            return this;
        }


        public UpdateForItemsRequest discountType(Coupon.DiscountType discountType) {
            params.addOpt("discount_type", discountType);
            return this;
        }


        public UpdateForItemsRequest discountAmount(Long discountAmount) {
            params.addOpt("discount_amount", discountAmount);
            return this;
        }


        public UpdateForItemsRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public UpdateForItemsRequest discountPercentage(Double discountPercentage) {
            params.addOpt("discount_percentage", discountPercentage);
            return this;
        }


        public UpdateForItemsRequest applyOn(Coupon.ApplyOn applyOn) {
            params.addOpt("apply_on", applyOn);
            return this;
        }


        public UpdateForItemsRequest durationType(Coupon.DurationType durationType) {
            params.addOpt("duration_type", durationType);
            return this;
        }


        public UpdateForItemsRequest durationMonth(Integer durationMonth) {
            params.addOpt("duration_month", durationMonth);
            return this;
        }


        public UpdateForItemsRequest validTill(Timestamp validTill) {
            params.addOpt("valid_till", validTill);
            return this;
        }


        public UpdateForItemsRequest maxRedemptions(Integer maxRedemptions) {
            params.addOpt("max_redemptions", maxRedemptions);
            return this;
        }


        public UpdateForItemsRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        public UpdateForItemsRequest metaData(JSONObject metaData) {
            params.addOpt("meta_data", metaData);
            return this;
        }


        public UpdateForItemsRequest includedInMrr(Boolean includedInMrr) {
            params.addOpt("included_in_mrr", includedInMrr);
            return this;
        }


        public UpdateForItemsRequest period(Integer period) {
            params.addOpt("period", period);
            return this;
        }


        public UpdateForItemsRequest periodUnit(com.chargebee.models.enums.PeriodUnit periodUnit) {
            params.addOpt("period_unit", periodUnit);
            return this;
        }


        public UpdateForItemsRequest itemConstraintConstraint(int index, ItemConstraint.Constraint itemConstraintConstraint) {
            params.add("item_constraints[constraint][" + index + "]", itemConstraintConstraint);
            return this;
        }
        public UpdateForItemsRequest itemConstraintItemType(int index, ItemConstraint.ItemType itemConstraintItemType) {
            params.add("item_constraints[item_type][" + index + "]", itemConstraintItemType);
            return this;
        }
        public UpdateForItemsRequest itemConstraintItemPriceIds(int index, JSONArray itemConstraintItemPriceIds) {
            params.addOpt("item_constraints[item_price_ids][" + index + "]", itemConstraintItemPriceIds);
            return this;
        }
        public UpdateForItemsRequest itemConstraintCriteriaItemType(int index, ItemConstraintCriteria.ItemType itemConstraintCriteriaItemType) {
            params.addOpt("item_constraint_criteria[item_type][" + index + "]", itemConstraintCriteriaItemType);
            return this;
        }
        public UpdateForItemsRequest itemConstraintCriteriaItemFamilyIds(int index, JSONArray itemConstraintCriteriaItemFamilyIds) {
            params.addOpt("item_constraint_criteria[item_family_ids][" + index + "]", itemConstraintCriteriaItemFamilyIds);
            return this;
        }
        public UpdateForItemsRequest itemConstraintCriteriaCurrencies(int index, JSONArray itemConstraintCriteriaCurrencies) {
            params.addOpt("item_constraint_criteria[currencies][" + index + "]", itemConstraintCriteriaCurrencies);
            return this;
        }
        public UpdateForItemsRequest itemConstraintCriteriaItemPricePeriods(int index, JSONArray itemConstraintCriteriaItemPricePeriods) {
            params.addOpt("item_constraint_criteria[item_price_periods][" + index + "]", itemConstraintCriteriaItemPricePeriods);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CouponListRequest extends ListRequest<CouponListRequest> {

        private CouponListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<CouponListRequest> id() {
            return new StringFilter<CouponListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<CouponListRequest> name() {
            return new StringFilter<CouponListRequest>("name",this).supportsMultiOperators(true);        
        }


        public EnumFilter<Coupon.DiscountType, CouponListRequest> discountType() {
            return new EnumFilter<Coupon.DiscountType, CouponListRequest>("discount_type",this);        
        }


        public EnumFilter<Coupon.DurationType, CouponListRequest> durationType() {
            return new EnumFilter<Coupon.DurationType, CouponListRequest>("duration_type",this);        
        }


        public EnumFilter<Coupon.Status, CouponListRequest> status() {
            return new EnumFilter<Coupon.Status, CouponListRequest>("status",this);        
        }


        public EnumFilter<Coupon.ApplyOn, CouponListRequest> applyOn() {
            return new EnumFilter<Coupon.ApplyOn, CouponListRequest>("apply_on",this);        
        }


        public TimestampFilter<CouponListRequest> createdAt() {
            return new TimestampFilter<CouponListRequest>("created_at",this);        
        }


        public TimestampFilter<CouponListRequest> updatedAt() {
            return new TimestampFilter<CouponListRequest>("updated_at",this);        
        }






        public CouponListRequest sortByCreatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","created_at");
            return this;
        }


        public StringFilter<CouponListRequest> currencyCode() {
            return new StringFilter<CouponListRequest>("currency_code",this).supportsMultiOperators(true);        
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


        public UpdateRequest discountType(Coupon.DiscountType discountType) {
            params.addOpt("discount_type", discountType);
            return this;
        }


        public UpdateRequest discountAmount(Long discountAmount) {
            params.addOpt("discount_amount", discountAmount);
            return this;
        }


        public UpdateRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public UpdateRequest discountPercentage(Double discountPercentage) {
            params.addOpt("discount_percentage", discountPercentage);
            return this;
        }


        public UpdateRequest applyOn(Coupon.ApplyOn applyOn) {
            params.addOpt("apply_on", applyOn);
            return this;
        }


        public UpdateRequest durationType(Coupon.DurationType durationType) {
            params.addOpt("duration_type", durationType);
            return this;
        }


        public UpdateRequest durationMonth(Integer durationMonth) {
            params.addOpt("duration_month", durationMonth);
            return this;
        }


        public UpdateRequest validTill(Timestamp validTill) {
            params.addOpt("valid_till", validTill);
            return this;
        }


        public UpdateRequest maxRedemptions(Integer maxRedemptions) {
            params.addOpt("max_redemptions", maxRedemptions);
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


        public UpdateRequest includedInMrr(Boolean includedInMrr) {
            params.addOpt("included_in_mrr", includedInMrr);
            return this;
        }


        public UpdateRequest period(Integer period) {
            params.addOpt("period", period);
            return this;
        }


        public UpdateRequest periodUnit(com.chargebee.models.enums.PeriodUnit periodUnit) {
            params.addOpt("period_unit", periodUnit);
            return this;
        }


        public UpdateRequest planConstraint(PlanConstraint planConstraint) {
            params.addOpt("plan_constraint", planConstraint);
            return this;
        }


        public UpdateRequest addonConstraint(AddonConstraint addonConstraint) {
            params.addOpt("addon_constraint", addonConstraint);
            return this;
        }


        public UpdateRequest planIds(List<String> planIds) {
            params.addOpt("plan_ids", planIds);
            return this;
        }

        public UpdateRequest planIds(String... planIds) {
            params.addOpt("plan_ids", planIds);
            return this;
        }

        public UpdateRequest addonIds(List<String> addonIds) {
            params.addOpt("addon_ids", addonIds);
            return this;
        }

        public UpdateRequest addonIds(String... addonIds) {
            params.addOpt("addon_ids", addonIds);
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
