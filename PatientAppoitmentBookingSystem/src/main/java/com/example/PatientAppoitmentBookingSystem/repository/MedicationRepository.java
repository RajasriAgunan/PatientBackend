package com.example.PatientAppoitmentBookingSystem.repository;

import com.example.PatientAppoitmentBookingSystem.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    @Query("SELECT a FROM Medication a WHERE a.appointment.id = :app_id")
    List<Medication> findMedicationByAppointmentId(Long app_id);

}
