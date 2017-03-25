package com.jennifer.controller.rest;

import com.jennifer.entity.*;
import com.jennifer.service.OrderInfoService;
import com.jennifer.service.ShoppingProductService;
import com.jennifer.service.UserInfoService;
import com.jennifer.util.AppUtil;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Rest controller for OrderInfo activities
 */
@RestController
@RequestMapping("/api/order")
@SessionAttributes({"favoriteBag", "shoppingBag", "orderAmount"})
public class RestOrderInfoController {
    private static final Logger log = LoggerFactory.getLogger(RestDeliveryMethodController.class);
    private OrderInfoService orderInfoService;
    private UserInfoService userInfoService;
    private ShoppingProductService shoppingProductService;

    @Autowired
    public RestOrderInfoController(OrderInfoService orderInfoService, UserInfoService userInfoService, ShoppingProductService shoppingProductService) {
        this.orderInfoService = orderInfoService;
        this.userInfoService = userInfoService;
        this.shoppingProductService = shoppingProductService;
    }

    @GetMapping
    public Object findAll() {
        return orderInfoService.findAllOrders();
    }

    @PutMapping
    public Object update(@RequestBody OrderInfo orderInfo) {
        OrderInfo orderInfoFound = orderInfoService.findById(orderInfo.getId());

        if (orderInfoFound != null) {
            OrderInfo orderInfoUpdated = orderInfoService.update(orderInfo);
            return orderInfoUpdated;
        }

        return new ResponseEntity<>("Unable to update!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/new")
    public Object getNewOrder() {
        return orderInfoService.getNewOrder();
    }
}
