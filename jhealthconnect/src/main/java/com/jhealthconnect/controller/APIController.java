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

// UserRepository
package com.jhealthconnect.repository;

import com.jhealthconnect.entity.User;
import com.jhealthconnect.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndUserType(String email, UserType userType);

    List<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);

    List<User> findByUserType(UserType userType);

    @Query("SELECT u FROM User u ORDER BY u.createdAt DESC")
    List<User> findTopByOrderByCreatedAtDesc(@Param("limit") int limit);

    long countByIsActiveTrue();

    @Query("SELECT COUNT(u) FROM User u WHERE YEAR(u.createdAt) = YEAR(CURRENT_DATE) AND MONTH(u.createdAt) = MONTH(CURRENT_DATE)")
    long countNewUsersThisMonth();

    @Query("SELECT u.userType, COUNT(u) FROM User u GROUP BY u.userType")
    Map<String, Long> getUserCountByType();
}

// AppointmentRepository
package com.jhealthconnect.repository;

import com.jhealthconnect.entity.Appointment;
import com.jhealthconnect.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByUserIdAndStatus(Long userId, AppointmentStatus status);

    List<Appointment> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<Appointment> findByStatus(AppointmentStatus status);

    List<Appointment> findByDate(String date);

    List<Appointment> findByStatusAndDate(AppointmentStatus status, String date);

    @Query("SELECT a FROM Appointment a ORDER BY a.createdAt DESC")
    List<Appointment> findTopByOrderByCreatedAtDesc(@Param("limit") int limit);

    long countByStatus(AppointmentStatus status);

    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.date = CURRENT_DATE")
    long countTodayAppointments();

    @Query("SELECT SUM(d.consultationFee) FROM Appointment a JOIN a.doctor d WHERE YEAR(a.createdAt) = YEAR(CURRENT_DATE) AND MONTH(a.createdAt) = MONTH(CURRENT_DATE) AND a.status = 'COMPLETED'")
    Double getMonthlyRevenue();

    @Query("SELECT new map(MONTH(a.createdAt) as month, COUNT(a) as count) FROM Appointment a WHERE YEAR(a.createdAt) = YEAR(CURRENT_DATE) GROUP BY MONTH(a.createdAt)")
    Map<String, Object> getMonthlyStats();

    @Query("SELECT new map(MONTH(a.createdAt) as month, SUM(d.consultationFee) as revenue) FROM Appointment a JOIN a.doctor d WHERE YEAR(a.createdAt) = YEAR(CURRENT_DATE) AND a.status = 'COMPLETED' GROUP BY MONTH(a.createdAt)")
    Map<String, Object> getRevenueAnalytics();

    @Query("SELECT a.status, COUNT(a) FROM Appointment a GROUP BY a.status")
    Map<String, Long> getStatusDistribution();
}

// InsuranceCompanyRepository
package com.jhealthconnect.repository;

import com.jhealthconnect.entity.InsuranceCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceCompanyRepository extends JpaRepository<InsuranceCompany, Long> {

    @Query("SELECT i FROM InsuranceCompany i WHERE i.active = true ORDER BY i.rating DESC, i.coverage DESC")
    List<InsuranceCompany> findTopByOrderByRatingDescCoverageDesc(@Param("limit") int limit);

    List<InsuranceCompany> findByActiveTrue();

    List<InsuranceCompany> findByNameContainingIgnoreCase(String name);

    List<InsuranceCompany> findByType(String type);
}

// InsuranceClaimRepository
package com.jhealthconnect.repository;

import com.jhealthconnect.entity.InsuranceClaim;
import com.jhealthconnect.entity.ClaimStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceClaimRepository extends JpaRepository<InsuranceClaim, Long> {

    List<InsuranceClaim> findByStatus(ClaimStatus status);

    List<InsuranceClaim> findByUserId(Long userId);

    List<InsuranceClaim> findByInsuranceCompanyId(Long insuranceCompanyId);

    long countByStatusIn(List<ClaimStatus> statuses);

    List<InsuranceClaim> findByUserIdOrderBySubmittedDateDesc(Long userId);
}