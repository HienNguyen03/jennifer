package com.jennifer.dao;

import com.jennifer.entity.ShoppingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ShoppingProduct data access object interface
 */

@Repository
public interface ShoppingProductDao extends JpaRepository<ShoppingProduct, Integer> {

    List<ShoppingProduct> findAll();

    @Query("from ShoppingProduct fp where fp.primaryKey.userInfo.id = ?1 order by fp.primaryKey.productInfo.id desc")
    List<ShoppingProduct> findAllByUserId(int userId);

    @Query("from ShoppingProduct fp where fp.primaryKey.userInfo.id = ?1 and fp.primaryKey.productInfo.id = ?2")
    ShoppingProduct findByUserIdAndProductId(int userId, int productId);

}
