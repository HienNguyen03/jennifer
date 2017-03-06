package com.jennifer.controller;

import com.jennifer.entity.UserInfo;
import com.jennifer.service.UserInfoService;
import com.jennifer.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
