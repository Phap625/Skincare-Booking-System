package edu.uth.jpa.dtos;
import lombok.Data;
@Data

public class RegisterDTO {
    private String username;
    private String password;
    private String role;
    private String email;

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }


}
