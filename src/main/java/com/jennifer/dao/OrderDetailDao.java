package com.jennifer.dao;

import com.jennifer.entity.OrderDetail;
import com.jennifer.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * OrderDetail data access object interface
 */
@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetail,Long> {
    List<OrderDetail> findAll();

    @Query(value = "SELECT od from OrderDetail od where od.primaryKey.orderInfo.id = ?1")
    List<OrderDetail> findOrderDetails(int id);
}
