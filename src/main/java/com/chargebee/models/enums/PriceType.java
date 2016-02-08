package com.chargebee.models.enums;

public enum PriceType {
    TAX_EXCLUSIVE,
    TAX_INCLUSIVE,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}