package com.jennifer.dao;

import com.jennifer.entity.CategoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * CategoryInfo data access object interface
 */
public interface CategoryInfoDao extends JpaRepository<CategoryInfo, Integer> {

    @Query("from CategoryInfo c order by c.superCategoryInfo.id, c.placeOrder")
    List<CategoryInfo> findAll();

}
