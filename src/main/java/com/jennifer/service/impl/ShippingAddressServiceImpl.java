package com.jennifer.service.impl;

import com.jennifer.controller.rest.RestShippingAddressController;

import com.jennifer.dao.ShippingAddressDao;
import com.jennifer.entity.ShippingAddress;
import com.jennifer.entity.UserInfo;
import com.jennifer.service.ShippingAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * ShippingAddressService implementation
 */
@Service("shippingAddressService")
public class ShippingAddressServiceImpl implements ShippingAddressService{
    private static final Logger log = LoggerFactory.getLogger(ShippingAddressServiceImpl.class);
    private ShippingAddressDao shippingAddressDao;

    @Autowired
    public ShippingAddressServiceImpl(ShippingAddressDao shippingAddressDao){
        this.shippingAddressDao = shippingAddressDao;
    }

    @Override
    public List<ShippingAddress> findAllShippingAddresses() {
        return shippingAddressDao.findAll();
    }

    @Override
    public List<ShippingAddress> findByUser(UserInfo userInfo) {
        return shippingAddressDao.findByUserInfo(userInfo.getId());
    }
}

