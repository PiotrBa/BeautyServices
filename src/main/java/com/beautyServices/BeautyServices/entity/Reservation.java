package com.beautyServices.BeautyServices.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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
    private List<CosmeticService> serviceList = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime appointment;
    public String getCreateAppointmentFormatted(){
        if (appointment == null){
            return "Not updated";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm | dd/MM/yyyy");
        return formatter.format(appointment);
    }

    @Column(updatable = false)
    private LocalDateTime createReservation;
    public String getCreateReservationFormatted(){
        if(createReservation == null) {
            return "Not available";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm | dd/MM/yyyy");
        return formatter.format(createReservation);
    }

    private LocalDateTime updateReservation;
    public String getUpdateReservationFormatted(){
        if(updateReservation == null) {
            return "Not updated";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm | dd/MM/yyyy");
        return formatter.format(updateReservation);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for(CosmeticService cosmeticService : serviceList) {
            totalPrice += cosmeticService.getPrice();
        }
        return totalPrice;
    }

    public int getTotalDuration() {
        int totalDuration = 0;
        for(CosmeticService cosmeticService : serviceList) {
            totalDuration += cosmeticService.getServiceDuration();
        }
        return totalDuration;
    }

    public String getTotalPriceAndDuration() {
        return getTotalPrice() + "£ / " + getTotalDuration() + "min";
    }

}
