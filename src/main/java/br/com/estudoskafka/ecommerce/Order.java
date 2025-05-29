package br.com.estudoskafka.ecommerce;

import java.math.BigDecimal;

public class Order {
    private final String userID, order;
    private final BigDecimal value;

    public Order(String userID, String order, BigDecimal value) {
        this.userID = userID;
        this.order = order;
        this.value = value;
    }
}
