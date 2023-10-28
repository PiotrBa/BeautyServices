package com.example.BeautyServices.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CosmeticService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;
    private String serviceName;
    private double price;
    private int serviceDuration;
    private String serviceDescription;
}

