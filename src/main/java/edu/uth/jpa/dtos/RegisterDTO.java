package edu.uth.jpa.dtos;
import edu.uth.jpa.enums.userRole;
import lombok.Data;
import lombok.Getter;

@Getter
@Data

public class RegisterDTO {
    private String username;
    private String password;
    private String role;
    private String email;


}
