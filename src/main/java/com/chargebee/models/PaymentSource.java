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

public class PaymentSource extends Resource<PaymentSource> {

    public enum Status {
        VALID,
        EXPIRING,
        EXPIRED,
        INVALID,
        PENDING_VERIFICATION,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class Card extends Resource<Card> {
        public enum Brand {
             VISA,MASTERCARD,AMERICAN_EXPRESS,DISCOVER,JCB,DINERS_CLUB,OTHER,BANCONTACT,NOT_APPLICABLE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum FundingType {
             CREDIT,DEBIT,PREPAID,NOT_KNOWN,NOT_APPLICABLE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public Card(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String firstName() {
            return optString("first_name");
        }

        public String lastName() {
            return optString("last_name");
        }

        public String iin() {
            return reqString("iin");
        }

        public String last4() {
            return reqString("last4");
        }

        public Brand brand() {
            return reqEnum("brand", Brand.class);
        }

        public FundingType fundingType() {
            return reqEnum("funding_type", FundingType.class);
        }

        public Integer expiryMonth() {
            return reqInteger("expiry_month");
        }

        public Integer expiryYear() {
            return reqInteger("expiry_year");
        }

        public String billingAddr1() {
            return optString("billing_addr1");
        }

        public String billingAddr2() {
            return optString("billing_addr2");
        }

        public String billingCity() {
            return optString("billing_city");
        }

        public String billingStateCode() {
            return optString("billing_state_code");
        }

        public String billingState() {
            return optString("billing_state");
        }

        public String billingCountry() {
            return optString("billing_country");
        }

        public String billingZip() {
            return optString("billing_zip");
        }

        public String maskedNumber() {
            return optString("masked_number");
        }

    }

    public static class BankAccount extends Resource<BankAccount> {
        public enum AccountType {
             CHECKING,SAVINGS,BUSINESS_CHECKING,CURRENT,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum EcheckType {
             WEB,PPD,CCD,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum AccountHolderType {
             INDIVIDUAL,COMPANY,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public BankAccount(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String last4() {
            return reqString("last4");
        }

        public String nameOnAccount() {
            return optString("name_on_account");
        }

        public String firstName() {
            return optString("first_name");
        }

        public String lastName() {
            return optString("last_name");
        }

        public String bankName() {
            return optString("bank_name");
        }

        public String mandateId() {
            return optString("mandate_id");
        }

        public AccountType accountType() {
            return optEnum("account_type", AccountType.class);
        }

        public EcheckType echeckType() {
            return optEnum("echeck_type", EcheckType.class);
        }

        public AccountHolderType accountHolderType() {
            return optEnum("account_holder_type", AccountHolderType.class);
        }

        public String email() {
            return optString("email");
        }

    }

    public static class AmazonPayment extends Resource<AmazonPayment> {
        public AmazonPayment(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String email() {
            return optString("email");
        }

        public String agreementId() {
            return optString("agreement_id");
        }

    }

    public static class Upi extends Resource<Upi> {
        public Upi(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String vpa() {
            return optString("vpa");
        }

    }

    public static class Paypal extends Resource<Paypal> {
        public Paypal(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String email() {
            return optString("email");
        }

        public String agreementId() {
            return optString("agreement_id");
        }

    }

    public static class Mandate extends Resource<Mandate> {
        public Mandate(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public String subscriptionId() {
            return reqString("subscription_id");
        }

        public Timestamp createdAt() {
            return reqTimestamp("created_at");
        }

    }

    //Constructors
    //============

    public PaymentSource(String jsonStr) {
        super(jsonStr);
    }

    public PaymentSource(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
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

    public String customerId() {
        return reqString("customer_id");
    }

    public Type type() {
        return reqEnum("type", Type.class);
    }

    public String referenceId() {
        return reqString("reference_id");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Gateway gateway() {
        return reqEnum("gateway", Gateway.class);
    }

    public String gatewayAccountId() {
        return optString("gateway_account_id");
    }

    public String ipAddress() {
        return optString("ip_address");
    }

    public String issuingCountry() {
        return optString("issuing_country");
    }

    public PaymentSource.Card card() {
        return optSubResource("card", PaymentSource.Card.class);
    }

    public PaymentSource.BankAccount bankAccount() {
        return optSubResource("bank_account", PaymentSource.BankAccount.class);
    }

    public PaymentSource.AmazonPayment amazonPayment() {
        return optSubResource("amazon_payment", PaymentSource.AmazonPayment.class);
    }

    public PaymentSource.Upi upi() {
        return optSubResource("upi", PaymentSource.Upi.class);
    }

    public PaymentSource.Paypal paypal() {
        return optSubResource("paypal", PaymentSource.Paypal.class);
    }

    public List<PaymentSource.Mandate> mandates() {
        return optList("mandates", PaymentSource.Mandate.class);
    }

    public Boolean deleted() {
        return reqBoolean("deleted");
    }

    public String businessEntityId() {
        return optString("business_entity_id");
    }

    // Operations
    //===========

    public static CreateUsingTempTokenRequest createUsingTempToken() {
        String uri = uri("payment_sources", "create_using_temp_token");
        return new CreateUsingTempTokenRequest(Method.POST, uri);
    }

    public static CreateUsingPermanentTokenRequest createUsingPermanentToken() {
        String uri = uri("payment_sources", "create_using_permanent_token");
        return new CreateUsingPermanentTokenRequest(Method.POST, uri);
    }

    public static CreateUsingTokenRequest createUsingToken() {
        String uri = uri("payment_sources", "create_using_token");
        return new CreateUsingTokenRequest(Method.POST, uri);
    }

    public static CreateUsingPaymentIntentRequest createUsingPaymentIntent() {
        String uri = uri("payment_sources", "create_using_payment_intent");
        return new CreateUsingPaymentIntentRequest(Method.POST, uri);
    }

    public static CreateCardRequest createCard() {
        String uri = uri("payment_sources", "create_card");
        return new CreateCardRequest(Method.POST, uri);
    }

    public static CreateBankAccountRequest createBankAccount() {
        String uri = uri("payment_sources", "create_bank_account");
        return new CreateBankAccountRequest(Method.POST, uri);
    }

    public static UpdateCardRequest updateCard(String id) {
        String uri = uri("payment_sources", nullCheck(id), "update_card");
        return new UpdateCardRequest(Method.POST, uri);
    }

    public static UpdateBankAccountRequest updateBankAccount(String id) {
        String uri = uri("payment_sources", nullCheck(id), "update_bank_account");
        return new UpdateBankAccountRequest(Method.POST, uri);
    }

    public static VerifyBankAccountRequest verifyBankAccount(String id) {
        String uri = uri("payment_sources", nullCheck(id), "verify_bank_account");
        return new VerifyBankAccountRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("payment_sources", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static PaymentSourceListRequest list() {
        String uri = uri("payment_sources");
        return new PaymentSourceListRequest(uri);
    }

    public static SwitchGatewayAccountRequest switchGatewayAccount(String id) {
        String uri = uri("payment_sources", nullCheck(id), "switch_gateway_account");
        return new SwitchGatewayAccountRequest(Method.POST, uri);
    }

    public static ExportPaymentSourceRequest exportPaymentSource(String id) {
        String uri = uri("payment_sources", nullCheck(id), "export_payment_source");
        return new ExportPaymentSourceRequest(Method.POST, uri);
    }

    public static Request delete(String id) {
        String uri = uri("payment_sources", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static Request deleteLocal(String id) {
        String uri = uri("payment_sources", nullCheck(id), "delete_local");
        return new Request(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateUsingTempTokenRequest extends Request<CreateUsingTempTokenRequest> {

        private CreateUsingTempTokenRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateUsingTempTokenRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public CreateUsingTempTokenRequest gatewayAccountId(String gatewayAccountId) {
            params.addOpt("gateway_account_id", gatewayAccountId);
            return this;
        }


        public CreateUsingTempTokenRequest type(com.chargebee.models.enums.Type type) {
            params.add("type", type);
            return this;
        }


        public CreateUsingTempTokenRequest tmpToken(String tmpToken) {
            params.add("tmp_token", tmpToken);
            return this;
        }


        public CreateUsingTempTokenRequest issuingCountry(String issuingCountry) {
            params.addOpt("issuing_country", issuingCountry);
            return this;
        }


        public CreateUsingTempTokenRequest replacePrimaryPaymentSource(Boolean replacePrimaryPaymentSource) {
            params.addOpt("replace_primary_payment_source", replacePrimaryPaymentSource);
            return this;
        }


        public CreateUsingTempTokenRequest additionalInformation(JSONObject additionalInformation) {
            params.addOpt("additional_information", additionalInformation);
            return this;
        }




        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateUsingPermanentTokenRequest extends Request<CreateUsingPermanentTokenRequest> {

        private CreateUsingPermanentTokenRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateUsingPermanentTokenRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public CreateUsingPermanentTokenRequest type(com.chargebee.models.enums.Type type) {
            params.add("type", type);
            return this;
        }


        public CreateUsingPermanentTokenRequest gatewayAccountId(String gatewayAccountId) {
            params.addOpt("gateway_account_id", gatewayAccountId);
            return this;
        }


        public CreateUsingPermanentTokenRequest referenceId(String referenceId) {
            params.add("reference_id", referenceId);
            return this;
        }


        public CreateUsingPermanentTokenRequest issuingCountry(String issuingCountry) {
            params.addOpt("issuing_country", issuingCountry);
            return this;
        }


        public CreateUsingPermanentTokenRequest replacePrimaryPaymentSource(Boolean replacePrimaryPaymentSource) {
            params.addOpt("replace_primary_payment_source", replacePrimaryPaymentSource);
            return this;
        }




        public CreateUsingPermanentTokenRequest additionalInformation(JSONObject additionalInformation) {
            params.addOpt("additional_information", additionalInformation);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateUsingTokenRequest extends Request<CreateUsingTokenRequest> {

        private CreateUsingTokenRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateUsingTokenRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public CreateUsingTokenRequest replacePrimaryPaymentSource(Boolean replacePrimaryPaymentSource) {
            params.addOpt("replace_primary_payment_source", replacePrimaryPaymentSource);
            return this;
        }


        public CreateUsingTokenRequest tokenId(String tokenId) {
            params.add("token_id", tokenId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateUsingPaymentIntentRequest extends Request<CreateUsingPaymentIntentRequest> {

        private CreateUsingPaymentIntentRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateUsingPaymentIntentRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public CreateUsingPaymentIntentRequest replacePrimaryPaymentSource(Boolean replacePrimaryPaymentSource) {
            params.addOpt("replace_primary_payment_source", replacePrimaryPaymentSource);
            return this;
        }


        public CreateUsingPaymentIntentRequest paymentIntentId(String paymentIntentId) {
            params.addOpt("payment_intent[id]", paymentIntentId);
            return this;
        }

        public CreateUsingPaymentIntentRequest paymentIntentGatewayAccountId(String paymentIntentGatewayAccountId) {
            params.addOpt("payment_intent[gateway_account_id]", paymentIntentGatewayAccountId);
            return this;
        }

        public CreateUsingPaymentIntentRequest paymentIntentGwToken(String paymentIntentGwToken) {
            params.addOpt("payment_intent[gw_token]", paymentIntentGwToken);
            return this;
        }

        public CreateUsingPaymentIntentRequest paymentIntentPaymentMethodType(PaymentIntent.PaymentMethodType paymentIntentPaymentMethodType) {
            params.addOpt("payment_intent[payment_method_type]", paymentIntentPaymentMethodType);
            return this;
        }

        public CreateUsingPaymentIntentRequest paymentIntentReferenceId(String paymentIntentReferenceId) {
            params.addOpt("payment_intent[reference_id]", paymentIntentReferenceId);
            return this;
        }

        @Deprecated
        public CreateUsingPaymentIntentRequest paymentIntentGwPaymentMethodId(String paymentIntentGwPaymentMethodId) {
            params.addOpt("payment_intent[gw_payment_method_id]", paymentIntentGwPaymentMethodId);
            return this;
        }

        public CreateUsingPaymentIntentRequest paymentIntentAdditionalInfo(JSONObject paymentIntentAdditionalInfo) {
            params.addOpt("payment_intent[additional_info]", paymentIntentAdditionalInfo);
            return this;
        }

        public CreateUsingPaymentIntentRequest paymentIntentAdditionalInformation(JSONObject paymentIntentAdditionalInformation) {
            params.addOpt("payment_intent[additional_information]", paymentIntentAdditionalInformation);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateCardRequest extends Request<CreateCardRequest> {

        private CreateCardRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateCardRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public CreateCardRequest replacePrimaryPaymentSource(Boolean replacePrimaryPaymentSource) {
            params.addOpt("replace_primary_payment_source", replacePrimaryPaymentSource);
            return this;
        }




        public CreateCardRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
            return this;
        }

        public CreateCardRequest cardFirstName(String cardFirstName) {
            params.addOpt("card[first_name]", cardFirstName);
            return this;
        }

        public CreateCardRequest cardLastName(String cardLastName) {
            params.addOpt("card[last_name]", cardLastName);
            return this;
        }

        public CreateCardRequest cardNumber(String cardNumber) {
            params.add("card[number]", cardNumber);
            return this;
        }

        public CreateCardRequest cardExpiryMonth(Integer cardExpiryMonth) {
            params.add("card[expiry_month]", cardExpiryMonth);
            return this;
        }

        public CreateCardRequest cardExpiryYear(Integer cardExpiryYear) {
            params.add("card[expiry_year]", cardExpiryYear);
            return this;
        }

        public CreateCardRequest cardCvv(String cardCvv) {
            params.addOpt("card[cvv]", cardCvv);
            return this;
        }

        public CreateCardRequest cardBillingAddr1(String cardBillingAddr1) {
            params.addOpt("card[billing_addr1]", cardBillingAddr1);
            return this;
        }

        public CreateCardRequest cardBillingAddr2(String cardBillingAddr2) {
            params.addOpt("card[billing_addr2]", cardBillingAddr2);
            return this;
        }

        public CreateCardRequest cardBillingCity(String cardBillingCity) {
            params.addOpt("card[billing_city]", cardBillingCity);
            return this;
        }

        public CreateCardRequest cardBillingStateCode(String cardBillingStateCode) {
            params.addOpt("card[billing_state_code]", cardBillingStateCode);
            return this;
        }

        public CreateCardRequest cardBillingState(String cardBillingState) {
            params.addOpt("card[billing_state]", cardBillingState);
            return this;
        }

        public CreateCardRequest cardBillingZip(String cardBillingZip) {
            params.addOpt("card[billing_zip]", cardBillingZip);
            return this;
        }

        public CreateCardRequest cardBillingCountry(String cardBillingCountry) {
            params.addOpt("card[billing_country]", cardBillingCountry);
            return this;
        }

        public CreateCardRequest cardAdditionalInformation(JSONObject cardAdditionalInformation) {
            params.addOpt("card[additional_information]", cardAdditionalInformation);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateBankAccountRequest extends Request<CreateBankAccountRequest> {

        private CreateBankAccountRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateBankAccountRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public CreateBankAccountRequest issuingCountry(String issuingCountry) {
            params.addOpt("issuing_country", issuingCountry);
            return this;
        }


        public CreateBankAccountRequest replacePrimaryPaymentSource(Boolean replacePrimaryPaymentSource) {
            params.addOpt("replace_primary_payment_source", replacePrimaryPaymentSource);
            return this;
        }




        public CreateBankAccountRequest bankAccountGatewayAccountId(String bankAccountGatewayAccountId) {
            params.addOpt("bank_account[gateway_account_id]", bankAccountGatewayAccountId);
            return this;
        }

        public CreateBankAccountRequest bankAccountIban(String bankAccountIban) {
            params.addOpt("bank_account[iban]", bankAccountIban);
            return this;
        }

        public CreateBankAccountRequest bankAccountFirstName(String bankAccountFirstName) {
            params.addOpt("bank_account[first_name]", bankAccountFirstName);
            return this;
        }

        public CreateBankAccountRequest bankAccountLastName(String bankAccountLastName) {
            params.addOpt("bank_account[last_name]", bankAccountLastName);
            return this;
        }

        public CreateBankAccountRequest bankAccountCompany(String bankAccountCompany) {
            params.addOpt("bank_account[company]", bankAccountCompany);
            return this;
        }

        public CreateBankAccountRequest bankAccountEmail(String bankAccountEmail) {
            params.addOpt("bank_account[email]", bankAccountEmail);
            return this;
        }

        public CreateBankAccountRequest bankAccountPhone(String bankAccountPhone) {
            params.addOpt("bank_account[phone]", bankAccountPhone);
            return this;
        }

        public CreateBankAccountRequest bankAccountBankName(String bankAccountBankName) {
            params.addOpt("bank_account[bank_name]", bankAccountBankName);
            return this;
        }

        public CreateBankAccountRequest bankAccountAccountNumber(String bankAccountAccountNumber) {
            params.addOpt("bank_account[account_number]", bankAccountAccountNumber);
            return this;
        }

        public CreateBankAccountRequest bankAccountRoutingNumber(String bankAccountRoutingNumber) {
            params.addOpt("bank_account[routing_number]", bankAccountRoutingNumber);
            return this;
        }

        public CreateBankAccountRequest bankAccountBankCode(String bankAccountBankCode) {
            params.addOpt("bank_account[bank_code]", bankAccountBankCode);
            return this;
        }

        public CreateBankAccountRequest bankAccountAccountType(com.chargebee.models.enums.AccountType bankAccountAccountType) {
            params.addOpt("bank_account[account_type]", bankAccountAccountType);
            return this;
        }

        public CreateBankAccountRequest bankAccountAccountHolderType(com.chargebee.models.enums.AccountHolderType bankAccountAccountHolderType) {
            params.addOpt("bank_account[account_holder_type]", bankAccountAccountHolderType);
            return this;
        }

        public CreateBankAccountRequest bankAccountEcheckType(com.chargebee.models.enums.EcheckType bankAccountEcheckType) {
            params.addOpt("bank_account[echeck_type]", bankAccountEcheckType);
            return this;
        }

        public CreateBankAccountRequest bankAccountSwedishIdentityNumber(String bankAccountSwedishIdentityNumber) {
            params.addOpt("bank_account[swedish_identity_number]", bankAccountSwedishIdentityNumber);
            return this;
        }

        public CreateBankAccountRequest bankAccountBillingAddress(JSONObject bankAccountBillingAddress) {
            params.addOpt("bank_account[billing_address]", bankAccountBillingAddress);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateCardRequest extends Request<UpdateCardRequest> {

        private UpdateCardRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateCardRequest gatewayMetaData(JSONObject gatewayMetaData) {
            params.addOpt("gateway_meta_data", gatewayMetaData);
            return this;
        }


        public UpdateCardRequest referenceTransaction(String referenceTransaction) {
            params.addOpt("reference_transaction", referenceTransaction);
            return this;
        }


        public UpdateCardRequest cardFirstName(String cardFirstName) {
            params.addOpt("card[first_name]", cardFirstName);
            return this;
        }

        public UpdateCardRequest cardLastName(String cardLastName) {
            params.addOpt("card[last_name]", cardLastName);
            return this;
        }

        public UpdateCardRequest cardExpiryMonth(Integer cardExpiryMonth) {
            params.addOpt("card[expiry_month]", cardExpiryMonth);
            return this;
        }

        public UpdateCardRequest cardExpiryYear(Integer cardExpiryYear) {
            params.addOpt("card[expiry_year]", cardExpiryYear);
            return this;
        }

        public UpdateCardRequest cardBillingAddr1(String cardBillingAddr1) {
            params.addOpt("card[billing_addr1]", cardBillingAddr1);
            return this;
        }

        public UpdateCardRequest cardBillingAddr2(String cardBillingAddr2) {
            params.addOpt("card[billing_addr2]", cardBillingAddr2);
            return this;
        }

        public UpdateCardRequest cardBillingCity(String cardBillingCity) {
            params.addOpt("card[billing_city]", cardBillingCity);
            return this;
        }

        public UpdateCardRequest cardBillingZip(String cardBillingZip) {
            params.addOpt("card[billing_zip]", cardBillingZip);
            return this;
        }

        public UpdateCardRequest cardBillingStateCode(String cardBillingStateCode) {
            params.addOpt("card[billing_state_code]", cardBillingStateCode);
            return this;
        }

        public UpdateCardRequest cardBillingState(String cardBillingState) {
            params.addOpt("card[billing_state]", cardBillingState);
            return this;
        }

        public UpdateCardRequest cardBillingCountry(String cardBillingCountry) {
            params.addOpt("card[billing_country]", cardBillingCountry);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateBankAccountRequest extends Request<UpdateBankAccountRequest> {

        private UpdateBankAccountRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateBankAccountRequest bankAccountFirstName(String bankAccountFirstName) {
            params.addOpt("bank_account[first_name]", bankAccountFirstName);
            return this;
        }

        public UpdateBankAccountRequest bankAccountLastName(String bankAccountLastName) {
            params.addOpt("bank_account[last_name]", bankAccountLastName);
            return this;
        }

        public UpdateBankAccountRequest bankAccountEmail(String bankAccountEmail) {
            params.addOpt("bank_account[email]", bankAccountEmail);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class VerifyBankAccountRequest extends Request<VerifyBankAccountRequest> {

        private VerifyBankAccountRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public VerifyBankAccountRequest amount1(Long amount1) {
            params.add("amount1", amount1);
            return this;
        }


        public VerifyBankAccountRequest amount2(Long amount2) {
            params.add("amount2", amount2);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class PaymentSourceListRequest extends ListRequest<PaymentSourceListRequest> {

        private PaymentSourceListRequest(String uri) {
            super(uri);
        }
    
        public PaymentSourceListRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription_id", subscriptionId);
            return this;
        }


        public StringFilter<PaymentSourceListRequest> customerId() {
            return new StringFilter<PaymentSourceListRequest>("customer_id",this).supportsMultiOperators(true);        
        }


        public EnumFilter<com.chargebee.models.enums.Type, PaymentSourceListRequest> type() {
            return new EnumFilter<com.chargebee.models.enums.Type, PaymentSourceListRequest>("type",this);        
        }


        public EnumFilter<PaymentSource.Status, PaymentSourceListRequest> status() {
            return new EnumFilter<PaymentSource.Status, PaymentSourceListRequest>("status",this);        
        }


        public TimestampFilter<PaymentSourceListRequest> updatedAt() {
            return new TimestampFilter<PaymentSourceListRequest>("updated_at",this);        
        }


        public TimestampFilter<PaymentSourceListRequest> createdAt() {
            return new TimestampFilter<PaymentSourceListRequest>("created_at",this);        
        }


        public PaymentSourceListRequest sortByCreatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","created_at");
            return this;
        }
        public PaymentSourceListRequest sortByUpdatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","updated_at");
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class SwitchGatewayAccountRequest extends Request<SwitchGatewayAccountRequest> {

        private SwitchGatewayAccountRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public SwitchGatewayAccountRequest gatewayAccountId(String gatewayAccountId) {
            params.add("gateway_account_id", gatewayAccountId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class ExportPaymentSourceRequest extends Request<ExportPaymentSourceRequest> {

        private ExportPaymentSourceRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ExportPaymentSourceRequest gatewayAccountId(String gatewayAccountId) {
            params.add("gateway_account_id", gatewayAccountId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

}
