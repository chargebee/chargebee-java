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

public class Usage extends Resource<Usage> {

    //Constructors
    //============

    public Usage(String jsonStr) {
        super(jsonStr);
    }

    public Usage(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return optString("id");
    }

    public Timestamp usageDate() {
        return reqTimestamp("usage_date");
    }

    public String subscriptionId() {
        return reqString("subscription_id");
    }

    public String itemPriceId() {
        return reqString("item_price_id");
    }

    public String invoiceId() {
        return optString("invoice_id");
    }

    public String lineItemId() {
        return optString("line_item_id");
    }

    public String quantity() {
        return reqString("quantity");
    }

    public Source source() {
        return optEnum("source", Source.class);
    }

    public String note() {
        return optString("note");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    // Operations
    //===========

    public static CreateRequest create(String id) {
        String uri = uri("subscriptions", nullCheck(id), "usages");
        return new CreateRequest(Method.POST, uri);
    }

    public static RetrieveRequest retrieve(String id) {
        String uri = uri("subscriptions", nullCheck(id), "usages");
        return new RetrieveRequest(Method.GET, uri);
    }

    public static DeleteRequest delete(String id) {
        String uri = uri("subscriptions", nullCheck(id), "delete_usage");
        return new DeleteRequest(Method.POST, uri);
    }

    public static UsageListRequest list() {
        String uri = uri("usages");
        return new UsageListRequest(uri);
    }

    public static PdfRequest pdf() {
        String uri = uri("usages", "pdf");
        return new PdfRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }


        public CreateRequest itemPriceId(String itemPriceId) {
            params.add("item_price_id", itemPriceId);
            return this;
        }


        public CreateRequest quantity(String quantity) {
            params.add("quantity", quantity);
            return this;
        }


        public CreateRequest usageDate(Timestamp usageDate) {
            params.add("usage_date", usageDate);
            return this;
        }


        @Deprecated
        public CreateRequest dedupeOption(com.chargebee.models.enums.DedupeOption dedupeOption) {
            params.addOpt("dedupe_option", dedupeOption);
            return this;
        }


        public CreateRequest note(String note) {
            params.addOpt("note", note);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class RetrieveRequest extends Request<RetrieveRequest> {

        private RetrieveRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RetrieveRequest id(String id) {
            params.add("id", id);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class DeleteRequest extends Request<DeleteRequest> {

        private DeleteRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public DeleteRequest id(String id) {
            params.add("id", id);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class UsageListRequest extends ListRequest<UsageListRequest> {

        private UsageListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<UsageListRequest> id() {
            return new StringFilter<UsageListRequest>("id",this);        
        }


        public StringFilter<UsageListRequest> subscriptionId() {
            return new StringFilter<UsageListRequest>("subscription_id",this);        
        }


        public TimestampFilter<UsageListRequest> usageDate() {
            return new TimestampFilter<UsageListRequest>("usage_date",this);        
        }


        public StringFilter<UsageListRequest> itemPriceId() {
            return new StringFilter<UsageListRequest>("item_price_id",this);        
        }


        public StringFilter<UsageListRequest> invoiceId() {
            return new StringFilter<UsageListRequest>("invoice_id",this).supportsPresenceOperator(true);        
        }


        public EnumFilter<com.chargebee.models.enums.Source, UsageListRequest> source() {
            return new EnumFilter<com.chargebee.models.enums.Source, UsageListRequest>("source",this);        
        }


        public UsageListRequest sortByUsageDate(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","usage_date");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class PdfRequest extends Request<PdfRequest> {

        private PdfRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public PdfRequest dispositionType(com.chargebee.models.enums.DispositionType dispositionType) {
            params.addOpt("disposition_type", dispositionType);
            return this;
        }


        public PdfRequest invoiceId(String invoiceId) {
            params.add("invoice[id]", invoiceId);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

}
