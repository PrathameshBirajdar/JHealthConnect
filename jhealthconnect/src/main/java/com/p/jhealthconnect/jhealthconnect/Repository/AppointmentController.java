package com.p.jhealthconnect.jhealthconnect.Controller;

import com.p.jhealthconnect.jhealthconnect.model.Appointment;
import com.p.jhealthconnect.jhealthconnect.model.Doctor;
import com.p.jhealthconnect.jhealthconnect.service.AppointmentService;
import com.p.jhealthconnect.jhealthconnect.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/book")
    public String showAppointmentForm(Model model,
            @RequestParam(required = false) Long doctorId) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        Appointment appointment = new Appointment();

        // If doctorId is provided, pre-select the doctor
        if (doctorId != null) {
            Optional<Doctor> selectedDoctor = doctorService.getDoctorById(doctorId);
            selectedDoctor.ifPresent(appointment::setDoctor);
        }

        model.addAttribute("doctors", doctors);
        model.addAttribute("appointment", appointment);
        return "Appointment";
    }

    @PostMapping("/book")
    public String bookAppointment(@Valid @ModelAttribute("appointment") Appointment appointment,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // If validation fails, reload the form with errors
            List<Doctor> doctors = doctorService.getAllDoctors();
            model.addAttribute("doctors", doctors);
            return "Appointment";
        }

        try {
            // Check if doctor is available on the selected date
            if (appointment.getDoctor() != null && appointment.getAppointmentDate() != null) {
                boolean isAvailable = appointmentService.isDoctorAvailable(
                        appointment.getDoctor(), appointment.getAppointmentDate());

                if (!isAvailable) {
                    List<Doctor> doctors = doctorService.getAllDoctors();
                    model.addAttribute("doctors", doctors);
                    model.addAttribute("error",
                            "Doctor is not available on the selected date. Please choose another date.");
                    return "Appointment";
                }
            }

            appointmentService.saveAppointment(appointment);
            redirectAttributes.addFlashAttribute("success",
                    "Appointment booked successfully! We will confirm your appointment shortly.");
            return "redirect:/appointments/success";

        } catch (Exception e) {
            List<Doctor> doctors = doctorService.getAllDoctors();
            model.addAttribute("doctors", doctors);
            model.addAttribute("error", "An error occurred while booking the appointment. Please try again.");
            return "Appointment";
        }
    }

    @GetMapping("/success")
    public String appointmentSuccess() {
        return "appointment-success";
    }

    @GetMapping("/list")
    public String listAllAppointments(Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointment-list";
    }

    @GetMapping("/search")
    public String searchAppointments(@RequestParam(required = false) String patientName,
                                   @RequestParam(required = false) String status,
                                   Model model) {
        List<Appointment> appointments;
        
        if (patientName != null && !patientName.isEmpty()) {
            appointments = appointmentService.searchByPatientName(patientName);
        } else if (status != null && !status.isEmpty()) {
            Appointment.AppointmentStatus appointmentStatus = Appointment.AppointmentStatus.valueOf(status);
            appointments = appointmentService.getAppointmentsByStatus(appointmentStatus);
        } else {
            appointments = appointmentService.getAllAppointments();
        }
        
        model.addAttribute("appointments", appointments);
        return "appointment-list";
    }

    @PostMapping("/update-status/{id}")
    public String updateAppointmentStatus(@PathVariable Long id,
                                        @RequestParam String status,
                                        RedirectAttributes redirectAttributes) {
        try {
            Optional<Appointment> appointmentOpt = appointmentService.getAppointmentById(id);
            if (appointmentOpt.isPresent()) {
                Appointment appointment = appointmentOpt.get();
                appointment.setStatus(Appointment.AppointmentStatus.valueOf(status));
                appointmentService.saveAppointment(appointment);
                redirectAttributes.addFlashAttribute("success", "Appointment status updated successfully!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Appointment not found!");
            }
        } catch (Exception