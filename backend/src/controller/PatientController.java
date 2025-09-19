package com.jhealthconnect.backend.duplicate;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhealthconnect.backend.model.Patient;
import com.jhealthconnect.backend.repository.PatientRepository;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {
    private final PatientRepository repo;
    public PatientController(PatientRepository repo) { this.repo = repo; }

    @GetMapping public List<Patient> all() { return repo.findAll(); }
    @PostMapping public Patient create(@RequestBody Patient p){ return repo.save(p); }
}
