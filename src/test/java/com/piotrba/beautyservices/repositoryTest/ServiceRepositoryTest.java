package com.piotrba.beautyservices.repositoryTest;

import com.piotrba.beautyservices.entity.CosmeticService;
import com.piotrba.beautyservices.repository.ServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static com.piotrba.beautyservices.UtilsData.builderService;

@DataJpaTest
@ActiveProfiles("test")
public class ServiceRepositoryTest {

    @Autowired
    private ServiceRepository serviceRepository;

    @Test
    void whenFindAll_thenReturnsAllServices(){
        CosmeticService testService1 = builderService();
        testService1.setServiceName("Service 1");
        CosmeticService testService2 = builderService();
        testService2.setServiceName("Service 2");
        serviceRepository.saveAll(List.of(testService1, testService2));

        List<CosmeticService> serviceList = serviceRepository.findAll();

        Assertions.assertFalse(serviceList.isEmpty(), "The list of services should be empty.");
        Assertions.assertEquals(2,serviceList.size(), "There should be at least two services.");
    }

    @Test
    void whenFindById_thenReturnService(){
        CosmeticService service = builderService();
        service = serviceRepository.save(service);

        Optional<CosmeticService> foundService = serviceRepository.findById(service.getServiceId());

        Assertions.assertTrue(foundService.isPresent(),"The service should be found.");
        Assertions.assertEquals(service.getServiceId(), foundService.get().getServiceId(), "The ID should be the same.");
    }

    @Test
    void whenSave_thenServiceIsCreated(){
        CosmeticService newService = builderService();
        CosmeticService savedService = serviceRepository.save(newService);
        Optional<CosmeticService> foundService = serviceRepository.findById(savedService.getServiceId());
        Assertions.assertNotNull(savedService.getServiceId(), "The service ID should not be null after saving");
        Assertions.assertEquals("New Service", savedService.getServiceName(), "The service name should be New Service");
        Assertions.assertEquals(200.0, savedService.getPrice(), "The service price should be 200.0");
        Assertions.assertEquals(120, savedService.getServiceDuration(), "The service duration should be 120 minutes");
        Assertions.assertEquals("New Service Description", savedService.getServiceDescription(), "The service description should be New Service Description");

        Assertions.assertTrue(foundService.isPresent(), "The service should be available in the database");
    }

    @Test
    void whenDelete_thenServiceIsRemoved(){
        CosmeticService service = builderService();
        service = serviceRepository.save(service);
        serviceRepository.delete(service);

        Optional<CosmeticService> foundService = serviceRepository.findById(service.getServiceId());
        Assertions.assertFalse(foundService.isPresent(), "The service should be removed");

    }

}
