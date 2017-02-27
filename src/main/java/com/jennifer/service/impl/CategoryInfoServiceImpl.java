package com.jennifer.service.impl;

import com.jennifer.dao.CategoryInfoDao;
import com.jennifer.entity.CategoryInfo;
import com.jennifer.service.CategoryInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategoryInfo service implementation
 */

@Service("categoryInfoService")
public class CategoryInfoServiceImpl implements CategoryInfoService {

    private static final Logger log = LoggerFactory.getLogger(CategoryInfoServiceImpl.class);
    private CategoryInfoDao categoryInfoDao;

    @Autowired
    public CategoryInfoServiceImpl(CategoryInfoDao categoryInfoDao) {
        this.categoryInfoDao = categoryInfoDao;
    }

    @Override
    public List<CategoryInfo> findAllCategories() {
        return categoryInfoDao.findAll();
    }

    @Override
    public CategoryInfo findCategory(int id) {
        return categoryInfoDao.findOne(id);
    }
}
