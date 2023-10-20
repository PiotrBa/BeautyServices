package com.example.BeautyServices.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutViewController {

    @GetMapping("/logout")
    public String getLogoutView() {
        return "/security/logout";
    }
}
