package com.jennifer.service.impl;

import com.jennifer.dao.FavoriteProductDao;
import com.jennifer.dao.ShoppingProductDao;
import com.jennifer.entity.FavoriteProduct;
import com.jennifer.entity.ShoppingProduct;
import com.jennifer.service.FavoriteProductService;
import com.jennifer.service.ShoppingProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ShoppingProduct service implementation
 */

@Service("shoppingProductService")
public class ShoppingProductServiceImpl implements ShoppingProductService {

    private static final Logger log = LoggerFactory.getLogger(ShoppingProductServiceImpl.class);

    private ShoppingProductDao shoppingProductDao;

    @Autowired
    public ShoppingProductServiceImpl(ShoppingProductDao shoppingProductDao) {
        this.shoppingProductDao = shoppingProductDao;
    }

    @Override
    public List<ShoppingProduct> findAll() {
        return shoppingProductDao.findAll();
    }

    @Override
    public ShoppingProduct update(ShoppingProduct shoppingProduct) {
        return shoppingProductDao.save(shoppingProduct);
    }

    @Override
    public void delete(ShoppingProduct shoppingProduct) {
        shoppingProductDao.delete(shoppingProduct);
    }

    @Override
    public ShoppingProduct add(ShoppingProduct shoppingProduct) {
        return shoppingProductDao.save(shoppingProduct);
    }

    @Override
    public List<ShoppingProduct> findAllByUserId(int userId) {
        return shoppingProductDao.findAllByUserId(userId);
    }

    @Override
    public ShoppingProduct findByUserIdAndProductId(int userId, int productId) {
        return shoppingProductDao.findByUserIdAndProductId(userId, productId);
    }
}
