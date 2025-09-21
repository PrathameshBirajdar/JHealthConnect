package com.jhealthconnect.controller;

import com.jhealthconnect.service.AppointmentService;
import com.jhealthconnect.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends BaseController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("topDoctors", doctorService.getTopDoctors(5));
        model.addAttribute("pendingAppointments", appointmentService.getPendingAppointments());
        return "index";
    }
}
