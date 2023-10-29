package com.example.BeautyServices.controller;

import com.example.BeautyServices.entity.Customer;
import com.example.BeautyServices.entity.Reservation;
import com.example.BeautyServices.repository.CustomerRepository;
import com.example.BeautyServices.repository.ReservationRepository;
import com.example.BeautyServices.repository.ServiceRepository;
import lombok.AllArgsConstructor;
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

    @GetMapping()
    public String showAppointmentsView(Model model, Principal principal){
        String userName = principal.getName();
        Customer customer = customerRepository.getByUsername(userName);
        List<Reservation> reservation = reservationRepository.findByCustomerName(customer.getName());
        model.addAttribute("reservation", reservation);
        model.addAttribute("customer", customer);
        return "/homepage/homepage";
    }


    @GetMapping("/add")
    public String addAppointmentsView(Model model, Principal principal){
        String userName = principal.getName();
        Customer customer = customerRepository.getByUsername(userName);
        model.addAttribute("reservations", new Reservation());
        model.addAttribute("customer", customer);
        model.addAttribute("serviceList", serviceRepository.findAll());
        return "/homepage/homepage-add";
    }

    @PostMapping("/add")
    public String addAppointments(Reservation reservation, Principal principal) {
        String userName = principal.getName();
        Customer customer = customerRepository.getByUsername(userName);
        LocalDateTime dateTimeNow = LocalDateTime.now();
        reservation.setCustomer(customer);
        reservation.setCreateReservation(dateTimeNow);
        reservationRepository.save(reservation);
        return "redirect:/homepage";
    }

    @GetMapping("/edit")
    public String editAppointmentsView(@RequestParam Long id, Model model) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
            model.addAttribute("reservations", reservationOptional.get());
            model.addAttribute("customer", reservationOptional.get().getCustomer());
            model.addAttribute("serviceList", serviceRepository.findAll());
        }
        return "/homepage/homepage-edit";
    }

    @PostMapping("/edit")
    public String editAppointments(Reservation reservation, @RequestParam Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
            LocalDateTime dateTimeNow = LocalDateTime.now();
            Reservation newReservation = reservationOptional.get();
            newReservation.setServiceList(reservation.getServiceList());
            newReservation.setAppointment(reservation.getAppointment());
            newReservation.setUpdateReservation(dateTimeNow);
            reservationRepository.save(newReservation);
        }
        return "redirect:/homepage";
    }

    @GetMapping("/delete")
    public String deleteAppointmentsView(@RequestParam Long id, Model model) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()){
            model.addAttribute("reservation", reservationOptional.get());
        }

        return "/homepage/homepage-delete";
    }

    @PostMapping("/delete")
    public String deleteAppointments(@RequestParam Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if(reservationOptional.isPresent()){
            Reservation reservation = reservationOptional.get();
            reservationRepository.delete(reservation);
        }
        return "redirect:/homepage";
    }
}
