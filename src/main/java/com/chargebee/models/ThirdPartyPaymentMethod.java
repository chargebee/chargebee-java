package com.chargebee.models;

import com.chargebee.internal.*;
import com.chargebee.models.enums.*;
import org.json.*;

public class ThirdPartyPaymentMethod extends Resource<ThirdPartyPaymentMethod> {

    //Constructors
    //============

    public ThirdPartyPaymentMethod(String jsonStr) {
        super(jsonStr);
    }

    public ThirdPartyPaymentMethod(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public Gateway gateway() {
        return reqEnum("gateway", Gateway.class);
    }

    public String gatewayAccountId() {
        return optString("gateway_account_id");
    }

    public String referenceId() {
        return reqString("reference_id");
    }

    // Operations
    //===========


}
