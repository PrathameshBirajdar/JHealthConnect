package com.jhealthconnect.backend.duplicate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhealthconnect.backend.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {}
