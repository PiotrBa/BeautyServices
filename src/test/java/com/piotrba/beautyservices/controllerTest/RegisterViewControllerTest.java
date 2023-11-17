package com.piotrba.beautyservices.controllerTest;

import com.piotrba.beautyservices.controller.RegisterViewController;
import com.piotrba.beautyservices.entity.Customer;
import com.piotrba.beautyservices.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RegisterViewControllerTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private Model model;
    private RegisterViewController registerViewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        registerViewController = new RegisterViewController(customerRepository, passwordEncoder);
    }

    @Test
    void testRegisterCustomerView() {
        String viewName = registerViewController.registerCustomerView(model);
        assertEquals("/security/register", viewName);
        verify(model).addAttribute(eq("user"), any(Customer.class));
    }

    @Test
    void testRegisterCustomer() {
        Customer customer = new Customer();
        customer.setPassword("password");
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        String viewName = registerViewController.registerCustomer(customer);

        assertEquals("redirect:/login", viewName);
        verify(passwordEncoder).encode("password");
        verify(customerRepository).save(customer);
        assertEquals("ROLE_USER", customer.getRole());
        assertTrue(customer.getActive());
    }

    @Test
    void testRegisterAdminView() {
        String viewName = registerViewController.registerAdminView(model);
        assertEquals("/security/register-admin", viewName);
        verify(model).addAttribute(eq("admin"), any(Customer.class));
    }

    @Test
    void testRegisterAdmin() {
        Customer customer = new Customer();
        customer.setPassword("password");
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        String viewName = registerViewController.registerAdmin(customer);

        assertEquals("redirect:/customers", viewName);
        verify(passwordEncoder).encode("password");
        verify(customerRepository).save(customer);
        assertEquals("ROLE_ADMIN", customer.getRole());
        assertTrue(customer.getActive());
    }
}

