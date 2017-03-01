package com.jennifer.service;

import com.jennifer.entity.OrderDetail;
import com.jennifer.entity.OrderInfo;

import java.util.List;

/**
 * Providing service for OrderDetail
 */
public interface OrderDetailService {

    List<OrderDetail> findAllOrderDetails();
    List<OrderDetail> findByOrder(OrderInfo orderInfo);
}
