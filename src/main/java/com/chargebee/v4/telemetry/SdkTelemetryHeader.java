/*
 * Copyright 2026 Chargebee Inc.
 */

package com.chargebee.v4.telemetry;

/** Constants for the anonymous SDK telemetry request header. */
public final class SdkTelemetryHeader {

  /**
   * Name of the request header carrying SDK telemetry. Exposed so that proxies, interceptors, and
   * tests can reference it without hardcoding the string.
   */
  public static final String HEADER_NAME = "x-chargebee-sdk-telemetry";

  /** Server drops larger values, so the SDK omits the header rather than sending a truncated one. */
  static final int MAX_HEADER_BYTES = 4096;

  static final String REQUEST_ID_HEADER = "chargebee-request-id";
  static final String RUNTIME = "jvm";
  static final String SDK_SEGMENT = "sdk";

  static final String FT_TELEMETRY_ADAPTER = "ft-telemetry_adapter";
  static final String FT_CUSTOM_TRANSPORT = "ft-custom_transport";
  static final String FT_RETRY_CONFIG = "ft-retry_config";

  private SdkTelemetryHeader() {}
}
