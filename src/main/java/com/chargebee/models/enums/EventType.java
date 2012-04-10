package com.chargebee.models.enums;

public enum EventType {
    SUBSCRIPTION_CREATED,
    SUBSCRIPTION_ACTIVATED,
    SUBSCRIPTION_CHANGED,
    SUBSCRIPTION_CANCELED_UNPAID,
    SUBSCRIPTION_CANCELED_NO_CARD,
    SUBSCRIPTION_CANCELED,
    SUBSCRIPTION_REACTIVATED,
    SUBSCRIPTION_RENEWED,
    INVOICE_RECEIPT,
    PAYMENT_COLLECTED,
    PAYMENT_FAILED,
    PAYMENT_REFUND,
    CARD_ADDED,
    CARD_UPDATED,
    CARD_EXPIRED,
    TRIAL_END_REMINDER,
    SUBCRIPTION_CANCELATION_REMINDER,
    CARD_EXPIRY_REMINDER,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}