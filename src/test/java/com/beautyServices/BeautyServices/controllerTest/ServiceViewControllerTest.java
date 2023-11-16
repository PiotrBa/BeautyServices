package com.beautyServices.BeautyServices.controllerTest;

import com.beautyServices.BeautyServices.controller.ServiceViewController;
import com.beautyServices.BeautyServices.entity.CosmeticService;
import com.beautyServices.BeautyServices.repository.ServiceRepository;
import com.beautyServices.BeautyServices.service.ServiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ServiceViewControllerTest {

    @Mock
    private ServiceRepository serviceRepository;
    @Mock
    private ServiceService serviceService;
    @Mock
    private Model model;

    private ServiceViewController serviceViewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        serviceViewController = new ServiceViewController(serviceRepository, serviceService);
    }

    @Test
    void testGetListView() {
        String viewName = serviceViewController.getListView(model);
        assertEquals("/service/service-list", viewName);
        verify(model).addAttribute(eq("services"), any());
    }

    @Test
    void testAddServiceView() {
        String viewName = serviceViewController.addServiceView(model);
        assertEquals("/service/service-add", viewName);
        verify(model).addAttribute(eq("services"), any(CosmeticService.class));
    }

    @Test
    void testAddService() {
        CosmeticService cosmeticService = new CosmeticService();
        String viewName = serviceViewController.addService(cosmeticService);
        assertEquals("redirect:/services", viewName);
        verify(serviceRepository).save(cosmeticService);
    }

    @Test
    void testEditServiceView() {
        Long id = 1L;
        when(serviceRepository.findById(id)).thenReturn(Optional.of(new CosmeticService()));

        String viewName = serviceViewController.editServiceView(model, id);
        assertEquals("/service/service-edit", viewName);
        verify(model).addAttribute(eq("services"), any());
    }

    @Test
    void testEditService() {
        Long id = 1L;
        CosmeticService cosmeticService = new CosmeticService();
        when(serviceRepository.findById(id)).thenReturn(Optional.of(new CosmeticService()));

        String viewName = serviceViewController.editService(id, cosmeticService);
        assertEquals("redirect:/services", viewName);
        verify(serviceRepository).save(any(CosmeticService.class));
    }

    @Test
    void testDeleteServiceView() {
        Long id = 1L;
        when(serviceRepository.findById(id)).thenReturn(Optional.of(new CosmeticService()));

        String viewName = serviceViewController.deleteServiceView(model, id);
        assertEquals("/service/service-delete", viewName);
        verify(model).addAttribute(eq("services"), any(CosmeticService.class));
    }

    @Test
    void testDeleteService() {
        Long id = 1L;
        CosmeticService cosmeticService = new CosmeticService();
        when(serviceRepository.findById(id)).thenReturn(Optional.of(cosmeticService));

        String viewName = serviceViewController.deleteService(id);
        assertEquals("redirect:/services", viewName);
        verify(serviceService).removeService(cosmeticService);
    }
}
