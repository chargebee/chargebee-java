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
 * Auto-generated service registry for lazy initialization of all Chargebee services. This class
 * handles the thread-safe lazy loading of service instances.
 */
final class ServiceRegistry {
  private final ChargebeeClient client;

  private volatile GiftService giftService;

  private volatile CsvTaxRuleService csvTaxRuleService;

  private volatile UsageService usageService;

  private volatile TimeMachineService timeMachineService;

  private volatile BusinessEntityService businessEntityService;

  private volatile OfferEventService offerEventService;

  private volatile InAppSubscriptionService inAppSubscriptionService;

  private volatile Pc2MigrationService pc2MigrationService;

  private volatile CreditNoteService creditNoteService;

  private volatile CouponSetService couponSetService;

  private volatile QuoteService quoteService;

  private volatile Pc2MigrationItemService pc2MigrationItemService;

  private volatile EstimateService estimateService;

  private volatile VariantService variantService;

  private volatile Pc2MigrationItemFamilyService pc2MigrationItemFamilyService;

  private volatile PaymentSourceService paymentSourceService;

  private volatile RecordedPurchaseService recordedPurchaseService;

  private volatile PlanService planService;

  private volatile ExportService exportService;

  private volatile OrderService orderService;

  private volatile ItemService itemService;

  private volatile CustomerEntitlementService customerEntitlementService;

  private volatile PersonalizedOfferService personalizedOfferService;

  private volatile OmnichannelSubscriptionService omnichannelSubscriptionService;

  private volatile OmnichannelSubscriptionItemService omnichannelSubscriptionItemService;

  private volatile RampService rampService;

  private volatile OmnichannelOneTimeOrderService omnichannelOneTimeOrderService;

  private volatile DifferentialPriceService differentialPriceService;

  private volatile EntitlementService entitlementService;

  private volatile AdditionalBillingLogiqService additionalBillingLogiqService;

  private volatile SubscriptionSettingService subscriptionSettingService;

  private volatile SiteMigrationDetailService siteMigrationDetailService;

  private volatile PaymentIntentService paymentIntentService;

  private volatile PaymentScheduleSchemeService paymentScheduleSchemeService;

  private volatile CardService cardService;

  private volatile AttachedItemService attachedItemService;

  private volatile UsageEventService usageEventService;

  private volatile PriceVariantService priceVariantService;

  private volatile FullExportService fullExportService;

  private volatile VirtualBankAccountService virtualBankAccountService;

  private volatile AddonService addonService;

  private volatile TpSiteUserService tpSiteUserService;

  private volatile ConfigurationService configurationService;

  private volatile PricingPageSessionService pricingPageSessionService;

  private volatile Pc2MigrationItemPriceService pc2MigrationItemPriceService;

  private volatile RuleService ruleService;

  private volatile SubscriptionService subscriptionService;

  private volatile MediaService mediaService;

  private volatile BusinessProfileService businessProfileService;

  private volatile PromotionalCreditService promotionalCreditService;

  private volatile BrandConfigurationService brandConfigurationService;

  private volatile WebhookEndpointService webhookEndpointService;

  private volatile FeatureService featureService;

  private volatile UnbilledChargesSettingService unbilledChargesSettingService;

  private volatile CurrencyService currencyService;

  private volatile EventService eventService;

  private volatile UsageFileService usageFileService;

  private volatile NonSubscriptionService nonSubscriptionService;

  private volatile ResourceMigrationService resourceMigrationService;

  private volatile ProductService productService;

  private volatile CouponCodeService couponCodeService;

  private volatile AddressService addressService;

  private volatile CouponService couponService;

  private volatile PortalSessionService portalSessionService;

  private volatile ItemPriceService itemPriceService;

  private volatile OfferFulfillmentService offerFulfillmentService;

  private volatile HostedPageService hostedPageService;

  private volatile PurchaseService purchaseService;

  private volatile PaymentVoucherService paymentVoucherService;

  private volatile ItemFamilyService itemFamilyService;

  private volatile SubscriptionEntitlementService subscriptionEntitlementService;

  private volatile ThirdPartyEntityMappingService thirdPartyEntityMappingService;

  private volatile EntitlementOverrideService entitlementOverrideService;

  private volatile ThirdPartyConfigurationService thirdPartyConfigurationService;

  private volatile UnbilledChargeService unbilledChargeService;

  private volatile CommentService commentService;

  private volatile InvoiceService invoiceService;

  private volatile TransactionService transactionService;

  private volatile ThirdPartySyncDetailService thirdPartySyncDetailService;

  private volatile CustomerService customerService;

  private volatile ItemEntitlementService itemEntitlementService;

  ServiceRegistry(ChargebeeClient client) {
    this.client = client;
  }

  /**
   * Get or create the GiftService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  GiftService gifts() {
    if (giftService == null) {
      synchronized (this) {
        if (giftService == null) {
          giftService = new GiftService(client);
        }
      }
    }
    return giftService;
  }

  /**
   * Get or create the CsvTaxRuleService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  CsvTaxRuleService csvTaxRules() {
    if (csvTaxRuleService == null) {
      synchronized (this) {
        if (csvTaxRuleService == null) {
          csvTaxRuleService = new CsvTaxRuleService(client);
        }
      }
    }
    return csvTaxRuleService;
  }

  /**
   * Get or create the UsageService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  UsageService usages() {
    if (usageService == null) {
      synchronized (this) {
        if (usageService == null) {
          usageService = new UsageService(client);
        }
      }
    }
    return usageService;
  }

  /**
   * Get or create the TimeMachineService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  TimeMachineService timeMachines() {
    if (timeMachineService == null) {
      synchronized (this) {
        if (timeMachineService == null) {
          timeMachineService = new TimeMachineService(client);
        }
      }
    }
    return timeMachineService;
  }

  /**
   * Get or create the BusinessEntityService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  BusinessEntityService businessEntities() {
    if (businessEntityService == null) {
      synchronized (this) {
        if (businessEntityService == null) {
          businessEntityService = new BusinessEntityService(client);
        }
      }
    }
    return businessEntityService;
  }

  /**
   * Get or create the OfferEventService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  OfferEventService offerEvents() {
    if (offerEventService == null) {
      synchronized (this) {
        if (offerEventService == null) {
          offerEventService = new OfferEventService(client);
        }
      }
    }
    return offerEventService;
  }

  /**
   * Get or create the InAppSubscriptionService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  InAppSubscriptionService inAppSubscriptions() {
    if (inAppSubscriptionService == null) {
      synchronized (this) {
        if (inAppSubscriptionService == null) {
          inAppSubscriptionService = new InAppSubscriptionService(client);
        }
      }
    }
    return inAppSubscriptionService;
  }

  /**
   * Get or create the Pc2MigrationService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  Pc2MigrationService pc2Migrations() {
    if (pc2MigrationService == null) {
      synchronized (this) {
        if (pc2MigrationService == null) {
          pc2MigrationService = new Pc2MigrationService(client);
        }
      }
    }
    return pc2MigrationService;
  }

  /**
   * Get or create the CreditNoteService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  CreditNoteService creditNotes() {
    if (creditNoteService == null) {
      synchronized (this) {
        if (creditNoteService == null) {
          creditNoteService = new CreditNoteService(client);
        }
      }
    }
    return creditNoteService;
  }

  /**
   * Get or create the CouponSetService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  CouponSetService couponSets() {
    if (couponSetService == null) {
      synchronized (this) {
        if (couponSetService == null) {
          couponSetService = new CouponSetService(client);
        }
      }
    }
    return couponSetService;
  }

  /**
   * Get or create the QuoteService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  QuoteService quotes() {
    if (quoteService == null) {
      synchronized (this) {
        if (quoteService == null) {
          quoteService = new QuoteService(client);
        }
      }
    }
    return quoteService;
  }

  /**
   * Get or create the Pc2MigrationItemService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  Pc2MigrationItemService pc2MigrationItems() {
    if (pc2MigrationItemService == null) {
      synchronized (this) {
        if (pc2MigrationItemService == null) {
          pc2MigrationItemService = new Pc2MigrationItemService(client);
        }
      }
    }
    return pc2MigrationItemService;
  }

  /**
   * Get or create the EstimateService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  EstimateService estimates() {
    if (estimateService == null) {
      synchronized (this) {
        if (estimateService == null) {
          estimateService = new EstimateService(client);
        }
      }
    }
    return estimateService;
  }

  /**
   * Get or create the VariantService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  VariantService variants() {
    if (variantService == null) {
      synchronized (this) {
        if (variantService == null) {
          variantService = new VariantService(client);
        }
      }
    }
    return variantService;
  }

  /**
   * Get or create the Pc2MigrationItemFamilyService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  Pc2MigrationItemFamilyService pc2MigrationItemFamilies() {
    if (pc2MigrationItemFamilyService == null) {
      synchronized (this) {
        if (pc2MigrationItemFamilyService == null) {
          pc2MigrationItemFamilyService = new Pc2MigrationItemFamilyService(client);
        }
      }
    }
    return pc2MigrationItemFamilyService;
  }

  /**
   * Get or create the PaymentSourceService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  PaymentSourceService paymentSources() {
    if (paymentSourceService == null) {
      synchronized (this) {
        if (paymentSourceService == null) {
          paymentSourceService = new PaymentSourceService(client);
        }
      }
    }
    return paymentSourceService;
  }

  /**
   * Get or create the RecordedPurchaseService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  RecordedPurchaseService recordedPurchases() {
    if (recordedPurchaseService == null) {
      synchronized (this) {
        if (recordedPurchaseService == null) {
          recordedPurchaseService = new RecordedPurchaseService(client);
        }
      }
    }
    return recordedPurchaseService;
  }

  /**
   * Get or create the PlanService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  PlanService plans() {
    if (planService == null) {
      synchronized (this) {
        if (planService == null) {
          planService = new PlanService(client);
        }
      }
    }
    return planService;
  }

  /**
   * Get or create the ExportService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  ExportService exports() {
    if (exportService == null) {
      synchronized (this) {
        if (exportService == null) {
          exportService = new ExportService(client);
        }
      }
    }
    return exportService;
  }

  /**
   * Get or create the OrderService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  OrderService orders() {
    if (orderService == null) {
      synchronized (this) {
        if (orderService == null) {
          orderService = new OrderService(client);
        }
      }
    }
    return orderService;
  }

  /**
   * Get or create the ItemService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  ItemService items() {
    if (itemService == null) {
      synchronized (this) {
        if (itemService == null) {
          itemService = new ItemService(client);
        }
      }
    }
    return itemService;
  }

  /**
   * Get or create the CustomerEntitlementService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  CustomerEntitlementService customerEntitlements() {
    if (customerEntitlementService == null) {
      synchronized (this) {
        if (customerEntitlementService == null) {
          customerEntitlementService = new CustomerEntitlementService(client);
        }
      }
    }
    return customerEntitlementService;
  }

  /**
   * Get or create the PersonalizedOfferService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  PersonalizedOfferService personalizedOffers() {
    if (personalizedOfferService == null) {
      synchronized (this) {
        if (personalizedOfferService == null) {
          personalizedOfferService = new PersonalizedOfferService(client);
        }
      }
    }
    return personalizedOfferService;
  }

  /**
   * Get or create the OmnichannelSubscriptionService instance. Thread-safe lazy initialization
   * using double-checked locking.
   */
  OmnichannelSubscriptionService omnichannelSubscriptions() {
    if (omnichannelSubscriptionService == null) {
      synchronized (this) {
        if (omnichannelSubscriptionService == null) {
          omnichannelSubscriptionService = new OmnichannelSubscriptionService(client);
        }
      }
    }
    return omnichannelSubscriptionService;
  }

  /**
   * Get or create the OmnichannelSubscriptionItemService instance. Thread-safe lazy initialization
   * using double-checked locking.
   */
  OmnichannelSubscriptionItemService omnichannelSubscriptionItems() {
    if (omnichannelSubscriptionItemService == null) {
      synchronized (this) {
        if (omnichannelSubscriptionItemService == null) {
          omnichannelSubscriptionItemService = new OmnichannelSubscriptionItemService(client);
        }
      }
    }
    return omnichannelSubscriptionItemService;
  }

  /**
   * Get or create the RampService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  RampService ramps() {
    if (rampService == null) {
      synchronized (this) {
        if (rampService == null) {
          rampService = new RampService(client);
        }
      }
    }
    return rampService;
  }

  /**
   * Get or create the OmnichannelOneTimeOrderService instance. Thread-safe lazy initialization
   * using double-checked locking.
   */
  OmnichannelOneTimeOrderService omnichannelOneTimeOrders() {
    if (omnichannelOneTimeOrderService == null) {
      synchronized (this) {
        if (omnichannelOneTimeOrderService == null) {
          omnichannelOneTimeOrderService = new OmnichannelOneTimeOrderService(client);
        }
      }
    }
    return omnichannelOneTimeOrderService;
  }

  /**
   * Get or create the DifferentialPriceService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  DifferentialPriceService differentialPrices() {
    if (differentialPriceService == null) {
      synchronized (this) {
        if (differentialPriceService == null) {
          differentialPriceService = new DifferentialPriceService(client);
        }
      }
    }
    return differentialPriceService;
  }

  /**
   * Get or create the EntitlementService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  EntitlementService entitlements() {
    if (entitlementService == null) {
      synchronized (this) {
        if (entitlementService == null) {
          entitlementService = new EntitlementService(client);
        }
      }
    }
    return entitlementService;
  }

  /**
   * Get or create the AdditionalBillingLogiqService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  AdditionalBillingLogiqService additionalBillingLogiqs() {
    if (additionalBillingLogiqService == null) {
      synchronized (this) {
        if (additionalBillingLogiqService == null) {
          additionalBillingLogiqService = new AdditionalBillingLogiqService(client);
        }
      }
    }
    return additionalBillingLogiqService;
  }

  /**
   * Get or create the SubscriptionSettingService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  SubscriptionSettingService subscriptionSettings() {
    if (subscriptionSettingService == null) {
      synchronized (this) {
        if (subscriptionSettingService == null) {
          subscriptionSettingService = new SubscriptionSettingService(client);
        }
      }
    }
    return subscriptionSettingService;
  }

  /**
   * Get or create the SiteMigrationDetailService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  SiteMigrationDetailService siteMigrationDetails() {
    if (siteMigrationDetailService == null) {
      synchronized (this) {
        if (siteMigrationDetailService == null) {
          siteMigrationDetailService = new SiteMigrationDetailService(client);
        }
      }
    }
    return siteMigrationDetailService;
  }

  /**
   * Get or create the PaymentIntentService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  PaymentIntentService paymentIntents() {
    if (paymentIntentService == null) {
      synchronized (this) {
        if (paymentIntentService == null) {
          paymentIntentService = new PaymentIntentService(client);
        }
      }
    }
    return paymentIntentService;
  }

  /**
   * Get or create the PaymentScheduleSchemeService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  PaymentScheduleSchemeService paymentScheduleSchemes() {
    if (paymentScheduleSchemeService == null) {
      synchronized (this) {
        if (paymentScheduleSchemeService == null) {
          paymentScheduleSchemeService = new PaymentScheduleSchemeService(client);
        }
      }
    }
    return paymentScheduleSchemeService;
  }

  /**
   * Get or create the CardService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  CardService cards() {
    if (cardService == null) {
      synchronized (this) {
        if (cardService == null) {
          cardService = new CardService(client);
        }
      }
    }
    return cardService;
  }

  /**
   * Get or create the AttachedItemService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  AttachedItemService attachedItems() {
    if (attachedItemService == null) {
      synchronized (this) {
        if (attachedItemService == null) {
          attachedItemService = new AttachedItemService(client);
        }
      }
    }
    return attachedItemService;
  }

  /**
   * Get or create the UsageEventService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  UsageEventService usageEvents() {
    if (usageEventService == null) {
      synchronized (this) {
        if (usageEventService == null) {
          usageEventService = new UsageEventService(client);
        }
      }
    }
    return usageEventService;
  }

  /**
   * Get or create the PriceVariantService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  PriceVariantService priceVariants() {
    if (priceVariantService == null) {
      synchronized (this) {
        if (priceVariantService == null) {
          priceVariantService = new PriceVariantService(client);
        }
      }
    }
    return priceVariantService;
  }

  /**
   * Get or create the FullExportService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  FullExportService fullExports() {
    if (fullExportService == null) {
      synchronized (this) {
        if (fullExportService == null) {
          fullExportService = new FullExportService(client);
        }
      }
    }
    return fullExportService;
  }

  /**
   * Get or create the VirtualBankAccountService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  VirtualBankAccountService virtualBankAccounts() {
    if (virtualBankAccountService == null) {
      synchronized (this) {
        if (virtualBankAccountService == null) {
          virtualBankAccountService = new VirtualBankAccountService(client);
        }
      }
    }
    return virtualBankAccountService;
  }

  /**
   * Get or create the AddonService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  AddonService addons() {
    if (addonService == null) {
      synchronized (this) {
        if (addonService == null) {
          addonService = new AddonService(client);
        }
      }
    }
    return addonService;
  }

  /**
   * Get or create the TpSiteUserService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  TpSiteUserService tpSiteUsers() {
    if (tpSiteUserService == null) {
      synchronized (this) {
        if (tpSiteUserService == null) {
          tpSiteUserService = new TpSiteUserService(client);
        }
      }
    }
    return tpSiteUserService;
  }

  /**
   * Get or create the ConfigurationService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  ConfigurationService configurations() {
    if (configurationService == null) {
      synchronized (this) {
        if (configurationService == null) {
          configurationService = new ConfigurationService(client);
        }
      }
    }
    return configurationService;
  }

  /**
   * Get or create the PricingPageSessionService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  PricingPageSessionService pricingPageSessions() {
    if (pricingPageSessionService == null) {
      synchronized (this) {
        if (pricingPageSessionService == null) {
          pricingPageSessionService = new PricingPageSessionService(client);
        }
      }
    }
    return pricingPageSessionService;
  }

  /**
   * Get or create the Pc2MigrationItemPriceService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  Pc2MigrationItemPriceService pc2MigrationItemPrices() {
    if (pc2MigrationItemPriceService == null) {
      synchronized (this) {
        if (pc2MigrationItemPriceService == null) {
          pc2MigrationItemPriceService = new Pc2MigrationItemPriceService(client);
        }
      }
    }
    return pc2MigrationItemPriceService;
  }

  /**
   * Get or create the RuleService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  RuleService rules() {
    if (ruleService == null) {
      synchronized (this) {
        if (ruleService == null) {
          ruleService = new RuleService(client);
        }
      }
    }
    return ruleService;
  }

  /**
   * Get or create the SubscriptionService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  SubscriptionService subscriptions() {
    if (subscriptionService == null) {
      synchronized (this) {
        if (subscriptionService == null) {
          subscriptionService = new SubscriptionService(client);
        }
      }
    }
    return subscriptionService;
  }

  /**
   * Get or create the MediaService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  MediaService medias() {
    if (mediaService == null) {
      synchronized (this) {
        if (mediaService == null) {
          mediaService = new MediaService(client);
        }
      }
    }
    return mediaService;
  }

  /**
   * Get or create the BusinessProfileService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  BusinessProfileService businessProfiles() {
    if (businessProfileService == null) {
      synchronized (this) {
        if (businessProfileService == null) {
          businessProfileService = new BusinessProfileService(client);
        }
      }
    }
    return businessProfileService;
  }

  /**
   * Get or create the PromotionalCreditService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  PromotionalCreditService promotionalCredits() {
    if (promotionalCreditService == null) {
      synchronized (this) {
        if (promotionalCreditService == null) {
          promotionalCreditService = new PromotionalCreditService(client);
        }
      }
    }
    return promotionalCreditService;
  }

  /**
   * Get or create the BrandConfigurationService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  BrandConfigurationService brandConfigurations() {
    if (brandConfigurationService == null) {
      synchronized (this) {
        if (brandConfigurationService == null) {
          brandConfigurationService = new BrandConfigurationService(client);
        }
      }
    }
    return brandConfigurationService;
  }

  /**
   * Get or create the WebhookEndpointService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  WebhookEndpointService webhookEndpoints() {
    if (webhookEndpointService == null) {
      synchronized (this) {
        if (webhookEndpointService == null) {
          webhookEndpointService = new WebhookEndpointService(client);
        }
      }
    }
    return webhookEndpointService;
  }

  /**
   * Get or create the FeatureService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  FeatureService features() {
    if (featureService == null) {
      synchronized (this) {
        if (featureService == null) {
          featureService = new FeatureService(client);
        }
      }
    }
    return featureService;
  }

  /**
   * Get or create the UnbilledChargesSettingService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  UnbilledChargesSettingService unbilledChargesSettings() {
    if (unbilledChargesSettingService == null) {
      synchronized (this) {
        if (unbilledChargesSettingService == null) {
          unbilledChargesSettingService = new UnbilledChargesSettingService(client);
        }
      }
    }
    return unbilledChargesSettingService;
  }

  /**
   * Get or create the CurrencyService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  CurrencyService currencies() {
    if (currencyService == null) {
      synchronized (this) {
        if (currencyService == null) {
          currencyService = new CurrencyService(client);
        }
      }
    }
    return currencyService;
  }

  /**
   * Get or create the EventService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  EventService events() {
    if (eventService == null) {
      synchronized (this) {
        if (eventService == null) {
          eventService = new EventService(client);
        }
      }
    }
    return eventService;
  }

  /**
   * Get or create the UsageFileService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  UsageFileService usageFiles() {
    if (usageFileService == null) {
      synchronized (this) {
        if (usageFileService == null) {
          usageFileService = new UsageFileService(client);
        }
      }
    }
    return usageFileService;
  }

  /**
   * Get or create the NonSubscriptionService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  NonSubscriptionService nonSubscriptions() {
    if (nonSubscriptionService == null) {
      synchronized (this) {
        if (nonSubscriptionService == null) {
          nonSubscriptionService = new NonSubscriptionService(client);
        }
      }
    }
    return nonSubscriptionService;
  }

  /**
   * Get or create the ResourceMigrationService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  ResourceMigrationService resourceMigrations() {
    if (resourceMigrationService == null) {
      synchronized (this) {
        if (resourceMigrationService == null) {
          resourceMigrationService = new ResourceMigrationService(client);
        }
      }
    }
    return resourceMigrationService;
  }

  /**
   * Get or create the ProductService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  ProductService products() {
    if (productService == null) {
      synchronized (this) {
        if (productService == null) {
          productService = new ProductService(client);
        }
      }
    }
    return productService;
  }

  /**
   * Get or create the CouponCodeService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  CouponCodeService couponCodes() {
    if (couponCodeService == null) {
      synchronized (this) {
        if (couponCodeService == null) {
          couponCodeService = new CouponCodeService(client);
        }
      }
    }
    return couponCodeService;
  }

  /**
   * Get or create the AddressService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  AddressService addresses() {
    if (addressService == null) {
      synchronized (this) {
        if (addressService == null) {
          addressService = new AddressService(client);
        }
      }
    }
    return addressService;
  }

  /**
   * Get or create the CouponService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  CouponService coupons() {
    if (couponService == null) {
      synchronized (this) {
        if (couponService == null) {
          couponService = new CouponService(client);
        }
      }
    }
    return couponService;
  }

  /**
   * Get or create the PortalSessionService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  PortalSessionService portalSessions() {
    if (portalSessionService == null) {
      synchronized (this) {
        if (portalSessionService == null) {
          portalSessionService = new PortalSessionService(client);
        }
      }
    }
    return portalSessionService;
  }

  /**
   * Get or create the ItemPriceService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  ItemPriceService itemPrices() {
    if (itemPriceService == null) {
      synchronized (this) {
        if (itemPriceService == null) {
          itemPriceService = new ItemPriceService(client);
        }
      }
    }
    return itemPriceService;
  }

  /**
   * Get or create the OfferFulfillmentService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  OfferFulfillmentService offerFulfillments() {
    if (offerFulfillmentService == null) {
      synchronized (this) {
        if (offerFulfillmentService == null) {
          offerFulfillmentService = new OfferFulfillmentService(client);
        }
      }
    }
    return offerFulfillmentService;
  }

  /**
   * Get or create the HostedPageService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  HostedPageService hostedPages() {
    if (hostedPageService == null) {
      synchronized (this) {
        if (hostedPageService == null) {
          hostedPageService = new HostedPageService(client);
        }
      }
    }
    return hostedPageService;
  }

  /**
   * Get or create the PurchaseService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  PurchaseService purchases() {
    if (purchaseService == null) {
      synchronized (this) {
        if (purchaseService == null) {
          purchaseService = new PurchaseService(client);
        }
      }
    }
    return purchaseService;
  }

  /**
   * Get or create the PaymentVoucherService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  PaymentVoucherService paymentVouchers() {
    if (paymentVoucherService == null) {
      synchronized (this) {
        if (paymentVoucherService == null) {
          paymentVoucherService = new PaymentVoucherService(client);
        }
      }
    }
    return paymentVoucherService;
  }

  /**
   * Get or create the ItemFamilyService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  ItemFamilyService itemFamilies() {
    if (itemFamilyService == null) {
      synchronized (this) {
        if (itemFamilyService == null) {
          itemFamilyService = new ItemFamilyService(client);
        }
      }
    }
    return itemFamilyService;
  }

  /**
   * Get or create the SubscriptionEntitlementService instance. Thread-safe lazy initialization
   * using double-checked locking.
   */
  SubscriptionEntitlementService subscriptionEntitlements() {
    if (subscriptionEntitlementService == null) {
      synchronized (this) {
        if (subscriptionEntitlementService == null) {
          subscriptionEntitlementService = new SubscriptionEntitlementService(client);
        }
      }
    }
    return subscriptionEntitlementService;
  }

  /**
   * Get or create the ThirdPartyEntityMappingService instance. Thread-safe lazy initialization
   * using double-checked locking.
   */
  ThirdPartyEntityMappingService thirdPartyEntityMappings() {
    if (thirdPartyEntityMappingService == null) {
      synchronized (this) {
        if (thirdPartyEntityMappingService == null) {
          thirdPartyEntityMappingService = new ThirdPartyEntityMappingService(client);
        }
      }
    }
    return thirdPartyEntityMappingService;
  }

  /**
   * Get or create the EntitlementOverrideService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  EntitlementOverrideService entitlementOverrides() {
    if (entitlementOverrideService == null) {
      synchronized (this) {
        if (entitlementOverrideService == null) {
          entitlementOverrideService = new EntitlementOverrideService(client);
        }
      }
    }
    return entitlementOverrideService;
  }

  /**
   * Get or create the ThirdPartyConfigurationService instance. Thread-safe lazy initialization
   * using double-checked locking.
   */
  ThirdPartyConfigurationService thirdPartyConfigurations() {
    if (thirdPartyConfigurationService == null) {
      synchronized (this) {
        if (thirdPartyConfigurationService == null) {
          thirdPartyConfigurationService = new ThirdPartyConfigurationService(client);
        }
      }
    }
    return thirdPartyConfigurationService;
  }

  /**
   * Get or create the UnbilledChargeService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  UnbilledChargeService unbilledCharges() {
    if (unbilledChargeService == null) {
      synchronized (this) {
        if (unbilledChargeService == null) {
          unbilledChargeService = new UnbilledChargeService(client);
        }
      }
    }
    return unbilledChargeService;
  }

  /**
   * Get or create the CommentService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  CommentService comments() {
    if (commentService == null) {
      synchronized (this) {
        if (commentService == null) {
          commentService = new CommentService(client);
        }
      }
    }
    return commentService;
  }

  /**
   * Get or create the InvoiceService instance. Thread-safe lazy initialization using double-checked
   * locking.
   */
  InvoiceService invoices() {
    if (invoiceService == null) {
      synchronized (this) {
        if (invoiceService == null) {
          invoiceService = new InvoiceService(client);
        }
      }
    }
    return invoiceService;
  }

  /**
   * Get or create the TransactionService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  TransactionService transactions() {
    if (transactionService == null) {
      synchronized (this) {
        if (transactionService == null) {
          transactionService = new TransactionService(client);
        }
      }
    }
    return transactionService;
  }

  /**
   * Get or create the ThirdPartySyncDetailService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  ThirdPartySyncDetailService thirdPartySyncDetails() {
    if (thirdPartySyncDetailService == null) {
      synchronized (this) {
        if (thirdPartySyncDetailService == null) {
          thirdPartySyncDetailService = new ThirdPartySyncDetailService(client);
        }
      }
    }
    return thirdPartySyncDetailService;
  }

  /**
   * Get or create the CustomerService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  CustomerService customers() {
    if (customerService == null) {
      synchronized (this) {
        if (customerService == null) {
          customerService = new CustomerService(client);
        }
      }
    }
    return customerService;
  }

  /**
   * Get or create the ItemEntitlementService instance. Thread-safe lazy initialization using
   * double-checked locking.
   */
  ItemEntitlementService itemEntitlements() {
    if (itemEntitlementService == null) {
      synchronized (this) {
        if (itemEntitlementService == null) {
          itemEntitlementService = new ItemEntitlementService(client);
        }
      }
    }
    return itemEntitlementService;
  }
}
