package com.jennifer.dao;

import com.jennifer.entity.CategoryInfo;
import com.jennifer.entity.ProductInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProductInfo data access object interface
 */

@Repository
public interface ProductInfoDao extends JpaRepository<ProductInfo, Integer> {

    ProductInfo findById(int id);
    List<ProductInfo> findAll();
    List<ProductInfo> findAllByOrderByIdDesc();

    //@Query("from ProductInfo p where p.categoryInfo.id = ?1")
    List<ProductInfo> findByCategoryInfo(CategoryInfo categoryInfo);

    List<ProductInfo> findByStatusLikeOrderByIdDesc(String status, Pageable pageable);
    default List<ProductInfo> findLatestProducts() {
        return findByStatusLikeOrderByIdDesc("available", new PageRequest(0,12));
    }

    @Query("from ProductInfo pi where pi.categoryInfo.id = ?1")
    List<ProductInfo> findSameCategoryProducts(int categoryId);

}
