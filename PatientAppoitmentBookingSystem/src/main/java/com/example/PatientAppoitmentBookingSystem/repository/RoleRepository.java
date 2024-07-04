package com.example.PatientAppoitmentBookingSystem.repository;

import com.example.PatientAppoitmentBookingSystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
