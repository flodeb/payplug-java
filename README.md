# Installation with maven

Add jitpack repository :

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add in your `pom.xml` dependencies :

```xml
<dependency>
    <groupId>com.github.flodeb</groupId>
    <artifactId>payplug-java</artifactId>
    <version>0.2.0</version>
</dependency>
```

# How to ?

## Prerequisites

See PayPlug API documentation for more information about the payment workflow : https://docs.payplug.com/api/

Instanciate a `PayplugConfiguration` object with your PayPlug secret key (used to communicate with PayPlug API. It can be obtained from your PayPlug portal. At this moment, it begins by `sk_test_` for sandbox environment or `sk_live_` for production).

```java
PayplugConfiguration payPlugConf = new PayplugConfiguration("sk_test_AAAAAAAAAAA");
```
## Create a payment

Instanciate a `PaymentService` (at this moment, it uses a `RestTemplate` from Spring Framework)

```java
HttpClient httpClient = new HttpClient(new RestTemplate());
PaymentService paymentService = new PaymentService(httpClient);
```

Create a payment with payment and customer information (check classes for more attributes) :

```java
Customer customer = new Customer();
customer.setFirstName("John");
customer.setLastName("Doe");
customer.setEmail("john.doe@example.com");

// someChecksumToVerify may be your internal payment ID, or a built checksum that you will verify in your IPN
HostedPayment hostedPayment = new HostedPayment();
hostedPayment.setCancelUrl("https://example.com/payplug/cancel/[someChecksumToVerify]"); // Where your customer is redirected when cancellation of payment
hostedPayment.setReturnUrl("https://example.com/payplug/confirm/[someChecksumToVerify]"); // Where your customer is redirect when confirmation of payment

Payment payment = new Payment();
payment.setAmount(1500); // Amount in CENTS
payment.setCurrency(EUR);
payment.setCustomer(customer);
payment.setHostedPayment(hostedPayment);
payment.setNotificationUrl("https://yourwebsite.com/payplug/notify/[someChecksumToVerify]"); // PayPlug hook to notify about payment (success, failure)

paymentService.create(payment, payPlugConf);
```

## IPN: Receive notification of Payment (via notificationUrl)

PayPlug calls your notification url Hook. You can treat it with `NotificationService`  :

// content is a the request content sent by PayPlug to your hook
Resource resource = notificationService.treat(content, payPlugConfig);

Then, you can treat the resource (check if payment is Paid, ... etc), depending of what it is :

```java
if (resource instanceof Payment) {
    Payment payment = (Payment) resource;
    // Treat payment (you can check payment.getIsPaid())

} else if (resource instanceof Refund) {
    Refund refund = (Refund) resource;
    // Treat refund
}
```




