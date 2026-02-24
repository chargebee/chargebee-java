package com.chargebee.v4.internal;

import com.chargebee.v4.models.itemPrice.ItemPrice;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ItemPrice JSON parsing")
class ItemPriceParserTest {

    private static String itemPriceJson;
    private static ItemPrice itemPrice;

    @BeforeAll
    static void loadFixture() throws IOException {
        try (InputStream is = ItemPriceParserTest.class.getResourceAsStream("/fixtures/itemPrices.json")) {
            assertNotNull(is, "fixtures/itemPrices.json not found on classpath");
            itemPriceJson = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
        itemPrice = ItemPrice.fromJson(itemPriceJson);
    }

    @Nested
    @DisplayName("Top-level scalar fields")
    class TopLevelFields {

        @Test void parsesStringFields() {
            assertEquals("silver-USD-monthly", itemPrice.getId());
            assertEquals("silver USD monthly", itemPrice.getName());
            assertEquals("silver USD", itemPrice.getExternalName());
            assertEquals("silver", itemPrice.getItemId());
            assertEquals("USD", itemPrice.getCurrencyCode());
        }

        @Test void parsesBooleanFields() {
            assertEquals(true, itemPrice.getIsTaxable());
        }

        @Test void parsesIntegerFields() {
            assertEquals(1, itemPrice.getPeriod());
            assertEquals(0, itemPrice.getFreeQuantity());
        }

        @Test void parsesLongFields() {
            assertEquals(1000L, itemPrice.getPrice());
            assertEquals(1594106928574L, itemPrice.getResourceVersion());
        }

        @Test void parsesEnumFields() {
            assertEquals(ItemPrice.Status.ACTIVE, itemPrice.getStatus());
            assertEquals(ItemPrice.PricingModel.PER_UNIT, itemPrice.getPricingModel());
            assertEquals(ItemPrice.PeriodUnit.MONTH, itemPrice.getPeriodUnit());
            assertEquals(ItemPrice.ItemType.PLAN, itemPrice.getItemType());
            assertEquals(ItemPrice.Channel._UNKNOWN, itemPrice.getChannel());
        }

        @Test void parsesTimestampFields() {
            Timestamp ts = new Timestamp(1594106928L * 1000);
            assertEquals(ts, itemPrice.getCreatedAt());
            assertEquals(ts, itemPrice.getUpdatedAt());
        }

        @Test void absentFieldsAreNull() {
            assertNull(itemPrice.getDescription());
            assertNull(itemPrice.getItemFamilyId());
            assertNull(itemPrice.getPriceVariantId());
            assertNull(itemPrice.getPriceInDecimal());
            assertNull(itemPrice.getTrialPeriod());
            assertNull(itemPrice.getBillingCycles());
            assertNull(itemPrice.getShippingPeriod());
            assertNull(itemPrice.getArchivedAt());
            assertNull(itemPrice.getInvoiceNotes());
            assertNull(itemPrice.getBusinessEntityId());
            assertNull(itemPrice.getTaxDetail());
            assertNull(itemPrice.getAccountingDetail());
            assertNull(itemPrice.getDeleted());
        }
    }

    @Nested
    @DisplayName("Empty collections")
    class EmptyCollections {

        @Test void emptyArraysAreEmptyLists() {
            assertNotNull(itemPrice.getTiers());
            assertTrue(itemPrice.getTiers().isEmpty());

            assertNotNull(itemPrice.getTaxProvidersFields());
            assertTrue(itemPrice.getTaxProvidersFields().isEmpty());
        }
    }

    @Nested
    @DisplayName("Custom fields")
    class CustomFieldsTests {

        @Test void parsesCustomFields() {
            assertNotNull(itemPrice.getCustomFields());
            assertEquals(2, itemPrice.getCustomFields().size());
            assertEquals("saas", itemPrice.getCustomField("cf_product_line"));
            assertEquals("silver", itemPrice.getCustomField("cf_tier_level"));
        }
    }
}
