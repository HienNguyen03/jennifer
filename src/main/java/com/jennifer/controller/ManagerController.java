package com.jennifer.controller;

import com.jennifer.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Handles all mappings controlling by manager
 */

@Controller
public class ManagerController {
    private static final Logger log = LoggerFactory.getLogger(ManagerController.class);
    private UserInfoService userInfoService;

    @Autowired
    public ManagerController(UserInfoService userInfoService){
        this.userInfoService = userInfoService;
    }



}
