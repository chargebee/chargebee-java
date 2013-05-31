package com.chargebee.models.enums;

public enum Gateway {
    CHARGEBEE,
    AUTHORIZE_NET,
    BALANCED_PAYMENTS,
    BEANSTREAM,
    BRAINTREE,
    ELAVON,
    EWAY,
    HDFC,
    MIGS,
    NMI,
    OGONE,
    PAYMILL,
    PAYPAL_PRO,
    PAYPAL_PAYFLOW_PRO,
    PIN,
    SAGE_PAY,
    STRIPE,
    TCO,
    WORLDPAY,
    WIRECARD,
    NOT_APPLICABLE,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}