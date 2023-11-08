package com.beautyServices.BeautyServices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Controller
public class PostLoginController {
    private static final Logger logger = LoggerFactory.getLogger(PostLoginController.class);
    @GetMapping("/postLogin")
    public void postLogin(Authentication authentication, HttpServletResponse response) throws IOException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean isAdmin = false;

        for (GrantedAuthority authority : authorities) {
            if ("ROLE_ADMIN".equals(authority.getAuthority())) {
                isAdmin = true;
                break;
            }
        }

        if (isAdmin) {
            logger.info("Admin is login.");
            response.sendRedirect("/reservations");
        } else {
            logger.info("Customer is login.");
            response.sendRedirect("/homepage");
        }
    }
}