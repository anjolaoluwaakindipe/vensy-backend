package com.dalevents.vensy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dalevents.vensy.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
