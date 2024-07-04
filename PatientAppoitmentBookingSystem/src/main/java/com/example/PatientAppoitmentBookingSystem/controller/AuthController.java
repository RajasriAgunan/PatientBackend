package com.example.PatientAppoitmentBookingSystem.controller;


import com.example.PatientAppoitmentBookingSystem.dto.DoctorRegisterDto;
import com.example.PatientAppoitmentBookingSystem.dto.JwtAuthResponse;
import com.example.PatientAppoitmentBookingSystem.dto.LoginRequestDto;
import com.example.PatientAppoitmentBookingSystem.dto.PatientRegisterDto;
import com.example.PatientAppoitmentBookingSystem.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private AuthService authService;

 @PostMapping("login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginRequestDto requestDto) {
        JwtAuthResponse jwtAuthResponse = authService.login(requestDto);
        return ResponseEntity.ok(jwtAuthResponse);
    }


   @PostMapping("patient-register")
    public ResponseEntity<String> PatientRegister(@RequestBody PatientRegisterDto registerDto) {
        String response = authService.PatientRegister(registerDto);
        return ResponseEntity.ok(response);
    }


    @PostMapping("doctor-register")
    public ResponseEntity<String> DoctorRegister(@RequestBody DoctorRegisterDto doctorRegisterDto) {
        String response = authService.DoctorRegister(doctorRegisterDto);
        return ResponseEntity.ok(response);
    }

}
