package com.example.BeautyServices.repository;

import com.example.BeautyServices.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
