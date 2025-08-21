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

public class SubscriptionEntitlementsCreatedDetail extends Resource<SubscriptionEntitlementsCreatedDetail> {

    //Constructors
    //============

    public SubscriptionEntitlementsCreatedDetail(String jsonStr) {
        super(jsonStr);
    }

    public SubscriptionEntitlementsCreatedDetail(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String subscriptionId() {
        return optString("subscription_id");
    }

    public Boolean hasNext() {
        return reqBoolean("has_next");
    }

    // Operations
    //===========


}
