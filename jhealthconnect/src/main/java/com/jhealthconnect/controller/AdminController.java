// ========================================
// 2. AdminController.java - MATCHES YOUR STRUCTURE
// ========================================
package com.jhealthconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhealthconnect.entity.Doctor;
import com.jhealthconnect.entity.Recommendation;
import com.jhealthconnect.entity.User;
import com.jhealthconnect.service.DoctorService;
import com.jhealthconnect.service.RecommendationService;
import com.jhealthconnect.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private UserService userService;

    // Main admin dashboard - returns admin/layout.html
    @GetMapping({"/layout", ""})
    public String adminDashboard(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("recommendations", recommendationService.getAllRecommendations());
        return "admin/layout";  // Points to templates/admin/layout.html
    }

    // Manage doctors - returns admin/admin-doctors.html
    @GetMapping("/doctors")
    public String manageDoctors(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "admin/admin-doctors";
    }

    @PostMapping("/doctors/save")
    public String saveDoctor(@ModelAttribute Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "redirect:/admin/doctors";
    }

    @GetMapping("/doctor/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/admin/doctors";
    }

    // Manage recommendations - returns admin/admin-recommendation.html
    @GetMapping("/recommendations")
    public String manageRecommendations(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        model.addAttribute("recommendations", recommendationService.getAllRecommendations());
        model.addAttribute("doctors", doctorService.getActiveDoctors());
        return "admin/admin-recommendation";
    }

    @PostMapping("/recommendations/save")
    public String saveRecommendation(@ModelAttribute Recommendation recommendation, @RequestParam Long doctorId) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        recommendation.setDoctor(doctor);
        recommendationService.saveRecommendation(recommendation);
        return "redirect:/admin/recommendations";
    }

    @GetMapping("/recommendation/delete/{id}")
    public String deleteRecommendation(@PathVariable Long id) {
        recommendationService.deleteRecommendation(id);
        return "redirect:/admin/recommendations";
    }
}
