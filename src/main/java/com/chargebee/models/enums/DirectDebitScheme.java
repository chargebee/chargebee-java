package com.chargebee.models.enums;

public enum DirectDebitScheme {
    ACH,
    BACS,
    SEPA_CORE,
    AUTOGIRO,
    BECS,
    BECS_NZ,
    PAD,
    NOT_APPLICABLE,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}