package com.chargebee.v4.internal;

import com.chargebee.v4.models.transaction.Transaction;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Transaction JSON parsing")
class TransactionParserTest {

    private static String transactionJson;
    private static Transaction transaction;

    @BeforeAll
    static void loadFixture() throws IOException {
        try (InputStream is = TransactionParserTest.class.getResourceAsStream("/fixtures/transactioin.json")) {
            assertNotNull(is, "fixtures/transactioin.json not found on classpath");
            transactionJson = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
        transaction = Transaction.fromJson(transactionJson);
    }

    @Nested
    @DisplayName("Top-level scalar fields")
    class TopLevelFields {

        @Test void parsesStringFields() {
            assertEquals("txn___test__KyVnHhSBWltv42pB", transaction.getId());
            assertEquals("__test__KyVnHhSBWltgF2p6", transaction.getCustomerId());
            assertEquals("gw___test__KyVnGlSBWltbL2AB", transaction.getGatewayAccountId());
            assertEquals("pm___test__KyVnHhSBWltu42p7", transaction.getPaymentSourceId());
            assertEquals("USD", transaction.getCurrencyCode());
            assertEquals("ch_1HUyC0Jv9j0DyntJiR6W37yv", transaction.getIdAtGateway());
            assertEquals("Payment complete.", transaction.getFraudReason());
            assertEquals("************1111", transaction.getMaskedCardNumber());
        }

        @Test void parsesBooleanFields() {
            assertEquals(false, transaction.getDeleted());
        }

        @Test void parsesLongFields() {
            assertEquals(1000L, transaction.getAmount());
            assertEquals(1000L, transaction.getAmountCapturable());
            assertEquals(1517505917000L, transaction.getResourceVersion());
        }

        @Test void parsesBigDecimalFields() {
            assertEquals(0, new BigDecimal("1").compareTo(transaction.getExchangeRate()));
        }

        @Test void parsesEnumFields() {
            assertEquals(Transaction.Status.SUCCESS, transaction.getStatus());
            assertEquals(Transaction.Type.AUTHORIZATION, transaction.getType());
            assertEquals(Transaction.PaymentMethod.CARD, transaction.getPaymentMethod());
            assertEquals(Transaction.Gateway.STRIPE, transaction.getGateway());
            assertEquals(Transaction.AuthorizationReason.BLOCKING_FUNDS, transaction.getAuthorizationReason());
            assertEquals(Transaction.FraudFlag._UNKNOWN, transaction.getFraudFlag());
            assertEquals(Transaction.InitiatorType._UNKNOWN, transaction.getInitiatorType());
        }

        @Test void parsesTimestampFields() {
            assertEquals(new Timestamp(1600968316L * 1000), transaction.getDate());
            assertEquals(new Timestamp(1517505917L * 1000), transaction.getUpdatedAt());
        }

        @Test void absentFieldsAreNull() {
            assertNull(transaction.getSubscriptionId());
            assertNull(transaction.getReferenceNumber());
            assertNull(transaction.getSettledAt());
            assertNull(transaction.getThreeDSecure());
            assertNull(transaction.getErrorCode());
            assertNull(transaction.getErrorText());
            assertNull(transaction.getVoidedAt());
            assertNull(transaction.getAmountUnused());
            assertNull(transaction.getReferenceTransactionId());
            assertNull(transaction.getRefundedTxnId());
            assertNull(transaction.getReferenceAuthorizationId());
            assertNull(transaction.getReversalTransactionId());
            assertNull(transaction.getBusinessEntityId());
            assertNull(transaction.getErrorDetail());
        }
    }

    @Nested
    @DisplayName("Empty/absent collections")
    class EmptyCollections {

        @Test void emptyArraysAreEmptyLists() {
            assertNotNull(transaction.getLinkedInvoices());
            assertTrue(transaction.getLinkedInvoices().isEmpty());

            assertNotNull(transaction.getLinkedCreditNotes());
            assertTrue(transaction.getLinkedCreditNotes().isEmpty());

            assertNotNull(transaction.getLinkedRefunds());
            assertTrue(transaction.getLinkedRefunds().isEmpty());

            assertNotNull(transaction.getLinkedPayments());
            assertTrue(transaction.getLinkedPayments().isEmpty());
        }
    }
}
