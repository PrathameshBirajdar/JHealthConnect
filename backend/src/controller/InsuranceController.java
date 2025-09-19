package com.jhealthconnect.backend.duplicate;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhealthconnect.backend.model.InsuranceClaim;
import com.jhealthconnect.backend.repository.InsuranceRepository;

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
