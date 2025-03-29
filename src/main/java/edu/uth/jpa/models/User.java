package edu.uth.jpa.models;
import jakarta.persistence.*;
import java.util.*;
import lombok.Getter;
import lombok.Setter;

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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    private String role;
    //getter setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String username, String password,String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public User() {}
    @ManyToMany
    @JoinTable(
            name = "user_permission",
            joinColumns = @JoinColumn(name = "user_id"),  // Khóa ngoại của User
            inverseJoinColumns = @JoinColumn(name = "permission_id") // Khóa ngoại của Permission
    )
    private Set<Permission> permissions=new HashSet<>();
    public Set<Permission> getPermissions() {return this.permissions;}
    public void setPermissions(Set<Permission> permissions) { this.permissions = permissions; }
}
