package com.chargebee.models.enums;

public enum Gateway {
    CHARGEBEE,
    BRAINTREE,
    AUTHORIZE_NET,
    STRIPE,
    BALANCED_PAYMENTS,
    TCO,
    WORLDPAY,
    PAYPAL_PRO,
    PIN,
    WIRECARD,
    OGONE,
    BEANSTREAM,
    EWAY,
    HDFC,
    NOT_APPLICABLE,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}