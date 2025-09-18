package com.jhealthconnect.controller;

import com.jhealthconnect.model.Patient;
import com.jhealthconnect.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {
    private final PatientRepository repo;
    public PatientController(PatientRepository repo) { this.repo = repo; }

    @GetMapping public List<Patient> all() { return repo.findAll(); }
    @PostMapping public Patient create(@RequestBody Patient p){ return repo.save(p); }
}
