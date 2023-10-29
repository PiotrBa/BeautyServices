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
                new Customer(1L, "Adam", "07234567890", "adam.johnson@example.com", "adam", passwordEncoder.encode("1234"), "ROLE_ADMIN", true));
        customerRepository.saveAll(customers);

        List<CosmeticService> nailCosmeticServices = Arrays.asList(
                new CosmeticService(1L,"Manicure", 30.0, 60, "Hand care with polish"),
                new CosmeticService(2L,"Pedicure", 40.0, 60, "Foot care with polish"),
                new CosmeticService(3L,"Gel Nails", 50.0, 90, "Durable gel extensions"),
                new CosmeticService(4L,"Nail Art", 20.0, 30, "Creative designs"),
                new CosmeticService(5L,"French Manicure", 35.0, 60, "White-tip style"),
                new CosmeticService(6L,"Acrylic Nails", 55.0, 90, "Sturdy acrylic extensions"),
                new CosmeticService(7L,"Shellac Manicure", 45.0, 60, "Long-lasting shellac polish"),
                new CosmeticService(8L,"Spa Pedicure", 60.0, 90, "Foot spa with extras"),
                new CosmeticService(9L,"Nail Repair", 10.0, 15, "Fixing damaged nails"),
                new CosmeticService(10L,"Nail Polish Change", 15.0, 15, "Quick polish swap")
        );
        serviceRepository.saveAll(nailCosmeticServices);

    }
}
