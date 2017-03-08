package com.jennifer.service;

import com.jennifer.entity.FavoriteProduct;

import java.util.List;

/**
 * Provided services for FavoriteProduct
 */

public interface FavoriteProductService {

    List<FavoriteProduct> findAll();
    FavoriteProduct update(FavoriteProduct product);
    void delete(FavoriteProduct product);
    FavoriteProduct add(FavoriteProduct product);
    List<FavoriteProduct> findAllByUserId(int userId);
    FavoriteProduct findByUserIdAndProductId(int userId, int productId);

}
