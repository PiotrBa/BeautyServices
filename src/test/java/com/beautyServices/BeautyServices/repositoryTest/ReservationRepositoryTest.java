package com.beautyServices.BeautyServices.repositoryTest;

import com.beautyServices.BeautyServices.entity.CosmeticService;
import com.beautyServices.BeautyServices.entity.Customer;
import com.beautyServices.BeautyServices.entity.Reservation;
import com.beautyServices.BeautyServices.repository.ReservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class ReservationRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ReservationRepository reservationRepository;


    @Test
    @Transactional
    void whenFindByCustomer_thenReturnReservations(){
        Customer customer = new Customer(null, "Test User", "1234567890", "test@example.com", "test user", "password", "USER", true);
        testEntityManager.persist(customer);

        CosmeticService service = new CosmeticService(null, "Service Test", 120.0, 75, "Test Description");
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
    @Transactional
    void whenFindByCustomerName_thenReturnReservation(){
        Customer customer = new Customer(null, "Test User", "1234567890", "test@example.com", "test user", "password", "USER", true);
        testEntityManager.persist(customer);

        CosmeticService service = new CosmeticService(null, "Service Test", 120.0, 75, "Test Description");
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
    @Transactional
    void whenFindAllByServiceListContains_thenReturnsReservations(){
        CosmeticService cosmeticService = new CosmeticService(null, "Test service name", 00.0, 0, "Test");
        testEntityManager.persist(cosmeticService);

        Customer customer = new Customer(null, "Test User", "1234567890", "test@example.com", "test user2", "password", "USER", true);
        testEntityManager.persist(customer);

        List<CosmeticService> serviceList = new ArrayList<>();
        serviceList.add(cosmeticService);
        Reservation reservation = new Reservation(null, customer, serviceList, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        testEntityManager.persistAndFlush(reservation);

        List<Reservation> foundReservations = reservationRepository.findAllByserviceListContains(cosmeticService);

        Assertions.assertFalse(foundReservations.isEmpty(), "Reservations should be found");
        Assertions.assertTrue(foundReservations.stream()
                .anyMatch(r -> r.getServiceList().contains(cosmeticService)), "The reservation should contain the specified cosmetic service");
    }

    @Test
    @Transactional
    void whenFindAll_thenReturnAllReservations() {
        Customer customer1 = new Customer(null, "Test User 1", "1234567890", "test1@example.com", "test user1", "password1", "USER", true);
        testEntityManager.persist(customer1);
        CosmeticService service1 = new CosmeticService(null, "Service 1", 100.0, 60, "Description 1");
        testEntityManager.persist(service1);
        List<CosmeticService> serviceList1 = new ArrayList<>();
        serviceList1.add(service1);
        Reservation reservation1 = new Reservation(null, customer1, serviceList1, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        testEntityManager.persist(reservation1);

        Customer customer2 = new Customer(null, "Test User 2", "1234567890", "test2@example.com", "test user2", "password2", "USER", true);
        testEntityManager.persist(customer2);
        CosmeticService service2 = new CosmeticService(null, "Service 2", 200.0, 90, "Description 2");
        testEntityManager.persist(service2);
        List<CosmeticService> serviceList2 = new ArrayList<>();
        serviceList2.add(service2);
        Reservation reservation2 = new Reservation(null, customer2, serviceList2, LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(1));
        testEntityManager.persist(reservation2);

        List<Reservation> reservations = reservationRepository.findAll();

        Assertions.assertFalse(reservations.isEmpty(), "Should return all reservations");
    }


    @Test
    @Transactional
    void whenFindById_thenReturnReservation() {
        Customer customer = new Customer(null, "Test User", "1234567890", "test@example.com", "test user", "password", "USER", true);
        testEntityManager.persist(customer);

        CosmeticService service = new CosmeticService(null, "Service Test", 120.0, 75, "Test Description");
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
    @Transactional
    void whenSaveReservation_thenPersist() {
        Customer customer = new Customer(null, "New User", "1234567891", "new@example.com", "new user", "password", "USER", true);
        testEntityManager.persist(customer);
        CosmeticService service = new CosmeticService(null, "New Service", 200.0, 90, "New Description");
        testEntityManager.persist(service);
        List<CosmeticService> serviceList = new ArrayList<>();
        serviceList.add(service);
        Reservation newReservation = new Reservation(null, customer, serviceList, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());

        Reservation savedReservation = reservationRepository.save(newReservation);

        Assertions.assertNotNull(savedReservation.getReservationId(), "Reservation should be saved with an ID");
        assertEquals(newReservation.getCustomer().getName(), savedReservation.getCustomer().getName(), "Customer names should match");
    }

    @Test
    @Transactional
    void whenDeleteReservation_thenRemove() {
        Customer customer = new Customer(null, "User to Delete", "1234567892", "delete@example.com", "delete user", "password", "USER", true);
        testEntityManager.persist(customer);
        CosmeticService service = new CosmeticService(null, "Delete Service", 150.0, 60, "Delete Description");
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
