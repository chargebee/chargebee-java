package com.chargebee.models.enums;

public enum Gateway {
    CHARGEBEE,
    STRIPE,
    WEPAY,
    BRAINTREE,
    AUTHORIZE_NET,
    PAYPAL_PRO,
    PIN,
    EWAY,
    EWAY_RAPID,
    WORLDPAY,
    BALANCED_PAYMENTS,
    BEANSTREAM,
    BLUEPAY,
    ELAVON,
    FIRST_DATA_GLOBAL,
    HDFC,
    MIGS,
    NMI,
    OGONE,
    PAYMILL,
    PAYPAL_PAYFLOW_PRO,
    SAGE_PAY,
    TCO,
    WIRECARD,
    GOCARDLESS,
    ADYEN,
    NOT_APPLICABLE,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}