package com.jennifer.controller.rest;

import com.jennifer.controller.ShippingAddressController;
import com.jennifer.entity.ShippingAddress;
import com.jennifer.entity.UserInfo;
import com.jennifer.service.ShippingAddressService;
import com.jennifer.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//        List<ShippingAddress> shippingAddresses = shippingAddressService.findByUser(userInfo);
//        for (ShippingAddress s: shippingAddresses){
//            log.info(s.toString());
//        }
        return shippingAddressService.findByUser(userInfo);
    }

    @PutMapping("/user")
    public Object updateShippingAddress(@RequestBody ShippingAddress shippingAddress){
        shippingAddress.toString();
        UserInfo userInfo = AppUtil.getCustomerFromSession();
        ShippingAddress shippingAddressFound = shippingAddressService.findById(shippingAddress.getId());

        if(shippingAddressFound.getUserInfo().getId() != userInfo.getId()){
            return new ResponseEntity("Unable to update!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            log.info(shippingAddressService.update(shippingAddress).toString());
            return shippingAddressService.update(shippingAddress);
        }

    }

    @DeleteMapping("/user/delete")
    public Object deleteShippingAddress(@RequestBody ShippingAddress shippingAddress){
        UserInfo userInfo = AppUtil.getCustomerFromSession();
        ShippingAddress shippingAddressFound = shippingAddressService.findById(shippingAddress.getId());
        try{
            if(shippingAddressFound.getUserInfo().getId() != userInfo.getId()){
                return new ResponseEntity("Unable to delete!", HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                shippingAddressService.delete(shippingAddressFound);
                return shippingAddressFound;
            }
        }catch (DataIntegrityViolationException e){
            shippingAddress.setStatus("Deleted");
            return shippingAddressService.update(shippingAddress);
        }catch (Exception e){
            return new ResponseEntity(" Exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user")
    public Object insertShippingAddress(@RequestBody ShippingAddress shippingAddress){
        UserInfo userInfo = AppUtil.getCustomerFromSession();
        ShippingAddress shippingAddressFound = shippingAddressService.findById(shippingAddress.getId());

        if(shippingAddressFound != null) {
            return null;
        }else{
            shippingAddress.setUserInfo(userInfo);
            shippingAddress.setStatus("Available");
            return shippingAddressService.insert(shippingAddress);
        }

    }


}
