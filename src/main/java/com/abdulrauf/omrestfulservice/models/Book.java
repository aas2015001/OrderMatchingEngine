package com.abdulrauf.omrestfulservice.models;

import java.util.Collection;

public class Book {
    private Collection<BidOffer> buys;
    private Collection<AskOffer> sells;

    public Book(Collection<BidOffer> buys, Collection<AskOffer> sells) {
        this.buys = buys;
        this.sells = sells;
    }

    public Collection<BidOffer> getBuys() {
        return buys;
    }

    public void setBuys(Collection<BidOffer> buys) {
        this.buys = buys;
    }

    public Collection<AskOffer> getSells() {
        return sells;
    }

    public void setSells(Collection<AskOffer> sells) {
        this.sells = sells;
    }
}
