package br.com.estudoskafka.ecommerce;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (var dispatcher = new KafkaDispatcher<Order>()) {
            for (var i = 0; i < 10; i++) {

                var userID = UUID.randomUUID().toString();
                var orderID  = UUID.randomUUID().toString();
                var amount = new BigDecimal(Math.random() * 5000 + 1);
                dispatcher.send("ECOMMERCE_NEW_ORDER", userID, orderID);

                var email = "Thank you for your order! We are processing your order!";
                dispatcher.send("ECOMMERCE_SEND_EMAIL", userID, email);
            }
        }
    }
}
