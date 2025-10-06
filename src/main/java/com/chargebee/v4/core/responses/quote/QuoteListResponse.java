package com.chargebee.v4.core.responses.quote;

import java.util.List;

import com.chargebee.v4.core.models.quote.Quote;

import com.chargebee.v4.core.models.quotedSubscription.QuotedSubscription;

import com.chargebee.v4.core.models.quotedRamp.QuotedRamp;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.core.services.QuoteService;
import com.chargebee.v4.core.models.quote.params.QuoteListParams;

/** Immutable response object for QuoteList operation. Contains paginated list data. */
public final class QuoteListResponse {

  private final List<QuoteListItem> list;

  private final String nextOffset;

  private final QuoteService service;
  private final QuoteListParams originalParams;

  private QuoteListResponse(
      List<QuoteListItem> list,
      String nextOffset,
      QuoteService service,
      QuoteListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into QuoteListResponse object (no service context). Use this when you only
   * need to read a single page (no nextPage()).
   */
  public static QuoteListResponse fromJson(String json) {
    try {

      List<QuoteListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(QuoteListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new QuoteListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse QuoteListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into QuoteListResponse object with service context for pagination (enables
   * nextPage()).
   */
  public static QuoteListResponse fromJson(
      String json, QuoteService service, QuoteListParams originalParams) {
    try {

      List<QuoteListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(QuoteListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new QuoteListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse QuoteListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<QuoteListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<QuoteListItem> items() {
    return list;
  }

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws Exception if unable to fetch next page
   */
  public QuoteListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    QuoteListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  public static class QuoteListItem {

    private Quote quote;

    private QuotedSubscription quotedSubscription;

    private QuotedRamp quotedRamp;

    public Quote getQuote() {
      return quote;
    }

    public QuotedSubscription getQuotedSubscription() {
      return quotedSubscription;
    }

    public QuotedRamp getQuotedRamp() {
      return quotedRamp;
    }

    public static QuoteListItem fromJson(String json) {
      QuoteListItem item = new QuoteListItem();

      String __quoteJson = JsonUtil.getObject(json, "quote");
      if (__quoteJson != null) {
        item.quote = Quote.fromJson(__quoteJson);
      }

      String __quotedSubscriptionJson = JsonUtil.getObject(json, "quoted_subscription");
      if (__quotedSubscriptionJson != null) {
        item.quotedSubscription = QuotedSubscription.fromJson(__quotedSubscriptionJson);
      }

      String __quotedRampJson = JsonUtil.getObject(json, "quoted_ramp");
      if (__quotedRampJson != null) {
        item.quotedRamp = QuotedRamp.fromJson(__quotedRampJson);
      }

      return item;
    }
  }
}
