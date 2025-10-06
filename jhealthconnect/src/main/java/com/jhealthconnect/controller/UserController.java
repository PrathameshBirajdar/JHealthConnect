// ========================================
// 1. UserController.java - MATCHES YOUR STRUCTURE
// ========================================
package com.jhealthconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jhealthconnect.entity.User;
import com.jhealthconnect.service.DoctorService;
import com.jhealthconnect.service.RecommendationService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private RecommendationService recommendationService;

    // Main user dashboard - returns user/layout.html
    @GetMapping({"/layout", ""})
    public String userHome(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("recommendations", recommendationService.getAllRecommendations());
        return "user/layout";  // Points to templates/user/layout.html
    }

    // View all doctors - returns user/user-doctors.html
    @GetMapping("/doctors")
    public String listDoctors(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "user/user-doctors";
    }

    // View single doctor - returns user/doctors-details.html
    @GetMapping("/doctor/{id}")
    public String viewDoctor(@PathVariable Long id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        model.addAttribute("doctor", doctorService.getDoctorById(id));
        return "user/doctors-details";  // Points to templates/user/doctors-details.html
    }

    // View recommendations - returns user/user-recommendation.html
    @GetMapping("/recommendations")
    public String listRecommendations(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        model.addAttribute("recommendations", recommendationService.getAllRecommendations());
        return "user/user-recommendation";
    }
}
