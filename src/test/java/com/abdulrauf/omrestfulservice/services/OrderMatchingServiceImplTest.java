package com.abdulrauf.omrestfulservice.services;

import com.abdulrauf.omrestfulservice.models.AskOffer;
import com.abdulrauf.omrestfulservice.models.BidOffer;
import com.abdulrauf.omrestfulservice.models.Book;
import com.abdulrauf.omrestfulservice.models.Order;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.*;

public class OrderMatchingServiceImplTest {
    private OrderMatchingService orderMatchingService;
    private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    @Before
    public void setup() {
        orderMatchingService = new OrderMatchingServiceImpl(new OrderProducerImpl(queue), new OrderConsumerImpl(queue));
    }

    @Test
    public void testOrderMatchingLogic() {
        orderMatchingService.putAskOffer(new AskOffer(10, 15));
        orderMatchingService.putAskOffer(new AskOffer(10, 13));
        orderMatchingService.putBidOffer(new BidOffer(10, 7));
        orderMatchingService.putBidOffer(new BidOffer(10, 9.5));

        Book book = orderMatchingService.getBook();
        System.out.println("buys: " + Arrays.toString(book.getBuys().toArray()));
        System.out.println("sells: " + Arrays.toString(book.getSells().toArray()));

        orderMatchingService.putAskOffer(new AskOffer(5, 9.5));
        book = orderMatchingService.getBook();
        System.out.println();
        System.out.println("buys: " + Arrays.toString(book.getBuys().toArray()));
        System.out.println("sells: " + Arrays.toString(book.getSells().toArray()));

        orderMatchingService.putBidOffer(new BidOffer(6, 13));
        book = orderMatchingService.getBook();
        System.out.println();
        System.out.println("buys: " + Arrays.toString(book.getBuys().toArray()));
        System.out.println("sells: " + Arrays.toString(book.getSells().toArray()));

        orderMatchingService.putAskOffer(new AskOffer(7, 7));
        book = orderMatchingService.getBook();
        System.out.println();
        System.out.println("buys: " + Arrays.toString(book.getBuys().toArray()));
        System.out.println("sells: " + Arrays.toString(book.getSells().toArray()));

        orderMatchingService.putAskOffer(new AskOffer(12, 6));

        waitTillAsyncThreadFinishes();

        book = orderMatchingService.getBook();
        System.out.println();
        System.out.println("buys: " + Arrays.toString(book.getBuys().toArray()));
        System.out.println("sells: " + Arrays.toString(book.getSells().toArray()));
    }

    private void waitTillAsyncThreadFinishes() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}