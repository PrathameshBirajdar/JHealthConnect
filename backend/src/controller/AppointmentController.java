package com.jhealthconnect.controller;

import com.jhealthconnect.model.Appointment;
import com.jhealthconnect.repository.AppointmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:5500")
public class AppointmentController {
    private final AppointmentRepository repo;

    public AppointmentController(AppointmentRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Appointment> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Appointment book(@RequestBody Appointment a) {
        return repo.save(a);
    }
}
