package com.piotrba.beautyservices;

import com.piotrba.beautyservices.entity.CosmeticService;
import com.piotrba.beautyservices.entity.Customer;

public class UtilsData {

    public static Customer builderCustomer(){

        return Customer.builder()
                .name("Test User")
                .mobileNumber("1234567890")
                .email("test@example.com")
                .username("test user")
                .password("password")
                .role("USER")
                .active(true)
                .build();
    }

    public static CosmeticService builderService(){

        return CosmeticService.builder()
                .serviceName("Test Service")
                .price(100.0)
                .serviceDuration(60)
                .serviceDescription("Test Description")
                .build();
    }
}
