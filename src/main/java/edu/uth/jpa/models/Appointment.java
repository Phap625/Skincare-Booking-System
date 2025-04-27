package edu.uth.jpa.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String date;
    private String department;
    private String doctor;
    private String message;

    private String service; // nếu có form đặt dịch vụ
    private String username; // 🔥 thêm để lưu ai đã đặt
    private String role;
}
