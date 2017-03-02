package com.jennifer.controller.rest;

import com.jennifer.entity.OrderDetail;
import com.jennifer.entity.OrderInfo;
import com.jennifer.service.OrderDetailService;
import com.jennifer.service.OrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for OrderDetail activities
 */
@RestController
@RequestMapping("/api/order-detail")
public class RestOrderDetailController {
    private static final Logger log = LoggerFactory.getLogger(RestOrderDetailController.class);
    private OrderDetailService orderDetailService;
    OrderInfoService orderInfoService;

    @Autowired
    public RestOrderDetailController(OrderDetailService orderDetailService, OrderInfoService orderInfoService){
        this.orderDetailService = orderDetailService;
        this.orderInfoService = orderInfoService;
    }

    @GetMapping("/{orderId}")
    public Object findOrderDetail(@PathVariable("orderId") Integer id){
        OrderInfo orderInfo = orderInfoService.findById(id);

        if (orderInfo!=null)
            return orderDetailService.findByOrder(orderInfo);

        return null;
    }
}
