package com.example.PatientAppoitmentBookingSystem.repository;

import com.example.PatientAppoitmentBookingSystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);
    Boolean existsByEmail(String email);
}
