package com.jhealthconnect.controller;

import lombok.Data;

@Data
public class AppointmentRequest {
    private Long doctorId;
    private String appointmentDate;
    private String patientName;
    private Integer age;
    private String gender;
    private String contact;
}
