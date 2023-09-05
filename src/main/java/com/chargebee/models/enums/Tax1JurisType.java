package com.chargebee.models.enums;

public enum Tax1JurisType {
    COUNTRY,
    FEDERAL,
    STATE,
    COUNTY,
    CITY,
    SPECIAL,
    UNINCORPORATED,
    OTHER,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}