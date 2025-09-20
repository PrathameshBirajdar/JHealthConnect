package com.p.jhealthconnect.jhealthconnect.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageContoller {

    @GetMapping("/")
    public String home() {
        return "index"; // loads templates/index.html
    }

    @GetMapping("/doctors")
    public String doctors() {
        return "Doctor"; // loads templates/Doctor.html
    }

    @GetMapping("/appointments")
    public String appointments() {
        return "Appointment"; // loads templates/Appointment.html
    }

    @GetMapping("/insurance")
    public String insurance() {
        return "Insurance"; // loads templates/Insurance.html
    }
}
