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

    public Integer discountAmount() {
        return optInteger("discount_amount");
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

    public List<String> planIds() {
        return optList("plan_ids", String.class);
    }

    public List<String> addonIds() {
        return optList("addon_ids", String.class);
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

    public static CreateRequest create() throws IOException {
        String uri = uri("coupons");
        return new CreateRequest(Method.POST, uri);
    }

    public static CouponListRequest list() throws IOException {
        String uri = uri("coupons");
        return new CouponListRequest(uri);
    }

    public static Request retrieve(String id) throws IOException {
        String uri = uri("coupons", nullCheck(id));
        return new Request(Method.GET, uri);
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


        public CreateRequest discountType(DiscountType discountType) {
            params.add("discount_type", discountType);
            return this;
        }


        public CreateRequest discountAmount(Integer discountAmount) {
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


        public CreateRequest applyOn(ApplyOn applyOn) {
            params.add("apply_on", applyOn);
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

        public CreateRequest durationType(DurationType durationType) {
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


        public EnumFilter<DiscountType, CouponListRequest> discountType() {
            return new EnumFilter<DiscountType, CouponListRequest>("discount_type",this);        
        }


        public EnumFilter<DurationType, CouponListRequest> durationType() {
            return new EnumFilter<DurationType, CouponListRequest>("duration_type",this);        
        }


        public EnumFilter<Status, CouponListRequest> status() {
            return new EnumFilter<Status, CouponListRequest>("status",this);        
        }


        public EnumFilter<ApplyOn, CouponListRequest> applyOn() {
            return new EnumFilter<ApplyOn, CouponListRequest>("apply_on",this);        
        }


        public TimestampFilter<CouponListRequest> createdAt() {
            return new TimestampFilter<CouponListRequest>("created_at",this);        
        }


        public CouponListRequest sortByCreatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","created_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
