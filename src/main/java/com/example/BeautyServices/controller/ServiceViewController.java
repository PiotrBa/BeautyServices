package com.example.BeautyServices.controller;

import com.example.BeautyServices.entity.Service;
import com.example.BeautyServices.repository.ServiceRepository;
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

    @GetMapping()
    public String getListView(Model model) {
        model.addAttribute("services", serviceRepository.findAll());
        return "/service/service-list";
    }

    @GetMapping("/add")
    public String addServiceView(Model model){
        model.addAttribute("services", new Service());
        return "/service/service-add";
    }

    @PostMapping("/add")
    public String addService(Service service){
        serviceRepository.save(service);
        return "redirect:/services";
    }

    @GetMapping("/edit")
    public String editServiceView(Model model, @RequestParam Long id){
        model.addAttribute("services", serviceRepository.findById(id));
        return "/service/service-edit";
    }

    @PostMapping("edit")
    public String editService(@RequestParam Long id, Service service){
        Optional<Service> serviceOptional = serviceRepository.findById(id);
        if (serviceOptional.isPresent()) {
            Service newService = serviceOptional.get();
            newService.setServiceName(service.getServiceName());
            newService.setPrice(service.getPrice());
            newService.setServiceDuration(service.getServiceDuration());
            newService.setServiceDescription(service.getServiceDescription());
            serviceRepository.save(newService);
        }
        return "redirect:/services";
    }

    @GetMapping("/delete")
    public String deleteServiceView(Model model, @RequestParam Long id){
        model.addAttribute("services", serviceRepository.findById(id));
        return "/service/service-delete";
    }

    @PostMapping("/delete")
    public String deleteService(@RequestParam Long id){
        Optional<Service> serviceOptional = serviceRepository.findById(id);
        if(serviceOptional.isPresent()){
            Service service = serviceOptional.get();
            serviceRepository.delete(service);
        }
        return "redirect:/services";
    }
}

