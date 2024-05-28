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

public class BusinessEntityTransfer extends Resource<BusinessEntityTransfer> {

    public enum ResourceType {
        CUSTOMER,
        SUBSCRIPTION,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum ReasonCode {
        CORRECTION,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public BusinessEntityTransfer(String jsonStr) {
        super(jsonStr);
    }

    public BusinessEntityTransfer(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public ResourceType resourceType() {
        return reqEnum("resource_type", ResourceType.class);
    }

    public String resourceId() {
        return reqString("resource_id");
    }

    public String activeResourceId() {
        return reqString("active_resource_id");
    }

    public String destinationBusinessEntityId() {
        return reqString("destination_business_entity_id");
    }

    public String sourceBusinessEntityId() {
        return reqString("source_business_entity_id");
    }

    public ReasonCode reasonCode() {
        return reqEnum("reason_code", ReasonCode.class);
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    // Operations
    //===========


}
