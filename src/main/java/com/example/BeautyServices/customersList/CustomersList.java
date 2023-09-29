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

    @Override
    public void run(String... args) {
        List<Customer> customers = Arrays.asList(
                new Customer(1L, "Alice Johnson", "+1234567890", "alice.johnson@example.com"),
                new Customer(2L, "Bob Smith", "+1234567891", "bob.smith@example.com"),
                new Customer(3L, "Charlie Brown", "+1234567892", "charlie.brown@example.com"),
                new Customer(4L, "David Wilson", "+1234567893", "david.wilson@example.com"),
                new Customer(5L, "Eve Martin", "+1234567894", "eve.martin@example.com"),
                new Customer(6L, "Frank White", "+1234567895", "frank.white@example.com"),
                new Customer(7L, "Grace King", "+1234567896", "grace.king@example.com"),
                new Customer(8L, "Helen Clark", "+1234567897", "helen.clark@example.com"),
                new Customer(9L, "Ivan Rodriguez", "+1234567898", "ivan.rodriguez@example.com"),
                new Customer(10L, "Jasmine Lee", "+1234567899", "jasmine.lee@example.com")
        );

        customerRepository.saveAll(customers);
    }
}
