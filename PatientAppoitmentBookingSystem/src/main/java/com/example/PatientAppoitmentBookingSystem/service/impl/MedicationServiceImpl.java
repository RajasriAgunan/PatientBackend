package com.example.PatientAppoitmentBookingSystem.service.impl;


import com.example.PatientAppoitmentBookingSystem.dto.MedicationDto;
import com.example.PatientAppoitmentBookingSystem.entity.Appointment;
import com.example.PatientAppoitmentBookingSystem.entity.Medication;
import com.example.PatientAppoitmentBookingSystem.exception.ResourceNotFoundException;
import com.example.PatientAppoitmentBookingSystem.repository.AppointmentRepository;
import com.example.PatientAppoitmentBookingSystem.repository.MedicationRepository;
import com.example.PatientAppoitmentBookingSystem.service.MedicationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicationServiceImpl implements MedicationService {
    private MedicationRepository medicationRepository;
    private AppointmentRepository appointmentRepository;
    private ModelMapper modelMapper;

    @Override
    public MedicationDto saveMedication(MedicationDto medicationDto) {
        Appointment appointment = appointmentRepository.findById(medicationDto.getAppointment().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", medicationDto.getAppointment().getId()));

        medicationDto.setAppointment(appointment);
        Medication medication = modelMapper.map(medicationDto, Medication.class);
        return modelMapper.map(medicationRepository.save(medication), MedicationDto.class);
    }

    @Override
    public List<MedicationDto> getAllMedication() {
        return medicationRepository.findAll()
                .stream().map(medication -> modelMapper.map(medication, MedicationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MedicationDto getMedicationById(Long id) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medication", "id", id));
        return modelMapper.map(medication, MedicationDto.class);
    }

    @Override
    public MedicationDto updateMedication(Long id, MedicationDto medicationDto) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medication", "id", id));
        medication.setId(medicationDto.getId());

        Appointment appointment = appointmentRepository.findById(medicationDto.getAppointment().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", medicationDto.getAppointment().getId()));

        medication.setAppointment(appointment);
        medication.setPrescription(medicationDto.getPrescription());
        medication.setNotes(medicationDto.getNotes());
        return modelMapper.map(medicationRepository.save(medication), MedicationDto.class);
    }


    @Override
    public void deleteMedication(Long id) {
        Medication existingMedication = medicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medication", "id", id));
        medicationRepository.deleteById(id);
    }

    @Override
    public List<Medication> findMedicationByAppointmentId(Long app_id) {
        return medicationRepository.findMedicationByAppointmentId(app_id);
    }

}
