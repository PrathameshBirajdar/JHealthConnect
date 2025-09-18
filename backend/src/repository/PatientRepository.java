package com.jhealthconnect.repository;
import com.jhealthconnect.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PatientRepository extends JpaRepository<Patient, Long> {}
