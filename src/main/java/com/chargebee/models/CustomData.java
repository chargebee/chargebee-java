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

public class CustomData extends Resource<CustomData> {

    //Constructors
    //============

    public CustomData(String jsonStr) {
        super(jsonStr);
    }

    public CustomData(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public EntityType entityType() {
        return reqEnum("entity_type", EntityType.class);
    }

    public String id() {
        return reqString("id");
    }

    public String schemaId() {
        return reqString("schema_id");
    }

    public String schemaValues() {
        return reqString("schema_values");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp modifiedAt() {
        return reqTimestamp("modified_at");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    // Operations
    //===========


}
