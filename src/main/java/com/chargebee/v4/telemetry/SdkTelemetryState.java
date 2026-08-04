/*
 * Copyright 2026 Chargebee Inc.
 */

package com.chargebee.v4.telemetry;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Per-client holder for the last completed call, used by the N+1 SDK telemetry header.
 *
 * <p>Internal SDK type: applications must not depend on it. It is public only so that {@code
 * ChargebeeClient} can own one instance; all accessors are package-private.
 */
public final class SdkTelemetryState {

  private final AtomicReference<SdkTelemetrySnapshot> lastCall = new AtomicReference<>();

  SdkTelemetrySnapshot lastCall() {
    return lastCall.get();
  }

  void record(SdkTelemetrySnapshot snapshot) {
    lastCall.set(snapshot);
  }

  void clear() {
    lastCall.set(null);
  }
}
