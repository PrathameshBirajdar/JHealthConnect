package com.jhealthconnect.backend.controller;

import com.jhealthconnect.backend.model.Doctor;
import com.jhealthconnect.backend.repository.DoctorRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = {"http://localhost:5500", "http://localhost:3000", "http://127.0.0.1:3000"}) // allow frontend dev servers
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
