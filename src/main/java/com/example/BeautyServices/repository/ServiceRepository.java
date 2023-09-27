package com.example.BeautyServices.repository;

import com.example.BeautyServices.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
