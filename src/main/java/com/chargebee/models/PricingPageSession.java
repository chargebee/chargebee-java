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

public class PricingPageSession extends Resource<PricingPageSession> {

    //Constructors
    //============

    public PricingPageSession(String jsonStr) {
        super(jsonStr);
    }

    public PricingPageSession(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return optString("id");
    }

    public String url() {
        return optString("url");
    }

    public Timestamp createdAt() {
        return optTimestamp("created_at");
    }

    public Timestamp expiresAt() {
        return optTimestamp("expires_at");
    }

    // Operations
    //===========

    public static CreateForNewSubscriptionRequest createForNewSubscription() {
        String uri = uri("pricing_page_sessions", "create_for_new_subscription");
        return new CreateForNewSubscriptionRequest(Method.POST, uri);
    }

    public static CreateForExistingSubscriptionRequest createForExistingSubscription() {
        String uri = uri("pricing_page_sessions", "create_for_existing_subscription");
        return new CreateForExistingSubscriptionRequest(Method.POST, uri);
    }


    // Operation Request Classes
    //==========================

    public static class CreateForNewSubscriptionRequest extends Request<CreateForNewSubscriptionRequest> {

        private CreateForNewSubscriptionRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateForNewSubscriptionRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public CreateForNewSubscriptionRequest businessEntityId(String businessEntityId) {
            params.addOpt("business_entity_id", businessEntityId);
            return this;
        }


        public CreateForNewSubscriptionRequest autoSelectLocalCurrency(Boolean autoSelectLocalCurrency) {
            params.addOpt("auto_select_local_currency", autoSelectLocalCurrency);
            return this;
        }


        public CreateForNewSubscriptionRequest pricingPageId(String pricingPageId) {
            params.add("pricing_page[id]", pricingPageId);
            return this;
        }
        
        public CreateForNewSubscriptionRequest subscriptionId(String subscriptionId) {
            params.addOpt("subscription[id]", subscriptionId);
            return this;
        }
        
        public CreateForNewSubscriptionRequest customerId(String customerId) {
            params.addOpt("customer[id]", customerId);
            return this;
        }
        
        public CreateForNewSubscriptionRequest customerEmail(String customerEmail) {
            params.addOpt("customer[email]", customerEmail);
            return this;
        }
        
        public CreateForNewSubscriptionRequest customerFirstName(String customerFirstName) {
            params.addOpt("customer[first_name]", customerFirstName);
            return this;
        }
        
        public CreateForNewSubscriptionRequest customerLastName(String customerLastName) {
            params.addOpt("customer[last_name]", customerLastName);
            return this;
        }
        
        public CreateForNewSubscriptionRequest customerCompany(String customerCompany) {
            params.addOpt("customer[company]", customerCompany);
            return this;
        }
        
        public CreateForNewSubscriptionRequest customerPhone(String customerPhone) {
            params.addOpt("customer[phone]", customerPhone);
            return this;
        }
        
        public CreateForNewSubscriptionRequest customerLocale(String customerLocale) {
            params.addOpt("customer[locale]", customerLocale);
            return this;
        }
        
        public CreateForNewSubscriptionRequest billingAddressFirstName(String billingAddressFirstName) {
            params.addOpt("billing_address[first_name]", billingAddressFirstName);
            return this;
        }
        
        public CreateForNewSubscriptionRequest billingAddressLastName(String billingAddressLastName) {
            params.addOpt("billing_address[last_name]", billingAddressLastName);
            return this;
        }
        
        public CreateForNewSubscriptionRequest billingAddressEmail(String billingAddressEmail) {
            params.addOpt("billing_address[email]", billingAddressEmail);
            return this;
        }
        
        public CreateForNewSubscriptionRequest billingAddressCompany(String billingAddressCompany) {
            params.addOpt("billing_address[company]", billingAddressCompany);
            return this;
        }
        
        public CreateForNewSubscriptionRequest billingAddressPhone(String billingAddressPhone) {
            params.addOpt("billing_address[phone]", billingAddressPhone);
            return this;
        }
        
        public CreateForNewSubscriptionRequest billingAddressLine1(String billingAddressLine1) {
            params.addOpt("billing_address[line1]", billingAddressLine1);
            return this;
        }
        
        public CreateForNewSubscriptionRequest billingAddressLine2(String billingAddressLine2) {
            params.addOpt("billing_address[line2]", billingAddressLine2);
            return this;
        }
        
        public CreateForNewSubscriptionRequest billingAddressLine3(String billingAddressLine3) {
            params.addOpt("billing_address[line3]", billingAddressLine3);
            return this;
        }
        
        public CreateForNewSubscriptionRequest billingAddressCity(String billingAddressCity) {
            params.addOpt("billing_address[city]", billingAddressCity);
            return this;
        }
        
        public CreateForNewSubscriptionRequest billingAddressStateCode(String billingAddressStateCode) {
            params.addOpt("billing_address[state_code]", billingAddressStateCode);
            return this;
        }
        
        public CreateForNewSubscriptionRequest billingAddressState(String billingAddressState) {
            params.addOpt("billing_address[state]", billingAddressState);
            return this;
        }
        
        public CreateForNewSubscriptionRequest billingAddressZip(String billingAddressZip) {
            params.addOpt("billing_address[zip]", billingAddressZip);
            return this;
        }
        
        public CreateForNewSubscriptionRequest billingAddressCountry(String billingAddressCountry) {
            params.addOpt("billing_address[country]", billingAddressCountry);
            return this;
        }
        
        public CreateForNewSubscriptionRequest billingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus billingAddressValidationStatus) {
            params.addOpt("billing_address[validation_status]", billingAddressValidationStatus);
            return this;
        }
        
        public CreateForNewSubscriptionRequest shippingAddressFirstName(String shippingAddressFirstName) {
            params.addOpt("shipping_address[first_name]", shippingAddressFirstName);
            return this;
        }
        
        public CreateForNewSubscriptionRequest shippingAddressLastName(String shippingAddressLastName) {
            params.addOpt("shipping_address[last_name]", shippingAddressLastName);
            return this;
        }
        
        public CreateForNewSubscriptionRequest shippingAddressEmail(String shippingAddressEmail) {
            params.addOpt("shipping_address[email]", shippingAddressEmail);
            return this;
        }
        
        public CreateForNewSubscriptionRequest shippingAddressCompany(String shippingAddressCompany) {
            params.addOpt("shipping_address[company]", shippingAddressCompany);
            return this;
        }
        
        public CreateForNewSubscriptionRequest shippingAddressPhone(String shippingAddressPhone) {
            params.addOpt("shipping_address[phone]", shippingAddressPhone);
            return this;
        }
        
        public CreateForNewSubscriptionRequest shippingAddressLine1(String shippingAddressLine1) {
            params.addOpt("shipping_address[line1]", shippingAddressLine1);
            return this;
        }
        
        public CreateForNewSubscriptionRequest shippingAddressLine2(String shippingAddressLine2) {
            params.addOpt("shipping_address[line2]", shippingAddressLine2);
            return this;
        }
        
        public CreateForNewSubscriptionRequest shippingAddressLine3(String shippingAddressLine3) {
            params.addOpt("shipping_address[line3]", shippingAddressLine3);
            return this;
        }
        
        public CreateForNewSubscriptionRequest shippingAddressCity(String shippingAddressCity) {
            params.addOpt("shipping_address[city]", shippingAddressCity);
            return this;
        }
        
        public CreateForNewSubscriptionRequest shippingAddressStateCode(String shippingAddressStateCode) {
            params.addOpt("shipping_address[state_code]", shippingAddressStateCode);
            return this;
        }
        
        public CreateForNewSubscriptionRequest shippingAddressState(String shippingAddressState) {
            params.addOpt("shipping_address[state]", shippingAddressState);
            return this;
        }
        
        public CreateForNewSubscriptionRequest shippingAddressZip(String shippingAddressZip) {
            params.addOpt("shipping_address[zip]", shippingAddressZip);
            return this;
        }
        
        public CreateForNewSubscriptionRequest shippingAddressCountry(String shippingAddressCountry) {
            params.addOpt("shipping_address[country]", shippingAddressCountry);
            return this;
        }
        
        public CreateForNewSubscriptionRequest shippingAddressValidationStatus(com.chargebee.models.enums.ValidationStatus shippingAddressValidationStatus) {
            params.addOpt("shipping_address[validation_status]", shippingAddressValidationStatus);
            return this;
        }
        
        public CreateForNewSubscriptionRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.addOpt("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public CreateForNewSubscriptionRequest discountDurationType(int index, com.chargebee.models.enums.DurationType discountDurationType) {
            params.add("discounts[duration_type][" + index + "]", discountDurationType);
            return this;
        }
        public CreateForNewSubscriptionRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public CreateForNewSubscriptionRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public CreateForNewSubscriptionRequest discountPeriod(int index, Integer discountPeriod) {
            params.addOpt("discounts[period][" + index + "]", discountPeriod);
            return this;
        }
        public CreateForNewSubscriptionRequest discountPeriodUnit(int index, com.chargebee.models.enums.PeriodUnit discountPeriodUnit) {
            params.addOpt("discounts[period_unit][" + index + "]", discountPeriodUnit);
            return this;
        }
        public CreateForNewSubscriptionRequest discountIncludedInMrr(int index, Boolean discountIncludedInMrr) {
            params.addOpt("discounts[included_in_mrr][" + index + "]", discountIncludedInMrr);
            return this;
        }
        public CreateForNewSubscriptionRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        public CreateForNewSubscriptionRequest discountQuantity(int index, Integer discountQuantity) {
            params.addOpt("discounts[quantity][" + index + "]", discountQuantity);
            return this;
        }
        public CreateForNewSubscriptionRequest discountLabel(int index, String discountLabel) {
            params.addOpt("discounts[label][" + index + "]", discountLabel);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

    public static class CreateForExistingSubscriptionRequest extends Request<CreateForExistingSubscriptionRequest> {

        private CreateForExistingSubscriptionRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }
    
        public CreateForExistingSubscriptionRequest redirectUrl(String redirectUrl) {
            params.addOpt("redirect_url", redirectUrl);
            return this;
        }


        public CreateForExistingSubscriptionRequest pricingPageId(String pricingPageId) {
            params.add("pricing_page[id]", pricingPageId);
            return this;
        }
        
        public CreateForExistingSubscriptionRequest subscriptionId(String subscriptionId) {
            params.add("subscription[id]", subscriptionId);
            return this;
        }
        
        public CreateForExistingSubscriptionRequest discountApplyOn(int index, com.chargebee.models.enums.ApplyOn discountApplyOn) {
            params.addOpt("discounts[apply_on][" + index + "]", discountApplyOn);
            return this;
        }
        public CreateForExistingSubscriptionRequest discountDurationType(int index, com.chargebee.models.enums.DurationType discountDurationType) {
            params.add("discounts[duration_type][" + index + "]", discountDurationType);
            return this;
        }
        public CreateForExistingSubscriptionRequest discountPercentage(int index, Double discountPercentage) {
            params.addOpt("discounts[percentage][" + index + "]", discountPercentage);
            return this;
        }
        public CreateForExistingSubscriptionRequest discountAmount(int index, Long discountAmount) {
            params.addOpt("discounts[amount][" + index + "]", discountAmount);
            return this;
        }
        public CreateForExistingSubscriptionRequest discountPeriod(int index, Integer discountPeriod) {
            params.addOpt("discounts[period][" + index + "]", discountPeriod);
            return this;
        }
        public CreateForExistingSubscriptionRequest discountPeriodUnit(int index, com.chargebee.models.enums.PeriodUnit discountPeriodUnit) {
            params.addOpt("discounts[period_unit][" + index + "]", discountPeriodUnit);
            return this;
        }
        public CreateForExistingSubscriptionRequest discountIncludedInMrr(int index, Boolean discountIncludedInMrr) {
            params.addOpt("discounts[included_in_mrr][" + index + "]", discountIncludedInMrr);
            return this;
        }
        public CreateForExistingSubscriptionRequest discountItemPriceId(int index, String discountItemPriceId) {
            params.addOpt("discounts[item_price_id][" + index + "]", discountItemPriceId);
            return this;
        }
        public CreateForExistingSubscriptionRequest discountQuantity(int index, Integer discountQuantity) {
            params.addOpt("discounts[quantity][" + index + "]", discountQuantity);
            return this;
        }
        public CreateForExistingSubscriptionRequest discountLabel(int index, String discountLabel) {
            params.addOpt("discounts[label][" + index + "]", discountLabel);
            return this;
        }
        @Override
        public Params params() {
            return params;
        }
    }

}
