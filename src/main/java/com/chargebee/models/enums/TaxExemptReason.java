package com.chargebee.models.enums;

public enum TaxExemptReason {
    TAX_NOT_CONFIGURED,
    REGION_NON_TAXABLE,
    EXPORT,
    CUSTOMER_EXEMPT,
    PRODUCT_EXEMPT,
    ZERO_RATED,
    REVERSE_CHARGE,
    HIGH_VALUE_PHYSICAL_GOODS,
    ZERO_VALUE_ITEM,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}