package com.chargebee.v4.models.ledgerOperation.responses;

import com.chargebee.v4.models.ledgerOperation.LedgerOperation;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RetrieveLedgerOperation operation. Contains the response data from
 * a single resource get operation.
 */
public final class RetrieveLedgerOperationResponse extends BaseResponse {
  private final LedgerOperation ledgerOperation;

  private RetrieveLedgerOperationResponse(Builder builder) {
    super(builder.httpResponse);

    this.ledgerOperation = builder.ledgerOperation;
  }

  /** Parse JSON response into RetrieveLedgerOperationResponse object. */
  public static RetrieveLedgerOperationResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RetrieveLedgerOperationResponse object with HTTP response. */
  public static RetrieveLedgerOperationResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __ledgerOperationObj = JsonUtil.getJsonObject(jsonObj, "ledger_operation");
      if (__ledgerOperationObj != null) {
        builder.ledgerOperation(LedgerOperation.fromJson(__ledgerOperationObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RetrieveLedgerOperationResponse from JSON", e);
    }
  }

  /** Create a new builder for RetrieveLedgerOperationResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for RetrieveLedgerOperationResponse. */
  public static class Builder {

    private LedgerOperation ledgerOperation;

    private Response httpResponse;

    private Builder() {}

    public Builder ledgerOperation(LedgerOperation ledgerOperation) {
      this.ledgerOperation = ledgerOperation;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public RetrieveLedgerOperationResponse build() {
      return new RetrieveLedgerOperationResponse(this);
    }
  }

  /** Get the ledgerOperation from the response. */
  public LedgerOperation getLedgerOperation() {
    return ledgerOperation;
  }

  @Override
  public String toString() {
    return "RetrieveLedgerOperationResponse{" + "ledgerOperation=" + ledgerOperation + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RetrieveLedgerOperationResponse that = (RetrieveLedgerOperationResponse) o;
    return java.util.Objects.equals(ledgerOperation, that.ledgerOperation);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(ledgerOperation);
  }
}
