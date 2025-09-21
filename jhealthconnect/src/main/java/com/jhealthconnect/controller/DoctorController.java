package com.jhealthconnect.controller;

import com.jhealthconnect.entity.Doctor;
import com.jhealthconnect.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String listDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctors";
    }

    @GetMapping("/{id}")
    public String getDoctor(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            model.addAttribute("error", "Doctor not found!");
            return "error";
        }
        model.addAttribute("doctor", doctor);
        return "doctor_details";
    }
}
