package com.example.BeautyServices.setUpDataLoader;

import com.example.BeautyServices.entity.CosmeticService;
import com.example.BeautyServices.entity.Customer;
import com.example.BeautyServices.repository.CustomerRepository;
import com.example.BeautyServices.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class SetUpDataLoader implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ServiceRepository serviceRepository;

    @Override
    public void run(String... args) {
        List<Customer> customers = Arrays.asList(
                new Customer(1L, "Adam", "+1234567890", "adam.johnson@example.com", "adam", passwordEncoder.encode("1234"), "ROLE_ADMIN", true));
        customerRepository.saveAll(customers);

        List<CosmeticService> nailCosmeticServices = Arrays.asList(
                new CosmeticService(1L,"Manicure", 30.0, 60, "Basic manicure"),
                new CosmeticService(2L,"Pedicure", 40.0, 60, "Basic pedicure"),
                new CosmeticService(3L,"Gel Nails", 50.0, 90, "Gel nail extension"),
                new CosmeticService(4L,"Nail Art", 20.0, 30, "Nail art design"),
                new CosmeticService(5L,"French Manicure", 35.0, 60, "Classic French manicure"),
                new CosmeticService(6L,"Acrylic Nails", 55.0, 90, "Acrylic nail extension"),
                new CosmeticService(7L,"Shellac Manicure", 45.0, 60, "Shellac manicure"),
                new CosmeticService(8L,"Spa Pedicure", 60.0, 90, "Relaxing spa pedicure"),
                new CosmeticService(9L,"Nail Repair", 10.0, 15, "Nail repair"),
                new CosmeticService(10L,"Nail Polish Change", 15.0, 15, "Nail polish change")
        );
        serviceRepository.saveAll(nailCosmeticServices);

    }
}
