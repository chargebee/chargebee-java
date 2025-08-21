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

public class OmnichannelOneTimeOrder extends Resource<OmnichannelOneTimeOrder> {

    public enum Source {
        APPLE_APP_STORE,
        GOOGLE_PLAY_STORE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class OmnichannelTransaction extends Resource<OmnichannelTransaction> {
        public enum Type {
             PURCHASE,RENEWAL,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public OmnichannelTransaction(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public String idAtSource() {
            return reqString("id_at_source");
        }

        public String appId() {
            return reqString("app_id");
        }

        public String priceCurrency() {
            return optString("price_currency");
        }

        public Long priceUnits() {
            return optLong("price_units");
        }

        public Long priceNanos() {
            return optLong("price_nanos");
        }

        public Type type() {
            return reqEnum("type", Type.class);
        }

        public Timestamp transactedAt() {
            return optTimestamp("transacted_at");
        }

        public Timestamp createdAt() {
            return reqTimestamp("created_at");
        }

        public Long resourceVersion() {
            return optLong("resource_version");
        }

    }

    //Constructors
    //============

    public OmnichannelOneTimeOrder(String jsonStr) {
        super(jsonStr);
    }

    public OmnichannelOneTimeOrder(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String appId() {
        return reqString("app_id");
    }

    public String customerId() {
        return optString("customer_id");
    }

    public String idAtSource() {
        return reqString("id_at_source");
    }

    public String origin() {
        return optString("origin");
    }

    public Source source() {
        return reqEnum("source", Source.class);
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public List<OmnichannelOneTimeOrderItem> omnichannelOneTimeOrderItems() {
        return reqList("omnichannel_one_time_order_items", OmnichannelOneTimeOrderItem.class);
    }

    public OmnichannelOneTimeOrder.OmnichannelTransaction purchaseTransaction() {
        return optSubResource("purchase_transaction", OmnichannelOneTimeOrder.OmnichannelTransaction.class);
    }

    // Operations
    //===========

    public static Request retrieve(String id) {
        String uri = uri("omnichannel_one_time_orders", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static OmnichannelOneTimeOrderListRequest list() {
        String uri = uri("omnichannel_one_time_orders");
        return new OmnichannelOneTimeOrderListRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class OmnichannelOneTimeOrderListRequest extends ListRequest<OmnichannelOneTimeOrderListRequest> {

        private OmnichannelOneTimeOrderListRequest(String uri) {
            super(uri);
        }
    
        public EnumFilter<OmnichannelOneTimeOrder.Source, OmnichannelOneTimeOrderListRequest> source() {
            return new EnumFilter<OmnichannelOneTimeOrder.Source, OmnichannelOneTimeOrderListRequest>("source",this);        
        }


        public StringFilter<OmnichannelOneTimeOrderListRequest> customerId() {
            return new StringFilter<OmnichannelOneTimeOrderListRequest>("customer_id",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
