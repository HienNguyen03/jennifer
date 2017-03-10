package com.jennifer.service;

import com.jennifer.entity.OrderInfo;
import com.jennifer.entity.UserInfo;

import java.util.List;

/**
 * Providing service for OrderInfo
 */
public interface OrderInfoService {

    List<OrderInfo> findAllOrders();
    OrderInfo findById(int id);
    OrderInfo update(OrderInfo orderInfo);
    int getNewOrder();
    List<OrderInfo> findByUserInfo(UserInfo user);

}
