package com.jhealthconnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jhealthconnect.entity.InsuranceCompany;
import com.jhealthconnect.repository.InsuranceRepository;

@Service
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;

    public InsuranceService(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    public List<InsuranceCompany> getAllCompanies() {
        return insuranceRepository.findAll();
    }

    public List<InsuranceCompany> getTopCompanies(int n) {
        return insuranceRepository.findAll(
                PageRequest.of(0, n, Sort.by(Sort.Direction.DESC, "policyCount"))
        ).getContent();
    }

    public Optional<InsuranceCompany> findById(Long id) {
        return insuranceRepository.findById(id);
    }

    public InsuranceCompany saveCompany(InsuranceCompany company) {
        return insuranceRepository.save(company);
    }

    public void deleteCompany(Long id) {
        insuranceRepository.deleteById(id);
    }

    public long countCompanies() {
        return insuranceRepository.count();
    }

    public List<InsuranceCompany> getTopInsurance(int limit) {
    return insuranceRepository.findAll().stream()
            .limit(limit)
            .toList();
    }
}
