package com.chargebee.models.enums;

public enum EventType {
    SUBSCRIPTION_CREATED,
    SUBSCRIPTION_TRIAL_ENDING,
    SUBSCRIPTION_ACTIVATED,
    SUBSCRIPTION_CHANGED,
    SUBSCRIPTION_CANCELED_FRAUD_REVIEW_FAILED,
    SUBSCRIPTION_CANCELLING,
    SUBSCRIPTION_CANCELLED,
    SUBSCRIPTION_REACTIVATED,
    SUBSCRIPTION_RENEWED,
    INVOICE_RECEIPT,
    INVOICE_CREATED,
    PAYMENT_SUCCEEDED,
    PAYMENT_FAILED,
    PAYMENT_REFUNDED,
    CARD_ADDED,
    CARD_UPDATED,
    CARD_EXPIRING,
    CARD_EXPIRED,
    PING,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}