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
 * Auto-generated implementation of ClientMethods interface. This class provides the actual service
 * method implementations that delegate to ServiceRegistry.
 */
abstract class ClientMethodsImpl implements ClientMethods {

  protected abstract ServiceRegistry getServiceRegistry();

  @Override
  public GiftService gifts() {
    return getServiceRegistry().gifts();
  }

  @Override
  public CsvTaxRuleService csvTaxRules() {
    return getServiceRegistry().csvTaxRules();
  }

  @Override
  public UsageService usages() {
    return getServiceRegistry().usages();
  }

  @Override
  public TimeMachineService timeMachines() {
    return getServiceRegistry().timeMachines();
  }

  @Override
  public BusinessEntityService businessEntities() {
    return getServiceRegistry().businessEntities();
  }

  @Override
  public OfferEventService offerEvents() {
    return getServiceRegistry().offerEvents();
  }

  @Override
  public InAppSubscriptionService inAppSubscriptions() {
    return getServiceRegistry().inAppSubscriptions();
  }

  @Override
  public Pc2MigrationService pc2Migrations() {
    return getServiceRegistry().pc2Migrations();
  }

  @Override
  public CreditNoteService creditNotes() {
    return getServiceRegistry().creditNotes();
  }

  @Override
  public CouponSetService couponSets() {
    return getServiceRegistry().couponSets();
  }

  @Override
  public QuoteService quotes() {
    return getServiceRegistry().quotes();
  }

  @Override
  public Pc2MigrationItemService pc2MigrationItems() {
    return getServiceRegistry().pc2MigrationItems();
  }

  @Override
  public EstimateService estimates() {
    return getServiceRegistry().estimates();
  }

  @Override
  public VariantService variants() {
    return getServiceRegistry().variants();
  }

  @Override
  public Pc2MigrationItemFamilyService pc2MigrationItemFamilies() {
    return getServiceRegistry().pc2MigrationItemFamilies();
  }

  @Override
  public PaymentSourceService paymentSources() {
    return getServiceRegistry().paymentSources();
  }

  @Override
  public RecordedPurchaseService recordedPurchases() {
    return getServiceRegistry().recordedPurchases();
  }

  @Override
  public PlanService plans() {
    return getServiceRegistry().plans();
  }

  @Override
  public ExportService exports() {
    return getServiceRegistry().exports();
  }

  @Override
  public OrderService orders() {
    return getServiceRegistry().orders();
  }

  @Override
  public ItemService items() {
    return getServiceRegistry().items();
  }

  @Override
  public CustomerEntitlementService customerEntitlements() {
    return getServiceRegistry().customerEntitlements();
  }

  @Override
  public PersonalizedOfferService personalizedOffers() {
    return getServiceRegistry().personalizedOffers();
  }

  @Override
  public OmnichannelSubscriptionService omnichannelSubscriptions() {
    return getServiceRegistry().omnichannelSubscriptions();
  }

  @Override
  public OmnichannelSubscriptionItemService omnichannelSubscriptionItems() {
    return getServiceRegistry().omnichannelSubscriptionItems();
  }

  @Override
  public RampService ramps() {
    return getServiceRegistry().ramps();
  }

  @Override
  public OmnichannelOneTimeOrderService omnichannelOneTimeOrders() {
    return getServiceRegistry().omnichannelOneTimeOrders();
  }

  @Override
  public DifferentialPriceService differentialPrices() {
    return getServiceRegistry().differentialPrices();
  }

  @Override
  public EntitlementService entitlements() {
    return getServiceRegistry().entitlements();
  }

  @Override
  public AdditionalBillingLogiqService additionalBillingLogiqs() {
    return getServiceRegistry().additionalBillingLogiqs();
  }

  @Override
  public SubscriptionSettingService subscriptionSettings() {
    return getServiceRegistry().subscriptionSettings();
  }

  @Override
  public SiteMigrationDetailService siteMigrationDetails() {
    return getServiceRegistry().siteMigrationDetails();
  }

  @Override
  public PaymentIntentService paymentIntents() {
    return getServiceRegistry().paymentIntents();
  }

  @Override
  public PaymentScheduleSchemeService paymentScheduleSchemes() {
    return getServiceRegistry().paymentScheduleSchemes();
  }

  @Override
  public CardService cards() {
    return getServiceRegistry().cards();
  }

  @Override
  public AttachedItemService attachedItems() {
    return getServiceRegistry().attachedItems();
  }

  @Override
  public UsageEventService usageEvents() {
    return getServiceRegistry().usageEvents();
  }

  @Override
  public PriceVariantService priceVariants() {
    return getServiceRegistry().priceVariants();
  }

  @Override
  public FullExportService fullExports() {
    return getServiceRegistry().fullExports();
  }

  @Override
  public VirtualBankAccountService virtualBankAccounts() {
    return getServiceRegistry().virtualBankAccounts();
  }

  @Override
  public AddonService addons() {
    return getServiceRegistry().addons();
  }

  @Override
  public TpSiteUserService tpSiteUsers() {
    return getServiceRegistry().tpSiteUsers();
  }

  @Override
  public ConfigurationService configurations() {
    return getServiceRegistry().configurations();
  }

  @Override
  public PricingPageSessionService pricingPageSessions() {
    return getServiceRegistry().pricingPageSessions();
  }

  @Override
  public Pc2MigrationItemPriceService pc2MigrationItemPrices() {
    return getServiceRegistry().pc2MigrationItemPrices();
  }

  @Override
  public RuleService rules() {
    return getServiceRegistry().rules();
  }

  @Override
  public SubscriptionService subscriptions() {
    return getServiceRegistry().subscriptions();
  }

  @Override
  public MediaService medias() {
    return getServiceRegistry().medias();
  }

  @Override
  public BusinessProfileService businessProfiles() {
    return getServiceRegistry().businessProfiles();
  }

  @Override
  public PromotionalCreditService promotionalCredits() {
    return getServiceRegistry().promotionalCredits();
  }

  @Override
  public BrandConfigurationService brandConfigurations() {
    return getServiceRegistry().brandConfigurations();
  }

  @Override
  public WebhookEndpointService webhookEndpoints() {
    return getServiceRegistry().webhookEndpoints();
  }

  @Override
  public FeatureService features() {
    return getServiceRegistry().features();
  }

  @Override
  public UnbilledChargesSettingService unbilledChargesSettings() {
    return getServiceRegistry().unbilledChargesSettings();
  }

  @Override
  public CurrencyService currencies() {
    return getServiceRegistry().currencies();
  }

  @Override
  public EventService events() {
    return getServiceRegistry().events();
  }

  @Override
  public UsageFileService usageFiles() {
    return getServiceRegistry().usageFiles();
  }

  @Override
  public NonSubscriptionService nonSubscriptions() {
    return getServiceRegistry().nonSubscriptions();
  }

  @Override
  public ResourceMigrationService resourceMigrations() {
    return getServiceRegistry().resourceMigrations();
  }

  @Override
  public ProductService products() {
    return getServiceRegistry().products();
  }

  @Override
  public CouponCodeService couponCodes() {
    return getServiceRegistry().couponCodes();
  }

  @Override
  public AddressService addresses() {
    return getServiceRegistry().addresses();
  }

  @Override
  public CouponService coupons() {
    return getServiceRegistry().coupons();
  }

  @Override
  public PortalSessionService portalSessions() {
    return getServiceRegistry().portalSessions();
  }

  @Override
  public ItemPriceService itemPrices() {
    return getServiceRegistry().itemPrices();
  }

  @Override
  public OfferFulfillmentService offerFulfillments() {
    return getServiceRegistry().offerFulfillments();
  }

  @Override
  public HostedPageService hostedPages() {
    return getServiceRegistry().hostedPages();
  }

  @Override
  public PurchaseService purchases() {
    return getServiceRegistry().purchases();
  }

  @Override
  public PaymentVoucherService paymentVouchers() {
    return getServiceRegistry().paymentVouchers();
  }

  @Override
  public ItemFamilyService itemFamilies() {
    return getServiceRegistry().itemFamilies();
  }

  @Override
  public SubscriptionEntitlementService subscriptionEntitlements() {
    return getServiceRegistry().subscriptionEntitlements();
  }

  @Override
  public ThirdPartyEntityMappingService thirdPartyEntityMappings() {
    return getServiceRegistry().thirdPartyEntityMappings();
  }

  @Override
  public EntitlementOverrideService entitlementOverrides() {
    return getServiceRegistry().entitlementOverrides();
  }

  @Override
  public ThirdPartyConfigurationService thirdPartyConfigurations() {
    return getServiceRegistry().thirdPartyConfigurations();
  }

  @Override
  public UnbilledChargeService unbilledCharges() {
    return getServiceRegistry().unbilledCharges();
  }

  @Override
  public CommentService comments() {
    return getServiceRegistry().comments();
  }

  @Override
  public InvoiceService invoices() {
    return getServiceRegistry().invoices();
  }

  @Override
  public TransactionService transactions() {
    return getServiceRegistry().transactions();
  }

  @Override
  public ThirdPartySyncDetailService thirdPartySyncDetails() {
    return getServiceRegistry().thirdPartySyncDetails();
  }

  @Override
  public CustomerService customers() {
    return getServiceRegistry().customers();
  }

  @Override
  public ItemEntitlementService itemEntitlements() {
    return getServiceRegistry().itemEntitlements();
  }
}
