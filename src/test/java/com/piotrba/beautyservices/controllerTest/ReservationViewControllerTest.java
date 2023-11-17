package com.piotrba.beautyservices.controllerTest;

import com.piotrba.beautyservices.controller.ReservationViewController;
import com.piotrba.beautyservices.entity.Reservation;
import com.piotrba.beautyservices.repository.CustomerRepository;
import com.piotrba.beautyservices.repository.ReservationRepository;
import com.piotrba.beautyservices.repository.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReservationViewControllerTest {

    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ServiceRepository serviceRepository;
    @Mock
    private Model model;

    private ReservationViewController reservationViewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reservationViewController = new ReservationViewController(reservationRepository, customerRepository, serviceRepository);
    }

    @Test
    void testGetListView() {
        String viewName = reservationViewController.getListView(model);
        assertEquals("/reservation/reservation-list", viewName);
        verify(model).addAttribute(eq("reservations"), any());
        verify(model).addAttribute(eq("services"), any());
    }

    @Test
    void testAddReservationView() {
        String viewName = reservationViewController.addReservationView(model);
        assertEquals("/reservation/reservation-add", viewName);
        verify(model).addAttribute(eq("reservations"), any(Reservation.class));
        verify(model).addAttribute(eq("customer"), any());
        verify(model).addAttribute(eq("serviceList"), any());
    }

    @Test
    void testAddReservation() {
        Reservation reservation = new Reservation();
        String viewName = reservationViewController.addReservation(reservation);
        assertEquals("redirect:/reservations", viewName);
        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    void testEditReservationView() {
        Long id = 1L;
        when(reservationRepository.findById(id)).thenReturn(Optional.of(new Reservation()));

        String viewName = reservationViewController.editReservationView(id, model);
        assertEquals("/reservation/reservation-edit", viewName);
        verify(model).addAttribute(eq("reservations"), any(Reservation.class));
        verify(model).addAttribute(eq("customer"), any());
        verify(model).addAttribute(eq("serviceList"), any());
    }

    @Test
    void testEditReservation() {
        Long id = 1L;
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(id)).thenReturn(Optional.of(new Reservation()));

        String viewName = reservationViewController.editReservation(reservation, id);
        assertEquals("redirect:/reservations", viewName);
        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    void testDeleteReservationView() {
        Long id = 1L;
        when(reservationRepository.findById(id)).thenReturn(Optional.of(new Reservation()));

        String viewName = reservationViewController.deleteReservationView(id, model);
        assertEquals("/reservation/reservation-delete", viewName);
        verify(model).addAttribute(eq("reservation"), any(Reservation.class));
    }

    @Test
    void testDeleteReservation() {
        Long id = 1L;
        when(reservationRepository.findById(id)).thenReturn(Optional.of(new Reservation()));

        String viewName = reservationViewController.deleteReservation(id);
        assertEquals("redirect:/reservations", viewName);
        verify(reservationRepository).delete(any(Reservation.class));
    }
}