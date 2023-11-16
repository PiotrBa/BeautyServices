package com.beautyServices.BeautyServices;

import com.beautyServices.BeautyServices.entity.CosmeticService;
import com.beautyServices.BeautyServices.entity.Customer;

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
