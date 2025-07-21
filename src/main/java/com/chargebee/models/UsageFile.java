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

public class UsageFile extends Resource<UsageFile> {

    public enum Status {
        QUEUED,
        IMPORTED,
        PROCESSING,
        PROCESSED,
        FAILED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class UploadDetail extends Resource<UploadDetail> {
        public UploadDetail(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String url() {
            return reqString("url");
        }

        public Timestamp expiresAt() {
            return reqTimestamp("expires_at");
        }

    }

    //Constructors
    //============

    public UsageFile(String jsonStr) {
        super(jsonStr);
    }

    public UsageFile(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String name() {
        return reqString("name");
    }

    public String mimeType() {
        return reqString("mime_type");
    }

    public String errorCode() {
        return optString("error_code");
    }

    public String errorReason() {
        return optString("error_reason");
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public Long totalRecordsCount() {
        return optLong("total_records_count");
    }

    public Long processedRecordsCount() {
        return optLong("processed_records_count");
    }

    public Long failedRecordsCount() {
        return optLong("failed_records_count");
    }

    public Long fileSizeInBytes() {
        return optLong("file_size_in_bytes");
    }

    public Timestamp processingStartedAt() {
        return optTimestamp("processing_started_at");
    }

    public Timestamp processingCompletedAt() {
        return optTimestamp("processing_completed_at");
    }

    public String uploadedBy() {
        return optString("uploaded_by");
    }

    public Timestamp uploadedAt() {
        return optTimestamp("uploaded_at");
    }

    public UsageFile.UploadDetail uploadDetails() {
        return optSubResource("upload_details", UsageFile.UploadDetail.class);
    }

    // Operations
    //===========

    public static UploadRequest upload() {
        String uri = uri("usage_files", "upload");
        return new UploadRequest(Method.POST, uri).setIdempotency(false);
    }

    public static Request status(String id) {
        String uri = uri("usage_files", nullCheck(id), "status");
        return new Request(Method.GET, uri);
    }


    // Operation Request Classes
    //==========================

    public static class UploadRequest extends Request<UploadRequest> {

        private UploadRequest(Method httpMeth, String uri) {
            super(httpMeth, uri, null, "file-ingest");
        }
    
        public UploadRequest fileName(String fileName) {
            params.add("file_name", fileName);
            return this;
        }


        public UploadRequest mimeType(String mimeType) {
            params.add("mime_type", mimeType);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
