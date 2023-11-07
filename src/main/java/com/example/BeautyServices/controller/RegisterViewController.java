package com.example.BeautyServices.controller;

import com.example.BeautyServices.entity.Customer;
import com.example.BeautyServices.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegisterViewController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(RegisterViewController.class);

    @GetMapping("/customer")
    public String registerCustomerView(Model model){
        logger.info("Request to view page for adding a new customer.");
        model.addAttribute("user", new Customer());
        return "/security/register";
    }

    @PostMapping("/customer")
    public String registerCustomer(Customer customer){
        logger.info("Request to add a new customer.");
       customer.setPassword(passwordEncoder.encode(customer.getPassword()));
       customer.setRole("ROLE_USER");
       customer.setActive(true);
       customerRepository.save(customer);
        logger.info("Customer added successfully!!!");
       return "redirect:/login";
    }

    @GetMapping("/admin")
    public String registerAdminView(Model model){
        logger.info("Request to view page for adding a new admin.");
        model.addAttribute("admin", new Customer());
        return "/security/register-admin";
    }

    @PostMapping("/admin")
    public String registerAdmin(Customer customer){
        logger.info("Request to add a new admin.");
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole("ROLE_ADMIN");
        customer.setActive(true);
        customerRepository.save(customer);
        logger.info("Admin added successfully!!!");
        return "redirect:/customers";
    }
}

