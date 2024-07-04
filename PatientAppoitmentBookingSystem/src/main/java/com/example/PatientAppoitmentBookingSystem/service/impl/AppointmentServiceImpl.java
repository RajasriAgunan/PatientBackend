package com.example.PatientAppoitmentBookingSystem.service.impl;


import com.example.PatientAppoitmentBookingSystem.dto.AppointmentDto;
import com.example.PatientAppoitmentBookingSystem.entity.Appointment;
import com.example.PatientAppoitmentBookingSystem.entity.Doctor;
import com.example.PatientAppoitmentBookingSystem.entity.Patient;
import com.example.PatientAppoitmentBookingSystem.exception.ResourceNotFoundException;
import com.example.PatientAppoitmentBookingSystem.repository.AppointmentRepository;
import com.example.PatientAppoitmentBookingSystem.repository.DoctorRepository;
import com.example.PatientAppoitmentBookingSystem.repository.PatientRepository;
import com.example.PatientAppoitmentBookingSystem.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    @Override
    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        if (checkAvailability(appointmentDto) > 0) {
            try {
                throw new Exception("Appointment slot already booked!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", appointmentDto.getDoctor().getId()));
        appointmentDto.setDoctor(doctor);

        Patient patient = patientRepository.findById(appointmentDto.getPatient().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", appointmentDto.getPatient().getId()));
        appointmentDto.setPatient(patient);
        Appointment appointment = modelMapper.map(appointmentDto, Appointment.class);
        return modelMapper.map(appointmentRepository.save(appointment), AppointmentDto.class);
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream().map(appointment -> modelMapper.map(appointment, AppointmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDto getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", id));
        return modelMapper.map(appointment, AppointmentDto.class);
    }

    @Override
    public void cancelAppointment(Long id) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", id));
        appointmentRepository.deleteById(id);
    }

    @Override
    public int checkAvailability(AppointmentDto appointmentDto) {
        return appointmentRepository.checkAvailability(
                appointmentDto.getDoctor().getId(),
                appointmentDto.getAppointment_date(),
                appointmentDto.getAppointment_from_time(),
                appointmentDto.getAppointment_to_time());
    }

    @Override
    public List<Appointment> findAllAppointmentByDoctorId(Long id) {
        return appointmentRepository.findAllAppointmentByDoctorId(id);
    }

    @Override
    public List<Appointment> findAllAppointmentByPatientId(Long id) {
        return appointmentRepository.findAllAppointmentByPatientId(id);
    }

    @Override
    public AppointmentDto changeAppointmentStatus(Long id, AppointmentDto appointmentDto) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", id));
        appointment.setId(appointmentDto.getId());

        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", appointmentDto.getDoctor().getId()));
        ((Appointment) appointment).setDoctor(doctor);

        Patient patient = patientRepository.findById(appointmentDto.getPatient().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", appointmentDto.getPatient().getId()));
        appointment.setPatient(patient);

        appointment.setAppointment_date(appointmentDto.getAppointment_date());
        appointment.setAppointment_from_time(appointmentDto.getAppointment_from_time());
        appointment.setAppointment_to_time(appointmentDto.getAppointment_to_time());
        appointment.setAppointment_type(appointmentDto.getAppointment_type());
        appointment.setAppointment_status(appointmentDto.getAppointment_status());
        return modelMapper.map(appointmentRepository.save(appointment), AppointmentDto.class);
    }


}
