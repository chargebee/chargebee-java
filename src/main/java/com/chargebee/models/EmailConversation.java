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

public class EmailConversation extends Resource<EmailConversation> {

    //Constructors
    //============

    public EmailConversation(String jsonStr) {
        super(jsonStr);
    }

    public EmailConversation(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String customerId() {
        return optString("customer_id");
    }

    public String mailboxId() {
        return optString("mailbox_id");
    }

    public String subject() {
        return optString("subject");
    }

    public String lastMessagePreview() {
        return optString("last_message_preview");
    }

    public Integer messageCount() {
        return reqInteger("message_count");
    }

    public String status() {
        return optString("status");
    }

    public Timestamp lastMessageAt() {
        return optTimestamp("last_message_at");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Timestamp updatedAt() {
        return reqTimestamp("updated_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    // Operations
    //===========


}
