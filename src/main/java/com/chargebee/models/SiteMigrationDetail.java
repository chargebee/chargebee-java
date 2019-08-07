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

public class SiteMigrationDetail extends Resource<SiteMigrationDetail> {

    public enum Status {
        MOVED_IN,
        MOVED_OUT,
        MOVING_OUT,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public SiteMigrationDetail(String jsonStr) {
        super(jsonStr);
    }

    public SiteMigrationDetail(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String entityId() {
        return reqString("entity_id");
    }

    public String otherSiteName() {
        return reqString("other_site_name");
    }

    public String entityIdAtOtherSite() {
        return reqString("entity_id_at_other_site");
    }

    public Timestamp migratedAt() {
        return reqTimestamp("migrated_at");
    }

    public EntityType entityType() {
        return reqEnum("entity_type", EntityType.class);
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    // Operations
    //===========

    public static SiteMigrationDetailListRequest list() {
        String uri = uri("site_migration_details");
        return new SiteMigrationDetailListRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class SiteMigrationDetailListRequest extends ListRequest<SiteMigrationDetailListRequest> {

        private SiteMigrationDetailListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<SiteMigrationDetailListRequest> entityIdAtOtherSite() {
            return new StringFilter<SiteMigrationDetailListRequest>("entity_id_at_other_site",this);        
        }


        public StringFilter<SiteMigrationDetailListRequest> otherSiteName() {
            return new StringFilter<SiteMigrationDetailListRequest>("other_site_name",this);        
        }


        public StringFilter<SiteMigrationDetailListRequest> entityId() {
            return new StringFilter<SiteMigrationDetailListRequest>("entity_id",this);        
        }


        public EnumFilter<com.chargebee.models.enums.EntityType, SiteMigrationDetailListRequest> entityType() {
            return new EnumFilter<com.chargebee.models.enums.EntityType, SiteMigrationDetailListRequest>("entity_type",this);        
        }


        public EnumFilter<Status, SiteMigrationDetailListRequest> status() {
            return new EnumFilter<Status, SiteMigrationDetailListRequest>("status",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
