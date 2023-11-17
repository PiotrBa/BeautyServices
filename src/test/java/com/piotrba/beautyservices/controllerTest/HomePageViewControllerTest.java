package com.piotrba.beautyservices.controllerTest;

import com.piotrba.beautyservices.controller.HomePageViewController;
import com.piotrba.beautyservices.entity.Customer;
import com.piotrba.beautyservices.entity.Reservation;
import com.piotrba.beautyservices.repository.CustomerRepository;
import com.piotrba.beautyservices.repository.ReservationRepository;
import com.piotrba.beautyservices.repository.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import java.security.Principal;
import java.util.Collections;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.*;

class HomePageViewControllerTest {

    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ServiceRepository serviceRepository;
    @Mock
    private Model model;
    @Mock
    private Principal principal;
    @InjectMocks
    private HomePageViewController homePageViewController;
    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testShowAppointmentsView() {
        String userName = "testUser";
        Customer customer = new Customer();
        customer.setUsername(userName);
        when(principal.getName()).thenReturn(userName);
        when(customerRepository.getByUsername(userName)).thenReturn(customer);
        when(reservationRepository.findByCustomer(customer)).thenReturn(Collections.emptyList());

        String viewName = homePageViewController.showAppointmentsView(model, principal);

        assertEquals("/homepage/homepage", viewName);
        verify(model).addAttribute("reservation", Collections.emptyList());
        verify(model).addAttribute("customer", customer);
    }

    @Test
    void testAddAppointmentsView() {
        String userName = "testUser";
        Customer customer = new Customer();
        customer.setUsername(userName);
        when(principal.getName()).thenReturn(userName);
        when(customerRepository.getByUsername(userName)).thenReturn(customer);
        when(serviceRepository.findAll()).thenReturn(Collections.emptyList());

        String viewName = homePageViewController.addAppointmentsView(model, principal);

        assertEquals("/homepage/homepage-add", viewName);
        verify(model).addAttribute("customer", customer);
        verify(model).addAttribute("reservations", new Reservation());
        verify(model).addAttribute("serviceList", Collections.emptyList());
    }

    @Test
    void testEditAppointmentsView() {
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        String viewName = homePageViewController.editAppointmentsView(reservationId, model);

        assertEquals("/homepage/homepage-edit", viewName);
        verify(model).addAttribute("reservations", reservation);
    }

    @Test
    void testDeleteAppointmentsView() {
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        String viewName = homePageViewController.deleteAppointmentsView(reservationId, model);

        assertEquals("/homepage/homepage-delete", viewName);
        verify(model).addAttribute("reservation", reservation);
    }
}

