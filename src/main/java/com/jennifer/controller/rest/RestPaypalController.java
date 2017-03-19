package com.jennifer.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Controller only serve for Paypal calls
 */

@RestController
@RequestMapping("/api/paypal")
public class RestPaypalController {

    private static final Logger log = LoggerFactory.getLogger(RestShippingAddressController.class);

    private RestTemplate restTemplate;

    @Autowired
    public RestPaypalController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @GetMapping("/create-payment")
    public void createPayment(){

    }

}
