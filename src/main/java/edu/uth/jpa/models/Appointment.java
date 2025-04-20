
package edu.uth.jpa.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
public class Appointment  {
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public void setService(String service) {
    }
}
