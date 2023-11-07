package com.example.BeautyServices.controller;


import com.example.BeautyServices.entity.Reservation;
import com.example.BeautyServices.repository.CustomerRepository;
import com.example.BeautyServices.repository.ReservationRepository;
import com.example.BeautyServices.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;


@Controller
@RequestMapping("/reservations")
@AllArgsConstructor
public class ReservationViewController {
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final ServiceRepository serviceRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReservationViewController.class);


    @GetMapping()
    public String getListView(Model model) {
        logger.info("Request to fetch all reservations.");
        model.addAttribute("reservations", reservationRepository.findAll());
        model.addAttribute("services", serviceRepository.findAll());
        return "/reservation/reservation-list";
    }

    @GetMapping("/add")
    public String addReservationView(Model model) {
        logger.info("Request to view page for adding a new reservation.");
        model.addAttribute("reservations", new Reservation());
        model.addAttribute("customer", customerRepository.findAll());
        model.addAttribute("serviceList", serviceRepository.findAll());
        return "/reservation/reservation-add";
    }

    @PostMapping("/add")
    public String addReservation(Reservation reservation) {
        logger.info("Request to add a new reservation.");
        LocalDateTime dateTimeNow = LocalDateTime.now();
        reservation.setCreateReservation(dateTimeNow);
        reservationRepository.save(reservation);
        logger.info("Reservation added successfully!!!");
        return "redirect:/reservations";
    }

    @GetMapping("/edit")
    public String editReservationView(@RequestParam Long id, Model model) {
        logger.info("Request to view page for editing reservation with id: {}", id);
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
            model.addAttribute("reservations", reservationOptional.get());
            model.addAttribute("customer", reservationOptional.get().getCustomer());
            model.addAttribute("serviceList", serviceRepository.findAll());
        }else {
            logger.info("Reservation with id: {} not found", id);
        }
        return "/reservation/reservation-edit";
    }

    @PostMapping("/edit")
    public String editReservation(Reservation reservation, @RequestParam Long id) {
        logger.info("Request to edit reservation with id: {}", id);
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
            LocalDateTime dateTimeNow = LocalDateTime.now();
            Reservation newReservation = reservationOptional.get();
            newReservation.setServiceList(reservation.getServiceList());
            newReservation.setAppointment(reservation.getAppointment());
            newReservation.setUpdateReservation(dateTimeNow);
            reservationRepository.save(newReservation);
            logger.info("Reservation with id: {} updated successfully", id);

        }else {
            logger.error("Failed to update reservation with id: {} as it does not exist", id);
        }
        return "redirect:/reservations";
    }

    @GetMapping("/delete")
    public String deleteReservationView(@RequestParam Long id, Model model) {
        logger.info("Request to view page for deleting reservation with id: {}", id);
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()){
            model.addAttribute("reservation", reservationOptional.get());
        }else {
            logger.error("Reservation with id: {} not found for deletion", id);
        }
        return "/reservation/reservation-delete";
    }

    @PostMapping("/delete")
    public String deleteReservation(@RequestParam Long id) {
        logger.info("Request to delete reservation with id: {}", id);
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if(reservationOptional.isPresent()){
            Reservation reservation = reservationOptional.get();
            reservationRepository.delete(reservation);
            logger.info("Reservation with id: {} deleted successfully", id);
        }else {
            logger.error("Failed to delete reservation with id: {} as it does not exist", id);
        }
        return "redirect:/reservations";
    }
}
