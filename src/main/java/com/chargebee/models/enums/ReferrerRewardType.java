package com.chargebee.models.enums;

public enum ReferrerRewardType {
    NONE,
    REFERRAL_DIRECT_REWARD,
    CUSTOM_PROMOTIONAL_CREDIT,
    CUSTOM_REVENUE_PERCENT_BASED,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}