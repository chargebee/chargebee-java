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

public class OmnichannelSubscriptionItemOffer extends Resource<OmnichannelSubscriptionItemOffer> {

    //Constructors
    //============

    public OmnichannelSubscriptionItemOffer(String jsonStr) {
        super(jsonStr);
    }

    public OmnichannelSubscriptionItemOffer(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String offerIdAtSource() {
        return optString("offer_id_at_source");
    }

    public Category category() {
        return reqEnum("category", Category.class);
    }

    public String categoryAtSource() {
        return optString("category_at_source");
    }

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public String typeAtSource() {
        return optString("type_at_source");
    }

    public DiscountType discountType() {
        return optEnum("discount_type", DiscountType.class);
    }

    public String duration() {
        return reqString("duration");
    }

    public Double percentage() {
        return optDouble("percentage");
    }

    public String priceCurrency() {
        return optString("price_currency");
    }

    public Long priceUnits() {
        return optLong("price_units");
    }

    public Long priceNanos() {
        return optLong("price_nanos");
    }

    public Timestamp offerTermStart() {
        return optTimestamp("offer_term_start");
    }

    public Timestamp offerTermEnd() {
        return optTimestamp("offer_term_end");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    // Operations
    //===========


}
