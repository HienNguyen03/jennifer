package com.jennifer.dao;

import com.jennifer.entity.OrderInfo;
import com.jennifer.entity.UserInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderInfo data access object interface
 */
@Repository
public interface OrderInfoDao extends JpaRepository<OrderInfo, Integer> {
    List<OrderInfo> findAll();
    List<OrderInfo> findAllByOrderByIdDesc();
    List<OrderInfo> findByUserInfoOrderByIdDesc(UserInfo userInfo);
    List<OrderInfo> findByUserInfo(UserInfo userInfo);
    OrderInfo findById(int id);

    @Query("from OrderInfo o where o.status = ?1")
    List<OrderInfo> findByOrderStatus(String status);

    List<OrderInfo> findByUserInfo_IdOrderByIdDesc(int userId, Pageable pageable);
    default List<OrderInfo> findLatestOrder(int userId) {
        return findByUserInfo_IdOrderByIdDesc(userId, new PageRequest(0,1));
    }
}
