package com.example.BeautyServices.customersList;

import com.example.BeautyServices.entity.Customer;
import com.example.BeautyServices.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomersList implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    //Customer list - for production purposes.
    @Override
    public void run(String... args) {
        List<Customer> customers = Arrays.asList(
                new Customer(1L, "Adam", "+1234567890", "adam.johnson@example.com", "adam", passwordEncoder.encode("1234"), "ROLE_ADMIN", true));
        customerRepository.saveAll(customers);
    }
}
