package com.chargebee.models.enums;

public enum OfflinePaymentMethod {
    NO_PREFERENCE,
    CASH,
    CHECK,
    BANK_TRANSFER,
    ACH_CREDIT,
    SEPA_CREDIT,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}