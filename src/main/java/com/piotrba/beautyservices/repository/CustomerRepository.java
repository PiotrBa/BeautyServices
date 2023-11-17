package com.piotrba.beautyservices.repository;

import com.piotrba.beautyservices.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer getByUsername(String username);
}
