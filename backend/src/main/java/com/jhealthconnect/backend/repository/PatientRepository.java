package com.jhealthconnect.backend.repository;

import com.jhealthconnect.backend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {}
