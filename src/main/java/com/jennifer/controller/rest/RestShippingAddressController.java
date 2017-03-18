package com.jennifer.controller.rest;

import com.jennifer.entity.OrderInfo;
import com.jennifer.entity.ShippingAddress;
import com.jennifer.entity.UserInfo;
import com.jennifer.service.OrderInfoService;
import com.jennifer.service.ShippingAddressService;
import com.jennifer.util.AppUtil;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest controller for ShippingAddress activities
 */
@RestController
@RequestMapping("/api/shipping-address")
public class RestShippingAddressController {
    private static final Logger log = LoggerFactory.getLogger(RestShippingAddressController.class);

    private ShippingAddressService shippingAddressService;
    private OrderInfoService orderInfoService;

    @Autowired
    public RestShippingAddressController(ShippingAddressService shippingAddressService,
                                         OrderInfoService orderInfoService){
        this.shippingAddressService = shippingAddressService;
        this.orderInfoService = orderInfoService;
    }

    @GetMapping
    public Object findAll() {
        return shippingAddressService.findAllShippingAddresses();
    }

    @GetMapping("/user")
    public Object findUserShippingAddress(){
        UserInfo userInfo = AppUtil.getCustomerFromSession();
        return shippingAddressService.findByUser(userInfo);
    }

    @PutMapping("/user")
    public Object updateShippingAddress(@RequestBody ShippingAddress shippingAddress){
        UserInfo userInfo = AppUtil.getCustomerFromSession();
        ShippingAddress shippingAddressFound = shippingAddressService.findById(shippingAddress.getId());

        if(shippingAddressFound.getUserInfo().getId() != userInfo.getId()){
            return new ResponseEntity<>("Unable to update!", HttpStatus.INTERNAL_SERVER_ERROR);
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
                return new ResponseEntity<>("Unable to delete!", HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                List<OrderInfo> orderInfos = orderInfoService.findAllOrders();

                for(OrderInfo o: orderInfos){
                    if(o.getShippingAddress().getId() == shippingAddressFound.getId()){
                        shippingAddressFound.setStatus("Deleted");
                        return shippingAddressService.update(shippingAddressFound);
                    }
                }

                shippingAddressService.delete(shippingAddressFound);
                return shippingAddressFound;
            }
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            shippingAddressFound.setStatus("Deleted");
            return shippingAddressService.update(shippingAddressFound);
        }catch (Exception e){
            return new ResponseEntity<>(" Exception", HttpStatus.INTERNAL_SERVER_ERROR);
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
