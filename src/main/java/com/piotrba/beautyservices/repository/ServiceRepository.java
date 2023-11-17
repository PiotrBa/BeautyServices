package com.piotrba.beautyservices.repository;

import com.piotrba.beautyservices.entity.CosmeticService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<CosmeticService, Long> {
}
