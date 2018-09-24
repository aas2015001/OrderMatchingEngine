package com.abdulrauf.omrestfulservice.Config;

import com.abdulrauf.omrestfulservice.models.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class AppConfig {
    @Bean(name = "sharedQueue")
    public LinkedBlockingQueue<Order> sharedQueue() {
        return new LinkedBlockingQueue<>();
    }
}
