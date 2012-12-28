package com.chargebee.models.enums;

public enum Gateway {
    CHARGEBEE,
    SAMURAI,
    BRAINTREE,
    TCO,
    STRIPE,
    WORLDPAY,
    PAYPAL_PRO,
    WIRECARD,
    OGONE,
    HDFC,
    EWAY,
    NOT_APPLICABLE,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}