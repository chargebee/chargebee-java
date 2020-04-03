package com.chargebee.models.enums;

public enum OnEvent {
    SUBSCRIPTION_CREATION,
    SUBSCRIPTION_TRIAL_START,
    PLAN_ACTIVATION,
    SUBSCRIPTION_ACTIVATION,
    CONTRACT_TERMINATION,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}