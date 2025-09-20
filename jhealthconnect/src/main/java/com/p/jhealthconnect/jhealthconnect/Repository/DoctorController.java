package com.p.jhealthconnect.jhealthconnect.Controller;

import com.p.jhealthconnect.jhealthconnect.model.Doctor;
import com.p.jhealthconnect.jhealthconnect.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String showDoctorsPage(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "Doctor";
    }

    @GetMapping("/search")
    public String searchDoctors(@RequestParam(required = false) String specialization,
            @RequestParam(required = false) String name,
            Model model) {
        List<Doctor> doctors;

        if (specialization != null && !specialization.isEmpty()) {
            doctors = doctorService.searchBySpecialization(specialization);
        } else if (name != null && !name.isEmpty()) {
            doctors = doctorService.searchByName(name);
        } else {
            doctors = doctorService.getAllDoctors();
        }

        model.addAttribute("doctors", doctors);
        return "Doctor";
    }

    @GetMapping("/book/{doctorId}")
    public String redirectToAppointmentBooking(@PathVariable Long doctorId,
            RedirectAttributes redirectAttributes) {
        Optional<Doctor> doctor = doctorService.getDoctorById(doctorId);
        if (doctor.isPresent()) {
            redirectAttributes.addAttribute("doctorId", doctorId);
            return "redirect:/appointments/book";
        } else {
            redirectAttributes.addFlashAttribute("error", "Doctor not found");
            return "redirect:/doctors";
        }
    }
}