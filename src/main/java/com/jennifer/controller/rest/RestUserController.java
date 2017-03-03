package com.jennifer.controller.rest;

import com.jennifer.entity.UserInfo;
import com.jennifer.service.UserInfoService;
import com.jennifer.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Controls all activities related to users
 */
@RestController
@RequestMapping("/api/profile")
public class RestUserController {
    private static final Logger log = LoggerFactory.getLogger(RestUserController.class);
    private UserInfoService userInfoService;

    @Autowired
    public RestUserController( UserInfoService userInfoService){
        this.userInfoService = userInfoService;
    }

    @GetMapping
    public Object findUserInfo(){
        UserInfo userInfo = AppUtil.getCustomerFromSession();
//        log.info(userInfo.toString());
//        ArrayList<UserInfo> userInfos = new ArrayList<>();
//        userInfos.add(userInfo);
        return userInfo;
    }

    @PutMapping
    public Object updateProfile(UserInfo userInfo){
        UserInfo userInfoFound = AppUtil.getCustomerFromSession();

        log.info("Sended" + userInfo.toString());

        if (userInfo.getId()!= userInfoFound.getId()){
            return new ResponseEntity("Unable to update!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info(userInfoService.updateUser(userInfo).toString());
        return userInfoService.updateUser(userInfo);

    }

}
