package com.jennifer.dao;

import com.jennifer.entity.ShippingAddress;
import com.jennifer.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 */
public interface ShippingAddressDao extends JpaRepository<ShippingAddress, Integer>{

    ShippingAddress findById(int id);
    List<ShippingAddress> findAll();

    @Query("from ShippingAddress sa where sa.userInfo.id = ?1")
    List<ShippingAddress> findByUserInfo(int id);

}
