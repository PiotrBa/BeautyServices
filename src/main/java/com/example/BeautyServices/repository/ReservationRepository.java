package com.example.BeautyServices.repository;

import com.example.BeautyServices.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation>findByCustomerName(String customer);
}
