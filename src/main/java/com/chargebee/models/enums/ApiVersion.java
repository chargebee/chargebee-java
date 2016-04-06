package com.chargebee.models.enums;

public enum ApiVersion {
    V1,
    V2,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}