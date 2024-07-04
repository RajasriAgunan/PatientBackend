package com.example.PatientAppoitmentBookingSystem.service;

import com.example.PatientAppoitmentBookingSystem.dto.MedicationDto;
import com.example.PatientAppoitmentBookingSystem.entity.Medication;

import java.util.List;

public interface MedicationService {
    MedicationDto saveMedication(MedicationDto medicationDto);
    List<MedicationDto> getAllMedication();
    MedicationDto getMedicationById(Long id);
    MedicationDto updateMedication(Long id, MedicationDto medicationDto);
    void deleteMedication(Long id);
    List<Medication> findMedicationByAppointmentId(Long app_id);
}