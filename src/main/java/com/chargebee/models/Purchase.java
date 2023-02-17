package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.filters.*;
import com.chargebee.filters.enums.SortOrder;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class Purchase extends Resource<Purchase> {

    //Constructors
    //============

    public Purchase(String jsonStr) {
        super(jsonStr);
    }

    public Purchase(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return optString("id");
    }

    public String customerId() {
        return reqString("customer_id");
    }

    public Timestamp createdAt() {
        return optTimestamp("created_at");
    }

    public Timestamp modifiedAt() {
        return optTimestamp("modified_at");
    }

    public List<String> subscriptionIds() {
        return optList("subscription_ids", String.class);
    }

    public List<String> invoiceIds() {
        return optList("invoice_ids", String.class);
    }

    // Operations
    //===========

    public static CreateRequest create() {
        String uri = uri("purchases");
        return new CreateRequest(Method.POST, uri);
    }

    public static EstimateRequest estimate() {
        String uri = uri("purchases", "estimate");
        return new EstimateRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateRequest extends Request<CreateRequest> {

        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateRequest customerId(String customerId) {
            params.add("customer_id", customerId);
            return this;
        }


        public CreateRequest invoiceInfoPoNumber(String invoiceInfoPoNumber) {
            params.addOpt("invoice_info[po_number]", invoiceInfoPoNumber);
            return this;
        }

        public CreateRequest invoiceInfoNotes(String invoiceInfoNotes) {
            params.addOpt("invoice_info[notes]", invoiceInfoNotes);
            return this;
        }

        public CreateRequest purchaseItemIndex(int index, Integer purchaseItemIndex) {
            params.add("purchase_items[index][" + index + "]", purchaseItemIndex);
            return this;
        }
        public CreateRequest purchaseItemItemPriceId(int index, String purchaseItemItemPriceId) {
            params.add("purchase_items[item_price_id][" + index + "]", purchaseItemItemPriceId);
            return this;
        }
        public CreateRequest purchaseItemQuantity(int index, Integer purchaseItemQuantity) {
            params.addOpt("purchase_items[quantity][" + index + "]", purchaseItemQuantity);
            return this;
        }
        public CreateRequest purchaseItemUnitAmount(int index, Long purchaseItemUnitAmount) {
            params.addOpt("purchase_items[unit_amount][" + index + "]", purchaseItemUnitAmount);
            return this;
        }
        public CreateRequest purchaseItemUnitAmountInDecimal(int index, String purchaseItemUnitAmountInDecimal) {
            params.addOpt("purchase_items[unit_amount_in_decimal][" + index + "]", purchaseItemUnitAmountInDecimal);
            return this;
        }
        public CreateRequest purchaseItemQuantityInDecimal(int index, String purchaseItemQuantityInDecimal) {
            params.addOpt("purchase_items[quantity_in_decimal][" + index + "]", purchaseItemQuantityInDecimal);
            return this;
        }
        public CreateRequest itemTierIndex(int index, Integer itemTierIndex) {
            params.add("item_tiers[index][" + index + "]", itemTierIndex);
            return this;
        }
        public CreateRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public CreateRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public CreateRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public CreateRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public CreateRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public CreateRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public CreateRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            return this;
        }
        public CreateRequest shippingAddressFirstName(int index, String shippingAddressFirstName) {
            params.addOpt("shipping_addresses[first_name][" + index + "]", shippingAddressFirstName);
            return this;
        }
        public CreateRequest shippingAddressLastName(int index, String shippingAddressLastName) {
            params.addOpt("shipping_addresses[last_name][" + index + "]", shippingAddressLastName);
            return this;
        }
        public CreateRequest shippingAddressEmail(int index, String shippingAddressEmail) {
            params.addOpt("shipping_addresses[email][" + index + "]", shippingAddressEmail);
            return this;
        }
        public CreateRequest shippingAddressCompany(int index, String shippingAddressCompany) {
            params.addOpt("shipping_addresses[company][" + index + "]", shippingAddressCompany);
            return this;
        }
        public CreateRequest shippingAddressPhone(int index, String shippingAddressPhone) {
            params.addOpt("shipping_addresses[phone][" + index + "]", shippingAddressPhone);
            return this;
        }
        public CreateRequest shippingAddressLine1(int index, String shippingAddressLine1) {
            params.addOpt("shipping_addresses[line1][" + index + "]", shippingAddressLine1);
            return this;
        }
        public CreateRequest shippingAddressLine2(int index, String shippingAddressLine2) {
            params.addOpt("shipping_addresses[line2][" + index + "]", shippingAddressLine2);
            return this;
        }
        public CreateRequest shippingAddressLine3(int index, String shippingAddressLine3) {
            params.addOpt("shipping_addresses[line3][" + index + "]", shippingAddressLine3);
            return this;
        }
        public CreateRequest shippingAddressCity(int index, String shippingAddressCity) {
            params.addOpt("shipping_addresses[city][" + index + "]", shippingAddressCity);
            return this;
        }
        public CreateRequest shippingAddressState(int index, String shippingAddressState) {
            params.addOpt("shipping_addresses[state][" + index + "]", shippingAddressState);
            return this;
        }
        public CreateRequest shippingAddressStateCode(int index, String shippingAddressStateCode) {
            params.addOpt("shipping_addresses[state_code][" + index + "]", shippingAddressStateCode);
            return this;
        }
        public CreateRequest shippingAddressCountry(int index, String shippingAddressCountry) {
            params.addOpt("shipping_addresses[country][" + index + "]", shippingAddressCountry);
            return this;
        }
        public CreateRequest shippingAddressZip(int index, String shippingAddressZip) {
            params.addOpt("shipping_addresses[zip][" + index + "]", shippingAddressZip);
            return this;
        }
        public CreateRequest shippingAddressValidationStatus(int index, com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_addresses[validation_status][" + index + "]", shippingAddressValidationStatus);
            return this;
        }
        public CreateRequest discountIndex(int index, Integer discountIndex) {
            params.addOpt("discounts[index][" + index + "]", discountIndex);
            return this;
        }
        public CreateRequest discountCouponId(int index, String discountCouponId) {
            params.addOpt("discounts[coupon_id][" + index + "]", discountCouponId);
            return this;
        }
        public CreateRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public CreateRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public CreateRequest discountIncludedInMrr(int index, Boolean discountIncludedInMrr) {
            params.addOpt("discounts[included_in_mrr][" + index + "]", discountIncludedInMrr);
            return this;
        }
        public CreateRequest subscriptionInfoIndex(int index, Integer subscriptionInfoIndex) {
            params.add("subscription_info[index][" + index + "]", subscriptionInfoIndex);
            return this;
        }
        public CreateRequest subscriptionInfoSubscriptionId(int index, String subscriptionInfoSubscriptionId) {
            params.addOpt("subscription_info[subscription_id][" + index + "]", subscriptionInfoSubscriptionId);
            return this;
        }
        public CreateRequest subscriptionInfoBillingCycles(int index, Integer subscriptionInfoBillingCycles) {
            params.addOpt("subscription_info[billing_cycles][" + index + "]", subscriptionInfoBillingCycles);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class EstimateRequest extends Request<EstimateRequest> {

        private EstimateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public EstimateRequest clientProfileId(String clientProfileId) {
            params.addOpt("client_profile_id", clientProfileId);
            return this;
        }


        public EstimateRequest customerId(String customerId) {
            params.addOpt("customer_id", customerId);
            return this;
        }


        public EstimateRequest customerVatNumber(String customerVatNumber) {
            params.addOpt("customer[vat_number]", customerVatNumber);
            return this;
        }

        public EstimateRequest customerVatNumberPrefix(String customerVatNumberPrefix) {
            params.addOpt("customer[vat_number_prefix]", customerVatNumberPrefix);
            return this;
        }

        public EstimateRequest customerRegisteredForGst(Boolean customerRegisteredForGst) {
            params.addOpt("customer[registered_for_gst]", customerRegisteredForGst);
            return this;
        }

        public EstimateRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }

        public EstimateRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }

        public EstimateRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }

        public EstimateRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }

        public EstimateRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }

        public EstimateRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }

        public EstimateRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }

        public EstimateRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }

        public EstimateRequest customerTaxability(com.chargebee.models.enums.Taxability customerTaxability) {
            params.addOpt("customer[taxability]", customerTaxability);
            return this;
        }

        public EstimateRequest customerEntityCode(com.chargebee.models.enums.EntityCode customerEntityCode) {
            params.addOpt("customer[entity_code]", customerEntityCode);
            return this;
        }

        public EstimateRequest customerExemptNumber(String customerExemptNumber) {
            params.addOpt("customer[exempt_number]", customerExemptNumber);
            return this;
        }

        public EstimateRequest customerExemptionDetails(JSONArray customerExemptionDetails) {
            params.addOpt("customer[exemption_details]", customerExemptionDetails);
            return this;
        }

        public EstimateRequest customerCustomerType(com.chargebee.models.enums.CustomerType customerCustomerType) {
            params.addOpt("customer[customer_type]", customerCustomerType);
            return this;
        }

        public EstimateRequest purchaseItemIndex(int index, Integer purchaseItemIndex) {
            params.add("purchase_items[index][" + index + "]", purchaseItemIndex);
            return this;
        }
        public EstimateRequest purchaseItemItemPriceId(int index, String purchaseItemItemPriceId) {
            params.add("purchase_items[item_price_id][" + index + "]", purchaseItemItemPriceId);
            return this;
        }
        public EstimateRequest purchaseItemQuantity(int index, Integer purchaseItemQuantity) {
            params.addOpt("purchase_items[quantity][" + index + "]", purchaseItemQuantity);
            return this;
        }
        public EstimateRequest purchaseItemUnitAmount(int index, Long purchaseItemUnitAmount) {
            params.addOpt("purchase_items[unit_amount][" + index + "]", purchaseItemUnitAmount);
            return this;
        }
        public EstimateRequest purchaseItemUnitAmountInDecimal(int index, String purchaseItemUnitAmountInDecimal) {
            params.addOpt("purchase_items[unit_amount_in_decimal][" + index + "]", purchaseItemUnitAmountInDecimal);
            return this;
        }
        public EstimateRequest purchaseItemQuantityInDecimal(int index, String purchaseItemQuantityInDecimal) {
            params.addOpt("purchase_items[quantity_in_decimal][" + index + "]", purchaseItemQuantityInDecimal);
            return this;
        }
        public EstimateRequest itemTierIndex(int index, Integer itemTierIndex) {
            params.add("item_tiers[index][" + index + "]", itemTierIndex);
            return this;
        }
        public EstimateRequest itemTierItemPriceId(int index, String itemTierItemPriceId) {
            params.addOpt("item_tiers[item_price_id][" + index + "]", itemTierItemPriceId);
            return this;
        }
        public EstimateRequest itemTierStartingUnit(int index, Integer itemTierStartingUnit) {
            params.addOpt("item_tiers[starting_unit][" + index + "]", itemTierStartingUnit);
            return this;
        }
        public EstimateRequest itemTierEndingUnit(int index, Integer itemTierEndingUnit) {
            params.addOpt("item_tiers[ending_unit][" + index + "]", itemTierEndingUnit);
            return this;
        }
        public EstimateRequest itemTierPrice(int index, Long itemTierPrice) {
            params.addOpt("item_tiers[price][" + index + "]", itemTierPrice);
            return this;
        }
        public EstimateRequest itemTierStartingUnitInDecimal(int index, String itemTierStartingUnitInDecimal) {
            params.addOpt("item_tiers[starting_unit_in_decimal][" + index + "]", itemTierStartingUnitInDecimal);
            return this;
        }
        public EstimateRequest itemTierEndingUnitInDecimal(int index, String itemTierEndingUnitInDecimal) {
            params.addOpt("item_tiers[ending_unit_in_decimal][" + index + "]", itemTierEndingUnitInDecimal);
            return this;
        }
        public EstimateRequest itemTierPriceInDecimal(int index, String itemTierPriceInDecimal) {
            params.addOpt("item_tiers[price_in_decimal][" + index + "]", itemTierPriceInDecimal);
            return this;
        }
        public EstimateRequest shippingAddressFirstName(int index, String shippingAddressFirstName) {
            params.addOpt("shipping_addresses[first_name][" + index + "]", shippingAddressFirstName);
            return this;
        }
        public EstimateRequest shippingAddressLastName(int index, String shippingAddressLastName) {
            params.addOpt("shipping_addresses[last_name][" + index + "]", shippingAddressLastName);
            return this;
        }
        public EstimateRequest shippingAddressEmail(int index, String shippingAddressEmail) {
            params.addOpt("shipping_addresses[email][" + index + "]", shippingAddressEmail);
            return this;
        }
        public EstimateRequest shippingAddressCompany(int index, String shippingAddressCompany) {
            params.addOpt("shipping_addresses[company][" + index + "]", shippingAddressCompany);
            return this;
        }
        public EstimateRequest shippingAddressPhone(int index, String shippingAddressPhone) {
            params.addOpt("shipping_addresses[phone][" + index + "]", shippingAddressPhone);
            return this;
        }
        public EstimateRequest shippingAddressLine1(int index, String shippingAddressLine1) {
            params.addOpt("shipping_addresses[line1][" + index + "]", shippingAddressLine1);
            return this;
        }
        public EstimateRequest shippingAddressLine2(int index, String shippingAddressLine2) {
            params.addOpt("shipping_addresses[line2][" + index + "]", shippingAddressLine2);
            return this;
        }
        public EstimateRequest shippingAddressLine3(int index, String shippingAddressLine3) {
            params.addOpt("shipping_addresses[line3][" + index + "]", shippingAddressLine3);
            return this;
        }
        public EstimateRequest shippingAddressCity(int index, String shippingAddressCity) {
            params.addOpt("shipping_addresses[city][" + index + "]", shippingAddressCity);
            return this;
        }
        public EstimateRequest shippingAddressState(int index, String shippingAddressState) {
            params.addOpt("shipping_addresses[state][" + index + "]", shippingAddressState);
            return this;
        }
        public EstimateRequest shippingAddressStateCode(int index, String shippingAddressStateCode) {
            params.addOpt("shipping_addresses[state_code][" + index + "]", shippingAddressStateCode);
            return this;
        }
        public EstimateRequest shippingAddressCountry(int index, String shippingAddressCountry) {
            params.addOpt("shipping_addresses[country][" + index + "]", shippingAddressCountry);
            return this;
        }
        public EstimateRequest shippingAddressZip(int index, String shippingAddressZip) {
            params.addOpt("shipping_addresses[zip][" + index + "]", shippingAddressZip);
            return this;
        }
        public EstimateRequest shippingAddressValidationStatus(int index, com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_addresses[validation_status][" + index + "]", shippingAddressValidationStatus);
            return this;
        }
        public EstimateRequest discountIndex(int index, Integer discountIndex) {
            params.addOpt("discounts[index][" + index + "]", discountIndex);
            return this;
        }
        public EstimateRequest discountCouponId(int index, String discountCouponId) {
            params.addOpt("discounts[coupon_id][" + index + "]", discountCouponId);
            return this;
        }
        public EstimateRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public EstimateRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public EstimateRequest discountIncludedInMrr(int index, Boolean discountIncludedInMrr) {
            params.addOpt("discounts[included_in_mrr][" + index + "]", discountIncludedInMrr);
            return this;
        }
        public EstimateRequest subscriptionInfoIndex(int index, Integer subscriptionInfoIndex) {
            params.add("subscription_info[index][" + index + "]", subscriptionInfoIndex);
            return this;
        }
        public EstimateRequest subscriptionInfoSubscriptionId(int index, String subscriptionInfoSubscriptionId) {
            params.addOpt("subscription_info[subscription_id][" + index + "]", subscriptionInfoSubscriptionId);
            return this;
        }
        public EstimateRequest subscriptionInfoBillingCycles(int index, Integer subscriptionInfoBillingCycles) {
            params.addOpt("subscription_info[billing_cycles][" + index + "]", subscriptionInfoBillingCycles);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

}
