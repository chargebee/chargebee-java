package com.chargebee.v4;

import com.chargebee.v4.client.ChargebeeClient;
import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.models.personalizedOffer.params.PersonalizedOffersParams;
import com.chargebee.v4.models.transaction.params.TransactionListParams;
import com.chargebee.v4.transport.ConsoleRequestLogger;

public class Sample {

    public static void main(String[] args) throws ChargebeeException {
        try {
            ChargebeeClient client = ChargebeeClient.builder("test_EHMf72DCN9ZSFnYJSnabYYWcuARgbgmX7", "alisheu-test")
                    .requestLogger(new ConsoleRequestLogger(ConsoleRequestLogger.LogLevel.CURL, true))
                    .build();


//            TransactionListParams params = TransactionListParams.builder()
//                    .status()
//                    .in(TransactionListParams.Status.SUCCESS)
//                    .type().in(TransactionListParams.Type.PAYMENT)
//                    .limit(10)
//                    .build();

            PersonalizedOffersParams params = PersonalizedOffersParams.builder()
                    .customerId("test_customer_id")
                    .build();

            client.personalizedOffers().personalizedOffers(params);

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }


}
