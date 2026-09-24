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

public class Einvoice extends Resource<Einvoice> {

    public enum EntityType {
        INVOICE,
        CREDIT_NOTE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Status {
        SCHEDULED,
        SKIPPED,
        IN_PROGRESS,
        SUCCESS,
        FAILED,
        REGISTERED,
        ACCEPTED,
        REJECTED,
        MESSAGE_ACKNOWLEDGEMENT,
        IN_PROCESS,
        UNDER_QUERY,
        CONDITIONALLY_ACCEPTED,
        PAID,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class Artifact extends Resource<Artifact> {
        public enum Direction {
             OUTBOUND,INBOUND,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum Status {
             SCHEDULED,SKIPPED,IN_PROGRESS,SUCCESS,FAILED,REGISTERED,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public Artifact(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String artifactType() {
            return reqString("artifact_type");
        }

        public Direction direction() {
            return reqEnum("direction", Direction.class);
        }

        public Status status() {
            return reqEnum("status", Status.class);
        }

        public String code() {
            return optString("code");
        }

        public String externalArtifactId() {
            return optString("external_artifact_id");
        }

        public Timestamp createdAt() {
            return reqTimestamp("created_at");
        }

        public Long resourceVersion() {
            return optLong("resource_version");
        }

        public Timestamp updatedAt() {
            return optTimestamp("updated_at");
        }

        public Boolean deleted() {
            return reqBoolean("deleted");
        }

    }

    //Constructors
    //============

    public Einvoice(String jsonStr) {
        super(jsonStr);
    }

    public Einvoice(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public EntityType entityType() {
        return reqEnum("entity_type", EntityType.class);
    }

    public String entityId() {
        return reqString("entity_id");
    }

    public String referenceId() {
        return optString("reference_id");
    }

    public String referenceNumber() {
        return optString("reference_number");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public String message() {
        return optString("message");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public Boolean deleted() {
        return reqBoolean("deleted");
    }

    public JSONArray providerReferences() {
        return optJSONArray("provider_references");
    }

    public String businessEntityId() {
        return optString("business_entity_id");
    }

    public List<Einvoice.Artifact> artifacts() {
        return optList("artifacts", Einvoice.Artifact.class);
    }

    // Operations
    //===========

    public static Request retrieve(String id) {
        String uri = uri("einvoices", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static EinvoiceListEinvoicesRequest listEinvoices() {
        String uri = uri("einvoices");
        return new EinvoiceListEinvoicesRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class EinvoiceListEinvoicesRequest extends ListRequest<EinvoiceListEinvoicesRequest> {

        private EinvoiceListEinvoicesRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<EinvoiceListEinvoicesRequest> id() {
            return new StringFilter<EinvoiceListEinvoicesRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<EinvoiceListEinvoicesRequest> referenceId() {
            return new StringFilter<EinvoiceListEinvoicesRequest>("reference_id",this).supportsMultiOperators(true);        
        }


        public TimestampFilter<EinvoiceListEinvoicesRequest> updatedAt() {
            return new TimestampFilter<EinvoiceListEinvoicesRequest>("updated_at",this);        
        }


        public EinvoiceListEinvoicesRequest sortByUpdatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","updated_at");
            return this;
        }


        public EinvoiceListEinvoicesRequest invoiceId(String invoiceId) {
            params.addOpt("invoice_id", invoiceId);
            return this;
        }


        public EinvoiceListEinvoicesRequest creditNoteId(String creditNoteId) {
            params.addOpt("credit_note_id", creditNoteId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
