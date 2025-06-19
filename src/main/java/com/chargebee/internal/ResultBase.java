package com.chargebee.internal;

import com.chargebee.models.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;
import java.util.ArrayList;

public class ResultBase {

    private JSONObject jsonObj;

    public ResultBase(JSONObject jsonObj) {
        this.jsonObj = jsonObj;
    }


    public Subscription subscription() {
        return (Subscription)get("subscription");
    }

    public ContractTerm contractTerm() {
        return (ContractTerm)get("contract_term");
    }

    public Discount discount() {
        return (Discount)get("discount");
    }

    public AdvanceInvoiceSchedule advanceInvoiceSchedule() {
        return (AdvanceInvoiceSchedule)get("advance_invoice_schedule");
    }

    public Customer customer() {
        return (Customer)get("customer");
    }

    public Hierarchy hierarchy() {
        return (Hierarchy)get("hierarchy");
    }

    public Contact contact() {
        return (Contact)get("contact");
    }

    public BusinessEntityTransfer businessEntityTransfer() {
        return (BusinessEntityTransfer)get("business_entity_transfer");
    }

    public Token token() {
        return (Token)get("token");
    }

    public PaymentSource paymentSource() {
        return (PaymentSource)get("payment_source");
    }

    public ThirdPartyPaymentMethod thirdPartyPaymentMethod() {
        return (ThirdPartyPaymentMethod)get("third_party_payment_method");
    }

    public VirtualBankAccount virtualBankAccount() {
        return (VirtualBankAccount)get("virtual_bank_account");
    }

    public Card card() {
        return (Card)get("card");
    }

    public PromotionalCredit promotionalCredit() {
        return (PromotionalCredit)get("promotional_credit");
    }

    public Invoice invoice() {
        return (Invoice)get("invoice");
    }

    public PaymentReferenceNumber paymentReferenceNumber() {
        return (PaymentReferenceNumber)get("payment_reference_number");
    }

    public PaymentSchedule paymentSchedule() {
        return (PaymentSchedule)get("payment_schedule");
    }

    public TaxWithheld taxWithheld() {
        return (TaxWithheld)get("tax_withheld");
    }

    public CreditNote creditNote() {
        return (CreditNote)get("credit_note");
    }

    public UnbilledCharge unbilledCharge() {
        return (UnbilledCharge)get("unbilled_charge");
    }

    public Order order() {
        return (Order)get("order");
    }

    public Gift gift() {
        return (Gift)get("gift");
    }

    public Transaction transaction() {
        return (Transaction)get("transaction");
    }

    public HostedPage hostedPage() {
        return (HostedPage)get("hosted_page");
    }

    public Estimate estimate() {
        return (Estimate)get("estimate");
    }

    public Quote quote() {
        return (Quote)get("quote");
    }

    public QuotedSubscription quotedSubscription() {
        return (QuotedSubscription)get("quoted_subscription");
    }

    public QuotedCharge quotedCharge() {
        return (QuotedCharge)get("quoted_charge");
    }

    public QuotedRamp quotedRamp() {
        return (QuotedRamp)get("quoted_ramp");
    }

    public QuoteLineGroup quoteLineGroup() {
        return (QuoteLineGroup)get("quote_line_group");
    }

    public Plan plan() {
        return (Plan)get("plan");
    }

    public Addon addon() {
        return (Addon)get("addon");
    }

    public Coupon coupon() {
        return (Coupon)get("coupon");
    }

    public CouponSet couponSet() {
        return (CouponSet)get("coupon_set");
    }

    public CouponCode couponCode() {
        return (CouponCode)get("coupon_code");
    }

    public Address address() {
        return (Address)get("address");
    }

    public Usage usage() {
        return (Usage)get("usage");
    }

    public Event event() {
        return (Event)get("event");
    }

    public Comment comment() {
        return (Comment)get("comment");
    }

    public Download download() {
        return (Download)get("download");
    }

    public PortalSession portalSession() {
        return (PortalSession)get("portal_session");
    }

    public SiteMigrationDetail siteMigrationDetail() {
        return (SiteMigrationDetail)get("site_migration_detail");
    }

    public ResourceMigration resourceMigration() {
        return (ResourceMigration)get("resource_migration");
    }

    public TimeMachine timeMachine() {
        return (TimeMachine)get("time_machine");
    }

    public Export export() {
        return (Export)get("export");
    }

    public PaymentIntent paymentIntent() {
        return (PaymentIntent)get("payment_intent");
    }

    public GatewayErrorDetail gatewayErrorDetail() {
        return (GatewayErrorDetail)get("gateway_error_detail");
    }

    public ItemFamily itemFamily() {
        return (ItemFamily)get("item_family");
    }

    public Item item() {
        return (Item)get("item");
    }

    public PriceVariant priceVariant() {
        return (PriceVariant)get("price_variant");
    }

    public Attribute attribute() {
        return (Attribute)get("attribute");
    }

    public ItemPrice itemPrice() {
        return (ItemPrice)get("item_price");
    }

    public AttachedItem attachedItem() {
        return (AttachedItem)get("attached_item");
    }

    public DifferentialPrice differentialPrice() {
        return (DifferentialPrice)get("differential_price");
    }

    public Configuration configuration() {
        return (Configuration)get("configuration");
    }

    public Feature feature() {
        return (Feature)get("feature");
    }

    public ImpactedSubscription impactedSubscription() {
        return (ImpactedSubscription)get("impacted_subscription");
    }

    public ImpactedItem impactedItem() {
        return (ImpactedItem)get("impacted_item");
    }

    public ImpactedItemPrice impactedItemPrice() {
        return (ImpactedItemPrice)get("impacted_item_price");
    }

    public Metadata metadata() {
        return (Metadata)get("metadata");
    }

    public SubscriptionEntitlement subscriptionEntitlement() {
        return (SubscriptionEntitlement)get("subscription_entitlement");
    }

    public CustomerEntitlement customerEntitlement() {
        return (CustomerEntitlement)get("customer_entitlement");
    }

    public ItemEntitlement itemEntitlement() {
        return (ItemEntitlement)get("item_entitlement");
    }

    public Entitlement entitlement() {
        return (Entitlement)get("entitlement");
    }

    public InAppSubscription inAppSubscription() {
        return (InAppSubscription)get("in_app_subscription");
    }

    public EntitlementOverride entitlementOverride() {
        return (EntitlementOverride)get("entitlement_override");
    }

    public BusinessEntity businessEntity() {
        return (BusinessEntity)get("business_entity");
    }

    public Purchase purchase() {
        return (Purchase)get("purchase");
    }

    public PaymentVoucher paymentVoucher() {
        return (PaymentVoucher)get("payment_voucher");
    }

    public Currency currency() {
        return (Currency)get("currency");
    }

    public Ramp ramp() {
        return (Ramp)get("ramp");
    }

    public PaymentScheduleScheme paymentScheduleScheme() {
        return (PaymentScheduleScheme)get("payment_schedule_scheme");
    }

    public PricingPageSession pricingPageSession() {
        return (PricingPageSession)get("pricing_page_session");
    }

    public OmnichannelSubscription omnichannelSubscription() {
        return (OmnichannelSubscription)get("omnichannel_subscription");
    }

    public OmnichannelTransaction omnichannelTransaction() {
        return (OmnichannelTransaction)get("omnichannel_transaction");
    }

    public OmnichannelSubscriptionItem omnichannelSubscriptionItem() {
        return (OmnichannelSubscriptionItem)get("omnichannel_subscription_item");
    }

    public RecordedPurchase recordedPurchase() {
        return (RecordedPurchase)get("recorded_purchase");
    }

    public Rule rule() {
        return (Rule)get("rule");
    }

    public UsageEvent usageEvent() {
        return (UsageEvent)get("usage_event");
    }

    public OmnichannelSubscriptionItemScheduledChange omnichannelSubscriptionItemScheduledChange() {
        return (OmnichannelSubscriptionItemScheduledChange)get("omnichannel_subscription_item_scheduled_change");
    }

    public UsageFile usageFile() {
        return (UsageFile)get("usage_file");
    }

    public List<AdvanceInvoiceSchedule> advanceInvoiceSchedules() {
        return (List<AdvanceInvoiceSchedule>) getList("advance_invoice_schedules", "advance_invoice_schedule");
    }

    public List<Hierarchy> hierarchies() {
        return (List<Hierarchy>) getList("hierarchies", "hierarchy");
    }

    public List<Invoice> invoices() {
        return (List<Invoice>) getList("invoices", "invoice");
    }

    public List<PaymentSchedule> paymentSchedules() {
        return (List<PaymentSchedule>) getList("payment_schedules", "payment_schedule");
    }

    public List<CreditNote> creditNotes() {
        return (List<CreditNote>) getList("credit_notes", "credit_note");
    }

    public List<UnbilledCharge> unbilledCharges() {
        return (List<UnbilledCharge>) getList("unbilled_charges", "unbilled_charge");
    }

    public List<Download> downloads() {
        return (List<Download>) getList("downloads", "download");
    }

    public List<Configuration> configurations() {
        return (List<Configuration>) getList("configurations", "configuration");
    }

    public List<InAppSubscription> inAppSubscriptions() {
        return (List<InAppSubscription>) getList("in_app_subscriptions", "in_app_subscription");
    }

    public List<DifferentialPrice> differentialPrices() {
        return (List<DifferentialPrice>) getList("differential_prices", "differential_price");
    }


    private List<? extends Resource> getList(String pluralName, String singularName) {
        JSONArray listModels = jsonObj.optJSONArray(pluralName);
        if (listModels == null) {
            return null;
        }
        try {
            List<Resource> list = new ArrayList<Resource>();
            for (int i = 0; i < listModels.length(); i++) {
                JSONObject modelJson = listModels.getJSONObject(i);
                list.add(_get(singularName, modelJson));
            }
            return list;
        } catch (JSONException jsonExp) {
            throw new RuntimeException(jsonExp);
        }
    }

    private Resource get(String key) {
        JSONObject modelJson = jsonObj.optJSONObject(key);
        return _get(key, modelJson);
    }

    private Resource _get(String key, JSONObject modelJson) {
        if(modelJson == null) {
            return null;
        }
        Class<Resource> modelClaz = ClazzUtil.getClaz(key);
        return ClazzUtil.createInstance(modelClaz, modelJson);
    }

    public JSONObject jsonObj(){
        return jsonObj;
    }

    @Override
    public String toString() {
        try {
            return jsonObj.toString(2);
        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        }
    }


}
