package com.example.PatientAppoitmentBookingSystem.service;

import com.example.PatientAppoitmentBookingSystem.dto.DoctorRegisterDto;
import com.example.PatientAppoitmentBookingSystem.dto.JwtAuthResponse;
import com.example.PatientAppoitmentBookingSystem.dto.LoginRequestDto;
import com.example.PatientAppoitmentBookingSystem.dto.PatientRegisterDto;

public interface AuthService {
    String DoctorRegister(DoctorRegisterDto doctorRegisterDto);

    String PatientRegister(PatientRegisterDto patientRegisterDto);

    JwtAuthResponse login(LoginRequestDto loginRequestDto);
}