package com.chargebee.models.enums;

public enum Source {
    ADMIN_CONSOLE,
    API,
    SCHEDULED_JOB,
    HOSTED_PAGE,
    SYSTEM,
    NONE,
    JS_API,
    PORTAL,
    MIGRATION,
    BULK_OPERATION,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}