package com.example.BeautyServices.repository;

import com.example.BeautyServices.entity.CosmeticService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<CosmeticService, Long> {
}
