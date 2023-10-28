package com.example.BeautyServices.service;

import com.example.BeautyServices.entity.Reservation;
import com.example.BeautyServices.entity.CosmeticService;
import com.example.BeautyServices.repository.ReservationRepository;
import com.example.BeautyServices.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ReservationRepository reservationRepository;

//    Fix foreign key constraint issue during service deletion

    @Transactional
    @Override
    public boolean removeService(CosmeticService cosmeticService) {
        if (cosmeticService == null) {
            throw new IllegalArgumentException("CosmeticService cannot be null");
        }

        boolean exists = serviceRepository.existsById(cosmeticService.getServiceId());
        if (!exists) {
            throw new IllegalArgumentException(String.format("CosmeticService with %d does not exist", cosmeticService.getServiceId()));
        }

        List<Reservation> relatedReservations = reservationRepository.findAllBycosmeticServiceListContains(cosmeticService);
        for (Reservation reservation : relatedReservations) {
            List<CosmeticService> servicesInReservation = reservation.getCosmeticServiceList();
            for (int i = 0; i < servicesInReservation.size(); i++) {
                if (servicesInReservation.get(i).getServiceId().equals(cosmeticService.getServiceId())) {
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

        serviceRepository.delete(cosmeticService);
        return true;
    }
}
