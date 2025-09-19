package com.jhealthconnect.backend.duplicate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhealthconnect.backend.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {}
