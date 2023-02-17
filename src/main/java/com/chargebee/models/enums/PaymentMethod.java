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
    APPLE_PAY,
    WECHAT_PAY,
    ACH_CREDIT,
    SEPA_CREDIT,
    IDEAL,
    GOOGLE_PAY,
    SOFORT,
    BANCONTACT,
    GIROPAY,
    DOTPAY,
    OTHER,
    UPI,
    NETBANKING_EMANDATES,
    CUSTOM,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}