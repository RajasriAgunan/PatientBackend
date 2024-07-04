package com.example.PatientAppoitmentBookingSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRegisterDto {
    private Long id;
    private String fullName;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private String designation;
    private String specialization;
    private String yearsOfExp;
    private String password;

    // Availability details
    private Long consultingFees;
    private Long consultingHrs;
    private String availabilityFromTime;
    private String availabilityToTime;
    private String status;
}
