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

public class PriceRamp extends Resource<PriceRamp> {

    public enum Status {
        ACTIVE,
        ARCHIVED,
        DELETED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class RampInterval extends Resource<RampInterval> {
        public RampInterval(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Integer startingBillingCycle() {
            return reqInteger("starting_billing_cycle");
        }

        public Integer endingBillingCycle() {
            return optInteger("ending_billing_cycle");
        }

        public Boolean applyCatalogPrice() {
            return optBoolean("apply_catalog_price");
        }

        public Long price() {
            return optLong("price");
        }

        public String priceInDecimal() {
            return optString("price_in_decimal");
        }

    }

    //Constructors
    //============

    public PriceRamp(String jsonStr) {
        super(jsonStr);
    }

    public PriceRamp(JSONObject jsonObj) {
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

    public Timestamp archivedAt() {
        return optTimestamp("archived_at");
    }

    public List<PriceRamp.RampInterval> rampIntervals() {
        return optList("ramp_intervals", PriceRamp.RampInterval.class);
    }

    public String businessEntityId() {
        return optString("business_entity_id");
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("price_ramps");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("price_ramps", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("price_ramps", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static Request delete(String id) {
        String uri = uri("price_ramps", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static PriceRampListRequest list() {
        String uri = uri("price_ramps");
        return new PriceRampListRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest itemPriceId(String itemPriceId) {
            params.add("item_price_id", itemPriceId);
            return this;
        }


        public CreateRequest businessEntityId(String businessEntityId) {
            params.addOpt("business_entity_id", businessEntityId);
            return this;
        }


        public CreateRequest rampIntervalStartingBillingCycle(int index, Integer rampIntervalStartingBillingCycle) {
            params.add("ramp_intervals[starting_billing_cycle][" + index + "]", rampIntervalStartingBillingCycle);
            return this;
        }
        public CreateRequest rampIntervalEndingBillingCycle(int index, Integer rampIntervalEndingBillingCycle) {
            params.addOpt("ramp_intervals[ending_billing_cycle][" + index + "]", rampIntervalEndingBillingCycle);
            return this;
        }
        public CreateRequest rampIntervalApplyCatalogPrice(int index, Boolean rampIntervalApplyCatalogPrice) {
            params.addOpt("ramp_intervals[apply_catalog_price][" + index + "]", rampIntervalApplyCatalogPrice);
            return this;
        }
        public CreateRequest rampIntervalPrice(int index, Long rampIntervalPrice) {
            params.addOpt("ramp_intervals[price][" + index + "]", rampIntervalPrice);
            return this;
        }
        public CreateRequest rampIntervalPriceInDecimal(int index, String rampIntervalPriceInDecimal) {
            params.addOpt("ramp_intervals[price_in_decimal][" + index + "]", rampIntervalPriceInDecimal);
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
    
        public UpdateRequest status(PriceRamp.Status status) {
            params.addOpt("status", status);
            return this;
        }


        public UpdateRequest rampIntervalStartingBillingCycle(int index, Integer rampIntervalStartingBillingCycle) {
            params.add("ramp_intervals[starting_billing_cycle][" + index + "]", rampIntervalStartingBillingCycle);
            return this;
        }
        public UpdateRequest rampIntervalEndingBillingCycle(int index, Integer rampIntervalEndingBillingCycle) {
            params.addOpt("ramp_intervals[ending_billing_cycle][" + index + "]", rampIntervalEndingBillingCycle);
            return this;
        }
        public UpdateRequest rampIntervalApplyCatalogPrice(int index, Boolean rampIntervalApplyCatalogPrice) {
            params.addOpt("ramp_intervals[apply_catalog_price][" + index + "]", rampIntervalApplyCatalogPrice);
            return this;
        }
        public UpdateRequest rampIntervalPrice(int index, Long rampIntervalPrice) {
            params.addOpt("ramp_intervals[price][" + index + "]", rampIntervalPrice);
            return this;
        }
        public UpdateRequest rampIntervalPriceInDecimal(int index, String rampIntervalPriceInDecimal) {
            params.addOpt("ramp_intervals[price_in_decimal][" + index + "]", rampIntervalPriceInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class PriceRampListRequest extends ListRequest<PriceRampListRequest> {

        private PriceRampListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<PriceRampListRequest> id() {
            return new StringFilter<PriceRampListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<PriceRampListRequest> itemPriceId() {
            return new StringFilter<PriceRampListRequest>("item_price_id",this).supportsMultiOperators(true);        
        }


        public EnumFilter<PriceRamp.Status, PriceRampListRequest> status() {
            return new EnumFilter<PriceRamp.Status, PriceRampListRequest>("status",this);        
        }


        public TimestampFilter<PriceRampListRequest> updatedAt() {
            return new TimestampFilter<PriceRampListRequest>("updated_at",this);        
        }


        public StringFilter<PriceRampListRequest> businessEntityId() {
            return new StringFilter<PriceRampListRequest>("business_entity_id",this).supportsPresenceOperator(true);        
        }


        public BooleanFilter<PriceRampListRequest> includeSiteLevelResources() {
            return new BooleanFilter<PriceRampListRequest>("include_site_level_resources",this);        
        }


        public PriceRampListRequest sortById(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","id");
            return this;
        }
        public PriceRampListRequest sortByStatus(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","status");
            return this;
        }
        public PriceRampListRequest sortByUpdatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","updated_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
