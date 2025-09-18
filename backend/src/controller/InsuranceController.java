package com.jhealthconnect.controller;

import com.jhealthconnect.model.InsuranceClaim;
import com.jhealthconnect.repository.InsuranceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurance")
@CrossOrigin(origins = "http://localhost:5500")
public class InsuranceController {
    private final InsuranceRepository repo;

    public InsuranceController(InsuranceRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<InsuranceClaim> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public InsuranceClaim submit(@RequestBody InsuranceClaim claim) {
        claim.setClaimStatus("Pending");
        return repo.save(claim);
    }
}
