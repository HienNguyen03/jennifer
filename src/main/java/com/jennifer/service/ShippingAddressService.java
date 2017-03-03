package com.jennifer.service;

import com.jennifer.entity.ShippingAddress;
import com.jennifer.entity.UserInfo;

import java.util.List;

/**
 * Providing service for ShippingAddress
 */
public interface ShippingAddressService {
    List<ShippingAddress> findAllShippingAddresses();
    List<ShippingAddress> findByUser(UserInfo userInfo);
}
