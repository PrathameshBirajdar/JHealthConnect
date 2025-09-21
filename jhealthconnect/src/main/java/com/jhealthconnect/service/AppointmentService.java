package com.jhealthconnect.service;

import com.jhealthconnect.entity.Appointment;
import com.jhealthconnect.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getPendingAppointments() {
        return appointmentRepository.findByStatus("PENDING");
    }

    public long countPendingAppointments() {
        return appointmentRepository.countByStatus("PENDING");
    }
}
