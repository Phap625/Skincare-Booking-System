package edu.uth.jpa.models;
import edu.uth.jpa.enums.userRole;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

import lombok.*;


@Entity
@Getter
@Setter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String username;
    private String password;
    private String email;
    private String phone;
    private LocalDate dob;
    private String role;

    public User() {}

    public User(String username, String password, String email, String role,LocalDate dob) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.dob = dob;
    }


    @ManyToMany
    @JoinTable(
            name = "user_permission",
            joinColumns = @JoinColumn(name = "user_id"),  // Khóa ngoại của User
            inverseJoinColumns = @JoinColumn(name = "permission_id") // Khóa ngoại của Permission
    )
    private Set<Permission> permissions=new HashSet<>();

}
