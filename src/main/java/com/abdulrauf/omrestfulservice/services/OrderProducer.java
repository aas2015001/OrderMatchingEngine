package com.abdulrauf.omrestfulservice.services;

import com.abdulrauf.omrestfulservice.models.Order;

public interface OrderProducer {
    void publish(Order order);
}
