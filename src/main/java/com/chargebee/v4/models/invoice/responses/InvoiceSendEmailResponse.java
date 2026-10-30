package com.chargebee.v4.models.invoice.responses;

import java.util.List;

import com.chargebee.v4.models.emailLog.EmailLog;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InvoiceSendEmail operation. Contains the response data from the
 * API.
 */
public final class InvoiceSendEmailResponse extends BaseResponse {
  private final List<EmailLog> emailLogs;

  private InvoiceSendEmailResponse(Builder builder) {
    super(builder.httpResponse);

    this.emailLogs = builder.emailLogs;
  }

  /** Parse JSON response into InvoiceSendEmailResponse object. */
  public static InvoiceSendEmailResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoiceSendEmailResponse object with HTTP response. */
  public static InvoiceSendEmailResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      builder.emailLogs(
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "email_logs"), EmailLog::fromJson));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceSendEmailResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceSendEmailResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceSendEmailResponse. */
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

    public InvoiceSendEmailResponse build() {
      return new InvoiceSendEmailResponse(this);
    }
  }

  /** Get the emailLogs from the response. */
  public List<EmailLog> getEmailLogs() {
    return emailLogs;
  }

  @Override
  public String toString() {
    return "InvoiceSendEmailResponse{" + "emailLogs=" + emailLogs + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    InvoiceSendEmailResponse that = (InvoiceSendEmailResponse) o;
    return java.util.Objects.equals(emailLogs, that.emailLogs);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(emailLogs);
  }
}
