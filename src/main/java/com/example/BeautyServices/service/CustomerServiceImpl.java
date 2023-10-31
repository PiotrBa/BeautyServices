package com.example.BeautyServices.service;

import com.example.BeautyServices.entity.Customer;
import com.example.BeautyServices.entity.Reservation;
import com.example.BeautyServices.repository.CustomerRepository;
import com.example.BeautyServices.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<Reservation> reservations = reservationRepository.findByCustomer(customer);
        for (Reservation reservation : reservations) {
            reservationRepository.delete(reservation);
        }
        customerRepository.delete(customer);
    }
}