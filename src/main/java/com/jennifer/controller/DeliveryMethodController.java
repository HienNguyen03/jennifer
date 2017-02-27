package com.jennifer.controller;

import com.jennifer.service.DeliveryMethodService;
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
public class DeliveryMethodController {

    private static final Logger log = LoggerFactory.getLogger(DeliveryMethodController.class);
    private DeliveryMethodService deliveryMethodService;

    @Autowired
    public DeliveryMethodController(DeliveryMethodService deliveryMethodService) {
        this.deliveryMethodService = deliveryMethodService;
    }

    @RequestMapping("/manager/delivery-method")
    public String mListOfDeliveryMethod(Model model) {
        return "manager/delivery-method";
    }
}
