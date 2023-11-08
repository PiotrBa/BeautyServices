package com.beautyServices.BeautyServices.repository;

import com.beautyServices.BeautyServices.entity.CosmeticService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<CosmeticService, Long> {
}
