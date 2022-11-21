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

public class Customer extends Resource<Customer> {

    public enum VatNumberStatus {
        VALID,
        INVALID,
        NOT_VALIDATED,
        UNDETERMINED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum BillingDayOfWeek {
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum PiiCleared {
        ACTIVE,
        SCHEDULED_FOR_CLEAR,
        CLEARED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    @Deprecated
    public enum CardStatus {
        NO_CARD,
        VALID,
        EXPIRING,
        EXPIRED,
        PENDING_VERIFICATION,
        INVALID,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum FraudFlag {
        SAFE,
        SUSPICIOUS,
        FRAUDULENT,
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

        public ValidationStatus validationStatus() {
            return optEnum("validation_status", ValidationStatus.class);
        }

    }

    public static class ReferralUrl extends Resource<ReferralUrl> {
        public ReferralUrl(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String externalCustomerId() {
            return optString("external_customer_id");
        }

        public String referralSharingUrl() {
            return reqString("referral_sharing_url");
        }

        public Timestamp createdAt() {
            return reqTimestamp("created_at");
        }

        public Timestamp updatedAt() {
            return reqTimestamp("updated_at");
        }

        public String referralCampaignId() {
            return reqString("referral_campaign_id");
        }

        public String referralAccountId() {
            return reqString("referral_account_id");
        }

        public String referralExternalCampaignId() {
            return optString("referral_external_campaign_id");
        }

        public ReferralSystem referralSystem() {
            return reqEnum("referral_system", ReferralSystem.class);
        }

    }

    public static class Contact extends Resource<Contact> {
        public Contact(JSONObject jsonObj) {
            super(jsonObj);
        }

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
            return reqString("email");
        }

        public String phone() {
            return optString("phone");
        }

        public String label() {
            return optString("label");
        }

        public Boolean enabled() {
            return reqBoolean("enabled");
        }

        public Boolean sendAccountEmail() {
            return reqBoolean("send_account_email");
        }

        public Boolean sendBillingEmail() {
            return reqBoolean("send_billing_email");
        }

    }

    public static class PaymentMethod extends Resource<PaymentMethod> {
        public enum Type {
             CARD,PAYPAL_EXPRESS_CHECKOUT,AMAZON_PAYMENTS,DIRECT_DEBIT,GENERIC,ALIPAY,UNIONPAY,APPLE_PAY,WECHAT_PAY,IDEAL,GOOGLE_PAY,SOFORT,BANCONTACT,GIROPAY,DOTPAY,UPI,NETBANKING_EMANDATES,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum Status {
             VALID,EXPIRING,EXPIRED,INVALID,PENDING_VERIFICATION,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public PaymentMethod(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Type type() {
            return reqEnum("type", Type.class);
        }

        public Gateway gateway() {
            return reqEnum("gateway", Gateway.class);
        }

        public String gatewayAccountId() {
            return optString("gateway_account_id");
        }

        public Status status() {
            return reqEnum("status", Status.class);
        }

        public String referenceId() {
            return reqString("reference_id");
        }

    }

    public static class Balance extends Resource<Balance> {
        public Balance(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Long promotionalCredits() {
            return reqLong("promotional_credits");
        }

        public Long excessPayments() {
            return reqLong("excess_payments");
        }

        public Long refundableCredits() {
            return reqLong("refundable_credits");
        }

        public Long unbilledCharges() {
            return reqLong("unbilled_charges");
        }

        public String currencyCode() {
            return reqString("currency_code");
        }

        @Deprecated
        public String balanceCurrencyCode() {
            return reqString("balance_currency_code");
        }

    }

    public static class EntityIdentifier extends Resource<EntityIdentifier> {
        public EntityIdentifier(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public String value() {
            return optString("value");
        }

        public String scheme() {
            return reqString("scheme");
        }

        public String standard() {
            return optString("standard");
        }

    }

    public static class Relationship extends Resource<Relationship> {
        public Relationship(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String parentId() {
            return optString("parent_id");
        }

        public String paymentOwnerId() {
            return reqString("payment_owner_id");
        }

        public String invoiceOwnerId() {
            return reqString("invoice_owner_id");
        }

    }

    public static class ParentAccountAccess extends Resource<ParentAccountAccess> {
        public enum PortalEditChildSubscriptions {
             YES,VIEW_ONLY,NO,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum PortalDownloadChildInvoices {
             YES,VIEW_ONLY,NO,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public ParentAccountAccess(JSONObject jsonObj) {
            super(jsonObj);
        }

        public PortalEditChildSubscriptions portalEditChildSubscriptions() {
            return optEnum("portal_edit_child_subscriptions", PortalEditChildSubscriptions.class);
        }

        public PortalDownloadChildInvoices portalDownloadChildInvoices() {
            return optEnum("portal_download_child_invoices", PortalDownloadChildInvoices.class);
        }

        public Boolean sendSubscriptionEmails() {
            return reqBoolean("send_subscription_emails");
        }

        public Boolean sendInvoiceEmails() {
            return reqBoolean("send_invoice_emails");
        }

        public Boolean sendPaymentEmails() {
            return reqBoolean("send_payment_emails");
        }

    }

    public static class ChildAccountAccess extends Resource<ChildAccountAccess> {
        public enum PortalEditSubscriptions {
             YES,VIEW_ONLY,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum PortalDownloadInvoices {
             YES,VIEW_ONLY,NO,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public ChildAccountAccess(JSONObject jsonObj) {
            super(jsonObj);
        }

        public PortalEditSubscriptions portalEditSubscriptions() {
            return optEnum("portal_edit_subscriptions", PortalEditSubscriptions.class);
        }

        public PortalDownloadInvoices portalDownloadInvoices() {
            return optEnum("portal_download_invoices", PortalDownloadInvoices.class);
        }

        public Boolean sendSubscriptionEmails() {
            return reqBoolean("send_subscription_emails");
        }

        public Boolean sendInvoiceEmails() {
            return reqBoolean("send_invoice_emails");
        }

        public Boolean sendPaymentEmails() {
            return reqBoolean("send_payment_emails");
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

    public OfflinePaymentMethod offlinePaymentMethod() {
        return optEnum("offline_payment_method", OfflinePaymentMethod.class);
    }

    public Integer netTermDays() {
        return reqInteger("net_term_days");
    }

    public Timestamp vatNumberValidatedTime() {
        return optTimestamp("vat_number_validated_time");
    }

    public VatNumberStatus vatNumberStatus() {
        return optEnum("vat_number_status", VatNumberStatus.class);
    }

    public Boolean allowDirectDebit() {
        return reqBoolean("allow_direct_debit");
    }

    public Boolean isLocationValid() {
        return optBoolean("is_location_valid");
    }

    public Timestamp createdAt() {
        return reqTimestamp("created_at");
    }

    public String createdFromIp() {
        return optString("created_from_ip");
    }

    public JSONArray exemptionDetails() {
        return optJSONArray("exemption_details");
    }

    public Taxability taxability() {
        return optEnum("taxability", Taxability.class);
    }

    public EntityCode entityCode() {
        return optEnum("entity_code", EntityCode.class);
    }

    public String exemptNumber() {
        return optString("exempt_number");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public String locale() {
        return optString("locale");
    }

    public Integer billingDate() {
        return optInteger("billing_date");
    }

    public Integer billingMonth() {
        return optInteger("billing_month");
    }

    public BillingDateMode billingDateMode() {
        return optEnum("billing_date_mode", BillingDateMode.class);
    }

    public BillingDayOfWeek billingDayOfWeek() {
        return optEnum("billing_day_of_week", BillingDayOfWeek.class);
    }

    public BillingDayOfWeekMode billingDayOfWeekMode() {
        return optEnum("billing_day_of_week_mode", BillingDayOfWeekMode.class);
    }

    public PiiCleared piiCleared() {
        return optEnum("pii_cleared", PiiCleared.class);
    }

    public Boolean autoCloseInvoices() {
        return optBoolean("auto_close_invoices");
    }

    public Channel channel() {
        return optEnum("channel", Channel.class);
    }

    @Deprecated
    public CardStatus cardStatus() {
        return optEnum("card_status", CardStatus.class);
    }

    public FraudFlag fraudFlag() {
        return optEnum("fraud_flag", FraudFlag.class);
    }

    public String primaryPaymentSourceId() {
        return optString("primary_payment_source_id");
    }

    public String backupPaymentSourceId() {
        return optString("backup_payment_source_id");
    }

    public Customer.BillingAddress billingAddress() {
        return optSubResource("billing_address", Customer.BillingAddress.class);
    }

    public List<Customer.ReferralUrl> referralUrls() {
        return optList("referral_urls", Customer.ReferralUrl.class);
    }

    public List<Customer.Contact> contacts() {
        return optList("contacts", Customer.Contact.class);
    }

    public Customer.PaymentMethod paymentMethod() {
        return optSubResource("payment_method", Customer.PaymentMethod.class);
    }

    public String invoiceNotes() {
        return optString("invoice_notes");
    }

    public String businessEntityId() {
        return optString("business_entity_id");
    }

    public String preferredCurrencyCode() {
        return optString("preferred_currency_code");
    }

    public Long promotionalCredits() {
        return reqLong("promotional_credits");
    }

    public Long unbilledCharges() {
        return reqLong("unbilled_charges");
    }

    public Long refundableCredits() {
        return reqLong("refundable_credits");
    }

    public Long excessPayments() {
        return reqLong("excess_payments");
    }

    public List<Customer.Balance> balances() {
        return optList("balances", Customer.Balance.class);
    }

    public List<Customer.EntityIdentifier> entityIdentifiers() {
        return optList("entity_identifiers", Customer.EntityIdentifier.class);
    }

    public Boolean isEinvoiceEnabled() {
        return optBoolean("is_einvoice_enabled");
    }

    public JSONObject metaData() {
        return optJSONObject("meta_data");
    }

    public Boolean deleted() {
        return reqBoolean("deleted");
    }

    public Boolean registeredForGst() {
        return optBoolean("registered_for_gst");
    }

    public Boolean consolidatedInvoicing() {
        return optBoolean("consolidated_invoicing");
    }

    public CustomerType customerType() {
        return optEnum("customer_type", CustomerType.class);
    }

    public Boolean businessCustomerWithoutVatNumber() {
        return optBoolean("business_customer_without_vat_number");
    }

    public String clientProfileId() {
        return optString("client_profile_id");
    }

    public Customer.Relationship relationship() {
        return optSubResource("relationship", Customer.Relationship.class);
    }

    public Boolean useDefaultHierarchySettings() {
        return optBoolean("use_default_hierarchy_settings");
    }

    public Customer.ParentAccountAccess parentAccountAccess() {
        return optSubResource("parent_account_access", Customer.ParentAccountAccess.class);
    }

    public Customer.ChildAccountAccess childAccountAccess() {
        return optSubResource("child_account_access", Customer.ChildAccountAccess.class);
    }

    public String vatNumberPrefix() {
        return optString("vat_number_prefix");
    }

    public String entityIdentifierScheme() {
        return optString("entity_identifier_scheme");
    }

    public String entityIdentifierStandard() {
        return optString("entity_identifier_standard");
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("customers");
        return new CreateRequest(Method.POST, uri);
    }

    public static CustomerListRequest list() {
        String uri = uri("customers");
        return new CustomerListRequest(uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("customers", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("customers", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static UpdatePaymentMethodRequest updatePaymentMethod(String id) {
        String uri = uri("customers", nullCheck(id), "update_payment_method");
        return new UpdatePaymentMethodRequest(Method.POST, uri);
    }

    public static UpdateBillingInfoRequest updateBillingInfo(String id) {
        String uri = uri("customers", nullCheck(id), "update_billing_info");
        return new UpdateBillingInfoRequest(Method.POST, uri);
    }

    public static ListRequest contactsForCustomer(String id) {
        String uri = uri("customers", nullCheck(id), "contacts");
        return new ListRequest(uri);
    }

    public static AssignPaymentRoleRequest assignPaymentRole(String id) {
        String uri = uri("customers", nullCheck(id), "assign_payment_role");
        return new AssignPaymentRoleRequest(Method.POST, uri);
    }

    public static AddContactRequest addContact(String id) {
        String uri = uri("customers", nullCheck(id), "add_contact");
        return new AddContactRequest(Method.POST, uri);
    }

    public static UpdateContactRequest updateContact(String id) {
        String uri = uri("customers", nullCheck(id), "update_contact");
        return new UpdateContactRequest(Method.POST, uri);
    }

    public static DeleteContactRequest deleteContact(String id) {
        String uri = uri("customers", nullCheck(id), "delete_contact");
        return new DeleteContactRequest(Method.POST, uri);
    }

    @Deprecated
    public static AddPromotionalCreditsRequest addPromotionalCredits(String id) {
        String uri = uri("customers", nullCheck(id), "add_promotional_credits");
        return new AddPromotionalCreditsRequest(Method.POST, uri);
    }

    @Deprecated
    public static DeductPromotionalCreditsRequest deductPromotionalCredits(String id) {
        String uri = uri("customers", nullCheck(id), "deduct_promotional_credits");
        return new DeductPromotionalCreditsRequest(Method.POST, uri);
    }

    @Deprecated
    public static SetPromotionalCreditsRequest setPromotionalCredits(String id) {
        String uri = uri("customers", nullCheck(id), "set_promotional_credits");
        return new SetPromotionalCreditsRequest(Method.POST, uri);
    }

    public static RecordExcessPaymentRequest recordExcessPayment(String id) {
        String uri = uri("customers", nullCheck(id), "record_excess_payment");
        return new RecordExcessPaymentRequest(Method.POST, uri);
    }

    public static CollectPaymentRequest collectPayment(String id) {
        String uri = uri("customers", nullCheck(id), "collect_payment");
        return new CollectPaymentRequest(Method.POST, uri);
    }

    public static DeleteRequest delete(String id) {
        String uri = uri("customers", nullCheck(id), "delete");
        return new DeleteRequest(Method.POST, uri);
    }

    public static MoveRequest move() {
        String uri = uri("customers", "move");
        return new MoveRequest(Method.POST, uri);
    }

    public static ChangeBillingDateRequest changeBillingDate(String id) {
        String uri = uri("customers", nullCheck(id), "change_billing_date");
        return new ChangeBillingDateRequest(Method.POST, uri);
    }

    public static MergeRequest merge() {
        String uri = uri("customers", "merge");
        return new MergeRequest(Method.POST, uri);
    }

    public static Request clearPersonalData(String id) {
        String uri = uri("customers", nullCheck(id), "clear_personal_data");
        return new Request(Method.POST, uri);
    }

    public static RelationshipsRequest relationships(String id) {
        String uri = uri("customers", nullCheck(id), "relationships");
        return new RelationshipsRequest(Method.POST, uri);
    }

    public static Request deleteRelationship(String id) {
        String uri = uri("customers", nullCheck(id), "delete_relationship");
        return new Request(Method.POST, uri);
    }

    public static HierarchyRequest hierarchy(String id) {
        String uri = uri("customers", nullCheck(id), "hierarchy");
        return new HierarchyRequest(Method.GET, uri);
    }

    public static UpdateHierarchySettingsRequest updateHierarchySettings(String id) {
        String uri = uri("customers", nullCheck(id), "update_hierarchy_settings");
        return new UpdateHierarchySettingsRequest(Method.POST, uri);
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


        public CreateRequest preferredCurrencyCode(String preferredCurrencyCode) {
            params.addOpt("preferred_currency_code", preferredCurrencyCode);
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


        public CreateRequest autoCollection(com.chargebee.models.enums.AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        public CreateRequest netTermDays(Integer netTermDays) {
            params.addOpt("net_term_days", netTermDays);
            return this;
        }


        public CreateRequest allowDirectDebit(Boolean allowDirectDebit) {
            params.addOpt("allow_direct_debit", allowDirectDebit);
            return this;
        }


        public CreateRequest vatNumber(String vatNumber) {
            params.addOpt("vat_number", vatNumber);
            return this;
        }


        public CreateRequest vatNumberPrefix(String vatNumberPrefix) {
            params.addOpt("vat_number_prefix", vatNumberPrefix);
            return this;
        }


        public CreateRequest entityIdentifierScheme(String entityIdentifierScheme) {
            params.addOpt("entity_identifier_scheme", entityIdentifierScheme);
            return this;
        }


        public CreateRequest entityIdentifierStandard(String entityIdentifierStandard) {
            params.addOpt("entity_identifier_standard", entityIdentifierStandard);
            return this;
        }


        public CreateRequest registeredForGst(Boolean registeredForGst) {
            params.addOpt("registered_for_gst", registeredForGst);
            return this;
        }


        public CreateRequest isEinvoiceEnabled(Boolean isEinvoiceEnabled) {
            params.addOpt("is_einvoice_enabled", isEinvoiceEnabled);
            return this;
        }


        public CreateRequest taxability(com.chargebee.models.enums.Taxability taxability) {
            params.addOpt("taxability", taxability);
            return this;
        }


        public CreateRequest exemptionDetails(JSONArray exemptionDetails) {
            params.addOpt("exemption_details", exemptionDetails);
            return this;
        }


        public CreateRequest customerType(com.chargebee.models.enums.CustomerType customerType) {
            params.addOpt("customer_type", customerType);
            return this;
        }


        public CreateRequest clientProfileId(String clientProfileId) {
            params.addOpt("client_profile_id", clientProfileId);
            return this;
        }


        public CreateRequest taxjarExemptionCategory(com.chargebee.models.enums.TaxjarExemptionCategory taxjarExemptionCategory) {
            params.addOpt("taxjar_exemption_category", taxjarExemptionCategory);
            return this;
        }


        public CreateRequest businessCustomerWithoutVatNumber(Boolean businessCustomerWithoutVatNumber) {
            params.addOpt("business_customer_without_vat_number", businessCustomerWithoutVatNumber);
            return this;
        }


        public CreateRequest locale(String locale) {
            params.addOpt("locale", locale);
            return this;
        }


        public CreateRequest entityCode(com.chargebee.models.enums.EntityCode entityCode) {
            params.addOpt("entity_code", entityCode);
            return this;
        }


        public CreateRequest exemptNumber(String exemptNumber) {
            params.addOpt("exempt_number", exemptNumber);
            return this;
        }


        public CreateRequest metaData(JSONObject metaData) {
            params.addOpt("meta_data", metaData);
            return this;
        }


        public CreateRequest offlinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod offlinePaymentMethod) {
            params.addOpt("offline_payment_method", offlinePaymentMethod);
            return this;
        }


        public CreateRequest autoCloseInvoices(Boolean autoCloseInvoices) {
            params.addOpt("auto_close_invoices", autoCloseInvoices);
            return this;
        }


        public CreateRequest consolidatedInvoicing(Boolean consolidatedInvoicing) {
            params.addOpt("consolidated_invoicing", consolidatedInvoicing);
            return this;
        }






        public CreateRequest tokenId(String tokenId) {
            params.addOpt("token_id", tokenId);
            return this;
        }


        public CreateRequest businessEntityId(String businessEntityId) {
            params.addOpt("business_entity_id", businessEntityId);
            return this;
        }


        @Deprecated
        public CreateRequest createdFromIp(String createdFromIp) {
            params.addOpt("created_from_ip", createdFromIp);
            return this;
        }


        public CreateRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        @Deprecated
        public CreateRequest cardGateway(com.chargebee.models.enums.Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public CreateRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
            return this;
        }

        @Deprecated
        public CreateRequest cardTmpToken(String cardTmpToken) {
            params.addOpt("card[tmp_token]", cardTmpToken);
            return this;
        }

        public CreateRequest bankAccountGatewayAccountId(String bankAccountGatewayAccountId) {
            params.addOpt("bank_account[gateway_account_id]", bankAccountGatewayAccountId);
            return this;
        }

        public CreateRequest bankAccountIban(String bankAccountIban) {
            params.addOpt("bank_account[iban]", bankAccountIban);
            return this;
        }

        public CreateRequest bankAccountFirstName(String bankAccountFirstName) {
            params.addOpt("bank_account[first_name]", bankAccountFirstName);
            return this;
        }

        public CreateRequest bankAccountLastName(String bankAccountLastName) {
            params.addOpt("bank_account[last_name]", bankAccountLastName);
            return this;
        }

        public CreateRequest bankAccountCompany(String bankAccountCompany) {
            params.addOpt("bank_account[company]", bankAccountCompany);
            return this;
        }

        public CreateRequest bankAccountEmail(String bankAccountEmail) {
            params.addOpt("bank_account[email]", bankAccountEmail);
            return this;
        }

        public CreateRequest bankAccountPhone(String bankAccountPhone) {
            params.addOpt("bank_account[phone]", bankAccountPhone);
            return this;
        }

        public CreateRequest bankAccountBankName(String bankAccountBankName) {
            params.addOpt("bank_account[bank_name]", bankAccountBankName);
            return this;
        }

        public CreateRequest bankAccountAccountNumber(String bankAccountAccountNumber) {
            params.addOpt("bank_account[account_number]", bankAccountAccountNumber);
            return this;
        }

        public CreateRequest bankAccountRoutingNumber(String bankAccountRoutingNumber) {
            params.addOpt("bank_account[routing_number]", bankAccountRoutingNumber);
            return this;
        }

        public CreateRequest bankAccountBankCode(String bankAccountBankCode) {
            params.addOpt("bank_account[bank_code]", bankAccountBankCode);
            return this;
        }

        public CreateRequest bankAccountAccountType(com.chargebee.models.enums.AccountType bankAccountAccountType) {
            params.addOpt("bank_account[account_type]", bankAccountAccountType);
            return this;
        }

        public CreateRequest bankAccountAccountHolderType(com.chargebee.models.enums.AccountHolderType bankAccountAccountHolderType) {
            params.addOpt("bank_account[account_holder_type]", bankAccountAccountHolderType);
            return this;
        }

        public CreateRequest bankAccountEcheckType(com.chargebee.models.enums.EcheckType bankAccountEcheckType) {
            params.addOpt("bank_account[echeck_type]", bankAccountEcheckType);
            return this;
        }

        public CreateRequest bankAccountIssuingCountry(String bankAccountIssuingCountry) {
            params.addOpt("bank_account[issuing_country]", bankAccountIssuingCountry);
            return this;
        }

        public CreateRequest bankAccountSwedishIdentityNumber(String bankAccountSwedishIdentityNumber) {
            params.addOpt("bank_account[swedish_identity_number]", bankAccountSwedishIdentityNumber);
            return this;
        }

        public CreateRequest bankAccountBillingAddress(JSONObject bankAccountBillingAddress) {
            params.addOpt("bank_account[billing_address]", bankAccountBillingAddress);
            return this;
        }

        public CreateRequest paymentMethodType(com.chargebee.models.enums.Type paymentMethodType) {
            params.addOpt("payment_method[type]", paymentMethodType);
            return this;
        }

        @Deprecated
        public CreateRequest paymentMethodGateway(com.chargebee.models.enums.Gateway paymentMethodGateway) {
            params.addOpt("payment_method[gateway]", paymentMethodGateway);
            return this;
        }

        public CreateRequest paymentMethodGatewayAccountId(String paymentMethodGatewayAccountId) {
            params.addOpt("payment_method[gateway_account_id]", paymentMethodGatewayAccountId);
            return this;
        }

        public CreateRequest paymentMethodReferenceId(String paymentMethodReferenceId) {
            params.addOpt("payment_method[reference_id]", paymentMethodReferenceId);
            return this;
        }

        public CreateRequest paymentMethodTmpToken(String paymentMethodTmpToken) {
            params.addOpt("payment_method[tmp_token]", paymentMethodTmpToken);
            return this;
        }

        public CreateRequest paymentMethodIssuingCountry(String paymentMethodIssuingCountry) {
            params.addOpt("payment_method[issuing_country]", paymentMethodIssuingCountry);
            return this;
        }

        public CreateRequest paymentMethodAdditionalInformation(JSONObject paymentMethodAdditionalInformation) {
            params.addOpt("payment_method[additional_information]", paymentMethodAdditionalInformation);
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

        @Deprecated
        public CreateRequest cardIpAddress(String cardIpAddress) {
            params.addOpt("card[ip_address]", cardIpAddress);
            return this;
        }

        public CreateRequest cardAdditionalInformation(JSONObject cardAdditionalInformation) {
            params.addOpt("card[additional_information]", cardAdditionalInformation);
            return this;
        }

        public CreateRequest paymentIntentId(String paymentIntentId) {
            params.addOpt("payment_intent[id]", paymentIntentId);
            return this;
        }

        public CreateRequest paymentIntentGatewayAccountId(String paymentIntentGatewayAccountId) {
            params.addOpt("payment_intent[gateway_account_id]", paymentIntentGatewayAccountId);
            return this;
        }

        public CreateRequest paymentIntentGwToken(String paymentIntentGwToken) {
            params.addOpt("payment_intent[gw_token]", paymentIntentGwToken);
            return this;
        }

        public CreateRequest paymentIntentPaymentMethodType(PaymentIntent.PaymentMethodType paymentIntentPaymentMethodType) {
            params.addOpt("payment_intent[payment_method_type]", paymentIntentPaymentMethodType);
            return this;
        }

        public CreateRequest paymentIntentReferenceId(String paymentIntentReferenceId) {
            params.addOpt("payment_intent[reference_id]", paymentIntentReferenceId);
            return this;
        }

        @Deprecated
        public CreateRequest paymentIntentGwPaymentMethodId(String paymentIntentGwPaymentMethodId) {
            params.addOpt("payment_intent[gw_payment_method_id]", paymentIntentGwPaymentMethodId);
            return this;
        }

        public CreateRequest paymentIntentAdditionalInformation(JSONObject paymentIntentAdditionalInformation) {
            params.addOpt("payment_intent[additional_information]", paymentIntentAdditionalInformation);
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

        public CreateRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public CreateRequest entityIdentifierId(int index, String entityIdentifierId) {
            params.addOpt("entity_identifiers[id][" + index + "]", entityIdentifierId);
            return this;
        }
        public CreateRequest entityIdentifierScheme(int index, String entityIdentifierScheme) {
            params.addOpt("entity_identifiers[scheme][" + index + "]", entityIdentifierScheme);
            return this;
        }
        public CreateRequest entityIdentifierValue(int index, String entityIdentifierValue) {
            params.addOpt("entity_identifiers[value][" + index + "]", entityIdentifierValue);
            return this;
        }
        public CreateRequest entityIdentifierStandard(int index, String entityIdentifierStandard) {
            params.addOpt("entity_identifiers[standard][" + index + "]", entityIdentifierStandard);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CustomerListRequest extends ListRequest<CustomerListRequest> {

        private CustomerListRequest(String uri) {
            super(uri);
        }
    
        public CustomerListRequest includeDeleted(Boolean includeDeleted) {
            params.addOpt("include_deleted", includeDeleted);
            return this;
        }


        public StringFilter<CustomerListRequest> id() {
            return new StringFilter<CustomerListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<CustomerListRequest> firstName() {
            return new StringFilter<CustomerListRequest>("first_name",this).supportsPresenceOperator(true);        
        }


        public StringFilter<CustomerListRequest> lastName() {
            return new StringFilter<CustomerListRequest>("last_name",this).supportsPresenceOperator(true);        
        }


        public StringFilter<CustomerListRequest> email() {
            return new StringFilter<CustomerListRequest>("email",this).supportsPresenceOperator(true);        
        }


        public StringFilter<CustomerListRequest> company() {
            return new StringFilter<CustomerListRequest>("company",this).supportsPresenceOperator(true);        
        }


        public StringFilter<CustomerListRequest> phone() {
            return new StringFilter<CustomerListRequest>("phone",this).supportsPresenceOperator(true);        
        }


        public EnumFilter<com.chargebee.models.enums.AutoCollection, CustomerListRequest> autoCollection() {
            return new EnumFilter<com.chargebee.models.enums.AutoCollection, CustomerListRequest>("auto_collection",this);        
        }


        public EnumFilter<com.chargebee.models.enums.Taxability, CustomerListRequest> taxability() {
            return new EnumFilter<com.chargebee.models.enums.Taxability, CustomerListRequest>("taxability",this);        
        }


        public TimestampFilter<CustomerListRequest> createdAt() {
            return new TimestampFilter<CustomerListRequest>("created_at",this);        
        }


        public TimestampFilter<CustomerListRequest> updatedAt() {
            return new TimestampFilter<CustomerListRequest>("updated_at",this);        
        }


        public StringFilter<CustomerListRequest> businessEntityId() {
            return new StringFilter<CustomerListRequest>("business_entity_id",this);        
        }


        public EnumFilter<com.chargebee.models.enums.OfflinePaymentMethod, CustomerListRequest> offlinePaymentMethod() {
            return new EnumFilter<com.chargebee.models.enums.OfflinePaymentMethod, CustomerListRequest>("offline_payment_method",this);        
        }


        public BooleanFilter<CustomerListRequest> autoCloseInvoices() {
            return new BooleanFilter<CustomerListRequest>("auto_close_invoices",this);        
        }


        public EnumFilter<com.chargebee.models.enums.Channel, CustomerListRequest> channel() {
            return new EnumFilter<com.chargebee.models.enums.Channel, CustomerListRequest>("channel",this);        
        }


        public CustomerListRequest sortByCreatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","created_at");
            return this;
        }
        public CustomerListRequest sortByUpdatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","updated_at");
            return this;
        }


        public StringFilter<CustomerListRequest> relationshipParentId() {
            return new StringFilter<CustomerListRequest>("relationship[parent_id]",this);        
        }

        public StringFilter<CustomerListRequest> relationshipPaymentOwnerId() {
            return new StringFilter<CustomerListRequest>("relationship[payment_owner_id]",this);        
        }

        public StringFilter<CustomerListRequest> relationshipInvoiceOwnerId() {
            return new StringFilter<CustomerListRequest>("relationship[invoice_owner_id]",this);        
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


        public UpdateRequest preferredCurrencyCode(String preferredCurrencyCode) {
            params.addOpt("preferred_currency_code", preferredCurrencyCode);
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


        public UpdateRequest autoCollection(com.chargebee.models.enums.AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        public UpdateRequest allowDirectDebit(Boolean allowDirectDebit) {
            params.addOpt("allow_direct_debit", allowDirectDebit);
            return this;
        }


        public UpdateRequest netTermDays(Integer netTermDays) {
            params.addOpt("net_term_days", netTermDays);
            return this;
        }


        public UpdateRequest taxability(com.chargebee.models.enums.Taxability taxability) {
            params.addOpt("taxability", taxability);
            return this;
        }


        public UpdateRequest exemptionDetails(JSONArray exemptionDetails) {
            params.addOpt("exemption_details", exemptionDetails);
            return this;
        }


        public UpdateRequest customerType(com.chargebee.models.enums.CustomerType customerType) {
            params.addOpt("customer_type", customerType);
            return this;
        }


        public UpdateRequest clientProfileId(String clientProfileId) {
            params.addOpt("client_profile_id", clientProfileId);
            return this;
        }


        public UpdateRequest taxjarExemptionCategory(com.chargebee.models.enums.TaxjarExemptionCategory taxjarExemptionCategory) {
            params.addOpt("taxjar_exemption_category", taxjarExemptionCategory);
            return this;
        }


        public UpdateRequest locale(String locale) {
            params.addOpt("locale", locale);
            return this;
        }


        public UpdateRequest entityCode(com.chargebee.models.enums.EntityCode entityCode) {
            params.addOpt("entity_code", entityCode);
            return this;
        }


        public UpdateRequest exemptNumber(String exemptNumber) {
            params.addOpt("exempt_number", exemptNumber);
            return this;
        }


        public UpdateRequest offlinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod offlinePaymentMethod) {
            params.addOpt("offline_payment_method", offlinePaymentMethod);
            return this;
        }


        public UpdateRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        public UpdateRequest autoCloseInvoices(Boolean autoCloseInvoices) {
            params.addOpt("auto_close_invoices", autoCloseInvoices);
            return this;
        }


        public UpdateRequest metaData(JSONObject metaData) {
            params.addOpt("meta_data", metaData);
            return this;
        }


        public UpdateRequest fraudFlag(FraudFlag fraudFlag) {
            params.addOpt("fraud_flag", fraudFlag);
            return this;
        }


        public UpdateRequest consolidatedInvoicing(Boolean consolidatedInvoicing) {
            params.addOpt("consolidated_invoicing", consolidatedInvoicing);
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
    
        public UpdatePaymentMethodRequest paymentMethodType(com.chargebee.models.enums.Type paymentMethodType) {
            params.add("payment_method[type]", paymentMethodType);
            return this;
        }

        @Deprecated
        public UpdatePaymentMethodRequest paymentMethodGateway(com.chargebee.models.enums.Gateway paymentMethodGateway) {
            params.addOpt("payment_method[gateway]", paymentMethodGateway);
            return this;
        }

        public UpdatePaymentMethodRequest paymentMethodGatewayAccountId(String paymentMethodGatewayAccountId) {
            params.addOpt("payment_method[gateway_account_id]", paymentMethodGatewayAccountId);
            return this;
        }

        public UpdatePaymentMethodRequest paymentMethodReferenceId(String paymentMethodReferenceId) {
            params.addOpt("payment_method[reference_id]", paymentMethodReferenceId);
            return this;
        }

        public UpdatePaymentMethodRequest paymentMethodTmpToken(String paymentMethodTmpToken) {
            params.addOpt("payment_method[tmp_token]", paymentMethodTmpToken);
            return this;
        }

        public UpdatePaymentMethodRequest paymentMethodIssuingCountry(String paymentMethodIssuingCountry) {
            params.addOpt("payment_method[issuing_country]", paymentMethodIssuingCountry);
            return this;
        }

        public UpdatePaymentMethodRequest paymentMethodAdditionalInformation(JSONObject paymentMethodAdditionalInformation) {
            params.addOpt("payment_method[additional_information]", paymentMethodAdditionalInformation);
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


        public UpdateBillingInfoRequest vatNumberPrefix(String vatNumberPrefix) {
            params.addOpt("vat_number_prefix", vatNumberPrefix);
            return this;
        }


        public UpdateBillingInfoRequest entityIdentifierScheme(String entityIdentifierScheme) {
            params.addOpt("entity_identifier_scheme", entityIdentifierScheme);
            return this;
        }


        public UpdateBillingInfoRequest entityIdentifierStandard(String entityIdentifierStandard) {
            params.addOpt("entity_identifier_standard", entityIdentifierStandard);
            return this;
        }


        public UpdateBillingInfoRequest registeredForGst(Boolean registeredForGst) {
            params.addOpt("registered_for_gst", registeredForGst);
            return this;
        }


        public UpdateBillingInfoRequest businessCustomerWithoutVatNumber(Boolean businessCustomerWithoutVatNumber) {
            params.addOpt("business_customer_without_vat_number", businessCustomerWithoutVatNumber);
            return this;
        }


        public UpdateBillingInfoRequest isEinvoiceEnabled(Boolean isEinvoiceEnabled) {
            params.addOpt("is_einvoice_enabled", isEinvoiceEnabled);
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

        public UpdateBillingInfoRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public UpdateBillingInfoRequest entityIdentifierId(int index, String entityIdentifierId) {
            params.addOpt("entity_identifiers[id][" + index + "]", entityIdentifierId);
            return this;
        }
        public UpdateBillingInfoRequest entityIdentifierScheme(int index, String entityIdentifierScheme) {
            params.addOpt("entity_identifiers[scheme][" + index + "]", entityIdentifierScheme);
            return this;
        }
        public UpdateBillingInfoRequest entityIdentifierValue(int index, String entityIdentifierValue) {
            params.addOpt("entity_identifiers[value][" + index + "]", entityIdentifierValue);
            return this;
        }
        public UpdateBillingInfoRequest entityIdentifierOperation(int index, com.chargebee.models.enums.Operation entityIdentifierOperation) {
            params.addOpt("entity_identifiers[operation][" + index + "]", entityIdentifierOperation);
            return this;
        }
        public UpdateBillingInfoRequest entityIdentifierStandard(int index, String entityIdentifierStandard) {
            params.addOpt("entity_identifiers[standard][" + index + "]", entityIdentifierStandard);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class AssignPaymentRoleRequest extends Request<AssignPaymentRoleRequest> {

        private AssignPaymentRoleRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public AssignPaymentRoleRequest paymentSourceId(String paymentSourceId) {
            params.add("payment_source_id", paymentSourceId);
            return this;
        }


        public AssignPaymentRoleRequest role(com.chargebee.models.enums.Role role) {
            params.add("role", role);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class AddContactRequest extends Request<AddContactRequest> {

        private AddContactRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public AddContactRequest contactId(String contactId) {
            params.addOpt("contact[id]", contactId);
            return this;
        }

        public AddContactRequest contactFirstName(String contactFirstName) {
            params.addOpt("contact[first_name]", contactFirstName);
            return this;
        }

        public AddContactRequest contactLastName(String contactLastName) {
            params.addOpt("contact[last_name]", contactLastName);
            return this;
        }

        public AddContactRequest contactEmail(String contactEmail) {
            params.add("contact[email]", contactEmail);
            return this;
        }

        public AddContactRequest contactPhone(String contactPhone) {
            params.addOpt("contact[phone]", contactPhone);
            return this;
        }

        public AddContactRequest contactLabel(String contactLabel) {
            params.addOpt("contact[label]", contactLabel);
            return this;
        }

        public AddContactRequest contactEnabled(Boolean contactEnabled) {
            params.addOpt("contact[enabled]", contactEnabled);
            return this;
        }

        public AddContactRequest contactSendBillingEmail(Boolean contactSendBillingEmail) {
            params.addOpt("contact[send_billing_email]", contactSendBillingEmail);
            return this;
        }

        public AddContactRequest contactSendAccountEmail(Boolean contactSendAccountEmail) {
            params.addOpt("contact[send_account_email]", contactSendAccountEmail);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateContactRequest extends Request<UpdateContactRequest> {

        private UpdateContactRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateContactRequest contactId(String contactId) {
            params.add("contact[id]", contactId);
            return this;
        }

        public UpdateContactRequest contactFirstName(String contactFirstName) {
            params.addOpt("contact[first_name]", contactFirstName);
            return this;
        }

        public UpdateContactRequest contactLastName(String contactLastName) {
            params.addOpt("contact[last_name]", contactLastName);
            return this;
        }

        public UpdateContactRequest contactEmail(String contactEmail) {
            params.addOpt("contact[email]", contactEmail);
            return this;
        }

        public UpdateContactRequest contactPhone(String contactPhone) {
            params.addOpt("contact[phone]", contactPhone);
            return this;
        }

        public UpdateContactRequest contactLabel(String contactLabel) {
            params.addOpt("contact[label]", contactLabel);
            return this;
        }

        public UpdateContactRequest contactEnabled(Boolean contactEnabled) {
            params.addOpt("contact[enabled]", contactEnabled);
            return this;
        }

        public UpdateContactRequest contactSendBillingEmail(Boolean contactSendBillingEmail) {
            params.addOpt("contact[send_billing_email]", contactSendBillingEmail);
            return this;
        }

        public UpdateContactRequest contactSendAccountEmail(Boolean contactSendAccountEmail) {
            params.addOpt("contact[send_account_email]", contactSendAccountEmail);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class DeleteContactRequest extends Request<DeleteContactRequest> {

        private DeleteContactRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public DeleteContactRequest contactId(String contactId) {
            params.add("contact[id]", contactId);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class AddPromotionalCreditsRequest extends Request<AddPromotionalCreditsRequest> {

        private AddPromotionalCreditsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public AddPromotionalCreditsRequest amount(Long amount) {
            params.add("amount", amount);
            return this;
        }


        public AddPromotionalCreditsRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public AddPromotionalCreditsRequest description(String description) {
            params.add("description", description);
            return this;
        }


        public AddPromotionalCreditsRequest creditType(com.chargebee.models.enums.CreditType creditType) {
            params.addOpt("credit_type", creditType);
            return this;
        }


        public AddPromotionalCreditsRequest reference(String reference) {
            params.addOpt("reference", reference);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class DeductPromotionalCreditsRequest extends Request<DeductPromotionalCreditsRequest> {

        private DeductPromotionalCreditsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public DeductPromotionalCreditsRequest amount(Long amount) {
            params.add("amount", amount);
            return this;
        }


        public DeductPromotionalCreditsRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public DeductPromotionalCreditsRequest description(String description) {
            params.add("description", description);
            return this;
        }


        public DeductPromotionalCreditsRequest creditType(com.chargebee.models.enums.CreditType creditType) {
            params.addOpt("credit_type", creditType);
            return this;
        }


        public DeductPromotionalCreditsRequest reference(String reference) {
            params.addOpt("reference", reference);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class SetPromotionalCreditsRequest extends Request<SetPromotionalCreditsRequest> {

        private SetPromotionalCreditsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public SetPromotionalCreditsRequest amount(Long amount) {
            params.add("amount", amount);
            return this;
        }


        public SetPromotionalCreditsRequest currencyCode(String currencyCode) {
            params.addOpt("currency_code", currencyCode);
            return this;
        }


        public SetPromotionalCreditsRequest description(String description) {
            params.add("description", description);
            return this;
        }


        public SetPromotionalCreditsRequest creditType(com.chargebee.models.enums.CreditType creditType) {
            params.addOpt("credit_type", creditType);
            return this;
        }


        public SetPromotionalCreditsRequest reference(String reference) {
            params.addOpt("reference", reference);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class RecordExcessPaymentRequest extends Request<RecordExcessPaymentRequest> {

        private RecordExcessPaymentRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RecordExcessPaymentRequest comment(String comment) {
            params.addOpt("comment", comment);
            return this;
        }


        public RecordExcessPaymentRequest transactionAmount(Long transactionAmount) {
            params.add("transaction[amount]", transactionAmount);
            return this;
        }

        public RecordExcessPaymentRequest transactionCurrencyCode(String transactionCurrencyCode) {
            params.addOpt("transaction[currency_code]", transactionCurrencyCode);
            return this;
        }

        public RecordExcessPaymentRequest transactionDate(Timestamp transactionDate) {
            params.add("transaction[date]", transactionDate);
            return this;
        }

        public RecordExcessPaymentRequest transactionPaymentMethod(com.chargebee.models.enums.PaymentMethod transactionPaymentMethod) {
            params.add("transaction[payment_method]", transactionPaymentMethod);
            return this;
        }

        public RecordExcessPaymentRequest transactionReferenceNumber(String transactionReferenceNumber) {
            params.addOpt("transaction[reference_number]", transactionReferenceNumber);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class CollectPaymentRequest extends Request<CollectPaymentRequest> {

        private CollectPaymentRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CollectPaymentRequest amount(Long amount) {
            params.addOpt("amount", amount);
            return this;
        }


        public CollectPaymentRequest paymentSourceId(String paymentSourceId) {
            params.addOpt("payment_source_id", paymentSourceId);
            return this;
        }


        public CollectPaymentRequest tokenId(String tokenId) {
            params.addOpt("token_id", tokenId);
            return this;
        }


        public CollectPaymentRequest replacePrimaryPaymentSource(Boolean replacePrimaryPaymentSource) {
            params.addOpt("replace_primary_payment_source", replacePrimaryPaymentSource);
            return this;
        }


        public CollectPaymentRequest retainPaymentSource(Boolean retainPaymentSource) {
            params.addOpt("retain_payment_source", retainPaymentSource);
            return this;
        }


        public CollectPaymentRequest paymentMethodType(com.chargebee.models.enums.Type paymentMethodType) {
            params.addOpt("payment_method[type]", paymentMethodType);
            return this;
        }

        public CollectPaymentRequest paymentMethodGatewayAccountId(String paymentMethodGatewayAccountId) {
            params.addOpt("payment_method[gateway_account_id]", paymentMethodGatewayAccountId);
            return this;
        }

        public CollectPaymentRequest paymentMethodReferenceId(String paymentMethodReferenceId) {
            params.addOpt("payment_method[reference_id]", paymentMethodReferenceId);
            return this;
        }

        public CollectPaymentRequest paymentMethodTmpToken(String paymentMethodTmpToken) {
            params.addOpt("payment_method[tmp_token]", paymentMethodTmpToken);
            return this;
        }

        public CollectPaymentRequest paymentMethodAdditionalInformation(JSONObject paymentMethodAdditionalInformation) {
            params.addOpt("payment_method[additional_information]", paymentMethodAdditionalInformation);
            return this;
        }

        public CollectPaymentRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
            return this;
        }

        public CollectPaymentRequest cardFirstName(String cardFirstName) {
            params.addOpt("card[first_name]", cardFirstName);
            return this;
        }

        public CollectPaymentRequest cardLastName(String cardLastName) {
            params.addOpt("card[last_name]", cardLastName);
            return this;
        }

        public CollectPaymentRequest cardNumber(String cardNumber) {
            params.addOpt("card[number]", cardNumber);
            return this;
        }

        public CollectPaymentRequest cardExpiryMonth(Integer cardExpiryMonth) {
            params.addOpt("card[expiry_month]", cardExpiryMonth);
            return this;
        }

        public CollectPaymentRequest cardExpiryYear(Integer cardExpiryYear) {
            params.addOpt("card[expiry_year]", cardExpiryYear);
            return this;
        }

        public CollectPaymentRequest cardCvv(String cardCvv) {
            params.addOpt("card[cvv]", cardCvv);
            return this;
        }

        public CollectPaymentRequest cardBillingAddr1(String cardBillingAddr1) {
            params.addOpt("card[billing_addr1]", cardBillingAddr1);
            return this;
        }

        public CollectPaymentRequest cardBillingAddr2(String cardBillingAddr2) {
            params.addOpt("card[billing_addr2]", cardBillingAddr2);
            return this;
        }

        public CollectPaymentRequest cardBillingCity(String cardBillingCity) {
            params.addOpt("card[billing_city]", cardBillingCity);
            return this;
        }

        public CollectPaymentRequest cardBillingStateCode(String cardBillingStateCode) {
            params.addOpt("card[billing_state_code]", cardBillingStateCode);
            return this;
        }

        public CollectPaymentRequest cardBillingState(String cardBillingState) {
            params.addOpt("card[billing_state]", cardBillingState);
            return this;
        }

        public CollectPaymentRequest cardBillingZip(String cardBillingZip) {
            params.addOpt("card[billing_zip]", cardBillingZip);
            return this;
        }

        public CollectPaymentRequest cardBillingCountry(String cardBillingCountry) {
            params.addOpt("card[billing_country]", cardBillingCountry);
            return this;
        }

        public CollectPaymentRequest cardAdditionalInformation(JSONObject cardAdditionalInformation) {
            params.addOpt("card[additional_information]", cardAdditionalInformation);
            return this;
        }

        public CollectPaymentRequest paymentIntentId(String paymentIntentId) {
            params.addOpt("payment_intent[id]", paymentIntentId);
            return this;
        }

        public CollectPaymentRequest paymentIntentGatewayAccountId(String paymentIntentGatewayAccountId) {
            params.addOpt("payment_intent[gateway_account_id]", paymentIntentGatewayAccountId);
            return this;
        }

        public CollectPaymentRequest paymentIntentGwToken(String paymentIntentGwToken) {
            params.addOpt("payment_intent[gw_token]", paymentIntentGwToken);
            return this;
        }

        public CollectPaymentRequest paymentIntentPaymentMethodType(PaymentIntent.PaymentMethodType paymentIntentPaymentMethodType) {
            params.addOpt("payment_intent[payment_method_type]", paymentIntentPaymentMethodType);
            return this;
        }

        @Deprecated
        public CollectPaymentRequest paymentIntentGwPaymentMethodId(String paymentIntentGwPaymentMethodId) {
            params.addOpt("payment_intent[gw_payment_method_id]", paymentIntentGwPaymentMethodId);
            return this;
        }

        public CollectPaymentRequest paymentIntentReferenceId(String paymentIntentReferenceId) {
            params.addOpt("payment_intent[reference_id]", paymentIntentReferenceId);
            return this;
        }

        public CollectPaymentRequest paymentIntentAdditionalInformation(JSONObject paymentIntentAdditionalInformation) {
            params.addOpt("payment_intent[additional_information]", paymentIntentAdditionalInformation);
            return this;
        }

        public CollectPaymentRequest invoiceAllocationInvoiceId(int index, String invoiceAllocationInvoiceId) {
            params.add("invoice_allocations[invoice_id][" + index + "]", invoiceAllocationInvoiceId);
            return this;
        }
        public CollectPaymentRequest invoiceAllocationAllocationAmount(int index, Long invoiceAllocationAllocationAmount) {
            params.addOpt("invoice_allocations[allocation_amount][" + index + "]", invoiceAllocationAllocationAmount);
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
    
        public DeleteRequest deletePaymentMethod(Boolean deletePaymentMethod) {
            params.addOpt("delete_payment_method", deletePaymentMethod);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class MoveRequest extends Request<MoveRequest> {

        private MoveRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public MoveRequest idAtFromSite(String idAtFromSite) {
            params.add("id_at_from_site", idAtFromSite);
            return this;
        }


        public MoveRequest fromSite(String fromSite) {
            params.add("from_site", fromSite);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class ChangeBillingDateRequest extends Request<ChangeBillingDateRequest> {

        private ChangeBillingDateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ChangeBillingDateRequest billingDate(Integer billingDate) {
            params.addOpt("billing_date", billingDate);
            return this;
        }


        public ChangeBillingDateRequest billingMonth(Integer billingMonth) {
            params.addOpt("billing_month", billingMonth);
            return this;
        }


        public ChangeBillingDateRequest billingDateMode(com.chargebee.models.enums.BillingDateMode billingDateMode) {
            params.addOpt("billing_date_mode", billingDateMode);
            return this;
        }


        public ChangeBillingDateRequest billingDayOfWeek(Customer.BillingDayOfWeek billingDayOfWeek) {
            params.addOpt("billing_day_of_week", billingDayOfWeek);
            return this;
        }


        public ChangeBillingDateRequest billingDayOfWeekMode(com.chargebee.models.enums.BillingDayOfWeekMode billingDayOfWeekMode) {
            params.addOpt("billing_day_of_week_mode", billingDayOfWeekMode);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class MergeRequest extends Request<MergeRequest> {

        private MergeRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public MergeRequest fromCustomerId(String fromCustomerId) {
            params.add("from_customer_id", fromCustomerId);
            return this;
        }


        public MergeRequest toCustomerId(String toCustomerId) {
            params.add("to_customer_id", toCustomerId);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class RelationshipsRequest extends Request<RelationshipsRequest> {

        private RelationshipsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RelationshipsRequest parentId(String parentId) {
            params.addOpt("parent_id", parentId);
            return this;
        }


        public RelationshipsRequest paymentOwnerId(String paymentOwnerId) {
            params.addOpt("payment_owner_id", paymentOwnerId);
            return this;
        }


        public RelationshipsRequest invoiceOwnerId(String invoiceOwnerId) {
            params.addOpt("invoice_owner_id", invoiceOwnerId);
            return this;
        }


        public RelationshipsRequest useDefaultHierarchySettings(Boolean useDefaultHierarchySettings) {
            params.addOpt("use_default_hierarchy_settings", useDefaultHierarchySettings);
            return this;
        }


        public RelationshipsRequest parentAccountAccessPortalEditChildSubscriptions(ParentAccountAccess.PortalEditChildSubscriptions parentAccountAccessPortalEditChildSubscriptions) {
            params.addOpt("parent_account_access[portal_edit_child_subscriptions]", parentAccountAccessPortalEditChildSubscriptions);
            return this;
        }

        public RelationshipsRequest parentAccountAccessPortalDownloadChildInvoices(ParentAccountAccess.PortalDownloadChildInvoices parentAccountAccessPortalDownloadChildInvoices) {
            params.addOpt("parent_account_access[portal_download_child_invoices]", parentAccountAccessPortalDownloadChildInvoices);
            return this;
        }

        public RelationshipsRequest parentAccountAccessSendSubscriptionEmails(Boolean parentAccountAccessSendSubscriptionEmails) {
            params.addOpt("parent_account_access[send_subscription_emails]", parentAccountAccessSendSubscriptionEmails);
            return this;
        }

        public RelationshipsRequest parentAccountAccessSendPaymentEmails(Boolean parentAccountAccessSendPaymentEmails) {
            params.addOpt("parent_account_access[send_payment_emails]", parentAccountAccessSendPaymentEmails);
            return this;
        }

        public RelationshipsRequest parentAccountAccessSendInvoiceEmails(Boolean parentAccountAccessSendInvoiceEmails) {
            params.addOpt("parent_account_access[send_invoice_emails]", parentAccountAccessSendInvoiceEmails);
            return this;
        }

        public RelationshipsRequest childAccountAccessPortalEditSubscriptions(ChildAccountAccess.PortalEditSubscriptions childAccountAccessPortalEditSubscriptions) {
            params.addOpt("child_account_access[portal_edit_subscriptions]", childAccountAccessPortalEditSubscriptions);
            return this;
        }

        public RelationshipsRequest childAccountAccessPortalDownloadInvoices(ChildAccountAccess.PortalDownloadInvoices childAccountAccessPortalDownloadInvoices) {
            params.addOpt("child_account_access[portal_download_invoices]", childAccountAccessPortalDownloadInvoices);
            return this;
        }

        public RelationshipsRequest childAccountAccessSendSubscriptionEmails(Boolean childAccountAccessSendSubscriptionEmails) {
            params.addOpt("child_account_access[send_subscription_emails]", childAccountAccessSendSubscriptionEmails);
            return this;
        }

        public RelationshipsRequest childAccountAccessSendPaymentEmails(Boolean childAccountAccessSendPaymentEmails) {
            params.addOpt("child_account_access[send_payment_emails]", childAccountAccessSendPaymentEmails);
            return this;
        }

        public RelationshipsRequest childAccountAccessSendInvoiceEmails(Boolean childAccountAccessSendInvoiceEmails) {
            params.addOpt("child_account_access[send_invoice_emails]", childAccountAccessSendInvoiceEmails);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class HierarchyRequest extends Request<HierarchyRequest> {

        private HierarchyRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public HierarchyRequest hierarchyOperationType(com.chargebee.models.enums.HierarchyOperationType hierarchyOperationType) {
            params.add("hierarchy_operation_type", hierarchyOperationType);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateHierarchySettingsRequest extends Request<UpdateHierarchySettingsRequest> {

        private UpdateHierarchySettingsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateHierarchySettingsRequest useDefaultHierarchySettings(Boolean useDefaultHierarchySettings) {
            params.addOpt("use_default_hierarchy_settings", useDefaultHierarchySettings);
            return this;
        }


        public UpdateHierarchySettingsRequest parentAccountAccessPortalEditChildSubscriptions(ParentAccountAccess.PortalEditChildSubscriptions parentAccountAccessPortalEditChildSubscriptions) {
            params.addOpt("parent_account_access[portal_edit_child_subscriptions]", parentAccountAccessPortalEditChildSubscriptions);
            return this;
        }

        public UpdateHierarchySettingsRequest parentAccountAccessPortalDownloadChildInvoices(ParentAccountAccess.PortalDownloadChildInvoices parentAccountAccessPortalDownloadChildInvoices) {
            params.addOpt("parent_account_access[portal_download_child_invoices]", parentAccountAccessPortalDownloadChildInvoices);
            return this;
        }

        public UpdateHierarchySettingsRequest parentAccountAccessSendSubscriptionEmails(Boolean parentAccountAccessSendSubscriptionEmails) {
            params.addOpt("parent_account_access[send_subscription_emails]", parentAccountAccessSendSubscriptionEmails);
            return this;
        }

        public UpdateHierarchySettingsRequest parentAccountAccessSendPaymentEmails(Boolean parentAccountAccessSendPaymentEmails) {
            params.addOpt("parent_account_access[send_payment_emails]", parentAccountAccessSendPaymentEmails);
            return this;
        }

        public UpdateHierarchySettingsRequest parentAccountAccessSendInvoiceEmails(Boolean parentAccountAccessSendInvoiceEmails) {
            params.addOpt("parent_account_access[send_invoice_emails]", parentAccountAccessSendInvoiceEmails);
            return this;
        }

        public UpdateHierarchySettingsRequest childAccountAccessPortalEditSubscriptions(ChildAccountAccess.PortalEditSubscriptions childAccountAccessPortalEditSubscriptions) {
            params.addOpt("child_account_access[portal_edit_subscriptions]", childAccountAccessPortalEditSubscriptions);
            return this;
        }

        public UpdateHierarchySettingsRequest childAccountAccessPortalDownloadInvoices(ChildAccountAccess.PortalDownloadInvoices childAccountAccessPortalDownloadInvoices) {
            params.addOpt("child_account_access[portal_download_invoices]", childAccountAccessPortalDownloadInvoices);
            return this;
        }

        public UpdateHierarchySettingsRequest childAccountAccessSendSubscriptionEmails(Boolean childAccountAccessSendSubscriptionEmails) {
            params.addOpt("child_account_access[send_subscription_emails]", childAccountAccessSendSubscriptionEmails);
            return this;
        }

        public UpdateHierarchySettingsRequest childAccountAccessSendPaymentEmails(Boolean childAccountAccessSendPaymentEmails) {
            params.addOpt("child_account_access[send_payment_emails]", childAccountAccessSendPaymentEmails);
            return this;
        }

        public UpdateHierarchySettingsRequest childAccountAccessSendInvoiceEmails(Boolean childAccountAccessSendInvoiceEmails) {
            params.addOpt("child_account_access[send_invoice_emails]", childAccountAccessSendInvoiceEmails);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

}
