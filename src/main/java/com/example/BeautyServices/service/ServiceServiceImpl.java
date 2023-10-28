package com.example.BeautyServices.service;

import com.example.BeautyServices.entity.Reservation;
import com.example.BeautyServices.entity.Service;
import com.example.BeautyServices.repository.ReservationRepository;
import com.example.BeautyServices.repository.ServiceRepository;
import lombok.AllArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ReservationRepository reservationRepository;

//    Fix foreign key constraint issue during service deletion

    @Transactional
    @Override
    public boolean removeService(Service service) {
        if (service == null) {
            throw new IllegalArgumentException("Service cannot be null");
        }

        boolean exists = serviceRepository.existsById(service.getServiceId());
        if (!exists) {
            throw new IllegalArgumentException(String.format("Service with %d does not exist", service.getServiceId()));
        }

        List<Reservation> relatedReservations = reservationRepository.findAllByServiceListContains(service);
        for (Reservation reservation : relatedReservations) {
            List<Service> servicesInReservation = reservation.getServiceList();
            for (int i = 0; i < servicesInReservation.size(); i++) {
                if (servicesInReservation.get(i).getServiceId().equals(service.getServiceId())) {
                    servicesInReservation.remove(i);
                    i--;
                }
            }

            if (servicesInReservation.isEmpty()) {
                reservationRepository.delete(reservation);
            } else {
                reservationRepository.save(reservation);
            }
        }

        serviceRepository.delete(service);
        return true;
    }
}
