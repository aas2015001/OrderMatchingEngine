package com.abdulrauf.omrestfulservice.services;

import com.abdulrauf.omrestfulservice.models.Order;

public interface OrderListener {
    void onProcess(Order order);
}
