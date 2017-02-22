package com.jennifer.dao;

import com.jennifer.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ProductInfo data access object interface
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo, Integer> {

    ProductInfo findById(int id);
    List<ProductInfo> findAll();

}
