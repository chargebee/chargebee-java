package com.chargebee.internal;

import com.chargebee.models.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;
import java.util.ArrayList;

public class ResultBase {

    private JSONObject jsonObj;
    
    public ResultBase(JSONObject jsonObj) {
        this.jsonObj = jsonObj;
    }


    public Subscription subscription() {
        return (Subscription)get("subscription");
    }

    public Customer customer() {
        return (Customer)get("customer");
    }

    public PaymentSource paymentSource() {
        return (PaymentSource)get("payment_source");
    }

    public ThirdPartyPaymentMethod thirdPartyPaymentMethod() {
        return (ThirdPartyPaymentMethod)get("third_party_payment_method");
    }

    public Card card() {
        return (Card)get("card");
    }

    public PromotionalCredit promotionalCredit() {
        return (PromotionalCredit)get("promotional_credit");
    }

    public Invoice invoice() {
        return (Invoice)get("invoice");
    }

    public CreditNote creditNote() {
        return (CreditNote)get("credit_note");
    }

    public UnbilledCharge unbilledCharge() {
        return (UnbilledCharge)get("unbilled_charge");
    }

    public Order order() {
        return (Order)get("order");
    }

    public Transaction transaction() {
        return (Transaction)get("transaction");
    }

    public HostedPage hostedPage() {
        return (HostedPage)get("hosted_page");
    }

    public Estimate estimate() {
        return (Estimate)get("estimate");
    }

    public Plan plan() {
        return (Plan)get("plan");
    }

    public Addon addon() {
        return (Addon)get("addon");
    }

    public Coupon coupon() {
        return (Coupon)get("coupon");
    }

    public CouponSet couponSet() {
        return (CouponSet)get("coupon_set");
    }

    public CouponCode couponCode() {
        return (CouponCode)get("coupon_code");
    }

    public Address address() {
        return (Address)get("address");
    }

    public Event event() {
        return (Event)get("event");
    }

    public Comment comment() {
        return (Comment)get("comment");
    }

    public Download download() {
        return (Download)get("download");
    }

    public PortalSession portalSession() {
        return (PortalSession)get("portal_session");
    }

    public SiteMigrationDetail siteMigrationDetail() {
        return (SiteMigrationDetail)get("site_migration_detail");
    }

    public ResourceMigration resourceMigration() {
        return (ResourceMigration)get("resource_migration");
    }

    public TimeMachine timeMachine() {
        return (TimeMachine)get("time_machine");
    }

    public List<UnbilledCharge> unbilledCharges() {
        return (List<UnbilledCharge>) getList("unbilled_charges", "unbilled_charge");
    }

    public List<CreditNote> creditNotes() {
        return (List<CreditNote>) getList("credit_notes", "credit_note");
    }

    public List<Invoice> invoices() {
        return (List<Invoice>) getList("invoices", "invoice");
    }


    private List<? extends Resource> getList(String pluralName, String singularName) {
        JSONArray listModels = jsonObj.optJSONArray(pluralName);
        if (listModels == null) {
            return null;
        }
        try {
            List<Resource> list = new ArrayList<Resource>();
            for (int i = 0; i < listModels.length(); i++) {
                JSONObject modelJson = listModels.getJSONObject(i);
                list.add(_get(singularName, modelJson));
            }
            return list;
        } catch (JSONException jsonExp) {
            throw new RuntimeException(jsonExp);
        }
    }

    private Resource get(String key) {
        JSONObject modelJson = jsonObj.optJSONObject(key);
        return _get(key, modelJson);
    }

    private Resource _get(String key, JSONObject modelJson) {
        if(modelJson == null) {
            return null;
        }
        Class<Resource> modelClaz = ClazzUtil.getClaz(key);
        return ClazzUtil.createInstance(modelClaz, modelJson);
    }
    
    public JSONObject jsonObj(){
        return jsonObj;
    }

    @Override
    public String toString() {
        try {
            return jsonObj.toString(2);
        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        }
    }


}
