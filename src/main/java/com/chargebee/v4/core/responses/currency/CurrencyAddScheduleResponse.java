package com.chargebee.v4.core.responses.currency;

import com.chargebee.v4.core.models.currency.Currency;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.sql.Timestamp;

/**
 * Immutable response object for CurrencyAddSchedule operation. Contains the response data from the
 * API.
 */
public final class CurrencyAddScheduleResponse {

  private final Timestamp scheduledAt;

  private final Currency currency;

  private final Response httpResponse;

  private CurrencyAddScheduleResponse(Builder builder) {

    this.scheduledAt = builder.scheduledAt;

    this.currency = builder.currency;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into CurrencyAddScheduleResponse object. */
  public static CurrencyAddScheduleResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CurrencyAddScheduleResponse object with HTTP response. */
  public static CurrencyAddScheduleResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.scheduledAt(JsonUtil.getTimestamp(json, "scheduled_at"));

      String __currencyJson = JsonUtil.getObject(json, "currency");
      if (__currencyJson != null) {
        builder.currency(Currency.fromJson(__currencyJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CurrencyAddScheduleResponse from JSON", e);
    }
  }

  /** Create a new builder for CurrencyAddScheduleResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CurrencyAddScheduleResponse. */
  public static class Builder {

    private Timestamp scheduledAt;

    private Currency currency;

    private Response httpResponse;

    private Builder() {}

    public Builder scheduledAt(Timestamp scheduledAt) {
      this.scheduledAt = scheduledAt;
      return this;
    }

    public Builder currency(Currency currency) {
      this.currency = currency;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CurrencyAddScheduleResponse build() {
      return new CurrencyAddScheduleResponse(this);
    }
  }

  /** Get the scheduledAt from the response. */
  public Timestamp getScheduledAt() {
    return scheduledAt;
  }

  /** Get the currency from the response. */
  public Currency getCurrency() {
    return currency;
  }

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
