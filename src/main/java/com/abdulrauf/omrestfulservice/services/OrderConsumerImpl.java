package com.abdulrauf.omrestfulservice.services;

import com.abdulrauf.omrestfulservice.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class OrderConsumerImpl implements OrderConsumer {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final ExecutorService executor;

    private LinkedBlockingQueue<Order> sharedQueue;

    @Autowired
    public OrderConsumerImpl(LinkedBlockingQueue<Order> sharedQueue) {
        this.sharedQueue = sharedQueue;
        executor = Executors.newCachedThreadPool();
    }

    @Override
    public void Subscribe(OrderListener orderListener) {
        LOGGER.info("Subscribing for an order watch...");

        executor.submit(() -> {
            try {
                while(true) {
                    LOGGER.info("Waiting for an order inside a thread: ");
                    Order order = sharedQueue.take();
                    LOGGER.info("Received an order inside a thread: " + order);

                    orderListener.onProcess(order);

                    LOGGER.info("Processed the order inside a thread: " + order);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                LOGGER.error("An error occcured while consuming an order inside a thread.", e);
            }
        });

        LOGGER.info("Finished subscribing for an order watch...");
    }
}
