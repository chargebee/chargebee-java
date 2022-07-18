package com.chargebee.models.enums;

public enum EntityType {
    CUSTOMER,
    SUBSCRIPTION,
    INVOICE,
    QUOTE,
    CREDIT_NOTE,
    TRANSACTION,
    PLAN,
    ADDON,
    COUPON,
    ORDER,
    BUSINESS_ENTITY,
    ITEM_FAMILY,
    ITEM,
    ITEM_PRICE,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}