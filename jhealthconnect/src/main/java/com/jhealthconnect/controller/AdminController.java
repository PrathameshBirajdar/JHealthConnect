package com.jhealthconnect.controller;

import com.jhealthconnect.entity.*;
import com.jhealthconnect.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private InsuranceService insuranceService;

    // Check admin access for all admin routes
    private boolean checkAdminAccess(HttpSession session) {
        return session.getAttribute("userId") != null && 
               "ADMIN".equals(session.getAttribute("userType"));
    }

    @GetMapping("/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        if (!checkAdminAccess(session)) {
            return "redirect:/login";
        }
        
        try {
            // Dashboard statistics
            Map<String, Object> stats = Map.of(
                "totalDoctors", doctorService.getTotalDoctorsCount(),
                "totalUsers", userService.getTotalUsersCount(),
                "totalAppointments", appointmentService.getTotalAppointmentsCount(),
                "pendingAppointments", appointmentService.getPendingAppointmentsCount(),
                "todayAppointments", appointmentService.getTodayAppointmentsCount(),
                "totalInsuranceCompanies", insuranceService.getTotalInsuranceCompaniesCount(),
                "pendingClaims", insuranceService.getPendingClaimsCount(),
                "monthlyRevenue", appointmentService.getMonthlyRevenue()
            );
            
            model.addAttribute("stats", stats);
            
            // Recent activities
            List<Appointment> recentAppointments = appointmentService.getRecentAppointments(10);
            List<User> recentUsers = userService.getRecentUsers(10);
            
            model.addAttribute("recentAppointments", recentAppointments);
            model.addAttribute("recentUsers", recentUsers);
            model.addAttribute("currentPage", "admin-dashboard");
            model.addAttribute("pageTitle", "Admin Dashboard");
            
            return "admin/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading admin dashboard: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/doctors")
    public String manageDoctors(HttpSession session, Model model,
                               @RequestParam(required = false) String search) {
        if (!checkAdminAccess(session)) {
            return "redirect:/login";
        }
        
        try {
            List<Doctor> doctors;
            if (search != null && !search.trim().isEmpty()) {
                doctors = doctorService.searchDoctors(search);
            } else {
                doctors = doctorService.getAllDoctors();
            }
            
            model.addAttribute("doctors", doctors);
            model.addAttribute("currentPage", "admin-doctors");
            model.addAttribute("pageTitle", "Manage Doctors");
            
            return "admin/doctors";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading doctors: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/doctors/add")
    public String addDoctorForm(HttpSession session, Model model) {
        if (!checkAdminAccess(session)) {
            return "redirect:/login";
        }
        
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("specializations", doctorService.getAllSpecializations());
        model.addAttribute("currentPage", "admin-doctors");
        model.addAttribute("pageTitle", "Add Doctor");
        
        return "admin/doctor_form";
    }

    @GetMapping("/doctors/edit/{id}")
    public String editDoctorForm(@PathVariable Long id, HttpSession session, Model model) {
        if (!checkAdminAccess(session)) {
            return "redirect:/login";
        }
        
        try {
            Doctor doctor = doctorService.getDoctorById(id);
            if (doctor == null) {
                return "redirect:/admin/doctors?error=Doctor not found";
            }
            
            model.addAttribute("doctor", doctor);
            model.addAttribute("specializations", doctorService.getAllSpecializations());
            model.addAttribute("currentPage", "admin-doctors");
            model.addAttribute("pageTitle", "Edit Doctor");
            
            return "admin/doctor_form";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading doctor: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/doctors/save")
    public String saveDoctor(@ModelAttribute Doctor doctor, 
                           HttpSession session, 
                           RedirectAttributes redirectAttributes) {
        if (!checkAdminAccess(session)) {
            return "redirect:/login";
        }
        
        try {
            if (doctor.getId() == null) {
                doctorService.addDoctor(doctor);
                redirectAttributes.addFlashAttribute("success", "Doctor added successfully!");
            } else {
                doctorService.updateDoctor(doctor);
                redirectAttributes.addFlashAttribute("success", "Doctor updated successfully!");
            }
            
            return "redirect:/admin/doctors";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error saving doctor: " + e.getMessage());
            return "redirect:/admin/doctors";
        }
    }

    @PostMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable Long id, 
                             HttpSession session, 
                             RedirectAttributes redirectAttributes) {
        if (!checkAdminAccess(session)) {
            return "redirect:/login";
        }
        
        try {
            doctorService.deleteDoctor(id);
            redirectAttributes.addFlashAttribute("success", "Doctor deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting doctor: " + e.getMessage());
        }
        
        return "redirect:/admin/doctors";
    }

    @GetMapping("/appointments")
    public String manageAppointments(HttpSession session, Model model,
                                   @RequestParam(required = false) String status,
                                   @RequestParam(required = false) String date) {
        if (!checkAdminAccess(session)) {
            return "redirect:/login";
        }
        
        try {
            List<Appointment> appointments;
            if (status != null || date != null) {
                appointments = appointmentService.filterAppointments(status, date);
            } else {
                appointments = appointmentService.getAllAppointments();
            }
            
            model.addAttribute("appointments", appointments);
            model.addAttribute("appointmentStatuses", appointmentService.getAllStatuses());
            model.addAttribute("currentPage", "admin-appointments");
            model.addAttribute("pageTitle", "Manage Appointments");
            
            return "admin/appointments";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading appointments: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/appointments/update-status/{id}")
    public String updateAppointmentStatus(@PathVariable Long id,
                                        @RequestParam String status,
                                        HttpSession session,
                                        RedirectAttributes redirectAttributes) {
        if (!checkAdminAccess(session)) {
            return "redirect:/login";
        }
        
        try {
            appointmentService.updateAppointmentStatus(id, status);
            redirectAttributes.addFlashAttribute("success", "Appointment status updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating appointment: " + e.getMessage());
        }
        
        return "redirect:/admin/appointments";
    }

    @GetMapping("/users")
    public String manageUsers(HttpSession session, Model model,
                            @RequestParam(required = false) String search,
                            @RequestParam(required = false) String userType) {
        if (!checkAdminAccess(session)) {
            return "redirect:/login";
        }
        
        try {
            List<User> users;
            if (search != null && !search.trim().isEmpty()) {
                users = userService.searchUsers(search);
            } else if (userType != null) {
                users = userService.getUsersByType(userType);
            } else {
                users = userService.getAllUsers();
            }
            
            model.addAttribute("users", users);
            model.addAttribute("userTypes", userService.getAllUserTypes());
            model.addAttribute("currentPage", "admin-users");
            model.addAttribute("pageTitle", "Manage Users");
            
            return "admin/users";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading users: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/users/toggle-status/{id}")
    public String toggleUserStatus(@PathVariable Long id,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        if (!checkAdminAccess(session)) {
            return "redirect:/login";
        }
        
        try {
            userService.toggleUserStatus(id);
            redirectAttributes.addFlashAttribute("success", "User status updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating user status: " + e.getMessage());
        }
        
        return "redirect:/admin/users";
    }

    @GetMapping("/insurance")
    public String manageInsurance(HttpSession session, Model model) {
        if (!checkAdminAccess(session)) {
            return "redirect:/login";
        }
        
        try {
            List<InsuranceCompany> insuranceCompanies = insuranceService.getAllInsuranceCompanies();
            model.addAttribute("insuranceCompanies", insuranceCompanies);
            model.addAttribute("currentPage", "admin-insurance");
            model.addAttribute("pageTitle", "Manage Insurance Companies");
            
            return "admin/insurance";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading insurance companies: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/claims")
    public String manageClaims(HttpSession session, Model model,
                             @RequestParam(required = false) String status) {
        if (!checkAdminAccess(session)) {
            return "redirect:/login";
        }
        
        try {
            List<InsuranceClaim> claims;
            if (status != null) {
                claims = insuranceService.getClaimsByStatus(status);
            } else {
                claims = insuranceService.getAllClaims();
            }
            
            model.addAttribute("claims", claims);
            model.addAttribute("claimStatuses", insuranceService.getAllClaimStatuses());
            model.addAttribute("currentPage", "admin-claims");
            model.addAttribute("pageTitle", "Manage Insurance Claims");
            
            return "admin/claims";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading claims: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/claims/update-status/{id}")
    public String updateClaimStatus(@PathVariable Long id,
                                  @RequestParam String status,
                                  @RequestParam(required = false) String remarks,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {
        if (!checkAdminAccess(session)) {
            return "redirect:/login";
        }
        
        try {
            insuranceService.updateClaimStatus(id, status, remarks);
            redirectAttributes.addFlashAttribute("success", "Claim status updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating claim: " + e.getMessage());
        }
        
        return "redirect:/admin/claims";
    }

    @GetMapping("/analytics")
    public String analytics(HttpSession session, Model model) {
        if (!checkAdminAccess(session)) {
            return "redirect:/login";
        }
        
        try {
            Map<String, Object> analyticsData = Map.of(
                "monthlyAppointments", appointmentService.getMonthlyAppointmentStats(),
                "doctorStats", doctorService.getDoctorStatistics(),
                "userGrowth", userService.getUserGrowthStats(),
                "revenueData", appointmentService.getRevenueAnalytics(),
                "topSpecializations", doctorService.getTopSpecializations(),
                "appointmentStatusDistribution", appointmentService.getStatusDistribution()
            );
            
            model.addAttribute("analyticsData", analyticsData);
            model.addAttribute("currentPage", "admin-analytics");
            model.addAttribute("pageTitle", "Analytics & Reports");
            
            return "admin/analytics";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading analytics: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/settings")
    public String adminSettings(HttpSession session, Model model) {
        if (!checkAdminAccess(session)) {
            return "redirect:/login";
        }
        
        model.addAttribute("currentPage", "admin-settings");
        model.addAttribute("pageTitle", "System Settings");
        
        return "admin/settings";
    }