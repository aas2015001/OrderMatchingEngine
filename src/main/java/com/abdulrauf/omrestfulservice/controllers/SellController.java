package com.abdulrauf.omrestfulservice.controllers;

import com.abdulrauf.omrestfulservice.models.AskOffer;
import com.abdulrauf.omrestfulservice.services.OrderMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellController {
    @Autowired
    private OrderMatchingService orderMatchingService;

    @RequestMapping(value = "/sell", method = RequestMethod.POST)
    public ResponseEntity sell(@RequestBody AskOffer askOffer) {
        orderMatchingService.putAskOffer(askOffer);

        return new ResponseEntity(HttpStatus.OK);
    }
}
