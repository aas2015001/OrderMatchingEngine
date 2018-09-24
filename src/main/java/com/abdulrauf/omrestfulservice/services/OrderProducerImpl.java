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
public class OrderProducerImpl implements OrderProducer {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final ExecutorService executor;

    private LinkedBlockingQueue<Order> sharedQueue;

    @Autowired
    public OrderProducerImpl(LinkedBlockingQueue<Order> sharedQueue) {
        this.sharedQueue = sharedQueue;
        executor = Executors.newCachedThreadPool();
    }

    @Override
    public void publish(Order order) {
        LOGGER.info("Publishing an order : " + order);

        executor.submit(() -> {
            try {
                LOGGER.info("Publishing an order inside an thread: " + order);
                sharedQueue.put(order);
                LOGGER.info("Finished publishing an order inside an thread: " + order);
            } catch (InterruptedException e) {
                e.printStackTrace();
                LOGGER.error("An error occcured while publishing an order inside an thread.", e);
            }
        });

        LOGGER.info("Finished publishing an order : " + order);
    }
}
