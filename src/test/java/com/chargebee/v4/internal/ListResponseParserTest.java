package com.chargebee.v4.internal;

import com.chargebee.v4.models.card.Card;
import com.chargebee.v4.models.customer.Customer;
import com.chargebee.v4.models.customer.responses.CustomerListResponse;
import com.chargebee.v4.models.customer.responses.CustomerListResponse.CustomerListItem;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("List response JSON parsing")
class ListResponseParserTest {

    private static CustomerListResponse response;

    @BeforeAll
    static void loadFixture() throws IOException {
        String json;
        try (InputStream is = ListResponseParserTest.class.getResourceAsStream("/fixtures/customer_list.json")) {
            assertNotNull(is, "fixtures/customer_list.json not found on classpath");
            json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
        response = CustomerListResponse.fromJson(json);
    }

    @Nested
    @DisplayName("List structure")
    class ListStructure {

        @Test void listNotNull() {
            assertNotNull(response.getList());
        }

        @Test void listSize() {
            assertEquals(3, response.getList().size());
        }

        @Test void eachItemHasCustomer() {
            for (CustomerListItem item : response.getList()) {
                assertNotNull(item.getCustomer(), "Each list item should have a customer");
            }
        }
    }

    @Nested
    @DisplayName("Pagination")
    class Pagination {

        @Test void nextOffsetParsed() {
            assertEquals("[\"1605531050000\",\"3\"]", response.getNextOffset());
        }

        @Test void hasNextPageTrue() {
            assertTrue(response.hasNextPage());
        }

        @Test void nextPageWithoutServiceThrows() {
            assertThrows(UnsupportedOperationException.class, () -> response.nextPage());
        }
    }

    @Nested
    @DisplayName("First customer (cust_AzZhUGSP1)")
    class FirstCustomer {

        private Customer customer() {
            return response.getList().get(0).getCustomer();
        }

        @Test void id() {
            assertEquals("cust_AzZhUGSP1", customer().getId());
        }

        @Test void firstName() {
            assertEquals("John", customer().getFirstName());
        }

        @Test void lastName() {
            assertEquals("Doe", customer().getLastName());
        }

        @Test void email() {
            assertEquals("john@example.com", customer().getEmail());
        }

        @Test void autoCollection() {
            assertEquals(Customer.AutoCollection.ON, customer().getAutoCollection());
        }

        @Test void createdAt() {
            assertEquals(new Timestamp(1605530769L * 1000), customer().getCreatedAt());
        }

        @Test void resourceVersion() {
            assertEquals(1605530800000L, customer().getResourceVersion());
        }

        @Test void deleted() {
            assertFalse(customer().getDeleted());
        }

        @Test void preferredCurrencyCode() {
            assertEquals("USD", customer().getPreferredCurrencyCode());
        }

        @Test void billingAddress() {
            assertNotNull(customer().getBillingAddress());
            assertEquals("San Francisco", customer().getBillingAddress().getCity());
            assertEquals("CA", customer().getBillingAddress().getStateCode());
            assertEquals("94105", customer().getBillingAddress().getZip());
        }

        @Test void noCard() {
            assertNull(response.getList().get(0).getCard());
        }
    }

    @Nested
    @DisplayName("Second customer (cust_AzZhUGSP2)")
    class SecondCustomer {

        private Customer customer() {
            return response.getList().get(1).getCustomer();
        }

        @Test void id() {
            assertEquals("cust_AzZhUGSP2", customer().getId());
        }

        @Test void email() {
            assertEquals("jane@example.com", customer().getEmail());
        }

        @Test void autoCollectionOff() {
            assertEquals(Customer.AutoCollection.OFF, customer().getAutoCollection());
        }

        @Test void netTermDays() {
            assertEquals(30, customer().getNetTermDays());
        }

        @Test void allowDirectDebit() {
            assertTrue(customer().getAllowDirectDebit());
        }

        @Test void promotionalCredits() {
            assertEquals(500L, customer().getPromotionalCredits());
        }

        @Test void noBillingAddress() {
            assertNull(customer().getBillingAddress());
        }

        @Test void noCard() {
            assertNull(response.getList().get(1).getCard());
        }
    }

    @Nested
    @DisplayName("Third customer with card (cust_AzZhUGSP3)")
    class ThirdCustomerWithCard {

        private Customer customer() {
            return response.getList().get(2).getCustomer();
        }

        private Card card() {
            return response.getList().get(2).getCard();
        }

        @Test void customerId() {
            assertEquals("cust_AzZhUGSP3", customer().getId());
        }

        @Test void taxabilityExempt() {
            assertEquals(Customer.Taxability.EXEMPT, customer().getTaxability());
        }

        @Test void cardNotNull() {
            assertNotNull(card());
        }

        @Test void cardLast4() {
            assertEquals("1111", card().getLast4());
        }

        @Test void cardType() {
            assertEquals(Card.CardType.VISA, card().getCardType());
        }

        @Test void cardExpiry() {
            assertEquals(12, card().getExpiryMonth());
            assertEquals(2028, card().getExpiryYear());
        }

        @Test void cardMaskedNumber() {
            assertEquals("************1111", card().getMaskedNumber());
        }

        @Test void cardCustomerId() {
            assertEquals("cust_AzZhUGSP3", card().getCustomerId());
        }
    }

    @Nested
    @DisplayName("Edge cases")
    class EdgeCases {

        @Test void emptyList() {
            CustomerListResponse empty = CustomerListResponse.fromJson("{\"list\": []}");
            assertNotNull(empty.getList());
            assertTrue(empty.getList().isEmpty());
            assertNull(empty.getNextOffset());
            assertFalse(empty.hasNextPage());
        }

        @Test void nullNextOffset() {
            CustomerListResponse resp = CustomerListResponse.fromJson(
                "{\"list\": [{\"customer\": {\"id\": \"c1\", \"object\": \"customer\"}}], \"next_offset\": null}");
            assertEquals(1, resp.getList().size());
            assertNull(resp.getNextOffset());
            assertFalse(resp.hasNextPage());
        }

        @Test void missingNextOffset() {
            CustomerListResponse resp = CustomerListResponse.fromJson(
                "{\"list\": [{\"customer\": {\"id\": \"c1\", \"object\": \"customer\"}}]}");
            assertNull(resp.getNextOffset());
            assertFalse(resp.hasNextPage());
        }

        @Test void missingListKey() {
            CustomerListResponse resp = CustomerListResponse.fromJson("{\"next_offset\": \"abc\"}");
            assertNotNull(resp.getList());
            assertTrue(resp.getList().isEmpty());
            assertEquals("abc", resp.getNextOffset());
        }

        @Test void emptyJsonObject() {
            CustomerListResponse resp = CustomerListResponse.fromJson("{}");
            assertNotNull(resp.getList());
            assertTrue(resp.getList().isEmpty());
            assertNull(resp.getNextOffset());
        }

        @Test void singleItemNoCard() {
            String json = "{\"list\": [{\"customer\": {\"id\": \"solo\", \"first_name\": \"Only\", \"email\": \"only@test.com\"}}]}";
            CustomerListResponse resp = CustomerListResponse.fromJson(json);
            assertEquals(1, resp.getList().size());
            assertEquals("solo", resp.getList().get(0).getCustomer().getId());
            assertEquals("Only", resp.getList().get(0).getCustomer().getFirstName());
            assertNull(resp.getList().get(0).getCard());
        }

        @Test void listItemWithOnlyCard() {
            String json = "{\"list\": [{\"card\": {\"status\": \"valid\", \"last4\": \"9999\"}}]}";
            CustomerListResponse resp = CustomerListResponse.fromJson(json);
            assertEquals(1, resp.getList().size());
            assertNull(resp.getList().get(0).getCustomer());
            assertNotNull(resp.getList().get(0).getCard());
            assertEquals("9999", resp.getList().get(0).getCard().getLast4());
        }

        @Test void nextOffsetWithSpecialCharacters() {
            String json = "{\"list\": [], \"next_offset\": \"[\\\"1605531050000\\\",\\\"100\\\"]\"}";
            CustomerListResponse resp = CustomerListResponse.fromJson(json);
            assertEquals("[\"1605531050000\",\"100\"]", resp.getNextOffset());
            assertTrue(resp.hasNextPage());
        }

        @Test void largeList() {
            StringBuilder json = new StringBuilder("{\"list\": [");
            for (int i = 0; i < 100; i++) {
                if (i > 0) json.append(",");
                json.append("{\"customer\": {\"id\": \"cust_").append(i)
                    .append("\", \"email\": \"user").append(i).append("@test.com\"}}");
            }
            json.append("], \"next_offset\": \"offset_100\"}");

            CustomerListResponse resp = CustomerListResponse.fromJson(json.toString());
            assertEquals(100, resp.getList().size());
            assertEquals("cust_0", resp.getList().get(0).getCustomer().getId());
            assertEquals("cust_99", resp.getList().get(99).getCustomer().getId());
            assertEquals("offset_100", resp.getNextOffset());
        }
    }
}
