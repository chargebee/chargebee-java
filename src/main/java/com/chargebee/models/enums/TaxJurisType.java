package com.chargebee.models.enums;

public enum TaxJurisType {
    COUNTRY,
    STATE,
    COUNTY,
    CITY,
    SPECIAL,
    OTHER,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}