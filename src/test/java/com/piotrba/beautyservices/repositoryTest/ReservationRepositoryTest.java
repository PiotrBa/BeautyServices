package com.piotrba.beautyservices.repositoryTest;

import com.piotrba.beautyservices.entity.CosmeticService;
import com.piotrba.beautyservices.entity.Customer;
import com.piotrba.beautyservices.entity.Reservation;
import com.piotrba.beautyservices.repository.ReservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.piotrba.beautyservices.UtilsData.builderCustomer;
import static com.piotrba.beautyservices.UtilsData.builderService;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class ReservationRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ReservationRepository reservationRepository;


    @Test
    void whenFindByCustomer_thenReturnReservations(){
        Customer customer = builderCustomer();
        testEntityManager.persist(customer);

        CosmeticService service = builderService();
        testEntityManager.persist(service);
        List<CosmeticService> serviceList = new ArrayList<>();
        serviceList.add(service);

        Reservation reservation = new Reservation(null, customer, serviceList, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        reservation.setCustomer(customer);
        testEntityManager.persistAndFlush(reservation);

        List<Reservation> foundReservation = reservationRepository.findByCustomerName("Test Customer");

        Assertions.assertTrue(foundReservation.isEmpty(), "Reservations should be found");
        assertEquals(customer.getName(), foundReservation.get(0).getCustomer().getName(), "The customer's name should match");
    }

    @Test
    void whenFindByCustomerName_thenReturnReservation(){
        Customer customer = builderCustomer();
        testEntityManager.persist(customer);

        CosmeticService service = builderService();
        testEntityManager.persist(service);
        List<CosmeticService> serviceList = new ArrayList<>();
        serviceList.add(service);

        Reservation reservation = new Reservation(null, customer, serviceList, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        reservation.setCustomer(customer);
        testEntityManager.persistAndFlush(reservation);

        List<Reservation> foundReservations = reservationRepository.findByCustomerName("Test Customer");

        Assertions.assertTrue(foundReservations.isEmpty(), "Reservations should be found");
        assertEquals(customer.getName(), foundReservations.get(0).getCustomer().getName(), "The customer's name should match");
    }

    @Test
    void whenFindAllByServiceListContains_thenReturnsReservations(){
        CosmeticService service = builderService();
        testEntityManager.persist(service);

        Customer customer = builderCustomer();
        testEntityManager.persist(customer);

        List<CosmeticService> serviceList = new ArrayList<>();
        serviceList.add(service);
        Reservation reservation = new Reservation(null, customer, serviceList, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        testEntityManager.persistAndFlush(reservation);

        List<Reservation> foundReservations = reservationRepository.findAllByserviceListContains(service);

        Assertions.assertFalse(foundReservations.isEmpty(), "Reservations should be found");
        Assertions.assertTrue(foundReservations.stream()
                .anyMatch(r -> r.getServiceList().contains(service)), "The reservation should contain the specified cosmetic service");
    }

    @Test
    void whenFindAll_thenReturnAllReservations() {
        Customer customer1 = builderCustomer();
        testEntityManager.persist(customer1);
        CosmeticService service1 = builderService();
        testEntityManager.persist(service1);
        List<CosmeticService> serviceList1 = new ArrayList<>();
        serviceList1.add(service1);
        Reservation reservation1 = new Reservation(null, customer1, serviceList1, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        testEntityManager.persist(reservation1);

        Customer customer2 = builderCustomer();
        testEntityManager.persist(customer2);
        CosmeticService service2 = builderService();
        testEntityManager.persist(service2);
        List<CosmeticService> serviceList2 = new ArrayList<>();
        serviceList2.add(service2);
        Reservation reservation2 = new Reservation(null, customer2, serviceList2, LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(1));
        testEntityManager.persist(reservation2);

        List<Reservation> reservations = reservationRepository.findAll();

        Assertions.assertFalse(reservations.isEmpty(), "Should return all reservations");
    }


    @Test
    void whenFindById_thenReturnReservation() {
        Customer customer = builderCustomer();
        testEntityManager.persist(customer);

        CosmeticService service = builderService();
        testEntityManager.persist(service);
        List<CosmeticService> serviceList = new ArrayList<>();
        serviceList.add(service);

        Reservation reservation = new Reservation(null, customer, serviceList, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        Reservation savedReservation = testEntityManager.persistFlushFind(reservation);

        Reservation foundReservation = reservationRepository.findById(savedReservation.getReservationId()).orElse(null);

        Assertions.assertNotNull(foundReservation, "Reservation should be found");
        assertEquals(savedReservation.getReservationId(), foundReservation.getReservationId(), "Reservation IDs should match");
    }

    @Test
    void whenSaveReservation_thenPersist() {
        Customer customer = builderCustomer();
        testEntityManager.persist(customer);
        CosmeticService service = builderService();
        testEntityManager.persist(service);
        List<CosmeticService> serviceList = new ArrayList<>();
        serviceList.add(service);
        Reservation newReservation = new Reservation(null, customer, serviceList, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());

        Reservation savedReservation = reservationRepository.save(newReservation);

        Assertions.assertNotNull(savedReservation.getReservationId(), "Reservation should be saved with an ID");
        assertEquals(newReservation.getCustomer().getName(), savedReservation.getCustomer().getName(), "Customer names should match");
    }

    @Test
    void whenDeleteReservation_thenRemove() {
        Customer customer = builderCustomer();
        testEntityManager.persist(customer);
        CosmeticService service = builderService();
        testEntityManager.persist(service);
        List<CosmeticService> serviceList = new ArrayList<>();
        serviceList.add(service);
        Reservation reservationToDelete = new Reservation(null, customer, serviceList, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        Reservation savedReservation = testEntityManager.persistFlushFind(reservationToDelete);

        reservationRepository.deleteById(savedReservation.getReservationId());

        Reservation deletedReservation = testEntityManager.find(Reservation.class, savedReservation.getReservationId());

        Assertions.assertNull(deletedReservation, "Reservation should be deleted");
    }
}
