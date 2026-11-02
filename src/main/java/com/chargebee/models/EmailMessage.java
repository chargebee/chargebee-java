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

public class EmailMessage extends Resource<EmailMessage> {

    public enum AuthorType {
        CONTACT,
        SITE_USER,
        AGENT,
        OTHER,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum SendStatus {
        PENDING,
        SENT,
        FAILED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class Attachment extends Resource<Attachment> {
        public Attachment(JSONObject jsonObj) {
            super(jsonObj);
        }

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

    }

    //Constructors
    //============

    public EmailMessage(String jsonStr) {
        super(jsonStr);
    }

    public EmailMessage(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String emailConversationId() {
        return optString("email_conversation_id");
    }

    public String messageType() {
        return optString("message_type");
    }

    public AuthorType authorType() {
        return optEnum("author_type", AuthorType.class);
    }

    public SendStatus sendStatus() {
        return optEnum("send_status", SendStatus.class);
    }

    public String contentHtml() {
        return optString("content_html");
    }

    public String contentText() {
        return optString("content_text");
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

    public String mailboxId() {
        return optString("mailbox_id");
    }

    public String authorId() {
        return optString("author_id");
    }

    public String authorName() {
        return optString("author_name");
    }

    public String fromEmail() {
        return optString("from_email");
    }

    public List<String> toEmails() {
        return optList("to_emails", String.class);
    }

    public List<String> ccEmails() {
        return optList("cc_emails", String.class);
    }

    public List<EmailMessage.Attachment> attachments() {
        return optList("attachments", EmailMessage.Attachment.class);
    }

    // Operations
    //===========


}
