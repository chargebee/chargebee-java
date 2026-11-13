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

public class OrganizationDetail extends Resource<OrganizationDetail> {

    //Constructors
    //============

    public OrganizationDetail(String jsonStr) {
        super(jsonStr);
    }

    public OrganizationDetail(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String organizationName() {
        return optString("organization_name");
    }

    public String email() {
        return optString("email");
    }

    public String addressLine1() {
        return optString("address_line1");
    }

    public String addressLine2() {
        return optString("address_line2");
    }

    public String addressLine3() {
        return optString("address_line3");
    }

    public String city() {
        return optString("city");
    }

    public String stateCode() {
        return optString("state_code");
    }

    public String state() {
        return optString("state");
    }

    public String countryCode() {
        return optString("country_code");
    }

    public String zip() {
        return optString("zip");
    }

    public String phone() {
        return optString("phone");
    }

    public String fax() {
        return optString("fax");
    }

    public String website() {
        return optString("website");
    }

    public Boolean isVatNumberPresent() {
        return optBoolean("is_vat_number_present");
    }

    public String vatNumber() {
        return optString("vat_number");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public String businessEntityId() {
        return optString("business_entity_id");
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("organization_details");
        return new CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("organization_details", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static OrganizationDetailListRequest list() {
        String uri = uri("organization_details");
        return new OrganizationDetailListRequest(uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest organizationName(String organizationName) {
            params.add("organization_name", organizationName);
            return this;
        }


        public CreateRequest email(String email) {
            params.addOpt("email", email);
            return this;
        }


        public CreateRequest addressLine1(String addressLine1) {
            params.add("address_line1", addressLine1);
            return this;
        }


        public CreateRequest addressLine2(String addressLine2) {
            params.addOpt("address_line2", addressLine2);
            return this;
        }


        public CreateRequest addressLine3(String addressLine3) {
            params.addOpt("address_line3", addressLine3);
            return this;
        }


        public CreateRequest city(String city) {
            params.add("city", city);
            return this;
        }


        public CreateRequest stateCode(String stateCode) {
            params.addOpt("state_code", stateCode);
            return this;
        }


        public CreateRequest state(String state) {
            params.add("state", state);
            return this;
        }


        public CreateRequest zip(String zip) {
            params.add("zip", zip);
            return this;
        }


        public CreateRequest countryCode(String countryCode) {
            params.addOpt("country_code", countryCode);
            return this;
        }


        public CreateRequest phone(String phone) {
            params.add("phone", phone);
            return this;
        }


        public CreateRequest fax(String fax) {
            params.addOpt("fax", fax);
            return this;
        }


        public CreateRequest website(String website) {
            params.addOpt("website", website);
            return this;
        }


        public CreateRequest isVatNumberPresent(Boolean isVatNumberPresent) {
            params.addOpt("is_vat_number_present", isVatNumberPresent);
            return this;
        }


        public CreateRequest vatNumber(String vatNumber) {
            params.addOpt("vat_number", vatNumber);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class OrganizationDetailListRequest extends ListRequest<OrganizationDetailListRequest> {

        private OrganizationDetailListRequest(String uri) {
            super(uri);
        }
    
        public StringFilter<OrganizationDetailListRequest> businessEntityId() {
            return new StringFilter<OrganizationDetailListRequest>("business_entity_id",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
