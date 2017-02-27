package com.jennifer.dao;

import com.jennifer.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 */
public interface ShippingAddressDao extends JpaRepository<ShippingAddress, Integer>{

    ShippingAddress findById(int id);
    List<ShippingAddress> findAll();
}
