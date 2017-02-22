package com.jennifer.service.impl;

import com.jennifer.dao.ProductInfoDao;
import com.jennifer.dao.UserInfoDao;
import com.jennifer.entity.ProductInfo;
import com.jennifer.service.ProductInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductInfo service implementation
 */

@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {

    private static final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    private ProductInfoDao productInfoDao;

    @Autowired
    public ProductInfoServiceImpl(ProductInfoDao productInfoDao) {
        this.productInfoDao = productInfoDao;
    }

    @Override
    public List<ProductInfo> findAllProducts() {
        return productInfoDao.findAll();
    }
}
