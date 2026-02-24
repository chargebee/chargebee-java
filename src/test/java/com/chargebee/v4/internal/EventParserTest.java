package com.chargebee.v4.internal;

import com.chargebee.v4.models.card.Card;
import com.chargebee.v4.models.customer.Customer;
import com.chargebee.v4.models.event.Event;
import com.chargebee.v4.models.event.Event.*;
import com.chargebee.v4.models.invoice.Invoice;
import com.chargebee.v4.models.subscription.Subscription;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Event JSON parsing")
class EventParserTest {

    private static Event event;

    @BeforeAll
    static void loadFixture() throws IOException {
        String raw;
        try (InputStream is = EventParserTest.class.getResourceAsStream("/fixtures/events.json")) {
            assertNotNull(is, "fixtures/events.json not found on classpath");
            raw = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
        String eventJson = JsonUtil.getObject(raw, "event");
        assertNotNull(eventJson, "could not extract 'event' wrapper from fixture");
        event = Event.fromJson(eventJson);
    }

    @Nested
    @DisplayName("Top-level scalar fields")
    class TopLevelFields {

        @Test void parsesStringFields() {
            assertEquals("ev_16BPgETyVrQbiGhA", event.getId());
            assertEquals("sarah@sarah.com", event.getUser());
        }

        @Test void parsesEnumFields() {
            assertEquals(Event.Source.ADMIN_CONSOLE, event.getSource());
            assertEquals(Event.EventType.SUBSCRIPTION_CREATED, event.getEventType());
            assertEquals(Event.ApiVersion.V2, event.getApiVersion());
            assertEquals(Event.WebhookStatus.NOT_CONFIGURED, event.getWebhookStatus());
        }

        @Test void parsesTimestampFields() {
            assertEquals(new Timestamp(1702645601L * 1000), event.getOccurredAt());
        }

        @Test void absentFieldsAreNull() {
            assertNull(event.getOriginUser());
            assertNull(event.getWebhookFailureReason());
        }
    }

    @Nested
    @DisplayName("Content — embedded subscription")
    class ContentSubscriptionTests {

        @Test void parsesSubscription() {
            Map<String, Object> content = event.getContent();
            String subJson = (String) content.get("subscription");
            assertNotNull(subJson);
            Subscription sub = Subscription.fromJson(subJson);

            assertEquals("16BPgETyVrQVHGh1", sub.getId());
            assertEquals("sarah", sub.getCustomerId());
            assertEquals("INR", sub.getCurrencyCode());
            assertEquals(Subscription.Status.ACTIVE, sub.getStatus());
            assertEquals(Subscription.Channel.WEB, sub.getChannel());
            assertEquals(1, sub.getBillingPeriod());
            assertEquals(Subscription.BillingPeriodUnit.MONTH, sub.getBillingPeriodUnit());
            assertEquals(false, sub.getHasScheduledChanges());
            assertEquals(false, sub.getDeleted());
            assertEquals(0L, sub.getMrr());
            assertEquals(0, sub.getDueInvoicesCount());
            assertEquals("16CQtCTrgrYwi9n2E", sub.getBusinessEntityId());
            assertEquals(new Timestamp(1702645601L * 1000), sub.getCreatedAt());
            assertEquals(new Timestamp(1702578600L * 1000), sub.getStartedAt());
            assertEquals(new Timestamp(1702578600L * 1000), sub.getActivatedAt());

            assertNotNull(sub.getSubscriptionItems());
            assertEquals(1, sub.getSubscriptionItems().size());
            Subscription.SubscriptionItems item = sub.getSubscriptionItems().get(0);
            assertEquals("cross-train-advanced-INR-1_MONTH", item.getItemPriceId());
            assertEquals(Subscription.SubscriptionItems.ItemType.PLAN, item.getItemType());
            assertEquals(1, item.getQuantity());
            assertEquals(11667L, item.getUnitPrice());
            assertEquals(11667L, item.getAmount());
        }
    }

    @Nested
    @DisplayName("Content — embedded customer")
    class ContentCustomerTests {

        @Test void parsesCustomer() {
            Map<String, Object> content = event.getContent();
            String custJson = (String) content.get("customer");
            assertNotNull(custJson);
            Customer cust = Customer.fromJson(custJson);

            assertEquals("sarah", cust.getId());
            assertEquals(Customer.AutoCollection.ON, cust.getAutoCollection());
            assertEquals(0, cust.getNetTermDays());
            assertEquals(false, cust.getAllowDirectDebit());
            assertEquals(Customer.Taxability.TAXABLE, cust.getTaxability());
            assertEquals(Customer.PiiCleared.ACTIVE, cust.getPiiCleared());
            assertEquals(Customer.Channel.WEB, cust.getChannel());
            assertEquals(Customer.CardStatus.VALID, cust.getCardStatus());
            assertEquals("INR", cust.getPreferredCurrencyCode());
            assertEquals(0L, cust.getPromotionalCredits());
            assertEquals(0L, cust.getRefundableCredits());
            assertEquals(0L, cust.getExcessPayments());
            assertEquals(0L, cust.getUnbilledCharges());
            assertEquals("pm_169vujTyVrL5fFDl", cust.getPrimaryPaymentSourceId());
            assertEquals(false, cust.getDeleted());
            assertEquals(true, cust.getAutoCloseInvoices());

            assertNotNull(cust.getPaymentMethod());
            assertEquals(Customer.PaymentMethod.Type.CARD, cust.getPaymentMethod().getType());
            assertEquals(Customer.PaymentMethod.Gateway.CHARGEBEE, cust.getPaymentMethod().getGateway());
            assertEquals("gw_1mk51R4QrLmQtYMht", cust.getPaymentMethod().getGatewayAccountId());
            assertEquals(Customer.PaymentMethod.Status.VALID, cust.getPaymentMethod().getStatus());
            assertEquals("tok_169vujTyVrL5LFDk", cust.getPaymentMethod().getReferenceId());
        }
    }

    @Nested
    @DisplayName("Content — embedded card")
    class ContentCardTests {

        @Test void parsesCard() {
            Map<String, Object> content = event.getContent();
            String cardJson = (String) content.get("card");
            assertNotNull(cardJson);
            Card card = Card.fromJson(cardJson);

            assertEquals("pm_169vujTyVrL5fFDl", card.getPaymentSourceId());
            assertEquals("boom", card.getCustomerId());
            assertEquals(Card.Status.VALID, card.getStatus());
            assertEquals(Card.Gateway.CHARGEBEE, card.getGateway());
            assertEquals("gw_1mk51R4QrLmQtYMht", card.getGatewayAccountId());
            assertEquals("411111", card.getIin());
            assertEquals("1111", card.getLast4());
            assertEquals(Card.CardType.VISA, card.getCardType());
            assertEquals(Card.FundingType.CREDIT, card.getFundingType());
            assertEquals(12, card.getExpiryMonth());
            assertEquals(2024, card.getExpiryYear());
            assertEquals("************1111", card.getMaskedNumber());
            assertEquals("10.0.0.1", card.getIpAddress());
            assertEquals(new Timestamp(1702645580L * 1000), card.getCreatedAt());
            assertEquals(new Timestamp(1702645580L * 1000), card.getUpdatedAt());
            assertEquals(1702645580740L, card.getResourceVersion());
        }
    }

    @Nested
    @DisplayName("Content — embedded invoice")
    class ContentInvoiceTests {

        @Test void parsesInvoice() {
            Map<String, Object> content = event.getContent();
            String invJson = (String) content.get("invoice");
            assertNotNull(invJson);
            Invoice inv = Invoice.fromJson(invJson);

            assertEquals("203", inv.getId());
            assertEquals("boom", inv.getCustomerId());
            assertEquals("16BPgETyVrQVHGh1", inv.getSubscriptionId());
            assertEquals(true, inv.getRecurring());
            assertEquals(Invoice.Status.PAID, inv.getStatus());
            assertEquals(Invoice.PriceType.TAX_EXCLUSIVE, inv.getPriceType());
            assertEquals(Invoice.Channel.WEB, inv.getChannel());
            assertEquals("INR", inv.getCurrencyCode());
            assertEquals(11667L, inv.getTotal());
            assertEquals(11667L, inv.getAmountPaid());
            assertEquals(11667L, inv.getSubTotal());
            assertEquals(11667L, inv.getNewSalesAmount());
            assertEquals(0L, inv.getTax());
            assertEquals(0L, inv.getAmountDue());
            assertEquals(0L, inv.getWriteOffAmount());
            assertEquals(0L, inv.getCreditsApplied());
            assertEquals(true, inv.getFirstInvoice());
            assertEquals(false, inv.getIsGifted());
            assertEquals(true, inv.getTermFinalized());
            assertEquals(false, inv.getDeleted());
            assertEquals("16CQtCTrgrYwi9n2E", inv.getBusinessEntityId());

            assertNotNull(inv.getLineItems());
            assertEquals(1, inv.getLineItems().size());
            Invoice.LineItems li = inv.getLineItems().get(0);
            assertEquals("li_16BPgETyVrQWBGh3", li.getId());
            assertEquals(11667L, li.getAmount());
            assertEquals(Invoice.LineItems.EntityType.PLAN_ITEM_PRICE, li.getEntityType());
            assertEquals(Invoice.LineItems.TaxExemptReason.EXPORT, li.getTaxExemptReason());

            assertNotNull(inv.getLinkedPayments());
            assertEquals(1, inv.getLinkedPayments().size());
            Invoice.LinkedPayments lp = inv.getLinkedPayments().get(0);
            assertEquals("txn_16BPgETyVrQXVGh4", lp.getTxnId());
            assertEquals(11667L, lp.getAppliedAmount());
            assertEquals(Invoice.LinkedPayments.TxnStatus.SUCCESS, lp.getTxnStatus());

            assertNotNull(inv.getNotes());
            assertEquals(1, inv.getNotes().size());
        }
    }

    @Nested
    @DisplayName("Webhooks array")
    class WebhooksTests {

        @Test void parsesOneWebhook() {
            assertNotNull(event.getWebhooks());
            assertEquals(1, event.getWebhooks().size());
        }

        @Test void webhookFields() {
            Webhooks wh = event.getWebhooks().get(0);
            assertEquals("whv2_Azz5aITsMVdKtVWV", wh.getId());
            assertEquals(Webhooks.WebhookStatus.NOT_APPLICABLE, wh.getWebhookStatus());
        }
    }
}
