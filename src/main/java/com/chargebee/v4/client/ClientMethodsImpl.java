package com.chargebee.v4.client;

import com.chargebee.v4.core.services.GiftService;

import com.chargebee.v4.core.services.CsvTaxRuleService;

import com.chargebee.v4.core.services.UsageService;

import com.chargebee.v4.core.services.TimeMachineService;

import com.chargebee.v4.core.services.BusinessEntityService;

import com.chargebee.v4.core.services.OfferEventService;

import com.chargebee.v4.core.services.InAppSubscriptionService;

import com.chargebee.v4.core.services.Pc2MigrationService;

import com.chargebee.v4.core.services.CreditNoteService;

import com.chargebee.v4.core.services.CouponSetService;

import com.chargebee.v4.core.services.QuoteService;

import com.chargebee.v4.core.services.Pc2MigrationItemService;

import com.chargebee.v4.core.services.EstimateService;

import com.chargebee.v4.core.services.VariantService;

import com.chargebee.v4.core.services.Pc2MigrationItemFamilyService;

import com.chargebee.v4.core.services.PaymentSourceService;

import com.chargebee.v4.core.services.RecordedPurchaseService;

import com.chargebee.v4.core.services.PlanService;

import com.chargebee.v4.core.services.ExportService;

import com.chargebee.v4.core.services.OrderService;

import com.chargebee.v4.core.services.ItemService;

import com.chargebee.v4.core.services.CustomerEntitlementService;

import com.chargebee.v4.core.services.PersonalizedOfferService;

import com.chargebee.v4.core.services.OmnichannelSubscriptionService;

import com.chargebee.v4.core.services.OmnichannelSubscriptionItemService;

import com.chargebee.v4.core.services.RampService;

import com.chargebee.v4.core.services.OmnichannelOneTimeOrderService;

import com.chargebee.v4.core.services.DifferentialPriceService;

import com.chargebee.v4.core.services.EntitlementService;

import com.chargebee.v4.core.services.AdditionalBillingLogiqService;

import com.chargebee.v4.core.services.SubscriptionSettingService;

import com.chargebee.v4.core.services.SiteMigrationDetailService;

import com.chargebee.v4.core.services.PaymentIntentService;

import com.chargebee.v4.core.services.PaymentScheduleSchemeService;

import com.chargebee.v4.core.services.CardService;

import com.chargebee.v4.core.services.AttachedItemService;

import com.chargebee.v4.core.services.UsageEventService;

import com.chargebee.v4.core.services.PriceVariantService;

import com.chargebee.v4.core.services.FullExportService;

import com.chargebee.v4.core.services.VirtualBankAccountService;

import com.chargebee.v4.core.services.AddonService;

import com.chargebee.v4.core.services.TpSiteUserService;

import com.chargebee.v4.core.services.ConfigurationService;

import com.chargebee.v4.core.services.PricingPageSessionService;

import com.chargebee.v4.core.services.Pc2MigrationItemPriceService;

import com.chargebee.v4.core.services.RuleService;

import com.chargebee.v4.core.services.SubscriptionService;

import com.chargebee.v4.core.services.MediaService;

import com.chargebee.v4.core.services.BusinessProfileService;

import com.chargebee.v4.core.services.PromotionalCreditService;

import com.chargebee.v4.core.services.BrandConfigurationService;

import com.chargebee.v4.core.services.WebhookEndpointService;

import com.chargebee.v4.core.services.FeatureService;

import com.chargebee.v4.core.services.UnbilledChargesSettingService;

import com.chargebee.v4.core.services.CurrencyService;

import com.chargebee.v4.core.services.EventService;

import com.chargebee.v4.core.services.UsageFileService;

import com.chargebee.v4.core.services.NonSubscriptionService;

import com.chargebee.v4.core.services.ResourceMigrationService;

import com.chargebee.v4.core.services.ProductService;

import com.chargebee.v4.core.services.CouponCodeService;

import com.chargebee.v4.core.services.AddressService;

import com.chargebee.v4.core.services.CouponService;

import com.chargebee.v4.core.services.PortalSessionService;

import com.chargebee.v4.core.services.ItemPriceService;

import com.chargebee.v4.core.services.OfferFulfillmentService;

import com.chargebee.v4.core.services.HostedPageService;

import com.chargebee.v4.core.services.PurchaseService;

import com.chargebee.v4.core.services.PaymentVoucherService;

import com.chargebee.v4.core.services.ItemFamilyService;

import com.chargebee.v4.core.services.SubscriptionEntitlementService;

import com.chargebee.v4.core.services.ThirdPartyEntityMappingService;

import com.chargebee.v4.core.services.EntitlementOverrideService;

import com.chargebee.v4.core.services.ThirdPartyConfigurationService;

import com.chargebee.v4.core.services.UnbilledChargeService;

import com.chargebee.v4.core.services.CommentService;

import com.chargebee.v4.core.services.InvoiceService;

import com.chargebee.v4.core.services.TransactionService;

import com.chargebee.v4.core.services.ThirdPartySyncDetailService;

import com.chargebee.v4.core.services.CustomerService;

import com.chargebee.v4.core.services.ItemEntitlementService;

/**
 * Auto-generated implementation of ClientMethods interface. This class provides the actual service
 * method implementations that delegate to ServiceRegistry.
 */
abstract class ClientMethodsImpl implements ClientMethods {

  protected abstract ServiceRegistry getServiceRegistry();

  @Override
  public GiftService gift() {
    return getServiceRegistry().gift();
  }

  @Override
  public CsvTaxRuleService csvTaxRule() {
    return getServiceRegistry().csvTaxRule();
  }

  @Override
  public UsageService usage() {
    return getServiceRegistry().usage();
  }

  @Override
  public TimeMachineService timeMachine() {
    return getServiceRegistry().timeMachine();
  }

  @Override
  public BusinessEntityService businessEntity() {
    return getServiceRegistry().businessEntity();
  }

  @Override
  public OfferEventService offerEvent() {
    return getServiceRegistry().offerEvent();
  }

  @Override
  public InAppSubscriptionService inAppSubscription() {
    return getServiceRegistry().inAppSubscription();
  }

  @Override
  public Pc2MigrationService pc2Migration() {
    return getServiceRegistry().pc2Migration();
  }

  @Override
  public CreditNoteService creditNote() {
    return getServiceRegistry().creditNote();
  }

  @Override
  public CouponSetService couponSet() {
    return getServiceRegistry().couponSet();
  }

  @Override
  public QuoteService quote() {
    return getServiceRegistry().quote();
  }

  @Override
  public Pc2MigrationItemService pc2MigrationItem() {
    return getServiceRegistry().pc2MigrationItem();
  }

  @Override
  public EstimateService estimate() {
    return getServiceRegistry().estimate();
  }

  @Override
  public VariantService variant() {
    return getServiceRegistry().variant();
  }

  @Override
  public Pc2MigrationItemFamilyService pc2MigrationItemFamily() {
    return getServiceRegistry().pc2MigrationItemFamily();
  }

  @Override
  public PaymentSourceService paymentSource() {
    return getServiceRegistry().paymentSource();
  }

  @Override
  public RecordedPurchaseService recordedPurchase() {
    return getServiceRegistry().recordedPurchase();
  }

  @Override
  public PlanService plan() {
    return getServiceRegistry().plan();
  }

  @Override
  public ExportService export() {
    return getServiceRegistry().export();
  }

  @Override
  public OrderService order() {
    return getServiceRegistry().order();
  }

  @Override
  public ItemService item() {
    return getServiceRegistry().item();
  }

  @Override
  public CustomerEntitlementService customerEntitlement() {
    return getServiceRegistry().customerEntitlement();
  }

  @Override
  public PersonalizedOfferService personalizedOffer() {
    return getServiceRegistry().personalizedOffer();
  }

  @Override
  public OmnichannelSubscriptionService omnichannelSubscription() {
    return getServiceRegistry().omnichannelSubscription();
  }

  @Override
  public OmnichannelSubscriptionItemService omnichannelSubscriptionItem() {
    return getServiceRegistry().omnichannelSubscriptionItem();
  }

  @Override
  public RampService ramp() {
    return getServiceRegistry().ramp();
  }

  @Override
  public OmnichannelOneTimeOrderService omnichannelOneTimeOrder() {
    return getServiceRegistry().omnichannelOneTimeOrder();
  }

  @Override
  public DifferentialPriceService differentialPrice() {
    return getServiceRegistry().differentialPrice();
  }

  @Override
  public EntitlementService entitlement() {
    return getServiceRegistry().entitlement();
  }

  @Override
  public AdditionalBillingLogiqService additionalBillingLogiq() {
    return getServiceRegistry().additionalBillingLogiq();
  }

  @Override
  public SubscriptionSettingService subscriptionSetting() {
    return getServiceRegistry().subscriptionSetting();
  }

  @Override
  public SiteMigrationDetailService siteMigrationDetail() {
    return getServiceRegistry().siteMigrationDetail();
  }

  @Override
  public PaymentIntentService paymentIntent() {
    return getServiceRegistry().paymentIntent();
  }

  @Override
  public PaymentScheduleSchemeService paymentScheduleScheme() {
    return getServiceRegistry().paymentScheduleScheme();
  }

  @Override
  public CardService card() {
    return getServiceRegistry().card();
  }

  @Override
  public AttachedItemService attachedItem() {
    return getServiceRegistry().attachedItem();
  }

  @Override
  public UsageEventService usageEvent() {
    return getServiceRegistry().usageEvent();
  }

  @Override
  public PriceVariantService priceVariant() {
    return getServiceRegistry().priceVariant();
  }

  @Override
  public FullExportService fullExport() {
    return getServiceRegistry().fullExport();
  }

  @Override
  public VirtualBankAccountService virtualBankAccount() {
    return getServiceRegistry().virtualBankAccount();
  }

  @Override
  public AddonService addon() {
    return getServiceRegistry().addon();
  }

  @Override
  public TpSiteUserService tpSiteUser() {
    return getServiceRegistry().tpSiteUser();
  }

  @Override
  public ConfigurationService configuration() {
    return getServiceRegistry().configuration();
  }

  @Override
  public PricingPageSessionService pricingPageSession() {
    return getServiceRegistry().pricingPageSession();
  }

  @Override
  public Pc2MigrationItemPriceService pc2MigrationItemPrice() {
    return getServiceRegistry().pc2MigrationItemPrice();
  }

  @Override
  public RuleService rule() {
    return getServiceRegistry().rule();
  }

  @Override
  public SubscriptionService subscription() {
    return getServiceRegistry().subscription();
  }

  @Override
  public MediaService media() {
    return getServiceRegistry().media();
  }

  @Override
  public BusinessProfileService businessProfile() {
    return getServiceRegistry().businessProfile();
  }

  @Override
  public PromotionalCreditService promotionalCredit() {
    return getServiceRegistry().promotionalCredit();
  }

  @Override
  public BrandConfigurationService brandConfiguration() {
    return getServiceRegistry().brandConfiguration();
  }

  @Override
  public WebhookEndpointService webhookEndpoint() {
    return getServiceRegistry().webhookEndpoint();
  }

  @Override
  public FeatureService feature() {
    return getServiceRegistry().feature();
  }

  @Override
  public UnbilledChargesSettingService unbilledChargesSetting() {
    return getServiceRegistry().unbilledChargesSetting();
  }

  @Override
  public CurrencyService currency() {
    return getServiceRegistry().currency();
  }

  @Override
  public EventService event() {
    return getServiceRegistry().event();
  }

  @Override
  public UsageFileService usageFile() {
    return getServiceRegistry().usageFile();
  }

  @Override
  public NonSubscriptionService nonSubscription() {
    return getServiceRegistry().nonSubscription();
  }

  @Override
  public ResourceMigrationService resourceMigration() {
    return getServiceRegistry().resourceMigration();
  }

  @Override
  public ProductService product() {
    return getServiceRegistry().product();
  }

  @Override
  public CouponCodeService couponCode() {
    return getServiceRegistry().couponCode();
  }

  @Override
  public AddressService address() {
    return getServiceRegistry().address();
  }

  @Override
  public CouponService coupon() {
    return getServiceRegistry().coupon();
  }

  @Override
  public PortalSessionService portalSession() {
    return getServiceRegistry().portalSession();
  }

  @Override
  public ItemPriceService itemPrice() {
    return getServiceRegistry().itemPrice();
  }

  @Override
  public OfferFulfillmentService offerFulfillment() {
    return getServiceRegistry().offerFulfillment();
  }

  @Override
  public HostedPageService hostedPage() {
    return getServiceRegistry().hostedPage();
  }

  @Override
  public PurchaseService purchase() {
    return getServiceRegistry().purchase();
  }

  @Override
  public PaymentVoucherService paymentVoucher() {
    return getServiceRegistry().paymentVoucher();
  }

  @Override
  public ItemFamilyService itemFamily() {
    return getServiceRegistry().itemFamily();
  }

  @Override
  public SubscriptionEntitlementService subscriptionEntitlement() {
    return getServiceRegistry().subscriptionEntitlement();
  }

  @Override
  public ThirdPartyEntityMappingService thirdPartyEntityMapping() {
    return getServiceRegistry().thirdPartyEntityMapping();
  }

  @Override
  public EntitlementOverrideService entitlementOverride() {
    return getServiceRegistry().entitlementOverride();
  }

  @Override
  public ThirdPartyConfigurationService thirdPartyConfiguration() {
    return getServiceRegistry().thirdPartyConfiguration();
  }

  @Override
  public UnbilledChargeService unbilledCharge() {
    return getServiceRegistry().unbilledCharge();
  }

  @Override
  public CommentService comment() {
    return getServiceRegistry().comment();
  }

  @Override
  public InvoiceService invoice() {
    return getServiceRegistry().invoice();
  }

  @Override
  public TransactionService transaction() {
    return getServiceRegistry().transaction();
  }

  @Override
  public ThirdPartySyncDetailService thirdPartySyncDetail() {
    return getServiceRegistry().thirdPartySyncDetail();
  }

  @Override
  public CustomerService customer() {
    return getServiceRegistry().customer();
  }

  @Override
  public ItemEntitlementService itemEntitlement() {
    return getServiceRegistry().itemEntitlement();
  }
}
