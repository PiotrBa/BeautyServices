package com.beautyServices.BeautyServices.controller;

import com.beautyServices.BeautyServices.entity.Customer;
import com.beautyServices.BeautyServices.entity.Reservation;
import com.beautyServices.BeautyServices.repository.CustomerRepository;
import com.beautyServices.BeautyServices.repository.ReservationRepository;
import com.beautyServices.BeautyServices.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/homepage")
@AllArgsConstructor
public class HomePageViewController {
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final ServiceRepository serviceRepository;

    private static final Logger logger = LoggerFactory.getLogger(HomePageViewController.class);

    @GetMapping()
    public String showAppointmentsView(Model model, Principal principal){
        logger.info("Request to fetch all reservations.");
        String userName = principal.getName();
        Customer customer = customerRepository.getByUsername(userName);
        List<Reservation> reservation = reservationRepository.findByCustomerName(customer.getName());
        model.addAttribute("reservation", reservation);
        model.addAttribute("customer", customer);
        return "/homepage/homepage";
    }


    @GetMapping("/add")
    public String addAppointmentsView(Model model, Principal principal){
        logger.info("Request to view page for adding a new reservation.");
        String userName = principal.getName();
        Customer customer = customerRepository.getByUsername(userName);
        model.addAttribute("reservations", new Reservation());
        model.addAttribute("customer", customer);
        model.addAttribute("serviceList", serviceRepository.findAll());
        return "/homepage/homepage-add";
    }

    @PostMapping("/add")
    public String addAppointments(Reservation reservation, Principal principal) {
        logger.info("Request to add a new reservation.");
        String userName = principal.getName();
        Customer customer = customerRepository.getByUsername(userName);
        LocalDateTime dateTimeNow = LocalDateTime.now();
        reservation.setCustomer(customer);
        reservation.setCreateReservation(dateTimeNow);
        reservationRepository.save(reservation);
        logger.info("Reservation added successfully!!!");
        return "redirect:/homepage";
    }

    @GetMapping("/edit")
    public String editAppointmentsView(@RequestParam Long id, Model model) {
        logger.info("Request to view page for editing reservation with id: {}", id);
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
            model.addAttribute("reservations", reservationOptional.get());
            model.addAttribute("customer", reservationOptional.get().getCustomer());
            model.addAttribute("serviceList", serviceRepository.findAll());
        }else {
            logger.info("Reservation with id: {} not found", id);
        }
        return "/homepage/homepage-edit";
    }

    @PostMapping("/edit")
    public String editAppointments(Reservation reservation, @RequestParam Long id) {
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
        return "redirect:/homepage";
    }

    @GetMapping("/delete")
    public String deleteAppointmentsView(@RequestParam Long id, Model model) {
        logger.info("Request to view page for deleting reservation with id: {}", id);
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()){
            model.addAttribute("reservation", reservationOptional.get());
        }else {
            logger.error("Reservation with id: {} not found for deletion", id);
        }
        return "/homepage/homepage-delete";
    }

    @PostMapping("/delete")
    public String deleteAppointments(@RequestParam Long id) {
        logger.info("Request to delete reservation with id: {}", id);
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if(reservationOptional.isPresent()){
            Reservation reservation = reservationOptional.get();
            reservationRepository.delete(reservation);
            logger.info("Reservation with id: {} deleted successfully", id);
        }else {
            logger.error("Failed to delete reservation with id: {} as it does not exist", id);
        }
        return "redirect:/homepage";
    }
}
