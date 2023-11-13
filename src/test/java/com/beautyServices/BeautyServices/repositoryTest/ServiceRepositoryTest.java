package com.beautyServices.BeautyServices.repositoryTest;

import com.beautyServices.BeautyServices.entity.CosmeticService;
import com.beautyServices.BeautyServices.repository.ServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class ServiceRepositoryTest {

    @Autowired
    private ServiceRepository serviceRepository;

    @Test
    @Transactional
    void whenFindAll_thenReturnsAllServices(){
        CosmeticService testService1 = new CosmeticService(null, "Service 1", 100.0, 60, "Description 1");
        CosmeticService testService2 = new CosmeticService(null, "Service 2", 150.0, 90, "Description 2");
        serviceRepository.save(testService1);
        serviceRepository.save(testService2);

        List<CosmeticService> serviceList = serviceRepository.findAll();

        Assertions.assertFalse(serviceList.isEmpty(), "The list of services should not be empty.");
        Assertions.assertTrue(serviceList.size() >= 2, "There should be at least two services.");
    }

    @Test
    @Transactional
    void whenFindById_thenReturnService(){
        CosmeticService service = new CosmeticService(null, "Service Test", 120.0, 75, "Test Description");
        service = serviceRepository.save(service);

        Optional<CosmeticService> foundService = serviceRepository.findById(service.getServiceId());

        Assertions.assertTrue(foundService.isPresent(),"The service should be found.");
        Assertions.assertEquals(service.getServiceId(), foundService.get().getServiceId(), "The ID should be the same.");
    }

    @Test
    @Transactional
    void whenSave_thenServiceIsCreated(){
        CosmeticService newService = new CosmeticService(null, "New Service", 200.0, 120, "New Service Description");
        CosmeticService savedService = serviceRepository.save(newService);

        Assertions.assertNotNull(savedService.getServiceId(), "The service ID should not be null after saving");
        Assertions.assertEquals("New Service", savedService.getServiceName(), "The service name should be New Service");
        Assertions.assertEquals(200.0, savedService.getPrice(), "The service price should be 200.0");
        Assertions.assertEquals(120, savedService.getServiceDuration(), "The service duration should be 120 minutes");
        Assertions.assertEquals("New Service Description", savedService.getServiceDescription(), "The service description should be New Service Description");

        Optional<CosmeticService> foundService = serviceRepository.findById(savedService.getServiceId());
        Assertions.assertTrue(foundService.isPresent(), "The service should be available in the database");
    }

    @Test
    @Transactional
    void whenDelete_thenServiceIsRemoved(){
        CosmeticService service = new CosmeticService(null, "Service for Deletion", 130.0, 80, "Deletion Test");
        service = serviceRepository.save(service);
        serviceRepository.delete(service);

        Optional<CosmeticService> foundService = serviceRepository.findById(service.getServiceId());
        Assertions.assertFalse(foundService.isPresent(), "The service should be removed");

    }

}
