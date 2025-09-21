package com.jhealthconnect.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user; // patient

    @ManyToOne
    private Doctor doctor;

    private LocalDateTime appointmentDate;
    private String status; // PENDING, CONFIRMED, COMPLETED, CANCELLED
}
