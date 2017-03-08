package com.jennifer.service.impl;

import com.jennifer.dao.FavoriteProductDao;
import com.jennifer.entity.FavoriteProduct;
import com.jennifer.service.FavoriteProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FavoriteProduct service implementation
 */

@Service("favoriteProductService")
public class FavoriteProductServiceImpl implements FavoriteProductService {

    private static final Logger log = LoggerFactory.getLogger(FavoriteProductServiceImpl.class);

    private FavoriteProductDao favoriteProductDao;

    @Autowired
    public FavoriteProductServiceImpl(FavoriteProductDao favoriteProductDao) {
        this.favoriteProductDao = favoriteProductDao;
    }

    @Override
    public List<FavoriteProduct> findAll() {
        return favoriteProductDao.findAll();
    }

    @Override
    public FavoriteProduct update(FavoriteProduct favoriteProduct) {
        return favoriteProductDao.save(favoriteProduct);
    }

    @Override
    public void delete(FavoriteProduct favoriteProduct) {
        favoriteProductDao.delete(favoriteProduct);
    }

    @Override
    public FavoriteProduct add(FavoriteProduct favoriteProduct) {
        return favoriteProductDao.save(favoriteProduct);
    }

    @Override
    public List<FavoriteProduct> findAllByUserId(int userId) {
        return favoriteProductDao.findAllByUserId(userId);
    }

    @Override
    public FavoriteProduct findByUserIdAndProductId(int userId, int productId) {
        return favoriteProductDao.findByUserIdAndProductId(userId, productId);
    }
}
