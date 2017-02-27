package com.jennifer.controller;

import com.jennifer.entity.ShippingAddress;
import com.jennifer.service.DeliveryMethodService;
import com.jennifer.service.ShippingAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for delivery method activities
 */
@Controller
public class ShippingAddressController {
    private static final Logger log = LoggerFactory.getLogger(ShippingAddressController.class);
    private ShippingAddressService shippingAddressService;

    @Autowired
    public ShippingAddressController(ShippingAddressService shippingAddressService){
        this.shippingAddressService = shippingAddressService;
    }

    @RequestMapping("/manager/shipping-address")
    public String mListOfShippingAddress(Model model){
        return "manager/shipping-address";
    }
}
