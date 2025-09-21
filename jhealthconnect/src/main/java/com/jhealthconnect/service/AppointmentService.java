package com.jhealthconnect.service;

import com.jhealthconnect.entity.Appointment;
import com.jhealthconnect.entity.AppointmentStatus;
import com.jhealthconnect.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
    
    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }
    
    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }
    
    public List<Appointment> findByUserId(Long userId) {
        return appointmentRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
    
    public List<Appointment> findByStatus(AppointmentStatus status) {
        return appointmentRepository.findByStatus(status);
    }
    
    public List<Appointment> findByDate(String date) {
        return appointmentRepository.findByDate(date);
    }
    
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
    
    public long getAppointmentCountByStatus(AppointmentStatus status) {
        return appointmentRepository.countByStatus(status);
    }
    
    public long getTodayAppointmentCount() {
        return appointmentRepository.countTodayAppointments();
    }
    
    public Double getMonthlyRevenue() {
        return appointmentRepository.getMonthlyRevenue();
    }
}