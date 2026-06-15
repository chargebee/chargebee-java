package com.chargebee.v4.models.promotionalGrant.responses;

import java.util.List;

import com.chargebee.v4.models.ledgerOperation.LedgerOperation;

import com.chargebee.v4.models.grantBlock.GrantBlock;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PromotionalGrants operation. Contains the response data from the
 * API.
 */
public final class PromotionalGrantsResponse extends BaseResponse {
  private final List<LedgerOperation> ledgerOperations;

  private final List<GrantBlock> grantBlocks;

  private PromotionalGrantsResponse(Builder builder) {
    super(builder.httpResponse);

    this.ledgerOperations = builder.ledgerOperations;

    this.grantBlocks = builder.grantBlocks;
  }

  /** Parse JSON response into PromotionalGrantsResponse object. */
  public static PromotionalGrantsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PromotionalGrantsResponse object with HTTP response. */
  public static PromotionalGrantsResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      builder.ledgerOperations(
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "ledger_operations"), LedgerOperation::fromJson));

      builder.grantBlocks(
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "grant_blocks"), GrantBlock::fromJson));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PromotionalGrantsResponse from JSON", e);
    }
  }

  /** Create a new builder for PromotionalGrantsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PromotionalGrantsResponse. */
  public static class Builder {

    private List<LedgerOperation> ledgerOperations;

    private List<GrantBlock> grantBlocks;

    private Response httpResponse;

    private Builder() {}

    public Builder ledgerOperations(List<LedgerOperation> ledgerOperations) {
      this.ledgerOperations = ledgerOperations;
      return this;
    }

    public Builder grantBlocks(List<GrantBlock> grantBlocks) {
      this.grantBlocks = grantBlocks;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public PromotionalGrantsResponse build() {
      return new PromotionalGrantsResponse(this);
    }
  }

  /** Get the ledgerOperations from the response. */
  public List<LedgerOperation> getLedgerOperations() {
    return ledgerOperations;
  }

  /** Get the grantBlocks from the response. */
  public List<GrantBlock> getGrantBlocks() {
    return grantBlocks;
  }

  @Override
  public String toString() {
    return "PromotionalGrantsResponse{"
        + "ledgerOperations="
        + ledgerOperations
        + ", grantBlocks="
        + grantBlocks
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PromotionalGrantsResponse that = (PromotionalGrantsResponse) o;
    return java.util.Objects.equals(ledgerOperations, that.ledgerOperations)
        && java.util.Objects.equals(grantBlocks, that.grantBlocks);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(ledgerOperations, grantBlocks);
  }
}
