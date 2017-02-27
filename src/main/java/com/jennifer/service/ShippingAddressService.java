package com.jennifer.service;

import com.jennifer.entity.ShippingAddress;

import java.util.List;

/**
 * Providing service for ShippingAddress
 */
public interface ShippingAddressService {
    List<ShippingAddress> findAllShippingAddresses();
}
