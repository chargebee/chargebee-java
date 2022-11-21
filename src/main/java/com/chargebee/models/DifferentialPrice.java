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

public class DifferentialPrice extends Resource<DifferentialPrice> {

    public enum Status {
        ACTIVE,
        DELETED,
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

    public static class ParentPeriod extends Resource<ParentPeriod> {
        public enum PeriodUnit {
             DAY,WEEK,MONTH,YEAR,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public ParentPeriod(JSONObject jsonObj) {
            super(jsonObj);
        }

        public PeriodUnit periodUnit() {
            return reqEnum("period_unit", PeriodUnit.class);
        }

        public JSONArray period() {
            return optJSONArray("period");
        }

    }

    //Constructors
    //============

    public DifferentialPrice(String jsonStr) {
        super(jsonStr);
    }

    public DifferentialPrice(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String itemPriceId() {
        return reqString("item_price_id");
    }

    public String parentItemId() {
        return reqString("parent_item_id");
    }

    public Long price() {
        return optLong("price");
    }

    public String priceInDecimal() {
        return optString("price_in_decimal");
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

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp modifiedAt() {
        return reqTimestamp("modified_at");
    }

    public List<DifferentialPrice.Tier> tiers() {
        return optList("tiers", DifferentialPrice.Tier.class);
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public List<DifferentialPrice.ParentPeriod> parentPeriods() {
        return optList("parent_periods", DifferentialPrice.ParentPeriod.class);
    }

    // Operations
    //===========

    public static CreateRequest create(String id) {
        String uri = uri("item_prices", nullCheck(id), "differential_prices");
        return new CreateRequest(Method.POST, uri);
    }

    public static RetrieveRequest retrieve(String id) {
        String uri = uri("differential_prices", nullCheck(id));
        return new RetrieveRequest(Method.GET, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("differential_prices", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static DeleteRequest delete(String id) {
        String uri = uri("differential_prices", nullCheck(id), "delete");
        return new DeleteRequest(Method.POST, uri);
    }

    public static DifferentialPriceListRequest list() {
        String uri = uri("differential_prices");
        return new DifferentialPriceListRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest parentItemId(String parentItemId) {
            params.add("parent_item_id", parentItemId);
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


        public CreateRequest parentPeriodPeriodUnit(int index, ParentPeriod.PeriodUnit parentPeriodPeriodUnit) {
            params.add("parent_periods[period_unit][" + index + "]", parentPeriodPeriodUnit);
            return this;
        }
        public CreateRequest parentPeriodPeriod(int index, JSONArray parentPeriodPeriod) {
            params.addOpt("parent_periods[period][" + index + "]", parentPeriodPeriod);
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

    public static class RetrieveRequest extends Request<RetrieveRequest> {

        private RetrieveRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RetrieveRequest itemPriceId(String itemPriceId) {
            params.add("item_price_id", itemPriceId);
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
    
        public UpdateRequest itemPriceId(String itemPriceId) {
            params.add("item_price_id", itemPriceId);
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


        public UpdateRequest parentPeriodPeriodUnit(int index, ParentPeriod.PeriodUnit parentPeriodPeriodUnit) {
            params.add("parent_periods[period_unit][" + index + "]", parentPeriodPeriodUnit);
            return this;
        }
        public UpdateRequest parentPeriodPeriod(int index, JSONArray parentPeriodPeriod) {
            params.addOpt("parent_periods[period][" + index + "]", parentPeriodPeriod);
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

    public static class DeleteRequest extends Request<DeleteRequest> {

        private DeleteRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public DeleteRequest itemPriceId(String itemPriceId) {
            params.add("item_price_id", itemPriceId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class DifferentialPriceListRequest extends ListRequest<DifferentialPriceListRequest> {

        private DifferentialPriceListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<DifferentialPriceListRequest> itemPriceId() {
            return new StringFilter<DifferentialPriceListRequest>("item_price_id",this).supportsMultiOperators(true);        
        }


        public StringFilter<DifferentialPriceListRequest> itemId() {
            return new StringFilter<DifferentialPriceListRequest>("item_id",this).supportsMultiOperators(true);        
        }


        public StringFilter<DifferentialPriceListRequest> id() {
            return new StringFilter<DifferentialPriceListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<DifferentialPriceListRequest> parentItemId() {
            return new StringFilter<DifferentialPriceListRequest>("parent_item_id",this).supportsMultiOperators(true);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
