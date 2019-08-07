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

public class CouponCode extends Resource<CouponCode> {

    public enum Status {
        NOT_REDEEMED,
        REDEEMED,
        ARCHIVED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public CouponCode(String jsonStr) {
        super(jsonStr);
    }

    public CouponCode(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String code() {
        return reqString("code");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public String couponId() {
        return reqString("coupon_id");
    }

    public String couponSetId() {
        return reqString("coupon_set_id");
    }

    public String couponSetName() {
        return reqString("coupon_set_name");
    }

    // Operations
    //===========

    @Deprecated
    public static CreateRequest create() {
        String uri = uri("coupon_codes");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("coupon_codes", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static CouponCodeListRequest list() {
        String uri = uri("coupon_codes");
        return new CouponCodeListRequest(uri);
    }

    public static Request archive(String id) {
        String uri = uri("coupon_codes", nullCheck(id), "archive");
        return new Request(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest couponId(String couponId) {
            params.add("coupon_id", couponId);
            return this;
        }


        public CreateRequest couponSetName(String couponSetName) {
            params.add("coupon_set_name", couponSetName);
            return this;
        }


        public CreateRequest code(String code) {
            params.add("code", code);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class CouponCodeListRequest extends ListRequest<CouponCodeListRequest> {

        private CouponCodeListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<CouponCodeListRequest> code() {
            return new StringFilter<CouponCodeListRequest>("code",this).supportsMultiOperators(true);        
        }


        public StringFilter<CouponCodeListRequest> couponId() {
            return new StringFilter<CouponCodeListRequest>("coupon_id",this).supportsMultiOperators(true);        
        }


        public StringFilter<CouponCodeListRequest> couponSetName() {
            return new StringFilter<CouponCodeListRequest>("coupon_set_name",this);        
        }


        public EnumFilter<CouponCode.Status, CouponCodeListRequest> status() {
            return new EnumFilter<CouponCode.Status, CouponCodeListRequest>("status",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
