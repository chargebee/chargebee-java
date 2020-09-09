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

public class CouponSet extends Resource<CouponSet> {

    //Constructors
    //============

    public CouponSet(String jsonStr) {
        super(jsonStr);
    }

    public CouponSet(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String couponId() {
        return reqString("coupon_id");
    }

    public String name() {
        return reqString("name");
    }

    public Integer totalCount() {
        return optInteger("total_count");
    }

    public Integer redeemedCount() {
        return optInteger("redeemed_count");
    }

    public Integer archivedCount() {
        return optInteger("archived_count");
    }

    public JSONObject metaData() {
        return optJSONObject("meta_data");
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("coupon_sets");
        return new CreateRequest(Method.POST, uri);
    }

    public static AddCouponCodesRequest addCouponCodes(String id) {
        String uri = uri("coupon_sets", nullCheck(id), "add_coupon_codes");
        return new AddCouponCodesRequest(Method.POST, uri);
    }

    public static CouponSetListRequest list() {
        String uri = uri("coupon_sets");
        return new CouponSetListRequest(uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("coupon_sets", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("coupon_sets", nullCheck(id), "update");
        return new UpdateRequest(Method.POST, uri);
    }

    public static Request delete(String id) {
        String uri = uri("coupon_sets", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static Request deleteUnusedCouponCodes(String id) {
        String uri = uri("coupon_sets", nullCheck(id), "delete_unused_coupon_codes");
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


        public CreateRequest name(String name) {
            params.add("name", name);
            return this;
        }


        public CreateRequest id(String id) {
            params.add("id", id);
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

    public static class AddCouponCodesRequest extends Request<AddCouponCodesRequest> {

        private AddCouponCodesRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public AddCouponCodesRequest code(List<String> code) {
            params.addOpt("code", code);
            return this;
        }

        public AddCouponCodesRequest code(String... code) {
            params.addOpt("code", code);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class CouponSetListRequest extends ListRequest<CouponSetListRequest> {

        private CouponSetListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<CouponSetListRequest> id() {
            return new StringFilter<CouponSetListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<CouponSetListRequest> name() {
            return new StringFilter<CouponSetListRequest>("name",this).supportsMultiOperators(true);        
        }


        public StringFilter<CouponSetListRequest> couponId() {
            return new StringFilter<CouponSetListRequest>("coupon_id",this).supportsMultiOperators(true);        
        }


        public NumberFilter<Integer, CouponSetListRequest> totalCount() {
            return new NumberFilter<Integer, CouponSetListRequest>("total_count",this);        
        }


        public NumberFilter<Integer, CouponSetListRequest> redeemedCount() {
            return new NumberFilter<Integer, CouponSetListRequest>("redeemed_count",this);        
        }


        public NumberFilter<Integer, CouponSetListRequest> archivedCount() {
            return new NumberFilter<Integer, CouponSetListRequest>("archived_count",this);        
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


        public UpdateRequest metaData(JSONObject metaData) {
            params.addOpt("meta_data", metaData);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
