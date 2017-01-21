package com.jennifer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This control all basic activities related to the application such as login, sign up
 */

@RestController
public class AppController {

    @RequestMapping("/")
    public String helloWorld(){
        return "Hello my friend !";
    }

}
