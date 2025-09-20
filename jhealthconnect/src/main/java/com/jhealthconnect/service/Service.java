package com.jhealthconnect.service;

import com.jhealthconnect.entity.Doctor;
import com.jhealthconnect.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getTopDoctors(int limit) {
        return doctorRepository.findTopByOrderByRatingDescReviewCountDesc(limit);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> getAvailableDoctors() {
        return doctorRepository.findByAvailableTrue();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public List<Doctor> searchDoctors(String search) {
        return doctorRepository.findByNameContainingIgnoreCaseOrSpecializationContainingIgnoreCase(search, search);
    }

    public List<Doctor> filterDoctors(String specialization, String location) {
        if (specialization != null && location != null) {
            return doctorRepository.findBySpecializationAndLocation(specialization, location);
        } else if (specialization != null) {
            return doctorRepository.findBySpecialization(specialization);
        } else if (location != null) {
            return doctorRepository.findByLocation(location);
        }
        return getAllDoctors();
    }

    public List<String> getAllSpecializations() {
        return doctorRepository.findDistinctSpecializations();
    }

    public List<String> getAllLocations() {
        return doctorRepository.findDistinctLocations();
    }

    public Doctor addDoctor(Doctor doctor) {
        doctor.setCreatedAt(LocalDateTime.now());
        doctor.setUpdatedAt(LocalDateTime.now());
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Doctor doctor) {
        doctor.setUpdatedAt(LocalDateTime.now());
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public long getTotalDoctorsCount() {
        return doctorRepository.count();
    }

    public Map<String, Object> getDoctorStatistics() {
        return Map.of(
            "totalDoctors", doctorRepository.count(),
            "availableDoctors", doctorRepository.countByAvailableTrue(),
            "verifiedDoctors", doctorRepository.countByVerifiedTrue(),
            "averageRating", doctorRepository.getAverageRating()
        );
    }

    public List<String> getTopSpecializations() {
        return doctorRepository.findTopSpecializations();
    }
}

// UserService
package com.jhealthconnect.service;

import com.jhealthconnect.entity.User;
import com.jhealthconnect.entity.UserType;
import com.jhealthconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User authenticate(String email, String password, String userType) {
        User user = userRepository.findByEmailAndUserType(email, UserType.valueOf(userType));
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> searchUsers(String search) {
        return userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(search, search);
    }

    public List<User> getUsersByType(String userType) {
        return userRepository.findByUserType(UserType.valueOf(userType));
    }

    public List<User> getRecentUsers(int limit) {
        return userRepository.findTopByOrderByCreatedAtDesc(limit);
    }

    public void toggleUserStatus(Long id) {
        User user = getUserById(id);
        if (user != null) {
            user.setIsActive(!user.getIsActive());
            userRepository.save(user);
        }
    }

    public long getTotalUsersCount() {
        return userRepository.count();
    }

    public List<String> getAllUserTypes() {
        return List.of("USER", "ADMIN", "DOCTOR");
    }

    public Map<String, Object> getUserGrowthStats() {
        return Map.of(
            "totalUsers", userRepository.count(),
            "activeUsers", userRepository.countByIsActiveTrue(),
            "newUsersThisMonth", userRepository.countNewUsersThisMonth(),
            "usersByType", userRepository.getUserCountByType()
        );
    }
}

// AppointmentService
package com.jhealthconnect.service;

import com.jhealthconnect.entity.Appointment;
import com.jhealthconnect.entity.AppointmentStatus;
import com.jhealthconnect.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getPendingAppointmentsByUser(Long userId) {
        return appointmentRepository.findByUserIdAndStatus(userId, AppointmentStatus.PENDING);
    }

    public List<Appointment> getAppointmentsByUser(Long userId) {
        return appointmentRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public Appointment bookAppointment(Appointment appointment) {
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setUpdatedAt(LocalDateTime.now());
        appointment.setStatus(AppointmentStatus.PENDING);
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Appointment appointment) {
        appointment.setUpdatedAt(LocalDateTime.now());
        return appointmentRepository.save(appointment);
    }

    public void updateAppointmentStatus(Long id, String status) {
        Appointment appointment = getAppointmentById(id);
        if (appointment != null) {
            appointment.setStatus(AppointmentStatus.valueOf(status));
            appointment.setUpdatedAt(LocalDateTime.now());
            appointmentRepository.save(appointment);
        }
    }

    public void cancelAppointment(Long id) {
        updateAppointmentStatus(id, "CANCELLED");
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getRecentAppointments(int limit) {
        return appointmentRepository.findTopByOrderByCreatedAtDesc(limit);
    }

    public List<Appointment> filterAppointments(String status, String date) {
        if (status != null && date != null) {
            return appointmentRepository.findByStatusAndDate(AppointmentStatus.valueOf(status), date);
        } else if (status != null) {
            return appointmentRepository.findByStatus(AppointmentStatus.valueOf(status));
        } else if (date != null) {
            return appointmentRepository.findByDate(date);
        }
        return getAllAppointments();
    }

    public List<String> getAllStatuses() {
        return List.of("PENDING", "CONFIRMED", "COMPLETED", "CANCELLED", "RESCHEDULED");
    }

    public long getTotalAppointmentsCount() {
        return appointmentRepository.count();
    }

    public long getPendingAppointmentsCount() {
        return appointmentRepository.countByStatus(AppointmentStatus.PENDING);
    }

    public long getTodayAppointmentsCount() {
        return appointmentRepository.countTodayAppointments();
    }

    public Double getMonthlyRevenue() {
        return appointmentRepository.getMonthlyRevenue();
    }

    public Map<String, Object> getMonthlyAppointmentStats() {
        return appointmentRepository.getMonthlyStats();
    }

    public Map<String, Object> getRevenueAnalytics() {
        return appointmentRepository.getRevenueAnalytics();
    }

    public Map<String, Long> getStatusDistribution() {
        return appointmentRepository.getStatusDistribution();
    }
}

// InsuranceService
package com.jhealthconnect.service;

import com.jhealthconnect.entity.InsuranceCompany;
import com.jhealthconnect.entity.InsuranceClaim;
import com.jhealthconnect.entity.ClaimStatus;
import com.jhealthconnect.repository.InsuranceCompanyRepository;
import com.jhealthconnect.repository.InsuranceClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceCompanyRepository insuranceCompanyRepository;

    @Autowired
    private InsuranceClaimRepository claimRepository;

    public List<InsuranceCompany> getTopInsuranceCompanies(int limit) {
        return insuranceCompanyRepository.findTopByOrderByRatingDescCoverageDesc(limit);
    }

    public List<InsuranceCompany> getAllInsuranceCompanies() {
        return insuranceCompanyRepository.findByActiveTrue();
    }

    public InsuranceCompany getInsuranceById(Long id) {
        return insuranceCompanyRepository.findById(id).orElse(null);
    }

    public InsuranceCompany addInsuranceCompany(InsuranceCompany company) {
        company.setCreatedAt(LocalDateTime.now());
        return insuranceCompanyRepository.save(company);
    }

    public InsuranceCompany updateInsuranceCompany(InsuranceCompany company) {
        return insuranceCompanyRepository.save(company);
    }

    public List<InsuranceClaim> getAllClaims() {
        return claimRepository.findAll();
    }

    public List<InsuranceClaim> getClaimsByStatus(String status) {
        return claimRepository.findByStatus(ClaimStatus.valueOf(status));
    }

    public InsuranceClaim submitClaim(InsuranceClaim claim) {
        claim.setClaimNumber("CLM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        claim.setSubmittedDate(LocalDateTime.now());
        claim.setStatus(ClaimStatus.SUBMITTED);
        return claimRepository.save(claim);
    }

    public void updateClaimStatus(Long id, String status, String remarks) {
        InsuranceClaim claim = claimRepository.findById(id).orElse(null);
        if (claim != null) {
            claim.setStatus(ClaimStatus.valueOf(status));
            claim.setRemarks(remarks);
            if ("APPROVED".equals(status) || "REJECTED".equals(status)) {
                claim.setProcessedDate(LocalDateTime.now());
            }
            claimRepository.save(claim);
        }
    }

    public List<String> getAllClaimStatuses() {
        return List.of("SUBMITTED", "UNDER_REVIEW", "APPROVED", "REJECTED", "PROCESSED");
    }

    public long getTotalInsuranceCompaniesCount() {
        return insuranceCompanyRepository.count();
    }

    public long getPendingClaimsCount() {
        return claimRepository.countByStatusIn(List.of(ClaimStatus.SUBMITTED, ClaimStatus.UNDER_REVIEW));
    }
}