package edu.uth.jpa.models;
import edu.uth.jpa.enums.userRole;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Getter
@Setter
@Table(name="users")
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));

    }


    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
