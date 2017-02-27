package com.jennifer.controller;

import com.jennifer.service.OrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for OrderInfo activities
 */
@Controller
public class OrderInfoController {

    private static final Logger log = LoggerFactory.getLogger(OrderInfoController.class);
    private OrderInfoService orderInfoService;

    @Autowired
    public OrderInfoController(OrderInfoService orderInfoService) {
        this.orderInfoService = orderInfoService;
    }

    @RequestMapping("/manager/order")
    public String mListOfOrderInfo(Model model) {
        return "manager/order";
    }

}
