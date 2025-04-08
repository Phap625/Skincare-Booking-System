package edu.uth.jpa.repositories;

import edu.uth.jpa.models.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
