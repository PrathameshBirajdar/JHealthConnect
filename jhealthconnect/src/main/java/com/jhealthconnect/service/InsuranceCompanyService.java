package com.jhealthconnect.service;

import com.jhealthconnect.entity.InsuranceCompany;
import com.jhealthconnect.repository.InsuranceCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceCompanyService {
    
    @Autowired
    private InsuranceCompanyRepository insuranceCompanyRepository;
    
    public InsuranceCompany saveInsuranceCompany(InsuranceCompany company) {
        return insuranceCompanyRepository.save(company);
    }
    
    public Optional<InsuranceCompany> findById(Long id) {
        return insuranceCompanyRepository.findById(id);
    }
    
    public List<InsuranceCompany> findAllInsuranceCompanies() {
        return insuranceCompanyRepository.findAll();
    }
    
    public List<InsuranceCompany> findActiveInsuranceCompanies() {
        return insuranceCompanyRepository.findByActiveTrue();
    }
    
    public List<InsuranceCompany> searchInsuranceCompanies(String name) {
        return insuranceCompanyRepository.findByNameContainingIgnoreCase(name);
    }
    
    public List<InsuranceCompany> findByType(String type) {
        return insuranceCompanyRepository.findByType(type);
    }
    
    public void deleteInsuranceCompany(Long id) {
        insuranceCompanyRepository.deleteById(id);
    }
}