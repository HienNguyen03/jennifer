package com.jennifer.controller.rest;

import com.jennifer.controller.ShippingAddressController;
import com.jennifer.entity.ShippingAddress;
import com.jennifer.entity.UserInfo;
import com.jennifer.service.ShippingAddressService;
import com.jennifer.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest controller for ShippingAddress activities
 */
@RestController
@RequestMapping("/api/shipping-address")
public class RestShippingAddressController {
    private static final Logger log = LoggerFactory.getLogger(RestShippingAddressController.class);

    private ShippingAddressService shippingAddressService;

    @Autowired
    public RestShippingAddressController(ShippingAddressService shippingAddressService){
        this.shippingAddressService = shippingAddressService;
    }

    @GetMapping
    public Object findAll() {
        List<ShippingAddress> shippingAddresses = shippingAddressService.findAllShippingAddresses();

        return shippingAddressService.findAllShippingAddresses();
    }

    @GetMapping("/user")
    public Object findUserShippingAddress(){
        UserInfo userInfo = AppUtil.getCustomerFromSession();

        return shippingAddressService.findByUser(userInfo);
    }

}
