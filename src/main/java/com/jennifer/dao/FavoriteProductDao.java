package com.jennifer.dao;

import com.jennifer.entity.FavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * FavoriteProduct data access object interface
 */

public interface FavoriteProductDao extends JpaRepository<FavoriteProduct, Integer> {

    List<FavoriteProduct> findAll();

    @Query("from FavoriteProduct fp where fp.primaryKey.userInfo.id = ?1 order by fp.primaryKey.productInfo.id desc")
    List<FavoriteProduct> findAllByUserId(int userId);

    @Query("from FavoriteProduct fp where fp.primaryKey.userInfo.id = ?1 and fp.primaryKey.productInfo.id = ?2")
    FavoriteProduct findByUserIdAndProductId(int userId, int productId);

}
