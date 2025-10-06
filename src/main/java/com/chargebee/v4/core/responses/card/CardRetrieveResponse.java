package com.chargebee.v4.core.responses.card;

import com.chargebee.v4.core.models.card.Card;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CardRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class CardRetrieveResponse {

  private final Card card;

  private CardRetrieveResponse(Card card) {

    this.card = card;
  }

  /** Parse JSON response into CardRetrieveResponse object. */
  public static CardRetrieveResponse fromJson(String json) {
    try {

      Card card = Card.fromJson(JsonUtil.getObject(json, "card"));

      return new CardRetrieveResponse(card);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CardRetrieveResponse from JSON", e);
    }
  }

  /** Get the card from the response. */
  public Card getCard() {
    return card;
  }
}
