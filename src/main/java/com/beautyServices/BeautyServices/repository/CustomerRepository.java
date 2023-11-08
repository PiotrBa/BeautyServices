package com.beautyServices.BeautyServices.repository;

import com.beautyServices.BeautyServices.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer getByUsername(String username);
}
