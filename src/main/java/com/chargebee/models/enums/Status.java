package com.chargebee.models.enums;

public enum Status {
    ACTIVE,
    ARCHIVED,
    DELETED,
    AVAILABLE,
    EXHAUSTED,
    SCHEDULED,
    IN_GRACE_PERIOD,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}