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

public class PrivateNote extends Resource<PrivateNote> {

    public enum AuthorType {
        SITE_USER,
        AGENT,
        OTHER,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public PrivateNote(String jsonStr) {
        super(jsonStr);
    }

    public PrivateNote(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String emailConversationId() {
        return reqString("email_conversation_id");
    }

    public AuthorType authorType() {
        return optEnum("author_type", AuthorType.class);
    }

    public String authorId() {
        return optString("author_id");
    }

    public String authorName() {
        return optString("author_name");
    }

    public String contentHtml() {
        return reqString("content_html");
    }

    public String contentText() {
        return optString("content_text");
    }

    public String metaData() {
        return optString("meta_data");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return reqTimestamp("updated_at");
    }

    // Operations
    //===========


}
