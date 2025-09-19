package com.jhealthconnect.backend.duplicate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhealthconnect.backend.model.Patient;
public interface PatientRepository extends JpaRepository<Patient, Long> {}
