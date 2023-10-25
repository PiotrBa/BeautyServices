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
    public String getCreateAppointmentFormatted(){
        if (appointment == null){
            return "Not updated";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm | dd/MM/yyyy");
        return formatter.format(appointment);
    }

    @Column(updatable = false)
    private LocalDateTime createReservation;
    //I was getting nullPointerException, so I had to do this.
    public String getCreateReservationFormatted(){
        if(createReservation == null) {
            return "Not available";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm | dd/MM/yyyy");
        return formatter.format(createReservation);
    }

    private LocalDateTime updateReservation;
    //I was getting nullPointerException, so I had to do this.
    public String getUpdateReservationFormatted(){
        if(updateReservation == null) {
            return "Not updated";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm | dd/MM/yyyy");
        return formatter.format(updateReservation);
    }
}
