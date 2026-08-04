/*
 * Copyright 2026 Chargebee Inc.
 */

package com.chargebee.v4.telemetry;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/** Immutable snapshot of a completed SDK API call for N+1 header emission. */
final class SdkTelemetrySnapshot {

  private final String sdkName;
  private final String sdkVersion;
  private final String resource;
  private final String operation;
  private final long startTimeEpochSeconds;
  private final long timeMs;
  private final Integer httpStatus;
  private final String errorCode;
  private final String requestId;
  private final Set<String> featureTokens;

  private SdkTelemetrySnapshot(Builder builder) {
    this.sdkName = builder.sdkName;
    this.sdkVersion = builder.sdkVersion;
    this.resource = builder.resource;
    this.operation = builder.operation;
    this.startTimeEpochSeconds = builder.startTimeEpochSeconds;
    this.timeMs = builder.timeMs;
    this.httpStatus = builder.httpStatus;
    this.errorCode = builder.errorCode;
    this.requestId = builder.requestId;
    this.featureTokens =
        Collections.unmodifiableSet(
            new LinkedHashSet<>(builder.featureTokens != null ? builder.featureTokens : Set.of()));
  }

  static Builder builder() {
    return new Builder();
  }

  public String getSdkName() {
    return sdkName;
  }

  public String getSdkVersion() {
    return sdkVersion;
  }

  public String getResource() {
    return resource;
  }

  public String getOperation() {
    return operation;
  }

  /** Unix epoch seconds at which the reported call started; {@code 0} when unknown. */
  public long getStartTimeEpochSeconds() {
    return startTimeEpochSeconds;
  }

  public long getTimeMs() {
    return timeMs;
  }

  public Integer getHttpStatus() {
    return httpStatus;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getRequestId() {
    return requestId;
  }

  public Set<String> getFeatureTokens() {
    return featureTokens;
  }

  static final class Builder {
    private String sdkName;
    private String sdkVersion;
    private String resource;
    private String operation;
    private long startTimeEpochSeconds;
    private long timeMs;
    private Integer httpStatus;
    private String errorCode;
    private String requestId;
    private Set<String> featureTokens = new LinkedHashSet<>();

    public Builder sdkName(String sdkName) {
      this.sdkName = sdkName;
      return this;
    }

    public Builder sdkVersion(String sdkVersion) {
      this.sdkVersion = sdkVersion;
      return this;
    }

    public Builder resource(String resource) {
      this.resource = resource;
      return this;
    }

    public Builder operation(String operation) {
      this.operation = operation;
      return this;
    }

    public Builder startTimeEpochSeconds(long startTimeEpochSeconds) {
      this.startTimeEpochSeconds = startTimeEpochSeconds;
      return this;
    }

    public Builder timeMs(long timeMs) {
      this.timeMs = timeMs;
      return this;
    }

    public Builder httpStatus(Integer httpStatus) {
      this.httpStatus = httpStatus;
      return this;
    }

    public Builder errorCode(String errorCode) {
      this.errorCode = errorCode;
      return this;
    }

    public Builder requestId(String requestId) {
      this.requestId = requestId;
      return this;
    }

    public Builder featureTokens(Set<String> featureTokens) {
      this.featureTokens =
          featureTokens != null ? new LinkedHashSet<>(featureTokens) : new LinkedHashSet<>();
      return this;
    }

    public Builder addFeatureToken(String featureToken) {
      if (featureToken != null && !featureToken.isBlank()) {
        this.featureTokens.add(featureToken);
      }
      return this;
    }

    public SdkTelemetrySnapshot build() {
      return new SdkTelemetrySnapshot(this);
    }
  }
}
