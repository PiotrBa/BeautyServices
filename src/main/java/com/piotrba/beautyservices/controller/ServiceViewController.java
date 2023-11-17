package com.piotrba.beautyservices.controller;

import com.piotrba.beautyservices.entity.CosmeticService;
import com.piotrba.beautyservices.repository.ServiceRepository;
import com.piotrba.beautyservices.service.ServiceService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
@RequestMapping("/services")
@AllArgsConstructor
public class ServiceViewController {
    private final ServiceRepository serviceRepository;
    private final ServiceService serviceService;

    private static final Logger logger = LoggerFactory.getLogger(ServiceViewController.class);

    @GetMapping()
    public String getListView(Model model) {
        logger.info("Request to fetch all services.");
        model.addAttribute("services", serviceRepository.findAll());
        return "/service/service-list";
    }

    @GetMapping("/add")
    public String addServiceView(Model model){
        logger.info("Request to view page for adding a new service.");
        model.addAttribute("services", new CosmeticService());
        return "/service/service-add";
    }

    @PostMapping("/add")
    public String addService(CosmeticService cosmeticService){
        logger.info("Request to add a new service.");
        serviceRepository.save(cosmeticService);
        logger.info("Service added successfully!!!");
        return "redirect:/services";
    }

    @GetMapping("/edit")
    public String editServiceView(Model model, @RequestParam Long id){
        logger.info("Request to view page for editing service with id: {}", id);
        Optional<CosmeticService> serviceOptional = serviceRepository.findById(id);
        if (serviceOptional.isPresent()) {
            model.addAttribute("services", serviceRepository.findById(id));
        }else {
            logger.info("Service with id: {} not found", id);
        }
        return "/service/service-edit";
    }

    @PostMapping("edit")
    public String editService(@RequestParam Long id, CosmeticService cosmeticService){
        logger.info("Request to edit service with id: {}", id);
        Optional<CosmeticService> serviceOptional = serviceRepository.findById(id);
        if (serviceOptional.isPresent()) {
            CosmeticService newCosmeticService = serviceOptional.get();
            newCosmeticService.setServiceName(cosmeticService.getServiceName());
            newCosmeticService.setPrice(cosmeticService.getPrice());
            newCosmeticService.setServiceDuration(cosmeticService.getServiceDuration());
            newCosmeticService.setServiceDescription(cosmeticService.getServiceDescription());
            serviceRepository.save(newCosmeticService);
            logger.info("Service with id: {} updated successfully", id);
        }else {
            logger.error("Failed to update service with id: {} as it does not exist", id);
        }
        return "redirect:/services";
    }

    @GetMapping("/delete")
    public String deleteServiceView(Model model, @RequestParam Long id){
        logger.info("Request to view page for deleting service with id: {}", id);
        Optional<CosmeticService> serviceOptional = serviceRepository.findById(id);
        if(serviceOptional.isPresent()) {
            model.addAttribute("services", serviceOptional.get());
        }else {
            logger.error("Service with id: {} not found for deletion", id);
        }
        return "/service/service-delete";
    }

    @PostMapping("/delete")
    public String deleteService(@RequestParam Long id){
        logger.info("Request to delete service with id: {}", id);
        Optional<CosmeticService> serviceOptional = serviceRepository.findById(id);
        if(serviceOptional.isPresent()){
            CosmeticService cosmeticService = serviceOptional.get();
            serviceService.removeService(cosmeticService);
            logger.info("Service with id: {} deleted successfully", id);
        }else {
            logger.error("Failed to delete service with id: {} as it does not exist", id);
        }
        return "redirect:/services";
    }
}

