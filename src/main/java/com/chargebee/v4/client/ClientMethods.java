package com.chargebee.v4.client;

import com.chargebee.v4.services.GiftService;

import com.chargebee.v4.services.CsvTaxRuleService;

import com.chargebee.v4.services.UsageService;

import com.chargebee.v4.services.TimeMachineService;

import com.chargebee.v4.services.BusinessEntityService;

import com.chargebee.v4.services.OfferEventService;

import com.chargebee.v4.services.InAppSubscriptionService;

import com.chargebee.v4.services.Pc2MigrationService;

import com.chargebee.v4.services.CreditNoteService;

import com.chargebee.v4.services.CouponSetService;

import com.chargebee.v4.services.QuoteService;

import com.chargebee.v4.services.Pc2MigrationItemService;

import com.chargebee.v4.services.EstimateService;

import com.chargebee.v4.services.VariantService;

import com.chargebee.v4.services.Pc2MigrationItemFamilyService;

import com.chargebee.v4.services.PaymentSourceService;

import com.chargebee.v4.services.RecordedPurchaseService;

import com.chargebee.v4.services.PlanService;

import com.chargebee.v4.services.ExportService;

import com.chargebee.v4.services.OrderService;

import com.chargebee.v4.services.ItemService;

import com.chargebee.v4.services.CustomerEntitlementService;

import com.chargebee.v4.services.PersonalizedOfferService;

import com.chargebee.v4.services.OmnichannelSubscriptionService;

import com.chargebee.v4.services.OmnichannelSubscriptionItemService;

import com.chargebee.v4.services.RampService;

import com.chargebee.v4.services.OmnichannelOneTimeOrderService;

import com.chargebee.v4.services.DifferentialPriceService;

import com.chargebee.v4.services.EntitlementService;

import com.chargebee.v4.services.AdditionalBillingLogiqService;

import com.chargebee.v4.services.SubscriptionSettingService;

import com.chargebee.v4.services.SiteMigrationDetailService;

import com.chargebee.v4.services.PaymentIntentService;

import com.chargebee.v4.services.PaymentScheduleSchemeService;

import com.chargebee.v4.services.CardService;

import com.chargebee.v4.services.AttachedItemService;

import com.chargebee.v4.services.UsageEventService;

import com.chargebee.v4.services.PriceVariantService;

import com.chargebee.v4.services.FullExportService;

import com.chargebee.v4.services.VirtualBankAccountService;

import com.chargebee.v4.services.AddonService;

import com.chargebee.v4.services.TpSiteUserService;

import com.chargebee.v4.services.ConfigurationService;

import com.chargebee.v4.services.PricingPageSessionService;

import com.chargebee.v4.services.Pc2MigrationItemPriceService;

import com.chargebee.v4.services.RuleService;

import com.chargebee.v4.services.SubscriptionService;

import com.chargebee.v4.services.MediaService;

import com.chargebee.v4.services.BusinessProfileService;

import com.chargebee.v4.services.PromotionalCreditService;

import com.chargebee.v4.services.BrandConfigurationService;

import com.chargebee.v4.services.WebhookEndpointService;

import com.chargebee.v4.services.FeatureService;

import com.chargebee.v4.services.UnbilledChargesSettingService;

import com.chargebee.v4.services.CurrencyService;

import com.chargebee.v4.services.EventService;

import com.chargebee.v4.services.UsageFileService;

import com.chargebee.v4.services.NonSubscriptionService;

import com.chargebee.v4.services.ResourceMigrationService;

import com.chargebee.v4.services.ProductService;

import com.chargebee.v4.services.CouponCodeService;

import com.chargebee.v4.services.AddressService;

import com.chargebee.v4.services.CouponService;

import com.chargebee.v4.services.PortalSessionService;

import com.chargebee.v4.services.ItemPriceService;

import com.chargebee.v4.services.OfferFulfillmentService;

import com.chargebee.v4.services.HostedPageService;

import com.chargebee.v4.services.PurchaseService;

import com.chargebee.v4.services.PaymentVoucherService;

import com.chargebee.v4.services.ItemFamilyService;

import com.chargebee.v4.services.SubscriptionEntitlementService;

import com.chargebee.v4.services.ThirdPartyEntityMappingService;

import com.chargebee.v4.services.EntitlementOverrideService;

import com.chargebee.v4.services.ThirdPartyConfigurationService;

import com.chargebee.v4.services.UnbilledChargeService;

import com.chargebee.v4.services.CommentService;

import com.chargebee.v4.services.InvoiceService;

import com.chargebee.v4.services.TransactionService;

import com.chargebee.v4.services.ThirdPartySyncDetailService;

import com.chargebee.v4.services.CustomerService;

import com.chargebee.v4.services.ItemEntitlementService;

/**
 * Auto-generated interface defining all service access methods. ChargebeeClient implements this
 * interface to provide type-safe access to all services.
 */
public interface ClientMethods {

  /**
   * Access gift-related operations.
   *
   * @return GiftService instance for fluent API access
   */
  GiftService gifts();

  /**
   * Access csv_tax_rule-related operations.
   *
   * @return CsvTaxRuleService instance for fluent API access
   */
  CsvTaxRuleService csvTaxRules();

  /**
   * Access usage-related operations.
   *
   * @return UsageService instance for fluent API access
   */
  UsageService usages();

  /**
   * Access time_machine-related operations.
   *
   * @return TimeMachineService instance for fluent API access
   */
  TimeMachineService timeMachines();

  /**
   * Access business_entity-related operations.
   *
   * @return BusinessEntityService instance for fluent API access
   */
  BusinessEntityService businessEntities();

  /**
   * Access offer_event-related operations.
   *
   * @return OfferEventService instance for fluent API access
   */
  OfferEventService offerEvents();

  /**
   * Access in_app_subscription-related operations.
   *
   * @return InAppSubscriptionService instance for fluent API access
   */
  InAppSubscriptionService inAppSubscriptions();

  /**
   * Access pc2_migration-related operations.
   *
   * @return Pc2MigrationService instance for fluent API access
   */
  Pc2MigrationService pc2Migrations();

  /**
   * Access credit_note-related operations.
   *
   * @return CreditNoteService instance for fluent API access
   */
  CreditNoteService creditNotes();

  /**
   * Access coupon_set-related operations.
   *
   * @return CouponSetService instance for fluent API access
   */
  CouponSetService couponSets();

  /**
   * Access quote-related operations.
   *
   * @return QuoteService instance for fluent API access
   */
  QuoteService quotes();

  /**
   * Access pc2_migration_item-related operations.
   *
   * @return Pc2MigrationItemService instance for fluent API access
   */
  Pc2MigrationItemService pc2MigrationItems();

  /**
   * Access estimate-related operations.
   *
   * @return EstimateService instance for fluent API access
   */
  EstimateService estimates();

  /**
   * Access variant-related operations.
   *
   * @return VariantService instance for fluent API access
   */
  VariantService variants();

  /**
   * Access pc2_migration_item_family-related operations.
   *
   * @return Pc2MigrationItemFamilyService instance for fluent API access
   */
  Pc2MigrationItemFamilyService pc2MigrationItemFamilies();

  /**
   * Access payment_source-related operations.
   *
   * @return PaymentSourceService instance for fluent API access
   */
  PaymentSourceService paymentSources();

  /**
   * Access recorded_purchase-related operations.
   *
   * @return RecordedPurchaseService instance for fluent API access
   */
  RecordedPurchaseService recordedPurchases();

  /**
   * Access plan-related operations.
   *
   * @return PlanService instance for fluent API access
   */
  PlanService plans();

  /**
   * Access export-related operations.
   *
   * @return ExportService instance for fluent API access
   */
  ExportService exports();

  /**
   * Access order-related operations.
   *
   * @return OrderService instance for fluent API access
   */
  OrderService orders();

  /**
   * Access item-related operations.
   *
   * @return ItemService instance for fluent API access
   */
  ItemService items();

  /**
   * Access customer_entitlement-related operations.
   *
   * @return CustomerEntitlementService instance for fluent API access
   */
  CustomerEntitlementService customerEntitlements();

  /**
   * Access personalized_offer-related operations.
   *
   * @return PersonalizedOfferService instance for fluent API access
   */
  PersonalizedOfferService personalizedOffers();

  /**
   * Access omnichannel_subscription-related operations.
   *
   * @return OmnichannelSubscriptionService instance for fluent API access
   */
  OmnichannelSubscriptionService omnichannelSubscriptions();

  /**
   * Access omnichannel_subscription_item-related operations.
   *
   * @return OmnichannelSubscriptionItemService instance for fluent API access
   */
  OmnichannelSubscriptionItemService omnichannelSubscriptionItems();

  /**
   * Access ramp-related operations.
   *
   * @return RampService instance for fluent API access
   */
  RampService ramps();

  /**
   * Access omnichannel_one_time_order-related operations.
   *
   * @return OmnichannelOneTimeOrderService instance for fluent API access
   */
  OmnichannelOneTimeOrderService omnichannelOneTimeOrders();

  /**
   * Access differential_price-related operations.
   *
   * @return DifferentialPriceService instance for fluent API access
   */
  DifferentialPriceService differentialPrices();

  /**
   * Access entitlement-related operations.
   *
   * @return EntitlementService instance for fluent API access
   */
  EntitlementService entitlements();

  /**
   * Access additional_billing_logiq-related operations.
   *
   * @return AdditionalBillingLogiqService instance for fluent API access
   */
  AdditionalBillingLogiqService additionalBillingLogiqs();

  /**
   * Access subscription_setting-related operations.
   *
   * @return SubscriptionSettingService instance for fluent API access
   */
  SubscriptionSettingService subscriptionSettings();

  /**
   * Access site_migration_detail-related operations.
   *
   * @return SiteMigrationDetailService instance for fluent API access
   */
  SiteMigrationDetailService siteMigrationDetails();

  /**
   * Access payment_intent-related operations.
   *
   * @return PaymentIntentService instance for fluent API access
   */
  PaymentIntentService paymentIntents();

  /**
   * Access payment_schedule_scheme-related operations.
   *
   * @return PaymentScheduleSchemeService instance for fluent API access
   */
  PaymentScheduleSchemeService paymentScheduleSchemes();

  /**
   * Access card-related operations.
   *
   * @return CardService instance for fluent API access
   */
  CardService cards();

  /**
   * Access attached_item-related operations.
   *
   * @return AttachedItemService instance for fluent API access
   */
  AttachedItemService attachedItems();

  /**
   * Access usage_event-related operations.
   *
   * @return UsageEventService instance for fluent API access
   */
  UsageEventService usageEvents();

  /**
   * Access price_variant-related operations.
   *
   * @return PriceVariantService instance for fluent API access
   */
  PriceVariantService priceVariants();

  /**
   * Access full_export-related operations.
   *
   * @return FullExportService instance for fluent API access
   */
  FullExportService fullExports();

  /**
   * Access virtual_bank_account-related operations.
   *
   * @return VirtualBankAccountService instance for fluent API access
   */
  VirtualBankAccountService virtualBankAccounts();

  /**
   * Access addon-related operations.
   *
   * @return AddonService instance for fluent API access
   */
  AddonService addons();

  /**
   * Access tp_site_user-related operations.
   *
   * @return TpSiteUserService instance for fluent API access
   */
  TpSiteUserService tpSiteUsers();

  /**
   * Access configuration-related operations.
   *
   * @return ConfigurationService instance for fluent API access
   */
  ConfigurationService configurations();

  /**
   * Access pricing_page_session-related operations.
   *
   * @return PricingPageSessionService instance for fluent API access
   */
  PricingPageSessionService pricingPageSessions();

  /**
   * Access pc2_migration_item_price-related operations.
   *
   * @return Pc2MigrationItemPriceService instance for fluent API access
   */
  Pc2MigrationItemPriceService pc2MigrationItemPrices();

  /**
   * Access rule-related operations.
   *
   * @return RuleService instance for fluent API access
   */
  RuleService rules();

  /**
   * Access subscription-related operations.
   *
   * @return SubscriptionService instance for fluent API access
   */
  SubscriptionService subscriptions();

  /**
   * Access media-related operations.
   *
   * @return MediaService instance for fluent API access
   */
  MediaService medias();

  /**
   * Access business_profile-related operations.
   *
   * @return BusinessProfileService instance for fluent API access
   */
  BusinessProfileService businessProfiles();

  /**
   * Access promotional_credit-related operations.
   *
   * @return PromotionalCreditService instance for fluent API access
   */
  PromotionalCreditService promotionalCredits();

  /**
   * Access brand_configuration-related operations.
   *
   * @return BrandConfigurationService instance for fluent API access
   */
  BrandConfigurationService brandConfigurations();

  /**
   * Access webhook_endpoint-related operations.
   *
   * @return WebhookEndpointService instance for fluent API access
   */
  WebhookEndpointService webhookEndpoints();

  /**
   * Access feature-related operations.
   *
   * @return FeatureService instance for fluent API access
   */
  FeatureService features();

  /**
   * Access unbilled_charges_setting-related operations.
   *
   * @return UnbilledChargesSettingService instance for fluent API access
   */
  UnbilledChargesSettingService unbilledChargesSettings();

  /**
   * Access currency-related operations.
   *
   * @return CurrencyService instance for fluent API access
   */
  CurrencyService currencies();

  /**
   * Access event-related operations.
   *
   * @return EventService instance for fluent API access
   */
  EventService events();

  /**
   * Access usage_file-related operations.
   *
   * @return UsageFileService instance for fluent API access
   */
  UsageFileService usageFiles();

  /**
   * Access non_subscription-related operations.
   *
   * @return NonSubscriptionService instance for fluent API access
   */
  NonSubscriptionService nonSubscriptions();

  /**
   * Access resource_migration-related operations.
   *
   * @return ResourceMigrationService instance for fluent API access
   */
  ResourceMigrationService resourceMigrations();

  /**
   * Access product-related operations.
   *
   * @return ProductService instance for fluent API access
   */
  ProductService products();

  /**
   * Access coupon_code-related operations.
   *
   * @return CouponCodeService instance for fluent API access
   */
  CouponCodeService couponCodes();

  /**
   * Access address-related operations.
   *
   * @return AddressService instance for fluent API access
   */
  AddressService addresses();

  /**
   * Access coupon-related operations.
   *
   * @return CouponService instance for fluent API access
   */
  CouponService coupons();

  /**
   * Access portal_session-related operations.
   *
   * @return PortalSessionService instance for fluent API access
   */
  PortalSessionService portalSessions();

  /**
   * Access item_price-related operations.
   *
   * @return ItemPriceService instance for fluent API access
   */
  ItemPriceService itemPrices();

  /**
   * Access offer_fulfillment-related operations.
   *
   * @return OfferFulfillmentService instance for fluent API access
   */
  OfferFulfillmentService offerFulfillments();

  /**
   * Access hosted_page-related operations.
   *
   * @return HostedPageService instance for fluent API access
   */
  HostedPageService hostedPages();

  /**
   * Access purchase-related operations.
   *
   * @return PurchaseService instance for fluent API access
   */
  PurchaseService purchases();

  /**
   * Access payment_voucher-related operations.
   *
   * @return PaymentVoucherService instance for fluent API access
   */
  PaymentVoucherService paymentVouchers();

  /**
   * Access item_family-related operations.
   *
   * @return ItemFamilyService instance for fluent API access
   */
  ItemFamilyService itemFamilies();

  /**
   * Access subscription_entitlement-related operations.
   *
   * @return SubscriptionEntitlementService instance for fluent API access
   */
  SubscriptionEntitlementService subscriptionEntitlements();

  /**
   * Access third_party_entity_mapping-related operations.
   *
   * @return ThirdPartyEntityMappingService instance for fluent API access
   */
  ThirdPartyEntityMappingService thirdPartyEntityMappings();

  /**
   * Access entitlement_override-related operations.
   *
   * @return EntitlementOverrideService instance for fluent API access
   */
  EntitlementOverrideService entitlementOverrides();

  /**
   * Access third_party_configuration-related operations.
   *
   * @return ThirdPartyConfigurationService instance for fluent API access
   */
  ThirdPartyConfigurationService thirdPartyConfigurations();

  /**
   * Access unbilled_charge-related operations.
   *
   * @return UnbilledChargeService instance for fluent API access
   */
  UnbilledChargeService unbilledCharges();

  /**
   * Access comment-related operations.
   *
   * @return CommentService instance for fluent API access
   */
  CommentService comments();

  /**
   * Access invoice-related operations.
   *
   * @return InvoiceService instance for fluent API access
   */
  InvoiceService invoices();

  /**
   * Access transaction-related operations.
   *
   * @return TransactionService instance for fluent API access
   */
  TransactionService transactions();

  /**
   * Access third_party_sync_detail-related operations.
   *
   * @return ThirdPartySyncDetailService instance for fluent API access
   */
  ThirdPartySyncDetailService thirdPartySyncDetails();

  /**
   * Access customer-related operations.
   *
   * @return CustomerService instance for fluent API access
   */
  CustomerService customers();

  /**
   * Access item_entitlement-related operations.
   *
   * @return ItemEntitlementService instance for fluent API access
   */
  ItemEntitlementService itemEntitlements();
}
