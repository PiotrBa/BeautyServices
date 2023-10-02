package com.example.BeautyServices.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "reservation_service", joinColumns = @JoinColumn(name = "reservation_id"), inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service> serviceList = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime appointment;


    public String getDateTimeReservationFormatted(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd MM yyyy");
        String format = formatter.format(createReservation);
        return format;
    }


    @Column(updatable = false)
    private LocalDateTime createReservation;
    private LocalDateTime updateReservation;
}
