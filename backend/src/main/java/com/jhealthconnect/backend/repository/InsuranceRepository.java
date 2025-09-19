package com.jhealthconnect.backend.repository;

import com.jhealthconnect.backend.model.InsuranceClaim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<InsuranceClaim, Long> {}
