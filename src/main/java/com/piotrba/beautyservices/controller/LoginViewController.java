package com.piotrba.beautyservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {
    private static final Logger logger = LoggerFactory.getLogger(LoginViewController.class);


    @GetMapping("/login")
    public String getLoginView() {
        logger.info("Request to view page for login.");
        return "/security/login";
    }

}
