package com.jhealthconnect.repository;
import com.jhealthconnect.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {}
