package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class Addon extends Resource<Addon> {

    public enum Type {
        ON_OFF,
        QUANTITY,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum ChargeType {
        RECURRING,
        NON_RECURRING,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum PeriodUnit {
        WEEK,
        MONTH,
        YEAR,
        NOT_APPLICABLE,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Status {
        ACTIVE,
        ARCHIVED,
        DELETED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    //Constructors
    //============

    public Addon(String jsonStr) {
        super(jsonStr);
    }

    public Addon(JSONObject jsonObj) {
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

    public String invoiceName() {
        return optString("invoice_name");
    }

    public String description() {
        return optString("description");
    }

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public ChargeType chargeType() {
        return reqEnum("charge_type", ChargeType.class);
    }

    public Integer price() {
        return reqInteger("price");
    }

    public Integer period() {
        return optInteger("period");
    }

    public PeriodUnit periodUnit() {
        return reqEnum("period_unit", PeriodUnit.class);
    }

    public String unit() {
        return optString("unit");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp archivedAt() {
        return optTimestamp("archived_at");
    }

    public Boolean enabledInPortal() {
        return reqBoolean("enabled_in_portal");
    }

    public String invoiceNotes() {
        return optString("invoice_notes");
    }

    public Boolean taxable() {
        return optBoolean("taxable");
    }

    // Operations
    //===========

    public static CreateRequest create() throws IOException {
        String uri = uri("addons");
        return new CreateRequest(Method.POST, uri);
    }

    public static UpdateRequest update(String id) throws IOException {
        String uri = uri("addons", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static ListRequest list() throws IOException {
        String uri = uri("addons");
        return new ListRequest(uri);
    }

    public static Request retrieve(String id) throws IOException {
        String uri = uri("addons", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static Request delete(String id) throws IOException {
        String uri = uri("addons", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest id(String id) {
            params.add("id", id);
            return this;
        }


        public CreateRequest name(String name) {
            params.add("name", name);
            return this;
        }


        public CreateRequest invoiceName(String invoiceName) {
            params.addOpt("invoice_name", invoiceName);
            return this;
        }


        public CreateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        public CreateRequest chargeType(ChargeType chargeType) {
            params.add("charge_type", chargeType);
            return this;
        }


        public CreateRequest price(Integer price) {
            params.addOpt("price", price);
            return this;
        }


        public CreateRequest period(Integer period) {
            params.addOpt("period", period);
            return this;
        }


        public CreateRequest periodUnit(PeriodUnit periodUnit) {
            params.addOpt("period_unit", periodUnit);
            return this;
        }


        public CreateRequest type(Type type) {
            params.add("type", type);
            return this;
        }


        public CreateRequest unit(String unit) {
            params.addOpt("unit", unit);
            return this;
        }


        public CreateRequest enabledInPortal(Boolean enabledInPortal) {
            params.addOpt("enabled_in_portal", enabledInPortal);
            return this;
        }


        public CreateRequest taxable(Boolean taxable) {
            params.addOpt("taxable", taxable);
            return this;
        }


        public CreateRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateRequest extends Request<UpdateRequest> {

        private UpdateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }


        public UpdateRequest name(String name) {
            params.addOpt("name", name);
            return this;
        }


        public UpdateRequest invoiceName(String invoiceName) {
            params.addOpt("invoice_name", invoiceName);
            return this;
        }


        public UpdateRequest description(String description) {
            params.addOpt("description", description);
            return this;
        }


        public UpdateRequest chargeType(ChargeType chargeType) {
            params.addOpt("charge_type", chargeType);
            return this;
        }


        public UpdateRequest price(Integer price) {
            params.addOpt("price", price);
            return this;
        }


        public UpdateRequest period(Integer period) {
            params.addOpt("period", period);
            return this;
        }


        public UpdateRequest periodUnit(PeriodUnit periodUnit) {
            params.addOpt("period_unit", periodUnit);
            return this;
        }


        public UpdateRequest type(Type type) {
            params.addOpt("type", type);
            return this;
        }


        public UpdateRequest unit(String unit) {
            params.addOpt("unit", unit);
            return this;
        }


        public UpdateRequest enabledInPortal(Boolean enabledInPortal) {
            params.addOpt("enabled_in_portal", enabledInPortal);
            return this;
        }


        public UpdateRequest taxable(Boolean taxable) {
            params.addOpt("taxable", taxable);
            return this;
        }


        public UpdateRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
