package edu.uth.jpa.models.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

    private LocalDateTime date;
    private String department;
    private String doctor;
    private String message;

    public void setService(String service) {
    }
}
