package com.chargebee.v4.internal;

import com.chargebee.v4.models.subscription.Subscription;
import com.chargebee.v4.models.subscription.Subscription.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Subscription JSON parsing")
class SubscriptionParserTest {

    private static String subscriptionJson;
    private static Subscription subscription;

    @BeforeAll
    static void loadFixture() throws IOException {
        try (InputStream is = SubscriptionParserTest.class.getResourceAsStream("/fixtures/subscription.json")) {
            assertNotNull(is, "fixtures/subscription.json not found on classpath");
            subscriptionJson = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
        subscription = Subscription.fromJson(subscriptionJson);
    }

    @Nested
    @DisplayName("Top-level scalar fields")
    class TopLevelFields {

        @Test void parsesStringFields() {
            assertEquals("__test__8asukSOXe0W3SU", subscription.getId());
            assertEquals("USD", subscription.getCurrencyCode());
            assertEquals("__test__8asukSOXe0QYSR", subscription.getCustomerId());
        }

        @Test void parsesBooleanFields() {
            assertEquals(false, subscription.getDeleted());
            assertEquals(false, subscription.getHasScheduledChanges());
        }

        @Test void parsesIntegerFields() {
            assertEquals(1, subscription.getBillingPeriod());
            assertEquals(1, subscription.getRemainingBillingCycles());
            assertEquals(1, subscription.getDueInvoicesCount());
        }

        @Test void parsesLongFields() {
            assertEquals(1612890938000L, subscription.getResourceVersion());
            assertEquals(0L, subscription.getMrr());
            assertEquals(1100L, subscription.getTotalDues());
        }

        @Test void parsesEnumFields() {
            assertEquals(Subscription.Status.ACTIVE, subscription.getStatus());
            assertEquals(Subscription.BillingPeriodUnit.MONTH, subscription.getBillingPeriodUnit());
            assertEquals(Subscription.Channel._UNKNOWN, subscription.getChannel());
        }

        @Test void parsesTimestampFields() {
            Timestamp ts = new Timestamp(1612890938L * 1000);
            assertEquals(ts, subscription.getActivatedAt());
            assertEquals(ts, subscription.getCreatedAt());
            assertEquals(ts, subscription.getStartedAt());
            assertEquals(ts, subscription.getDueSince());
            assertEquals(ts, subscription.getUpdatedAt());
            assertEquals(new Timestamp(1612890938L * 1000), subscription.getCurrentTermStart());
            assertEquals(new Timestamp(1615310138L * 1000), subscription.getCurrentTermEnd());
            assertEquals(new Timestamp(1615310138L * 1000), subscription.getNextBillingAt());
        }

        @Test void absentFieldsAreNull() {
            assertNull(subscription.getTrialStart());
            assertNull(subscription.getTrialEnd());
            assertNull(subscription.getPoNumber());
            assertNull(subscription.getCancelledAt());
            assertEquals(Subscription.CancelReason._UNKNOWN, subscription.getCancelReason());
            assertNull(subscription.getPaymentSourceId());
            assertNull(subscription.getShippingAddress());
            assertNull(subscription.getReferralInfo());
            assertNull(subscription.getContractTerm());
            assertNull(subscription.getBusinessEntityId());
        }
    }

    @Nested
    @DisplayName("Subscription items array")
    class SubscriptionItemsTests {

        @Test void parsesTwoItems() {
            assertNotNull(subscription.getSubscriptionItems());
            assertEquals(2, subscription.getSubscriptionItems().size());
        }

        @Test void firstItemIsPlan() {
            SubscriptionItems item = subscription.getSubscriptionItems().get(0);
            assertEquals("basic-USD", item.getItemPriceId());
            assertEquals(SubscriptionItems.ItemType.PLAN, item.getItemType());
            assertEquals(1, item.getQuantity());
            assertEquals(1000L, item.getUnitPrice());
            assertEquals(1000L, item.getAmount());
            assertEquals(0, item.getFreeQuantity());
            assertEquals(1, item.getBillingCycles());
        }

        @Test void secondItemIsAddon() {
            SubscriptionItems item = subscription.getSubscriptionItems().get(1);
            assertEquals("addon-USD", item.getItemPriceId());
            assertEquals(SubscriptionItems.ItemType.ADDON, item.getItemType());
            assertEquals(1, item.getQuantity());
            assertEquals(100L, item.getUnitPrice());
            assertEquals(100L, item.getAmount());
            assertEquals(0, item.getFreeQuantity());
            assertEquals(1, item.getBillingCycles());
        }
    }

    @Nested
    @DisplayName("Empty/absent collections")
    class EmptyCollections {

        @Test void emptyArraysAreEmptyLists() {
            assertNotNull(subscription.getItemTiers());
            assertTrue(subscription.getItemTiers().isEmpty());

            assertNotNull(subscription.getChargedItems());
            assertTrue(subscription.getChargedItems().isEmpty());

            assertNotNull(subscription.getCoupons());
            assertTrue(subscription.getCoupons().isEmpty());

            assertNotNull(subscription.getDiscounts());
            assertTrue(subscription.getDiscounts().isEmpty());

            assertNotNull(subscription.getAddons());
            assertTrue(subscription.getAddons().isEmpty());
        }
    }

    @Nested
    @DisplayName("Custom fields")
    class CustomFieldsTests {

        @Test void parsesCustomFields() {
            assertNotNull(subscription.getCustomFields());
            assertEquals(2, subscription.getCustomFields().size());
            assertEquals("enterprise", subscription.getCustomField("cf_license_tier"));
            assertEquals("Jane Smith", subscription.getCustomField("cf_account_manager"));
        }
    }
}
