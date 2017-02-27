package com.jennifer.controller.rest;


import com.jennifer.entity.OrderInfo;
import com.jennifer.service.OrderInfoService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public OrderInfo update(@RequestBody OrderInfo orderInfo){
        OrderInfo orderInfoFound = orderInfoService.findById(orderInfo.getId());

        if(orderInfoFound != null){
            OrderInfo orderInfoUpdated = orderInfoService.update(orderInfo);
            return orderInfoUpdated;
        }
        return null;
    }
}
