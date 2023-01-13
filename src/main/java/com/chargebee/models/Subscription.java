package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.filters.*;
import com.chargebee.filters.enums.SortOrder;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

public class Subscription extends Resource<Subscription> {

    public enum BillingPeriodUnit {
        DAY,
        WEEK,
        MONTH,
        YEAR,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum Status {
        FUTURE,
        IN_TRIAL,
        ACTIVE,
        NON_RENEWING,
        PAUSED,
        CANCELLED,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public enum CancelReason {
        NOT_PAID,
        NO_CARD,
        FRAUD_REVIEW_FAILED,
        NON_COMPLIANT_EU_CUSTOMER,
        TAX_CALCULATION_FAILED,
        CURRENCY_INCOMPATIBLE_WITH_GATEWAY,
        NON_COMPLIANT_CUSTOMER,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class SubscriptionItem extends Resource<SubscriptionItem> {
        public enum ItemType {
             PLAN,ADDON,CHARGE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum ChargeOnOption {
             IMMEDIATELY,ON_EVENT,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public SubscriptionItem(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String itemPriceId() {
            return reqString("item_price_id");
        }

        public ItemType itemType() {
            return reqEnum("item_type", ItemType.class);
        }

        public Integer quantity() {
            return optInteger("quantity");
        }

        public String quantityInDecimal() {
            return optString("quantity_in_decimal");
        }

        @Deprecated
        public String meteredQuantity() {
            return optString("metered_quantity");
        }

        @Deprecated
        public Timestamp lastCalculatedAt() {
            return optTimestamp("last_calculated_at");
        }

        public Long unitPrice() {
            return optLong("unit_price");
        }

        public String unitPriceInDecimal() {
            return optString("unit_price_in_decimal");
        }

        public Long amount() {
            return optLong("amount");
        }

        public String amountInDecimal() {
            return optString("amount_in_decimal");
        }

        public Integer freeQuantity() {
            return optInteger("free_quantity");
        }

        public String freeQuantityInDecimal() {
            return optString("free_quantity_in_decimal");
        }

        public Timestamp trialEnd() {
            return optTimestamp("trial_end");
        }

        public Integer billingCycles() {
            return optInteger("billing_cycles");
        }

        public Integer servicePeriodDays() {
            return optInteger("service_period_days");
        }

        public ChargeOnEvent chargeOnEvent() {
            return optEnum("charge_on_event", ChargeOnEvent.class);
        }

        public Boolean chargeOnce() {
            return optBoolean("charge_once");
        }

        public ChargeOnOption chargeOnOption() {
            return optEnum("charge_on_option", ChargeOnOption.class);
        }

    }

    public static class ItemTier extends Resource<ItemTier> {
        public ItemTier(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String itemPriceId() {
            return reqString("item_price_id");
        }

        public Integer startingUnit() {
            return reqInteger("starting_unit");
        }

        public Integer endingUnit() {
            return optInteger("ending_unit");
        }

        public Long price() {
            return reqLong("price");
        }

        public String startingUnitInDecimal() {
            return optString("starting_unit_in_decimal");
        }

        public String endingUnitInDecimal() {
            return optString("ending_unit_in_decimal");
        }

        public String priceInDecimal() {
            return optString("price_in_decimal");
        }

        public Integer index() {
            return reqInteger("index");
        }

    }

    public static class ChargedItem extends Resource<ChargedItem> {
        public ChargedItem(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String itemPriceId() {
            return reqString("item_price_id");
        }

        public Timestamp lastChargedAt() {
            return reqTimestamp("last_charged_at");
        }

    }

    public static class Addon extends Resource<Addon> {
        public Addon(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public Integer quantity() {
            return optInteger("quantity");
        }

        public Long unitPrice() {
            return optLong("unit_price");
        }

        public Long amount() {
            return optLong("amount");
        }

        public Timestamp trialEnd() {
            return optTimestamp("trial_end");
        }

        public Integer remainingBillingCycles() {
            return optInteger("remaining_billing_cycles");
        }

        public String quantityInDecimal() {
            return optString("quantity_in_decimal");
        }

        public String unitPriceInDecimal() {
            return optString("unit_price_in_decimal");
        }

        public String amountInDecimal() {
            return optString("amount_in_decimal");
        }

    }

    public static class EventBasedAddon extends Resource<EventBasedAddon> {
        public enum OnEvent {
             SUBSCRIPTION_CREATION,SUBSCRIPTION_TRIAL_START,PLAN_ACTIVATION,SUBSCRIPTION_ACTIVATION,CONTRACT_TERMINATION,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public EventBasedAddon(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public Integer quantity() {
            return reqInteger("quantity");
        }

        public Long unitPrice() {
            return reqLong("unit_price");
        }

        @Deprecated
        public Integer servicePeriodInDays() {
            return optInteger("service_period_in_days");
        }

        public OnEvent onEvent() {
            return reqEnum("on_event", OnEvent.class);
        }

        public Boolean chargeOnce() {
            return reqBoolean("charge_once");
        }

        public String quantityInDecimal() {
            return optString("quantity_in_decimal");
        }

        public String unitPriceInDecimal() {
            return optString("unit_price_in_decimal");
        }

    }

    public static class ChargedEventBasedAddon extends Resource<ChargedEventBasedAddon> {
        public ChargedEventBasedAddon(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public Timestamp lastChargedAt() {
            return reqTimestamp("last_charged_at");
        }

    }

    public static class Coupon extends Resource<Coupon> {
        public Coupon(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String couponId() {
            return reqString("coupon_id");
        }

        public Timestamp applyTill() {
            return optTimestamp("apply_till");
        }

        public Integer appliedCount() {
            return reqInteger("applied_count");
        }

        public String couponCode() {
            return optString("coupon_code");
        }

    }

    public static class ShippingAddress extends Resource<ShippingAddress> {
        public ShippingAddress(JSONObject jsonObj) {
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

        public Integer index() {
            return reqInteger("index");
        }

    }

    public static class ReferralInfo extends Resource<ReferralInfo> {
        public enum RewardStatus {
             PENDING,PAID,INVALID,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public ReferralInfo(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String referralCode() {
            return optString("referral_code");
        }

        public String couponCode() {
            return optString("coupon_code");
        }

        public String referrerId() {
            return optString("referrer_id");
        }

        public String externalReferenceId() {
            return optString("external_reference_id");
        }

        public RewardStatus rewardStatus() {
            return optEnum("reward_status", RewardStatus.class);
        }

        public ReferralSystem referralSystem() {
            return optEnum("referral_system", ReferralSystem.class);
        }

        public String accountId() {
            return reqString("account_id");
        }

        public String campaignId() {
            return reqString("campaign_id");
        }

        public String externalCampaignId() {
            return optString("external_campaign_id");
        }

        public FriendOfferType friendOfferType() {
            return optEnum("friend_offer_type", FriendOfferType.class);
        }

        public ReferrerRewardType referrerRewardType() {
            return optEnum("referrer_reward_type", ReferrerRewardType.class);
        }

        public NotifyReferralSystem notifyReferralSystem() {
            return optEnum("notify_referral_system", NotifyReferralSystem.class);
        }

        public String destinationUrl() {
            return optString("destination_url");
        }

        public Boolean postPurchaseWidgetEnabled() {
            return reqBoolean("post_purchase_widget_enabled");
        }

    }

    public static class ContractTerm extends Resource<ContractTerm> {
        public enum Status {
             ACTIVE,COMPLETED,CANCELLED,TERMINATED,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum ActionAtTermEnd {
             RENEW,EVERGREEN,CANCEL,RENEW_ONCE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public ContractTerm(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public Status status() {
            return reqEnum("status", Status.class);
        }

        public Timestamp contractStart() {
            return reqTimestamp("contract_start");
        }

        public Timestamp contractEnd() {
            return reqTimestamp("contract_end");
        }

        public Integer billingCycle() {
            return reqInteger("billing_cycle");
        }

        public ActionAtTermEnd actionAtTermEnd() {
            return reqEnum("action_at_term_end", ActionAtTermEnd.class);
        }

        public Long totalContractValue() {
            return reqLong("total_contract_value");
        }

        public Integer cancellationCutoffPeriod() {
            return optInteger("cancellation_cutoff_period");
        }

        public Timestamp createdAt() {
            return reqTimestamp("created_at");
        }

        public String subscriptionId() {
            return reqString("subscription_id");
        }

        public Integer remainingBillingCycles() {
            return optInteger("remaining_billing_cycles");
        }

    }

    public static class Discount extends Resource<Discount> {
        public enum Type {
             FIXED_AMOUNT,PERCENTAGE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum DurationType {
             ONE_TIME,FOREVER,LIMITED_PERIOD,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public enum ApplyOn {
             INVOICE_AMOUNT,SPECIFIC_ITEM_PRICE,
            _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
            java-client version incompatibility. We suggest you to upgrade to the latest version */ 
        }

        public Discount(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String id() {
            return reqString("id");
        }

        public String invoiceName() {
            return optString("invoice_name");
        }

        public Type type() {
            return reqEnum("type", Type.class);
        }

        public Double percentage() {
            return optDouble("percentage");
        }

        public Long amount() {
            return optLong("amount");
        }

        public String currencyCode() {
            return optString("currency_code");
        }

        public DurationType durationType() {
            return reqEnum("duration_type", DurationType.class);
        }

        public Integer period() {
            return optInteger("period");
        }

        public PeriodUnit periodUnit() {
            return optEnum("period_unit", PeriodUnit.class);
        }

        public Boolean includedInMrr() {
            return reqBoolean("included_in_mrr");
        }

        public ApplyOn applyOn() {
            return reqEnum("apply_on", ApplyOn.class);
        }

        public String itemPriceId() {
            return optString("item_price_id");
        }

        public Timestamp createdAt() {
            return reqTimestamp("created_at");
        }

        public Timestamp applyTill() {
            return optTimestamp("apply_till");
        }

        public Integer appliedCount() {
            return optInteger("applied_count");
        }

        public String couponId() {
            return reqString("coupon_id");
        }

        public Integer index() {
            return reqInteger("index");
        }

    }

    //Constructors
    //============

    public Subscription(String jsonStr) {
        super(jsonStr);
    }

    public Subscription(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String currencyCode() {
        return reqString("currency_code");
    }

    public String planId() {
        return reqString("plan_id");
    }

    public Integer planQuantity() {
        return reqInteger("plan_quantity");
    }

    public Long planUnitPrice() {
        return optLong("plan_unit_price");
    }

    public Long setupFee() {
        return optLong("setup_fee");
    }

    public Integer billingPeriod() {
        return optInteger("billing_period");
    }

    public BillingPeriodUnit billingPeriodUnit() {
        return optEnum("billing_period_unit", BillingPeriodUnit.class);
    }

    public Timestamp startDate() {
        return optTimestamp("start_date");
    }

    public Timestamp trialEnd() {
        return optTimestamp("trial_end");
    }

    public Integer remainingBillingCycles() {
        return optInteger("remaining_billing_cycles");
    }

    public String poNumber() {
        return optString("po_number");
    }

    public AutoCollection autoCollection() {
        return optEnum("auto_collection", AutoCollection.class);
    }

    public String planQuantityInDecimal() {
        return optString("plan_quantity_in_decimal");
    }

    public String planUnitPriceInDecimal() {
        return optString("plan_unit_price_in_decimal");
    }

    public String customerId() {
        return reqString("customer_id");
    }

    public Long planAmount() {
        return optLong("plan_amount");
    }

    public Integer planFreeQuantity() {
        return optInteger("plan_free_quantity");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public Timestamp trialStart() {
        return optTimestamp("trial_start");
    }

    public TrialEndAction trialEndAction() {
        return optEnum("trial_end_action", TrialEndAction.class);
    }

    public Timestamp currentTermStart() {
        return optTimestamp("current_term_start");
    }

    public Timestamp currentTermEnd() {
        return optTimestamp("current_term_end");
    }

    public Timestamp nextBillingAt() {
        return optTimestamp("next_billing_at");
    }

    public Timestamp createdAt() {
        return optTimestamp("created_at");
    }

    public Timestamp startedAt() {
        return optTimestamp("started_at");
    }

    public Timestamp activatedAt() {
        return optTimestamp("activated_at");
    }

    public String giftId() {
        return optString("gift_id");
    }

    public Integer contractTermBillingCycleOnRenewal() {
        return optInteger("contract_term_billing_cycle_on_renewal");
    }

    public Boolean overrideRelationship() {
        return optBoolean("override_relationship");
    }

    public Timestamp pauseDate() {
        return optTimestamp("pause_date");
    }

    public Timestamp resumeDate() {
        return optTimestamp("resume_date");
    }

    public Timestamp cancelledAt() {
        return optTimestamp("cancelled_at");
    }

    public CancelReason cancelReason() {
        return optEnum("cancel_reason", CancelReason.class);
    }

    public String affiliateToken() {
        return optString("affiliate_token");
    }

    public String createdFromIp() {
        return optString("created_from_ip");
    }

    public Long resourceVersion() {
        return optLong("resource_version");
    }

    public Timestamp updatedAt() {
        return optTimestamp("updated_at");
    }

    public Boolean hasScheduledAdvanceInvoices() {
        return reqBoolean("has_scheduled_advance_invoices");
    }

    public Boolean hasScheduledChanges() {
        return reqBoolean("has_scheduled_changes");
    }

    public String paymentSourceId() {
        return optString("payment_source_id");
    }

    public String planFreeQuantityInDecimal() {
        return optString("plan_free_quantity_in_decimal");
    }

    public String planAmountInDecimal() {
        return optString("plan_amount_in_decimal");
    }

    public Timestamp cancelScheduleCreatedAt() {
        return optTimestamp("cancel_schedule_created_at");
    }

    public OfflinePaymentMethod offlinePaymentMethod() {
        return optEnum("offline_payment_method", OfflinePaymentMethod.class);
    }

    public Channel channel() {
        return optEnum("channel", Channel.class);
    }

    public Integer netTermDays() {
        return optInteger("net_term_days");
    }

    public List<Subscription.SubscriptionItem> subscriptionItems() {
        return optList("subscription_items", Subscription.SubscriptionItem.class);
    }

    public List<Subscription.ItemTier> itemTiers() {
        return optList("item_tiers", Subscription.ItemTier.class);
    }

    public List<Subscription.ChargedItem> chargedItems() {
        return optList("charged_items", Subscription.ChargedItem.class);
    }

    public Integer dueInvoicesCount() {
        return optInteger("due_invoices_count");
    }

    public Timestamp dueSince() {
        return optTimestamp("due_since");
    }

    public Long totalDues() {
        return optLong("total_dues");
    }

    public Long mrr() {
        return optLong("mrr");
    }

    public BigDecimal exchangeRate() {
        return optBigDecimal("exchange_rate");
    }

    public String baseCurrencyCode() {
        return optString("base_currency_code");
    }

    public List<Subscription.Addon> addons() {
        return optList("addons", Subscription.Addon.class);
    }

    public List<Subscription.EventBasedAddon> eventBasedAddons() {
        return optList("event_based_addons", Subscription.EventBasedAddon.class);
    }

    public List<Subscription.ChargedEventBasedAddon> chargedEventBasedAddons() {
        return optList("charged_event_based_addons", Subscription.ChargedEventBasedAddon.class);
    }

    @Deprecated
    public String coupon() {
        return optString("coupon");
    }

    public List<Subscription.Coupon> coupons() {
        return optList("coupons", Subscription.Coupon.class);
    }

    public Subscription.ShippingAddress shippingAddress() {
        return optSubResource("shipping_address", Subscription.ShippingAddress.class);
    }

    public Subscription.ReferralInfo referralInfo() {
        return optSubResource("referral_info", Subscription.ReferralInfo.class);
    }

    public String invoiceNotes() {
        return optString("invoice_notes");
    }

    public JSONObject metaData() {
        return optJSONObject("meta_data");
    }

    public JSONObject metadata() {
        return optJSONObject("metadata");
    }

    public Boolean deleted() {
        return reqBoolean("deleted");
    }

    public Timestamp changesScheduledAt() {
        return optTimestamp("changes_scheduled_at");
    }

    public Subscription.ContractTerm contractTerm() {
        return optSubResource("contract_term", Subscription.ContractTerm.class);
    }

    public String cancelReasonCode() {
        return optString("cancel_reason_code");
    }

    public Integer freePeriod() {
        return optInteger("free_period");
    }

    public FreePeriodUnit freePeriodUnit() {
        return optEnum("free_period_unit", FreePeriodUnit.class);
    }

    public Boolean createPendingInvoices() {
        return optBoolean("create_pending_invoices");
    }

    public Boolean autoCloseInvoices() {
        return optBoolean("auto_close_invoices");
    }

    public List<Subscription.Discount> discounts() {
        return optList("discounts", Subscription.Discount.class);
    }

    public String businessEntityId() {
        return optString("business_entity_id");
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("subscriptions");
        return new CreateRequest(Method.POST, uri);
    }

    public static CreateForCustomerRequest createForCustomer(String id) {
        String uri = uri("customers", nullCheck(id), "subscriptions");
        return new CreateForCustomerRequest(Method.POST, uri);
    }

    public static CreateWithItemsRequest createWithItems(String id) {
        String uri = uri("customers", nullCheck(id), "subscription_for_items");
        return new CreateWithItemsRequest(Method.POST, uri);
    }

    public static SubscriptionListRequest list() {
        String uri = uri("subscriptions");
        return new SubscriptionListRequest(uri);
    }

    @Deprecated
    public static ListRequest subscriptionsForCustomer(String id) {
        String uri = uri("customers", nullCheck(id), "subscriptions");
        return new ListRequest(uri);
    }

    public static ListRequest contractTermsForSubscription(String id) {
        String uri = uri("subscriptions", nullCheck(id), "contract_terms");
        return new ListRequest(uri);
    }

    public static ListRequest listDiscounts(String id) {
        String uri = uri("subscriptions", nullCheck(id), "discounts");
        return new ListRequest(uri);
    }

    public static Request retrieve(String id) {
        String uri = uri("subscriptions", nullCheck(id));
        return new Request(Method.GET, uri);
    }

    public static Request retrieveWithScheduledChanges(String id) {
        String uri = uri("subscriptions", nullCheck(id), "retrieve_with_scheduled_changes");
        return new Request(Method.GET, uri);
    }

    public static Request removeScheduledChanges(String id) {
        String uri = uri("subscriptions", nullCheck(id), "remove_scheduled_changes");
        return new Request(Method.POST, uri);
    }

    public static RemoveScheduledCancellationRequest removeScheduledCancellation(String id) {
        String uri = uri("subscriptions", nullCheck(id), "remove_scheduled_cancellation");
        return new RemoveScheduledCancellationRequest(Method.POST, uri);
    }

    public static RemoveCouponsRequest removeCoupons(String id) {
        String uri = uri("subscriptions", nullCheck(id), "remove_coupons");
        return new RemoveCouponsRequest(Method.POST, uri);
    }

    public static UpdateRequest update(String id) {
        String uri = uri("subscriptions", nullCheck(id));
        return new UpdateRequest(Method.POST, uri);
    }

    public static UpdateForItemsRequest updateForItems(String id) {
        String uri = uri("subscriptions", nullCheck(id), "update_for_items");
        return new UpdateForItemsRequest(Method.POST, uri);
    }

    public static ChangeTermEndRequest changeTermEnd(String id) {
        String uri = uri("subscriptions", nullCheck(id), "change_term_end");
        return new ChangeTermEndRequest(Method.POST, uri);
    }

    public static ReactivateRequest reactivate(String id) {
        String uri = uri("subscriptions", nullCheck(id), "reactivate");
        return new ReactivateRequest(Method.POST, uri);
    }

    public static AddChargeAtTermEndRequest addChargeAtTermEnd(String id) {
        String uri = uri("subscriptions", nullCheck(id), "add_charge_at_term_end");
        return new AddChargeAtTermEndRequest(Method.POST, uri);
    }

    public static ChargeAddonAtTermEndRequest chargeAddonAtTermEnd(String id) {
        String uri = uri("subscriptions", nullCheck(id), "charge_addon_at_term_end");
        return new ChargeAddonAtTermEndRequest(Method.POST, uri);
    }

    public static ChargeFutureRenewalsRequest chargeFutureRenewals(String id) {
        String uri = uri("subscriptions", nullCheck(id), "charge_future_renewals");
        return new ChargeFutureRenewalsRequest(Method.POST, uri);
    }

    public static EditAdvanceInvoiceScheduleRequest editAdvanceInvoiceSchedule(String id) {
        String uri = uri("subscriptions", nullCheck(id), "edit_advance_invoice_schedule");
        return new EditAdvanceInvoiceScheduleRequest(Method.POST, uri);
    }

    public static Request retrieveAdvanceInvoiceSchedule(String id) {
        String uri = uri("subscriptions", nullCheck(id), "retrieve_advance_invoice_schedule");
        return new Request(Method.GET, uri);
    }

    public static RemoveAdvanceInvoiceScheduleRequest removeAdvanceInvoiceSchedule(String id) {
        String uri = uri("subscriptions", nullCheck(id), "remove_advance_invoice_schedule");
        return new RemoveAdvanceInvoiceScheduleRequest(Method.POST, uri);
    }

    public static RegenerateInvoiceRequest regenerateInvoice(String id) {
        String uri = uri("subscriptions", nullCheck(id), "regenerate_invoice");
        return new RegenerateInvoiceRequest(Method.POST, uri);
    }

    public static ImportSubscriptionRequest importSubscription() {
        String uri = uri("subscriptions", "import_subscription");
        return new ImportSubscriptionRequest(Method.POST, uri);
    }

    public static ImportForCustomerRequest importForCustomer(String id) {
        String uri = uri("customers", nullCheck(id), "import_subscription");
        return new ImportForCustomerRequest(Method.POST, uri);
    }

    public static ImportContractTermRequest importContractTerm(String id) {
        String uri = uri("subscriptions", nullCheck(id), "import_contract_term");
        return new ImportContractTermRequest(Method.POST, uri);
    }

    public static ImportUnbilledChargesRequest importUnbilledCharges(String id) {
        String uri = uri("subscriptions", nullCheck(id), "import_unbilled_charges");
        return new ImportUnbilledChargesRequest(Method.POST, uri);
    }

    public static ImportForItemsRequest importForItems(String id) {
        String uri = uri("customers", nullCheck(id), "import_for_items");
        return new ImportForItemsRequest(Method.POST, uri);
    }

    public static OverrideBillingProfileRequest overrideBillingProfile(String id) {
        String uri = uri("subscriptions", nullCheck(id), "override_billing_profile");
        return new OverrideBillingProfileRequest(Method.POST, uri);
    }

    public static Request delete(String id) {
        String uri = uri("subscriptions", nullCheck(id), "delete");
        return new Request(Method.POST, uri);
    }

    public static PauseRequest pause(String id) {
        String uri = uri("subscriptions", nullCheck(id), "pause");
        return new PauseRequest(Method.POST, uri);
    }

    public static CancelRequest cancel(String id) {
        String uri = uri("subscriptions", nullCheck(id), "cancel");
        return new CancelRequest(Method.POST, uri);
    }

    public static CancelForItemsRequest cancelForItems(String id) {
        String uri = uri("subscriptions", nullCheck(id), "cancel_for_items");
        return new CancelForItemsRequest(Method.POST, uri);
    }

    public static ResumeRequest resume(String id) {
        String uri = uri("subscriptions", nullCheck(id), "resume");
        return new ResumeRequest(Method.POST, uri);
    }

    public static Request removeScheduledPause(String id) {
        String uri = uri("subscriptions", nullCheck(id), "remove_scheduled_pause");
        return new Request(Method.POST, uri);
    }

    public static Request removeScheduledResumption(String id) {
        String uri = uri("subscriptions", nullCheck(id), "remove_scheduled_resumption");
        return new Request(Method.POST, uri);
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


        public CreateRequest businessEntityId(String businessEntityId) {
            params.addOpt("business_entity_id", businessEntityId);
            return this;
        }


        public CreateRequest planId(String planId) {
            params.add("plan_id", planId);
            return this;
        }


        public CreateRequest planQuantity(Integer planQuantity) {
            params.addOpt("plan_quantity", planQuantity);
            return this;
        }


        public CreateRequest planQuantityInDecimal(String planQuantityInDecimal) {
            params.addOpt("plan_quantity_in_decimal", planQuantityInDecimal);
            return this;
        }


        public CreateRequest planUnitPrice(Long planUnitPrice) {
            params.addOpt("plan_unit_price", planUnitPrice);
            return this;
        }


        public CreateRequest planUnitPriceInDecimal(String planUnitPriceInDecimal) {
            params.addOpt("plan_unit_price_in_decimal", planUnitPriceInDecimal);
            return this;
        }


        public CreateRequest setupFee(Long setupFee) {
            params.addOpt("setup_fee", setupFee);
            return this;
        }


        public CreateRequest trialEnd(Timestamp trialEnd) {
            params.addOpt("trial_end", trialEnd);
            return this;
        }


        public CreateRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public CreateRequest mandatoryAddonsToRemove(List<String> mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public CreateRequest mandatoryAddonsToRemove(String... mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public CreateRequest startDate(Timestamp startDate) {
            params.addOpt("start_date", startDate);
            return this;
        }


        @Deprecated
        public CreateRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public CreateRequest autoCollection(com.chargebee.models.enums.AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        public CreateRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }






        public CreateRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }




        public CreateRequest offlinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod offlinePaymentMethod) {
            params.addOpt("offline_payment_method", offlinePaymentMethod);
            return this;
        }


        public CreateRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public CreateRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateRequest tokenId(String tokenId) {
            params.addOpt("token_id", tokenId);
            return this;
        }


        public CreateRequest affiliateToken(String affiliateToken) {
            params.addOpt("affiliate_token", affiliateToken);
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


        public CreateRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public CreateRequest metaData(JSONObject metaData) {
            params.addOpt("meta_data", metaData);
            return this;
        }




        public CreateRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }


        public CreateRequest freePeriod(Integer freePeriod) {
            params.addOpt("free_period", freePeriod);
            return this;
        }


        public CreateRequest freePeriodUnit(com.chargebee.models.enums.FreePeriodUnit freePeriodUnit) {
            params.addOpt("free_period_unit", freePeriodUnit);
            return this;
        }




        public CreateRequest contractTermBillingCycleOnRenewal(Integer contractTermBillingCycleOnRenewal) {
            params.addOpt("contract_term_billing_cycle_on_renewal", contractTermBillingCycleOnRenewal);
            return this;
        }


        public CreateRequest trialEndAction(com.chargebee.models.enums.TrialEndAction trialEndAction) {
            params.addOpt("trial_end_action", trialEndAction);
            return this;
        }


        public CreateRequest clientProfileId(String clientProfileId) {
            params.addOpt("client_profile_id", clientProfileId);
            return this;
        }


        public CreateRequest customerId(String customerId) {
            params.addOpt("customer[id]", customerId);
            return this;
        }

        public CreateRequest customerEmail(String customerEmail) {
            params.addOpt("customer[email]", customerEmail);
            return this;
        }

        public CreateRequest customerFirstName(String customerFirstName) {
            params.addOpt("customer[first_name]", customerFirstName);
            return this;
        }

        public CreateRequest customerLastName(String customerLastName) {
            params.addOpt("customer[last_name]", customerLastName);
            return this;
        }

        public CreateRequest customerCompany(String customerCompany) {
            params.addOpt("customer[company]", customerCompany);
            return this;
        }

        public CreateRequest customerTaxability(com.chargebee.models.enums.Taxability customerTaxability) {
            params.addOpt("customer[taxability]", customerTaxability);
            return this;
        }

        public CreateRequest customerLocale(String customerLocale) {
            params.addOpt("customer[locale]", customerLocale);
            return this;
        }

        public CreateRequest customerEntityCode(com.chargebee.models.enums.EntityCode customerEntityCode) {
            params.addOpt("customer[entity_code]", customerEntityCode);
            return this;
        }

        public CreateRequest customerExemptNumber(String customerExemptNumber) {
            params.addOpt("customer[exempt_number]", customerExemptNumber);
            return this;
        }

        public CreateRequest customerNetTermDays(Integer customerNetTermDays) {
            params.addOpt("customer[net_term_days]", customerNetTermDays);
            return this;
        }

        public CreateRequest customerTaxjarExemptionCategory(com.chargebee.models.enums.TaxjarExemptionCategory customerTaxjarExemptionCategory) {
            params.addOpt("customer[taxjar_exemption_category]", customerTaxjarExemptionCategory);
            return this;
        }

        public CreateRequest customerPhone(String customerPhone) {
            params.addOpt("customer[phone]", customerPhone);
            return this;
        }

        public CreateRequest customerAutoCollection(com.chargebee.models.enums.AutoCollection customerAutoCollection) {
            params.addOpt("customer[auto_collection]", customerAutoCollection);
            return this;
        }

        public CreateRequest customerOfflinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod customerOfflinePaymentMethod) {
            params.addOpt("customer[offline_payment_method]", customerOfflinePaymentMethod);
            return this;
        }

        public CreateRequest customerAllowDirectDebit(Boolean customerAllowDirectDebit) {
            params.addOpt("customer[allow_direct_debit]", customerAllowDirectDebit);
            return this;
        }

        public CreateRequest customerConsolidatedInvoicing(Boolean customerConsolidatedInvoicing) {
            params.addOpt("customer[consolidated_invoicing]", customerConsolidatedInvoicing);
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

        public CreateRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public CreateRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public CreateRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public CreateRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public CreateRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public CreateRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CreateRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CreateRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CreateRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CreateRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CreateRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public CreateRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CreateRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CreateRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CreateRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public CreateRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
            return this;
        }

        public CreateRequest customerEntityIdentifierScheme(String customerEntityIdentifierScheme) {
            params.addOpt("customer[entity_identifier_scheme]", customerEntityIdentifierScheme);
            return this;
        }

        public CreateRequest customerEntityIdentifierStandard(String customerEntityIdentifierStandard) {
            params.addOpt("customer[entity_identifier_standard]", customerEntityIdentifierStandard);
            return this;
        }

        public CreateRequest customerIsEinvoiceEnabled(Boolean customerIsEinvoiceEnabled) {
            params.addOpt("customer[is_einvoice_enabled]", customerIsEinvoiceEnabled);
            return this;
        }

        public CreateRequest customerRegisteredForGst(Boolean customerRegisteredForGst) {
            params.addOpt("customer[registered_for_gst]", customerRegisteredForGst);
            return this;
        }

        public CreateRequest customerBusinessCustomerWithoutVatNumber(Boolean customerBusinessCustomerWithoutVatNumber) {
            params.addOpt("customer[business_customer_without_vat_number]", customerBusinessCustomerWithoutVatNumber);
            return this;
        }

        public CreateRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public CreateRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public CreateRequest customerExemptionDetails(JSONArray customerExemptionDetails) {
            params.addOpt("customer[exemption_details]", customerExemptionDetails);
            return this;
        }

        public CreateRequest customerCustomerType(com.chargebee.models.enums.CustomerType customerCustomerType) {
            params.addOpt("customer[customer_type]", customerCustomerType);
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
        public CreateRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public CreateRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public CreateRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public CreateRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public CreateRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public CreateRequest addonBillingCycles(int index, Integer addonBillingCycles) {
            params.addOpt("addons[billing_cycles][" + index + "]", addonBillingCycles);
            return this;
        }
        public CreateRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public CreateRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public CreateRequest eventBasedAddonUnitPrice(int index, Long eventBasedAddonUnitPrice) {
            params.addOpt("event_based_addons[unit_price][" + index + "]", eventBasedAddonUnitPrice);
            return this;
        }
        public CreateRequest eventBasedAddonQuantityInDecimal(int index, String eventBasedAddonQuantityInDecimal) {
            params.addOpt("event_based_addons[quantity_in_decimal][" + index + "]", eventBasedAddonQuantityInDecimal);
            return this;
        }
        public CreateRequest eventBasedAddonUnitPriceInDecimal(int index, String eventBasedAddonUnitPriceInDecimal) {
            params.addOpt("event_based_addons[unit_price_in_decimal][" + index + "]", eventBasedAddonUnitPriceInDecimal);
            return this;
        }
        public CreateRequest eventBasedAddonServicePeriodInDays(int index, Integer eventBasedAddonServicePeriodInDays) {
            params.addOpt("event_based_addons[service_period_in_days][" + index + "]", eventBasedAddonServicePeriodInDays);
            return this;
        }
        public CreateRequest eventBasedAddonOnEvent(int index, com.chargebee.models.enums.OnEvent eventBasedAddonOnEvent) {
            params.addOpt("event_based_addons[on_event][" + index + "]", eventBasedAddonOnEvent);
            return this;
        }
        public CreateRequest eventBasedAddonChargeOnce(int index, Boolean eventBasedAddonChargeOnce) {
            params.addOpt("event_based_addons[charge_once][" + index + "]", eventBasedAddonChargeOnce);
            return this;
        }
        public CreateRequest eventBasedAddonChargeOn(int index, com.chargebee.models.enums.ChargeOn eventBasedAddonChargeOn) {
            params.addOpt("event_based_addons[charge_on][" + index + "]", eventBasedAddonChargeOn);
            return this;
        }
        public CreateRequest addonTrialEnd(int index, Timestamp addonTrialEnd) {
            params.addOpt("addons[trial_end][" + index + "]", addonTrialEnd);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateForCustomerRequest extends Request<CreateForCustomerRequest> {

        private CreateForCustomerRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateForCustomerRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }


        public CreateForCustomerRequest businessEntityId(String businessEntityId) {
            params.addOpt("business_entity_id", businessEntityId);
            return this;
        }


        public CreateForCustomerRequest planId(String planId) {
            params.add("plan_id", planId);
            return this;
        }


        public CreateForCustomerRequest planQuantity(Integer planQuantity) {
            params.addOpt("plan_quantity", planQuantity);
            return this;
        }


        public CreateForCustomerRequest planQuantityInDecimal(String planQuantityInDecimal) {
            params.addOpt("plan_quantity_in_decimal", planQuantityInDecimal);
            return this;
        }


        public CreateForCustomerRequest planUnitPrice(Long planUnitPrice) {
            params.addOpt("plan_unit_price", planUnitPrice);
            return this;
        }


        public CreateForCustomerRequest planUnitPriceInDecimal(String planUnitPriceInDecimal) {
            params.addOpt("plan_unit_price_in_decimal", planUnitPriceInDecimal);
            return this;
        }


        public CreateForCustomerRequest setupFee(Long setupFee) {
            params.addOpt("setup_fee", setupFee);
            return this;
        }


        public CreateForCustomerRequest trialEnd(Timestamp trialEnd) {
            params.addOpt("trial_end", trialEnd);
            return this;
        }


        public CreateForCustomerRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public CreateForCustomerRequest mandatoryAddonsToRemove(List<String> mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public CreateForCustomerRequest mandatoryAddonsToRemove(String... mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public CreateForCustomerRequest startDate(Timestamp startDate) {
            params.addOpt("start_date", startDate);
            return this;
        }


        @Deprecated
        public CreateForCustomerRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public CreateForCustomerRequest autoCollection(com.chargebee.models.enums.AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        public CreateForCustomerRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }






        public CreateForCustomerRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }




        public CreateForCustomerRequest offlinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod offlinePaymentMethod) {
            params.addOpt("offline_payment_method", offlinePaymentMethod);
            return this;
        }


        public CreateForCustomerRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public CreateForCustomerRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateForCustomerRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateForCustomerRequest paymentSourceId(String paymentSourceId) {
            params.addOpt("payment_source_id", paymentSourceId);
            return this;
        }


        public CreateForCustomerRequest overrideRelationship(Boolean overrideRelationship) {
            params.addOpt("override_relationship", overrideRelationship);
            return this;
        }


        public CreateForCustomerRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        public CreateForCustomerRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public CreateForCustomerRequest metaData(JSONObject metaData) {
            params.addOpt("meta_data", metaData);
            return this;
        }




        public CreateForCustomerRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }


        public CreateForCustomerRequest replacePrimaryPaymentSource(Boolean replacePrimaryPaymentSource) {
            params.addOpt("replace_primary_payment_source", replacePrimaryPaymentSource);
            return this;
        }


        public CreateForCustomerRequest freePeriod(Integer freePeriod) {
            params.addOpt("free_period", freePeriod);
            return this;
        }


        public CreateForCustomerRequest freePeriodUnit(com.chargebee.models.enums.FreePeriodUnit freePeriodUnit) {
            params.addOpt("free_period_unit", freePeriodUnit);
            return this;
        }




        public CreateForCustomerRequest contractTermBillingCycleOnRenewal(Integer contractTermBillingCycleOnRenewal) {
            params.addOpt("contract_term_billing_cycle_on_renewal", contractTermBillingCycleOnRenewal);
            return this;
        }


        public CreateForCustomerRequest trialEndAction(com.chargebee.models.enums.TrialEndAction trialEndAction) {
            params.addOpt("trial_end_action", trialEndAction);
            return this;
        }


        public CreateForCustomerRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public CreateForCustomerRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public CreateForCustomerRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public CreateForCustomerRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public CreateForCustomerRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public CreateForCustomerRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CreateForCustomerRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CreateForCustomerRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CreateForCustomerRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CreateForCustomerRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CreateForCustomerRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public CreateForCustomerRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CreateForCustomerRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CreateForCustomerRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CreateForCustomerRequest paymentIntentId(String paymentIntentId) {
            params.addOpt("payment_intent[id]", paymentIntentId);
            return this;
        }

        public CreateForCustomerRequest paymentIntentGatewayAccountId(String paymentIntentGatewayAccountId) {
            params.addOpt("payment_intent[gateway_account_id]", paymentIntentGatewayAccountId);
            return this;
        }

        public CreateForCustomerRequest paymentIntentGwToken(String paymentIntentGwToken) {
            params.addOpt("payment_intent[gw_token]", paymentIntentGwToken);
            return this;
        }

        public CreateForCustomerRequest paymentIntentPaymentMethodType(PaymentIntent.PaymentMethodType paymentIntentPaymentMethodType) {
            params.addOpt("payment_intent[payment_method_type]", paymentIntentPaymentMethodType);
            return this;
        }

        public CreateForCustomerRequest paymentIntentReferenceId(String paymentIntentReferenceId) {
            params.addOpt("payment_intent[reference_id]", paymentIntentReferenceId);
            return this;
        }

        @Deprecated
        public CreateForCustomerRequest paymentIntentGwPaymentMethodId(String paymentIntentGwPaymentMethodId) {
            params.addOpt("payment_intent[gw_payment_method_id]", paymentIntentGwPaymentMethodId);
            return this;
        }

        public CreateForCustomerRequest paymentIntentAdditionalInformation(JSONObject paymentIntentAdditionalInformation) {
            params.addOpt("payment_intent[additional_information]", paymentIntentAdditionalInformation);
            return this;
        }

        public CreateForCustomerRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public CreateForCustomerRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public CreateForCustomerRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public CreateForCustomerRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public CreateForCustomerRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public CreateForCustomerRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public CreateForCustomerRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public CreateForCustomerRequest addonBillingCycles(int index, Integer addonBillingCycles) {
            params.addOpt("addons[billing_cycles][" + index + "]", addonBillingCycles);
            return this;
        }
        public CreateForCustomerRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public CreateForCustomerRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public CreateForCustomerRequest eventBasedAddonUnitPrice(int index, Long eventBasedAddonUnitPrice) {
            params.addOpt("event_based_addons[unit_price][" + index + "]", eventBasedAddonUnitPrice);
            return this;
        }
        public CreateForCustomerRequest eventBasedAddonQuantityInDecimal(int index, String eventBasedAddonQuantityInDecimal) {
            params.addOpt("event_based_addons[quantity_in_decimal][" + index + "]", eventBasedAddonQuantityInDecimal);
            return this;
        }
        public CreateForCustomerRequest eventBasedAddonUnitPriceInDecimal(int index, String eventBasedAddonUnitPriceInDecimal) {
            params.addOpt("event_based_addons[unit_price_in_decimal][" + index + "]", eventBasedAddonUnitPriceInDecimal);
            return this;
        }
        public CreateForCustomerRequest eventBasedAddonServicePeriodInDays(int index, Integer eventBasedAddonServicePeriodInDays) {
            params.addOpt("event_based_addons[service_period_in_days][" + index + "]", eventBasedAddonServicePeriodInDays);
            return this;
        }
        public CreateForCustomerRequest eventBasedAddonOnEvent(int index, com.chargebee.models.enums.OnEvent eventBasedAddonOnEvent) {
            params.addOpt("event_based_addons[on_event][" + index + "]", eventBasedAddonOnEvent);
            return this;
        }
        public CreateForCustomerRequest eventBasedAddonChargeOnce(int index, Boolean eventBasedAddonChargeOnce) {
            params.addOpt("event_based_addons[charge_once][" + index + "]", eventBasedAddonChargeOnce);
            return this;
        }
        public CreateForCustomerRequest eventBasedAddonChargeOn(int index, com.chargebee.models.enums.ChargeOn eventBasedAddonChargeOn) {
            params.addOpt("event_based_addons[charge_on][" + index + "]", eventBasedAddonChargeOn);
            return this;
        }
        public CreateForCustomerRequest addonTrialEnd(int index, Timestamp addonTrialEnd) {
            params.addOpt("addons[trial_end][" + index + "]", addonTrialEnd);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateWithItemsRequest extends Request<CreateWithItemsRequest> {

        private CreateWithItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateWithItemsRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }


        public CreateWithItemsRequest businessEntityId(String businessEntityId) {
            params.addOpt("business_entity_id", businessEntityId);
            return this;
        }


        public CreateWithItemsRequest trialEnd(Timestamp trialEnd) {
            params.addOpt("trial_end", trialEnd);
            return this;
        }


        public CreateWithItemsRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        @Deprecated
        public CreateWithItemsRequest setupFee(Long setupFee) {
            params.addOpt("setup_fee", setupFee);
            return this;
        }


        public CreateWithItemsRequest mandatoryItemsToRemove(List<String> mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public CreateWithItemsRequest mandatoryItemsToRemove(String... mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public CreateWithItemsRequest netTermDays(Integer netTermDays) {
            params.addOpt("net_term_days", netTermDays);
            return this;
        }


        public CreateWithItemsRequest startDate(Timestamp startDate) {
            params.addOpt("start_date", startDate);
            return this;
        }


        @Deprecated
        public CreateWithItemsRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public CreateWithItemsRequest autoCollection(com.chargebee.models.enums.AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        public CreateWithItemsRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }






        public CreateWithItemsRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }




        public CreateWithItemsRequest offlinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod offlinePaymentMethod) {
            params.addOpt("offline_payment_method", offlinePaymentMethod);
            return this;
        }


        public CreateWithItemsRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public CreateWithItemsRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateWithItemsRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public CreateWithItemsRequest paymentSourceId(String paymentSourceId) {
            params.addOpt("payment_source_id", paymentSourceId);
            return this;
        }


        public CreateWithItemsRequest overrideRelationship(Boolean overrideRelationship) {
            params.addOpt("override_relationship", overrideRelationship);
            return this;
        }


        public CreateWithItemsRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        public CreateWithItemsRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public CreateWithItemsRequest metaData(JSONObject metaData) {
            params.addOpt("meta_data", metaData);
            return this;
        }




        public CreateWithItemsRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }


        public CreateWithItemsRequest replacePrimaryPaymentSource(Boolean replacePrimaryPaymentSource) {
            params.addOpt("replace_primary_payment_source", replacePrimaryPaymentSource);
            return this;
        }


        public CreateWithItemsRequest freePeriod(Integer freePeriod) {
            params.addOpt("free_period", freePeriod);
            return this;
        }


        public CreateWithItemsRequest freePeriodUnit(com.chargebee.models.enums.FreePeriodUnit freePeriodUnit) {
            params.addOpt("free_period_unit", freePeriodUnit);
            return this;
        }




        public CreateWithItemsRequest contractTermBillingCycleOnRenewal(Integer contractTermBillingCycleOnRenewal) {
            params.addOpt("contract_term_billing_cycle_on_renewal", contractTermBillingCycleOnRenewal);
            return this;
        }


        public CreateWithItemsRequest createPendingInvoices(Boolean createPendingInvoices) {
            params.addOpt("create_pending_invoices", createPendingInvoices);
            return this;
        }


        public CreateWithItemsRequest autoCloseInvoices(Boolean autoCloseInvoices) {
            params.addOpt("auto_close_invoices", autoCloseInvoices);
            return this;
        }


        public CreateWithItemsRequest firstInvoicePending(Boolean firstInvoicePending) {
            params.addOpt("first_invoice_pending", firstInvoicePending);
            return this;
        }


        public CreateWithItemsRequest trialEndAction(com.chargebee.models.enums.TrialEndAction trialEndAction) {
            params.addOpt("trial_end_action", trialEndAction);
            return this;
        }


        public CreateWithItemsRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public CreateWithItemsRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public CreateWithItemsRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public CreateWithItemsRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public CreateWithItemsRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public CreateWithItemsRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public CreateWithItemsRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public CreateWithItemsRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public CreateWithItemsRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public CreateWithItemsRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public CreateWithItemsRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public CreateWithItemsRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public CreateWithItemsRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public CreateWithItemsRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public CreateWithItemsRequest paymentIntentId(String paymentIntentId) {
            params.addOpt("payment_intent[id]", paymentIntentId);
            return this;
        }

        public CreateWithItemsRequest paymentIntentGatewayAccountId(String paymentIntentGatewayAccountId) {
            params.addOpt("payment_intent[gateway_account_id]", paymentIntentGatewayAccountId);
            return this;
        }

        public CreateWithItemsRequest paymentIntentGwToken(String paymentIntentGwToken) {
            params.addOpt("payment_intent[gw_token]", paymentIntentGwToken);
            return this;
        }

        public CreateWithItemsRequest paymentIntentPaymentMethodType(PaymentIntent.PaymentMethodType paymentIntentPaymentMethodType) {
            params.addOpt("payment_intent[payment_method_type]", paymentIntentPaymentMethodType);
            return this;
        }

        public CreateWithItemsRequest paymentIntentReferenceId(String paymentIntentReferenceId) {
            params.addOpt("payment_intent[reference_id]", paymentIntentReferenceId);
            return this;
        }

        @Deprecated
        public CreateWithItemsRequest paymentIntentGwPaymentMethodId(String paymentIntentGwPaymentMethodId) {
            params.addOpt("payment_intent[gw_payment_method_id]", paymentIntentGwPaymentMethodId);
            return this;
        }

        public CreateWithItemsRequest paymentIntentAdditionalInformation(JSONObject paymentIntentAdditionalInformation) {
            params.addOpt("payment_intent[additional_information]", paymentIntentAdditionalInformation);
            return this;
        }

        public CreateWithItemsRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public CreateWithItemsRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public CreateWithItemsRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.add("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public CreateWithItemsRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public CreateWithItemsRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        public CreateWithItemsRequest subscriptionItemUnitPrice(int index, Long subscriptionItemUnitPrice) {
            params.addOpt("subscription_items[unit_price][" + index + "]", subscriptionItemUnitPrice);
            return this;
        }
        public CreateWithItemsRequest subscriptionItemUnitPriceInDecimal(int index, String subscriptionItemUnitPriceInDecimal) {
            params.addOpt("subscription_items[unit_price_in_decimal][" + index + "]", subscriptionItemUnitPriceInDecimal);
            return this;
        }
        public CreateWithItemsRequest subscriptionItemBillingCycles(int index, Integer subscriptionItemBillingCycles) {
            params.addOpt("subscription_items[billing_cycles][" + index + "]", subscriptionItemBillingCycles);
            return this;
        }
        public CreateWithItemsRequest subscriptionItemTrialEnd(int index, Timestamp subscriptionItemTrialEnd) {
            params.addOpt("subscription_items[trial_end][" + index + "]", subscriptionItemTrialEnd);
            return this;
        }
        public CreateWithItemsRequest subscriptionItemServicePeriodDays(int index, Integer subscriptionItemServicePeriodDays) {
            params.addOpt("subscription_items[service_period_days][" + index + "]", subscriptionItemServicePeriodDays);
            return this;
        }
        public CreateWithItemsRequest subscriptionItemChargeOnEvent(int index, com.chargebee.models.enums.ChargeOnEvent subscriptionItemChargeOnEvent) {
            params.addOpt("subscription_items[charge_on_event][" + index + "]", subscriptionItemChargeOnEvent);
            return this;
        }
        public CreateWithItemsRequest subscriptionItemChargeOnce(int index, Boolean subscriptionItemChargeOnce) {
            params.addOpt("subscription_items[charge_once][" + index + "]", subscriptionItemChargeOnce);
            return this;
        }
        @Deprecated
        public CreateWithItemsRequest subscriptionItemItemType(int index, com.chargebee.models.enums.ItemType subscriptionItemItemType) {
            params.addOpt("subscription_items[item_type][" + index + "]", subscriptionItemItemType);
            return this;
        }
        public CreateWithItemsRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.add("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public CreateWithItemsRequest discountDurationType(int index, com.chargebee.models.enums.DurationType discountDurationType) {
            params.add("discounts[duration_type][" + index + "]", discountDurationType);
            return this;
        }
        public CreateWithItemsRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public CreateWithItemsRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public CreateWithItemsRequest discountPeriod(int index, Integer discountPeriod) {
            params.addOpt("discounts[period][" + index + "]", discountPeriod);
            return this;
        }
        public CreateWithItemsRequest discountPeriodUnit(int index, com.chargebee.models.enums.PeriodUnit discountPeriodUnit) {
            params.addOpt("discounts[period_unit][" + index + "]", discountPeriodUnit);
            return this;
        }
        public CreateWithItemsRequest discountIncludedInMrr(int index, Boolean discountIncludedInMrr) {
            params.addOpt("discounts[included_in_mrr][" + index + "]", discountIncludedInMrr);
            return this;
        }
        public CreateWithItemsRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        public CreateWithItemsRequest subscriptionItemChargeOnOption(int index, com.chargebee.models.enums.ChargeOnOption subscriptionItemChargeOnOption) {
            params.addOpt("subscription_items[charge_on_option][" + index + "]", subscriptionItemChargeOnOption);
            return this;
        }
        public CreateWithItemsRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public CreateWithItemsRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public CreateWithItemsRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public CreateWithItemsRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public CreateWithItemsRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public CreateWithItemsRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public CreateWithItemsRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class SubscriptionListRequest extends ListRequest<SubscriptionListRequest> {

        private SubscriptionListRequest(String uri) {
            super(uri);
        }
    
        public SubscriptionListRequest includeDeleted(Boolean includeDeleted) {
            params.addOpt("include_deleted", includeDeleted);
            return this;
        }


        public StringFilter<SubscriptionListRequest> id() {
            return new StringFilter<SubscriptionListRequest>("id",this).supportsMultiOperators(true);        
        }


        public StringFilter<SubscriptionListRequest> customerId() {
            return new StringFilter<SubscriptionListRequest>("customer_id",this).supportsMultiOperators(true);        
        }


        public StringFilter<SubscriptionListRequest> planId() {
            return new StringFilter<SubscriptionListRequest>("plan_id",this).supportsMultiOperators(true);        
        }


        public StringFilter<SubscriptionListRequest> itemId() {
            return new StringFilter<SubscriptionListRequest>("item_id",this).supportsMultiOperators(true);        
        }


        public StringFilter<SubscriptionListRequest> itemPriceId() {
            return new StringFilter<SubscriptionListRequest>("item_price_id",this).supportsMultiOperators(true);        
        }


        public EnumFilter<Subscription.Status, SubscriptionListRequest> status() {
            return new EnumFilter<Subscription.Status, SubscriptionListRequest>("status",this);        
        }


        public EnumFilter<Subscription.CancelReason, SubscriptionListRequest> cancelReason() {
            return new EnumFilter<Subscription.CancelReason, SubscriptionListRequest>("cancel_reason",this).supportsPresenceOperator(true);        
        }


        public StringFilter<SubscriptionListRequest> cancelReasonCode() {
            return new StringFilter<SubscriptionListRequest>("cancel_reason_code",this).supportsMultiOperators(true);        
        }


        public NumberFilter<Integer, SubscriptionListRequest> remainingBillingCycles() {
            return new NumberFilter<Integer, SubscriptionListRequest>("remaining_billing_cycles",this).supportsPresenceOperator(true);        
        }


        public TimestampFilter<SubscriptionListRequest> createdAt() {
            return new TimestampFilter<SubscriptionListRequest>("created_at",this);        
        }


        public TimestampFilter<SubscriptionListRequest> activatedAt() {
            return new TimestampFilter<SubscriptionListRequest>("activated_at",this).supportsPresenceOperator(true);        
        }


        public TimestampFilter<SubscriptionListRequest> nextBillingAt() {
            return new TimestampFilter<SubscriptionListRequest>("next_billing_at",this);        
        }


        public TimestampFilter<SubscriptionListRequest> cancelledAt() {
            return new TimestampFilter<SubscriptionListRequest>("cancelled_at",this);        
        }


        public BooleanFilter<SubscriptionListRequest> hasScheduledChanges() {
            return new BooleanFilter<SubscriptionListRequest>("has_scheduled_changes",this);        
        }


        public TimestampFilter<SubscriptionListRequest> updatedAt() {
            return new TimestampFilter<SubscriptionListRequest>("updated_at",this);        
        }


        public EnumFilter<com.chargebee.models.enums.OfflinePaymentMethod, SubscriptionListRequest> offlinePaymentMethod() {
            return new EnumFilter<com.chargebee.models.enums.OfflinePaymentMethod, SubscriptionListRequest>("offline_payment_method",this);        
        }


        public BooleanFilter<SubscriptionListRequest> autoCloseInvoices() {
            return new BooleanFilter<SubscriptionListRequest>("auto_close_invoices",this);        
        }


        public BooleanFilter<SubscriptionListRequest> overrideRelationship() {
            return new BooleanFilter<SubscriptionListRequest>("override_relationship",this);        
        }


        public SubscriptionListRequest sortByCreatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","created_at");
            return this;
        }
        public SubscriptionListRequest sortByUpdatedAt(SortOrder order) {
            params.addOpt("sort_by["+order.name().toLowerCase()+"]","updated_at");
            return this;
        }


        public StringFilter<SubscriptionListRequest> businessEntityId() {
            return new StringFilter<SubscriptionListRequest>("business_entity_id",this);        
        }


        public EnumFilter<com.chargebee.models.enums.Channel, SubscriptionListRequest> channel() {
            return new EnumFilter<com.chargebee.models.enums.Channel, SubscriptionListRequest>("channel",this);        
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class RemoveScheduledCancellationRequest extends Request<RemoveScheduledCancellationRequest> {

        private RemoveScheduledCancellationRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RemoveScheduledCancellationRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class RemoveCouponsRequest extends Request<RemoveCouponsRequest> {

        private RemoveCouponsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RemoveCouponsRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public RemoveCouponsRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
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
    
        public UpdateRequest planId(String planId) {
            params.addOpt("plan_id", planId);
            return this;
        }


        public UpdateRequest planQuantity(Integer planQuantity) {
            params.addOpt("plan_quantity", planQuantity);
            return this;
        }


        public UpdateRequest planUnitPrice(Long planUnitPrice) {
            params.addOpt("plan_unit_price", planUnitPrice);
            return this;
        }


        public UpdateRequest setupFee(Long setupFee) {
            params.addOpt("setup_fee", setupFee);
            return this;
        }


        public UpdateRequest replaceAddonList(Boolean replaceAddonList) {
            params.addOpt("replace_addon_list", replaceAddonList);
            return this;
        }


        public UpdateRequest mandatoryAddonsToRemove(List<String> mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public UpdateRequest mandatoryAddonsToRemove(String... mandatoryAddonsToRemove) {
            params.addOpt("mandatory_addons_to_remove", mandatoryAddonsToRemove);
            return this;
        }

        public UpdateRequest planQuantityInDecimal(String planQuantityInDecimal) {
            params.addOpt("plan_quantity_in_decimal", planQuantityInDecimal);
            return this;
        }


        public UpdateRequest planUnitPriceInDecimal(String planUnitPriceInDecimal) {
            params.addOpt("plan_unit_price_in_decimal", planUnitPriceInDecimal);
            return this;
        }


        public UpdateRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public UpdateRequest startDate(Timestamp startDate) {
            params.addOpt("start_date", startDate);
            return this;
        }


        public UpdateRequest trialEnd(Timestamp trialEnd) {
            params.addOpt("trial_end", trialEnd);
            return this;
        }


        public UpdateRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        @Deprecated
        public UpdateRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public UpdateRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }




        public UpdateRequest reactivateFrom(Timestamp reactivateFrom) {
            params.addOpt("reactivate_from", reactivateFrom);
            return this;
        }




        public UpdateRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }


        public UpdateRequest autoCollection(com.chargebee.models.enums.AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        public UpdateRequest offlinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod offlinePaymentMethod) {
            params.addOpt("offline_payment_method", offlinePaymentMethod);
            return this;
        }


        public UpdateRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public UpdateRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public UpdateRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public UpdateRequest replaceCouponList(Boolean replaceCouponList) {
            params.addOpt("replace_coupon_list", replaceCouponList);
            return this;
        }


        public UpdateRequest prorate(Boolean prorate) {
            params.addOpt("prorate", prorate);
            return this;
        }


        public UpdateRequest endOfTerm(Boolean endOfTerm) {
            params.addOpt("end_of_term", endOfTerm);
            return this;
        }


        public UpdateRequest forceTermReset(Boolean forceTermReset) {
            params.addOpt("force_term_reset", forceTermReset);
            return this;
        }


        public UpdateRequest reactivate(Boolean reactivate) {
            params.addOpt("reactivate", reactivate);
            return this;
        }


        public UpdateRequest tokenId(String tokenId) {
            params.addOpt("token_id", tokenId);
            return this;
        }


        public UpdateRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        public UpdateRequest metaData(JSONObject metaData) {
            params.addOpt("meta_data", metaData);
            return this;
        }


        public UpdateRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }


        public UpdateRequest overrideRelationship(Boolean overrideRelationship) {
            params.addOpt("override_relationship", overrideRelationship);
            return this;
        }


        public UpdateRequest changesScheduledAt(Timestamp changesScheduledAt) {
            params.addOpt("changes_scheduled_at", changesScheduledAt);
            return this;
        }


        public UpdateRequest changeOption(com.chargebee.models.enums.ChangeOption changeOption) {
            params.addOpt("change_option", changeOption);
            return this;
        }


        public UpdateRequest contractTermBillingCycleOnRenewal(Integer contractTermBillingCycleOnRenewal) {
            params.addOpt("contract_term_billing_cycle_on_renewal", contractTermBillingCycleOnRenewal);
            return this;
        }






        public UpdateRequest freePeriod(Integer freePeriod) {
            params.addOpt("free_period", freePeriod);
            return this;
        }


        public UpdateRequest freePeriodUnit(com.chargebee.models.enums.FreePeriodUnit freePeriodUnit) {
            params.addOpt("free_period_unit", freePeriodUnit);
            return this;
        }




        public UpdateRequest trialEndAction(com.chargebee.models.enums.TrialEndAction trialEndAction) {
            params.addOpt("trial_end_action", trialEndAction);
            return this;
        }


        @Deprecated
        public UpdateRequest cardGateway(com.chargebee.models.enums.Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public UpdateRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
            return this;
        }

        @Deprecated
        public UpdateRequest cardTmpToken(String cardTmpToken) {
            params.addOpt("card[tmp_token]", cardTmpToken);
            return this;
        }

        public UpdateRequest paymentMethodType(com.chargebee.models.enums.Type paymentMethodType) {
            params.addOpt("payment_method[type]", paymentMethodType);
            return this;
        }

        @Deprecated
        public UpdateRequest paymentMethodGateway(com.chargebee.models.enums.Gateway paymentMethodGateway) {
            params.addOpt("payment_method[gateway]", paymentMethodGateway);
            return this;
        }

        public UpdateRequest paymentMethodGatewayAccountId(String paymentMethodGatewayAccountId) {
            params.addOpt("payment_method[gateway_account_id]", paymentMethodGatewayAccountId);
            return this;
        }

        public UpdateRequest paymentMethodReferenceId(String paymentMethodReferenceId) {
            params.addOpt("payment_method[reference_id]", paymentMethodReferenceId);
            return this;
        }

        public UpdateRequest paymentMethodTmpToken(String paymentMethodTmpToken) {
            params.addOpt("payment_method[tmp_token]", paymentMethodTmpToken);
            return this;
        }

        public UpdateRequest paymentMethodIssuingCountry(String paymentMethodIssuingCountry) {
            params.addOpt("payment_method[issuing_country]", paymentMethodIssuingCountry);
            return this;
        }

        public UpdateRequest paymentMethodAdditionalInformation(JSONObject paymentMethodAdditionalInformation) {
            params.addOpt("payment_method[additional_information]", paymentMethodAdditionalInformation);
            return this;
        }

        public UpdateRequest cardFirstName(String cardFirstName) {
            params.addOpt("card[first_name]", cardFirstName);
            return this;
        }

        public UpdateRequest cardLastName(String cardLastName) {
            params.addOpt("card[last_name]", cardLastName);
            return this;
        }

        public UpdateRequest cardNumber(String cardNumber) {
            params.addOpt("card[number]", cardNumber);
            return this;
        }

        public UpdateRequest cardExpiryMonth(Integer cardExpiryMonth) {
            params.addOpt("card[expiry_month]", cardExpiryMonth);
            return this;
        }

        public UpdateRequest cardExpiryYear(Integer cardExpiryYear) {
            params.addOpt("card[expiry_year]", cardExpiryYear);
            return this;
        }

        public UpdateRequest cardCvv(String cardCvv) {
            params.addOpt("card[cvv]", cardCvv);
            return this;
        }

        public UpdateRequest cardBillingAddr1(String cardBillingAddr1) {
            params.addOpt("card[billing_addr1]", cardBillingAddr1);
            return this;
        }

        public UpdateRequest cardBillingAddr2(String cardBillingAddr2) {
            params.addOpt("card[billing_addr2]", cardBillingAddr2);
            return this;
        }

        public UpdateRequest cardBillingCity(String cardBillingCity) {
            params.addOpt("card[billing_city]", cardBillingCity);
            return this;
        }

        public UpdateRequest cardBillingStateCode(String cardBillingStateCode) {
            params.addOpt("card[billing_state_code]", cardBillingStateCode);
            return this;
        }

        public UpdateRequest cardBillingState(String cardBillingState) {
            params.addOpt("card[billing_state]", cardBillingState);
            return this;
        }

        public UpdateRequest cardBillingZip(String cardBillingZip) {
            params.addOpt("card[billing_zip]", cardBillingZip);
            return this;
        }

        public UpdateRequest cardBillingCountry(String cardBillingCountry) {
            params.addOpt("card[billing_country]", cardBillingCountry);
            return this;
        }

        @Deprecated
        public UpdateRequest cardIpAddress(String cardIpAddress) {
            params.addOpt("card[ip_address]", cardIpAddress);
            return this;
        }

        public UpdateRequest cardAdditionalInformation(JSONObject cardAdditionalInformation) {
            params.addOpt("card[additional_information]", cardAdditionalInformation);
            return this;
        }

        public UpdateRequest paymentIntentId(String paymentIntentId) {
            params.addOpt("payment_intent[id]", paymentIntentId);
            return this;
        }

        public UpdateRequest paymentIntentGatewayAccountId(String paymentIntentGatewayAccountId) {
            params.addOpt("payment_intent[gateway_account_id]", paymentIntentGatewayAccountId);
            return this;
        }

        public UpdateRequest paymentIntentGwToken(String paymentIntentGwToken) {
            params.addOpt("payment_intent[gw_token]", paymentIntentGwToken);
            return this;
        }

        public UpdateRequest paymentIntentPaymentMethodType(PaymentIntent.PaymentMethodType paymentIntentPaymentMethodType) {
            params.addOpt("payment_intent[payment_method_type]", paymentIntentPaymentMethodType);
            return this;
        }

        public UpdateRequest paymentIntentReferenceId(String paymentIntentReferenceId) {
            params.addOpt("payment_intent[reference_id]", paymentIntentReferenceId);
            return this;
        }

        @Deprecated
        public UpdateRequest paymentIntentGwPaymentMethodId(String paymentIntentGwPaymentMethodId) {
            params.addOpt("payment_intent[gw_payment_method_id]", paymentIntentGwPaymentMethodId);
            return this;
        }

        public UpdateRequest paymentIntentAdditionalInformation(JSONObject paymentIntentAdditionalInformation) {
            params.addOpt("payment_intent[additional_information]", paymentIntentAdditionalInformation);
            return this;
        }

        public UpdateRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }

        public UpdateRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }

        public UpdateRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }

        public UpdateRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }

        public UpdateRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }

        public UpdateRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public UpdateRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public UpdateRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public UpdateRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public UpdateRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public UpdateRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }

        public UpdateRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public UpdateRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public UpdateRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public UpdateRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public UpdateRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public UpdateRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public UpdateRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public UpdateRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public UpdateRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public UpdateRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public UpdateRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public UpdateRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public UpdateRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public UpdateRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public UpdateRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public UpdateRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public UpdateRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public UpdateRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public UpdateRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
            return this;
        }

        public UpdateRequest customerEntityIdentifierScheme(String customerEntityIdentifierScheme) {
            params.addOpt("customer[entity_identifier_scheme]", customerEntityIdentifierScheme);
            return this;
        }

        public UpdateRequest customerIsEinvoiceEnabled(Boolean customerIsEinvoiceEnabled) {
            params.addOpt("customer[is_einvoice_enabled]", customerIsEinvoiceEnabled);
            return this;
        }

        public UpdateRequest customerEntityIdentifierStandard(String customerEntityIdentifierStandard) {
            params.addOpt("customer[entity_identifier_standard]", customerEntityIdentifierStandard);
            return this;
        }

        public UpdateRequest customerBusinessCustomerWithoutVatNumber(Boolean customerBusinessCustomerWithoutVatNumber) {
            params.addOpt("customer[business_customer_without_vat_number]", customerBusinessCustomerWithoutVatNumber);
            return this;
        }

        public UpdateRequest customerRegisteredForGst(Boolean customerRegisteredForGst) {
            params.addOpt("customer[registered_for_gst]", customerRegisteredForGst);
            return this;
        }

        public UpdateRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public UpdateRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public UpdateRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public UpdateRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public UpdateRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public UpdateRequest addonBillingCycles(int index, Integer addonBillingCycles) {
            params.addOpt("addons[billing_cycles][" + index + "]", addonBillingCycles);
            return this;
        }
        public UpdateRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public UpdateRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public UpdateRequest eventBasedAddonUnitPrice(int index, Long eventBasedAddonUnitPrice) {
            params.addOpt("event_based_addons[unit_price][" + index + "]", eventBasedAddonUnitPrice);
            return this;
        }
        public UpdateRequest eventBasedAddonServicePeriodInDays(int index, Integer eventBasedAddonServicePeriodInDays) {
            params.addOpt("event_based_addons[service_period_in_days][" + index + "]", eventBasedAddonServicePeriodInDays);
            return this;
        }
        public UpdateRequest eventBasedAddonChargeOn(int index, com.chargebee.models.enums.ChargeOn eventBasedAddonChargeOn) {
            params.addOpt("event_based_addons[charge_on][" + index + "]", eventBasedAddonChargeOn);
            return this;
        }
        public UpdateRequest eventBasedAddonOnEvent(int index, com.chargebee.models.enums.OnEvent eventBasedAddonOnEvent) {
            params.addOpt("event_based_addons[on_event][" + index + "]", eventBasedAddonOnEvent);
            return this;
        }
        public UpdateRequest eventBasedAddonChargeOnce(int index, Boolean eventBasedAddonChargeOnce) {
            params.addOpt("event_based_addons[charge_once][" + index + "]", eventBasedAddonChargeOnce);
            return this;
        }
        public UpdateRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public UpdateRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public UpdateRequest eventBasedAddonQuantityInDecimal(int index, String eventBasedAddonQuantityInDecimal) {
            params.addOpt("event_based_addons[quantity_in_decimal][" + index + "]", eventBasedAddonQuantityInDecimal);
            return this;
        }
        public UpdateRequest eventBasedAddonUnitPriceInDecimal(int index, String eventBasedAddonUnitPriceInDecimal) {
            params.addOpt("event_based_addons[unit_price_in_decimal][" + index + "]", eventBasedAddonUnitPriceInDecimal);
            return this;
        }
        public UpdateRequest addonTrialEnd(int index, Timestamp addonTrialEnd) {
            params.addOpt("addons[trial_end][" + index + "]", addonTrialEnd);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class UpdateForItemsRequest extends Request<UpdateForItemsRequest> {

        private UpdateForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public UpdateForItemsRequest mandatoryItemsToRemove(List<String> mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public UpdateForItemsRequest mandatoryItemsToRemove(String... mandatoryItemsToRemove) {
            params.addOpt("mandatory_items_to_remove", mandatoryItemsToRemove);
            return this;
        }

        public UpdateForItemsRequest replaceItemsList(Boolean replaceItemsList) {
            params.addOpt("replace_items_list", replaceItemsList);
            return this;
        }


        @Deprecated
        public UpdateForItemsRequest setupFee(Long setupFee) {
            params.addOpt("setup_fee", setupFee);
            return this;
        }


        public UpdateForItemsRequest netTermDays(Integer netTermDays) {
            params.addOpt("net_term_days", netTermDays);
            return this;
        }


        public UpdateForItemsRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public UpdateForItemsRequest startDate(Timestamp startDate) {
            params.addOpt("start_date", startDate);
            return this;
        }


        public UpdateForItemsRequest trialEnd(Timestamp trialEnd) {
            params.addOpt("trial_end", trialEnd);
            return this;
        }


        public UpdateForItemsRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        @Deprecated
        public UpdateForItemsRequest coupon(String coupon) {
            params.addOpt("coupon", coupon);
            return this;
        }


        public UpdateForItemsRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }




        public UpdateForItemsRequest reactivateFrom(Timestamp reactivateFrom) {
            params.addOpt("reactivate_from", reactivateFrom);
            return this;
        }




        public UpdateForItemsRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }


        public UpdateForItemsRequest autoCollection(com.chargebee.models.enums.AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        public UpdateForItemsRequest offlinePaymentMethod(com.chargebee.models.enums.OfflinePaymentMethod offlinePaymentMethod) {
            params.addOpt("offline_payment_method", offlinePaymentMethod);
            return this;
        }


        public UpdateForItemsRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public UpdateForItemsRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public UpdateForItemsRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public UpdateForItemsRequest replaceCouponList(Boolean replaceCouponList) {
            params.addOpt("replace_coupon_list", replaceCouponList);
            return this;
        }


        public UpdateForItemsRequest prorate(Boolean prorate) {
            params.addOpt("prorate", prorate);
            return this;
        }


        public UpdateForItemsRequest endOfTerm(Boolean endOfTerm) {
            params.addOpt("end_of_term", endOfTerm);
            return this;
        }


        public UpdateForItemsRequest forceTermReset(Boolean forceTermReset) {
            params.addOpt("force_term_reset", forceTermReset);
            return this;
        }


        public UpdateForItemsRequest reactivate(Boolean reactivate) {
            params.addOpt("reactivate", reactivate);
            return this;
        }


        public UpdateForItemsRequest tokenId(String tokenId) {
            params.addOpt("token_id", tokenId);
            return this;
        }


        public UpdateForItemsRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        public UpdateForItemsRequest metaData(JSONObject metaData) {
            params.addOpt("meta_data", metaData);
            return this;
        }


        public UpdateForItemsRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }


        public UpdateForItemsRequest overrideRelationship(Boolean overrideRelationship) {
            params.addOpt("override_relationship", overrideRelationship);
            return this;
        }


        public UpdateForItemsRequest changesScheduledAt(Timestamp changesScheduledAt) {
            params.addOpt("changes_scheduled_at", changesScheduledAt);
            return this;
        }


        public UpdateForItemsRequest changeOption(com.chargebee.models.enums.ChangeOption changeOption) {
            params.addOpt("change_option", changeOption);
            return this;
        }


        public UpdateForItemsRequest contractTermBillingCycleOnRenewal(Integer contractTermBillingCycleOnRenewal) {
            params.addOpt("contract_term_billing_cycle_on_renewal", contractTermBillingCycleOnRenewal);
            return this;
        }






        public UpdateForItemsRequest freePeriod(Integer freePeriod) {
            params.addOpt("free_period", freePeriod);
            return this;
        }


        public UpdateForItemsRequest freePeriodUnit(com.chargebee.models.enums.FreePeriodUnit freePeriodUnit) {
            params.addOpt("free_period_unit", freePeriodUnit);
            return this;
        }




        public UpdateForItemsRequest createPendingInvoices(Boolean createPendingInvoices) {
            params.addOpt("create_pending_invoices", createPendingInvoices);
            return this;
        }


        public UpdateForItemsRequest autoCloseInvoices(Boolean autoCloseInvoices) {
            params.addOpt("auto_close_invoices", autoCloseInvoices);
            return this;
        }


        public UpdateForItemsRequest trialEndAction(com.chargebee.models.enums.TrialEndAction trialEndAction) {
            params.addOpt("trial_end_action", trialEndAction);
            return this;
        }


        @Deprecated
        public UpdateForItemsRequest cardGateway(com.chargebee.models.enums.Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public UpdateForItemsRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
            return this;
        }

        @Deprecated
        public UpdateForItemsRequest cardTmpToken(String cardTmpToken) {
            params.addOpt("card[tmp_token]", cardTmpToken);
            return this;
        }

        public UpdateForItemsRequest paymentMethodType(com.chargebee.models.enums.Type paymentMethodType) {
            params.addOpt("payment_method[type]", paymentMethodType);
            return this;
        }

        @Deprecated
        public UpdateForItemsRequest paymentMethodGateway(com.chargebee.models.enums.Gateway paymentMethodGateway) {
            params.addOpt("payment_method[gateway]", paymentMethodGateway);
            return this;
        }

        public UpdateForItemsRequest paymentMethodGatewayAccountId(String paymentMethodGatewayAccountId) {
            params.addOpt("payment_method[gateway_account_id]", paymentMethodGatewayAccountId);
            return this;
        }

        public UpdateForItemsRequest paymentMethodReferenceId(String paymentMethodReferenceId) {
            params.addOpt("payment_method[reference_id]", paymentMethodReferenceId);
            return this;
        }

        public UpdateForItemsRequest paymentMethodTmpToken(String paymentMethodTmpToken) {
            params.addOpt("payment_method[tmp_token]", paymentMethodTmpToken);
            return this;
        }

        public UpdateForItemsRequest paymentMethodIssuingCountry(String paymentMethodIssuingCountry) {
            params.addOpt("payment_method[issuing_country]", paymentMethodIssuingCountry);
            return this;
        }

        public UpdateForItemsRequest paymentMethodAdditionalInformation(JSONObject paymentMethodAdditionalInformation) {
            params.addOpt("payment_method[additional_information]", paymentMethodAdditionalInformation);
            return this;
        }

        public UpdateForItemsRequest cardFirstName(String cardFirstName) {
            params.addOpt("card[first_name]", cardFirstName);
            return this;
        }

        public UpdateForItemsRequest cardLastName(String cardLastName) {
            params.addOpt("card[last_name]", cardLastName);
            return this;
        }

        public UpdateForItemsRequest cardNumber(String cardNumber) {
            params.addOpt("card[number]", cardNumber);
            return this;
        }

        public UpdateForItemsRequest cardExpiryMonth(Integer cardExpiryMonth) {
            params.addOpt("card[expiry_month]", cardExpiryMonth);
            return this;
        }

        public UpdateForItemsRequest cardExpiryYear(Integer cardExpiryYear) {
            params.addOpt("card[expiry_year]", cardExpiryYear);
            return this;
        }

        public UpdateForItemsRequest cardCvv(String cardCvv) {
            params.addOpt("card[cvv]", cardCvv);
            return this;
        }

        public UpdateForItemsRequest cardBillingAddr1(String cardBillingAddr1) {
            params.addOpt("card[billing_addr1]", cardBillingAddr1);
            return this;
        }

        public UpdateForItemsRequest cardBillingAddr2(String cardBillingAddr2) {
            params.addOpt("card[billing_addr2]", cardBillingAddr2);
            return this;
        }

        public UpdateForItemsRequest cardBillingCity(String cardBillingCity) {
            params.addOpt("card[billing_city]", cardBillingCity);
            return this;
        }

        public UpdateForItemsRequest cardBillingStateCode(String cardBillingStateCode) {
            params.addOpt("card[billing_state_code]", cardBillingStateCode);
            return this;
        }

        public UpdateForItemsRequest cardBillingState(String cardBillingState) {
            params.addOpt("card[billing_state]", cardBillingState);
            return this;
        }

        public UpdateForItemsRequest cardBillingZip(String cardBillingZip) {
            params.addOpt("card[billing_zip]", cardBillingZip);
            return this;
        }

        public UpdateForItemsRequest cardBillingCountry(String cardBillingCountry) {
            params.addOpt("card[billing_country]", cardBillingCountry);
            return this;
        }

        @Deprecated
        public UpdateForItemsRequest cardIpAddress(String cardIpAddress) {
            params.addOpt("card[ip_address]", cardIpAddress);
            return this;
        }

        public UpdateForItemsRequest cardAdditionalInformation(JSONObject cardAdditionalInformation) {
            params.addOpt("card[additional_information]", cardAdditionalInformation);
            return this;
        }

        public UpdateForItemsRequest paymentIntentId(String paymentIntentId) {
            params.addOpt("payment_intent[id]", paymentIntentId);
            return this;
        }

        public UpdateForItemsRequest paymentIntentGatewayAccountId(String paymentIntentGatewayAccountId) {
            params.addOpt("payment_intent[gateway_account_id]", paymentIntentGatewayAccountId);
            return this;
        }

        public UpdateForItemsRequest paymentIntentGwToken(String paymentIntentGwToken) {
            params.addOpt("payment_intent[gw_token]", paymentIntentGwToken);
            return this;
        }

        public UpdateForItemsRequest paymentIntentPaymentMethodType(PaymentIntent.PaymentMethodType paymentIntentPaymentMethodType) {
            params.addOpt("payment_intent[payment_method_type]", paymentIntentPaymentMethodType);
            return this;
        }

        public UpdateForItemsRequest paymentIntentReferenceId(String paymentIntentReferenceId) {
            params.addOpt("payment_intent[reference_id]", paymentIntentReferenceId);
            return this;
        }

        @Deprecated
        public UpdateForItemsRequest paymentIntentGwPaymentMethodId(String paymentIntentGwPaymentMethodId) {
            params.addOpt("payment_intent[gw_payment_method_id]", paymentIntentGwPaymentMethodId);
            return this;
        }

        public UpdateForItemsRequest paymentIntentAdditionalInformation(JSONObject paymentIntentAdditionalInformation) {
            params.addOpt("payment_intent[additional_information]", paymentIntentAdditionalInformation);
            return this;
        }

        public UpdateForItemsRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }

        public UpdateForItemsRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }

        public UpdateForItemsRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }

        public UpdateForItemsRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }

        public UpdateForItemsRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }

        public UpdateForItemsRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public UpdateForItemsRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public UpdateForItemsRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public UpdateForItemsRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public UpdateForItemsRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public UpdateForItemsRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }

        public UpdateForItemsRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public UpdateForItemsRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public UpdateForItemsRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public UpdateForItemsRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public UpdateForItemsRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public UpdateForItemsRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public UpdateForItemsRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public UpdateForItemsRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public UpdateForItemsRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public UpdateForItemsRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public UpdateForItemsRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public UpdateForItemsRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public UpdateForItemsRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public UpdateForItemsRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public UpdateForItemsRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public UpdateForItemsRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public UpdateForItemsRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public UpdateForItemsRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public UpdateForItemsRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
            return this;
        }

        public UpdateForItemsRequest customerEntityIdentifierScheme(String customerEntityIdentifierScheme) {
            params.addOpt("customer[entity_identifier_scheme]", customerEntityIdentifierScheme);
            return this;
        }

        public UpdateForItemsRequest customerIsEinvoiceEnabled(Boolean customerIsEinvoiceEnabled) {
            params.addOpt("customer[is_einvoice_enabled]", customerIsEinvoiceEnabled);
            return this;
        }

        public UpdateForItemsRequest customerEntityIdentifierStandard(String customerEntityIdentifierStandard) {
            params.addOpt("customer[entity_identifier_standard]", customerEntityIdentifierStandard);
            return this;
        }

        public UpdateForItemsRequest customerBusinessCustomerWithoutVatNumber(Boolean customerBusinessCustomerWithoutVatNumber) {
            params.addOpt("customer[business_customer_without_vat_number]", customerBusinessCustomerWithoutVatNumber);
            return this;
        }

        public UpdateForItemsRequest customerRegisteredForGst(Boolean customerRegisteredForGst) {
            params.addOpt("customer[registered_for_gst]", customerRegisteredForGst);
            return this;
        }

        public UpdateForItemsRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public UpdateForItemsRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public UpdateForItemsRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.add("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public UpdateForItemsRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public UpdateForItemsRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        public UpdateForItemsRequest subscriptionItemUnitPrice(int index, Long subscriptionItemUnitPrice) {
            params.addOpt("subscription_items[unit_price][" + index + "]", subscriptionItemUnitPrice);
            return this;
        }
        public UpdateForItemsRequest subscriptionItemUnitPriceInDecimal(int index, String subscriptionItemUnitPriceInDecimal) {
            params.addOpt("subscription_items[unit_price_in_decimal][" + index + "]", subscriptionItemUnitPriceInDecimal);
            return this;
        }
        public UpdateForItemsRequest subscriptionItemBillingCycles(int index, Integer subscriptionItemBillingCycles) {
            params.addOpt("subscription_items[billing_cycles][" + index + "]", subscriptionItemBillingCycles);
            return this;
        }
        public UpdateForItemsRequest subscriptionItemTrialEnd(int index, Timestamp subscriptionItemTrialEnd) {
            params.addOpt("subscription_items[trial_end][" + index + "]", subscriptionItemTrialEnd);
            return this;
        }
        public UpdateForItemsRequest subscriptionItemServicePeriodDays(int index, Integer subscriptionItemServicePeriodDays) {
            params.addOpt("subscription_items[service_period_days][" + index + "]", subscriptionItemServicePeriodDays);
            return this;
        }
        public UpdateForItemsRequest subscriptionItemChargeOnEvent(int index, com.chargebee.models.enums.ChargeOnEvent subscriptionItemChargeOnEvent) {
            params.addOpt("subscription_items[charge_on_event][" + index + "]", subscriptionItemChargeOnEvent);
            return this;
        }
        public UpdateForItemsRequest subscriptionItemChargeOnce(int index, Boolean subscriptionItemChargeOnce) {
            params.addOpt("subscription_items[charge_once][" + index + "]", subscriptionItemChargeOnce);
            return this;
        }
        public UpdateForItemsRequest subscriptionItemChargeOnOption(int index, com.chargebee.models.enums.ChargeOnOption subscriptionItemChargeOnOption) {
            params.addOpt("subscription_items[charge_on_option][" + index + "]", subscriptionItemChargeOnOption);
            return this;
        }
        @Deprecated
        public UpdateForItemsRequest subscriptionItemItemType(int index, com.chargebee.models.enums.ItemType subscriptionItemItemType) {
            params.addOpt("subscription_items[item_type][" + index + "]", subscriptionItemItemType);
            return this;
        }
        public UpdateForItemsRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.add("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public UpdateForItemsRequest discountDurationType(int index, com.chargebee.models.enums.DurationType discountDurationType) {
            params.add("discounts[duration_type][" + index + "]", discountDurationType);
            return this;
        }
        public UpdateForItemsRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public UpdateForItemsRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public UpdateForItemsRequest discountPeriod(int index, Integer discountPeriod) {
            params.addOpt("discounts[period][" + index + "]", discountPeriod);
            return this;
        }
        public UpdateForItemsRequest discountPeriodUnit(int index, com.chargebee.models.enums.PeriodUnit discountPeriodUnit) {
            params.addOpt("discounts[period_unit][" + index + "]", discountPeriodUnit);
            return this;
        }
        public UpdateForItemsRequest discountIncludedInMrr(int index, Boolean discountIncludedInMrr) {
            params.addOpt("discounts[included_in_mrr][" + index + "]", discountIncludedInMrr);
            return this;
        }
        public UpdateForItemsRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        public UpdateForItemsRequest discountOperationType(int index, com.chargebee.models.enums.OperationType discountOperationType) {
            params.add("discounts[operation_type][" + index + "]", discountOperationType);
            return this;
        }
        public UpdateForItemsRequest discountId(int index, String discountId) {
            params.addOpt("discounts[id][" + index + "]", discountId);
            return this;
        }
        public UpdateForItemsRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public UpdateForItemsRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public UpdateForItemsRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public UpdateForItemsRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public UpdateForItemsRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public UpdateForItemsRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public UpdateForItemsRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class ChangeTermEndRequest extends Request<ChangeTermEndRequest> {

        private ChangeTermEndRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ChangeTermEndRequest termEndsAt(Timestamp termEndsAt) {
            params.add("term_ends_at", termEndsAt);
            return this;
        }


        public ChangeTermEndRequest prorate(Boolean prorate) {
            params.addOpt("prorate", prorate);
            return this;
        }


        public ChangeTermEndRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }




        @Override
        public Params params() {
            return params;
        }
    }

    public static class ReactivateRequest extends Request<ReactivateRequest> {

        private ReactivateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ReactivateRequest trialEnd(Timestamp trialEnd) {
            params.addOpt("trial_end", trialEnd);
            return this;
        }


        public ReactivateRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }




        @Deprecated
        public ReactivateRequest trialPeriodDays(Integer trialPeriodDays) {
            params.addOpt("trial_period_days", trialPeriodDays);
            return this;
        }


        public ReactivateRequest reactivateFrom(Timestamp reactivateFrom) {
            params.addOpt("reactivate_from", reactivateFrom);
            return this;
        }


        public ReactivateRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }






        public ReactivateRequest billingAlignmentMode(com.chargebee.models.enums.BillingAlignmentMode billingAlignmentMode) {
            params.addOpt("billing_alignment_mode", billingAlignmentMode);
            return this;
        }


        public ReactivateRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }


        public ReactivateRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public ReactivateRequest contractTermBillingCycleOnRenewal(Integer contractTermBillingCycleOnRenewal) {
            params.addOpt("contract_term_billing_cycle_on_renewal", contractTermBillingCycleOnRenewal);
            return this;
        }


        public ReactivateRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public ReactivateRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public ReactivateRequest paymentIntentId(String paymentIntentId) {
            params.addOpt("payment_intent[id]", paymentIntentId);
            return this;
        }

        public ReactivateRequest paymentIntentGatewayAccountId(String paymentIntentGatewayAccountId) {
            params.addOpt("payment_intent[gateway_account_id]", paymentIntentGatewayAccountId);
            return this;
        }

        public ReactivateRequest paymentIntentGwToken(String paymentIntentGwToken) {
            params.addOpt("payment_intent[gw_token]", paymentIntentGwToken);
            return this;
        }

        public ReactivateRequest paymentIntentPaymentMethodType(PaymentIntent.PaymentMethodType paymentIntentPaymentMethodType) {
            params.addOpt("payment_intent[payment_method_type]", paymentIntentPaymentMethodType);
            return this;
        }

        public ReactivateRequest paymentIntentReferenceId(String paymentIntentReferenceId) {
            params.addOpt("payment_intent[reference_id]", paymentIntentReferenceId);
            return this;
        }

        @Deprecated
        public ReactivateRequest paymentIntentGwPaymentMethodId(String paymentIntentGwPaymentMethodId) {
            params.addOpt("payment_intent[gw_payment_method_id]", paymentIntentGwPaymentMethodId);
            return this;
        }

        public ReactivateRequest paymentIntentAdditionalInformation(JSONObject paymentIntentAdditionalInformation) {
            params.addOpt("payment_intent[additional_information]", paymentIntentAdditionalInformation);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class AddChargeAtTermEndRequest extends Request<AddChargeAtTermEndRequest> {

        private AddChargeAtTermEndRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public AddChargeAtTermEndRequest amount(Long amount) {
            params.addOpt("amount", amount);
            return this;
        }


        public AddChargeAtTermEndRequest description(String description) {
            params.add("description", description);
            return this;
        }


        public AddChargeAtTermEndRequest amountInDecimal(String amountInDecimal) {
            params.addOpt("amount_in_decimal", amountInDecimal);
            return this;
        }


        public AddChargeAtTermEndRequest avalaraSaleType(com.chargebee.models.enums.AvalaraSaleType avalaraSaleType) {
            params.addOpt("avalara_sale_type", avalaraSaleType);
            return this;
        }


        public AddChargeAtTermEndRequest avalaraTransactionType(Integer avalaraTransactionType) {
            params.addOpt("avalara_transaction_type", avalaraTransactionType);
            return this;
        }


        public AddChargeAtTermEndRequest avalaraServiceType(Integer avalaraServiceType) {
            params.addOpt("avalara_service_type", avalaraServiceType);
            return this;
        }


        public AddChargeAtTermEndRequest dateFrom(Timestamp dateFrom) {
            params.addOpt("date_from", dateFrom);
            return this;
        }


        public AddChargeAtTermEndRequest dateTo(Timestamp dateTo) {
            params.addOpt("date_to", dateTo);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class ChargeAddonAtTermEndRequest extends Request<ChargeAddonAtTermEndRequest> {

        private ChargeAddonAtTermEndRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ChargeAddonAtTermEndRequest addonId(String addonId) {
            params.add("addon_id", addonId);
            return this;
        }


        public ChargeAddonAtTermEndRequest addonQuantity(Integer addonQuantity) {
            params.addOpt("addon_quantity", addonQuantity);
            return this;
        }


        public ChargeAddonAtTermEndRequest addonUnitPrice(Long addonUnitPrice) {
            params.addOpt("addon_unit_price", addonUnitPrice);
            return this;
        }


        public ChargeAddonAtTermEndRequest addonQuantityInDecimal(String addonQuantityInDecimal) {
            params.addOpt("addon_quantity_in_decimal", addonQuantityInDecimal);
            return this;
        }


        public ChargeAddonAtTermEndRequest addonUnitPriceInDecimal(String addonUnitPriceInDecimal) {
            params.addOpt("addon_unit_price_in_decimal", addonUnitPriceInDecimal);
            return this;
        }


        public ChargeAddonAtTermEndRequest dateFrom(Timestamp dateFrom) {
            params.addOpt("date_from", dateFrom);
            return this;
        }


        public ChargeAddonAtTermEndRequest dateTo(Timestamp dateTo) {
            params.addOpt("date_to", dateTo);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class ChargeFutureRenewalsRequest extends Request<ChargeFutureRenewalsRequest> {

        private ChargeFutureRenewalsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ChargeFutureRenewalsRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }


        public ChargeFutureRenewalsRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }


        public ChargeFutureRenewalsRequest scheduleType(com.chargebee.models.enums.ScheduleType scheduleType) {
            params.addOpt("schedule_type", scheduleType);
            return this;
        }


        public ChargeFutureRenewalsRequest fixedIntervalScheduleNumberOfOccurrences(Integer fixedIntervalScheduleNumberOfOccurrences) {
            params.addOpt("fixed_interval_schedule[number_of_occurrences]", fixedIntervalScheduleNumberOfOccurrences);
            return this;
        }

        public ChargeFutureRenewalsRequest fixedIntervalScheduleDaysBeforeRenewal(Integer fixedIntervalScheduleDaysBeforeRenewal) {
            params.addOpt("fixed_interval_schedule[days_before_renewal]", fixedIntervalScheduleDaysBeforeRenewal);
            return this;
        }

        public ChargeFutureRenewalsRequest fixedIntervalScheduleEndScheduleOn(com.chargebee.models.enums.EndScheduleOn fixedIntervalScheduleEndScheduleOn) {
            params.addOpt("fixed_interval_schedule[end_schedule_on]", fixedIntervalScheduleEndScheduleOn);
            return this;
        }

        public ChargeFutureRenewalsRequest fixedIntervalScheduleEndDate(Timestamp fixedIntervalScheduleEndDate) {
            params.addOpt("fixed_interval_schedule[end_date]", fixedIntervalScheduleEndDate);
            return this;
        }

        public ChargeFutureRenewalsRequest specificDatesScheduleTermsToCharge(int index, Integer specificDatesScheduleTermsToCharge) {
            params.addOpt("specific_dates_schedule[terms_to_charge][" + index + "]", specificDatesScheduleTermsToCharge);
            return this;
        }
        public ChargeFutureRenewalsRequest specificDatesScheduleDate(int index, Timestamp specificDatesScheduleDate) {
            params.addOpt("specific_dates_schedule[date][" + index + "]", specificDatesScheduleDate);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class EditAdvanceInvoiceScheduleRequest extends Request<EditAdvanceInvoiceScheduleRequest> {

        private EditAdvanceInvoiceScheduleRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public EditAdvanceInvoiceScheduleRequest termsToCharge(Integer termsToCharge) {
            params.addOpt("terms_to_charge", termsToCharge);
            return this;
        }


        public EditAdvanceInvoiceScheduleRequest scheduleType(com.chargebee.models.enums.ScheduleType scheduleType) {
            params.addOpt("schedule_type", scheduleType);
            return this;
        }


        public EditAdvanceInvoiceScheduleRequest fixedIntervalScheduleNumberOfOccurrences(Integer fixedIntervalScheduleNumberOfOccurrences) {
            params.addOpt("fixed_interval_schedule[number_of_occurrences]", fixedIntervalScheduleNumberOfOccurrences);
            return this;
        }

        public EditAdvanceInvoiceScheduleRequest fixedIntervalScheduleDaysBeforeRenewal(Integer fixedIntervalScheduleDaysBeforeRenewal) {
            params.addOpt("fixed_interval_schedule[days_before_renewal]", fixedIntervalScheduleDaysBeforeRenewal);
            return this;
        }

        public EditAdvanceInvoiceScheduleRequest fixedIntervalScheduleEndScheduleOn(com.chargebee.models.enums.EndScheduleOn fixedIntervalScheduleEndScheduleOn) {
            params.addOpt("fixed_interval_schedule[end_schedule_on]", fixedIntervalScheduleEndScheduleOn);
            return this;
        }

        public EditAdvanceInvoiceScheduleRequest fixedIntervalScheduleEndDate(Timestamp fixedIntervalScheduleEndDate) {
            params.addOpt("fixed_interval_schedule[end_date]", fixedIntervalScheduleEndDate);
            return this;
        }

        public EditAdvanceInvoiceScheduleRequest specificDatesScheduleId(int index, String specificDatesScheduleId) {
            params.addOpt("specific_dates_schedule[id][" + index + "]", specificDatesScheduleId);
            return this;
        }
        public EditAdvanceInvoiceScheduleRequest specificDatesScheduleTermsToCharge(int index, Integer specificDatesScheduleTermsToCharge) {
            params.addOpt("specific_dates_schedule[terms_to_charge][" + index + "]", specificDatesScheduleTermsToCharge);
            return this;
        }
        public EditAdvanceInvoiceScheduleRequest specificDatesScheduleDate(int index, Timestamp specificDatesScheduleDate) {
            params.addOpt("specific_dates_schedule[date][" + index + "]", specificDatesScheduleDate);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class RemoveAdvanceInvoiceScheduleRequest extends Request<RemoveAdvanceInvoiceScheduleRequest> {

        private RemoveAdvanceInvoiceScheduleRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RemoveAdvanceInvoiceScheduleRequest specificDatesScheduleId(int index, String specificDatesScheduleId) {
            params.addOpt("specific_dates_schedule[id][" + index + "]", specificDatesScheduleId);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class RegenerateInvoiceRequest extends Request<RegenerateInvoiceRequest> {

        private RegenerateInvoiceRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public RegenerateInvoiceRequest dateFrom(Timestamp dateFrom) {
            params.addOpt("date_from", dateFrom);
            return this;
        }


        public RegenerateInvoiceRequest dateTo(Timestamp dateTo) {
            params.addOpt("date_to", dateTo);
            return this;
        }


        public RegenerateInvoiceRequest prorate(Boolean prorate) {
            params.addOpt("prorate", prorate);
            return this;
        }


        public RegenerateInvoiceRequest invoiceImmediately(Boolean invoiceImmediately) {
            params.addOpt("invoice_immediately", invoiceImmediately);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class ImportSubscriptionRequest extends Request<ImportSubscriptionRequest> {

        private ImportSubscriptionRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ImportSubscriptionRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }


        public ImportSubscriptionRequest clientProfileId(String clientProfileId) {
            params.addOpt("client_profile_id", clientProfileId);
            return this;
        }


        public ImportSubscriptionRequest planId(String planId) {
            params.add("plan_id", planId);
            return this;
        }


        public ImportSubscriptionRequest planQuantity(Integer planQuantity) {
            params.addOpt("plan_quantity", planQuantity);
            return this;
        }


        public ImportSubscriptionRequest planQuantityInDecimal(String planQuantityInDecimal) {
            params.addOpt("plan_quantity_in_decimal", planQuantityInDecimal);
            return this;
        }


        public ImportSubscriptionRequest planUnitPrice(Long planUnitPrice) {
            params.addOpt("plan_unit_price", planUnitPrice);
            return this;
        }


        public ImportSubscriptionRequest planUnitPriceInDecimal(String planUnitPriceInDecimal) {
            params.addOpt("plan_unit_price_in_decimal", planUnitPriceInDecimal);
            return this;
        }


        public ImportSubscriptionRequest setupFee(Long setupFee) {
            params.addOpt("setup_fee", setupFee);
            return this;
        }


        public ImportSubscriptionRequest trialEnd(Timestamp trialEnd) {
            params.addOpt("trial_end", trialEnd);
            return this;
        }


        public ImportSubscriptionRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public ImportSubscriptionRequest startDate(Timestamp startDate) {
            params.addOpt("start_date", startDate);
            return this;
        }




        public ImportSubscriptionRequest autoCollection(com.chargebee.models.enums.AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        public ImportSubscriptionRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public ImportSubscriptionRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public ImportSubscriptionRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public ImportSubscriptionRequest contractTermBillingCycleOnRenewal(Integer contractTermBillingCycleOnRenewal) {
            params.addOpt("contract_term_billing_cycle_on_renewal", contractTermBillingCycleOnRenewal);
            return this;
        }


        public ImportSubscriptionRequest status(Subscription.Status status) {
            params.add("status", status);
            return this;
        }


        public ImportSubscriptionRequest currentTermEnd(Timestamp currentTermEnd) {
            params.addOpt("current_term_end", currentTermEnd);
            return this;
        }


        public ImportSubscriptionRequest currentTermStart(Timestamp currentTermStart) {
            params.addOpt("current_term_start", currentTermStart);
            return this;
        }


        public ImportSubscriptionRequest trialStart(Timestamp trialStart) {
            params.addOpt("trial_start", trialStart);
            return this;
        }


        public ImportSubscriptionRequest cancelledAt(Timestamp cancelledAt) {
            params.addOpt("cancelled_at", cancelledAt);
            return this;
        }


        public ImportSubscriptionRequest startedAt(Timestamp startedAt) {
            params.addOpt("started_at", startedAt);
            return this;
        }


        public ImportSubscriptionRequest activatedAt(Timestamp activatedAt) {
            params.addOpt("activated_at", activatedAt);
            return this;
        }


        public ImportSubscriptionRequest pauseDate(Timestamp pauseDate) {
            params.addOpt("pause_date", pauseDate);
            return this;
        }


        public ImportSubscriptionRequest resumeDate(Timestamp resumeDate) {
            params.addOpt("resume_date", resumeDate);
            return this;
        }






        public ImportSubscriptionRequest createCurrentTermInvoice(Boolean createCurrentTermInvoice) {
            params.addOpt("create_current_term_invoice", createCurrentTermInvoice);
            return this;
        }




        public ImportSubscriptionRequest affiliateToken(String affiliateToken) {
            params.addOpt("affiliate_token", affiliateToken);
            return this;
        }




        public ImportSubscriptionRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        public ImportSubscriptionRequest metaData(JSONObject metaData) {
            params.addOpt("meta_data", metaData);
            return this;
        }


        public ImportSubscriptionRequest customerId(String customerId) {
            params.addOpt("customer[id]", customerId);
            return this;
        }

        public ImportSubscriptionRequest customerEmail(String customerEmail) {
            params.addOpt("customer[email]", customerEmail);
            return this;
        }

        public ImportSubscriptionRequest customerFirstName(String customerFirstName) {
            params.addOpt("customer[first_name]", customerFirstName);
            return this;
        }

        public ImportSubscriptionRequest customerLastName(String customerLastName) {
            params.addOpt("customer[last_name]", customerLastName);
            return this;
        }

        public ImportSubscriptionRequest customerCompany(String customerCompany) {
            params.addOpt("customer[company]", customerCompany);
            return this;
        }

        public ImportSubscriptionRequest customerTaxability(com.chargebee.models.enums.Taxability customerTaxability) {
            params.addOpt("customer[taxability]", customerTaxability);
            return this;
        }

        public ImportSubscriptionRequest customerLocale(String customerLocale) {
            params.addOpt("customer[locale]", customerLocale);
            return this;
        }

        public ImportSubscriptionRequest customerEntityCode(com.chargebee.models.enums.EntityCode customerEntityCode) {
            params.addOpt("customer[entity_code]", customerEntityCode);
            return this;
        }

        public ImportSubscriptionRequest customerExemptNumber(String customerExemptNumber) {
            params.addOpt("customer[exempt_number]", customerExemptNumber);
            return this;
        }

        public ImportSubscriptionRequest customerNetTermDays(Integer customerNetTermDays) {
            params.addOpt("customer[net_term_days]", customerNetTermDays);
            return this;
        }

        public ImportSubscriptionRequest customerTaxjarExemptionCategory(com.chargebee.models.enums.TaxjarExemptionCategory customerTaxjarExemptionCategory) {
            params.addOpt("customer[taxjar_exemption_category]", customerTaxjarExemptionCategory);
            return this;
        }

        public ImportSubscriptionRequest customerPhone(String customerPhone) {
            params.addOpt("customer[phone]", customerPhone);
            return this;
        }

        public ImportSubscriptionRequest customerCustomerType(com.chargebee.models.enums.CustomerType customerCustomerType) {
            params.addOpt("customer[customer_type]", customerCustomerType);
            return this;
        }

        public ImportSubscriptionRequest customerAutoCollection(com.chargebee.models.enums.AutoCollection customerAutoCollection) {
            params.addOpt("customer[auto_collection]", customerAutoCollection);
            return this;
        }

        public ImportSubscriptionRequest customerAllowDirectDebit(Boolean customerAllowDirectDebit) {
            params.addOpt("customer[allow_direct_debit]", customerAllowDirectDebit);
            return this;
        }

        public ImportSubscriptionRequest contractTermId(String contractTermId) {
            params.addOpt("contract_term[id]", contractTermId);
            return this;
        }

        public ImportSubscriptionRequest contractTermCreatedAt(Timestamp contractTermCreatedAt) {
            params.addOpt("contract_term[created_at]", contractTermCreatedAt);
            return this;
        }

        public ImportSubscriptionRequest contractTermContractStart(Timestamp contractTermContractStart) {
            params.addOpt("contract_term[contract_start]", contractTermContractStart);
            return this;
        }

        public ImportSubscriptionRequest contractTermBillingCycle(Integer contractTermBillingCycle) {
            params.addOpt("contract_term[billing_cycle]", contractTermBillingCycle);
            return this;
        }

        public ImportSubscriptionRequest contractTermTotalAmountRaised(Long contractTermTotalAmountRaised) {
            params.addOpt("contract_term[total_amount_raised]", contractTermTotalAmountRaised);
            return this;
        }

        public ImportSubscriptionRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public ImportSubscriptionRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        @Deprecated
        public ImportSubscriptionRequest cardGateway(com.chargebee.models.enums.Gateway cardGateway) {
            params.addOpt("card[gateway]", cardGateway);
            return this;
        }

        public ImportSubscriptionRequest cardGatewayAccountId(String cardGatewayAccountId) {
            params.addOpt("card[gateway_account_id]", cardGatewayAccountId);
            return this;
        }

        @Deprecated
        public ImportSubscriptionRequest cardTmpToken(String cardTmpToken) {
            params.addOpt("card[tmp_token]", cardTmpToken);
            return this;
        }

        public ImportSubscriptionRequest paymentMethodType(com.chargebee.models.enums.Type paymentMethodType) {
            params.addOpt("payment_method[type]", paymentMethodType);
            return this;
        }

        @Deprecated
        public ImportSubscriptionRequest paymentMethodGateway(com.chargebee.models.enums.Gateway paymentMethodGateway) {
            params.addOpt("payment_method[gateway]", paymentMethodGateway);
            return this;
        }

        public ImportSubscriptionRequest paymentMethodGatewayAccountId(String paymentMethodGatewayAccountId) {
            params.addOpt("payment_method[gateway_account_id]", paymentMethodGatewayAccountId);
            return this;
        }

        public ImportSubscriptionRequest paymentMethodReferenceId(String paymentMethodReferenceId) {
            params.addOpt("payment_method[reference_id]", paymentMethodReferenceId);
            return this;
        }

        public ImportSubscriptionRequest paymentMethodIssuingCountry(String paymentMethodIssuingCountry) {
            params.addOpt("payment_method[issuing_country]", paymentMethodIssuingCountry);
            return this;
        }

        public ImportSubscriptionRequest paymentMethodAdditionalInformation(JSONObject paymentMethodAdditionalInformation) {
            params.addOpt("payment_method[additional_information]", paymentMethodAdditionalInformation);
            return this;
        }

        public ImportSubscriptionRequest cardFirstName(String cardFirstName) {
            params.addOpt("card[first_name]", cardFirstName);
            return this;
        }

        public ImportSubscriptionRequest cardLastName(String cardLastName) {
            params.addOpt("card[last_name]", cardLastName);
            return this;
        }

        public ImportSubscriptionRequest cardNumber(String cardNumber) {
            params.addOpt("card[number]", cardNumber);
            return this;
        }

        public ImportSubscriptionRequest cardExpiryMonth(Integer cardExpiryMonth) {
            params.addOpt("card[expiry_month]", cardExpiryMonth);
            return this;
        }

        public ImportSubscriptionRequest cardExpiryYear(Integer cardExpiryYear) {
            params.addOpt("card[expiry_year]", cardExpiryYear);
            return this;
        }

        public ImportSubscriptionRequest cardCvv(String cardCvv) {
            params.addOpt("card[cvv]", cardCvv);
            return this;
        }

        public ImportSubscriptionRequest cardBillingAddr1(String cardBillingAddr1) {
            params.addOpt("card[billing_addr1]", cardBillingAddr1);
            return this;
        }

        public ImportSubscriptionRequest cardBillingAddr2(String cardBillingAddr2) {
            params.addOpt("card[billing_addr2]", cardBillingAddr2);
            return this;
        }

        public ImportSubscriptionRequest cardBillingCity(String cardBillingCity) {
            params.addOpt("card[billing_city]", cardBillingCity);
            return this;
        }

        public ImportSubscriptionRequest cardBillingStateCode(String cardBillingStateCode) {
            params.addOpt("card[billing_state_code]", cardBillingStateCode);
            return this;
        }

        public ImportSubscriptionRequest cardBillingState(String cardBillingState) {
            params.addOpt("card[billing_state]", cardBillingState);
            return this;
        }

        public ImportSubscriptionRequest cardBillingZip(String cardBillingZip) {
            params.addOpt("card[billing_zip]", cardBillingZip);
            return this;
        }

        public ImportSubscriptionRequest cardBillingCountry(String cardBillingCountry) {
            params.addOpt("card[billing_country]", cardBillingCountry);
            return this;
        }

        public ImportSubscriptionRequest cardAdditionalInformation(JSONObject cardAdditionalInformation) {
            params.addOpt("card[additional_information]", cardAdditionalInformation);
            return this;
        }

        public ImportSubscriptionRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }

        public ImportSubscriptionRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }

        public ImportSubscriptionRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }

        public ImportSubscriptionRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }

        public ImportSubscriptionRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }

        public ImportSubscriptionRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public ImportSubscriptionRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public ImportSubscriptionRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public ImportSubscriptionRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public ImportSubscriptionRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public ImportSubscriptionRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }

        public ImportSubscriptionRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public ImportSubscriptionRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public ImportSubscriptionRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public ImportSubscriptionRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public ImportSubscriptionRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public ImportSubscriptionRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public ImportSubscriptionRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public ImportSubscriptionRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public ImportSubscriptionRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public ImportSubscriptionRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public ImportSubscriptionRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public ImportSubscriptionRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public ImportSubscriptionRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public ImportSubscriptionRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public ImportSubscriptionRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public ImportSubscriptionRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public ImportSubscriptionRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public ImportSubscriptionRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public ImportSubscriptionRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
            return this;
        }

        public ImportSubscriptionRequest transactionAmount(Long transactionAmount) {
            params.addOpt("transaction[amount]", transactionAmount);
            return this;
        }

        public ImportSubscriptionRequest transactionPaymentMethod(com.chargebee.models.enums.PaymentMethod transactionPaymentMethod) {
            params.addOpt("transaction[payment_method]", transactionPaymentMethod);
            return this;
        }

        public ImportSubscriptionRequest transactionReferenceNumber(String transactionReferenceNumber) {
            params.addOpt("transaction[reference_number]", transactionReferenceNumber);
            return this;
        }

        public ImportSubscriptionRequest transactionDate(Timestamp transactionDate) {
            params.addOpt("transaction[date]", transactionDate);
            return this;
        }

        public ImportSubscriptionRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public ImportSubscriptionRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public ImportSubscriptionRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public ImportSubscriptionRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public ImportSubscriptionRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public ImportSubscriptionRequest addonBillingCycles(int index, Integer addonBillingCycles) {
            params.addOpt("addons[billing_cycles][" + index + "]", addonBillingCycles);
            return this;
        }
        public ImportSubscriptionRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public ImportSubscriptionRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public ImportSubscriptionRequest eventBasedAddonUnitPrice(int index, Long eventBasedAddonUnitPrice) {
            params.addOpt("event_based_addons[unit_price][" + index + "]", eventBasedAddonUnitPrice);
            return this;
        }
        public ImportSubscriptionRequest eventBasedAddonQuantityInDecimal(int index, String eventBasedAddonQuantityInDecimal) {
            params.addOpt("event_based_addons[quantity_in_decimal][" + index + "]", eventBasedAddonQuantityInDecimal);
            return this;
        }
        public ImportSubscriptionRequest eventBasedAddonUnitPriceInDecimal(int index, String eventBasedAddonUnitPriceInDecimal) {
            params.addOpt("event_based_addons[unit_price_in_decimal][" + index + "]", eventBasedAddonUnitPriceInDecimal);
            return this;
        }
        public ImportSubscriptionRequest eventBasedAddonServicePeriodInDays(int index, Integer eventBasedAddonServicePeriodInDays) {
            params.addOpt("event_based_addons[service_period_in_days][" + index + "]", eventBasedAddonServicePeriodInDays);
            return this;
        }
        public ImportSubscriptionRequest eventBasedAddonOnEvent(int index, com.chargebee.models.enums.OnEvent eventBasedAddonOnEvent) {
            params.addOpt("event_based_addons[on_event][" + index + "]", eventBasedAddonOnEvent);
            return this;
        }
        public ImportSubscriptionRequest eventBasedAddonChargeOnce(int index, Boolean eventBasedAddonChargeOnce) {
            params.addOpt("event_based_addons[charge_once][" + index + "]", eventBasedAddonChargeOnce);
            return this;
        }
        public ImportSubscriptionRequest chargedEventBasedAddonId(int index, String chargedEventBasedAddonId) {
            params.addOpt("charged_event_based_addons[id][" + index + "]", chargedEventBasedAddonId);
            return this;
        }
        public ImportSubscriptionRequest chargedEventBasedAddonLastChargedAt(int index, Timestamp chargedEventBasedAddonLastChargedAt) {
            params.addOpt("charged_event_based_addons[last_charged_at][" + index + "]", chargedEventBasedAddonLastChargedAt);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class ImportForCustomerRequest extends Request<ImportForCustomerRequest> {

        private ImportForCustomerRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ImportForCustomerRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }


        public ImportForCustomerRequest planId(String planId) {
            params.add("plan_id", planId);
            return this;
        }


        public ImportForCustomerRequest planQuantity(Integer planQuantity) {
            params.addOpt("plan_quantity", planQuantity);
            return this;
        }


        public ImportForCustomerRequest planQuantityInDecimal(String planQuantityInDecimal) {
            params.addOpt("plan_quantity_in_decimal", planQuantityInDecimal);
            return this;
        }


        public ImportForCustomerRequest planUnitPrice(Long planUnitPrice) {
            params.addOpt("plan_unit_price", planUnitPrice);
            return this;
        }


        public ImportForCustomerRequest planUnitPriceInDecimal(String planUnitPriceInDecimal) {
            params.addOpt("plan_unit_price_in_decimal", planUnitPriceInDecimal);
            return this;
        }


        public ImportForCustomerRequest setupFee(Long setupFee) {
            params.addOpt("setup_fee", setupFee);
            return this;
        }


        public ImportForCustomerRequest trialEnd(Timestamp trialEnd) {
            params.addOpt("trial_end", trialEnd);
            return this;
        }


        public ImportForCustomerRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        public ImportForCustomerRequest startDate(Timestamp startDate) {
            params.addOpt("start_date", startDate);
            return this;
        }




        public ImportForCustomerRequest autoCollection(com.chargebee.models.enums.AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        public ImportForCustomerRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public ImportForCustomerRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public ImportForCustomerRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public ImportForCustomerRequest paymentSourceId(String paymentSourceId) {
            params.addOpt("payment_source_id", paymentSourceId);
            return this;
        }


        public ImportForCustomerRequest status(Subscription.Status status) {
            params.add("status", status);
            return this;
        }


        public ImportForCustomerRequest currentTermEnd(Timestamp currentTermEnd) {
            params.addOpt("current_term_end", currentTermEnd);
            return this;
        }


        public ImportForCustomerRequest currentTermStart(Timestamp currentTermStart) {
            params.addOpt("current_term_start", currentTermStart);
            return this;
        }


        public ImportForCustomerRequest trialStart(Timestamp trialStart) {
            params.addOpt("trial_start", trialStart);
            return this;
        }


        public ImportForCustomerRequest cancelledAt(Timestamp cancelledAt) {
            params.addOpt("cancelled_at", cancelledAt);
            return this;
        }


        public ImportForCustomerRequest startedAt(Timestamp startedAt) {
            params.addOpt("started_at", startedAt);
            return this;
        }


        public ImportForCustomerRequest activatedAt(Timestamp activatedAt) {
            params.addOpt("activated_at", activatedAt);
            return this;
        }


        public ImportForCustomerRequest pauseDate(Timestamp pauseDate) {
            params.addOpt("pause_date", pauseDate);
            return this;
        }


        public ImportForCustomerRequest resumeDate(Timestamp resumeDate) {
            params.addOpt("resume_date", resumeDate);
            return this;
        }






        public ImportForCustomerRequest contractTermBillingCycleOnRenewal(Integer contractTermBillingCycleOnRenewal) {
            params.addOpt("contract_term_billing_cycle_on_renewal", contractTermBillingCycleOnRenewal);
            return this;
        }


        public ImportForCustomerRequest createCurrentTermInvoice(Boolean createCurrentTermInvoice) {
            params.addOpt("create_current_term_invoice", createCurrentTermInvoice);
            return this;
        }


        public ImportForCustomerRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        public ImportForCustomerRequest metaData(JSONObject metaData) {
            params.addOpt("meta_data", metaData);
            return this;
        }


        public ImportForCustomerRequest contractTermId(String contractTermId) {
            params.addOpt("contract_term[id]", contractTermId);
            return this;
        }

        public ImportForCustomerRequest contractTermCreatedAt(Timestamp contractTermCreatedAt) {
            params.addOpt("contract_term[created_at]", contractTermCreatedAt);
            return this;
        }

        public ImportForCustomerRequest contractTermContractStart(Timestamp contractTermContractStart) {
            params.addOpt("contract_term[contract_start]", contractTermContractStart);
            return this;
        }

        public ImportForCustomerRequest contractTermBillingCycle(Integer contractTermBillingCycle) {
            params.addOpt("contract_term[billing_cycle]", contractTermBillingCycle);
            return this;
        }

        public ImportForCustomerRequest contractTermTotalAmountRaised(Long contractTermTotalAmountRaised) {
            params.addOpt("contract_term[total_amount_raised]", contractTermTotalAmountRaised);
            return this;
        }

        public ImportForCustomerRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public ImportForCustomerRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public ImportForCustomerRequest transactionAmount(Long transactionAmount) {
            params.addOpt("transaction[amount]", transactionAmount);
            return this;
        }

        public ImportForCustomerRequest transactionPaymentMethod(com.chargebee.models.enums.PaymentMethod transactionPaymentMethod) {
            params.addOpt("transaction[payment_method]", transactionPaymentMethod);
            return this;
        }

        public ImportForCustomerRequest transactionReferenceNumber(String transactionReferenceNumber) {
            params.addOpt("transaction[reference_number]", transactionReferenceNumber);
            return this;
        }

        public ImportForCustomerRequest transactionDate(Timestamp transactionDate) {
            params.addOpt("transaction[date]", transactionDate);
            return this;
        }

        public ImportForCustomerRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public ImportForCustomerRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public ImportForCustomerRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public ImportForCustomerRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public ImportForCustomerRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public ImportForCustomerRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public ImportForCustomerRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public ImportForCustomerRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public ImportForCustomerRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public ImportForCustomerRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public ImportForCustomerRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public ImportForCustomerRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public ImportForCustomerRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public ImportForCustomerRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public ImportForCustomerRequest addonId(int index, String addonId) {
            params.addOpt("addons[id][" + index + "]", addonId);
            return this;
        }
        public ImportForCustomerRequest addonQuantity(int index, Integer addonQuantity) {
            params.addOpt("addons[quantity][" + index + "]", addonQuantity);
            return this;
        }
        public ImportForCustomerRequest addonQuantityInDecimal(int index, String addonQuantityInDecimal) {
            params.addOpt("addons[quantity_in_decimal][" + index + "]", addonQuantityInDecimal);
            return this;
        }
        public ImportForCustomerRequest addonUnitPrice(int index, Long addonUnitPrice) {
            params.addOpt("addons[unit_price][" + index + "]", addonUnitPrice);
            return this;
        }
        public ImportForCustomerRequest addonUnitPriceInDecimal(int index, String addonUnitPriceInDecimal) {
            params.addOpt("addons[unit_price_in_decimal][" + index + "]", addonUnitPriceInDecimal);
            return this;
        }
        public ImportForCustomerRequest addonBillingCycles(int index, Integer addonBillingCycles) {
            params.addOpt("addons[billing_cycles][" + index + "]", addonBillingCycles);
            return this;
        }
        public ImportForCustomerRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public ImportForCustomerRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public ImportForCustomerRequest eventBasedAddonUnitPrice(int index, Long eventBasedAddonUnitPrice) {
            params.addOpt("event_based_addons[unit_price][" + index + "]", eventBasedAddonUnitPrice);
            return this;
        }
        public ImportForCustomerRequest eventBasedAddonQuantityInDecimal(int index, String eventBasedAddonQuantityInDecimal) {
            params.addOpt("event_based_addons[quantity_in_decimal][" + index + "]", eventBasedAddonQuantityInDecimal);
            return this;
        }
        public ImportForCustomerRequest eventBasedAddonUnitPriceInDecimal(int index, String eventBasedAddonUnitPriceInDecimal) {
            params.addOpt("event_based_addons[unit_price_in_decimal][" + index + "]", eventBasedAddonUnitPriceInDecimal);
            return this;
        }
        public ImportForCustomerRequest eventBasedAddonServicePeriodInDays(int index, Integer eventBasedAddonServicePeriodInDays) {
            params.addOpt("event_based_addons[service_period_in_days][" + index + "]", eventBasedAddonServicePeriodInDays);
            return this;
        }
        public ImportForCustomerRequest eventBasedAddonOnEvent(int index, com.chargebee.models.enums.OnEvent eventBasedAddonOnEvent) {
            params.addOpt("event_based_addons[on_event][" + index + "]", eventBasedAddonOnEvent);
            return this;
        }
        public ImportForCustomerRequest eventBasedAddonChargeOnce(int index, Boolean eventBasedAddonChargeOnce) {
            params.addOpt("event_based_addons[charge_once][" + index + "]", eventBasedAddonChargeOnce);
            return this;
        }
        public ImportForCustomerRequest chargedEventBasedAddonId(int index, String chargedEventBasedAddonId) {
            params.addOpt("charged_event_based_addons[id][" + index + "]", chargedEventBasedAddonId);
            return this;
        }
        public ImportForCustomerRequest chargedEventBasedAddonLastChargedAt(int index, Timestamp chargedEventBasedAddonLastChargedAt) {
            params.addOpt("charged_event_based_addons[last_charged_at][" + index + "]", chargedEventBasedAddonLastChargedAt);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class ImportContractTermRequest extends Request<ImportContractTermRequest> {

        private ImportContractTermRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ImportContractTermRequest contractTermBillingCycleOnRenewal(Integer contractTermBillingCycleOnRenewal) {
            params.addOpt("contract_term_billing_cycle_on_renewal", contractTermBillingCycleOnRenewal);
            return this;
        }


        public ImportContractTermRequest contractTermId(String contractTermId) {
            params.addOpt("contract_term[id]", contractTermId);
            return this;
        }

        public ImportContractTermRequest contractTermCreatedAt(Timestamp contractTermCreatedAt) {
            params.addOpt("contract_term[created_at]", contractTermCreatedAt);
            return this;
        }

        public ImportContractTermRequest contractTermContractStart(Timestamp contractTermContractStart) {
            params.addOpt("contract_term[contract_start]", contractTermContractStart);
            return this;
        }

        public ImportContractTermRequest contractTermContractEnd(Timestamp contractTermContractEnd) {
            params.addOpt("contract_term[contract_end]", contractTermContractEnd);
            return this;
        }

        public ImportContractTermRequest contractTermStatus(ContractTerm.Status contractTermStatus) {
            params.addOpt("contract_term[status]", contractTermStatus);
            return this;
        }

        public ImportContractTermRequest contractTermTotalAmountRaised(Long contractTermTotalAmountRaised) {
            params.addOpt("contract_term[total_amount_raised]", contractTermTotalAmountRaised);
            return this;
        }

        public ImportContractTermRequest contractTermTotalContractValue(Long contractTermTotalContractValue) {
            params.addOpt("contract_term[total_contract_value]", contractTermTotalContractValue);
            return this;
        }

        public ImportContractTermRequest contractTermBillingCycle(Integer contractTermBillingCycle) {
            params.addOpt("contract_term[billing_cycle]", contractTermBillingCycle);
            return this;
        }

        public ImportContractTermRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public ImportContractTermRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class ImportUnbilledChargesRequest extends Request<ImportUnbilledChargesRequest> {

        private ImportUnbilledChargesRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ImportUnbilledChargesRequest unbilledChargeId(int index, String unbilledChargeId) {
            params.addOpt("unbilled_charges[id][" + index + "]", unbilledChargeId);
            return this;
        }
        public ImportUnbilledChargesRequest unbilledChargeDateFrom(int index, Timestamp unbilledChargeDateFrom) {
            params.add("unbilled_charges[date_from][" + index + "]", unbilledChargeDateFrom);
            return this;
        }
        public ImportUnbilledChargesRequest unbilledChargeDateTo(int index, Timestamp unbilledChargeDateTo) {
            params.add("unbilled_charges[date_to][" + index + "]", unbilledChargeDateTo);
            return this;
        }
        public ImportUnbilledChargesRequest unbilledChargeEntityType(int index, UnbilledCharge.EntityType unbilledChargeEntityType) {
            params.add("unbilled_charges[entity_type][" + index + "]", unbilledChargeEntityType);
            return this;
        }
        public ImportUnbilledChargesRequest unbilledChargeEntityId(int index, String unbilledChargeEntityId) {
            params.addOpt("unbilled_charges[entity_id][" + index + "]", unbilledChargeEntityId);
            return this;
        }
        public ImportUnbilledChargesRequest unbilledChargeDescription(int index, String unbilledChargeDescription) {
            params.addOpt("unbilled_charges[description][" + index + "]", unbilledChargeDescription);
            return this;
        }
        public ImportUnbilledChargesRequest unbilledChargeUnitAmount(int index, Long unbilledChargeUnitAmount) {
            params.addOpt("unbilled_charges[unit_amount][" + index + "]", unbilledChargeUnitAmount);
            return this;
        }
        public ImportUnbilledChargesRequest unbilledChargeQuantity(int index, Integer unbilledChargeQuantity) {
            params.addOpt("unbilled_charges[quantity][" + index + "]", unbilledChargeQuantity);
            return this;
        }
        public ImportUnbilledChargesRequest unbilledChargeAmount(int index, Long unbilledChargeAmount) {
            params.addOpt("unbilled_charges[amount][" + index + "]", unbilledChargeAmount);
            return this;
        }
        public ImportUnbilledChargesRequest unbilledChargeUnitAmountInDecimal(int index, String unbilledChargeUnitAmountInDecimal) {
            params.addOpt("unbilled_charges[unit_amount_in_decimal][" + index + "]", unbilledChargeUnitAmountInDecimal);
            return this;
        }
        public ImportUnbilledChargesRequest unbilledChargeQuantityInDecimal(int index, String unbilledChargeQuantityInDecimal) {
            params.addOpt("unbilled_charges[quantity_in_decimal][" + index + "]", unbilledChargeQuantityInDecimal);
            return this;
        }
        public ImportUnbilledChargesRequest unbilledChargeAmountInDecimal(int index, String unbilledChargeAmountInDecimal) {
            params.addOpt("unbilled_charges[amount_in_decimal][" + index + "]", unbilledChargeAmountInDecimal);
            return this;
        }
        public ImportUnbilledChargesRequest unbilledChargeDiscountAmount(int index, Long unbilledChargeDiscountAmount) {
            params.addOpt("unbilled_charges[discount_amount][" + index + "]", unbilledChargeDiscountAmount);
            return this;
        }
        public ImportUnbilledChargesRequest unbilledChargeUseForProration(int index, Boolean unbilledChargeUseForProration) {
            params.addOpt("unbilled_charges[use_for_proration][" + index + "]", unbilledChargeUseForProration);
            return this;
        }
        public ImportUnbilledChargesRequest unbilledChargeIsAdvanceCharge(int index, Boolean unbilledChargeIsAdvanceCharge) {
            params.addOpt("unbilled_charges[is_advance_charge][" + index + "]", unbilledChargeIsAdvanceCharge);
            return this;
        }
        public ImportUnbilledChargesRequest discountUnbilledChargeId(int index, String discountUnbilledChargeId) {
            params.addOpt("discounts[unbilled_charge_id][" + index + "]", discountUnbilledChargeId);
            return this;
        }
        public ImportUnbilledChargesRequest discountEntityType(int index, Invoice.Discount.EntityType discountEntityType) {
            params.addOpt("discounts[entity_type][" + index + "]", discountEntityType);
            return this;
        }
        public ImportUnbilledChargesRequest discountEntityId(int index, String discountEntityId) {
            params.addOpt("discounts[entity_id][" + index + "]", discountEntityId);
            return this;
        }
        public ImportUnbilledChargesRequest discountDescription(int index, String discountDescription) {
            params.addOpt("discounts[description][" + index + "]", discountDescription);
            return this;
        }
        public ImportUnbilledChargesRequest discountAmount(int index, Long discountAmount) {
            params.add("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public ImportUnbilledChargesRequest tierUnbilledChargeId(int index, String tierUnbilledChargeId) {
            params.add("tiers[unbilled_charge_id][" + index + "]", tierUnbilledChargeId);
            return this;
        }
        public ImportUnbilledChargesRequest tierStartingUnit(int index, Integer tierStartingUnit) {
            params.addOpt("tiers[starting_unit][" + index + "]", tierStartingUnit);
            return this;
        }
        public ImportUnbilledChargesRequest tierEndingUnit(int index, Integer tierEndingUnit) {
            params.addOpt("tiers[ending_unit][" + index + "]", tierEndingUnit);
            return this;
        }
        public ImportUnbilledChargesRequest tierQuantityUsed(int index, Integer tierQuantityUsed) {
            params.addOpt("tiers[quantity_used][" + index + "]", tierQuantityUsed);
            return this;
        }
        public ImportUnbilledChargesRequest tierUnitAmount(int index, Long tierUnitAmount) {
            params.addOpt("tiers[unit_amount][" + index + "]", tierUnitAmount);
            return this;
        }
        public ImportUnbilledChargesRequest tierStartingUnitInDecimal(int index, String tierStartingUnitInDecimal) {
            params.addOpt("tiers[starting_unit_in_decimal][" + index + "]", tierStartingUnitInDecimal);
            return this;
        }
        public ImportUnbilledChargesRequest tierEndingUnitInDecimal(int index, String tierEndingUnitInDecimal) {
            params.addOpt("tiers[ending_unit_in_decimal][" + index + "]", tierEndingUnitInDecimal);
            return this;
        }
        public ImportUnbilledChargesRequest tierQuantityUsedInDecimal(int index, String tierQuantityUsedInDecimal) {
            params.addOpt("tiers[quantity_used_in_decimal][" + index + "]", tierQuantityUsedInDecimal);
            return this;
        }
        public ImportUnbilledChargesRequest tierUnitAmountInDecimal(int index, String tierUnitAmountInDecimal) {
            params.addOpt("tiers[unit_amount_in_decimal][" + index + "]", tierUnitAmountInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class ImportForItemsRequest extends Request<ImportForItemsRequest> {

        private ImportForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ImportForItemsRequest id(String id) {
            params.addOpt("id", id);
            return this;
        }


        public ImportForItemsRequest trialEnd(Timestamp trialEnd) {
            params.addOpt("trial_end", trialEnd);
            return this;
        }


        public ImportForItemsRequest billingCycles(Integer billingCycles) {
            params.addOpt("billing_cycles", billingCycles);
            return this;
        }


        @Deprecated
        public ImportForItemsRequest setupFee(Long setupFee) {
            params.addOpt("setup_fee", setupFee);
            return this;
        }


        public ImportForItemsRequest netTermDays(Integer netTermDays) {
            params.addOpt("net_term_days", netTermDays);
            return this;
        }


        public ImportForItemsRequest startDate(Timestamp startDate) {
            params.addOpt("start_date", startDate);
            return this;
        }




        public ImportForItemsRequest autoCollection(com.chargebee.models.enums.AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        public ImportForItemsRequest poNumber(String poNumber) {
            params.addOpt("po_number", poNumber);
            return this;
        }


        public ImportForItemsRequest couponIds(List<String> couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public ImportForItemsRequest couponIds(String... couponIds) {
            params.addOpt("coupon_ids", couponIds);
            return this;
        }

        public ImportForItemsRequest paymentSourceId(String paymentSourceId) {
            params.addOpt("payment_source_id", paymentSourceId);
            return this;
        }


        public ImportForItemsRequest status(Subscription.Status status) {
            params.add("status", status);
            return this;
        }


        public ImportForItemsRequest currentTermEnd(Timestamp currentTermEnd) {
            params.addOpt("current_term_end", currentTermEnd);
            return this;
        }


        public ImportForItemsRequest currentTermStart(Timestamp currentTermStart) {
            params.addOpt("current_term_start", currentTermStart);
            return this;
        }


        public ImportForItemsRequest trialStart(Timestamp trialStart) {
            params.addOpt("trial_start", trialStart);
            return this;
        }


        public ImportForItemsRequest cancelledAt(Timestamp cancelledAt) {
            params.addOpt("cancelled_at", cancelledAt);
            return this;
        }


        public ImportForItemsRequest startedAt(Timestamp startedAt) {
            params.addOpt("started_at", startedAt);
            return this;
        }


        public ImportForItemsRequest activatedAt(Timestamp activatedAt) {
            params.addOpt("activated_at", activatedAt);
            return this;
        }


        public ImportForItemsRequest pauseDate(Timestamp pauseDate) {
            params.addOpt("pause_date", pauseDate);
            return this;
        }


        public ImportForItemsRequest resumeDate(Timestamp resumeDate) {
            params.addOpt("resume_date", resumeDate);
            return this;
        }






        public ImportForItemsRequest contractTermBillingCycleOnRenewal(Integer contractTermBillingCycleOnRenewal) {
            params.addOpt("contract_term_billing_cycle_on_renewal", contractTermBillingCycleOnRenewal);
            return this;
        }


        public ImportForItemsRequest createCurrentTermInvoice(Boolean createCurrentTermInvoice) {
            params.addOpt("create_current_term_invoice", createCurrentTermInvoice);
            return this;
        }


        public ImportForItemsRequest invoiceNotes(String invoiceNotes) {
            params.addOpt("invoice_notes", invoiceNotes);
            return this;
        }


        public ImportForItemsRequest metaData(JSONObject metaData) {
            params.addOpt("meta_data", metaData);
            return this;
        }


        public ImportForItemsRequest createPendingInvoices(Boolean createPendingInvoices) {
            params.addOpt("create_pending_invoices", createPendingInvoices);
            return this;
        }


        public ImportForItemsRequest autoCloseInvoices(Boolean autoCloseInvoices) {
            params.addOpt("auto_close_invoices", autoCloseInvoices);
            return this;
        }


        public ImportForItemsRequest contractTermId(String contractTermId) {
            params.addOpt("contract_term[id]", contractTermId);
            return this;
        }

        public ImportForItemsRequest contractTermCreatedAt(Timestamp contractTermCreatedAt) {
            params.addOpt("contract_term[created_at]", contractTermCreatedAt);
            return this;
        }

        public ImportForItemsRequest contractTermContractStart(Timestamp contractTermContractStart) {
            params.addOpt("contract_term[contract_start]", contractTermContractStart);
            return this;
        }

        public ImportForItemsRequest contractTermBillingCycle(Integer contractTermBillingCycle) {
            params.addOpt("contract_term[billing_cycle]", contractTermBillingCycle);
            return this;
        }

        public ImportForItemsRequest contractTermTotalAmountRaised(Long contractTermTotalAmountRaised) {
            params.addOpt("contract_term[total_amount_raised]", contractTermTotalAmountRaised);
            return this;
        }

        public ImportForItemsRequest contractTermActionAtTermEnd(ContractTerm.ActionAtTermEnd contractTermActionAtTermEnd) {
            params.addOpt("contract_term[action_at_term_end]", contractTermActionAtTermEnd);
            return this;
        }

        public ImportForItemsRequest contractTermCancellationCutoffPeriod(Integer contractTermCancellationCutoffPeriod) {
            params.addOpt("contract_term[cancellation_cutoff_period]", contractTermCancellationCutoffPeriod);
            return this;
        }

        public ImportForItemsRequest transactionAmount(Long transactionAmount) {
            params.addOpt("transaction[amount]", transactionAmount);
            return this;
        }

        public ImportForItemsRequest transactionPaymentMethod(com.chargebee.models.enums.PaymentMethod transactionPaymentMethod) {
            params.addOpt("transaction[payment_method]", transactionPaymentMethod);
            return this;
        }

        public ImportForItemsRequest transactionReferenceNumber(String transactionReferenceNumber) {
            params.addOpt("transaction[reference_number]", transactionReferenceNumber);
            return this;
        }

        public ImportForItemsRequest transactionDate(Timestamp transactionDate) {
            params.addOpt("transaction[date]", transactionDate);
            return this;
        }

        public ImportForItemsRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }

        public ImportForItemsRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }

        public ImportForItemsRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }

        public ImportForItemsRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }

        public ImportForItemsRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }

        public ImportForItemsRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }

        public ImportForItemsRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }

        public ImportForItemsRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }

        public ImportForItemsRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }

        public ImportForItemsRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }

        public ImportForItemsRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }

        public ImportForItemsRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }

        public ImportForItemsRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }

        public ImportForItemsRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }

        public ImportForItemsRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.add("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public ImportForItemsRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public ImportForItemsRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        public ImportForItemsRequest subscriptionItemUnitPrice(int index, Long subscriptionItemUnitPrice) {
            params.addOpt("subscription_items[unit_price][" + index + "]", subscriptionItemUnitPrice);
            return this;
        }
        public ImportForItemsRequest subscriptionItemUnitPriceInDecimal(int index, String subscriptionItemUnitPriceInDecimal) {
            params.addOpt("subscription_items[unit_price_in_decimal][" + index + "]", subscriptionItemUnitPriceInDecimal);
            return this;
        }
        public ImportForItemsRequest subscriptionItemBillingCycles(int index, Integer subscriptionItemBillingCycles) {
            params.addOpt("subscription_items[billing_cycles][" + index + "]", subscriptionItemBillingCycles);
            return this;
        }
        public ImportForItemsRequest subscriptionItemTrialEnd(int index, Timestamp subscriptionItemTrialEnd) {
            params.addOpt("subscription_items[trial_end][" + index + "]", subscriptionItemTrialEnd);
            return this;
        }
        public ImportForItemsRequest subscriptionItemServicePeriodDays(int index, Integer subscriptionItemServicePeriodDays) {
            params.addOpt("subscription_items[service_period_days][" + index + "]", subscriptionItemServicePeriodDays);
            return this;
        }
        public ImportForItemsRequest subscriptionItemChargeOnEvent(int index, com.chargebee.models.enums.ChargeOnEvent subscriptionItemChargeOnEvent) {
            params.addOpt("subscription_items[charge_on_event][" + index + "]", subscriptionItemChargeOnEvent);
            return this;
        }
        public ImportForItemsRequest subscriptionItemChargeOnce(int index, Boolean subscriptionItemChargeOnce) {
            params.addOpt("subscription_items[charge_once][" + index + "]", subscriptionItemChargeOnce);
            return this;
        }
        @Deprecated
        public ImportForItemsRequest subscriptionItemItemType(int index, com.chargebee.models.enums.ItemType subscriptionItemItemType) {
            params.addOpt("subscription_items[item_type][" + index + "]", subscriptionItemItemType);
            return this;
        }
        public ImportForItemsRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.add("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public ImportForItemsRequest discountDurationType(int index, com.chargebee.models.enums.DurationType discountDurationType) {
            params.add("discounts[duration_type][" + index + "]", discountDurationType);
            return this;
        }
        public ImportForItemsRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public ImportForItemsRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public ImportForItemsRequest discountPeriod(int index, Integer discountPeriod) {
            params.addOpt("discounts[period][" + index + "]", discountPeriod);
            return this;
        }
        public ImportForItemsRequest discountPeriodUnit(int index, com.chargebee.models.enums.PeriodUnit discountPeriodUnit) {
            params.addOpt("discounts[period_unit][" + index + "]", discountPeriodUnit);
            return this;
        }
        public ImportForItemsRequest discountIncludedInMrr(int index, Boolean discountIncludedInMrr) {
            params.addOpt("discounts[included_in_mrr][" + index + "]", discountIncludedInMrr);
            return this;
        }
        public ImportForItemsRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        public ImportForItemsRequest chargedItemItemPriceId(int index, String chargedItemItemPriceId) {
            params.addOpt("charged_items[item_price_id][" + index + "]", chargedItemItemPriceId);
            return this;
        }
        public ImportForItemsRequest chargedItemLastChargedAt(int index, Timestamp chargedItemLastChargedAt) {
            params.addOpt("charged_items[last_charged_at][" + index + "]", chargedItemLastChargedAt);
            return this;
        }
        public ImportForItemsRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public ImportForItemsRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public ImportForItemsRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public ImportForItemsRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public ImportForItemsRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public ImportForItemsRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public ImportForItemsRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class OverrideBillingProfileRequest extends Request<OverrideBillingProfileRequest> {

        private OverrideBillingProfileRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public OverrideBillingProfileRequest paymentSourceId(String paymentSourceId) {
            params.addOpt("payment_source_id", paymentSourceId);
            return this;
        }


        public OverrideBillingProfileRequest autoCollection(com.chargebee.models.enums.AutoCollection autoCollection) {
            params.addOpt("auto_collection", autoCollection);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class PauseRequest extends Request<PauseRequest> {

        private PauseRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public PauseRequest pauseOption(com.chargebee.models.enums.PauseOption pauseOption) {
            params.addOpt("pause_option", pauseOption);
            return this;
        }


        public PauseRequest pauseDate(Timestamp pauseDate) {
            params.addOpt("pause_date", pauseDate);
            return this;
        }


        public PauseRequest unbilledChargesHandling(com.chargebee.models.enums.UnbilledChargesHandling unbilledChargesHandling) {
            params.addOpt("unbilled_charges_handling", unbilledChargesHandling);
            return this;
        }


        public PauseRequest invoiceDunningHandling(com.chargebee.models.enums.InvoiceDunningHandling invoiceDunningHandling) {
            params.addOpt("invoice_dunning_handling", invoiceDunningHandling);
            return this;
        }


        public PauseRequest skipBillingCycles(Integer skipBillingCycles) {
            params.addOpt("skip_billing_cycles", skipBillingCycles);
            return this;
        }


        public PauseRequest resumeDate(Timestamp resumeDate) {
            params.addOpt("resume_date", resumeDate);
            return this;
        }


        @Override
        public Params params() {
            return params;
        }
    }

    public static class CancelRequest extends Request<CancelRequest> {

        private CancelRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CancelRequest endOfTerm(Boolean endOfTerm) {
            params.addOpt("end_of_term", endOfTerm);
            return this;
        }


        public CancelRequest cancelAt(Timestamp cancelAt) {
            params.addOpt("cancel_at", cancelAt);
            return this;
        }


        public CancelRequest creditOptionForCurrentTermCharges(com.chargebee.models.enums.CreditOptionForCurrentTermCharges creditOptionForCurrentTermCharges) {
            params.addOpt("credit_option_for_current_term_charges", creditOptionForCurrentTermCharges);
            return this;
        }


        public CancelRequest unbilledChargesOption(com.chargebee.models.enums.UnbilledChargesOption unbilledChargesOption) {
            params.addOpt("unbilled_charges_option", unbilledChargesOption);
            return this;
        }


        public CancelRequest accountReceivablesHandling(com.chargebee.models.enums.AccountReceivablesHandling accountReceivablesHandling) {
            params.addOpt("account_receivables_handling", accountReceivablesHandling);
            return this;
        }


        public CancelRequest refundableCreditsHandling(com.chargebee.models.enums.RefundableCreditsHandling refundableCreditsHandling) {
            params.addOpt("refundable_credits_handling", refundableCreditsHandling);
            return this;
        }


        public CancelRequest contractTermCancelOption(com.chargebee.models.enums.ContractTermCancelOption contractTermCancelOption) {
            params.addOpt("contract_term_cancel_option", contractTermCancelOption);
            return this;
        }


        public CancelRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public CancelRequest cancelReasonCode(String cancelReasonCode) {
            params.addOpt("cancel_reason_code", cancelReasonCode);
            return this;
        }


        public CancelRequest eventBasedAddonId(int index, String eventBasedAddonId) {
            params.addOpt("event_based_addons[id][" + index + "]", eventBasedAddonId);
            return this;
        }
        public CancelRequest eventBasedAddonQuantity(int index, Integer eventBasedAddonQuantity) {
            params.addOpt("event_based_addons[quantity][" + index + "]", eventBasedAddonQuantity);
            return this;
        }
        public CancelRequest eventBasedAddonUnitPrice(int index, Long eventBasedAddonUnitPrice) {
            params.addOpt("event_based_addons[unit_price][" + index + "]", eventBasedAddonUnitPrice);
            return this;
        }
        public CancelRequest eventBasedAddonServicePeriodInDays(int index, Integer eventBasedAddonServicePeriodInDays) {
            params.addOpt("event_based_addons[service_period_in_days][" + index + "]", eventBasedAddonServicePeriodInDays);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CancelForItemsRequest extends Request<CancelForItemsRequest> {

        private CancelForItemsRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CancelForItemsRequest endOfTerm(Boolean endOfTerm) {
            params.addOpt("end_of_term", endOfTerm);
            return this;
        }


        public CancelForItemsRequest cancelAt(Timestamp cancelAt) {
            params.addOpt("cancel_at", cancelAt);
            return this;
        }


        public CancelForItemsRequest creditOptionForCurrentTermCharges(com.chargebee.models.enums.CreditOptionForCurrentTermCharges creditOptionForCurrentTermCharges) {
            params.addOpt("credit_option_for_current_term_charges", creditOptionForCurrentTermCharges);
            return this;
        }


        public CancelForItemsRequest unbilledChargesOption(com.chargebee.models.enums.UnbilledChargesOption unbilledChargesOption) {
            params.addOpt("unbilled_charges_option", unbilledChargesOption);
            return this;
        }


        public CancelForItemsRequest accountReceivablesHandling(com.chargebee.models.enums.AccountReceivablesHandling accountReceivablesHandling) {
            params.addOpt("account_receivables_handling", accountReceivablesHandling);
            return this;
        }


        public CancelForItemsRequest refundableCreditsHandling(com.chargebee.models.enums.RefundableCreditsHandling refundableCreditsHandling) {
            params.addOpt("refundable_credits_handling", refundableCreditsHandling);
            return this;
        }


        public CancelForItemsRequest contractTermCancelOption(com.chargebee.models.enums.ContractTermCancelOption contractTermCancelOption) {
            params.addOpt("contract_term_cancel_option", contractTermCancelOption);
            return this;
        }


        public CancelForItemsRequest invoiceDate(Timestamp invoiceDate) {
            params.addOpt("invoice_date", invoiceDate);
            return this;
        }


        public CancelForItemsRequest cancelReasonCode(String cancelReasonCode) {
            params.addOpt("cancel_reason_code", cancelReasonCode);
            return this;
        }


        public CancelForItemsRequest subscriptionItemItemPriceId(int index, String subscriptionItemItemPriceId) {
            params.addOpt("subscription_items[item_price_id][" + index + "]", subscriptionItemItemPriceId);
            return this;
        }
        public CancelForItemsRequest subscriptionItemQuantity(int index, Integer subscriptionItemQuantity) {
            params.addOpt("subscription_items[quantity][" + index + "]", subscriptionItemQuantity);
            return this;
        }
        public CancelForItemsRequest subscriptionItemQuantityInDecimal(int index, String subscriptionItemQuantityInDecimal) {
            params.addOpt("subscription_items[quantity_in_decimal][" + index + "]", subscriptionItemQuantityInDecimal);
            return this;
        }
        public CancelForItemsRequest subscriptionItemUnitPrice(int index, Long subscriptionItemUnitPrice) {
            params.addOpt("subscription_items[unit_price][" + index + "]", subscriptionItemUnitPrice);
            return this;
        }
        public CancelForItemsRequest subscriptionItemUnitPriceInDecimal(int index, String subscriptionItemUnitPriceInDecimal) {
            params.addOpt("subscription_items[unit_price_in_decimal][" + index + "]", subscriptionItemUnitPriceInDecimal);
            return this;
        }
        public CancelForItemsRequest subscriptionItemServicePeriodDays(int index, Integer subscriptionItemServicePeriodDays) {
            params.addOpt("subscription_items[service_period_days][" + index + "]", subscriptionItemServicePeriodDays);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class ResumeRequest extends Request<ResumeRequest> {

        private ResumeRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public ResumeRequest resumeOption(com.chargebee.models.enums.ResumeOption resumeOption) {
            params.addOpt("resume_option", resumeOption);
            return this;
        }


        public ResumeRequest resumeDate(Timestamp resumeDate) {
            params.addOpt("resume_date", resumeDate);
            return this;
        }


        public ResumeRequest chargesHandling(com.chargebee.models.enums.ChargesHandling chargesHandling) {
            params.addOpt("charges_handling", chargesHandling);
            return this;
        }


        public ResumeRequest unpaidInvoicesHandling(com.chargebee.models.enums.UnpaidInvoicesHandling unpaidInvoicesHandling) {
            params.addOpt("unpaid_invoices_handling", unpaidInvoicesHandling);
            return this;
        }


        public ResumeRequest paymentIntentId(String paymentIntentId) {
            params.addOpt("payment_intent[id]", paymentIntentId);
            return this;
        }

        public ResumeRequest paymentIntentGatewayAccountId(String paymentIntentGatewayAccountId) {
            params.addOpt("payment_intent[gateway_account_id]", paymentIntentGatewayAccountId);
            return this;
        }

        public ResumeRequest paymentIntentGwToken(String paymentIntentGwToken) {
            params.addOpt("payment_intent[gw_token]", paymentIntentGwToken);
            return this;
        }

        public ResumeRequest paymentIntentPaymentMethodType(PaymentIntent.PaymentMethodType paymentIntentPaymentMethodType) {
            params.addOpt("payment_intent[payment_method_type]", paymentIntentPaymentMethodType);
            return this;
        }

        public ResumeRequest paymentIntentReferenceId(String paymentIntentReferenceId) {
            params.addOpt("payment_intent[reference_id]", paymentIntentReferenceId);
            return this;
        }

        @Deprecated
        public ResumeRequest paymentIntentGwPaymentMethodId(String paymentIntentGwPaymentMethodId) {
            params.addOpt("payment_intent[gw_payment_method_id]", paymentIntentGwPaymentMethodId);
            return this;
        }

        public ResumeRequest paymentIntentAdditionalInformation(JSONObject paymentIntentAdditionalInformation) {
            params.addOpt("payment_intent[additional_information]", paymentIntentAdditionalInformation);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

}
