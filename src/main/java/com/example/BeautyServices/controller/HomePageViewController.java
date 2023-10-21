package com.example.BeautyServices.controller;

import com.example.BeautyServices.entity.Customer;
import com.example.BeautyServices.entity.Reservation;
import com.example.BeautyServices.repository.CustomerRepository;
import com.example.BeautyServices.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/homepage")
@AllArgsConstructor
public class HomePageViewController {
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;

    @GetMapping()
    public String showAppointments(Model model, Principal principal){
        String userName = principal.getName();
        Customer customer = customerRepository.getByUsername(userName);
        List<Reservation> reservation = reservationRepository.findByCustomerName(customer.getName());
        model.addAttribute("reservation", reservation);
        model.addAttribute("customer", customer);
        return "/homepage";
    }
}
