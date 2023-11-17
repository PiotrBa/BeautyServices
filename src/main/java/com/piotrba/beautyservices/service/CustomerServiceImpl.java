package com.piotrba.beautyservices.service;

import com.piotrba.beautyservices.entity.Customer;
import com.piotrba.beautyservices.entity.Reservation;
import com.piotrba.beautyservices.repository.CustomerRepository;
import com.piotrba.beautyservices.repository.ReservationRepository;
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