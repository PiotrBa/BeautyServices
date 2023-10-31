package com.example.BeautyServices.controller;

import com.example.BeautyServices.entity.Customer;
import com.example.BeautyServices.repository.CustomerRepository;
import com.example.BeautyServices.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerViewController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;


    @GetMapping()
    public String getListView(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "/customer/customer-list";
    }

    @GetMapping("/add")
    public String addCustomerView(Model model){
        model.addAttribute("customers", new Customer());
        return "/customer/customer-add";
    }

    @PostMapping("/add")
    public String addCustomer(Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole("ROLE_USER");
        customer.setActive(true);
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/edit")
    public String editCustomerView(Model model, @RequestParam Long id){
        model.addAttribute("customers", customerRepository.findById(id));
        return "/customer/customer-edit";
    }

    @PostMapping("edit")
    public String editCustomer(Customer customer, @RequestParam Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer newCustomer = customerOptional.get();
            newCustomer.setName(customer.getName());
            newCustomer.setMobileNumber(customer.getMobileNumber());
            newCustomer.setEmail(customer.getEmail());
            customerRepository.save(newCustomer);
        }
        return "redirect:/customers";
    }

    @GetMapping("/delete")
    public String deleteCustomerView(Model model, @RequestParam Long id){
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isPresent()) {
            model.addAttribute("customers", customerOptional.get());
        }
        return "/customer/customer-delete";
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam Long id){
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            customerService.deleteCustomer(customer.getCustomerId());
        }
        return "redirect:/customers";
    }
}
