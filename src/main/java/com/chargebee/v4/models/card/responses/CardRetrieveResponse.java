package com.chargebee.v4.models.card.responses;

import com.chargebee.v4.models.card.Card;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CardRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class CardRetrieveResponse extends BaseResponse {
  private final Card card;

  private CardRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.card = builder.card;
  }

  /** Parse JSON response into CardRetrieveResponse object. */
  public static CardRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CardRetrieveResponse object with HTTP response. */
  public static CardRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __cardJson = JsonUtil.getObject(json, "card");
      if (__cardJson != null) {
        builder.card(Card.fromJson(__cardJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CardRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for CardRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CardRetrieveResponse. */
  public static class Builder {

    private Card card;

    private Response httpResponse;

    private Builder() {}

    public Builder card(Card card) {
      this.card = card;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CardRetrieveResponse build() {
      return new CardRetrieveResponse(this);
    }
  }

  /** Get the card from the response. */
  public Card getCard() {
    return card;
  }

  @Override
  public String toString() {
    return "CardRetrieveResponse{" + "card=" + card + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CardRetrieveResponse that = (CardRetrieveResponse) o;
    return java.util.Objects.equals(card, that.card);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(card);
  }
}
