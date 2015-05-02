package com.chargebee.models.enums;

public enum Type {
    CARD,
    PAYPAL_EXPRESS_CHECKOUT,
    AMAZON_PAYMENTS,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}