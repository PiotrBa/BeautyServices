package com.example.BeautyServices.controller;

import com.example.BeautyServices.entity.CosmeticService;
import com.example.BeautyServices.repository.ServiceRepository;
import com.example.BeautyServices.service.ServiceService;
import lombok.AllArgsConstructor;
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

    @GetMapping()
    public String getListView(Model model) {
        model.addAttribute("services", serviceRepository.findAll());
        return "/service/service-list";
    }

    @GetMapping("/add")
    public String addServiceView(Model model){
        model.addAttribute("services", new CosmeticService());
        return "/service/service-add";
    }

    @PostMapping("/add")
    public String addService(CosmeticService cosmeticService){
        serviceRepository.save(cosmeticService);
        return "redirect:/services";
    }

    @GetMapping("/edit")
    public String editServiceView(Model model, @RequestParam Long id){
        model.addAttribute("services", serviceRepository.findById(id));
        return "/service/service-edit";
    }

    @PostMapping("edit")
    public String editService(@RequestParam Long id, CosmeticService cosmeticService){
        Optional<CosmeticService> serviceOptional = serviceRepository.findById(id);
        if (serviceOptional.isPresent()) {
            CosmeticService newCosmeticService = serviceOptional.get();
            newCosmeticService.setServiceName(cosmeticService.getServiceName());
            newCosmeticService.setPrice(cosmeticService.getPrice());
            newCosmeticService.setServiceDuration(cosmeticService.getServiceDuration());
            newCosmeticService.setServiceDescription(cosmeticService.getServiceDescription());
            serviceRepository.save(newCosmeticService);
        }
        return "redirect:/services";
    }

    @GetMapping("/delete")
    public String deleteServiceView(Model model, @RequestParam Long id){
        Optional<CosmeticService> serviceOptional = serviceRepository.findById(id);
        if(serviceOptional.isPresent()) {
            model.addAttribute("services", serviceOptional.get());
        }
        return "/service/service-delete";
    }

    @PostMapping("/delete")
    public String deleteService(@RequestParam Long id){
        Optional<CosmeticService> serviceOptional = serviceRepository.findById(id);
        if(serviceOptional.isPresent()){
            CosmeticService cosmeticService = serviceOptional.get();
            serviceService.removeService(cosmeticService);
        }
        return "redirect:/services";
    }
}

