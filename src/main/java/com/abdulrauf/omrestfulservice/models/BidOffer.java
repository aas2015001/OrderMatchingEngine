package com.abdulrauf.omrestfulservice.models;

import java.time.Duration;

public class BidOffer extends Order {
    public BidOffer() {
        super(OrderType.BUY);
    }

    public BidOffer(long qty, double prc) {
        super(qty, prc, OrderType.BUY);
    }
}
