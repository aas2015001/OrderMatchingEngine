package com.abdulrauf.omrestfulservice.models;

import java.time.Duration;

public class AskOffer extends Order {
    public AskOffer() {
        super(OrderType.SELL);
    }

    public AskOffer(long qty, double prc) {
        super(qty, prc, OrderType.SELL);
    }
}
