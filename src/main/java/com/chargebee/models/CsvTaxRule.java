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

public class CsvTaxRule extends Resource<CsvTaxRule> {

    public enum Status {
        ACTIVE,
        EXPIRED,
        SCHEDULED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum ServiceType {
        DIGITAL,
        OTHER,
        NOT_APPLICABLE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public CsvTaxRule(String jsonStr) {
        super(jsonStr);
    }

    public CsvTaxRule(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String taxProfileName() {
        return optString("tax_profile_name");
    }

    public String country() {
        return optString("country");
    }

    public String state() {
        return optString("state");
    }

    public String zipCode() {
        return optString("zip_code");
    }

    public Integer zipCodeStart() {
        return optInteger("zip_code_start");
    }

    public Integer zipCodeEnd() {
        return optInteger("zip_code_end");
    }

    public String tax1Name() {
        return reqString("tax1_name");
    }

    public Double tax1Rate() {
        return reqDouble("tax1_rate");
    }

    public Tax1JurisType tax1JurisType() {
        return optEnum("tax1_juris_type", Tax1JurisType.class);
    }

    public String tax1JurisName() {
        return optString("tax1_juris_name");
    }

    public String tax1JurisCode() {
        return optString("tax1_juris_code");
    }

    public String tax2Name() {
        return optString("tax2_name");
    }

    public Double tax2Rate() {
        return optDouble("tax2_rate");
    }

    public Tax2JurisType tax2JurisType() {
        return optEnum("tax2_juris_type", Tax2JurisType.class);
    }

    public String tax2JurisName() {
        return optString("tax2_juris_name");
    }

    public String tax2JurisCode() {
        return optString("tax2_juris_code");
    }

    public String tax3Name() {
        return optString("tax3_name");
    }

    public Double tax3Rate() {
        return optDouble("tax3_rate");
    }

    public Tax3JurisType tax3JurisType() {
        return optEnum("tax3_juris_type", Tax3JurisType.class);
    }

    public String tax3JurisName() {
        return optString("tax3_juris_name");
    }

    public String tax3JurisCode() {
        return optString("tax3_juris_code");
    }

    public String tax4Name() {
        return optString("tax4_name");
    }

    public Double tax4Rate() {
        return optDouble("tax4_rate");
    }

    public Tax4JurisType tax4JurisType() {
        return optEnum("tax4_juris_type", Tax4JurisType.class);
    }

    public String tax4JurisName() {
        return optString("tax4_juris_name");
    }

    public String tax4JurisCode() {
        return optString("tax4_juris_code");
    }

    public Status status() {
        return optEnum("status", Status.class);
    }

    public String timeZone() {
        return optString("time_zone");
    }

    public Timestamp validFrom() {
        return optTimestamp("valid_from");
    }

    public Timestamp validTill() {
        return optTimestamp("valid_till");
    }

    public ServiceType serviceType() {
        return optEnum("service_type", ServiceType.class);
    }

    public Integer ruleWeight() {
        return optInteger("rule_weight");
    }

    public Boolean overwrite() {
        return reqBoolean("overwrite");
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("csv_tax_rules");
        return new CreateRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest taxProfileName(String taxProfileName) {
            params.addOpt("tax_profile_name", taxProfileName);
            return this;
        }


        public CreateRequest country(String country) {
            params.addOpt("country", country);
            return this;
        }


        public CreateRequest state(String state) {
            params.addOpt("state", state);
            return this;
        }


        public CreateRequest zipCode(String zipCode) {
            params.addOpt("zip_code", zipCode);
            return this;
        }


        public CreateRequest zipCodeStart(Integer zipCodeStart) {
            params.addOpt("zip_code_start", zipCodeStart);
            return this;
        }


        public CreateRequest zipCodeEnd(Integer zipCodeEnd) {
            params.addOpt("zip_code_end", zipCodeEnd);
            return this;
        }


        public CreateRequest tax1Name(String tax1Name) {
            params.addOpt("tax1_name", tax1Name);
            return this;
        }


        public CreateRequest tax1Rate(Double tax1Rate) {
            params.addOpt("tax1_rate", tax1Rate);
            return this;
        }


        public CreateRequest tax1JurisType(com.chargebee.models.enums.Tax1JurisType tax1JurisType) {
            params.addOpt("tax1_juris_type", tax1JurisType);
            return this;
        }


        public CreateRequest tax1JurisName(String tax1JurisName) {
            params.addOpt("tax1_juris_name", tax1JurisName);
            return this;
        }


        public CreateRequest tax1JurisCode(String tax1JurisCode) {
            params.addOpt("tax1_juris_code", tax1JurisCode);
            return this;
        }


        public CreateRequest tax2Name(String tax2Name) {
            params.addOpt("tax2_name", tax2Name);
            return this;
        }


        public CreateRequest tax2Rate(Double tax2Rate) {
            params.addOpt("tax2_rate", tax2Rate);
            return this;
        }


        public CreateRequest tax2JurisType(com.chargebee.models.enums.Tax2JurisType tax2JurisType) {
            params.addOpt("tax2_juris_type", tax2JurisType);
            return this;
        }


        public CreateRequest tax2JurisName(String tax2JurisName) {
            params.addOpt("tax2_juris_name", tax2JurisName);
            return this;
        }


        public CreateRequest tax2JurisCode(String tax2JurisCode) {
            params.addOpt("tax2_juris_code", tax2JurisCode);
            return this;
        }


        public CreateRequest tax3Name(String tax3Name) {
            params.addOpt("tax3_name", tax3Name);
            return this;
        }


        public CreateRequest tax3Rate(Double tax3Rate) {
            params.addOpt("tax3_rate", tax3Rate);
            return this;
        }


        public CreateRequest tax3JurisType(com.chargebee.models.enums.Tax3JurisType tax3JurisType) {
            params.addOpt("tax3_juris_type", tax3JurisType);
            return this;
        }


        public CreateRequest tax3JurisName(String tax3JurisName) {
            params.addOpt("tax3_juris_name", tax3JurisName);
            return this;
        }


        public CreateRequest tax3JurisCode(String tax3JurisCode) {
            params.addOpt("tax3_juris_code", tax3JurisCode);
            return this;
        }


        public CreateRequest tax4Name(String tax4Name) {
            params.addOpt("tax4_name", tax4Name);
            return this;
        }


        public CreateRequest tax4Rate(Double tax4Rate) {
            params.addOpt("tax4_rate", tax4Rate);
            return this;
        }


        public CreateRequest tax4JurisType(com.chargebee.models.enums.Tax4JurisType tax4JurisType) {
            params.addOpt("tax4_juris_type", tax4JurisType);
            return this;
        }


        public CreateRequest tax4JurisName(String tax4JurisName) {
            params.addOpt("tax4_juris_name", tax4JurisName);
            return this;
        }


        public CreateRequest tax4JurisCode(String tax4JurisCode) {
            params.addOpt("tax4_juris_code", tax4JurisCode);
            return this;
        }


        public CreateRequest serviceType(CsvTaxRule.ServiceType serviceType) {
            params.addOpt("service_type", serviceType);
            return this;
        }


        public CreateRequest timeZone(String timeZone) {
            params.addOpt("time_zone", timeZone);
            return this;
        }


        public CreateRequest validFrom(Timestamp validFrom) {
            params.addOpt("valid_from", validFrom);
            return this;
        }


        public CreateRequest validTill(Timestamp validTill) {
            params.addOpt("valid_till", validTill);
            return this;
        }


        public CreateRequest overwrite(Boolean overwrite) {
            params.addOpt("overwrite", overwrite);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
