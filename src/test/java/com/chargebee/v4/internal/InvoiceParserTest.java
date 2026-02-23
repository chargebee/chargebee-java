package com.chargebee.v4.internal;

import com.chargebee.v4.models.invoice.Invoice;
import com.chargebee.v4.models.invoice.Invoice.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Invoice JSON parsing")
class InvoiceParserTest {

    private static String invoiceJson;
    private static Invoice invoice;

    @BeforeAll
    static void loadFixture() throws IOException {
        try (InputStream is = InvoiceParserTest.class.getResourceAsStream("/fixtures/invoice.json")) {
            assertNotNull(is, "fixtures/invoice.json not found on classpath");
            invoiceJson = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
        invoice = Invoice.fromJson(invoiceJson);
    }

    @Nested
    @DisplayName("Top-level scalar fields")
    class TopLevelFields {

        @Test void parsesStringFields() {
            assertEquals("__demo_inv__1", invoice.getId());
            assertEquals("__test__KyVkkWS1xLskm8", invoice.getCustomerId());
            assertEquals("USD", invoice.getCurrencyCode());
        }

        @Test void parsesBooleanFields() {
            assertEquals(false, invoice.getRecurring());
            assertEquals(true, invoice.getFirstInvoice());
            assertEquals(false, invoice.getHasAdvanceCharges());
            assertEquals(true, invoice.getTermFinalized());
            assertEquals(false, invoice.getIsGifted());
            assertEquals(false, invoice.getDeleted());
        }

        @Test void parsesIntegerFields() {
            assertEquals(0, invoice.getNetTermDays());
        }

        @Test void parsesLongFields() {
            assertEquals(0L, invoice.getAmountAdjusted());
            assertEquals(0L, invoice.getAmountDue());
            assertEquals(2000L, invoice.getAmountPaid());
            assertEquals(0L, invoice.getAmountToCollect());
            assertEquals(0L, invoice.getCreditsApplied());
            assertEquals(2000L, invoice.getNewSalesAmount());
            assertEquals(0L, invoice.getRoundOffAmount());
            assertEquals(1517463750000L, invoice.getResourceVersion());
            assertEquals(2000L, invoice.getSubTotal());
            assertEquals(0L, invoice.getTax());
            assertEquals(2000L, invoice.getTotal());
            assertEquals(0L, invoice.getWriteOffAmount());
        }

        @Test void parsesBigDecimalFields() {
            assertEquals(0, new BigDecimal("1").compareTo(invoice.getExchangeRate()));
        }

        @Test void parsesEnumFields() {
            assertEquals(Invoice.Status.PAID, invoice.getStatus());
            assertEquals(Invoice.PriceType.TAX_EXCLUSIVE, invoice.getPriceType());
            assertEquals(Invoice.Channel._UNKNOWN, invoice.getChannel());
        }

        @Test void parsesTimestampFields() {
            assertEquals(new Timestamp(1517463749L * 1000), invoice.getDate());
            assertEquals(new Timestamp(1517463749L * 1000), invoice.getDueDate());
            assertEquals(new Timestamp(1517463750L * 1000), invoice.getPaidAt());
            assertEquals(new Timestamp(1517463750L * 1000), invoice.getUpdatedAt());
        }

        @Test void absentFieldsAreNull() {
            assertNull(invoice.getSubscriptionId());
            assertNull(invoice.getPaymentOwner());
            assertNull(invoice.getPoNumber());
            assertNull(invoice.getVatNumber());
            assertNull(invoice.getVoidReasonCode());
            assertNull(invoice.getBusinessEntityId());
            assertNull(invoice.getStatementDescriptor());
            assertNull(invoice.getEinvoice());
            assertNull(invoice.getSiteDetailsAtCreation());
            assertNull(invoice.getTaxOrigin());
        }
    }

    @Nested
    @DisplayName("Billing address")
    class BillingAddressTests {

        @Test void parsesAllFields() {
            BillingAddress ba = invoice.getBillingAddress();
            assertNotNull(ba);
            assertEquals("John", ba.getFirstName());
            assertEquals("Mathew", ba.getLastName());
            assertEquals(BillingAddress.ValidationStatus.NOT_VALIDATED, ba.getValidationStatus());
        }

        @Test void absentSubFieldsAreNull() {
            BillingAddress ba = invoice.getBillingAddress();
            assertNull(ba.getCity());
            assertNull(ba.getCountry());
            assertNull(ba.getLine1());
            assertNull(ba.getZip());
        }
    }

    @Nested
    @DisplayName("Shipping address")
    class ShippingAddressTests {

        @Test void parsesAllFields() {
            ShippingAddress sa = invoice.getShippingAddress();
            assertNotNull(sa);
            assertEquals("John", sa.getFirstName());
            assertEquals("Mathew", sa.getLastName());
            assertEquals("Walnut", sa.getCity());
            assertEquals("US", sa.getCountry());
            assertEquals("California", sa.getState());
            assertEquals("CA", sa.getStateCode());
            assertEquals("91789", sa.getZip());
            assertEquals(ShippingAddress.ValidationStatus.NOT_VALIDATED, sa.getValidationStatus());
        }
    }

    @Nested
    @DisplayName("Line items array")
    class LineItemsTests {

        @Test void parsesTwoLineItems() {
            assertNotNull(invoice.getLineItems());
            assertEquals(2, invoice.getLineItems().size());
        }

        @Test void firstLineItemFields() {
            LineItems li = invoice.getLineItems().get(0);
            assertEquals("li___test__KyVkkWS1xLt9LF", li.getId());
            assertEquals("__test__KyVkkWS1xLskm8", li.getCustomerId());
            assertEquals("SSL Charge USD Monthly", li.getDescription());
            assertEquals("ssl-charge-USD", li.getEntityId());
            assertEquals(2000L, li.getAmount());
            assertEquals(2000L, li.getUnitAmount());
            assertEquals(1, li.getQuantity());
            assertEquals(0L, li.getDiscountAmount());
            assertEquals(0L, li.getItemLevelDiscountAmount());
            assertEquals(0L, li.getTaxAmount());
            assertEquals(false, li.getIsTaxed());
            assertEquals(LineItems.PricingModel.FLAT_FEE, li.getPricingModel());
            assertEquals(LineItems.EntityType.CHARGE_ITEM_PRICE, li.getEntityType());
            assertEquals(LineItems.TaxExemptReason.TAX_NOT_CONFIGURED, li.getTaxExemptReason());
            assertEquals(new Timestamp(1517463749L * 1000), li.getDateFrom());
            assertEquals(new Timestamp(1517463749L * 1000), li.getDateTo());
        }
    }

    @Nested
    @DisplayName("Linked payments array")
    class LinkedPaymentsTests {

        @Test void parsesTwoLinkedPayments() {
            assertNotNull(invoice.getLinkedPayments());
            assertEquals(2, invoice.getLinkedPayments().size());
        }

        @Test void firstLinkedPaymentFields() {
            LinkedPayments lp = invoice.getLinkedPayments().get(0);
            assertEquals("txn___test__KyVkkWS1xLtFiG", lp.getTxnId());
            assertEquals(2000L, lp.getAppliedAmount());
            assertEquals(2000L, lp.getTxnAmount());
            assertEquals(LinkedPayments.TxnStatus.SUCCESS, lp.getTxnStatus());
            assertEquals(new Timestamp(1517463750L * 1000), lp.getAppliedAt());
            assertEquals(new Timestamp(1517463750L * 1000), lp.getTxnDate());
        }

        @Test void secondLinkedPaymentHasZeroAppliedAmount() {
            LinkedPayments lp = invoice.getLinkedPayments().get(1);
            assertEquals(0L, lp.getAppliedAmount());
            assertEquals(2000L, lp.getTxnAmount());
        }
    }

    @Nested
    @DisplayName("Empty/absent collections")
    class EmptyCollections {

        @Test void emptyArraysAreEmptyLists() {
            assertNotNull(invoice.getLineItemTiers());
            assertTrue(invoice.getLineItemTiers().isEmpty());

            assertNotNull(invoice.getLineItemDiscounts());
            assertTrue(invoice.getLineItemDiscounts().isEmpty());

            assertNotNull(invoice.getLineItemTaxes());
            assertTrue(invoice.getLineItemTaxes().isEmpty());

            assertNotNull(invoice.getDiscounts());
            assertTrue(invoice.getDiscounts().isEmpty());

            assertNotNull(invoice.getTaxes());
            assertTrue(invoice.getTaxes().isEmpty());

            assertNotNull(invoice.getReferenceTransactions());
            assertTrue(invoice.getReferenceTransactions().isEmpty());

            assertNotNull(invoice.getNotes());
            assertTrue(invoice.getNotes().isEmpty());
        }
    }

    @Nested
    @DisplayName("Custom fields")
    class CustomFieldsTests {

        @Test void parsesCustomFields() {
            assertNotNull(invoice.getCustomFields());
            assertEquals(2, invoice.getCustomFields().size());
            assertEquals("engineering", invoice.getCustomField("cf_department"));
            assertEquals("CC-1042", invoice.getCustomField("cf_cost_center"));
        }
    }
}
