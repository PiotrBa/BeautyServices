package com.beautyServices.BeautyServices.repository;

import com.beautyServices.BeautyServices.entity.Customer;
import com.beautyServices.BeautyServices.entity.Reservation;
import com.beautyServices.BeautyServices.entity.CosmeticService;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation>findByCustomerName(String customer);
    List<Reservation> findAllByserviceListContains(CosmeticService cosmeticService);
    List<Reservation> findByCustomer(Customer customer);

}
