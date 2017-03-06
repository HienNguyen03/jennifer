package com.jennifer.controller.rest;


import com.jennifer.entity.OrderDetail;
import com.jennifer.entity.OrderInfo;
import com.jennifer.service.OrderDetailService;
import com.jennifer.service.OrderInfoService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Object findAll() {
        return orderInfoService.findAllOrders();
    }

    @PutMapping
    public Object update(@RequestBody OrderInfo orderInfo){
        OrderInfo orderInfoFound = orderInfoService.findById(orderInfo.getId());

        if(orderInfoFound != null){
            OrderInfo orderInfoUpdated = orderInfoService.update(orderInfo);
            return orderInfoUpdated;
        }

        return new ResponseEntity("Unable to update!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/new")
    public Object getNewOrder(){
        return orderInfoService.getNewOrder();
    }
}
