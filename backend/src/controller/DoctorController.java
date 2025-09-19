package com.jhealthconnect.backend.duplicate;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhealthconnect.backend.model.Doctor;
import com.jhealthconnect.backend.repository.DoctorRepository;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "http://localhost:5500") // allow frontend (Live Server)
public class DoctorController {
    private final DoctorRepository repo;

    public DoctorController(DoctorRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return repo.findAll();
    }
}
