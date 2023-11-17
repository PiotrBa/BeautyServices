package com.piotrba.beautyservices.repository;

import com.piotrba.beautyservices.entity.Customer;
import com.piotrba.beautyservices.entity.Reservation;
import com.piotrba.beautyservices.entity.CosmeticService;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation>findByCustomerName(String customer);
    List<Reservation> findAllByserviceListContains(CosmeticService cosmeticService);
    List<Reservation> findByCustomer(Customer customer);

}
