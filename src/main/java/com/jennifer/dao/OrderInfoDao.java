package com.jennifer.dao;

import com.jennifer.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * OrderInfo data access object interface
 */
public interface OrderInfoDao extends JpaRepository<OrderInfo, Integer> {
    List<OrderInfo> findAll();

    OrderInfo findById(int id);
}
