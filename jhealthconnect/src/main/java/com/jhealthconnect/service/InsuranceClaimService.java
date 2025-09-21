package com.jhealthconnect.service;

import com.jhealthconnect.entity.InsuranceClaim;
import com.jhealthconnect.entity.ClaimStatus;
import com.jhealthconnect.repository.InsuranceClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceClaimService {
    
    @Autowired
    private InsuranceClaimRepository insuranceClaimRepository;
    
    public InsuranceClaim saveInsuranceClaim(InsuranceClaim claim) {
        return insuranceClaimRepository.save(claim);
    }
    
    public Optional<InsuranceClaim> findById(Long id) {
        return insuranceClaimRepository.findById(id);
    }
    
    public List<InsuranceClaim> findAllInsuranceClaims() {
        return insuranceClaimRepository.findAll();
    }
    
    public List<InsuranceClaim> findByStatus(ClaimStatus status) {
        return insuranceClaimRepository.findByStatus(status);
    }
    
    public List<InsuranceClaim> findByUserId(Long userId) {
        return insuranceClaimRepository.findByUserIdOrderBySubmittedDateDesc(userId);
    }
    
    public List<InsuranceClaim> findByInsuranceCompanyId(Long companyId) {
        return insuranceClaimRepository.findByInsuranceCompanyId(companyId);
    }
    
    public void deleteInsuranceClaim(Long id) {
        insuranceClaimRepository.deleteById(id);
    }
}