package com.chargebee;

public interface IdempotencyConstants {
    String IDEMPOTENCY_HEADER = "chargebee-idempotency-key";

    String IDEMPOTENCY_REPLAY_HEADER = "chargebee-idempotency-replayed";
}
