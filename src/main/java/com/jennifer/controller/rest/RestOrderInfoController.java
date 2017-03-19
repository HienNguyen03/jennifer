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

import java.math.BigDecimal;
import java.util.*;

/**
 * Rest controller for OrderInfo activities
 */
@RestController
@RequestMapping("/api/order")
@SessionAttributes({"favoriteBag", "shoppingBag"})
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

    @PostMapping
    public Object insert(Model model) {
        try {
            UserInfo userInfo = userInfoService.findById(AppUtil.getCustomerFromSession().getId());
            BigDecimal totalPrice = new BigDecimal(0);
            OrderInfo orderInfo = new OrderInfo();

            List<ShoppingProduct> shoppingProductList = shoppingProductService.findAllByUserId(userInfo.getId());
            List<OrderDetail> orderDetailList = new ArrayList<>();

            for (ShoppingProduct shoppingProduct : shoppingProductList) {
                Float productPrice = shoppingProduct.getProductInfo().getUnitPrice().floatValue() * shoppingProduct.getQuantity() * (100 - (float) shoppingProduct.getProductInfo().getDiscount()) / 100;
                totalPrice = totalPrice.add(new BigDecimal(productPrice));
                orderDetailList.add(new OrderDetail(orderInfo, shoppingProduct.getProductInfo(), shoppingProduct.getProductInfo().getUnitPrice(), shoppingProduct.getQuantity(), shoppingProduct.getProductInfo().getDiscount()));
            }

            orderInfo.setOrderDate(new Date());
            orderInfo.setTotalPrice(totalPrice);
            orderInfo.setUserInfo(userInfo);
            orderInfo.setStatus("Pending");

            orderInfo.setOrderDetails(orderDetailList);
            orderInfoService.update(orderInfo);

            int deleteLoopSize = shoppingProductList.size();
            for (int i = 0; i < deleteLoopSize; i++)
                shoppingProductService.delete(shoppingProductList.get(i));

            List<ShoppingProduct> shoppingProducts = shoppingProductService.findAllByUserId(userInfo.getId());

            Map<ProductInfo,Integer> userShoppingBag = new HashMap<>();
            for(ShoppingProduct shoppingProduct : shoppingProducts){
                userShoppingBag.put(shoppingProduct.getProductInfo(), shoppingProduct.getQuantity());
            }

            model.addAttribute("shoppingBag", userShoppingBag);

            return shoppingProducts;
        } catch(Exception e) {
            return new ResponseEntity<>("Unable to make order!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/new")
    public Object getNewOrder() {
        return orderInfoService.getNewOrder();
    }
}
