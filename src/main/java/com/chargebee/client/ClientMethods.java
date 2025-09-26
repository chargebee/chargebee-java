package com.chargebee.client;

import com.chargebee.core.services.GiftService;

import com.chargebee.core.services.CsvTaxRuleService;

import com.chargebee.core.services.UsageService;

import com.chargebee.core.services.TimeMachineService;

import com.chargebee.core.services.BusinessEntityService;

import com.chargebee.core.services.OfferEventService;

import com.chargebee.core.services.InAppSubscriptionService;

import com.chargebee.core.services.Pc2MigrationService;

import com.chargebee.core.services.CreditNoteService;

import com.chargebee.core.services.CouponSetService;

import com.chargebee.core.services.QuoteService;

import com.chargebee.core.services.Pc2MigrationItemService;

import com.chargebee.core.services.EstimateService;

import com.chargebee.core.services.VariantService;

import com.chargebee.core.services.Pc2MigrationItemFamilyService;

import com.chargebee.core.services.PaymentSourceService;

import com.chargebee.core.services.RecordedPurchaseService;

import com.chargebee.core.services.PlanService;

import com.chargebee.core.services.ExportService;

import com.chargebee.core.services.OrderService;

import com.chargebee.core.services.ItemService;

import com.chargebee.core.services.CustomerEntitlementService;

import com.chargebee.core.services.PersonalizedOfferService;

import com.chargebee.core.services.OmnichannelSubscriptionService;

import com.chargebee.core.services.OmnichannelSubscriptionItemService;

import com.chargebee.core.services.RampService;

import com.chargebee.core.services.OmnichannelOneTimeOrderService;

import com.chargebee.core.services.DifferentialPriceService;

import com.chargebee.core.services.EntitlementService;

import com.chargebee.core.services.AdditionalBillingLogiqService;

import com.chargebee.core.services.SubscriptionSettingService;

import com.chargebee.core.services.SiteMigrationDetailService;

import com.chargebee.core.services.PaymentIntentService;

import com.chargebee.core.services.PaymentScheduleSchemeService;

import com.chargebee.core.services.CardService;

import com.chargebee.core.services.AttachedItemService;

import com.chargebee.core.services.UsageEventService;

import com.chargebee.core.services.PriceVariantService;

import com.chargebee.core.services.FullExportService;

import com.chargebee.core.services.VirtualBankAccountService;

import com.chargebee.core.services.AddonService;

import com.chargebee.core.services.TpSiteUserService;

import com.chargebee.core.services.ConfigurationService;

import com.chargebee.core.services.PricingPageSessionService;

import com.chargebee.core.services.Pc2MigrationItemPriceService;

import com.chargebee.core.services.RuleService;

import com.chargebee.core.services.SubscriptionService;

import com.chargebee.core.services.MediaService;

import com.chargebee.core.services.BusinessProfileService;

import com.chargebee.core.services.PromotionalCreditService;

import com.chargebee.core.services.BrandConfigurationService;

import com.chargebee.core.services.WebhookEndpointService;

import com.chargebee.core.services.FeatureService;

import com.chargebee.core.services.UnbilledChargesSettingService;

import com.chargebee.core.services.CurrencyService;

import com.chargebee.core.services.EventService;

import com.chargebee.core.services.UsageFileService;

import com.chargebee.core.services.NonSubscriptionService;

import com.chargebee.core.services.ResourceMigrationService;

import com.chargebee.core.services.ProductService;

import com.chargebee.core.services.CouponCodeService;

import com.chargebee.core.services.AddressService;

import com.chargebee.core.services.CouponService;

import com.chargebee.core.services.PortalSessionService;

import com.chargebee.core.services.ItemPriceService;

import com.chargebee.core.services.OfferFulfillmentService;

import com.chargebee.core.services.HostedPageService;

import com.chargebee.core.services.PurchaseService;

import com.chargebee.core.services.PaymentVoucherService;

import com.chargebee.core.services.ItemFamilyService;

import com.chargebee.core.services.SubscriptionEntitlementService;

import com.chargebee.core.services.ThirdPartyEntityMappingService;

import com.chargebee.core.services.EntitlementOverrideService;

import com.chargebee.core.services.ThirdPartyConfigurationService;

import com.chargebee.core.services.UnbilledChargeService;

import com.chargebee.core.services.CommentService;

import com.chargebee.core.services.InvoiceService;

import com.chargebee.core.services.TransactionService;

import com.chargebee.core.services.ThirdPartySyncDetailService;

import com.chargebee.core.services.CustomerService;

import com.chargebee.core.services.ItemEntitlementService;

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
  GiftService gift();

  /**
   * Access csv_tax_rule-related operations.
   *
   * @return CsvTaxRuleService instance for fluent API access
   */
  CsvTaxRuleService csvTaxRule();

  /**
   * Access usage-related operations.
   *
   * @return UsageService instance for fluent API access
   */
  UsageService usage();

  /**
   * Access time_machine-related operations.
   *
   * @return TimeMachineService instance for fluent API access
   */
  TimeMachineService timeMachine();

  /**
   * Access business_entity-related operations.
   *
   * @return BusinessEntityService instance for fluent API access
   */
  BusinessEntityService businessEntity();

  /**
   * Access offer_event-related operations.
   *
   * @return OfferEventService instance for fluent API access
   */
  OfferEventService offerEvent();

  /**
   * Access in_app_subscription-related operations.
   *
   * @return InAppSubscriptionService instance for fluent API access
   */
  InAppSubscriptionService inAppSubscription();

  /**
   * Access pc2_migration-related operations.
   *
   * @return Pc2MigrationService instance for fluent API access
   */
  Pc2MigrationService pc2Migration();

  /**
   * Access credit_note-related operations.
   *
   * @return CreditNoteService instance for fluent API access
   */
  CreditNoteService creditNote();

  /**
   * Access coupon_set-related operations.
   *
   * @return CouponSetService instance for fluent API access
   */
  CouponSetService couponSet();

  /**
   * Access quote-related operations.
   *
   * @return QuoteService instance for fluent API access
   */
  QuoteService quote();

  /**
   * Access pc2_migration_item-related operations.
   *
   * @return Pc2MigrationItemService instance for fluent API access
   */
  Pc2MigrationItemService pc2MigrationItem();

  /**
   * Access estimate-related operations.
   *
   * @return EstimateService instance for fluent API access
   */
  EstimateService estimate();

  /**
   * Access variant-related operations.
   *
   * @return VariantService instance for fluent API access
   */
  VariantService variant();

  /**
   * Access pc2_migration_item_family-related operations.
   *
   * @return Pc2MigrationItemFamilyService instance for fluent API access
   */
  Pc2MigrationItemFamilyService pc2MigrationItemFamily();

  /**
   * Access payment_source-related operations.
   *
   * @return PaymentSourceService instance for fluent API access
   */
  PaymentSourceService paymentSource();

  /**
   * Access recorded_purchase-related operations.
   *
   * @return RecordedPurchaseService instance for fluent API access
   */
  RecordedPurchaseService recordedPurchase();

  /**
   * Access plan-related operations.
   *
   * @return PlanService instance for fluent API access
   */
  PlanService plan();

  /**
   * Access export-related operations.
   *
   * @return ExportService instance for fluent API access
   */
  ExportService export();

  /**
   * Access order-related operations.
   *
   * @return OrderService instance for fluent API access
   */
  OrderService order();

  /**
   * Access item-related operations.
   *
   * @return ItemService instance for fluent API access
   */
  ItemService item();

  /**
   * Access customer_entitlement-related operations.
   *
   * @return CustomerEntitlementService instance for fluent API access
   */
  CustomerEntitlementService customerEntitlement();

  /**
   * Access personalized_offer-related operations.
   *
   * @return PersonalizedOfferService instance for fluent API access
   */
  PersonalizedOfferService personalizedOffer();

  /**
   * Access omnichannel_subscription-related operations.
   *
   * @return OmnichannelSubscriptionService instance for fluent API access
   */
  OmnichannelSubscriptionService omnichannelSubscription();

  /**
   * Access omnichannel_subscription_item-related operations.
   *
   * @return OmnichannelSubscriptionItemService instance for fluent API access
   */
  OmnichannelSubscriptionItemService omnichannelSubscriptionItem();

  /**
   * Access ramp-related operations.
   *
   * @return RampService instance for fluent API access
   */
  RampService ramp();

  /**
   * Access omnichannel_one_time_order-related operations.
   *
   * @return OmnichannelOneTimeOrderService instance for fluent API access
   */
  OmnichannelOneTimeOrderService omnichannelOneTimeOrder();

  /**
   * Access differential_price-related operations.
   *
   * @return DifferentialPriceService instance for fluent API access
   */
  DifferentialPriceService differentialPrice();

  /**
   * Access entitlement-related operations.
   *
   * @return EntitlementService instance for fluent API access
   */
  EntitlementService entitlement();

  /**
   * Access additional_billing_logiq-related operations.
   *
   * @return AdditionalBillingLogiqService instance for fluent API access
   */
  AdditionalBillingLogiqService additionalBillingLogiq();

  /**
   * Access subscription_setting-related operations.
   *
   * @return SubscriptionSettingService instance for fluent API access
   */
  SubscriptionSettingService subscriptionSetting();

  /**
   * Access site_migration_detail-related operations.
   *
   * @return SiteMigrationDetailService instance for fluent API access
   */
  SiteMigrationDetailService siteMigrationDetail();

  /**
   * Access payment_intent-related operations.
   *
   * @return PaymentIntentService instance for fluent API access
   */
  PaymentIntentService paymentIntent();

  /**
   * Access payment_schedule_scheme-related operations.
   *
   * @return PaymentScheduleSchemeService instance for fluent API access
   */
  PaymentScheduleSchemeService paymentScheduleScheme();

  /**
   * Access card-related operations.
   *
   * @return CardService instance for fluent API access
   */
  CardService card();

  /**
   * Access attached_item-related operations.
   *
   * @return AttachedItemService instance for fluent API access
   */
  AttachedItemService attachedItem();

  /**
   * Access usage_event-related operations.
   *
   * @return UsageEventService instance for fluent API access
   */
  UsageEventService usageEvent();

  /**
   * Access price_variant-related operations.
   *
   * @return PriceVariantService instance for fluent API access
   */
  PriceVariantService priceVariant();

  /**
   * Access full_export-related operations.
   *
   * @return FullExportService instance for fluent API access
   */
  FullExportService fullExport();

  /**
   * Access virtual_bank_account-related operations.
   *
   * @return VirtualBankAccountService instance for fluent API access
   */
  VirtualBankAccountService virtualBankAccount();

  /**
   * Access addon-related operations.
   *
   * @return AddonService instance for fluent API access
   */
  AddonService addon();

  /**
   * Access tp_site_user-related operations.
   *
   * @return TpSiteUserService instance for fluent API access
   */
  TpSiteUserService tpSiteUser();

  /**
   * Access configuration-related operations.
   *
   * @return ConfigurationService instance for fluent API access
   */
  ConfigurationService configuration();

  /**
   * Access pricing_page_session-related operations.
   *
   * @return PricingPageSessionService instance for fluent API access
   */
  PricingPageSessionService pricingPageSession();

  /**
   * Access pc2_migration_item_price-related operations.
   *
   * @return Pc2MigrationItemPriceService instance for fluent API access
   */
  Pc2MigrationItemPriceService pc2MigrationItemPrice();

  /**
   * Access rule-related operations.
   *
   * @return RuleService instance for fluent API access
   */
  RuleService rule();

  /**
   * Access subscription-related operations.
   *
   * @return SubscriptionService instance for fluent API access
   */
  SubscriptionService subscription();

  /**
   * Access media-related operations.
   *
   * @return MediaService instance for fluent API access
   */
  MediaService media();

  /**
   * Access business_profile-related operations.
   *
   * @return BusinessProfileService instance for fluent API access
   */
  BusinessProfileService businessProfile();

  /**
   * Access promotional_credit-related operations.
   *
   * @return PromotionalCreditService instance for fluent API access
   */
  PromotionalCreditService promotionalCredit();

  /**
   * Access brand_configuration-related operations.
   *
   * @return BrandConfigurationService instance for fluent API access
   */
  BrandConfigurationService brandConfiguration();

  /**
   * Access webhook_endpoint-related operations.
   *
   * @return WebhookEndpointService instance for fluent API access
   */
  WebhookEndpointService webhookEndpoint();

  /**
   * Access feature-related operations.
   *
   * @return FeatureService instance for fluent API access
   */
  FeatureService feature();

  /**
   * Access unbilled_charges_setting-related operations.
   *
   * @return UnbilledChargesSettingService instance for fluent API access
   */
  UnbilledChargesSettingService unbilledChargesSetting();

  /**
   * Access currency-related operations.
   *
   * @return CurrencyService instance for fluent API access
   */
  CurrencyService currency();

  /**
   * Access event-related operations.
   *
   * @return EventService instance for fluent API access
   */
  EventService event();

  /**
   * Access usage_file-related operations.
   *
   * @return UsageFileService instance for fluent API access
   */
  UsageFileService usageFile();

  /**
   * Access non_subscription-related operations.
   *
   * @return NonSubscriptionService instance for fluent API access
   */
  NonSubscriptionService nonSubscription();

  /**
   * Access resource_migration-related operations.
   *
   * @return ResourceMigrationService instance for fluent API access
   */
  ResourceMigrationService resourceMigration();

  /**
   * Access product-related operations.
   *
   * @return ProductService instance for fluent API access
   */
  ProductService product();

  /**
   * Access coupon_code-related operations.
   *
   * @return CouponCodeService instance for fluent API access
   */
  CouponCodeService couponCode();

  /**
   * Access address-related operations.
   *
   * @return AddressService instance for fluent API access
   */
  AddressService address();

  /**
   * Access coupon-related operations.
   *
   * @return CouponService instance for fluent API access
   */
  CouponService coupon();

  /**
   * Access portal_session-related operations.
   *
   * @return PortalSessionService instance for fluent API access
   */
  PortalSessionService portalSession();

  /**
   * Access item_price-related operations.
   *
   * @return ItemPriceService instance for fluent API access
   */
  ItemPriceService itemPrice();

  /**
   * Access offer_fulfillment-related operations.
   *
   * @return OfferFulfillmentService instance for fluent API access
   */
  OfferFulfillmentService offerFulfillment();

  /**
   * Access hosted_page-related operations.
   *
   * @return HostedPageService instance for fluent API access
   */
  HostedPageService hostedPage();

  /**
   * Access purchase-related operations.
   *
   * @return PurchaseService instance for fluent API access
   */
  PurchaseService purchase();

  /**
   * Access payment_voucher-related operations.
   *
   * @return PaymentVoucherService instance for fluent API access
   */
  PaymentVoucherService paymentVoucher();

  /**
   * Access item_family-related operations.
   *
   * @return ItemFamilyService instance for fluent API access
   */
  ItemFamilyService itemFamily();

  /**
   * Access subscription_entitlement-related operations.
   *
   * @return SubscriptionEntitlementService instance for fluent API access
   */
  SubscriptionEntitlementService subscriptionEntitlement();

  /**
   * Access third_party_entity_mapping-related operations.
   *
   * @return ThirdPartyEntityMappingService instance for fluent API access
   */
  ThirdPartyEntityMappingService thirdPartyEntityMapping();

  /**
   * Access entitlement_override-related operations.
   *
   * @return EntitlementOverrideService instance for fluent API access
   */
  EntitlementOverrideService entitlementOverride();

  /**
   * Access third_party_configuration-related operations.
   *
   * @return ThirdPartyConfigurationService instance for fluent API access
   */
  ThirdPartyConfigurationService thirdPartyConfiguration();

  /**
   * Access unbilled_charge-related operations.
   *
   * @return UnbilledChargeService instance for fluent API access
   */
  UnbilledChargeService unbilledCharge();

  /**
   * Access comment-related operations.
   *
   * @return CommentService instance for fluent API access
   */
  CommentService comment();

  /**
   * Access invoice-related operations.
   *
   * @return InvoiceService instance for fluent API access
   */
  InvoiceService invoice();

  /**
   * Access transaction-related operations.
   *
   * @return TransactionService instance for fluent API access
   */
  TransactionService transaction();

  /**
   * Access third_party_sync_detail-related operations.
   *
   * @return ThirdPartySyncDetailService instance for fluent API access
   */
  ThirdPartySyncDetailService thirdPartySyncDetail();

  /**
   * Access customer-related operations.
   *
   * @return CustomerService instance for fluent API access
   */
  CustomerService customer();

  /**
   * Access item_entitlement-related operations.
   *
   * @return ItemEntitlementService instance for fluent API access
   */
  ItemEntitlementService itemEntitlement();
}
