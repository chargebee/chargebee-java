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
