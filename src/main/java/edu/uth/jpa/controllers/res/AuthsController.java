package edu.uth.jpa.controllers.res;

import edu.uth.jpa.models.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import edu.uth.jpa.dtos.AuthResponse;
import edu.uth.jpa.dtos.LoginRequest;
import edu.uth.jpa.dtos.RegisterDTO;
import edu.uth.jpa.jwt.JwtUtil;
import edu.uth.jpa.services.UserServices;

import javax.sql.RowSet;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auths")
public class AuthsController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServices UserServices;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            UserDetails userDetails = UserServices.loadUserByUsername(request.getUsername());
            String role = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(",")); // nếu bạn có nhiều role, sẽ là "ROLE_ADMIN,ROLE_USER" v.v.

            String token = jwtUtil.generateToken(userDetails.getUsername(), role);

            // 👉 Tạo AuthResponse và set thêm role
            AuthResponse response = new AuthResponse(token);
            response.setRole(role);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai thông tin đăng nhập");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO) {
        String result = UserServices.registerUser(registerDTO);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid token!");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Có thể trả về username hoặc cả object user tùy ý
        return ResponseEntity.ok(Map.of(
                "username",userDetails.getUsername(),
                "role",userDetails.getAuthorities()
        ));
    }


}
