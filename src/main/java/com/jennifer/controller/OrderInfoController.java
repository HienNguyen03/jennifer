package com.jennifer.controller;

import com.jennifer.entity.OrderDetail;
import com.jennifer.entity.OrderInfo;
import com.jennifer.entity.UserInfo;
import com.jennifer.service.OrderDetailService;
import com.jennifer.service.OrderInfoService;
import com.jennifer.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for OrderInfo activities
 */
@Controller
public class OrderInfoController {

    private static final Logger log = LoggerFactory.getLogger(OrderInfoController.class);
    private OrderInfoService orderInfoService;
    private OrderDetailService orderDetailService;

    @Autowired
    public OrderInfoController(OrderInfoService orderInfoService, OrderDetailService orderDetailService) {
        this.orderInfoService = orderInfoService;
        this.orderDetailService = orderDetailService;
    }

    @RequestMapping("/manager/order")
    public String getAllOrders() {
        return "manager/order";
    }

    @GetMapping("/order-history")
    public String history(Model model) throws ParseException {
        log.info(" > HISTORY - GET");
        UserInfo userInfo = AppUtil.getCustomerFromSession();
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        if (userInfo!= null){
            Map<OrderInfo,List<OrderDetail> > orderHistory = new HashMap<OrderInfo, List<OrderDetail> >();
            List<OrderInfo> orderInfos = orderInfoService.findByUserInfo(userInfo);

            for (OrderInfo o: orderInfos){
                List<OrderDetail> orderDetails = orderDetailService.findByOrder(o);
                orderHistory.put(o, orderDetails);
            }
            model.addAttribute("orderHistory", orderHistory);
        }
        return "order-history";
    }

}
