package com.jennifer.controller.rest;

import com.jennifer.entity.DeliveryMethod;
import com.jennifer.service.DeliveryMethodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Object findAll() {
        return deliveryMethodService.findAllDeliveryMethods();
    }

    //Update data
    @PutMapping
    public Object update(@RequestBody DeliveryMethod deliveryMethod) {
        DeliveryMethod deliveryMethodFound = deliveryMethodService.findById(deliveryMethod.getId());

        if (deliveryMethodFound != null) {
            DeliveryMethod deliveryMethodUpdated = deliveryMethodService.update(deliveryMethod);
            return deliveryMethod;
        }

        return new ResponseEntity("Unable to update!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //deleting data
    @DeleteMapping
    public Object delete(@RequestBody DeliveryMethod deliveryMethod){
        DeliveryMethod deliveryMethodFound = deliveryMethodService.findById(deliveryMethod.getId());

        try {
            if (deliveryMethodFound != null)
                deliveryMethodService.delete(deliveryMethodFound);

            return deliveryMethodFound;

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity("Product '" + deliveryMethodFound.getName() + "' is in used! Unable to delete!", HttpStatus.CONFLICT);
        }catch (Exception e){
            return new ResponseEntity(" Exception", HttpStatus.INTERNAL_SERVER_ERROR);
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
