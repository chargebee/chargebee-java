package com.chargebee.models.enums;

public enum PaymentMethod {
    CARD,
    CASH,
    CHECK,
    CHARGEBACK,
    BANK_TRANSFER,
    AMAZON_PAYMENTS,
    PAYPAL_EXPRESS_CHECKOUT,
    DIRECT_DEBIT,
    ALIPAY,
    UNIONPAY,
    OTHER,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}