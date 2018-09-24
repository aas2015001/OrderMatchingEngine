package com.abdulrauf.omrestfulservice.controllers;

import com.abdulrauf.omrestfulservice.models.Book;
import com.abdulrauf.omrestfulservice.services.OrderMatchingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderMatchingService orderMatchingService;

    @RequestMapping("/book")
    public Book getBook() {
        LOGGER.info("processing book request!");
        Book book = orderMatchingService.getBook();
        return book;
    }
}
