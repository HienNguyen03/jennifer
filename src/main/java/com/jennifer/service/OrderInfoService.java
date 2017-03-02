package com.jennifer.service;

import com.jennifer.entity.OrderInfo;
import java.util.List;

/**
 * Providing service for OrderInfo
 */
public interface OrderInfoService {

    List<OrderInfo> findAllOrders();
    OrderInfo findById(int id);
    OrderInfo update(OrderInfo orderInfo);
}
