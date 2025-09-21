package com.jhealthconnect.service;

import com.jhealthconnect.entity.Doctor;
import com.jhealthconnect.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
    
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }
    
    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }
    
    public List<Doctor> findAvailableDoctors() {
        return doctorRepository.findByAvailableTrue();
    }
    
    public List<Doctor> searchDoctors(String searchTerm) {
        return doctorRepository.findByNameContainingIgnoreCaseOrSpecializationContainingIgnoreCase(searchTerm, searchTerm);
    }
    
    public List<Doctor> findBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }
    
    public List<Doctor> findByLocation(String location) {
        return doctorRepository.findByLocation(location);
    }
    
    public List<String> getAllSpecializations() {
        return doctorRepository.findDistinctSpecializations();
    }
    
    public List<String> getAllLocations() {
        return doctorRepository.findDistinctLocations();
    }
    
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
    
    public long getAvailableDoctorCount() {
        return doctorRepository.countByAvailableTrue();
    }
    
    public long getVerifiedDoctorCount() {
        return doctorRepository.countByVerifiedTrue();
    }
    
    public Double getAverageRating() {
        return doctorRepository.getAverageRating();
    }
}