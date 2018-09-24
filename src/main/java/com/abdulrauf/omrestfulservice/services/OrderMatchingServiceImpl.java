package com.abdulrauf.omrestfulservice.services;

import com.abdulrauf.omrestfulservice.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

@Service
public class OrderMatchingServiceImpl implements OrderMatchingService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final ConcurrentNavigableMap<Double, BidOffer> bidOffers;
    private final ConcurrentNavigableMap<Double, AskOffer> askOffers;
    private final OrderConsumer orderConsumer;
    private final OrderProducer orderProducer;

    // first-in-first-out
    @Autowired
    public OrderMatchingServiceImpl(OrderProducer orderProducer, OrderConsumer orderConsumer) {
        this.bidOffers = new ConcurrentSkipListMap<>(Comparator.reverseOrder());
        this.askOffers = new ConcurrentSkipListMap<>(Double::compareTo);

        this.orderProducer = orderProducer;
        this.orderConsumer = orderConsumer;

        this.orderConsumer.Subscribe(order -> {
            LOGGER.info("Order matching process started for an order " + order);

            matchOrder(order);

            if(order.getQty() > 0) {
                if(order.getType().equals(OrderType.BUY)) {
                    bidOffers.put(order.getPrc(), (BidOffer) order);
                } else {
                    askOffers.put(order.getPrc(), (AskOffer) order);
                }
            }

            LOGGER.info("Finished order matching process started for an order " + order);
        });
    }

    @Override
    public void putBidOffer(BidOffer bidOffer) {
        orderProducer.publish(bidOffer);
    }

    @Override
    public void putAskOffer(AskOffer askOffer) {
        orderProducer.publish(askOffer);
    }

    @Override
    public Book getBook() {
        Book book = new Book(bidOffers.values(), askOffers.values());
        return book;
    }

    @Override
    public void matchOrder(Order order) {
        switch (order.getType())
        {
            case BUY:
                matchBuyOrder(order);
                break;
            case SELL:
                matchSellOrder(order);
                break;
            default:
                LOGGER.error ("Offer not supported!");
        }
    }

    private void matchBuyOrder(Order buyOrder) {
        Iterator iterator = askOffers.keySet().iterator();
        while(iterator.hasNext()) {
            Double askPrice = (Double) iterator.next();
            AskOffer askOffer = askOffers.get(askPrice);
            if(buyOrder.getQty() > 0 && buyOrder.getPrc() >= askOffer.getPrc()) {
                askOffers.remove(askPrice);
                if(buyOrder.getQty() > askOffer.getQty()) {
                    buyOrder.setQty(buyOrder.getQty() - askOffer.getQty());
                } else {
                    askOffer.setQty(askOffer.getQty() - buyOrder.getQty());
                    buyOrder.setQty(0);
                    askOffers.put(askPrice, askOffer);
                }
            }
        }
    }

    private void matchSellOrder(Order sellOrder) {
        Iterator iterator = bidOffers.keySet().iterator();
        while(iterator.hasNext()) {
            Double bidPrice = (Double) iterator.next();
            BidOffer bidOffer = bidOffers.get(bidPrice);
            if(sellOrder.getQty() > 0 && sellOrder.getPrc() <= bidOffer.getPrc()) {
                bidOffers.remove(bidPrice);
                if(sellOrder.getQty() > bidOffer.getQty()) {
                    sellOrder.setQty(sellOrder.getQty() - bidOffer.getQty());
                } else {
                    bidOffer.setQty(bidOffer.getQty() - sellOrder.getQty());
                    sellOrder.setQty(0);
                    bidOffers.put(bidPrice, bidOffer);
                }
            }
        }
    }
}
