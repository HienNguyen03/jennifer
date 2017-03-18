package com.jennifer.controller.rest;

import com.jennifer.dto.ChangePasswordForm;
import com.jennifer.entity.UserInfo;
import com.jennifer.service.UserInfoService;
import com.jennifer.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controls all activities related to users
 */
@RestController
@RequestMapping("/api/user")
public class RestUserController {
    private static final Logger log = LoggerFactory.getLogger(RestUserController.class);
    private UserInfoService userInfoService;

    @Autowired
    public RestUserController( UserInfoService userInfoService){
        this.userInfoService = userInfoService;
    }

    @GetMapping("/profile")
    public Object findUserInfo(){
        UserInfo userInfo = AppUtil.getCustomerFromSession();
        UserInfo userInfoFound = userInfoService.findById(userInfo.getId());
        return userInfoFound;
    }

    @PutMapping("/profile")
    public Object updateProfile(@RequestBody UserInfo userInfo){
        UserInfo userInfoSession = AppUtil.getCustomerFromSession();
        log.info("Sended " + userInfo.toString());

        if (userInfo.getId()!= userInfoSession.getId()){
            return new ResponseEntity<>("Unable to update!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            UserInfo userInfoFound = userInfoService.findById(userInfoSession.getId());
            userInfoFound.setFullname(userInfo.getFullname());
            userInfoFound.setEmail(userInfo.getEmail());
            return userInfoService.updateUser(userInfoFound);
        }
    }

    @PutMapping("/password")
    public Object changePassword(@RequestBody ChangePasswordForm changePasswordForm){
        log.info(changePasswordForm.toString());
        UserInfo userInfo = AppUtil.getCustomerFromSession();

        boolean passwordMatched = userInfoService.compareUserPassword(userInfo,changePasswordForm.getOldPassword());
        if (!passwordMatched){
            return new ResponseEntity<>("Old password is not correct!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return userInfoService.changePassword(userInfo, changePasswordForm.getNewPassword());
        }

    }

}
