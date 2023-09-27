package com.example.BeautyServices.controller;

import com.example.BeautyServices.entity.Customer;
import com.example.BeautyServices.entity.Reservation;

import com.example.BeautyServices.entity.Service;
import com.example.BeautyServices.repository.CustomerRepository;
import com.example.BeautyServices.repository.ReservationRepository;
import com.example.BeautyServices.repository.ServiceRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/reservations")
@AllArgsConstructor
public class ReservationViewController {


    private final ReservationRepository reservationRepository;
    private final ServiceRepository serviceRepository;


    @GetMapping()
    public String getListView(Model model) {
        model.addAttribute("reservations", reservationRepository.findAll());
        return "/reservation/reservation-list";
    }

    @GetMapping("/add")
    public String addReservationView(Model model){
        model.addAttribute("reservations", new Reservation());
        model.addAttribute("serviceList", serviceRepository.findAll());
        return "/reservation/reservation-add";
    }

    @PostMapping("/add")
    public String addReservation(@Valid Reservation reservation){
        reservationRepository.save(reservation);
        return "redirect:/reservations";
    }

    @GetMapping("/edit")
    public String editReservationView(@RequestParam Long id, Model model){
        model.addAttribute("reservation", reservationRepository.findById(id));
        model.addAttribute("service", serviceRepository.findById(id));
        return "/reservation/reservation-edit";
    }

    @PostMapping("/edit")
    public String editReservation(@Valid Reservation reservation, @RequestParam Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
            Reservation newReservation = reservationOptional.get();
            newReservation.setCustomer(reservation.getCustomer());
            newReservation.setServiceList(reservation.getServiceList());
            newReservation.setAppointment(reservation.getAppointment());
            newReservation.setUpdateReservation(reservation.getUpdateReservation());
            reservationRepository.save(newReservation);
        }
        return "redirect:/reservations";
    }

    @GetMapping("/delete")
    public String deleteReservationView(@RequestParam Long id, Model model){
        model.addAttribute("reservation", reservationRepository.findById(id));
        return "/reservation/reservation-delete";
    }

    @PostMapping("/delete")
    public String deleteReservation(@RequestParam Long id){
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()){
            reservationRepository.delete(reservationOptional.get());
        }
        return "redirect:/reservations";
    }

}
