package com.chargebee.models.enums;

public enum PricingModel {
    FLAT_FEE,
    PER_UNIT,
    TIERED,
    VOLUME,
    STAIRSTEP,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}