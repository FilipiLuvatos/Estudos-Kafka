package br.com.estudoskafka.ecommerce;

import java.math.BigDecimal;

public class Order {

    private final String userId, orderId;
    private final BigDecimal amount;

    public Order(String userId, String orderId, BigDecimal amount) {
        this.userId = userId;
        this.orderId = orderId;
        this.amount = amount;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public String getUserId(){
        return userId;
    }

    public String getEmail(){
        return "email";
    }
}
