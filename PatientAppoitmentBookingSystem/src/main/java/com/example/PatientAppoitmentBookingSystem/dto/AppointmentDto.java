package com.example.PatientAppoitmentBookingSystem.dto;

import com.example.PatientAppoitmentBookingSystem.entity.Doctor;
import com.example.PatientAppoitmentBookingSystem.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private Long id;
    private Doctor doctor;
    private Patient patient;
    private String appointment_date;
    private String appointment_from_time;
    private String appointment_to_time;
    private String appointment_type;
    private String appointment_status;
}