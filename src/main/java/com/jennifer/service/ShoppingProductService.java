package com.jennifer.service;

import com.jennifer.entity.ShoppingProduct;

import java.util.List;

/**
 * Provided services for ShoppingProduct
 */

public interface ShoppingProductService {

    List<ShoppingProduct> findAll();
    ShoppingProduct update(ShoppingProduct product);
    void delete(ShoppingProduct product);
    ShoppingProduct add(ShoppingProduct product);
    List<ShoppingProduct> findAllByUserId(int userId);
    ShoppingProduct findByUserIdAndProductId(int userId, int productId);

}
