package com.jennifer.dao;

import com.jennifer.entity.CategoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * CategoryInfo data access object interface
 */
public interface CategoryInfoDao extends JpaRepository<CategoryInfo, Integer> {

    List<CategoryInfo> findAll();

}
