package com.beautyServices.BeautyServices.controllerTest;

import com.beautyServices.BeautyServices.controller.LoginViewController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginViewControllerTest {

    private LoginViewController loginViewController;

    @BeforeEach
    void setUp() {
        loginViewController = new LoginViewController();
    }

    @Test
    void testGetLoginView() {
        String viewName = loginViewController.getLoginView();
        assertEquals("/security/login", viewName);
    }
}