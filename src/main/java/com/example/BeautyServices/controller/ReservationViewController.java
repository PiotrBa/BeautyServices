package com.example.BeautyServices.controller;


import com.example.BeautyServices.entity.Reservation;
import com.example.BeautyServices.repository.CustomerRepository;
import com.example.BeautyServices.repository.ReservationRepository;
import com.example.BeautyServices.repository.ServiceRepository;
import lombok.AllArgsConstructor;
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

    @GetMapping()
    public String getListView(Model model) {
        model.addAttribute("reservations", reservationRepository.findAll());
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("services", serviceRepository.findAll());
        return "/reservation/reservation-list";
    }

    @GetMapping("/add")
    public String addReservationView(Model model) {
        model.addAttribute("reservations", new Reservation());
        model.addAttribute("customer", customerRepository.findAll());
        model.addAttribute("serviceList", serviceRepository.findAll());
        return "/reservation/reservation-add";
    }

    @PostMapping("/add")
    public String addReservation(Reservation reservation) {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        reservation.setCreateReservation(dateTimeNow);
        reservationRepository.save(reservation);
        return "redirect:/reservations";
    }

    @GetMapping("/edit")
    public String editReservationView(@RequestParam Long id, Model model) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
            model.addAttribute("reservations", reservationOptional.get());
            model.addAttribute("customer", reservationOptional.get().getCustomer());
        }
        model.addAttribute("serviceList", serviceRepository.findAll());
        return "/reservation/reservation-edit";
    }

    @PostMapping("/edit")
    public String editReservation(Reservation reservation, @RequestParam Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
            LocalDateTime dateTimeNow = LocalDateTime.now();

            Reservation newReservation = reservationOptional.get();
            newReservation.setServiceList(reservation.getServiceList());
            newReservation.setAppointment(reservation.getAppointment());
            newReservation.setUpdateReservation(dateTimeNow);
            reservationRepository.save(newReservation);
        }
        return "redirect:/reservations";
    }

    @GetMapping("/delete")
    public String deleteReservationView(@RequestParam Long id, Model model) {
        model.addAttribute("reservation", reservationRepository.findById(id).get());
        return "/reservation/reservation-delete";
    }

    @PostMapping("/delete")
    public String deleteReservation(@RequestParam Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        reservationOptional.ifPresent(reservationRepository::delete);
        return "redirect:/reservations";
    }
}
