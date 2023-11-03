package com.example.BeautyServices.controller;

import com.example.BeautyServices.entity.Customer;
import com.example.BeautyServices.repository.CustomerRepository;
import lombok.AllArgsConstructor;
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

    @GetMapping("/customer")
    public String registerCustomerView(Model model){
        model.addAttribute("user", new Customer());
        return "/security/register";
    }

    @PostMapping("/customer")
    public String registerCustomer(Customer customer){
       customer.setPassword(passwordEncoder.encode(customer.getPassword()));
       customer.setRole("ROLE_USER");
       customer.setActive(true);
       customerRepository.save(customer);
       return "redirect:/login";
    }

    @GetMapping("/admin")
    public String registerAdminView(Model model){
        model.addAttribute("admin", new Customer());
        return "/security/register-admin";
    }

    @PostMapping("/admin")
    public String registerAdmin(Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole("ROLE_ADMIN");
        customer.setActive(true);
        customerRepository.save(customer);
        return "redirect:/customers";
    }
}

