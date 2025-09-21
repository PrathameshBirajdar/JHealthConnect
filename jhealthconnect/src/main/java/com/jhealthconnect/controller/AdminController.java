package com.jhealthconnect.controller;

import com.jhealthconnect.service.UserService;
import com.jhealthconnect.service.DoctorService;
import com.jhealthconnect.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private AppointmentService appointmentService;
    
    @GetMapping("/dashboard/stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        try {
            Map<String, Object> stats = new HashMap<>();
            stats.put("totalUsers", userService.getActiveUserCount());
            stats.put("totalDoctors", doctorService.getAvailableDoctorCount());
            stats.put("totalAppointments", appointmentService.getTodayAppointmentCount());
            stats.put("monthlyRevenue", appointmentService.getMonthlyRevenue());
            stats.put("newUsersThisMonth", userService.getNewUsersThisMonth());
            stats.put("averageDoctorRating", doctorService.getAverageRating());
            
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/users/stats")
    public ResponseEntity<Map<String, Object>> getUserStats() {
        try {
            Map<String, Object> stats = new HashMap<>();
            stats.put("activeUsers", userService.getActiveUserCount());
            stats.put("newUsersThisMonth", userService.getNewUsersThisMonth());
            
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/doctors/stats")
    public ResponseEntity<Map<String, Object>> getDoctorStats() {
        try {
            Map<String, Object> stats = new HashMap<>();
            stats.put("availableDoctors", doctorService.getAvailableDoctorCount());
            stats.put("verifiedDoctors", doctorService.getVerifiedDoctorCount());
            stats.put("averageRating", doctorService.getAverageRating());
            
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}