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

public class CustomDataSchema extends Resource<CustomDataSchema> {

    public enum Status {
        ACTIVE,
        ARCHIVED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public CustomDataSchema(String jsonStr) {
        super(jsonStr);
    }

    public CustomDataSchema(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String displayName() {
        return reqString("display_name");
    }

    public EntityType entityType() {
        return reqEnum("entity_type", EntityType.class);
    }

    public String schemaDefinition() {
        return reqString("schema_definition");
    }

    public Status status() {
        return reqEnum("status", Status.class);
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
