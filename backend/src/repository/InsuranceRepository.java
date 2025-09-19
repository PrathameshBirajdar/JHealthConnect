package com.jhealthconnect.backend.duplicate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhealthconnect.backend.model.InsuranceClaim;

public interface InsuranceRepository extends JpaRepository<InsuranceClaim, Long> {}
