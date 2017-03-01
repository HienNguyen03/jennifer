package com.jennifer.controller;

import com.jennifer.dto.SignupForm;
import com.jennifer.service.UserInfoService;
import com.jennifer.util.AppUtil;
import com.jennifer.validator.SignupFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * This controls all basic activities related to the application such as login,
 * sign up and different landing page redirection based on login role
 */

@Controller
public class AppController {

    private static final Logger log = LoggerFactory.getLogger(AppController.class);

    private AuthenticationTrustResolver authenticationTrustResolver;
    private SignupFormValidator signupFormValidator;
    private UserInfoService userInfoService;

    @Autowired
    public AppController(AuthenticationTrustResolver authenticationTrustResolver, SignupFormValidator signupFormValidator, UserInfoService userInfoService) {
        this.authenticationTrustResolver = authenticationTrustResolver;
        this.signupFormValidator = signupFormValidator;
        this.userInfoService = userInfoService;
    }

    @RequestMapping("/")
    public String customer_homepage(){
        return "index";
    }

    @RequestMapping("/manager")
    public String manager_homepage(){
        return "manager/index";
    }

    @InitBinder("signupForm")
    protected void initSignupBuilder(WebDataBinder binder) {
        binder.setValidator(signupFormValidator);
    }


    // This method handles login GET requests.
    // check invalid login information
    // check logout
    // If the user is already logged-in and tries to go to login page again, will be redirected to the home page.
    @RequestMapping(value = "/login")
    public String login(Model model, @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout) {

        //log.info("error:" + error);
        //log.info("logout:" + logout);

        if (error != null) {
            model.addAttribute("error", AppUtil.getMessage("login.failure"));
        }

        if (logout != null) {
            model.addAttribute("logout_error", AppUtil.getMessage("logout.success"));
        }

        model.addAttribute(new SignupForm());

        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/";
        }
    }

    // This method returns true if users is already authenticated [logged-in]
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    @RequestMapping(value = "/signup")
    public String signup(){
        return "redirect:/login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(Model model, @ModelAttribute("signupForm") @Valid SignupForm signupForm,
                             BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            //log.info("Error: " + result.getAllErrors().toString());
            model.addAttribute("signupForm", signupForm);
            return "login";
        }

        boolean jobDone;
        jobDone = userInfoService.signup(signupForm);

        if (jobDone)
            AppUtil.flashWithName(redirectAttributes, "Signup", "success", "signup.success");
        else
            AppUtil.flashWithName(redirectAttributes, "Signup", "danger", "signup.failure");

        return "redirect:/login";

    }
}
