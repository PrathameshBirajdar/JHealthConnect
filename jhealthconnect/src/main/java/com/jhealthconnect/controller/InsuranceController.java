package com.jhealthconnect.controller;

import com.jhealthconnect.entity.InsuranceCompany;
import com.jhealthconnect.entity.InsuranceClaim;
import com.jhealthconnect.entity.ClaimStatus;
import com.jhealthconnect.service.InsuranceCompanyService;
import com.jhealthconnect.service.InsuranceClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/insurance")
@CrossOrigin(origins = "*")
public class InsuranceController {
    
    @Autowired
    private InsuranceCompanyService insuranceCompanyService;
    
    @Autowired
    private InsuranceClaimService insuranceClaimService;
    
    // Insurance Company endpoints
    @PostMapping("/companies")
    public ResponseEntity<InsuranceCompany> addInsuranceCompany(@RequestBody InsuranceCompany company) {
        try {
            InsuranceCompany savedCompany = insuranceCompanyService.saveInsuranceCompany(company);
            return ResponseEntity.ok(savedCompany);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/companies")
    public ResponseEntity<List<InsuranceCompany>> getAllInsuranceCompanies() {
        List<InsuranceCompany> companies = insuranceCompanyService.findAllInsuranceCompanies();
        return ResponseEntity.ok(companies);
    }
    
    @GetMapping("/companies/active")
    public ResponseEntity<List<InsuranceCompany>> getActiveInsuranceCompanies() {
        List<InsuranceCompany> companies = insuranceCompanyService.findActiveInsuranceCompanies();
        return ResponseEntity.ok(companies);
    }
    
    @GetMapping("/companies/{id}")
    public ResponseEntity<InsuranceCompany> getInsuranceCompanyById(@PathVariable Long id) {
        Optional<InsuranceCompany> company = insuranceCompanyService.findById(id);
        return company.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    // Insurance Claim endpoints
    @PostMapping("/claims")
    public ResponseEntity<InsuranceClaim> submitClaim(@RequestBody InsuranceClaim claim) {
        try {
            InsuranceClaim savedClaim = insuranceClaimService.saveInsuranceClaim(claim);
            return ResponseEntity.ok(savedClaim);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/claims")
    public ResponseEntity<List<InsuranceClaim>> getAllClaims() {
        List<InsuranceClaim> claims = insuranceClaimService.findAllInsuranceClaims();
        return ResponseEntity.ok(claims);
    }
    
    @GetMapping("/claims/user/{userId}")
    public ResponseEntity<List<InsuranceClaim>> getUserClaims(@PathVariable Long userId) {
        List<InsuranceClaim> claims = insuranceClaimService.findByUserId(userId);
        return ResponseEntity.ok(claims);
    }
    
    @GetMapping("/claims/status/{status}")
    public ResponseEntity<List<InsuranceClaim>> getClaimsByStatus(@PathVariable ClaimStatus status) {
        List<InsuranceClaim> claims = insuranceClaimService.findByStatus(status);
        return ResponseEntity.ok(claims);
    }
    
    @PutMapping("/claims/{id}")
    public ResponseEntity<InsuranceClaim> updateClaim(@PathVariable Long id, @RequestBody InsuranceClaim claim) {
        try {
            claim.setId(id);
            InsuranceClaim updatedClaim = insuranceClaimService.saveInsuranceClaim(claim);
            return ResponseEntity.ok(updatedClaim);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
