package com.jhealthconnect.repository;

import com.jhealthconnect.model.InsuranceClaim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<InsuranceClaim, Long> {}
