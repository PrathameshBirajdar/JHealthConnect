package com.jhealthconnect.repository;

import com.jhealthconnect.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT d FROM Doctor d WHERE d.available = true ORDER BY d.rating DESC, d.reviewCount DESC")
    List<Doctor> findTopByOrderByRatingDescReviewCountDesc(@Param("limit") int limit);

    List<Doctor> findByAvailableTrue();

    List<Doctor> findByNameContainingIgnoreCaseOrSpecializationContainingIgnoreCase(String name, String specialization);

    List<Doctor> findBySpecializationAndLocation(String specialization, String location);

    List<Doctor> findBySpecialization(String specialization);

    List<Doctor> findByLocation(String location);

    @Query("SELECT DISTINCT d.specialization FROM Doctor d")
    List<String> findDistinctSpecializations();

    @Query("SELECT DISTINCT d.location FROM Doctor d")
    List<String> findDistinctLocations();

    long countByAvailableTrue();

    long countByVerifiedTrue();

    @Query("SELECT AVG(d.rating) FROM Doctor d")
    Double getAverageRating();

    @Query("SELECT d.specialization FROM Doctor d GROUP BY d.specialization ORDER BY COUNT(d) DESC")
    List<String> findTopSpecializations();
}