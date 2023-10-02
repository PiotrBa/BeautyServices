package com.example.BeautyServices.controller;

import com.example.BeautyServices.entity.Customer;
import com.example.BeautyServices.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerViewController {
    private final CustomerRepository repository;


    @GetMapping()
    public String getListView(Model model) {
        model.addAttribute("customers", repository.findAll());
        return "/customer/customer-list";
    }

    @GetMapping("/add")
    public String addCustomerView(Model model){
        model.addAttribute("customers", new Customer());
        return "/customer/customer-add";
    }

    @PostMapping("/add")
    public String addCustomer(Customer customer){
        repository.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/edit")
    public String editCustomerView(Model model, @RequestParam Long id){
        model.addAttribute("customers", repository.findById(id));
        return "/customer/customer-edit";
    }

    @PostMapping("edit")
    public String editCustomer(Customer customer, @RequestParam Long id) {
        Optional<Customer> customerOptional = repository.findById(id);
        if (customerOptional.isPresent()) {
            Customer newCustomer = customerOptional.get();
            newCustomer.setName(customer.getName());
            newCustomer.setMobileNumber(customer.getMobileNumber());
            newCustomer.setEmail(customer.getEmail());
            repository.save(newCustomer);
        }
        return "redirect:/customers";
    }

    @GetMapping("/delete")
    public String deleteCustomerView(Model model, @RequestParam Long id){
        model.addAttribute("customers", repository.findById(id));
        return "/customer/customer-delete";
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam Long id){
        Optional<Customer> customerOptional = repository.findById(id);
        customerOptional.ifPresent(repository::delete);
        return "redirect:/customers";
    }
}
