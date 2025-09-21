package com.jhealthconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhealthconnect.entity.InsuranceCompany;

public interface InsuranceRepository extends JpaRepository<InsuranceCompany, Long> {
}
