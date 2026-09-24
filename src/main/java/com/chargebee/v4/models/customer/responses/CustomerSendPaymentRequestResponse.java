package com.chargebee.v4.models.customer.responses;

import java.util.List;

import com.chargebee.v4.models.emailLog.EmailLog;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CustomerSendPaymentRequest operation. Contains the response data
 * from the API.
 */
public final class CustomerSendPaymentRequestResponse extends BaseResponse {
  private final List<EmailLog> emailLogs;

  private CustomerSendPaymentRequestResponse(Builder builder) {
    super(builder.httpResponse);

    this.emailLogs = builder.emailLogs;
  }

  /** Parse JSON response into CustomerSendPaymentRequestResponse object. */
  public static CustomerSendPaymentRequestResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CustomerSendPaymentRequestResponse object with HTTP response. */
  public static CustomerSendPaymentRequestResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      builder.emailLogs(
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "email_logs"), EmailLog::fromJson));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CustomerSendPaymentRequestResponse from JSON", e);
    }
  }

  /** Create a new builder for CustomerSendPaymentRequestResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CustomerSendPaymentRequestResponse. */
  public static class Builder {

    private List<EmailLog> emailLogs;

    private Response httpResponse;

    private Builder() {}

    public Builder emailLogs(List<EmailLog> emailLogs) {
      this.emailLogs = emailLogs;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CustomerSendPaymentRequestResponse build() {
      return new CustomerSendPaymentRequestResponse(this);
    }
  }

  /** Get the emailLogs from the response. */
  public List<EmailLog> getEmailLogs() {
    return emailLogs;
  }

  @Override
  public String toString() {
    return "CustomerSendPaymentRequestResponse{" + "emailLogs=" + emailLogs + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CustomerSendPaymentRequestResponse that = (CustomerSendPaymentRequestResponse) o;
    return java.util.Objects.equals(emailLogs, that.emailLogs);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(emailLogs);
  }
}
