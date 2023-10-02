package com.example.BeautyServices.customersList;

import com.example.BeautyServices.entity.Customer;
import com.example.BeautyServices.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomersList implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    //Customer list - for production purposes.
    @Override
    public void run(String... args) {
        List<Customer> customers = Arrays.asList(
                new Customer(1L, "Alice Johnson", "+1234567890", "alice.johnson@example.com", "alice", "password123", "ROLE_USER", true),
                new Customer(2L, "Bob Smith", "+1234567891", "bob.smith@example.com", "bob", "password123", "ROLE_USER", true),
                new Customer(3L, "Charlie Brown", "+1234567892", "charlie.brown@example.com", "charlie", "password123", "ROLE_USER", true),
                new Customer(4L, "David Wilson", "+1234567893", "david.wilson@example.com", "david", "password123", "ROLE_USER", true),
                new Customer(5L, "Eve Martin", "+1234567894", "eve.martin@example.com", "eve", "password123", "ROLE_USER", true),
                new Customer(6L, "Frank White", "+1234567895", "frank.white@example.com", "frank", "password123", "ROLE_USER", true),
                new Customer(7L, "Grace King", "+1234567896", "grace.king@example.com", "grace", "password123", "ROLE_USER", true),
                new Customer(8L, "Helen Clark", "+1234567897", "helen.clark@example.com", "helen", "password123", "ROLE_USER", true),
                new Customer(9L, "Ivan Rodriguez", "+1234567898", "ivan.rodriguez@example.com", "ivan", "password123", "ROLE_USER", true),
                new Customer(10L, "Jasmine Lee", "+1234567899", "jasmine.lee@example.com", "jasmine", "password123", "ROLE_USER", true)
        );

        customerRepository.saveAll(customers);
    }
}
