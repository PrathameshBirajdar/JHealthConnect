package com.jhealthconnect.backend.repository;

import com.jhealthconnect.backend.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {}
