package com.example.PatientAppoitmentBookingSystem.controller;


import com.example.PatientAppoitmentBookingSystem.dto.DoctorRegisterDto;
import com.example.PatientAppoitmentBookingSystem.dto.PatientRegisterDto;
import com.example.PatientAppoitmentBookingSystem.service.DoctorPatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("api/")
public class DoctorPatientController {
    private DoctorPatientService doctorPatientService;

    @PreAuthorize("hasAnyRole(\"ADMIN\", \"PATIENT\",\"DOCTOR\")")
    @GetMapping("doctor-list")
    public ResponseEntity<List<DoctorRegisterDto>> getDoctorList() {
        List<DoctorRegisterDto> doctorList = doctorPatientService.getDoctorList();
        return ResponseEntity.ok().body(doctorList);
    }

    @PreAuthorize("hasAnyRole(\"ADMIN\", \"PATIENT\",\"DOCTOR\")")
    @GetMapping("doctor-list/{id}")
    public ResponseEntity<DoctorRegisterDto> getDoctorById(@PathVariable("id") Long id) {
        DoctorRegisterDto doctorRegisterDto = doctorPatientService.getDoctorById(id);
        return ResponseEntity.ok().body(doctorRegisterDto);
    }

    @PreAuthorize("hasAnyRole(\"ADMIN\",\"DOCTOR\")")
    @GetMapping("doctor/my-profile")
    public ResponseEntity<DoctorRegisterDto> findDoctorByEmail(@RequestParam("email") String email) {
        DoctorRegisterDto doctorRegisterDto = doctorPatientService.findDoctorByEmail(email);
        return ResponseEntity.ok().body(doctorRegisterDto);
    }
    @PreAuthorize("hasAnyRole(\"ADMIN\", \"DOCTOR\")")
    @PutMapping("doctor/my-profile/{id}")
    public ResponseEntity<DoctorRegisterDto> editDoctorDetail(@PathVariable("id") Long id, @RequestBody DoctorRegisterDto doctorRegisterDto) {
        DoctorRegisterDto updatedAvailability = doctorPatientService.editDoctorDetail(id, doctorRegisterDto);
        return ResponseEntity.ok(updatedAvailability);
    }
    @PreAuthorize("hasRole(\"ADMIN\")")
    @DeleteMapping("doctor/delete/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        doctorPatientService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor deleted successfully!");
    }
    @PreAuthorize("hasAnyRole(\"ADMIN\")")
    @GetMapping("patient-list")
    public ResponseEntity<List<PatientRegisterDto>> getPatientList() {
        List<PatientRegisterDto> patientList = doctorPatientService.getPatientList();
        return ResponseEntity.ok().body(patientList);
    }
    @PreAuthorize("hasAnyRole(\"ADMIN\", \"PATIENT\",\"DOCTOR\")")
    @GetMapping("patient-list/{id}")
    public ResponseEntity<PatientRegisterDto> getPatientById(@PathVariable("id") Long id) {
        PatientRegisterDto patient = doctorPatientService.getPatientById(id);
        return ResponseEntity.ok().body(patient);
    }
    @PreAuthorize("hasAnyRole(\"ADMIN\", \"PATIENT\")")
    @GetMapping("patient/my-profile")
    public ResponseEntity<PatientRegisterDto> findPatientByEmail(@RequestParam("email") String email) {
        PatientRegisterDto patient = doctorPatientService.findPatientByEmail(email);
        return ResponseEntity.ok().body(patient);
    }
    @PreAuthorize("hasAnyRole(\"ADMIN\", \"PATIENT\")")
    @PutMapping("patient/my-profile/{id}")
    public ResponseEntity<PatientRegisterDto> editpatientDetail(@PathVariable("id") Long id, @RequestBody PatientRegisterDto patientRegisterDto) {
        PatientRegisterDto updatedPatient = doctorPatientService.editpatientDetail(id, patientRegisterDto);
        return ResponseEntity.ok(updatedPatient);
    }
    @PreAuthorize("hasAnyRole(\"ADMIN\")")
    @DeleteMapping("patient/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        doctorPatientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully!");
    }

}