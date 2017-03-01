package com.jennifer.service.impl;

import com.jennifer.dao.OrderDetailDao;
import com.jennifer.dao.OrderInfoDao;
import com.jennifer.entity.OrderDetail;
import com.jennifer.entity.OrderInfo;
import com.jennifer.service.OrderDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * OrderDetailService implementation
 */
@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {

    private static final Logger log = LoggerFactory.getLogger(OrderDetailServiceImpl.class);
    private OrderDetailDao orderDetailDao;
    private OrderInfoDao orderInfoDao;
    @Autowired
    public OrderDetailServiceImpl(OrderDetailDao orderDetailDao, OrderInfoDao orderInfoDao){
        this.orderDetailDao = orderDetailDao;
        this.orderInfoDao = orderInfoDao;
    }

    @Override
    public List<OrderDetail> findAllOrderDetails() {
        return orderDetailDao.findAll();
    }

    @Override
    public List<OrderDetail> findByOrder(OrderInfo orderInfo) {

        return orderDetailDao.findOrderDetails(orderInfo.getId());
    }
}
