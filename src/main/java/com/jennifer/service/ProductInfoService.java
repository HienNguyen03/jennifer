package com.jennifer.service;

import com.jennifer.entity.CategoryInfo;
import com.jennifer.entity.ProductInfo;

import java.util.List;

/**
 * Provided services for ProductInfo
 */

public interface ProductInfoService {

    List<ProductInfo> findAllProducts();
    ProductInfo findProduct(int id);
    ProductInfo updateProduct(ProductInfo productInfo);
    void deleteProduct(ProductInfo productInfo);
    ProductInfo addProduct(ProductInfo productInfo);
    List<ProductInfo> findProductsByCategory(CategoryInfo categoryInfo);
    List<ProductInfo> getLatestProducts();

}
