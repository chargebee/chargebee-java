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

public class EmailAttachment extends Resource<EmailAttachment> {

    //Constructors
    //============

    public EmailAttachment(String jsonStr) {
        super(jsonStr);
    }

    public EmailAttachment(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String emailMessageId() {
        return optString("email_message_id");
    }

    public String fileName() {
        return optString("file_name");
    }

    public String contentType() {
        return optString("content_type");
    }

    public Long sizeBytes() {
        return optLong("size_bytes");
    }

    public String contentDisposition() {
        return optString("content_disposition");
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
