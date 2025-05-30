package com.chargebee.models.enums;

public enum EntityType {
    CUSTOMER,
    SUBSCRIPTION,
    COUPON,
    PLAN_ITEM_PRICE,
    ADDON_ITEM_PRICE,
    CHARGE_ITEM_PRICE,
    INVOICE,
    QUOTE,
    CREDIT_NOTE,
    TRANSACTION,
    PLAN,
    ADDON,
    ORDER,
    ITEM_FAMILY,
    ITEM,
    ITEM_PRICE,
    PLAN_ITEM,
    ADDON_ITEM,
    CHARGE_ITEM,
    PLAN_PRICE,
    ADDON_PRICE,
    CHARGE_PRICE,
    DIFFERENTIAL_PRICE,
    ATTACHED_ITEM,
    FEATURE,
    SUBSCRIPTION_ENTITLEMENT,
    ITEM_ENTITLEMENT,
    BUSINESS_ENTITY,
    PRICE_VARIANT,
    OMNICHANNEL_SUBSCRIPTION,
    OMNICHANNEL_SUBSCRIPTION_ITEM,
    OMNICHANNEL_TRANSACTION,
    RECORDED_PURCHASE,
    OMNICHANNEL_SUBSCRIPTION_ITEM_SCHEDULED_CHANGE,
    SALES_ORDER,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}