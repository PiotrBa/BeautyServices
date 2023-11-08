package com.beautyServices.BeautyServices.controller;

import com.beautyServices.BeautyServices.entity.Customer;
import com.beautyServices.BeautyServices.repository.CustomerRepository;
import com.beautyServices.BeautyServices.service.CustomerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(CustomerViewController.class);

    @GetMapping()
    public String getListView(Model model) {
        logger.info("Request to fetch all customers.");
        model.addAttribute("customers", customerRepository.findAll());
        return "/customer/customer-list";
    }

    @GetMapping("/add")
    public String addCustomerView(Model model){
        logger.info("Request to view page for adding a new customer.");
        model.addAttribute("customers", new Customer());
        return "/customer/customer-add";
    }

    @PostMapping("/add")
    public String addCustomer(Customer customer){
        logger.info("Request to add a new customer.");
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole("ROLE_USER");
        customer.setActive(true);
        customerRepository.save(customer);
        logger.info("Customer added successfully!!!");
        return "redirect:/customers";
    }

    @GetMapping("/edit")
    public String editCustomerView(Model model, @RequestParam Long id){
        logger.info("Request to view page for editing customer with id: {}", id);
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isPresent()) {
            model.addAttribute("customers", customerRepository.findById(id));
        }else {
            logger.info("Customer with id: {} not found", id);
        }
        return "/customer/customer-edit";
    }

    @PostMapping("edit")
    public String editCustomer(Customer customer, @RequestParam Long id) {
        logger.info("Request to edit customer with id: {}", id);
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer newCustomer = customerOptional.get();
            newCustomer.setName(customer.getName());
            newCustomer.setMobileNumber(customer.getMobileNumber());
            newCustomer.setEmail(customer.getEmail());
            customerRepository.save(newCustomer);
            logger.info("Customer with id: {} updated successfully", id);
        }else {
            logger.error("Failed to update customer with id: {} as it does not exist", id);
        }
        return "redirect:/customers";
    }

    @GetMapping("/delete")
    public String deleteCustomerView(Model model, @RequestParam Long id){
        logger.info("Request to view page for deleting customer with id: {}", id);
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isPresent()) {
            model.addAttribute("customers", customerOptional.get());
        }else {
            logger.error("Customer with id: {} not found for deletion", id);
        }
        return "/customer/customer-delete";
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam Long id){
        logger.info("Request to delete customer with id: {}", id);
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            customerService.deleteCustomer(customer.getCustomerId());
            logger.info("Customer with id: {} deleted successfully", id);
        }else {
            logger.error("Failed to delete customer with id: {} as it does not exist", id);
        }
        return "redirect:/customers";
    }
}
