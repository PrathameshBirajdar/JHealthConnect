// DoctorRepository.java
package com.jhealthconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jhealthconnect.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByStatus(String status);
    List<Doctor> findBySpecialization(String specialization);
}