package com.example.PatientAppoitmentBookingSystem.controller;

import com.example.PatientAppoitmentBookingSystem.dto.AppointmentDto;
import com.example.PatientAppoitmentBookingSystem.entity.Appointment;
import com.example.PatientAppoitmentBookingSystem.service.impl.AppointmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("api/appointment")
public class AppointmentController {
    private AppointmentServiceImpl appointmentService;


    @PreAuthorize("hasAnyRole(\"ADMIN\", \"PATIENT\",\"DOCTOR\")")
    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody AppointmentDto appointmentDto) {
        AppointmentDto savedAppointment = appointmentService.createAppointment(appointmentDto);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole(\"ADMIN\")")
    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        List<AppointmentDto> appointmentDtoList = appointmentService.getAllAppointments();
        return ResponseEntity.ok().body(appointmentDtoList);
    }
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"DOCTOR\", \"PATIENT\")")
    @GetMapping("{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable("id") Long id) {
        AppointmentDto appointmentDto = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok().body(appointmentDto);
    }
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"DOCTOR\")")
    @PutMapping("{id}")
    public ResponseEntity<AppointmentDto> changeAppointmentStatus(@PathVariable("id") Long id, @RequestBody AppointmentDto appointmentDto) {
        AppointmentDto updateStatus = appointmentService.changeAppointmentStatus(id, appointmentDto);
        return ResponseEntity.ok().body(updateStatus);
    }

    @PreAuthorize("hasAnyRole(\"ADMIN\",\"DOCTOR\", \"PATIENT\")")
    @DeleteMapping("{id}")
    public ResponseEntity<String> cancelAppointment(@PathVariable long id) {
        appointmentService.cancelAppointment(id);
        return ResponseEntity.ok("Appointment Cancelled successfully!");
    }
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"DOCTOR\", \"PATIENT\")")
    @GetMapping("doctor/{id}")
    public ResponseEntity<List<Appointment>> findAllAppointmentByDoctorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(appointmentService.findAllAppointmentByDoctorId(id));
    }
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"DOCTOR\", \"PATIENT\")")
    @GetMapping("patient/{id}")
    public ResponseEntity<List<Appointment>> findAllAppointmentByPatientId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(appointmentService.findAllAppointmentByPatientId(id));
    }


}