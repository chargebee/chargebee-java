package com.chargebee.v4.models.hostedPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import com.chargebee.v4.models.hostedPage.responses.HostedPageRetrieveResponse;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class HostedPageTest {

  @Test
  void getContentDeserializesNestedEntitiesAsMaps() {
    String json =
        "{"
            + "\"hosted_page\": {"
            + "  \"id\": \"hp_123\","
            + "  \"type\": \"checkout_new\","
            + "  \"url\": \"https://example.com/pages/v3/hp_123\","
            + "  \"state\": \"succeeded\","
            + "  \"content\": {"
            + "    \"customer\": {\"id\": \"cust_1\", \"email\": \"a@example.com\"},"
            + "    \"subscription\": {\"id\": \"sub_xyz123\", \"status\": \"active\"},"
            + "    \"invoice\": {\"id\": \"inv_1\", \"total\": 1000}"
            + "  }"
            + "}"
            + "}";

    HostedPageRetrieveResponse response = HostedPageRetrieveResponse.fromJson(json);
    HostedPage hostedPage = response.getHostedPage();
    Map<String, Object> content = hostedPage.getContent();

    assertInstanceOf(Map.class, content.get("customer"));
    @SuppressWarnings("unchecked")
    Map<String, Object> customer = (Map<String, Object>) content.get("customer");
    assertEquals("cust_1", customer.get("id"));
    assertEquals("a@example.com", customer.get("email"));

    assertInstanceOf(Map.class, content.get("subscription"));
    @SuppressWarnings("unchecked")
    Map<String, Object> subscription = (Map<String, Object>) content.get("subscription");
    assertEquals("sub_xyz123", subscription.get("id"));
    assertEquals("active", subscription.get("status"));

    assertInstanceOf(Map.class, content.get("invoice"));
    @SuppressWarnings("unchecked")
    Map<String, Object> invoice = (Map<String, Object>) content.get("invoice");
    assertEquals("inv_1", invoice.get("id"));
    assertEquals(1000L, invoice.get("total"));
  }

  @Test
  void getContentSupportsNestedArrays() {
    String json =
        "{"
            + "\"hosted_page\": {"
            + "  \"id\": \"hp_123\","
            + "  \"content\": {"
            + "    \"subscription\": {"
            + "      \"id\": \"sub_1\","
            + "      \"subscription_items\": [{\"item_price_id\": \"price_1\", \"quantity\": 2}]"
            + "    }"
            + "  }"
            + "}"
            + "}";

    HostedPageRetrieveResponse response = HostedPageRetrieveResponse.fromJson(json);
    HostedPage hostedPage = response.getHostedPage();
    @SuppressWarnings("unchecked")
    Map<String, Object> subscription =
        (Map<String, Object>) hostedPage.getContent().get("subscription");
    assertInstanceOf(List.class, subscription.get("subscription_items"));
    @SuppressWarnings("unchecked")
    List<Object> items = (List<Object>) subscription.get("subscription_items");
    assertInstanceOf(Map.class, items.get(0));
    @SuppressWarnings("unchecked")
    Map<String, Object> item = (Map<String, Object>) items.get(0);
    assertEquals("price_1", item.get("item_price_id"));
    assertEquals(2L, item.get("quantity"));
  }
}
