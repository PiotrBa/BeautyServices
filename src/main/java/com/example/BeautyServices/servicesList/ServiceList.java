package com.example.BeautyServices.servicesList;

import com.example.BeautyServices.entity.Service;
import com.example.BeautyServices.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class ServiceList implements CommandLineRunner {
    private final ServiceRepository serviceRepository;

    @Override
    public void run(String... args) {
        List<Service> nailServices = Arrays.asList(
                new Service(1L,"Manicure", 30.0, 60, "Basic manicure"),
                new Service(2L,"Pedicure", 40.0, 60, "Basic pedicure"),
                new Service(3L,"Gel Nails", 50.0, 90, "Gel nail extension"),
                new Service(4L,"Nail Art", 20.0, 30, "Nail art design"),
                new Service(5L,"French Manicure", 35.0, 60, "Classic French manicure"),
                new Service(6L,"Acrylic Nails", 55.0, 90, "Acrylic nail extension"),
                new Service(7L,"Shellac Manicure", 45.0, 60, "Shellac manicure"),
                new Service(8L,"Spa Pedicure", 60.0, 90, "Relaxing spa pedicure"),
                new Service(9L,"Nail Repair", 10.0, 15, "Nail repair"),
                new Service(10L,"Nail Polish Change", 15.0, 15, "Nail polish change")
        );
        serviceRepository.saveAll(nailServices);
    }
}
