package com.jennifer.dao;

import com.jennifer.entity.DeliveryMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * DeliveryMethod data access object interface
 */
public interface DeliveryMethodDao extends JpaRepository<DeliveryMethod, Integer> {

    DeliveryMethod findById(int id);

    List<DeliveryMethod> findAll();

}