### v1.1.39  (2014-04-17)
* * *
Issue fix. For linked invoices and transactions, the enum values are used directly from Invoice & Transaction resource to represent type & status attributes.

### v1.1.38  (2014-03-25)
* * *
* Now the [Transaction attributes](https://apidocs.chargebee.com/docs/api/transactions#transaction_attributes "Transaction attributes") contains the details about the linked invoices.

* Now the [Invoice attributes](https://apidocs.chargebee.com/docs/api/invoices#invoice_attributes "Invoice attributes") contains the details about the linked transactions.

* Support for recording a payment received via offline mode. See our API documentation on [Record Payment for an Invoice](https://apidocs.chargebee.com/docs/api/transactions#record_payment_for_an_invoice "Record Payment for an Invoice")

### v1.1.37  (2014-03-19)
* * *
Support for deleting the plans & addons. See our API documentation on [Delete a plan](https://apidocs.chargebee.com/docs/api/plans#delete_a_plan "Delete a plan") & [Delete an addon](https://apidocs.chargebee.com/docs/api/addons#delete_an_addon "Delete an addon").

### v1.1.35  (2014-03-10)
* * *
* Support for creating coupons on the fly via API

* Support for updating the plans & addons.

* Now our hosted pages can be shown as popup checkout using our javascript API.

### v1.1.34  (2014-02-19)
* * *
* Support for passing shipping address for create subscription & update subscription API

* Added new attributes for the Address resource.

### v1.1.33  (2014-02-12)
* * *
* New resource Download added to expose the URLs from which you can download resources like invoice PDFs.

* Update card hosted page now support pass_thru_parameter like the checkout pages.

* Support for downloading invoice as PDF.

* Transaction resource now exposes the void description for transactions that are voided.

### v1.1.32  (2014-02-02)
* * *
Support for refund invoice and transaction.

### v1.1.31  (2014-01-26)
* * *
* Support for creating plans & addons on the fly via API.

* Support for handling double data type params handled. Eg., download_penality param for create new plan.

### v1.1.30  (2014-01-18)
* * *
Support addedBy attribute as input while creating comment. This can be used to pass information about who added the comment. If not provided, the value will be name of the api key.

### v1.1.29  (2014-01-16)
* * *
* Adding object that represent comments resource. Now comments can be added to the entities - Subscription, Invoice, Transaction, Plan, Addon & Coupon.

* API to fetch multiple subscriptions of a customer.

* Added support to get the list of events filtered by event type. Events can be fetched based on the event type eg., payment_succeeded.

