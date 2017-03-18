package com.jennifer.dao;

import com.jennifer.entity.CategoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CategoryInfo data access object interface
 */
@Repository
public interface CategoryInfoDao extends JpaRepository<CategoryInfo, Integer> {

    CategoryInfo findById(int id);

    @Query("from CategoryInfo c order by c.superCategoryInfo.id, c.placeOrder")
    List<CategoryInfo> findAll();

}
