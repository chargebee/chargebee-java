package com.chargebee.models.enums;

public enum PaymentMethodType {
    CARD,
    PAYPAL_EXPRESS_CHECKOUT,
    AMAZON_PAYMENTS,
    DIRECT_DEBIT,
    GENERIC,
    ALIPAY,
    UNIONPAY,
    APPLE_PAY,
    WECHAT_PAY,
    IDEAL,
    GOOGLE_PAY,
    SOFORT,
    BANCONTACT,
    GIROPAY,
    DOTPAY,
    UPI,
    NETBANKING_EMANDATES,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}