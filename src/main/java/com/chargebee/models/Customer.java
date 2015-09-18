package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class Customer extends Resource<Customer> {

    public enum CardStatus {
        NO_CARD,
        VALID,
        EXPIRING,
        EXPIRED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class BillingAddress extends Resource<BillingAddress> {
        public BillingAddress(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String firstName() {
            return optString("first_name");
        }

        public String lastName() {
            return optString("last_name");
        }

        public String email() {
            return optString("email");
        }

        public String company() {
            return optString("company");
        }

        public String phone() {
            return optString("phone");
        }

        public String line1() {
            return optString("line1");
        }

        public String line2() {
            return optString("line2");
        }

        public String line3() {
            return optString("line3");
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

        public String country() {
            return optString("country");
        }

        public String zip() {
            return optString("zip");
        }

    }

    public static class PaymentMethod extends Resource<PaymentMethod> {
        public enum Type {
             CARD,PAYPAL_EXPRESS_CHECKOUT,AMAZON_PAYMENTS,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum Status {
             VALID,EXPIRING,EXPIRED,INVALID,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public PaymentMethod(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Type type() {
            return optEnum("type", Type.class);
        }

        public Gateway gateway() {
            return reqEnum("gateway", Gateway.class);
        }

        public Status status() {
            return reqEnum("status", Status.class);
        }

        public String referenceId() {
            return optString("reference_id");
        }

    }

    //Constructors
    //============

    public Customer(String jsonStr) {
        super(jsonStr);
    }

    public Customer(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String firstName() {
        return optString("first_name");
    }

    public String lastName() {
        return optString("last_name");
    }

    public String email() {
        return optString("email");
    }

    public String phone() {
        return optString("phone");
    }

    public String company() {
        return optString("company");
    }

    public String vatNumber() {
        return optString("vat_number");
    }

    public AutoCollection autoCollection() {
        return reqEnum("auto_collection", AutoCollection.class);
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public String createdFromIp() {
        return optString("created_from_ip");
    }

    public Taxability taxability() {
        return optEnum("taxability", Taxability.class);
    }

    public CardStatus cardStatus() {
        return optEnum("card_status", CardStatus.class);
    }

    public Customer.BillingAddress billingAddress() {
        return optSubResource("billing_address", Customer.BillingAddress.class);
    }

    public Customer.PaymentMethod paymentMethod() {
        return optSubResource("payment_method", Customer.PaymentMethod.class);
    }

    public String invoiceNotes() {
        return optString("invoice_notes");
    }

    public Integer accountCredits() {
        return reqInteger("account_credits");
    }

    // Operations
    //===========

    public static CreateRequest create() throws IOException {
        String uri = uri("customers");
        return new CreateRequest(Method.POST, uri);
    }

    public static ListRequest list() throws IOException {
        String uri = uri("customers");
        return new ListRequest(uri);
    }

    public static Request retrieve(String id) throws IOException {
        String uri = uri("customers", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static UpdateRequest update(String id) throws IOException {
        String uri = uri("customers", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static UpdatePaymentMethodRequest updatePaymentMethod(String id) throws IOException {
        String uri = uri("customers", nullCheck(id), "update_payment_method");
        return new UpdatePaymentMethodRequest(Method.POST, uri);
    }

    public static UpdateBillingInfoRequest updateBillingInfo(String id) throws IOException {
        String uri = uri("customers", nullCheck(id), "update_billing_info");
        return new UpdateBillingInfoRequest(Method.POST, uri);
    }

    public static AddAccountCreditsRequest addAccountCredits(String id) throws IOException {
        String uri = uri("customers", nullCheck(id), "add_account_credits");
        return new AddAccountCreditsRequest(Method.POST, uri);
    }

    public static DeductAccountCreditsRequest deductAccountCredits(String id) throws IOException {
        String uri = uri("customers", nullCheck(id), "deduct_account_credits");
        return new DeductAccountCreditsRequest(Method.POST, uri);
    }

    public static SetAccountCreditsRequest setAccountCredits(String id) throws IOException {
        String uri = uri("customers", nullCheck(id), "set_account_credits");
        return new SetAccountCreditsRequest(Method.POST, uri);
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


        public CreateRequest firstName(String firstName) {
            params.addOpt("first_name", firstName);
            return this;
        }


        public CreateRequest lastName(String lastName) {
            params.addOpt("last_name", lastName);
            return this;
        }


        public CreateRequest email(String email) {
            params.addOpt("email", email);
            return this;
        }


        public CreateRequest phone(String phone) {
            params.addOpt("phone", phone);
            return this;
        }


        public CreateRequest company(String company) {
            params.addOpt("company", company);
            return this;
        }


        public CreateRequest autoCollection(AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        public CreateRequest vatNumber(String vatNumber) {
            params.addOpt("vat_number", vatNumber);
            return this;
        }


        public CreateRequest taxability(Taxability taxability) {
            params.addOpt("taxability", taxability);
            return this;
        }


        public CreateRequest createdFromIp(String createdFromIp) {
            params.addOpt("created_from_ip", createdFromIp);
            return this;
        }


        public CreateRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        public CreateRequest cardGateway(Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public CreateRequest cardTmpToken(String cardTmpToken) {
            params.addOpt("card[tmp_token]", cardTmpToken);
            return this;
        }

        public CreateRequest paymentMethodType(Type paymentMethodType) {
            params.addOpt("payment_method[type]", paymentMethodType);
            return this;
        }

        public CreateRequest paymentMethodGateway(Gateway paymentMethodGateway) {
            params.addOpt("payment_method[gateway]", paymentMethodGateway);
            return this;
        }

        public CreateRequest paymentMethodReferenceId(String paymentMethodReferenceId) {
            params.addOpt("payment_method[reference_id]", paymentMethodReferenceId);
            return this;
        }

        public CreateRequest cardFirstName(String cardFirstName) {
            params.addOpt("card[first_name]", cardFirstName);
            return this;
        }

        public CreateRequest cardLastName(String cardLastName) {
            params.addOpt("card[last_name]", cardLastName);
            return this;
        }

        public CreateRequest cardNumber(String cardNumber) {
            params.addOpt("card[number]", cardNumber);
            return this;
        }

        public CreateRequest cardExpiryMonth(Integer cardExpiryMonth) {
            params.addOpt("card[expiry_month]", cardExpiryMonth);
            return this;
        }

        public CreateRequest cardExpiryYear(Integer cardExpiryYear) {
            params.addOpt("card[expiry_year]", cardExpiryYear);
            return this;
        }

        public CreateRequest cardCvv(String cardCvv) {
            params.addOpt("card[cvv]", cardCvv);
            return this;
        }

        public CreateRequest cardBillingAddr1(String cardBillingAddr1) {
            params.addOpt("card[billing_addr1]", cardBillingAddr1);
            return this;
        }

        public CreateRequest cardBillingAddr2(String cardBillingAddr2) {
            params.addOpt("card[billing_addr2]", cardBillingAddr2);
            return this;
        }

        public CreateRequest cardBillingCity(String cardBillingCity) {
            params.addOpt("card[billing_city]", cardBillingCity);
            return this;
        }

        public CreateRequest cardBillingStateCode(String cardBillingStateCode) {
            params.addOpt("card[billing_state_code]", cardBillingStateCode);
            return this;
        }

        public CreateRequest cardBillingState(String cardBillingState) {
            params.addOpt("card[billing_state]", cardBillingState);
            return this;
        }

        public CreateRequest cardBillingZip(String cardBillingZip) {
            params.addOpt("card[billing_zip]", cardBillingZip);
            return this;
        }

        public CreateRequest cardBillingCountry(String cardBillingCountry) {
            params.addOpt("card[billing_country]", cardBillingCountry);
            return this;
        }

        public CreateRequest cardIpAddress(String cardIpAddress) {
            params.addOpt("card[ip_address]", cardIpAddress);
            return this;
        }

        public CreateRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }

        public CreateRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }

        public CreateRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }

        public CreateRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }

        public CreateRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }

        public CreateRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public CreateRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public CreateRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public CreateRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public CreateRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public CreateRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }

        public CreateRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public CreateRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
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
    
        public UpdateRequest firstName(String firstName) {
            params.addOpt("first_name", firstName);
            return this;
        }


        public UpdateRequest lastName(String lastName) {
            params.addOpt("last_name", lastName);
            return this;
        }


        public UpdateRequest email(String email) {
            params.addOpt("email", email);
            return this;
        }


        public UpdateRequest phone(String phone) {
            params.addOpt("phone", phone);
            return this;
        }


        public UpdateRequest company(String company) {
            params.addOpt("company", company);
            return this;
        }


        public UpdateRequest autoCollection(AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        public UpdateRequest taxability(Taxability taxability) {
            params.addOpt("taxability", taxability);
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

    public static class UpdatePaymentMethodRequest extends Request<UpdatePaymentMethodRequest> {

        private UpdatePaymentMethodRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdatePaymentMethodRequest paymentMethodType(Type paymentMethodType) {
            params.add("payment_method[type]", paymentMethodType);
            return this;
        }

        public UpdatePaymentMethodRequest paymentMethodGateway(Gateway paymentMethodGateway) {
            params.addOpt("payment_method[gateway]", paymentMethodGateway);
            return this;
        }

        public UpdatePaymentMethodRequest paymentMethodReferenceId(String paymentMethodReferenceId) {
            params.add("payment_method[reference_id]", paymentMethodReferenceId);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateBillingInfoRequest extends Request<UpdateBillingInfoRequest> {

        private UpdateBillingInfoRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateBillingInfoRequest vatNumber(String vatNumber) {
            params.addOpt("vat_number", vatNumber);
            return this;
        }


        public UpdateBillingInfoRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public UpdateBillingInfoRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class AddAccountCreditsRequest extends Request<AddAccountCreditsRequest> {

        private AddAccountCreditsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public AddAccountCreditsRequest amount(Integer amount) {
            params.add("amount", amount);
            return this;
        }


        public AddAccountCreditsRequest description(String description) {
            params.add("description", description);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class DeductAccountCreditsRequest extends Request<DeductAccountCreditsRequest> {

        private DeductAccountCreditsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public DeductAccountCreditsRequest amount(Integer amount) {
            params.add("amount", amount);
            return this;
        }


        public DeductAccountCreditsRequest description(String description) {
            params.add("description", description);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class SetAccountCreditsRequest extends Request<SetAccountCreditsRequest> {

        private SetAccountCreditsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public SetAccountCreditsRequest amount(Integer amount) {
            params.add("amount", amount);
            return this;
        }


        public SetAccountCreditsRequest description(String description) {
            params.add("description", description);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
