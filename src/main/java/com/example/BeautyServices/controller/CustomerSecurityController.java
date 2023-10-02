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
public class CustomerSecurityController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping()
    public String registerCustomerView(Model model){
        model.addAttribute("user", new Customer());
        return "/security/register";
    }

    @PostMapping()
    public String registerCustomer(Customer customer){
       customer.setPassword(passwordEncoder.encode(customer.getPassword()));
       customer.setRole("USER");
       customer.setActive(true);
       customerRepository.save(customer);
       return "/reservations";
    }

}
