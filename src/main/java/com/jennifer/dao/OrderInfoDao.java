package com.jennifer.dao;

import com.jennifer.entity.OrderInfo;
import com.jennifer.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * OrderInfo data access object interface
 */
public interface OrderInfoDao extends JpaRepository<OrderInfo, Integer> {
    List<OrderInfo> findAll();

    OrderInfo findById(int id);
    @Query(value = "SELECT o from OrderInfo o where o.status = ?1")
    List<OrderInfo> findByOrderStatus(String status);

    List<OrderInfo> findByUserInfo(UserInfo userInfo);
}
