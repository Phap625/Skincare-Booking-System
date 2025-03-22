package edu.uth.jpa.models;
import jakarta.persistence.*;
import java.util.*;
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    //getter setter

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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
