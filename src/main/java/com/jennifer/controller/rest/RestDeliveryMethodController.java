package com.jennifer.controller.rest;

import com.jennifer.entity.DeliveryMethod;
import com.jennifer.service.DeliveryMethodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for DeliveryMethod activities
 */
@RestController
@RequestMapping("/api/delivery-method")
public class RestDeliveryMethodController {
    private static final Logger log = LoggerFactory.getLogger(RestDeliveryMethodController.class);
    private DeliveryMethodService deliveryMethodService;

    @Autowired
    public RestDeliveryMethodController(DeliveryMethodService deliveryMethodService) {
        this.deliveryMethodService = deliveryMethodService;
    }

    //Get all data from database
    @GetMapping
    public List<DeliveryMethod> findAll() {
        return deliveryMethodService.findAllDeliveryMethods();
    }

    //Update data
    @PutMapping
    public DeliveryMethod update(@RequestBody DeliveryMethod deliveryMethod) {
        DeliveryMethod deliveryMethodFound = deliveryMethodService.findById(deliveryMethod.getId());

        if (deliveryMethodFound != null) {
            DeliveryMethod deliveryMethodUpdated = deliveryMethodService.update(deliveryMethod);
            return deliveryMethod;
        } else {
           return null;
        }

    }

    //deleting data
    @DeleteMapping
    public DeliveryMethod delete(@RequestBody DeliveryMethod deliveryMethod){
        DeliveryMethod deliveryMethodFound = deliveryMethodService.findById(deliveryMethod.getId());

        if (deliveryMethodFound != null) {
            deliveryMethodService.delete(deliveryMethodFound);
            return deliveryMethod;
        }else{
            return null;
        }
    }

    //inserting data
    @PostMapping
    public  DeliveryMethod insert(@RequestBody DeliveryMethod deliveryMethod){
        DeliveryMethod deliveryMethodFound = deliveryMethodService.findById(deliveryMethod.getId());

        if (deliveryMethodFound != null) {
            return null;
        }else{
            DeliveryMethod deliveryMethodInserted = deliveryMethodService.insert(deliveryMethod);
            return deliveryMethodInserted;
        }
    }
}
