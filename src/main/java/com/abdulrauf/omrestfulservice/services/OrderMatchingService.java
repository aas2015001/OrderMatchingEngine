package com.abdulrauf.omrestfulservice.services;

import com.abdulrauf.omrestfulservice.models.AskOffer;
import com.abdulrauf.omrestfulservice.models.BidOffer;
import com.abdulrauf.omrestfulservice.models.Book;
import com.abdulrauf.omrestfulservice.models.Order;

public interface OrderMatchingService {
    void putBidOffer(BidOffer bidOffer);
    void putAskOffer(AskOffer askOffer);
    Book getBook();
    void matchOrder(Order order);
}
