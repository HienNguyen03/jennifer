package com.jennifer.controller.rest;


import com.jennifer.entity.OrderInfo;
import com.jennifer.service.OrderInfoService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Rest controller for OrderInfo activities
 */
@RestController
@RequestMapping("/api/order")
public class RestOrderInfoController {
    private static final Logger log = LoggerFactory.getLogger(RestDeliveryMethodController.class);
    private OrderInfoService orderInfoService;

    @Autowired
    public RestOrderInfoController(OrderInfoService orderInfoService) {
        this.orderInfoService = orderInfoService;
    }

    @GetMapping
    public List<OrderInfo> findAll() {
        return orderInfoService.findAllOrders();
    }


}
