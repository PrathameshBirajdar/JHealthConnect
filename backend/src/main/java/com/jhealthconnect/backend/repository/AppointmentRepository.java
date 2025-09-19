package com.jhealthconnect.backend.repository;

import com.jhealthconnect.backend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {}
