package edu.uth.jpa.dtos;
import edu.uth.jpa.enums.userRole;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
public class AuthResponse {
    private String token;
    private String role;
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter
}
