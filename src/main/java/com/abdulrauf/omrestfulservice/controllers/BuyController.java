package com.abdulrauf.omrestfulservice.controllers;

import com.abdulrauf.omrestfulservice.models.BidOffer;
import com.abdulrauf.omrestfulservice.services.OrderMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyController {
    @Autowired
    private OrderMatchingService orderMatchingService;

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ResponseEntity buy(@RequestBody BidOffer bidOffer) {
        orderMatchingService.putBidOffer(bidOffer);

        return new ResponseEntity(HttpStatus.OK);
    }
}
