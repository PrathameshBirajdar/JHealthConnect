package com.jhealthconnect.service;

import com.jhealthconnect.entity.Doctor;
import com.jhealthconnect.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public long countDoctors() {
        return doctorRepository.count();
    }

    public List<Doctor> getTopDoctors(int limit) {
        List<Doctor> all = doctorRepository.findAll();
        return all.stream().limit(limit).toList(); // simple fallback
    }
}
